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

import com.intellij.execution.configurations.RuntimeConfigurationException;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.fest.assertions.Fail.fail;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.state.run.VariablesValidator.INCORRECT_VARIABLE_MESSAGE;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 18/11/13
 * Time: 13:21
 */
public class VariablesValidatorTest {
    private XQueryRunVariable variable1 = new XQueryRunVariable("name:name", null, "xs:string", "value", true);
    private XQueryRunVariables variables = new XQueryRunVariables();
    private VariablesValidator validator = new VariablesValidator();

    @Test
    public void shouldThrowAnExceptionIfVariableWithoutName() {
        variables.setVariables(asList(new XQueryRunVariable()));

        try {
            validator.validate(variables);
            fail();
        } catch (RuntimeConfigurationException e) {
            assertThat(e.getMessage(), is(INCORRECT_VARIABLE_MESSAGE));
        }
    }

    @Test
    public void shouldThrowAnExceptionIfVariableWithoutType() {
        variables.setVariables(asList(new XQueryRunVariable("name", null, null, null, false)));

        try {
            validator.validate(variables);
            fail();
        } catch (RuntimeConfigurationException e) {
            assertThat(e.getMessage(), is(INCORRECT_VARIABLE_MESSAGE));
        }
    }

    @Test
    public void shouldNotThrowAnExceptionIfVariableIsOk() throws RuntimeConfigurationException {
        variables.setVariables(asList(variable1));

        validator.validate(variables);
    }
}
