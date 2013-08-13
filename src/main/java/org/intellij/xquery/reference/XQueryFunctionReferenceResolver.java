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

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.ResolveResult;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;

/**
 * User: ligasgr
 * Date: 07/08/13
 * Time: 15:12
 */
public class XQueryFunctionReferenceResolver {

    private XQueryFunctionCall myElement;
    private final String checkedNamespace;
    private List<XQueryFunctionName> matchingFunctionNames;

    public XQueryFunctionReferenceResolver(String checkedNamespace, XQueryFunctionCall myElement) {
        this.checkedNamespace = checkedNamespace;
        this.myElement = myElement;
    }

    public ResolveResult[] getResolutionResults() {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        matchingFunctionNames = new ArrayList<XQueryFunctionName>();
        addFunctionDeclarationReferencesFromFile(file, checkedNamespace);
        addFunctionNameReferencesFromImportedFiles(file);
        return convertToResolveResults(matchingFunctionNames);
    }

    private void addFunctionDeclarationReferencesFromFile(XQueryFile file, String checkedNamespace) {
        for (XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            addFunctionAsTargetIfMatches(functionDecl, checkedNamespace);
        }
    }

    private void addFunctionAsTargetIfMatches(XQueryFunctionDecl functionDecl, String checkedNamespace) {
        if (functionDeclarationWithValidName(functionDecl)) {
            XQueryQName<XQueryFunctionName> source = aXQueryQName(myElement.getFunctionName())
                    .withPrefix(checkedNamespace)
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
        if (functionHasNamespacePrefix()) {
            for (XQueryFile importedFile : getFilesFromImportWithMatchingNamespacePrefix(file)) {
                addFunctionDeclarationReferencesFromFile(importedFile, importedFile.getModuleNamespaceName().getText());
            }
        }
    }

    private boolean functionHasNamespacePrefix() {
        return myElement.getFunctionName().getFunctionNamespace() != null;
    }

    private Collection<XQueryFile> getFilesFromImportWithMatchingNamespacePrefix(XQueryFile file) {
        return file.getImportedFilesThatExist(file, new
                Condition<XQueryModuleImport>() {
                    @Override
                    public boolean value(XQueryModuleImport moduleImport) {
                        String namespacePrefix = myElement.getFunctionName().getFunctionNamespace().getText();
                        return namespacePrefix.equals(moduleImport.getNamespaceName().getText());
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
