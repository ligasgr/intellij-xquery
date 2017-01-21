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

package org.intellij.xquery.completion.keyword;

import com.intellij.codeInsight.completion.CompletionInitializationContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.tree.IElementType;
import org.intellij.grammar.parser.GeneratedParserUtilBase;
import org.intellij.xquery.XQueryLanguage;
import org.intellij.xquery.lexer.XQueryLexer;
import org.intellij.xquery.psi.impl.XQueryPsiImplUtil;
import org.intellij.xquery.util.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static com.intellij.openapi.util.text.StringUtil.join;
import static java.util.Arrays.asList;
import static org.intellij.grammar.parser.GeneratedParserUtilBase.COMPLETION_STATE_KEY;
import static org.intellij.xquery.completion.XQueryCompletionContributor.KEYWORD_PRIORITY;
import static org.intellij.xquery.completion.XQueryCompletionContributor.SENSITIVE_KEYWORD_PRIORITY;
import static org.intellij.xquery.completion.XQueryCompletionContributor.prioritized;

/**
 * User: ligasgr
 * Date: 28/11/13
 * Time: 14:26
 */
public class KeywordCollector {

    private Map<String, List<String>> keywordSuggestions = new HashMap<String, List<String>>();

    {
        keywordSuggestions.put("", asList("xquery", "ancestor", "ancestor-or-self", "attribute", "binary", "child", "comment", "copy", "declare", "delete", "descendant", "descendant-or-self", "document", "document-node", "element", "every", "following", "following-sibling", "for", "function", "if", "import", "insert", "let", "map", "module", "namespace", "namespace-node", "node", "ordered", "parent", "preceding", "preceding-sibling", "processing-instruction", "rename", "replace", "schema-attribute", "schema-element", "self", "some", "switch", "text", "try"));
        keywordSuggestions.put("xquery", asList("version", "encoding"));
        keywordSuggestions.put("declare", asList("boundary-space", "default", "base-uri", "construction", "ordering", "copy-namespaces", "decimal-format", "namespace", "updating", "private", "variable", "function", "context", "option", "revalidation"));
        keywordSuggestions.put("module", asList("namespace"));
        keywordSuggestions.put("import", asList("schema", "module"));
    }

    public List<LookupElement> getProposedLookUpItems(PsiElement position) {
        Set<String> collected = new HashSet<String>();
        Collection<String> keywordsForContextSensitiveKeywords = suggestKeywordsForContextSensitiveKeywords(position);
        Collection<String> keywordsBasedOnParserExpectedKeywords = suggestKeywordsBasedOnParserExpectedKeywords(position);
        collected.addAll(keywordsForContextSensitiveKeywords);
        List<LookupElement> result = new LinkedList<LookupElement>();
        result.addAll(convertToLookupElements(keywordsForContextSensitiveKeywords, SENSITIVE_KEYWORD_PRIORITY));
        result.addAll(convertToLookupElements(addOnlyIfNotAlreadyAdded(keywordsBasedOnParserExpectedKeywords, collected), KEYWORD_PRIORITY));
        return result;
    }

    private Collection<String> addOnlyIfNotAlreadyAdded(Collection<String> newKeywords, Set<String> alreadyAdded) {
        List<String> result = new LinkedList<String>();
        for (String keyword : newKeywords) {
            if (!alreadyAdded.contains(keyword)) {
                result.add(keyword);
            }
        }
        return result;
    }

    private List<LookupElement> convertToLookupElements(Collection<String> keywords, int priority) {
        List<LookupElement> result = new LinkedList<LookupElement>();
        for (String keyword : keywords) {
            result.add(prioritized(convert(keyword), priority));
        }
        return result;
    }

    private LookupElementBuilder convert(String keyword) {
        return LookupElementBuilder.create(keyword).bold();
    }

    private Collection<String> suggestKeywordsForContextSensitiveKeywords(@NotNull PsiElement position) {
        String text = getPrecedingTokenText(position, StringUtils.EMPTY).trim();
        List<String> foundSuggestions = keywordSuggestions.get(text);
        return foundSuggestions != null ? foundSuggestions : Collections.<String>emptyList();
    }

