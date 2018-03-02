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

package org.intellij.xquery.runner.state.run;

import com.intellij.execution.configurations.RuntimeConfigurationError;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.junit.Test;


import static org.fest.assertions.Fail.fail;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.state.run.DataSourceValidator.DATA_SOURCE_MISSING_MESSAGE;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;


/**
 * User: ligasgr
 * Date: 18/11/13
 * Time: 13:39
 */
public class DataSourceValidatorTest {

    private DataSourceValidator validator = new DataSourceValidator();

    @Test
    public void shouldThrowAnExceptionIfDataSourceNameNotSet() {
        XQueryDataSourcesSettings settings = mock (XQueryDataSourcesSettings.class);
        given (settings.getDataSourceConfigurationForName (null)).willThrow (new RuntimeException (XQueryDataSourcesSettings.NO_DATA_SOURCE_FOUND_FOR_NAME_MESSAGE + "null"));

        try {
            validator.validate (settings, null);
            fail();
        } catch (RuntimeConfigurationException e) {
            assertThat(e.getMessage(), is(DATA_SOURCE_MISSING_MESSAGE));
        }
    }

    @Test
    public void shouldDoNothingIfDataSourceNameSet() throws RuntimeConfigurationError {
        XQueryDataSourceConfiguration config = mock (XQueryDataSourceConfiguration.class);
        XQueryDataSourcesSettings settings = mock (XQueryDataSourcesSettings.class);
        given (settings.getDataSourceConfigurationForName (anyString())).willReturn (config);

        validator.validate (settings, "any");
    }
}
