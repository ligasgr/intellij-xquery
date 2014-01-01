/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.reference.namespace;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.intellij.xquery.psi.XQueryNamespaceDecl;
import org.intellij.xquery.psi.XQueryPrefix;

import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 06/07/13
 * Time: 01:13
 */
public class XQueryFunctionPrefixReferenceTest extends BaseFunctionalTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/reference/namespace";
    }

    public void testFunctionPrefixReferenceForModuleDeclaration() {
        myFixture.configureByFiles("FunctionPrefixReference_Module.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryModuleDecl.class);
    }

    public void testFunctionPrefixReferenceForModuleImport() {
        myFixture.configureByFiles("FunctionPrefixReference_Import.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryModuleImport.class);
    }

    public void testFunctionPrefixReferenceForNamespaceDeclaration() {
        myFixture.configureByFiles("FunctionPrefixReference_Declaration.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryNamespaceDecl.class);
    }

    public void testFunctionPrefixRename() {
        myFixture.configureByFiles("FunctionPrefixRename.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("FunctionPrefixRename.xq", "FunctionPrefixRenameAfter.xq", false);
    }

    public void testFunctionPrefixReferenceToNotExistingNamespace() {
        myFixture.configureByFiles("FunctionPrefixReferenceToNotExistingNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertNull(resolvedReference);
    }

    public void testFunctionPrefixReferenceToDuplicatedNamespace() {
        myFixture.configureByFiles("FunctionPrefixReferenceToDuplicatedNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertNull(resolvedReference);
    }

    public void testFunctionPrefixReferenceForPredeclaredNamespace() {
        myFixture.configureByFiles("FunctionPrefixReferenceForPredeclaredNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        PsiElement originalElement = myFixture.getFile().findElementAt(myFixture.getCaretOffset());
        XQueryPrefix sourceOfReference = PsiTreeUtil.getParentOfType(originalElement, XQueryPrefix.class);
        assertEquals(resolvedReference, sourceOfReference);
    }
}
