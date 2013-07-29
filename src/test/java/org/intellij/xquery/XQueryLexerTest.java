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

    private static void doTest(@NonNls String text, @NonNls String[] expectedTokens) {
        Lexer lexer = new XQueryLexer();
        doTest(text, expectedTokens, lexer);
    }

    private static void doTestHL(@NonNls String text, @NonNls String[] expectedTokens) {
        Lexer lexer = new XQueryLexer();
        doTest(text, expectedTokens, lexer);
    }

    private static void doTest(String text, String[] expectedTokens, Lexer lexer) {
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

    public void testNCName() throws Exception {
        doTest("xxx", new String[]{
                "WHITE_SPACE", "",
                "NCName", "xxx"});
    }

    public void testQName() throws Exception {
        doTest("xxx:yyy", new String[]{
                "WHITE_SPACE", "",
                "NCName", "xxx",
                ":", ":",
                "NCName", "yyy"});
    }

    public void testLiterals() throws Exception {
        doTest("\"abc\",'bcd',123,456.78", new String[]{
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
        doTest("(: content :)", new String[] {
                "ExprCommentStart", "(:",
                "ExprCommentContent", " content ",
                "ExprCommentEnd", ":)",
        });
    }

    public void testXmlContent() throws Exception {
        doTest("<tag attr='val'>content</tag>", new String[] {
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
}