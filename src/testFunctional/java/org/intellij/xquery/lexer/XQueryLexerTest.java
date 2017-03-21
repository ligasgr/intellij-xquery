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

package org.intellij.xquery.lexer;

import com.intellij.lexer.Lexer;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.jetbrains.annotations.NonNls;

public class XQueryLexerTest extends BaseFunctionalTestCase {

    private static void assertProducedTokens(@NonNls String text, @NonNls String[] expectedTokens) {
        Lexer lexer = new XQueryLexer();
        assertProducedTokens(text, expectedTokens, lexer);
    }

    private static void assertProducedTokens(String text, String[] expectedTokens, Lexer lexer) {
        lexer.start(text);
        int idx = 0;
        while (lexer.getTokenType() != null) {
            if (idx >= expectedTokens.length) fail("Too many tokens");
            String tokenName = lexer.getTokenType().toString();
            String expectedTokenType = expectedTokens[idx++];
            String expectedTokenText = expectedTokens[idx++];
            assertEquals(expectedTokenType, tokenName);
            String tokenText = lexer.getBufferSequence().subSequence(lexer.getTokenStart(),
                    lexer.getTokenEnd()).toString();
            assertEquals(expectedTokenText, tokenText);
            lexer.advance();
        }

        if (idx < expectedTokens.length) fail("Not enough tokens");
    }

    public void testNCName() throws Exception {
        assertProducedTokens("xxx", new String[]{
                "WHITE_SPACE", "",
                "NCName", "xxx"});
    }

    public void testQName() throws Exception {
        assertProducedTokens("xxx:yyy", new String[]{
                "WHITE_SPACE", "",
                "NCName", "xxx",
                ":", ":",
                "NCName", "yyy"});
    }

    public void testLiterals() throws Exception {
        assertProducedTokens("123,456.78", new String[]{
                "WHITE_SPACE", "",
                "IntegerLiteral", "123",
                ",", ",",
                "DecimalLiteral", "456.78"
        });
    }

    public void testAposStringLiteral() throws Exception {
        assertProducedTokens("'abc'", new String[] {
                "WHITE_SPACE", "",
                "OpeningApos", "'",
                "StringChar", "abc",
                "ClosingApos", "'"
        });
    }

    public void testQuotStringLiteral() throws Exception {
        assertProducedTokens("\"abc\"", new String[] {
                "WHITE_SPACE", "",
                "OpeningQuot", "\"",
                "StringChar", "abc",
                "ClosingQuot", "\""
        });
    }

    public void testAposStringLiteralWithAdditionalStuff() throws Exception {
        assertProducedTokens("'abc&#xa0;&#123;&amp;def'", new String[] {
                "WHITE_SPACE", "",
                "OpeningApos", "'",
                "StringChar", "abc",
                "CharRef", "&#xa0;",
                "CharRef", "&#123;",
                "PredefinedEntityRef", "&amp;",
                "StringChar", "def",
                "ClosingApos", "'"
        });
    }

    public void testQuotStringLiteralWithAdditionalStuff() throws Exception {
        assertProducedTokens("\"abc&#xa0;&#123;&amp;def\"", new String[] {
                "WHITE_SPACE", "",
                "OpeningQuot", "\"",
                "StringChar", "abc",
                "CharRef", "&#xa0;",
                "CharRef", "&#123;",
                "PredefinedEntityRef", "&amp;",
                "StringChar", "def",
                "ClosingQuot", "\""
        });
    }

    public void testIncompleteQuotStringLiteral() throws Exception {
        assertProducedTokens("'abc", new String[] {
                "WHITE_SPACE", "",
                "OpeningApos", "'",
                "StringChar", "abc",
        });
    }

    public void testIncompleteAposStringLiteral() throws Exception {
        assertProducedTokens("'abc", new String[] {
                "WHITE_SPACE", "",
                "OpeningApos", "'",
                "StringChar", "abc",
        });
    }

    public void testExpressionComment() throws Exception {
        assertProducedTokens("(: content :)", new String[]{
                "WHITE_SPACE", "",
                "ExprCommentStart", "(:",
                "ExprCommentContent", " content ",
                "ExprCommentEnd", ":)"
        });
    }

    public void testNestedExpressionComment() throws Exception {
        assertProducedTokens("(: con(:t:)ent :)", new String[]{
                "WHITE_SPACE", "",
                "ExprCommentStart", "(:",
                "ExprCommentContent", " con",
                "ExprCommentStart", "(:",
                "ExprCommentContent", "t",
                "ExprCommentEnd", ":)",
                "ExprCommentContent", "ent ",
                "ExprCommentEnd", ":)"
        });
    }

