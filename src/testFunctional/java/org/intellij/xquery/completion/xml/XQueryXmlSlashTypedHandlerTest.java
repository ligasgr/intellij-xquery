/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.completion.xml;

import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.XQueryFileType;

public class XQueryXmlSlashTypedHandlerTest extends BaseFunctionalTestCase {

    public void testClosingEmptyTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a<caret>");
        myFixture.type("/");
        myFixture.checkResult("<a/><caret>");
    }

    public void testClosingEmptyTagWithAttributes() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a b=\"c\"<caret>");
        myFixture.type("/");
        myFixture.checkResult("<a b=\"c\"/><caret>");
    }

    public void testSkipsWhenSlashAlreadyPresentInEmptyTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a<caret>/>");
        myFixture.type("/");
        myFixture.checkResult("<a/<caret>>");
    }

    public void testClosingFullTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a><<caret>");
        myFixture.type("/");
        myFixture.checkResult("<a></a><caret>");
    }

    public void testClosingFullTagWithContentsAndAttributes() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a b=\"c\">def<<caret>");
        myFixture.type("/");
        myFixture.checkResult("<a b=\"c\">def</a><caret>");
    }

    public void testOnlyInsertsSlashWhenNotInXml() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "");
        myFixture.type("/");
        myFixture.checkResult("/");
    }

    public void testOnlyInsertsSlashWhenInXpath() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "/abc<caret>");
        myFixture.type("/");
        myFixture.checkResult("/abc/");
    }

    public void testOnlyInsertsSlashInXpathInsideOfXmlContent() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<abc>/abc<caret></abc>");
        myFixture.type("/");
        myFixture.checkResult("<abc>/abc/</abc>");
    }

    public void testOnlyInsertsSlashInXpathInsideOfEnclosedExpression() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<abc>{/abc<caret>}</abc>");
        myFixture.type("/");
        myFixture.checkResult("<abc>{/abc/}</abc>");
    }

    public void testClosingEmptyTagInsideOfAnotherTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<abc><a<caret></abc>");
        myFixture.type("/");
        myFixture.checkResult("<abc><a/><caret></abc>");
    }

    public void testClosingEmptyTagInsideOfAnotherTagWithSameName() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a><a<caret></a>");
        myFixture.type("/");
        myFixture.checkResult("<a><a/><caret></a>");
    }

    public void testClosingEmptyTagInsideOfEnclosedExpressionInsideOfAnotherTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a>{<a<caret>}</a>");
        myFixture.type("/");
        myFixture.checkResult("<a>{<a/><caret>}</a>");
    }

    public void testClosingFullTagInsideOfEnclosedExpressionInsideOfAnotherTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a>{<a><<caret>}</a>");
        myFixture.type("/");
        myFixture.checkResult("<a>{<a></a><caret>}</a>");
    }

    public void testOnlyInsertsSlashInXpathInsideOfEnclosedExpressionWhichIsInsideOfATagInExpression() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<abc>{<def>{/abc<caret>}</def>}</abc>");
        myFixture.type("/");
        myFixture.checkResult("<abc>{<def>{/abc/}</def>}</abc>");
    }

    public void testClosingFullTagWithNamespacePrefix() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<html:p><<caret>");
        myFixture.type("/");
        myFixture.checkResult("<html:p></html:p><caret>");
    }
}
