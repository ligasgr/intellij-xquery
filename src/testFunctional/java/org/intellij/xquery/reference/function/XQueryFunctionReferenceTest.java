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

package org.intellij.xquery.reference.function;

import com.intellij.psi.PsiElement;
import com.intellij.refactoring.actions.SafeDeleteAction;
import org.intellij.xquery.Assertions;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.XQueryFlavour;
import org.intellij.xquery.psi.XQueryArrowFunctionReference;
import org.intellij.xquery.psi.XQueryFunctionCall;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryNamedFunctionRef;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.intellij.xquery.psi.XQueryVarRef;
import org.intellij.xquery.settings.XQuerySettings;

import static com.intellij.psi.util.PsiTreeUtil.getParentOfType;
import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 03/07/13
 * Time: 21:44
 */
public class XQueryFunctionReferenceTest extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/reference/function";
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

    public void testFunctionReferenceInArrowWithOneArgument() {
        myFixture.configureByFiles("FunctionReferenceInArrowWithOneArgument.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryArrowFunctionReference.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals(1, functionDeclaration.getArity());
    }

    public void testFunctionReferenceInArrowWithTwoArguments() {
        myFixture.configureByFiles("FunctionReferenceInArrowWithTwoArguments.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryArrowFunctionReference.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals(2, functionDeclaration.getArity());
    }

    public void testFunctionReferenceFromAnotherFileWhenMarkLogicFlavourUsed() {
        XQuerySettings settings = XQuerySettings.getInstance(myFixture.getProject());
        XQueryFlavour previous = settings.getFlavour();
        settings.setFlavour(XQueryFlavour.MARKLOGIC);
        myFixture.configureByFiles("MarkLogicReferenceFromAnotherFile.xq", "MarkLogicReferencedFile.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);

        settings.setFlavour(previous);
        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) resolvedReference.getParent();
        assertEquals("MarkLogicReferencedFile.xq", functionDeclaration.getContainingFile().getName());
    }

    public void testFunctionSafeDelete() {
        myFixture.configureByFiles("FunctionSafeDelete.xq");
        myFixture.testAction(new SafeDeleteAction());
        myFixture.checkResultByFile("FunctionSafeDelete.xq", "FunctionSafeDeleteAfter.xq", false);
    }
}
