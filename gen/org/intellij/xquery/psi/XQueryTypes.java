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

// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.intellij.xquery.psi.impl.*;

public interface XQueryTypes {

  IElementType ABBREV_FORWARD_STEP = new XQueryElementType("ABBREV_FORWARD_STEP");
  IElementType ABBREV_REVERSE_STEP = new XQueryElementType("ABBREV_REVERSE_STEP");
  IElementType ADDITIVE_EXPR = new XQueryElementType("ADDITIVE_EXPR");
  IElementType ADDITIVE_OPERATOR = new XQueryElementType("ADDITIVE_OPERATOR");
  IElementType ALLOWING_EMPTY = new XQueryElementType("ALLOWING_EMPTY");
  IElementType AND_EXPR = new XQueryElementType("AND_EXPR");
  IElementType AND_OPERATOR = new XQueryElementType("AND_OPERATOR");
  IElementType ANNOTATION = new XQueryElementType("ANNOTATION");
  IElementType ANNOTATION_NAME = new XQueryElementType("ANNOTATION_NAME");
  IElementType ANY_FUNCTION_TEST = new XQueryElementType("ANY_FUNCTION_TEST");
  IElementType ANY_KIND_TEST = new XQueryElementType("ANY_KIND_TEST");
  IElementType APOS_ATTR_CONTENT_CHAR = new XQueryElementType("APOS_ATTR_CONTENT_CHAR");
  IElementType APOS_ATTR_VALUE_CONTENT = new XQueryElementType("APOS_ATTR_VALUE_CONTENT");
  IElementType ARGUMENT = new XQueryElementType("ARGUMENT");
  IElementType ARGUMENT_LIST = new XQueryElementType("ARGUMENT_LIST");
  IElementType ARGUMENT_PLACEHOLDER = new XQueryElementType("ARGUMENT_PLACEHOLDER");
  IElementType ARRAY_CONSTRUCTOR = new XQueryElementType("ARRAY_CONSTRUCTOR");
  IElementType ARRAY_TEST = new XQueryElementType("ARRAY_TEST");
  IElementType ARROW_EXPR = new XQueryElementType("ARROW_EXPR");
  IElementType ARROW_FUNCTION_REFERENCE = new XQueryElementType("ARROW_FUNCTION_REFERENCE");
  IElementType ARROW_FUNCTION_SPECIFIER = new XQueryElementType("ARROW_FUNCTION_SPECIFIER");
  IElementType ATOMIC_OR_UNION_TYPE = new XQueryElementType("ATOMIC_OR_UNION_TYPE");
  IElementType ATTRIBUTE_DECLARATION = new XQueryElementType("ATTRIBUTE_DECLARATION");
  IElementType ATTRIBUTE_NAME = new XQueryElementType("ATTRIBUTE_NAME");
  IElementType ATTRIBUTE_TEST = new XQueryElementType("ATTRIBUTE_TEST");
  IElementType ATTRIB_NAME_OR_WILDCARD = new XQueryElementType("ATTRIB_NAME_OR_WILDCARD");
  IElementType ATTR_LOCAL_NAME = new XQueryElementType("ATTR_LOCAL_NAME");
  IElementType ATTR_NAMESPACE = new XQueryElementType("ATTR_NAMESPACE");
  IElementType AXIS_STEP = new XQueryElementType("AXIS_STEP");
  IElementType BASE_URI_DECL = new XQueryElementType("BASE_URI_DECL");
  IElementType BOUNDARY_SPACE_DECL = new XQueryElementType("BOUNDARY_SPACE_DECL");
  IElementType CASE_CLAUSE = new XQueryElementType("CASE_CLAUSE");
  IElementType CASTABLE_EXPR = new XQueryElementType("CASTABLE_EXPR");
  IElementType CASTABLE_OPERATOR = new XQueryElementType("CASTABLE_OPERATOR");
  IElementType CAST_EXPR = new XQueryElementType("CAST_EXPR");
  IElementType CAST_OPERATOR = new XQueryElementType("CAST_OPERATOR");
  IElementType CATCH_CLAUSE = new XQueryElementType("CATCH_CLAUSE");
  IElementType CATCH_CLAUSE_EXPRESSION = new XQueryElementType("CATCH_CLAUSE_EXPRESSION");
  IElementType CATCH_ERROR_LIST = new XQueryElementType("CATCH_ERROR_LIST");
  IElementType COMMENT_TEST = new XQueryElementType("COMMENT_TEST");
  IElementType COMMON_CONTENT = new XQueryElementType("COMMON_CONTENT");
  IElementType COMPARISON_EXPR = new XQueryElementType("COMPARISON_EXPR");
  IElementType COMPATIBILITY_ANNOTATION = new XQueryElementType("COMPATIBILITY_ANNOTATION");
  IElementType COMPUTED_CONSTRUCTOR = new XQueryElementType("COMPUTED_CONSTRUCTOR");
  IElementType COMP_ATTR_CONSTRUCTOR = new XQueryElementType("COMP_ATTR_CONSTRUCTOR");
  IElementType COMP_COMMENT_CONSTRUCTOR = new XQueryElementType("COMP_COMMENT_CONSTRUCTOR");
  IElementType COMP_DOC_CONSTRUCTOR = new XQueryElementType("COMP_DOC_CONSTRUCTOR");
  IElementType COMP_ELEM_CONSTRUCTOR = new XQueryElementType("COMP_ELEM_CONSTRUCTOR");
  IElementType COMP_NAMESPACE_CONSTRUCTOR = new XQueryElementType("COMP_NAMESPACE_CONSTRUCTOR");
  IElementType COMP_PI_CONSTRUCTOR = new XQueryElementType("COMP_PI_CONSTRUCTOR");
  IElementType COMP_TEXT_CONSTRUCTOR = new XQueryElementType("COMP_TEXT_CONSTRUCTOR");
  IElementType CONCAT_OPERATOR = new XQueryElementType("CONCAT_OPERATOR");
  IElementType CONSTRUCTION_DECL = new XQueryElementType("CONSTRUCTION_DECL");
  IElementType CONTEXT_ITEM_DECL = new XQueryElementType("CONTEXT_ITEM_DECL");
  IElementType CONTEXT_ITEM_EXPR = new XQueryElementType("CONTEXT_ITEM_EXPR");
  IElementType COPY_NAMESPACES_DECL = new XQueryElementType("COPY_NAMESPACES_DECL");
  IElementType COUNT_CLAUSE = new XQueryElementType("COUNT_CLAUSE");
  IElementType CURLY_ARRAY_CONSTRUCTOR = new XQueryElementType("CURLY_ARRAY_CONSTRUCTOR");
  IElementType CURRENT_ITEM = new XQueryElementType("CURRENT_ITEM");
  IElementType C_DATA_SECTION = new XQueryElementType("C_DATA_SECTION");
  IElementType C_DATA_SECTION_CONTENTS = new XQueryElementType("C_DATA_SECTION_CONTENTS");
  IElementType DECIMAL_FORMAT_DECL = new XQueryElementType("DECIMAL_FORMAT_DECL");
  IElementType DEFAULT_COLLATION_DECL = new XQueryElementType("DEFAULT_COLLATION_DECL");
  IElementType DEFAULT_ELEMENT_NAMESPACE_DECL = new XQueryElementType("DEFAULT_ELEMENT_NAMESPACE_DECL");
  IElementType DEFAULT_FUNCTION_NAMESPACE_DECL = new XQueryElementType("DEFAULT_FUNCTION_NAMESPACE_DECL");
  IElementType DELETE_EXPR = new XQueryElementType("DELETE_EXPR");
  IElementType DIRECT_CONSTRUCTOR = new XQueryElementType("DIRECT_CONSTRUCTOR");
  IElementType DIR_ATTRIBUTE = new XQueryElementType("DIR_ATTRIBUTE");
  IElementType DIR_ATTRIBUTE_LIST = new XQueryElementType("DIR_ATTRIBUTE_LIST");
  IElementType DIR_ATTRIBUTE_NAME = new XQueryElementType("DIR_ATTRIBUTE_NAME");
  IElementType DIR_ATTRIBUTE_VALUE = new XQueryElementType("DIR_ATTRIBUTE_VALUE");
  IElementType DIR_COMMENT_CONSTRUCTOR = new XQueryElementType("DIR_COMMENT_CONSTRUCTOR");
  IElementType DIR_COMMENT_CONTENTS = new XQueryElementType("DIR_COMMENT_CONTENTS");
  IElementType DIR_ELEM_CONTENT = new XQueryElementType("DIR_ELEM_CONTENT");
  IElementType DIR_PI_CONSTRUCTOR = new XQueryElementType("DIR_PI_CONSTRUCTOR");
  IElementType DIR_PI_CONTENTS = new XQueryElementType("DIR_PI_CONTENTS");
  IElementType DOCUMENT_TEST = new XQueryElementType("DOCUMENT_TEST");
  IElementType ELEMENT_DECLARATION = new XQueryElementType("ELEMENT_DECLARATION");
  IElementType ELEMENT_NAME = new XQueryElementType("ELEMENT_NAME");
  IElementType ELEMENT_NAME_OR_WILDCARD = new XQueryElementType("ELEMENT_NAME_OR_WILDCARD");
  IElementType ELEMENT_TEST = new XQueryElementType("ELEMENT_TEST");
  IElementType EMPTY_ORDER_DECL = new XQueryElementType("EMPTY_ORDER_DECL");
  IElementType ENCLOSED_CONTENT_EXPRESSION = new XQueryElementType("ENCLOSED_CONTENT_EXPRESSION");
  IElementType ENCLOSED_EXPRESSION = new XQueryElementType("ENCLOSED_EXPRESSION");
  IElementType ENCLOSED_PREFIX_EXPRESSION = new XQueryElementType("ENCLOSED_PREFIX_EXPRESSION");
  IElementType ENCLOSED_URI_EXPRESSION = new XQueryElementType("ENCLOSED_URI_EXPRESSION");
  IElementType EQUALITY_COMP = new XQueryElementType("EQUALITY_COMP");
  IElementType ESCAPE_APOS = new XQueryElementType("ESCAPE_APOS");
  IElementType ESCAPE_QUOT = new XQueryElementType("ESCAPE_QUOT");
  IElementType EXPR = new XQueryElementType("EXPR");
  IElementType EXPR_SINGLE = new XQueryElementType("EXPR_SINGLE");
  IElementType EXTENSION_EXPR = new XQueryElementType("EXTENSION_EXPR");
  IElementType EXTERNAL_VAR_PART = new XQueryElementType("EXTERNAL_VAR_PART");
  IElementType FLWOR_EXPR = new XQueryElementType("FLWOR_EXPR");
  IElementType FORWARD_STEP = new XQueryElementType("FORWARD_STEP");
  IElementType FOR_BINDING = new XQueryElementType("FOR_BINDING");
  IElementType FOR_CLAUSE = new XQueryElementType("FOR_CLAUSE");
  IElementType FUNCTION_ARITY = new XQueryElementType("FUNCTION_ARITY");
  IElementType FUNCTION_BODY = new XQueryElementType("FUNCTION_BODY");
  IElementType FUNCTION_CALL = new XQueryElementType("FUNCTION_CALL");
  IElementType FUNCTION_DECL = new XQueryElementType("FUNCTION_DECL");
  IElementType FUNCTION_ITEM_EXPR = new XQueryElementType("FUNCTION_ITEM_EXPR");
  IElementType FUNCTION_LOCAL_NAME = new XQueryElementType("FUNCTION_LOCAL_NAME");
  IElementType FUNCTION_NAME = new XQueryElementType("FUNCTION_NAME");
  IElementType FUNCTION_TEST = new XQueryElementType("FUNCTION_TEST");
  IElementType GENERAL_ITEM_TYPE = new XQueryElementType("GENERAL_ITEM_TYPE");
  IElementType GROUPING_SPEC = new XQueryElementType("GROUPING_SPEC");
  IElementType GROUPING_SPEC_LIST = new XQueryElementType("GROUPING_SPEC_LIST");
  IElementType GROUPING_VARIABLE = new XQueryElementType("GROUPING_VARIABLE");
  IElementType GROUP_BY_CLAUSE = new XQueryElementType("GROUP_BY_CLAUSE");
  IElementType IF_EXPR = new XQueryElementType("IF_EXPR");
  IElementType INHERIT_MODE = new XQueryElementType("INHERIT_MODE");
  IElementType INLINE_FUNCTION_EXPR = new XQueryElementType("INLINE_FUNCTION_EXPR");
  IElementType INSERT_EXPR = new XQueryElementType("INSERT_EXPR");
  IElementType INSERT_EXPR_TARGET_CHOICE = new XQueryElementType("INSERT_EXPR_TARGET_CHOICE");
  IElementType INSTANCEOF_EXPR = new XQueryElementType("INSTANCEOF_EXPR");
  IElementType INSTANCE_OF_OPERATOR = new XQueryElementType("INSTANCE_OF_OPERATOR");
  IElementType INTERSECT_EXCEPT_EXPR = new XQueryElementType("INTERSECT_EXCEPT_EXPR");
  IElementType INTERSECT_EXCEPT_OPERATOR = new XQueryElementType("INTERSECT_EXCEPT_OPERATOR");
  IElementType ITEM_TYPE = new XQueryElementType("ITEM_TYPE");
  IElementType KEY_SPECIFIER = new XQueryElementType("KEY_SPECIFIER");
  IElementType KIND_TEST = new XQueryElementType("KIND_TEST");
  IElementType LET_BINDING = new XQueryElementType("LET_BINDING");
  IElementType LET_CLAUSE = new XQueryElementType("LET_CLAUSE");
  IElementType LITERAL = new XQueryElementType("LITERAL");
  IElementType LOCAL_PART = new XQueryElementType("LOCAL_PART");
  IElementType LOOKUP = new XQueryElementType("LOOKUP");
  IElementType MAP_CONSTRUCTOR = new XQueryElementType("MAP_CONSTRUCTOR");
  IElementType MAP_CONSTRUCTOR_ENTRY = new XQueryElementType("MAP_CONSTRUCTOR_ENTRY");
  IElementType MAP_KEY_EXPR = new XQueryElementType("MAP_KEY_EXPR");
  IElementType MAP_TEST = new XQueryElementType("MAP_TEST");
  IElementType MAP_VALUE_EXPR = new XQueryElementType("MAP_VALUE_EXPR");
  IElementType MARKLOGIC_ANNOTATION = new XQueryElementType("MARKLOGIC_ANNOTATION");
  IElementType MARKLOGIC_ANY_KIND_TEST = new XQueryElementType("MARKLOGIC_ANY_KIND_TEST");
  IElementType MARKLOGIC_ARRAY_NODE_TEST = new XQueryElementType("MARKLOGIC_ARRAY_NODE_TEST");
  IElementType MARKLOGIC_BINARY_TEST = new XQueryElementType("MARKLOGIC_BINARY_TEST");
  IElementType MARKLOGIC_BOOLEAN_NODE_TEST = new XQueryElementType("MARKLOGIC_BOOLEAN_NODE_TEST");
  IElementType MARKLOGIC_CATCH_ERROR_LIST = new XQueryElementType("MARKLOGIC_CATCH_ERROR_LIST");
  IElementType MARKLOGIC_COMP_ARRAY_NODE_CONSTRUCTOR = new XQueryElementType("MARKLOGIC_COMP_ARRAY_NODE_CONSTRUCTOR");
  IElementType MARKLOGIC_COMP_BINARY_CONSTRUCTOR = new XQueryElementType("MARKLOGIC_COMP_BINARY_CONSTRUCTOR");
  IElementType MARKLOGIC_COMP_BOOLEAN_NODE_CONSTRUCTOR = new XQueryElementType("MARKLOGIC_COMP_BOOLEAN_NODE_CONSTRUCTOR");
  IElementType MARKLOGIC_COMP_NULL_NODE_CONSTRUCTOR = new XQueryElementType("MARKLOGIC_COMP_NULL_NODE_CONSTRUCTOR");
  IElementType MARKLOGIC_COMP_NUMBER_NODE_CONSTRUCTOR = new XQueryElementType("MARKLOGIC_COMP_NUMBER_NODE_CONSTRUCTOR");
  IElementType MARKLOGIC_COMP_OBJECT_NODE_CONSTRUCTOR = new XQueryElementType("MARKLOGIC_COMP_OBJECT_NODE_CONSTRUCTOR");
  IElementType MARKLOGIC_NAMESPACE_AXIS = new XQueryElementType("MARKLOGIC_NAMESPACE_AXIS");
  IElementType MARKLOGIC_NULL_NODE_TEST = new XQueryElementType("MARKLOGIC_NULL_NODE_TEST");
  IElementType MARKLOGIC_NUMBER_NODE_TEST = new XQueryElementType("MARKLOGIC_NUMBER_NODE_TEST");
  IElementType MARKLOGIC_OBJECT_NODE_TEST = new XQueryElementType("MARKLOGIC_OBJECT_NODE_TEST");
  IElementType MARKLOGIC_TEXT_TEST = new XQueryElementType("MARKLOGIC_TEXT_TEST");
  IElementType MARKLOGIC_VALIDATION = new XQueryElementType("MARKLOGIC_VALIDATION");
  IElementType MISPLACED_COMMENT = new XQueryElementType("MISPLACED_COMMENT");
  IElementType MODULE_DECL = new XQueryElementType("MODULE_DECL");
  IElementType MODULE_IMPORT = new XQueryElementType("MODULE_IMPORT");
  IElementType MODULE_IMPORT_NAMESPACE = new XQueryElementType("MODULE_IMPORT_NAMESPACE");
  IElementType MODULE_IMPORT_PATH = new XQueryElementType("MODULE_IMPORT_PATH");
  IElementType MULTIPLICATIVE_EXPR = new XQueryElementType("MULTIPLICATIVE_EXPR");
  IElementType MULTIPLICATIVE_OPERATOR = new XQueryElementType("MULTIPLICATIVE_OPERATOR");
  IElementType MULTI_VARIABLE_BINDING = new XQueryElementType("MULTI_VARIABLE_BINDING");
  IElementType NAMED_FUNCTION_REF = new XQueryElementType("NAMED_FUNCTION_REF");
  IElementType NAMESPACE_DECL = new XQueryElementType("NAMESPACE_DECL");
  IElementType NAMESPACE_NODE_TEST = new XQueryElementType("NAMESPACE_NODE_TEST");
  IElementType NAMESPACE_PREFIX = new XQueryElementType("NAMESPACE_PREFIX");
  IElementType NAME_TEST = new XQueryElementType("NAME_TEST");
  IElementType NEW_NAME_EXPR = new XQueryElementType("NEW_NAME_EXPR");
  IElementType NEXT_ITEM = new XQueryElementType("NEXT_ITEM");
  IElementType NODE_COMP = new XQueryElementType("NODE_COMP");
  IElementType NODE_CONSTRUCTOR = new XQueryElementType("NODE_CONSTRUCTOR");
  IElementType NODE_TEST = new XQueryElementType("NODE_TEST");
  IElementType NUMERIC_LITERAL = new XQueryElementType("NUMERIC_LITERAL");
  IElementType OBJECT_PROPERTY = new XQueryElementType("OBJECT_PROPERTY");
  IElementType OBJECT_PROPERTY_LIST = new XQueryElementType("OBJECT_PROPERTY_LIST");
  IElementType OCCURRENCE_INDICATOR = new XQueryElementType("OCCURRENCE_INDICATOR");
  IElementType OPTION_DECL = new XQueryElementType("OPTION_DECL");
  IElementType ORDERED_EXPR = new XQueryElementType("ORDERED_EXPR");
  IElementType ORDERING_MODE_DECL = new XQueryElementType("ORDERING_MODE_DECL");
  IElementType ORDER_BY_CLAUSE = new XQueryElementType("ORDER_BY_CLAUSE");
  IElementType ORDER_SPEC = new XQueryElementType("ORDER_SPEC");
  IElementType ORDER_SPEC_LIST = new XQueryElementType("ORDER_SPEC_LIST");
  IElementType OR_EXPR = new XQueryElementType("OR_EXPR");
  IElementType OR_OPERATOR = new XQueryElementType("OR_OPERATOR");
  IElementType PARAM = new XQueryElementType("PARAM");
  IElementType PARAM_LIST = new XQueryElementType("PARAM_LIST");
  IElementType PARENTHESIZED_EXPR = new XQueryElementType("PARENTHESIZED_EXPR");
  IElementType PARENTHESIZED_ITEM_TYPE = new XQueryElementType("PARENTHESIZED_ITEM_TYPE");
  IElementType PATH_EXPR = new XQueryElementType("PATH_EXPR");
  IElementType PI_TEST = new XQueryElementType("PI_TEST");
  IElementType POSITIONAL_VAR = new XQueryElementType("POSITIONAL_VAR");
  IElementType POSTFIX_EXPR = new XQueryElementType("POSTFIX_EXPR");
  IElementType PRAGMA = new XQueryElementType("PRAGMA");
  IElementType PRAGMA_CONTENTS = new XQueryElementType("PRAGMA_CONTENTS");
  IElementType PREDICATE = new XQueryElementType("PREDICATE");
  IElementType PREDICATE_LIST = new XQueryElementType("PREDICATE_LIST");
  IElementType PREFIX = new XQueryElementType("PREFIX");
  IElementType PRESERVE_MODE = new XQueryElementType("PRESERVE_MODE");
  IElementType PREVIOUS_ITEM = new XQueryElementType("PREVIOUS_ITEM");
  IElementType PRIMARY_EXPR = new XQueryElementType("PRIMARY_EXPR");
  IElementType QUANTIFIED_EXPR = new XQueryElementType("QUANTIFIED_EXPR");
  IElementType QUERY_BODY = new XQueryElementType("QUERY_BODY");
  IElementType QUOT_ATTR_CONTENT_CHAR = new XQueryElementType("QUOT_ATTR_CONTENT_CHAR");
  IElementType QUOT_ATTR_VALUE_CONTENT = new XQueryElementType("QUOT_ATTR_VALUE_CONTENT");
  IElementType RANGE_EXPR = new XQueryElementType("RANGE_EXPR");
  IElementType RELATIONAL_COMP = new XQueryElementType("RELATIONAL_COMP");
  IElementType RELATIVE_PATH_OPERATOR = new XQueryElementType("RELATIVE_PATH_OPERATOR");
  IElementType RENAME_EXPR = new XQueryElementType("RENAME_EXPR");
  IElementType REPLACE_EXPR = new XQueryElementType("REPLACE_EXPR");
  IElementType RETURN_CLAUSE = new XQueryElementType("RETURN_CLAUSE");
  IElementType REVALIDATION_DECL = new XQueryElementType("REVALIDATION_DECL");
  IElementType REVERSE_STEP = new XQueryElementType("REVERSE_STEP");
  IElementType SAXON_MAP_ENTRIES_SEPARATOR = new XQueryElementType("SAXON_MAP_ENTRIES_SEPARATOR");
  IElementType SAXON_MAP_ENTRY_SEPARATOR = new XQueryElementType("SAXON_MAP_ENTRY_SEPARATOR");
  IElementType SCHEMA_ATTRIBUTE_TEST = new XQueryElementType("SCHEMA_ATTRIBUTE_TEST");
  IElementType SCHEMA_ELEMENT_TEST = new XQueryElementType("SCHEMA_ELEMENT_TEST");
  IElementType SCHEMA_IMPORT = new XQueryElementType("SCHEMA_IMPORT");
  IElementType SEPARATOR = new XQueryElementType("SEPARATOR");
  IElementType SEQUENCE_TYPE = new XQueryElementType("SEQUENCE_TYPE");
  IElementType SEQUENCE_TYPE_UNION = new XQueryElementType("SEQUENCE_TYPE_UNION");
  IElementType SIMPLE_MAP_EXPR = new XQueryElementType("SIMPLE_MAP_EXPR");
  IElementType SIMPLE_MAP_OPERATOR = new XQueryElementType("SIMPLE_MAP_OPERATOR");
  IElementType SIMPLE_TYPE_NAME = new XQueryElementType("SIMPLE_TYPE_NAME");
  IElementType SINGLE_TYPE = new XQueryElementType("SINGLE_TYPE");
  IElementType SOURCE_EXPR = new XQueryElementType("SOURCE_EXPR");
  IElementType SQUARE_ARRAY_CONSTRUCTOR = new XQueryElementType("SQUARE_ARRAY_CONSTRUCTOR");
  IElementType STEP_EXPR = new XQueryElementType("STEP_EXPR");
  IElementType STRING_CONCAT_EXPR = new XQueryElementType("STRING_CONCAT_EXPR");
  IElementType STRING_CONSTRUCTOR = new XQueryElementType("STRING_CONSTRUCTOR");
  IElementType STRING_CONSTRUCTOR_CHARS = new XQueryElementType("STRING_CONSTRUCTOR_CHARS");
  IElementType STRING_CONSTRUCTOR_CONTENT = new XQueryElementType("STRING_CONSTRUCTOR_CONTENT");
  IElementType STRING_CONSTRUCTOR_INTERPOLATION = new XQueryElementType("STRING_CONSTRUCTOR_INTERPOLATION");
  IElementType STRING_LITERAL = new XQueryElementType("STRING_LITERAL");
  IElementType STRING_LITERAL_OR_WILDCARD = new XQueryElementType("STRING_LITERAL_OR_WILDCARD");
  IElementType SWITCH_CASE_CLAUSE = new XQueryElementType("SWITCH_CASE_CLAUSE");
  IElementType SWITCH_CASE_OPERAND = new XQueryElementType("SWITCH_CASE_OPERAND");
  IElementType SWITCH_DEFAULT_RETURN_CLAUSE = new XQueryElementType("SWITCH_DEFAULT_RETURN_CLAUSE");
  IElementType SWITCH_EXPR = new XQueryElementType("SWITCH_EXPR");
  IElementType SWITCH_RETURN_CLAUSE = new XQueryElementType("SWITCH_RETURN_CLAUSE");
  IElementType TARGET_EXPR = new XQueryElementType("TARGET_EXPR");
  IElementType TEXT_TEST = new XQueryElementType("TEXT_TEST");
  IElementType TO_OPERATOR = new XQueryElementType("TO_OPERATOR");
  IElementType TRANSFORM_EXPR = new XQueryElementType("TRANSFORM_EXPR");
  IElementType TREAT_EXPR = new XQueryElementType("TREAT_EXPR");
  IElementType TREAT_OPERATOR = new XQueryElementType("TREAT_OPERATOR");
  IElementType TRY_CATCH_EXPR = new XQueryElementType("TRY_CATCH_EXPR");
  IElementType TRY_CLAUSE = new XQueryElementType("TRY_CLAUSE");
  IElementType TYPED_FUNCTION_TEST = new XQueryElementType("TYPED_FUNCTION_TEST");
  IElementType TYPESWITCH_DEFAULT_RETURN_CLAUSE = new XQueryElementType("TYPESWITCH_DEFAULT_RETURN_CLAUSE");
  IElementType TYPESWITCH_EXPR = new XQueryElementType("TYPESWITCH_EXPR");
  IElementType TYPE_DECLARATION = new XQueryElementType("TYPE_DECLARATION");
  IElementType TYPE_NAME = new XQueryElementType("TYPE_NAME");
  IElementType UNARY_EXPR = new XQueryElementType("UNARY_EXPR");
  IElementType UNARY_LOOKUP = new XQueryElementType("UNARY_LOOKUP");
  IElementType UNION_EXPR = new XQueryElementType("UNION_EXPR");
  IElementType UNION_OPERATOR = new XQueryElementType("UNION_OPERATOR");
  IElementType UNORDERED_EXPR = new XQueryElementType("UNORDERED_EXPR");
  IElementType URI_LITERAL = new XQueryElementType("URI_LITERAL");
  IElementType VALIDATE_EXPR = new XQueryElementType("VALIDATE_EXPR");
  IElementType VALUE_COMP = new XQueryElementType("VALUE_COMP");
  IElementType VALUE_EXPR = new XQueryElementType("VALUE_EXPR");
  IElementType VAR_DECL = new XQueryElementType("VAR_DECL");
  IElementType VAR_DEFAULT_VALUE = new XQueryElementType("VAR_DEFAULT_VALUE");
  IElementType VAR_LOCAL_NAME = new XQueryElementType("VAR_LOCAL_NAME");
  IElementType VAR_NAME = new XQueryElementType("VAR_NAME");
  IElementType VAR_REF = new XQueryElementType("VAR_REF");
  IElementType VAR_VALUE = new XQueryElementType("VAR_VALUE");
  IElementType VERSION = new XQueryElementType("VERSION");
  IElementType VERSION_DECL = new XQueryElementType("VERSION_DECL");
  IElementType WHERE_CLAUSE = new XQueryElementType("WHERE_CLAUSE");
  IElementType WILDCARD = new XQueryElementType("WILDCARD");
  IElementType WINDOW_CLAUSE = new XQueryElementType("WINDOW_CLAUSE");
  IElementType XML_EMPTY_TAG = new XQueryElementType("XML_EMPTY_TAG");
  IElementType XML_FULL_TAG = new XQueryElementType("XML_FULL_TAG");
  IElementType XML_TAG_LOCAL_NAME = new XQueryElementType("XML_TAG_LOCAL_NAME");
  IElementType XML_TAG_NAME = new XQueryElementType("XML_TAG_NAME");
  IElementType XML_TAG_NAMESPACE = new XQueryElementType("XML_TAG_NAMESPACE");

