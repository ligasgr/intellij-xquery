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

import com.intellij.icons.AllIcons;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.breakpoints.XBreakpointHandler;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.XLineBreakpoint;
import org.jetbrains.annotations.NotNull;

public class XQueryBreakpointHandler extends XBreakpointHandler<XLineBreakpoint<XBreakpointProperties>> {
    private final XQueryDebugProcess debugProcess;

    public XQueryBreakpointHandler(XQueryDebugProcess debugProcess, Class<XQueryBreakpointType> breakpointTypeClass) {
        super(breakpointTypeClass);
        this.debugProcess = debugProcess;
    }

    @Override
    public void registerBreakpoint(@NotNull XLineBreakpoint<XBreakpointProperties> breakpoint) {
        final XSourcePosition sourcePosition = breakpoint.getSourcePosition();
        if (isSourcePositionInvalid(sourcePosition)) return;

        final int lineNumber = getActualLineNumber(breakpoint.getLine());
        if (handleInvalidLine(breakpoint, lineNumber)) return;
        debugProcess.setBreakpoint(getFileUrl(sourcePosition), lineNumber, breakpoint.getConditionExpression());
    }

    @Override
    public void unregisterBreakpoint(@NotNull XLineBreakpoint<XBreakpointProperties> breakpoint, boolean temporary) {
        final XSourcePosition sourcePosition = breakpoint.getSourcePosition();
        if (isSourcePositionInvalid(sourcePosition)) return;

        final int lineNumber = getActualLineNumber(breakpoint.getLine());
        if (handleInvalidLine(breakpoint, lineNumber)) return;
        debugProcess.removeBreakpoint(getFileUrl(sourcePosition), lineNumber);
    }

    private boolean handleInvalidLine(XLineBreakpoint<XBreakpointProperties> breakpoint, int lineNumber) {
        if (lineNumber == -1) {
            final XDebugSession session = debugProcess.getSession();
            session.updateBreakpointPresentation(breakpoint, AllIcons.Debugger.Db_invalid_breakpoint,
                    "Unsupported breakpoint position");
            return true;
        }
        return false;
    }

    public static String getFileUrl(XSourcePosition sourcePosition) {
        final VirtualFile file = sourcePosition.getFile();
        return getFileURL(file);
    }

    public static int getActualLineNumber(int line) {
        return line + 1;
    }

    public static String getFileURL(VirtualFile file) {
        return VfsUtil.virtualToIoFile(file).toURI().toASCIIString();
    }

    private boolean isSourcePositionInvalid(XSourcePosition sourcePosition) {
        return sourcePosition == null || !sourcePosition.getFile().exists() || !sourcePosition.getFile().isValid();
    }
}
