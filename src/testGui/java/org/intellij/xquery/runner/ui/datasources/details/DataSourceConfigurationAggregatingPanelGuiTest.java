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

import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * User: ligasgr
 * Date: 01/11/13
 * Time: 19:49
 */
public class DataSourceConfigurationAggregatingPanelGuiTest extends BaseGuiTest {

    private DataSourceConfigurationAggregatingPanel panel;
    private ConfigurationChangeListener nameChangedListener;
    private XQueryDataSourceConfiguration configuration;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        nameChangedListener = mock(ConfigurationChangeListener.class);
        configuration = new XQueryDataSourceConfiguration("config", XQueryDataSourceType.SAXON);
        panel = new DataSourceConfigurationAggregatingPanel(configuration, nameChangedListener);
        return new PanelTestingFrame(panel.getPanel());
    }

    @Test
    public void shouldShowMainPanel() {
        assertThat(panel.getPanel().isVisible(), is(true));
    }

    @Test
    public void shouldReturnTheSameValues() {
        XQueryDataSourceConfiguration currentConfig = panel.getCurrentConfigurationState();

        assertThat(currentConfig.equals(configuration), is(true));
    }
}
