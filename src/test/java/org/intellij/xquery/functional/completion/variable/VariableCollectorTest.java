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

package org.intellij.xquery.functional.completion.variable;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.Lookup;
import org.intellij.xquery.functional.BaseFunctionalTestCase;
import org.intellij.xquery.functional.reference.MatchingStringCondition;

import java.util.List;

import static com.intellij.util.containers.ContainerUtil.findAll;
import static java.util.Arrays.asList;

/**
 * User: ligasgr
 * Date: 02/08/13
 * Time: 13:46
 */
public class VariableCollectorTest extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/functional/completion/variable";
    }

    public void testVariableCompletionInTheSameFile() {
        myFixture.configureByFiles("VariableCompletionInTheSameFile.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(asList("globalScopeVar", "functionArgumentScopeVar", "flworScopeVar")));
    }

    public void testVariableCompletionInTheSameFileWithScopesChecking() {
        myFixture.configureByFiles("VariableCompletionInTheSameFileScopes.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(asList("anotherOne", "globalScopeVar", "scope:globalScopeVar",
                "functionArgumentScopeVar", "newOne", "p", "rank", "someVar")));
    }

    public void testVariableCompletionInTheSameFileForDuplicatedEntries() {
        myFixture.configureByFiles("VariableCompletionInTheSameFileForDuplicatedEntries.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testVariableCompletionInTheSameFileForSameNameAndDifferentScope() {
        myFixture.configureByFiles("VariableCompletionInTheSameFileForSameNameAndDifferentScope.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("example"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testVariableCompletionFromAnotherFile() {
        myFixture.configureByFiles("VariableCompletionFromAnotherFile.xq", "VariableReferencedFile.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(1, referenceBasedEntries.size());
    }

    public void testVariableCompletionFromAnotherFileWithPrivate() {
        myFixture.configureByFiles("VariableCompletionFromAnotherFileForPrivate.xq",
                "VariableReferencedFileWithPrivate.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("library:accessible"));
        assertEquals(0, referenceBasedEntries.size());
    }

    public void testVariableCompletionWithoutDollarAdded() {
        myFixture.configureByFile("VariableCompletionWithoutDollarAdded.xq");

        myFixture.completeBasic();
        myFixture.type("var");
        myFixture.type(Lookup.NORMAL_SELECT_CHAR);

        myFixture.checkResultByFile("VariableCompletionWithoutDollarAdded_after.xq");
    }

    public void testVariableCompletionWithDollarAdded() {
        myFixture.configureByFile("VariableCompletionWithDollarAdded.xq");

        myFixture.completeBasic();
        myFixture.type("var");
        myFixture.type(Lookup.NORMAL_SELECT_CHAR);

        myFixture.checkResultByFile("VariableCompletionWithDollarAdded_after.xq");
    }
}
