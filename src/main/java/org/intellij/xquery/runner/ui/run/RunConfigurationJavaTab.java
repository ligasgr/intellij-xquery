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

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.util.ui.UIUtil;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: ligasgr
 * Date: 04/08/13
 * Time: 16:39
 */
public class RunConfigurationJavaTab extends SettingsEditor<XQueryRunConfiguration> implements PanelWithAnchor {

    private JPanel editor;
    private CommonJavaParametersPanel commonJavaParametersPanel;
    private LabeledComponent<JComboBox> moduleSelectorComponent;
    private final ConfigurationModuleSelector moduleSelector;
    private AlternativeJREPanel alternativeJrePanel;
    private JComponent anchor;

    public RunConfigurationJavaTab(final Project project) {
        moduleSelector = new ConfigurationModuleSelector(project, moduleSelectorComponent.getComponent());
        commonJavaParametersPanel.setModuleContext(moduleSelector.getModule());
        moduleSelectorComponent.getComponent().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                commonJavaParametersPanel.setModuleContext(moduleSelector.getModule());
            }
        });

        anchor = UIUtil.mergeComponentsWithAnchor(commonJavaParametersPanel, alternativeJrePanel,
                moduleSelectorComponent);
    }

    public CommonJavaParametersPanel getCommonProgramParameters() {
        return commonJavaParametersPanel;
    }

    @Override
    public JComponent getAnchor() {
        return anchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        this.anchor = anchor;
        commonJavaParametersPanel.setAnchor(anchor);
        alternativeJrePanel.setAnchor(anchor);
        moduleSelectorComponent.setAnchor(anchor);
    }

    @Override
    protected void resetEditorFrom(XQueryRunConfiguration configuration) {
        commonJavaParametersPanel.reset(configuration);
        moduleSelector.reset(configuration);
        alternativeJrePanel.init(configuration.getAlternativeJrePath(), configuration.isAlternativeJrePathEnabled());
    }

    @Override
    protected void applyEditorTo(XQueryRunConfiguration configuration) throws ConfigurationException {
        commonJavaParametersPanel.applyTo(configuration);
        moduleSelector.applyTo(configuration);
        configuration.setAlternativeJrePath(alternativeJrePanel.getPath());
        configuration.setAlternativeJrePathEnabled(alternativeJrePanel.isPathEnabled());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return editor;
    }

    @Override
    protected void disposeEditor() {

    }
}
