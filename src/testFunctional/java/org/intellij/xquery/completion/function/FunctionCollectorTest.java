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

package org.intellij.xquery.completion.function;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.Lookup;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.reference.MatchingStringCondition;

import java.util.List;

import static com.intellij.util.containers.ContainerUtil.findAll;
import static org.intellij.xquery.completion.function.BuiltInFunctionTable.getFunctionsSignatures;
import static org.intellij.xquery.lexer.XQueryLexer.KEYWORDS;
import static org.intellij.xquery.reference.namespace.XQueryPredeclaredNamespace.FN;
import static org.intellij.xquery.reference.namespace.XQueryPredeclaredNamespace.MATH;

/**
 * User: ligasgr
 * Date: 02/08/13
 * Time: 13:46
 */
public class FunctionCollectorTest extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/completion/function";
    }

    public void testFunctionCompletionInTheSameFile() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFile.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testFunctionCompletionInTheSameFileForDuplicatedEntries() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFileForDuplicatedEntries.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(2, referenceBasedEntries.size());
    }

    public void testFunctionCompletionInTheSameFileForSameNameAndDifferentArity() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFileForSameNameAndDifferentArity.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(2, referenceBasedEntries.size());
    }

    public void testFunctionCompletionInTheSameFileWithoutPrefixWithDefaultNamespace() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFileWithoutPrefixWithDefaultNamespace.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("example:example"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionInTheSameFileWithPrefixWithDefaultNamespace() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFileWithPrefixWithDefaultNamespace.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("example:example"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionFromAnotherFile() {
        myFixture.configureByFiles("FunctionCompletionFromAnotherFile.xq", "FunctionReferencedFile.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testFunctionCompletionFromAnotherFileForPrivate() {
        myFixture.configureByFiles("FunctionCompletionFromAnotherFileForPrivate.xq",
                "FunctionReferencedFileWithPrivate.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(0, referenceBasedEntries.size());
    }

    public void testFunctionCompletionFromAnotherFileWithDefaultNamespaceAndImportedNamespacePrefix() {
        myFixture.configureByFiles("FunctionCompletionFromAnotherFileWithDefaultNamespaceAndImportedNamespacePrefix.xq",
                "FunctionReferencedFile.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(1, referenceBasedEntries.size());
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("accessible"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionFromAnotherFileWithDefaultNamespaceAndNotImportedNamespacePrefix() {
        myFixture.configureByFiles
                ("FunctionCompletionFromAnotherFileWithDefaultNamespaceAndNotImportedNamespacePrefix.xq",
                        "FunctionReferencedFile.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("accessible"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionFromAnotherFileWithDefaultNamespaceAndDeclaredNamespace() {
        myFixture.configureByFiles("FunctionCompletionFromAnotherFileWithDefaultNamespaceAndDeclaredNamespace.xq",
                "FunctionReferencedFile.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(1, referenceBasedEntries.size());
        List<String> referenceBasedEntriesWithAdditionalNamespace = findAll(strings,
                new MatchingStringCondition("accessible"));
        assertEquals(1, referenceBasedEntriesWithAdditionalNamespace.size());
    }

    public void testFunctionCompletionInTheSameFileWithoutParentheses() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFileWithoutParentheses.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testFunctionCompletionWithParenthesesAddedAfterFunctionName() {
        myFixture.configureByFile("FunctionCompletionWithParenthesesAdded.xq");

        myFixture.completeBasic();
        myFixture.type(Lookup.NORMAL_SELECT_CHAR);

        myFixture.checkResultByFile("FunctionCompletionWithParenthesesAddedAfter.xq");
    }

    public void testFunctionCompletionForBuiltIn() {
        myFixture.configureByFiles("FunctionCompletionForBuiltIn.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();

        assertCorrectBuiltInFunctionLookupItems(strings, 2, "", FN.getPrefix() + ":");
    }

    public void testFunctionCompletionForBuiltInWhenFnPrefixOverwritten() {
        myFixture.configureByFiles("FunctionCompletionForBuiltInWhenFnPrefixOverwritten.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();

        assertCorrectBuiltInFunctionLookupItems(strings, 1, "");
    }

    public void testFunctionCompletionForBuiltInWhenDefaultFunctionNamespaceOverwritten() {
        myFixture.configureByFiles("FunctionCompletionForBuiltInWhenDefaultFunctionNamespaceOverwritten.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();

        assertCorrectBuiltInFunctionLookupItems(strings, 1, FN.getPrefix() + ":");
    }

    public void testFunctionCompletionForBuiltInWithAdditionalPrefixForFunctions() {
        myFixture.configureByFiles("FunctionCompletionForBuiltInWithAdditionalPrefixForFunctions.xq");

        myFixture.complete(CompletionType.BASIC, 1);

        List<String> strings = myFixture.getLookupElementStrings();

        assertCorrectBuiltInFunctionLookupItems(strings, 3, "", FN.getPrefix() + ":", "my:");
    }

    private void assertCorrectBuiltInFunctionLookupItems(List<String> functions, int timesBuiltInFunctionsAppear,
                                                         String... functionPrefixes) {
        for (BuiltInFunctionSignature builtInFunctionSignature : getFunctionsSignatures(FN.getNamespace())) {
            for (String functionPrefix : functionPrefixes) {
                assertTrue(functions.contains(functionPrefix + builtInFunctionSignature.getName()));
            }
        }
        for (BuiltInFunctionSignature builtInFunctionSignature : getFunctionsSignatures(MATH.getNamespace())) {
            assertTrue(functions.contains(MATH.getPrefix() + ":" + builtInFunctionSignature.getName()));
        }
        int builtInLookupItemsSize = getFunctionsSignatures(FN.getNamespace()).size() * timesBuiltInFunctionsAppear
                + getFunctionsSignatures(MATH.getNamespace()).size()
                + KEYWORDS.getTypes().length;
        assertEquals(builtInLookupItemsSize, functions.size());
    }
}
