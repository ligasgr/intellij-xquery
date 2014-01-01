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

import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.MARKLOGIC;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.SAXON;
import static org.intellij.xquery.runner.ui.datasources.details.ConfigurationFilePanel.CONFIG_ENABLED;
import static org.intellij.xquery.runner.ui.datasources.details.ConfigurationFilePanel.CONFIG_FILE;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 24/10/13
 * Time: 13:42
 */
public class ConfigurationFilePanelGuiTest extends BaseGuiTest {
    private static final XQueryDataSourceType CONFIG_FILE_SUPPORTED_TYPE = SAXON;
    private static final XQueryDataSourceType CONFIG_FILE_UNSUPPORTED_TYPE = MARKLOGIC;
    private static final boolean ENABLED = true;
    private static final boolean DISABLED = false;
    private static final String FILE_NAME = "/my/file";
    private ConfigurationFilePanel panel;
    private DataSourceConfigurationAggregatingPanel aggregatingPanel;
    private ConfigurationChangeListener listener;
    private XQueryDataSourceConfiguration cfg;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new ConfigurationFilePanel();
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
    public void shouldHidePanelWhenNeeded() throws Exception {
        cfg.TYPE = CONFIG_FILE_UNSUPPORTED_TYPE;
        cfg.CONFIG_ENABLED = DISABLED;

        panel.init(cfg, aggregatingPanel, listener);

        assertThat(panel.getMainPanel().isVisible(), is(false));
    }

    @Test
    public void shouldShowPanelWhenNeeded() throws Exception {
        cfg.TYPE = CONFIG_FILE_SUPPORTED_TYPE;
        cfg.CONFIG_ENABLED = DISABLED;

        panel.init(cfg, aggregatingPanel, listener);

        assertThat(panel.getMainPanel().isVisible(), is(true));
    }

    @Test
    public void shouldInitializeWithCorrectFileName() throws Exception {
        cfg.TYPE = CONFIG_FILE_UNSUPPORTED_TYPE;
        cfg.CONFIG_ENABLED = ENABLED;
        cfg.CONFIG_FILE = FILE_NAME;

        panel.init(cfg, aggregatingPanel, listener);

        assertThat(panel.getConfigFile(), is(FILE_NAME));
    }

    @Test
    public void shouldDisableConfigFileField() throws Exception {
        initVisiblePanelWithConfigFile(DISABLED);

        window.checkBox(CONFIG_ENABLED).requireNotSelected();
        window.textBox(CONFIG_FILE).requireDisabled();
    }

    @Test
    public void shouldEnableConfigFileField() throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);

        window.checkBox(CONFIG_ENABLED).requireSelected();
        window.textBox(CONFIG_FILE).requireEnabled();
    }

    @Test
    public void shouldChangeValueOfConfigurationEnabledToTrue() throws Exception {
        initVisiblePanelWithConfigFile(DISABLED);

        window.checkBox(CONFIG_ENABLED).check();

        window.textBox(CONFIG_FILE).requireEnabled();
        assertThat(panel.isConfigurationEnabled(), is(true));
    }

    @Test
    public void shouldChangeValueOfConfigurationEnabledToFalse() throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);

        window.checkBox(CONFIG_ENABLED).uncheck();

        window.textBox(CONFIG_FILE).requireDisabled();
        assertThat(panel.isConfigurationEnabled(), is(false));
    }

    @Test
    public void shouldChangeValueOfConfigFileWhenTextEntered() throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);

        window.textBox(CONFIG_FILE).enterText(FILE_NAME);

        assertThat(panel.getConfigFile(), is(FILE_NAME));
    }

    @Test
    public void shouldUpdatePanelRelatedFields() throws Exception {
        cfg.TYPE = CONFIG_FILE_SUPPORTED_TYPE;
        cfg.CONFIG_ENABLED = ENABLED;
        cfg.CONFIG_FILE = FILE_NAME;
        panel.init(cfg, aggregatingPanel, listener);

        panel.updateConfigurationWithChanges(cfg);

        assertThat(cfg.CONFIG_ENABLED, is(ENABLED));
        assertThat(cfg.CONFIG_FILE, is(FILE_NAME));
    }

    @Test
    public void shouldInvokeChangeListenerAfterChangeOfCheckBox() throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);

        window.checkBox(CONFIG_ENABLED).uncheck();

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    @Test
    public void shouldInvokeChangeListenerAfterChangeOfValueOfConfigFile()
            throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);

        window.textBox(CONFIG_FILE).enterText(FILE_NAME);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    private void verifyChangeListenerInvokedForCurrentConfigurationState() {
        verify(aggregatingPanel, atLeast(1)).getCurrentConfigurationState();
        verify(listener, atLeast(1)).changeApplied(cfg);
    }

    private void initVisiblePanelWithConfigFile(boolean enabled) {
        cfg.TYPE = CONFIG_FILE_SUPPORTED_TYPE;
        cfg.CONFIG_ENABLED = enabled;
        panel.init(cfg, aggregatingPanel, listener);
    }
}
