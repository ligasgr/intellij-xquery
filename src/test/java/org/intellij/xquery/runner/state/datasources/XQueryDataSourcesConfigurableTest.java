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

package org.intellij.xquery.runner.state.datasources;

import com.intellij.openapi.options.ConfigurationException;
import org.intellij.xquery.runner.ui.datasources.DataSourcesSettingsForm;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.intellij.xquery.runner.state.datasources.XQueryDataSourcesConfigurable.CONFIGURABLE_NAME;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * User: ligasgr
 * Date: 12/11/13
 * Time: 21:19
 */
public class XQueryDataSourcesConfigurableTest {

    private static final List<XQueryDataSourceConfiguration> CONFIGURATIONS = unmodifiableList(Collections.<XQueryDataSourceConfiguration>emptyList());
    private XQueryDataSourcesSettings dataSourcesSettings;
    private DataSourcesSettingsForm settingsForm;
    private DataSourcesSettingsFormFactory settingsFormFactory;
    private TestXQueryDataSourcesConfigurable configurable;

    @Before
    public void setUp() throws Exception {
        dataSourcesSettings = mock(XQueryDataSourcesSettings.class);
        settingsForm = mock(DataSourcesSettingsForm.class);
        settingsFormFactory = mock(DataSourcesSettingsFormFactory.class);
        given(settingsFormFactory.getSettingsForm()).willReturn(settingsForm);
        configurable = new TestXQueryDataSourcesConfigurable();
    }

    @Test
    public void shouldReturnKnownNameAsDisplayName() {
        assertThat(configurable.getDisplayName(), is(CONFIGURABLE_NAME));
    }

    @Test
    public void shouldReturnNullHelpTopic() {
        assertThat(configurable.getHelpTopic(), is(nullValue()));
    }

    @Test
    public void shouldReturnFormComponentFromSettingsForm() {
        configurable.createComponent();

        verify(settingsForm).getFormComponent();
    }

    @Test
    public void shouldReturnFalseForIsModifiedWhenComponentWasNotCreated() {
        assertThat(configurable.isModified(), is(false));
    }

    @Test
    public void shouldDelegateModificationVerificationToSettingsFormBasedOnCurrentlySavedConfigurations() {
        given(dataSourcesSettings.getDataSourceConfigurations()).willReturn(CONFIGURATIONS);
        given(settingsForm.isModified(CONFIGURATIONS)).willReturn(true);
        configurable.createComponent();

        boolean result = configurable.isModified();

        assertThat(result, is(true));
    }

    @Test
    public void shouldDoNothingForApplyWhenFormNotInitialized() throws ConfigurationException {
        configurable.apply();

        verifyZeroInteractions(settingsForm, dataSourcesSettings);
    }

    @Test
    public void shouldUpdateDataSourceSettingsWithConfigurationFromForm() throws ConfigurationException {
        given(settingsForm.getCurrentConfigurations()).willReturn(CONFIGURATIONS);
        configurable.createComponent();

        configurable.apply();

        verify(settingsForm).getCurrentConfigurations();
        verify(dataSourcesSettings).setDataSourceConfigurations(CONFIGURATIONS);
    }

    @Test
    public void shouldDoNothingForResetWhenFormNotInitialized() throws ConfigurationException {
        configurable.reset();

        verifyZeroInteractions(settingsForm, dataSourcesSettings);
    }

    @Test
    public void shouldPopulateConfigurationsInFormWithConfigurationsFromDataSourceSettings() throws ConfigurationException {
        given(dataSourcesSettings.getDataSourceConfigurations()).willReturn(CONFIGURATIONS);
        configurable.createComponent();

        configurable.reset();

        verify(dataSourcesSettings).getDataSourceConfigurations();
        verify(settingsForm).populateWithConfigurations(CONFIGURATIONS);
    }

    @Test
    public void shouldGetSettingsFormOnlyOnceWhenInvokedTwice() {
        configurable.createComponent();
        configurable.createComponent();

        verify(settingsFormFactory).getSettingsForm();
    }

    @Test
    public void shouldGetSettingsFormTwiceIfInvokedTwiceWithDisposalInTheMeanwhile() {
        configurable.createComponent();
        configurable.disposeUIResources();
        configurable.createComponent();

        verify(settingsFormFactory,times(2)).getSettingsForm();
    }

    private class TestXQueryDataSourcesConfigurable extends XQueryDataSourcesConfigurable {
        @Override
        protected XQueryDataSourcesSettings getDataSourceSettings() {
            return dataSourcesSettings;
        }

        @Override
        protected DataSourcesSettingsFormFactory getDataSourcesSettingsFormFactory() {
            return settingsFormFactory;
        }
    }
}
