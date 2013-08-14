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

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.*;

import java.util.LinkedList;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;

/**
 * User: ligasgr
 * Date: 07/08/13
 * Time: 15:08
 */
public class XQueryFunctionReferenceForAutoCompletionCollector {

    private XQueryFunctionCall sourceOfReference;
    private List<XQueryQName> proposedReferences;

    public XQueryFunctionReferenceForAutoCompletionCollector(XQueryFunctionCall sourceOfReference) {
        this.sourceOfReference = sourceOfReference;
    }

    public Object[] getReferencesForAutoCompletion() {
        XQueryFile file = (XQueryFile) sourceOfReference.getContainingFile();
        proposedReferences = new LinkedList<XQueryQName>();
        addProposedReferencesFromFile(file);
        addReferencesFromModuleImports(file);
        return convertToLookupElements(proposedReferences);
    }

    private void addProposedReferencesFromFile(XQueryFile file) {
        for (XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                proposedReferences.add(aXQueryQName(functionDecl.getFunctionName()).build());
            }
        }
    }

    private void addReferencesFromModuleImports(XQueryFile file) {
        for (XQueryModuleImport moduleImport : file.getModuleImports()) {
            if (moduleImport.getNamespaceName() != null) {
                String targetPrefix = moduleImport.getNamespaceName().getName();
                addVariantsFromImportPaths(targetPrefix, moduleImport);
            }
        }
    }

    private void addVariantsFromImportPaths(String targetPrefix, XQueryModuleImport moduleImport) {
        for (XQueryModuleImportPath moduleImportPath : moduleImport.getModuleImportPathList()) {
            XQueryFile file = (XQueryFile) moduleImportPath.getReference().resolve();
            if (file != null) {
                addVariantsFromReferencedFile(targetPrefix, file);
            }
        }
    }

    private void addVariantsFromReferencedFile(String targetPrefix, XQueryFile file) {
        for (final XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                proposedReferences.add(aXQueryQName(functionDecl.getFunctionName()).withPrefix(targetPrefix).build());
            }
        }
    }

    private boolean functionNameExists(XQueryFunctionDecl functionDecl) {
        return functionDecl.getFunctionName() != null && functionDecl.getFunctionName().getTextLength() > 0;
    }

    private LookupElement[] convertToLookupElements(List<XQueryQName> proposedReferences) {
        LookupElement[] lookupElements = new LookupElement[proposedReferences.size()];
        for (int i = 0; i < proposedReferences.size(); i++) {
            XQueryQName qName = proposedReferences.get(i);
            XQueryFunctionName functionName = (XQueryFunctionName) qName.getNamedObject();
            XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
            lookupElements[i] = createLookupElement(functionDeclaration, qName.getTextRepresentation());
        }
        return lookupElements;
    }

    private LookupElement createLookupElement(XQueryFunctionDecl functionDeclaration, String key) {
        String tailText = " (" + (functionDeclaration.getParamList() != null ? functionDeclaration.getParamList()
                .getText() : "") + ")";
        String typeText = functionDeclaration.getSequenceType() != null ? functionDeclaration.getSequenceType()
                .getText() : "item()*";
        return LookupElementBuilder.create(functionDeclaration, key)
                .withIcon(AllIcons.Nodes.Method)
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
