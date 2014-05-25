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

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.intellij.xquery.settings.XQueryConfigurable.CONFIGURABLE_NAME;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class XQueryConfigurableTest {
    private XQuerySettings settings;
    private XQuerySettingsForm settingsForm;
    private XQuerySettingsFormFactory settingsFormFactory;
    private TestXQueryConfigurable configurable;

    @Before
    public void setUp() throws Exception {
        settings = mock(XQuerySettings.class);
        settingsForm = mock(XQuerySettingsForm.class);
        settingsFormFactory = mock(XQuerySettingsFormFactory.class);
        given(settingsFormFactory.getSettingsForm()).willReturn(
                settingsForm);
        configurable = new TestXQueryConfigurable(mock(Project.class));
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
        given(settingsForm.isModified()).willReturn(true);
        configurable.createComponent();

        boolean result = configurable.isModified();

        assertThat(result, is(true));
    }

    @Test
    public void shouldDoNothingForApplyWhenFormNotInitialized() throws ConfigurationException {
        configurable.apply();

        verifyZeroInteractions(settingsForm, settings);
    }

    @Test
    public void shouldDoNothingForResetWhenFormNotInitialized() throws ConfigurationException {
        configurable.reset();

        verifyZeroInteractions(settingsForm, settings);
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

        verify(settingsFormFactory, times(2)).getSettingsForm();
    }

    @Test
    public void shouldDelegateApplyToToLoadStateOfSettings() throws ConfigurationException {
        configurable.createComponent();
        XQuerySettings differentSettings = mock(XQuerySettings.class);
        given(settingsForm.getSettings()).willReturn(differentSettings);

        configurable.apply();

        verify(settings).loadState(differentSettings);
    }

    @Test
    public void shouldPopulateSettingsFormWithSettingsAfterResetInvoked() throws ConfigurationException {
        configurable.createComponent();

        configurable.reset();

        verify(settingsForm).setSettings(settings);
    }

    private class TestXQueryConfigurable extends XQueryConfigurable {
        public TestXQueryConfigurable(Project project) {
            super(project);
        }

        @Override
        protected XQuerySettings getSettings() {
            return settings;
        }

        @Override
        protected XQuerySettingsFormFactory getSettingsFormFactory() {
            return settingsFormFactory;
        }
    }
}
