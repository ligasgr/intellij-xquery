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
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;

import javax.swing.JPanel;

/**
 * User: ligasgr
 * Date: 02/10/13
 * Time: 22:11
 */
public class DataSourceConfigurationAggregatingPanel {
    private JPanel mainPanel;
    private NameAndDefaultButtonPanel nameAndDefaultButtonPanel;
    private ConfigurationFilePanel configurationFilePanel;
    private ConnectionParametersPanel connectionParametersPanel;
    private UserDefinedLibraryPanel userDefinedLibraryPanel;
    private XQueryDataSourceType dataSourceType;

    public DataSourceConfigurationAggregatingPanel(final XQueryDataSourceConfiguration dataSourceConfiguration,
                                                   ConfigurationChangeListener configurationChangeListener) {
        dataSourceType = dataSourceConfiguration.TYPE;
        initNameAndDefaultButtonPanel(dataSourceConfiguration, configurationChangeListener);
        initConfigurationFilePanel(dataSourceConfiguration, configurationChangeListener);
        initConnectionParametersPanel(dataSourceConfiguration, configurationChangeListener);
        initUserDefinedLibraryPanel(dataSourceConfiguration, configurationChangeListener);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public XQueryDataSourceConfiguration getCurrentConfigurationState() {
        XQueryDataSourceConfiguration currentConfiguration = new XQueryDataSourceConfiguration();
        currentConfiguration.TYPE = dataSourceType;
        nameAndDefaultButtonPanel.updateConfigurationWithChanges(currentConfiguration);
        configurationFilePanel.updateConfigurationWithChanges(currentConfiguration);
        connectionParametersPanel.updateConfigurationWithChanges(currentConfiguration);
        userDefinedLibraryPanel.updateConfigurationWithChanges(currentConfiguration);
        return currentConfiguration;
    }

    private void initNameAndDefaultButtonPanel(XQueryDataSourceConfiguration cfg,
                                               ConfigurationChangeListener changeListener) {
        nameAndDefaultButtonPanel.init(cfg, this, changeListener);
    }

    private void initConfigurationFilePanel(XQueryDataSourceConfiguration cfg,
                                            ConfigurationChangeListener changeListener) {
        configurationFilePanel.init(cfg, this, changeListener);
    }

    private void initConnectionParametersPanel(XQueryDataSourceConfiguration cfg,
                                               ConfigurationChangeListener changeListener) {
        connectionParametersPanel.init(cfg, this, changeListener);
    }

    private void initUserDefinedLibraryPanel(XQueryDataSourceConfiguration cfg,
                                             ConfigurationChangeListener changeListener) {
        userDefinedLibraryPanel.init(cfg, this, changeListener);
    }
}
