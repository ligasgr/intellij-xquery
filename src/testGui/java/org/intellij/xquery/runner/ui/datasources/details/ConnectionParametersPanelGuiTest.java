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

import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.MARKLOGIC;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.SAXON;
import static org.intellij.xquery.runner.ui.datasources.details.ConnectionParametersPanel.DATABASE_NAME_FIELD_NAME;
import static org.intellij.xquery.runner.ui.datasources.details.ConnectionParametersPanel.HOST_FIELD_NAME;
import static org.intellij.xquery.runner.ui.datasources.details.ConnectionParametersPanel.PASSWORD_FIELD_NAME;
import static org.intellij.xquery.runner.ui.datasources.details.ConnectionParametersPanel.PORT_FIELD_NAME;
import static org.intellij.xquery.runner.ui.datasources.details.ConnectionParametersPanel.USERNAME_FIELD_NAME;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 26/10/13
 * Time: 20:56
 */
public class ConnectionParametersPanelGuiTest extends BaseGuiTest {

    private static final XQueryDataSourceType NO_CONNECTION_PARAMETERS_TYPE = SAXON;
    private static final XQueryDataSourceType CONNECTION_PARAMETERS_TYPE = MARKLOGIC;
    private static final String HOST = "host";
    private static final String PORT = "123";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass";
    private static final String DATABASE = "db";
    private ConnectionParametersPanel panel;
    private DataSourceConfigurationAggregatingPanel aggregatingPanel;
    private ConfigurationChangeListener listener;
    private XQueryDataSourceConfiguration cfg;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new ConnectionParametersPanel();
        return new PanelTestingFrame(panel.getMainPanel());
    }


    @Before
    public void setUp() throws Exception {
        super.setUp();
        cfg = new XQueryDataSourceConfiguration();
        aggregatingPanel = mock(DataSourceConfigurationAggregatingPanel.class);
        given(aggregatingPanel.getCurrentConfigurationState()).willReturn(cfg);
        listener = mock(ConfigurationChangeListener.class);
    }

    @Test
    public void shouldHideWhenConnectionParametersNotSupported() {
        cfg.TYPE = NO_CONNECTION_PARAMETERS_TYPE;
        panel.init(cfg, aggregatingPanel, listener);

        assertThat(panel.getMainPanel().isVisible(), is(false));
    }

    @Test
    public void shouldShowWhenConnectionParametersSupported() {
        setUpVisiblePanel();

        assertThat(panel.getMainPanel().isVisible(), is(true));
    }

    @Test
    public void shouldInitializeHost() throws Exception {
        cfg.HOST = HOST;

        setUpVisiblePanel();

        assertThat(panel.getHost(), is(HOST));
    }

    @Test
    public void shouldInitializePort() throws Exception {
        cfg.PORT = PORT;

        setUpVisiblePanel();

        assertThat(panel.getPort(), is(PORT));
    }

    @Test
    public void shouldInitializeUsername() throws Exception {
        cfg.USERNAME = USERNAME;

        setUpVisiblePanel();

        assertThat(panel.getUsername(), is(USERNAME));
    }

    @Test
    public void shouldInitializePassword() throws Exception {
        cfg.PASSWORD = PASSWORD;

        setUpVisiblePanel();

        assertThat(panel.getPassword(), is(PASSWORD));
    }

    @Test
    public void shouldInitializeDatabase() throws Exception {
        cfg.DATABASE_NAME = DATABASE;

        setUpVisiblePanel();

        assertThat(panel.getDatabaseName(), is(DATABASE));
    }

    @Test
    public void shouldChangeHost() throws Exception {
        setUpVisiblePanel();

        window.textBox(HOST_FIELD_NAME).enterText(HOST);

        assertThat(panel.getHost(), is(HOST));
    }

    @Test
    public void shouldChangePort() throws Exception {
        setUpVisiblePanel();

        window.textBox(PORT_FIELD_NAME).enterText(PORT);

        assertThat(panel.getPort(), is(PORT));
    }

    @Test
    public void shouldChangeUsername() throws Exception {
        setUpVisiblePanel();

        window.textBox(USERNAME_FIELD_NAME).enterText(USERNAME);

        assertThat(panel.getUsername(), is(USERNAME));
    }

    @Test
    public void shouldChangePassword() throws Exception {
        setUpVisiblePanel();

        window.textBox(PASSWORD_FIELD_NAME).enterText(PASSWORD);

        assertThat(panel.getPassword(), is(PASSWORD));
    }

    @Test
    public void shouldChangeDatabase() throws Exception {
        setUpVisiblePanel();

        window.textBox(DATABASE_NAME_FIELD_NAME).enterText(DATABASE);

        assertThat(panel.getDatabaseName(), is(DATABASE));
    }

    @Test
    public void shouldInvokeListenerAfterChangeOfHost() throws Exception {
        setUpVisiblePanel();

        window.textBox(HOST_FIELD_NAME).enterText(HOST);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    @Test
    public void shouldInvokeListenerAfterChangeOfPort() throws Exception {
        setUpVisiblePanel();

        window.textBox(PORT_FIELD_NAME).enterText(PORT);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    @Test
    public void shouldInvokeListenerAfterChangeOfUsername() throws Exception {
        setUpVisiblePanel();

        window.textBox(USERNAME_FIELD_NAME).enterText(USERNAME);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    @Test
    public void shouldInvokeListenerAfterChangeOfPassword() throws Exception {
        setUpVisiblePanel();

        window.textBox(PASSWORD_FIELD_NAME).enterText(PASSWORD);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    @Test
    public void shouldInvokeListenerAfterChangeOfDatabase() throws Exception {
        setUpVisiblePanel();

        window.textBox(DATABASE_NAME_FIELD_NAME).enterText(DATABASE);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    private void setUpVisiblePanel() {
        cfg.TYPE = CONNECTION_PARAMETERS_TYPE;
        panel.init(cfg, aggregatingPanel, listener);
    }

    private void verifyChangeListenerInvokedForCurrentConfigurationState() {
        verify(aggregatingPanel, atLeast(1)).getCurrentConfigurationState();
        verify(listener, atLeast(1)).changeApplied(cfg);
    }
}
