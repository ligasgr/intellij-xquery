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

package org.intellij.xquery.runner;

import com.intellij.execution.CantRunException;
import com.intellij.execution.CommonProgramRunConfigurationParameters;
import com.intellij.execution.ExecutionBundle;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.execution.configurations.SimpleJavaParameters;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessHandlerFactory;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.util.ProgramParametersUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PathMacroManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.JavaSdkType;
import com.intellij.openapi.projectRoots.JdkUtil;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.projectRoots.SimpleJavaSdkType;
import com.intellij.openapi.projectRoots.impl.SdkVersionUtil;
import com.intellij.openapi.roots.JdkOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEnumerator;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.OrderRootsEnumerator;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.encoding.EncodingProjectManager;
import com.intellij.util.text.VersionComparatorUtil;
import org.intellij.xquery.runner.state.run.DataSourceAccessor;
import org.intellij.xquery.runner.state.run.VariablesAccessor;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.state.run.XQueryRunConfigurationSerializer;
import org.intellij.xquery.runner.state.run.XmlConfigurationAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class XQueryRunProfileState extends CommandLineState {
    private XQueryRunConfiguration configuration;
    private int port;
    private SimpleJavaParameters myParams;

    public XQueryRunProfileState(ExecutionEnvironment environment, XQueryRunConfiguration runConfiguration) {
        super(environment);
        configuration = runConfiguration;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        ProcessHandlerFactory factory = ProcessHandlerFactory.getInstance();
        OSProcessHandler processHandler = factory.createProcessHandler(createCommandLine());
        ProcessTerminatedListener.attach(processHandler);
        return processHandler;
    }

    private SimpleJavaParameters getJavaParameters() throws ExecutionException {
        if (myParams == null) {
            myParams = createJavaParameters();
        }
        return myParams;
    }

    private SimpleJavaParameters createJavaParameters() throws ExecutionException {
        final SimpleJavaParameters parameters = prepareRunnerParameters();
        configureJreRelatedParameters(parameters);
        return parameters;
    }

    private void configureJreRelatedParameters(SimpleJavaParameters parameters) throws CantRunException {
        final RunConfigurationModule module = configuration.getConfigurationModule();
        final String jreHome = configuration.isAlternativeJrePathEnabled() ? configuration.getAlternativeJrePath() : null;
        configureModule(module, parameters, jreHome);
        configureConfiguration(parameters, configuration);
    }


    private void configureModule(final RunConfigurationModule runConfigurationModule,
                                 final SimpleJavaParameters parameters,
                                 @Nullable String jreHome) throws CantRunException {
        Module module = runConfigurationModule.getModule();
        if (module == null) {
            throw CantRunException.noModuleConfigured(runConfigurationModule.getModuleName());
        }
        configureByModule(parameters, module, createModuleJdk(module, jreHome));
    }


    private void configureByModule(SimpleJavaParameters parameters, final Module module, final Sdk jdk) throws CantRunException {
        if (jdk == null) {
            throw CantRunException.noJdkConfigured();
        }
        parameters.setJdk(jdk);
        setDefaultCharset(parameters, module.getProject());
        configureEnumerator(OrderEnumerator.orderEntries(module).runtimeOnly().recursively(), jdk).collectPaths(parameters.getClassPath());
    }

    private void setDefaultCharset(SimpleJavaParameters parameters, final Project project) {
        Charset encoding = EncodingProjectManager.getInstance(project).getDefaultCharset();
        parameters.setCharset(encoding);
    }

    private OrderRootsEnumerator configureEnumerator(OrderEnumerator enumerator, Sdk jdk) {
        enumerator = enumerator.productionOnly();
        OrderRootsEnumerator rootsEnumerator = enumerator.classes();
        rootsEnumerator = rootsEnumerator.usingCustomRootProvider(e -> e instanceof JdkOrderEntry ? jdkRoots(jdk) : e.getFiles(OrderRootType.CLASSES));
        return rootsEnumerator;
    }

    private Sdk createModuleJdk(final Module module, @Nullable String jreHome) throws CantRunException {
        return jreHome == null ? getValidJdkToRunModule(module) : createAlternativeJdk(jreHome);
    }


    private SimpleJavaParameters prepareRunnerParameters() throws CantRunException {
        final SimpleJavaParameters parameters = new SimpleJavaParameters();
        parameters.setMainClass(configuration.getRunClass());
        boolean isDebugging = getEnvironment().getExecutor().getId().equals(DefaultDebugExecutor.EXECUTOR_ID);
        parameters.getProgramParametersList().prepend(getSerializedConfig(configuration, isDebugging, port).getAbsolutePath());
        parameters.getClassPath().addFirst(new XQueryRunnerClasspathEntryGenerator().generateRunnerClasspathEntries(configuration));
        return parameters;
    }

    private File getSerializedConfig(XQueryRunConfiguration configuration, boolean isDebugging, int port) {
        try {
            File serializedConfig = File.createTempFile("xquery-run", ".xml");
            XmlConfigurationAccessor xmlConfigurationAccessor = new XmlConfigurationAccessor();
            VariablesAccessor variablesAccessor = new VariablesAccessor();
            DataSourceAccessor dataSourceAccessor = new DataSourceAccessor();
            new XQueryRunConfigurationSerializer(configuration, xmlConfigurationAccessor, variablesAccessor,
                    dataSourceAccessor, isDebugging, port).serialize(new FileWriter(serializedConfig));
            return serializedConfig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private GeneralCommandLine createCommandLine() throws ExecutionException {
        final Project project = getEnvironment().getProject();
        return createFromJavaParameters(getJavaParameters(), project);
    }

    private GeneralCommandLine createFromJavaParameters(final SimpleJavaParameters javaParameters,
                                                        final Project project) throws CantRunException {
        return createFromJavaParameters(javaParameters, JdkUtil.useDynamicClasspath(project));
    }

    private static GeneralCommandLine createFromJavaParameters(final SimpleJavaParameters javaParameters,
                                                               final boolean forceDynamicClasspath) throws CantRunException {
        try {
            return ApplicationManager.getApplication().runReadAction(new Computable<GeneralCommandLine>() {
                public GeneralCommandLine compute() {
                    try {
                        final Sdk jdk = javaParameters.getJdk();
                        if (jdk == null) {
                            throw new CantRunException(ExecutionBundle.message("run.configuration.error.no.jdk.specified"));
                        }

                        final SdkTypeId sdkType = jdk.getSdkType();
                        if (!(sdkType instanceof JavaSdkType)) {
                            throw new CantRunException(ExecutionBundle.message("run.configuration.error.no.jdk.specified"));
                        }

                        final String exePath = ((JavaSdkType) sdkType).getVMExecutablePath(jdk);
                        if (exePath == null) {
                            throw new CantRunException(ExecutionBundle.message("run.configuration.cannot.find.vm.executable"));
                        }
                        if (javaParameters.getMainClass() == null && javaParameters.getJarPath() == null) {
                            throw new CantRunException(ExecutionBundle.message("main.class.is.not.specified.error.message"));
                        }

                        return JdkUtil.setupJVMCommandLine(exePath, javaParameters, forceDynamicClasspath);
                    } catch (CantRunException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (RuntimeException e) {
            if (e.getCause() instanceof CantRunException) {
                throw (CantRunException) e.getCause();
            } else {
                throw e;
            }
        }
    }

    private void configureConfiguration(SimpleJavaParameters parameters, XQueryRunConfiguration configuration) {
        ProgramParametersUtil.configureConfiguration(parameters, configuration);

        Project project = configuration.getProject();
        Module module = getModule(configuration);
        ;

        String alternativeJrePath = configuration.getAlternativeJrePath();
        if (alternativeJrePath != null) {
            configuration.setAlternativeJrePath(expandPath(alternativeJrePath, null, project));
        }

        String vmParameters = configuration.getVMParameters();
        if (vmParameters != null) {
            vmParameters = expandPath(vmParameters, module, project);

            for (Map.Entry<String, String> each : parameters.getEnv().entrySet()) {
                vmParameters = StringUtil.replace(vmParameters, "$" + each.getKey() + "$", each.getValue(), false);
            }
        }

        parameters.getVMParametersList().addParametersString(vmParameters);
    }


    private String expandPath(String path, Module module, Project project) {
        path = PathMacroManager.getInstance(project).expandPath(path);
        if (module != null) {
            path = PathMacroManager.getInstance(module).expandPath(path);
        }

        return path;
    }


    private Module getModule(CommonProgramRunConfigurationParameters configuration) {
        return configuration instanceof ModuleBasedConfiguration ? ((ModuleBasedConfiguration) configuration).getConfigurationModule().getModule() : null;
    }

    private Sdk getValidJdkToRunModule(final Module module) throws CantRunException {
        Sdk jdk = getJdkToRunModule(module);
        String currentRunningJavaHome = getCurrentRunningJavaHome();
        if (jdk == null) {
            if (currentRunningJavaHome != null) {
                jdk = createAlternativeJdk(currentRunningJavaHome);
            } else {
                throw CantRunException.noJdkForModule(module);
            }
        }
        final VirtualFile homeDirectory = jdk.getHomeDirectory();
        if (homeDirectory == null || !homeDirectory.isValid()) {
            throw CantRunException.jdkMisconfigured(jdk, module);
        }
        return jdk;
    }

    private Sdk getJdkToRunModule(Module module) {
        final Sdk moduleSdk = ModuleRootManager.getInstance(module).getSdk();
        if (moduleSdk == null) {
            return null;
        }

        final Set<Sdk> sdksFromDependencies = new LinkedHashSet<>();
        OrderEnumerator enumerator = OrderEnumerator.orderEntries(module).runtimeOnly().recursively();
        enumerator = enumerator.productionOnly();
        enumerator.forEachModule(module1 -> {
            Sdk sdk = ModuleRootManager.getInstance(module1).getSdk();
            if (sdk != null && sdk.getSdkType().equals(moduleSdk.getSdkType())) {
                sdksFromDependencies.add(sdk);
            }
            return true;
        });
        return findLatestVersion(moduleSdk, sdksFromDependencies);
    }

    private String getCurrentRunningJavaHome() {
        String javaHomeOfCurrentProcess = System.getProperty("java.home");
        if (javaHomeOfCurrentProcess != null && !javaHomeOfCurrentProcess.isEmpty()) {
            return javaHomeOfCurrentProcess;
        }
        return null;
    }

    private Sdk findLatestVersion(@NotNull Sdk mainSdk, @NotNull Set<Sdk> sdks) {
        Sdk result = mainSdk;
        for (Sdk sdk : sdks) {
            if (VersionComparatorUtil.compare(result.getVersionString(), sdk.getVersionString()) < 0) {
                result = sdk;
            }
        }
        return result;
    }


    private Sdk createAlternativeJdk(@NotNull String jreHome) throws CantRunException {
        final Sdk configuredJdk = ProjectJdkTable.getInstance().findJdk(jreHome);
        if (configuredJdk != null) {
            return configuredJdk;
        }

        if (!JdkUtil.checkForJre(jreHome) && !JdkUtil.checkForJdk(jreHome)) {
            throw new CantRunException(ExecutionBundle.message("jre.path.is.not.valid.jre.home.error.message", jreHome));
        }

        final String versionString = SdkVersionUtil.detectJdkVersion(jreHome);
        final Sdk jdk = new SimpleJavaSdkType().createJdk(versionString != null ? versionString : "", jreHome);
        if (jdk == null) throw CantRunException.noJdkConfigured();
        return jdk;
    }

    private VirtualFile[] jdkRoots(Sdk jdk) {
        return Arrays.stream(jdk.getRootProvider().getFiles(OrderRootType.CLASSES)).toArray(VirtualFile[]::new);
    }
}