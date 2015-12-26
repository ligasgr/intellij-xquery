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

package org.intellij.xquery.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import org.intellij.xquery.annotator.comment.MisplacedCommentHighlighter;
import org.intellij.xquery.annotator.duplicateFunction.ErrorAnnotationCreator;
import org.intellij.xquery.annotator.function.UnresolvedFunctionChecker;
import org.intellij.xquery.annotator.variable.UnresolvedVariableChecker;
import org.intellij.xquery.annotator.xml.UnresolvedXmlNamespaceChecker;
import org.intellij.xquery.annotator.xqdoc.XQDocHighlighter;
import org.intellij.xquery.highlighting.XQuerySyntaxHighlighter;
import org.intellij.xquery.psi.XQueryAnnotation;
import org.intellij.xquery.psi.XQueryCaseClause;
import org.intellij.xquery.psi.XQueryCompatibilityAnnotation;
import org.intellij.xquery.psi.XQueryCountClause;
import org.intellij.xquery.psi.XQueryCurrentItem;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryForBinding;
import org.intellij.xquery.psi.XQueryFunctionCall;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionInvocation;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryGroupByClause;
import org.intellij.xquery.psi.XQueryGroupingVariable;
import org.intellij.xquery.psi.XQueryItemType;
import org.intellij.xquery.psi.XQueryLetBinding;
import org.intellij.xquery.psi.XQueryMarklogicAnnotation;
import org.intellij.xquery.psi.XQueryMarklogicCatchErrorList;
import org.intellij.xquery.psi.XQueryMultiVariableBinding;
import org.intellij.xquery.psi.XQueryNextItem;
import org.intellij.xquery.psi.XQueryParam;
import org.intellij.xquery.psi.XQueryPositionalVar;
import org.intellij.xquery.psi.XQueryPreviousItem;
import org.intellij.xquery.psi.XQueryTypeswitchDefaultReturnClause;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;
import org.intellij.xquery.psi.XQueryWindowClause;
import org.intellij.xquery.psi.XQueryXmlTagNamespace;
import org.jetbrains.annotations.NotNull;

import static org.intellij.xquery.psi.XQueryTypes.EXPRCOMMENTCONTENT;
import static org.intellij.xquery.psi.XQueryTypes.EXPR_COMMENT_END;
import static org.intellij.xquery.psi.XQueryTypes.EXPR_COMMENT_START;

public class XQueryAnnotator implements Annotator, DumbAware {

