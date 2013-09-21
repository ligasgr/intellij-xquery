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

package org.intellij.xquery.reference.function;

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.ResolveResult;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;
import static org.intellij.xquery.util.StringUtils.removeQuotOrApos;

/**
 * User: ligasgr
 * Date: 07/08/13
 * Time: 15:12
 */
public class XQueryFunctionReferenceResolver {

    private XQueryFunctionInvocation myElement;
    private String checkedNamespacePrefix;
    private List<XQueryFunctionName> matchingFunctionNames;

    public XQueryFunctionReferenceResolver(XQueryFunctionInvocation myElement) {
        if (myElement.getFunctionName().getFunctionNamespace() != null)
            this.checkedNamespacePrefix = myElement.getFunctionName().getFunctionNamespace().getText();
        this.myElement = myElement;
    }

    public ResolveResult[] getResolutionResults() {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        matchingFunctionNames = new ArrayList<XQueryFunctionName>();
        addFunctionDeclarationReferencesFromFile(file, checkedNamespacePrefix);
        addFunctionNameReferencesFromImportedFiles(file);

        return convertToResolveResults(filterByArity(myElement.getArity(), matchingFunctionNames));
    }

    private List<XQueryFunctionName> filterByArity(int arity, List<XQueryFunctionName> matchingFunctionNames) {
        List<XQueryFunctionName> matchingArityFunctionNames = new ArrayList<XQueryFunctionName>();
        for (XQueryFunctionName functionName : matchingFunctionNames) {
            XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
            if (functionDeclaration.getArity() == arity) {
                matchingArityFunctionNames.add(functionName);
            }
        }
        if (matchingArityFunctionNames.size() > 0)
            return matchingArityFunctionNames;
        else
            return matchingFunctionNames;
    }

    private void addFunctionDeclarationReferencesFromFile(XQueryFile file, String checkedNamespacePrefix) {
        for (XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            addFunctionAsTargetIfMatches(functionDecl, checkedNamespacePrefix);
        }
    }

    private void addFunctionAsTargetIfMatches(XQueryFunctionDecl functionDecl, String checkedNamespacePrefix) {
        if (functionDeclarationWithValidName(functionDecl)) {
            XQueryQName<XQueryFunctionName> source = aXQueryQName(myElement.getFunctionName())
                    .withPrefix(checkedNamespacePrefix)
                    .build();
            XQueryQName<XQueryFunctionName> checkedQName = aXQueryQName(functionDecl.getFunctionName())
                    .build();
            if (source.equals(checkedQName)) {
                matchingFunctionNames.add(checkedQName.getNamedObject());
            }
        }
    }

    private boolean functionDeclarationWithValidName(XQueryFunctionDecl functionDecl) {
        return functionDecl.getFunctionName() != null && functionDecl.getFunctionName().getTextLength() > 0;
    }

    private void addFunctionNameReferencesFromImportedFiles(XQueryFile file) {
        for (XQueryFile importedFile : getFilesFromImportWithMatchingNamespace(file)) {
            String checkedPrefix = null;
            if (functionHasNamespacePrefix()) {
                checkedPrefix = importedFile.getModuleNamespaceName().getText();
            }
            addFunctionDeclarationReferencesFromFile(importedFile, checkedPrefix);
        }
    }

    private boolean functionHasNamespacePrefix() {
        return myElement.getFunctionName().getFunctionNamespace() != null;
    }

    private Collection<XQueryFile> getFilesFromImportWithMatchingNamespace(final XQueryFile file) {
        return file.getImportedFilesThatExist(new Condition<XQueryModuleImport>() {
            @Override
            public boolean value(XQueryModuleImport moduleImport) {
                if (moduleImport.getNamespaceName() != null && myElement.getFunctionName().getFunctionNamespace() != null) {
                    String namespacePrefix = myElement.getFunctionName().getFunctionNamespace().getText();
                    return moduleImport.getNamespaceName().getText().equals(namespacePrefix);
                } else if (moduleImport.getModuleImportNamespace() != null) {
                    String namespacePrefix = null;
                    if (myElement.getFunctionName().getFunctionNamespace() != null) {
                        namespacePrefix = myElement.getFunctionName().getFunctionNamespace().getText();
                    }
                    String namespace = file.mapPrefixToNamespace(namespacePrefix);
                    return removeQuotOrApos(moduleImport.getModuleImportNamespace().getText()).equals(namespace);
                } else {
                    return false;
                }
            }
        });
    }

    private ResolveResult[] convertToResolveResults(List<XQueryFunctionName> resolveResults) {
        ResolveResult[] convertedResults = new ResolveResult[resolveResults.size()];
        for (int i = 0; i < resolveResults.size(); i++) {
            convertedResults[i] = new PsiElementResolveResult(resolveResults.get(i));
        }
        return convertedResults;
    }
}
