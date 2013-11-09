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

package org.intellij.xquery.runner.ui.datasources.details;

import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.DocumentAdapter;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: ligasgr
 * Date: 02/10/13
 * Time: 22:11
 */
public class DataSourceConfigurationAggregatingPanel {

    public static final String NAME_FIELD = "nameField";
    public static final String SET_AS_DEFAULT_BUTTON_NAME = "setAsDefaultButton";
    private JPanel mainPanel;
    private LabeledComponent<JTextField> name;
    private ConfigurationFilePanel configurationFilePanel;
    private ConnectionParametersPanel connectionParametersPanel;
    private UserDefinedLibraryPanel userDefinedLibraryPanel;
    private JButton setAsDefaultButton;
    private XQueryDataSourceType dataSourceType;
    private boolean isDefault;

    public DataSourceConfigurationAggregatingPanel(final XQueryDataSourceConfiguration dataSourceConfiguration,
                                                   ConfigurationChangeListener configurationChangeListener) {
        dataSourceType = dataSourceConfiguration.TYPE;
        name.getComponent().setText(dataSourceConfiguration.NAME);
        name.getComponent().setName(NAME_FIELD);
        setAsDefaultButton.setName(SET_AS_DEFAULT_BUTTON_NAME);
        isDefault = dataSourceConfiguration.DEFAULT;
        initNameField(configurationChangeListener);
        initConfigurationFilePanel(dataSourceConfiguration, configurationChangeListener);
        initConnectionParametersPanel(dataSourceConfiguration, configurationChangeListener);
        initUserDefinedLibraryPanel(dataSourceConfiguration, configurationChangeListener);
        initSetAsDefaultButton(configurationChangeListener);
    }

    private void initNameField(final ConfigurationChangeListener configurationChangeListener) {
        name.getComponent().getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent e) {
                configurationChangeListener.changeApplied(getCurrentConfigurationState());
            }
        });
    }

    private void initSetAsDefaultButton(final ConfigurationChangeListener configurationChangeListener) {
        setAsDefaultButton.setEnabled(! isDefault);
        setAsDefaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDefault = true;
                setAsDefaultButton.setEnabled(false);
                configurationChangeListener.changeApplied(getCurrentConfigurationState());
            }
        });
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public XQueryDataSourceConfiguration getCurrentConfigurationState() {
        XQueryDataSourceConfiguration currentConfiguration = new XQueryDataSourceConfiguration();
        currentConfiguration.NAME = name.getComponent().getText();
        currentConfiguration.TYPE = dataSourceType;
        configurationFilePanel.updateConfigurationWithChanges(currentConfiguration);
        connectionParametersPanel.updateConfigurationWithChanges(currentConfiguration);
        userDefinedLibraryPanel.updateConfigurationWithChanges(currentConfiguration);
        currentConfiguration.DEFAULT = isDefault;
        return currentConfiguration;
    }

    private void initConfigurationFilePanel(XQueryDataSourceConfiguration cfg,
                                            ConfigurationChangeListener changeListener) {
        configurationFilePanel.init(cfg, this, changeListener);
    }

    private void initConnectionParametersPanel(XQueryDataSourceConfiguration cfg,
                                               ConfigurationChangeListener changeListener) {
        connectionParametersPanel.init(cfg, this, changeListener);
    }

    private void initUserDefinedLibraryPanel(XQueryDataSourceConfiguration cfg,
                                             ConfigurationChangeListener changeListener) {
        userDefinedLibraryPanel.init(cfg, this, changeListener);
    }
}