    private ErrorAnnotationCreator duplicateFunctionErrorCreator = new ErrorAnnotationCreator();
    private XQDocHighlighter xQDocHighlighter = new XQDocHighlighter();
    private MisplacedCommentHighlighter misplacedCommentHighlighter = new MisplacedCommentHighlighter();
    private UnresolvedVariableChecker unresolvedVariableChecker = new UnresolvedVariableChecker();
    private UnresolvedFunctionChecker unresolvedFunctionChecker = new UnresolvedFunctionChecker();
    private UnresolvedXmlNamespaceChecker unresolvedXmlNamespaceChecker = new UnresolvedXmlNamespaceChecker();

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        IElementType elementType = element.getNode().getElementType();
        if (element instanceof XQueryFunctionName) {
            duplicateFunctionErrorCreator.createDuplicateFunctionDeclarationError(holder, (XQueryFunctionName) element, (XQueryFile) element.getContainingFile());
        }
        if (element instanceof PsiComment) {
            xQDocHighlighter.highlightXQDocTags((PsiComment) element, holder);
        }
        if (isPartOfMisplacedComment(elementType)) {
            misplacedCommentHighlighter.highlight(element, holder);
        }
        if (element instanceof XQueryVarRef) {
            unresolvedVariableChecker.check((XQueryVarRef) element, holder);
        }
        if (element instanceof XQueryFunctionInvocation) {
            unresolvedFunctionChecker.check((XQueryFunctionInvocation) element, holder);
        }
        if (element instanceof XQueryXmlTagNamespace) {
            unresolvedXmlNamespaceChecker.check((XQueryXmlTagNamespace) element, holder);
        }
        if (element instanceof XQueryItemType) {
            highlight(element, holder, XQuerySyntaxHighlighter.ITEM_TYPE);
        }
        PsiElement elementParent = element.getParent();
        if (element instanceof XQueryFunctionName && elementParent instanceof XQueryFunctionCall) {
            highlight(element, holder, XQuerySyntaxHighlighter.FUNCTION_CALL);
        }
        if (element instanceof XQueryFunctionName && elementParent instanceof XQueryFunctionDecl) {
            highlight(element, holder, XQuerySyntaxHighlighter.FUNCTION_DECLARATION);
        }
        if (element instanceof XQueryVarName) {
            if (elementParent instanceof XQueryVarRef) {
                PsiReference reference = elementParent.getReference();
                if (reference != null) {
                    PsiElement resolvedReference = reference.resolve();
                    if (resolvedReference != null) {
                        highlightVariable((XQueryVarName) element, holder, resolvedReference.getParent());
                    }
                }
            } else {
                highlightVariable((XQueryVarName) element, holder, elementParent);
            }
        }
        if (element instanceof XQueryCompatibilityAnnotation || element instanceof XQueryMarklogicAnnotation) {
            highlight(element, holder, XQuerySyntaxHighlighter.ANNOTATION);
        }
        if (element instanceof XQueryAnnotation) {
            highlight(((XQueryAnnotation) element).getAnnotationName(), holder, XQuerySyntaxHighlighter.ANNOTATION);
            highlight(new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getStartOffset() + 1), holder, XQuerySyntaxHighlighter.ANNOTATION);
        }
    }

    private boolean isPartOfMisplacedComment(IElementType type) {
        return type == EXPRCOMMENTCONTENT || type == EXPR_COMMENT_START || type == EXPR_COMMENT_END;
    }

    private void highlightVariable(XQueryVarName element, AnnotationHolder holder, PsiElement elementParent) {
        TextAttributes attributes = getAttributes(XQuerySyntaxHighlighter.PREFIXED_VARIABLE);
        if (element.getPrefix() != null && !new TextAttributes().equals(attributes)) {
            highlight(element, holder, XQuerySyntaxHighlighter.PREFIXED_VARIABLE);
        } else {
            if (elementParent instanceof XQueryParam) {
                highlight(element, holder, XQuerySyntaxHighlighter.PARAMETER);
            } else if (elementParent instanceof XQueryVarDecl) {
                highlight(element, holder, XQuerySyntaxHighlighter.GLOBAL_VARIABLE);
            } else if (isLocalVariableDeclaration(elementParent)) {
                highlight(element, holder, XQuerySyntaxHighlighter.LOCAL_VARIABLE);
            }
        }
    }

    private boolean isLocalVariableDeclaration(PsiElement element) {
        return element instanceof XQueryGroupByClause
                || element instanceof XQueryCurrentItem
                || element instanceof XQueryGroupingVariable
                || element instanceof XQueryLetBinding
                || element instanceof XQueryMarklogicCatchErrorList
                || element instanceof XQueryMultiVariableBinding
                || element instanceof XQueryNextItem
                || element instanceof XQueryPositionalVar
                || element instanceof XQueryPreviousItem
                || element instanceof XQueryCaseClause
                || element instanceof XQueryCountClause
                || element instanceof XQueryForBinding
                || element instanceof XQueryTypeswitchDefaultReturnClause
                || element instanceof XQueryWindowClause
                ;
    }

    private void highlight(PsiElement element, AnnotationHolder holder, TextAttributesKey attributesKey) {
        //TODO: start using createAnnotation in newer version to do highlighting even when there are errors
        // use HighlightInfoType.SYMBOL_TYPE_SEVERITY to satisfy UpdateHighlightersUtil.isCovered()
        highlight(element.getTextRange(), holder, attributesKey);
    }


    private void highlight(TextRange textRange, AnnotationHolder holder, TextAttributesKey attributesKey) {
        holder.createInfoAnnotation(textRange, null)
                .setEnforcedTextAttributes(getAttributes(attributesKey));
    }

    private TextAttributes getAttributes(TextAttributesKey attributesKey) {
        return EditorColorsManager.getInstance().getGlobalScheme().getAttributes(attributesKey);
    }
}
