/*
 * Copyright 2013 Grzegorz Ligas
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

import java.util.HashMap;
import java.util.Map;

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
        Map<String, ResolveResult> functionDeclarationResults = getFunctionDeclarationReferences(file,
                new HashMap<String, ResolveResult>(), checkedNamespace);

        Map<String, ResolveResult> externalFunctionDeclarationResults = getExternalFunctionDeclarationReferences
                (file, functionDeclarationResults);

        return externalFunctionDeclarationResults.values().toArray(new
                ResolveResult[externalFunctionDeclarationResults.size()]);
    }


    private Map<String, ResolveResult> getExternalFunctionDeclarationReferences(XQueryFile file, Map<String,
            ResolveResult> results) {
        final String referenceNamespace = myElement.getFunctionName().getFunctionNamespace() != null ? myElement
                .getFunctionName().getFunctionNamespace().getText() : null;

        if (referenceNamespace != null) {
            addReferencesFromImportedModules(file, results, referenceNamespace);
        }
        return results;
    }

    private void addReferencesFromImportedModules(XQueryFile file, Map<String, ResolveResult> results,
                                                  String referenceNamespace) {
        for (XQueryModuleImport moduleImport : file.getModuleImports()) {
            if (referenceNamespace.equals(moduleImport.getNamespaceName().getText())) {
                addReferencesFromAllFilesInImport(moduleImport, results);
            }
        }
    }

    private void addReferencesFromAllFilesInImport(XQueryModuleImport moduleImport, Map<String,
            ResolveResult> results) {
        for (XQueryModuleImportPath path : moduleImport.getModuleImportPathList()) {
            if (path.getReference() != null) {
                XQueryFile xQueryFile = (XQueryFile) path.getReference().resolve();
                if (xQueryFile != null && xQueryFile.getModuleNamespaceName() != null) {
                    getFunctionDeclarationReferences(xQueryFile, results, xQueryFile.getModuleNamespaceName().getText
                            ());
                }
            }
        }
    }

    private Map<String, ResolveResult> getFunctionDeclarationReferences(XQueryFile file, Map<String,
            ResolveResult> results, String checkedNamespace) {
        for (XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                addReferenceIfNotAlreadyAdded(results, functionDecl, checkedNamespace);
            }
        }
        return results;
    }

    private boolean functionNameExists(XQueryFunctionDecl functionDecl) {
        return functionDecl.getFunctionName() != null && functionDecl.getFunctionName().getTextLength() > 0;
    }

    private void addReferenceIfNotAlreadyAdded(Map<String, ResolveResult> results, XQueryFunctionDecl functionDecl,
                                               String checkedNamespace) {
        String key = functionDecl.getFunctionName().getText();
        if (!results.containsKey(key)) {
            addElementToResultsIfMatching(results, functionDecl.getFunctionName(), functionDecl.getFunctionName(),
                    key, checkedNamespace);
        }
    }

    private void addElementToResultsIfMatching(Map<String, ResolveResult> results, PsiElement referenceTarget,
                                               XQueryFunctionName compareFunctionName, String key,
                                               String checkedNamespace) {
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
            results.put(key, new PsiElementResolveResult(referenceTarget));
        } else if (namespacesAreEmptyAndLocalNamesMatch) {
            results.put(key, new PsiElementResolveResult(referenceTarget));
        }
    }

}
