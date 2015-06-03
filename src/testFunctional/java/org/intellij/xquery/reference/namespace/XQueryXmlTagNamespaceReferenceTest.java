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
import org.intellij.xquery.psi.XQueryXmlTagNamespace;

import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

public class XQueryXmlTagNamespaceReferenceTest  extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/reference/namespace";
    }

    public void testXmlTagNamespaceReferenceForModuleDeclaration() {
        myFixture.configureByFiles("XmlTagNamespaceReference_Module.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryXmlTagNamespace.class);

        assertChildOf(resolvedReference, XQueryModuleDecl.class);
    }

    public void testXmlTagNamespaceReferenceForModuleImport() {
        myFixture.configureByFiles("XmlTagNamespaceReference_Import.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryXmlTagNamespace.class);

        assertChildOf(resolvedReference, XQueryModuleImport.class);
    }

    public void testXmlTagNamespaceReferenceForNamespaceDeclaration() {
        myFixture.configureByFiles("XmlTagNamespaceReference_Declaration.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryXmlTagNamespace.class);

        assertChildOf(resolvedReference, XQueryNamespaceDecl.class);
    }

    public void testXmlTagNamespaceReferenceForRootXmlElementXmlns() {
        myFixture.configureByFiles("XmlTagNamespaceReference_RootXmlElementXmlns.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryXmlTagNamespace.class);

        assertChildOf(resolvedReference, XQueryXmlFullTag.class);
    }

    public void testXmlTagNamespaceReferenceForSameXmlElementXmlns() {
        myFixture.configureByFiles("XmlTagNamespaceReference_SameXmlElementXmlns.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryXmlTagNamespace.class);

        assertChildOf(resolvedReference, XQueryXmlEmptyTag.class);
    }

    public void testXmlTagNamespaceRename() {
        myFixture.configureByFiles("XmlTagNamespaceRename.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("XmlTagNamespaceRename.xq", "XmlTagNamespaceRenameAfter.xq", false);
    }

    public void testXmlTagNamespaceRenameForRootXmlElementXmlns() {
        myFixture.configureByFiles("XmlTagNamespaceRename_RootXmlElementXmlns.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("XmlTagNamespaceRename_RootXmlElementXmlns.xq", "XmlTagNamespaceRenameAfter_RootXmlElementXmlns.xq", false);
    }

    public void testXmlTagNamespaceRenameForSameXmlElementXmlns() {
        myFixture.configureByFiles("XmlTagNamespaceRename_SameXmlElementXmlns.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("XmlTagNamespaceRename_SameXmlElementXmlns.xq", "XmlTagNamespaceRenameAfter_SameXmlElementXmlns.xq", false);
    }

    public void testXmlTagNamespaceReferenceToNotExistingNamespace() {
        myFixture.configureByFiles("XmlTagNamespaceReferenceToNotExistingNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryXmlTagNamespace.class);

        assertNull(resolvedReference);
    }

    public void testXmlTagNamespaceReferenceToDuplicatedNamespace() {
        myFixture.configureByFiles("XmlTagNamespaceReferenceToDuplicatedNamespace.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryXmlTagNamespace.class);

        assertNull(resolvedReference);
    }
}
