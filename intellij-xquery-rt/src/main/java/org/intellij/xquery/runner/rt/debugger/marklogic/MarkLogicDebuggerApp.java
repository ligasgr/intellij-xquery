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

package org.intellij.xquery.runner.rt.debugger.marklogic;

import com.codnos.dbgp.api.*;
import com.marklogic.xcc.ContentSource;
import com.marklogic.xcc.Request;
import com.marklogic.xcc.Session;
import com.marklogic.xcc.ValueFactory;
import com.marklogic.xcc.exceptions.XQueryException;
import com.marklogic.xcc.types.ValueType;
import com.marklogic.xcc.types.XName;
import com.marklogic.xcc.types.XdmVariable;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.debugger.BreakpointManager;
import org.intellij.xquery.runner.rt.debugger.DebugFrame;
import org.intellij.xquery.runner.rt.debugger.DebuggerStoppedException;
import org.intellij.xquery.runner.rt.debugger.Variable;
import org.intellij.xquery.runner.rt.vendor.marklogic.MarklogicRunnerApp;

import java.io.File;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.codnos.dbgp.api.DBGpFactory.engine;
import static org.intellij.xquery.runner.rt.FileUtil.readFile;
import static org.intellij.xquery.runner.rt.debugger.LogUtil.log;

public class MarkLogicDebuggerApp extends MarklogicRunnerApp implements DebuggerEngine
{
	private final Thread applicationThread;
	private final Object theLock = new Object();
	private final int mlDebuggerPort;
	private final List<DebugFrame> visibleDebugFrames = new LinkedList<>();
	private final List<DebugFrame> allFrames = new LinkedList<>();
	private final BreakpointManager breakpointManager = new BreakpointManager();
	private final List<StatusChangeHandler> statusChangeHandlers = new ArrayList<>();
	private final AtomicBoolean shouldBreak = new AtomicBoolean (false);
	private volatile Status status = Status.STARTING;
	private boolean steppingOver = false;
	private boolean steppingInto = false;
	private boolean steppingOut = false;
	private int stackDepthAtSteppingStart = 0;
	private String debuggerRequestId;

	public MarkLogicDebuggerApp (XQueryRunConfig config, PrintStream output)
	{
		super (config, output);

		mlDebuggerPort = Integer.parseInt (config.getMlDebugPort());
		applicationThread = new Thread (getApplicationRunnable());
	}

	// --------------------------------------------------------

	/*

	For evaluating ad-hoc code:
		Start
			dbg:eval with code as xs:string extern variable ==> $requestId
				request is on Task Server, suspended
			For each breakpoint
				call dbg:line ($requestId, $moduleUri, $line

		End
			dbg:detach or dbg:finish



	For running arbitrary modules:
		Start
			Get appserver id of run port
			issue dbg:connect ($appserverid) on debugger port

			submit request to be run on run port
			issue dbg:stopped on debugger port to get $requestid*

		Run

		End
			issue dbg:disconnect ($appserverid) on debugger port

	 */

	// --------------------------------------------------------
	// Implementation of local RunnerApp interface

	@Override
	public void runApp() throws Exception
	{
log ("MarkLogicDebuggerApp.runApp called: mlDebuggerPort=" + mlDebuggerPort);
		int debugPort = Integer.parseInt (config.getDebugPort());

		DBGpEngine dbgpEngine = engine().withPort (debugPort).withDebuggerEngine (this).build();
		dbgpEngine.connect();
	}

	// --------------------------------------------------------
	// Implementation of IntelliJ DebuggerEngine interface

	@Override
	public String getAppId()
	{
log ("MarkLogicDebuggerApp.getAppId called");
		return "MarklogicDebuggerEngine";
	}

	@Override
	public String getSession()
	{
log ("MarkLogicDebuggerApp.getSession called");
		return "";
	}

	@Override
	public String getIdeKey()
	{
log ("MarkLogicDebuggerApp.getIdeKey called");
		return "";
	}

	@Override
	public String getLanguage()
	{
log ("MarkLogicDebuggerApp.getLanguage called");
		return "XQuery";
	}

	@Override
	public String getProtocolVersion()
	{
log ("MarkLogicDebuggerApp.getProtocolVersion called");
		return "1.0";
	}

