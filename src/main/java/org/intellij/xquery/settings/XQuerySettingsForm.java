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

import javax.swing.*;

public class XQuerySettingsForm {
    public static final String SETTINGS_PANEL_NAME = "settingsPanel";
    private XQuerySettings settings;
    private JPanel rootPanel;

    public JComponent getFormComponent() {
        rootPanel = new JPanel();
        rootPanel.setName(SETTINGS_PANEL_NAME);
        return rootPanel;
    }

    public boolean isModified() {
        return false;
    }

    public XQuerySettings getSettings() {
        return settings;
    }

    public void setSettings(XQuerySettings settings) {
        this.settings = settings;
    }
}
