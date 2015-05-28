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

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionInitializationContext;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import org.intellij.xquery.completion.function.FunctionCollector;
import org.intellij.xquery.completion.keyword.KeywordCollector;
import org.intellij.xquery.completion.variable.VariableCollector;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryTypes;
import org.intellij.xquery.psi.XQueryURILiteral;
import org.intellij.xquery.reference.function.XQueryFunctionReference;
import org.intellij.xquery.reference.variable.XQueryVariableReference;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.patterns.StandardPatterns.instanceOf;

/**
 * User: ligasgr
 * Date: 31/07/13
 * Time: 14:35
 */
public class XQueryCompletionContributor extends CompletionContributor {
    public static final int VARIABLES_PRIORITY = 50;
    public static final int FUNCTIONS_PRIORITY = -4;
    public static final int BIF_PRIORITY = -50;
    public static final int EXTERNAL_VARIABLES_PRIORITY = -7;
    public static final int EXTERNAL_FUNCTIONS_PRIORITY = -7;
    public static final int SENSITIVE_KEYWORD_PRIORITY = -10;
    public static final int KEYWORD_PRIORITY = -20;

    private static final String PARENTHESES = "()";

    public XQueryCompletionContributor() {
        extendForVariablesOnly();
        extendForVariablesAndFunctions();
        extendForKeywords();
    }

    public static LookupElement prioritized(LookupElement lookupElement, int priority) {
        return PrioritizedLookupElement.withPriority(lookupElement, priority);
    }

    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        overwriteDummyIdentifierToTryToCreateFunctionReference(context);
    }

    private void extendForKeywords() {
        extend(CompletionType.BASIC, psiElement().inFile(instanceOf(XQueryFile.class)),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context,
                                                  @NotNull CompletionResultSet result) {
                        PsiElement position = parameters.getPosition();
                        if (isInsideOfUriLiteral(position)) return;
                        KeywordCollector keywordCollector = new KeywordCollector();
                        result.addAllElements(keywordCollector.getProposedLookUpItems(position));
                    }
                });
    }

    private void extendForVariablesAndFunctions() {
        extend(CompletionType.BASIC, referencePattern(XQueryTypes.NCNAME).withReferenceOfAnyOfTypes
                        (XQueryFunctionReference.class),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context,
                                                  @NotNull CompletionResultSet result) {
                        XQueryFile originalFile = (XQueryFile) parameters.getOriginalFile();
                        VariableCollector variableCollector = new VariableCollector(parameters
                                .getPosition());

                        result.addAllElements(FunctionCollector.getLookUpItems(originalFile));
                        result.addAllElements(variableCollector.getProposedLookUpItems());
                    }
                });
    }

    private void extendForVariablesOnly() {
        extend(CompletionType.BASIC, referencePattern(XQueryTypes.NCNAME).withReferenceOfAnyOfTypes
                        (XQueryVariableReference.class),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context,
                                                  @NotNull CompletionResultSet result) {
                        VariableCollector variableCollector = new VariableCollector(parameters
                                .getPosition());
                        result.addAllElements(variableCollector.getProposedLookUpItems());
                    }
                });
    }

    private boolean isInsideOfUriLiteral(PsiElement position) {
        return position.getParent() instanceof XQueryURILiteral;
    }

    private void overwriteDummyIdentifierToTryToCreateFunctionReference(CompletionInitializationContext context) {
        context.setDummyIdentifier(CompletionInitializationContext.DUMMY_IDENTIFIER + PARENTHESES);
    }

    private ReferencePattern referencePattern(IElementType type) {
        return new ReferencePattern().withElementType(type);
    }
}
