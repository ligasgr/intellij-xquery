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

package org.intellij.xquery.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;
import org.intellij.xquery.psi.XQueryTypes;

import static com.intellij.psi.tree.TokenSet.orSet;
import static org.intellij.xquery.parser.XQueryParserDefinition.CHARACTER_CONTENTS;
import static org.intellij.xquery.parser.XQueryParserDefinition.COMMENTS;
import static org.intellij.xquery.parser.XQueryParserDefinition.WHITE_SPACES;

public class XQueryLexer extends LookAheadLexer {
    public static final TokenSet KEYWORDS = TokenSet.create(
            XQueryTypes.K_ALLOWING,
            XQueryTypes.K_ANCESTOR,
            XQueryTypes.K_ANCESTOR_OR_SELF,
            XQueryTypes.K_AND,
            XQueryTypes.K_AS,
            XQueryTypes.K_ASCENDING,
            XQueryTypes.K_AT,
            XQueryTypes.K_ATTRIBUTE,
            XQueryTypes.K_BASE_URI,
            XQueryTypes.K_BOUNDARY_SPACE,
            XQueryTypes.K_BY,
            XQueryTypes.K_CASE,
            XQueryTypes.K_CAST,
            XQueryTypes.K_CASTABLE,
            XQueryTypes.K_CATCH,
            XQueryTypes.K_CHILD,
            XQueryTypes.K_COLLATION,
            XQueryTypes.K_COMMENT,
            XQueryTypes.K_CONSTRUCTION,
            XQueryTypes.K_CONTEXT,
            XQueryTypes.K_COPY_NAMESPACES,
            XQueryTypes.K_COUNT,
            XQueryTypes.K_DECIMAL_FORMAT,
            XQueryTypes.K_DECIMAL_SEPARATOR,
            XQueryTypes.K_DECLARE,
            XQueryTypes.K_DEFAULT,
            XQueryTypes.K_DESCENDANT,
            XQueryTypes.K_DESCENDANT_OR_SELF,
            XQueryTypes.K_DESCENDING,
            XQueryTypes.K_DIGIT,
            XQueryTypes.K_DIV,
            XQueryTypes.K_DOCUMENT,
            XQueryTypes.K_DOCUMENT_NODE,
            XQueryTypes.K_ELEMENT,
            XQueryTypes.K_ELSE,
            XQueryTypes.K_EMPTY,
            XQueryTypes.K_EMPTY_SEQUENCE,
            XQueryTypes.K_ENCODING,
            XQueryTypes.K_END,
            XQueryTypes.K_EVERY,
            XQueryTypes.K_EXCEPT,
            XQueryTypes.K_EXTERNAL,
            XQueryTypes.K_FOLLOWING,
            XQueryTypes.K_FOLLOWING_SIBLING,
            XQueryTypes.K_FOR,
            XQueryTypes.K_FUNCTION,
            XQueryTypes.K_GREATEST,
            XQueryTypes.K_GROUP,
            XQueryTypes.K_GROUPING_SEPARATOR,
            XQueryTypes.K_IDIV,
            XQueryTypes.K_IF,
            XQueryTypes.K_IMPORT,
            XQueryTypes.K_IN,
            XQueryTypes.K_INFINITY,
            XQueryTypes.K_INHERIT,
            XQueryTypes.K_INSTANCE,
            XQueryTypes.K_INTERSECT,
            XQueryTypes.K_IS,
            XQueryTypes.K_ITEM,
            XQueryTypes.K_LAX,
            XQueryTypes.K_LEAST,
            XQueryTypes.K_LET,
            XQueryTypes.K_MAP,
            XQueryTypes.K_MINUS_SIGN,
            XQueryTypes.K_MOD,
            XQueryTypes.K_MODULE,
            XQueryTypes.K_NAMESPACE,
            XQueryTypes.K_NAMESPACE_NODE,
            XQueryTypes.K_NAN,
            XQueryTypes.K_NEXT,
            XQueryTypes.K_NODE,
            XQueryTypes.K_NO_INHERIT,
            XQueryTypes.K_NO_PRESERVE,
            XQueryTypes.K_OF,
            XQueryTypes.K_ONLY,
            XQueryTypes.K_OPTION,
            XQueryTypes.K_OR,
            XQueryTypes.K_ORDER,
            XQueryTypes.K_ORDERED,
            XQueryTypes.K_ORDERING,
            XQueryTypes.K_PARENT,
            XQueryTypes.K_PATTERN_SEPARATOR,
            XQueryTypes.K_PERCENT,
            XQueryTypes.K_PER_MILLE,
            XQueryTypes.K_PI,
            XQueryTypes.K_PRECEDING,
            XQueryTypes.K_PRECEDING_SIBLING,
            XQueryTypes.K_PRESERVE,
            XQueryTypes.K_PREVIOUS,
            XQueryTypes.K_RENAME,
            XQueryTypes.K_REPLACE,
            XQueryTypes.K_RETURN,
            XQueryTypes.K_REVALIDATION,
            XQueryTypes.K_SATISFIES,
            XQueryTypes.K_SCHEMA,
            XQueryTypes.K_SCHEMA_ATTRIBUTE,
            XQueryTypes.K_SCHEMA_ELEMENT,
            XQueryTypes.K_SELF,
            XQueryTypes.K_SLIDING,
            XQueryTypes.K_SOME,
            XQueryTypes.K_STABLE,
            XQueryTypes.K_START,
            XQueryTypes.K_STRICT,
            XQueryTypes.K_STRIP,
            XQueryTypes.K_SWITCH,
            XQueryTypes.K_TEXT,
            XQueryTypes.K_THEN,
            XQueryTypes.K_TO,
            XQueryTypes.K_TREAT,
            XQueryTypes.K_TRY,
            XQueryTypes.K_TUMBLING,
            XQueryTypes.K_TYPE,
            XQueryTypes.K_TYPESWITCH,
            XQueryTypes.K_UNION,
            XQueryTypes.K_UNORDERED,
            XQueryTypes.K_VALIDATE,
            XQueryTypes.K_VARIABLE,
            XQueryTypes.K_VERSION,
            XQueryTypes.K_WHEN,
            XQueryTypes.K_WHERE,
            XQueryTypes.K_WINDOW,
            XQueryTypes.K_XQUERY,
            XQueryTypes.K_ZERO_DIGIT
    );

    public XQueryLexer() {
        super(new MergingLexerAdapter(new FlexAdapter(new _XQueryLexer()), orSet(COMMENTS, WHITE_SPACES, CHARACTER_CONTENTS)));
    }
}