	@Override
	public String getInitialFileUri()
	{
log ("MarkLogicDebuggerApp.getInitialFileUri called: " + new File (config.getMainFile()).toURI().toString());
		return new File (config.getMainFile()).toURI().toString();
	}

	@Override
	public void run() throws Exception
	{
log ("MarkLogicDebuggerApp.run called");
		steppingOver = false;
		steppingInto = false;
		steppingOut = false;

		if (status == Status.STARTING) {
			startApplicationRunInSeparateThread();
		} else {
			resume();
		}
	}

	@Override
	public void stepOver()
	{
log ("MarkLogicDebuggerApp.stepOver called");
		steppingOver = true;
		stackDepthAtSteppingStart = visibleDebugFrames.size();
		resume();
	}

	@Override
	public void stepInto()
	{
log ("MarkLogicDebuggerApp.stepInto called");
		steppingInto = true;
		stackDepthAtSteppingStart = visibleDebugFrames.size();
		resume();
	}

	@Override
	public void stepOut()
	{
log ("MarkLogicDebuggerApp.stepOut called");
		steppingOut = true;
		stackDepthAtSteppingStart = visibleDebugFrames.size();
		resume();
	}

	@Override
	public Breakpoint breakpointSet (Breakpoint breakpoint)
	{
log ("MarkLogicDebuggerApp.breakpointSet called, breakpoint: " + printBreakpoint (breakpoint));
		return breakpointManager.setBreakpoint (breakpoint);
	}

	private String printBreakpoint (Breakpoint breakpoint)
	{
		return "Breakpoint, type: " + breakpoint.getType() + ", function: " + breakpoint.getFunction() +
			", expr: " + breakpoint.getExpression() + ", line: " + breakpoint.getLineNumber() +
			", enabled: " + breakpoint.isEnabled() + ", file: " + breakpoint.getFileURL();
	}

	@Override
	public Optional<Breakpoint> breakpointRemove (String breakpointId)
	{
log ("MarkLogicDebuggerApp.breakpointRemove called, breakpointId: " + breakpointId);
		return breakpointManager.removeBreakpoint (breakpointId);
	}

	@Override
	public Breakpoint breakpointGet (String breakpointId)
	{
log ("MarkLogicDebuggerApp.breakpointGet called, breakpointId: " + breakpointId);
		return breakpointManager.getBreakpoint (breakpointId);
	}

	@Override
	public void breakpointUpdate (String breakpointId, BreakpointUpdateData breakpointUpdateData)
	{
log ("MarkLogicDebuggerApp.breakpointUpdate called, breakpointId: " + breakpointId);
		Breakpoint breakpointToUpdate = breakpointManager.getBreakpoint (breakpointId);
		Breakpoint updatedBreakpoint = breakpointToUpdate.update (breakpointUpdateData);

		breakpointManager.updateBreakpoint (updatedBreakpoint);
	}

	@Override
	public void registerStatusChangeHandler (StatusChangeHandler StatusChangeHandler)
	{
log ("MarkLogicDebuggerApp.registerStatusChangeHandler called");
		synchronized (statusChangeHandlers) {
			statusChangeHandlers.add (StatusChangeHandler);
		}
	}

	@Override
	public int getStackDepth()
	{
log ("MarkLogicDebuggerApp.getStackDepth called");
		return visibleDebugFrames.size();
	}

	@Override
	public Status getStatus()
	{
log ("MarkLogicDebuggerApp.getStatus called");
		return status;
	}

	@Override
	public boolean breakNow()
	{
log ("MarkLogicDebuggerApp.breakNow called");
		synchronized (theLock) {
			if (status == Status.STOPPED || status == Status.STOPPING) {
				return false;
			} else {
				shouldBreak.set (true);
				return true;
			}
		}
	}

	@Override
	public Optional<PropertyValue> eval (int depth, String expression)
	{
log ("MarkLogicDebuggerApp.eval called: depth=" + depth + ", expr: " + expression);
		DebugFrame debugFrame = visibleDebugFrames.get (depth);

		if (debugFrame != null) {
			Optional<Variable> result = debugFrame.eval (expression);

			if (result.isPresent()) {
				return Optional.of (convertToPropertyValue (result.get()));
			}
		}

		return Optional.empty();
	}

	@Override
	public StackFrame getFrame (int i)
	{
log ("MarkLogicDebuggerApp.getFrame: " + i);
		return visibleDebugFrames.size() > 0 ? convertToStackFrame (visibleDebugFrames.get (i)) : null;
	}

