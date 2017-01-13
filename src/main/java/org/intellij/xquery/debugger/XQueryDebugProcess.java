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

package org.intellij.xquery.debugger;

import com.codnos.dbgp.api.Breakpoint;
import com.codnos.dbgp.api.DBGpIde;
import com.codnos.dbgp.api.DebuggerIde;
import com.codnos.dbgp.api.Status;
import com.codnos.dbgp.api.SystemInfo;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ExecutionConsole;
import com.intellij.openapi.Disposable;
import com.intellij.util.ui.UIUtil;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XExpression;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.breakpoints.XBreakpointHandler;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.intellij.xdebugger.frame.XSuspendContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.codnos.dbgp.api.Breakpoint.aConditionalBreakpoint;
import static com.codnos.dbgp.api.Breakpoint.aLineBreakpoint;

public class XQueryDebugProcess extends XDebugProcess implements Disposable {

    private final ExecutionConsole executionConsole;
    private final DBGpIde dbgpIde;
    private final ProcessHandler processHandler;
    private final XBreakpointHandler<?>[] xBreakpointHandlers = new XBreakpointHandler<?>[]{
            new XQueryBreakpointHandler(this, XQueryBreakpointType.class),
    };
    private final Map<String, Breakpoint> breakpointLocationToBreakpoint = new HashMap<>();

    public XQueryDebugProcess(XDebugSession session, ExecutionResult executionResult, DBGpIde dbgpIde) {
        super(session);
        this.dbgpIde = dbgpIde;
        executionConsole = executionResult.getExecutionConsole();
        processHandler = executionResult.getProcessHandler();
    }

    @Override
    public boolean checkCanInitBreakpoints() {
        return false;
    }

    @Nullable
    @Override
    protected ProcessHandler doGetProcessHandler() {
        return processHandler;
    }

    @NotNull
    @Override
    public ExecutionConsole createConsole() {
        return executionConsole;
    }

    @NotNull
    @Override
    public XBreakpointHandler<?>[] getBreakpointHandlers() {
        return xBreakpointHandlers;
    }

    @NotNull
    @Override
    public XDebuggerEditorsProvider getEditorsProvider() {
        return new XQueryEditorsProvider();
    }

    @Override
    public void startStepOver(XSuspendContext context) {
        dbgpIde.stepOver();
    }

    @Override
    public void startStepInto(XSuspendContext context) {
        dbgpIde.stepInto();
    }

    @Override
    public void startStepOut(XSuspendContext context) {
        dbgpIde.stepOut();
    }

    @Override
    public void startPausing() {
        dbgpIde.breakNow();
    }

    @Override
    public void stop() {
    }

    @Override
    public void resume(XSuspendContext context) {
        dbgpIde.run();
    }

    @Override
    public void runToPosition(@NotNull XSourcePosition position, XSuspendContext context) {
        String fileURL = XQueryBreakpointHandler.getFileUrl(position);
        int lineNumber = XQueryBreakpointHandler.getActualLineNumber(position.getLine());
        dbgpIde.breakpointSet(aLineBreakpoint(fileURL, lineNumber).withTemporary(true).build());
        dbgpIde.run();
    }

    public void setBreakpoint(String fileURL, int lineNumber, XExpression conditionExpression) {
        Breakpoint breakpoint = dbgpIde.breakpointSet(createBreakpoint(fileURL, lineNumber, conditionExpression));
        breakpointLocationToBreakpoint.put(locationString(fileURL, lineNumber), breakpoint);
    }

    private Breakpoint createBreakpoint(String fileURL, int lineNumber, XExpression conditionExpression) {
        if (conditionExpression != null) {
            return aConditionalBreakpoint(fileURL, lineNumber, conditionExpression.getExpression()).build();
        }
        return aLineBreakpoint(fileURL, lineNumber).build();
    }

    @NotNull
    private String locationString(String fileURL, int lineNumber) {
        return fileURL + "#" + lineNumber;
    }

    public void removeBreakpoint(String fileURL, int lineNumber) {
        dbgpIde.breakpointRemove(breakpointLocationToBreakpoint.get(locationString(fileURL, lineNumber)).getBreakpointId());
    }

    @Override
    public void dispose() {
    }

    public static class XQueryDebuggerIde implements DebuggerIde {

        private final XDebugSession session;
        private final ProcessHandler processHandler;

        public XQueryDebuggerIde(XDebugSession session, ProcessHandler processHandler) {
            this.session = session;
            this.processHandler = processHandler;
        }

        @Override
        public void onConnected(SystemInfo message) {
            UIUtil.invokeLaterIfNeeded(() -> {
                session.initBreakpoints();
                session.resume();
            });
        }

        @Override
        public void onStatus(Status status, DBGpIde dbgpIde) {
            if (Status.STOPPED == status) {
                processHandler.destroyProcess();
            } else if (Status.BREAK == status) {
                UIUtil.invokeLaterIfNeeded(() -> session.positionReached(prepareContext(dbgpIde)));
            }
        }

        private XSuspendContext prepareContext(DBGpIde dbgpIde) {
            return new XQuerySuspendContext(dbgpIde, session.getProject());
        }
    }
}