    private String getPrecedingTokenText(PsiElement position, String defaultText) {
        PsiElement prevElement = XQueryPsiImplUtil.getPrevNonWhiteSpaceElement(position);
        return prevElement != null ? prevElement.getText() : defaultText;
    }

    private String getPrecedingText(PsiElement position, String defaultText) {
        TextRange range = getPrecedingTextRange(position);
        PsiFile posFile = position.getContainingFile();
        return range.isEmpty() ? defaultText : range.substring(posFile.getText());
    }

    private TextRange getPrecedingTextRange(PsiElement position) {
        TextRange positionTextRange = position.getTextRange();
        return new TextRange(0, positionTextRange.getStartOffset());
    }

    @NotNull
    private Collection<String> suggestKeywordsBasedOnParserExpectedKeywords(@NotNull PsiElement position) {
        String text = getPrecedingText(position, CompletionInitializationContext.DUMMY_IDENTIFIER);
        Project project = position.getProject();
        PsiFile temporaryFileForCompletionCheck = createFileForText(project, text + "          ");
        int completionOffset = calculateCompletionOffset(position);
        GeneratedParserUtilBase.CompletionState completionStateInTemporaryFile = getCompletionStateForKeywords(completionOffset);
        temporaryFileForCompletionCheck.putUserData(COMPLETION_STATE_KEY, completionStateInTemporaryFile);
        triggerParsingInFile(temporaryFileForCompletionCheck);
        List<String> stripped = stringPrecedingText(StringUtils.normalizeWhitespaces(text), completionStateInTemporaryFile.items);
        return expandMultiWordOptions(stripped);
    }

    @NotNull
    private List<String> stringPrecedingText(String text, Collection<String> items) {
        Set<String> stripped = new HashSet<>(items.size());
        List<String> variantsToReplace = variantsToReplace(text);
        for (String item : items) {
            stripped.add(strippedItem(variantsToReplace, item));
        }
        return new ArrayList<>(stripped);
    }

    private String strippedItem(List<String> variantsToReplace, String item) {
        for (String textToReplace: variantsToReplace) {
            if (item.startsWith(textToReplace)) {
                return item.replaceFirst(textToReplace, "").trim();
            }
        }
        return item.trim();
    }

    private List<String> variantsToReplace(String text) {
        Set<String> possibleEndings = new HashSet<>();
        List<String> possibleBeginnings = expandMultiWordOptions(Collections.singletonList(Pattern.quote(text)));
        for (String possibleBeginning : possibleBeginnings) {
            String possibleEnding = text.replaceFirst(possibleBeginning, "");
            if (!StringUtil.isEmptyOrSpaces(possibleEnding)) {
                possibleEndings.add(possibleEnding.trim());
            }
        }
        possibleEndings.add(text);
        return new ArrayList<>(possibleEndings);
    }

    private List<String> expandMultiWordOptions(List<String> items) {
        List<String> expanded = new ArrayList<>();
        for (String item : items) {
            String[] allWords = item.split(" ");
            String currentBuildUp = null;
            for (String word : allWords) {
                if (currentBuildUp == null) {
                    currentBuildUp = word;
                } else {
                    currentBuildUp = currentBuildUp + " " + word;
                }
                expanded.add(currentBuildUp);
            }
        }
        return expanded;
    }

    private PsiFile createFileForText(Project project, String text) {
        return PsiFileFactory.getInstance(project).createFileFromText("a.xq", XQueryLanguage.INSTANCE, text, true, false);
    }

    private void triggerParsingInFile(PsiFile file) {
        TreeUtil.ensureParsed(file.getNode());
    }

    private GeneratedParserUtilBase.CompletionState getCompletionStateForKeywords(final int completionOffset) {
        return new GeneratedParserUtilBase.CompletionState(completionOffset) {
            @Override
            public String convertItem(Object o) {
                if (o instanceof IElementType && XQueryLexer.KEYWORDS.contains((IElementType) o)) {
                    return o.toString();
                } else if (o instanceof String) {
                    return (String) o;
                }
                else if (o instanceof Object[]) {
                    return join((Object[]) o, this, " ");
                }
                return null;
            }
        };
    }

    private int calculateCompletionOffset(PsiElement position) {
        return position.getTextRange().getStartOffset() - getPrecedingTextRange(position).getStartOffset();
    }

}
