/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.rt.debugger.saxon;

import com.codnos.dbgp.api.Breakpoint;
import com.codnos.dbgp.api.BreakpointType;
import com.codnos.dbgp.api.BreakpointUpdateData;
import com.codnos.dbgp.api.DBGpEngine;
import com.codnos.dbgp.api.DebuggerEngine;
import com.codnos.dbgp.api.PropertyValue;
import com.codnos.dbgp.api.StackFrame;
import com.codnos.dbgp.api.Status;
import com.codnos.dbgp.api.StatusChangeHandler;
import net.sf.saxon.Configuration;
import net.sf.saxon.expr.parser.Optimizer;
import net.sf.saxon.lib.FeatureKeys;
import net.sf.saxon.query.QueryModule;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.trans.XPathException;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.debugger.BreakpointManager;
import org.intellij.xquery.runner.rt.debugger.DebugFrame;
import org.intellij.xquery.runner.rt.debugger.DebuggerStoppedException;
import org.intellij.xquery.runner.rt.debugger.Variable;
import org.intellij.xquery.runner.rt.vendor.saxon.SaxonRunnerApp;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.codnos.dbgp.api.DBGpFactory.engine;
import static org.intellij.xquery.runner.rt.debugger.LogUtil.log;

public class SaxonDebuggerApp extends SaxonRunnerApp implements DebuggerEngine {
    private final Thread applicationThread;
    private final Object theLock = new Object();
    private final LinkedList<DebugFrame> visibleDebugFrames = new LinkedList<>();
    private final LinkedList<DebugFrame> allFrames = new LinkedList<>();
    private final BreakpointManager breakpointManager = new BreakpointManager();
    private final List<StatusChangeHandler> statusChangeHandlers = new ArrayList<>();
    private final AtomicBoolean shouldBreak = new AtomicBoolean(false);
    private volatile Status status = Status.STARTING;
    private boolean steppingOver = false;
    private boolean steppingInto = false;
    private boolean steppingOut = false;
    private int stackDepthAtSteppingStart;
    private QueryModule mainModule;

    public SaxonDebuggerApp(XQueryRunConfig config, PrintStream output) throws XPathException {
        super(config, output);
        applicationThread = new Thread(getApplicationRunnable());
    }

    @Override
    public void runApp() throws Exception {
        int debugPort = Integer.valueOf(config.getDebugPort());
        DBGpEngine dbgpEngine = engine().withPort(debugPort).withDebuggerEngine(this).build();
        dbgpEngine.connect();
    }

    @Override
    public String getAppId() {
        return "SaxonDebuggerEngine";
    }

    @Override
    public String getSession() {
        return "";
    }

    @Override
    public String getIdeKey() {
        return "";
    }

    @Override
    public String getLanguage() {
        return "XQuery";
    }

    @Override
    public String getProtocolVersion() {
        return "1.0";
    }

    @Override
    public String getInitialFileUri() {
        return new File(config.getMainFile()).toURI().toString();
    }

