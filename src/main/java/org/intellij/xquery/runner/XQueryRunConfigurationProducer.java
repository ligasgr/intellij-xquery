/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

import com.intellij.execution.Location;
import com.intellij.execution.RunManagerEx;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.psi.*;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.state.run.XQueryRunVariable;
import org.intellij.xquery.runner.state.run.XQueryRunVariables;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static org.intellij.xquery.util.StringUtils.EMPTY;
import static org.intellij.xquery.util.StringUtils.removeQuotOrAposIfNeeded;

public class XQueryRunConfigurationProducer extends RunConfigurationProducer implements Cloneable {

    public XQueryRunConfigurationProducer() {
        super(XQueryRunConfigurationType.getInstance());
    }

    @Override
    protected boolean setupConfigurationFromContext(RunConfiguration configuration, ConfigurationContext context, Ref sourceElement) {
        if (!(configuration instanceof XQueryRunConfiguration)) return false;
        XQueryRunConfiguration appConfiguration = (XQueryRunConfiguration) configuration;
        Location location = context.getLocation();
        PsiElement psiElement = location.getPsiElement();
        PsiFile psiFile = psiElement.getContainingFile();
        if (isUnsupportedFile(psiFile)) return false;
        XQueryFile containingFile = (XQueryFile) psiFile;
        if (containingFile.isLibraryModule()) return false;
        final VirtualFile vFile = location.getVirtualFile();
        if (vFile == null) return false;
        prepareSettings(appConfiguration, context, vFile, containingFile);
        return true;
    }

    @Override
    public boolean isConfigurationFromContext(RunConfiguration configuration, ConfigurationContext context) {
        if (!(configuration instanceof XQueryRunConfiguration)) return false;
        XQueryRunConfiguration appConfiguration = (XQueryRunConfiguration) configuration;
        Location location = context.getLocation();
        PsiFile psiFile = location.getPsiElement().getContainingFile();
        if (isUnsupportedFile(psiFile) || ((XQueryFile) psiFile).isLibraryModule()) return false;
        return isForTheSameFile(psiFile, appConfiguration) && isForTheSameModule(location, appConfiguration)
                && appConfiguration.getDataSourceName() != null;
    }

    @Override
    protected RunnerAndConfigurationSettings cloneTemplateConfiguration(@NotNull ConfigurationContext context) {
        RunnerAndConfigurationSettings cloned = super.cloneTemplateConfiguration(context);
        cloned.setEditBeforeRun(true);
        return cloned;
    }

    private boolean isUnsupportedFile(PsiFile psiFile) {
        return !(psiFile instanceof XQueryFile);
    }

    private void prepareSettings(XQueryRunConfiguration configuration, ConfigurationContext context,
                                                           VirtualFile vFile, XQueryFile containingFile) {
        setFileSpecificSettings(vFile, configuration, containingFile);
        setupConfigurationModule(context, configuration);
    }

    private void setFileSpecificSettings(VirtualFile vFile, XQueryRunConfiguration configuration, XQueryFile containingFile) {
        configuration.setMainFileName(FileUtil.toSystemIndependentName(vFile.getCanonicalPath()));
        configuration.setName(vFile.getPresentableName());
        configuration.setVariables(getExternalVariables(containingFile));
        configuration.setContextItemEnabled(containingFile.getContextItem() != null);
        String contextItemType = getContextItemType(containingFile);
        if (contextItemType != null)
            configuration.setContextItemType(contextItemType);
    }

    private String getContextItemType(XQueryFile containingFile) {
        XQueryContextItemDecl contextItem = containingFile.getContextItem();
        if (contextItem != null && contextItem.getItemType() != null)
            return contextItem.getItemType().getText();
        return null;
    }

    private XQueryRunVariables getExternalVariables(XQueryFile file) {
        List<XQueryRunVariable> variables = new ArrayList<XQueryRunVariable>();
        for (XQueryVarDecl varDecl : file.getVariableDeclarations()) {
            if (varDecl.isExternal()) {
                String type = varDecl.getTypeDeclaration() != null ? varDecl.getTypeDeclaration().getSequenceType()
                        .getText() : null;
                XQueryVarDefaultValue varDefaultValue = varDecl.getExternalVarPart().getVarDefaultValue();
                String value = EMPTY;
                if (varDefaultValue != null && varDefaultValue.getExprSingle().getFirstChild() instanceof
                        XQueryLiteral) {
                    value = removeQuotOrAposIfNeeded(varDefaultValue.getExprSingle().getText());
                }
                boolean isActive = varDefaultValue == null;
                String name = varDecl.getVarName().getText();
                XQueryPrefix prefix = varDecl.getVarName().getPrefix();
                String namespace = EMPTY;
                if (prefix != null) {
                    PsiElement resolved = prefix.getReference().resolve();
                    if (resolved != null) {
                        namespace = ((XQueryNamespaceSource) resolved.getParent()).getNamespace();
                    }
                }
                XQueryRunVariable variable = new XQueryRunVariable(name, namespace, type, value, isActive);
                variables.add(variable);
            }
        }
        return new XQueryRunVariables(variables);
    }

    private void setupConfigurationModule(@Nullable ConfigurationContext context,
                                          ModuleBasedConfiguration configuration) {
        if (context != null) {
            Module module = getModule(context, configuration);
            if (module != null) {
                configuration.setModule(module);
            }
        }
    }

    private Module getModule(ConfigurationContext context, ModuleBasedConfiguration configuration) {
        Module module = getPredefinedModule(context);
        if (module == null)
            module = getModuleForLocation(context, configuration);
        return module;
    }

    private Module getModuleForLocation(ConfigurationContext context, ModuleBasedConfiguration configuration) {
        final Module contextModule = context.getModule();
        return findModule(configuration, contextModule);
    }

    private Module getPredefinedModule(ConfigurationContext context) {
        final RunnerAndConfigurationSettings template = getConfigurationTemplate(context);
        return getPredefinedModule(template);
    }

    private RunnerAndConfigurationSettings getConfigurationTemplate(ConfigurationContext context) {
        return context.getRunManager().getConfigurationTemplate(getConfigurationFactory());
    }

    private Module findModule(ModuleBasedConfiguration configuration, Module contextModule) {
        if (configuration.getConfigurationModule().getModule() == null && contextModule != null) {
            return contextModule;
        }
        return null;
    }

    private boolean isForTheSameModule(Location location, XQueryRunConfiguration appConfiguration) {
        final Module configurationModule = appConfiguration.getConfigurationModule().getModule();
        return Comparing.equal(location.getModule(), configurationModule)
                || Comparing.equal(getPredefinedModule(location), configurationModule);
    }

    private boolean isForTheSameFile(PsiFile psiFile, XQueryRunConfiguration appConfiguration) {
        String checkedFilePath = FileUtil.toSystemIndependentName(psiFile.getVirtualFile().getCanonicalPath());
        String existingConfigFilePath = "";
        if (appConfiguration.getMainFileName() != null)
            existingConfigFilePath =
                    FileUtil.toSystemIndependentName(appConfiguration.getMainFileName());
        return Comparing.equal(checkedFilePath, existingConfigFilePath);
    }

    private Module getPredefinedModule(Location location) {
        return ((XQueryRunConfiguration) RunManagerEx.getInstanceEx(location.getProject())
                .getConfigurationTemplate(getConfigurationFactory())
                .getConfiguration()).getConfigurationModule().getModule();
    }

    private Module getPredefinedModule(RunnerAndConfigurationSettings template) {
        return ((ModuleBasedConfiguration) template.getConfiguration()).getConfigurationModule().getModule();
    }
}
