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

package org.intellij.xquery.runner.ui.datasources;

import com.intellij.openapi.ui.LabeledComponent;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: ligasgr
 * Date: 02/10/13
 * Time: 22:11
 */
public class DataSourceMainConfigurationPanel {

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

    public DataSourceMainConfigurationPanel(final XQueryDataSourceConfiguration dataSourceConfiguration,
                                            DocumentListener nameChangedListener) {
        dataSourceType = dataSourceConfiguration.TYPE;
        name.getComponent().setText(dataSourceConfiguration.NAME);
        name.getComponent().setName(NAME_FIELD);
        setAsDefaultButton.setName(SET_AS_DEFAULT_BUTTON_NAME);
        isDefault = dataSourceConfiguration.DEFAULT;
        name.getComponent().getDocument().addDocumentListener(nameChangedListener);
        initConfigurationFilePanel(dataSourceConfiguration);
        initConnectionParametersPanel(dataSourceConfiguration);
        initUserDefinedLibraryPanel(dataSourceConfiguration);
        initSetAsDefaultButton();
    }

    private void initSetAsDefaultButton() {
        setAsDefaultButton.setEnabled(!isDefault);
        setAsDefaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDefault = true;
                setAsDefaultButton.setEnabled(false);
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
        currentConfiguration.CONFIG_ENABLED = configurationFilePanel.isConfigurationEnabled();
        currentConfiguration.CONFIG_FILE = configurationFilePanel.getConfigFile();
        currentConfiguration.HOST = connectionParametersPanel.getHost();
        currentConfiguration.PORT = connectionParametersPanel.getPort();
        currentConfiguration.USERNAME = connectionParametersPanel.getUsername();
        currentConfiguration.PASSWORD = connectionParametersPanel.getPassword();
        currentConfiguration.USER_DEFINED_LIBRARY_ENABLED = userDefinedLibraryPanel.isUserDefinedLibraryEnabled();
        currentConfiguration.USER_DEFINED_LIBRARY_PATHS = userDefinedLibraryPanel.getUserDefinedLibraryPaths();
        currentConfiguration.DATABASE_NAME = connectionParametersPanel.getDatabaseName();
        currentConfiguration.DEFAULT = isDefault;
        return currentConfiguration;
    }

    private void initConfigurationFilePanel(XQueryDataSourceConfiguration cfg) {
        configurationFilePanel.init(cfg.TYPE, cfg.CONFIG_ENABLED, cfg.CONFIG_FILE);
    }

    private void initConnectionParametersPanel(XQueryDataSourceConfiguration cfg) {
        connectionParametersPanel.init(cfg.TYPE, cfg.HOST, cfg.PORT, cfg.USERNAME, cfg.PASSWORD, cfg.DATABASE_NAME);
    }

    private void initUserDefinedLibraryPanel(XQueryDataSourceConfiguration cfg) {
        userDefinedLibraryPanel.init(cfg.USER_DEFINED_LIBRARY_ENABLED, cfg.USER_DEFINED_LIBRARY_PATHS);
    }
}
