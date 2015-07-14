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

package org.intellij.xquery.completion.function;

import com.intellij.codeInsight.lookup.Lookup;
import org.intellij.xquery.completion.BaseCollectorTest;
import org.intellij.xquery.reference.MatchingStringCondition;

import java.util.List;

import static com.intellij.util.containers.ContainerUtil.findAll;
import static org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces.FN;
import static org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces.MATH;

/**
 * User: ligasgr
 * Date: 02/08/13
 * Time: 13:46
 */
public class FunctionCollectorTest extends BaseCollectorTest {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/completion/function";
    }

    private XQuery30BuiltInFunctionTable bifTable = new XQuery30BuiltInFunctionTable();

    public void testFunctionCompletionInTheSameFile() {
        List<String> strings = getCompletionProposals();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testFunctionCompletionInTheSameFileForDuplicatedEntries() {
        List<String> strings = getCompletionProposals();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(2, referenceBasedEntries.size());
    }

    public void testFunctionCompletionInTheSameFileForSameNameAndDifferentArity() {
        List<String> strings = getCompletionProposals();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(2, referenceBasedEntries.size());
    }

    public void testFunctionCompletionInTheSameFileWithoutPrefixWithDefaultNamespace() {
        List<String> strings = getCompletionProposals();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("example:example"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionInTheSameFileWithPrefixWithDefaultNamespace() {
        List<String> strings = getCompletionProposals();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("example:example"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionInTheSameFileWithPrefixAndPartOfNamePresent() {
        List<String> strings = getCompletionProposals();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("Example"));
        assertEquals(1, referenceBasedEntries.size());
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("example:Example"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionFromAnotherFile() {
        List<String> strings = getCompletionProposals("FunctionReferencedFile.xq");
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testFunctionCompletionFromAnotherFileForPrivate() {
        List<String> strings = getCompletionProposals("FunctionReferencedFileWithPrivate.xq");
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(0, referenceBasedEntries.size());
    }

    public void testFunctionCompletionFromAnotherFileWithDefaultNamespaceAndImportedNamespacePrefix() {
        List<String> strings = getCompletionProposals("FunctionReferencedFile.xq");
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(1, referenceBasedEntries.size());
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("accessible"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionFromAnotherFileWithDefaultNamespaceAndNotImportedNamespacePrefix() {
        List<String> strings = getCompletionProposals("FunctionReferencedFile.xq");
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("accessible"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionFromAnotherFileWithDefaultNamespaceAndDeclaredNamespace() {
        List<String> strings = getCompletionProposals("FunctionReferencedFile.xq");
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(1, referenceBasedEntries.size());
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("accessible"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionInTheSameFileWithoutParentheses() {
        List<String> strings = getCompletionProposals();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testFunctionCompletionWithParenthesesAdded() {
        testInsertHandler(Lookup.NORMAL_SELECT_CHAR);
    }

    public void testFunctionCompletionWithNamespacePrefixPresent() {
        testInsertHandler(Lookup.NORMAL_SELECT_CHAR);
    }

    public void testFunctionCompletionWithTextAfterNotReplaced() {
        testInsertHandler(Lookup.NORMAL_SELECT_CHAR);
    }

    public void testFunctionCompletionWithTextAfterReplaced() {
        testInsertHandler(Lookup.REPLACE_SELECT_CHAR);
    }

    public void testFunctionCompletionForBuiltIn() {
        List<String> strings = getCompletionProposals();
        assertCorrectBuiltInFunctionLookupItems(strings, 2, "", FN.getPrefix() + ":");
    }

    public void testFunctionCompletionForBuiltInWhenFnPrefixOverwritten() {
        List<String> strings = getCompletionProposals();
        assertCorrectBuiltInFunctionLookupItems(strings, 1, "");
    }

    public void testFunctionCompletionForBuiltInWhenDefaultFunctionNamespaceOverwritten() {
        List<String> strings = getCompletionProposals();
        assertCorrectBuiltInFunctionLookupItems(strings, 1, FN.getPrefix() + ":");
    }

    public void testFunctionCompletionForBuiltInWithAdditionalPrefixForFunctions() {
        List<String> strings = getCompletionProposals();
        assertCorrectBuiltInFunctionLookupItems(strings, 3, "", FN.getPrefix() + ":", "my:");
    }

    private void assertCorrectBuiltInFunctionLookupItems(List<String> functions, int timesBuiltInFunctionsAppear,
                                                         String... functionPrefixes) {
        for (BuiltInFunctionSignature builtInFunctionSignature : bifTable.getFunctionsSignatures(FN.getNamespace())) {
            for (String functionPrefix : functionPrefixes) {
                assertTrue(functions.contains(functionPrefix + builtInFunctionSignature.getName()));
            }
        }
        for (BuiltInFunctionSignature builtInFunctionSignature : bifTable.getFunctionsSignatures(MATH.getNamespace())) {
            assertTrue(functions.contains(MATH.getPrefix() + ":" + builtInFunctionSignature.getName()));
        }
    }
}
