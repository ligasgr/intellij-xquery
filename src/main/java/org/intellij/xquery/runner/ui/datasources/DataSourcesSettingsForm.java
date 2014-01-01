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

package org.intellij.xquery.runner.ui.datasources;

import com.intellij.openapi.ui.Splitter;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import static java.awt.BorderLayout.CENTER;

/**
 * User: ligasgr
 * Date: 02/10/13
 * Time: 14:49
 */
public class DataSourcesSettingsForm {

    private JPanel formComponent;
    private DataSourceListPanel dataSourceListPanel;
    private DataSourceDetailsPanel dataSourceDetailsPanel;
    private Splitter splitter;

    public DataSourcesSettingsForm(List<XQueryDataSourceConfiguration> dataSourceConfigurations, DataSourceListPanel
            dataSourceListPanel, DataSourceDetailsPanel dataSourceDetailsPanel) {
        this.dataSourceListPanel = dataSourceListPanel;
        this.dataSourceDetailsPanel = dataSourceDetailsPanel;
        formComponent = new JPanel(new BorderLayout());
        splitter = prepareSplitter(this.dataSourceListPanel, this.dataSourceDetailsPanel);
        formComponent.add(splitter, CENTER);
        populateWithConfigurations(dataSourceConfigurations);
    }

    public void populateWithConfigurations(List<XQueryDataSourceConfiguration> dataSourceConfigurations) {
        dataSourceListPanel.populateWithConfigurations(dataSourceConfigurations);
    }

    public boolean isModified(List<XQueryDataSourceConfiguration> dataSourceConfigurations) {
        return !dataSourceListPanel.getCurrentConfigurations().equals(dataSourceConfigurations);
    }

    public List<XQueryDataSourceConfiguration> getCurrentConfigurations() {
        return dataSourceListPanel.getCurrentConfigurations();
    }

    public XQueryDataSourceConfiguration getSelectedDataSource() {
        return dataSourceListPanel.getSelectedDataSource();
    }

    public JComponent getFormComponent() {
        return formComponent;
    }

    private Splitter prepareSplitter(DataSourceListPanel dataSourceListPanel,
                                     JPanel dataSourcesConfigurationPanel) {
        Splitter splitter = new Splitter(false, 0.3f);
        splitter.setFirstComponent(dataSourceListPanel);
        splitter.setSecondComponent(dataSourcesConfigurationPanel);
        dataSourcesConfigurationPanel.setMinimumSize(new Dimension(300, 400));
        return splitter;
    }

    protected Splitter getSplitter() {
        return splitter;
    }
}
