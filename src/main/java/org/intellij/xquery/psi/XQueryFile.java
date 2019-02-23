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

package org.intellij.xquery.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.XQueryFileType;
import org.intellij.xquery.XQueryFlavour;
import org.intellij.xquery.XQueryLanguage;
import org.intellij.xquery.completion.function.BuiltInFunctionSignature;
import org.intellij.xquery.completion.function.BuiltInFunctionTable;
import org.intellij.xquery.model.XQueryLanguageVersion;
import org.intellij.xquery.reference.namespace.PredeclaredNamespaces;
import org.intellij.xquery.settings.XQuerySettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import javax.xml.XMLConstants;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.intellij.util.containers.ContainerUtil.findAll;
import static org.intellij.xquery.model.XQueryLanguageVersion.V0_9_ML;
import static org.intellij.xquery.model.XQueryLanguageVersion.V1_0_ML;
import static org.intellij.xquery.model.XQueryLanguageVersion.V3_1;
import static org.intellij.xquery.psi.XQueryUtil.getReferencesToExistingFilesInImport;
import static org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces.FN;
import static org.intellij.xquery.util.StringUtils.removeQuotOrApos;

public class XQueryFile extends PsiFileBase {

    public XQueryFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, XQueryLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return XQueryFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "XQuery File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }

    private CachedValue<Collection<XQueryVarDecl>> variableDeclarations;
    private CachedValue<Collection<XQueryModuleImport>> moduleImports;
    private CachedValue<Collection<XQueryNamespaceDecl>> namespaceDeclarations;
    private CachedValue<Collection<XQueryFunctionDecl>> functionDeclarations;
    private CachedValue<Collection<XQueryFunctionInvocation>> functionInvocations;
    private CachedValue<Collection<XQueryVarRef>> variableReferences;
    private CachedValue<Collection<XQueryVarName>> variableNames;
    private CachedValue<Collection<XQueryNamespaceDecl>> namespacesMatchingDefault;
    private CachedValue<String> defaultFunctionNamespace;
    private CachedValue<Map<String, String>> functionPrefixToNamespaceMapping;
    private CachedValue<XQueryModuleDecl> moduleDeclaration;
    private CachedValue<Map<String, String>> variablePrefixToNamespaceMapping;
    private CachedValue<XQueryVersionDecl> versionDeclaration;
    private CachedValue<Collection<XQueryAnnotation>> annotations;
    private CachedValue<BuiltInFunctionTable> builtInFunctionTable;

