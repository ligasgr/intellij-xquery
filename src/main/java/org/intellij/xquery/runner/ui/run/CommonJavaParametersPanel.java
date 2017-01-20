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

package org.intellij.xquery.runner.ui.run;

import com.intellij.execution.ExecutionBundle;
import com.intellij.execution.ui.CommonProgramParametersPanel;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.RawCommandLineEditor;
import com.intellij.util.ui.UIUtil;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;

import javax.swing.*;
import java.awt.*;

// Modified copy of com.intellij.execution.ui.CommonJavaParametersPanel which is not available in anything but IDEA
public class CommonJavaParametersPanel extends CommonProgramParametersPanel {
    private LabeledComponent<RawCommandLineEditor> myVMParametersComponent;

    public CommonJavaParametersPanel() {
        super();
    }

    @Override
    protected void addComponents() {
        myVMParametersComponent = LabeledComponent.create(new RawCommandLineEditor(), ExecutionBundle.message("run.configuration.java.vm.parameters.label"));
        copyDialogCaption(myVMParametersComponent);

        myVMParametersComponent.setLabelLocation(BorderLayout.WEST);

        add(myVMParametersComponent);
        super.addComponents();
    }

    public void setVMParameters(String text) {
        myVMParametersComponent.getComponent().setText(text);
    }

    public String getVMParameters() {
        return myVMParametersComponent.getComponent().getText();
    }

    @Override
    public void setAnchor(JComponent labelAnchor) {
        super.setAnchor(labelAnchor);
        myVMParametersComponent.setAnchor(labelAnchor);
    }

    @Override
    protected void setupAnchor() {
        super.setupAnchor();
        myAnchor = UIUtil.mergeComponentsWithAnchor(this, myVMParametersComponent);
    }

    public void applyTo(XQueryRunConfiguration configuration) {
        super.applyTo(configuration);
        configuration.setVMParameters(getVMParameters());
    }

    public void reset(XQueryRunConfiguration configuration) {
        super.reset(configuration);
        setVMParameters(configuration.getVMParameters());
    }
}
