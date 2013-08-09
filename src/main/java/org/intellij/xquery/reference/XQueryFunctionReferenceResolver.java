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

package org.intellij.xquery.reference;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.ResolveResult;
import org.intellij.xquery.psi.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ligasgr
 * Date: 07/08/13
 * Time: 15:12
 */
public class XQueryFunctionReferenceResolver {

    private XQueryFunctionCall myElement;
    private final String checkedNamespace;

    public XQueryFunctionReferenceResolver(String checkedNamespace, XQueryFunctionCall myElement) {
        this.checkedNamespace = checkedNamespace;
        this.myElement = myElement;
    }

    public ResolveResult[] multiResolve(boolean incompleteCode) {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        List<ResolveResult> resolveResults = new ArrayList<ResolveResult>();
        resolveResults.addAll(getFunctionDeclarationReferences(checkedNamespace, file));
        resolveResults.addAll(getExternalFunctionDeclarationReferences(file));
        return resolveResults.toArray(new ResolveResult[resolveResults.size()]);
    }


    private List<ResolveResult> getExternalFunctionDeclarationReferences(XQueryFile file) {
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        final String referenceNamespace = myElement.getFunctionName().getFunctionNamespace() != null ? myElement
                .getFunctionName().getFunctionNamespace().getText() : null;

        if (referenceNamespace != null) {
            results.addAll(getReferencesFromModuleImports(file, referenceNamespace));
        }
        return results;
    }

    private List<ResolveResult> getReferencesFromModuleImports(XQueryFile file, String referenceNamespace) {
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (XQueryModuleImport moduleImport : file.getModuleImports()) {
            if (referenceNamespace.equals(moduleImport.getNamespaceName().getText())) {
                results.addAll(getReferencesFromAllFilesInImport(moduleImport));
            }
        }
        return results;
    }

    private List<ResolveResult> getReferencesFromAllFilesInImport(XQueryModuleImport moduleImport) {
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (XQueryModuleImportPath path : moduleImport.getModuleImportPathList()) {
            if (path.getReference() != null) {
                XQueryFile xQueryFile = (XQueryFile) path.getReference().resolve();
                if (xQueryFile != null && xQueryFile.getModuleNamespaceName() != null) {
                    results.addAll(getFunctionDeclarationReferences(xQueryFile.getModuleNamespaceName().getText
                            (), xQueryFile));
                }
            }
        }
        return results;
    }

    private List<ResolveResult> getFunctionDeclarationReferences(String checkedNamespace, XQueryFile file) {
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                results.addAll(getMatchingElements(functionDecl.getFunctionName(), functionDecl.getFunctionName(),
                        checkedNamespace));
            }
        }
        return results;
    }

    private boolean functionNameExists(XQueryFunctionDecl functionDecl) {
        return functionDecl.getFunctionName() != null && functionDecl.getFunctionName().getTextLength() > 0;
    }

    private List<ResolveResult> getMatchingElements(PsiElement referenceTarget,
                                                    XQueryFunctionName compareFunctionName,
                                                    String checkedNamespace) {
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        final String functionDeclNamespace = compareFunctionName.getFunctionNamespace() != null ? compareFunctionName
                .getFunctionNamespace().getText() : null;
        final String referenceNamespace = checkedNamespace;
        final String functionDeclLocalName = compareFunctionName.getFunctionLocalName().getText();
        final String referenceLocalName = myElement.getFunctionName().getFunctionLocalName().getText();
        boolean namespacesAndLocalNamesMatch = referenceNamespace != null && referenceNamespace.equals
                (functionDeclNamespace) && referenceLocalName.equals(functionDeclLocalName);
        boolean namespacesAreEmptyAndLocalNamesMatch = referenceNamespace == null && functionDeclNamespace == null &&
                functionDeclLocalName.equals(referenceLocalName);

        if (namespacesAndLocalNamesMatch) {
            results.add(new PsiElementResolveResult(referenceTarget));
        } else if (namespacesAreEmptyAndLocalNamesMatch) {
            results.add(new PsiElementResolveResult(referenceTarget));
        }
        return results;
    }

}
