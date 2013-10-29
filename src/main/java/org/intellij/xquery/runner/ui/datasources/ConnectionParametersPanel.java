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

import javax.swing.*;

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

    public void init(XQueryDataSourceType type, String host, String port, String username, String password,
                     String databaseName) {
        mainPanel.setVisible(type.connectionPropertiesAreSupported());
        this.host.getComponent().setText(host);
        this.port.getComponent().setText(port);
        this.username.getComponent().setText(username);
        this.password.getComponent().setText(password);
        this.databaseName.getComponent().setText(databaseName);
    }
}
