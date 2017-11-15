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
package org.intellij.xquery.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.intellij.xquery.psi.XQueryTypes.*;
import static org.intellij.grammar.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class XQueryParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ABBREV_FORWARD_STEP) {
      r = AbbrevForwardStep(b, 0);
    }
    else if (t == ABBREV_REVERSE_STEP) {
      r = AbbrevReverseStep(b, 0);
    }
    else if (t == ADDITIVE_EXPR) {
      r = AdditiveExpr(b, 0);
    }
    else if (t == ADDITIVE_OPERATOR) {
      r = AdditiveOperator(b, 0);
    }
    else if (t == ALLOWING_EMPTY) {
      r = AllowingEmpty(b, 0);
    }
    else if (t == AND_EXPR) {
      r = AndExpr(b, 0);
    }
    else if (t == AND_OPERATOR) {
      r = AndOperator(b, 0);
    }
    else if (t == ANNOTATION) {
      r = Annotation(b, 0);
    }
    else if (t == ANNOTATION_NAME) {
      r = AnnotationName(b, 0);
    }
    else if (t == ANY_FUNCTION_TEST) {
      r = AnyFunctionTest(b, 0);
    }
    else if (t == ANY_KIND_TEST) {
      r = AnyKindTest(b, 0);
    }
    else if (t == APOS_ATTR_CONTENT_CHAR) {
      r = AposAttrContentChar(b, 0);
    }
    else if (t == APOS_ATTR_VALUE_CONTENT) {
      r = AposAttrValueContent(b, 0);
    }
    else if (t == ARGUMENT) {
      r = Argument(b, 0);
    }
    else if (t == ARGUMENT_LIST) {
      r = ArgumentList(b, 0);
    }
    else if (t == ARGUMENT_PLACEHOLDER) {
      r = ArgumentPlaceholder(b, 0);
    }
    else if (t == ARRAY_CONSTRUCTOR) {
      r = ArrayConstructor(b, 0);
    }
    else if (t == ARRAY_TEST) {
      r = ArrayTest(b, 0);
    }
    else if (t == ARROW_EXPR) {
      r = ArrowExpr(b, 0);
    }
    else if (t == ARROW_FUNCTION_REFERENCE) {
      r = ArrowFunctionReference(b, 0);
    }
    else if (t == ARROW_FUNCTION_SPECIFIER) {
      r = ArrowFunctionSpecifier(b, 0);
    }
    else if (t == ATOMIC_OR_UNION_TYPE) {
      r = AtomicOrUnionType(b, 0);
    }
    else if (t == ATTR_LOCAL_NAME) {
      r = AttrLocalName(b, 0);
    }
    else if (t == ATTR_NAMESPACE) {
      r = AttrNamespace(b, 0);
    }
    else if (t == ATTRIB_NAME_OR_WILDCARD) {
      r = AttribNameOrWildcard(b, 0);
    }
    else if (t == ATTRIBUTE_DECLARATION) {
      r = AttributeDeclaration(b, 0);
    }
    else if (t == ATTRIBUTE_NAME) {
      r = AttributeName(b, 0);
    }
    else if (t == ATTRIBUTE_TEST) {
      r = AttributeTest(b, 0);
    }
    else if (t == AXIS_STEP) {
      r = AxisStep(b, 0);
    }
    else if (t == BASE_URI_DECL) {
      r = BaseURIDecl(b, 0);
    }
    else if (t == BOUNDARY_SPACE_DECL) {
      r = BoundarySpaceDecl(b, 0);
    }
    else if (t == C_DATA_SECTION) {
      r = CDataSection(b, 0);
    }
    else if (t == C_DATA_SECTION_CONTENTS) {
      r = CDataSectionContents(b, 0);
    }
    else if (t == CASE_CLAUSE) {
      r = CaseClause(b, 0);
    }
    else if (t == CAST_EXPR) {
      r = CastExpr(b, 0);
    }
    else if (t == CAST_OPERATOR) {
      r = CastOperator(b, 0);
    }
    else if (t == CASTABLE_EXPR) {
      r = CastableExpr(b, 0);
    }
    else if (t == CASTABLE_OPERATOR) {
      r = CastableOperator(b, 0);
    }
    else if (t == CATCH_CLAUSE) {
      r = CatchClause(b, 0);
    }
    else if (t == CATCH_CLAUSE_EXPRESSION) {
      r = CatchClauseExpression(b, 0);
    }
    else if (t == CATCH_ERROR_LIST) {
      r = CatchErrorList(b, 0);
    }
    else if (t == COMMENT_TEST) {
      r = CommentTest(b, 0);
    }
    else if (t == COMMON_CONTENT) {
      r = CommonContent(b, 0);
    }
    else if (t == COMP_ATTR_CONSTRUCTOR) {
      r = CompAttrConstructor(b, 0);
    }
    else if (t == COMP_COMMENT_CONSTRUCTOR) {
      r = CompCommentConstructor(b, 0);
    }
    else if (t == COMP_DOC_CONSTRUCTOR) {
      r = CompDocConstructor(b, 0);
    }
    else if (t == COMP_ELEM_CONSTRUCTOR) {
      r = CompElemConstructor(b, 0);
    }
    else if (t == COMP_NAMESPACE_CONSTRUCTOR) {
      r = CompNamespaceConstructor(b, 0);
    }
    else if (t == COMP_PI_CONSTRUCTOR) {
      r = CompPIConstructor(b, 0);
    }
    else if (t == COMP_TEXT_CONSTRUCTOR) {
      r = CompTextConstructor(b, 0);
    }
    else if (t == COMPARISON_EXPR) {
      r = ComparisonExpr(b, 0);
    }
    else if (t == COMPATIBILITY_ANNOTATION) {
      r = CompatibilityAnnotation(b, 0);
    }
    else if (t == COMPUTED_CONSTRUCTOR) {
      r = ComputedConstructor(b, 0);
    }
    else if (t == CONCAT_OPERATOR) {
      r = ConcatOperator(b, 0);
    }
    else if (t == CONSTRUCTION_DECL) {
      r = ConstructionDecl(b, 0);
    }
    else if (t == CONTEXT_ITEM_DECL) {
      r = ContextItemDecl(b, 0);
    }
    else if (t == CONTEXT_ITEM_EXPR) {
      r = ContextItemExpr(b, 0);
    }
    else if (t == COPY_NAMESPACES_DECL) {
      r = CopyNamespacesDecl(b, 0);
    }
    else if (t == COUNT_CLAUSE) {
      r = CountClause(b, 0);
    }
    else if (t == CURLY_ARRAY_CONSTRUCTOR) {
      r = CurlyArrayConstructor(b, 0);
    }
    else if (t == CURRENT_ITEM) {
      r = CurrentItem(b, 0);
    }
    else if (t == DECIMAL_FORMAT_DECL) {
      r = DecimalFormatDecl(b, 0);
    }
    else if (t == DEFAULT_COLLATION_DECL) {
      r = DefaultCollationDecl(b, 0);
    }
    else if (t == DEFAULT_ELEMENT_NAMESPACE_DECL) {
      r = DefaultElementNamespaceDecl(b, 0);
    }
    else if (t == DEFAULT_FUNCTION_NAMESPACE_DECL) {
      r = DefaultFunctionNamespaceDecl(b, 0);
    }
    else if (t == DELETE_EXPR) {
      r = DeleteExpr(b, 0);
    }
    else if (t == DIR_ATTRIBUTE) {
      r = DirAttribute(b, 0);
    }
    else if (t == DIR_ATTRIBUTE_LIST) {
      r = DirAttributeList(b, 0);
    }
    else if (t == DIR_ATTRIBUTE_NAME) {
      r = DirAttributeName(b, 0);
    }
    else if (t == DIR_ATTRIBUTE_VALUE) {
      r = DirAttributeValue(b, 0);
    }
    else if (t == DIR_COMMENT_CONSTRUCTOR) {
      r = DirCommentConstructor(b, 0);
    }
    else if (t == DIR_COMMENT_CONTENTS) {
      r = DirCommentContents(b, 0);
    }
    else if (t == DIR_ELEM_CONTENT) {
      r = DirElemContent(b, 0);
    }
    else if (t == DIR_PI_CONSTRUCTOR) {
      r = DirPIConstructor(b, 0);
    }
    else if (t == DIR_PI_CONTENTS) {
      r = DirPIContents(b, 0);
    }
    else if (t == DIRECT_CONSTRUCTOR) {
      r = DirectConstructor(b, 0);
    }
    else if (t == DOCUMENT_TEST) {
      r = DocumentTest(b, 0);
    }
    else if (t == ELEMENT_DECLARATION) {
      r = ElementDeclaration(b, 0);
    }
    else if (t == ELEMENT_NAME) {
      r = ElementName(b, 0);
    }
    else if (t == ELEMENT_NAME_OR_WILDCARD) {
      r = ElementNameOrWildcard(b, 0);
    }
    else if (t == ELEMENT_TEST) {
      r = ElementTest(b, 0);
    }
    else if (t == EMPTY_ORDER_DECL) {
      r = EmptyOrderDecl(b, 0);
    }
    else if (t == ENCLOSED_CONTENT_EXPRESSION) {
      r = EnclosedContentExpression(b, 0);
    }
    else if (t == ENCLOSED_EXPRESSION) {
      r = EnclosedExpression(b, 0);
    }
    else if (t == ENCLOSED_PREFIX_EXPRESSION) {
      r = EnclosedPrefixExpression(b, 0);
    }
    else if (t == ENCLOSED_URI_EXPRESSION) {
      r = EnclosedURIExpression(b, 0);
    }
    else if (t == EQUALITY_COMP) {
      r = EqualityComp(b, 0);
    }
    else if (t == ESCAPE_APOS) {
      r = EscapeApos(b, 0);
    }
    else if (t == ESCAPE_QUOT) {
      r = EscapeQuot(b, 0);
    }
    else if (t == EXPR) {
      r = Expr(b, 0);
    }
    else if (t == EXPR_SINGLE) {
      r = ExprSingle(b, 0);
    }
    else if (t == EXTENSION_EXPR) {
      r = ExtensionExpr(b, 0);
    }
    else if (t == EXTERNAL_VAR_PART) {
      r = ExternalVarPart(b, 0);
    }
    else if (t == FLWOR_EXPR) {
      r = FLWORExpr(b, 0);
    }
    else if (t == FOR_BINDING) {
      r = ForBinding(b, 0);
    }
    else if (t == FOR_CLAUSE) {
      r = ForClause(b, 0);
    }
    else if (t == FORWARD_STEP) {
      r = ForwardStep(b, 0);
    }
    else if (t == FUNCTION_ARITY) {
      r = FunctionArity(b, 0);
    }
    else if (t == FUNCTION_BODY) {
      r = FunctionBody(b, 0);
    }
    else if (t == FUNCTION_CALL) {
      r = FunctionCall(b, 0);
    }
    else if (t == FUNCTION_DECL) {
      r = FunctionDecl(b, 0);
    }
    else if (t == FUNCTION_ITEM_EXPR) {
      r = FunctionItemExpr(b, 0);
    }
    else if (t == FUNCTION_LOCAL_NAME) {
      r = FunctionLocalName(b, 0);
    }
    else if (t == FUNCTION_NAME) {
      r = FunctionName(b, 0);
    }
    else if (t == FUNCTION_TEST) {
      r = FunctionTest(b, 0);
    }
    else if (t == GENERAL_ITEM_TYPE) {
      r = GeneralItemType(b, 0);
    }
    else if (t == GROUP_BY_CLAUSE) {
      r = GroupByClause(b, 0);
    }
    else if (t == GROUPING_SPEC) {
      r = GroupingSpec(b, 0);
    }
    else if (t == GROUPING_SPEC_LIST) {
      r = GroupingSpecList(b, 0);
    }
    else if (t == GROUPING_VARIABLE) {
      r = GroupingVariable(b, 0);
    }
    else if (t == IF_EXPR) {
      r = IfExpr(b, 0);
    }
    else if (t == INHERIT_MODE) {
      r = InheritMode(b, 0);
    }
    else if (t == INLINE_FUNCTION_EXPR) {
      r = InlineFunctionExpr(b, 0);
    }
    else if (t == INSERT_EXPR) {
      r = InsertExpr(b, 0);
    }
    else if (t == INSERT_EXPR_TARGET_CHOICE) {
      r = InsertExprTargetChoice(b, 0);
    }
    else if (t == INSTANCE_OF_OPERATOR) {
      r = InstanceOfOperator(b, 0);
    }
    else if (t == INSTANCEOF_EXPR) {
      r = InstanceofExpr(b, 0);
    }
    else if (t == INTERSECT_EXCEPT_EXPR) {
      r = IntersectExceptExpr(b, 0);
    }
    else if (t == INTERSECT_EXCEPT_OPERATOR) {
      r = IntersectExceptOperator(b, 0);
    }
    else if (t == ITEM_TYPE) {
      r = ItemType(b, 0);
    }
    else if (t == KEY_SPECIFIER) {
      r = KeySpecifier(b, 0);
    }
    else if (t == KIND_TEST) {
      r = KindTest(b, 0);
    }
    else if (t == LET_BINDING) {
      r = LetBinding(b, 0);
    }
    else if (t == LET_CLAUSE) {
      r = LetClause(b, 0);
    }
    else if (t == LITERAL) {
      r = Literal(b, 0);
    }
    else if (t == LOCAL_PART) {
      r = LocalPart(b, 0);
    }
    else if (t == LOOKUP) {
      r = Lookup(b, 0);
    }
    else if (t == MAP_CONSTRUCTOR) {
      r = MapConstructor(b, 0);
    }
    else if (t == MAP_CONSTRUCTOR_ENTRY) {
      r = MapConstructorEntry(b, 0);
    }
    else if (t == MAP_KEY_EXPR) {
      r = MapKeyExpr(b, 0);
    }
    else if (t == MAP_TEST) {
      r = MapTest(b, 0);
    }
    else if (t == MAP_VALUE_EXPR) {
      r = MapValueExpr(b, 0);
    }
    else if (t == MARKLOGIC_ANNOTATION) {
      r = MarklogicAnnotation(b, 0);
    }
    else if (t == MARKLOGIC_ANY_KIND_TEST) {
      r = MarklogicAnyKindTest(b, 0);
    }
    else if (t == MARKLOGIC_ARRAY_NODE_TEST) {
      r = MarklogicArrayNodeTest(b, 0);
    }
    else if (t == MARKLOGIC_BINARY_TEST) {
      r = MarklogicBinaryTest(b, 0);
    }
    else if (t == MARKLOGIC_BOOLEAN_NODE_TEST) {
      r = MarklogicBooleanNodeTest(b, 0);
    }
    else if (t == MARKLOGIC_CATCH_ERROR_LIST) {
      r = MarklogicCatchErrorList(b, 0);
    }
    else if (t == MARKLOGIC_COMP_ARRAY_NODE_CONSTRUCTOR) {
      r = MarklogicCompArrayNodeConstructor(b, 0);
    }
    else if (t == MARKLOGIC_COMP_BINARY_CONSTRUCTOR) {
      r = MarklogicCompBinaryConstructor(b, 0);
    }
    else if (t == MARKLOGIC_COMP_BOOLEAN_NODE_CONSTRUCTOR) {
      r = MarklogicCompBooleanNodeConstructor(b, 0);
    }
    else if (t == MARKLOGIC_COMP_NULL_NODE_CONSTRUCTOR) {
      r = MarklogicCompNullNodeConstructor(b, 0);
    }
    else if (t == MARKLOGIC_COMP_NUMBER_NODE_CONSTRUCTOR) {
      r = MarklogicCompNumberNodeConstructor(b, 0);
    }
    else if (t == MARKLOGIC_COMP_OBJECT_NODE_CONSTRUCTOR) {
      r = MarklogicCompObjectNodeConstructor(b, 0);
    }
    else if (t == MARKLOGIC_NAMESPACE_AXIS) {
      r = MarklogicNamespaceAxis(b, 0);
    }
    else if (t == MARKLOGIC_NULL_NODE_TEST) {
      r = MarklogicNullNodeTest(b, 0);
    }
    else if (t == MARKLOGIC_NUMBER_NODE_TEST) {
      r = MarklogicNumberNodeTest(b, 0);
    }
    else if (t == MARKLOGIC_OBJECT_NODE_TEST) {
      r = MarklogicObjectNodeTest(b, 0);
    }
    else if (t == MARKLOGIC_TEXT_TEST) {
      r = MarklogicTextTest(b, 0);
    }
    else if (t == MARKLOGIC_VALIDATION) {
      r = MarklogicValidation(b, 0);
    }
    else if (t == MISPLACED_COMMENT) {
      r = MisplacedComment(b, 0);
    }
    else if (t == MODULE_DECL) {
      r = ModuleDecl(b, 0);
    }
    else if (t == MODULE_IMPORT) {
      r = ModuleImport(b, 0);
    }
    else if (t == MODULE_IMPORT_NAMESPACE) {
      r = ModuleImportNamespace(b, 0);
    }
    else if (t == MODULE_IMPORT_PATH) {
      r = ModuleImportPath(b, 0);
    }
    else if (t == MULTI_VARIABLE_BINDING) {
      r = MultiVariableBinding(b, 0);
    }
    else if (t == MULTIPLICATIVE_EXPR) {
      r = MultiplicativeExpr(b, 0);
    }
    else if (t == MULTIPLICATIVE_OPERATOR) {
      r = MultiplicativeOperator(b, 0);
    }
    else if (t == NAME_TEST) {
      r = NameTest(b, 0);
    }
    else if (t == NAMED_FUNCTION_REF) {
      r = NamedFunctionRef(b, 0);
    }
    else if (t == NAMESPACE_DECL) {
      r = NamespaceDecl(b, 0);
    }
    else if (t == NAMESPACE_NODE_TEST) {
      r = NamespaceNodeTest(b, 0);
    }
    else if (t == NAMESPACE_PREFIX) {
      r = NamespacePrefix(b, 0);
    }
    else if (t == NEW_NAME_EXPR) {
      r = NewNameExpr(b, 0);
    }
    else if (t == NEXT_ITEM) {
      r = NextItem(b, 0);
    }
    else if (t == NODE_COMP) {
      r = NodeComp(b, 0);
    }
    else if (t == NODE_CONSTRUCTOR) {
      r = NodeConstructor(b, 0);
    }
    else if (t == NODE_TEST) {
      r = NodeTest(b, 0);
    }
    else if (t == NUMERIC_LITERAL) {
      r = NumericLiteral(b, 0);
    }
    else if (t == OBJECT_PROPERTY) {
      r = ObjectProperty(b, 0);
    }
    else if (t == OBJECT_PROPERTY_LIST) {
      r = ObjectPropertyList(b, 0);
    }
    else if (t == OCCURRENCE_INDICATOR) {
      r = OccurrenceIndicator(b, 0);
    }
    else if (t == OPTION_DECL) {
      r = OptionDecl(b, 0);
    }
    else if (t == OR_EXPR) {
      r = OrExpr(b, 0);
    }
    else if (t == OR_OPERATOR) {
      r = OrOperator(b, 0);
    }
    else if (t == ORDER_BY_CLAUSE) {
      r = OrderByClause(b, 0);
    }
    else if (t == ORDER_SPEC) {
      r = OrderSpec(b, 0);
    }
    else if (t == ORDER_SPEC_LIST) {
      r = OrderSpecList(b, 0);
    }
    else if (t == ORDERED_EXPR) {
      r = OrderedExpr(b, 0);
    }
    else if (t == ORDERING_MODE_DECL) {
      r = OrderingModeDecl(b, 0);
    }
    else if (t == PI_TEST) {
      r = PITest(b, 0);
    }
    else if (t == PARAM) {
      r = Param(b, 0);
    }
    else if (t == PARAM_LIST) {
      r = ParamList(b, 0);
    }
    else if (t == PARENTHESIZED_EXPR) {
      r = ParenthesizedExpr(b, 0);
    }
    else if (t == PARENTHESIZED_ITEM_TYPE) {
      r = ParenthesizedItemType(b, 0);
    }
    else if (t == PATH_EXPR) {
      r = PathExpr(b, 0);
    }
    else if (t == POSITIONAL_VAR) {
      r = PositionalVar(b, 0);
    }
    else if (t == POSTFIX_EXPR) {
      r = PostfixExpr(b, 0);
    }
    else if (t == PRAGMA) {
      r = Pragma(b, 0);
    }
    else if (t == PRAGMA_CONTENTS) {
      r = PragmaContents(b, 0);
    }
    else if (t == PREDICATE) {
      r = Predicate(b, 0);
    }
    else if (t == PREDICATE_LIST) {
      r = PredicateList(b, 0);
    }
    else if (t == PREFIX) {
      r = Prefix(b, 0);
    }
    else if (t == PRESERVE_MODE) {
      r = PreserveMode(b, 0);
    }
    else if (t == PREVIOUS_ITEM) {
      r = PreviousItem(b, 0);
    }
    else if (t == PRIMARY_EXPR) {
      r = PrimaryExpr(b, 0);
    }
    else if (t == QUANTIFIED_EXPR) {
      r = QuantifiedExpr(b, 0);
    }
    else if (t == QUERY_BODY) {
      r = QueryBody(b, 0);
    }
    else if (t == QUOT_ATTR_CONTENT_CHAR) {
      r = QuotAttrContentChar(b, 0);
    }
    else if (t == QUOT_ATTR_VALUE_CONTENT) {
      r = QuotAttrValueContent(b, 0);
    }
    else if (t == RANGE_EXPR) {
      r = RangeExpr(b, 0);
    }
    else if (t == RELATIONAL_COMP) {
      r = RelationalComp(b, 0);
    }
    else if (t == RELATIVE_PATH_OPERATOR) {
      r = RelativePathOperator(b, 0);
    }
    else if (t == RENAME_EXPR) {
      r = RenameExpr(b, 0);
    }
    else if (t == REPLACE_EXPR) {
      r = ReplaceExpr(b, 0);
    }
    else if (t == RETURN_CLAUSE) {
      r = ReturnClause(b, 0);
    }
    else if (t == REVALIDATION_DECL) {
      r = RevalidationDecl(b, 0);
    }
    else if (t == REVERSE_STEP) {
      r = ReverseStep(b, 0);
    }
    else if (t == SAXON_MAP_ENTRIES_SEPARATOR) {
      r = SaxonMapEntriesSeparator(b, 0);
    }
    else if (t == SAXON_MAP_ENTRY_SEPARATOR) {
      r = SaxonMapEntrySeparator(b, 0);
    }
    else if (t == SCHEMA_ATTRIBUTE_TEST) {
      r = SchemaAttributeTest(b, 0);
    }
    else if (t == SCHEMA_ELEMENT_TEST) {
      r = SchemaElementTest(b, 0);
    }
    else if (t == SCHEMA_IMPORT) {
      r = SchemaImport(b, 0);
    }
    else if (t == SEPARATOR) {
      r = Separator(b, 0);
    }
    else if (t == SEQUENCE_TYPE) {
      r = SequenceType(b, 0);
    }
    else if (t == SEQUENCE_TYPE_UNION) {
      r = SequenceTypeUnion(b, 0);
    }
    else if (t == SIMPLE_MAP_EXPR) {
      r = SimpleMapExpr(b, 0);
    }
    else if (t == SIMPLE_MAP_OPERATOR) {
      r = SimpleMapOperator(b, 0);
    }
    else if (t == SIMPLE_TYPE_NAME) {
      r = SimpleTypeName(b, 0);
    }
    else if (t == SINGLE_TYPE) {
      r = SingleType(b, 0);
    }
    else if (t == SOURCE_EXPR) {
      r = SourceExpr(b, 0);
    }
    else if (t == SQUARE_ARRAY_CONSTRUCTOR) {
      r = SquareArrayConstructor(b, 0);
    }
    else if (t == STEP_EXPR) {
      r = StepExpr(b, 0);
    }
    else if (t == STRING_CONCAT_EXPR) {
      r = StringConcatExpr(b, 0);
    }
    else if (t == STRING_CONSTRUCTOR) {
      r = StringConstructor(b, 0);
    }
    else if (t == STRING_CONSTRUCTOR_CHARS) {
      r = StringConstructorChars(b, 0);
    }
    else if (t == STRING_CONSTRUCTOR_CONTENT) {
      r = StringConstructorContent(b, 0);
    }
    else if (t == STRING_CONSTRUCTOR_INTERPOLATION) {
      r = StringConstructorInterpolation(b, 0);
    }
    else if (t == STRING_LITERAL) {
      r = StringLiteral(b, 0);
    }
    else if (t == STRING_LITERAL_OR_WILDCARD) {
      r = StringLiteralOrWildcard(b, 0);
    }
    else if (t == SWITCH_CASE_CLAUSE) {
      r = SwitchCaseClause(b, 0);
    }
    else if (t == SWITCH_CASE_OPERAND) {
      r = SwitchCaseOperand(b, 0);
    }
    else if (t == SWITCH_DEFAULT_RETURN_CLAUSE) {
      r = SwitchDefaultReturnClause(b, 0);
    }
    else if (t == SWITCH_EXPR) {
      r = SwitchExpr(b, 0);
    }
    else if (t == SWITCH_RETURN_CLAUSE) {
      r = SwitchReturnClause(b, 0);
    }
    else if (t == TARGET_EXPR) {
      r = TargetExpr(b, 0);
    }
    else if (t == TEXT_TEST) {
      r = TextTest(b, 0);
    }
    else if (t == TO_OPERATOR) {
      r = ToOperator(b, 0);
    }
    else if (t == TRANSFORM_EXPR) {
      r = TransformExpr(b, 0);
    }
    else if (t == TREAT_EXPR) {
      r = TreatExpr(b, 0);
    }
    else if (t == TREAT_OPERATOR) {
      r = TreatOperator(b, 0);
    }
    else if (t == TRY_CATCH_EXPR) {
      r = TryCatchExpr(b, 0);
    }
    else if (t == TRY_CLAUSE) {
      r = TryClause(b, 0);
    }
    else if (t == TYPE_DECLARATION) {
      r = TypeDeclaration(b, 0);
    }
    else if (t == TYPE_NAME) {
      r = TypeName(b, 0);
    }
    else if (t == TYPED_FUNCTION_TEST) {
      r = TypedFunctionTest(b, 0);
    }
    else if (t == TYPESWITCH_DEFAULT_RETURN_CLAUSE) {
      r = TypeswitchDefaultReturnClause(b, 0);
    }
    else if (t == TYPESWITCH_EXPR) {
      r = TypeswitchExpr(b, 0);
    }
    else if (t == URI_LITERAL) {
      r = URILiteral(b, 0);
    }
    else if (t == UNARY_EXPR) {
      r = UnaryExpr(b, 0);
    }
    else if (t == UNARY_LOOKUP) {
      r = UnaryLookup(b, 0);
    }
    else if (t == UNION_EXPR) {
      r = UnionExpr(b, 0);
    }
    else if (t == UNION_OPERATOR) {
      r = UnionOperator(b, 0);
    }
    else if (t == UNORDERED_EXPR) {
      r = UnorderedExpr(b, 0);
    }
    else if (t == VALIDATE_EXPR) {
      r = ValidateExpr(b, 0);
    }
    else if (t == VALUE_COMP) {
      r = ValueComp(b, 0);
    }
    else if (t == VALUE_EXPR) {
      r = ValueExpr(b, 0);
    }
    else if (t == VAR_DECL) {
      r = VarDecl(b, 0);
    }
    else if (t == VAR_DEFAULT_VALUE) {
      r = VarDefaultValue(b, 0);
    }
    else if (t == VAR_LOCAL_NAME) {
      r = VarLocalName(b, 0);
    }
    else if (t == VAR_NAME) {
      r = VarName(b, 0);
    }
    else if (t == VAR_REF) {
      r = VarRef(b, 0);
    }
    else if (t == VAR_VALUE) {
      r = VarValue(b, 0);
    }
    else if (t == VERSION) {
      r = Version(b, 0);
    }
    else if (t == VERSION_DECL) {
      r = VersionDecl(b, 0);
    }
    else if (t == WHERE_CLAUSE) {
      r = WhereClause(b, 0);
    }
    else if (t == WILDCARD) {
      r = Wildcard(b, 0);
    }
    else if (t == WINDOW_CLAUSE) {
      r = WindowClause(b, 0);
    }
    else if (t == XML_EMPTY_TAG) {
      r = XmlEmptyTag(b, 0);
    }
    else if (t == XML_FULL_TAG) {
      r = XmlFullTag(b, 0);
    }
    else if (t == XML_TAG_LOCAL_NAME) {
      r = XmlTagLocalName(b, 0);
    }
    else if (t == XML_TAG_NAME) {
      r = XmlTagName(b, 0);
    }
    else if (t == XML_TAG_NAMESPACE) {
      r = XmlTagNamespace(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return xqueryFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ADDITIVE_EXPR, AND_EXPR, ARROW_EXPR, CASTABLE_EXPR,
      CAST_EXPR, COMPARISON_EXPR, CONTEXT_ITEM_EXPR, DELETE_EXPR,
      EXPR_SINGLE, EXTENSION_EXPR, FLWOR_EXPR, FUNCTION_ITEM_EXPR,
      IF_EXPR, INLINE_FUNCTION_EXPR, INSERT_EXPR, INSTANCEOF_EXPR,
      INTERSECT_EXCEPT_EXPR, MAP_KEY_EXPR, MAP_VALUE_EXPR, MULTIPLICATIVE_EXPR,
      NEW_NAME_EXPR, ORDERED_EXPR, OR_EXPR, PARENTHESIZED_EXPR,
      PATH_EXPR, POSTFIX_EXPR, PRIMARY_EXPR, QUANTIFIED_EXPR,
      RANGE_EXPR, RENAME_EXPR, REPLACE_EXPR, SIMPLE_MAP_EXPR,
      SOURCE_EXPR, STEP_EXPR, STRING_CONCAT_EXPR, SWITCH_EXPR,
      TARGET_EXPR, TRANSFORM_EXPR, TREAT_EXPR, TRY_CATCH_EXPR,
      TYPESWITCH_EXPR, UNARY_EXPR, UNION_EXPR, UNORDERED_EXPR,
      VALIDATE_EXPR, VALUE_EXPR),
  };

  /* ********************************************************** */
  // "@"? NodeTest
  public static boolean AbbrevForwardStep(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AbbrevForwardStep")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ABBREV_FORWARD_STEP, "<abbrev forward step>");
    r = AbbrevForwardStep_0(b, l + 1);
    r = r && NodeTest(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "@"?
  private static boolean AbbrevForwardStep_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AbbrevForwardStep_0")) return false;
    consumeToken(b, AT_SIGN);
    return true;
  }

  /* ********************************************************** */
  // ".."
  public static boolean AbbrevReverseStep(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AbbrevReverseStep")) return false;
    if (!nextTokenIs(b, DOT_DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT_DOT);
    exit_section_(b, m, ABBREV_REVERSE_STEP, r);
    return r;
  }

  /* ********************************************************** */
  // MultiplicativeExpr AdditiveOptionalExpr*
  public static boolean AdditiveExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditiveExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ADDITIVE_EXPR, "<additive expr>");
    r = MultiplicativeExpr(b, l + 1);
    r = r && AdditiveExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // AdditiveOptionalExpr*
  private static boolean AdditiveExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditiveExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!AdditiveOptionalExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AdditiveExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "+" | "-"
  public static boolean AdditiveOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditiveOperator")) return false;
    if (!nextTokenIs(b, "<additive operator>", OP_PLUS, OP_MINUS)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADDITIVE_OPERATOR, "<additive operator>");
    r = consumeToken(b, OP_PLUS);
    if (!r) r = consumeToken(b, OP_MINUS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AdditiveOperator MultiplicativeExpr
  static boolean AdditiveOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditiveOptionalExpr")) return false;
    if (!nextTokenIs(b, "", OP_PLUS, OP_MINUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = AdditiveOperator(b, l + 1);
    p = r; // pin = 1
    r = r && MultiplicativeExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "allowing" "empty"
  public static boolean AllowingEmpty(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AllowingEmpty")) return false;
    if (!nextTokenIs(b, K_ALLOWING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ALLOWING, K_EMPTY);
    exit_section_(b, m, ALLOWING_EMPTY, r);
    return r;
  }

  /* ********************************************************** */
  // ComparisonExpr AndMultipliedExpr*
  public static boolean AndExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, AND_EXPR, "<and expr>");
    r = ComparisonExpr(b, l + 1);
    r = r && AndExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // AndMultipliedExpr*
  private static boolean AndExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!AndMultipliedExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AndExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // AndOperator ComparisonExpr
  static boolean AndMultipliedExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndMultipliedExpr")) return false;
    if (!nextTokenIs(b, K_AND)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = AndOperator(b, l + 1);
    p = r; // pin = 1
    r = r && ComparisonExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "and"
  public static boolean AndOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndOperator")) return false;
    if (!nextTokenIs(b, K_AND)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AND);
    exit_section_(b, m, AND_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // VarDecl | FunctionDecl
  static boolean AnnotatedDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotatedDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarDecl(b, l + 1);
    if (!r) r = FunctionDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "%" AnnotationName ("(" Literal ("," Literal)* ")")?
  public static boolean Annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation")) return false;
    if (!nextTokenIs(b, PERCENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PERCENT);
    r = r && AnnotationName(b, l + 1);
    r = r && Annotation_2(b, l + 1);
    exit_section_(b, m, ANNOTATION, r);
    return r;
  }

  // ("(" Literal ("," Literal)* ")")?
  private static boolean Annotation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation_2")) return false;
    Annotation_2_0(b, l + 1);
    return true;
  }

  // "(" Literal ("," Literal)* ")"
  private static boolean Annotation_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PAR);
    r = r && Literal(b, l + 1);
    r = r && Annotation_2_0_2(b, l + 1);
    r = r && consumeToken(b, R_PAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," Literal)*
  private static boolean Annotation_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation_2_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Annotation_2_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Annotation_2_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Literal
  private static boolean Annotation_2_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation_2_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Literal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EQName
  public static boolean AnnotationName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationName")) return false;
    if (!nextTokenIs(b, "<annotation name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_NAME, "<annotation name>");
    r = EQName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "array" "(" "*" ")"
  static boolean AnyArrayTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnyArrayTest")) return false;
    if (!nextTokenIs(b, K_ARRAY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 3, K_ARRAY, L_PAR, STAR_SIGN, R_PAR);
    p = r; // pin = 3
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "function" "(" "*" ")"
  public static boolean AnyFunctionTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnyFunctionTest")) return false;
    if (!nextTokenIs(b, K_FUNCTION)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ANY_FUNCTION_TEST, null);
    r = consumeTokens(b, 3, K_FUNCTION, L_PAR, STAR_SIGN, R_PAR);
    p = r; // pin = 3
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "node" "(" ")"
  public static boolean AnyKindTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnyKindTest")) return false;
    if (!nextTokenIs(b, K_NODE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ANY_KIND_TEST, null);
    r = consumeTokens(b, 2, K_NODE, L_PAR, R_PAR);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "map" "(" "*" ")"
  static boolean AnyMapTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnyMapTest")) return false;
    if (!nextTokenIs(b, K_MAP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 3, K_MAP, L_PAR, STAR_SIGN, R_PAR);
    p = r; // pin = 3
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Char
  public static boolean AposAttrContentChar(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AposAttrContentChar")) return false;
    if (!nextTokenIs(b, CHAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CHAR);
    exit_section_(b, m, APOS_ATTR_CONTENT_CHAR, r);
    return r;
  }

  /* ********************************************************** */
  // AposAttrContentChar
  //  | CommonContent
  public static boolean AposAttrValueContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AposAttrValueContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, APOS_ATTR_VALUE_CONTENT, "<apos attr value content>");
    r = AposAttrContentChar(b, l + 1);
    if (!r) r = CommonContent(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OpeningApos (PredefinedEntityRef | CharRef | EscapeApos | StringChar)* ClosingApos
  static boolean AposStringLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AposStringLiteral")) return false;
    if (!nextTokenIs(b, OPENINGAPOS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPENINGAPOS);
    r = r && AposStringLiteral_1(b, l + 1);
    r = r && consumeToken(b, CLOSINGAPOS);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PredefinedEntityRef | CharRef | EscapeApos | StringChar)*
  private static boolean AposStringLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AposStringLiteral_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!AposStringLiteral_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AposStringLiteral_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // PredefinedEntityRef | CharRef | EscapeApos | StringChar
  private static boolean AposStringLiteral_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AposStringLiteral_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PREDEFINEDENTITYREF);
    if (!r) r = consumeToken(b, CHARREF);
    if (!r) r = EscapeApos(b, l + 1);
    if (!r) r = consumeToken(b, STRINGCHAR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle | ArgumentPlaceholder
  public static boolean Argument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Argument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENT, "<argument>");
    r = ExprSingle(b, l + 1);
    if (!r) r = ArgumentPlaceholder(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "(" (Argument OptionalArgumentAfterComma*)? ")"
  public static boolean ArgumentList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentList")) return false;
    if (!nextTokenIs(b, L_PAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENT_LIST, null);
    r = consumeToken(b, L_PAR);
    p = r; // pin = 1
    r = r && report_error_(b, ArgumentList_1(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (Argument OptionalArgumentAfterComma*)?
  private static boolean ArgumentList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentList_1")) return false;
    ArgumentList_1_0(b, l + 1);
    return true;
  }

  // Argument OptionalArgumentAfterComma*
  private static boolean ArgumentList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Argument(b, l + 1);
    r = r && ArgumentList_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OptionalArgumentAfterComma*
  private static boolean ArgumentList_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentList_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!OptionalArgumentAfterComma(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArgumentList_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "?"
  public static boolean ArgumentPlaceholder(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentPlaceholder")) return false;
    if (!nextTokenIs(b, QUESTIONMARK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUESTIONMARK);
    exit_section_(b, m, ARGUMENT_PLACEHOLDER, r);
    return r;
  }

  /* ********************************************************** */
  // SquareArrayConstructor | CurlyArrayConstructor
  public static boolean ArrayConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayConstructor")) return false;
    if (!nextTokenIs(b, "<array constructor>", L_BRACKET, K_ARRAY)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_CONSTRUCTOR, "<array constructor>");
    r = SquareArrayConstructor(b, l + 1);
    if (!r) r = CurlyArrayConstructor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AnyArrayTest | TypedArrayTest
  public static boolean ArrayTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayTest")) return false;
    if (!nextTokenIs(b, K_ARRAY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AnyArrayTest(b, l + 1);
    if (!r) r = TypedArrayTest(b, l + 1);
    exit_section_(b, m, ARRAY_TEST, r);
    return r;
  }

  /* ********************************************************** */
  // UnaryExpr ArrowOptionalExpr*
  public static boolean ArrowExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrowExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ARROW_EXPR, "<arrow expr>");
    r = UnaryExpr(b, l + 1);
    r = r && ArrowExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ArrowOptionalExpr*
  private static boolean ArrowExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrowExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ArrowOptionalExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArrowExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // ArrowFunctionSpecifier ArgumentList
  public static boolean ArrowFunctionReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrowFunctionReference")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARROW_FUNCTION_REFERENCE, "<arrow function reference>");
    r = ArrowFunctionSpecifier(b, l + 1);
    r = r && ArgumentList(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // FunctionName | VarRef | ParenthesizedExpr
  public static boolean ArrowFunctionSpecifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrowFunctionSpecifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARROW_FUNCTION_SPECIFIER, "<arrow function specifier>");
    r = FunctionName(b, l + 1);
    if (!r) r = VarRef(b, l + 1);
    if (!r) r = ParenthesizedExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "=>" ArrowFunctionReference
  static boolean ArrowOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrowOptionalExpr")) return false;
    if (!nextTokenIs(b, OP_ARROW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, OP_ARROW);
    p = r; // pin = 1
    r = r && ArrowFunctionReference(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // EQName
  public static boolean AtomicOrUnionType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AtomicOrUnionType")) return false;
    if (!nextTokenIs(b, "<atomic or union type>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATOMIC_OR_UNION_TYPE, "<atomic or union type>");
    r = EQName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AttrNCName
  public static boolean AttrLocalName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttrLocalName")) return false;
    if (!nextTokenIs(b, ATTRNCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ATTRNCNAME);
    exit_section_(b, m, ATTR_LOCAL_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // AttrNCName
  public static boolean AttrNamespace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttrNamespace")) return false;
    if (!nextTokenIs(b, ATTRNCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ATTRNCNAME);
    exit_section_(b, m, ATTR_NAMESPACE, r);
    return r;
  }

  /* ********************************************************** */
  // AttributeName | "*"
  public static boolean AttribNameOrWildcard(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttribNameOrWildcard")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTRIB_NAME_OR_WILDCARD, "<attrib name or wildcard>");
    r = AttributeName(b, l + 1);
    if (!r) r = consumeToken(b, STAR_SIGN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AttributeName
  public static boolean AttributeDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeDeclaration")) return false;
    if (!nextTokenIs(b, "<attribute declaration>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE_DECLARATION, "<attribute declaration>");
    r = AttributeName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // EQName
  public static boolean AttributeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeName")) return false;
    if (!nextTokenIs(b, "<attribute name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE_NAME, "<attribute name>");
    r = EQName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "attribute" "(" (AttribNameOrWildcard ("," TypeName)?)? ")"
  public static boolean AttributeTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeTest")) return false;
    if (!nextTokenIs(b, K_ATTRIBUTE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE_TEST, null);
    r = consumeTokens(b, 2, K_ATTRIBUTE, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, AttributeTest_2(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (AttribNameOrWildcard ("," TypeName)?)?
  private static boolean AttributeTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeTest_2")) return false;
    AttributeTest_2_0(b, l + 1);
    return true;
  }

  // AttribNameOrWildcard ("," TypeName)?
  private static boolean AttributeTest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeTest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AttribNameOrWildcard(b, l + 1);
    r = r && AttributeTest_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," TypeName)?
  private static boolean AttributeTest_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeTest_2_0_1")) return false;
    AttributeTest_2_0_1_0(b, l + 1);
    return true;
  }

  // "," TypeName
  private static boolean AttributeTest_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeTest_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && TypeName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (ReverseStep | ForwardStep | MarklogicNamespaceAxis) PredicateList
  public static boolean AxisStep(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AxisStep")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AXIS_STEP, "<axis step>");
    r = AxisStep_0(b, l + 1);
    r = r && PredicateList(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ReverseStep | ForwardStep | MarklogicNamespaceAxis
  private static boolean AxisStep_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AxisStep_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ReverseStep(b, l + 1);
    if (!r) r = ForwardStep(b, l + 1);
    if (!r) r = MarklogicNamespaceAxis(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "declare" "base-uri" URILiteral Separator
  public static boolean BaseURIDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BaseURIDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BASE_URI_DECL, null);
    r = consumeTokens(b, 2, K_DECLARE, K_BASE_URI);
    p = r; // pin = 2
    r = r && report_error_(b, URILiteral(b, l + 1));
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "declare" "boundary-space" ("preserve" | "strip") Separator
  public static boolean BoundarySpaceDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BoundarySpaceDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BOUNDARY_SPACE_DECL, null);
    r = consumeTokens(b, 2, K_DECLARE, K_BOUNDARY_SPACE);
    p = r; // pin = 2
    r = r && report_error_(b, BoundarySpaceDecl_2(b, l + 1));
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // "preserve" | "strip"
  private static boolean BoundarySpaceDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BoundarySpaceDecl_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_PRESERVE);
    if (!r) r = consumeToken(b, K_STRIP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "<![CDATA[" CDataSectionContents "]]>"
  public static boolean CDataSection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CDataSection")) return false;
    if (!nextTokenIs(b, CDATA_BEGIN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CDATA_BEGIN);
    r = r && CDataSectionContents(b, l + 1);
    r = r && consumeToken(b, CDATA_END);
    exit_section_(b, m, C_DATA_SECTION, r);
    return r;
  }

  /* ********************************************************** */
  // CDataSectionContentChar*
  public static boolean CDataSectionContents(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CDataSectionContents")) return false;
    Marker m = enter_section_(b, l, _NONE_, C_DATA_SECTION_CONTENTS, "<c data section contents>");
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CDATASECTIONCONTENTCHAR)) break;
      if (!empty_element_parsed_guard_(b, "CDataSectionContents", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // "case" ("$" VarName "as")? SequenceTypeUnion SwitchReturnClause
  public static boolean CaseClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseClause")) return false;
    if (!nextTokenIs(b, K_CASE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CASE_CLAUSE, null);
    r = consumeToken(b, K_CASE);
    p = r; // pin = 1
    r = r && report_error_(b, CaseClause_1(b, l + 1));
    r = p && report_error_(b, SequenceTypeUnion(b, l + 1)) && r;
    r = p && SwitchReturnClause(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ("$" VarName "as")?
  private static boolean CaseClause_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseClause_1")) return false;
    CaseClause_1_0(b, l + 1);
    return true;
  }

  // "$" VarName "as"
  private static boolean CaseClause_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CaseClause_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    r = r && consumeToken(b, K_AS);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ArrowExpr CastOptionalExpr?
  public static boolean CastExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CastExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, CAST_EXPR, "<cast expr>");
    r = ArrowExpr(b, l + 1);
    r = r && CastExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // CastOptionalExpr?
  private static boolean CastExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CastExpr_1")) return false;
    CastOptionalExpr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "cast" "as"
  public static boolean CastOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CastOperator")) return false;
    if (!nextTokenIs(b, K_CAST)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_CAST, K_AS);
    exit_section_(b, m, CAST_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // CastOperator SingleType
  static boolean CastOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CastOptionalExpr")) return false;
    if (!nextTokenIs(b, K_CAST)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = CastOperator(b, l + 1);
    p = r; // pin = 1
    r = r && SingleType(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // CastExpr CastableOptionalExpr?
  public static boolean CastableExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CastableExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, CASTABLE_EXPR, "<castable expr>");
    r = CastExpr(b, l + 1);
    r = r && CastableExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // CastableOptionalExpr?
  private static boolean CastableExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CastableExpr_1")) return false;
    CastableOptionalExpr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "castable" "as"
  public static boolean CastableOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CastableOperator")) return false;
    if (!nextTokenIs(b, K_CASTABLE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_CASTABLE, K_AS);
    exit_section_(b, m, CASTABLE_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // CastableOperator SingleType
  static boolean CastableOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CastableOptionalExpr")) return false;
    if (!nextTokenIs(b, K_CASTABLE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = CastableOperator(b, l + 1);
    p = r; // pin = 1
    r = r && SingleType(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "catch" (CatchErrorList | MarklogicCatchErrorList) CatchClauseExpression
  public static boolean CatchClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchClause")) return false;
    if (!nextTokenIs(b, K_CATCH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CATCH_CLAUSE, null);
    r = consumeToken(b, K_CATCH);
    p = r; // pin = 1
    r = r && report_error_(b, CatchClause_1(b, l + 1));
    r = p && CatchClauseExpression(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // CatchErrorList | MarklogicCatchErrorList
  private static boolean CatchClause_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchClause_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CatchErrorList(b, l + 1);
    if (!r) r = MarklogicCatchErrorList(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EnclosedExpression
  public static boolean CatchClauseExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchClauseExpression")) return false;
    if (!nextTokenIs(b, L_C_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EnclosedExpression(b, l + 1);
    exit_section_(b, m, CATCH_CLAUSE_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // NameTest ("|" NameTest)*
  public static boolean CatchErrorList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchErrorList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CATCH_ERROR_LIST, "<catch error list>");
    r = NameTest(b, l + 1);
    r = r && CatchErrorList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ("|" NameTest)*
  private static boolean CatchErrorList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchErrorList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!CatchErrorList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CatchErrorList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "|" NameTest
  private static boolean CatchErrorList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CatchErrorList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIPE);
    r = r && NameTest(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "comment" "(" ")"
  public static boolean CommentTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CommentTest")) return false;
    if (!nextTokenIs(b, K_COMMENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, COMMENT_TEST, null);
    r = consumeTokens(b, 2, K_COMMENT, L_PAR, R_PAR);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // PredefinedEntityRef | CharRef | "{{" | "}}" | EnclosedExpression
  public static boolean CommonContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CommonContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMON_CONTENT, "<common content>");
    r = consumeToken(b, PREDEFINEDENTITYREF);
    if (!r) r = consumeToken(b, CHARREF);
    if (!r) r = consumeToken(b, DBL_L_C_BRACE);
    if (!r) r = consumeToken(b, DBL_R_C_BRACE);
    if (!r) r = EnclosedExpression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "attribute" (EQName | ("{" Expr "}")) EnclosedExpression
  public static boolean CompAttrConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompAttrConstructor")) return false;
    if (!nextTokenIs(b, K_ATTRIBUTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_ATTRIBUTE);
    r = r && CompAttrConstructor_1(b, l + 1);
    r = r && EnclosedExpression(b, l + 1);
    exit_section_(b, m, COMP_ATTR_CONSTRUCTOR, r);
    return r;
  }

  // EQName | ("{" Expr "}")
  private static boolean CompAttrConstructor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompAttrConstructor_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EQName(b, l + 1);
    if (!r) r = CompAttrConstructor_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "{" Expr "}"
  private static boolean CompAttrConstructor_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompAttrConstructor_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_C_BRACE);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, R_C_BRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "comment" EnclosedExpression
  public static boolean CompCommentConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompCommentConstructor")) return false;
    if (!nextTokenIs(b, K_COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_COMMENT);
    r = r && EnclosedExpression(b, l + 1);
    exit_section_(b, m, COMP_COMMENT_CONSTRUCTOR, r);
    return r;
  }

  /* ********************************************************** */
  // "document" EnclosedExpression
  public static boolean CompDocConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompDocConstructor")) return false;
    if (!nextTokenIs(b, K_DOCUMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DOCUMENT);
    r = r && EnclosedExpression(b, l + 1);
    exit_section_(b, m, COMP_DOC_CONSTRUCTOR, r);
    return r;
  }

  /* ********************************************************** */
  // "element" (EQName | ("{" Expr "}")) EnclosedContentExpression
  public static boolean CompElemConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompElemConstructor")) return false;
    if (!nextTokenIs(b, K_ELEMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_ELEMENT);
    r = r && CompElemConstructor_1(b, l + 1);
    r = r && EnclosedContentExpression(b, l + 1);
    exit_section_(b, m, COMP_ELEM_CONSTRUCTOR, r);
    return r;
  }

  // EQName | ("{" Expr "}")
  private static boolean CompElemConstructor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompElemConstructor_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EQName(b, l + 1);
    if (!r) r = CompElemConstructor_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "{" Expr "}"
  private static boolean CompElemConstructor_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompElemConstructor_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_C_BRACE);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, R_C_BRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "namespace" (Prefix | (EnclosedPrefixExpression)) EnclosedURIExpression
  public static boolean CompNamespaceConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompNamespaceConstructor")) return false;
    if (!nextTokenIs(b, K_NAMESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_NAMESPACE);
    r = r && CompNamespaceConstructor_1(b, l + 1);
    r = r && EnclosedURIExpression(b, l + 1);
    exit_section_(b, m, COMP_NAMESPACE_CONSTRUCTOR, r);
    return r;
  }

  // Prefix | (EnclosedPrefixExpression)
  private static boolean CompNamespaceConstructor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompNamespaceConstructor_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Prefix(b, l + 1);
    if (!r) r = CompNamespaceConstructor_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EnclosedPrefixExpression)
  private static boolean CompNamespaceConstructor_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompNamespaceConstructor_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EnclosedPrefixExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "processing-instruction" (NCName | ("{" Expr "}")) EnclosedExpression
  public static boolean CompPIConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompPIConstructor")) return false;
    if (!nextTokenIs(b, K_PI)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_PI);
    r = r && CompPIConstructor_1(b, l + 1);
    r = r && EnclosedExpression(b, l + 1);
    exit_section_(b, m, COMP_PI_CONSTRUCTOR, r);
    return r;
  }

  // NCName | ("{" Expr "}")
  private static boolean CompPIConstructor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompPIConstructor_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NCNAME);
    if (!r) r = CompPIConstructor_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "{" Expr "}"
  private static boolean CompPIConstructor_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompPIConstructor_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_C_BRACE);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, R_C_BRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "text" EnclosedExpression
  public static boolean CompTextConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompTextConstructor")) return false;
    if (!nextTokenIs(b, K_TEXT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_TEXT);
    r = r && EnclosedExpression(b, l + 1);
    exit_section_(b, m, COMP_TEXT_CONSTRUCTOR, r);
    return r;
  }

  /* ********************************************************** */
  // ValueComp | GeneralComp | NodeComp
  static boolean Comparison(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comparison")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ValueComp(b, l + 1);
    if (!r) r = GeneralComp(b, l + 1);
    if (!r) r = NodeComp(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // StringConcatExpr ComparisonOptionalExpr ?
  public static boolean ComparisonExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComparisonExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, COMPARISON_EXPR, "<comparison expr>");
    r = StringConcatExpr(b, l + 1);
    r = r && ComparisonExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ComparisonOptionalExpr ?
  private static boolean ComparisonExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComparisonExpr_1")) return false;
    ComparisonOptionalExpr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Comparison StringConcatExpr
  static boolean ComparisonOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComparisonOptionalExpr")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = Comparison(b, l + 1);
    p = r; // pin = 1
    r = r && StringConcatExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "updating"
  public static boolean CompatibilityAnnotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompatibilityAnnotation")) return false;
    if (!nextTokenIs(b, K_UPDATING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_UPDATING);
    exit_section_(b, m, COMPATIBILITY_ANNOTATION, r);
    return r;
  }

  /* ********************************************************** */
  // CompDocConstructor
  //  | CompElemConstructor
  //  | CompAttrConstructor
  //  | CompNamespaceConstructor
  //  | CompTextConstructor
  //  | CompCommentConstructor
  //  | CompPIConstructor
  //  | MarklogicCompBinaryConstructor
  //  | MarklogicCompObjectNodeConstructor
  //  | MarklogicCompNumberNodeConstructor
  //  | MarklogicCompBooleanNodeConstructor
  //  | MarklogicCompNullNodeConstructor
  //  | MarklogicCompArrayNodeConstructor
  public static boolean ComputedConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComputedConstructor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPUTED_CONSTRUCTOR, "<computed constructor>");
    r = CompDocConstructor(b, l + 1);
    if (!r) r = CompElemConstructor(b, l + 1);
    if (!r) r = CompAttrConstructor(b, l + 1);
    if (!r) r = CompNamespaceConstructor(b, l + 1);
    if (!r) r = CompTextConstructor(b, l + 1);
    if (!r) r = CompCommentConstructor(b, l + 1);
    if (!r) r = CompPIConstructor(b, l + 1);
    if (!r) r = MarklogicCompBinaryConstructor(b, l + 1);
    if (!r) r = MarklogicCompObjectNodeConstructor(b, l + 1);
    if (!r) r = MarklogicCompNumberNodeConstructor(b, l + 1);
    if (!r) r = MarklogicCompBooleanNodeConstructor(b, l + 1);
    if (!r) r = MarklogicCompNullNodeConstructor(b, l + 1);
    if (!r) r = MarklogicCompArrayNodeConstructor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "||"
  public static boolean ConcatOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConcatOperator")) return false;
    if (!nextTokenIs(b, PIPE_PIPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIPE_PIPE);
    exit_section_(b, m, CONCAT_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // "declare" "construction" ("strip" | "preserve") Separator
  public static boolean ConstructionDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstructionDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONSTRUCTION_DECL, null);
    r = consumeTokens(b, 2, K_DECLARE, K_CONSTRUCTION);
    p = r; // pin = 2
    r = r && report_error_(b, ConstructionDecl_2(b, l + 1));
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // "strip" | "preserve"
  private static boolean ConstructionDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstructionDecl_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_STRIP);
    if (!r) r = consumeToken(b, K_PRESERVE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "declare" "context" "item" ("as" ItemType)? (VarValueAssignment | ("external" (":=" VarDefaultValue)?)) Separator
  public static boolean ContextItemDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContextItemDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONTEXT_ITEM_DECL, null);
    r = consumeTokens(b, 2, K_DECLARE, K_CONTEXT, K_ITEM);
    p = r; // pin = 2
    r = r && report_error_(b, ContextItemDecl_3(b, l + 1));
    r = p && report_error_(b, ContextItemDecl_4(b, l + 1)) && r;
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ("as" ItemType)?
  private static boolean ContextItemDecl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContextItemDecl_3")) return false;
    ContextItemDecl_3_0(b, l + 1);
    return true;
  }

  // "as" ItemType
  private static boolean ContextItemDecl_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContextItemDecl_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AS);
    r = r && ItemType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // VarValueAssignment | ("external" (":=" VarDefaultValue)?)
  private static boolean ContextItemDecl_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContextItemDecl_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarValueAssignment(b, l + 1);
    if (!r) r = ContextItemDecl_4_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "external" (":=" VarDefaultValue)?
  private static boolean ContextItemDecl_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContextItemDecl_4_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_EXTERNAL);
    r = r && ContextItemDecl_4_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (":=" VarDefaultValue)?
  private static boolean ContextItemDecl_4_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContextItemDecl_4_1_1")) return false;
    ContextItemDecl_4_1_1_0(b, l + 1);
    return true;
  }

  // ":=" VarDefaultValue
  private static boolean ContextItemDecl_4_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContextItemDecl_4_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_ASSIGN);
    r = r && VarDefaultValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "."
  public static boolean ContextItemExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContextItemExpr")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    exit_section_(b, m, CONTEXT_ITEM_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // "declare" "copy-namespaces" PreserveMode "," InheritMode Separator
  public static boolean CopyNamespacesDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CopyNamespacesDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, COPY_NAMESPACES_DECL, null);
    r = consumeTokens(b, 2, K_DECLARE, K_COPY_NAMESPACES);
    p = r; // pin = 2
    r = r && report_error_(b, PreserveMode(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, InheritMode(b, l + 1)) && r;
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "count" "$" VarName
  public static boolean CountClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CountClause")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, COUNT_CLAUSE, "<count clause>");
    r = consumeTokens(b, 1, K_COUNT, DOLLAR_SIGN);
    p = r; // pin = 1
    r = r && VarName(b, l + 1);
    exit_section_(b, l, m, r, p, FLWORExprRecover_parser_);
    return r || p;
  }

  /* ********************************************************** */
  // "array" EnclosedExpression
  public static boolean CurlyArrayConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CurlyArrayConstructor")) return false;
    if (!nextTokenIs(b, K_ARRAY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CURLY_ARRAY_CONSTRUCTOR, null);
    r = consumeToken(b, K_ARRAY);
    p = r; // pin = 1
    r = r && EnclosedExpression(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // VarName
  public static boolean CurrentItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CurrentItem")) return false;
    if (!nextTokenIs(b, "<current item>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CURRENT_ITEM, "<current item>");
    r = VarName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "decimal-separator" | "grouping-separator" | "infinity" | "minus-sign" | "NaN" | "percent" | "per-mille" | "zero-digit" | "digit" | "pattern-separator" | "exponent-separator"
  static boolean DFPropertyName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DFPropertyName")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DECIMAL_SEPARATOR);
    if (!r) r = consumeToken(b, K_GROUPING_SEPARATOR);
    if (!r) r = consumeToken(b, K_INFINITY);
    if (!r) r = consumeToken(b, K_MINUS_SIGN);
    if (!r) r = consumeToken(b, K_NAN);
    if (!r) r = consumeToken(b, K_PERCENT);
    if (!r) r = consumeToken(b, K_PER_MILLE);
    if (!r) r = consumeToken(b, K_ZERO_DIGIT);
    if (!r) r = consumeToken(b, K_DIGIT);
    if (!r) r = consumeToken(b, K_PATTERN_SEPARATOR);
    if (!r) r = consumeToken(b, K_EXPONENT_SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "declare" (("decimal-format" EQName) | ("default" "decimal-format")) (DFPropertyName "=" StringLiteral)* Separator
  public static boolean DecimalFormatDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DecimalFormatDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DECIMAL_FORMAT_DECL, null);
    r = consumeToken(b, K_DECLARE);
    r = r && DecimalFormatDecl_1(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, DecimalFormatDecl_2(b, l + 1));
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ("decimal-format" EQName) | ("default" "decimal-format")
  private static boolean DecimalFormatDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DecimalFormatDecl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DecimalFormatDecl_1_0(b, l + 1);
    if (!r) r = DecimalFormatDecl_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "decimal-format" EQName
  private static boolean DecimalFormatDecl_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DecimalFormatDecl_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DECIMAL_FORMAT);
    r = r && EQName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "default" "decimal-format"
  private static boolean DecimalFormatDecl_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DecimalFormatDecl_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_DEFAULT, K_DECIMAL_FORMAT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (DFPropertyName "=" StringLiteral)*
  private static boolean DecimalFormatDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DecimalFormatDecl_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!DecimalFormatDecl_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DecimalFormatDecl_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // DFPropertyName "=" StringLiteral
  private static boolean DecimalFormatDecl_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DecimalFormatDecl_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DFPropertyName(b, l + 1);
    r = r && consumeToken(b, EQUAL);
    r = r && StringLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "declare" "default" "collation" URILiteral Separator
  public static boolean DefaultCollationDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultCollationDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DEFAULT_COLLATION_DECL, null);
    r = consumeTokens(b, 3, K_DECLARE, K_DEFAULT, K_COLLATION);
    p = r; // pin = 3
    r = r && report_error_(b, URILiteral(b, l + 1));
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "declare" "default" "element" "namespace" URILiteral
  public static boolean DefaultElementNamespaceDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultElementNamespaceDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DEFAULT_ELEMENT_NAMESPACE_DECL, null);
    r = consumeTokens(b, 3, K_DECLARE, K_DEFAULT, K_ELEMENT, K_NAMESPACE);
    p = r; // pin = 3
    r = r && URILiteral(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "declare" "default" "function" "namespace" URILiteral
  public static boolean DefaultFunctionNamespaceDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultFunctionNamespaceDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DEFAULT_FUNCTION_NAMESPACE_DECL, null);
    r = consumeTokens(b, 3, K_DECLARE, K_DEFAULT, K_FUNCTION, K_NAMESPACE);
    p = r; // pin = 3
    r = r && URILiteral(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // (DefaultFunctionNamespaceDecl | DefaultElementNamespaceDecl) Separator
  static boolean DefaultNamespaceDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultNamespaceDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DefaultNamespaceDecl_0(b, l + 1);
    r = r && Separator(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DefaultFunctionNamespaceDecl | DefaultElementNamespaceDecl
  private static boolean DefaultNamespaceDecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultNamespaceDecl_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DefaultFunctionNamespaceDecl(b, l + 1);
    if (!r) r = DefaultElementNamespaceDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "delete" ("node" | "nodes") TargetExpr
  public static boolean DeleteExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DeleteExpr")) return false;
    if (!nextTokenIs(b, K_DELETE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DELETE);
    r = r && DeleteExpr_1(b, l + 1);
    r = r && TargetExpr(b, l + 1);
    exit_section_(b, m, DELETE_EXPR, r);
    return r;
  }

  // "node" | "nodes"
  private static boolean DeleteExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DeleteExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_NODE);
    if (!r) r = consumeToken(b, K_NODES);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DirAttributeName AttrEqual DirAttributeValue
  public static boolean DirAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttribute")) return false;
    if (!nextTokenIs(b, ATTRNCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DirAttributeName(b, l + 1);
    r = r && consumeToken(b, ATTREQUAL);
    r = r && DirAttributeValue(b, l + 1);
    exit_section_(b, m, DIR_ATTRIBUTE, r);
    return r;
  }

  /* ********************************************************** */
  // DirAttribute*
  public static boolean DirAttributeList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeList")) return false;
    Marker m = enter_section_(b, l, _NONE_, DIR_ATTRIBUTE_LIST, "<dir attribute list>");
    int c = current_position_(b);
    while (true) {
      if (!DirAttribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DirAttributeList", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // AttrNamespace AttrColon AttrLocalName | AttrLocalName
  public static boolean DirAttributeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeName")) return false;
    if (!nextTokenIs(b, ATTRNCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DirAttributeName_0(b, l + 1);
    if (!r) r = AttrLocalName(b, l + 1);
    exit_section_(b, m, DIR_ATTRIBUTE_NAME, r);
    return r;
  }

  // AttrNamespace AttrColon AttrLocalName
  private static boolean DirAttributeName_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeName_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AttrNamespace(b, l + 1);
    r = r && consumeToken(b, ATTRCOLON);
    r = r && AttrLocalName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (OpeningQuot (EscapeQuot | QuotAttrValueContent)* ClosingQuot)
  //  | (OpeningApos (EscapeApos | AposAttrValueContent)* ClosingApos)
  public static boolean DirAttributeValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeValue")) return false;
    if (!nextTokenIs(b, "<dir attribute value>", OPENINGAPOS, OPENINGQUOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIR_ATTRIBUTE_VALUE, "<dir attribute value>");
    r = DirAttributeValue_0(b, l + 1);
    if (!r) r = DirAttributeValue_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OpeningQuot (EscapeQuot | QuotAttrValueContent)* ClosingQuot
  private static boolean DirAttributeValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeValue_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPENINGQUOT);
    r = r && DirAttributeValue_0_1(b, l + 1);
    r = r && consumeToken(b, CLOSINGQUOT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EscapeQuot | QuotAttrValueContent)*
  private static boolean DirAttributeValue_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeValue_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!DirAttributeValue_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DirAttributeValue_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // EscapeQuot | QuotAttrValueContent
  private static boolean DirAttributeValue_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeValue_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EscapeQuot(b, l + 1);
    if (!r) r = QuotAttrValueContent(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OpeningApos (EscapeApos | AposAttrValueContent)* ClosingApos
  private static boolean DirAttributeValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPENINGAPOS);
    r = r && DirAttributeValue_1_1(b, l + 1);
    r = r && consumeToken(b, CLOSINGAPOS);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EscapeApos | AposAttrValueContent)*
  private static boolean DirAttributeValue_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeValue_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!DirAttributeValue_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DirAttributeValue_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // EscapeApos | AposAttrValueContent
  private static boolean DirAttributeValue_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirAttributeValue_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EscapeApos(b, l + 1);
    if (!r) r = AposAttrValueContent(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "<!--" DirCommentContents "-->"
  public static boolean DirCommentConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirCommentConstructor")) return false;
    if (!nextTokenIs(b, DIR_COMMENT_BEGIN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DIR_COMMENT_BEGIN);
    r = r && DirCommentContents(b, l + 1);
    r = r && consumeToken(b, DIR_COMMENT_END);
    exit_section_(b, m, DIR_COMMENT_CONSTRUCTOR, r);
    return r;
  }

  /* ********************************************************** */
  // DirCommentChar*
  public static boolean DirCommentContents(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirCommentContents")) return false;
    Marker m = enter_section_(b, l, _NONE_, DIR_COMMENT_CONTENTS, "<dir comment contents>");
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, DIRCOMMENTCHAR)) break;
      if (!empty_element_parsed_guard_(b, "DirCommentContents", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // XmlEmptyTag | XmlFullTag
  static boolean DirElemConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirElemConstructor")) return false;
    if (!nextTokenIs(b, XMLSTARTTAGSTART)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = XmlEmptyTag(b, l + 1);
    if (!r) r = XmlFullTag(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DirectConstructor
  //  | CDataSection
  //  | CommonContent
  //  | ElementContentChar
  public static boolean DirElemContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirElemContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIR_ELEM_CONTENT, "<dir elem content>");
    r = DirectConstructor(b, l + 1);
    if (!r) r = CDataSection(b, l + 1);
    if (!r) r = CommonContent(b, l + 1);
    if (!r) r = consumeToken(b, ELEMENTCONTENTCHAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "<?" PITarget (S DirPIContents)? "?>"
  public static boolean DirPIConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirPIConstructor")) return false;
    if (!nextTokenIs(b, PI_BEGIN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PI_BEGIN, PITARGET);
    r = r && DirPIConstructor_2(b, l + 1);
    r = r && consumeToken(b, PI_END);
    exit_section_(b, m, DIR_PI_CONSTRUCTOR, r);
    return r;
  }

  // (S DirPIContents)?
  private static boolean DirPIConstructor_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirPIConstructor_2")) return false;
    DirPIConstructor_2_0(b, l + 1);
    return true;
  }

  // S DirPIContents
  private static boolean DirPIConstructor_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirPIConstructor_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, S);
    r = r && DirPIContents(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DirPIContentChar*
  public static boolean DirPIContents(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirPIContents")) return false;
    Marker m = enter_section_(b, l, _NONE_, DIR_PI_CONTENTS, "<dir pi contents>");
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, DIRPICONTENTCHAR)) break;
      if (!empty_element_parsed_guard_(b, "DirPIContents", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // DirElemConstructor
  //  | DirCommentConstructor
  //  | DirPIConstructor
  public static boolean DirectConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectConstructor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECT_CONSTRUCTOR, "<direct constructor>");
    r = DirElemConstructor(b, l + 1);
    if (!r) r = DirCommentConstructor(b, l + 1);
    if (!r) r = DirPIConstructor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "document-node" "(" (ElementTest | SchemaElementTest)? ")"
  public static boolean DocumentTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DocumentTest")) return false;
    if (!nextTokenIs(b, K_DOCUMENT_NODE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DOCUMENT_TEST, null);
    r = consumeTokens(b, 2, K_DOCUMENT_NODE, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, DocumentTest_2(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (ElementTest | SchemaElementTest)?
  private static boolean DocumentTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DocumentTest_2")) return false;
    DocumentTest_2_0(b, l + 1);
    return true;
  }

  // ElementTest | SchemaElementTest
  private static boolean DocumentTest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DocumentTest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ElementTest(b, l + 1);
    if (!r) r = SchemaElementTest(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // QName | URIQualifiedName
  static boolean EQName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EQName")) return false;
    if (!nextTokenIs(b, "", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = QName(b, l + 1);
    if (!r) r = consumeToken(b, URIQUALIFIEDNAME);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ElementName
  public static boolean ElementDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementDeclaration")) return false;
    if (!nextTokenIs(b, "<element declaration>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ELEMENT_DECLARATION, "<element declaration>");
    r = ElementName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // EQName
  public static boolean ElementName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementName")) return false;
    if (!nextTokenIs(b, "<element name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ELEMENT_NAME, "<element name>");
    r = EQName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ElementName | "*"
  public static boolean ElementNameOrWildcard(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementNameOrWildcard")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ELEMENT_NAME_OR_WILDCARD, "<element name or wildcard>");
    r = ElementName(b, l + 1);
    if (!r) r = consumeToken(b, STAR_SIGN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "element" "(" (ElementNameOrWildcard ("," TypeName "?"?)?)? ")"
  public static boolean ElementTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementTest")) return false;
    if (!nextTokenIs(b, K_ELEMENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ELEMENT_TEST, null);
    r = consumeTokens(b, 2, K_ELEMENT, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, ElementTest_2(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (ElementNameOrWildcard ("," TypeName "?"?)?)?
  private static boolean ElementTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementTest_2")) return false;
    ElementTest_2_0(b, l + 1);
    return true;
  }

  // ElementNameOrWildcard ("," TypeName "?"?)?
  private static boolean ElementTest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementTest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ElementNameOrWildcard(b, l + 1);
    r = r && ElementTest_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," TypeName "?"?)?
  private static boolean ElementTest_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementTest_2_0_1")) return false;
    ElementTest_2_0_1_0(b, l + 1);
    return true;
  }

  // "," TypeName "?"?
  private static boolean ElementTest_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementTest_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && TypeName(b, l + 1);
    r = r && ElementTest_2_0_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "?"?
  private static boolean ElementTest_2_0_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElementTest_2_0_1_0_2")) return false;
    consumeToken(b, QUESTIONMARK);
    return true;
  }

  /* ********************************************************** */
  // "declare" "default" "order" "empty" ("greatest" | "least") Separator
  public static boolean EmptyOrderDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EmptyOrderDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EMPTY_ORDER_DECL, null);
    r = consumeTokens(b, 3, K_DECLARE, K_DEFAULT, K_ORDER, K_EMPTY);
    p = r; // pin = 3
    r = r && report_error_(b, EmptyOrderDecl_4(b, l + 1));
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // "greatest" | "least"
  private static boolean EmptyOrderDecl_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EmptyOrderDecl_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_GREATEST);
    if (!r) r = consumeToken(b, K_LEAST);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EnclosedExpression
  public static boolean EnclosedContentExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnclosedContentExpression")) return false;
    if (!nextTokenIs(b, L_C_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EnclosedExpression(b, l + 1);
    exit_section_(b, m, ENCLOSED_CONTENT_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // "{" Expr? "}"
  public static boolean EnclosedExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnclosedExpression")) return false;
    if (!nextTokenIs(b, L_C_BRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENCLOSED_EXPRESSION, null);
    r = consumeToken(b, L_C_BRACE);
    p = r; // pin = 1
    r = r && report_error_(b, EnclosedExpression_1(b, l + 1));
    r = p && consumeToken(b, R_C_BRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Expr?
  private static boolean EnclosedExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnclosedExpression_1")) return false;
    Expr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // EnclosedExpression
  public static boolean EnclosedPrefixExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnclosedPrefixExpression")) return false;
    if (!nextTokenIs(b, L_C_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EnclosedExpression(b, l + 1);
    exit_section_(b, m, ENCLOSED_PREFIX_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // EnclosedExpression
  static boolean EnclosedTryTargetExpression(PsiBuilder b, int l) {
    return EnclosedExpression(b, l + 1);
  }

  /* ********************************************************** */
  // EnclosedExpression
  public static boolean EnclosedURIExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnclosedURIExpression")) return false;
    if (!nextTokenIs(b, L_C_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EnclosedExpression(b, l + 1);
    exit_section_(b, m, ENCLOSED_URI_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // "=" | "!="
  public static boolean EqualityComp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EqualityComp")) return false;
    if (!nextTokenIs(b, "<equality comp>", NOT_EQUAL, EQUAL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EQUALITY_COMP, "<equality comp>");
    r = consumeToken(b, EQUAL);
    if (!r) r = consumeToken(b, NOT_EQUAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "''"
  public static boolean EscapeApos(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EscapeApos")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ESCAPE_APOS, "<escape apos>");
    r = consumeToken(b, "''");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "\"\""
  public static boolean EscapeQuot(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EscapeQuot")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ESCAPE_QUOT, "<escape quot>");
    r = consumeToken(b, "\"\"");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle ExprSingleAfterComma*
  public static boolean Expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expr")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXPR, "<expr>");
    r = ExprSingle(b, l + 1);
    p = r; // pin = 1
    r = r && Expr_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ExprSingleAfterComma*
  private static boolean Expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ExprSingleAfterComma(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // FLWORExpr
  //  | QuantifiedExpr
  //  | SwitchExpr
  //  | TypeswitchExpr
  //  | IfExpr
  //  | TryCatchExpr
  //  | InsertExpr
  //  | DeleteExpr
  //  | RenameExpr
  //  | ReplaceExpr
  //  | TransformExpr
  //  | OrExpr
  public static boolean ExprSingle(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExprSingle")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, EXPR_SINGLE, "<expr single>");
    r = FLWORExpr(b, l + 1);
    if (!r) r = QuantifiedExpr(b, l + 1);
    if (!r) r = SwitchExpr(b, l + 1);
    if (!r) r = TypeswitchExpr(b, l + 1);
    if (!r) r = IfExpr(b, l + 1);
    if (!r) r = TryCatchExpr(b, l + 1);
    if (!r) r = InsertExpr(b, l + 1);
    if (!r) r = DeleteExpr(b, l + 1);
    if (!r) r = RenameExpr(b, l + 1);
    if (!r) r = ReplaceExpr(b, l + 1);
    if (!r) r = TransformExpr(b, l + 1);
    if (!r) r = OrExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "," ExprSingle
  static boolean ExprSingleAfterComma(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExprSingleAfterComma")) return false;
    if (!nextTokenIs(b, COMMA)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Pragma+ "{" Expr? "}"
  public static boolean ExtensionExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtensionExpr")) return false;
    if (!nextTokenIs(b, PRAGMA_BEGIN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXTENSION_EXPR, null);
    r = ExtensionExpr_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, L_C_BRACE));
    r = p && report_error_(b, ExtensionExpr_2(b, l + 1)) && r;
    r = p && consumeToken(b, R_C_BRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Pragma+
  private static boolean ExtensionExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtensionExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Pragma(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!Pragma(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ExtensionExpr_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr?
  private static boolean ExtensionExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExtensionExpr_2")) return false;
    Expr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "external" (":=" VarDefaultValue)?
  public static boolean ExternalVarPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalVarPart")) return false;
    if (!nextTokenIs(b, K_EXTERNAL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXTERNAL_VAR_PART, null);
    r = consumeToken(b, K_EXTERNAL);
    p = r; // pin = 1
    r = r && ExternalVarPart_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (":=" VarDefaultValue)?
  private static boolean ExternalVarPart_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalVarPart_1")) return false;
    ExternalVarPart_1_0(b, l + 1);
    return true;
  }

  // ":=" VarDefaultValue
  private static boolean ExternalVarPart_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExternalVarPart_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_ASSIGN);
    r = r && VarDefaultValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // InitialClause IntermediateClause* ReturnClause
  public static boolean FLWORExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FLWORExpr")) return false;
    if (!nextTokenIs(b, "<flwor expr>", K_FOR, K_LET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FLWOR_EXPR, "<flwor expr>");
    r = InitialClause(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, FLWORExpr_1(b, l + 1));
    r = p && ReturnClause(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // IntermediateClause*
  private static boolean FLWORExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FLWORExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!IntermediateClause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FLWORExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // !('count' | 'for' | 'group' | 'let' | 'order' | 'return' | 'stable' | 'where' | '}' | XmlEndTagStart TagName | XmlStartTagStart TagName | ',')
  static boolean FLWORExprRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FLWORExprRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !FLWORExprRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // 'count' | 'for' | 'group' | 'let' | 'order' | 'return' | 'stable' | 'where' | '}' | XmlEndTagStart TagName | XmlStartTagStart TagName | ','
  private static boolean FLWORExprRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FLWORExprRecover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_COUNT);
    if (!r) r = consumeToken(b, K_FOR);
    if (!r) r = consumeToken(b, K_GROUP);
    if (!r) r = consumeToken(b, K_LET);
    if (!r) r = consumeToken(b, K_ORDER);
    if (!r) r = consumeToken(b, K_RETURN);
    if (!r) r = consumeToken(b, K_STABLE);
    if (!r) r = consumeToken(b, K_WHERE);
    if (!r) r = consumeToken(b, R_C_BRACE);
    if (!r) r = parseTokens(b, 0, XMLENDTAGSTART, TAGNAME);
    if (!r) r = parseTokens(b, 0, XMLSTARTTAGSTART, TAGNAME);
    if (!r) r = consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DefaultNamespaceDecl | Setter | NamespaceDecl | Import
  static boolean FirstDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FirstDecl")) return false;
    if (!nextTokenIs(b, "", K_DECLARE, K_IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DefaultNamespaceDecl(b, l + 1);
    if (!r) r = Setter(b, l + 1);
    if (!r) r = NamespaceDecl(b, l + 1);
    if (!r) r = Import(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "$" ForBindingDetails
  public static boolean ForBinding(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForBinding")) return false;
    if (!nextTokenIs(b, DOLLAR_SIGN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FOR_BINDING, null);
    r = consumeToken(b, DOLLAR_SIGN);
    p = r; // pin = 1
    r = r && ForBindingDetails(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // VarName TypeDeclaration? AllowingEmpty? PositionalVar? "in" ExprSingle
  static boolean ForBindingDetails(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForBindingDetails")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = VarName(b, l + 1);
    r = r && ForBindingDetails_1(b, l + 1);
    r = r && ForBindingDetails_2(b, l + 1);
    r = r && ForBindingDetails_3(b, l + 1);
    r = r && consumeToken(b, K_IN);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, FLWORExprRecover_parser_);
    return r;
  }

  // TypeDeclaration?
  private static boolean ForBindingDetails_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForBindingDetails_1")) return false;
    TypeDeclaration(b, l + 1);
    return true;
  }

  // AllowingEmpty?
  private static boolean ForBindingDetails_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForBindingDetails_2")) return false;
    AllowingEmpty(b, l + 1);
    return true;
  }

  // PositionalVar?
  private static boolean ForBindingDetails_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForBindingDetails_3")) return false;
    PositionalVar(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "for" ForBinding ("," ForBinding)*
  public static boolean ForClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForClause")) return false;
    if (!nextTokenIs(b, K_FOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FOR_CLAUSE, null);
    r = consumeToken(b, K_FOR);
    r = r && ForBinding(b, l + 1);
    p = r; // pin = 2
    r = r && ForClause_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ("," ForBinding)*
  private static boolean ForClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForClause_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ForClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ForClause_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," ForBinding
  private static boolean ForClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ForBinding(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ("child" "::")
  //  | ("descendant" "::")
  //  | ("attribute" "::")
  //  | ("self" "::")
  //  | ("descendant-or-self" "::")
  //  | ("following-sibling" "::")
  //  | ("following" "::")
  static boolean ForwardAxis(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForwardAxis")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForwardAxis_0(b, l + 1);
    if (!r) r = ForwardAxis_1(b, l + 1);
    if (!r) r = ForwardAxis_2(b, l + 1);
    if (!r) r = ForwardAxis_3(b, l + 1);
    if (!r) r = ForwardAxis_4(b, l + 1);
    if (!r) r = ForwardAxis_5(b, l + 1);
    if (!r) r = ForwardAxis_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "child" "::"
  private static boolean ForwardAxis_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForwardAxis_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_CHILD, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "descendant" "::"
  private static boolean ForwardAxis_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForwardAxis_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_DESCENDANT, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "attribute" "::"
  private static boolean ForwardAxis_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForwardAxis_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ATTRIBUTE, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "self" "::"
  private static boolean ForwardAxis_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForwardAxis_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_SELF, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "descendant-or-self" "::"
  private static boolean ForwardAxis_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForwardAxis_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_DESCENDANT_OR_SELF, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "following-sibling" "::"
  private static boolean ForwardAxis_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForwardAxis_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_FOLLOWING_SIBLING, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "following" "::"
  private static boolean ForwardAxis_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForwardAxis_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_FOLLOWING, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FullForwardStep | AbbrevForwardStep
  public static boolean ForwardStep(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForwardStep")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FORWARD_STEP, "<forward step>");
    r = FullForwardStep(b, l + 1);
    if (!r) r = AbbrevForwardStep(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ForwardAxis NodeTest
  static boolean FullForwardStep(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FullForwardStep")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ForwardAxis(b, l + 1);
    p = r; // pin = 1
    r = r && NodeTest(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ReverseAxis NodeTest
  static boolean FullReverseStep(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FullReverseStep")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ReverseAxis(b, l + 1);
    p = r; // pin = 1
    r = r && NodeTest(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // IntegerLiteral
  public static boolean FunctionArity(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionArity")) return false;
    if (!nextTokenIs(b, INTEGERLITERAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INTEGERLITERAL);
    exit_section_(b, m, FUNCTION_ARITY, r);
    return r;
  }

  /* ********************************************************** */
  // EnclosedExpression
  public static boolean FunctionBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionBody")) return false;
    if (!nextTokenIs(b, L_C_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EnclosedExpression(b, l + 1);
    exit_section_(b, m, FUNCTION_BODY, r);
    return r;
  }

  /* ********************************************************** */
  // FunctionName ArgumentList
  public static boolean FunctionCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall")) return false;
    if (!nextTokenIs(b, "<function call>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_CALL, "<function call>");
    r = FunctionName(b, l + 1);
    r = r && ArgumentList(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "declare" (MarklogicAnnotation | CompatibilityAnnotation | Annotation)* "function" FunctionName ParamList ("as" SequenceType)? (FunctionBody | "external") Separator
  public static boolean FunctionDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DECL, null);
    r = consumeToken(b, K_DECLARE);
    r = r && FunctionDecl_1(b, l + 1);
    r = r && consumeToken(b, K_FUNCTION);
    p = r; // pin = 3
    r = r && report_error_(b, FunctionName(b, l + 1));
    r = p && report_error_(b, ParamList(b, l + 1)) && r;
    r = p && report_error_(b, FunctionDecl_5(b, l + 1)) && r;
    r = p && report_error_(b, FunctionDecl_6(b, l + 1)) && r;
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (MarklogicAnnotation | CompatibilityAnnotation | Annotation)*
  private static boolean FunctionDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!FunctionDecl_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionDecl_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // MarklogicAnnotation | CompatibilityAnnotation | Annotation
  private static boolean FunctionDecl_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MarklogicAnnotation(b, l + 1);
    if (!r) r = CompatibilityAnnotation(b, l + 1);
    if (!r) r = Annotation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("as" SequenceType)?
  private static boolean FunctionDecl_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_5")) return false;
    FunctionDecl_5_0(b, l + 1);
    return true;
  }

  // "as" SequenceType
  private static boolean FunctionDecl_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AS);
    r = r && SequenceType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FunctionBody | "external"
  private static boolean FunctionDecl_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionBody(b, l + 1);
    if (!r) r = consumeToken(b, K_EXTERNAL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NamedFunctionRef | InlineFunctionExpr
  public static boolean FunctionItemExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionItemExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, FUNCTION_ITEM_EXPR, "<function item expr>");
    r = NamedFunctionRef(b, l + 1);
    if (!r) r = InlineFunctionExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NCName
  public static boolean FunctionLocalName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionLocalName")) return false;
    if (!nextTokenIs(b, NCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NCNAME);
    exit_section_(b, m, FUNCTION_LOCAL_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // Prefix ':' FunctionLocalName | FunctionLocalName | URIQualifiedName
  public static boolean FunctionName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionName")) return false;
    if (!nextTokenIs(b, "<function name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_NAME, "<function name>");
    r = FunctionName_0(b, l + 1);
    if (!r) r = FunctionLocalName(b, l + 1);
    if (!r) r = consumeToken(b, URIQUALIFIEDNAME);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Prefix ':' FunctionLocalName
  private static boolean FunctionName_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionName_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = Prefix(b, l + 1);
    r = r && consumeToken(b, COLON);
    p = r; // pin = 2
    r = r && FunctionLocalName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Annotation* (AnyFunctionTest | TypedFunctionTest)
  public static boolean FunctionTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionTest")) return false;
    if (!nextTokenIs(b, "<function test>", PERCENT, K_FUNCTION)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_TEST, "<function test>");
    r = FunctionTest_0(b, l + 1);
    r = r && FunctionTest_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Annotation*
  private static boolean FunctionTest_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionTest_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionTest_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // AnyFunctionTest | TypedFunctionTest
  private static boolean FunctionTest_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionTest_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AnyFunctionTest(b, l + 1);
    if (!r) r = TypedFunctionTest(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EqualityComp  | RelationalComp
  static boolean GeneralComp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GeneralComp")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EqualityComp(b, l + 1);
    if (!r) r = RelationalComp(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "item" "(" ")"
  public static boolean GeneralItemType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GeneralItemType")) return false;
    if (!nextTokenIs(b, K_ITEM)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, GENERAL_ITEM_TYPE, null);
    r = consumeTokens(b, 2, K_ITEM, L_PAR, R_PAR);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "group" "by" GroupingSpecList
  public static boolean GroupByClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupByClause")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, GROUP_BY_CLAUSE, "<group by clause>");
    r = consumeTokens(b, 2, K_GROUP, K_BY);
    p = r; // pin = 2
    r = r && GroupingSpecList(b, l + 1);
    exit_section_(b, l, m, r, p, FLWORExprRecover_parser_);
    return r || p;
  }

  /* ********************************************************** */
  // GroupingVariable (TypeDeclaration? ":=" ExprSingle)? ("collation" URILiteral)?
  public static boolean GroupingSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingSpec")) return false;
    if (!nextTokenIs(b, DOLLAR_SIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = GroupingVariable(b, l + 1);
    r = r && GroupingSpec_1(b, l + 1);
    r = r && GroupingSpec_2(b, l + 1);
    exit_section_(b, m, GROUPING_SPEC, r);
    return r;
  }

  // (TypeDeclaration? ":=" ExprSingle)?
  private static boolean GroupingSpec_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingSpec_1")) return false;
    GroupingSpec_1_0(b, l + 1);
    return true;
  }

  // TypeDeclaration? ":=" ExprSingle
  private static boolean GroupingSpec_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingSpec_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = GroupingSpec_1_0_0(b, l + 1);
    r = r && consumeToken(b, OP_ASSIGN);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TypeDeclaration?
  private static boolean GroupingSpec_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingSpec_1_0_0")) return false;
    TypeDeclaration(b, l + 1);
    return true;
  }

  // ("collation" URILiteral)?
  private static boolean GroupingSpec_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingSpec_2")) return false;
    GroupingSpec_2_0(b, l + 1);
    return true;
  }

  // "collation" URILiteral
  private static boolean GroupingSpec_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingSpec_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_COLLATION);
    r = r && URILiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // GroupingSpec ("," GroupingSpec)*
  public static boolean GroupingSpecList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingSpecList")) return false;
    if (!nextTokenIs(b, DOLLAR_SIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = GroupingSpec(b, l + 1);
    r = r && GroupingSpecList_1(b, l + 1);
    exit_section_(b, m, GROUPING_SPEC_LIST, r);
    return r;
  }

  // ("," GroupingSpec)*
  private static boolean GroupingSpecList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingSpecList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!GroupingSpecList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "GroupingSpecList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," GroupingSpec
  private static boolean GroupingSpecList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingSpecList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && GroupingSpec(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "$" VarName
  public static boolean GroupingVariable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupingVariable")) return false;
    if (!nextTokenIs(b, DOLLAR_SIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    exit_section_(b, m, GROUPING_VARIABLE, r);
    return r;
  }

  /* ********************************************************** */
  // "if" "(" Expr ")" "then" ExprSingle "else" ExprSingle
  public static boolean IfExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfExpr")) return false;
    if (!nextTokenIs(b, K_IF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IF_EXPR, null);
    r = consumeTokens(b, 1, K_IF, L_PAR);
    p = r; // pin = 1
    r = r && report_error_(b, Expr(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, R_PAR, K_THEN)) && r;
    r = p && report_error_(b, ExprSingle(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, K_ELSE)) && r;
    r = p && ExprSingle(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // SchemaImport | ModuleImport
  static boolean Import(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Import")) return false;
    if (!nextTokenIs(b, K_IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SchemaImport(b, l + 1);
    if (!r) r = ModuleImport(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "inherit" | "no-inherit"
  public static boolean InheritMode(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InheritMode")) return false;
    if (!nextTokenIs(b, "<inherit mode>", K_INHERIT, K_NO_INHERIT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INHERIT_MODE, "<inherit mode>");
    r = consumeToken(b, K_INHERIT);
    if (!r) r = consumeToken(b, K_NO_INHERIT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ForClause | WindowClause | LetClause
  static boolean InitialClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitialClause")) return false;
    if (!nextTokenIs(b, "", K_FOR, K_LET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForClause(b, l + 1);
    if (!r) r = WindowClause(b, l + 1);
    if (!r) r = LetClause(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Annotation* "function" ParamList ("as" SequenceType)? FunctionBody
  public static boolean InlineFunctionExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineFunctionExpr")) return false;
    if (!nextTokenIs(b, "<inline function expr>", PERCENT, K_FUNCTION)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INLINE_FUNCTION_EXPR, "<inline function expr>");
    r = InlineFunctionExpr_0(b, l + 1);
    r = r && consumeToken(b, K_FUNCTION);
    r = r && ParamList(b, l + 1);
    r = r && InlineFunctionExpr_3(b, l + 1);
    r = r && FunctionBody(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Annotation*
  private static boolean InlineFunctionExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineFunctionExpr_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InlineFunctionExpr_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ("as" SequenceType)?
  private static boolean InlineFunctionExpr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineFunctionExpr_3")) return false;
    InlineFunctionExpr_3_0(b, l + 1);
    return true;
  }

  // "as" SequenceType
  private static boolean InlineFunctionExpr_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineFunctionExpr_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AS);
    r = r && SequenceType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "insert" ("node" | "nodes") SourceExpr InsertExprTargetChoice TargetExpr
  public static boolean InsertExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertExpr")) return false;
    if (!nextTokenIs(b, K_INSERT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_INSERT);
    r = r && InsertExpr_1(b, l + 1);
    r = r && SourceExpr(b, l + 1);
    r = r && InsertExprTargetChoice(b, l + 1);
    r = r && TargetExpr(b, l + 1);
    exit_section_(b, m, INSERT_EXPR, r);
    return r;
  }

  // "node" | "nodes"
  private static boolean InsertExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_NODE);
    if (!r) r = consumeToken(b, K_NODES);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (("as" ("first" | "last"))? "into")
  //  | "after"
  //  | "before"
  public static boolean InsertExprTargetChoice(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertExprTargetChoice")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSERT_EXPR_TARGET_CHOICE, "<insert expr target choice>");
    r = InsertExprTargetChoice_0(b, l + 1);
    if (!r) r = consumeToken(b, K_AFTER);
    if (!r) r = consumeToken(b, K_BEFORE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ("as" ("first" | "last"))? "into"
  private static boolean InsertExprTargetChoice_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertExprTargetChoice_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InsertExprTargetChoice_0_0(b, l + 1);
    r = r && consumeToken(b, K_INTO);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("as" ("first" | "last"))?
  private static boolean InsertExprTargetChoice_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertExprTargetChoice_0_0")) return false;
    InsertExprTargetChoice_0_0_0(b, l + 1);
    return true;
  }

  // "as" ("first" | "last")
  private static boolean InsertExprTargetChoice_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertExprTargetChoice_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AS);
    r = r && InsertExprTargetChoice_0_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "first" | "last"
  private static boolean InsertExprTargetChoice_0_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InsertExprTargetChoice_0_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_FIRST);
    if (!r) r = consumeToken(b, K_LAST);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "instance" "of"
  public static boolean InstanceOfOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceOfOperator")) return false;
    if (!nextTokenIs(b, K_INSTANCE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_INSTANCE, K_OF);
    exit_section_(b, m, INSTANCE_OF_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // InstanceOfOperator SequenceType
  static boolean InstanceOfOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceOfOptionalExpr")) return false;
    if (!nextTokenIs(b, K_INSTANCE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = InstanceOfOperator(b, l + 1);
    p = r; // pin = 1
    r = r && SequenceType(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // TreatExpr InstanceOfOptionalExpr?
  public static boolean InstanceofExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceofExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, INSTANCEOF_EXPR, "<instanceof expr>");
    r = TreatExpr(b, l + 1);
    r = r && InstanceofExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // InstanceOfOptionalExpr?
  private static boolean InstanceofExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstanceofExpr_1")) return false;
    InstanceOfOptionalExpr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // InitialClause | WhereClause | GroupByClause | OrderByClause | CountClause
  static boolean IntermediateClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IntermediateClause")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InitialClause(b, l + 1);
    if (!r) r = WhereClause(b, l + 1);
    if (!r) r = GroupByClause(b, l + 1);
    if (!r) r = OrderByClause(b, l + 1);
    if (!r) r = CountClause(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // InstanceofExpr IntersectExceptOptionalExpr*
  public static boolean IntersectExceptExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IntersectExceptExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, INTERSECT_EXCEPT_EXPR, "<intersect except expr>");
    r = InstanceofExpr(b, l + 1);
    r = r && IntersectExceptExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IntersectExceptOptionalExpr*
  private static boolean IntersectExceptExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IntersectExceptExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!IntersectExceptOptionalExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IntersectExceptExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "intersect" | "except"
  public static boolean IntersectExceptOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IntersectExceptOperator")) return false;
    if (!nextTokenIs(b, "<intersect except operator>", K_EXCEPT, K_INTERSECT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INTERSECT_EXCEPT_OPERATOR, "<intersect except operator>");
    r = consumeToken(b, K_INTERSECT);
    if (!r) r = consumeToken(b, K_EXCEPT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IntersectExceptOperator InstanceofExpr
  static boolean IntersectExceptOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IntersectExceptOptionalExpr")) return false;
    if (!nextTokenIs(b, "", K_EXCEPT, K_INTERSECT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = IntersectExceptOperator(b, l + 1);
    p = r; // pin = 1
    r = r && InstanceofExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // KindTest | GeneralItemType | FunctionTest | MapTest | ArrayTest | AtomicOrUnionType | ParenthesizedItemType
  public static boolean ItemType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ItemType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ITEM_TYPE, "<item type>");
    r = KindTest(b, l + 1);
    if (!r) r = GeneralItemType(b, l + 1);
    if (!r) r = FunctionTest(b, l + 1);
    if (!r) r = MapTest(b, l + 1);
    if (!r) r = ArrayTest(b, l + 1);
    if (!r) r = AtomicOrUnionType(b, l + 1);
    if (!r) r = ParenthesizedItemType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NCName | IntegerLiteral | ParenthesizedExpr | "*"
  public static boolean KeySpecifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KeySpecifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KEY_SPECIFIER, "<key specifier>");
    r = consumeToken(b, NCNAME);
    if (!r) r = consumeToken(b, INTEGERLITERAL);
    if (!r) r = ParenthesizedExpr(b, l + 1);
    if (!r) r = consumeToken(b, STAR_SIGN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DocumentTest
  //  | ElementTest
  //  | AttributeTest
  //  | SchemaElementTest
  //  | SchemaAttributeTest
  //  | PITest
  //  | CommentTest
  //  | MarklogicTextTest
  //  | TextTest
  //  | NamespaceNodeTest
  //  | MarklogicAnyKindTest
  //  | AnyKindTest
  //  | MarklogicBinaryTest
  //  | MarklogicObjectNodeTest
  //  | MarklogicNumberNodeTest
  //  | MarklogicBooleanNodeTest
  //  | MarklogicNullNodeTest
  //  | MarklogicArrayNodeTest
  public static boolean KindTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KindTest")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KIND_TEST, "<kind test>");
    r = DocumentTest(b, l + 1);
    if (!r) r = ElementTest(b, l + 1);
    if (!r) r = AttributeTest(b, l + 1);
    if (!r) r = SchemaElementTest(b, l + 1);
    if (!r) r = SchemaAttributeTest(b, l + 1);
    if (!r) r = PITest(b, l + 1);
    if (!r) r = CommentTest(b, l + 1);
    if (!r) r = MarklogicTextTest(b, l + 1);
    if (!r) r = TextTest(b, l + 1);
    if (!r) r = NamespaceNodeTest(b, l + 1);
    if (!r) r = MarklogicAnyKindTest(b, l + 1);
    if (!r) r = AnyKindTest(b, l + 1);
    if (!r) r = MarklogicBinaryTest(b, l + 1);
    if (!r) r = MarklogicObjectNodeTest(b, l + 1);
    if (!r) r = MarklogicNumberNodeTest(b, l + 1);
    if (!r) r = MarklogicBooleanNodeTest(b, l + 1);
    if (!r) r = MarklogicNullNodeTest(b, l + 1);
    if (!r) r = MarklogicArrayNodeTest(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "$" VarName TypeDeclaration? ":=" ExprSingle
  public static boolean LetBinding(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetBinding")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LET_BINDING, "<let binding>");
    r = consumeToken(b, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    r = r && LetBinding_2(b, l + 1);
    r = r && consumeToken(b, OP_ASSIGN);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, FLWORExprRecover_parser_);
    return r;
  }

  // TypeDeclaration?
  private static boolean LetBinding_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetBinding_2")) return false;
    TypeDeclaration(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "let" LetBinding ("," LetBinding)*
  public static boolean LetClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetClause")) return false;
    if (!nextTokenIs(b, K_LET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LET_CLAUSE, null);
    r = consumeToken(b, K_LET);
    p = r; // pin = 1
    r = r && report_error_(b, LetBinding(b, l + 1));
    r = p && LetClause_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ("," LetBinding)*
  private static boolean LetClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetClause_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!LetClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "LetClause_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," LetBinding
  private static boolean LetClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && LetBinding(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ModuleDecl Prolog
  static boolean LibraryModule(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LibraryModule")) return false;
    if (!nextTokenIs(b, K_MODULE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ModuleDecl(b, l + 1);
    p = r; // pin = 1
    r = r && Prolog(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // NumericLiteral | StringLiteral
  public static boolean Literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL, "<literal>");
    r = NumericLiteral(b, l + 1);
    if (!r) r = StringLiteral(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NCName
  public static boolean LocalPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalPart")) return false;
    if (!nextTokenIs(b, NCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NCNAME);
    exit_section_(b, m, LOCAL_PART, r);
    return r;
  }

  /* ********************************************************** */
  // "?" KeySpecifier
  public static boolean Lookup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Lookup")) return false;
    if (!nextTokenIs(b, QUESTIONMARK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUESTIONMARK);
    r = r && KeySpecifier(b, l + 1);
    exit_section_(b, m, LOOKUP, r);
    return r;
  }

  /* ********************************************************** */
  // Prolog QueryBody
  static boolean MainModule(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MainModule")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = Prolog(b, l + 1);
    p = r; // pin = 1
    r = r && QueryBody(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "map" "{" (MapConstructorEntry ("," MapConstructorEntry)*)? SaxonMapEntriesSeparator? "}"
  public static boolean MapConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapConstructor")) return false;
    if (!nextTokenIs(b, K_MAP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MAP_CONSTRUCTOR, null);
    r = consumeTokens(b, 2, K_MAP, L_C_BRACE);
    p = r; // pin = 2
    r = r && report_error_(b, MapConstructor_2(b, l + 1));
    r = p && report_error_(b, MapConstructor_3(b, l + 1)) && r;
    r = p && consumeToken(b, R_C_BRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (MapConstructorEntry ("," MapConstructorEntry)*)?
  private static boolean MapConstructor_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapConstructor_2")) return false;
    MapConstructor_2_0(b, l + 1);
    return true;
  }

  // MapConstructorEntry ("," MapConstructorEntry)*
  private static boolean MapConstructor_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapConstructor_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MapConstructorEntry(b, l + 1);
    r = r && MapConstructor_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," MapConstructorEntry)*
  private static boolean MapConstructor_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapConstructor_2_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MapConstructor_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MapConstructor_2_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," MapConstructorEntry
  private static boolean MapConstructor_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapConstructor_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && MapConstructorEntry(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SaxonMapEntriesSeparator?
  private static boolean MapConstructor_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapConstructor_3")) return false;
    SaxonMapEntriesSeparator(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // MapKeyExpr (":"|SaxonMapEntrySeparator) MapValueExpr
  public static boolean MapConstructorEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapConstructorEntry")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MAP_CONSTRUCTOR_ENTRY, "<map constructor entry>");
    r = MapKeyExpr(b, l + 1);
    r = r && MapConstructorEntry_1(b, l + 1);
    p = r; // pin = 2
    r = r && MapValueExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ":"|SaxonMapEntrySeparator
  private static boolean MapConstructorEntry_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapConstructorEntry_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    if (!r) r = SaxonMapEntrySeparator(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean MapKeyExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapKeyExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MAP_KEY_EXPR, "<map key expr>");
    r = ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AnyMapTest | TypedMapTest
  public static boolean MapTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapTest")) return false;
    if (!nextTokenIs(b, K_MAP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AnyMapTest(b, l + 1);
    if (!r) r = TypedMapTest(b, l + 1);
    exit_section_(b, m, MAP_TEST, r);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean MapValueExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapValueExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MAP_VALUE_EXPR, "<map value expr>");
    r = ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "private"
  public static boolean MarklogicAnnotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicAnnotation")) return false;
    if (!nextTokenIs(b, K_PRIVATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_PRIVATE);
    exit_section_(b, m, MARKLOGIC_ANNOTATION, r);
    return r;
  }

  /* ********************************************************** */
  // "node" "(" (StringLiteralOrWildcard) ")"
  public static boolean MarklogicAnyKindTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicAnyKindTest")) return false;
    if (!nextTokenIs(b, K_NODE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_NODE, L_PAR);
    r = r && MarklogicAnyKindTest_2(b, l + 1);
    r = r && consumeToken(b, R_PAR);
    exit_section_(b, m, MARKLOGIC_ANY_KIND_TEST, r);
    return r;
  }

  // (StringLiteralOrWildcard)
  private static boolean MarklogicAnyKindTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicAnyKindTest_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteralOrWildcard(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "array-node" "(" (StringLiteralOrWildcard)? ")"
  public static boolean MarklogicArrayNodeTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicArrayNodeTest")) return false;
    if (!nextTokenIs(b, K_ARRAY_NODE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MARKLOGIC_ARRAY_NODE_TEST, null);
    r = consumeTokens(b, 2, K_ARRAY_NODE, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, MarklogicArrayNodeTest_2(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (StringLiteralOrWildcard)?
  private static boolean MarklogicArrayNodeTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicArrayNodeTest_2")) return false;
    MarklogicArrayNodeTest_2_0(b, l + 1);
    return true;
  }

  // (StringLiteralOrWildcard)
  private static boolean MarklogicArrayNodeTest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicArrayNodeTest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteralOrWildcard(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "binary" "(" ")"
  public static boolean MarklogicBinaryTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicBinaryTest")) return false;
    if (!nextTokenIs(b, K_BINARY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MARKLOGIC_BINARY_TEST, null);
    r = consumeTokens(b, 2, K_BINARY, L_PAR, R_PAR);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "boolean-node" "(" (StringLiteralOrWildcard)? ")"
  public static boolean MarklogicBooleanNodeTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicBooleanNodeTest")) return false;
    if (!nextTokenIs(b, K_BOOLEAN_NODE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MARKLOGIC_BOOLEAN_NODE_TEST, null);
    r = consumeTokens(b, 2, K_BOOLEAN_NODE, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, MarklogicBooleanNodeTest_2(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (StringLiteralOrWildcard)?
  private static boolean MarklogicBooleanNodeTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicBooleanNodeTest_2")) return false;
    MarklogicBooleanNodeTest_2_0(b, l + 1);
    return true;
  }

  // (StringLiteralOrWildcard)
  private static boolean MarklogicBooleanNodeTest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicBooleanNodeTest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteralOrWildcard(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "(" "$" VarName ")"
  public static boolean MarklogicCatchErrorList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicCatchErrorList")) return false;
    if (!nextTokenIs(b, L_PAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, L_PAR, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    r = r && consumeToken(b, R_PAR);
    exit_section_(b, m, MARKLOGIC_CATCH_ERROR_LIST, r);
    return r;
  }

  /* ********************************************************** */
  // "array-node" "{" Expr "}"
  public static boolean MarklogicCompArrayNodeConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicCompArrayNodeConstructor")) return false;
    if (!nextTokenIs(b, K_ARRAY_NODE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ARRAY_NODE, L_C_BRACE);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, R_C_BRACE);
    exit_section_(b, m, MARKLOGIC_COMP_ARRAY_NODE_CONSTRUCTOR, r);
    return r;
  }

  /* ********************************************************** */
  // "binary" "{" Expr "}"
  public static boolean MarklogicCompBinaryConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicCompBinaryConstructor")) return false;
    if (!nextTokenIs(b, K_BINARY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_BINARY, L_C_BRACE);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, R_C_BRACE);
    exit_section_(b, m, MARKLOGIC_COMP_BINARY_CONSTRUCTOR, r);
    return r;
  }

  /* ********************************************************** */
  // "boolean-node" "{" Expr "}"
  public static boolean MarklogicCompBooleanNodeConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicCompBooleanNodeConstructor")) return false;
    if (!nextTokenIs(b, K_BOOLEAN_NODE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_BOOLEAN_NODE, L_C_BRACE);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, R_C_BRACE);
    exit_section_(b, m, MARKLOGIC_COMP_BOOLEAN_NODE_CONSTRUCTOR, r);
    return r;
  }

  /* ********************************************************** */
  // "null-node" "{" "}"
  public static boolean MarklogicCompNullNodeConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicCompNullNodeConstructor")) return false;
    if (!nextTokenIs(b, K_NULL_NODE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_NULL_NODE, L_C_BRACE, R_C_BRACE);
    exit_section_(b, m, MARKLOGIC_COMP_NULL_NODE_CONSTRUCTOR, r);
    return r;
  }

  /* ********************************************************** */
  // "number-node" "{" Expr "}"
  public static boolean MarklogicCompNumberNodeConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicCompNumberNodeConstructor")) return false;
    if (!nextTokenIs(b, K_NUMBER_NODE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_NUMBER_NODE, L_C_BRACE);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, R_C_BRACE);
    exit_section_(b, m, MARKLOGIC_COMP_NUMBER_NODE_CONSTRUCTOR, r);
    return r;
  }

  /* ********************************************************** */
  // "object-node" "{" ObjectPropertyList? "}"
  public static boolean MarklogicCompObjectNodeConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicCompObjectNodeConstructor")) return false;
    if (!nextTokenIs(b, K_OBJECT_NODE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_OBJECT_NODE, L_C_BRACE);
    r = r && MarklogicCompObjectNodeConstructor_2(b, l + 1);
    r = r && consumeToken(b, R_C_BRACE);
    exit_section_(b, m, MARKLOGIC_COMP_OBJECT_NODE_CONSTRUCTOR, r);
    return r;
  }

  // ObjectPropertyList?
  private static boolean MarklogicCompObjectNodeConstructor_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicCompObjectNodeConstructor_2")) return false;
    ObjectPropertyList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "namespace" "::" NodeTest
  public static boolean MarklogicNamespaceAxis(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicNamespaceAxis")) return false;
    if (!nextTokenIs(b, K_NAMESPACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MARKLOGIC_NAMESPACE_AXIS, null);
    r = consumeTokens(b, 2, K_NAMESPACE, COLON_COLON);
    p = r; // pin = 2
    r = r && NodeTest(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "null-node" "(" (StringLiteralOrWildcard)? ")"
  public static boolean MarklogicNullNodeTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicNullNodeTest")) return false;
    if (!nextTokenIs(b, K_NULL_NODE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MARKLOGIC_NULL_NODE_TEST, null);
    r = consumeTokens(b, 2, K_NULL_NODE, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, MarklogicNullNodeTest_2(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (StringLiteralOrWildcard)?
  private static boolean MarklogicNullNodeTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicNullNodeTest_2")) return false;
    MarklogicNullNodeTest_2_0(b, l + 1);
    return true;
  }

  // (StringLiteralOrWildcard)
  private static boolean MarklogicNullNodeTest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicNullNodeTest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteralOrWildcard(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "number-node" "(" (StringLiteralOrWildcard)? ")"
  public static boolean MarklogicNumberNodeTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicNumberNodeTest")) return false;
    if (!nextTokenIs(b, K_NUMBER_NODE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MARKLOGIC_NUMBER_NODE_TEST, null);
    r = consumeTokens(b, 2, K_NUMBER_NODE, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, MarklogicNumberNodeTest_2(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (StringLiteralOrWildcard)?
  private static boolean MarklogicNumberNodeTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicNumberNodeTest_2")) return false;
    MarklogicNumberNodeTest_2_0(b, l + 1);
    return true;
  }

  // (StringLiteralOrWildcard)
  private static boolean MarklogicNumberNodeTest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicNumberNodeTest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteralOrWildcard(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "object-node" "(" (StringLiteralOrWildcard)? ")"
  public static boolean MarklogicObjectNodeTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicObjectNodeTest")) return false;
    if (!nextTokenIs(b, K_OBJECT_NODE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MARKLOGIC_OBJECT_NODE_TEST, null);
    r = consumeTokens(b, 2, K_OBJECT_NODE, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, MarklogicObjectNodeTest_2(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (StringLiteralOrWildcard)?
  private static boolean MarklogicObjectNodeTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicObjectNodeTest_2")) return false;
    MarklogicObjectNodeTest_2_0(b, l + 1);
    return true;
  }

  // (StringLiteralOrWildcard)
  private static boolean MarklogicObjectNodeTest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicObjectNodeTest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteralOrWildcard(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "text" "(" (StringLiteralOrWildcard) ")"
  public static boolean MarklogicTextTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicTextTest")) return false;
    if (!nextTokenIs(b, K_TEXT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_TEXT, L_PAR);
    r = r && MarklogicTextTest_2(b, l + 1);
    r = r && consumeToken(b, R_PAR);
    exit_section_(b, m, MARKLOGIC_TEXT_TEST, r);
    return r;
  }

  // (StringLiteralOrWildcard)
  private static boolean MarklogicTextTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicTextTest_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteralOrWildcard(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "as" TypeName
  public static boolean MarklogicValidation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MarklogicValidation")) return false;
    if (!nextTokenIs(b, K_AS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AS);
    r = r && TypeName(b, l + 1);
    exit_section_(b, m, MARKLOGIC_VALIDATION, r);
    return r;
  }

  /* ********************************************************** */
  // "(:" MisplacedCommentContent ":)"
  public static boolean MisplacedComment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MisplacedComment")) return false;
    if (!nextTokenIs(b, EXPR_COMMENT_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXPR_COMMENT_START);
    r = r && MisplacedCommentContent(b, l + 1);
    r = r && consumeToken(b, EXPR_COMMENT_END);
    exit_section_(b, m, MISPLACED_COMMENT, r);
    return r;
  }

  /* ********************************************************** */
  // (ExprCommentContent|MisplacedComment)*
  static boolean MisplacedCommentContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MisplacedCommentContent")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MisplacedCommentContent_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MisplacedCommentContent", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ExprCommentContent|MisplacedComment
  private static boolean MisplacedCommentContent_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MisplacedCommentContent_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXPRCOMMENTCONTENT);
    if (!r) r = MisplacedComment(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // VersionDecl? (LibraryModule | MainModule)
  static boolean Module(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Module")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Module_0(b, l + 1);
    r = r && Module_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // VersionDecl?
  private static boolean Module_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Module_0")) return false;
    VersionDecl(b, l + 1);
    return true;
  }

  // LibraryModule | MainModule
  private static boolean Module_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Module_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LibraryModule(b, l + 1);
    if (!r) r = MainModule(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "module" "namespace" NamespacePrefix "=" URILiteral Separator
  public static boolean ModuleDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleDecl")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_DECL, "<module decl>");
    r = consumeTokens(b, 1, K_MODULE, K_NAMESPACE);
    p = r; // pin = 1
    r = r && report_error_(b, NamespacePrefix(b, l + 1));
    r = p && report_error_(b, consumeToken(b, EQUAL)) && r;
    r = p && report_error_(b, URILiteral(b, l + 1)) && r;
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, ModuleDeclRecover_parser_);
    return r || p;
  }

  /* ********************************************************** */
  // !('declare' | 'import')
  static boolean ModuleDeclRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleDeclRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !ModuleDeclRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // 'declare' | 'import'
  private static boolean ModuleDeclRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleDeclRecover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_DECLARE);
    if (!r) r = consumeToken(b, K_IMPORT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "import" "module" ("namespace" NamespacePrefix "=")? ModuleImportNamespace ("at" ModuleImportPath ("," ModuleImportPath)*)? Separator
  public static boolean ModuleImport(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleImport")) return false;
    if (!nextTokenIs(b, K_IMPORT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_IMPORT, null);
    r = consumeTokens(b, 2, K_IMPORT, K_MODULE);
    p = r; // pin = 2
    r = r && report_error_(b, ModuleImport_2(b, l + 1));
    r = p && report_error_(b, ModuleImportNamespace(b, l + 1)) && r;
    r = p && report_error_(b, ModuleImport_4(b, l + 1)) && r;
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ("namespace" NamespacePrefix "=")?
  private static boolean ModuleImport_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleImport_2")) return false;
    ModuleImport_2_0(b, l + 1);
    return true;
  }

  // "namespace" NamespacePrefix "="
  private static boolean ModuleImport_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleImport_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_NAMESPACE);
    r = r && NamespacePrefix(b, l + 1);
    r = r && consumeToken(b, EQUAL);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("at" ModuleImportPath ("," ModuleImportPath)*)?
  private static boolean ModuleImport_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleImport_4")) return false;
    ModuleImport_4_0(b, l + 1);
    return true;
  }

  // "at" ModuleImportPath ("," ModuleImportPath)*
  private static boolean ModuleImport_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleImport_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AT);
    r = r && ModuleImportPath(b, l + 1);
    r = r && ModuleImport_4_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," ModuleImportPath)*
  private static boolean ModuleImport_4_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleImport_4_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ModuleImport_4_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ModuleImport_4_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," ModuleImportPath
  private static boolean ModuleImport_4_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleImport_4_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ModuleImportPath(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ModuleImportPath
  public static boolean ModuleImportNamespace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleImportNamespace")) return false;
    if (!nextTokenIs(b, "<module import namespace>", OPENINGAPOS, OPENINGQUOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MODULE_IMPORT_NAMESPACE, "<module import namespace>");
    r = ModuleImportPath(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // URILiteral
  public static boolean ModuleImportPath(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ModuleImportPath")) return false;
    if (!nextTokenIs(b, "<module import path>", OPENINGAPOS, OPENINGQUOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MODULE_IMPORT_PATH, "<module import path>");
    r = URILiteral(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "$" VarName TypeDeclaration? "in" ExprSingle
  public static boolean MultiVariableBinding(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiVariableBinding")) return false;
    if (!nextTokenIs(b, DOLLAR_SIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    r = r && MultiVariableBinding_2(b, l + 1);
    r = r && consumeToken(b, K_IN);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, m, MULTI_VARIABLE_BINDING, r);
    return r;
  }

  // TypeDeclaration?
  private static boolean MultiVariableBinding_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiVariableBinding_2")) return false;
    TypeDeclaration(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // UnionExpr MultiplicativeOptionalExpr*
  public static boolean MultiplicativeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplicativeExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MULTIPLICATIVE_EXPR, "<multiplicative expr>");
    r = UnionExpr(b, l + 1);
    r = r && MultiplicativeExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // MultiplicativeOptionalExpr*
  private static boolean MultiplicativeExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplicativeExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MultiplicativeOptionalExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MultiplicativeExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "*" | "div" | "idiv" | "mod"
  public static boolean MultiplicativeOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplicativeOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MULTIPLICATIVE_OPERATOR, "<multiplicative operator>");
    r = consumeToken(b, STAR_SIGN);
    if (!r) r = consumeToken(b, K_DIV);
    if (!r) r = consumeToken(b, K_IDIV);
    if (!r) r = consumeToken(b, K_MOD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MultiplicativeOperator UnionExpr
  static boolean MultiplicativeOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplicativeOptionalExpr")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = MultiplicativeOperator(b, l + 1);
    p = r; // pin = 1
    r = r && UnionExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Wildcard | EQName
  public static boolean NameTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NameTest")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NAME_TEST, "<name test>");
    r = Wildcard(b, l + 1);
    if (!r) r = EQName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // FunctionName "#" FunctionArity
  public static boolean NamedFunctionRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamedFunctionRef")) return false;
    if (!nextTokenIs(b, "<named function ref>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NAMED_FUNCTION_REF, "<named function ref>");
    r = FunctionName(b, l + 1);
    r = r && consumeToken(b, HASH);
    r = r && FunctionArity(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "declare" "namespace" NamespacePrefix "=" URILiteral Separator
  public static boolean NamespaceDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamespaceDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NAMESPACE_DECL, null);
    r = consumeTokens(b, 2, K_DECLARE, K_NAMESPACE);
    p = r; // pin = 2
    r = r && report_error_(b, NamespacePrefix(b, l + 1));
    r = p && report_error_(b, consumeToken(b, EQUAL)) && r;
    r = p && report_error_(b, URILiteral(b, l + 1)) && r;
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "namespace-node" "(" ")"
  public static boolean NamespaceNodeTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamespaceNodeTest")) return false;
    if (!nextTokenIs(b, K_NAMESPACE_NODE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NAMESPACE_NODE_TEST, null);
    r = consumeTokens(b, 2, K_NAMESPACE_NODE, L_PAR, R_PAR);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // NCName
  public static boolean NamespacePrefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamespacePrefix")) return false;
    if (!nextTokenIs(b, NCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NCNAME);
    exit_section_(b, m, NAMESPACE_PREFIX, r);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean NewNameExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NewNameExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, NEW_NAME_EXPR, "<new name expr>");
    r = ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VarName
  public static boolean NextItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NextItem")) return false;
    if (!nextTokenIs(b, "<next item>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NEXT_ITEM, "<next item>");
    r = VarName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "is" | "<<" | ">>"
  public static boolean NodeComp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodeComp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NODE_COMP, "<node comp>");
    r = consumeToken(b, K_IS);
    if (!r) r = consumeToken(b, NODECOMP_LT);
    if (!r) r = consumeToken(b, NODECOMP_GT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DirectConstructor
  //  | ComputedConstructor
  public static boolean NodeConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodeConstructor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NODE_CONSTRUCTOR, "<node constructor>");
    r = DirectConstructor(b, l + 1);
    if (!r) r = ComputedConstructor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KindTest | NameTest
  public static boolean NodeTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NodeTest")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NODE_TEST, "<node test>");
    r = KindTest(b, l + 1);
    if (!r) r = NameTest(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IntegerLiteral | DecimalLiteral | DoubleLiteral
  public static boolean NumericLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMERIC_LITERAL, "<numeric literal>");
    r = consumeToken(b, INTEGERLITERAL);
    if (!r) r = consumeToken(b, DECIMALLITERAL);
    if (!r) r = consumeToken(b, DOUBLELITERAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle ":" ExprSingle
  public static boolean ObjectProperty(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectProperty")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_PROPERTY, "<object property>");
    r = ExprSingle(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ObjectProperty ("," ObjectProperty)* ","?
  public static boolean ObjectPropertyList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectPropertyList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_PROPERTY_LIST, "<object property list>");
    r = ObjectProperty(b, l + 1);
    r = r && ObjectPropertyList_1(b, l + 1);
    r = r && ObjectPropertyList_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ("," ObjectProperty)*
  private static boolean ObjectPropertyList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectPropertyList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ObjectPropertyList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ObjectPropertyList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," ObjectProperty
  private static boolean ObjectPropertyList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectPropertyList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ObjectProperty(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ","?
  private static boolean ObjectPropertyList_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectPropertyList_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // "?" | "*" | "+"
  public static boolean OccurrenceIndicator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OccurrenceIndicator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OCCURRENCE_INDICATOR, "<occurrence indicator>");
    r = consumeToken(b, QUESTIONMARK);
    if (!r) r = consumeToken(b, STAR_SIGN);
    if (!r) r = consumeToken(b, OP_PLUS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "declare" "option" EQName StringLiteral Separator
  public static boolean OptionDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OptionDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, OPTION_DECL, null);
    r = consumeTokens(b, 2, K_DECLARE, K_OPTION);
    p = r; // pin = 2
    r = r && report_error_(b, EQName(b, l + 1));
    r = p && report_error_(b, StringLiteral(b, l + 1)) && r;
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "," Argument
  static boolean OptionalArgumentAfterComma(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OptionalArgumentAfterComma")) return false;
    if (!nextTokenIs(b, COMMA)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && Argument(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // AndExpr OrMultipliedExpr*
  public static boolean OrExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, OR_EXPR, "<or expr>");
    r = AndExpr(b, l + 1);
    r = r && OrExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OrMultipliedExpr*
  private static boolean OrExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!OrMultipliedExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "OrExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // OrOperator AndExpr
  static boolean OrMultipliedExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrMultipliedExpr")) return false;
    if (!nextTokenIs(b, K_OR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = OrOperator(b, l + 1);
    p = r; // pin = 1
    r = r && AndExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "or"
  public static boolean OrOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrOperator")) return false;
    if (!nextTokenIs(b, K_OR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_OR);
    exit_section_(b, m, OR_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // (("order" "by") | ("stable" "order" "by")) OrderSpecList
  public static boolean OrderByClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderByClause")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ORDER_BY_CLAUSE, "<order by clause>");
    r = OrderByClause_0(b, l + 1);
    p = r; // pin = 1
    r = r && OrderSpecList(b, l + 1);
    exit_section_(b, l, m, r, p, FLWORExprRecover_parser_);
    return r || p;
  }

  // ("order" "by") | ("stable" "order" "by")
  private static boolean OrderByClause_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderByClause_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = OrderByClause_0_0(b, l + 1);
    if (!r) r = OrderByClause_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "order" "by"
  private static boolean OrderByClause_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderByClause_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ORDER, K_BY);
    exit_section_(b, m, null, r);
    return r;
  }

  // "stable" "order" "by"
  private static boolean OrderByClause_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderByClause_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_STABLE, K_ORDER, K_BY);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ("ascending" | "descending")? ("empty" ("greatest" | "least"))? ("collation" URILiteral)?
  static boolean OrderModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderModifier")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = OrderModifier_0(b, l + 1);
    r = r && OrderModifier_1(b, l + 1);
    r = r && OrderModifier_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("ascending" | "descending")?
  private static boolean OrderModifier_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderModifier_0")) return false;
    OrderModifier_0_0(b, l + 1);
    return true;
  }

  // "ascending" | "descending"
  private static boolean OrderModifier_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderModifier_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_ASCENDING);
    if (!r) r = consumeToken(b, K_DESCENDING);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("empty" ("greatest" | "least"))?
  private static boolean OrderModifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderModifier_1")) return false;
    OrderModifier_1_0(b, l + 1);
    return true;
  }

  // "empty" ("greatest" | "least")
  private static boolean OrderModifier_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderModifier_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_EMPTY);
    r = r && OrderModifier_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "greatest" | "least"
  private static boolean OrderModifier_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderModifier_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_GREATEST);
    if (!r) r = consumeToken(b, K_LEAST);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("collation" URILiteral)?
  private static boolean OrderModifier_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderModifier_2")) return false;
    OrderModifier_2_0(b, l + 1);
    return true;
  }

  // "collation" URILiteral
  private static boolean OrderModifier_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderModifier_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_COLLATION);
    r = r && URILiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle OrderModifier
  public static boolean OrderSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderSpec")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ORDER_SPEC, "<order spec>");
    r = ExprSingle(b, l + 1);
    r = r && OrderModifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OrderSpec ("," OrderSpec)*
  public static boolean OrderSpecList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderSpecList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ORDER_SPEC_LIST, "<order spec list>");
    r = OrderSpec(b, l + 1);
    r = r && OrderSpecList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ("," OrderSpec)*
  private static boolean OrderSpecList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderSpecList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!OrderSpecList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "OrderSpecList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," OrderSpec
  private static boolean OrderSpecList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderSpecList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && OrderSpec(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "ordered" EnclosedExpression
  public static boolean OrderedExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderedExpr")) return false;
    if (!nextTokenIs(b, K_ORDERED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_ORDERED);
    r = r && EnclosedExpression(b, l + 1);
    exit_section_(b, m, ORDERED_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // "declare" "ordering" ("ordered" | "unordered") Separator
  public static boolean OrderingModeDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderingModeDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ORDERING_MODE_DECL, null);
    r = consumeTokens(b, 2, K_DECLARE, K_ORDERING);
    p = r; // pin = 2
    r = r && report_error_(b, OrderingModeDecl_2(b, l + 1));
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // "ordered" | "unordered"
  private static boolean OrderingModeDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrderingModeDecl_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_ORDERED);
    if (!r) r = consumeToken(b, K_UNORDERED);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "processing-instruction" "(" (NCName | StringLiteral)? ")"
  public static boolean PITest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PITest")) return false;
    if (!nextTokenIs(b, K_PI)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PI_TEST, null);
    r = consumeTokens(b, 2, K_PI, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, PITest_2(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (NCName | StringLiteral)?
  private static boolean PITest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PITest_2")) return false;
    PITest_2_0(b, l + 1);
    return true;
  }

  // NCName | StringLiteral
  private static boolean PITest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PITest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NCNAME);
    if (!r) r = StringLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "$" VarName TypeDeclaration?
  public static boolean Param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Param")) return false;
    if (!nextTokenIs(b, DOLLAR_SIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    r = r && Param_2(b, l + 1);
    exit_section_(b, m, PARAM, r);
    return r;
  }

  // TypeDeclaration?
  private static boolean Param_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Param_2")) return false;
    TypeDeclaration(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "(" (Param ("," Param)*)? ")"
  public static boolean ParamList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamList")) return false;
    if (!nextTokenIs(b, L_PAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARAM_LIST, null);
    r = consumeToken(b, L_PAR);
    p = r; // pin = 1
    r = r && report_error_(b, ParamList_1(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (Param ("," Param)*)?
  private static boolean ParamList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamList_1")) return false;
    ParamList_1_0(b, l + 1);
    return true;
  }

  // Param ("," Param)*
  private static boolean ParamList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Param(b, l + 1);
    r = r && ParamList_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," Param)*
  private static boolean ParamList_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamList_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ParamList_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ParamList_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," Param
  private static boolean ParamList_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamList_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "(" Expr? ")"
  public static boolean ParenthesizedExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesizedExpr")) return false;
    if (!nextTokenIs(b, L_PAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARENTHESIZED_EXPR, null);
    r = consumeToken(b, L_PAR);
    p = r; // pin = 1
    r = r && report_error_(b, ParenthesizedExpr_1(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Expr?
  private static boolean ParenthesizedExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesizedExpr_1")) return false;
    Expr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "(" ItemType ")"
  public static boolean ParenthesizedItemType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesizedItemType")) return false;
    if (!nextTokenIs(b, L_PAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PAR);
    r = r && ItemType(b, l + 1);
    r = r && consumeToken(b, R_PAR);
    exit_section_(b, m, PARENTHESIZED_ITEM_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // RootPathExpr | RootPathOrChildExpr | RelativePathExpr
  public static boolean PathExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PathExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, PATH_EXPR, "<path expr>");
    r = RootPathExpr(b, l + 1);
    if (!r) r = RootPathOrChildExpr(b, l + 1);
    if (!r) r = RelativePathExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "at" "$" VarName
  public static boolean PositionalVar(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PositionalVar")) return false;
    if (!nextTokenIs(b, K_AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_AT, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    exit_section_(b, m, POSITIONAL_VAR, r);
    return r;
  }

  /* ********************************************************** */
  // PrimaryExpr (Predicate | ArgumentList | Lookup)*
  public static boolean PostfixExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostfixExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, POSTFIX_EXPR, "<postfix expr>");
    r = PrimaryExpr(b, l + 1);
    r = r && PostfixExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (Predicate | ArgumentList | Lookup)*
  private static boolean PostfixExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostfixExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!PostfixExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PostfixExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Predicate | ArgumentList | Lookup
  private static boolean PostfixExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostfixExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Predicate(b, l + 1);
    if (!r) r = ArgumentList(b, l + 1);
    if (!r) r = Lookup(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "(#" S? EQName (S PragmaContents)? "#)"
  public static boolean Pragma(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pragma")) return false;
    if (!nextTokenIs(b, PRAGMA_BEGIN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PRAGMA, null);
    r = consumeToken(b, PRAGMA_BEGIN);
    p = r; // pin = 1
    r = r && report_error_(b, Pragma_1(b, l + 1));
    r = p && report_error_(b, EQName(b, l + 1)) && r;
    r = p && report_error_(b, Pragma_3(b, l + 1)) && r;
    r = p && consumeToken(b, PRAGMA_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // S?
  private static boolean Pragma_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pragma_1")) return false;
    consumeToken(b, S);
    return true;
  }

  // (S PragmaContents)?
  private static boolean Pragma_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pragma_3")) return false;
    Pragma_3_0(b, l + 1);
    return true;
  }

  // S PragmaContents
  private static boolean Pragma_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pragma_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, S);
    r = r && PragmaContents(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PragmaContentChar*
  public static boolean PragmaContents(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PragmaContents")) return false;
    Marker m = enter_section_(b, l, _NONE_, PRAGMA_CONTENTS, "<pragma contents>");
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, PRAGMACONTENTCHAR)) break;
      if (!empty_element_parsed_guard_(b, "PragmaContents", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // "[" Expr "]"
  public static boolean Predicate(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Predicate")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, PREDICATE, r);
    return r;
  }

  /* ********************************************************** */
  // Predicate*
  public static boolean PredicateList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PredicateList")) return false;
    Marker m = enter_section_(b, l, _NONE_, PREDICATE_LIST, "<predicate list>");
    int c = current_position_(b);
    while (true) {
      if (!Predicate(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PredicateList", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // NCName
  public static boolean Prefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Prefix")) return false;
    if (!nextTokenIs(b, NCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NCNAME);
    exit_section_(b, m, PREFIX, r);
    return r;
  }

  /* ********************************************************** */
  // Prefix ':' LocalPart
  static boolean PrefixedName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixedName")) return false;
    if (!nextTokenIs(b, NCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Prefix(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && LocalPart(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "preserve" | "no-preserve"
  public static boolean PreserveMode(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreserveMode")) return false;
    if (!nextTokenIs(b, "<preserve mode>", K_NO_PRESERVE, K_PRESERVE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRESERVE_MODE, "<preserve mode>");
    r = consumeToken(b, K_PRESERVE);
    if (!r) r = consumeToken(b, K_NO_PRESERVE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VarName
  public static boolean PreviousItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreviousItem")) return false;
    if (!nextTokenIs(b, "<previous item>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PREVIOUS_ITEM, "<previous item>");
    r = VarName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Literal
  //  | VarRef
  //  | ParenthesizedExpr
  //  | ContextItemExpr
  //  | FunctionCall
  //  | OrderedExpr
  //  | UnorderedExpr
  //  | NodeConstructor
  //  | FunctionItemExpr
  //  | MapConstructor
  //  | ArrayConstructor
  //  | StringConstructor
  //  | UnaryLookup
  public static boolean PrimaryExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, PRIMARY_EXPR, "<primary expr>");
    r = Literal(b, l + 1);
    if (!r) r = VarRef(b, l + 1);
    if (!r) r = ParenthesizedExpr(b, l + 1);
    if (!r) r = ContextItemExpr(b, l + 1);
    if (!r) r = FunctionCall(b, l + 1);
    if (!r) r = OrderedExpr(b, l + 1);
    if (!r) r = UnorderedExpr(b, l + 1);
    if (!r) r = NodeConstructor(b, l + 1);
    if (!r) r = FunctionItemExpr(b, l + 1);
    if (!r) r = MapConstructor(b, l + 1);
    if (!r) r = ArrayConstructor(b, l + 1);
    if (!r) r = StringConstructor(b, l + 1);
    if (!r) r = UnaryLookup(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // FirstDecl* SecondDecl*
  static boolean Prolog(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Prolog")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Prolog_0(b, l + 1);
    r = r && Prolog_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FirstDecl*
  private static boolean Prolog_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Prolog_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!FirstDecl(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Prolog_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // SecondDecl*
  private static boolean Prolog_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Prolog_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!SecondDecl(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Prolog_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // PrefixedName
  //  | UnprefixedName
  static boolean QName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QName")) return false;
    if (!nextTokenIs(b, NCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PrefixedName(b, l + 1);
    if (!r) r = UnprefixedName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ("some" | "every") MultiVariableBinding ("," MultiVariableBinding)* "satisfies" ExprSingle
  public static boolean QuantifiedExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuantifiedExpr")) return false;
    if (!nextTokenIs(b, "<quantified expr>", K_EVERY, K_SOME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _COLLAPSE_, QUANTIFIED_EXPR, "<quantified expr>");
    r = QuantifiedExpr_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, MultiVariableBinding(b, l + 1));
    r = p && report_error_(b, QuantifiedExpr_2(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, K_SATISFIES)) && r;
    r = p && ExprSingle(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // "some" | "every"
  private static boolean QuantifiedExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuantifiedExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_SOME);
    if (!r) r = consumeToken(b, K_EVERY);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," MultiVariableBinding)*
  private static boolean QuantifiedExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuantifiedExpr_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!QuantifiedExpr_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "QuantifiedExpr_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," MultiVariableBinding
  private static boolean QuantifiedExpr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuantifiedExpr_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && MultiVariableBinding(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expr
  public static boolean QueryBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QueryBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, QUERY_BODY, "<query body>");
    r = Expr(b, l + 1);
    exit_section_(b, l, m, r, false, QueryBodyRecover_parser_);
    return r;
  }

  /* ********************************************************** */
  // !<<eof>>
  static boolean QueryBodyRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QueryBodyRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !eof(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Char
  public static boolean QuotAttrContentChar(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotAttrContentChar")) return false;
    if (!nextTokenIs(b, CHAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CHAR);
    exit_section_(b, m, QUOT_ATTR_CONTENT_CHAR, r);
    return r;
  }

  /* ********************************************************** */
  // QuotAttrContentChar
  //  | CommonContent
  public static boolean QuotAttrValueContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotAttrValueContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, QUOT_ATTR_VALUE_CONTENT, "<quot attr value content>");
    r = QuotAttrContentChar(b, l + 1);
    if (!r) r = CommonContent(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OpeningQuot (PredefinedEntityRef | CharRef | EscapeQuot | StringChar)* ClosingQuot
  static boolean QuotStringLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotStringLiteral")) return false;
    if (!nextTokenIs(b, OPENINGQUOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPENINGQUOT);
    r = r && QuotStringLiteral_1(b, l + 1);
    r = r && consumeToken(b, CLOSINGQUOT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PredefinedEntityRef | CharRef | EscapeQuot | StringChar)*
  private static boolean QuotStringLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotStringLiteral_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!QuotStringLiteral_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "QuotStringLiteral_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // PredefinedEntityRef | CharRef | EscapeQuot | StringChar
  private static boolean QuotStringLiteral_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QuotStringLiteral_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PREDEFINEDENTITYREF);
    if (!r) r = consumeToken(b, CHARREF);
    if (!r) r = EscapeQuot(b, l + 1);
    if (!r) r = consumeToken(b, STRINGCHAR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // AdditiveExpr RangeOptionalExpr?
  public static boolean RangeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, RANGE_EXPR, "<range expr>");
    r = AdditiveExpr(b, l + 1);
    r = r && RangeExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // RangeOptionalExpr?
  private static boolean RangeExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeExpr_1")) return false;
    RangeOptionalExpr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ToOperator AdditiveExpr
  static boolean RangeOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RangeOptionalExpr")) return false;
    if (!nextTokenIs(b, K_TO)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ToOperator(b, l + 1);
    p = r; // pin = 1
    r = r && AdditiveExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "<" | "<=" | ">" | ">="
  public static boolean RelationalComp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationalComp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RELATIONAL_COMP, "<relational comp>");
    r = consumeToken(b, LT_CHAR);
    if (!r) r = consumeToken(b, LE_CHARS);
    if (!r) r = consumeToken(b, GT_CHAR);
    if (!r) r = consumeToken(b, GE_CHARS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // StepExpr RelativePathOptionalExpr*
  static boolean RelativePathExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelativePathExpr")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StepExpr(b, l + 1);
    r = r && RelativePathExpr_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // RelativePathOptionalExpr*
  private static boolean RelativePathExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelativePathExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!RelativePathOptionalExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "RelativePathExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "/" | "//"
  public static boolean RelativePathOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelativePathOperator")) return false;
    if (!nextTokenIs(b, "<relative path operator>", SLASH, SLASH_SLASH)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RELATIVE_PATH_OPERATOR, "<relative path operator>");
    r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, SLASH_SLASH);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (RelativePathOperator) StepExpr
  static boolean RelativePathOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelativePathOptionalExpr")) return false;
    if (!nextTokenIs(b, "", SLASH, SLASH_SLASH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = RelativePathOptionalExpr_0(b, l + 1);
    p = r; // pin = 1
    r = r && StepExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (RelativePathOperator)
  private static boolean RelativePathOptionalExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelativePathOptionalExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = RelativePathOperator(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "rename" "node" TargetExpr "as" NewNameExpr
  public static boolean RenameExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RenameExpr")) return false;
    if (!nextTokenIs(b, K_RENAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_RENAME, K_NODE);
    r = r && TargetExpr(b, l + 1);
    r = r && consumeToken(b, K_AS);
    r = r && NewNameExpr(b, l + 1);
    exit_section_(b, m, RENAME_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // "replace" ("value" "of")? "node" TargetExpr "with" ExprSingle
  public static boolean ReplaceExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReplaceExpr")) return false;
    if (!nextTokenIs(b, K_REPLACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_REPLACE);
    r = r && ReplaceExpr_1(b, l + 1);
    r = r && consumeToken(b, K_NODE);
    r = r && TargetExpr(b, l + 1);
    r = r && consumeToken(b, K_WITH);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, m, REPLACE_EXPR, r);
    return r;
  }

  // ("value" "of")?
  private static boolean ReplaceExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReplaceExpr_1")) return false;
    ReplaceExpr_1_0(b, l + 1);
    return true;
  }

  // "value" "of"
  private static boolean ReplaceExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReplaceExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_VALUE, K_OF);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "return" ExprSingle
  public static boolean ReturnClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnClause")) return false;
    if (!nextTokenIs(b, K_RETURN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RETURN_CLAUSE, null);
    r = consumeToken(b, K_RETURN);
    p = r; // pin = 1
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "declare" "revalidation" ("strict" | "lax" | "skip")
  public static boolean RevalidationDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RevalidationDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_DECLARE, K_REVALIDATION);
    r = r && RevalidationDecl_2(b, l + 1);
    exit_section_(b, m, REVALIDATION_DECL, r);
    return r;
  }

  // "strict" | "lax" | "skip"
  private static boolean RevalidationDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RevalidationDecl_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_STRICT);
    if (!r) r = consumeToken(b, K_LAX);
    if (!r) r = consumeToken(b, K_SKIP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ("parent" "::")
  //  | ("ancestor" "::")
  //  | ("preceding-sibling" "::")
  //  | ("preceding" "::")
  //  | ("ancestor-or-self" "::")
  static boolean ReverseAxis(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReverseAxis")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ReverseAxis_0(b, l + 1);
    if (!r) r = ReverseAxis_1(b, l + 1);
    if (!r) r = ReverseAxis_2(b, l + 1);
    if (!r) r = ReverseAxis_3(b, l + 1);
    if (!r) r = ReverseAxis_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "parent" "::"
  private static boolean ReverseAxis_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReverseAxis_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_PARENT, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "ancestor" "::"
  private static boolean ReverseAxis_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReverseAxis_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ANCESTOR, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "preceding-sibling" "::"
  private static boolean ReverseAxis_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReverseAxis_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_PRECEDING_SIBLING, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "preceding" "::"
  private static boolean ReverseAxis_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReverseAxis_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_PRECEDING, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // "ancestor-or-self" "::"
  private static boolean ReverseAxis_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReverseAxis_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_ANCESTOR_OR_SELF, COLON_COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FullReverseStep | AbbrevReverseStep
  public static boolean ReverseStep(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReverseStep")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REVERSE_STEP, "<reverse step>");
    r = FullReverseStep(b, l + 1);
    if (!r) r = AbbrevReverseStep(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "/" RelativePathExpr?
  static boolean RootPathExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RootPathExpr")) return false;
    if (!nextTokenIs(b, SLASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SLASH);
    r = r && RootPathExpr_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // RelativePathExpr?
  private static boolean RootPathExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RootPathExpr_1")) return false;
    RelativePathExpr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "//" RelativePathExpr
  static boolean RootPathOrChildExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RootPathOrChildExpr")) return false;
    if (!nextTokenIs(b, SLASH_SLASH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, SLASH_SLASH);
    p = r; // pin = 1
    r = r && RelativePathExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ","
  public static boolean SaxonMapEntriesSeparator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SaxonMapEntriesSeparator")) return false;
    if (!nextTokenIs(b, COMMA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    exit_section_(b, m, SAXON_MAP_ENTRIES_SEPARATOR, r);
    return r;
  }

  /* ********************************************************** */
  // ":="
  public static boolean SaxonMapEntrySeparator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SaxonMapEntrySeparator")) return false;
    if (!nextTokenIs(b, OP_ASSIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_ASSIGN);
    exit_section_(b, m, SAXON_MAP_ENTRY_SEPARATOR, r);
    return r;
  }

  /* ********************************************************** */
  // "schema-attribute" "(" AttributeDeclaration ")"
  public static boolean SchemaAttributeTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaAttributeTest")) return false;
    if (!nextTokenIs(b, K_SCHEMA_ATTRIBUTE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SCHEMA_ATTRIBUTE_TEST, null);
    r = consumeTokens(b, 2, K_SCHEMA_ATTRIBUTE, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, AttributeDeclaration(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "schema-element" "(" ElementDeclaration ")"
  public static boolean SchemaElementTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaElementTest")) return false;
    if (!nextTokenIs(b, K_SCHEMA_ELEMENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SCHEMA_ELEMENT_TEST, null);
    r = consumeTokens(b, 2, K_SCHEMA_ELEMENT, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, ElementDeclaration(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "import" "schema" (("namespace" NamespacePrefix "=") | ("default" "element" "namespace"))? URILiteral ("at" URILiteral ("," URILiteral)*)? Separator
  public static boolean SchemaImport(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaImport")) return false;
    if (!nextTokenIs(b, K_IMPORT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SCHEMA_IMPORT, null);
    r = consumeTokens(b, 2, K_IMPORT, K_SCHEMA);
    p = r; // pin = 2
    r = r && report_error_(b, SchemaImport_2(b, l + 1));
    r = p && report_error_(b, URILiteral(b, l + 1)) && r;
    r = p && report_error_(b, SchemaImport_4(b, l + 1)) && r;
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (("namespace" NamespacePrefix "=") | ("default" "element" "namespace"))?
  private static boolean SchemaImport_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaImport_2")) return false;
    SchemaImport_2_0(b, l + 1);
    return true;
  }

  // ("namespace" NamespacePrefix "=") | ("default" "element" "namespace")
  private static boolean SchemaImport_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaImport_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SchemaImport_2_0_0(b, l + 1);
    if (!r) r = SchemaImport_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "namespace" NamespacePrefix "="
  private static boolean SchemaImport_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaImport_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_NAMESPACE);
    r = r && NamespacePrefix(b, l + 1);
    r = r && consumeToken(b, EQUAL);
    exit_section_(b, m, null, r);
    return r;
  }

  // "default" "element" "namespace"
  private static boolean SchemaImport_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaImport_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_DEFAULT, K_ELEMENT, K_NAMESPACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("at" URILiteral ("," URILiteral)*)?
  private static boolean SchemaImport_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaImport_4")) return false;
    SchemaImport_4_0(b, l + 1);
    return true;
  }

  // "at" URILiteral ("," URILiteral)*
  private static boolean SchemaImport_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaImport_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AT);
    r = r && URILiteral(b, l + 1);
    r = r && SchemaImport_4_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," URILiteral)*
  private static boolean SchemaImport_4_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaImport_4_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!SchemaImport_4_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SchemaImport_4_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," URILiteral
  private static boolean SchemaImport_4_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SchemaImport_4_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && URILiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ContextItemDecl | OptionDecl | AnnotatedDecl
  static boolean SecondDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SecondDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ContextItemDecl(b, l + 1);
    if (!r) r = OptionDecl(b, l + 1);
    if (!r) r = AnnotatedDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ";"
  public static boolean Separator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Separator")) return false;
    if (!nextTokenIs(b, SEMICOLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    exit_section_(b, m, SEPARATOR, r);
    return r;
  }

  /* ********************************************************** */
  // ("empty-sequence" "(" ")")
  //  | (ItemType OccurrenceIndicator?)
  public static boolean SequenceType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SequenceType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SEQUENCE_TYPE, "<sequence type>");
    r = SequenceType_0(b, l + 1);
    if (!r) r = SequenceType_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "empty-sequence" "(" ")"
  private static boolean SequenceType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SequenceType_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_EMPTY_SEQUENCE, L_PAR, R_PAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // ItemType OccurrenceIndicator?
  private static boolean SequenceType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SequenceType_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ItemType(b, l + 1);
    r = r && SequenceType_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OccurrenceIndicator?
  private static boolean SequenceType_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SequenceType_1_1")) return false;
    OccurrenceIndicator(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // SequenceType ("|" SequenceType)*
  public static boolean SequenceTypeUnion(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SequenceTypeUnion")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SEQUENCE_TYPE_UNION, "<sequence type union>");
    r = SequenceType(b, l + 1);
    r = r && SequenceTypeUnion_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ("|" SequenceType)*
  private static boolean SequenceTypeUnion_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SequenceTypeUnion_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!SequenceTypeUnion_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SequenceTypeUnion_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "|" SequenceType
  private static boolean SequenceTypeUnion_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SequenceTypeUnion_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIPE);
    r = r && SequenceType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BoundarySpaceDecl | DefaultCollationDecl | BaseURIDecl | ConstructionDecl | OrderingModeDecl | EmptyOrderDecl | RevalidationDecl | CopyNamespacesDecl | DecimalFormatDecl
  static boolean Setter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Setter")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BoundarySpaceDecl(b, l + 1);
    if (!r) r = DefaultCollationDecl(b, l + 1);
    if (!r) r = BaseURIDecl(b, l + 1);
    if (!r) r = ConstructionDecl(b, l + 1);
    if (!r) r = OrderingModeDecl(b, l + 1);
    if (!r) r = EmptyOrderDecl(b, l + 1);
    if (!r) r = RevalidationDecl(b, l + 1);
    if (!r) r = CopyNamespacesDecl(b, l + 1);
    if (!r) r = DecimalFormatDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PathExpr SimpleMapOptionalExpr*
  public static boolean SimpleMapExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleMapExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, SIMPLE_MAP_EXPR, "<simple map expr>");
    r = PathExpr(b, l + 1);
    r = r && SimpleMapExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SimpleMapOptionalExpr*
  private static boolean SimpleMapExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleMapExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!SimpleMapOptionalExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SimpleMapExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "!"
  public static boolean SimpleMapOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleMapOperator")) return false;
    if (!nextTokenIs(b, EXCLAMATION_MARK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXCLAMATION_MARK);
    exit_section_(b, m, SIMPLE_MAP_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // SimpleMapOperator PathExpr
  static boolean SimpleMapOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleMapOptionalExpr")) return false;
    if (!nextTokenIs(b, EXCLAMATION_MARK)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = SimpleMapOperator(b, l + 1);
    p = r; // pin = 1
    r = r && PathExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // TypeName
  public static boolean SimpleTypeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleTypeName")) return false;
    if (!nextTokenIs(b, "<simple type name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_TYPE_NAME, "<simple type name>");
    r = TypeName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SimpleTypeName "?"?
  public static boolean SingleType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SingleType")) return false;
    if (!nextTokenIs(b, "<single type>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SINGLE_TYPE, "<single type>");
    r = SimpleTypeName(b, l + 1);
    r = r && SingleType_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "?"?
  private static boolean SingleType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SingleType_1")) return false;
    consumeToken(b, QUESTIONMARK);
    return true;
  }

  /* ********************************************************** */
  // "sliding" SlidingWindowDetails
  static boolean SlidingWindowClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SlidingWindowClause")) return false;
    if (!nextTokenIs(b, K_SLIDING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, K_SLIDING);
    p = r; // pin = 1
    r = r && SlidingWindowDetails(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "window" "$" VarName TypeDeclaration? "in" ExprSingle WindowStartCondition WindowEndCondition
  static boolean SlidingWindowDetails(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SlidingWindowDetails")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 0, K_WINDOW, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    r = r && SlidingWindowDetails_3(b, l + 1);
    r = r && consumeToken(b, K_IN);
    r = r && ExprSingle(b, l + 1);
    r = r && WindowStartCondition(b, l + 1);
    r = r && WindowEndCondition(b, l + 1);
    exit_section_(b, l, m, r, false, FLWORExprRecover_parser_);
    return r;
  }

  // TypeDeclaration?
  private static boolean SlidingWindowDetails_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SlidingWindowDetails_3")) return false;
    TypeDeclaration(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean SourceExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SourceExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, SOURCE_EXPR, "<source expr>");
    r = ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "[" (ExprSingle ("," ExprSingle)*)? "]"
  public static boolean SquareArrayConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SquareArrayConstructor")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SQUARE_ARRAY_CONSTRUCTOR, null);
    r = consumeToken(b, L_BRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, SquareArrayConstructor_1(b, l + 1));
    r = p && consumeToken(b, R_BRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (ExprSingle ("," ExprSingle)*)?
  private static boolean SquareArrayConstructor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SquareArrayConstructor_1")) return false;
    SquareArrayConstructor_1_0(b, l + 1);
    return true;
  }

  // ExprSingle ("," ExprSingle)*
  private static boolean SquareArrayConstructor_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SquareArrayConstructor_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ExprSingle(b, l + 1);
    r = r && SquareArrayConstructor_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," ExprSingle)*
  private static boolean SquareArrayConstructor_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SquareArrayConstructor_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!SquareArrayConstructor_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SquareArrayConstructor_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," ExprSingle
  private static boolean SquareArrayConstructor_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SquareArrayConstructor_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PostfixExpr | AxisStep
  public static boolean StepExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StepExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, STEP_EXPR, "<step expr>");
    r = PostfixExpr(b, l + 1);
    if (!r) r = AxisStep(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // RangeExpr StringConcatMultipliedExpr*
  public static boolean StringConcatExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConcatExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, STRING_CONCAT_EXPR, "<string concat expr>");
    r = RangeExpr(b, l + 1);
    r = r && StringConcatExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // StringConcatMultipliedExpr*
  private static boolean StringConcatExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConcatExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!StringConcatMultipliedExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StringConcatExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // ConcatOperator RangeExpr
  static boolean StringConcatMultipliedExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConcatMultipliedExpr")) return false;
    if (!nextTokenIs(b, PIPE_PIPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ConcatOperator(b, l + 1);
    p = r; // pin = 1
    r = r && RangeExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "``[" StringConstructorContent "]``"
  public static boolean StringConstructor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConstructor")) return false;
    if (!nextTokenIs(b, STRING_CONSTR_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STRING_CONSTRUCTOR, null);
    r = consumeToken(b, STRING_CONSTR_START);
    p = r; // pin = 1
    r = r && report_error_(b, StringConstructorContent(b, l + 1));
    r = p && consumeToken(b, STRING_CONSTR_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Char*
  public static boolean StringConstructorChars(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConstructorChars")) return false;
    Marker m = enter_section_(b, l, _NONE_, STRING_CONSTRUCTOR_CHARS, "<string constructor chars>");
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CHAR)) break;
      if (!empty_element_parsed_guard_(b, "StringConstructorChars", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // StringConstructorChars (StringConstructorInterpolation StringConstructorChars)*
  public static boolean StringConstructorContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConstructorContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_CONSTRUCTOR_CONTENT, "<string constructor content>");
    r = StringConstructorChars(b, l + 1);
    r = r && StringConstructorContent_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (StringConstructorInterpolation StringConstructorChars)*
  private static boolean StringConstructorContent_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConstructorContent_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!StringConstructorContent_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StringConstructorContent_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // StringConstructorInterpolation StringConstructorChars
  private static boolean StringConstructorContent_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConstructorContent_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringConstructorInterpolation(b, l + 1);
    r = r && StringConstructorChars(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "`{" Expr? "}`"
  public static boolean StringConstructorInterpolation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConstructorInterpolation")) return false;
    if (!nextTokenIs(b, STRING_CONSTR_EXPR_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STRING_CONSTRUCTOR_INTERPOLATION, null);
    r = consumeToken(b, STRING_CONSTR_EXPR_START);
    p = r; // pin = 1
    r = r && report_error_(b, StringConstructorInterpolation_1(b, l + 1));
    r = p && consumeToken(b, STRING_CONSTR_EXPR_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Expr?
  private static boolean StringConstructorInterpolation_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringConstructorInterpolation_1")) return false;
    Expr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // QuotStringLiteral | AposStringLiteral
  public static boolean StringLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringLiteral")) return false;
    if (!nextTokenIs(b, "<string literal>", OPENINGAPOS, OPENINGQUOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LITERAL, "<string literal>");
    r = QuotStringLiteral(b, l + 1);
    if (!r) r = AposStringLiteral(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // StringLiteral | "*"
  public static boolean StringLiteralOrWildcard(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringLiteralOrWildcard")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LITERAL_OR_WILDCARD, "<string literal or wildcard>");
    r = StringLiteral(b, l + 1);
    if (!r) r = consumeToken(b, STAR_SIGN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ("case" SwitchCaseOperand)+ SwitchReturnClause
  public static boolean SwitchCaseClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCaseClause")) return false;
    if (!nextTokenIs(b, K_CASE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SwitchCaseClause_0(b, l + 1);
    r = r && SwitchReturnClause(b, l + 1);
    exit_section_(b, m, SWITCH_CASE_CLAUSE, r);
    return r;
  }

  // ("case" SwitchCaseOperand)+
  private static boolean SwitchCaseClause_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCaseClause_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SwitchCaseClause_0_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!SwitchCaseClause_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SwitchCaseClause_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // "case" SwitchCaseOperand
  private static boolean SwitchCaseClause_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCaseClause_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_CASE);
    r = r && SwitchCaseOperand(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean SwitchCaseOperand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCaseOperand")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_CASE_OPERAND, "<switch case operand>");
    r = ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "default" SwitchReturnClause
  public static boolean SwitchDefaultReturnClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchDefaultReturnClause")) return false;
    if (!nextTokenIs(b, K_DEFAULT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_DEFAULT_RETURN_CLAUSE, null);
    r = consumeToken(b, K_DEFAULT);
    p = r; // pin = 1
    r = r && SwitchReturnClause(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "switch" "(" Expr ")" SwitchCaseClause+ SwitchDefaultReturnClause
  public static boolean SwitchExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchExpr")) return false;
    if (!nextTokenIs(b, K_SWITCH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_EXPR, null);
    r = consumeTokens(b, 1, K_SWITCH, L_PAR);
    p = r; // pin = 1
    r = r && report_error_(b, Expr(b, l + 1));
    r = p && report_error_(b, consumeToken(b, R_PAR)) && r;
    r = p && report_error_(b, SwitchExpr_4(b, l + 1)) && r;
    r = p && SwitchDefaultReturnClause(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // SwitchCaseClause+
  private static boolean SwitchExpr_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchExpr_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SwitchCaseClause(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!SwitchCaseClause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SwitchExpr_4", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "return" ExprSingle
  public static boolean SwitchReturnClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchReturnClause")) return false;
    if (!nextTokenIs(b, K_RETURN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_RETURN);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, m, SWITCH_RETURN_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean TargetExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TargetExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, TARGET_EXPR, "<target expr>");
    r = ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "text" "(" ")"
  public static boolean TextTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TextTest")) return false;
    if (!nextTokenIs(b, K_TEXT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TEXT_TEST, null);
    r = consumeTokens(b, 2, K_TEXT, L_PAR, R_PAR);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "to"
  public static boolean ToOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ToOperator")) return false;
    if (!nextTokenIs(b, K_TO)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_TO);
    exit_section_(b, m, TO_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // "copy" "$" VarName ":=" ExprSingle ("," "$" VarName ":=" ExprSingle)* "modify" ExprSingle "return" ExprSingle
  public static boolean TransformExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TransformExpr")) return false;
    if (!nextTokenIs(b, K_COPY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_COPY, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    r = r && consumeToken(b, OP_ASSIGN);
    r = r && ExprSingle(b, l + 1);
    r = r && TransformExpr_5(b, l + 1);
    r = r && consumeToken(b, K_MODIFY);
    r = r && ExprSingle(b, l + 1);
    r = r && consumeToken(b, K_RETURN);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, m, TRANSFORM_EXPR, r);
    return r;
  }

  // ("," "$" VarName ":=" ExprSingle)*
  private static boolean TransformExpr_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TransformExpr_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!TransformExpr_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TransformExpr_5", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," "$" VarName ":=" ExprSingle
  private static boolean TransformExpr_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TransformExpr_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    r = r && consumeToken(b, OP_ASSIGN);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CastableExpr TreatOptionalExpr?
  public static boolean TreatExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TreatExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, TREAT_EXPR, "<treat expr>");
    r = CastableExpr(b, l + 1);
    r = r && TreatExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TreatOptionalExpr?
  private static boolean TreatExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TreatExpr_1")) return false;
    TreatOptionalExpr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "treat" "as"
  public static boolean TreatOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TreatOperator")) return false;
    if (!nextTokenIs(b, K_TREAT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_TREAT, K_AS);
    exit_section_(b, m, TREAT_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // TreatOperator SequenceType
  static boolean TreatOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TreatOptionalExpr")) return false;
    if (!nextTokenIs(b, K_TREAT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = TreatOperator(b, l + 1);
    p = r; // pin = 1
    r = r && SequenceType(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // TryClause CatchClause+
  public static boolean TryCatchExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryCatchExpr")) return false;
    if (!nextTokenIs(b, K_TRY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TRY_CATCH_EXPR, null);
    r = TryClause(b, l + 1);
    p = r; // pin = 1
    r = r && TryCatchExpr_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // CatchClause+
  private static boolean TryCatchExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryCatchExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CatchClause(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!CatchClause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TryCatchExpr_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "try"  EnclosedTryTargetExpression
  public static boolean TryClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TryClause")) return false;
    if (!nextTokenIs(b, K_TRY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TRY_CLAUSE, null);
    r = consumeToken(b, K_TRY);
    p = r; // pin = 1
    r = r && EnclosedTryTargetExpression(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "tumbling" TumblingWindowDetails
  static boolean TumblingWindowClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TumblingWindowClause")) return false;
    if (!nextTokenIs(b, K_TUMBLING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, K_TUMBLING);
    p = r; // pin = 1
    r = r && TumblingWindowDetails(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "window" "$" VarName TypeDeclaration? "in" ExprSingle WindowStartCondition WindowEndCondition?
  static boolean TumblingWindowDetails(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TumblingWindowDetails")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 0, K_WINDOW, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    r = r && TumblingWindowDetails_3(b, l + 1);
    r = r && consumeToken(b, K_IN);
    r = r && ExprSingle(b, l + 1);
    r = r && WindowStartCondition(b, l + 1);
    r = r && TumblingWindowDetails_7(b, l + 1);
    exit_section_(b, l, m, r, false, FLWORExprRecover_parser_);
    return r;
  }

  // TypeDeclaration?
  private static boolean TumblingWindowDetails_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TumblingWindowDetails_3")) return false;
    TypeDeclaration(b, l + 1);
    return true;
  }

  // WindowEndCondition?
  private static boolean TumblingWindowDetails_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TumblingWindowDetails_7")) return false;
    WindowEndCondition(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "as" SequenceType
  public static boolean TypeDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeDeclaration")) return false;
    if (!nextTokenIs(b, K_AS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_AS);
    r = r && SequenceType(b, l + 1);
    exit_section_(b, m, TYPE_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // EQName
  public static boolean TypeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeName")) return false;
    if (!nextTokenIs(b, "<type name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_NAME, "<type name>");
    r = EQName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "array" "(" SequenceType ")"
  static boolean TypedArrayTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypedArrayTest")) return false;
    if (!nextTokenIs(b, K_ARRAY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 2, K_ARRAY, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, SequenceType(b, l + 1));
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "function" "(" (SequenceType ("," SequenceType)*)? ")" "as" SequenceType
  public static boolean TypedFunctionTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypedFunctionTest")) return false;
    if (!nextTokenIs(b, K_FUNCTION)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TYPED_FUNCTION_TEST, null);
    r = consumeTokens(b, 0, K_FUNCTION, L_PAR);
    r = r && TypedFunctionTest_2(b, l + 1);
    r = r && consumeTokens(b, 1, R_PAR, K_AS);
    p = r; // pin = 4
    r = r && SequenceType(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (SequenceType ("," SequenceType)*)?
  private static boolean TypedFunctionTest_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypedFunctionTest_2")) return false;
    TypedFunctionTest_2_0(b, l + 1);
    return true;
  }

  // SequenceType ("," SequenceType)*
  private static boolean TypedFunctionTest_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypedFunctionTest_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SequenceType(b, l + 1);
    r = r && TypedFunctionTest_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," SequenceType)*
  private static boolean TypedFunctionTest_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypedFunctionTest_2_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!TypedFunctionTest_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypedFunctionTest_2_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "," SequenceType
  private static boolean TypedFunctionTest_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypedFunctionTest_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && SequenceType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "map" "(" AtomicOrUnionType "," SequenceType ")"
  static boolean TypedMapTest(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypedMapTest")) return false;
    if (!nextTokenIs(b, K_MAP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 2, K_MAP, L_PAR);
    p = r; // pin = 2
    r = r && report_error_(b, AtomicOrUnionType(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, SequenceType(b, l + 1)) && r;
    r = p && consumeToken(b, R_PAR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "default" ("$" VarName)? SwitchReturnClause
  public static boolean TypeswitchDefaultReturnClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeswitchDefaultReturnClause")) return false;
    if (!nextTokenIs(b, K_DEFAULT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TYPESWITCH_DEFAULT_RETURN_CLAUSE, null);
    r = consumeToken(b, K_DEFAULT);
    p = r; // pin = 1
    r = r && report_error_(b, TypeswitchDefaultReturnClause_1(b, l + 1));
    r = p && SwitchReturnClause(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ("$" VarName)?
  private static boolean TypeswitchDefaultReturnClause_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeswitchDefaultReturnClause_1")) return false;
    TypeswitchDefaultReturnClause_1_0(b, l + 1);
    return true;
  }

  // "$" VarName
  private static boolean TypeswitchDefaultReturnClause_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeswitchDefaultReturnClause_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR_SIGN);
    r = r && VarName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "typeswitch" "(" Expr ")" CaseClause+ TypeswitchDefaultReturnClause
  public static boolean TypeswitchExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeswitchExpr")) return false;
    if (!nextTokenIs(b, K_TYPESWITCH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TYPESWITCH_EXPR, null);
    r = consumeTokens(b, 1, K_TYPESWITCH, L_PAR);
    p = r; // pin = 1
    r = r && report_error_(b, Expr(b, l + 1));
    r = p && report_error_(b, consumeToken(b, R_PAR)) && r;
    r = p && report_error_(b, TypeswitchExpr_4(b, l + 1)) && r;
    r = p && TypeswitchDefaultReturnClause(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // CaseClause+
  private static boolean TypeswitchExpr_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeswitchExpr_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CaseClause(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!CaseClause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypeswitchExpr_4", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // StringLiteral
  public static boolean URILiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "URILiteral")) return false;
    if (!nextTokenIs(b, "<uri literal>", OPENINGAPOS, OPENINGQUOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, URI_LITERAL, "<uri literal>");
    r = StringLiteral(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ("-" | "+")* ValueExpr
  public static boolean UnaryExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, UNARY_EXPR, "<unary expr>");
    r = UnaryExpr_0(b, l + 1);
    r = r && ValueExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ("-" | "+")*
  private static boolean UnaryExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpr_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!UnaryExpr_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "UnaryExpr_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // "-" | "+"
  private static boolean UnaryExpr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpr_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_MINUS);
    if (!r) r = consumeToken(b, OP_PLUS);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "?" KeySpecifier
  public static boolean UnaryLookup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryLookup")) return false;
    if (!nextTokenIs(b, QUESTIONMARK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUESTIONMARK);
    r = r && KeySpecifier(b, l + 1);
    exit_section_(b, m, UNARY_LOOKUP, r);
    return r;
  }

  /* ********************************************************** */
  // IntersectExceptExpr UnionOptionalExpr*
  public static boolean UnionExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnionExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, UNION_EXPR, "<union expr>");
    r = IntersectExceptExpr(b, l + 1);
    r = r && UnionExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // UnionOptionalExpr*
  private static boolean UnionExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnionExpr_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!UnionOptionalExpr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "UnionExpr_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "union" | "|"
  public static boolean UnionOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnionOperator")) return false;
    if (!nextTokenIs(b, "<union operator>", K_UNION, PIPE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNION_OPERATOR, "<union operator>");
    r = consumeToken(b, K_UNION);
    if (!r) r = consumeToken(b, PIPE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // UnionOperator IntersectExceptExpr
  static boolean UnionOptionalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnionOptionalExpr")) return false;
    if (!nextTokenIs(b, "", K_UNION, PIPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = UnionOperator(b, l + 1);
    p = r; // pin = 1
    r = r && IntersectExceptExpr(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "unordered" EnclosedExpression
  public static boolean UnorderedExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnorderedExpr")) return false;
    if (!nextTokenIs(b, K_UNORDERED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_UNORDERED);
    r = r && EnclosedExpression(b, l + 1);
    exit_section_(b, m, UNORDERED_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // LocalPart
  static boolean UnprefixedName(PsiBuilder b, int l) {
    return LocalPart(b, l + 1);
  }

  /* ********************************************************** */
  // "validate" (ValidationMode | ("type" TypeName) | MarklogicValidation)? "{" Expr "}"
  public static boolean ValidateExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValidateExpr")) return false;
    if (!nextTokenIs(b, K_VALIDATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VALIDATE_EXPR, null);
    r = consumeToken(b, K_VALIDATE);
    p = r; // pin = 1
    r = r && report_error_(b, ValidateExpr_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, L_C_BRACE)) && r;
    r = p && report_error_(b, Expr(b, l + 1)) && r;
    r = p && consumeToken(b, R_C_BRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (ValidationMode | ("type" TypeName) | MarklogicValidation)?
  private static boolean ValidateExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValidateExpr_1")) return false;
    ValidateExpr_1_0(b, l + 1);
    return true;
  }

  // ValidationMode | ("type" TypeName) | MarklogicValidation
  private static boolean ValidateExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValidateExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ValidationMode(b, l + 1);
    if (!r) r = ValidateExpr_1_0_1(b, l + 1);
    if (!r) r = MarklogicValidation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "type" TypeName
  private static boolean ValidateExpr_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValidateExpr_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_TYPE);
    r = r && TypeName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "lax" | "strict"
  static boolean ValidationMode(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValidationMode")) return false;
    if (!nextTokenIs(b, "", K_LAX, K_STRICT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_LAX);
    if (!r) r = consumeToken(b, K_STRICT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "eq" | "ne" | "lt" | "le" | "gt" | "ge"
  public static boolean ValueComp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueComp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE_COMP, "<value comp>");
    r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, NE);
    if (!r) r = consumeToken(b, LT);
    if (!r) r = consumeToken(b, LE);
    if (!r) r = consumeToken(b, GT);
    if (!r) r = consumeToken(b, GE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ValidateExpr | ExtensionExpr | SimpleMapExpr
  public static boolean ValueExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, VALUE_EXPR, "<value expr>");
    r = ValidateExpr(b, l + 1);
    if (!r) r = ExtensionExpr(b, l + 1);
    if (!r) r = SimpleMapExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "declare" (MarklogicAnnotation | CompatibilityAnnotation | Annotation)* "variable" "$" VarName TypeDeclaration? (VarDetails) Separator
  public static boolean VarDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl")) return false;
    if (!nextTokenIs(b, K_DECLARE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VAR_DECL, null);
    r = consumeToken(b, K_DECLARE);
    r = r && VarDecl_1(b, l + 1);
    r = r && consumeTokens(b, 1, K_VARIABLE, DOLLAR_SIGN);
    p = r; // pin = 3
    r = r && report_error_(b, VarName(b, l + 1));
    r = p && report_error_(b, VarDecl_5(b, l + 1)) && r;
    r = p && report_error_(b, VarDecl_6(b, l + 1)) && r;
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (MarklogicAnnotation | CompatibilityAnnotation | Annotation)*
  private static boolean VarDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!VarDecl_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "VarDecl_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // MarklogicAnnotation | CompatibilityAnnotation | Annotation
  private static boolean VarDecl_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MarklogicAnnotation(b, l + 1);
    if (!r) r = CompatibilityAnnotation(b, l + 1);
    if (!r) r = Annotation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TypeDeclaration?
  private static boolean VarDecl_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_5")) return false;
    TypeDeclaration(b, l + 1);
    return true;
  }

  // (VarDetails)
  private static boolean VarDecl_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarDetails(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean VarDefaultValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDefaultValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR_DEFAULT_VALUE, "<var default value>");
    r = ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // VarValueAssignment | ExternalVarPart
  static boolean VarDetails(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDetails")) return false;
    if (!nextTokenIs(b, "", OP_ASSIGN, K_EXTERNAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VarValueAssignment(b, l + 1);
    if (!r) r = ExternalVarPart(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NCName
  public static boolean VarLocalName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarLocalName")) return false;
    if (!nextTokenIs(b, NCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NCNAME);
    exit_section_(b, m, VAR_LOCAL_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // Prefix ':' VarLocalName | VarLocalName | URIQualifiedName
  public static boolean VarName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarName")) return false;
    if (!nextTokenIs(b, "<var name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR_NAME, "<var name>");
    r = VarName_0(b, l + 1);
    if (!r) r = VarLocalName(b, l + 1);
    if (!r) r = consumeToken(b, URIQUALIFIEDNAME);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Prefix ':' VarLocalName
  private static boolean VarName_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarName_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = Prefix(b, l + 1);
    r = r && consumeToken(b, COLON);
    p = r; // pin = 2
    r = r && VarLocalName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "$" VarName
  public static boolean VarRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarRef")) return false;
    if (!nextTokenIs(b, DOLLAR_SIGN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VAR_REF, null);
    r = consumeToken(b, DOLLAR_SIGN);
    p = r; // pin = 1
    r = r && VarName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean VarValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR_VALUE, "<var value>");
    r = ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ":=" VarValue
  static boolean VarValueAssignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarValueAssignment")) return false;
    if (!nextTokenIs(b, OP_ASSIGN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, OP_ASSIGN);
    p = r; // pin = 1
    r = r && VarValue(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // StringLiteral
  public static boolean Version(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Version")) return false;
    if (!nextTokenIs(b, "<version>", OPENINGAPOS, OPENINGQUOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VERSION, "<version>");
    r = StringLiteral(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "xquery" ((VersionDeclEncoding) | (VersionDeclVersion)) Separator
  public static boolean VersionDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDecl")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VERSION_DECL, "<version decl>");
    r = consumeToken(b, K_XQUERY);
    p = r; // pin = 1
    r = r && report_error_(b, VersionDecl_1(b, l + 1));
    r = p && Separator(b, l + 1) && r;
    exit_section_(b, l, m, r, p, VersionDeclRecover_parser_);
    return r || p;
  }

  // (VersionDeclEncoding) | (VersionDeclVersion)
  private static boolean VersionDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDecl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VersionDecl_1_0(b, l + 1);
    if (!r) r = VersionDecl_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (VersionDeclEncoding)
  private static boolean VersionDecl_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDecl_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VersionDeclEncoding(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (VersionDeclVersion)
  private static boolean VersionDecl_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDecl_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VersionDeclVersion(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "encoding" StringLiteral
  static boolean VersionDeclEncoding(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDeclEncoding")) return false;
    if (!nextTokenIs(b, K_ENCODING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, K_ENCODING);
    p = r; // pin = 1
    r = r && StringLiteral(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // !('$' | '%' | '(#' | '(' | '*' | '+' | '-' | '.' | '..' | '/' | '//' | '<!--' | '<?' | '?' | '@' | '[' | '``[' | 'ancestor' | 'ancestor-or-self' | 'array' | 'array-node' | 'attribute' | 'binary' | 'boolean-node' | 'child' | 'comment' | 'copy' | 'declare' | 'delete' | 'descendant' | 'descendant-or-self' | 'document' | 'document-node' | 'element' | 'every' | 'following' | 'following-sibling' | 'for' | 'function' | 'if' | 'import' | 'insert' | 'let' | 'map' | 'module' | 'namespace' | 'namespace-node' | 'node' | 'null-node' | 'number-node' | 'object-node' | 'ordered' | 'parent' | 'preceding' | 'preceding-sibling' | 'processing-instruction' | 'rename' | 'replace' | 'schema-attribute' | 'schema-element' | 'self' | 'some' | 'switch' | 'text' | 'try' | 'typeswitch' | 'unordered' | 'validate' | <<eof>> | BracedURILiteral | DecimalLiteral | DoubleLiteral | IntegerLiteral | NCName | OpeningApos | OpeningQuot | URIQualifiedName | XmlStartTagStart)
  static boolean VersionDeclRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDeclRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !VersionDeclRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '$' | '%' | '(#' | '(' | '*' | '+' | '-' | '.' | '..' | '/' | '//' | '<!--' | '<?' | '?' | '@' | '[' | '``[' | 'ancestor' | 'ancestor-or-self' | 'array' | 'array-node' | 'attribute' | 'binary' | 'boolean-node' | 'child' | 'comment' | 'copy' | 'declare' | 'delete' | 'descendant' | 'descendant-or-self' | 'document' | 'document-node' | 'element' | 'every' | 'following' | 'following-sibling' | 'for' | 'function' | 'if' | 'import' | 'insert' | 'let' | 'map' | 'module' | 'namespace' | 'namespace-node' | 'node' | 'null-node' | 'number-node' | 'object-node' | 'ordered' | 'parent' | 'preceding' | 'preceding-sibling' | 'processing-instruction' | 'rename' | 'replace' | 'schema-attribute' | 'schema-element' | 'self' | 'some' | 'switch' | 'text' | 'try' | 'typeswitch' | 'unordered' | 'validate' | <<eof>> | BracedURILiteral | DecimalLiteral | DoubleLiteral | IntegerLiteral | NCName | OpeningApos | OpeningQuot | URIQualifiedName | XmlStartTagStart
  private static boolean VersionDeclRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDeclRecover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR_SIGN);
    if (!r) r = consumeToken(b, PERCENT);
    if (!r) r = consumeToken(b, PRAGMA_BEGIN);
    if (!r) r = consumeToken(b, L_PAR);
    if (!r) r = consumeToken(b, STAR_SIGN);
    if (!r) r = consumeToken(b, OP_PLUS);
    if (!r) r = consumeToken(b, OP_MINUS);
    if (!r) r = consumeToken(b, DOT);
    if (!r) r = consumeToken(b, DOT_DOT);
    if (!r) r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, SLASH_SLASH);
    if (!r) r = consumeToken(b, DIR_COMMENT_BEGIN);
    if (!r) r = consumeToken(b, PI_BEGIN);
    if (!r) r = consumeToken(b, QUESTIONMARK);
    if (!r) r = consumeToken(b, AT_SIGN);
    if (!r) r = consumeToken(b, L_BRACKET);
    if (!r) r = consumeToken(b, STRING_CONSTR_START);
    if (!r) r = consumeToken(b, K_ANCESTOR);
    if (!r) r = consumeToken(b, K_ANCESTOR_OR_SELF);
    if (!r) r = consumeToken(b, K_ARRAY);
    if (!r) r = consumeToken(b, K_ARRAY_NODE);
    if (!r) r = consumeToken(b, K_ATTRIBUTE);
    if (!r) r = consumeToken(b, K_BINARY);
    if (!r) r = consumeToken(b, K_BOOLEAN_NODE);
    if (!r) r = consumeToken(b, K_CHILD);
    if (!r) r = consumeToken(b, K_COMMENT);
    if (!r) r = consumeToken(b, K_COPY);
    if (!r) r = consumeToken(b, K_DECLARE);
    if (!r) r = consumeToken(b, K_DELETE);
    if (!r) r = consumeToken(b, K_DESCENDANT);
    if (!r) r = consumeToken(b, K_DESCENDANT_OR_SELF);
    if (!r) r = consumeToken(b, K_DOCUMENT);
    if (!r) r = consumeToken(b, K_DOCUMENT_NODE);
    if (!r) r = consumeToken(b, K_ELEMENT);
    if (!r) r = consumeToken(b, K_EVERY);
    if (!r) r = consumeToken(b, K_FOLLOWING);
    if (!r) r = consumeToken(b, K_FOLLOWING_SIBLING);
    if (!r) r = consumeToken(b, K_FOR);
    if (!r) r = consumeToken(b, K_FUNCTION);
    if (!r) r = consumeToken(b, K_IF);
    if (!r) r = consumeToken(b, K_IMPORT);
    if (!r) r = consumeToken(b, K_INSERT);
    if (!r) r = consumeToken(b, K_LET);
    if (!r) r = consumeToken(b, K_MAP);
    if (!r) r = consumeToken(b, K_MODULE);
    if (!r) r = consumeToken(b, K_NAMESPACE);
    if (!r) r = consumeToken(b, K_NAMESPACE_NODE);
    if (!r) r = consumeToken(b, K_NODE);
    if (!r) r = consumeToken(b, K_NULL_NODE);
    if (!r) r = consumeToken(b, K_NUMBER_NODE);
    if (!r) r = consumeToken(b, K_OBJECT_NODE);
    if (!r) r = consumeToken(b, K_ORDERED);
    if (!r) r = consumeToken(b, K_PARENT);
    if (!r) r = consumeToken(b, K_PRECEDING);
    if (!r) r = consumeToken(b, K_PRECEDING_SIBLING);
    if (!r) r = consumeToken(b, K_PI);
    if (!r) r = consumeToken(b, K_RENAME);
    if (!r) r = consumeToken(b, K_REPLACE);
    if (!r) r = consumeToken(b, K_SCHEMA_ATTRIBUTE);
    if (!r) r = consumeToken(b, K_SCHEMA_ELEMENT);
    if (!r) r = consumeToken(b, K_SELF);
    if (!r) r = consumeToken(b, K_SOME);
    if (!r) r = consumeToken(b, K_SWITCH);
    if (!r) r = consumeToken(b, K_TEXT);
    if (!r) r = consumeToken(b, K_TRY);
    if (!r) r = consumeToken(b, K_TYPESWITCH);
    if (!r) r = consumeToken(b, K_UNORDERED);
    if (!r) r = consumeToken(b, K_VALIDATE);
    if (!r) r = eof(b, l + 1);
    if (!r) r = consumeToken(b, BRACEDURILITERAL);
    if (!r) r = consumeToken(b, DECIMALLITERAL);
    if (!r) r = consumeToken(b, DOUBLELITERAL);
    if (!r) r = consumeToken(b, INTEGERLITERAL);
    if (!r) r = consumeToken(b, NCNAME);
    if (!r) r = consumeToken(b, OPENINGAPOS);
    if (!r) r = consumeToken(b, OPENINGQUOT);
    if (!r) r = consumeToken(b, URIQUALIFIEDNAME);
    if (!r) r = consumeToken(b, XMLSTARTTAGSTART);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "version" Version ("encoding" StringLiteral)?
  static boolean VersionDeclVersion(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDeclVersion")) return false;
    if (!nextTokenIs(b, K_VERSION)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, K_VERSION);
    p = r; // pin = 1
    r = r && report_error_(b, Version(b, l + 1));
    r = p && VersionDeclVersion_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ("encoding" StringLiteral)?
  private static boolean VersionDeclVersion_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDeclVersion_2")) return false;
    VersionDeclVersion_2_0(b, l + 1);
    return true;
  }

  // "encoding" StringLiteral
  private static boolean VersionDeclVersion_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VersionDeclVersion_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_ENCODING);
    r = r && StringLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "where" ExprSingle
  public static boolean WhereClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhereClause")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, WHERE_CLAUSE, "<where clause>");
    r = consumeToken(b, K_WHERE);
    p = r; // pin = 1
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, l, m, r, p, FLWORExprRecover_parser_);
    return r || p;
  }

  /* ********************************************************** */
  // (Prefix ":" "*")
  //  | ("*" ":" NCName)
  //  | "*"
  //  | (BracedURILiteral "*")
  public static boolean Wildcard(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Wildcard")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, WILDCARD, "<wildcard>");
    r = Wildcard_0(b, l + 1);
    if (!r) r = Wildcard_1(b, l + 1);
    if (!r) r = consumeToken(b, STAR_SIGN);
    if (!r) r = Wildcard_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Prefix ":" "*"
  private static boolean Wildcard_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Wildcard_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Prefix(b, l + 1);
    r = r && consumeTokens(b, 0, COLON, STAR_SIGN);
    exit_section_(b, m, null, r);
    return r;
  }

  // "*" ":" NCName
  private static boolean Wildcard_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Wildcard_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, STAR_SIGN, COLON, NCNAME);
    exit_section_(b, m, null, r);
    return r;
  }

  // BracedURILiteral "*"
  private static boolean Wildcard_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Wildcard_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BRACEDURILITERAL, STAR_SIGN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "for" (TumblingWindowClause | SlidingWindowClause)
  public static boolean WindowClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowClause")) return false;
    if (!nextTokenIs(b, K_FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_FOR);
    r = r && WindowClause_1(b, l + 1);
    exit_section_(b, m, WINDOW_CLAUSE, r);
    return r;
  }

  // TumblingWindowClause | SlidingWindowClause
  private static boolean WindowClause_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowClause_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TumblingWindowClause(b, l + 1);
    if (!r) r = SlidingWindowClause(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "only"? "end" WindowVars "when" ExprSingle
  static boolean WindowEndCondition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowEndCondition")) return false;
    if (!nextTokenIs(b, "", K_END, K_ONLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = WindowEndCondition_0(b, l + 1);
    r = r && consumeToken(b, K_END);
    r = r && WindowVars(b, l + 1);
    r = r && consumeToken(b, K_WHEN);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "only"?
  private static boolean WindowEndCondition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowEndCondition_0")) return false;
    consumeToken(b, K_ONLY);
    return true;
  }

  /* ********************************************************** */
  // "start" WindowVars "when" ExprSingle
  static boolean WindowStartCondition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowStartCondition")) return false;
    if (!nextTokenIs(b, K_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, K_START);
    r = r && WindowVars(b, l + 1);
    r = r && consumeToken(b, K_WHEN);
    r = r && ExprSingle(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ("$" CurrentItem)? PositionalVar? ("previous" "$" PreviousItem)? ("next" "$" NextItem)?
  static boolean WindowVars(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowVars")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = WindowVars_0(b, l + 1);
    r = r && WindowVars_1(b, l + 1);
    r = r && WindowVars_2(b, l + 1);
    r = r && WindowVars_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("$" CurrentItem)?
  private static boolean WindowVars_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowVars_0")) return false;
    WindowVars_0_0(b, l + 1);
    return true;
  }

  // "$" CurrentItem
  private static boolean WindowVars_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowVars_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOLLAR_SIGN);
    r = r && CurrentItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PositionalVar?
  private static boolean WindowVars_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowVars_1")) return false;
    PositionalVar(b, l + 1);
    return true;
  }

  // ("previous" "$" PreviousItem)?
  private static boolean WindowVars_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowVars_2")) return false;
    WindowVars_2_0(b, l + 1);
    return true;
  }

  // "previous" "$" PreviousItem
  private static boolean WindowVars_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowVars_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_PREVIOUS, DOLLAR_SIGN);
    r = r && PreviousItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("next" "$" NextItem)?
  private static boolean WindowVars_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowVars_3")) return false;
    WindowVars_3_0(b, l + 1);
    return true;
  }

  // "next" "$" NextItem
  private static boolean WindowVars_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WindowVars_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, K_NEXT, DOLLAR_SIGN);
    r = r && NextItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // XmlEndTagStart XmlTagName MisplacedComment* XmlTagEnd
  static boolean XmlClosingTagPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlClosingTagPart")) return false;
    if (!nextTokenIs(b, XMLENDTAGSTART)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, XMLENDTAGSTART);
    p = r; // pin = 1
    r = r && report_error_(b, XmlTagName(b, l + 1));
    r = p && report_error_(b, XmlClosingTagPart_2(b, l + 1)) && r;
    r = p && consumeToken(b, XMLTAGEND) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // MisplacedComment*
  private static boolean XmlClosingTagPart_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlClosingTagPart_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MisplacedComment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "XmlClosingTagPart_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // XmlStartTagStart XmlTagName MisplacedComment* DirAttributeList? MisplacedComment* XmlEmptyElementEnd
  public static boolean XmlEmptyTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlEmptyTag")) return false;
    if (!nextTokenIs(b, XMLSTARTTAGSTART)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, XMLSTARTTAGSTART);
    r = r && XmlTagName(b, l + 1);
    r = r && XmlEmptyTag_2(b, l + 1);
    r = r && XmlEmptyTag_3(b, l + 1);
    r = r && XmlEmptyTag_4(b, l + 1);
    r = r && consumeToken(b, XMLEMPTYELEMENTEND);
    exit_section_(b, m, XML_EMPTY_TAG, r);
    return r;
  }

  // MisplacedComment*
  private static boolean XmlEmptyTag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlEmptyTag_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MisplacedComment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "XmlEmptyTag_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // DirAttributeList?
  private static boolean XmlEmptyTag_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlEmptyTag_3")) return false;
    DirAttributeList(b, l + 1);
    return true;
  }

  // MisplacedComment*
  private static boolean XmlEmptyTag_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlEmptyTag_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MisplacedComment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "XmlEmptyTag_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // XmlOpeningTagPart DirElemContent* XmlClosingTagPart
  public static boolean XmlFullTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlFullTag")) return false;
    if (!nextTokenIs(b, XMLSTARTTAGSTART)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, XML_FULL_TAG, null);
    r = XmlOpeningTagPart(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, XmlFullTag_1(b, l + 1));
    r = p && XmlClosingTagPart(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // DirElemContent*
  private static boolean XmlFullTag_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlFullTag_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!DirElemContent(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "XmlFullTag_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // XmlStartTagStart XmlTagName MisplacedComment* DirAttributeList? MisplacedComment* XmlTagEnd
  static boolean XmlOpeningTagPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlOpeningTagPart")) return false;
    if (!nextTokenIs(b, XMLSTARTTAGSTART)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, XMLSTARTTAGSTART);
    p = r; // pin = 1
    r = r && report_error_(b, XmlTagName(b, l + 1));
    r = p && report_error_(b, XmlOpeningTagPart_2(b, l + 1)) && r;
    r = p && report_error_(b, XmlOpeningTagPart_3(b, l + 1)) && r;
    r = p && report_error_(b, XmlOpeningTagPart_4(b, l + 1)) && r;
    r = p && consumeToken(b, XMLTAGEND) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // MisplacedComment*
  private static boolean XmlOpeningTagPart_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlOpeningTagPart_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MisplacedComment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "XmlOpeningTagPart_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // DirAttributeList?
  private static boolean XmlOpeningTagPart_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlOpeningTagPart_3")) return false;
    DirAttributeList(b, l + 1);
    return true;
  }

  // MisplacedComment*
  private static boolean XmlOpeningTagPart_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlOpeningTagPart_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MisplacedComment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "XmlOpeningTagPart_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // XmlTagNCName
  public static boolean XmlTagLocalName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlTagLocalName")) return false;
    if (!nextTokenIs(b, XMLTAGNCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, XMLTAGNCNAME);
    exit_section_(b, m, XML_TAG_LOCAL_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // XmlTagNamespace XmlColon XmlTagLocalName | XmlTagLocalName
  public static boolean XmlTagName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlTagName")) return false;
    if (!nextTokenIs(b, XMLTAGNCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = XmlTagName_0(b, l + 1);
    if (!r) r = XmlTagLocalName(b, l + 1);
    exit_section_(b, m, XML_TAG_NAME, r);
    return r;
  }

  // XmlTagNamespace XmlColon XmlTagLocalName
  private static boolean XmlTagName_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlTagName_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = XmlTagNamespace(b, l + 1);
    r = r && consumeToken(b, XMLCOLON);
    r = r && XmlTagLocalName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // XmlTagNCName
  public static boolean XmlTagNamespace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "XmlTagNamespace")) return false;
    if (!nextTokenIs(b, XMLTAGNCNAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, XMLTAGNCNAME);
    exit_section_(b, m, XML_TAG_NAMESPACE, r);
    return r;
  }

  /* ********************************************************** */
  // Module
  static boolean xqueryFile(PsiBuilder b, int l) {
    return Module(b, l + 1);
  }

  final static Parser FLWORExprRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return FLWORExprRecover(b, l + 1);
    }
  };
  final static Parser ModuleDeclRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return ModuleDeclRecover(b, l + 1);
    }
  };
  final static Parser QueryBodyRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return QueryBodyRecover(b, l + 1);
    }
  };
  final static Parser VersionDeclRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return VersionDeclRecover(b, l + 1);
    }
  };
}
