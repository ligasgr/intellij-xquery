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

import java.util.*;

import static java.util.Collections.emptyList;

/**
 * User: ligasgr
 * Date: 03/07/13
 * Time: 13:11
 */
public class XQueryFunctionNamespaceNameReference extends PsiReferenceBase<XQueryFunctionNamespace> implements
        PsiPolyVariantReference {
    public XQueryFunctionNamespaceNameReference(XQueryFunctionNamespace element, TextRange textRange) {
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
        return results.toArray(new ResolveResult[results.size()]);
    }

    private Collection<ResolveResult> getReferencesFromModuleName(XQueryNamespaceName moduleNamespaceName) {
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
                if (myElement.getText().equals(moduleImport.getNamespaceName().getText())) {
                    results.add(new PsiElementResolveResult(moduleImport.getNamespaceName()));
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
                if (myElement.getText().equals(namespaceDeclaration.getNamespaceName().getText())) {
                    results.add(new PsiElementResolveResult(namespaceDeclaration.getNamespaceName()));
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
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        myElement.replace(getUpdatedRef(newElementName).getFunctionNamespace());
        return myElement;
    }

    private XQueryFunctionName getUpdatedRef(String newName) {
        XQueryFunctionName functionName = XQueryElementFactory.createFunctionReference(myElement.getProject(),
                newName, "dummy");
        return functionName;
    }
}
