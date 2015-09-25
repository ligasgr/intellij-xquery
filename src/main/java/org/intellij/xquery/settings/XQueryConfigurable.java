/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class XQueryConfigurable implements Configurable {

    public static final String CONFIGURABLE_NAME = "XQuery";
    private final XQuerySettingsFormFactory settingsFormFactory = getSettingsFormFactory();
    private final Project project;
    private XQuerySettingsForm settingsForm;

    public XQueryConfigurable(Project project) {
        this.project = project;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return CONFIGURABLE_NAME;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }


    @Nullable
    @Override
    public JComponent createComponent() {
        if (settingsForm == null) {
            settingsForm = settingsFormFactory.getSettingsForm();
        }
        return settingsForm.getFormComponent();
    }

    @Override
    public boolean isModified() {
        if (settingsForm != null) {
            return settingsForm.isModified();
        }
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
        if (settingsForm != null) {
            boolean restartAnnotations = false;
            if (isModified()) {
                restartAnnotations = true;
            }
            getSettings().loadState(settingsForm.getSettings());
            if (restartAnnotations) {
                UIUtils.restartDaemons();
            }
        }
    }

    @Override
    public void reset() {
        if (settingsForm != null) {
            settingsForm.setSettings(getSettings());
        }
    }

    @Override
    public void disposeUIResources() {
        settingsForm = null;
    }

    protected XQuerySettings getSettings() {
        return XQuerySettings.getInstance(project);
    }

    protected XQuerySettingsFormFactory getSettingsFormFactory() {
        return new XQuerySettingsFormFactory();
    }
}
