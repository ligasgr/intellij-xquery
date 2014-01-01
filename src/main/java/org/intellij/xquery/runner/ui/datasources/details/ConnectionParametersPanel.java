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

import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.DocumentAdapter;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * User: ligasgr
 * Date: 06/10/13
 * Time: 17:51
 */
public class ConnectionParametersPanel {
    public static final String HOST_FIELD_NAME = "host";
    public static final String PORT_FIELD_NAME = "port";
    public static final String USERNAME_FIELD_NAME = "username";
    public static final String PASSWORD_FIELD_NAME = "password";
    public static final String DATABASE_NAME_FIELD_NAME = "databaseName";
    private JPanel mainPanel;
    private LabeledComponent<JTextField> host;
    private LabeledComponent<JTextField> port;
    private LabeledComponent<JTextField> username;
    private LabeledComponent<JTextField> password;
    private LabeledComponent<JTextField> databaseName;

    public ConnectionParametersPanel() {
        host.getComponent().setName(HOST_FIELD_NAME);
        port.getComponent().setName(PORT_FIELD_NAME);
        username.getComponent().setName(USERNAME_FIELD_NAME);
        password.getComponent().setName(PASSWORD_FIELD_NAME);
        databaseName.getComponent().setName(DATABASE_NAME_FIELD_NAME);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public String getHost() {
        return host.getComponent().getText();
    }

    public String getPort() {
        return port.getComponent().getText();
    }

    public String getUsername() {
        return username.getComponent().getText();
    }

    public String getPassword() {
        return password.getComponent().getText();
    }

    public String getDatabaseName() {
        return databaseName.getComponent().getText();
    }

    public void init(XQueryDataSourceConfiguration cfg, DataSourceConfigurationAggregatingPanel
            aggregatingPanel, ConfigurationChangeListener listener) {
        mainPanel.setVisible(cfg.TYPE.connectionPropertiesAreSupported());
        this.host.getComponent().setText(cfg.HOST);
        this.port.getComponent().setText(cfg.PORT);
        this.username.getComponent().setText(cfg.USERNAME);
        this.password.getComponent().setText(cfg.PASSWORD);
        this.databaseName.getComponent().setText(cfg.DATABASE_NAME);
        setUpChangeListeners(aggregatingPanel, listener);
    }

    public void updateConfigurationWithChanges(XQueryDataSourceConfiguration currentConfiguration) {
        currentConfiguration.HOST = getHost();
        currentConfiguration.PORT = getPort();
        currentConfiguration.USERNAME = getUsername();
        currentConfiguration.PASSWORD = getPassword();
        currentConfiguration.DATABASE_NAME = getDatabaseName();
    }

    private void setUpChangeListeners(final DataSourceConfigurationAggregatingPanel
                                              aggregatingPanel,
                                      final ConfigurationChangeListener listener) {
        DocumentListener textFieldListener = new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent e) {
                listener.changeApplied(aggregatingPanel
                        .getCurrentConfigurationState());
            }
        };
        host.getComponent().getDocument().addDocumentListener(textFieldListener);
        port.getComponent().getDocument().addDocumentListener(textFieldListener);
        username.getComponent().getDocument().addDocumentListener(textFieldListener);
        password.getComponent().getDocument().addDocumentListener(textFieldListener);
        databaseName.getComponent().getDocument().addDocumentListener(textFieldListener);
    }
}
