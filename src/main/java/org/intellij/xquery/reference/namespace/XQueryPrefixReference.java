/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.reference.namespace;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveResult;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.intellij.xquery.psi.XQueryNamespaceDecl;
import org.intellij.xquery.psi.XQueryNamespacePrefix;
import org.intellij.xquery.psi.XQueryPsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public abstract class XQueryPrefixReference<T extends XQueryPsiElement> extends PsiReferenceBase<T> implements
        PsiPolyVariantReference {
    public XQueryPrefixReference(T element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        Collection<ResolveResult> primaryReferences = getPrimaryReferences();
        results.addAll(primaryReferences);
        if (shouldAddOtherReferences(primaryReferences)) {
            results.addAll(getReferencesFromNamespaceDeclarations(file.getNamespaceDeclarations()));
            results.addAll(getReferencesFromModuleImports(file.getModuleImports()));
            results.addAll(getReferencesFromModuleName(file.getModuleNamespaceName()));
        }
        if (results.isEmpty() && file.isPredeclaredNamespacePrefix(myElement.getText())) {
            results.add(new PsiElementResolveResult(getElement()));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    protected boolean shouldAddOtherReferences(Collection<ResolveResult> primaryReferences) {
        return !includeOnlyPrimaryReferencesIfPresent() || (includeOnlyPrimaryReferencesIfPresent() && primaryReferences.isEmpty());
    }

    protected boolean includeOnlyPrimaryReferencesIfPresent() {
        return false;
    }

    protected Collection<ResolveResult> getPrimaryReferences() {
        return Collections.emptyList();
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

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }
}
