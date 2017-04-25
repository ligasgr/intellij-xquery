/*
 * Copyright 2017 OverStory Ltd <copyright@overstory.co.uk> and other contributors
 * (see the CONTRIBUTORS file).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intellij.xquery.runner.rt.debugger.marklogic

import com.codnos.dbgp.api.Breakpoint
import com.marklogic.xcc.*
import com.marklogic.xcc.exceptions.RequestException
import com.marklogic.xcc.types.ValueType
import com.marklogic.xcc.types.XSInteger
import groovy.util.slurpersupport.GPathResult
import org.intellij.xquery.runner.rt.XQueryRunConfig
import org.intellij.xquery.runner.rt.debugger.BreakpointManager
import org.intellij.xquery.runner.rt.debugger.DebugFrame
import org.intellij.xquery.runner.rt.debugger.Variable

import java.util.regex.Pattern

import static org.intellij.xquery.runner.rt.debugger.LogUtil.log

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 4/16/17
 * Time: 12:13 AM
 */
@SuppressWarnings ("PublicMethodNotExposedInInterface")
class MarkLogicDebugConnector
{
	protected final XQueryRunConfig config
	private final Session session
	private final String initialFile

	MarkLogicDebugConnector (XQueryRunConfig config, Session session)
	{
		this.config = config
		this.session = session
		this.initialFile = new File (config.getMainFile()).toURI().toString()
	}

	// ---------------------------------------------------

	private ResultSequence evalRequest (String query, Map<String,Object> args = [:]) throws RequestException
	{
//log ("MarkLogicXdmpDebugger.evalRequest, query: " + query);
		Request request = session.newAdhocQuery (query)

//		for (String name : args.keySet()) {
		args.each { String name, Object value ->
			String strValue = (value == null) ? "" : value.toString()

//log ("    arg, name: " + name + ", value: " + args.get (name));

			if (value instanceof BigInteger) {
//					request.setNewVariable (new XName (name), ValueFactory.newXSInteger ((BigInteger) value))
				request.setNewVariable (name, ValueType.XS_INTEGER, value)
			} else if ((value instanceof Long) || (value instanceof Integer)) {
				request.setNewVariable (name, ValueType.XS_INTEGER, Long.parseLong (strValue))
			} else {
				request.setNewVariable (name, ValueType.XS_STRING, strValue)
			}
		}

		return session.submitRequest (request)
	}

	// ---------------------------------------------------

	private static final String DBG_EVAL_REQUEST = 'dbg-eval-request.xqy'

	BigInteger submitEvalForDebug (String query, Map<String,Object> args) throws Exception
	{
		log ("MarkLogicXdmpDebugger.submitEvalForDebug submitting debug request to MarkLogic")

		ResultSequence rs = evalRequest (xfile (DBG_EVAL_REQUEST), [__query__: query, __variables__: generateVarsXml (args)])
		BigInteger requestId = getSingleBigIntResult (rs)

		log ("MarkLogicXdmpDebugger.submitEvalForDebug back from call, requestId: " + requestId)

		return requestId
	}

	// ToDo: Finish this
	private static String generateVarsXml (Map<String,Object> args)
	{
		return "<variables/>"
	}

	// ---------------------------------------------------

	private static final String CLEAR_STOPPED_REQ = 'clear-stopped.xqy'

	void clearStoppedRequests() throws RequestException
	{
log ("MarkLogicXdmpDebugger.clearStoppedRequests")
		evalRequest (xfile (CLEAR_STOPPED_REQ))
	}

	private static final String RESUME_REQ = 'resume-stopped.xqy'

	void resumeRequest (BigInteger requestId) throws RequestException
	{
log ("MarkLogicXdmpDebugger.resumeRequest")
		evalRequest (xfile (RESUME_REQ), [id: requestId])
	}

	// ---------------------------------------------------

