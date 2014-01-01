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
import org.intellij.xquery.psi.XQueryNamespaceDecl;
import org.intellij.xquery.psi.XQueryPrefix;

import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 02/12/13
 * Time: 13:56
 */
public class XQueryQNamePrefixReferenceTest extends BaseFunctionalTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/reference/namespace";
    }

    public void testQNamePrefixReferenceForNameTest() {
        myFixture.configureByFiles("QNamePrefixReferenceForNameTest.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryNamespaceDecl.class);
    }

    public void testQNamePrefixReferenceForNameTestWithWildcard() {
        myFixture.configureByFiles("QNamePrefixReferenceForNameTestWithWildcard.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryNamespaceDecl.class);
    }

    public void testQNamePrefixReferenceForItemType() {
        myFixture.configureByFiles("QNamePrefixReferenceForItemType.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryNamespaceDecl.class);
    }

    public void testQNamePrefixReferenceForConstructor() {
        myFixture.configureByFiles("QNamePrefixReferenceForConstructor.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryPrefix.class);

        assertChildOf(resolvedReference, XQueryNamespaceDecl.class);
    }

    public void testQNamePrefixRename() {
        myFixture.configureByFiles("QNamePrefixRename.xq");
        myFixture.renameElementAtCaret("aaa");
        myFixture.checkResultByFile("QNamePrefixRename.xq", "QNamePrefixRenameAfter.xq", false);
    }
}
