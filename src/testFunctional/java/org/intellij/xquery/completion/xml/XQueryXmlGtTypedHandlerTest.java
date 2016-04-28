/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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
        assertResultAfterCompletion("<a/<caret>>", "<a/><caret>");
    }

    public void testClosingFullTag() {
        assertResultAfterCompletion("<a<caret>", "<a><caret></a>");
    }

    public void testClosingFullTagWithAttributes() {
        assertResultAfterCompletion("<a b=\"c\"<caret>", "<a b=\"c\"><caret></a>");
    }

    public void testSkipsWhenGtAlreadyPresentInEndOfOpeningTag() {
        assertResultAfterCompletion("<a<caret>></a>", "<a><caret></a>");
    }

    public void testSkipsWhenGtAlreadyPresentInEndOfClosingTag() {
        assertResultAfterCompletion("<a></a<caret>>", "<a></a><caret>");
    }

    public void testOnlyInsertsGtWhenNotInXml() {
        assertResultAfterCompletion("", ">");
    }

    public void testOnlyInsertsSlashWhenInEquation() {
        assertResultAfterCompletion("1 <caret>", "1 ><caret>");
    }

    public void testClosingFullTagWithNamespaceName() {
        assertResultAfterCompletion("<html:a<caret>", "<html:a><caret></html:a>");
    }

    public void testClosingFullTagInsideOfDeeplyNestedExpression() {
        assertResultAfterCompletion("xquery version '1.0-ml';\n" +
                "declare function local:test($value) {()};\n" +
                "\n" +
                "declare function local:foo() {\n" +
                "    try {\n" +
                "        local:test(<a></a>)\n" +
                "    } catch ($e) {\n" +
                "        local:test(<a<caret>)\n" +
                "    }\n" +
                "};\n" +
                "\n" +
                "local:test(<a></a>)", "xquery version '1.0-ml';\n" +
                        "declare function local:test($value) {()};\n" +
                        "\n" +
                        "declare function local:foo() {\n" +
                        "    try {\n" +
                        "        local:test(<a></a>)\n" +
                        "    } catch ($e) {\n" +
                        "        local:test(<a></a>)\n" +
                        "    }\n" +
                        "};\n" +
                        "\n" +
                        "local:test(<a></a>)");
    }

    public void testNotClosingTagsWithTypos() {
        assertResultAfterCompletion("<test><<caret></test>", "<test><><caret></test>");
        assertResultAfterCompletion("<test><inner<<caret></test>", "<test><inner<><caret></test>");
    }

    private void assertResultAfterCompletion(String input, String output) {
        myFixture.configureByText(XQueryFileType.INSTANCE, input);
        myFixture.type(">");
        myFixture.checkResult(output);
    }
}
