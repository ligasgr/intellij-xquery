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

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.openapi.vfs.LocalFileSystem;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.runner.XQueryRunConfigurationFactory;
import org.intellij.xquery.runner.XQueryRunConfigurationType;
import org.mockito.InOrder;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.intellij.xquery.runner.XQueryRunConfigurationType.XQUERY_MAIN_MODULE;
import static org.intellij.xquery.runner.state.run.XQueryRunConfiguration.RUNNER_CLASS;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

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
    private static final String MAIN_FILE_NAME = "file.xq";
    private static final boolean CONTEXT_ITEM_ENABLED = true;
    private static final boolean CONTEXT_ITEM_FROM_EDITOR_ENABLED = true;
    private static final String CONTEXT_ITEM_TEXT = "my text";
    private static final String CONTEXT_ITEM_FILE = "file.xml";
    private static final String CONTEXT_ITEM_TYPE = "xs:int";
    private static final String DATA_SOURCE_NAME = "saxon";

    private final Map<String, String> envs = new HashMap<String, String>();
    private TestXQueryRunConfiguration configuration;
    private XQueryRunVariables variables = new XQueryRunVariables(Collections.<XQueryRunVariable>emptyList());
    private VariablesValidator variablesValidator;
    private ContextItemValidator contextItemValidator;
    private DataSourceValidator dataSourceValidator;
    private AlternativeJreValidator alternativeJreValidator;
    private ModuleValidator moduleValidator;
    private XmlConfigurationAccessor xmlConfigurationAccessor;
    private VariablesAccessor variablesAccessor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        XQueryRunConfigurationModule module = new XQueryRunConfigurationModule(getProject());
        XQueryRunConfigurationType type = new XQueryRunConfigurationType();
        ConfigurationFactory factory = new XQueryRunConfigurationFactory(XQUERY_MAIN_MODULE, type);
        variablesValidator = mock(VariablesValidator.class);
        contextItemValidator = mock(ContextItemValidator.class);
        dataSourceValidator = mock(DataSourceValidator.class);
        alternativeJreValidator = mock(AlternativeJreValidator.class);
        moduleValidator = mock(ModuleValidator.class);
        xmlConfigurationAccessor = mock(XmlConfigurationAccessor.class);
        variablesAccessor = mock(VariablesAccessor.class);
        configuration = new TestXQueryRunConfiguration(XQUERY_MAIN_MODULE, module, factory,
                variablesValidator, contextItemValidator, dataSourceValidator, alternativeJreValidator, moduleValidator,
                xmlConfigurationAccessor, variablesAccessor);
        configuration.setVariables(variables);
        configuration.setDataSourceName(DATA_SOURCE_NAME);
    }

    public void testShouldCreateConfiguration() {
        assertThat(configuration, is(not(nullValue())));
    }

    public void testShouldSetWorkingDirectoryToProjectBasePath() {
        assertThat(configuration.getWorkingDirectory(), is(getProject().getBasePath().replace('/', File.separatorChar)));
        assertThat(configuration.getWorkingDirectory(), not(containsString(LocalFileSystem.PROTOCOL)));
        assertThat(configuration.getRawWorkingDirectory(), containsString(LocalFileSystem.PROTOCOL));
        assertThat(configuration.getRawWorkingDirectory(), not(containsString("\\")));
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

    public void testShouldAssignAndReturnXQueryRelatedParameters() {
        configuration.setVariables(variables);
        configuration.setMainFileName(MAIN_FILE_NAME);
        configuration.setContextItemEnabled(CONTEXT_ITEM_ENABLED);
        configuration.setContextItemFromEditorEnabled(CONTEXT_ITEM_FROM_EDITOR_ENABLED);
        configuration.setContextItemText(CONTEXT_ITEM_TEXT);
        configuration.setContextItemFile(CONTEXT_ITEM_FILE);
        configuration.setContextItemType(CONTEXT_ITEM_TYPE);
        configuration.setDataSourceName(DATA_SOURCE_NAME);

        assertThat(configuration.getVariables(), is(variables));
        assertThat(configuration.getMainFileName(), is(MAIN_FILE_NAME));
        assertThat(configuration.isContextItemEnabled(), is(CONTEXT_ITEM_ENABLED));
        assertThat(configuration.isContextItemFromEditorEnabled(), is(CONTEXT_ITEM_FROM_EDITOR_ENABLED));
        assertThat(configuration.getContextItemText(), is(CONTEXT_ITEM_TEXT));
        assertThat(configuration.getContextItemFile(), is(CONTEXT_ITEM_FILE));
        assertThat(configuration.getContextItemType(), is(CONTEXT_ITEM_TYPE));
        assertThat(configuration.getDataSourceName(), is(DATA_SOURCE_NAME));
    }

    public void testShouldVerifyConfigurationUsingValidators() throws RuntimeConfigurationException {
        configuration.checkConfiguration();

        InOrder inOrder = inOrder(alternativeJreValidator,
                moduleValidator,
                variablesValidator,
                contextItemValidator,
                dataSourceValidator);
        inOrder.verify(alternativeJreValidator).validate(configuration);
        inOrder.verify(moduleValidator).validate(configuration);
        inOrder.verify(variablesValidator).validate(variables);
        inOrder.verify(contextItemValidator).validate(anyBoolean(), anyString(), anyBoolean(), anyString(), anyString());
    }

    private class TestXQueryRunConfiguration extends XQueryRunConfiguration {
        public TestXQueryRunConfiguration(String name,
                                          XQueryRunConfigurationModule configurationModule,
                                          ConfigurationFactory factory,
                                          VariablesValidator variablesValidator,
                                          ContextItemValidator contextItemValidator,
                                          DataSourceValidator dataSourceValidator,
                                          AlternativeJreValidator alternativeJreValidator,
                                          ModuleValidator moduleValidator,
                                          XmlConfigurationAccessor xmlConfigurationAccessor,
                                          VariablesAccessor variablesAccessor) {
            super(name, configurationModule, factory, variablesValidator, contextItemValidator, dataSourceValidator,
                    alternativeJreValidator, moduleValidator, xmlConfigurationAccessor, variablesAccessor);
        }
    }
}
