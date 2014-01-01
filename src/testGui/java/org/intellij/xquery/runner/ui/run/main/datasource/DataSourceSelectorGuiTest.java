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

package org.intellij.xquery.runner.ui.run.main.datasource;

import org.fest.swing.core.GenericTypeMatcher;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.junit.Test;

import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.event.KeyEvent;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.run.main.datasource.DataSourceSelector.NO_DATA_SOURCE;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 10/11/13
 * Time: 21:26
 */
public class DataSourceSelectorGuiTest extends BaseGuiTest {

    private static final String CONFIGURATION_NAME = "myName";
    private static final String DEFAULT_NAME = "default";
    private TestDataSourceSelector selector;
    private JComboBox comboBox;
    private XQueryDataSourceConfiguration configuration = new XQueryDataSourceConfiguration(CONFIGURATION_NAME,
            XQueryDataSourceType.BASEX);
    private XQueryRunConfiguration runConfiguration;
    private XQueryDataSourcesSettings dataSourcesSettings;
    private XQueryDataSourceConfiguration defaultConfiguration;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        runConfiguration = mock(XQueryRunConfiguration.class);
        dataSourcesSettings = mock(XQueryDataSourcesSettings.class);
        defaultConfiguration = new XQueryDataSourceConfiguration(DEFAULT_NAME, XQueryDataSourceType.MARKLOGIC);
        comboBox = new JComboBox();
        selector = new TestDataSourceSelector(comboBox);
        return new PanelTestingFrame(comboBox);
    }

    @Test
    public void shouldShowComboBox() {
        window.comboBox().requireVisible();
    }

    @Test
    public void shouldDisplayNoSelected() {
        window.label(new GenericTypeMatcher<BasicComboBoxRenderer>(BasicComboBoxRenderer.class) {
            @Override
            protected boolean isMatching(BasicComboBoxRenderer component) {
                return true;
            }
        }).requireText(NO_DATA_SOURCE);
    }

    @Test
    public void shouldDisplayDataSourceNameAsElement() {
        selector.setDataSources(asList(configuration));

        assertThat(window.comboBox().contents()[0], is(CONFIGURATION_NAME));
    }

    @Test
    public void shouldSelectItem() {
        selector.setDataSources(asList(configuration));

        selector.setSelectedDataSource(configuration);

        window.comboBox().requireSelection(CONFIGURATION_NAME);
    }

    @Test
    public void shouldSetEmptyNameInConfigurationIfNoSelection() {
        selector.applyTo(runConfiguration);

        verify(runConfiguration).setDataSourceName(null);
    }

    @Test
    public void shouldSetSelectedNameInConfigurationIfSelectionExists() {
        selector.setDataSources(asList(configuration));
        selector.setSelectedDataSource(configuration);

        selector.applyTo(runConfiguration);

        verify(runConfiguration).setDataSourceName(CONFIGURATION_NAME);
    }

    @Test
    public void shouldPopulateComboBoxWithDataSourceFromConfiguration() {
        given(dataSourcesSettings.getDataSourceConfigurations()).willReturn(asList(configuration, defaultConfiguration));
        given(runConfiguration.getDataSourceName()).willReturn(CONFIGURATION_NAME);

        selector.reset(runConfiguration);

        window.comboBox().requireSelection(CONFIGURATION_NAME);
    }

    @Test
    public void shouldHaveEmptySelectionWhenDefaultDataSourceNotSetAndDataSourceFromConfigurationDoesNotExist() {
        given(dataSourcesSettings.getDataSourceConfigurations()).willReturn(asList(configuration, defaultConfiguration));
        given(dataSourcesSettings.getDefaultDataSourceConfiguration()).willReturn(null);
        given(runConfiguration.getDataSourceName()).willReturn("different");

        selector.reset(runConfiguration);

        window.comboBox().requireNoSelection();
    }

    @Test
    public void shouldHaveDefaultSelectedWhenDefaultDataSourceSetAndDataSourceFromConfigurationDoesNotExist() {
        given(dataSourcesSettings.getDataSourceConfigurations()).willReturn(asList(configuration, defaultConfiguration));
        given(dataSourcesSettings.getDefaultDataSourceConfiguration()).willReturn(defaultConfiguration);
        given(runConfiguration.getDataSourceName()).willReturn("different");

        selector.reset(runConfiguration);

        window.comboBox().requireSelection(DEFAULT_NAME);
    }

    @Test
    public void shouldSpeedSearchConfig() {
        selector.setDataSources(asList(configuration));

        window.comboBox().pressKey(KeyEvent.VK_M);

        window.comboBox().requireSelection(CONFIGURATION_NAME);
    }

    private class TestDataSourceSelector extends DataSourceSelector {
        public TestDataSourceSelector(JComboBox comboBox) {
            super(comboBox);
        }

        @Override
        protected XQueryDataSourcesSettings getDataSourcesSettings() {
            return dataSourcesSettings;
        }
    }
}
