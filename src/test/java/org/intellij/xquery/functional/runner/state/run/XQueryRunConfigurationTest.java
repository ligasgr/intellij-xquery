/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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

package org.intellij.xquery.functional.runner.state.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import org.intellij.xquery.functional.BaseFunctionalTestCase;
import org.intellij.xquery.runner.XQueryRunConfigurationFactory;
import org.intellij.xquery.runner.XQueryRunConfigurationType;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.state.run.XQueryRunConfigurationModule;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.intellij.xquery.runner.XQueryRunConfigurationType.XQUERY_MAIN_MODULE;
import static org.intellij.xquery.runner.state.run.XQueryRunConfiguration.RUNNER_CLASS;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 14/11/13
 * Time: 15:53
 */
public class XQueryRunConfigurationTest extends BaseFunctionalTestCase {
    private static final String VM_PARAMS = "params";
    private static final String PROGRAM_PARAMS = "program params";
    private static final String JRE_PATH = "jre path";
    private static final boolean JRE_ENABLED = true;
    private static final boolean PASS_PARENT_ENVS = true;

    private final Map<String, String> envs = new HashMap<String, String>();
    private XQueryRunConfiguration configuration;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        XQueryRunConfigurationModule module = new XQueryRunConfigurationModule(getProject());
        XQueryRunConfigurationType type = new XQueryRunConfigurationType();
        ConfigurationFactory factory = new XQueryRunConfigurationFactory(XQUERY_MAIN_MODULE, type);
        configuration = new XQueryRunConfiguration(XQUERY_MAIN_MODULE, module, factory);
    }

    public void testShouldCreateConfiguration() {
        assertThat(configuration, is(not(nullValue())));
    }

    public void testShouldSetWorkingDirectoryToProjectBasePath() {
        assertThat(configuration.getWorkingDirectory(), is(getProject().getBasePath()));
    }

    public void testShouldReturnCorrectRunnerClass() {
        assertThat(configuration.getRunClass(), is(RUNNER_CLASS));
    }

    public void testShouldReturnNullPackage() {
        assertThat(configuration.getPackage(), is(nullValue()));
    }

    public void testShouldAssignAndReturnJavaRelatedParameters() {
        configuration.setVMParameters(VM_PARAMS);
        configuration.setProgramParameters(PROGRAM_PARAMS);
        configuration.setAlternativeJrePathEnabled(JRE_ENABLED);
        configuration.setAlternativeJrePath(JRE_PATH);
        configuration.setEnvs(envs);
        configuration.setPassParentEnvs(PASS_PARENT_ENVS);

        assertThat(configuration.getVMParameters(), is(VM_PARAMS));
        assertThat(configuration.getProgramParameters(), is(PROGRAM_PARAMS));
        assertThat(configuration.isAlternativeJrePathEnabled(), is(JRE_ENABLED));
        assertThat(configuration.getAlternativeJrePath(), is(JRE_PATH));
        assertThat(configuration.getEnvs(), is(envs));
        assertThat(configuration.isPassParentEnvs(), is(PASS_PARENT_ENVS));
    }
}
