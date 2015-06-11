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

package org.intellij.xquery.completion.xml;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.XQueryLanguage;
import org.intellij.xquery.psi.XQueryDirAttributeValue;
import org.intellij.xquery.psi.XQueryTypes;
import org.intellij.xquery.psi.XQueryXmlFullTag;
import org.jetbrains.annotations.NotNull;

public class XQueryXmlSlashTypedHandler extends TypedHandlerDelegate {

    @Override
    public Result beforeCharTyped(final char c, final Project project, final Editor editor, final PsiFile editedFile, final FileType fileType) {
        if ((editedFile.getLanguage() instanceof XQueryLanguage) && c == '/') {
            PsiDocumentManager.getInstance(project).commitAllDocuments();
            PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
            if (file == null) return Result.CONTINUE;
            final int offset = editor.getCaretModel().getOffset();
            FileViewProvider provider = file.getViewProvider();
            PsiElement element = provider.findElementAt(offset, XQueryLanguage.class);
            if (element != null && element.getNode() != null
                    && element.getNode().getElementType() == XQueryTypes.XMLEMPTYELEMENTEND
                    && offset == element.getTextOffset()) {
                editor.getCaretModel().moveToOffset(offset + 1);
                editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
                return Result.STOP;
            }
        }
        return Result.CONTINUE;
    }

    @Override
    public Result charTyped(char c, final Project project, final @NotNull Editor editor, @NotNull final PsiFile editedFile) {
        if ((editedFile.getLanguage() instanceof XQueryLanguage) && c == '/') {
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
            if ("</".equals(prevLeafText) && prevLeaf.getElementType() == XQueryTypes.XMLENDTAGSTART) {
                XQueryXmlFullTag tag = PsiTreeUtil.getParentOfType(element, XQueryXmlFullTag.class);
                if (tag != null && StringUtil.isNotEmpty(tag.getXmlTagNameList().get(0).getName()) && TreeUtil.findSibling(prevLeaf, XQueryTypes.XMLTAGNCNAME) == null) {
                    String name = tag.getXmlTagNameList().get(0).getName();
                    EditorModificationUtil.insertStringAtCaret(editor, name + ">", false);
                    return Result.STOP;
                }
            }
            if (!"/".equals(prevLeafText.trim())) return Result.CONTINUE;
            prevLeaf = getPreviousNonWhiteSpaceLeaf(prevLeaf);
            if (prevLeaf == null) return Result.CONTINUE;
            if (PsiTreeUtil.getParentOfType(element, XQueryDirAttributeValue.class) != null) return Result.CONTINUE;
            if (PsiTreeUtil.getParentOfType(prevLeaf.getPsi(), XQueryXmlFullTag.class) != null) {
                EditorModificationUtil.insertStringAtCaret(editor, ">", false);
                return Result.STOP;
            }
        }
        return Result.CONTINUE;
    }

    protected ASTNode getPreviousNonWhiteSpaceLeaf(ASTNode originalPrevLeaf) {
        ASTNode prevLeaf = originalPrevLeaf;
        while ((prevLeaf = TreeUtil.prevLeaf(prevLeaf)) != null && prevLeaf.getElementType() == TokenType.WHITE_SPACE) {
        }
        return prevLeaf;
    }
}
