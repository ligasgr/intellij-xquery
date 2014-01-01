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
import org.junit.Test;

import static org.fest.assertions.Fail.fail;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.state.run.DataSourceValidator.DATA_SOURCE_MISSING_MESSAGE;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 18/11/13
 * Time: 13:39
 */
public class DataSourceValidatorTest {

    private DataSourceValidator validator = new DataSourceValidator();

    @Test
    public void shouldThrowAnExceptionIfDataSourceNameNotSet() {
        try {
            validator.validate(null);
            fail();
        } catch (RuntimeConfigurationException e) {
            assertThat(e.getMessage(), is(DATA_SOURCE_MISSING_MESSAGE));
        }
    }

    @Test
    public void shouldDoNothingIfDataSourceNameSet() throws RuntimeConfigurationError {
        validator.validate("any");
    }
}
