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

package org.intellij.xquery.settings;

import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.junit.Test;

import javax.swing.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.settings.XQuerySettingsForm.SETTINGS_PANEL_NAME;
import static org.junit.Assert.assertThat;

public class XQuerySettingsFormTest extends BaseGuiTest {
    private static final String MY_PANEL = "myPanel";
    private static final String EXAMPLE_VALUE = "myValue";
    private XQuerySettingsForm settingsForm;
    private boolean updatePanelCalled;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        SettingsPanel panel = new SettingsPanel() {
            @Override
            public XQuerySettings updateSettings(XQuerySettings settings) {
                setSettingsToExampleValue(settings);
                return settings;
            }

            @Override
            public void updatePanel(XQuerySettings settings) {
                updatePanelCalled = true;
            }
        };
        panel.setName(MY_PANEL);
        settingsForm = new XQuerySettingsForm(panel);
        return new PanelTestingFrame(settingsForm.getFormComponent());
    }

    @Test
    public void shouldHaveVisiblePanels() {
        window.panel(SETTINGS_PANEL_NAME).requireVisible();
        window.panel(MY_PANEL).requireVisible();
    }

    @Test
    public void shouldReturnRootPanel() {
        assertThat((JPanel) settingsForm.getFormComponent(), is(window.panel(SETTINGS_PANEL_NAME).component()));
    }

    @Test
    public void shouldReturnSettingsModifiedBySubPanel() {
        XQuerySettings settings = settingsForm.getSettings();

        assertThat(settings.getDefaultMainModuleExtension(), is(EXAMPLE_VALUE));
        assertThat(settings.getDefaultLibraryModuleExtension(), is(EXAMPLE_VALUE));
    }

    @Test
    public void shouldCallUpdatePanelAfterSettingSettings() {
        settingsForm.setSettings(new XQuerySettings());

        assertThat(updatePanelCalled, is(true));
    }

    @Test
    public void shouldIndicateModificationIfSettingsDiffer() {
        settingsForm.setSettings(new XQuerySettings());

        assertThat(settingsForm.isModified(), is(true));
    }

    @Test
    public void shouldIndicateNoModificationIfSettingsDoNotDiffer() {
        XQuerySettings settings = new XQuerySettings();
        setSettingsToExampleValue(settings);
        settingsForm.setSettings(settings);

        assertThat(settingsForm.isModified(), is(false));
    }

    private void setSettingsToExampleValue(XQuerySettings settings) {
        settings.setDefaultMainModuleExtension(EXAMPLE_VALUE);
        settings.setDefaultLibraryModuleExtension(EXAMPLE_VALUE);
    }
}