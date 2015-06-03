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

package org.intellij.xquery.reference.xml;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.hamcrest.CoreMatchers;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.intellij.xquery.psi.XQueryNamespaceDecl;
import org.intellij.xquery.psi.XQueryXmlEmptyTag;
import org.intellij.xquery.psi.XQueryXmlFullTag;
import org.intellij.xquery.psi.XQueryXmlTagName;
import org.intellij.xquery.psi.XQueryXmlTagNamespace;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;
import static org.junit.Assert.assertThat;

public class XQueryXmlTagNameReferenceTest extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/reference/xml";
    }

    public void testOpeningXmlTagNameReferenceToSelfInFullTag() {
        myFixture.configureByFiles("OpeningXmlTagNameReferenceToSelfInFullTag.xq");
        verifyReferencesSelf();
    }

    public void testClosingXmlTagNameReferenceInFullTag() {
        myFixture.configureByFiles("ClosingXmlTagNameReferenceInFullTag.xq");
        verifyNotReferencingSelf();
    }

    public void testOpeningXmlTagNameReferenceToSelfInEmptyTag() {
        myFixture.configureByFiles("OpeningXmlTagNameReferenceToSelfInEmptyTag.xq");
        verifyReferencesSelf();
    }

    public void testXmlTagNameReferenceToInFullTagWithNamespace() {
        myFixture.configureByFiles("XmlTagNameReferenceToInFullTagWithNamespace.xq");
        verifyReferencesSelf();
    }

    private void verifyReferencesSelf() {
        PsiElement sourceOfReference = findSourceOfReference();

        XQueryXmlTagName resolvedXmlTagName = getResolvedXmlTagName();
        assertThat(resolvedXmlTagName, is(sourceOfReference));
    }

    private void verifyNotReferencingSelf() {
        PsiElement sourceOfReference = findSourceOfReference();

        XQueryXmlTagName resolvedXmlTagName = getResolvedXmlTagName();
        assertThat(resolvedXmlTagName, is(not(sourceOfReference)));
    }

    private XQueryXmlTagName getResolvedXmlTagName() {
        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryXmlTagName.class);

        return getXmlTagName(resolvedReference);
    }

    private XQueryXmlTagName getXmlTagName(PsiElement resolvedReference) {
        return PsiTreeUtil.getParentOfType(resolvedReference, XQueryXmlTagName.class, false);
    }

    private PsiElement findSourceOfReference() {
        return getXmlTagName(myFixture.getFile().findElementAt(myFixture.getCaretOffset()));
    }
}
