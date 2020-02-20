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
import com.marklogic.xcc.exceptions.XQueryException
import com.marklogic.xcc.types.ValueType
import com.marklogic.xcc.types.XSInteger
import groovy.util.slurpersupport.GPathResult
import groovy.xml.StreamingMarkupBuilder
import org.intellij.xquery.runner.rt.XQueryRunConfig
import org.intellij.xquery.runner.rt.XQueryRunnerVariable
import org.intellij.xquery.runner.rt.debugger.BreakpointManager
import org.intellij.xquery.runner.rt.debugger.DebugFrame
import org.intellij.xquery.runner.rt.debugger.LogUtil
import org.intellij.xquery.runner.rt.debugger.Variable
import static org.intellij.xquery.runner.rt.debugger.marklogic.MarkLogicRunMode.*

import java.util.regex.Pattern

import static org.intellij.xquery.runner.rt.FileUtil.readFile

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 4/16/17
 * Time: 12:13 AM
 */
@SuppressWarnings ("PublicMethodNotExposedInInterface")
class MarkLogicDebugConnector
{
	private final LogUtil log = new LogUtil();
	private static final int DEFAULT_CAPTURE_TIMEOUT = 120
	protected final XQueryRunConfig config
	private final Session session

	MarkLogicDebugConnector (XQueryRunConfig config, Session session)
	{
		this.config = config
		this.session = session
	}

	// ---------------------------------------------------

