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

import static org.intellij.xquery.settings.XQuerySettingsForm.SETTINGS_PANEL_NAME;

public class XQuerySettingsFormTest extends BaseGuiTest {
    private XQuerySettingsForm settingsForm;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        settingsForm = new XQuerySettingsForm();
        return new PanelTestingFrame(settingsForm.getFormComponent());
    }

    @Test
    public void shouldHaveVisibleMainPanel() {
        window.panel(SETTINGS_PANEL_NAME).requireVisible();
    }

}