    @NotNull
    public Collection<XQueryVarDecl> getVariableDeclarations() {
        if (variableDeclarations == null) {
            variableDeclarations = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryVarDecl>>() {
                        @Override
                        public Result<Collection<XQueryVarDecl>> compute() {
                            return CachedValueProvider.Result.create(calcVariableDeclarations(), XQueryFile.this);
                        }
                    }, false);
        }
        return variableDeclarations.getValue();
    }

    @NotNull
    public Collection<XQueryModuleImport> getModuleImports() {
        if (moduleImports == null) {
            moduleImports = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryModuleImport>>() {
                        @Override
                        public Result<Collection<XQueryModuleImport>> compute() {
                            return CachedValueProvider.Result.create(calcModuleImports(), XQueryFile.this);
                        }
                    }, false);
        }
        return moduleImports.getValue();
    }

    @NotNull
    public Collection<XQueryNamespaceDecl> getNamespaceDeclarations() {
        if (namespaceDeclarations == null) {
            namespaceDeclarations = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryNamespaceDecl>>() {
                        @Override
                        public Result<Collection<XQueryNamespaceDecl>> compute() {
                            return CachedValueProvider.Result.create(calcNamespaceDeclarations(), XQueryFile.this);
                        }
                    }, false);
        }
        return namespaceDeclarations.getValue();
    }

    @NotNull
    public Collection<XQueryFunctionDecl> getFunctionDeclarations() {
        if (functionDeclarations == null) {
            functionDeclarations = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryFunctionDecl>>() {
                        @Override
                        public Result<Collection<XQueryFunctionDecl>> compute() {
                            return CachedValueProvider.Result.create(calcFunctionDeclarations(), XQueryFile.this);
                        }
                    }, false);
        }
        return functionDeclarations.getValue();
    }

    public Collection<XQueryVarRef> getVariableReferences() {
        if (variableReferences == null) {
            variableReferences = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryVarRef>>() {
                        @Override
                        public Result<Collection<XQueryVarRef>> compute() {
                            return CachedValueProvider.Result.create(calcVariableReferences(), XQueryFile.this);
                        }
                    }, false);
        }
        return variableReferences.getValue();
    }

    public Collection<XQueryVarName> getVariableNames() {
        if (variableNames == null) {
            variableNames = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryVarName>>() {
                        @Override
                        public Result<Collection<XQueryVarName>> compute() {
                            return CachedValueProvider.Result.create(calcVariableNames(), XQueryFile.this);
                        }
                    }, false);
        }
        return variableNames.getValue();
    }

    @NotNull
    public Collection<XQueryFunctionInvocation> getFunctionInvocations() {
        if (functionInvocations == null) {
            functionInvocations = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryFunctionInvocation>>() {
                        @Override
                        public Result<Collection<XQueryFunctionInvocation>> compute() {
                            return CachedValueProvider.Result.create(calcFunctionsInvocations(), XQueryFile.this);
                        }
                    }, false);
        }
        return functionInvocations.getValue();
    }

    @NotNull
    public Collection<XQueryNamespaceDecl> getNamespaceDeclarationsMatchingDefaultNamespace() {
        if (namespacesMatchingDefault == null) {
            namespacesMatchingDefault = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryNamespaceDecl>>() {
                        @Override
                        public Result<Collection<XQueryNamespaceDecl>> compute() {
                            return CachedValueProvider.Result.create
                                    (calcNamespaceDeclarationsMatchingDefaultNamespace(), XQueryFile.this);
                        }
                    }, false);
        }
        return namespacesMatchingDefault.getValue();
    }

    @NotNull
    public String getDefaultFunctionNamespace() {
        if (defaultFunctionNamespace == null) {
            defaultFunctionNamespace = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<String>() {
                        @Override
                        public Result<String> compute() {
                            return CachedValueProvider.Result.create
                                    (calcDefaultFunctionNamespace(), XQueryFile.this, getSettings());
                        }
                    }, false);
        }
        return defaultFunctionNamespace.getValue();
    }

    @Nullable
    public XQueryModuleDecl getModuleDeclaration() {
        if (moduleDeclaration == null) {
            moduleDeclaration = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<XQueryModuleDecl>() {
                        @Override
                        public Result<XQueryModuleDecl> compute() {
                            return CachedValueProvider.Result.create
                                    (calcModuleDeclaration(), XQueryFile.this);
                        }
                    }, false);
        }
        return moduleDeclaration.getValue();
    }

    @Nullable
    public XQueryVersionDecl getVersionDeclaration() {
        if (versionDeclaration == null) {
            versionDeclaration = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<XQueryVersionDecl>() {
                        @Override
                        public Result<XQueryVersionDecl> compute() {
                            return CachedValueProvider.Result.create
                                    (calcVersionDeclaration(), XQueryFile.this);
                        }
                    }, false);
        }
        return versionDeclaration.getValue();
    }

    public Collection<XQueryAnnotation> getAnnotations() {
        if (annotations == null) {
            annotations = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryAnnotation>>() {
                        @Override
                        public Result<Collection<XQueryAnnotation>> compute() {
                            return CachedValueProvider.Result.create(calcAnnotations(), XQueryFile.this);
                        }
                    }, false);
        }
        return annotations.getValue();
    }

    public Collection<XQueryXmlTagName> getXmlTagNames() {
        return PsiTreeUtil.findChildrenOfType(this, XQueryXmlTagName.class);
    }

    private Collection<XQueryVarDecl> calcVariableDeclarations() {
        Collection<XQueryVarDecl> variableDeclarations = PsiTreeUtil.findChildrenOfType(this, XQueryVarDecl.class);
        return variableDeclarations;
    }

    private Collection<XQueryVarRef> calcVariableReferences() {
        Collection<XQueryVarRef> variableReferences = PsiTreeUtil.findChildrenOfType(this, XQueryVarRef.class);
        return variableReferences;
    }

    private Collection<XQueryVarName> calcVariableNames() {
        Collection<XQueryVarName> variableNames = PsiTreeUtil.findChildrenOfType(this, XQueryVarName.class);
        return variableNames;
    }

    private Collection<XQueryModuleImport> calcModuleImports() {
        Collection<XQueryModuleImport> moduleImports = PsiTreeUtil.findChildrenOfType(this, XQueryModuleImport.class);
        return moduleImports;
    }

    private Collection<XQueryNamespaceDecl> calcNamespaceDeclarations() {
        Collection<XQueryNamespaceDecl> namespaceDeclarations = PsiTreeUtil.findChildrenOfType(this,
                XQueryNamespaceDecl.class);
        return namespaceDeclarations;
    }

    private Collection<XQueryFunctionDecl> calcFunctionDeclarations() {
        Collection<XQueryFunctionDecl> functionDeclarations = PsiTreeUtil.findChildrenOfType(this,
                XQueryFunctionDecl.class);
        return functionDeclarations;
    }

    private Collection<XQueryFunctionInvocation> calcFunctionsInvocations() {
        Collection<XQueryFunctionInvocation> functionsInvocations = PsiTreeUtil.findChildrenOfType(this,
                XQueryFunctionInvocation.class);
        return functionsInvocations;
    }

    private Collection<XQueryAnnotation> calcAnnotations() {
        return PsiTreeUtil.findChildrenOfType(this, XQueryAnnotation.class);
    }

    private Collection<XQueryNamespaceDecl> calcNamespaceDeclarationsMatchingDefaultNamespace() {
        Collection<XQueryNamespaceDecl> all = getNamespaceDeclarations();
        return findAll(all, new Condition<XQueryNamespaceDecl>() {
            @Override
            public boolean value(XQueryNamespaceDecl declaration) {
                return declaration.getNamespacePrefix() != null && declaration.getURILiteral() != null &&
                        getDefaultFunctionNamespace().equals(removeQuotOrApos(declaration.getURILiteral().getText()));
            }
        });
    }

    private XQueryModuleDecl calcModuleDeclaration() {
        return PsiTreeUtil.findChildOfType(this, XQueryModuleDecl.class);
    }

    private String calcDefaultFunctionNamespace() {
        XQueryDefaultFunctionNamespaceDecl defaultFunctionNamespaceDecl = getDefaultNamespaceFunctionDeclaration();
        if (defaultFunctionNamespaceDecl != null && defaultFunctionNamespaceDecl.getURILiteral() != null)
            return removeQuotOrApos(defaultFunctionNamespaceDecl.getURILiteral().getText());
        else if (isLibraryModule()
                && (versionIsMarklogicSpecific() || getSettings().isMarklogicFlavour())
                && getModuleDeclaration().getURILiteral() != null) {
            return removeQuotOrApos(getModuleDeclaration().getURILiteral().getText());
        }
        return FN.getNamespace();
    }

    private XQueryVersionDecl calcVersionDeclaration() {
        return PsiTreeUtil.findChildOfType(this, XQueryVersionDecl.class);
    }

    public XQueryNamespacePrefix getModuleNamespaceName() {
        XQueryModuleDecl moduleDecl = getModuleDeclaration();
        return moduleDecl != null ? moduleDecl.getNamespacePrefix() : null;
    }

    public Collection<XQueryFile> getImportedFilesThatExist(Condition<XQueryModuleImport> condition) {
        Collection<XQueryFile> result = new LinkedList<XQueryFile>();
        for (XQueryModuleImport moduleImport : findAll(getModuleImports(), condition)) {
            result.addAll(getReferencesToExistingFilesInImport(moduleImport));
        }
        return result;
    }

    public XQueryDefaultFunctionNamespaceDecl getDefaultNamespaceFunctionDeclaration() {
        return PsiTreeUtil.findChildOfType(this, XQueryDefaultFunctionNamespaceDecl.class);
    }

    public String mapFunctionPrefixToNamespace(String prefix) {
        return getFunctionPrefixToNamespaceMap().get(prefix);
    }

    public Map<String, String> getFunctionPrefixToNamespaceMap() {
        if (functionPrefixToNamespaceMapping == null) {
            functionPrefixToNamespaceMapping = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Map<String, String>>() {
                        @Override
                        public Result<Map<String, String>> compute() {
                            return CachedValueProvider.Result.create(calcFunctionPrefixToNamespaceMap(),
                                    XQueryFile.this, getSettings());
                        }
                    }, false);
        }
        return functionPrefixToNamespaceMapping.getValue();
    }

    private Map<String, String> calcFunctionPrefixToNamespaceMap() {
        Map<String, String> namespaceMapping = calcPrefixToNamespaceMap();
        namespaceMapping.put(null, getDefaultFunctionNamespace());
        return namespaceMapping;
    }

    private Map<String, String> calcPrefixToNamespaceMap() {
        XQueryNamespacePrefix moduleNamespaceName = getModuleNamespaceName();
        Collection<XQueryNamespaceDecl> namespaceDeclarations = getNamespaceDeclarations();
        Map<String, String> namespaceMapping = new HashMap<String, String>(getPrefixToNamespaceMap());
        if (moduleNamespaceName != null && getModuleDeclaration().getURILiteral() != null) {
            namespaceMapping.put(moduleNamespaceName.getName(),
                    removeQuotOrApos(getModuleDeclaration().getURILiteral().getText()));
        }
        for (XQueryNamespaceDecl namespaceDeclaration : namespaceDeclarations) {
            if (namespaceDeclaration.getNamespacePrefix() != null && namespaceDeclaration.getURILiteral() != null) {
                namespaceMapping.put(namespaceDeclaration.getNamespacePrefix().getText(),
                        removeQuotOrApos(namespaceDeclaration.getURILiteral().getText()));
            }
        }
        for (XQueryModuleImport moduleImport : getModuleImports()) {
            if (moduleImport.getNamespacePrefix() != null && moduleImport.getModuleImportNamespace() != null) {
                namespaceMapping.put(moduleImport.getNamespacePrefix().getText(),
                        removeQuotOrApos(moduleImport.getModuleImportNamespace().getText()));
            }
        }

        return namespaceMapping;
    }

    public boolean isLibraryModule() {
        XQueryModuleDecl moduleDecl = PsiTreeUtil.findChildOfType(this, XQueryModuleDecl.class);
        return moduleDecl != null;
    }

    public XQueryContextItemDecl getContextItem() {
        return PsiTreeUtil.findChildOfType(this, XQueryContextItemDecl.class);
    }

    public boolean isPrefixForDefaultFunctionNamespace(String prefix) {
        return isDefaultFunctionNamespace(mapFunctionPrefixToNamespace(prefix));
    }

    public boolean isDefaultFunctionNamespace(String namespace) {
        return getDefaultFunctionNamespace().equals(namespace);
    }

    private Map<String, String> calcVariablePrefixToNamespaceMap() {
        Map<String, String> namespaceMapping = calcPrefixToNamespaceMap();
        namespaceMapping.put(null, getVariableDefaultNamespace());
        return namespaceMapping;
    }

    private String getVariableDefaultNamespace() {
        if (isLibraryModule()
                && (versionIsMarklogicSpecific() || getSettings().isMarklogicFlavour())
                && getModuleDeclaration().getURILiteral() != null) {
            return removeQuotOrApos(getModuleDeclaration().getURILiteral().getText());
        }
        return XMLConstants.DEFAULT_NS_PREFIX;
    }

    public String mapVariablePrefixToNamespace(String prefix) {
        return getVariablePrefixToNamespaceMap().get(prefix);
    }

    public Map<String, String> getVariablePrefixToNamespaceMap() {
        if (variablePrefixToNamespaceMapping == null) {
            variablePrefixToNamespaceMapping = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Map<String, String>>() {
                        @Override
                        public Result<Map<String, String>> compute() {
                            return CachedValueProvider.Result.create(calcVariablePrefixToNamespaceMap(),
                                    XQueryFile.this, getSettings());
                        }
                    }, false);
        }
        return variablePrefixToNamespaceMapping.getValue();
    }

    public XQueryQueryBody getQueryBody() {
        return PsiTreeUtil.findChildOfType(this, XQueryQueryBody.class);
    }

    private XQuerySettings getSettings() {
        return XQuerySettings.getInstance(getProject());
    }

    private PredeclaredNamespaces predeclaredNamespaces() {
        return getBuiltInFunctionTable().predeclaredNamespaces();
    }

    public boolean isPredeclaredNamespacePrefix(String namespacePrefix) {
        return predeclaredNamespaces().isPredeclaredNamespacePrefix(namespacePrefix);
    }

    public boolean isPredeclaredNamespace(String namespace) {
        return predeclaredNamespaces().isPredeclaredNamespace(namespace);
    }

    Map<String, String> getPrefixToNamespaceMap() {
        return predeclaredNamespaces().getPrefixToNamespaceMap(this);
    }

    public BuiltInFunctionTable calcBuiltInFunctionTable() {
        if (versionIsMarklogicSpecific()) {
            return XQueryFlavour.MARKLOGIC.getBifTable();
        }
        return getSettings().getFlavour().getBifTable();
    }

    public BuiltInFunctionTable getBuiltInFunctionTable() {
        if (builtInFunctionTable == null) {
            builtInFunctionTable = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<BuiltInFunctionTable>() {
                        @Override
                        public Result<BuiltInFunctionTable> compute() {
                            return CachedValueProvider.Result.create(calcBuiltInFunctionTable(),
                                    XQueryFile.this, getSettings());
                        }
                    }, false);
        }
        return builtInFunctionTable.getValue();
    }

    public boolean versionIsNotMarklogicSpecific() {
        return !versionIsMarklogicSpecific();
    }

    public boolean versionIs31() {
        XQueryVersionDecl versionDecl = getVersionDeclaration();
        XQueryVersion version = versionDecl != null ? versionDecl.getVersion() : null;
        boolean versionIs31 = false;
        if (version != null) {
            String versionString = version.getVersionString();
            XQueryLanguageVersion languageVersion = XQueryLanguageVersion.valueFor(versionString);
            if (V3_1 == languageVersion) {
                versionIs31 = true;
            }
        } else if (getSettings().isFlavourWithVersion31()) {
            versionIs31 = true;
        }
        return versionIs31;
    }

    public boolean versionIsMarklogicSpecific() {
        XQueryVersionDecl versionDecl = getVersionDeclaration();
        XQueryVersion version = versionDecl != null ? versionDecl.getVersion() : null;
        boolean versionIsMarklogicSpecific = false;
        if (version != null) {
            String versionString = version.getVersionString();
            XQueryLanguageVersion languageVersion = XQueryLanguageVersion.valueFor(versionString);
            if (V1_0_ML == languageVersion || V0_9_ML == languageVersion) {
                versionIsMarklogicSpecific = true;
            }
        } else if (getSettings().isMarklogicFlavour()) {
            versionIsMarklogicSpecific = true;
        }
        return versionIsMarklogicSpecific;
    }

    public boolean isBuiltInFunction(String namespace, String name) {
        return getBuiltInFunctionTable().isBuiltInFunction(namespace, name);
    }

    public boolean isBuiltInFunction(XQueryFunctionName functionName) {
        String name = functionName.getLocalNameText();
        String prefix = functionName.getPrefixText();
        String namespace = mapFunctionPrefixToNamespace(prefix);
        return isBuiltInFunction(namespace, name);
    }

    public Collection<BuiltInFunctionSignature> getFunctionsSignatures(String namespace, String name) {
        return getBuiltInFunctionTable().getFunctionsSignatures(namespace, name);
    }
}