	void setMlBreakPoints (BigInteger requestId, BreakpointManager breakpointManager) throws RequestException
	{
log ("MarkLogicXdmpDebugger.setMlBreakPoints, requestId: " + requestId)

		Map<Integer, Map<String, Breakpoint>> breakPoints = breakpointManager.allBreakpoints()

		breakPoints.each { Integer bpId, Map<String, Breakpoint> bpMap ->
			log ("setMlBreakPoints: id=" + bpId)

			bpMap.each { String mapId, Breakpoint bp ->
				log ("  Breakpoint: id: " + bp.getBreakpointId() + ", type: " + bp.getType() +
					", function: " + bp.getFunction() + ", line: " + bp.getLineNumber() + ", expr: " + bp.getExpression())

				setMlBreakPoint (requestId, bp)
			}
		}
	}

	private static final String SET_BP_REQ = 'setup-breakpoint.xqy'

	private void setMlBreakPoint (BigInteger requestId, Breakpoint bp) throws RequestException
	{
		String file = bp.getFileURL().get()
		Integer line = bp.getLineNumber().get()
//		String functionName = bp.getFunction().get();
log ("MarkLogicXdmpDebugger.setMlBreakPoint called")

		BigInteger exprId = firstExprOnLine (requestId, file, line)

		if (exprId == null) {
			System.err.println ("Cannot set ML breakpoint, no expression found on line " + line)
		} else {
log ("MarkLogicXdmpDebugger.setMlBreakPoint setting breakpoint, line: " + line + ", file: " + file + ", reqId: " + requestId + ", expr: " + exprId)
			evalRequest (xfile (SET_BP_REQ), [id: requestId, exprid: exprId])
log ("MarkLogicXdmpDebugger.setMlBreakPoint breakpoint set, line: " + line + ", file: " + file + ", reqId: " + requestId + ", expr: " + exprId)
		}
	}

	// ---------------------------------------------------

	private static final String EXPRS_REQ = 'get-expresisons.xqy'

	private BigInteger firstExprOnLine (BigInteger requestId, String file, Integer line) throws RequestException
	{
		ResultSequence rs = evalRequest (xfile (EXPRS_REQ), [id: requestId, uri: '', line: line.toString()])
		BigInteger expr = getSingleBigIntResult (rs)

		if (expr == null) {
log ("MarkLogicXdmpDebugger.firstExprOnLine returning null")
			return null
		} else {
log ("MarkLogicXdmpDebugger.firstExprOnLine " + expr)
			return expr
		}
	}

	// ---------------------------------------------------

	private static final String WAIT_REQ = 'wait-for-request.xqy'

	BigInteger waitForStateChange (BigInteger requestId, Integer timeoutSecs) throws RequestException
	{
		ResultSequence rs = evalRequest (xfile (WAIT_REQ), [id: requestId, timeout: timeoutSecs])
		BigInteger expr = getSingleBigIntResult (rs)

		if (expr == null) {
			log ("MarkLogicXdmpDebugger.waitForStateChange returning null")
			return null;
		} else {
			log ("MarkLogicXdmpDebugger.waitForStateChange " + expr)
			return expr;
		}
	}

	// ---------------------------------------------------

	private static final String GET_STATUS_REQ = 'get-request-status.xqy'

	Map<String,String> getRequestStatus (BigInteger requestId) throws RequestException
	{
		ResultSequence rs = evalRequest (xfile (GET_STATUS_REQ), [id: requestId])
		Map<String,String> stat = new HashMap<>()

		if (rs.size() > 0) stat ['xml'] = rs.itemAt (0).asString()
		if (rs.size() > 1) stat ['id'] = rs.itemAt (1).asString()
		if (rs.size() > 2) stat ['req-status'] = rs.itemAt (2).asString()
		if (rs.size() > 3) stat ['debug-status'] = rs.itemAt (3).asString()
		if (rs.size() > 4) stat ['where-stopped'] = rs.itemAt (4).asString()

//		log ("MarkLogicXdmpDebugger.getRequestStatus returning: " + stat);

		return stat
	}

	// ---------------------------------------------------

	private static final String GET_REQ_STACK = 'get-request-stack.xqy'

