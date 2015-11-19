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

package org.intellij.xquery.completion.variable;

import com.intellij.codeInsight.lookup.Lookup;
import org.intellij.xquery.completion.BaseCollectorTest;
import org.intellij.xquery.reference.MatchingStringCondition;

import java.util.List;

import static com.intellij.util.containers.ContainerUtil.findAll;
import static java.util.Arrays.asList;

/**
 * User: ligasgr
 * Date: 02/08/13
 * Time: 13:46
 */
public class VariableCollectorTest extends BaseCollectorTest {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/completion/variable";
    }

    public void testVariableCompletionInTheSameFile() {
        List<String> strings = getCompletionProposals();
        assertTrue(strings.containsAll(asList("globalScopeVar", "functionArgumentScopeVar", "flworScopeVar")));
    }

    public void testVariableCompletionInTheSameFileScopes() {
        List<String> strings = getCompletionProposals();
        assertTrue(strings.containsAll(asList("anotherOne", "globalScopeVar", "scope:globalScopeVar",
                "functionArgumentScopeVar", "p", "rank", "someVar")));
    }

    public void testVariableCompletionInTheSameFileForDuplicatedEntries() {
        List<String> strings = getCompletionProposals();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testVariableCompletionInTheSameFileForSameNameAndDifferentScope() {
        List<String> strings = getCompletionProposals();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testVariableCompletionFromAnotherFile() {
        List<String> strings = getCompletionProposals("VariableReferencedFile.xq");
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testVariableCompletionFromAnotherFileForPrivate() {
        List<String> strings = getCompletionProposals("VariableReferencedFileWithPrivate.xq");
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(0, referenceBasedEntries.size());
    }

    public void testVariableCompletionInTheSameFileWithPrefixAndPartOfNamePresent() {
        List<String> strings = getCompletionProposals();
        assertTrue(strings.contains("local:Example"));
    }

    public void testVariableCompletionWithoutDollarAdded() {
        testInsertHandler("var", Lookup.NORMAL_SELECT_CHAR);
    }

    public void testVariableCompletionWithDollarAdded() {
        testInsertHandler("var", Lookup.NORMAL_SELECT_CHAR);
    }

    public void testVariableCompletionWithNamespacePrefixPresentAndWithoutDollarAdded() {
        testInsertHandler("tmp", Lookup.NORMAL_SELECT_CHAR);
    }

    public void testVariableCompletionWithNamespacePrefixPresentAndWithDollarAdded() {
        testInsertHandler("tmp", Lookup.NORMAL_SELECT_CHAR);
    }

    public void testVariableCompletionWithTextAfterNotReplaced() {
        testInsertHandler("tmp", Lookup.NORMAL_SELECT_CHAR);
    }

    public void testVariableCompletionWithTextAfterReplaced() {
        testInsertHandler("tmp", Lookup.REPLACE_SELECT_CHAR);
    }
}
