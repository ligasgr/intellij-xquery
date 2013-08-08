/*
 * Copyright 2013 Grzegorz Ligas
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

package org.intellij.xquery.reference;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.Lookup;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import org.intellij.xquery.psi.XQueryFunctionCall;
import org.intellij.xquery.psi.XQueryFunctionDecl;

import java.util.List;

import static java.util.Arrays.asList;
import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 03/07/13
 * Time: 21:44
 */
public class XQueryFunctionReferenceTest extends LightPlatformCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/reference/function";
    }

    public void testFunctionCompletionInTheSameFile() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFile.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(asList("example")));
    }

    public void testFunctionCompletionInTheSameFileForDuplicatedEntries() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFileForDuplicatedEntries.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(asList("example")));
    }

    public void testFunctionCompletionInTheSameFileForSameNameAndDifferentArity() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFileForSameNameAndDifferentArity.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(asList("example")));
    }

    public void testFunctionCompletionInTheSameFileWithoutParentheses() {
        myFixture.configureByFiles("FunctionCompletionInTheSameFileWithoutParentheses.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(asList("example")));
    }

    public void testFunctionCompletionWithParenthesesAddedAfterFunctionName() {
        myFixture.configureByFile("FunctionCompletionWithParenthesesAdded.xq");
        myFixture.completeBasic();
        myFixture.type(Lookup.NORMAL_SELECT_CHAR);
        myFixture.checkResultByFile("FunctionCompletionWithParenthesesAddedAfter.xq");
    }

    public void testFunctionRenameInTheSameFile() {
        myFixture.configureByFiles("FunctionRenameInTheSameFile.xq");
        myFixture.renameElementAtCaret("renamed");
        myFixture.checkResultByFile("FunctionRenameInTheSameFile.xq", "FunctionRenameInTheSameFileAfter.xq", false);
    }

    public void testFunctionReferenceInLibraryModule() {
        myFixture.configureByFiles("FunctionReferenceInTheSameFile_Library.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
    }

    public void testFunctionReferenceFromVariableDeclaration() {
        myFixture.configureByFiles("FunctionReferenceInTheSameFile_Global.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
    }

    public void testFunctionReferenceFromFunctionArgument() {
        myFixture.configureByFiles("FunctionReferenceInTheSameFile_FunctionArgument.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
    }

    public void testFunctionReferenceFromFlworExpression() {
        myFixture.configureByFiles("FunctionReferenceInTheSameFile_Flwor.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
    }

    public void testFunctionReferenceFromAnotherFile() {
        myFixture.configureByFiles("FunctionReferenceFromAnotherFile.xq", "FunctionReferencedFile.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl varDecl = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals("FunctionReferencedFile.xq", varDecl.getContainingFile().getName());
    }

    public void testFunctionReferenceToNotExistingFunction() {
        myFixture.configureByFiles("FunctionReferenceToNotExistingFunction.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertNull(resolvedReference);
    }

    public void testFunctionReferenceToDuplicatedFunction() {
        myFixture.configureByFiles("FunctionReferenceToDuplicatedFunction.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
    }

    public void testFunctionReferenceToFunctionDuplicatedInImport() {
        myFixture.configureByFiles("FunctionReferenceToFunctionDuplicatedInImport.xq", "FunctionReferencedFile.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertNull(resolvedReference);
    }
}
