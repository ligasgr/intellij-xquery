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
package org.intellij.xquery.completion.variable;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.intellij.xquery.psi.XQueryTypes;

/**
* User: ligasgr
* Date: 29/11/13
* Time: 23:21
*/
class VariableInsertHandler implements InsertHandler<LookupElement> {
    @Override
    public void handleInsert(InsertionContext context, LookupElement item) {
        final Editor editor = context.getEditor();
        final Document document = editor.getDocument();
        context.commitDocument();
        PsiElement element = findPreviousToken(context);
        if (element instanceof LeafPsiElement
                && ((LeafPsiElement) element).getElementType() != XQueryTypes.DOLLAR_SIGN) {
            document.insertString(context.getStartOffset(), "$");
        }
    }

    private PsiElement findPreviousToken(InsertionContext context) {
        final PsiFile file = context.getFile();
        PsiElement element = file.findElementAt(context.getStartOffset() - 1);
        if (element instanceof PsiWhiteSpace) {
            element = file.findElementAt(element.getTextRange().getStartOffset());
        }
        return element;
    }
}
