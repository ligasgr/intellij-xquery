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
import org.intellij.xquery.icons.XQueryIcons;
import org.intellij.xquery.psi.*;

import java.util.LinkedList;
import java.util.List;

/**
 * User: ligasgr
 * Date: 07/08/13
 * Time: 15:08
 */
public class XQueryFunctionReferenceVariantsCollector {

    private XQueryFunctionCall myElement;

    public XQueryFunctionReferenceVariantsCollector(XQueryFunctionCall myElement) {
        this.myElement = myElement;
    }

    public Object[] getVariants() {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        List<LookupElement> variants = new LinkedList<LookupElement>();
        variants.addAll(getVariantsFromThisFile(file));
        variants.addAll(getVariantsFromModuleImports(file));
        return variants.toArray();
    }

    private List<LookupElement> getVariantsFromThisFile(XQueryFile file) {
        List<LookupElement> variants = new LinkedList<LookupElement>();
        for (final XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                variants.add(createLookupElement(functionDecl, functionDecl.getFunctionName().getText()));
            }
        }
        return variants;
    }

    private List<LookupElement> getVariantsFromModuleImports(XQueryFile file) {
        List<LookupElement> variants = new LinkedList<LookupElement>();
        for (XQueryModuleImport moduleImport : file.getModuleImports()) {
            if (moduleImport.getNamespaceName() != null) {
                String targetPrefix = moduleImport.getNamespaceName().getName();
                variants.addAll(getVariantsFromImportPaths(targetPrefix, moduleImport));
            }
        }
        return variants;
    }

    private List<LookupElement> getVariantsFromImportPaths(String targetPrefix, XQueryModuleImport moduleImport) {
        List<LookupElement> variants = new LinkedList<LookupElement>();
        for (XQueryModuleImportPath moduleImportPath : moduleImport.getModuleImportPathList()) {
            XQueryFile file = (XQueryFile) moduleImportPath.getReference().resolve();
            if (file != null) {
                variants.addAll(getVariantsFromReferencedFile(targetPrefix, file));
            }
        }
        return variants;
    }

    private List<LookupElement> getVariantsFromReferencedFile(String targetPrefix, XQueryFile file) {
        List<LookupElement> variants = new LinkedList<LookupElement>();
        for (final XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                variants.add(createLookupElement(functionDecl, targetPrefix + ":" + functionDecl
                        .getFunctionName().getFunctionLocalName().getText()));
            }
        }
        return variants;
    }

    private boolean functionNameExists(XQueryFunctionDecl functionDecl) {
        return functionDecl.getFunctionName() != null && functionDecl.getFunctionName().getTextLength() > 0;
    }

    private LookupElement createLookupElement(XQueryFunctionDecl functionDeclaration, String key) {
        String tailText = " (" + (functionDeclaration.getParamList() != null ? functionDeclaration.getParamList()
                .getText() : "") + ")";
        String typeText = functionDeclaration.getSequenceType() != null ? functionDeclaration.getSequenceType()
                .getText() : "";
        return LookupElementBuilder.create(functionDeclaration, key)
                .withIcon(XQueryIcons.FILE)
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
