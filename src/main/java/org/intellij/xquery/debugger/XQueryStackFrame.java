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
import com.codnos.dbgp.api.Context;
import com.codnos.dbgp.api.PropertyValue;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.ui.ColoredTextContainer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.util.PlatformIcons;
import com.intellij.xdebugger.XDebuggerBundle;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;
import com.intellij.xdebugger.frame.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class XQueryStackFrame extends XStackFrame {
    private final String fileName;
    private final int lineNumber;
    private final String where;
    private final int stackDepth;
    private final DBGpIde dbgpIde;
    private final Project project;

    public XQueryStackFrame(String fileName, int lineNumber, String where, int stackDepth, DBGpIde dbgpIde, Project project) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.where = where;
        this.stackDepth = stackDepth;
        this.dbgpIde = dbgpIde;
        this.project = project;
    }

    @Nullable
    @Override
    public XSourcePosition getSourcePosition() {
        return new XQuerySourcePosition(fileName, lineNumber, project);
    }

    @Override
    public void computeChildren(@NotNull XCompositeNode node) {
        Context currentContext = dbgpIde.contextGet(stackDepth);
        XValueChildrenList myVariables = new XValueChildrenList();
        for (final PropertyValue binding : currentContext.getVariables()) {
            myVariables.add(new XNamedValue(binding.getName()) {
                @Override
                public void computePresentation(@NotNull XValueNode node, @NotNull XValuePlace place) {
                    node.setPresentation(PlatformIcons.VARIABLE_ICON, binding.getType(), binding.getValue(), false);
                }
            });
        }
        node.addChildren(myVariables, true);
    }

    @Override
    public void customizePresentation(@NotNull ColoredTextContainer component) {
        XSourcePosition position = this.getSourcePosition();
        if(position != null) {
            component.append(where != null && !"".equals(where) ? where + "()" : "<main>()", SimpleTextAttributes.REGULAR_ATTRIBUTES);
            component.append(":" + (lineNumber) +", ", SimpleTextAttributes.REGULAR_ATTRIBUTES);
            component.append(position.getFile().getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
            component.append("(" + position.getFile().getPath() + ")", SimpleTextAttributes.GRAYED_ATTRIBUTES);
            component.setIcon(AllIcons.Debugger.StackFrame);
        } else {
            component.append(XDebuggerBundle.message("invalid.frame", new Object[0]), SimpleTextAttributes.ERROR_ATTRIBUTES);
        }

    }

    @Nullable
    @Override
    public XDebuggerEvaluator getEvaluator() {
        return new XQueryEvaluator(dbgpIde, stackDepth);
    }
}
