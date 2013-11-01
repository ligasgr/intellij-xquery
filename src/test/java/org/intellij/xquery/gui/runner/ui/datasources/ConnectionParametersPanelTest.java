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

package org.intellij.xquery.gui.runner.ui.datasources;

import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.gui.BaseGuiTest;
import org.intellij.xquery.gui.PanelTestingFrame;
import org.intellij.xquery.runner.ui.datasources.ConnectionParametersPanel;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.MARKLOGIC;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.SAXON;
import static org.intellij.xquery.runner.ui.datasources.ConnectionParametersPanel.DATABASE_NAME_FIELD_NAME;
import static org.intellij.xquery.runner.ui.datasources.ConnectionParametersPanel.HOST_FIELD_NAME;
import static org.intellij.xquery.runner.ui.datasources.ConnectionParametersPanel.PASSWORD_FIELD_NAME;
import static org.intellij.xquery.runner.ui.datasources.ConnectionParametersPanel.PORT_FIELD_NAME;
import static org.intellij.xquery.runner.ui.datasources.ConnectionParametersPanel.USERNAME_FIELD_NAME;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 26/10/13
 * Time: 20:56
 */
public class ConnectionParametersPanelTest extends BaseGuiTest {

    private static final XQueryDataSourceType NO_CONNECTION_PARAMETERS_TYPE = SAXON;
    private static final XQueryDataSourceType CONNECTION_PARAMETERS_TYPE = MARKLOGIC;
    private static final String HOST = "host";
    private static final String PORT = "123";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass";
    private static final String DATABASE = "db";
    private ConnectionParametersPanel panel;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new ConnectionParametersPanel();
        return new PanelTestingFrame(panel.getMainPanel());
    }

    @Test
    public void shouldHideWhenConnectionParametersNotSupported() {
        panel.init(NO_CONNECTION_PARAMETERS_TYPE, null, null, null, null, null);

        assertThat(panel.getMainPanel().isVisible(), is(false));
    }

    @Test
    public void shouldShowWhenConnectionParametersSupported() {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, null, null, null, null);

        assertThat(panel.getMainPanel().isVisible(), is(true));
    }

    @Test
    public void shouldInitializeHost() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, HOST, null, null, null, null);

        assertThat(panel.getHost(), is(HOST));
    }

    @Test
    public void shouldInitializePort() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, PORT, null, null, null);

        assertThat(panel.getPort(), is(PORT));
    }

    @Test
    public void shouldInitializeUsername() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, null, USERNAME, null, null);

        assertThat(panel.getUsername(), is(USERNAME));
    }

    @Test
    public void shouldInitializePassword() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, null, null, PASSWORD, null);

        assertThat(panel.getPassword(), is(PASSWORD));
    }

    @Test
    public void shouldInitializeDatabase() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, null, null, null, DATABASE);

        assertThat(panel.getDatabaseName(), is(DATABASE));
    }

    @Test
    public void shouldChangeHost() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, null, null, null, null);

        window.textBox(HOST_FIELD_NAME).enterText(HOST);

        assertThat(panel.getHost(), is(HOST));
    }

    @Test
    public void shouldChangePort() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, null, null, null, null);

        window.textBox(PORT_FIELD_NAME).enterText(PORT);

        assertThat(panel.getPort(), is(PORT));
    }

    @Test
    public void shouldChangeUsername() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, null, null, null, null);

        window.textBox(USERNAME_FIELD_NAME).enterText(USERNAME);

        assertThat(panel.getUsername(), is(USERNAME));
    }

    @Test
    public void shouldChangePassword() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, null, null, null, null);

        window.textBox(PASSWORD_FIELD_NAME).enterText(PASSWORD);

        assertThat(panel.getPassword(), is(PASSWORD));
    }

    @Test
    public void shouldChangeDatabase() throws Exception {
        panel.init(CONNECTION_PARAMETERS_TYPE, null, null, null, null, null);

        window.textBox(DATABASE_NAME_FIELD_NAME).enterText(DATABASE);

        assertThat(panel.getDatabaseName(), is(DATABASE));
    }
}
