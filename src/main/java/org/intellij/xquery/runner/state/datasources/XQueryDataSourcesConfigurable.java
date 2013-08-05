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

package org.intellij.xquery.runner.state.datasources;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.intellij.xquery.runner.ui.datasources.DataSourcesSettingsForm;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * User: ligasgr
 * Date: 02/10/13
 * Time: 14:47
 */
public class XQueryDataSourcesConfigurable implements Configurable {
    private DataSourcesSettingsForm settingsForm;

    @Nls
    @Override
    public String getDisplayName() {
        return "XQuery Data Sources";
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
            settingsForm = new DataSourcesSettingsForm(XQueryDataSourcesSettings.getInstance()
                    .getDataSourceConfigurations());
        }
        return settingsForm.getFormComponent();
    }

    @Override
    public boolean isModified() {
        if (settingsForm != null) {
            return settingsForm.isModified(XQueryDataSourcesSettings.getInstance().getDataSourceConfigurations());
        }
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
        if (settingsForm != null) {
            XQueryDataSourcesSettings.getInstance()
                    .setDataSourceConfigurations(settingsForm.getCurrentConfigurations());
        }
    }

    @Override
    public void reset() {
        if (settingsForm != null) {
            settingsForm.refreshCurrentConfiguration(XQueryDataSourcesSettings.getInstance()
                    .getDataSourceConfigurations());
        }
    }

    @Override
    public void disposeUIResources() {
        settingsForm = null;
    }
}
