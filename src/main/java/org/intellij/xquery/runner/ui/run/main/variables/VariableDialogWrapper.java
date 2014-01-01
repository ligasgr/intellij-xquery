/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.ui.run.main.variables;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.intellij.xquery.runner.state.run.XQueryRunVariable;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;

/**
 * User: ligasgr
 * Date: 11/11/13
 * Time: 14:11
 */
public class VariableDialogWrapper extends DialogWrapper {

    private VariableDialog variableDialog;

    public VariableDialogWrapper(VariableDialog variableDialog) {
        super(true);
        this.variableDialog = variableDialog;
        setTitle("Edit variable");
        init();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        return variableDialog.validate();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return variableDialog.getPanel();
    }

    public void init(XQueryRunVariable variable) {
        variableDialog.init(variable);
    }

    public void applyValuesTo(XQueryRunVariable variable) {
        variableDialog.applyValuesTo(variable);
    }
}
