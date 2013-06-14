package org.intellij.xquery;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.intellij.xquery.psi.XQueryTypes;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 09/06/13
 * Time: 00:05
 */
public class SimpleCompletionContributor extends CompletionContributor {
    public SimpleCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(XQueryTypes.NCNAME).withLanguage(XQueryLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );
    }
}
