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
import com.codnos.dbgp.api.PropertyValue;
import com.intellij.util.PlatformIcons;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;
import com.intellij.xdebugger.frame.XValue;
import com.intellij.xdebugger.frame.XValueNode;
import com.intellij.xdebugger.frame.XValuePlace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Optional;

public class XQueryEvaluator extends XDebuggerEvaluator {
    private final DBGpIde dbgpIde;
    private final int stackDepth;

    public XQueryEvaluator(DBGpIde dbgpIde, int stackDepth) {
        this.dbgpIde = dbgpIde;
        this.stackDepth = stackDepth;
    }

    @Override
    public boolean isCodeFragmentEvaluationSupported() {
        return false;
    }

    @Override
    public void evaluate(@NotNull String expression, @NotNull XEvaluationCallback callback, @Nullable XSourcePosition xSourcePosition) {
        try {
            Optional<PropertyValue> result = dbgpIde.eval(stackDepth, expression);
            if (result.isPresent()) {
                callback.evaluated(new XQueryValue(result.get()));
            } else {
                callback.errorOccurred("Unable to evaluate expression");
            }
        } catch (Exception e) {
            callback.errorOccurred(e.getMessage() != null ? e.getMessage() : e.toString());
        }
    }

    private class XQueryValue extends XValue {
        private final PropertyValue propertyValue;

        public XQueryValue(PropertyValue propertyValue) {
            this.propertyValue = propertyValue;
        }

        @Override
        public void computePresentation(@NotNull XValueNode xValueNode, @NotNull XValuePlace xValuePlace) {
            Icon icon = PlatformIcons.PROPERTY_ICON;
            xValueNode.setPresentation(icon, propertyValue.getType(), propertyValue.getValue(), false);
        }
    }
}