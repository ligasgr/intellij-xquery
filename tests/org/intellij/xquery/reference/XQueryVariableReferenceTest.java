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
import com.intellij.psi.PsiReference;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryLetBinding;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.intellij.xquery.psi.XQueryVarValue;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * User: ligasgr
 * Date: 28/06/13
 * Time: 13:25
 */
public class XQueryVariableReferenceTest extends LightCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "testData/org/intellij/xquery/reference/variable";
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
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent().getParent().getParent();
        PsiReference[] references = element.getReferences();
        PsiReference reference = references[0];
        PsiElement resolvedReference = reference.resolve();
        XQueryVarDecl varDecl = (XQueryVarDecl) resolvedReference.getParent();
        XQueryVarValue varValue = varDecl.getVarValue();
        assertEquals(".", varValue.getText());
    }

    public void testVariableReferenceOfFunctionArgument() {
        myFixture.configureByFiles("VariableReferenceInTheSameFile_FunctionArgument.xq");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent().getParent().getParent();
        PsiReference[] references = element.getReferences();
        PsiReference reference = references[0];
        PsiElement resolvedReference = reference.resolve();
        XQueryFunctionDecl functionDecl = (XQueryFunctionDecl) resolvedReference.getParent().getParent().getParent();
        assertEquals("example", functionDecl.getFunctionName().getText());
    }

    public void testVariableReferenceOfFlworExpressionReference() {
        myFixture.configureByFiles("VariableReferenceInTheSameFile_Flwor.xq");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent().getParent().getParent();
        PsiReference[] references = element.getReferences();
        PsiReference reference = references[0];
        PsiElement resolvedReference = reference.resolve();
        XQueryLetBinding letBinding = (XQueryLetBinding) resolvedReference.getParent();
        assertEquals("\"sdfsd\"", letBinding.getExprSingle().getText());
    }


    public void testVariableReferenceScopeOfGlobalVariableFromQueryBody() {
        myFixture.configureByFiles("VariableReferenceScopes_Global.xq");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent().getParent().getParent();
        PsiReference[] references = element.getReferences();
        PsiReference reference = references[0];
        PsiElement resolvedReference = reference.resolve();
        XQueryVarDecl varDecl = (XQueryVarDecl) resolvedReference.getParent();
        XQueryVarValue varValue = varDecl.getVarValue();
        assertEquals("\"argGlobal\"", varValue.getText());
    }

    public void testVariableReferenceScopeOfGlobalVariableFromAnotherGlobalVariable() {
        myFixture.configureByFiles("VariableReferenceScopes_GlobalVar.xq");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent().getParent().getParent();
        PsiReference[] references = element.getReferences();
        PsiReference reference = references[0];
        PsiElement resolvedReference = reference.resolve();
        XQueryVarDecl varDecl = (XQueryVarDecl) resolvedReference.getParent();
        XQueryVarValue varValue = varDecl.getVarValue();
        assertEquals("\"argGlobal\"", varValue.getText());
    }

    public void testVariableReferenceScopeOfFunctionArgument() {
        myFixture.configureByFiles("VariableReferenceScopes_Function.xq");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent().getParent().getParent();
        PsiReference[] references = element.getReferences();
        PsiReference reference = references[0];
        PsiElement resolvedReference = reference.resolve();
        XQueryFunctionDecl functionDecl = (XQueryFunctionDecl) resolvedReference.getParent().getParent().getParent();
        assertEquals("example", functionDecl.getFunctionName().getText());
    }

    public void testVariableReferenceScopeOfLocalVariableReference() {
        myFixture.configureByFiles("VariableReferenceScopes_Local.xq");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent().getParent().getParent();
        PsiReference[] references = element.getReferences();
        PsiReference reference = references[0];
        PsiElement resolvedReference = reference.resolve();
        XQueryLetBinding letBinding = (XQueryLetBinding) resolvedReference.getParent();
        assertEquals("\"argLocal\"", letBinding.getExprSingle().getText());
    }

    public void testVariableReferenceFromAnotherFile() {
        myFixture.configureByFiles("VariableReferenceFromAnotherFile.xq","VariableReferencedFile.xq");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent().getParent().getParent();
        PsiReference[] references = element.getReferences();
        PsiReference reference = references[0];
        PsiElement resolvedReference = reference.resolve();
        XQueryVarDecl varDecl = (XQueryVarDecl) resolvedReference.getParent();
        XQueryVarValue varValue = varDecl.getVarValue();
        assertEquals("\"yeah\"", varValue.getText());
        assertEquals("VariableReferencedFile.xq", varDecl.getContainingFile().getName());
    }
}