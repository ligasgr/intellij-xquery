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

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import org.intellij.xquery.icons.XQueryIcons;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.*;
import org.intellij.xquery.psi.impl.XQueryPsiImplUtil;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;
import static org.intellij.xquery.psi.XQueryUtil.getReferencesToExistingFilesInImport;

/**
 * User: ligasgr
 * Date: 07/08/13
 * Time: 15:08
 */
public class XQueryFunctionReferenceForAutoCompletionCollector {

    private XQueryFunctionInvocation sourceOfReference;
    private List<XQueryQName<XQueryFunctionName>> proposedReferences;

    public XQueryFunctionReferenceForAutoCompletionCollector(XQueryFunctionInvocation sourceOfReference) {
        this.sourceOfReference = sourceOfReference;
    }

    public Object[] getReferencesForAutoCompletion() {
        XQueryFile file = (XQueryFile) sourceOfReference.getContainingFile();
        proposedReferences = new LinkedList<XQueryQName<XQueryFunctionName>>();
        addProposedReferencesFromFile(file);
        addProposedReferencesFromModuleImports(file);
        return convertToLookupElements(proposedReferences);
    }

    private void addProposedReferencesFromFile(XQueryFile file) {
        for (XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                XQueryQName<XQueryFunctionName> basicQName = aXQueryQName(functionDecl.getFunctionName()).build();
                boolean isInDefaultNamespace = file.getDefaultFunctionNamespace().equals(basicQName.getNamespace());
                XQueryFunctionName functionName = functionDecl.getFunctionName();
                if (functionName.getFunctionNamespace() != null && isInDefaultNamespace) {
                    proposedReferences.add(aXQueryQName(functionDecl.getFunctionName()).withPrefix(null).build());
                } else if (isInDefaultNamespace) {
                    Collection<XQueryNamespaceDecl> matchingNamespaceDeclarations = file
                            .getNamespaceDeclarationsMatchingDefaultNamespace();
                    for (XQueryNamespaceDecl namespaceDeclaration : matchingNamespaceDeclarations) {
                        proposedReferences.add(aXQueryQName(functionDecl.getFunctionName()).withPrefix
                                (namespaceDeclaration.getNamespaceName().getText()).build());
                    }
                }
                proposedReferences.add(basicQName);
            }
        }
    }

    private void addProposedReferencesFromModuleImports(XQueryFile file) {
        for (XQueryModuleImport moduleImport : file.getModuleImports()) {
            String targetPrefix = null;
            if (moduleImport.getNamespaceName() != null) {
                targetPrefix = moduleImport.getNamespaceName().getName();
            }
            addProposedReferencesFromImport(targetPrefix, moduleImport);
        }
    }

    private void addProposedReferencesFromImport(String targetPrefix, XQueryModuleImport moduleImport) {
        for (XQueryFile file : getReferencesToExistingFilesInImport(moduleImport)) {
            addProposedReferencesFromImportedFile(targetPrefix, file, (XQueryFile) moduleImport.getContainingFile());
        }
    }

    private void addProposedReferencesFromImportedFile(String targetPrefix, XQueryFile importedFile, XQueryFile file) {
        for (final XQueryFunctionDecl functionDecl : importedFile.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl) && XQueryPsiImplUtil.functionIsPublic(functionDecl)) {
                XQueryQName<XQueryFunctionName> basicQName = aXQueryQName(functionDecl.getFunctionName()).withPrefix
                        (targetPrefix).build();
                boolean isInDefaultNamespace = file.getDefaultFunctionNamespace().equals(basicQName.getNamespace());
                if (targetPrefix != null && isInDefaultNamespace) {
                    proposedReferences.add(aXQueryQName(functionDecl.getFunctionName()).withPrefix(null).build());
                } else if (isInDefaultNamespace) {
                    Collection<XQueryNamespaceDecl> matchingNamespaceDeclarations = file
                            .getNamespaceDeclarationsMatchingDefaultNamespace();
                    for (XQueryNamespaceDecl namespaceDeclaration : matchingNamespaceDeclarations) {
                        proposedReferences.add(aXQueryQName(functionDecl.getFunctionName()).withPrefix
                                (namespaceDeclaration.getNamespaceName().getText()).build());
                    }
                }
                proposedReferences.add(basicQName);
            }
        }
    }

    private boolean functionNameExists(XQueryFunctionDecl functionDecl) {
        return functionDecl.getFunctionName() != null && functionDecl.getFunctionName().getTextLength() > 0;
    }

    private LookupElement[] convertToLookupElements(List<XQueryQName<XQueryFunctionName>> proposedReferences) {
        LookupElement[] lookupElements = new LookupElement[proposedReferences.size()];
        for (int i = 0; i < proposedReferences.size(); i++) {
            lookupElements[i] = convertToLookupElement(proposedReferences.get(i));
        }
        return lookupElements;
    }

    private LookupElement convertToLookupElement(XQueryQName<XQueryFunctionName> qName) {
        XQueryFunctionName functionName = qName.getNamedObject();
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
        return createLookupElement(functionDeclaration, qName.getTextRepresentation());
    }

    private LookupElement createLookupElement(XQueryFunctionDecl functionDeclaration, String key) {
        String tailText = functionDeclaration.getParamList() != null ? functionDeclaration.getParamList().getText() :
                "";
        String typeText = functionDeclaration.getSequenceType() != null ? functionDeclaration.getSequenceType()
                .getText() : "item()*";
        return LookupElementBuilder.create(functionDeclaration, key)
                .withIcon(XQueryIcons.FUNCTION_ICON)
                .withTailText(tailText, true)
                .withTypeText(typeText)
                .withInsertHandler(new ParenthesesInsertHandler<LookupElement>() {
                    @Override
                    protected boolean placeCaretInsideParentheses(InsertionContext context, LookupElement item) {
                        return true;
                    }
                });
    }
}
