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

package org.intellij.xquery.reference.namespace;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.XQueryBaseTestCase;
import org.intellij.xquery.psi.XQueryFunctionNamespace;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.intellij.xquery.psi.XQueryNamespaceDecl;

import java.util.List;

import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 06/07/13
 * Time: 01:13
 */
public class XQueryFunctionNamespaceNameReferenceTest extends XQueryBaseTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/reference/namespace";
    }

    public void testFunctionNamespaceCompletion() {
        myFixture.configureByFiles("FunctionNamespaceNameCompletion.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertEquals(0, strings.size());
    }

    public void testFunctionNamespaceReferenceForModuleDeclaration() {
        myFixture.configureByFiles("FunctionNamespaceNameReference_Module.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionNamespace.class);

        assertChildOf(resolvedReference, XQueryModuleDecl.class);
    }

    public void testFunctionNamespaceReferenceForModuleImport() {
        myFixture.configureByFiles("FunctionNamespaceNameReference_Import.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionNamespace.class);

        assertChildOf(resolvedReference, XQueryModuleImport.class);
    }

    public void testFunctionNamespaceReferenceForNamespaceDeclaration() {
        myFixture.configureByFiles("FunctionNamespaceNameReference_Declaration.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionNamespace.class);

        assertChildOf(resolvedReference, XQueryNamespaceDecl.class);
    }

    public void testFunctionNamespaceRename() {
        myFixture.configureByFiles("FunctionNamespaceNameRename.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("FunctionNamespaceNameRename.xq", "FunctionNamespaceNameRenameAfter.xq", false);
    }

    public void testFunctionNamespaceReferenceToNotExistingNamespace() {
        myFixture.configureByFiles("FunctionNamespaceReferenceToNotExistingNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionNamespace.class);

        assertNull(resolvedReference);
    }

    public void testFunctionNamespaceReferenceToDuplicatedNamespace() {
        myFixture.configureByFiles("FunctionNamespaceReferenceToDuplicatedNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryFunctionNamespace.class);

        assertNull(resolvedReference);
    }
}