	private ResultSequence evalRequest (String query, Map<String,Object> args = [:]) throws RequestException
	{
		log.trace ("evalRequest, query: " + query)
		Request request = session.newAdhocQuery (query)

		args.each { String name, Object value ->
			String strValue = (value == null) ? "" : value.toString()

			if (value instanceof BigInteger) {
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

	private static final String DBG_REQUEST = 'dbg-request.xqy'

	BigInteger submitRequestForDebug() throws Exception
	{
		String uri = config.getMainFile()
		List<XQueryRunnerVariable> vars = config.getVariables()
		MarkLogicRunMode runMode = config.getMlDebugRunMode()
		String appserverRootPath = config.getMlDebugAppserverRoot()
		int connectTimeout = toInt (config.mlCaptureTimeoutSecs, DEFAULT_CAPTURE_TIMEOUT)
		BigInteger appserverId
		String appserverName = ""
		String argument

		switch (runMode) {
		case ADHOC:
			argument = readFile (uri)
			break
		case INVOKE:
			argument = mlFileUri (uri)
			break
		case CAPTURE_APPSERVER:
			appserverId = capturedAppserverId
			appserverName = getAppserverName (appserverId)
			argument = appserverId.toString()
			log.info "Attaching to appserver ${appserverName} for debugging, waiting for up to ${connectTimeout} seconds..."
			break
		default:
			throw new RuntimeException ("ACK! Unrecognized Run Mode: ${runMode}")
		}

		log.debug ("submitRequestForDebug submitting debug request to MarkLogic: runMode: ${runMode}, appserverRoot: ${appserverRootPath}, uri: ${mlFileUri (uri)}")

		ResultSequence rs = evalRequest (xfile (DBG_REQUEST), [mode: runMode.toString(), root: appserverRootPath, argument: argument, variables: generateVarsXml (vars), 'connect-timeout': connectTimeout])
		BigInteger requestId = getSingleBigIntResult (rs)

		if (requestId == null) {
			log.error ('submitRequestForDebug: requestId is null')
			throw new RuntimeException ("Cannot start request for debugging in '${runMode}' run mode")
		}

		log.trace ("submitRequestForDebug back from call, requestId: ${requestId}")

		// This is unnecessary, but will trigger an undefined external var exception
		// if any are undefined.  Need to do this first, because dbg:value() will hang if
		// the request encounters an XQuery error later.
		requestStackFrames (requestId)

		log.trace ("submitRequestForDebug: returning, requestId: ${requestId}")

		if (runMode == CAPTURE_APPSERVER) {
			log.info "Successfully attached to appserver '${appserverName}', debugging started"
		}

		return requestId
	}

	private int toInt (String value, int defaultValue)
	{
		try {
			Integer.parseInt (value)
		} catch (Exception e) {
			defaultValue
		}
	}

	private static final String GET_APPSERVER_ID = 'get-appserver-id.xqy'

	private BigInteger getCapturedAppserverId()
	{
		String appserver = config.mlDebugAppserver

		if ((appserver == null) || appserver.length() == 0) return 0

		ResultSequence rs = evalRequest (xfile (GET_APPSERVER_ID), [appserver: appserver])

		getSingleBigIntResult (rs)
	}

	private static final String GET_APPSERVER_NAME = 'get-appserver-name.xqy'

	private String getAppserverName (BigInteger id)
	{
		ResultSequence rs = evalRequest (xfile (GET_APPSERVER_NAME), [id: id])

		if (rs.size() > 0) {
			rs.itemAt (0).asString()
		} else {
			null
		}
	}

	private static String generateVarsXml (List<XQueryRunnerVariable> vars)
	{
		def builder = new StreamingMarkupBuilder()

		def xml = builder.bind {
			mkp.declareNamespace ([xsi: 'http://www.w3.org/2001/XMLSchema-instance', xs: 'http://www.w3.org/2001/XMLSchema'])
			'variables' {
				vars.each { XQueryRunnerVariable var ->
					if (var.ACTIVE) {
						'variable' (name: var.NAME, ns: var.NAMESPACE, 'xsi:type': var.TYPE, var.VALUE)
					}
				}
			}
		}

		return xml.toString()
	}

	// ---------------------------------------------------

	private static final String GET_REQUEST_VALUE = 'request-value.xqy'

	String requestValue (BigInteger requestId, String expr) throws RequestException
	{
		requestValueAndType (requestId, expr) [0]
	}

	List<String> requestValueAndType (BigInteger requestId, String expr) throws RequestException
	{
		try {
			ResultSequence rs = evalRequest (xfile (GET_REQUEST_VALUE), [id: requestId, expr: expr])

			[rs.itemAt (0).asString(), rs.itemAt (1).asString()]
		} catch (Exception e) {
			log.debug ("Cannot obtain value for '${expr}' from request '${requestId}': ${e.message}")
			throw e
		}
	}

	// ---------------------------------------------------

	private static final String CLEAR_STOPPED_REQ = 'clear-stopped.xqy'

	void clearStoppedRequests() throws RequestException
	{
		log.debug ("clearStoppedRequests")

		BigInteger serverId = (config.mlDebugRunMode == CAPTURE_APPSERVER) ? capturedAppserverId : BigInteger.ZERO

		try {
			evalRequest (xfile (CLEAR_STOPPED_REQ), ['captured-appserver-id': serverId])
		} catch (Exception e) {
			log.debug ("clearStoppedRequests: Caught exception on first attempt, trying again: " + e)

			try {
				evalRequest (xfile (CLEAR_STOPPED_REQ))
			} catch (Exception ex) {
				log.warn ("clearStoppedRequests: Caught exception on second attempt, stopping attempt to clear stopped requests: " + ex)
			}
		}
	}

	// ---------------------------------------------------

	private static final String RESUME_REQ = 'resume-stopped.xqy'

	void continueRequest (BigInteger requestId) throws RequestException
	{
		log.trace ("continueRequest")
		evalRequest (xfile (RESUME_REQ), [id: requestId])
	}

	private static final String RUN_TO_NEXT_BP = 'run-to-next-breakpoint.xqy'

	void runToNextBreakPoint (BigInteger requestId) throws RequestException
	{
		log.trace ("runToNextBreakPoint")
		evalRequest (xfile (RUN_TO_NEXT_BP), [id: requestId])
	}

	// ---------------------------------------------------

	private static final String STEP_OVER = 'step-over-expr.xqy'

	void stepOverExpression (BigInteger requestId) throws RequestException
	{
		log.trace ("stepOverExpression")
		evalRequest (xfile (STEP_OVER), [id: requestId])
	}

	// ---------------------------------------------------

	private static final String STEP_INTO = 'step-into-expr.xqy'

	void stepIntoExpression (BigInteger requestId) throws RequestException
	{
		log.trace ("stepIntoExpression")
		evalRequest (xfile (STEP_INTO), [id: requestId])
	}

	// ---------------------------------------------------

	private static final String STEP_OUT = 'step-out-of-expr.xqy'

	void stepOutOfExpression (BigInteger requestId) throws RequestException
	{
		log.trace ("stepOutOfExpression")
		evalRequest (xfile (STEP_OUT), [id: requestId, function: ''])
	}


	void stepOutOfFunction (BigInteger requestId, String functionName) throws RequestException
	{
		log.trace ("stepOutOfFunction: function=" + functionName)
		evalRequest (xfile (STEP_OUT), [id: requestId, function: functionName])
	}

	// ---------------------------------------------------

	private static final String SET_BP_REQ = 'setup-breakpoint.xqy'
	private static final String CLEAR_BPS_REQ = 'clear-all-breakpoints.xqy'


	void setMlBreakPoints (BigInteger requestId, BreakpointManager breakpointManager)
	{
		MarkLogicRunMode runMode = config.getMlDebugRunMode()
		String mainFile = config.mainFile

		log.debug ("setMlBreakPoints, requestId: " + requestId)

		evalRequest (xfile (CLEAR_BPS_REQ), [id: requestId])

		Map<Integer, Map<String, Breakpoint>> breakPoints = breakpointManager.allBreakpoints()

		breakPoints.each { Integer bpId, Map<String, Breakpoint> bpMap ->
			log.debug (" setMlBreakPoints: id=" + bpId)

			bpMap.each { String mapId, Breakpoint bp ->
				String file = bp.fileURL.orElse ('file:') - 'file:'
				if ((runMode == ADHOC) && ( ! file.equals (mainFile))) {
					// If running Ad Hoc, it doesn't make sense to set breakpoints in any files other than the one being eval'ed
					log.debug ("  Skipping out-of-scope breakpoint ${bp.getBreakpointId()} in ${file}")
				} else {
					log.debug ("  Setting breakpoint: id: " + bp.getBreakpointId() + ", type: " + bp.getType() +
						", function: " + bp.getFunction() + ", line: " + bp.getLineNumber() + ", expr: " + bp.getExpression())

					setMlBreakPoint (requestId, bp)
				}
			}
		}
	}

	private void setMlBreakPoint (BigInteger requestId, Breakpoint bp)
	{
		String file = bp.getFileURL().get()
		Integer line = bp.getLineNumber().get()
//		String functionName = bp.getFunction().get();
		log.info ("setMlBreakPoint called, reqId: ${requestId}, line: ${line}, file: ${file}")

		BigInteger exprId = exprForLine (requestId, file, line)

		if (exprId == null) {
			log.warn ("Cannot set ML breakpoint, no expression found on line ${line} in file '${file}' (may be out of scope)")
		} else {
			log.trace ("setMlBreakPoint setting breakpoint, line: " + line + ", file: " + file + ", reqId: " + requestId + ", expr: " + exprId)
			evalRequest (xfile (SET_BP_REQ), [id: requestId, exprid: exprId])
		}
	}

	// ---------------------------------------------------

	private static final String EXPRS_REQ = 'get-expresisons.xqy'

	private BigInteger exprForLine (BigInteger requestId, String file, Integer line) throws RequestException
	{
		MarkLogicRunMode runMode = config.getMlDebugRunMode()
		String uri = (runMode == ADHOC) ? '' : mlFileUri (file)

		ResultSequence rs

		log.debug ("exprForLine reqId: ${requestId}, line: ${line}, file: ${file}")

		try {
			rs = evalRequest (xfile (EXPRS_REQ), [id: requestId, uri: uri, line: line.toString()])
		} catch (XQueryException e) {
			if ('DBG-MODULEDNE'.equals (e.getCode())) {
				log.debug ("Ignoring DBG-MODULEDNE, assuming breakpoint not in scope for request")
				return null
			} else if ((runMode == ADHOC) && 'DBG-LINE'.equals (e.getCode())) {
				log.debug ("Ignoring DBG-LINE, assuming eval request or concurrent edit")
				return null
			} else {
				throw e
			}
		}

		BigInteger expr = getSingleBigIntResult (rs, rs.size() - 1)

		if (expr == null) {
			log.debug ("exprForLine returning null")
			return null
		} else {
			log.debug ("exprForLine " + expr)
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
			log.trace ("waitForStateChange returning null")
			return null
		} else {
			log.trace ("waitForStateChange " + expr)
			return expr
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
		if (rs.size() > 5) stat ['expr-id'] = rs.itemAt (5).asString()
		if (rs.size() > 6) stat ['error-msg'] = rs.itemAt (6).asString()

		if (stat ['error-msg']) throw new DeferredXqueryException (stat)

		return stat
	}

	// ---------------------------------------------------

	private static final String GET_REQ_STACK = 'get-request-stack.xqy'

	List<DebugFrame> requestStackFrames (BigInteger requestId)
	{
		log.trace ("requestStackFrames called")

		ResultSequence rs = evalRequest (xfile (GET_REQ_STACK), [id: requestId])
		GPathResult stack = new XmlSlurper (false, true).parseText (rs.asString()).declareNamespace ([d: 'http://marklogic.com/xdmp/debug'])
		List<DebugFrame> debugFrames = []

		stack.frame.each { GPathResult frame ->
			debugFrames << new MarklogicDebugFrame (
				connector: this,
				requestId: requestId,
				lineNumber: Integer.parseInt (frame.line.text()),
				functionName: functionName (frame.operation.text()),
				uri: fileUri (frame),
				variables: frameVariables (requestId, frame)
			)
		}

		debugFrames
	}

	private String fileUri (GPathResult frame)
	{
		if ( ! frame.uri.text()) return new File (config.getMainFile()).toURI().toString()

		log.debug ("fileUri: ${config.mlDebugAppserverRoot + frame.uri.text()}")

		config.mlDebugAppserverRoot + frame.uri.text()
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

	private List<Variable> frameVariables (BigInteger requestId, GPathResult frame) throws RequestException
	{
		List<Variable> variables = []

		frame.'external-variables'.'external-variable'.each { GPathResult variable ->
			String name = variableName (variable.name.text(), variable.name.nodeIterator().next().namespaceURI(), variable.prefix.text())
			String qname = variableName (variable.name.text(), null, variable.prefix.text())
			List<String> valAndType = variableValue (variable.value.text(), requestId, qname)
			variables << new Variable (name, valAndType [1], valAndType [0], 'external')
		}

		frame.'global-variables'.'global-variable'.each { GPathResult variable ->
			String name = variableName (variable.name.text(), variable.name.nodeIterator().next().namespaceURI(), variable.prefix.text())
			String qname = variableName (variable.name.text(), null, variable.prefix.text())
			List<String> valAndType = variableValue (variable.value.text(), requestId, qname)
			variables << new Variable (name, valAndType [1], valAndType [0], 'global')
		}

		frame.variables.variable.each { GPathResult variable ->
			String name = variableName (variable.name.text(), variable.name.nodeIterator().next().namespaceURI(), variable.prefix.text())
			String value = variable.value.text()
			variables << new Variable (name, guessVarType (value), value)
		}

		variables
	}

	private static String variableName (String name, String ns, String prefix)
	{
		String p = (prefix) ? "${prefix}:" : ''
		String n = (ns) ? "{${ns}} " : ''

		"${n}\$${p}${name}"
	}

	private List<String> variableValue (String givenValue, BigInteger requestId, String qname)
	{
		if (givenValue) {
			[givenValue, guessVarType (givenValue)]
		} else {
			requestValueAndType (requestId, qname)
		}
	}

	private static final String datePatternString = '[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9]'
	private static final String timePatternString = '[0-2][0-9]:[0-5][0-9]:[0-5][0-9](\\.[0-9]+.*)?'
	private static final Pattern datePattern = Pattern.compile (datePatternString)
	private static final Pattern dateTimePattern = Pattern.compile ("${datePatternString}T${timePatternString}".toString())
	private static final Pattern booleanPattern = Pattern.compile ('fn:true\\(\\)|fn:false\\(\\)')

	private static String guessVarType (String value)
	{
		if ((value == null) || (value.length() == 0) || (value == '()')) return 'empty-sequence()'

		if (value.startsWith ('xs:')) return value.substring (0, value.indexOf ('('))
		if (value.startsWith ('(')) return 'sequence'
		if (value.startsWith ('<!--')) return 'comment()'
		if (value.startsWith ('<?')) return 'processing-instruction()'
		if (value.startsWith ('<')) return 'element()'
		if (value.startsWith ('document{')) return 'document-node()'
		if (value.startsWith ('text{')) return 'text()'
		if (value.startsWith ('attribute{')) return 'attribute()'
		if (value.matches (booleanPattern)) return 'xs:boolean'

		try { Long.parseLong (value); return 'xs:integer' } catch (Exception e) { /* nothing*/ }
		try { Double.parseDouble (value); return 'xs:decimal' } catch (Exception e) { /* nothing*/ }

		'xs:string'
	}

	// ---------------------------------------------------

	private String mlFileUri (String fileUri)
	{
		fileUri - config.mlDebugAppserverRoot - 'file:'
	}

	// ---------------------------------------------------

	private static BigInteger getSingleBigIntResult (ResultSequence rs, int index = 0)
	{
		if (rs.size() == 0) return null

		ResultItem ri = rs.resultItemAt (index)

		if (ri.getItemType() != ValueType.XS_INTEGER) return null

		return ((XSInteger) ri.getItem()).asBigInteger()
	}

	// ---------------------------------------------------

	private static String SLASH = File.separator

	private String xfile (String name)
	{
		resourceFileText ("${getClass().package.name}.xquery", name)
	}

	// FixMe: Move this somewhere more generic
	static String resourceFileText (String packageName, String filename)
	{
		String path = "${packageName.replace ('.', SLASH)}${SLASH}${filename}"

		try {
			URL resource = MarkLogicDebugConnector.classLoader.getResource (path)

			if (resource == null) {
				throw new RuntimeException ("Cannot find resource at '${path}'".toString())
			}

			resource.text
		} catch (RuntimeException e) {
			throw e
		} catch (Exception e) {
			throw new RuntimeException ("Cannot get content of resource at '${path}' ${e.toString()}".toString())
		}
	}

	// ---------------------------------------------------
}
