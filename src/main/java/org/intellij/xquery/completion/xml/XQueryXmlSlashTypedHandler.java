/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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
import org.intellij.xquery.psi.XQueryEnclosedExpression;
import org.intellij.xquery.psi.XQueryTypes;
import org.intellij.xquery.psi.XQueryXmlFullTag;
import org.intellij.xquery.psi.XQueryXmlTagName;
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
            if (isAtTheSlashOfClosingOfEmptyTag(offset, element)) {
                moveCaretByOne(editor, offset);
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
            if (isStartOfEndOfTag(prevLeaf, prevLeafText)) {
                XQueryXmlFullTag tag = PsiTreeUtil.getParentOfType(element, XQueryXmlFullTag.class);
                if (tag != null) {
                    XQueryXmlTagName tagName = tag.getXmlTagNameList().get(0);
                    if (hasNoClosingTagName(prevLeaf, tag, tagName)) {
                        finishClosingTag(editor, tagName);
                        return Result.STOP;
                    }
                }
            }
            if (!"/".equals(prevLeafText.trim())) return Result.CONTINUE;
            prevLeaf = getPreviousNonWhiteSpaceLeaf(prevLeaf);
            if (prevLeaf == null) return Result.CONTINUE;
            if (PsiTreeUtil.getParentOfType(element, XQueryDirAttributeValue.class) != null) return Result.CONTINUE;
            if (prevLeaf.getElementType() == XQueryTypes.ELEMENTCONTENTCHAR) return Result.CONTINUE;
            XQueryEnclosedExpression parentEnclosedExpression = PsiTreeUtil.getParentOfType(element, XQueryEnclosedExpression.class, true, XQueryXmlFullTag.class);
            XQueryXmlFullTag fullTag = getParentFullTag(prevLeaf);
            if (isInEnclosedExpressionNestedInXmlTag(parentEnclosedExpression, fullTag)) return Result.CONTINUE;
            if (isInUnclosedXmlTag(fullTag)) {
                closeEmptyTag(editor);
                return Result.STOP;
            }
        }
        return Result.CONTINUE;
    }

    private void moveCaretByOne(Editor editor, int offset) {
        editor.getCaretModel().moveToOffset(offset + 1);
        editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
    }

    private boolean isAtTheSlashOfClosingOfEmptyTag(int offset, PsiElement element) {
        return element != null && element.getNode() != null
                && element.getNode().getElementType() == XQueryTypes.XMLEMPTYELEMENTEND
                && offset == element.getTextOffset();
    }

    private boolean isInUnclosedXmlTag(XQueryXmlFullTag fullTag) {
        return fullTag != null;
    }

    private void closeEmptyTag(@NotNull Editor editor) {
        EditorModificationUtil.insertStringAtCaret(editor, ">", false);
    }

    private void finishClosingTag(@NotNull Editor editor, XQueryXmlTagName tagName) {
        String prefix = tagName.getXmlTagNamespace() != null ? tagName.getXmlTagNamespace().getName() + ":" : "";
        String name = prefix + tagName.getXmlTagLocalName().getText();
        EditorModificationUtil.insertStringAtCaret(editor, name + ">", false);
    }

    private boolean hasNoClosingTagName(ASTNode prevLeaf, XQueryXmlFullTag tag, XQueryXmlTagName tagName) {
        return tag != null && StringUtil.isNotEmpty(tagName.getName()) && TreeUtil.findSibling(prevLeaf, XQueryTypes.XMLTAGNCNAME) == null;
    }

    private boolean isStartOfEndOfTag(ASTNode prevLeaf, String prevLeafText) {
        return "</".equals(prevLeafText) && prevLeaf.getElementType() == XQueryTypes.XMLENDTAGSTART;
    }

    private boolean isInEnclosedExpressionNestedInXmlTag(XQueryEnclosedExpression parentEnclosedExpression,
                                                                     XQueryXmlFullTag fullTag) {
        return parentEnclosedExpression != null && fullTag == null;
    }

    protected XQueryXmlFullTag getParentFullTag(ASTNode prevLeaf) {
        return PsiTreeUtil.getParentOfType(prevLeaf.getPsi(), XQueryXmlFullTag.class, true, XQueryEnclosedExpression.class);
    }

    protected ASTNode getPreviousNonWhiteSpaceLeaf(ASTNode originalPrevLeaf) {
        ASTNode prevLeaf = originalPrevLeaf;
        while ((prevLeaf = TreeUtil.prevLeaf(prevLeaf)) != null && prevLeaf.getElementType() == TokenType.WHITE_SPACE) {
        }
        return prevLeaf;
    }
}
