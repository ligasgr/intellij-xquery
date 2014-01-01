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
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.junit.Test;

import javax.swing.JPanel;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 09/11/13
 * Time: 02:28
 */
public class DataSourcesSettingsFormGuiTest extends BaseGuiTest {
    private DataSourceDetailsPanel dataSourceDetailsPanel;
    private TestDataSourceListPanel dataSourceListPanel;
    private TestDataSourcesSettingsForm form;
    private XQueryDataSourceConfiguration configuration = new XQueryDataSourceConfiguration("name", XQueryDataSourceType.SAXON);

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        dataSourceDetailsPanel = new DataSourceDetailsPanel();
        dataSourceListPanel = new TestDataSourceListPanel(dataSourceDetailsPanel);
        List<XQueryDataSourceConfiguration> dataSourceConfigurations = asList(configuration);
        form = new TestDataSourcesSettingsForm(dataSourceConfigurations);
        return new PanelTestingFrame((JPanel) form.getFormComponent());
    }

    @Test
    public void shouldSetDataSourceListPanelAsFirstComponentInSplitter() {
        assertThat(form.getSplitter().getFirstComponent().equals(dataSourceListPanel), is(true));
    }

    @Test
    public void shouldSetDataSourceDetailsPanelAsSecondComponentInSplitter() {
        assertThat(form.getSplitter().getSecondComponent().equals(dataSourceDetailsPanel), is(true));
    }

    @Test
    public void shouldPopulateDataSourceListPanelWithConfigurations() {
        assertThat(dataSourceListPanel.populateWithConfigurationsCalled, is(true));
    }

    @Test
    public void shouldDelegatePopulationToDataSourceListPanel() {
        form.populateWithConfigurations(asList(configuration));

        assertThat(dataSourceListPanel.populateWithConfigurationsCalled, is(true));
    }

    @Test
    public void shouldDelegateGettingCurrentConfigurationsToDataSourceListPanel() {
        form.getCurrentConfigurations();

        assertThat(dataSourceListPanel.getCurrentConfigurationsCalled, is(true));
    }

    @Test
    public void shouldDelegateGettingSelectedDataSourceToDataSourceListPanel() {
        form.getSelectedDataSource();

        assertThat(dataSourceListPanel.getSelectedDataSourceCalled, is(true));
    }

    @Test
    public void shouldVerifyIfModifiedBasedOnConfigurationsListFromDataSourceListPanel() {
        form.isModified(Collections.<XQueryDataSourceConfiguration>emptyList());

        assertThat(dataSourceListPanel.getCurrentConfigurationsCalled, is(true));
    }

    private class TestDataSourcesSettingsForm extends DataSourcesSettingsForm {
        public TestDataSourcesSettingsForm(List<XQueryDataSourceConfiguration> dataSourceConfigurations) {
            super(dataSourceConfigurations, dataSourceListPanel, dataSourceDetailsPanel);
        }

        @Override
        public Splitter getSplitter() {
            return super.getSplitter();
        }
    }

    private class TestDataSourceListPanel extends DataSourceListPanel {
        public boolean getSelectedDataSourceCalled;
        public boolean getCurrentConfigurationsCalled;
        public boolean populateWithConfigurationsCalled;

        public TestDataSourceListPanel(DataSourceDetailsPanel dataSourceDetailsPanel) {
            super(dataSourceDetailsPanel);
        }

        @Override
        public XQueryDataSourceConfiguration getSelectedDataSource() {
            getSelectedDataSourceCalled = true;
            return super.getSelectedDataSource();
        }

        @Override
        public List<XQueryDataSourceConfiguration> getCurrentConfigurations() {
            getCurrentConfigurationsCalled = true;
            return super.getCurrentConfigurations();
        }

        @Override
        public void populateWithConfigurations(List<XQueryDataSourceConfiguration> dataSourceConfigurations) {
            populateWithConfigurationsCalled = true;
            super.populateWithConfigurations(dataSourceConfigurations);
        }
    }
}