	@Override
	public Collection<PropertyValue> getVariables (int i)
	{
log ("MarkLogicDebuggerApp.getVariables: " + i);
		return visibleDebugFrames.size() > 0 ? convertToPropertyValues (visibleDebugFrames.get (i).getVariables()) : Collections.emptyList();
	}

	// -----------------------------------------------------------------------

	private void runDebuggerApp() throws Exception
	{
		log ("MarkLogicDebuggerApp.runDebuggerApp called");
		ContentSource contentSource = getContentSource();
		Session session = contentSource.newSession();
		String query = readFile (config.getMainFile());
		Request request = prepareDebugEvalRequest (session, query);

		try {
			log ("MarkLogicDebuggerApp.runDebuggerApp submitting debug request to MarkLogic");
			debuggerRequestId = session.submitRequest (request).asString();
			log ("MarkLogicDebuggerApp.runDebuggerApp back from call, requestId: " + debuggerRequestId);

			setMlBreakPoints();
		} catch (Exception e) {
			System.err.println (e.toString());
		}
	}

	private static final String DBG_EVAL_REQUEST =
		"xquery version '1.0-ml';" +
		"declare variable $__query__ external;" +
		"declare variable $__variables__ external;" +
		"declare function local:gen-vars() {" +
		"    () " +
		"};" +
		"dbg:eval ($__query__, local:gen-vars())";

	private static final XdmVariable DBG_REQUEST_VAR = ValueFactory.newVariable (new XName ("__query__"), ValueFactory.newValue (ValueType.XS_STRING, DBG_EVAL_REQUEST));

	public Request prepareDebugEvalRequest (Session session, String query)
	{
		Request request = session.newAdhocQuery (DBG_EVAL_REQUEST);

		request.setVariable (DBG_REQUEST_VAR);
		request.setNewVariable ("__variables__", ValueType.XS_STRING, generateVarsXml());

		return request;
	}

	private String generateVarsXml()
	{
		return "<variables/>";
	}

	private void setMlBreakPoints()
	{
		Map<Integer, Map<String, Breakpoint>> breakPoints = breakpointManager.allBreakpoints();

		for (Integer bpId : breakPoints.keySet()) {
			Map<String, Breakpoint> bpMap = breakPoints.get (bpId);

			log ("setMlBreakPoints: id=" + bpId);

			for (String mapId : bpMap.keySet()) {
				Breakpoint bp = bpMap.get (mapId);

				log ("  Breakpoint: id: " + bp.getBreakpointId() + ", type: " + bp.getType() +
					", function: " + bp.getFunction() + ", line: " + bp.getLineNumber() + ", expr: " + bp.getExpression());
			}
		}
	}

	// --------------------------------------------------------------------------

	private Runnable getApplicationRunnable()
	{
		return new Runnable()
		{
			public void run()
			{
				log ("app.run()");

				try {
					synchronized (theLock) {
						log ("changing status to running");
						changeState (Status.RUNNING);
					}

					log ("running the app");
//					MarkLogicDebuggerApp.super.runApp();
					runDebuggerApp();

					log ("ran the app");
					changeToStopped();
				} catch (Exception e) {
					log ("got exception: " + e);
					e.printStackTrace();
					changeToStopped();
				}
			}
		};
	}

	private void startApplicationRunInSeparateThread()
	{
		assert status == Status.STARTING : "Already started";

		log ("start()");
		applicationThread.start();

		try {
			synchronized (theLock) {
				while (status == Status.STARTING) {
					log ("waiting for start");
					theLock.wait();
				}

				log ("not waiting anymore for start");
			}
		} catch (InterruptedException e) {
			// Nothing?
		}
	}

	// -----------------------------------------------------------------------

	private void changeState (Status newState)
	{
		log ("Changing state: " + status + " -> " + newState);
		Status previous = status;
		status = newState;
		theLock.notifyAll();
		notifyStateChange (previous, status);
	}

//	private void changeToBreak() throws DebuggerStoppedException
//	{
//		try {
//			synchronized (theLock) {
//				steppingOver = false;
//				steppingInto = false;
//				steppingOut = false;
//				log ("changeToBreak in status=" + status);
//				log ("suspended");
//				changeState (Status.BREAK);
//
//				log ("starting to wait");
//				do {
//					log ("waiting until not suspended");
//					theLock.wait();
//				} while (status == Status.BREAK);
//				log ("not suspended anymore=" + status);
//
//				if (status == Status.STOPPED) {
//					throw new DebuggerStoppedException();
//				}
//			}
//		} catch (InterruptedException e) {
//			throw new DebuggerStoppedException();
//		}
//	}

