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

package org.intellij.xquery.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiComment;
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

    public XQueryCompletionContributor() {
        extend(CompletionType.BASIC, psiElement().inFile(instanceOf(XQueryFile.class)),
                new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context,
                                          @NotNull CompletionResultSet result) {
                PsiElement position = parameters.getPosition();
                PsiElement parent = position.getParent().getParent();

                if ((parent instanceof XQueryFunctionName && !(parent.getParent() instanceof XQueryFunctionCall)) || parent instanceof XQueryVarName || parent instanceof XQueryModuleImportPath) return;

                result.addAllElements(getAllKeywords());
            }
        });
    }

    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        final CharSequence text = context.getEditor().getDocument().getCharsSequence();
        if (context.getStartOffset() > 1 && !text.subSequence(context.getStartOffset() - 1,
                context.getStartOffset()).equals("$")) {
            context.setDummyIdentifier(CompletionInitializationContext.DUMMY_IDENTIFIER_TRIMMED + "()");
        } else {
            context.setDummyIdentifier(CompletionInitializationContext.DUMMY_IDENTIFIER);
        }
    }

    private List<LookupElement> getAllKeywords() {
        List<LookupElement> result = new LinkedList<LookupElement>();
        for (IElementType keywordTokenType : KEYWORDS.getTypes()) {
            result.add(LookupElementBuilder.create(keywordTokenType.toString()).bold());
        }
        return result;
    }
}
