/*
 * Copyright 2013 Grzegorz Ligas
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

package org.intellij.xquery;

import com.intellij.lexer.Lexer;
import com.intellij.testFramework.LightPlatformTestCase;
import com.intellij.testFramework.PlatformTestCase;
import org.jetbrains.annotations.NonNls;

/**
 * User: ligasgr
 * Date: 29/07/13
 * Time: 23:28
 */
public class XQueryLexerTest extends LightPlatformTestCase {

    public XQueryLexerTest() {
        PlatformTestCase.initPlatformLangPrefix();
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
                "ExprCommentEnd", ":)",
        });
    }

    public void testXmlContent() throws Exception {
        assertProducedTokens("<tag attr='val'>content</tag>", new String[]{
                "<", "<",
                "NCName", "tag",
                "S", " ",
                "NCName", "attr",
                "=", "=",
                "'", "'",
                "Char", "v",
                "Char", "a",
                "Char", "l",
                "'", "'",
                ">", ">",
                "ElementContentChar", "content",
                "</", "</",
                "NCName", "tag",
                ">", ">",
        });
    }

    public void testFlworExpression() throws Exception {
        assertProducedTokens("for $i in 1 to 10 let $j := 'no. ' || $i return $j", new String[] {
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
                "return", "return",
                "WHITE_SPACE", " ",
                "$", "$",
                "NCName", "j"
        });
    }



    public void testVersionDeclaration() throws Exception {
        assertProducedTokens("xquery version '3.0';", new String[] {
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
        assertProducedTokens("xquery encoding 'UTF-8' version '3.0';", new String[] {
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

    public void testVariableDeclaration() throws Exception {
        assertProducedTokens("declare variable $x := 'value';", new String[] {
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

    public void testVariableReference() throws Exception {
        assertProducedTokens("$y", new String[] {
                "$", "$",
                "NCName", "y"
        });
    }

    public void testFunctionDeclaration() throws Exception {
        assertProducedTokens("declare function x() {()};", new String[] {
                "WHITE_SPACE", "",
                "declare", "declare",
                "WHITE_SPACE", " ",
                "function", "function",
                "WHITE_SPACE", " ",
                "NCName", "x",
                "WHITE_SPACE", "",
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

    public void testFunctionCall() throws Exception {
        assertProducedTokens("fn:empty('')", new String[] {
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
            String tokenText = lexer.getBufferSequence().subSequence(lexer.getTokenStart(), lexer.getTokenEnd()).toString();
            assertEquals(expectedTokenText, tokenText);
            lexer.advance();
        }

        if (idx < expectedTokens.length) fail("Not enough tokens");
    }
}