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
import org.intellij.xquery.runner.ui.datasources.ConfigurationFilePanel;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.MARKLOGIC;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.SAXON;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 24/10/13
 * Time: 13:42
 */
public class ConfigurationFilePanelTest extends BaseGuiTest {
    private static final XQueryDataSourceType CONFIG_FILE_SUPPORTED_TYPE = SAXON;
    private static final XQueryDataSourceType CONFIG_FILE_UNSUPPORTED_TYPE = MARKLOGIC;
    private static final boolean ENABLED = true;
    private static final boolean DISABLED = false;
    private static final String FILE_NAME = "/my/file";
    private ConfigurationFilePanel panel;

    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new ConfigurationFilePanel();
        return new PanelTestingFrame(panel.getMainPanel());
    }

    @Test
    public void shouldHidePanelWhenNeeded() throws Exception {
        panel.init(CONFIG_FILE_UNSUPPORTED_TYPE, false, null);

        assertThat(panel.getMainPanel().isVisible(), is(false));
    }

    @Test
    public void shouldShowPanelWhenNeeded() throws Exception {
        panel.init(CONFIG_FILE_SUPPORTED_TYPE, false, null);

        assertThat(panel.getMainPanel().isVisible(), is(true));
    }

    @Test
    public void shouldInitializeWithCorrectFileName() throws Exception {
        panel.init(CONFIG_FILE_UNSUPPORTED_TYPE, true, FILE_NAME);

        assertThat(panel.getConfigFile(), is(FILE_NAME));
    }

    @Test
    public void shouldDisableConfigFileField() throws Exception {
        initVisiblePanelWithConfigFile(DISABLED);

        window.checkBox("configurationEnabled").requireNotSelected();
        window.textBox("configFile").requireDisabled();
    }

    @Test
    public void shouldEnableConfigFileField() throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);

        window.checkBox("configurationEnabled").requireSelected();
        window.textBox("configFile").requireEnabled();
    }

    @Test
    public void shouldChangeValueOfConfigurationEnabledToTrue() throws Exception {
        initVisiblePanelWithConfigFile(DISABLED);

        window.checkBox("configurationEnabled").check();

        window.textBox("configFile").requireEnabled();
        assertThat(panel.isConfigurationEnabled(), is(true));
    }

    @Test
    public void shouldChangeValueOfConfigurationEnabledToFalse() throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);

        window.checkBox("configurationEnabled").uncheck();

        window.textBox("configFile").requireDisabled();
        assertThat(panel.isConfigurationEnabled(), is(false));
    }

    @Test
    public void shouldChangeValueOfConfigFileWhenTextEntered() throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);

        window.textBox("configFile").enterText(FILE_NAME);

        assertThat(panel.getConfigFile(), is(FILE_NAME));
    }

    private void initVisiblePanelWithConfigFile(boolean enabled) {
        panel.init(CONFIG_FILE_SUPPORTED_TYPE, enabled, null);
    }
}
