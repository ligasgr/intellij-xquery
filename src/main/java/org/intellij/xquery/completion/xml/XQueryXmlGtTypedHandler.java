/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.XQueryLanguage;
import org.intellij.xquery.psi.XQueryTypes;
import org.intellij.xquery.psi.XQueryXmlFullTag;
import org.intellij.xquery.psi.XQueryXmlTagName;
import org.jetbrains.annotations.NotNull;

public class XQueryXmlGtTypedHandler extends TypedHandlerDelegate {

    @Override
    public Result beforeCharTyped(final char c, final Project project, final Editor editor, final PsiFile editedFile, final FileType fileType) {
        if ((editedFile.getLanguage() instanceof XQueryLanguage) && c == '>') {
            PsiDocumentManager.getInstance(project).commitAllDocuments();
            PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
            if (file == null) return Result.CONTINUE;
            final int offset = editor.getCaretModel().getOffset();
            FileViewProvider provider = file.getViewProvider();
            PsiElement element = provider.findElementAt(offset, XQueryLanguage.class);

            if (element != null && element.getNode() != null && (
                    element.getNode().getElementType() == XQueryTypes.XMLTAGEND ||
                            element.getNode().getElementType() == XQueryTypes.XMLEMPTYELEMENTEND)) {
                EditorModificationUtil.moveCaretRelatively(editor, 1);
                editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
                return Result.STOP;
            }
        }
        return Result.CONTINUE;
    }


    @Override
    public Result charTyped(char c, final Project project, final @NotNull Editor editor, @NotNull final PsiFile editedFile) {
        if ((editedFile.getLanguage() instanceof XQueryLanguage) && c == '>') {
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
            if (">".equals(prevLeafText) && prevLeaf.getElementType() == XQueryTypes.XMLTAGEND) {
                XQueryXmlFullTag tag = PsiTreeUtil.getParentOfType(element, XQueryXmlFullTag.class);
                if (tag != null  && (
                        (tag.getXmlTagNameList().size() == 1 && xmlTagStartsAndStops(tag).length == 2)
                        || (tag.getXmlTagNameList().size() == 2 && !tagName(tag, 0).getText().equals(tagName(tag, 1).getText()))
                )) {
                    XQueryXmlTagName tagName = tagName(tag, 0);
                    if (StringUtil.isNotEmpty(tagName.getName()) && TreeUtil.findSibling(prevLeaf, XQueryTypes.XMLTAGNCNAME) == null) {
                        String prefix = tagName.getXmlTagNamespace() != null ? tagName.getXmlTagNamespace().getName() + ":" : "";
                        String name = prefix + tagName.getXmlTagLocalName().getText();
                        EditorModificationUtil.insertStringAtCaret(editor, "</" + name + ">", false, 0);
                        return Result.STOP;
                    }
                }
            }
        }
        return Result.CONTINUE;
    }

    private XQueryXmlTagName tagName(XQueryXmlFullTag tag, int index) {
        return tag.getXmlTagNameList().get(index);
    }

    @NotNull
    private ASTNode[] xmlTagStartsAndStops(XQueryXmlFullTag tag) {
        return tag.getNode().getChildren(TokenSet.create(XQueryTypes.XMLSTARTTAGSTART, XQueryTypes.XMLTAGEND, XQueryTypes.XMLENDTAGSTART));
    }
}