	List<DebugFrame> requestStackFrames (BigInteger requestId)
	{
		ResultSequence rs = evalRequest (xfile (GET_REQ_STACK), [id: requestId])
		log ("requestStackFrames (raw): ${rs.asString()}")
		GPathResult stack = new XmlSlurper (false, true).parseText (rs.asString()).declareNamespace ([d: 'http://marklogic.com/xdmp/debug'])
		List<DebugFrame> debugFrames = []

		log ("requestStackFrames: ${stack}")

//		debugFrames << new MarklogicDebugFrame (lineNumber: stack.line.text(), uri: stack.uri.text())

		stack.frame.each { GPathResult frame ->
			debugFrames << new MarklogicDebugFrame (
				lineNumber: Integer.parseInt (frame.line.text()),
				functionName: functionName (frame.operation.text()),
				uri: (frame.uri.text() != '') ?: initialFile,
				variables: frameVariables (frame)
			)

			log ("requestStackFrames: frame: ${debugFrames.last()}")
		}

		log ("returning requestStackFrames: size=${debugFrames.size()}")
		debugFrames
	}

	private static final Pattern funcPattern = Pattern.compile ('([-:_a-zA-Z0-9])+\\(.*\\)')

	private static String functionName (String operation)
	{
		if (operation.matches (funcPattern)) {
			operation.substring (0, operation.indexOf ('('))
		} else {
			operation.replaceAll ('"', '&quot;')
		}
	}

	private static List<Variable> frameVariables (GPathResult frame)
	{
		List<Variable> variables = []

		log ("frameVariables: Doing external-variables")
		frame.'external-variables'.'external-variable'.each { GPathResult variable ->
			String name = variableName (variable.name.text(), variable.'@xmlns'.text(), variable.prefix.text())
			// ToDo: fetch values from ML
//			String value = variable.value.text()
			variables << new Variable (name, 'item()*', null, 'external')
		}

		log ("frameVariables: Doing global-variables")
		frame.'global-variables'.'global-variable'.each { GPathResult variable ->
			String name = variableName (variable.name.text(), variable.'@xmlns'.text(), variable.prefix.text())
			// ToDo: fetch values from ML
//			String value = variable.value.text()
			variables << new Variable (name, 'item()*', null, 'global')
		}

		log ("frameVariables: Doing regular variables")
		frame.variables.variable.each { GPathResult variable ->
			String name = variableName (variable.name.text(), variable.'@xmlns'.text(), variable.prefix.text())
			String value = variable.value.text()
			variables << new Variable (name, guessVarType (value), value)
		}

		variables
	}

	private static String variableName (String name, String ns, String prefix)
	{
		String p = (prefix) ? "${prefix}:" : ''
		String n = (ns) ? "{${ns}}" : ''

		"${n}\$${p}${name}"
	}

	private static final String datePatternString = '[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]'
	private static final String timePatternString = '[0-2][0-9]:[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?'
	private static final Pattern datePattern = Pattern.compile (datePatternString)
	private static final Pattern dateTimePattern = Pattern.compile ("${datePatternString}T${timePatternString}")

	// ToDo: Should lookup the type from the parsed source
	private static String guessVarType (String value)
	{
		if ((value == null) || (value.length() == 0)) return '()'

		if (value.matches (dateTimePattern)) return 'xs:dateTime'
		if (value.matches (datePattern)) return 'xs:date'

		try { Long.parseLong (value); return 'xs:integer' } catch (Exception e) { /* nothing*/ }
		try { Double.parseDouble (value); return 'xs:decimal' } catch (Exception e) { /* nothing*/ }

		'xs:string'
	}

	// ---------------------------------------------------

	private static BigInteger getSingleBigIntResult (ResultSequence rs)
	{
		if (rs.size() == 0) return null

		ResultItem ri = rs.resultItemAt (0)

		if (ri.getItemType() != ValueType.XS_INTEGER) return null

		return ((XSInteger) ri.getItem()).asBigInteger()
	}

	// ---------------------------------------------------

	private static String SLASH = File.separator
	private String XPACKAGE = "${getClass().package.name.replace ('.', SLASH)}${SLASH}xquery${SLASH}"

	private String xfile (String name)
	{
		getClass().classLoader.getResource ("${XPACKAGE}${name}").text
	}

	// ---------------------------------------------------
}