    @Override
    public void run() throws Exception {
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
    public void stepOver() {
        steppingOver = true;
        stackDepthAtSteppingStart = visibleDebugFrames.size();
        resume();
    }

    @Override
    public void stepInto() {
        steppingInto = true;
        stackDepthAtSteppingStart = visibleDebugFrames.size();
        resume();
    }

    @Override
    public void stepOut() {
        steppingOut = true;
        stackDepthAtSteppingStart = visibleDebugFrames.size();
        resume();
    }

    @Override
    public Breakpoint breakpointSet(Breakpoint breakpoint) {
        return breakpointManager.setBreakpoint(breakpoint);
    }

    @Override
    public Optional<Breakpoint> breakpointRemove(String breakpointId) {
        return breakpointManager.removeBreakpoint(breakpointId);
    }

    @Override
    public Breakpoint breakpointGet(String breakpointId) {
        return breakpointManager.getBreakpoint(breakpointId);
    }

    @Override
    public void breakpointUpdate(String breakpointId, BreakpointUpdateData breakpointUpdateData) {
        Breakpoint breakpointToUpdate = breakpointManager.getBreakpoint(breakpointId);
        Breakpoint updatedBreakpoint = breakpointToUpdate.update(breakpointUpdateData);
        breakpointManager.updateBreakpoint(updatedBreakpoint);
    }

    @Override
    public void registerStatusChangeHandler(StatusChangeHandler StatusChangeHandler) {
        synchronized (statusChangeHandlers) {
            statusChangeHandlers.add(StatusChangeHandler);
        }
    }

    @Override
    public int getStackDepth() {
        return visibleDebugFrames.size();
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public boolean breakNow() {
        synchronized (theLock) {
            if (status == Status.STOPPED || status == Status.STOPPING) {
                return false;
            } else {
                shouldBreak.set(true);
                return true;
            }
        }
    }

    @Override
    public Optional<PropertyValue> eval(int depth, String expression) {
        DebugFrame debugFrame = visibleDebugFrames.get(depth);
        if (debugFrame != null) {
            Optional<Variable> result = debugFrame.eval(expression);
            if (result.isPresent()) {
                return Optional.of(convertToPropertyValue(result.get()));
            }
        }
        return Optional.empty();
    }

    @Override
    public StackFrame getFrame(int i) {
        return visibleDebugFrames.size() > 0 ? convertToStackFrame(visibleDebugFrames.get(i)) : null;
    }

    @Override
    public Collection<PropertyValue> getVariables(int i) {
        return visibleDebugFrames.size() > 0
                ? convertToPropertyValues(visibleDebugFrames.get(i).getVariables())
                : Collections.emptyList();
    }

    public void enter(SaxonDebugFrame frame) {
        assert Thread.currentThread() == applicationThread;
        final String uri = frame.getUri();
        final DebugFrame previous = visibleDebugFrames.peekFirst();

        if (frameInSameContextAsLastOne(frame, previous)) {
            log("removing existing previous frame as it is in the same scope");
            visibleDebugFrames.removeFirst();
        }

        if (locationIsDifferentFromPrevious(frame, previous)) {
            logFramesBeforeAddingNewOne();
            visibleDebugFrames.addFirst(frame);
            allFrames.addFirst(frame);
            logFramesAfterAddingNewOne();
        } else {
            allFrames.addFirst(frame);
        }

        if (isStopped()) {
            throw new DebuggerStoppedException();
        } else {
            processChangeOfFrame(frame, uri, previous);
        }
    }

    public void leave() {
        assert Thread.currentThread() == applicationThread;
        if (isStopped()) {
            throw new DebuggerStoppedException();
        }
        logFramesBeforeRemove();
        DebugFrame removedFrame = allFrames.removeFirst();
        logFramesAfterRemove(removedFrame);
        if (removedFrame.equals(visibleDebugFrames.peekFirst())) {
            visibleDebugFrames.removeFirst();
        }
    }

    QueryModule getMainModule() {
        return mainModule;
    }

    DebugFrame getCurrentFrame() {
        return visibleDebugFrames.peekFirst();
    }

    @Override
    protected void doAdditionalConfiguration(Processor processor) {
        Configuration configuration = processor.getUnderlyingConfiguration();
        configuration.setBooleanProperty(FeatureKeys.EAGER_EVALUATION, true);
        StaticQueryContext context = configuration.getDefaultStaticQueryContext();
        configuration.setTraceListener(new SaxonTraceListener(this));
        context.setCodeInjector(new SaxonExtendedTraceCodeInjector());
        Optimizer optimizer = configuration.obtainOptimizer();
        optimizer.setOptimizationLevel(Optimizer.NO_OPTIMIZATION);
    }

    @Override
    protected void setMainModule(QueryModule mainModule) {
        this.mainModule = mainModule;
    }


    private boolean frameInSameContextAsLastOne(SaxonDebugFrame frame, DebugFrame previous) {
        return previous != null && sameFile(previous, frame) && (sameFunction(previous, frame) || bothInMain(frame, previous))
                && differentLine(previous, frame) && visibleDebugFrames.size() > 0;
    }

    private void processChangeOfFrame(SaxonDebugFrame frame, String uri, DebugFrame previous) {
        if (steppingOver && shouldChangeToBreakAfterStepOver(frame, previous)) {
            changeToBreak();
            return;
        }
        if (steppingInto && shouldChangeToBreakAfterStepInto(frame, previous)) {
            changeToBreak();
            return;
        }
        if (steppingOut && shouldChangeToBreakAfterStepOut(frame, previous)) {
            changeToBreak();
            return;
        }

        if (shouldBreakAfterBreakpointWasReached(frame, previous, uri)) {
            changeToBreak();
            return;
        }
        if (shouldBreakAfterBreakWasRequested()) {
            changeToBreak();
        }
    }

    private boolean shouldChangeToBreakAfterStepOver(SaxonDebugFrame frame, DebugFrame previous) {
        log("stackDepthAtSteppingStart = " + stackDepthAtSteppingStart);
        log("previous = " + previous);
        log("frame = " + frame);
        if (sameFile(previous, frame) && sameFunction(previous, frame) && differentLine(previous, frame)) {
            log("suspending after stepping over, same file, same function, different line");
            return true;
        }
        if (sameFile(previous, frame) && !sameFunction(previous, frame) && visibleDebugFrames.size() <= stackDepthAtSteppingStart) {
            log("suspending after stepping over, same file, different function, stack depth not deeper");
            return true;
        }
        if (!sameFile(previous, frame) && visibleDebugFrames.size() <= stackDepthAtSteppingStart) {
            log("suspending after stepping over, different file, stack depth not deeper");
            return true;
        }
        return false;
    }

    private boolean shouldChangeToBreakAfterStepInto(SaxonDebugFrame frame, DebugFrame previous) {
        log("stackDepthAtSteppingStart = " + stackDepthAtSteppingStart);
        log("previous = " + previous);
        log("frame = " + frame);
        if (sameFile(previous, frame) && differentLine(previous, frame)) {
            log("suspending after stepping into a different line");
            return true;
        }
        if (!sameFile(previous, frame)) {
            log("suspending after stepping into a different file");
            return true;
        }
        return false;
    }

    private boolean shouldChangeToBreakAfterStepOut(SaxonDebugFrame frame, DebugFrame previous) {
        log("stackDepthAtSteppingStart = " + stackDepthAtSteppingStart);
        log("previous = " + previous);
        log("frame = " + frame);
        if (sameFile(previous, frame) && !sameFunction(previous, frame) && visibleDebugFrames.size() < stackDepthAtSteppingStart) {
            log("suspending after stepping out, same file, different function, stack depth not deeper");
            changeToBreak();
            return true;
        }
        if (sameFile(previous, frame) && sameFunction(previous, frame) && differentLine(previous, frame) && visibleDebugFrames.size() < stackDepthAtSteppingStart) {
            log("suspending after stepping out, same file, same function as the seemingly previous frame, stack depth not deeper than when started");
            changeToBreak();
            return true;
        }
        if (!sameFile(previous, frame) && visibleDebugFrames.size() < stackDepthAtSteppingStart) {
            log("suspending after stepping out, different file, stack depth not deeper");
            changeToBreak();
            return true;
        }
        return false;
    }

    private boolean shouldBreakAfterBreakpointWasReached(SaxonDebugFrame frame, DebugFrame previous, String uri) {
        final Breakpoint breakpoint = breakpointManager.getBreakpoint(uri, frame.getLineNumber());
        log("breakpoint=" + breakpoint);
        if (breakpoint != null && breakpoint.isEnabled()) {
            if (locationIsDifferentFromPrevious(frame, previous)) {
                if (breakpoint.getType() == BreakpointType.LINE || (breakpoint.getType() == BreakpointType.CONDITIONAL && conditionWasMet(breakpoint))) {
                    log("suspending");
                    if (breakpoint.isTemporary()) {
                        breakpointRemove(breakpoint.getBreakpointId());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean shouldBreakAfterBreakWasRequested() {
        boolean shouldBreakAfterRequest = shouldBreak.compareAndSet(true, false);
        if (shouldBreakAfterRequest) {
            log("suspending as break request was received");
            return true;
        }
        return false;
    }

    private void logFramesBeforeAddingNewOne() {
        log("about to add frame to stack");
        logFrames("visibleDebugFrames", visibleDebugFrames);
        logFrames("allFrames", allFrames);
    }

    private void logFramesAfterAddingNewOne() {
        log("added frame to stack");
        logFrames("visibleDebugFrames", visibleDebugFrames);
        logFrames("allFrames", allFrames);
    }

    private void logFramesAfterRemove(DebugFrame removedFrame) {
        log("removed frame file " + removedFrame.getUri() + " line: " + removedFrame.getLineNumber());
    }

    private void logFramesBeforeRemove() {
        logFrames("allFrames before leave", allFrames);
        logFrames("visibleDebugFrames before leave", visibleDebugFrames);
    }

    private boolean conditionWasMet(Breakpoint breakpoint) {
        Optional<PropertyValue> evaluatedExpression = eval(0, breakpoint.getExpression().get());
        if (evaluatedExpression.isPresent()) {
            PropertyValue propertyValue = evaluatedExpression.get();
            return propertyValue.getType().equals("xs:boolean") && propertyValue.getValue().equals("true");
        }
        return false;
    }

    private void logFrames(String header, LinkedList<DebugFrame> stackFrames) {
        StringBuilder sb = new StringBuilder(header + " till now:");
        sb.append(System.lineSeparator());
        for (DebugFrame frame : stackFrames) {
            sb.append(frame);
            sb.append(System.lineSeparator());
        }
        log(sb.toString());
    }

    private boolean locationIsDifferentFromPrevious(SaxonDebugFrame frame, DebugFrame previous) {
        return previous == null || !sameFile(previous, frame) || differentLine(previous, frame);
    }

    private boolean bothInMain(DebugFrame frame, DebugFrame previous) {
        return isInMain(previous) && isInMain(frame);
    }

    private boolean differentLine(DebugFrame frame1, DebugFrame frame2) {
        return frame1 == null || frame2 == null || frame1.getLineNumber() != frame2.getLineNumber();
    }

    private boolean isInMain(DebugFrame frame) {
        return frame == null || frame.getFunctionName() == null;
    }

    private boolean sameFunction(DebugFrame frame1, DebugFrame frame2) {
        return bothInMain(frame1, frame2) || (frame1 != null && frame1.getFunctionName() != null && frame1.getFunctionName().equals(frame2.getFunctionName()));
    }

    private boolean sameFile(DebugFrame frame1, DebugFrame frame2) {
        return frame1 == null || frame1.getUri().equals(frame2.getUri());
    }

    private Collection<PropertyValue> convertToPropertyValues(List<Variable> variables) {
        Collection<PropertyValue> result = new ArrayList<PropertyValue>(variables.size());
        for (Variable variable : variables) {
            result.add(convertToPropertyValue(variable));
        }
        return result;
    }

    private PropertyValue convertToPropertyValue(Variable variable) {
        return new PropertyValue(variable.name, variable.type, variable.value);
    }

    private StackFrame convertToStackFrame(DebugFrame debugFrame) {
        return new StackFrame(debugFrame.getUri(), debugFrame.getLineNumber(), debugFrame.getFunctionName());
    }

    private Runnable getApplicationRunnable() {
        return new Runnable() {
            public void run() {
                log("app.run()");
                try {
                    synchronized (theLock) {
                        log("changing status to running");
                        changeState(Status.RUNNING);
                    }
                    log("running the app");
                    SaxonDebuggerApp.super.runApp();
                    log("ran the app");
                    changeToStopped();
                } catch (Exception e) {
                    log("got exception: " + e.getMessage());
                    e.printStackTrace();
                    changeToStopped();
                }
            }
        };
    }

    private void changeState(Status newState) {
        Status previous = status;
        status = newState;
        theLock.notifyAll();
        notifyStateChange(previous, status);
    }

    private void startApplicationRunInSeparateThread() {
        assert status == Status.STARTING : "Already started";
        log("start()");
        applicationThread.start();

        try {
            synchronized (theLock) {
                while (status == Status.STARTING) {
                    log("waiting for start");
                    theLock.wait();
                }
                log("not waiting anymore for start");
            }
        } catch (InterruptedException e) {
        }
    }

    private void changeToBreak() throws DebuggerStoppedException {
        try {
            synchronized (theLock) {
                steppingOver = false;
                steppingInto = false;
                steppingOut = false;
                log("changeToBreak in status=" + status);
                log("suspended");
                changeState(Status.BREAK);

                log("starting to wait");
                do {
                    log("waiting until not suspended");
                    theLock.wait();
                }
                while (status == Status.BREAK);
                log("not suspended anymore=" + status);

                if (status == Status.STOPPED) {
                    throw new DebuggerStoppedException();
                }
            }
        } catch (InterruptedException e) {
            throw new DebuggerStoppedException();
        }
    }

    private void resume() throws DebuggerStoppedException {
        log("resume()");
        synchronized (theLock) {
            log("resume=" + status);
            if (status == Status.STOPPED) {
                throw new DebuggerStoppedException();
            } else if (status != Status.BREAK) {
                throw new IllegalStateException();
            }

            log("changing status to running");
            changeState(Status.RUNNING);
            log("status running");
        }
    }

    private boolean isStopped() {
        synchronized (theLock) {
            log("isStopped=" + status);
            return status == Status.STOPPED;
        }
    }

    private void changeToStopped() {
        assert Thread.currentThread() == applicationThread;
        log("changeToStopped()");
        synchronized (theLock) {
            log("about to stop");
            changeState(Status.STOPPING);
            changeState(Status.STOPPED);
            log("stopped");
        }
    }

    private void notifyStateChange(Status previous, Status current) {
        log("changing status from " + previous + " to " + current);
        List<StatusChangeHandler> handlersToRemove = new ArrayList<StatusChangeHandler>();
        synchronized (statusChangeHandlers) {
            for (StatusChangeHandler StatusChangeHandler : statusChangeHandlers) {
                if (StatusChangeHandler.applicableFor(previous, current)) {
                    log("status change handler " + StatusChangeHandler + " is applicable");
                    StatusChangeHandler.statusChanged(previous, current);
                    handlersToRemove.add(StatusChangeHandler);
                }
            }
            for (StatusChangeHandler StatusChangeHandler : handlersToRemove) {
                statusChangeHandlers.remove(StatusChangeHandler);
            }
        }
    }
}