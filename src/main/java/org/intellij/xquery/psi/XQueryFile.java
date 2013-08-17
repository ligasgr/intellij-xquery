/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com>
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
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.XQueryFileType;
import org.intellij.xquery.XQueryLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.intellij.util.containers.ContainerUtil.findAll;
import static org.intellij.xquery.psi.XQueryUtil.getReferencesToExistingFilesInImport;
import static org.intellij.xquery.psi.XQueryUtil.removeQuotOrApos;
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

    public Collection<XQueryVarDecl> getVariableDeclarations() {
        Collection<XQueryVarDecl> variableDeclarations = PsiTreeUtil.findChildrenOfType(this, XQueryVarDecl.class);
        return variableDeclarations;
    }

    public Collection<XQueryModuleImport> getModuleImports() {
        Collection<XQueryModuleImport> moduleImports = PsiTreeUtil.findChildrenOfType(this, XQueryModuleImport.class);
        return moduleImports;
    }

    public XQueryNamespaceName getModuleNamespaceName() {
        XQueryModuleDecl moduleDecl = getModuleDeclaration();
        return moduleDecl != null ? moduleDecl.getNamespaceName() : null;
    }

    private XQueryModuleDecl getModuleDeclaration() {
        return PsiTreeUtil.findChildOfType(this, XQueryModuleDecl.class);
    }

    public Collection<XQueryNamespaceDecl> getNamespaceDeclarations() {
        Collection<XQueryNamespaceDecl> namespaceDeclarations = PsiTreeUtil.findChildrenOfType(this,
                XQueryNamespaceDecl.class);
        return namespaceDeclarations;
    }

    public Collection<XQueryFunctionDecl> getFunctionDeclarations() {
        Collection<XQueryFunctionDecl> functionDeclarations = PsiTreeUtil.findChildrenOfType(this,
                XQueryFunctionDecl.class);
        return functionDeclarations;
    }

    public Collection<XQueryFile> getImportedFilesThatExist(Condition<XQueryModuleImport> condition) {
        Collection<XQueryFile> result = new LinkedList<XQueryFile>();
        for (XQueryModuleImport moduleImport : findAll(getModuleImports(), condition)) {
            result.addAll(getReferencesToExistingFilesInImport(moduleImport));
        }
        return result;
    }

    public String getDefaultFunctionNamespace() {
        XQueryDefaultFunctionNamespaceDecl defaultFunctionNamespaceDecl = getDefaultNamespaceFunctionDeclaration();
        if (defaultFunctionNamespaceDecl != null && defaultFunctionNamespaceDecl.getURILiteral() != null)
            return removeQuotOrApos(defaultFunctionNamespaceDecl.getURILiteral().getText());
        return FN.getNamespace();
    }

    private XQueryDefaultFunctionNamespaceDecl getDefaultNamespaceFunctionDeclaration() {
        return PsiTreeUtil.findChildOfType(this, XQueryDefaultFunctionNamespaceDecl.class);
    }

    public String mapPrefixToNamespace(String prefix) {
        return getNamespaceMapping().get(prefix);
    }

    private Map<String, String> getNamespaceMapping() {
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

    public Collection<XQueryNamespaceDecl> getNamespaceDeclarationsMatchingDefaultNamespace() {
        Collection<XQueryNamespaceDecl> all = getNamespaceDeclarations();
        return findAll(all, new Condition<XQueryNamespaceDecl>() {
            @Override
            public boolean value(XQueryNamespaceDecl declaration) {
                return declaration.getNamespaceName() != null && declaration.getURILiteral() != null &&
                        getDefaultFunctionNamespace().equals(removeQuotOrApos(declaration.getURILiteral().getText()));
            }
        });
    }
}