    public void testXmlContent() throws Exception {
        assertProducedTokens("<tag attr='val'>content</tag>", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", " ",
                "AttrNCName", "attr",
                "AttrEqual", "=",
                "OpeningApos", "'",
                "Char", "val",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "ElementContentChar", "content",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "tag",
                "XmlTagEnd", ">"
        });
    }

    public void testDirectComment() throws Exception {
        assertProducedTokens("<tag attr='val'><!--content--></tag>", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", " ",
                "AttrNCName", "attr",
                "AttrEqual", "=",
                "OpeningApos", "'",
                "Char", "val",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "<!--", "<!--",
                "DirCommentChar", "content",
                "-->", "-->",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "tag",
                "XmlTagEnd", ">"
        });
    }

    public void testDirectCommentWithoutSurroundingTag() throws Exception {
        assertProducedTokens("<!--content-->", new String[]{
                "WHITE_SPACE", "",
                "<!--", "<!--",
                "DirCommentChar", "content",
                "-->", "-->",
        });
    }

    public void testXmlContentWithCharacterRefs() throws Exception {
        assertProducedTokens("<tag attr='x&#xa0;&#123;y'>x&#xa0;&#123;y</tag>", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", " ",
                "AttrNCName", "attr",
                "AttrEqual", "=",
                "OpeningApos", "'",
                "Char", "x",
                "CharRef", "&#xa0;",
                "CharRef", "&#123;",
                "Char", "y",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "ElementContentChar", "x",
                "CharRef", "&#xa0;",
                "CharRef", "&#123;",
                "ElementContentChar", "y",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "tag",
                "XmlTagEnd", ">"
        });
    }

    public void testXmlContentWithPredefinedRefs() throws Exception {
        assertProducedTokens("<tag attr='x&amp;&nbsp;y'>x&amp;&nbsp;y</tag>", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", " ",
                "AttrNCName", "attr",
                "AttrEqual", "=",
                "OpeningApos", "'",
                "Char", "x",
                "PredefinedEntityRef", "&amp;",
                "PredefinedEntityRef", "&nbsp;",
                "Char", "y",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "ElementContentChar", "x",
                "PredefinedEntityRef", "&amp;",
                "PredefinedEntityRef", "&nbsp;",
                "ElementContentChar", "y",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "tag",
                "XmlTagEnd", ">"
        });
    }

    public void testFlworExpression() throws Exception {
        assertProducedTokens("for $i in 1 to 10 let $j := 'no. ' || $i order by $i ascending, " +
                "$j descending return $j", new String[]{
                "WHITE_SPACE", "",
                "for", "for",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "1",
                "WHITE_SPACE", " ",
                "to", "to",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "10",
                "WHITE_SPACE", " ",
                "let", "let",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "j",
                "WHITE_SPACE", " ",
                ":=", ":=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "no. ",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "||", "||",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "order", "order",
                "WHITE_SPACE", " ",
                "by", "by",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "ascending", "ascending",
                ",", ",",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "j",
                "WHITE_SPACE", " ",
                "descending", "descending",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "j"
        });
    }

    public void testFlworExpressionWithOrderFollowedByWhere() throws Exception {
        assertProducedTokens("for $i in 1 to 10 order by $i ascending where $i = 5 order by $i descending where $i = 5 return $i", new String[]{
                "WHITE_SPACE", "",
                "for", "for",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "1",
                "WHITE_SPACE", " ",
                "to", "to",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "10",
                "WHITE_SPACE", " ",
                "order", "order",
                "WHITE_SPACE", " ",
                "by", "by",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "ascending", "ascending",
                "WHITE_SPACE", " ",
                "where", "where",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "5",
                "WHITE_SPACE", " ",
                "order", "order",
                "WHITE_SPACE", " ",
                "by", "by",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "descending", "descending",
                "WHITE_SPACE", " ",
                "where", "where",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "5",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i"
        });
    }

    public void testVersionDeclaration() throws Exception {
        assertProducedTokens("xquery version '3.0';", new String[]{
                "WHITE_SPACE", "",
                "xquery", "xquery",
                "WHITE_SPACE", " ",
                "version", "version",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "3.0",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testIncompleteVersionDeclaration() throws Exception {
        assertProducedTokens("xquery version", new String[]{
                "WHITE_SPACE", "",
                "xquery", "xquery",
                "WHITE_SPACE", " ",
                "version", "version"
        });
    }

    public void testEncodingAndVersionDeclaration() throws Exception {
        assertProducedTokens("xquery encoding 'UTF-8' version '3.0';", new String[]{
                "WHITE_SPACE", "",
                "xquery", "xquery",
                "WHITE_SPACE", " ",
                "encoding", "encoding",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "UTF-8",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "version", "version",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "3.0",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testVariableReference() throws Exception {
        assertProducedTokens("$y", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "y"
        });
    }

    public void testFunctionCall() throws Exception {
        assertProducedTokens("fn:empty('')", new String[]{
                "WHITE_SPACE", "",
                "NCName", "fn",
                ":", ":",
                "NCName", "empty",
                "(", "(",
                "OpeningApos", "'",
                "ClosingApos", "'",
                ")", ")"
        });
    }

    public void testQueryByElement() throws Exception {
        assertProducedTokens("//tag", new String[]{
                "WHITE_SPACE", "",
                "//", "//",
                "NCName", "tag"
        });
    }

    public void testQueryByAttribute() throws Exception {
        assertProducedTokens("//@attr", new String[]{
                "WHITE_SPACE", "",
                "//", "//",
                "WHITE_SPACE", "",
                "@", "@",
                "NCName", "attr"
        });
    }

    public void testQueryWithSelector() throws Exception {
        assertProducedTokens("/tag[2]", new String[]{
                "WHITE_SPACE", "",
                "/", "/",
                "NCName", "tag",
                "[", "[",
                "IntegerLiteral", "2",
                "]", "]"
        });
    }

    public void testQueryWithAxis() throws Exception {
        assertProducedTokens("//tag/parent::*", new String[]{
                "WHITE_SPACE", "",
                "//", "//",
                "NCName", "tag",
                "/", "/",
                "parent", "parent",
                "::", "::",
                "WHITE_SPACE", "",
                "*", "*"
        });
    }

    public void testQueryWithWildcard() throws Exception {
        assertProducedTokens("/*:div", new String[]{
                "WHITE_SPACE", "",
                "/", "/",
                "*", "*",
                ":", ":",
                "NCName", "div"
        });
    }

    public void testGroupBy() throws Exception {
        assertProducedTokens("for $x in 1 to 20 group by $key := $x mod 2 return $x", new String[]{
                "WHITE_SPACE", "",
                "for", "for",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "x",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "1",
                "WHITE_SPACE", " ",
                "to", "to",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "20",
                "WHITE_SPACE", " ",
                "group", "group",
                "WHITE_SPACE", " ",
                "by", "by",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "key",
                "WHITE_SPACE", " ",
                ":=", ":=",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "x",
                "WHITE_SPACE", " ",
                "mod", "mod",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "2",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "x"
        });
    }

    public void testGroupByWithCollation() throws Exception {
        assertProducedTokens("for $x in 1 to 20 group by $key collation 'x' return $x", new String[]{
                "WHITE_SPACE", "",
                "for", "for",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "x",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "1",
                "WHITE_SPACE", " ",
                "to", "to",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "20",
                "WHITE_SPACE", " ",
                "group", "group",
                "WHITE_SPACE", " ",
                "by", "by",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "key",
                "WHITE_SPACE", " ",
                "collation", "collation",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "x",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "x"
        });
    }

    public void testWhere() throws Exception {
        assertProducedTokens("for $i in //item where $i/id = '0' return $i/name", new String[]{
                "WHITE_SPACE", "",
                "for", "for",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "//", "//",
                "NCName", "item",
                "WHITE_SPACE", " ",
                "where", "where",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "/", "/",
                "NCName", "id",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "0",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "/", "/",
                "NCName", "name"
        });
    }

    public void testOrderBy() throws Exception {
        assertProducedTokens("for $i in //item order by $i/id return $i/name", new String[]{
                "WHITE_SPACE", "",
                "for", "for",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "//", "//",
                "NCName", "item",
                "WHITE_SPACE", " ",
                "order", "order",
                "WHITE_SPACE", " ",
                "by", "by",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "/", "/",
                "NCName", "id",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "/", "/",
                "NCName", "name"
        });
    }

    public void testOrderByWithCollation() throws Exception {
        assertProducedTokens("for $i in //item order by 'x' collation 'x' return $i", new String[]{
                "WHITE_SPACE", "",
                "for", "for",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "//", "//",
                "NCName", "item",
                "WHITE_SPACE", " ",
                "order", "order",
                "WHITE_SPACE", " ",
                "by", "by",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "x",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "collation", "collation",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "x",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
        });
    }

    public void testOrderByEmptyGreatestWithCollation() throws Exception {
        assertProducedTokens("for $i in //item order by 'x' empty greatest collation 'x' return $i", new String[]{
                "WHITE_SPACE", "",
                "for", "for",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "//", "//",
                "NCName", "item",
                "WHITE_SPACE", " ",
                "order", "order",
                "WHITE_SPACE", " ",
                "by", "by",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "x",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "empty", "empty",
                "WHITE_SPACE", " ",
                "greatest", "greatest",
                "WHITE_SPACE", " ",
                "collation", "collation",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "x",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
        });
    }

    public void testTypeswitch() throws Exception {
        assertProducedTokens("typeswitch (//element) case $type1 as element(*, x:type1) return true() default return " +
                "false()", new String[]{
                "WHITE_SPACE", "",
                "typeswitch", "typeswitch",
                "WHITE_SPACE", " ",
                "(", "(",
                "//", "//",
                "NCName", "element",
                ")", ")",
                "WHITE_SPACE", " ",
                "case", "case",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "type1",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "element", "element",
                "(", "(",
                "*", "*",
                ",", ",",
                "WHITE_SPACE", " ",
                "NCName", "x",
                ":", ":",
                "NCName", "type1",
                ")", ")",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "NCName", "true",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "default", "default",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "NCName", "false",
                "(", "(",
                ")", ")"
        });
    }

    public void testEvery() throws Exception {
        assertProducedTokens("every $i in //item satisfies (exists($i/subitem))", new String[]{
                "WHITE_SPACE", "",
                "every", "every",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "//", "//",
                "NCName", "item",
                "WHITE_SPACE", " ",
                "satisfies", "satisfies",
                "WHITE_SPACE", " ",
                "(", "(",
                "WHITE_SPACE", "",
                "NCName", "exists",
                "(", "(",
                "$", "$",
                "NCName", "i",
                "/", "/",
                "NCName", "subitem",
                ")", ")",
                ")", ")"
        });
    }

    public void testPragma() throws Exception {
        assertProducedTokens("(# use-magic #) " +
                "{ //query/with[some-magic] }", new String[]{
                "WHITE_SPACE", "",
                "(#", "(#",
                "S", " ",
                "NCName", "use-magic",
                "S", " ",
                "#)", "#)",
                "WHITE_SPACE", " ",
                "{", "{",
                "WHITE_SPACE", " ",
                "//", "//",
                "NCName", "query",
                "/", "/",
                "NCName", "with",
                "[", "[",
                "WHITE_SPACE", "",
                "NCName", "some-magic",
                "]", "]",
                "WHITE_SPACE", " ",
                "}", "}"
        });
    }

    public void testMoreComplicatedPragma() throws Exception {
        assertProducedTokens("(# use-magic for query #) " +
                "{ //query/with[some-magic] }", new String[]{
                "WHITE_SPACE", "",
                "(#", "(#",
                "S", " ",
                "NCName", "use-magic",
                "S", " ",
                "PragmaContentChar", "for query ",
                "#)", "#)",
                "WHITE_SPACE", " ",
                "{", "{",
                "WHITE_SPACE", " ",
                "//", "//",
                "NCName", "query",
                "/", "/",
                "NCName", "with",
                "[", "[",
                "WHITE_SPACE", "",
                "NCName", "some-magic",
                "]", "]",
                "WHITE_SPACE", " ",
                "}", "}"
        });
    }

    public void testCData() throws Exception {
        assertProducedTokens("<tag><![CDATA[ data ]]></tag>", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "<![CDATA[", "<![CDATA[",
                "CDataSectionContentChar", " data ",
                "]]>", "]]>",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "tag",
                "XmlTagEnd", ">"
        });
    }

    public void testCDataWithoutSurroundingTag() throws Exception {
        assertProducedTokens("<![CDATA[data]]>", new String[]{
                "WHITE_SPACE", "",
                "<![CDATA[", "<![CDATA[",
                "CDataSectionContentChar", "data",
                "]]>", "]]>",
        });
    }

    public void testProcessingInstruction() throws Exception {
        assertProducedTokens("<?name content chars ?>", new String[]{
                "WHITE_SPACE", "",
                "<?", "<?",
                "PITarget", "name",
                "S", " ",
                "DirPIContentChar", "content chars ",
                "?>", "?>"
        });
    }

    public void testComparisonWithFunctionResult() throws Exception {
        assertProducedTokens("if (0 < string-length('')) then 0 else 1", new String[]{
                "WHITE_SPACE", "",
                "if", "if",
                "WHITE_SPACE", " ",
                "(", "(",
                "IntegerLiteral", "0",
                "WHITE_SPACE", " ",
                "<", "<",
                "WHITE_SPACE", " ",
                "NCName", "string-length",
                "(", "(",
                "OpeningApos", "'",
                "ClosingApos", "'",
                ")", ")",
                ")", ")",
                "WHITE_SPACE", " ",
                "then", "then",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "0",
                "WHITE_SPACE", " ",
                "else", "else",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "1"
        });
    }

    public void testComparisonWithFunctionResultWithNamespacePrefix() throws Exception {
        assertProducedTokens("if (0 < functx:string-length('')) then 0 else 1", new String[]{
                "WHITE_SPACE", "",
                "if", "if",
                "WHITE_SPACE", " ",
                "(", "(",
                "IntegerLiteral", "0",
                "WHITE_SPACE", " ",
                "<", "<",
                "WHITE_SPACE", " ",
                "NCName", "functx",
                ":", ":",
                "NCName", "string-length",
                "(", "(",
                "OpeningApos", "'",
                "ClosingApos", "'",
                ")", ")",
                ")", ")",
                "WHITE_SPACE", " ",
                "then", "then",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "0",
                "WHITE_SPACE", " ",
                "else", "else",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "1"
        });
    }

    public void testComparisonWithParenthesizedExpression() throws Exception {
        assertProducedTokens("if (1 < (2 * 3)) then 0 else 1", new String[]{
                "WHITE_SPACE", "",
                "if", "if",
                "WHITE_SPACE", " ",
                "(", "(",
                "IntegerLiteral", "1",
                "WHITE_SPACE", " ",
                "<", "<",
                "WHITE_SPACE", " ",
                "(", "(",
                "IntegerLiteral", "2",
                "WHITE_SPACE", " ",
                "*", "*",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "3",
                ")", ")",
                ")", ")",
                "WHITE_SPACE", " ",
                "then", "then",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "0",
                "WHITE_SPACE", " ",
                "else", "else",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "1"
        });
    }

    public void testComparisonWithCommentFollowedByParenthesizedExpression() throws Exception {
        assertProducedTokens("if (1 < (: 2 * 3 :) (2 * 3)) then 0 else 1", new String[]{
                "WHITE_SPACE", "",
                "if", "if",
                "WHITE_SPACE", " ",
                "(", "(",
                "IntegerLiteral", "1",
                "WHITE_SPACE", " ",
                "<", "<",
                "WHITE_SPACE", " ",
                "ExprCommentStart", "(:",
                "ExprCommentContent", " 2 * 3 ",
                "ExprCommentEnd", ":)",
                "WHITE_SPACE", " ",
                "(", "(",
                "IntegerLiteral", "2",
                "WHITE_SPACE", " ",
                "*", "*",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "3",
                ")", ")",
                ")", ")",
                "WHITE_SPACE", " ",
                "then", "then",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "0",
                "WHITE_SPACE", " ",
                "else", "else",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "1"
        });
    }

    public void testSimpleMapOperator() throws Exception {
        assertProducedTokens("avg( //employee / salary ! translate(., '$','') ! number(.))", new String[]{
                "WHITE_SPACE", "",
                "NCName", "avg",
                "(", "(",
                "WHITE_SPACE", " ",
                "//", "//",
                "NCName", "employee",
                "WHITE_SPACE", " ",
                "/", "/",
                "WHITE_SPACE", " ",
                "NCName", "salary",
                "WHITE_SPACE", " ",
                "!", "!",
                "WHITE_SPACE", " ",
                "NCName", "translate",
                "(", "(",
                ".", ".",
                ",", ",",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "$",
                "ClosingApos", "'",
                ",", ",",
                "OpeningApos", "'",
                "ClosingApos", "'",
                ")", ")",
                "WHITE_SPACE", " ",
                "!", "!",
                "WHITE_SPACE", " ",
                "NCName", "number",
                "(", "(",
                ".", ".",
                ")", ")",
                ")", ")"
        });
    }

    public void testDecimalSeparator() throws Exception {
        assertProducedTokens("declare default decimal-format decimal-separator = 'ds' NaN = 'nan';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "default", "default",
                "WHITE_SPACE", " ",
                "decimal-format", "decimal-format",
                "WHITE_SPACE", " ",
                "decimal-separator", "decimal-separator",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "ds",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "NaN", "NaN",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "nan",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testValidate() throws Exception {
        assertProducedTokens("validate {'string'}", new String[]{
                "WHITE_SPACE", "",
                "validate", "validate",
                "WHITE_SPACE", " ",
                "{", "{",
                "OpeningApos", "'",
                "StringChar", "string",
                "ClosingApos", "'",
                "}", "}"
        });
    }

    public void testValidateLax() throws Exception {
        assertProducedTokens("validate lax {'string'}", new String[]{
                "WHITE_SPACE", "",
                "validate", "validate",
                "WHITE_SPACE", " ",
                "lax", "lax",
                "WHITE_SPACE", " ",
                "{", "{",
                "OpeningApos", "'",
                "StringChar", "string",
                "ClosingApos", "'",
                "}", "}"
        });
    }

    public void testMarklogicValidateAs() throws Exception {
        assertProducedTokens("validate as xs:string {'string'}", new String[]{
                "WHITE_SPACE", "",
                "validate", "validate",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "NCName", "xs",
                ":", ":",
                "NCName", "string",
                "WHITE_SPACE", " ",
                "{", "{",
                "OpeningApos", "'",
                "StringChar", "string",
                "ClosingApos", "'",
                "}", "}"
        });
    }

    public void testItemType() throws Exception {
        assertProducedTokens("declare variable $item as item() external;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "variable", "variable",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "item",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "item", "item",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "external", "external",
                ";", ";"
        });
    }

    public void testItemTypeWithParentheses() throws Exception {
        assertProducedTokens("declare variable $item as (item()) external;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "variable", "variable",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "item",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "(", "(",
                "item", "item",
                "(", "(",
                ")", ")",
                ")", ")",
                "WHITE_SPACE", " ",
                "external", "external",
                ";", ";"
        });
    }

    public void testItemTypeWithParenthesesAndNoSpaceBefore() throws Exception {
        assertProducedTokens("declare variable $item as(item()) external;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "variable", "variable",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "item",
                "WHITE_SPACE", " ",
                "as", "as",
                "(", "(",
                "item", "item",
                "(", "(",
                ")", ")",
                ")", ")",
                "WHITE_SPACE", " ",
                "external", "external",
                ";", ";"
        });
    }

    public void testItemTypeWithParenthesesAndSpaces() throws Exception {
        assertProducedTokens("declare variable $item as ( item ( ) ) external;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "variable", "variable",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "item",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "(", "(",
                "WHITE_SPACE", " ",
                "item", "item",
                "WHITE_SPACE", " ",
                "(", "(",
                "WHITE_SPACE", " ",
                ")", ")",
                "WHITE_SPACE", " ",
                ")", ")",
                "WHITE_SPACE", " ",
                "external", "external",
                ";", ";"
        });
    }

    public void testKindTestInStepExpression() throws Exception {
        assertProducedTokens("/xxx/text()", new String[]{
                "WHITE_SPACE", "",
                "/", "/",
                "NCName", "xxx",
                "/", "/",
                "text", "text",
                "(", "(",
                ")", ")"
        });
    }

    public void testAttributeAxis() throws Exception {
        assertProducedTokens("/attribute::foo", new String[]{
                "WHITE_SPACE", "",
                "/", "/",
                "attribute", "attribute",
                "::", "::",
                "WHITE_SPACE", "",
                "NCName", "foo"
        });
    }

    public void testQueryWithAxisWithKindTest() throws Exception {
        assertProducedTokens("/ancestor::node()", new String[]{
                "WHITE_SPACE", "",
                "/", "/",
                "ancestor", "ancestor",
                "::", "::",
                "node", "node",
                "WHITE_SPACE", "",
                "(", "(",
                ")", ")"
        });
    }

    public void testQueryWithAxisWithNameTest() throws Exception {
        assertProducedTokens("/ancestor::node", new String[]{
                "WHITE_SPACE", "",
                "/", "/",
                "ancestor", "ancestor",
                "::", "::",
                "WHITE_SPACE", "",
                "NCName", "node"
        });
    }

    public void testIncompleteXmlContent() throws Exception {
        assertProducedTokens("</books>", new String[]{
                "WHITE_SPACE", "",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "books",
                "XmlTagEnd", ">"
        });
    }

    public void testModuleNamespace() throws Exception {
        assertProducedTokens("module namespace a = 'a';", new String[]{
                "WHITE_SPACE", "",
                "module", "module",
                "WHITE_SPACE", " ",
                "namespace", "namespace",
                "WHITE_SPACE", " ",
                "NCName", "a",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                ";", ";"
        });
    }

    public void testIncompleteModuleNamespace() throws Exception {
        assertProducedTokens("module namespace", new String[]{
                "WHITE_SPACE", "",
                "module", "module",
                "WHITE_SPACE", " ",
                "namespace", "namespace"
        });
    }

    public void testDeclareDefaultFunctionNamespace() throws Exception {
        assertProducedTokens("declare default function namespace 'a';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "default", "default",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "namespace", "namespace",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareDefaultElementNamespace() throws Exception {
        assertProducedTokens("declare default element namespace 'a';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "default", "default",
                "WHITE_SPACE", " ",
                "element", "element",
                "WHITE_SPACE", " ",
                "namespace", "namespace",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareBoundarySpacePreserve() throws Exception {
        assertProducedTokens("declare boundary-space preserve;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "boundary-space", "boundary-space",
                "WHITE_SPACE", " ",
                "preserve", "preserve",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareBoundarySpaceStrip() throws Exception {
        assertProducedTokens("declare boundary-space strip;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "boundary-space", "boundary-space",
                "WHITE_SPACE", " ",
                "strip", "strip",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareDefaultCollation() throws Exception {
        assertProducedTokens("declare default collation 'a';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "default", "default",
                "WHITE_SPACE", " ",
                "collation", "collation",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareBaseUri() throws Exception {
        assertProducedTokens("declare base-uri 'a';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "base-uri", "base-uri",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareConstructionStrip() throws Exception {
        assertProducedTokens("declare construction strip;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "construction", "construction",
                "WHITE_SPACE", " ",
                "strip", "strip",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareConstructionPreserve() throws Exception {
        assertProducedTokens("declare construction preserve;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "construction", "construction",
                "WHITE_SPACE", " ",
                "preserve", "preserve",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareOrderingOrdered() throws Exception {
        assertProducedTokens("declare ordering ordered;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "ordering", "ordering",
                "WHITE_SPACE", " ",
                "ordered", "ordered",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareOrderingUnordered() throws Exception {
        assertProducedTokens("declare ordering unordered;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "ordering", "ordering",
                "WHITE_SPACE", " ",
                "unordered", "unordered",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareDefaultOrderEmptyGreatest() throws Exception {
        assertProducedTokens("declare default order empty greatest;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "default", "default",
                "WHITE_SPACE", " ",
                "order", "order",
                "WHITE_SPACE", " ",
                "empty", "empty",
                "WHITE_SPACE", " ",
                "greatest", "greatest",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareDefaultOrderEmptyLeast() throws Exception {
        assertProducedTokens("declare default order empty least;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "default", "default",
                "WHITE_SPACE", " ",
                "order", "order",
                "WHITE_SPACE", " ",
                "empty", "empty",
                "WHITE_SPACE", " ",
                "least", "least",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareCopyNamespacePreserveInherit() throws Exception {
        assertProducedTokens("declare copy-namespaces preserve, inherit;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "copy-namespaces", "copy-namespaces",
                "WHITE_SPACE", " ",
                "preserve", "preserve",
                ",", ",",
                "WHITE_SPACE", " ",
                "inherit", "inherit",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareCopyNamespaceNoPreserveNoInherit() throws Exception {
        assertProducedTokens("declare copy-namespaces no-preserve, no-inherit;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "copy-namespaces", "copy-namespaces",
                "WHITE_SPACE", " ",
                "no-preserve", "no-preserve",
                ",", ",",
                "WHITE_SPACE", " ",
                "no-inherit", "no-inherit",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareDecimalFormat() throws Exception {
        assertProducedTokens("declare decimal-format a:a " +
                "decimal-separator = 'a' grouping-separator = 'a' infinity = 'a' " +
                "minus-sign = 'a' NaN = 'a' percent = 'a' per-mille = 'a' " +
                "zero-digit = 'a' digit = 'a' pattern-separator = 'a';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "decimal-format", "decimal-format",
                "WHITE_SPACE", " ",
                "NCName", "a",
                ":", ":",
                "NCName", "a",
                "WHITE_SPACE", " ",
                "decimal-separator", "decimal-separator",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "grouping-separator", "grouping-separator",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "infinity", "infinity",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "minus-sign", "minus-sign",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "NaN", "NaN",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "percent", "percent",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "per-mille", "per-mille",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "zero-digit", "zero-digit",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "digit", "digit",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "pattern-separator", "pattern-separator",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareDefaultDecimalFormat() throws Exception {
        assertProducedTokens("declare default decimal-format;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "default", "default",
                "WHITE_SPACE", " ",
                "decimal-format", "decimal-format",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareNamespace() throws Exception {
        assertProducedTokens("declare namespace a = 'a';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "namespace", "namespace",
                "WHITE_SPACE", " ",
                "NCName", "a",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testImportSchema() throws Exception {
        assertProducedTokens("import schema 'a';", new String[]{
                "WHITE_SPACE", "",
                "import", "import",
                "WHITE_SPACE", " ",
                "schema", "schema",
                "WHITE_SPACE", " ",
                
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testImportSchemaAtLocation() throws Exception {
        assertProducedTokens("import schema 'a' at 'a';", new String[]{
                "WHITE_SPACE", "",
                "import", "import",
                "WHITE_SPACE", " ",
                "schema", "schema",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testImportSchemaAtMultipleLocations() throws Exception {
        assertProducedTokens("import schema 'a' at 'a', 'a';", new String[]{
                "WHITE_SPACE", "",
                "import", "import",
                "WHITE_SPACE", " ",
                "schema", "schema",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                ",", ",",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testImportSchemaNamespaceAtMultipleLocations() throws Exception {
        assertProducedTokens("import schema namespace a = 'a' at 'a', 'a';", new String[]{
                "WHITE_SPACE", "",
                "import", "import",
                "WHITE_SPACE", " ",
                "schema", "schema",
                "WHITE_SPACE", " ",
                "namespace", "namespace",
                "WHITE_SPACE", " ",
                "NCName", "a",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                ",", ",",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testImportSchemaDefaultElementNamespaceAtMultipleLocations() throws Exception {
        assertProducedTokens("import schema default element namespace 'a' at 'a', 'a';", new String[]{
                "WHITE_SPACE", "",
                "import", "import",
                "WHITE_SPACE", " ",
                "schema", "schema",
                "WHITE_SPACE", " ",
                "default", "default",
                "WHITE_SPACE", " ",
                "element", "element",
                "WHITE_SPACE", " ",
                "namespace", "namespace",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                ",", ",",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testImportModuleWithPrefix() throws Exception {
        assertProducedTokens("import module namespace ex = 'example';", new String[]{
                "WHITE_SPACE", "",
                "import", "import",
                "WHITE_SPACE", " ",
                "module", "module",
                "WHITE_SPACE", " ",
                "namespace", "namespace",
                "WHITE_SPACE", " ",
                "NCName", "ex",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "example",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testImportModuleWithoutPrefix() throws Exception {
        assertProducedTokens("import module 'example';", new String[]{
                "WHITE_SPACE", "",
                "import", "import",
                "WHITE_SPACE", " ",
                "module", "module",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "example",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testImportModuleWithoutPrefixAtLocation() throws Exception {
        assertProducedTokens("import module 'example' at 'a';", new String[]{
                "WHITE_SPACE", "",
                "import", "import",
                "WHITE_SPACE", " ",
                "module", "module",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "example",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testImportModuleWithoutPrefixAtMultipleLocations() throws Exception {
        assertProducedTokens("import module 'example' at 'a', 'a';", new String[]{
                "WHITE_SPACE", "",
                "import", "import",
                "WHITE_SPACE", " ",
                "module", "module",
                "WHITE_SPACE", " ",
                
                "OpeningApos", "'",
                "StringChar", "example",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                ",", ",",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareContextItemAs() throws Exception {
        assertProducedTokens("declare context item as item() external;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "context", "context",
                "WHITE_SPACE", " ",
                "item", "item",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "item", "item",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "external", "external",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareContextItemExternal() throws Exception {
        assertProducedTokens("declare context item external;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "context", "context",
                "WHITE_SPACE", " ",
                "item", "item",
                "WHITE_SPACE", " ",
                "external", "external",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testDeclareContextItemWithValue() throws Exception {
        assertProducedTokens("declare context item := ();", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "context", "context",
                "WHITE_SPACE", " ",
                "item", "item",
                "WHITE_SPACE", " ",
                ":=", ":=",
                "WHITE_SPACE", " ",
                "(", "(",
                ")", ")",
                ";", ";"
        });
    }

    public void testDeclareContextItemWithDefaultValue() throws Exception {
        assertProducedTokens("declare context item external := ();", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "context", "context",
                "WHITE_SPACE", " ",
                "item", "item",
                "WHITE_SPACE", " ",
                "external", "external",
                "WHITE_SPACE", " ",
                ":=", ":=",
                "WHITE_SPACE", " ",
                "(", "(",
                ")", ")",
                ";", ";"
        });
    }

    public void testDeclareOption() throws Exception {
        assertProducedTokens("declare option a:a 'a';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "option", "option",
                "WHITE_SPACE", " ",
                "NCName", "a",
                ":", ":",
                "NCName", "a",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testVariableDeclaration() throws Exception {
        assertProducedTokens("declare variable $x := 'value';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "variable", "variable",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "x",
                "WHITE_SPACE", " ",
                ":=", ":=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "value",
                "ClosingApos", "'",
                ";", ";"
        });
    }

    public void testVariableDeclarationWithMarklogicPrivate() throws Exception {
        assertProducedTokens("declare private variable $x := 'value';", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "private", "private",
                "WHITE_SPACE", " ",
                "variable", "variable",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "x",
                "WHITE_SPACE", " ",
                ":=", ":=",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "value",
                "ClosingApos", "'",
                ";", ";"
        });
    }

    public void testIncompleteVariableDeclaration() throws Exception {
        assertProducedTokens("declare variable", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "variable", "variable"
        });
    }

    public void testVariableWithoutDeclaration() throws Exception {
        assertProducedTokens("variable", new String[]{
                "WHITE_SPACE", "",
                "NCName", "variable"
        });
    }

    public void testFunctionDeclaration() throws Exception {
        assertProducedTokens("declare function x() {()};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "x",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "(", "(",
                ")", ")",
                "}", "}",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testUpdatingFunctionDeclaration() throws Exception {
        assertProducedTokens("declare updating function x() {()};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "updating", "updating",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "x",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "(", "(",
                ")", ")",
                "}", "}",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testUpdatingAnnotatedFunctionDeclaration() throws Exception {
        assertProducedTokens("declare %updating function x() {()};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "%", "%",
                "NCName", "updating",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "x",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "(", "(",
                ")", ")",
                "}", "}",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testIncompleteFunctionDeclaration() throws Exception {
        assertProducedTokens("declare function", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "function", "function"
        });
    }

    public void testFunctionDeclarationWithNameVariable() throws Exception {
        assertProducedTokens("declare function variable", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "variable"
        });
    }

    public void testFunctionWithoutDeclaration() throws Exception {
        assertProducedTokens("function", new String[]{
                "WHITE_SPACE", "",
                "NCName", "function"
        });
    }

    public void testFunctionDeclarationWithComments() throws Exception {
        assertProducedTokens("declare(: comment :)function (:comment:) x() {()};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "ExprCommentStart", "(:",
                "ExprCommentContent", " comment ",
                "ExprCommentEnd", ":)",
                "function", "function",
                "WHITE_SPACE", " ",
                "ExprCommentStart", "(:",
                "ExprCommentContent", "comment",
                "ExprCommentEnd", ":)",
                "WHITE_SPACE", " ",
                "NCName", "x",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "(", "(",
                ")", ")",
                "}", "}",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testFunctionDeclarationWithSimpleAnnotation() throws Exception {
        assertProducedTokens("declare %rest:path function x() {()};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "%", "%",
                "NCName", "rest",
                ":", ":",
                "NCName", "path",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "x",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "(", "(",
                ")", ")",
                "}", "}",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testFunctionDeclarationWithAnnotationWithSingleLiteral() throws Exception {
        assertProducedTokens("declare %rest:path(\"/test\") function local:login() {<test/>};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "%", "%",
                "NCName", "rest",
                ":", ":",
                "NCName", "path",
                "(", "(",
                "OpeningQuot", "\"",
                "StringChar", "/test",
                "ClosingQuot", "\"",
                ")", ")",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "local",
                ":", ":",
                "NCName", "login",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "test",
                "WHITE_SPACE", "",
                "XmlEmptyElementEnd", "/>",
                "}", "}",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testFunctionDeclarationWithAnnotationWithLiterals() throws Exception {
        assertProducedTokens("declare %rest:path(\"/test\",\"test2\") function local:login() {<test/>};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "%", "%",
                "NCName", "rest",
                ":", ":",
                "NCName", "path",
                "(", "(",
                "OpeningQuot","\"",
                "StringChar", "/test",
                "ClosingQuot","\"",
                ",", ",",
                "OpeningQuot","\"",
                "StringChar", "test2",
                "ClosingQuot","\"",
                ")", ")",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "local",
                ":", ":",
                "NCName", "login",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "test",
                "WHITE_SPACE", "",
                "XmlEmptyElementEnd", "/>",
                "}", "}",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testFunctionDeclarationWithMarklogicPrivate() throws Exception {
        assertProducedTokens("declare private function local:private() {<test/>};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "private", "private",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "local",
                ":", ":",
                "NCName", "private",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "test",
                "WHITE_SPACE", "",
                "XmlEmptyElementEnd", "/>",
                "}", "}",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testFlworAllowingEmptyWithTypeAndPositionalVar() throws Exception {
        assertProducedTokens("for $i as empty-sequence() allowing empty at $j in 1 to 10", new String[]{
                "WHITE_SPACE", "",
                "for", "for",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "empty-sequence", "empty-sequence",
                "(", "(",
                ")", ")",
                "WHITE_SPACE", " ",
                "allowing", "allowing",
                "WHITE_SPACE", " ",
                "empty", "empty",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "j",
                "WHITE_SPACE", " ",
                "in", "in",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "1",
                "WHITE_SPACE", " ",
                "to", "to",
                "WHITE_SPACE", " ",
                "IntegerLiteral", "10"
        });
    }

    public void testOrderedExpression() {
        assertProducedTokens("ordered {''}", new String[]{
                "WHITE_SPACE", "",
                "ordered", "ordered",
                "WHITE_SPACE", " ",
                "{", "{",
                "OpeningApos", "'",
                "ClosingApos", "'",
                "}", "}"
        });
    }

    public void testUnorderedExpression() {
        assertProducedTokens("unordered {''}", new String[]{
                "WHITE_SPACE", "",
                "unordered", "unordered",
                "WHITE_SPACE", " ",
                "{", "{",
                "OpeningApos", "'",
                "ClosingApos", "'",
                "}", "}"
        });
    }

    public void testTreatExpression() {
        assertProducedTokens("1 treat as xs:integer", new String[]{
                "WHITE_SPACE", "",
                "IntegerLiteral", "1",
                "WHITE_SPACE", " ",
                "treat", "treat",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "NCName", "xs",
                ":", ":",
                "NCName", "integer"
        });
    }

    public void testIncompleteTreatExpression() {
        assertProducedTokens("1 treat as", new String[]{
                "WHITE_SPACE", "",
                "IntegerLiteral", "1",
                "WHITE_SPACE", " ",
                "treat", "treat",
                "WHITE_SPACE", " ",
                "as", "as"
        });
    }

    public void testKeywordInElementSequenceType() {
        assertProducedTokens("/div treat as element(div)", new String[]{
                "WHITE_SPACE", "",
                "/", "/",
                "NCName", "div",
                "WHITE_SPACE", " ",
                "treat", "treat",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "element", "element",
                "(", "(",
                "WHITE_SPACE", "",
                "NCName", "div",
                ")", ")"
        });
    }

    public void testKeywordInSequenceTypeWhenAlmostLikeElementTest() {
        assertProducedTokens("/div treat as element", new String[]{
                "WHITE_SPACE", "",
                "/", "/",
                "NCName", "div",
                "WHITE_SPACE", " ",
                "treat", "treat",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "NCName", "element"
        });
    }

    public void testKeywordInSequenceType() {
        assertProducedTokens("/div treat as div", new String[]{
                "WHITE_SPACE", "",
                "/", "/",
                "NCName", "div",
                "WHITE_SPACE", " ",
                "treat", "treat",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "NCName", "div"
        });
    }

    public void testRevalidation() {
        assertProducedTokens("declare revalidation skip;", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "revalidation", "revalidation",
                "WHITE_SPACE", " ",
                "skip", "skip",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testInsert() {
        assertProducedTokens("insert node <a>a</a> as last into /a/z", new String[]{
                "WHITE_SPACE", "",
                "insert", "insert",
                "WHITE_SPACE", " ",
                "node", "node",
                "WHITE_SPACE", " ",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "a",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "ElementContentChar", "a",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "a",
                "XmlTagEnd", ">",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "last", "last",
                "WHITE_SPACE", " ",
                "into", "into",
                "WHITE_SPACE", " ",
                "/", "/",
                "NCName", "a",
                "/", "/",
                "NCName", "z"
        });
    }

    public void testDelete() {
        assertProducedTokens("delete nodes /email/message", new String[]{
                "WHITE_SPACE", "",
                "delete", "delete",
                "WHITE_SPACE", " ",
                "nodes", "nodes",
                "WHITE_SPACE", " ",
                "/", "/",
                "NCName", "email",
                "/", "/",
                "NCName", "message"
        });
    }

    public void testReplace() {
        assertProducedTokens("replace node /a/z with element w", new String[]{
                "WHITE_SPACE", "",
                "replace", "replace",
                "WHITE_SPACE", " ",
                "node", "node",
                "WHITE_SPACE", " ",
                "/", "/",
                "NCName", "a",
                "/", "/",
                "NCName", "z",
                "WHITE_SPACE", " ",
                "with", "with",
                "WHITE_SPACE", " ",
                "element", "element",
                "WHITE_SPACE", " ",
                "NCName", "w"
        });
    }

    public void testRename() {
        assertProducedTokens("rename node /a/z as 'a'", new String[]{
                "WHITE_SPACE", "",
                "rename", "rename",
                "WHITE_SPACE", " ",
                "node", "node",
                "WHITE_SPACE", " ",
                "/", "/",
                "NCName", "a",
                "/", "/",
                "NCName", "z",
                "WHITE_SPACE", " ",
                "as", "as",
                "WHITE_SPACE", " ",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
        });
    }

    public void testTransform() {
        assertProducedTokens("copy $j := /e modify delete node $j/z return $j", new String[]{
                "WHITE_SPACE", "",
                "copy", "copy",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "j",
                "WHITE_SPACE", " ",
                ":=", ":=",
                "WHITE_SPACE", " ",
                "/", "/",
                "NCName", "e",
                "WHITE_SPACE", " ",
                "modify", "modify",
                "WHITE_SPACE", " ",
                "delete", "delete",
                "WHITE_SPACE", " ",
                "node", "node",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "j",
                "/", "/",
                "NCName", "z",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "j"
        });
    }

    public void testXQuery30TryCatch() {
        assertProducedTokens("try{'a'} catch * {'b'}", new String[]{
                "WHITE_SPACE", "",
                "try", "try",
                "{", "{",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "}", "}",
                "WHITE_SPACE", " ",
                "catch", "catch",
                "WHITE_SPACE", " ",
                "*", "*",
                "WHITE_SPACE", " ",
                "{", "{",
                "OpeningApos", "'",
                "StringChar", "b",
                "ClosingApos", "'",
                "}", "}"
        });
    }

    public void testMarklogicTryCatch() {
        assertProducedTokens("try {'a'} catch ($e) {'b'}", new String[]{
                "WHITE_SPACE", "",
                "try", "try",
                "WHITE_SPACE", " ",
                "{", "{",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                "}", "}",
                "WHITE_SPACE", " ",
                "catch", "catch",
                "WHITE_SPACE", " ",
                "(", "(",
                "$", "$",
                "NCName", "e",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "OpeningApos", "'",
                "StringChar", "b",
                "ClosingApos", "'",
                "}", "}"
        });
    }

    public void testMarklogicNamespaceAxis() {
        assertProducedTokens("$a/namespace::b", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "namespace", "namespace",
                "::", "::",
                "WHITE_SPACE", "",
                "NCName", "b"
        });
    }

    public void testMarklogicBinaryTest() {
        assertProducedTokens("$a/binary()", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "binary", "binary",
                "(", "(",
                ")", ")"
        });
    }

    public void testMarklogicBinaryConstructor() {
        assertProducedTokens("binary {$a}", new String[]{
                "WHITE_SPACE", "",
                "binary", "binary",
                "WHITE_SPACE", " ",
                "{", "{",
                "$", "$",
                "NCName", "a",
                "}", "}"
        });
    }

    public void testMarklogicObjectNodeTest() {
        assertProducedTokens("$a/object-node()", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "object-node", "object-node",
                "(", "(",
                ")", ")"
        });
    }

    public void testMarklogicObjectNodeConstructor() {
        assertProducedTokens("object-node {$a}", new String[]{
                "WHITE_SPACE", "",
                "object-node", "object-node",
                "WHITE_SPACE", " ",
                "{", "{",
                "$", "$",
                "NCName", "a",
                "}", "}"
        });
    }

    public void testMarklogicObjectNodeConstructorWithProperty() {
        assertProducedTokens("object-node {'a':$a}", new String[]{
                "WHITE_SPACE", "",
                "object-node", "object-node",
                "WHITE_SPACE", " ",
                "{", "{",
                "OpeningApos", "'",
                "StringChar", "a",
                "ClosingApos", "'",
                ":", ":",
                "$", "$",
                "NCName", "a",
                "}", "}"
        });
    }

    public void testMarklogicNumberNodeTest() {
        assertProducedTokens("$a/number-node()", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "number-node", "number-node",
                "(", "(",
                ")", ")"
        });
    }

    public void testMarklogicNumberNodeTestWithLiteral() {
        assertProducedTokens("$a/number-node('count')", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "number-node", "number-node",
                "(", "(",
                "OpeningApos", "'",
                "StringChar", "count",
                "ClosingApos", "'",
                ")", ")"
        });
    }

    public void testMarklogicNumberNodeConstructor() {
        assertProducedTokens("number-node {$a}", new String[]{
                "WHITE_SPACE", "",
                "number-node", "number-node",
                "WHITE_SPACE", " ",
                "{", "{",
                "$", "$",
                "NCName", "a",
                "}", "}"
        });
    }

    public void testMarklogicBooleanNodeTest() {
        assertProducedTokens("$a/boolean-node()", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "boolean-node", "boolean-node",
                "(", "(",
                ")", ")"
        });
    }

    public void testMarklogicBooleanNodeTestWithLiteral() {
        assertProducedTokens("$a/boolean-node('completed')", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "boolean-node", "boolean-node",
                "(", "(",
                "OpeningApos", "'",
                "StringChar", "completed",
                "ClosingApos", "'",
                ")", ")"
        });
    }

    public void testMarklogicObjectBooleanConstructor() {
        assertProducedTokens("boolean-node {$a}", new String[]{
                "WHITE_SPACE", "",
                "boolean-node", "boolean-node",
                "WHITE_SPACE", " ",
                "{", "{",
                "$", "$",
                "NCName", "a",
                "}", "}"
        });
    }

    public void testMarklogicNullNodeTest() {
        assertProducedTokens("$a/null-node()", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "null-node", "null-node",
                "(", "(",
                ")", ")"
        });
    }

    public void testMarklogicNullNodeTestWithLiteral() {
        assertProducedTokens("$a/null-node('account')", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "null-node", "null-node",
                "(", "(",
                "OpeningApos", "'",
                "StringChar", "account",
                "ClosingApos", "'",
                ")", ")"
        });
    }

    public void testMarklogicNullNodeConstructor() {
        assertProducedTokens("null-node {}", new String[]{
                "WHITE_SPACE", "",
                "null-node", "null-node",
                "WHITE_SPACE", " ",
                "{", "{",
                "}", "}"
        });
    }

    public void testMarklogicArrayNodeTest() {
        assertProducedTokens("$a/array-node()", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "array-node", "array-node",
                "(", "(",
                ")", ")"
        });
    }

    public void testMarklogicArrayNodeTestWithLiteral() {
        assertProducedTokens("$a/array-node('names')", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "array-node", "array-node",
                "(", "(",
                "OpeningApos", "'",
                "StringChar", "names",
                "ClosingApos", "'",
                ")", ")"
        });
    }

    public void testMarklogicArrayNodeConstructor() {
        assertProducedTokens("array-node {$a}", new String[]{
                "WHITE_SPACE", "",
                "array-node", "array-node",
                "WHITE_SPACE", " ",
                "{", "{",
                "$", "$",
                "NCName", "a",
                "}", "}"
        });
    }

    public void testMarklogicTextTest() {
        assertProducedTokens("$a/text('title')", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "text", "text",
                "(", "(",
                "OpeningApos", "'",
                "StringChar", "title",
                "ClosingApos", "'",
                ")", ")"
        });
    }

    public void testMarklogicAnyKindTest() {
        assertProducedTokens("$a/node('title')", new String[]{
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                "/", "/",
                "node", "node",
                "(", "(",
                "OpeningApos", "'",
                "StringChar", "title",
                "ClosingApos", "'",
                ")", ")"
        });
    }

    public void testNCNameWithHyphen() {
        assertProducedTokens("abc-def:def-ghi", new String[]{
                "WHITE_SPACE", "",
                "NCName", "abc-def",
                ":", ":",
                "NCName", "def-ghi",
        });
    }

    public void testIncompleteTagStartBeforeComment() {
        assertProducedTokens("<foo (: a :)", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "foo",
                "WHITE_SPACE", " ",
                "(:", "(:",
                "ExprCommentContent", " a ",
                ":)", ":)",
        });
    }

    public void testIncompleteTagStartBeforeEmptySequence() {
        assertProducedTokens("<foo ()", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "foo",
                "WHITE_SPACE", " ",
                "(", "(",
                ")", ")",
        });
    }

    public void testIncompletePrefixedTagStartBeforeEmptySequence() {
        assertProducedTokens("<foo:foo ()", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "foo",
                "XmlColon", ":",
                "XmlTagNCName", "foo",
                "WHITE_SPACE", " ",
                "(", "(",
                ")", ")",
        });
    }

    public void testTagWithMisplacedComments() {
        assertProducedTokens("<foo (: foo :) attr='attr' (: bar :)></foo>", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "foo",
                "WHITE_SPACE", " ",
                "(:", "(:",
                "ExprCommentContent", " foo ",
                ":)", ":)",
                "WHITE_SPACE", " ",
                "AttrNCName", "attr",
                "AttrEqual", "=",
                "OpeningApos", "'",
                "Char", "attr",
                "ClosingApos", "'",
                "WHITE_SPACE", " ",
                "(:", "(:",
                "ExprCommentContent", " bar ",
                ":)", ":)",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "foo",
                "XmlTagEnd", ">"
        });
    }

    public void testNestedMisplacedComment() {
        assertProducedTokens("<a (: a(:b:)c :)/>", new String[]{
                "WHITE_SPACE", "",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "a",
                "WHITE_SPACE", " ",
                "(:", "(:",
                "ExprCommentContent", " a",
                "(:", "(:",
                "ExprCommentContent", "b",
                ":)", ":)",
                "ExprCommentContent", "c ",
                ":)", ":)",
                "WHITE_SPACE", "",
                "XmlEmptyElementEnd", "/>"
        });
    }

    public void testInternalExpressionsInElementContent() {
        assertProducedTokens("declare function foo($a, $b) {<foo>a is '{$a}", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "foo",
                "(", "(",
                "WHITE_SPACE", "",
                "$", "$",
                "NCName", "a",
                ",", ",",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "b",
                ")", ")",
                "WHITE_SPACE", " ",
                "{", "{",
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "foo",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "ElementContentChar", "a",
                "WHITE_SPACE", " ",
                "ElementContentChar", "is",
                "WHITE_SPACE", " ",
                "ElementContentChar", "'",
                "{", "{",
                "$", "$",
                "NCName", "a",
                "}", "}"

        });
    }

    public void testArray() {
        assertProducedTokens("array {}", new String[] {
                "WHITE_SPACE", "",
                "array", "array",
                "WHITE_SPACE", " ",
                "{", "{",
                "}", "}"
        });
    }

    public void testStringConstructor() {
        assertProducedTokens("``[`{$s}` fish]``", new String[] {
                "WHITE_SPACE", "",
                "``[", "``[",
                "`{", "`{",
                "$", "$",
                "NCName", "s",
                "}`", "}`",
                "Char", " fish",
                "]``", "]``"
        });
    }

    public void testNestedStringConstructor() {
        assertProducedTokens("``[`{ $i, ``[literal text]``, $j, ``[more literal text]`` }`]``", new String[] {
                "WHITE_SPACE", "",
                "``[", "``[",
                "`{", "`{",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
                ",", ",",
                "WHITE_SPACE", " ",
                "``[", "``[",
                "Char", "literal text",
                "]``", "]``",
                ",", ",",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "j",
                ",", ",",
                "WHITE_SPACE", " ",
                "``[", "``[",
                "Char", "more literal text",
                "]``", "]``",
                "WHITE_SPACE", " ",
                "}`", "}`",
                "]``", "]``"
        });
    }

    public void testIncompleteStringConstructorExpression() {
        assertProducedTokens("``[`{$s]``", new String[] {
                "WHITE_SPACE", "",
                "``[", "``[",
                "`{", "`{",
                "$", "$",
                "NCName", "s",
                "]``", "]``"
        });
    }

    public void testMultiLineStringConstructor() {
        assertProducedTokens("``[`{$s}`\nfish]``", new String[] {
                "WHITE_SPACE", "",
                "``[", "``[",
                "`{", "`{",
                "$", "$",
                "NCName", "s",
                "}`", "}`",
                "Char", "\nfish",
                "]``", "]``"
        });
    }
}