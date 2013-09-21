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
import org.intellij.xquery.XQueryLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.intellij.util.containers.ContainerUtil.findAll;
import static org.intellij.xquery.psi.XQueryUtil.getReferencesToExistingFilesInImport;
import static org.intellij.xquery.util.StringUtils.removeQuotOrApos;
import static org.intellij.xquery.reference.namespace.XQueryPredeclaredNamespace.FN;
import static org.intellij.xquery.reference.namespace.XQueryPredeclaredNamespace.getMappingFromPrefix;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 18:59
 */
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

    private CachedValue<Collection<XQueryVarDecl>> myVariables;
    private CachedValue<Collection<XQueryModuleImport>> myModulesImports;
    private CachedValue<Collection<XQueryNamespaceDecl>> myNamespaceDeclarations;
    private CachedValue<Collection<XQueryFunctionDecl>> myFunctions;
    private CachedValue<Collection<XQueryNamespaceDecl>> myNamespacesMatchingDefault;
    private CachedValue<String> myDefaultFunctionNamespace;
    private CachedValue<Map<String, String>> myNamespaceMapping;
    private CachedValue<XQueryModuleDecl> myModuleDeclaration;

    @NotNull
    public Collection<XQueryVarDecl> getVariableDeclarations() {
        if (myVariables == null) {
            myVariables = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryVarDecl>>() {
                        @Override
                        public Result<Collection<XQueryVarDecl>> compute() {
                            return CachedValueProvider.Result.create(calcVariableDeclarations(), XQueryFile.this);
                        }
                    }, false);
        }
        return myVariables.getValue();
    }

    @NotNull
    public Collection<XQueryModuleImport> getModuleImports() {
        if (myModulesImports == null) {
            myModulesImports = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryModuleImport>>() {
                        @Override
                        public Result<Collection<XQueryModuleImport>> compute() {
                            return CachedValueProvider.Result.create(calcModuleImports(), XQueryFile.this);
                        }
                    }, false);
        }
        return myModulesImports.getValue();
    }

    @NotNull
    public Collection<XQueryNamespaceDecl> getNamespaceDeclarations() {
        if (myNamespaceDeclarations == null) {
            myNamespaceDeclarations = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryNamespaceDecl>>() {
                        @Override
                        public Result<Collection<XQueryNamespaceDecl>> compute() {
                            return CachedValueProvider.Result.create(calcNamespaceDeclarations(), XQueryFile.this);
                        }
                    }, false);
        }
        return myNamespaceDeclarations.getValue();
    }

    @NotNull
    public Collection<XQueryFunctionDecl> getFunctionDeclarations() {
        if (myFunctions == null) {
            myFunctions = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryFunctionDecl>>() {
                        @Override
                        public Result<Collection<XQueryFunctionDecl>> compute() {
                            return CachedValueProvider.Result.create(calcFunctionDeclarations(), XQueryFile.this);
                        }
                    }, false);
        }
        return myFunctions.getValue();
    }

    @NotNull
    public Collection<XQueryNamespaceDecl> getNamespaceDeclarationsMatchingDefaultNamespace() {
        if (myNamespacesMatchingDefault == null) {
            myNamespacesMatchingDefault = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Collection<XQueryNamespaceDecl>>() {
                        @Override
                        public Result<Collection<XQueryNamespaceDecl>> compute() {
                            return CachedValueProvider.Result.create
                                    (calcNamespaceDeclarationsMatchingDefaultNamespace(), XQueryFile.this);
                        }
                    }, false);
        }
        return myNamespacesMatchingDefault.getValue();
    }

    @NotNull
    public String getDefaultFunctionNamespace() {
        if (myDefaultFunctionNamespace == null) {
            myDefaultFunctionNamespace = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<String>() {
                        @Override
                        public Result<String> compute() {
                            return CachedValueProvider.Result.create
                                    (calcDefaultFunctionNamespace(), XQueryFile.this);
                        }
                    }, false);
        }
        return myDefaultFunctionNamespace.getValue();
    }

    @Nullable
    public XQueryModuleDecl getModuleDeclaration() {
        if (myModuleDeclaration == null) {
            myModuleDeclaration = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<XQueryModuleDecl>() {
                        @Override
                        public Result<XQueryModuleDecl> compute() {
                            return CachedValueProvider.Result.create
                                    (calcModuleDeclaration(), XQueryFile.this);
                        }
                    }, false);
        }
        return myModuleDeclaration.getValue();
    }

    private Collection<XQueryVarDecl> calcVariableDeclarations() {
        Collection<XQueryVarDecl> variableDeclarations = PsiTreeUtil.findChildrenOfType(this, XQueryVarDecl.class);
        return variableDeclarations;
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

    private Collection<XQueryNamespaceDecl> calcNamespaceDeclarationsMatchingDefaultNamespace() {
        Collection<XQueryNamespaceDecl> all = getNamespaceDeclarations();
        return findAll(all, new Condition<XQueryNamespaceDecl>() {
            @Override
            public boolean value(XQueryNamespaceDecl declaration) {
                return declaration.getNamespaceName() != null && declaration.getURILiteral() != null &&
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
        return FN.getNamespace();
    }

    public XQueryNamespaceName getModuleNamespaceName() {
        XQueryModuleDecl moduleDecl = getModuleDeclaration();
        return moduleDecl != null ? moduleDecl.getNamespaceName() : null;
    }

    public Collection<XQueryFile> getImportedFilesThatExist(Condition<XQueryModuleImport> condition) {
        Collection<XQueryFile> result = new LinkedList<XQueryFile>();
        for (XQueryModuleImport moduleImport : findAll(getModuleImports(), condition)) {
            result.addAll(getReferencesToExistingFilesInImport(moduleImport));
        }
        return result;
    }

    private XQueryDefaultFunctionNamespaceDecl getDefaultNamespaceFunctionDeclaration() {
        return PsiTreeUtil.findChildOfType(this, XQueryDefaultFunctionNamespaceDecl.class);
    }

    public String mapPrefixToNamespace(String prefix) {
        return getNamespaceMapping().get(prefix);
    }

    private Map<String, String> getNamespaceMapping() {
        if (myNamespaceMapping == null) {
            myNamespaceMapping = CachedValuesManager
                    .getManager(getProject())
                    .createCachedValue(new CachedValueProvider<Map<String, String>>() {
                        @Override
                        public Result<Map<String, String>> compute() {
                            return CachedValueProvider.Result.create(calcNamespaceMapping(), XQueryFile.this);
                        }
                    }, false);
        }
        return myNamespaceMapping.getValue();
    }

    private Map<String, String> calcNamespaceMapping() {
        XQueryNamespaceName moduleNamespaceName = getModuleNamespaceName();
        Collection<XQueryNamespaceDecl> namespaceDeclarations = getNamespaceDeclarations();
        Collection<XQueryModuleImport> moduleImports = getModuleImports();
        Map<String, String> namespaceMapping = new HashMap<String, String>(getMappingFromPrefix());
        namespaceMapping.put(null, getDefaultFunctionNamespace());
        if (moduleNamespaceName != null && getModuleDeclaration().getURILiteral() != null) {
            namespaceMapping.put(moduleNamespaceName.getName(),
                    removeQuotOrApos(getModuleDeclaration().getURILiteral().getText()));
        }
        if (namespaceDeclarations != null) {
            for (XQueryNamespaceDecl namespaceDeclaration : namespaceDeclarations) {
                if (namespaceDeclaration.getNamespaceName() != null && namespaceDeclaration.getURILiteral() != null) {
                    namespaceMapping.put(namespaceDeclaration.getNamespaceName().getText(),
                            removeQuotOrApos(namespaceDeclaration.getURILiteral().getText()));
                }
            }
        }
        if (moduleImports != null) {
            for (XQueryModuleImport moduleImport : moduleImports) {
                if (moduleImport.getNamespaceName() != null && moduleImport.getModuleImportNamespace() != null) {
                    namespaceMapping.put(moduleImport.getNamespaceName().getText(),
                            removeQuotOrApos(moduleImport.getModuleImportNamespace().getText()));
                }
            }
        }

        return namespaceMapping;
    }
}