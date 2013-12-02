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

package org.intellij.xquery.reference.namespace;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * User: ligasgr
 * Date: 03/07/13
 * Time: 13:11
 */
public class XQueryNamespacePrefixReference extends PsiReferenceBase<XQueryPrefix> implements
        PsiPolyVariantReference {
    public XQueryNamespacePrefixReference(XQueryPrefix element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        results.addAll(getReferencesFromNamespaceDeclarations(file.getNamespaceDeclarations()));
        results.addAll(getReferencesFromModuleImports(file.getModuleImports()));
        results.addAll(getReferencesFromModuleName(file.getModuleNamespaceName()));
        if (results.size() == 0 && isPredeclaredNamespace(myElement.getText())) {
            results.add(new PsiElementResolveResult(getElement()));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    private Collection<ResolveResult> getReferencesFromModuleName(XQueryNamespacePrefix moduleNamespaceName) {
        if (moduleNamespaceName != null && myElement.getText().equals(moduleNamespaceName.getText())) {
            return Arrays.asList(new ResolveResult[]{new PsiElementResolveResult(moduleNamespaceName)});
        } else {
            return emptyList();
        }
    }

    private Collection<ResolveResult> getReferencesFromModuleImports(Collection<XQueryModuleImport> moduleImports) {
        if (moduleImports.size() > 0) {
            List<ResolveResult> results = new ArrayList<ResolveResult>();
            for (XQueryModuleImport moduleImport : moduleImports) {
                XQueryNamespacePrefix namespaceName = moduleImport.getNamespacePrefix();
                if (namespaceName != null && myElement.getText().equals(namespaceName.getText())) {
                    results.add(new PsiElementResolveResult(moduleImport.getNamespacePrefix()));
                }
            }
            return results;
        } else {
            return emptyList();
        }
    }

    private Collection<ResolveResult> getReferencesFromNamespaceDeclarations(Collection<XQueryNamespaceDecl>
                                                                                     namespaceDeclarations) {
        if (namespaceDeclarations.size() > 0) {
            List<ResolveResult> results = new ArrayList<ResolveResult>();
            for (XQueryNamespaceDecl namespaceDeclaration : namespaceDeclarations) {
                if (myElement.getText().equals(namespaceDeclaration.getNamespacePrefix().getText())) {
                    results.add(new PsiElementResolveResult(namespaceDeclaration.getNamespacePrefix()));
                }
            }
            return results;
        } else {
            return emptyList();
        }
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        PsiElement result = resolveResults.length == 1 ? resolveResults[0].getElement() : null;
        return result;
    }

    private boolean isPredeclaredNamespace(String namespacePrefix) {
        return XQueryPredeclaredNamespace.getMappingFromPrefix().keySet().contains(namespacePrefix);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        myElement.replace(getUpdatedRef(newElementName).getPrefix());
        return myElement;
    }

    private XQueryFunctionName getUpdatedRef(String newName) {
        XQueryFunctionName functionName = XQueryElementFactory.createFunctionReference(myElement.getProject(),
                newName, "dummy");
        return functionName;
    }


}
