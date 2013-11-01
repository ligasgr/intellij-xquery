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

package org.intellij.xquery.functional.reference.function;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.Lookup;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.functional.BaseFunctionalTestCase;
import org.intellij.xquery.psi.XQueryFunctionCall;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryNamedFunctionRef;
import org.intellij.xquery.functional.reference.MatchingStringCondition;

import java.util.List;

import static com.intellij.util.containers.ContainerUtil.findAll;
import static org.intellij.xquery.functional.Assertions.assertChildOf;
import static org.intellij.xquery.functional.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 03/07/13
 * Time: 21:44
 */
public class XQueryFunctionReferenceTest extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/functional/reference/function";
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

    public void testFunctionNamedReference() {
        myFixture.configureByFiles("FunctionNamedReference.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryNamedFunctionRef.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
    }

    public void testFunctionReferenceFromAnotherFile() {
        myFixture.configureByFiles("FunctionReferenceFromAnotherFile.xq", "FunctionReferencedFile.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals("FunctionReferencedFile.xq", functionDeclaration.getContainingFile().getName());
    }

    public void testFunctionReferenceToNotExistingFunction() {
        myFixture.configureByFiles("FunctionReferenceToNotExistingFunction.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertNull(resolvedReference);
    }

    public void testFunctionReferenceToDuplicatedFunction() {
        myFixture.configureByFiles("FunctionReferenceToDuplicatedFunction.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertNull(resolvedReference);
    }

    public void testFunctionReferenceToFunctionDuplicatedInImport() {
        myFixture.configureByFiles("FunctionReferenceToFunctionDuplicatedInImport.xq", "FunctionReferencedFile.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertNull(resolvedReference);
    }

    public void testFunctionReferenceFromPrefixToDefaultNamespace() {
        myFixture.configureByFile("FunctionReferenceFromPrefixToDefaultNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
    }

    public void testFunctionReferenceFromDefaultNamespaceToPrefix() {
        myFixture.configureByFile("FunctionReferenceFromDefaultNamespaceToPrefix.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
    }

    public void testFunctionReferenceFromDefaultNamespaceToDefaultNamespace() {
        myFixture.configureByFile("FunctionReferenceFromDefaultNamespaceToDefaultNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
    }

    public void testFunctionReferenceFromAnotherFileWithDefaultNamespace() {
        myFixture.configureByFiles("FunctionReferenceFromAnotherFileWithDefaultNamespace.xq",
                "FunctionReferencedFile.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals("FunctionReferencedFile.xq", functionDeclaration.getContainingFile().getName());
    }

    public void testFunctionReferenceFromAnotherFileWithoutImportedPrefix() {
        myFixture.configureByFiles("FunctionReferenceFromAnotherFileWithoutImportedPrefix.xq",
                "FunctionReferencedFile.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals("FunctionReferencedFile.xq", functionDeclaration.getContainingFile().getName());
    }

    public void testFunctionReferenceFromAnotherFileWithDefaultNamespaceToFileWithDefaultNamespace() {
        myFixture.configureByFiles("FunctionReferenceFromAnotherFileWithDefaultNamespaceToFileWithDefaultNamespace" +
                ".xq", "FunctionReferencedFileWithDefaultNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals("FunctionReferencedFileWithDefaultNamespace.xq", functionDeclaration.getContainingFile().getName
                ());
    }

    public void testFunctionReferenceFromAnotherFileWithoutImportedPrefixToFileWithDefaultNamespace() {
        myFixture.configureByFiles("FunctionReferenceFromAnotherFileWithoutImportedPrefixToFileWithDefaultNamespace.xq",
                "FunctionReferencedFileWithDefaultNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals("FunctionReferencedFileWithDefaultNamespace.xq", functionDeclaration.getContainingFile().getName
                ());
    }

    public void testFunctionReferenceFromAnotherFileWhichHasDefaultNamespace() {
        myFixture.configureByFiles("FunctionReferenceFromAnotherFileWhichHasDefaultNamespace.xq",
                "FunctionReferencedFileWithDefaultNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals("FunctionReferencedFileWithDefaultNamespace.xq", functionDeclaration.getContainingFile().getName
                ());
    }

    public void testFunctionReferenceWithDifferentArity() {
        myFixture.configureByFiles("FunctionReferenceWithDifferentArity.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals(2, functionDeclaration.getArity());
    }
}
