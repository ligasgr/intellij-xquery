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

package org.intellij.xquery.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.intellij.xquery.lexer.XQueryLexer;
import org.intellij.xquery.psi.XQueryBasicTypes;
import org.intellij.xquery.psi.XQueryTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class XQuerySyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("XQUERY_BAD_CHARACTER",
            DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE);
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("XQUERY_KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey SEMICOLON = createTextAttributesKey("XQUERY_SEMICOLON",
            DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey STRING = createTextAttributesKey("XQUERY_STRING",
            DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("XQUERY_NUMBER",
            DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey OPERATION_SIGN = createTextAttributesKey("XQUERY_OPERATION",
            DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey PARENTHESIS = createTextAttributesKey("XQUERY_PARENTHESIS",
            DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BRACKET = createTextAttributesKey("XQUERY_BRACKET",
            DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey BRACE = createTextAttributesKey("XQUERY_BRACE",
            DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("XQUERY_COMMENT",
            DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey DOC_COMMENT = createTextAttributesKey("XQUERY_DOC_COMMENT",
            DefaultLanguageHighlighterColors.DOC_COMMENT);
    public static final TextAttributesKey DOC_COMMENT_TAG = createTextAttributesKey("XQUERY_DOC_COMMENT_TAG",
            DefaultLanguageHighlighterColors.DOC_COMMENT_TAG);
    public static final TextAttributesKey XML_COMMENT = createTextAttributesKey("XQUERY_XML_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey XML_TAG = createTextAttributesKey("XQUERY_XML_TAG",
            DefaultLanguageHighlighterColors.MARKUP_TAG);
    public static final TextAttributesKey XML_TAG_NAME = createTextAttributesKey("XQUERY_XML_TAG_NAME");
    public static final TextAttributesKey XML_ATTRIBUTE_NAME = createTextAttributesKey("XQUERY_XML_ATTRIBUTE_NAME",
            DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE);
    public static final TextAttributesKey XML_TAG_DATA = createTextAttributesKey("XQUERY_XML_TAG_DATA",
            HighlighterColors.TEXT);
    public static final TextAttributesKey FUNCTION_CALL = createTextAttributesKey("XQUERY_FUNCTION_CALL",
            DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey FUNCTION_DECLARATION = createTextAttributesKey("XQUERY_FUNCTION_DECLARATION",
            DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey PARAMETER = createTextAttributesKey("XQUERY_PARAMETER",
            DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey LOCAL_VARIABLE = createTextAttributesKey("XQUERY_LOCAL_VARIABLE",
            DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
    public static final TextAttributesKey GLOBAL_VARIABLE = createTextAttributesKey("XQUERY_GLOBAL_VARIABLE",
            DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
    public static final TextAttributesKey PREFIXED_VARIABLE = createTextAttributesKey("XQUERY_PREFIXED_VARIABLE");
    public static final TextAttributesKey ITEM_TYPE = createTextAttributesKey("XQUERY_ITEM_TYPE",
            DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey ANNOTATION = createTextAttributesKey("XQUERY_ANNOTATION",
            DefaultLanguageHighlighterColors.METADATA);

    public static final TextAttributesKey XML_ENTITY_REFERENCE = createTextAttributesKey("XQUERY_XML_ENTITY_REFERENCE",
            DefaultLanguageHighlighterColors.MARKUP_ENTITY);
    private static final TextAttributesKey[] BAD_CHAR_KEYS = pack(BAD_CHARACTER);
    private static final TextAttributesKey[] KEYWORDS = pack(KEYWORD);
    private static final TextAttributesKey[] SEMICOLONS = pack(SEMICOLON);
    private static final TextAttributesKey[] STRINGS = pack(STRING);
    private static final TextAttributesKey[] NUMBERS = pack(NUMBER);
    private static final TextAttributesKey[] OPERATION_SIGNS = pack(OPERATION_SIGN);
    private static final TextAttributesKey[] PARENTHESES = pack(PARENTHESIS);
    private static final TextAttributesKey[] BRACKETS = pack(BRACKET);
    private static final TextAttributesKey[] BRACES = pack(BRACE);
    private static final TextAttributesKey[] COMMENTS = pack(COMMENT);
    private static final TextAttributesKey[] DOC_COMMENTS = pack(DOC_COMMENT);
    private static final TextAttributesKey[] XML_COMMENTS = pack(XML_COMMENT);
    private static final TextAttributesKey[] XML_TAGS = pack(XML_TAG);
    private static final TextAttributesKey[] XML_TAG_NAMES = pack(XML_TAG, XML_TAG_NAME);
    private static final TextAttributesKey[] XML_ATTRIBUTE_NAMES = pack(XML_TAG, XML_ATTRIBUTE_NAME);
    private static final TextAttributesKey[] XML_TAG_DATA_KEY = pack(XML_TAG_DATA);
    private static final TextAttributesKey[] XML_ENTITY_REFERENCES = pack(XML_ENTITY_REFERENCE);

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
                || tokenType == XQueryTypes.EXPRCOMMENTCONTENT
                || tokenType == XQueryTypes.EXPR_COMMENT_END
                || tokenType == XQueryTypes.EXPR_COMMENT_START) {
            return COMMENTS;
        }
        if (tokenType == XQueryBasicTypes.DOC_COMMENT_CONTENT
                || tokenType == XQueryBasicTypes.DOC_COMMENT_END
                || tokenType == XQueryBasicTypes.DOC_COMMENT_START) {
            return DOC_COMMENTS;
        }
        if (tokenType == XQueryTypes.XMLEMPTYELEMENTEND
                || tokenType == XQueryTypes.XMLENDTAGSTART
                || tokenType == XQueryTypes.XMLSTARTTAGSTART
                || tokenType == XQueryTypes.XMLTAGEND) {
            return XML_TAGS;
        }
        if (tokenType == XQueryTypes.XMLTAGNCNAME
                || tokenType == XQueryTypes.XMLCOLON) {
            return XML_TAG_NAMES;
        }
        if (tokenType == XQueryTypes.ATTRNCNAME
                || tokenType == XQueryTypes.ATTRCOLON
                || tokenType == XQueryTypes.ATTREQUAL) {
            return XML_ATTRIBUTE_NAMES;
        }
        if (tokenType == XQueryTypes.CHARREF
                || tokenType == XQueryTypes.PREDEFINEDENTITYREF) {
            return XML_ENTITY_REFERENCES;
        }
        if (tokenType == XQueryTypes.ELEMENTCONTENTCHAR) {
            return XML_TAG_DATA_KEY;
        }
        if (tokenType == XQueryTypes.DIRCOMMENTCHAR
                || tokenType == XQueryTypes.DIR_COMMENT_BEGIN
                || tokenType == XQueryTypes.DIR_COMMENT_END) {
            return XML_COMMENTS;
        }
        if (tokenType == XQueryTypes.DECIMALLITERAL
                || tokenType == XQueryTypes.DOUBLELITERAL
                || tokenType == XQueryTypes.INTEGERLITERAL) {
            return NUMBERS;
        }
        if (tokenType == XQueryTypes.STRINGCHAR
                || tokenType == XQueryTypes.CHAR
                || tokenType == XQueryTypes.OPENINGAPOS
                || tokenType == XQueryTypes.CLOSINGAPOS
                || tokenType == XQueryTypes.AMPERSAND
                || tokenType == XQueryTypes.OPENINGQUOT
                || tokenType == XQueryTypes.CLOSINGQUOT) {
            return STRINGS;
        }
        if (tokenType == XQueryTypes.SEMICOLON) {
            return SEMICOLONS;
        }
        if (tokenType == XQueryTypes.L_PAR || tokenType == XQueryTypes.R_PAR) {
            return PARENTHESES;
        }
        if (tokenType == XQueryTypes.L_BRACKET || tokenType == XQueryTypes.R_BRACKET) {
            return BRACKETS;
        }
        if (tokenType == XQueryTypes.L_C_BRACE || tokenType == XQueryTypes.R_C_BRACE) {
            return BRACES;
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
                || tokenType == XQueryTypes.EQUAL
                || tokenType == XQueryTypes.NOT_EQUAL
                || tokenType == XQueryTypes.OP_ASSIGN
                || tokenType == XQueryTypes.GE_CHARS
                || tokenType == XQueryTypes.GT_CHAR
                || tokenType == XQueryTypes.LE_CHARS
                || tokenType == XQueryTypes.LT_CHAR
                || tokenType == XQueryTypes.STAR_SIGN
                || tokenType == XQueryTypes.OP_PLUS
                || tokenType == XQueryTypes.OP_MINUS
                || tokenType == XQueryTypes.NODECOMP_GT
                || tokenType == XQueryTypes.NODECOMP_LT
                || tokenType == XQueryTypes.OP_ARROW
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
                || tokenType == XQueryTypes.K_BINARY
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
                || tokenType == XQueryTypes.K_REVALIDATION
                || tokenType == XQueryTypes.K_SKIP
                || tokenType == XQueryTypes.K_INSERT
                || tokenType == XQueryTypes.K_NODES
                || tokenType == XQueryTypes.K_FIRST
                || tokenType == XQueryTypes.K_LAST
                || tokenType == XQueryTypes.K_INTO
                || tokenType == XQueryTypes.K_AFTER
                || tokenType == XQueryTypes.K_BEFORE
                || tokenType == XQueryTypes.K_DELETE
                || tokenType == XQueryTypes.K_VALUE
                || tokenType == XQueryTypes.K_WITH
                || tokenType == XQueryTypes.K_COPY
                || tokenType == XQueryTypes.K_MODIFY
                || tokenType == XQueryTypes.K_RENAME
                || tokenType == XQueryTypes.K_REPLACE
                || tokenType == XQueryTypes.K_OBJECT_NODE
                || tokenType == XQueryTypes.K_NUMBER_NODE
                || tokenType == XQueryTypes.K_BOOLEAN_NODE
                || tokenType == XQueryTypes.K_NULL_NODE
                || tokenType == XQueryTypes.K_ARRAY_NODE
                || tokenType == XQueryTypes.K_ARRAY
                || tokenType == XQueryTypes.K_EXPONENT_SEPARATOR
                ) {
            return KEYWORDS;
        }
        return EMPTY;
    }
}