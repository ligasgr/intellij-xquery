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
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import org.intellij.xquery.psi.*;

import java.util.List;

import static com.intellij.psi.util.PsiTreeUtil.getParentOfType;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.intellij.xquery.reference.ReferenceUtil.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.assertNotChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 28/06/13
 * Time: 13:25
 */
public class XQueryVariableReferenceTest extends LightPlatformCodeInsightFixtureTestCase {
    public XQueryVariableReferenceTest() {
        super();
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/reference/variable";
    }

    public void testVariableCompletionInTheSameFile() {
        myFixture.configureByFiles("VariableCompletionInTheSameFile.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(asList("globalScopeVar", "functionArgumentScopeVar", "flworScopeVar")));
        assertEquals(3, strings.size());
    }

    public void testVariableCompletionInTheSameFileWithScopesChecking() {
        myFixture.configureByFiles("VariableCompletionInTheSameFileScopes.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(asList("anotherOne", "globalScopeVar", "scope:globalScopeVar", "functionArgumentScopeVar", "newOne", "p", "rank", "someVar")));
        assertEquals(8, strings.size());
    }

    public void testVariableRenameInTheSameFile() {
        myFixture.configureByFiles("VariableRenameInTheSameFile.xq");
        myFixture.renameElementAtCaret("endAfterChanges");
        myFixture.checkResultByFile("VariableRenameInTheSameFile.xq", "VariableRenameInTheSameFileAfter.xq", false);
    }

    public void testVariableReferenceOfGlobalVariable() {
        myFixture.configureByFiles("VariableReferenceInTheSameFile_Global.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryVarRef.class);

        assertChildOf(resolvedReference, XQueryVarDecl.class);
        assertNotChildOf(resolvedReference, XQueryVarRef.class);
    }

    public void testVariableReferenceOfFunctionArgument() {
        myFixture.configureByFiles("VariableReferenceInTheSameFile_FunctionArgument.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryVarRef.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        assertNotChildOf(resolvedReference, XQueryVarRef.class);
    }

    public void testVariableReferenceOfFlworExpressionReference() {
        myFixture.configureByFiles("VariableReferenceInTheSameFile_Flwor.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryVarRef.class);

        assertChildOf(resolvedReference, XQueryLetBinding.class);
        assertNotChildOf(resolvedReference, XQueryVarRef.class);
    }

    public void testVariableReferenceScopeOfGlobalVariableFromQueryBody() {
        myFixture.configureByFiles("VariableReferenceScopes_Global.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryVarRef.class);

        assertChildOf(resolvedReference, XQueryVarDecl.class);
        assertNotChildOf(resolvedReference, XQueryVarRef.class);
    }

    public void testVariableReferenceScopeOfGlobalVariableFromAnotherGlobalVariable() {
        myFixture.configureByFiles("VariableReferenceScopes_GlobalVar.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryVarRef.class);

        assertChildOf(resolvedReference, XQueryVarDecl.class);
        assertNotChildOf(resolvedReference, XQueryVarRef.class);
    }


    public void testVariableReferenceScopeOfFunctionArgument() {
        myFixture.configureByFiles("VariableReferenceScopes_Function.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryVarRef.class);

        assertChildOf(resolvedReference, XQueryFunctionDecl.class);
        assertNotChildOf(resolvedReference, XQueryVarRef.class);
    }

    public void testVariableReferenceScopeOfLocalVariableReference() {
        myFixture.configureByFiles("VariableReferenceScopes_Local.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryVarRef.class);

        assertChildOf(resolvedReference, XQueryLetBinding.class);
        assertNotChildOf(resolvedReference, XQueryVarRef.class);
    }

    public void testVariableReferenceFromAnotherFile() {
        myFixture.configureByFiles("VariableReferenceFromAnotherFile.xq", "VariableReferencedFile.xq");
        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryVarRef.class);

        assertChildOf(resolvedReference, XQueryVarDecl.class);
        XQueryVarDecl varDecl = getParentOfType(resolvedReference, XQueryVarDecl.class);
        assertEquals("VariableReferencedFile.xq", varDecl.getContainingFile().getName());
    }
}