	private void resume() throws DebuggerStoppedException
	{
		log ("resume()");
		synchronized (theLock) {
			log ("resume=" + status);
			if (status == Status.STOPPED) {
				throw new DebuggerStoppedException();
			} else if (status != Status.BREAK) {
				throw new IllegalStateException();
			}

			log ("changing status to running");
			changeState (Status.RUNNING);
			log ("status running");
		}
	}

//	private boolean isStopped()
//	{
//		synchronized (theLock) {
//			log ("isStopped=" + status);
//			return status == Status.STOPPED;
//		}
//	}

	private void changeToStopped()
	{
		assert Thread.currentThread() == applicationThread;
		log ("changeToStopped()");
		synchronized (theLock) {
			log ("about to stop");
			changeState (Status.STOPPING);
			changeState (Status.STOPPED);
			log ("stopped");
		}
	}

	private void notifyStateChange (Status previous, Status current)
	{
		log ("changing status from " + previous + " to " + current);
		List<StatusChangeHandler> handlersToRemove = new ArrayList<StatusChangeHandler>();
		synchronized (statusChangeHandlers) {
			for (StatusChangeHandler StatusChangeHandler : statusChangeHandlers) {
				if (StatusChangeHandler.applicableFor (previous, current)) {
					log ("status change handler " + StatusChangeHandler + " is applicable");
					StatusChangeHandler.statusChanged (previous, current);
					handlersToRemove.add (StatusChangeHandler);
				}
			}
			for (StatusChangeHandler StatusChangeHandler : handlersToRemove) {
				statusChangeHandlers.remove (StatusChangeHandler);
			}
		}
	}

	// -----------------------------------------------------------------------

	private static Collection<PropertyValue> convertToPropertyValues (List<Variable> variables)
	{
		Collection<PropertyValue> result = new ArrayList<> (variables.size());

		for (Variable variable : variables) {
			result.add (convertToPropertyValue (variable));
		}
		return result;
	}

	private static PropertyValue convertToPropertyValue (Variable variable)
	{
		return new PropertyValue (variable.name, variable.type, variable.value);
	}

	private static StackFrame convertToStackFrame (DebugFrame debugFrame)
	{
		return new StackFrame (debugFrame.getUri(), debugFrame.getLineNumber(), debugFrame.getFunctionName());
	}

