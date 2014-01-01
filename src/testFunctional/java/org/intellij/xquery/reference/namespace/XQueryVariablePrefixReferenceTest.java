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
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.intellij.xquery.psi.XQueryNamespaceDecl;
import org.intellij.xquery.psi.XQueryPrefix;

import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 03/07/13
 * Time: 13:49
 */
public class XQueryVariablePrefixReferenceTest extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/reference/namespace";
    }

    public void testVariablePrefixReferenceForModuleDeclaration() {
        myFixture.configureByFiles("VariablePrefixReference_Module.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryModuleDecl.class);
    }

    public void testVariablePrefixReferenceForModuleImport() {
        myFixture.configureByFiles("VariablePrefixReference_Import.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryModuleImport.class);
    }

    public void testVariablePrefixReferenceForNamespaceDeclaration() {
        myFixture.configureByFiles("VariablePrefixReference_Declaration.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryNamespaceDecl.class);
    }

    public void testVariablePrefixRename() {
        myFixture.configureByFiles("VariablePrefixRename.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("VariablePrefixRename.xq", "VariablePrefixRenameAfter.xq", false);
    }

    public void testVariablePrefixReferenceToNotExistingNamespace() {
        myFixture.configureByFiles("VariablePrefixReferenceToNotExistingNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertNull(resolvedReference);
    }

    public void testVariablePrefixReferenceToDuplicatedNamespace() {
        myFixture.configureByFiles("VariablePrefixReferenceToDuplicatedNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertNull(resolvedReference);
    }
}