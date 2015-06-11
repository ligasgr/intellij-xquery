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

public class XQueryXmlGtTypedHandlerTest extends BaseFunctionalTestCase {

    public void testSkipsWhenGtAlreadyPresentInEmptyTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a/<caret>>");
        myFixture.type(">");
        myFixture.checkResult("<a/><caret>");
    }

    public void testClosingFullTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a<caret>");
        myFixture.type(">");
        myFixture.checkResult("<a><caret></a>");
    }

    public void testClosingFullTagWithAttributes() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a b=\"c\"<caret>");
        myFixture.type(">");
        myFixture.checkResult("<a b=\"c\"><caret></a>");
    }

    public void testSkipsWhenGtAlreadyPresentInEndOfOpeningTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a<caret>></a>");
        myFixture.type(">");
        myFixture.checkResult("<a><caret></a>");
    }

    public void testSkipsWhenGtAlreadyPresentInEndOfClosingTag() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "<a></a<caret>>");
        myFixture.type(">");
        myFixture.checkResult("<a></a><caret>");
    }

    public void testOnlyInsertsGtWhenNotInXml() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "");
        myFixture.type(">");
        myFixture.checkResult(">");
    }

    public void testOnlyInsertsSlashWhenInEquation() {
        myFixture.configureByText(XQueryFileType.INSTANCE, "1 <caret>");
        myFixture.type(">");
        myFixture.checkResult("1 ><caret>");
    }
}
