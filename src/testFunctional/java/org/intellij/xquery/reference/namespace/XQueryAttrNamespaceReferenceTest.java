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
import org.intellij.xquery.psi.XQueryXmlEmptyTag;
import org.intellij.xquery.psi.XQueryXmlFullTag;
import org.intellij.xquery.psi.XQueryAttrNamespace;

import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

public class XQueryAttrNamespaceReferenceTest extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/reference/namespace";
    }

    public void testAttrNamespaceReferenceForModuleDeclaration() {
        myFixture.configureByFiles("AttrNamespaceReference_Module.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryAttrNamespace.class);

        assertChildOf(resolvedReference, XQueryModuleDecl.class);
    }

    public void testAttrNamespaceReferenceForModuleImport() {
        myFixture.configureByFiles("AttrNamespaceReference_Import.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryAttrNamespace.class);

        assertChildOf(resolvedReference, XQueryModuleImport.class);
    }

    public void testAttrNamespaceReferenceForNamespaceDeclaration() {
        myFixture.configureByFiles("AttrNamespaceReference_Declaration.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryAttrNamespace.class);

        assertChildOf(resolvedReference, XQueryNamespaceDecl.class);
    }

    public void testAttrNamespaceReferenceForRootXmlElementXmlns() {
        myFixture.configureByFiles("AttrNamespaceReference_RootXmlElementXmlns.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryAttrNamespace.class);

        assertChildOf(resolvedReference, XQueryXmlFullTag.class);
    }

    public void testAttrNamespaceReferenceForSameXmlElementXmlns() {
        myFixture.configureByFiles("AttrNamespaceReference_SameXmlElementXmlns.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryAttrNamespace.class);

        assertChildOf(resolvedReference, XQueryXmlEmptyTag.class);
    }

    public void testAttrNamespaceRename() {
        myFixture.configureByFiles("AttrNamespaceRename.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("AttrNamespaceRename.xq", "AttrNamespaceRenameAfter.xq", false);
    }

    public void testAttrNamespaceRenameForRootXmlElementXmlns() {
        myFixture.configureByFiles("AttrNamespaceRename_RootXmlElementXmlns.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("AttrNamespaceRename_RootXmlElementXmlns.xq", "AttrNamespaceRenameAfter_RootXmlElementXmlns.xq", false);
    }

    public void testAttrNamespaceRenameForSameXmlElementXmlns() {
        myFixture.configureByFiles("AttrNamespaceRename_SameXmlElementXmlns.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("AttrNamespaceRename_SameXmlElementXmlns.xq", "AttrNamespaceRenameAfter_SameXmlElementXmlns.xq", false);
    }

    public void testAttrNamespaceReferenceToNotExistingNamespace() {
        myFixture.configureByFiles("AttrNamespaceReferenceToNotExistingNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryAttrNamespace.class);

        assertNull(resolvedReference);
    }

    public void testAttrNamespaceReferenceToDuplicatedNamespace() {
        myFixture.configureByFiles("AttrNamespaceReferenceToDuplicatedNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryAttrNamespace.class);

        assertNull(resolvedReference);
    }
}
