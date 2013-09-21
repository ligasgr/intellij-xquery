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

package org.intellij.xquery.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import org.intellij.xquery.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.patterns.StandardPatterns.instanceOf;
import static org.intellij.xquery.lexer.XQueryLexer.KEYWORDS;

/**
 * User: ligasgr
 * Date: 31/07/13
 * Time: 14:35
 */
public class XQueryCompletionContributor extends CompletionContributor {

    private static final String PARENTHESES = "()";

    public XQueryCompletionContributor() {
        extend(CompletionType.BASIC, psiElement().inFile(instanceOf(XQueryFile.class)),
                new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context,
                                          @NotNull CompletionResultSet result) {
                if (positionAlreadyHasContributionFromReferences(getGrandfatherOfPosition(parameters))) return;
                result.addAllElements(getAllKeywords());
            }
        });
    }

    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        final CharSequence text = context.getEditor().getDocument().getCharsSequence();
        if (isAPositionSuitableForFunctionCall(context, text)) {
            context.setDummyIdentifier(CompletionInitializationContext.DUMMY_IDENTIFIER_TRIMMED + PARENTHESES);
        } else {
            context.setDummyIdentifier(CompletionInitializationContext.DUMMY_IDENTIFIER);
        }
    }

    private PsiElement getGrandfatherOfPosition(CompletionParameters parameters) {
        return parameters.getPosition().getParent().getParent();
    }

    private boolean positionAlreadyHasContributionFromReferences(PsiElement parent) {
        return (parent instanceof XQueryFunctionName && !(parent.getParent() instanceof XQueryFunctionCall))
                || parent instanceof XQueryVarName
                || parent instanceof XQueryModuleImportPath;
    }

    private boolean isAPositionSuitableForFunctionCall(CompletionInitializationContext context, CharSequence text) {
        return context.getStartOffset() > 1 && !text.subSequence(context.getStartOffset() - 1,
                context.getStartOffset()).equals("$");
    }

    private List<LookupElement> getAllKeywords() {
        List<LookupElement> result = new LinkedList<LookupElement>();
        for (IElementType keywordTokenType : KEYWORDS.getTypes()) {
            result.add(LookupElementBuilder.create(keywordTokenType.toString()).bold());
        }
        return result;
    }
}
