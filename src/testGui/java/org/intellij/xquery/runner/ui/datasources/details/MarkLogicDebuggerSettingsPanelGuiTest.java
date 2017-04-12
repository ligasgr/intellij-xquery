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

package org.intellij.xquery.runner.ui.datasources.details;

import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.MARKLOGIC;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.SAXON;
import static org.intellij.xquery.runner.ui.datasources.details.ConnectionParametersPanel.*;
import static org.intellij.xquery.runner.ui.datasources.details.UserDefinedLibraryPanel.PATH_LIST_NAME;
import static org.intellij.xquery.runner.ui.datasources.details.UserDefinedLibraryPanel.USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * User: ligasgr
 * Date: 26/10/13
 * Time: 20:56
 */
public class MarkLogicDebuggerSettingsPanelGuiTest extends BaseGuiTest {

    private static final XQueryDataSourceType NO_CONNECTION_PARAMETERS_TYPE = SAXON;
    private static final XQueryDataSourceType CONNECTION_PARAMETERS_TYPE = MARKLOGIC;
    private static final boolean ENABLED = true;
    private static final boolean DISABLED = false;
    private static final String PORT = "123";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass";
    private MarkLogicDebuggerSettingsPanel panel;
    private DataSourceConfigurationAggregatingPanel aggregatingPanel;
    private ConfigurationChangeListener listener;
    private XQueryDataSourceConfiguration cfg;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new MarkLogicDebuggerSettingsPanel();
        return new PanelTestingFrame (panel.getMainPanel());
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
        setUpVisiblePanel (ENABLED);

        assertThat(panel.getMainPanel().isVisible(), is(true));
    }

    @Test
    public void shouldInitializePort() throws Exception {
        cfg.MLDEBUGGER_CONFIG_PORT = PORT;

        setUpVisiblePanel (ENABLED);

        assertThat(panel.getPort(), is(PORT));
    }

    @Test
    public void shouldInitializeUsername() throws Exception {
        cfg.MLDEBUGGER_CONFIG_USER = USERNAME;

        setUpVisiblePanel (ENABLED);

        assertThat(panel.getUser(), is(USERNAME));
    }

    @Test
    public void shouldInitializePassword() throws Exception {
        cfg.MLDEBUGGER_CONFIG_PASSWORD = PASSWORD;

        setUpVisiblePanel (ENABLED);

        assertThat(panel.getPassword(), is(PASSWORD));
    }

    @Test
    public void shouldChangePort() throws Exception {
        setUpVisiblePanel (ENABLED);

        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PORT_FIELD_NAME).enterText(PORT);

        assertThat(panel.getPort(), is(PORT));
    }

    @Test
    public void shouldChangeUsername() throws Exception {
        setUpVisiblePanel (ENABLED);

        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_USER_FIELD_NAME).enterText (USERNAME);

        assertThat (panel.getUser(), is(USERNAME));
    }

    @Test
    public void shouldChangePassword() throws Exception {
        setUpVisiblePanel (ENABLED);

        window.textBox(MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PASSWORD_FIELD_NAME).enterText (PASSWORD);

        assertThat(panel.getPassword(), is(PASSWORD));
    }

    @Test
    public void shouldInvokeListenerAfterChangeOfPort() throws Exception {
        setUpVisiblePanel (ENABLED);

        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PORT_FIELD_NAME).enterText (PORT);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    @Test
    public void shouldInvokeListenerAfterChangeOfUsername() throws Exception {
        setUpVisiblePanel (ENABLED);

        window.textBox(MarkLogicDebuggerSettingsPanel.MLDEBUGGER_USER_FIELD_NAME).enterText (USERNAME);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    @Test
    public void shouldInvokeListenerAfterChangeOfPassword() throws Exception {
        setUpVisiblePanel (ENABLED);

        window.textBox(MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PASSWORD_FIELD_NAME).enterText(USERNAME);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    @Test
    public void shouldPopulateEnabledFieldAndDisableTextFields() {
        setUpVisiblePanel (DISABLED);

        window.checkBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_ENABLED_FIELD_NAME).requireNotSelected();
        assertThat (panel.isMlDebuggerEnabled(), is (false));

        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PORT_FIELD_NAME).requireDisabled();
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_USER_FIELD_NAME).requireDisabled();
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PASSWORD_FIELD_NAME).requireDisabled();
    }

    @Test
    public void shouldPopulateEnabledFieldAndEnableTextFields() {
        setUpVisiblePanel (ENABLED);

        window.checkBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_ENABLED_FIELD_NAME).requireSelected();
        assertThat (panel.isMlDebuggerEnabled(), is (true));

        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PORT_FIELD_NAME).requireEnabled();
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_USER_FIELD_NAME).requireEnabled();
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PASSWORD_FIELD_NAME).requireEnabled();
    }

    @Test
    public void shouldChangeEnabledFieldToUnchecked() {
        setUpVisiblePanel (ENABLED);

        window.checkBox (USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME).uncheck();

        assertThat(panel.isMlDebuggerEnabled(), is(false));
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PORT_FIELD_NAME).requireDisabled();
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_USER_FIELD_NAME).requireDisabled();
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PASSWORD_FIELD_NAME).requireDisabled();
    }

    @Test
    public void shouldChangeEnabledFieldToChecked() {
        setUpVisiblePanel (DISABLED);

        window.checkBox(USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME).check();

        assertThat(panel.isMlDebuggerEnabled(), is(true));
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PORT_FIELD_NAME).requireEnabled();
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_USER_FIELD_NAME).requireEnabled();
        window.textBox (MarkLogicDebuggerSettingsPanel.MLDEBUGGER_PASSWORD_FIELD_NAME).requireEnabled();
    }

    @Test
    public void shouldReturnIfUserDefineLibraryIsEnabled() {
        setUpVisiblePanel (ENABLED);

        assertThat (panel.isMlDebuggerEnabled(), is(true));
    }

    @Test
    public void shouldPopulateTextFields() {
        cfg.MLDEBUGGER_CONFIG_ENABLED = ENABLED;
        cfg.MLDEBUGGER_CONFIG_PORT = PORT;
        cfg.MLDEBUGGER_CONFIG_USER = USERNAME;
        cfg.MLDEBUGGER_CONFIG_PASSWORD = PASSWORD;

        setUpVisiblePanel (ENABLED);

        assertThat (cfg.MLDEBUGGER_CONFIG_ENABLED, is(true));
        assertThat (cfg.MLDEBUGGER_CONFIG_PORT, is(PORT));
        assertThat (cfg.MLDEBUGGER_CONFIG_USER, is(USERNAME));
        assertThat (cfg.MLDEBUGGER_CONFIG_PASSWORD, is(PASSWORD));
    }

    // ---------------------------------------------------------------

    private void setUpVisiblePanel (boolean enabled) {
        cfg.TYPE = CONNECTION_PARAMETERS_TYPE;
        cfg.MLDEBUGGER_CONFIG_ENABLED = enabled;
        panel.init(cfg, aggregatingPanel, listener);
    }

    private void verifyChangeListenerInvokedForCurrentConfigurationState() {
        verify(aggregatingPanel, atLeast(1)).getCurrentConfigurationState();
        verify(listener, atLeast(1)).changeApplied(cfg);
    }
}
