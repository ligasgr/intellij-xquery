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

package org.intellij.xquery.runner.ui.datasources.details;

import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.components.JBCheckBox;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor;

/**
 * User: ligasgr
 * Date: 03/10/13
 * Time: 15:11
 */
public class ConfigurationFilePanel {

    public static final String CONFIG_FILE = "configFile";
    public static final String CONFIG_ENABLED = "configurationEnabled";
    private JPanel mainPanel;
    private JBCheckBox configurationEnabled;
    private TextFieldWithBrowseButton configFile;

    public ConfigurationFilePanel() {
        configFile.addBrowseFolderListener("Choose file", null, null, createSingleFileNoJarsDescriptor(),
                TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT, false);
        configFile.getTextField().setName(CONFIG_FILE);
        configurationEnabled.addActionListener(getConfigEnabledListener());
    }

    public boolean isConfigurationEnabled() {
        return configurationEnabled.isSelected();
    }

    public String getConfigFile() {
        return configFile.getText();
    }

    public void init(XQueryDataSourceConfiguration cfg, DataSourceConfigurationAggregatingPanel
            dataSourceConfigurationAggregatingPanel, ConfigurationChangeListener changeListener) {
        mainPanel.setVisible(cfg.TYPE.configFileIsSupported());
        this.configurationEnabled.setSelected(cfg.CONFIG_ENABLED);
        configFile.setText(cfg.CONFIG_FILE);
        configurationEnabledChanged();
        setUpChangeListeners(dataSourceConfigurationAggregatingPanel, changeListener);
    }

    private void configurationEnabledChanged() {
        configFile.setEnabled(configurationEnabled.isSelected());
    }

    private ActionListener getConfigEnabledListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                configurationEnabledChanged();
            }
        };
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateConfigurationWithChanges(XQueryDataSourceConfiguration currentConfiguration) {
        currentConfiguration.CONFIG_ENABLED = isConfigurationEnabled();
        currentConfiguration.CONFIG_FILE = getConfigFile();
    }

    private void setUpChangeListeners(final DataSourceConfigurationAggregatingPanel aggregatingPanel,
                                     final ConfigurationChangeListener listener) {
        configurationEnabled.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.changeApplied(aggregatingPanel
                        .getCurrentConfigurationState());
            }
        });
        configFile.getTextField().getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent e) {
                listener.changeApplied(aggregatingPanel.getCurrentConfigurationState());
            }
        });
    }
}