  IElementType AMPERSAND = new XQueryTokenType("&");
  IElementType ATTRCOLON = new XQueryTokenType("AttrColon");
  IElementType ATTREQUAL = new XQueryTokenType("AttrEqual");
  IElementType ATTRNCNAME = new XQueryTokenType("AttrNCName");
  IElementType AT_SIGN = new XQueryTokenType("@");
  IElementType BACKTICK = new XQueryTokenType("`");
  IElementType BRACEDURILITERAL = new XQueryTokenType("BracedURILiteral");
  IElementType CDATASECTIONCONTENTCHAR = new XQueryTokenType("CDataSectionContentChar");
  IElementType CDATA_BEGIN = new XQueryTokenType("<![CDATA[");
  IElementType CDATA_END = new XQueryTokenType("]]>");
  IElementType CHAR = new XQueryTokenType("Char");
  IElementType CHARREF = new XQueryTokenType("CharRef");
  IElementType CLOSE_TAG = new XQueryTokenType("/>");
  IElementType CLOSINGAPOS = new XQueryTokenType("ClosingApos");
  IElementType CLOSINGQUOT = new XQueryTokenType("ClosingQuot");
  IElementType COLON = new XQueryTokenType(":");
  IElementType COLON_COLON = new XQueryTokenType("::");
  IElementType COMMA = new XQueryTokenType(",");
  IElementType DBL_L_C_BRACE = new XQueryTokenType("{{");
  IElementType DBL_R_C_BRACE = new XQueryTokenType("}}");
  IElementType DECIMALLITERAL = new XQueryTokenType("DecimalLiteral");
  IElementType DIRCOMMENTCHAR = new XQueryTokenType("DirCommentChar");
  IElementType DIRPICONTENTCHAR = new XQueryTokenType("DirPIContentChar");
  IElementType DIR_COMMENT_BEGIN = new XQueryTokenType("<!--");
  IElementType DIR_COMMENT_END = new XQueryTokenType("-->");
  IElementType DOLLAR_SIGN = new XQueryTokenType("$");
  IElementType DOT = new XQueryTokenType(".");
  IElementType DOT_DOT = new XQueryTokenType("..");
  IElementType DOUBLELITERAL = new XQueryTokenType("DoubleLiteral");
  IElementType ELEMENTCONTENTCHAR = new XQueryTokenType("ElementContentChar");
  IElementType END_TAG = new XQueryTokenType("</");
  IElementType EQ = new XQueryTokenType("eq");
  IElementType EQUAL = new XQueryTokenType("=");
  IElementType EXCLAMATION_MARK = new XQueryTokenType("!");
  IElementType EXPRCOMMENTCONTENT = new XQueryTokenType("ExprCommentContent");
  IElementType EXPR_COMMENT_END = new XQueryTokenType(":)");
  IElementType EXPR_COMMENT_START = new XQueryTokenType("(:");
  IElementType GE = new XQueryTokenType("ge");
  IElementType GE_CHARS = new XQueryTokenType(">=");
  IElementType GT = new XQueryTokenType("gt");
  IElementType GT_CHAR = new XQueryTokenType(">");
  IElementType HASH = new XQueryTokenType("#");
  IElementType INTEGERLITERAL = new XQueryTokenType("IntegerLiteral");
  IElementType K_AFTER = new XQueryTokenType("after");
  IElementType K_ALLOWING = new XQueryTokenType("allowing");
  IElementType K_ANCESTOR = new XQueryTokenType("ancestor");
  IElementType K_ANCESTOR_OR_SELF = new XQueryTokenType("ancestor-or-self");
  IElementType K_AND = new XQueryTokenType("and");
  IElementType K_ARRAY = new XQueryTokenType("array");
  IElementType K_ARRAY_NODE = new XQueryTokenType("array-node");
  IElementType K_AS = new XQueryTokenType("as");
  IElementType K_ASCENDING = new XQueryTokenType("ascending");
  IElementType K_AT = new XQueryTokenType("at");
  IElementType K_ATTRIBUTE = new XQueryTokenType("attribute");
  IElementType K_BASE_URI = new XQueryTokenType("base-uri");
  IElementType K_BEFORE = new XQueryTokenType("before");
  IElementType K_BINARY = new XQueryTokenType("binary");
  IElementType K_BOOLEAN_NODE = new XQueryTokenType("boolean-node");
  IElementType K_BOUNDARY_SPACE = new XQueryTokenType("boundary-space");
  IElementType K_BY = new XQueryTokenType("by");
  IElementType K_CASE = new XQueryTokenType("case");
  IElementType K_CAST = new XQueryTokenType("cast");
  IElementType K_CASTABLE = new XQueryTokenType("castable");
  IElementType K_CATCH = new XQueryTokenType("catch");
  IElementType K_CHILD = new XQueryTokenType("child");
  IElementType K_COLLATION = new XQueryTokenType("collation");
  IElementType K_COMMENT = new XQueryTokenType("comment");
  IElementType K_CONSTRUCTION = new XQueryTokenType("construction");
  IElementType K_CONTEXT = new XQueryTokenType("context");
  IElementType K_COPY = new XQueryTokenType("copy");
  IElementType K_COPY_NAMESPACES = new XQueryTokenType("copy-namespaces");
  IElementType K_COUNT = new XQueryTokenType("count");
  IElementType K_DECIMAL_FORMAT = new XQueryTokenType("decimal-format");
  IElementType K_DECIMAL_SEPARATOR = new XQueryTokenType("decimal-separator");
  IElementType K_DECLARE = new XQueryTokenType("declare");
  IElementType K_DEFAULT = new XQueryTokenType("default");
  IElementType K_DELETE = new XQueryTokenType("delete");
  IElementType K_DESCENDANT = new XQueryTokenType("descendant");
  IElementType K_DESCENDANT_OR_SELF = new XQueryTokenType("descendant-or-self");
  IElementType K_DESCENDING = new XQueryTokenType("descending");
  IElementType K_DIGIT = new XQueryTokenType("digit");
  IElementType K_DIV = new XQueryTokenType("div");
  IElementType K_DOCUMENT = new XQueryTokenType("document");
  IElementType K_DOCUMENT_NODE = new XQueryTokenType("document-node");
  IElementType K_ELEMENT = new XQueryTokenType("element");
  IElementType K_ELSE = new XQueryTokenType("else");
  IElementType K_EMPTY = new XQueryTokenType("empty");
  IElementType K_EMPTY_SEQUENCE = new XQueryTokenType("empty-sequence");
  IElementType K_ENCODING = new XQueryTokenType("encoding");
  IElementType K_END = new XQueryTokenType("end");
  IElementType K_EVERY = new XQueryTokenType("every");
  IElementType K_EXCEPT = new XQueryTokenType("except");
  IElementType K_EXPONENT_SEPARATOR = new XQueryTokenType("exponent-separator");
  IElementType K_EXTERNAL = new XQueryTokenType("external");
  IElementType K_FIRST = new XQueryTokenType("first");
  IElementType K_FOLLOWING = new XQueryTokenType("following");
  IElementType K_FOLLOWING_SIBLING = new XQueryTokenType("following-sibling");
  IElementType K_FOR = new XQueryTokenType("for");
  IElementType K_FUNCTION = new XQueryTokenType("function");
  IElementType K_GREATEST = new XQueryTokenType("greatest");
  IElementType K_GROUP = new XQueryTokenType("group");
  IElementType K_GROUPING_SEPARATOR = new XQueryTokenType("grouping-separator");
  IElementType K_IDIV = new XQueryTokenType("idiv");
  IElementType K_IF = new XQueryTokenType("if");
  IElementType K_IMPORT = new XQueryTokenType("import");
  IElementType K_IN = new XQueryTokenType("in");
  IElementType K_INFINITY = new XQueryTokenType("infinity");
  IElementType K_INHERIT = new XQueryTokenType("inherit");
  IElementType K_INSERT = new XQueryTokenType("insert");
  IElementType K_INSTANCE = new XQueryTokenType("instance");
  IElementType K_INTERSECT = new XQueryTokenType("intersect");
  IElementType K_INTO = new XQueryTokenType("into");
  IElementType K_IS = new XQueryTokenType("is");
  IElementType K_ITEM = new XQueryTokenType("item");
  IElementType K_LAST = new XQueryTokenType("last");
  IElementType K_LAX = new XQueryTokenType("lax");
  IElementType K_LEAST = new XQueryTokenType("least");
  IElementType K_LET = new XQueryTokenType("let");
  IElementType K_MAP = new XQueryTokenType("map");
  IElementType K_MINUS_SIGN = new XQueryTokenType("minus-sign");
  IElementType K_MOD = new XQueryTokenType("mod");
  IElementType K_MODIFY = new XQueryTokenType("modify");
  IElementType K_MODULE = new XQueryTokenType("module");
  IElementType K_NAMESPACE = new XQueryTokenType("namespace");
  IElementType K_NAMESPACE_NODE = new XQueryTokenType("namespace-node");
  IElementType K_NAN = new XQueryTokenType("NaN");
  IElementType K_NEXT = new XQueryTokenType("next");
  IElementType K_NODE = new XQueryTokenType("node");
  IElementType K_NODES = new XQueryTokenType("nodes");
  IElementType K_NO_INHERIT = new XQueryTokenType("no-inherit");
  IElementType K_NO_PRESERVE = new XQueryTokenType("no-preserve");
  IElementType K_NULL_NODE = new XQueryTokenType("null-node");
  IElementType K_NUMBER_NODE = new XQueryTokenType("number-node");
  IElementType K_OBJECT_NODE = new XQueryTokenType("object-node");
  IElementType K_OF = new XQueryTokenType("of");
  IElementType K_ONLY = new XQueryTokenType("only");
  IElementType K_OPTION = new XQueryTokenType("option");
  IElementType K_OR = new XQueryTokenType("or");
  IElementType K_ORDER = new XQueryTokenType("order");
  IElementType K_ORDERED = new XQueryTokenType("ordered");
  IElementType K_ORDERING = new XQueryTokenType("ordering");
  IElementType K_PARENT = new XQueryTokenType("parent");
  IElementType K_PATTERN_SEPARATOR = new XQueryTokenType("pattern-separator");
  IElementType K_PERCENT = new XQueryTokenType("percent");
  IElementType K_PER_MILLE = new XQueryTokenType("per-mille");
  IElementType K_PI = new XQueryTokenType("processing-instruction");
  IElementType K_PRECEDING = new XQueryTokenType("preceding");
  IElementType K_PRECEDING_SIBLING = new XQueryTokenType("preceding-sibling");
  IElementType K_PRESERVE = new XQueryTokenType("preserve");
  IElementType K_PREVIOUS = new XQueryTokenType("previous");
  IElementType K_PRIVATE = new XQueryTokenType("private");
  IElementType K_RENAME = new XQueryTokenType("rename");
  IElementType K_REPLACE = new XQueryTokenType("replace");
  IElementType K_RETURN = new XQueryTokenType("return");
  IElementType K_REVALIDATION = new XQueryTokenType("revalidation");
  IElementType K_SATISFIES = new XQueryTokenType("satisfies");
  IElementType K_SCHEMA = new XQueryTokenType("schema");
  IElementType K_SCHEMA_ATTRIBUTE = new XQueryTokenType("schema-attribute");
  IElementType K_SCHEMA_ELEMENT = new XQueryTokenType("schema-element");
  IElementType K_SELF = new XQueryTokenType("self");
  IElementType K_SKIP = new XQueryTokenType("skip");
  IElementType K_SLIDING = new XQueryTokenType("sliding");
  IElementType K_SOME = new XQueryTokenType("some");
  IElementType K_STABLE = new XQueryTokenType("stable");
  IElementType K_START = new XQueryTokenType("start");
  IElementType K_STRICT = new XQueryTokenType("strict");
  IElementType K_STRIP = new XQueryTokenType("strip");
  IElementType K_SWITCH = new XQueryTokenType("switch");
  IElementType K_TEXT = new XQueryTokenType("text");
  IElementType K_THEN = new XQueryTokenType("then");
  IElementType K_TO = new XQueryTokenType("to");
  IElementType K_TREAT = new XQueryTokenType("treat");
  IElementType K_TRY = new XQueryTokenType("try");
  IElementType K_TUMBLING = new XQueryTokenType("tumbling");
  IElementType K_TYPE = new XQueryTokenType("type");
  IElementType K_TYPESWITCH = new XQueryTokenType("typeswitch");
  IElementType K_UNION = new XQueryTokenType("union");
  IElementType K_UNORDERED = new XQueryTokenType("unordered");
  IElementType K_UPDATING = new XQueryTokenType("updating");
  IElementType K_VALIDATE = new XQueryTokenType("validate");
  IElementType K_VALUE = new XQueryTokenType("value");
  IElementType K_VARIABLE = new XQueryTokenType("variable");
  IElementType K_VERSION = new XQueryTokenType("version");
  IElementType K_WHEN = new XQueryTokenType("when");
  IElementType K_WHERE = new XQueryTokenType("where");
  IElementType K_WINDOW = new XQueryTokenType("window");
  IElementType K_WITH = new XQueryTokenType("with");
  IElementType K_XQUERY = new XQueryTokenType("xquery");
  IElementType K_ZERO_DIGIT = new XQueryTokenType("zero-digit");
  IElementType LE = new XQueryTokenType("le");
  IElementType LE_CHARS = new XQueryTokenType("<=");
  IElementType LT = new XQueryTokenType("lt");
  IElementType LT_CHAR = new XQueryTokenType("<");
  IElementType L_BRACKET = new XQueryTokenType("[");
  IElementType L_C_BRACE = new XQueryTokenType("{");
  IElementType L_PAR = new XQueryTokenType("(");
  IElementType NCNAME = new XQueryTokenType("NCName");
  IElementType NE = new XQueryTokenType("ne");
  IElementType NODECOMP_GT = new XQueryTokenType(">>");
  IElementType NODECOMP_LT = new XQueryTokenType("<<");
  IElementType NOT_EQUAL = new XQueryTokenType("!=");
  IElementType OPENINGAPOS = new XQueryTokenType("OpeningApos");
  IElementType OPENINGQUOT = new XQueryTokenType("OpeningQuot");
  IElementType OP_ARROW = new XQueryTokenType("=>");
  IElementType OP_ASSIGN = new XQueryTokenType(":=");
  IElementType OP_MINUS = new XQueryTokenType("-");
  IElementType OP_PLUS = new XQueryTokenType("+");
  IElementType PERCENT = new XQueryTokenType("%");
  IElementType PIPE = new XQueryTokenType("|");
  IElementType PIPE_PIPE = new XQueryTokenType("||");
  IElementType PITARGET = new XQueryTokenType("PITarget");
  IElementType PI_BEGIN = new XQueryTokenType("<?");
  IElementType PI_END = new XQueryTokenType("?>");
  IElementType PRAGMACONTENTCHAR = new XQueryTokenType("PragmaContentChar");
  IElementType PRAGMA_BEGIN = new XQueryTokenType("(#");
  IElementType PRAGMA_END = new XQueryTokenType("#)");
  IElementType PREDEFINEDENTITYREF = new XQueryTokenType("PredefinedEntityRef");
  IElementType QUESTIONMARK = new XQueryTokenType("?");
  IElementType R_BRACKET = new XQueryTokenType("]");
  IElementType R_C_BRACE = new XQueryTokenType("}");
  IElementType R_PAR = new XQueryTokenType(")");
  IElementType S = new XQueryTokenType("S");
  IElementType SEMICOLON = new XQueryTokenType(";");
  IElementType SLASH = new XQueryTokenType("/");
  IElementType SLASH_SLASH = new XQueryTokenType("//");
  IElementType STAR_SIGN = new XQueryTokenType("*");
  IElementType STRINGCHAR = new XQueryTokenType("StringChar");
  IElementType STRING_CONSTR_END = new XQueryTokenType("]``");
  IElementType STRING_CONSTR_EXPR_END = new XQueryTokenType("}`");
  IElementType STRING_CONSTR_EXPR_START = new XQueryTokenType("`{");
  IElementType STRING_CONSTR_START = new XQueryTokenType("``[");
  IElementType TAGNAME = new XQueryTokenType("TagName");
  IElementType URIQUALIFIEDNAME = new XQueryTokenType("URIQualifiedName");
  IElementType XMLCOLON = new XQueryTokenType("XmlColon");
  IElementType XMLEMPTYELEMENTEND = new XQueryTokenType("XmlEmptyElementEnd");
  IElementType XMLENDTAGSTART = new XQueryTokenType("XmlEndTagStart");
  IElementType XMLSTARTTAGSTART = new XQueryTokenType("XmlStartTagStart");
  IElementType XMLTAGEND = new XQueryTokenType("XmlTagEnd");
  IElementType XMLTAGNCNAME = new XQueryTokenType("XmlTagNCName");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ABBREV_FORWARD_STEP) {
        return new XQueryAbbrevForwardStepImpl(node);
      }
      else if (type == ABBREV_REVERSE_STEP) {
        return new XQueryAbbrevReverseStepImpl(node);
      }
      else if (type == ADDITIVE_EXPR) {
        return new XQueryAdditiveExprImpl(node);
      }
      else if (type == ADDITIVE_OPERATOR) {
        return new XQueryAdditiveOperatorImpl(node);
      }
      else if (type == ALLOWING_EMPTY) {
        return new XQueryAllowingEmptyImpl(node);
      }
      else if (type == AND_EXPR) {
        return new XQueryAndExprImpl(node);
      }
      else if (type == AND_OPERATOR) {
        return new XQueryAndOperatorImpl(node);
      }
      else if (type == ANNOTATION) {
        return new XQueryAnnotationImpl(node);
      }
      else if (type == ANNOTATION_NAME) {
        return new XQueryAnnotationNameImpl(node);
      }
      else if (type == ANY_FUNCTION_TEST) {
        return new XQueryAnyFunctionTestImpl(node);
      }
      else if (type == ANY_KIND_TEST) {
        return new XQueryAnyKindTestImpl(node);
      }
      else if (type == APOS_ATTR_CONTENT_CHAR) {
        return new XQueryAposAttrContentCharImpl(node);
      }
      else if (type == APOS_ATTR_VALUE_CONTENT) {
        return new XQueryAposAttrValueContentImpl(node);
      }
      else if (type == ARGUMENT) {
        return new XQueryArgumentImpl(node);
      }
      else if (type == ARGUMENT_LIST) {
        return new XQueryArgumentListImpl(node);
      }
      else if (type == ARGUMENT_PLACEHOLDER) {
        return new XQueryArgumentPlaceholderImpl(node);
      }
      else if (type == ARRAY_CONSTRUCTOR) {
        return new XQueryArrayConstructorImpl(node);
      }
      else if (type == ARRAY_TEST) {
        return new XQueryArrayTestImpl(node);
      }
      else if (type == ARROW_EXPR) {
        return new XQueryArrowExprImpl(node);
      }
      else if (type == ARROW_FUNCTION_REFERENCE) {
        return new XQueryArrowFunctionReferenceImpl(node);
      }
      else if (type == ARROW_FUNCTION_SPECIFIER) {
        return new XQueryArrowFunctionSpecifierImpl(node);
      }
      else if (type == ATOMIC_OR_UNION_TYPE) {
        return new XQueryAtomicOrUnionTypeImpl(node);
      }
      else if (type == ATTRIBUTE_DECLARATION) {
        return new XQueryAttributeDeclarationImpl(node);
      }
      else if (type == ATTRIBUTE_NAME) {
        return new XQueryAttributeNameImpl(node);
      }
      else if (type == ATTRIBUTE_TEST) {
        return new XQueryAttributeTestImpl(node);
      }
      else if (type == ATTRIB_NAME_OR_WILDCARD) {
        return new XQueryAttribNameOrWildcardImpl(node);
      }
      else if (type == ATTR_LOCAL_NAME) {
        return new XQueryAttrLocalNameImpl(node);
      }
      else if (type == ATTR_NAMESPACE) {
        return new XQueryAttrNamespaceImpl(node);
      }
      else if (type == AXIS_STEP) {
        return new XQueryAxisStepImpl(node);
      }
      else if (type == BASE_URI_DECL) {
        return new XQueryBaseURIDeclImpl(node);
      }
      else if (type == BOUNDARY_SPACE_DECL) {
        return new XQueryBoundarySpaceDeclImpl(node);
      }
      else if (type == CASE_CLAUSE) {
        return new XQueryCaseClauseImpl(node);
      }
      else if (type == CASTABLE_EXPR) {
        return new XQueryCastableExprImpl(node);
      }
      else if (type == CASTABLE_OPERATOR) {
        return new XQueryCastableOperatorImpl(node);
      }
      else if (type == CAST_EXPR) {
        return new XQueryCastExprImpl(node);
      }
      else if (type == CAST_OPERATOR) {
        return new XQueryCastOperatorImpl(node);
      }
      else if (type == CATCH_CLAUSE) {
        return new XQueryCatchClauseImpl(node);
      }
      else if (type == CATCH_CLAUSE_EXPRESSION) {
        return new XQueryCatchClauseExpressionImpl(node);
      }
      else if (type == CATCH_ERROR_LIST) {
        return new XQueryCatchErrorListImpl(node);
      }
      else if (type == COMMENT_TEST) {
        return new XQueryCommentTestImpl(node);
      }
      else if (type == COMMON_CONTENT) {
        return new XQueryCommonContentImpl(node);
      }
      else if (type == COMPARISON_EXPR) {
        return new XQueryComparisonExprImpl(node);
      }
      else if (type == COMPATIBILITY_ANNOTATION) {
        return new XQueryCompatibilityAnnotationImpl(node);
      }
      else if (type == COMPUTED_CONSTRUCTOR) {
        return new XQueryComputedConstructorImpl(node);
      }
      else if (type == COMP_ATTR_CONSTRUCTOR) {
        return new XQueryCompAttrConstructorImpl(node);
      }
      else if (type == COMP_COMMENT_CONSTRUCTOR) {
        return new XQueryCompCommentConstructorImpl(node);
      }
      else if (type == COMP_DOC_CONSTRUCTOR) {
        return new XQueryCompDocConstructorImpl(node);
      }
      else if (type == COMP_ELEM_CONSTRUCTOR) {
        return new XQueryCompElemConstructorImpl(node);
      }
      else if (type == COMP_NAMESPACE_CONSTRUCTOR) {
        return new XQueryCompNamespaceConstructorImpl(node);
      }
      else if (type == COMP_PI_CONSTRUCTOR) {
        return new XQueryCompPIConstructorImpl(node);
      }
      else if (type == COMP_TEXT_CONSTRUCTOR) {
        return new XQueryCompTextConstructorImpl(node);
      }
      else if (type == CONCAT_OPERATOR) {
        return new XQueryConcatOperatorImpl(node);
      }
      else if (type == CONSTRUCTION_DECL) {
        return new XQueryConstructionDeclImpl(node);
      }
      else if (type == CONTEXT_ITEM_DECL) {
        return new XQueryContextItemDeclImpl(node);
      }
      else if (type == CONTEXT_ITEM_EXPR) {
        return new XQueryContextItemExprImpl(node);
      }
      else if (type == COPY_NAMESPACES_DECL) {
        return new XQueryCopyNamespacesDeclImpl(node);
      }
      else if (type == COUNT_CLAUSE) {
        return new XQueryCountClauseImpl(node);
      }
      else if (type == CURLY_ARRAY_CONSTRUCTOR) {
        return new XQueryCurlyArrayConstructorImpl(node);
      }
      else if (type == CURRENT_ITEM) {
        return new XQueryCurrentItemImpl(node);
      }
      else if (type == C_DATA_SECTION) {
        return new XQueryCDataSectionImpl(node);
      }
      else if (type == C_DATA_SECTION_CONTENTS) {
        return new XQueryCDataSectionContentsImpl(node);
      }
      else if (type == DECIMAL_FORMAT_DECL) {
        return new XQueryDecimalFormatDeclImpl(node);
      }
      else if (type == DEFAULT_COLLATION_DECL) {
        return new XQueryDefaultCollationDeclImpl(node);
      }
      else if (type == DEFAULT_ELEMENT_NAMESPACE_DECL) {
        return new XQueryDefaultElementNamespaceDeclImpl(node);
      }
      else if (type == DEFAULT_FUNCTION_NAMESPACE_DECL) {
        return new XQueryDefaultFunctionNamespaceDeclImpl(node);
      }
      else if (type == DELETE_EXPR) {
        return new XQueryDeleteExprImpl(node);
      }
      else if (type == DIRECT_CONSTRUCTOR) {
        return new XQueryDirectConstructorImpl(node);
      }
      else if (type == DIR_ATTRIBUTE) {
        return new XQueryDirAttributeImpl(node);
      }
      else if (type == DIR_ATTRIBUTE_LIST) {
        return new XQueryDirAttributeListImpl(node);
      }
      else if (type == DIR_ATTRIBUTE_NAME) {
        return new XQueryDirAttributeNameImpl(node);
      }
      else if (type == DIR_ATTRIBUTE_VALUE) {
        return new XQueryDirAttributeValueImpl(node);
      }
      else if (type == DIR_COMMENT_CONSTRUCTOR) {
        return new XQueryDirCommentConstructorImpl(node);
      }
      else if (type == DIR_COMMENT_CONTENTS) {
        return new XQueryDirCommentContentsImpl(node);
      }
      else if (type == DIR_ELEM_CONTENT) {
        return new XQueryDirElemContentImpl(node);
      }
      else if (type == DIR_PI_CONSTRUCTOR) {
        return new XQueryDirPIConstructorImpl(node);
      }
      else if (type == DIR_PI_CONTENTS) {
        return new XQueryDirPIContentsImpl(node);
      }
      else if (type == DOCUMENT_TEST) {
        return new XQueryDocumentTestImpl(node);
      }
      else if (type == ELEMENT_DECLARATION) {
        return new XQueryElementDeclarationImpl(node);
      }
      else if (type == ELEMENT_NAME) {
        return new XQueryElementNameImpl(node);
      }
      else if (type == ELEMENT_NAME_OR_WILDCARD) {
        return new XQueryElementNameOrWildcardImpl(node);
      }
      else if (type == ELEMENT_TEST) {
        return new XQueryElementTestImpl(node);
      }
      else if (type == EMPTY_ORDER_DECL) {
        return new XQueryEmptyOrderDeclImpl(node);
      }
      else if (type == ENCLOSED_CONTENT_EXPRESSION) {
        return new XQueryEnclosedContentExpressionImpl(node);
      }
      else if (type == ENCLOSED_EXPRESSION) {
        return new XQueryEnclosedExpressionImpl(node);
      }
      else if (type == ENCLOSED_PREFIX_EXPRESSION) {
        return new XQueryEnclosedPrefixExpressionImpl(node);
      }
      else if (type == ENCLOSED_URI_EXPRESSION) {
        return new XQueryEnclosedURIExpressionImpl(node);
      }
      else if (type == EQUALITY_COMP) {
        return new XQueryEqualityCompImpl(node);
      }
      else if (type == ESCAPE_APOS) {
        return new XQueryEscapeAposImpl(node);
      }
      else if (type == ESCAPE_QUOT) {
        return new XQueryEscapeQuotImpl(node);
      }
      else if (type == EXPR) {
        return new XQueryExprImpl(node);
      }
      else if (type == EXTENSION_EXPR) {
        return new XQueryExtensionExprImpl(node);
      }
      else if (type == EXTERNAL_VAR_PART) {
        return new XQueryExternalVarPartImpl(node);
      }
      else if (type == FLWOR_EXPR) {
        return new XQueryFLWORExprImpl(node);
      }
      else if (type == FORWARD_STEP) {
        return new XQueryForwardStepImpl(node);
      }
      else if (type == FOR_BINDING) {
        return new XQueryForBindingImpl(node);
      }
      else if (type == FOR_CLAUSE) {
        return new XQueryForClauseImpl(node);
      }
      else if (type == FUNCTION_ARITY) {
        return new XQueryFunctionArityImpl(node);
      }
      else if (type == FUNCTION_BODY) {
        return new XQueryFunctionBodyImpl(node);
      }
      else if (type == FUNCTION_CALL) {
        return new XQueryFunctionCallImpl(node);
      }
      else if (type == FUNCTION_DECL) {
        return new XQueryFunctionDeclImpl(node);
      }
      else if (type == FUNCTION_ITEM_EXPR) {
        return new XQueryFunctionItemExprImpl(node);
      }
      else if (type == FUNCTION_LOCAL_NAME) {
        return new XQueryFunctionLocalNameImpl(node);
      }
      else if (type == FUNCTION_NAME) {
        return new XQueryFunctionNameImpl(node);
      }
      else if (type == FUNCTION_TEST) {
        return new XQueryFunctionTestImpl(node);
      }
      else if (type == GENERAL_ITEM_TYPE) {
        return new XQueryGeneralItemTypeImpl(node);
      }
      else if (type == GROUPING_SPEC) {
        return new XQueryGroupingSpecImpl(node);
      }
      else if (type == GROUPING_SPEC_LIST) {
        return new XQueryGroupingSpecListImpl(node);
      }
      else if (type == GROUPING_VARIABLE) {
        return new XQueryGroupingVariableImpl(node);
      }
      else if (type == GROUP_BY_CLAUSE) {
        return new XQueryGroupByClauseImpl(node);
      }
      else if (type == IF_EXPR) {
        return new XQueryIfExprImpl(node);
      }
      else if (type == INHERIT_MODE) {
        return new XQueryInheritModeImpl(node);
      }
      else if (type == INLINE_FUNCTION_EXPR) {
        return new XQueryInlineFunctionExprImpl(node);
      }
      else if (type == INSERT_EXPR) {
        return new XQueryInsertExprImpl(node);
      }
      else if (type == INSERT_EXPR_TARGET_CHOICE) {
        return new XQueryInsertExprTargetChoiceImpl(node);
      }
      else if (type == INSTANCEOF_EXPR) {
        return new XQueryInstanceofExprImpl(node);
      }
      else if (type == INSTANCE_OF_OPERATOR) {
        return new XQueryInstanceOfOperatorImpl(node);
      }
      else if (type == INTERSECT_EXCEPT_EXPR) {
        return new XQueryIntersectExceptExprImpl(node);
      }
      else if (type == INTERSECT_EXCEPT_OPERATOR) {
        return new XQueryIntersectExceptOperatorImpl(node);
      }
      else if (type == ITEM_TYPE) {
        return new XQueryItemTypeImpl(node);
      }
      else if (type == KEY_SPECIFIER) {
        return new XQueryKeySpecifierImpl(node);
      }
      else if (type == KIND_TEST) {
        return new XQueryKindTestImpl(node);
      }
      else if (type == LET_BINDING) {
        return new XQueryLetBindingImpl(node);
      }
      else if (type == LET_CLAUSE) {
        return new XQueryLetClauseImpl(node);
      }
      else if (type == LITERAL) {
        return new XQueryLiteralImpl(node);
      }
      else if (type == LOCAL_PART) {
        return new XQueryLocalPartImpl(node);
      }
      else if (type == LOOKUP) {
        return new XQueryLookupImpl(node);
      }
      else if (type == MAP_CONSTRUCTOR) {
        return new XQueryMapConstructorImpl(node);
      }
      else if (type == MAP_CONSTRUCTOR_ENTRY) {
        return new XQueryMapConstructorEntryImpl(node);
      }
      else if (type == MAP_TEST) {
        return new XQueryMapTestImpl(node);
      }
      else if (type == MARKLOGIC_ANNOTATION) {
        return new XQueryMarklogicAnnotationImpl(node);
      }
      else if (type == MARKLOGIC_ANY_KIND_TEST) {
        return new XQueryMarklogicAnyKindTestImpl(node);
      }
      else if (type == MARKLOGIC_ARRAY_NODE_TEST) {
        return new XQueryMarklogicArrayNodeTestImpl(node);
      }
      else if (type == MARKLOGIC_BINARY_TEST) {
        return new XQueryMarklogicBinaryTestImpl(node);
      }
      else if (type == MARKLOGIC_BOOLEAN_NODE_TEST) {
        return new XQueryMarklogicBooleanNodeTestImpl(node);
      }
      else if (type == MARKLOGIC_CATCH_ERROR_LIST) {
        return new XQueryMarklogicCatchErrorListImpl(node);
      }
      else if (type == MARKLOGIC_COMP_ARRAY_NODE_CONSTRUCTOR) {
        return new XQueryMarklogicCompArrayNodeConstructorImpl(node);
      }
      else if (type == MARKLOGIC_COMP_BINARY_CONSTRUCTOR) {
        return new XQueryMarklogicCompBinaryConstructorImpl(node);
      }
      else if (type == MARKLOGIC_COMP_BOOLEAN_NODE_CONSTRUCTOR) {
        return new XQueryMarklogicCompBooleanNodeConstructorImpl(node);
      }
      else if (type == MARKLOGIC_COMP_NULL_NODE_CONSTRUCTOR) {
        return new XQueryMarklogicCompNullNodeConstructorImpl(node);
      }
      else if (type == MARKLOGIC_COMP_NUMBER_NODE_CONSTRUCTOR) {
        return new XQueryMarklogicCompNumberNodeConstructorImpl(node);
      }
      else if (type == MARKLOGIC_COMP_OBJECT_NODE_CONSTRUCTOR) {
        return new XQueryMarklogicCompObjectNodeConstructorImpl(node);
      }
      else if (type == MARKLOGIC_NAMESPACE_AXIS) {
        return new XQueryMarklogicNamespaceAxisImpl(node);
      }
      else if (type == MARKLOGIC_NULL_NODE_TEST) {
        return new XQueryMarklogicNullNodeTestImpl(node);
      }
      else if (type == MARKLOGIC_NUMBER_NODE_TEST) {
        return new XQueryMarklogicNumberNodeTestImpl(node);
      }
      else if (type == MARKLOGIC_OBJECT_NODE_TEST) {
        return new XQueryMarklogicObjectNodeTestImpl(node);
      }
      else if (type == MARKLOGIC_TEXT_TEST) {
        return new XQueryMarklogicTextTestImpl(node);
      }
      else if (type == MARKLOGIC_VALIDATION) {
        return new XQueryMarklogicValidationImpl(node);
      }
      else if (type == MISPLACED_COMMENT) {
        return new XQueryMisplacedCommentImpl(node);
      }
      else if (type == MODULE_DECL) {
        return new XQueryModuleDeclImpl(node);
      }
      else if (type == MODULE_IMPORT) {
        return new XQueryModuleImportImpl(node);
      }
      else if (type == MODULE_IMPORT_NAMESPACE) {
        return new XQueryModuleImportNamespaceImpl(node);
      }
      else if (type == MODULE_IMPORT_PATH) {
        return new XQueryModuleImportPathImpl(node);
      }
      else if (type == MULTIPLICATIVE_EXPR) {
        return new XQueryMultiplicativeExprImpl(node);
      }
      else if (type == MULTIPLICATIVE_OPERATOR) {
        return new XQueryMultiplicativeOperatorImpl(node);
      }
      else if (type == MULTI_VARIABLE_BINDING) {
        return new XQueryMultiVariableBindingImpl(node);
      }
      else if (type == NAMED_FUNCTION_REF) {
        return new XQueryNamedFunctionRefImpl(node);
      }
      else if (type == NAMESPACE_DECL) {
        return new XQueryNamespaceDeclImpl(node);
      }
      else if (type == NAMESPACE_NODE_TEST) {
        return new XQueryNamespaceNodeTestImpl(node);
      }
      else if (type == NAMESPACE_PREFIX) {
        return new XQueryNamespacePrefixImpl(node);
      }
      else if (type == NAME_TEST) {
        return new XQueryNameTestImpl(node);
      }
      else if (type == NEXT_ITEM) {
        return new XQueryNextItemImpl(node);
      }
      else if (type == NODE_COMP) {
        return new XQueryNodeCompImpl(node);
      }
      else if (type == NODE_CONSTRUCTOR) {
        return new XQueryNodeConstructorImpl(node);
      }
      else if (type == NODE_TEST) {
        return new XQueryNodeTestImpl(node);
      }
      else if (type == NUMERIC_LITERAL) {
        return new XQueryNumericLiteralImpl(node);
      }
      else if (type == OBJECT_PROPERTY) {
        return new XQueryObjectPropertyImpl(node);
      }
      else if (type == OBJECT_PROPERTY_LIST) {
        return new XQueryObjectPropertyListImpl(node);
      }
      else if (type == OCCURRENCE_INDICATOR) {
        return new XQueryOccurrenceIndicatorImpl(node);
      }
      else if (type == OPTION_DECL) {
        return new XQueryOptionDeclImpl(node);
      }
      else if (type == ORDERED_EXPR) {
        return new XQueryOrderedExprImpl(node);
      }
      else if (type == ORDERING_MODE_DECL) {
        return new XQueryOrderingModeDeclImpl(node);
      }
      else if (type == ORDER_BY_CLAUSE) {
        return new XQueryOrderByClauseImpl(node);
      }
      else if (type == ORDER_SPEC) {
        return new XQueryOrderSpecImpl(node);
      }
      else if (type == ORDER_SPEC_LIST) {
        return new XQueryOrderSpecListImpl(node);
      }
      else if (type == OR_EXPR) {
        return new XQueryOrExprImpl(node);
      }
      else if (type == OR_OPERATOR) {
        return new XQueryOrOperatorImpl(node);
      }
      else if (type == PARAM) {
        return new XQueryParamImpl(node);
      }
      else if (type == PARAM_LIST) {
        return new XQueryParamListImpl(node);
      }
      else if (type == PARENTHESIZED_EXPR) {
        return new XQueryParenthesizedExprImpl(node);
      }
      else if (type == PARENTHESIZED_ITEM_TYPE) {
        return new XQueryParenthesizedItemTypeImpl(node);
      }
      else if (type == PATH_EXPR) {
        return new XQueryPathExprImpl(node);
      }
      else if (type == PI_TEST) {
        return new XQueryPITestImpl(node);
      }
      else if (type == POSITIONAL_VAR) {
        return new XQueryPositionalVarImpl(node);
      }
      else if (type == POSTFIX_EXPR) {
        return new XQueryPostfixExprImpl(node);
      }
      else if (type == PRAGMA) {
        return new XQueryPragmaImpl(node);
      }
      else if (type == PRAGMA_CONTENTS) {
        return new XQueryPragmaContentsImpl(node);
      }
      else if (type == PREDICATE) {
        return new XQueryPredicateImpl(node);
      }
      else if (type == PREDICATE_LIST) {
        return new XQueryPredicateListImpl(node);
      }
      else if (type == PREFIX) {
        return new XQueryPrefixImpl(node);
      }
      else if (type == PRESERVE_MODE) {
        return new XQueryPreserveModeImpl(node);
      }
      else if (type == PREVIOUS_ITEM) {
        return new XQueryPreviousItemImpl(node);
      }
      else if (type == PRIMARY_EXPR) {
        return new XQueryPrimaryExprImpl(node);
      }
      else if (type == QUANTIFIED_EXPR) {
        return new XQueryQuantifiedExprImpl(node);
      }
      else if (type == QUERY_BODY) {
        return new XQueryQueryBodyImpl(node);
      }
      else if (type == QUOT_ATTR_CONTENT_CHAR) {
        return new XQueryQuotAttrContentCharImpl(node);
      }
      else if (type == QUOT_ATTR_VALUE_CONTENT) {
        return new XQueryQuotAttrValueContentImpl(node);
      }
      else if (type == RANGE_EXPR) {
        return new XQueryRangeExprImpl(node);
      }
      else if (type == RELATIONAL_COMP) {
        return new XQueryRelationalCompImpl(node);
      }
      else if (type == RELATIVE_PATH_OPERATOR) {
        return new XQueryRelativePathOperatorImpl(node);
      }
      else if (type == RENAME_EXPR) {
        return new XQueryRenameExprImpl(node);
      }
      else if (type == REPLACE_EXPR) {
        return new XQueryReplaceExprImpl(node);
      }
      else if (type == RETURN_CLAUSE) {
        return new XQueryReturnClauseImpl(node);
      }
      else if (type == REVALIDATION_DECL) {
        return new XQueryRevalidationDeclImpl(node);
      }
      else if (type == REVERSE_STEP) {
        return new XQueryReverseStepImpl(node);
      }
      else if (type == SAXON_MAP_ENTRIES_SEPARATOR) {
        return new XQuerySaxonMapEntriesSeparatorImpl(node);
      }
      else if (type == SAXON_MAP_ENTRY_SEPARATOR) {
        return new XQuerySaxonMapEntrySeparatorImpl(node);
      }
      else if (type == SCHEMA_ATTRIBUTE_TEST) {
        return new XQuerySchemaAttributeTestImpl(node);
      }
      else if (type == SCHEMA_ELEMENT_TEST) {
        return new XQuerySchemaElementTestImpl(node);
      }
      else if (type == SCHEMA_IMPORT) {
        return new XQuerySchemaImportImpl(node);
      }
      else if (type == SEPARATOR) {
        return new XQuerySeparatorImpl(node);
      }
      else if (type == SEQUENCE_TYPE) {
        return new XQuerySequenceTypeImpl(node);
      }
      else if (type == SEQUENCE_TYPE_UNION) {
        return new XQuerySequenceTypeUnionImpl(node);
      }
      else if (type == SIMPLE_MAP_EXPR) {
        return new XQuerySimpleMapExprImpl(node);
      }
      else if (type == SIMPLE_MAP_OPERATOR) {
        return new XQuerySimpleMapOperatorImpl(node);
      }
      else if (type == SIMPLE_TYPE_NAME) {
        return new XQuerySimpleTypeNameImpl(node);
      }
      else if (type == SINGLE_TYPE) {
        return new XQuerySingleTypeImpl(node);
      }
      else if (type == SQUARE_ARRAY_CONSTRUCTOR) {
        return new XQuerySquareArrayConstructorImpl(node);
      }
      else if (type == STEP_EXPR) {
        return new XQueryStepExprImpl(node);
      }
      else if (type == STRING_CONCAT_EXPR) {
        return new XQueryStringConcatExprImpl(node);
      }
      else if (type == STRING_CONSTRUCTOR) {
        return new XQueryStringConstructorImpl(node);
      }
      else if (type == STRING_CONSTRUCTOR_CHARS) {
        return new XQueryStringConstructorCharsImpl(node);
      }
      else if (type == STRING_CONSTRUCTOR_CONTENT) {
        return new XQueryStringConstructorContentImpl(node);
      }
      else if (type == STRING_CONSTRUCTOR_INTERPOLATION) {
        return new XQueryStringConstructorInterpolationImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new XQueryStringLiteralImpl(node);
      }
      else if (type == STRING_LITERAL_OR_WILDCARD) {
        return new XQueryStringLiteralOrWildcardImpl(node);
      }
      else if (type == SWITCH_CASE_CLAUSE) {
        return new XQuerySwitchCaseClauseImpl(node);
      }
      else if (type == SWITCH_CASE_OPERAND) {
        return new XQuerySwitchCaseOperandImpl(node);
      }
      else if (type == SWITCH_DEFAULT_RETURN_CLAUSE) {
        return new XQuerySwitchDefaultReturnClauseImpl(node);
      }
      else if (type == SWITCH_EXPR) {
        return new XQuerySwitchExprImpl(node);
      }
      else if (type == SWITCH_RETURN_CLAUSE) {
        return new XQuerySwitchReturnClauseImpl(node);
      }
      else if (type == TEXT_TEST) {
        return new XQueryTextTestImpl(node);
      }
      else if (type == TO_OPERATOR) {
        return new XQueryToOperatorImpl(node);
      }
      else if (type == TRANSFORM_EXPR) {
        return new XQueryTransformExprImpl(node);
      }
      else if (type == TREAT_EXPR) {
        return new XQueryTreatExprImpl(node);
      }
      else if (type == TREAT_OPERATOR) {
        return new XQueryTreatOperatorImpl(node);
      }
      else if (type == TRY_CATCH_EXPR) {
        return new XQueryTryCatchExprImpl(node);
      }
      else if (type == TRY_CLAUSE) {
        return new XQueryTryClauseImpl(node);
      }
      else if (type == TYPED_FUNCTION_TEST) {
        return new XQueryTypedFunctionTestImpl(node);
      }
      else if (type == TYPESWITCH_DEFAULT_RETURN_CLAUSE) {
        return new XQueryTypeswitchDefaultReturnClauseImpl(node);
      }
      else if (type == TYPESWITCH_EXPR) {
        return new XQueryTypeswitchExprImpl(node);
      }
      else if (type == TYPE_DECLARATION) {
        return new XQueryTypeDeclarationImpl(node);
      }
      else if (type == TYPE_NAME) {
        return new XQueryTypeNameImpl(node);
      }
      else if (type == UNARY_EXPR) {
        return new XQueryUnaryExprImpl(node);
      }
      else if (type == UNARY_LOOKUP) {
        return new XQueryUnaryLookupImpl(node);
      }
      else if (type == UNION_EXPR) {
        return new XQueryUnionExprImpl(node);
      }
      else if (type == UNION_OPERATOR) {
        return new XQueryUnionOperatorImpl(node);
      }
      else if (type == UNORDERED_EXPR) {
        return new XQueryUnorderedExprImpl(node);
      }
      else if (type == URI_LITERAL) {
        return new XQueryURILiteralImpl(node);
      }
      else if (type == VALIDATE_EXPR) {
        return new XQueryValidateExprImpl(node);
      }
      else if (type == VALUE_COMP) {
        return new XQueryValueCompImpl(node);
      }
      else if (type == VAR_DECL) {
        return new XQueryVarDeclImpl(node);
      }
      else if (type == VAR_DEFAULT_VALUE) {
        return new XQueryVarDefaultValueImpl(node);
      }
      else if (type == VAR_LOCAL_NAME) {
        return new XQueryVarLocalNameImpl(node);
      }
      else if (type == VAR_NAME) {
        return new XQueryVarNameImpl(node);
      }
      else if (type == VAR_REF) {
        return new XQueryVarRefImpl(node);
      }
      else if (type == VAR_VALUE) {
        return new XQueryVarValueImpl(node);
      }
      else if (type == VERSION) {
        return new XQueryVersionImpl(node);
      }
      else if (type == VERSION_DECL) {
        return new XQueryVersionDeclImpl(node);
      }
      else if (type == WHERE_CLAUSE) {
        return new XQueryWhereClauseImpl(node);
      }
      else if (type == WILDCARD) {
        return new XQueryWildcardImpl(node);
      }
      else if (type == WINDOW_CLAUSE) {
        return new XQueryWindowClauseImpl(node);
      }
      else if (type == XML_EMPTY_TAG) {
        return new XQueryXmlEmptyTagImpl(node);
      }
      else if (type == XML_FULL_TAG) {
        return new XQueryXmlFullTagImpl(node);
      }
      else if (type == XML_TAG_LOCAL_NAME) {
        return new XQueryXmlTagLocalNameImpl(node);
      }
      else if (type == XML_TAG_NAME) {
        return new XQueryXmlTagNameImpl(node);
      }
      else if (type == XML_TAG_NAMESPACE) {
        return new XQueryXmlTagNamespaceImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
