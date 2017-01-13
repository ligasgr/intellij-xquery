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

import com.codnos.dbgp.api.DBGpIde;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.runners.DefaultProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.util.net.NetUtils;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugProcessStarter;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebuggerManager;
import org.intellij.xquery.runner.XQueryRunProfileState;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.jetbrains.annotations.NotNull;

import static com.codnos.dbgp.api.DBGpFactory.ide;

public class XQueryDebuggerRunner extends DefaultProgramRunner {
    private static final String XQUERY_RUNNER_ID = "XQueryDebuggerRunner";

    @NotNull
    @Override
    public String getRunnerId() {
        return XQUERY_RUNNER_ID;
    }

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
        return DefaultDebugExecutor.EXECUTOR_ID.equals(executorId) && profile instanceof XQueryRunConfiguration && isDebugSupportedFor((XQueryRunConfiguration) profile);
    }

    private boolean isDebugSupportedFor(XQueryRunConfiguration profile) {
        XQueryDataSourceType dataSourceType = profile.getDataSourceType();
        return dataSourceType != null && dataSourceType.isDebugSupported();
    }

    @Override
    protected RunContentDescriptor doExecute(@NotNull RunProfileState state, @NotNull ExecutionEnvironment env) throws ExecutionException {
        return createContentDescriptor(state, env);
    }


    private RunContentDescriptor createContentDescriptor(final RunProfileState runProfileState,
                                                         final ExecutionEnvironment environment) throws ExecutionException {
        XDebuggerManager debuggerManager = XDebuggerManager.getInstance(environment.getProject());
        XDebugProcessStarter processStarter = getProcessStarter(runProfileState, environment);
        final XDebugSession debugSession = debuggerManager.startSession(environment, processStarter);
        return debugSession.getRunContentDescriptor();
    }

    private int getAvailablePort() {
        return NetUtils.tryToFindAvailableSocketPort(9000);
    }

    private XDebugProcessStarter getProcessStarter(final RunProfileState runProfileState, final ExecutionEnvironment
            executionEnvironment) throws ExecutionException {
        int port = getAvailablePort();
        ((XQueryRunProfileState) runProfileState).setPort(port);
        return new XDebugProcessStarter() {
            @NotNull
            public XDebugProcess start(@NotNull final XDebugSession session) throws ExecutionException {
                final ExecutionResult result = runProfileState.execute(executionEnvironment.getExecutor(), XQueryDebuggerRunner.this);
                XQueryDebugProcess.XQueryDebuggerIde debuggerIde = new XQueryDebugProcess.XQueryDebuggerIde(session, result.getProcessHandler());
                final DBGpIde dbgpIde = ide().withPort(port).withDebuggerIde(debuggerIde).build();
                dbgpIde.startListening();
                result.getProcessHandler().addProcessListener(new ProcessAdapter() {
                    @Override
                    public void processTerminated(ProcessEvent event) {
                        dbgpIde.stopListening();
                    }
                });
                return new XQueryDebugProcess(session, result, dbgpIde);
            }
        };
    }
}
