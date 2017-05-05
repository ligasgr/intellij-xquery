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
import com.marklogic.xcc.Session;
import com.marklogic.xcc.exceptions.RequestException;
import com.marklogic.xcc.exceptions.XQueryException;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.debugger.BreakpointManager;
import org.intellij.xquery.runner.rt.debugger.DebugFrame;
import org.intellij.xquery.runner.rt.debugger.DebuggerStoppedException;
import org.intellij.xquery.runner.rt.debugger.Variable;
import org.intellij.xquery.runner.rt.vendor.marklogic.MarklogicRunnerApp;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
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
	private final List<DebugFrame> stackFrames = new LinkedList<>();
	private final BreakpointManager breakpointManager = new BreakpointManager();
	private final List<StatusChangeHandler> statusChangeHandlers = new ArrayList<>();
	private final AtomicBoolean shouldBreak = new AtomicBoolean (false);
	private final String sessionId = "" + new Random(System.currentTimeMillis()).nextLong();
	private volatile Status status = Status.STARTING;
	private boolean runOut = false;
	private boolean steppingOver = false;
	private MarkLogicDebugConnector debugConnector = null;
	private BigInteger debuggerRequestId = BigInteger.ZERO;

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
				call dbg:line ($requestId, $moduleUri, $line) to get expression id
				call dbg:break ($requestId, $expressionId) to set breakpoint
					(some breakpoints may not have an expression, literals are not expressions)

		End
			xdmp:cancel



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
log ("MarkLogicDebuggerApp.getSession called: " + sessionId);
		return sessionId;
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
		String initalFile = new File (config.getMainFile()).toURI().toString();

