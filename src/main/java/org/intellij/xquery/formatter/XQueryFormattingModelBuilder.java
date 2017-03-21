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

package org.intellij.xquery.formatter;

import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.tree.TokenSet;
import org.intellij.xquery.XQueryLanguage;
import org.intellij.xquery.formatter.settings.XQueryCodeStyleSettings;
import org.intellij.xquery.psi.XQueryTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.formatting.FormattingModelProvider.createFormattingModelForPsiFile;
import static org.intellij.xquery.psi.XQueryTypes.*;

/**
 * User: ligasgr
 * Date: 21/08/13
 * Time: 19:49
 */
public class XQueryFormattingModelBuilder implements FormattingModelBuilder {

    private static final TokenSet KEYWORDS = TokenSet.create(K_NAMESPACE, K_DEFAULT, K_IMPORT, K_DECLARE, K_SCHEMA,
            K_AT, K_COLLATION, K_ELEMENT, K_FUNCTION, K_TEXT, K_COMMENT, K_NODE, K_PI, K_IF, K_THEN, K_ELSE,
            K_TYPESWITCH, K_SWITCH, K_VALIDATE, K_CONTEXT, K_MODULE, K_INHERIT, K_NO_INHERIT, K_PRESERVE,
            K_NO_PRESERVE, K_BASE_URI, K_XQUERY, K_VERSION, K_ENCODING, K_OPTION, K_STRIP, K_SOME, K_IN, K_AS, K_LET,
            K_RETURN, K_AND, K_OR, K_CASE, K_FOR, K_VARIABLE, K_COPY_NAMESPACES, K_CONSTRUCTION, K_ORDER, K_EVERY,
            K_BOUNDARY_SPACE, K_ORDERING, K_ORDERED, K_UNORDERED, K_EMPTY, K_GREATEST, K_LEAST, K_DECIMAL_FORMAT,
            K_DECIMAL_SEPARATOR, K_GROUPING_SEPARATOR, K_INFINITY, K_MINUS_SIGN, K_NAN, K_PERCENT, K_PER_MILLE,
            K_ZERO_DIGIT, K_DIGIT, K_PATTERN_SEPARATOR, K_ITEM, K_TO, K_WHERE, K_GROUP, K_BY, K_ALLOWING,
            K_ASCENDING, K_DESCENDING, K_INSTANCE, K_OF, K_SATISFIES, K_MAP, K_ATTRIBUTE, K_DOCUMENT_NODE,
            K_EMPTY_SEQUENCE, K_NAMESPACE_NODE, K_SCHEMA_ATTRIBUTE, K_SCHEMA_ELEMENT, K_DOCUMENT, K_STABLE, K_CHILD,
            K_DESCENDANT, K_SELF, K_DESCENDANT_OR_SELF, K_FOLLOWING_SIBLING, K_FOLLOWING, K_PARENT, K_ANCESTOR,
            K_PRECEDING_SIBLING, K_PRECEDING, K_ANCESTOR_OR_SELF, K_TUMBLING, K_SLIDING, K_WINDOW, K_START, K_WHEN,
            K_ONLY, K_END, K_PREVIOUS, K_NEXT, K_COUNT, K_TRY, K_CATCH, K_DIV, K_IDIV, K_MOD, K_UNION, K_INTERSECT,
            K_EXCEPT, K_TREAT, K_CASTABLE, K_CAST, K_IS, K_TYPE, K_LAX, K_STRICT, K_EXTERNAL, K_EXPONENT_SEPARATOR,
            K_ARRAY);

    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        CommonCodeStyleSettings commonSettings = settings.getCommonSettings(XQueryLanguage.INSTANCE);
        XQueryCodeStyleSettings xQuerySettings = settings.getCustomSettings(XQueryCodeStyleSettings.class);
        final XQueryFormattingBlock block = new XQueryFormattingBlock(element.getNode(), null, null, commonSettings,
                createSpacingBuilder(commonSettings, xQuerySettings, settings));
        FormattingModel result = createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
        return result;
    }

    private static SpacingBuilder createSpacingBuilder(CommonCodeStyleSettings settings,
                                                       XQueryCodeStyleSettings xQuerySettings,
                                                       CodeStyleSettings codeStyleSettings) {
        return new SpacingBuilder(codeStyleSettings, XQueryLanguage.INSTANCE)
                .before(SEPARATOR).none()
                .around(EQUALITY_COMP).spaceIf(settings.SPACE_AROUND_EQUALITY_OPERATORS)

                .aroundInside(EQUAL, MODULE_DECL).spaceIf(xQuerySettings.SPACE_AROUND_ASSIGNMENT_IN_PROLOG)
                .aroundInside(EQUAL, SCHEMA_IMPORT).spaceIf(xQuerySettings.SPACE_AROUND_ASSIGNMENT_IN_PROLOG)
                .aroundInside(EQUAL, MODULE_IMPORT).spaceIf(xQuerySettings.SPACE_AROUND_ASSIGNMENT_IN_PROLOG)
                .aroundInside(EQUAL, DECIMAL_FORMAT_DECL).spaceIf(xQuerySettings.SPACE_AROUND_ASSIGNMENT_IN_PROLOG)
                .aroundInside(EQUAL, NAMESPACE_DECL).spaceIf(xQuerySettings.SPACE_AROUND_ASSIGNMENT_IN_PROLOG)

                .aroundInside(ATTREQUAL, DIR_ATTRIBUTE).spaceIf(xQuerySettings
                        .SPACE_AROUND_ASSIGNMENT_IN_XML_ATTRIBUTE)

                .around(RELATIONAL_COMP).spaceIf(settings.SPACE_AROUND_RELATIONAL_OPERATORS)

                .afterInside(OP_PLUS, UNARY_EXPR).spaceIf(settings.SPACE_AROUND_UNARY_OPERATOR)
                .afterInside(OP_MINUS, UNARY_EXPR).spaceIf(settings.SPACE_AROUND_UNARY_OPERATOR)

                .around(ADDITIVE_OPERATOR).spaceIf(settings.SPACE_AROUND_ADDITIVE_OPERATORS)

                .around(COLON_COLON).spaceIf(xQuerySettings.SPACE_AROUND_AXIS_OPERATOR)

                .around(MULTIPLICATIVE_OPERATOR).spaceIf(settings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)

                .around(OP_ASSIGN).spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .around(EQUAL).spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .aroundInside(COLON, MAP_CONSTRUCTOR_ENTRY).spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS)

                .beforeInside(L_PAR, IF_EXPR).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)
                .beforeInside(L_PAR, SWITCH_EXPR).spaceIf(settings.SPACE_BEFORE_SWITCH_PARENTHESES)
                .beforeInside(L_PAR, TYPESWITCH_EXPR).spaceIf(xQuerySettings.SPACE_BEFORE_TYPESWITCH_PARENTHESES)

                .beforeInside(L_PAR, SEQUENCE_TYPE).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, GENERAL_ITEM_TYPE).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, ANY_KIND_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, DOCUMENT_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, TEXT_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, COMMENT_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, NAMESPACE_NODE_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, PI_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, ATTRIBUTE_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, SCHEMA_ATTRIBUTE_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, ELEMENT_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, MAP_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, SCHEMA_ELEMENT_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, ANY_FUNCTION_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)
                .beforeInside(L_PAR, TYPED_FUNCTION_TEST).spaceIf(xQuerySettings.SPACE_BEFORE_TYPE_TEST_PARENTHESES)

                .before(PARAM_LIST).spaceIf(xQuerySettings.SPACE_BEFORE_FUNCTION_DECLARATION_PARENTHESES)
                .beforeInside(ARGUMENT_LIST, FUNCTION_CALL).spaceIf(xQuerySettings
                        .SPACE_BEFORE_FUNCTION_CALL_PARENTHESES)

                .before(COMMA).spaceIf(settings.SPACE_BEFORE_COMMA)
                .after(COMMA).spaceIf(settings.SPACE_AFTER_COMMA)

                .around(KEYWORDS).spaces(1)
                .around(VERSION_DECL).spaces(1)
                .around(MODULE_DECL).spaces(1)
                .around(DEFAULT_FUNCTION_NAMESPACE_DECL).spaces(1)
                .around(DEFAULT_ELEMENT_NAMESPACE_DECL).spaces(1)
                .around(BOUNDARY_SPACE_DECL).spaces(1)
                .around(DEFAULT_COLLATION_DECL).spaces(1)
                .around(BASE_URI_DECL).spaces(1)
                .around(CONSTRUCTION_DECL).spaces(1)
                .around(ORDERING_MODE_DECL).spaces(1)
                .around(EMPTY_ORDER_DECL).spaces(1)
                .around(COPY_NAMESPACES_DECL).spaces(1)
                .around(DECIMAL_FORMAT_DECL).spaces(1)
                .around(NAMESPACE_DECL).spaces(1)
                .around(MODULE_IMPORT).spaces(1)
                .around(SCHEMA_IMPORT).spaces(1)
                .around(OPTION_DECL).spaces(1)
                .around(VAR_DECL).spaces(1)
                .around(FUNCTION_DECL).spaces(1)
                .around(FOR_CLAUSE).spaces(1)
                .around(WINDOW_CLAUSE).spaces(1)
                .around(LET_CLAUSE).spaces(1)
                .around(WHERE_CLAUSE).spaces(1)
                .around(GROUP_BY_CLAUSE).spaces(1)
                .around(ORDER_BY_CLAUSE).spaces(1)
                .around(COUNT_CLAUSE).spaces(1)
                .around(RETURN_CLAUSE).spaces(1)
                .around(SWITCH_CASE_CLAUSE).spaces(1)
                .around(SWITCH_RETURN_CLAUSE).spaces(1)
                .around(SWITCH_DEFAULT_RETURN_CLAUSE).spaces(1)
                .around(CASE_CLAUSE).spaces(1)
                .around(TYPESWITCH_DEFAULT_RETURN_CLAUSE).spaces(1)
                .around(TYPE_DECLARATION).spaces(1)
                .around(TRY_CLAUSE).spaces(1)
                .around(CATCH_CLAUSE).spaces(1)
                .around(TO_OPERATOR).spaces(1)
                .around(AND_OPERATOR).spaces(1)
                .around(OR_OPERATOR).spaces(1)
                .around(UNION_OPERATOR).spaces(1)
                .around(CONCAT_OPERATOR).spaces(1)
                .around(INTERSECT_EXCEPT_OPERATOR).spaces(1)
                .around(INSTANCE_OF_OPERATOR).spaces(1)
                .around(TREAT_OPERATOR).spaces(1)
                .around(CASTABLE_OPERATOR).spaces(1)
                .around(CAST_OPERATOR).spaces(1)
                .around(NODE_COMP).spaces(1)
                .around(VALUE_COMP).spaces(1)
                .around(CATCH_CLAUSE_EXPRESSION).spaces(1)

                .after(L_BRACKET).none()
                .before(R_BRACKET).none()
                .after(L_C_BRACE).none()
                .before(L_C_BRACE).spaces(1)
                .before(R_C_BRACE).none()
                .after(R_C_BRACE).spaces(1)
                .before(ARGUMENT_LIST).none()
                .before(ARGUMENT).none()
                .after(ARGUMENT).none()
                .before(PARAM).none()
                .after(PARAM).none()
                .after(EXPR).none()
                ;
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }
}
