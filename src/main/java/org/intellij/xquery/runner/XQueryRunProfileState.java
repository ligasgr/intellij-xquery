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

package org.intellij.xquery.runner;

import com.intellij.execution.CantRunException;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.JavaCommandLineState;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.util.JavaParametersUtil;
import org.intellij.xquery.runner.state.run.DataSourceAccessor;
import org.intellij.xquery.runner.state.run.VariablesAccessor;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.state.run.XQueryRunConfigurationSerializer;
import org.intellij.xquery.runner.state.run.XmlConfigurationAccessor;

import java.io.File;
import java.io.FileWriter;

/**
 * User: ligasgr
 * Date: 04/08/13
 * Time: 22:40
 */
public class XQueryRunProfileState extends JavaCommandLineState {

    private XQueryRunConfiguration configuration;

    public XQueryRunProfileState(ExecutionEnvironment environment, XQueryRunConfiguration runConfiguration) {
        super(environment);
        configuration = runConfiguration;
    }

    @Override
    protected JavaParameters createJavaParameters() throws ExecutionException {
        final JavaParameters parameters = prepareRunnerParameters();
        configureJreRelatedParameters(parameters);
        return parameters;
    }

    private void configureJreRelatedParameters(JavaParameters parameters) throws CantRunException {
        final RunConfigurationModule module = configuration.getConfigurationModule();
        final String jreHome = configuration.isAlternativeJrePathEnabled() ? configuration.getAlternativeJrePath() :
                null;
        JavaParametersUtil.configureModule(module, parameters, JavaParameters.JDK_AND_CLASSES, jreHome);
        JavaParametersUtil.configureConfiguration(parameters, configuration);
    }

    private JavaParameters prepareRunnerParameters() throws CantRunException {
        final JavaParameters parameters = new JavaParameters();
        parameters.setMainClass(configuration.getRunClass());
        parameters.getClassPath().addFirst(new XQueryRunnerClasspathEntryGenerator()
                .generateRunnerClasspathEntries(configuration));
        parameters.getProgramParametersList().prepend(getSerializedConfig(configuration).getAbsolutePath());
        return parameters;
    }

    private File getSerializedConfig(XQueryRunConfiguration configuration) {
        try {
            File serializedConfig = File.createTempFile("xquery-run", ".xml");
            XmlConfigurationAccessor xmlConfigurationAccessor = new XmlConfigurationAccessor();
            VariablesAccessor variablesAccessor = new VariablesAccessor();
            DataSourceAccessor dataSourceAccessor = new DataSourceAccessor();
            new XQueryRunConfigurationSerializer(configuration, xmlConfigurationAccessor, variablesAccessor,
                    dataSourceAccessor).serialize(new FileWriter(serializedConfig));
            return serializedConfig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}