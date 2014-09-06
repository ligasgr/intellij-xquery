/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.completion;

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.intellij.xquery.psi.XQueryTypes;

public class InsertHandlerUtils {
    public static void removePreviousNamespaceAndColonIfPresent(InsertionContext context) {
        final Editor editor = context.getEditor();
        final Document document = editor.getDocument();
        PsiElement elementBeforeColon = findElementBeforeColon(context);
        removeNamespaceAndColon(context, document, elementBeforeColon);
    }

    private static PsiElement findElementBeforeColon(InsertionContext context) {
        final PsiFile file = context.getFile();
        PsiElement element = file.findElementAt(context.getStartOffset() - 1);
        if (element instanceof LeafPsiElement
                && ((LeafPsiElement) element).getElementType() == XQueryTypes.COLON) {
            return file.findElementAt(context.getStartOffset() - 2);
        }
        return null;
    }

    private static void removeNamespaceAndColon(InsertionContext context, Document document, PsiElement elementBeforeColon) {
        if (elementBeforeColon != null) {
            TextRange textRange = elementBeforeColon.getTextRange();
            document.deleteString(textRange.getStartOffset(), context.getStartOffset());
        }
    }
}
