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

import net.java.openjdk.cacio.ctc.junit.CacioFESTRunner;
import net.java.openjdk.cacio.ctc.junit.CacioTestRunner;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.ui.BaseGuiTest;
import org.intellij.xquery.runner.ui.PanelTestingFrame;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 24/10/13
 * Time: 13:42
 */
@RunWith(CacioFESTRunner.class)
public class ConfigurationFilePanelTest extends BaseGuiTest {
    private static final XQueryDataSourceType CONFIG_FILE_SUPPORTED_TYPE = XQueryDataSourceType.SAXON;
    private static final XQueryDataSourceType CONFIG_FILE_UNSUPPORTED_TYPE = XQueryDataSourceType.MARKLOGIC;
    private static final boolean ENABLED = true;
    private static final boolean DISABLED = false;
    private ConfigurationFilePanel configurationFilePanel;

    protected PanelTestingFrame getPanelTestingFrame() {
        configurationFilePanel = new ConfigurationFilePanel();
        return new PanelTestingFrame(configurationFilePanel.getMainPanel());
    }

    @Test
    public void shouldShowPanelWhenNeeded() throws Exception {
        configurationFilePanel.init(CONFIG_FILE_SUPPORTED_TYPE, false, null);

        assertThat(configurationFilePanel.getMainPanel().isVisible(), is(true));
    }

    @Test
    public void shouldHidePanelWhenNeeded() throws Exception {
        configurationFilePanel.init(CONFIG_FILE_UNSUPPORTED_TYPE, false, null);

        assertThat(configurationFilePanel.getMainPanel().isVisible(), is(false));
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
        window.checkBox("configurationEnabled").requireNotSelected();
        window.robot.settings().delayBetweenEvents(200);

        window.checkBox("configurationEnabled").check().requireSelected();

        window.textBox("configFile").requireEnabled();
        assertThat(configurationFilePanel.isConfigurationEnabled(), is(true));
    }

    @Test
    public void shouldChangeValueOfConfigurationEnabledToFalse() throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);
        window.checkBox("configurationEnabled").requireSelected();
        window.robot.settings().delayBetweenEvents(200);

        window.checkBox("configurationEnabled").uncheck().requireNotSelected();

        window.textBox("configFile").requireDisabled();
        assertThat(configurationFilePanel.isConfigurationEnabled(), is(false));
    }

    @Test
    public void shouldChangeValueOfConfigFileWhenTextEntered() throws Exception {
        initVisiblePanelWithConfigFile(ENABLED);

        window.textBox("configFile").enterText("/my/file");

        assertThat(configurationFilePanel.getConfigFile(), is("/my/file"));
    }

    private void initVisiblePanelWithConfigFile(boolean enabled) {
        configurationFilePanel.init(CONFIG_FILE_SUPPORTED_TYPE, enabled, null);
    }
}
