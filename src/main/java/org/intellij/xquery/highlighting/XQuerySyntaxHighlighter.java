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

package org.intellij.xquery.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.intellij.xquery.lexer.XQueryLexer;
import org.intellij.xquery.psi.XQueryBasicTypes;
import org.intellij.xquery.psi.XQueryTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 19:01
 */
public class XQuerySyntaxHighlighter extends SyntaxHighlighterBase {

    private static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("XQUERY_BAD_CHARACTER",
            DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE);
    private static final TextAttributesKey KEYWORD = createTextAttributesKey("XQUERY_KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD);
    private static final TextAttributesKey STRING = createTextAttributesKey("XQUERY_STRING",
            DefaultLanguageHighlighterColors.STRING);
    private static final TextAttributesKey SEMICOLON = createTextAttributesKey("XQUERY_SEMICOLON",
            DefaultLanguageHighlighterColors.SEMICOLON);
    private static final TextAttributesKey PARENTHESIS = createTextAttributesKey("XQUERY_PARENTHESIS",
            DefaultLanguageHighlighterColors.PARENTHESES);
    private static final TextAttributesKey COMMENT = createTextAttributesKey("XQUERY_COMMENT",
            DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    private static final TextAttributesKey NUMBER = createTextAttributesKey("XQUERY_NUMBER",
            DefaultLanguageHighlighterColors.NUMBER);
    private static final TextAttributesKey NAME = createTextAttributesKey("XQUERY_NAME",
            DefaultLanguageHighlighterColors.NUMBER);
    private static final TextAttributesKey OPERATION_SIGN = createTextAttributesKey("XQUERY_OPERATION",
            DefaultLanguageHighlighterColors.OPERATION_SIGN);
    private static final TextAttributesKey[] BAD_CHAR_KEYS = pack(BAD_CHARACTER);
    private static final TextAttributesKey[] KEYWORDS = pack(KEYWORD);
    private static final TextAttributesKey[] STRINGS = pack(STRING);
    private static final TextAttributesKey[] SEMICOLONS = pack(SEMICOLON);
    private static final TextAttributesKey[] PARENTHESES = pack(PARENTHESIS);
    private static final TextAttributesKey[] COMMENTS = pack(COMMENT);
    private static final TextAttributesKey[] NUMBERS = pack(NUMBER);
    private static final TextAttributesKey[] NAMES = pack(NAME);
    private static final TextAttributesKey[] OPERATION_SIGNS = pack(OPERATION_SIGN);

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new XQueryLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType == XQueryBasicTypes.EXPR_COMMENT_CONTENT
                || tokenType == XQueryBasicTypes.EXPR_COMMENT_END
                || tokenType == XQueryBasicTypes.EXPR_COMMENT_START
                || tokenType == XQueryTypes.DIRCOMMENTCHAR
                || tokenType == XQueryTypes.DIR_COMMENT_BEGIN
                || tokenType == XQueryTypes.DIR_COMMENT_END) {
            return COMMENTS;
        }
        if (tokenType == XQueryTypes.DECIMALLITERAL
                || tokenType == XQueryTypes.DOUBLELITERAL
                || tokenType == XQueryTypes.INTEGERLITERAL) {
            return NUMBERS;
        }
        if (tokenType == XQueryTypes.STRINGLITERAL
                || tokenType == XQueryTypes.CHAR
                || tokenType == XQueryTypes.ELEMENTCONTENTCHAR
                || tokenType == XQueryTypes.PREDEFINEDENTITYREF
                || tokenType == XQueryTypes.APOSTROPHE
                || tokenType == XQueryTypes.QUOT) {
            return STRINGS;
        }
        if (tokenType == XQueryTypes.SEMICOLON) {
            return SEMICOLONS;
        }
        if (tokenType == XQueryTypes.L_PAR || tokenType == XQueryTypes.R_PAR || tokenType == XQueryTypes.L_C_BRACE ||
                tokenType == XQueryTypes.R_C_BRACE) {
            return PARENTHESES;
        }
        if (tokenType == TokenType.BAD_CHARACTER) {
            return BAD_CHAR_KEYS;
        }
        if (tokenType == XQueryTypes.EQ
                || tokenType == XQueryTypes.GE
                || tokenType == XQueryTypes.GT
                || tokenType == XQueryTypes.LE
                || tokenType == XQueryTypes.LT
                || tokenType == XQueryTypes.NE
                ) {
            return OPERATION_SIGNS;
        }
        if (tokenType == XQueryTypes.K_IMPORT
                || tokenType == XQueryTypes.K_DEFAULT
                || tokenType == XQueryTypes.K_DECLARE
                || tokenType == XQueryTypes.K_NAMESPACE
                || tokenType == XQueryTypes.K_SCHEMA
                || tokenType == XQueryTypes.K_AT
                || tokenType == XQueryTypes.K_COLLATION
                || tokenType == XQueryTypes.K_ELEMENT
                || tokenType == XQueryTypes.K_FUNCTION
                || tokenType == XQueryTypes.K_TEXT
                || tokenType == XQueryTypes.K_COMMENT
                || tokenType == XQueryTypes.K_NODE
                || tokenType == XQueryTypes.K_PI
                || tokenType == XQueryTypes.K_IF
                || tokenType == XQueryTypes.K_THEN
                || tokenType == XQueryTypes.K_ELSE
                || tokenType == XQueryTypes.K_TYPESWITCH
                || tokenType == XQueryTypes.K_SWITCH
                || tokenType == XQueryTypes.K_VALIDATE
                || tokenType == XQueryTypes.K_CONTEXT
                || tokenType == XQueryTypes.K_MODULE
                || tokenType == XQueryTypes.K_INHERIT
                || tokenType == XQueryTypes.K_NO_INHERIT
                || tokenType == XQueryTypes.K_PRESERVE
                || tokenType == XQueryTypes.K_NO_PRESERVE
                || tokenType == XQueryTypes.K_BASE_URI
                || tokenType == XQueryTypes.K_XQUERY
                || tokenType == XQueryTypes.K_VERSION
                || tokenType == XQueryTypes.K_ENCODING
                || tokenType == XQueryTypes.K_OPTION
                || tokenType == XQueryTypes.K_STRIP
                || tokenType == XQueryTypes.K_SOME
                || tokenType == XQueryTypes.K_IN
                || tokenType == XQueryTypes.K_AS
                || tokenType == XQueryTypes.K_LET
                || tokenType == XQueryTypes.K_RETURN
                || tokenType == XQueryTypes.K_AND
                || tokenType == XQueryTypes.K_OR
                || tokenType == XQueryTypes.K_CASE
                || tokenType == XQueryTypes.K_FOR
                || tokenType == XQueryTypes.K_VARIABLE
                || tokenType == XQueryTypes.K_COPY_NAMESPACES
                || tokenType == XQueryTypes.K_CONSTRUCTION
                || tokenType == XQueryTypes.K_ORDER
                || tokenType == XQueryTypes.K_EVERY
                || tokenType == XQueryTypes.K_BOUNDARY_SPACE
                || tokenType == XQueryTypes.K_ORDERING
                || tokenType == XQueryTypes.K_ORDERED
                || tokenType == XQueryTypes.K_UNORDERED
                || tokenType == XQueryTypes.K_EMPTY
                || tokenType == XQueryTypes.K_GREATEST
                || tokenType == XQueryTypes.K_LEAST
                || tokenType == XQueryTypes.K_DECIMAL_FORMAT
                || tokenType == XQueryTypes.K_DECIMAL_SEPARATOR
                || tokenType == XQueryTypes.K_GROUPING_SEPARATOR
                || tokenType == XQueryTypes.K_INFINITY
                || tokenType == XQueryTypes.K_MINUS_SIGN
                || tokenType == XQueryTypes.K_NAN
                || tokenType == XQueryTypes.K_PERCENT
                || tokenType == XQueryTypes.K_PER_MILLE
                || tokenType == XQueryTypes.K_ZERO_DIGIT
                || tokenType == XQueryTypes.K_DIGIT
                || tokenType == XQueryTypes.K_PATTERN_SEPARATOR
                || tokenType == XQueryTypes.K_ITEM
                || tokenType == XQueryTypes.K_TO
                || tokenType == XQueryTypes.K_WHERE
                || tokenType == XQueryTypes.K_GROUP
                || tokenType == XQueryTypes.K_BY
                || tokenType == XQueryTypes.K_ALLOWING
                || tokenType == XQueryTypes.K_ASCENDING
                || tokenType == XQueryTypes.K_DESCENDING
                || tokenType == XQueryTypes.K_MAP
                || tokenType == XQueryTypes.K_INSTANCE
                || tokenType == XQueryTypes.K_OF
                || tokenType == XQueryTypes.K_SATISFIES
                || tokenType == XQueryTypes.K_CHILD
                || tokenType == XQueryTypes.K_DESCENDANT
                || tokenType == XQueryTypes.K_SELF
                || tokenType == XQueryTypes.K_DESCENDANT_OR_SELF
                || tokenType == XQueryTypes.K_FOLLOWING_SIBLING
                || tokenType == XQueryTypes.K_FOLLOWING
                || tokenType == XQueryTypes.K_PARENT
                || tokenType == XQueryTypes.K_ANCESTOR
                || tokenType == XQueryTypes.K_PRECEDING_SIBLING
                || tokenType == XQueryTypes.K_PRECEDING
                || tokenType == XQueryTypes.K_ANCESTOR_OR_SELF
                || tokenType == XQueryTypes.K_TUMBLING
                || tokenType == XQueryTypes.K_SLIDING
                || tokenType == XQueryTypes.K_WINDOW
                || tokenType == XQueryTypes.K_START
                || tokenType == XQueryTypes.K_WHEN
                || tokenType == XQueryTypes.K_ONLY
                || tokenType == XQueryTypes.K_END
                || tokenType == XQueryTypes.K_WHEN
                || tokenType == XQueryTypes.K_PREVIOUS
                || tokenType == XQueryTypes.K_NEXT
                || tokenType == XQueryTypes.K_COUNT
                || tokenType == XQueryTypes.K_TRY
                || tokenType == XQueryTypes.K_CATCH
                || tokenType == XQueryTypes.K_DIV
                || tokenType == XQueryTypes.K_IDIV
                || tokenType == XQueryTypes.K_MOD
                || tokenType == XQueryTypes.K_UNION
                || tokenType == XQueryTypes.K_INTERSECT
                || tokenType == XQueryTypes.K_EXCEPT
                || tokenType == XQueryTypes.K_TREAT
                || tokenType == XQueryTypes.K_CASTABLE
                || tokenType == XQueryTypes.K_CAST
                || tokenType == XQueryTypes.K_IS
                || tokenType == XQueryTypes.K_TYPE
                || tokenType == XQueryTypes.K_LAX
                || tokenType == XQueryTypes.K_STRICT
                || tokenType == XQueryTypes.K_DOCUMENT
                || tokenType == XQueryTypes.K_EMPTY
                || tokenType == XQueryTypes.K_EVERY
                || tokenType == XQueryTypes.K_EXTERNAL
                || tokenType == XQueryTypes.K_STABLE
                || tokenType == XQueryTypes.K_EMPTY_SEQUENCE
                || tokenType == XQueryTypes.K_DOCUMENT_NODE
                || tokenType == XQueryTypes.K_NAMESPACE_NODE
                || tokenType == XQueryTypes.K_ATTRIBUTE
                || tokenType == XQueryTypes.K_SCHEMA_ATTRIBUTE
                || tokenType == XQueryTypes.K_SCHEMA_ELEMENT
                ) {
            return KEYWORDS;
        }
        return EMPTY;
    }
}