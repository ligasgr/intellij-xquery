/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.state.datasources;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.SAXON;
import static org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings.COMPONENT_NAME;
import static org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings
        .NO_DATA_SOURCE_FOUND_FOR_NAME_MESSAGE;
import static org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings.PRESENTABLE_NAME;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 13/11/13
 * Time: 14:17
 */
public class XQueryDataSourcesSettingsTest {
    private static final String CONFIG_NAME = "any";
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private XQueryDataSourcesSettings dataSourcesSettings;
    private XQueryDataSourceConfiguration dataSourceConfiguration;

    @Before
    public void setUp() throws Exception {
        dataSourcesSettings = new XQueryDataSourcesSettings();
        dataSourceConfiguration = new XQueryDataSourceConfiguration(CONFIG_NAME, SAXON);
        dataSourceConfiguration.DEFAULT = true;
    }

    @Test
    public void shouldReturnTheSameDataSourceConfigurationsThatWereSet() {
        List<XQueryDataSourceConfiguration> dataSourceConfigurations = emptyList();

        dataSourcesSettings.setDataSourceConfigurations(dataSourceConfigurations);

        assertThat(dataSourcesSettings.getDataSourceConfigurations(), is(sameInstance(dataSourceConfigurations)));
    }

    @Test
    public void shouldReturnTheInstanceAsState() {
        assertThat(dataSourcesSettings.getState() == dataSourcesSettings, is(true));
    }

    @Test
    public void shouldReturnPresentableName() {
        assertThat(dataSourcesSettings.getPresentableName(), is(PRESENTABLE_NAME));
    }

    @Test
    public void shouldReturnComponentName() {
        assertThat(dataSourcesSettings.getComponentName(), is(COMPONENT_NAME));
    }

    @Test
    public void shouldReturnTheSameListAsInNewState() {
        List<XQueryDataSourceConfiguration> dataSourceConfigurations = emptyList();
        XQueryDataSourcesSettings newState = new XQueryDataSourcesSettings();
        newState.setDataSourceConfigurations(dataSourceConfigurations);

        dataSourcesSettings.loadState(newState);

        assertThat(dataSourcesSettings.getDataSourceConfigurations(), is(sameInstance(dataSourceConfigurations)));
    }

    @Test
    public void shouldReturnNullWhenConfigListWasEmpty() {
        assertThat(dataSourcesSettings.getDataSourceConfigurationForName(CONFIG_NAME), is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenConfigNotFoundByNameWhenNotInList() {
        dataSourcesSettings.setDataSourceConfigurations(asList(dataSourceConfiguration));

        assertThat(dataSourcesSettings.getDataSourceConfigurationForName("another"), is(nullValue()));
    }

    @Test
    public void shouldReturnConfigurationWhenMatchesTheName() {
        dataSourcesSettings.setDataSourceConfigurations(asList(new XQueryDataSourceConfiguration(),
                new XQueryDataSourceConfiguration("some", null), dataSourceConfiguration));

        assertThat(dataSourcesSettings.getDataSourceConfigurationForName(CONFIG_NAME), is(dataSourceConfiguration));
    }

    @Test
    public void shouldReturnNullWhenListIsEmpty() {
        assertThat(dataSourcesSettings.getDefaultDataSourceConfiguration(), is(nullValue()));
    }

    @Test
    public void shouldReturnConfigurationWhenIsDefault() {
        dataSourcesSettings.setDataSourceConfigurations(asList(new XQueryDataSourceConfiguration(),
                new XQueryDataSourceConfiguration("some", null), dataSourceConfiguration));

        assertThat(dataSourcesSettings.getDefaultDataSourceConfiguration(), is(dataSourceConfiguration));
    }
}