log ("MarkLogicDebuggerApp.getInitialFileUri called: " + initalFile);
		return initalFile;
	}

	@Override
	public void run() throws RequestException
	{
log ("MarkLogicDebuggerApp.run called");

		if (status == Status.STARTING) {
			startApplicationRunInSeparateThread();
log ("MarkLogicDebuggerApp.run thread started");
		} else {
			runOut = true;
			debugConnector.continueRequest (debuggerRequestId);
			resume();
log ("MarkLogicDebuggerApp.run resumed");
		}
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
	public void stepOver()
	{
log ("MarkLogicDebuggerApp.stepOver called");
		try {
			runOut = true;
			steppingOver = true;
			debugConnector.stepOverExpression (debuggerRequestId);
		} catch (RequestException e) {
			abort (e);
		}

		resume();
	}

	@Override
	public void stepInto()
	{
log ("MarkLogicDebuggerApp.stepInto called");
		try {
			runOut = false;
			debugConnector.stepIntoExpression (debuggerRequestId);
		} catch (RequestException e) {
			abort (e);
		}

		resume();
	}

	@Override
	public void stepOut()
	{
log ("MarkLogicDebuggerApp.stepOut called");
		try {
			if (refreshStackFrames().size() == 0) {
				runOut = true;
				debugConnector.stepOutOfExpression (debuggerRequestId);
			} else {
				runOut = false;
				debugConnector.stepOutOfFunction (debuggerRequestId, stackFrames.get (0).getFunctionName());
			}
		} catch (RequestException e) {
			abort (e);
		}
		resume();
	}

	@Override
	public Breakpoint breakpointSet (Breakpoint breakpoint)
	{
log ("MarkLogicDebuggerApp.breakpointSet called, breakpoint: " + printBreakpoint (breakpoint));
		Breakpoint bp = breakpointManager.setBreakpoint (breakpoint);
		if (debugConnector != null) debugConnector.setMlBreakPoints (debuggerRequestId, breakpointManager);

		return bp;
	}

	@Override
	public Optional<Breakpoint> breakpointRemove (String breakpointId)
	{
log ("MarkLogicDebuggerApp.breakpointRemove called, breakpointId: " + breakpointId);
		Optional<Breakpoint> bp = breakpointManager.removeBreakpoint (breakpointId);

		if (debugConnector != null) debugConnector.setMlBreakPoints (debuggerRequestId, breakpointManager);

		return bp;
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
		if (debugConnector != null) debugConnector.setMlBreakPoints (debuggerRequestId, breakpointManager);
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
	public Status getStatus()
	{
log ("MarkLogicDebuggerApp.getStatus called");
		return status;
	}

	@Override
	public int getStackDepth()
	{
		log ("MarkLogicDebuggerApp.getStackDepth called");

		List<DebugFrame> stack = refreshStackFrames();

		log ("MarkLogicDebuggerApp.getStackDepth = " + stack.size());
		return stack.size();
	}

	@Override
	public StackFrame getFrame (int i)
	{
log ("MarkLogicDebuggerApp.getFrame: " + i + ", frame: " + stackFrames.get (i));
		return stackFrames.size() > 0 ? convertToStackFrame (stackFrames.get (i)) : null;
	}

	@Override
	public Collection<PropertyValue> getVariables (int i)
	{
log ("MarkLogicDebuggerApp.getVariables: frame=" + i + ", vars=" + stackFrames.get (i).getVariables());
		return stackFrames.size() > 0 ? convertToPropertyValues (stackFrames.get (i).getVariables()) : Collections.emptyList();
	}

	@Override
	public Optional<PropertyValue> eval (int depth, String expression)
	{
		log ("MarkLogicDebuggerApp.eval called: depth=" + depth + ", expr: " + expression);
		DebugFrame debugFrame = stackFrames.get (depth);

		if (debugFrame != null) {
			Optional<Variable> result = debugFrame.eval (expression);

			if (result.isPresent()) {
				return Optional.of (convertToPropertyValue (result.get()));
			}
		}

		return Optional.empty();
	}

	// -----------------------------------------------------------------------

	private boolean running = true;
	private Exception thrownException = null;

	private void abort (Exception e)
	{
		running = false;
		thrownException = e;
	}

	@SuppressWarnings ("OverlyComplexMethod")
	private void runDebuggerApp() throws Exception
	{
		log ("MarkLogicDebuggerApp.runDebuggerApp called");
		String query = readFile (config.getMainFile());
		ContentSource contentSource = getContentSource();
		Session session = contentSource.newSession();

		debugConnector = new MarkLogicDebugConnector (config, session);

		try {
			debugConnector.clearStoppedRequests();

			debuggerRequestId = debugConnector.submitEvalForDebug (query, config.getVariables());

			debugConnector.setMlBreakPoints (debuggerRequestId, breakpointManager);

			log ("runDebuggerApp: starting suspended request");
			debugConnector.runToNextBreakPoint (debuggerRequestId);

			log ("runDebuggerApp: entering eternal loop");

			running = true;
			thrownException = null;

			while (running) {
				Map<String,String> mlReqStatus = debugConnector.getRequestStatus (debuggerRequestId);
				String reqStatus = mlReqStatus.get ("req-status");
				String whereStopped = mlReqStatus.get ("where-stopped");
				String exprId = mlReqStatus.get ("expr-id");
				String errorMsg = mlReqStatus.get ("error-msg");

				log ("runDebuggerApp: top of loop: " + reqStatus);

				if (mlReqStatus.get ("id") == null) {
					log ("runDebuggerApp: request not active, stopping: " + debuggerRequestId);
					break;
				}

				if ((errorMsg != null) && (errorMsg.length() > 0)) {
					System.err.println (errorMsg);
					break;
				}

				log ("runDebuggerApp: switch(status): status: " + status + ", expr: " + exprId + ", where: " + whereStopped);

				switch (status) {
				case BREAK:
					if ("running".equals (reqStatus)) {
						resume();
						break;
					}

					Thread.sleep (2000);

					break;

				case RUNNING:
					log ("RUNNING: req-status=" + reqStatus + ", expr: " + exprId + ", where: " + whereStopped);

					if ("stopped".equals (reqStatus)) {
						if ( ! (runOut && "end".equals (whereStopped))) {
							changeToBreak();
							break;
						}
					}

					log ("Continuing");

					if (steppingOver) {
						steppingOver = false;
						debugConnector.stepOverExpression (debuggerRequestId);
					} else {
						debugConnector.continueRequest (debuggerRequestId);
					}

					debugConnector.getRequestStatus (debuggerRequestId);	// trigger deferred exception

					BigInteger reqId = debugConnector.waitForStateChange (debuggerRequestId, 30);

					debugConnector.getRequestStatus (debuggerRequestId);	// trigger deferred exception

					if (reqId == null) {
						System.err.println ("runDebuggerApp: Timeout waiting for ML request to break/finish, stopping");
						running = false;
					}

					break;

				case STARTING:
					log ("runDebuggerApp: In STARTING state, sleeping for 200 ms");
					Thread.sleep (200);
					break;

				case STOPPING:
					log ("runDebuggerApp: status = STOPPING");
					break;

				case STOPPED:
					log ("runDebuggerApp: status = STOPPED");
					break;

				default:
					log ("runDebuggerApp: Impossible debug status, stopping: " + mlReqStatus.get ("xml"));
				}

				if ( ! running) log ("running = false, breaking from main loop");
				if (thrownException != null) {
					log ("Abort due to Exception: " + thrownException);
					throw thrownException;
				}
			}
		} catch (DeferredXqueryException|XQueryException e) {
			System.err.println ("XQuery execution exception, stopping: " + e);
		} catch (RequestException e) {
			System.err.println ("Exception talking to MarkLogic, stopping: " + e);
		} catch (DebuggerStoppedException e) {
			System.err.println ("Debugger has unexpectedly stopped: " + e);
		} catch (Exception e) {
			System.err.println ("Unexpected exception, stopping: " + e);
		} finally {
			try {
				log ("clearing any stopped requests");
				debugConnector.clearStoppedRequests();
			} catch (RequestException e) {
				System.err.println ("Problem clearing stopped requests: " + e);
			}
		}
	}

	private Runnable getApplicationRunnable()
	{
		return new Runnable()
		{
			public void run()
			{
				log ("app.run()");

				try {
					synchronized (theLock) {
						log ("getApplicationRunnable: changing status to running");
						changeState (Status.RUNNING);
					}

					log ("running the app");
//					MarkLogicDebuggerApp.super.runApp();
					runDebuggerApp();

					log ("ran the app");
					changeToStopped();
				} catch (Throwable e) {
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

	private List<DebugFrame> refreshStackFrames()
	{
		stackFrames.clear();
		stackFrames.addAll (debugConnector.requestStackFrames (debuggerRequestId));

		return stackFrames;
	}

	private static String printBreakpoint (Breakpoint breakpoint)
	{
		return "Breakpoint, type: " + breakpoint.getType() + ", function: " + breakpoint.getFunction() +
			", expr: " + breakpoint.getExpression() + ", line: " + breakpoint.getLineNumber() +
			", enabled: " + breakpoint.isEnabled() + ", file: " + breakpoint.getFileURL();
	}

	private void changeState (Status newState)
	{
		log ("Changing state: " + status + " -> " + newState);
		Status previous = status;
		status = newState;
		theLock.notifyAll();
		notifyStateChange (previous, status);
	}

	private void changeToBreak() throws DebuggerStoppedException
	{
		try {
			synchronized (theLock) {
				steppingOver = runOut = false;
				log ("changeToBreak in status=" + status);
				log ("suspended");
				changeState (Status.BREAK);

				log ("starting to wait");

				do {
					log ("waiting until not suspended");
					theLock.wait();
				} while (status == Status.BREAK);

				log ("not suspended anymore=" + status);

				if (status == Status.STOPPED) {
					throw new DebuggerStoppedException();
				}
			}
		} catch (InterruptedException e) {
			throw new DebuggerStoppedException();
		}
	}

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

//			try {
//				debugConnector.runToNextBreakPoint (debuggerRequestId);
//			} catch (RequestException e) {
//				log ("resume(): caught XCC exception: " + e);
//				throw new RuntimeException (e);
//			}

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
		List<StatusChangeHandler> handlersToRemove = new ArrayList<>();

		synchronized (statusChangeHandlers) {
			for (StatusChangeHandler StatusChangeHandler : statusChangeHandlers) {
				if (StatusChangeHandler.applicableFor (previous, current)) {
					log ("status change handler " + StatusChangeHandler + " is applicable for " + previous + " ==> " + current);
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
//			log ("  variable: " + variable.name + ", value: " + variable.value + ", type: " + variable.type);
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
