/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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

/**
 * User: ligasgr
 * Date: 29/07/13
 * Time: 23:28
 */
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
        assertProducedTokens("\"abc\",'bcd',123,456.78", new String[]{
                "WHITE_SPACE", "",
                "StringLiteral", "\"abc\"",
                ",", ",",
                "WHITE_SPACE", "",
                "StringLiteral", "'bcd'",
                ",", ",",
                "IntegerLiteral", "123",
                ",", ",",
                "DecimalLiteral", "456.78"
        });
    }

    public void testExpressionComment() throws Exception {
        assertProducedTokens("(: content :)", new String[]{
                "ExprCommentStart", "(:",
                "ExprCommentContent", " content ",
                "ExprCommentEnd", ":)"
        });
    }

    public void testXmlContent() throws Exception {
        assertProducedTokens("<tag attr='val'>content</tag>", new String[]{
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", " ",
                "AttrNCName", "attr",
                "AttrEqual", "=",
                "'", "'",
                "Char", "v",
                "Char", "a",
                "Char", "l",
                "'", "'",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "ElementContentChar", "c",
                "ElementContentChar", "o",
                "ElementContentChar", "n",
                "ElementContentChar", "t",
                "ElementContentChar", "e",
                "ElementContentChar", "n",
                "ElementContentChar", "t",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "tag",
                "XmlTagEnd", ">"
        });
    }

    public void testDirectComment() throws Exception {
        assertProducedTokens("<tag attr='val'><!--content--></tag>", new String[]{
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", " ",
                "AttrNCName", "attr",
                "AttrEqual", "=",
                "'", "'",
                "Char", "v",
                "Char", "a",
                "Char", "l",
                "'", "'",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "<!--", "<!--",
                "DirCommentChar", "c",
                "DirCommentChar", "o",
                "DirCommentChar", "n",
                "DirCommentChar", "t",
                "DirCommentChar", "e",
                "DirCommentChar", "n",
                "DirCommentChar", "t",
                "-->", "-->",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "tag",
                "XmlTagEnd", ">"
        });
    }

    public void testDirectCommentWithoutSurroundingTag() throws Exception {
        assertProducedTokens("<!--content-->", new String[]{
                "<!--", "<!--",
                "DirCommentChar", "c",
                "DirCommentChar", "o",
                "DirCommentChar", "n",
                "DirCommentChar", "t",
                "DirCommentChar", "e",
                "DirCommentChar", "n",
                "DirCommentChar", "t",
                "-->", "-->",
        });
    }

    public void testXmlContentWithCharacterRefs() throws Exception {
        assertProducedTokens("<tag attr='x&#xa0;&#123;y'>x&#xa0;&#123;y</tag>", new String[]{
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", " ",
                "AttrNCName", "attr",
                "AttrEqual", "=",
                "'", "'",
                "Char", "x",
                "CharRef", "&#xa0;",
                "CharRef", "&#123;",
                "Char", "y",
                "'", "'",
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
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", " ",
                "AttrNCName", "attr",
                "AttrEqual", "=",
                "'", "'",
                "Char", "x",
                "PredefinedEntityRef", "&amp;",
                "PredefinedEntityRef", "&nbsp;",
                "Char", "y",
                "'", "'",
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
                "StringLiteral", "'no. '",
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

    public void testVersionDeclaration() throws Exception {
        assertProducedTokens("xquery version '3.0';", new String[]{
                "WHITE_SPACE", "",
                "xquery", "xquery",
                "WHITE_SPACE", " ",
                "version", "version",
                "WHITE_SPACE", " ",
                "StringLiteral", "'3.0'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testEncodingAndVersionDeclaration() throws Exception {
        assertProducedTokens("xquery encoding 'UTF-8' version '3.0';", new String[]{
                "WHITE_SPACE", "",
                "xquery", "xquery",
                "WHITE_SPACE", " ",
                "encoding", "encoding",
                "WHITE_SPACE", " ",
                "StringLiteral", "'UTF-8'",
                "WHITE_SPACE", " ",
                "version", "version",
                "WHITE_SPACE", " ",
                "StringLiteral", "'3.0'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testVariableReference() throws Exception {
        assertProducedTokens("$y", new String[]{
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
                "WHITE_SPACE", "",
                "StringLiteral", "''",
                ")", ")"
        });
    }

    public void testQueryByElement() throws Exception {
        assertProducedTokens("//tag", new String[]{
                "//", "//",
                "NCName", "tag"
        });
    }

    public void testQueryByAttribute() throws Exception {
        assertProducedTokens("//@attr", new String[]{
                "//", "//",
                "WHITE_SPACE", "",
                "@", "@",
                "NCName", "attr"
        });
    }

    public void testQueryWithSelector() throws Exception {
        assertProducedTokens("/tag[2]", new String[]{
                "/", "/",
                "NCName", "tag",
                "[", "[",
                "IntegerLiteral", "2",
                "]", "]"
        });
    }

    public void testQueryWithAxis() throws Exception {
        assertProducedTokens("//tag/parent::*", new String[]{
                "//", "//",
                "NCName", "tag",
                "/", "/",
                "parent", "parent",
                "::", "::",
                "*", "*"
        });
    }

    public void testGroupBy() throws Exception {
        assertProducedTokens("for $x in 1 to 20 group by $key := $x mod 2 return $x", new String[]{
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
                "StringLiteral", "'x'",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "x"
        });
    }

    public void testWhere() throws Exception {
        assertProducedTokens("for $i in //item where $i/id = '0' return $i/name", new String[]{
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
                "StringLiteral", "'0'",
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
                "StringLiteral", "'x'",
                "WHITE_SPACE", " ",
                "collation", "collation",
                "WHITE_SPACE", " ",
                "StringLiteral", "'x'",
                "WHITE_SPACE", " ",
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "i",
        });
    }

    public void testOrderByEmptyGreatestWithCollation() throws Exception {
        assertProducedTokens("for $i in //item order by 'x' empty greatest collation 'x' return $i", new String[]{
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
                "StringLiteral", "'x'",
                "WHITE_SPACE", " ",
                "empty", "empty",
                "WHITE_SPACE", " ",
                "greatest", "greatest",
                "WHITE_SPACE", " ",
                "collation", "collation",
                "WHITE_SPACE", " ",
                "StringLiteral", "'x'",
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
                "(#", "(#",
                "S", " ",
                "NCName", "use-magic",
                "S", " ",
                "PragmaContentChar", "f",
                "PragmaContentChar", "o",
                "PragmaContentChar", "r",
                "PragmaContentChar", " ",
                "PragmaContentChar", "q",
                "PragmaContentChar", "u",
                "PragmaContentChar", "e",
                "PragmaContentChar", "r",
                "PragmaContentChar", "y",
                "PragmaContentChar", " ",
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
                "XmlStartTagStart", "<",
                "WHITE_SPACE", "",
                "XmlTagNCName", "tag",
                "WHITE_SPACE", "",
                "XmlTagEnd", ">",
                "<![CDATA[", "<![CDATA[",
                "CDataSectionContentChar", " ",
                "CDataSectionContentChar", "d",
                "CDataSectionContentChar", "a",
                "CDataSectionContentChar", "t",
                "CDataSectionContentChar", "a",
                "CDataSectionContentChar", " ",
                "]]>", "]]>",
                "XmlEndTagStart", "</",
                "XmlTagNCName", "tag",
                "XmlTagEnd", ">"
        });
    }

    public void testCDataWithoutSurroundingTag() throws Exception {
        assertProducedTokens("<![CDATA[data]]>", new String[]{
                "<![CDATA[", "<![CDATA[",
                "CDataSectionContentChar", "d",
                "CDataSectionContentChar", "a",
                "CDataSectionContentChar", "t",
                "CDataSectionContentChar", "a",
                "]]>", "]]>",
        });
    }

    public void testProcessingInstruction() throws Exception {
        assertProducedTokens("<?name content chars ?>", new String[]{
                "<?", "<?",
                "PITarget", "name",
                "S", " ",
                "DirPIContentChar", "c",
                "DirPIContentChar", "o",
                "DirPIContentChar", "n",
                "DirPIContentChar", "t",
                "DirPIContentChar", "e",
                "DirPIContentChar", "n",
                "DirPIContentChar", "t",
                "DirPIContentChar", " ",
                "DirPIContentChar", "c",
                "DirPIContentChar", "h",
                "DirPIContentChar", "a",
                "DirPIContentChar", "r",
                "DirPIContentChar", "s",
                "DirPIContentChar", " ",
                "?>", "?>"
        });
    }

    public void testComparisonWithFunctionResult() throws Exception {
        assertProducedTokens("if (0 < string-length('')) then 0 else 1", new String[]{
                "if", "if",
                "WHITE_SPACE", " ",
                "(", "(",
                "IntegerLiteral", "0",
                "WHITE_SPACE", " ",
                "<", "<",
                "WHITE_SPACE", " ",
                "NCName", "string-length",
                "(", "(",
                "WHITE_SPACE", "",
                "StringLiteral", "''",
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
                "WHITE_SPACE", "",
                "StringLiteral", "''",
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
                "StringLiteral", "'$'",
                ",", ",",
                "WHITE_SPACE", "",
                "StringLiteral", "''",
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
                "StringLiteral", "'ds'",
                "WHITE_SPACE", " ",
                "NaN", "NaN",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'nan'",
                "WHITE_SPACE", "",
                ";", ";"
        });
    }

    public void testValidate() throws Exception {
        assertProducedTokens("validate {'string'}", new String[]{
                "validate", "validate",
                "WHITE_SPACE", " ",
                "{", "{",
                "WHITE_SPACE", "",
                "StringLiteral", "'string'",
                "}", "}"
        });
    }

    public void testValidateLax() throws Exception {
        assertProducedTokens("validate lax {'string'}", new String[]{
                "validate", "validate",
                "WHITE_SPACE", " ",
                "lax", "lax",
                "WHITE_SPACE", " ",
                "{", "{",
                "WHITE_SPACE", "",
                "StringLiteral", "'string'",
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
                "/", "/",
                "attribute", "attribute",
                "::", "::",
                "WHITE_SPACE", "",
                "NCName", "foo"
        });
    }

    public void testIncompleteXmlContent() throws Exception {
        assertProducedTokens("</books>", new String[]{
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
                "StringLiteral", "'a'",
                ";", ";"
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
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "grouping-separator", "grouping-separator",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "infinity", "infinity",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "minus-sign", "minus-sign",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "NaN", "NaN",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "percent", "percent",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "per-mille", "per-mille",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "zero-digit", "zero-digit",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "digit", "digit",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "pattern-separator", "pattern-separator",
                "WHITE_SPACE", " ",
                "=", "=",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                ",", ",",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                ",", ",",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                ",", ",",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
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
                "StringLiteral", "'example'",
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
                "StringLiteral", "'example'",
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
                "StringLiteral", "'example'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
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
                "StringLiteral", "'example'",
                "WHITE_SPACE", " ",
                "at", "at",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
                ",", ",",
                "WHITE_SPACE", " ",
                "StringLiteral", "'a'",
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
                "StringLiteral", "'a'",
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
                "StringLiteral", "'value'",
                ";", ";"
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
                ";", ";"
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
                ";", ";"
        });
    }

    public void testFunctionDeclarationWithSimpleAnnotation() throws Exception {
        assertProducedTokens("declare %rest:path function x() {()};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "%", "%",
                "WHITE_SPACE", "",
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
                ";", ";"
        });
    }

    public void testFunctionDeclarationWithAnnotationWithSingleLiteral() throws Exception {
        assertProducedTokens("declare %rest:path(\"/test\") function local:login() {<test/>};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "%", "%",
                "WHITE_SPACE", "",
                "NCName", "rest",
                ":", ":",
                "NCName", "path",
                "(", "(",
                "WHITE_SPACE", "",
                "StringLiteral", "\"/test\"",
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
                ";", ";"
        });
    }

    public void testFunctionDeclarationWithAnnotationWithLiterals() throws Exception {
        assertProducedTokens("declare %rest:path(\"/test\",\"test2\") function local:login() {<test/>};", new String[]{
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "%", "%",
                "WHITE_SPACE", "",
                "NCName", "rest",
                ":", ":",
                "NCName", "path",
                "(", "(",
                "WHITE_SPACE", "",
                "StringLiteral", "\"/test\"",
                ",", ",",
                "WHITE_SPACE", "",
                "StringLiteral", "\"test2\"",
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
                ";", ";"
        });
    }

    public void testFlworAllowingEmptyWithTypeAndPositionalVar() throws Exception {
        assertProducedTokens("for $i as empty-sequence() allowing empty at $j in 1 to 10", new String[]{
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
                "ordered", "ordered",
                "WHITE_SPACE", " ",
                "{", "{",
                "WHITE_SPACE", "",
                "StringLiteral", "''",
                "}", "}"
        });
    }

    public void testUnorderedExpression() {
        assertProducedTokens("unordered {''}", new String[]{
                "unordered", "unordered",
                "WHITE_SPACE", " ",
                "{", "{",
                "WHITE_SPACE", "",
                "StringLiteral", "''",
                "}", "}"
        });
    }
}
