/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.braces;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.intellij.xquery.XQueryLanguage;
import org.intellij.xquery.psi.XQueryFunctionBody;
import org.jetbrains.annotations.NotNull;

public class FunctionDeclarationBracesBodyHandler extends TypedHandlerDelegate {

    @Override
    public Result charTyped(char c, Project project, @NotNull Editor editor, @NotNull PsiFile editedFile) {
        if ((editedFile.getLanguage() instanceof XQueryLanguage) && (c == '{' || c == '}')) {
            PsiDocumentManager.getInstance(project).commitAllDocuments();
            PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
            if (file == null) return Result.CONTINUE;
            FileViewProvider provider = file.getViewProvider();
            final int offset = editor.getCaretModel().getOffset();
            PsiElement element = provider.findElementAt(offset - 1, XQueryLanguage.class);
            if (element == null) return Result.CONTINUE;
            if (!(element.getLanguage() instanceof XQueryLanguage)) return Result.CONTINUE;
            ASTNode prevLeaf = element.getNode();
            if (prevLeaf == null) return Result.CONTINUE;
            final String prevLeafText = prevLeaf.getText();

            if (isInFunctionBodyAfterInsertionOfMatchingRightBrace(element, prevLeafText)) {
                if (c == '{') {
                    editor.getDocument().insertString(offset + 1, ";");
                } else {
                    EditorModificationUtil.insertStringAtCaret(editor, ";", false);
                }
            }

        }

        return Result.CONTINUE;
    }

    private boolean isInFunctionBodyAfterInsertionOfMatchingRightBrace(PsiElement element, String prevLeafText) {
        return ("{".equals(prevLeafText) || "}".equals(prevLeafText)) && element.getParent() != null
                && element.getParent().getParent() != null
                && element.getParent().getParent() instanceof XQueryFunctionBody;
    }
}