	// -----------------------------------------------------------------------


//	public void enter (MarklogicDebugFrame frame)
//	{
//		assert Thread.currentThread() == applicationThread;
//		final String uri = frame.getUri();
//		final DebugFrame previous = visibleDebugFrames.peekFirst();
//
//		if (frameInSameContextAsLastOne (frame, previous)) {
//			log ("removing existing previous frame as it is in the same scope");
//			visibleDebugFrames.removeFirst();
//		}
//
//		if (locationIsDifferentFromPrevious (frame, previous)) {
//			logFramesBeforeAddingNewOne();
//			visibleDebugFrames.addFirst (frame);
//			allFrames.addFirst (frame);
//			logFramesAfterAddingNewOne();
//		} else {
//			allFrames.addFirst (frame);
//		}
//
//		if (isStopped()) {
//			throw new DebuggerStoppedException();
//		} else {
//			processChangeOfFrame (frame, uri, previous);
//		}
//	}
//
//	public void leave()
//	{
//		assert Thread.currentThread() == applicationThread;
//		if (isStopped()) {
//			throw new DebuggerStoppedException();
//		}
//		logFramesBeforeRemove();
//		DebugFrame removedFrame = allFrames.removeFirst();
//		logFramesAfterRemove (removedFrame);
//		if (removedFrame.equals (visibleDebugFrames.peekFirst())) {
//			visibleDebugFrames.removeFirst();
//		}
//	}
//
//    QueryModule getMainModule() {
//        return mainModule;
//    }
//
//	DebugFrame getCurrentFrame()
//	{
//		return visibleDebugFrames.peekFirst();
//	}
//
//    @Override
//    protected void doAdditionalConfiguration(Processor processor)
//    {
//        Configuration configuration = processor.getUnderlyingConfiguration();
//        configuration.setBooleanProperty(FeatureKeys.EAGER_EVALUATION, true);
//        StaticQueryContext context = configuration.getDefaultStaticQueryContext();
//        configuration.setTraceListener(new SaxonTraceListener(this));
//        context.setCodeInjector(new SaxonExtendedTraceCodeInjector());
//        Optimizer optimizer = configuration.obtainOptimizer();
//        optimizer.setOptimizationLevel(Optimizer.NO_OPTIMIZATION);
//    }
//
//    @Override
//    protected void setMainModule(QueryModule mainModule) {
//        this.mainModule = mainModule;
//    }
//
//
//	private boolean frameInSameContextAsLastOne (MarklogicDebugFrame frame, DebugFrame previous)
//	{
//		return previous != null && sameFile (previous, frame) && (sameFunction (previous, frame) || bothInMain (frame, previous)) && differentLine (previous, frame) && visibleDebugFrames.size() > 0;
//	}
//
//	private void processChangeOfFrame (MarklogicDebugFrame frame, String uri, DebugFrame previous)
//	{
//		if (steppingOver && shouldChangeToBreakAfterStepOver (frame, previous)) {
//			changeToBreak();
//			return;
//		}
//		if (steppingInto && shouldChangeToBreakAfterStepInto (frame, previous)) {
//			changeToBreak();
//			return;
//		}
//		if (steppingOut && shouldChangeToBreakAfterStepOut (frame, previous)) {
//			changeToBreak();
//			return;
//		}
//
//		if (shouldBreakAfterBreakpointWasReached (frame, previous, uri)) {
//			changeToBreak();
//			return;
//		}
//		if (shouldBreakAfterBreakWasRequested()) {
//			changeToBreak();
//		}
//	}
//
//	private boolean shouldChangeToBreakAfterStepOver (MarklogicDebugFrame frame, DebugFrame previous)
//	{
//		log ("stackDepthAtSteppingStart = " + stackDepthAtSteppingStart);
//		log ("previous = " + previous);
//		log ("frame = " + frame);
//		if (sameFile (previous, frame) && sameFunction (previous, frame) && differentLine (previous, frame)) {
//			log ("suspending after stepping over, same file, same function, different line");
//			return true;
//		}
//		if (sameFile (previous, frame) && !sameFunction (previous, frame) && visibleDebugFrames.size() <= stackDepthAtSteppingStart) {
//			log ("suspending after stepping over, same file, different function, stack depth not deeper");
//			return true;
//		}
//		if (!sameFile (previous, frame) && visibleDebugFrames.size() <= stackDepthAtSteppingStart) {
//			log ("suspending after stepping over, different file, stack depth not deeper");
//			return true;
//		}
//		return false;
//	}
//
//	private boolean shouldChangeToBreakAfterStepInto (MarklogicDebugFrame frame, DebugFrame previous)
//	{
//		log ("stackDepthAtSteppingStart = " + stackDepthAtSteppingStart);
//		log ("previous = " + previous);
//		log ("frame = " + frame);
//		if (sameFile (previous, frame) && differentLine (previous, frame)) {
//			log ("suspending after stepping into a different line");
//			return true;
//		}
//		if (!sameFile (previous, frame)) {
//			log ("suspending after stepping into a different file");
//			return true;
//		}
//		return false;
//	}
//
//	private boolean shouldChangeToBreakAfterStepOut (MarklogicDebugFrame frame, DebugFrame previous)
//	{
//		log ("stackDepthAtSteppingStart = " + stackDepthAtSteppingStart);
//		log ("previous = " + previous);
//		log ("frame = " + frame);
//		if (sameFile (previous, frame) && !sameFunction (previous, frame) && visibleDebugFrames.size() < stackDepthAtSteppingStart) {
//			log ("suspending after stepping out, same file, different function, stack depth not deeper");
//			changeToBreak();
//			return true;
//		}
//		if (sameFile (previous, frame) && sameFunction (previous, frame) && differentLine (previous, frame) && visibleDebugFrames.size() < stackDepthAtSteppingStart) {
//			log ("suspending after stepping out, same file, same function as the seemingly previous frame, stack depth not deeper than when started");
//			changeToBreak();
//			return true;
//		}
//		if (!sameFile (previous, frame) && visibleDebugFrames.size() < stackDepthAtSteppingStart) {
//			log ("suspending after stepping out, different file, stack depth not deeper");
//			changeToBreak();
//			return true;
//		}
//		return false;
//	}
//
//	private boolean shouldBreakAfterBreakpointWasReached (MarklogicDebugFrame frame, DebugFrame previous, String uri)
//	{
//		final Breakpoint breakpoint = breakpointManager.getBreakpoint (uri, frame.getLineNumber());
//		log ("breakpoint=" + breakpoint);
//		if (breakpoint != null && breakpoint.isEnabled()) {
//			if (locationIsDifferentFromPrevious (frame, previous)) {
//				if (breakpoint.getType() == BreakpointType.LINE || (breakpoint.getType() == BreakpointType.CONDITIONAL && conditionWasMet (breakpoint))) {
//					log ("suspending");
//					if (breakpoint.isTemporary()) {
//						breakpointRemove (breakpoint.getBreakpointId());
//					}
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	private boolean shouldBreakAfterBreakWasRequested()
//	{
//		boolean shouldBreakAfterRequest = shouldBreak.compareAndSet (true, false);
//		if (shouldBreakAfterRequest) {
//			log ("suspending as break request was received");
//			return true;
//		}
//		return false;
//	}
//
//	private void logFramesBeforeAddingNewOne()
//	{
//		log ("about to add frame to stack");
//		logFrames ("visibleDebugFrames", visibleDebugFrames);
//		logFrames ("allFrames", allFrames);
//	}
//
//	private void logFramesAfterAddingNewOne()
//	{
//		log ("added frame to stack");
//		logFrames ("visibleDebugFrames", visibleDebugFrames);
//		logFrames ("allFrames", allFrames);
//	}
//
//	private void logFramesAfterRemove (DebugFrame removedFrame)
//	{
//		log ("removed frame file " + removedFrame.getUri() + " line: " + removedFrame.getLineNumber());
//	}
//
//	private void logFramesBeforeRemove()
//	{
//		logFrames ("allFrames before leave", allFrames);
//		logFrames ("visibleDebugFrames before leave", visibleDebugFrames);
//	}
//
//	private boolean conditionWasMet (Breakpoint breakpoint)
//	{
//		Optional<PropertyValue> evaluatedExpression = eval (0, breakpoint.getExpression().get());
//		if (evaluatedExpression.isPresent()) {
//			PropertyValue propertyValue = evaluatedExpression.get();
//			return propertyValue.getType().equals ("xs:boolean") && propertyValue.getValue().equals ("true");
//		}
//		return false;
//	}
//
//	private void logFrames (String header, LinkedList<DebugFrame> stackFrames)
//	{
//		StringBuilder sb = new StringBuilder (header + " till now:");
//		sb.append (System.lineSeparator());
//		for (DebugFrame frame : stackFrames) {
//			sb.append (frame);
//			sb.append (System.lineSeparator());
//		}
//		log (sb.toString());
//	}
//
//	private boolean locationIsDifferentFromPrevious (MarklogicDebugFrame frame, DebugFrame previous)
//	{
//		return previous == null || !sameFile (previous, frame) || differentLine (previous, frame);
//	}
//
//	private boolean bothInMain (DebugFrame frame, DebugFrame previous)
//	{
//		return isInMain (previous) && isInMain (frame);
//	}
//
//	private boolean differentLine (DebugFrame frame1, DebugFrame frame2)
//	{
//		return frame1 == null || frame2 == null || frame1.getLineNumber() != frame2.getLineNumber();
//	}
//
//	private boolean isInMain (DebugFrame frame)
//	{
//		return frame == null || frame.getFunctionName() == null;
//	}
//
//	private boolean sameFunction (DebugFrame frame1, DebugFrame frame2)
//	{
//		return bothInMain (frame1, frame2) || (frame1 != null && frame1.getFunctionName() != null && frame1.getFunctionName().equals (frame2.getFunctionName()));
//	}
//
//	private boolean sameFile (DebugFrame frame1, DebugFrame frame2)
//	{
//		return frame1 == null || frame1.getUri().equals (frame2.getUri());
//	}
//
//
//
//
}
