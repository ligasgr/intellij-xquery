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

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.icons.XQueryIcons;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ligasgr
 * Date: 07/08/13
 * Time: 15:08
 */
public class XQueryFunctionReferenceVariantsCollector {

    private PsiElement myElement;

    public XQueryFunctionReferenceVariantsCollector(PsiElement myElement) {
        this.myElement = myElement;
    }

    public Object[] getVariants() {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        Map<String, LookupElement> variants = new HashMap<String, LookupElement>();

        for (final XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                addVariantIfNotAlreadyAdded(variants, functionDecl);
            }
        }
        return variants.values().toArray();
    }

    private void addVariantIfNotAlreadyAdded(Map<String, LookupElement> variants, XQueryFunctionDecl functionDecl) {
        String key = functionDecl.getFunctionName().getText();
        if (!variants.containsKey(key)) {
            variants.put(key, createLookupElement(functionDecl, key));
        }
    }

    private boolean functionNameExists(XQueryFunctionDecl functionDecl) {
        return functionDecl.getFunctionName() != null && functionDecl.getFunctionName().getTextLength() > 0;
    }

    private LookupElement createLookupElement(XQueryFunctionDecl functionDeclaration, String key) {
        String tailText = " (" + (functionDeclaration.getParamList() != null ? functionDeclaration.getParamList().getText() : "") + ")";
        String typeText = functionDeclaration.getSequenceType() != null ? functionDeclaration.getSequenceType().getText() : "";
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
