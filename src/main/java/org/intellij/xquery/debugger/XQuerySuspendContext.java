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
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.frame.XExecutionStack;
import com.intellij.xdebugger.frame.XSuspendContext;
import org.jetbrains.annotations.Nullable;

public class XQuerySuspendContext extends XSuspendContext {

    private final DBGpIde dbgpIde;
    private final Project project;

    public XQuerySuspendContext(DBGpIde dbgpIde, Project project) {
        this.dbgpIde = dbgpIde;
        this.project = project;
    }


    @Nullable
    @Override
    public XExecutionStack getActiveExecutionStack() {
        return new XQueryExecutionStack("XQuery stack", dbgpIde, project);
    }
}
