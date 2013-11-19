/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.util.ui.UIUtil;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.state.run.XQueryRunVariables;
import org.intellij.xquery.runner.ui.datasources.DataSourceDetailsPanel;
import org.intellij.xquery.runner.ui.datasources.DataSourceListPanel;
import org.intellij.xquery.runner.ui.datasources.DataSourcesSettingsForm;
import org.intellij.xquery.runner.ui.run.main.datasource.DataSourceSelector;
import org.intellij.xquery.runner.ui.run.main.datasource.DataSourcesDialog;
import org.intellij.xquery.runner.ui.run.main.module.XQueryModuleSelector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: ligasgr
 * Date: 21/09/13
 * Time: 22:54
 */
public class RunConfigurationMainTab extends SettingsEditor<XQueryRunConfiguration> implements PanelWithAnchor {

    private VariablesPanel variablesPanel;
    private LabeledComponent<XQueryModuleSelector> mainFile;
    private ContextItemPanel contextItemPanel;
    private final Project project;
    private JComponent anchor;
    private JPanel editor;
    private JButton configureDataSourcesButton;
    private LabeledComponent<JComboBox> dataSourceSelectorComponent;
    private DataSourceSelector dataSourceSelector;

    public RunConfigurationMainTab(final Project project) {
        this.project = project;
        dataSourceSelector = new DataSourceSelector(dataSourceSelectorComponent.getComponent());
        configureDataSourcesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataSourceDetailsPanel dataSourceDetailsPanel = new DataSourceDetailsPanel();
                DataSourceListPanel dataSourceListPanel = new DataSourceListPanel(dataSourceDetailsPanel);
                DataSourcesSettingsForm settingsForm = new DataSourcesSettingsForm(XQueryDataSourcesSettings
                        .getInstance()
                        .getDataSourceConfigurations(), dataSourceListPanel, dataSourceDetailsPanel);
                new DataSourcesDialog(editor, dataSourceSelector, settingsForm).show();
            }
        });

        anchor = UIUtil.mergeComponentsWithAnchor(mainFile, contextItemPanel, variablesPanel);
    }

    @Override
    public JComponent getAnchor() {
        return anchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        mainFile.setAnchor(anchor);
        contextItemPanel.setAnchor(anchor);
        variablesPanel.setAnchor(anchor);
    }

    public XQueryModuleSelector getMainFileField() {
        return mainFile.getComponent();
    }

    @Override
    protected void resetEditorFrom(XQueryRunConfiguration configuration) {
        getMainFileField().setText(configuration.getMainFileName());
        variablesPanel.setVariables(configuration.getVariables());
        contextItemPanel.init(configuration.isContextItemEnabled(), configuration.getContextItemText(),
                configuration.getContextItemFile(), configuration.isContextItemFromEditorEnabled(),
                configuration.getContextItemType());
        dataSourceSelector.reset(configuration);
    }

    @Override
    protected void applyEditorTo(XQueryRunConfiguration configuration) throws ConfigurationException {
        configuration.setMainFileName(getMainFileField().getText());
        configuration.setVariables(new XQueryRunVariables(variablesPanel.getVariables()));
        configuration.setContextItemEnabled(contextItemPanel.isContextItemEnabled());
        configuration.setContextItemFile(contextItemPanel.getContextItemPath());
        configuration.setContextItemText(contextItemPanel.getContextItemEditorContent());
        configuration.setContextItemFromEditorEnabled(contextItemPanel.isContextItemFromEditorEnabled());
        configuration.setContextItemType(contextItemPanel.getContextItemType());
        dataSourceSelector.applyTo(configuration);
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
        mainFile = new LabeledComponent<XQueryModuleSelector>();
        mainFile.setComponent(new XQueryModuleSelector(project));
        contextItemPanel = new ContextItemPanel(project);
    }
}
