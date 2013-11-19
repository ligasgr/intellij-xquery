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

package org.intellij.xquery.runner.state.run;

import com.intellij.diagnostic.logging.LogConfigurationPanel;
import com.intellij.execution.CommonJavaRunConfigurationParameters;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.ExternalizablePath;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.execution.configurations.*;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.components.PathMacroManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.intellij.xquery.runner.XQueryRunConfigurationType;
import org.intellij.xquery.runner.XQueryRunProfileState;
import org.intellij.xquery.runner.ui.run.RunConfigurationJavaTab;
import org.intellij.xquery.runner.ui.run.main.RunConfigurationMainTab;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: ligasgr
 * Date: 04/08/13
 * Time: 14:56
 */
public class XQueryRunConfiguration extends ModuleBasedConfiguration<XQueryRunConfigurationModule> implements
        CommonJavaRunConfigurationParameters, RunConfigurationWithSuppressedDefaultDebugAction {
    public static final String RUNNER_CLASS = "org.intellij.xquery.runner.rt.XQueryRunnerApp";
    private final VariablesValidator variablesValidator;
    private ContextItemValidator contextItemValidator;
    private final DataSourceValidator dataSourceValidator;
    private final AlternativeJreValidator alternativeJreValidator;
    private final ModuleValidator moduleValidator;
    private String mainFileName;
    private String vmParameters;
    private String programParameters;
    private String workingDirectory;
    private boolean alternativeJrePathEnabled;
    private String alternativeJrePath;
    private Map<String, String> myEnvs = new LinkedHashMap<String, String>();
    private boolean passParentEnvs = true;
    private XQueryRunVariables variables;
    private boolean contextItemEnabled;
    private boolean contextItemFromEditorEnabled = true;
    private String contextItemText;
    private String contextItemFile;
    private String dataSourceName;
    private String contextItemType;
    private XmlConfigurationAccessor xmlConfigurationAccessor;
    private VariablesAccessor variablesAccessor;

    public XQueryRunConfiguration(String name, XQueryRunConfigurationModule configurationModule,
                                  ConfigurationFactory factory) {
        this(name, configurationModule, factory, new VariablesValidator(), new ContextItemValidator(),
                new DataSourceValidator(), new AlternativeJreValidator(), new ModuleValidator(),
                new XmlConfigurationAccessor(), new VariablesAccessor());
        setWorkingDirectory(getProject().getBasePath());
    }

    public XQueryRunConfiguration(String name, XQueryRunConfigurationModule configurationModule,
                                  ConfigurationFactory factory, VariablesValidator variablesValidator,
                                  ContextItemValidator contextItemValidator, DataSourceValidator dataSourceValidator,
                                  AlternativeJreValidator alternativeJreValidator, ModuleValidator moduleValidator,
                                  XmlConfigurationAccessor xmlConfigurationAccessor,
                                  VariablesAccessor variablesAccessor) {
        super(name, configurationModule, factory);
        this.variablesValidator = variablesValidator;
        this.contextItemValidator = contextItemValidator;
        this.dataSourceValidator = dataSourceValidator;
        this.alternativeJreValidator = alternativeJreValidator;
        this.moduleValidator = moduleValidator;
        this.xmlConfigurationAccessor = xmlConfigurationAccessor;
        this.variablesAccessor = variablesAccessor;
        setWorkingDirectory(getProject().getBasePath());
    }

    @Override
    public Collection<Module> getValidModules() {
        Module[] modules = ModuleManager.getInstance(getProject()).getModules();
        return Arrays.asList(modules);
    }

    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        SettingsEditorGroup<XQueryRunConfiguration> group = new SettingsEditorGroup<XQueryRunConfiguration>();
        group.addEditor("Configuration", new RunConfigurationMainTab(getProject()));
        group.addEditor("Java Configuration", new RunConfigurationJavaTab(getProject()));
        group.addEditor("Logs", new LogConfigurationPanel<XQueryRunConfiguration>());
        return group;
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws
            ExecutionException {
        XQueryRunProfileState state = new XQueryRunProfileState(env, (XQueryRunConfiguration) env
                .getRunnerAndConfigurationSettings().getConfiguration());
        XQueryRunConfigurationModule module = getConfigurationModule();
        state.setConsoleBuilder(TextConsoleBuilderFactory.getInstance().createBuilder(getProject(),
                module.getSearchScope()));
        return state;
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        alternativeJreValidator.validate(this);
        moduleValidator.validate(this);
        variablesValidator.validate(variables);
        contextItemValidator.validate(contextItemEnabled, contextItemType, contextItemFromEditorEnabled,
                contextItemText, contextItemType);
        dataSourceValidator.validate(dataSourceName);
    }

    public void readExternal(final Element element) throws InvalidDataException {
        PathMacroManager.getInstance(getProject()).expandPaths(element);
        super.readExternal(element);
        xmlConfigurationAccessor.readConfiguration(element, this);
        readModule(element);
        EnvironmentVariablesComponent.readExternal(element, getEnvs());
        variablesAccessor.readVariables(element, this);
    }

    public void writeExternal(final Element element) throws WriteExternalException {
        super.writeExternal(element);
        xmlConfigurationAccessor.writeConfiguration(this, element);
        writeModule(element);
        EnvironmentVariablesComponent.writeExternal(element, getEnvs());
        PathMacroManager.getInstance(getProject()).collapsePathsRecursively(element);
        variablesAccessor.writeVariables(this, element);
    }

    @Override
    protected ModuleBasedConfiguration createInstance() {
        return new XQueryRunConfiguration(getName(), new XQueryRunConfigurationModule(getProject()),
                XQueryRunConfigurationType.getInstance().getConfigurationFactories()[0]);
    }

    public void setVariables(XQueryRunVariables variables) {
        this.variables = variables;
    }

    public XQueryRunVariables getVariables() {
        return variables;
    }

    public String getMainFileName() {
        return mainFileName;
    }

    public void setMainFileName(String mainFileName) {
        this.mainFileName = mainFileName;
    }

    public boolean isContextItemEnabled() {
        return contextItemEnabled;
    }

    public void setContextItemEnabled(boolean contextItemEnabled) {
        this.contextItemEnabled = contextItemEnabled;
    }

    public boolean isContextItemFromEditorEnabled() {
        return contextItemFromEditorEnabled;
    }

    public void setContextItemFromEditorEnabled(boolean contextItemFromEditorEnabled) {
        this.contextItemFromEditorEnabled = contextItemFromEditorEnabled;
    }

    public String getContextItemText() {
        return contextItemText;
    }

    public void setContextItemText(String contextItemText) {
        this.contextItemText = contextItemText;
    }

    public String getContextItemFile() {
        return contextItemFile;
    }

    public void setContextItemFile(String contextItemFile) {
        this.contextItemFile = contextItemFile;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public String getContextItemType() {
        return contextItemType;
    }

    public void setContextItemType(String contextItemType) {
        this.contextItemType = contextItemType;
    }

    public String getRawWorkingDirectory() {
        return workingDirectory;
    }

    public void setRawWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }


    @Override
    public void setVMParameters(String vmParameters) {
        this.vmParameters = vmParameters;
    }

    @Override
    public String getVMParameters() {
        return vmParameters;
    }

    @Override
    public boolean isAlternativeJrePathEnabled() {
        return alternativeJrePathEnabled;
    }

    @Override
    public void setAlternativeJrePathEnabled(boolean enabled) {
        alternativeJrePathEnabled = enabled;
    }

    @Override
    public String getAlternativeJrePath() {
        return alternativeJrePath;
    }

    @Override
    public void setAlternativeJrePath(String path) {
        alternativeJrePath = path;
    }

    @Nullable
    @Override
    public String getRunClass() {
        return RUNNER_CLASS;
    }

    @Nullable
    @Override
    public String getPackage() {
        return null;
    }

    @Override
    public void setProgramParameters(@Nullable String value) {
        programParameters = value;
    }

    @Nullable
    @Override
    public String getProgramParameters() {
        return programParameters;
    }

    @Override
    public void setWorkingDirectory(@Nullable String value) {
        workingDirectory = ExternalizablePath.urlValue(value);
    }

    @Nullable
    @Override
    public String getWorkingDirectory() {
        return ExternalizablePath.localPathValue(workingDirectory);
    }

    public void setPassParentEnvs(boolean passParentEnvs) {
        this.passParentEnvs = passParentEnvs;
    }

    @NotNull
    public Map<String, String> getEnvs() {
        return myEnvs;
    }

    public void setEnvs(@NotNull final Map<String, String> envs) {
        myEnvs.clear();
        myEnvs.putAll(envs);
    }

    public boolean isPassParentEnvs() {
        return passParentEnvs;
    }
}
