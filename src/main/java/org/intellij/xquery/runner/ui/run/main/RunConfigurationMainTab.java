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

package org.intellij.xquery.runner.ui.run.main;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.util.ui.UIUtil;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.ui.run.main.datasource.DataSourcePanel;
import org.intellij.xquery.runner.ui.run.main.module.ModuleSelectionPanel;
import org.intellij.xquery.runner.ui.run.main.variables.ContextItemPanel;
import org.intellij.xquery.runner.ui.run.main.variables.VariablesPanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * User: ligasgr
 * Date: 21/09/13
 * Time: 22:54
 */
public class RunConfigurationMainTab extends SettingsEditor<XQueryRunConfiguration> implements PanelWithAnchor {

    private ModuleSelectionPanel moduleSelectionPanel;
    private VariablesPanel variablesPanel;
    private ContextItemPanel contextItemPanel;
    private DataSourcePanel dataSourcePanel;
    private final Project project;
    private JComponent anchor;
    private JPanel editor;

    public RunConfigurationMainTab(final Project project) {
        this.project = project;
        anchor = UIUtil.mergeComponentsWithAnchor(moduleSelectionPanel, contextItemPanel, variablesPanel, dataSourcePanel);
    }

    @Override
    public JComponent getAnchor() {
        return anchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        moduleSelectionPanel.setAnchor(anchor);
        contextItemPanel.setAnchor(anchor);
        variablesPanel.setAnchor(anchor);
        dataSourcePanel.setAnchor(anchor);
    }

    @Override
    protected void resetEditorFrom(XQueryRunConfiguration configuration) {
        moduleSelectionPanel.init(configuration);
        variablesPanel.init(configuration);
        contextItemPanel.init(configuration);
        dataSourcePanel.init(configuration);
    }

    @Override
    protected void applyEditorTo(XQueryRunConfiguration configuration) throws ConfigurationException {
        moduleSelectionPanel.applyChanges(configuration);
        variablesPanel.applyChanges(configuration);
        contextItemPanel.applyChanges(configuration);
        dataSourcePanel.applyChanges(configuration);
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return editor;
    }

    @Override
    protected void disposeEditor() {
    }

    private void createUIComponents() {
        contextItemPanel = new ContextItemPanel(project);
        moduleSelectionPanel = new ModuleSelectionPanel(project);
    }
}
