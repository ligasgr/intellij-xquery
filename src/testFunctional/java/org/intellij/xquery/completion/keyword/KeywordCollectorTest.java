/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.completion.keyword;

import com.intellij.codeInsight.completion.CompletionType;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.XQueryFileType;

import java.util.List;

public class KeywordCollectorTest extends BaseFunctionalTestCase {

    public void testForEmptyFile() throws Exception {
        verifyKeywords("<caret>", "xquery", "ancestor", "ancestor-or-self", "attribute", "binary", "child", "comment", "copy", "declare", "delete", "descendant", "descendant-or-self", "document", "document-node", "element", "every", "following", "following-sibling", "for", "function", "if", "import", "insert", "let", "map", "module", "namespace", "namespace-node", "node", "ordered", "parent", "preceding", "preceding-sibling", "processing-instruction", "rename", "replace", "schema-attribute", "schema-element", "self", "some", "switch", "text", "try");
    }

    public void testForXQueryBeginning() throws Exception {
        verifyKeywords("xquery <caret>", "version", "encoding");
    }

    public void testForXQueryVersion() throws Exception {
        verifyKeywords("xquery version '1.0' <caret>", "encoding");
    }

    public void testForDeclare() throws Exception {
        verifyKeywords("declare <caret>", "boundary-space", "default", "base-uri", "construction", "ordering", "copy-namespaces", "decimal-format", "namespace", "updating", "private", "variable", "function", "context", "option", "revalidation");
    }

    public void testForModule() throws Exception {
        verifyKeywords("module <caret>", "namespace");
    }

    public void testForImport() throws Exception {
        verifyKeywords("import <caret>", "module", "schema");
    }

    public void testForDeclareDefault() throws Exception {
        verifyKeywords("declare default <caret>", "collation", "order", "order empty", "decimal-format", "element", "element namespace", "function", "function namespace");
    }

    public void testForSimpleExpression() throws Exception {
        verifyKeywords("1 <caret>", "mod", "or", "intersect", "is", "union", "cast", "cast as", "treat", "treat as", "div", "and", "instance", "instance of", "idiv", "except", "to", "castable", "castable as");
    }

    private void verifyKeywords(String text, String... expected) {
        myFixture.configureByText(XQueryFileType.INSTANCE, text);
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> found = myFixture.getLookupElementStrings();
        assertKeywordsFound(found, expected);
    }

    private void assertKeywordsFound(List<String> found, String... expected) {
        for (String keyword : expected) {
            assertTrue(String.format("Keyword %s missing", keyword), found.contains(keyword));
        }
    }
}
