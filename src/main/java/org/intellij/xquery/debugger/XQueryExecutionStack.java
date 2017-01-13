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
import com.codnos.dbgp.api.StackFrame;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.frame.XExecutionStack;
import com.intellij.xdebugger.frame.XStackFrame;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class XQueryExecutionStack extends XExecutionStack {
    private final DBGpIde dbgpIde;
    private final Project project;

    public XQueryExecutionStack(String displayName, DBGpIde dbgpIde, Project project) {
        super(displayName);
        this.dbgpIde = dbgpIde;
        this.project = project;
    }

    @Nullable
    @Override
    public XStackFrame getTopFrame() {
        int stackDepth = dbgpIde.stackDepth();
        if (stackDepth > 0) {
            return getStackFrame(dbgpIde, dbgpIde.stackGet(0), stackDepth - 1);
        }
        return null;
    }

    @Override
    public void computeStackFrames(int firstFrameIndex, XStackFrameContainer container) {
        List<XStackFrame> frames = new ArrayList<XStackFrame>();
        int stackDepth = dbgpIde.stackDepth();
        for (int i = firstFrameIndex; i < stackDepth; i++) {
            frames.add(getStackFrame(dbgpIde, dbgpIde.stackGet(i), i));
        }
        container.addStackFrames(frames, true);
    }

    private XStackFrame getStackFrame(DBGpIde dbgpIde, StackFrame frameDetails, int stackDepth) {
        String fileName = frameDetails.getFileURL();
        int lineNumber = frameDetails.getLineNumber();
        String where = frameDetails.getWhere();
        return new XQueryStackFrame(fileName, lineNumber, where, stackDepth, dbgpIde, project);
    }
}
