/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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
import com.intellij.openapi.diagnostic.Logger;
import static org.intellij.xquery.psi.XQueryTypes.*;
import static org.intellij.grammar.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class XQueryParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("org.intellij.xquery.parser.XQueryParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, EXTENDS_SETS_);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == ABBREV_FORWARD_STEP) {
      result_ = AbbrevForwardStep(builder_, 0);
    }
    else if (root_ == ABBREV_REVERSE_STEP) {
      result_ = AbbrevReverseStep(builder_, 0);
    }
    else if (root_ == ADDITIVE_EXPR) {
      result_ = AdditiveExpr(builder_, 0);
    }
    else if (root_ == ADDITIVE_OPERATOR) {
      result_ = AdditiveOperator(builder_, 0);
    }
    else if (root_ == ALLOWING_EMPTY) {
      result_ = AllowingEmpty(builder_, 0);
    }
    else if (root_ == AND_EXPR) {
      result_ = AndExpr(builder_, 0);
    }
    else if (root_ == AND_OPERATOR) {
      result_ = AndOperator(builder_, 0);
    }
    else if (root_ == ANNOTATION) {
      result_ = Annotation(builder_, 0);
    }
    else if (root_ == ANNOTATION_NAME) {
      result_ = AnnotationName(builder_, 0);
    }
    else if (root_ == ANY_FUNCTION_TEST) {
      result_ = AnyFunctionTest(builder_, 0);
    }
    else if (root_ == ANY_KIND_TEST) {
      result_ = AnyKindTest(builder_, 0);
    }
    else if (root_ == APOS_ATTR_CONTENT_CHAR) {
      result_ = AposAttrContentChar(builder_, 0);
    }
    else if (root_ == APOS_ATTR_VALUE_CONTENT) {
      result_ = AposAttrValueContent(builder_, 0);
    }
    else if (root_ == ARGUMENT) {
      result_ = Argument(builder_, 0);
    }
    else if (root_ == ARGUMENT_LIST) {
      result_ = ArgumentList(builder_, 0);
    }
    else if (root_ == ARGUMENT_PLACEHOLDER) {
      result_ = ArgumentPlaceholder(builder_, 0);
    }
    else if (root_ == ATOMIC_OR_UNION_TYPE) {
      result_ = AtomicOrUnionType(builder_, 0);
    }
    else if (root_ == ATTR_LOCAL_NAME) {
      result_ = AttrLocalName(builder_, 0);
    }
    else if (root_ == ATTR_NAMESPACE) {
      result_ = AttrNamespace(builder_, 0);
    }
    else if (root_ == ATTRIB_NAME_OR_WILDCARD) {
      result_ = AttribNameOrWildcard(builder_, 0);
    }
    else if (root_ == ATTRIBUTE_DECLARATION) {
      result_ = AttributeDeclaration(builder_, 0);
    }
    else if (root_ == ATTRIBUTE_NAME) {
      result_ = AttributeName(builder_, 0);
    }
    else if (root_ == ATTRIBUTE_TEST) {
      result_ = AttributeTest(builder_, 0);
    }
    else if (root_ == AXIS_STEP) {
      result_ = AxisStep(builder_, 0);
    }
    else if (root_ == BASE_URI_DECL) {
      result_ = BaseURIDecl(builder_, 0);
    }
    else if (root_ == BOUNDARY_SPACE_DECL) {
      result_ = BoundarySpaceDecl(builder_, 0);
    }
    else if (root_ == C_DATA_SECTION) {
      result_ = CDataSection(builder_, 0);
    }
    else if (root_ == C_DATA_SECTION_CONTENTS) {
      result_ = CDataSectionContents(builder_, 0);
    }
    else if (root_ == CASE_CLAUSE) {
      result_ = CaseClause(builder_, 0);
    }
    else if (root_ == CAST_EXPR) {
      result_ = CastExpr(builder_, 0);
    }
    else if (root_ == CAST_OPERATOR) {
      result_ = CastOperator(builder_, 0);
    }
    else if (root_ == CASTABLE_EXPR) {
      result_ = CastableExpr(builder_, 0);
    }
    else if (root_ == CASTABLE_OPERATOR) {
      result_ = CastableOperator(builder_, 0);
    }
    else if (root_ == CATCH_CLAUSE) {
      result_ = CatchClause(builder_, 0);
    }
    else if (root_ == CATCH_ERROR_LIST) {
      result_ = CatchErrorList(builder_, 0);
    }
    else if (root_ == COMMENT_TEST) {
      result_ = CommentTest(builder_, 0);
    }
    else if (root_ == COMMON_CONTENT) {
      result_ = CommonContent(builder_, 0);
    }
    else if (root_ == COMP_ATTR_CONSTRUCTOR) {
      result_ = CompAttrConstructor(builder_, 0);
    }
    else if (root_ == COMP_COMMENT_CONSTRUCTOR) {
      result_ = CompCommentConstructor(builder_, 0);
    }
    else if (root_ == COMP_DOC_CONSTRUCTOR) {
      result_ = CompDocConstructor(builder_, 0);
    }
    else if (root_ == COMP_ELEM_CONSTRUCTOR) {
      result_ = CompElemConstructor(builder_, 0);
    }
    else if (root_ == COMP_MAP_CONSTRUCTOR) {
      result_ = CompMapConstructor(builder_, 0);
    }
    else if (root_ == COMP_NAMESPACE_CONSTRUCTOR) {
      result_ = CompNamespaceConstructor(builder_, 0);
    }
    else if (root_ == COMP_PI_CONSTRUCTOR) {
      result_ = CompPIConstructor(builder_, 0);
    }
    else if (root_ == COMP_TEXT_CONSTRUCTOR) {
      result_ = CompTextConstructor(builder_, 0);
    }
    else if (root_ == COMPARISON_EXPR) {
      result_ = ComparisonExpr(builder_, 0);
    }
    else if (root_ == COMPATIBILITY_ANNOTATION) {
      result_ = CompatibilityAnnotation(builder_, 0);
    }
    else if (root_ == COMPUTED_CONSTRUCTOR) {
      result_ = ComputedConstructor(builder_, 0);
    }
    else if (root_ == CONCAT_OPERATOR) {
      result_ = ConcatOperator(builder_, 0);
    }
    else if (root_ == CONSTRUCTION_DECL) {
      result_ = ConstructionDecl(builder_, 0);
    }
    else if (root_ == CONSTRUCTOR) {
      result_ = Constructor(builder_, 0);
    }
    else if (root_ == CONTENT_EXPR) {
      result_ = ContentExpr(builder_, 0);
    }
    else if (root_ == CONTEXT_ITEM_DECL) {
      result_ = ContextItemDecl(builder_, 0);
    }
    else if (root_ == CONTEXT_ITEM_EXPR) {
      result_ = ContextItemExpr(builder_, 0);
    }
    else if (root_ == COPY_NAMESPACES_DECL) {
      result_ = CopyNamespacesDecl(builder_, 0);
    }
    else if (root_ == COUNT_CLAUSE) {
      result_ = CountClause(builder_, 0);
    }
    else if (root_ == CURRENT_ITEM) {
      result_ = CurrentItem(builder_, 0);
    }
    else if (root_ == DECIMAL_FORMAT_DECL) {
      result_ = DecimalFormatDecl(builder_, 0);
    }
    else if (root_ == DEFAULT_COLLATION_DECL) {
      result_ = DefaultCollationDecl(builder_, 0);
    }
    else if (root_ == DEFAULT_ELEMENT_NAMESPACE_DECL) {
      result_ = DefaultElementNamespaceDecl(builder_, 0);
    }
    else if (root_ == DEFAULT_FUNCTION_NAMESPACE_DECL) {
      result_ = DefaultFunctionNamespaceDecl(builder_, 0);
    }
    else if (root_ == DELETE_EXPR) {
      result_ = DeleteExpr(builder_, 0);
    }
    else if (root_ == DIR_ATTRIBUTE_LIST) {
      result_ = DirAttributeList(builder_, 0);
    }
    else if (root_ == DIR_ATTRIBUTE_NAME) {
      result_ = DirAttributeName(builder_, 0);
    }
    else if (root_ == DIR_ATTRIBUTE_VALUE) {
      result_ = DirAttributeValue(builder_, 0);
    }
    else if (root_ == DIR_COMMENT_CONSTRUCTOR) {
      result_ = DirCommentConstructor(builder_, 0);
    }
    else if (root_ == DIR_COMMENT_CONTENTS) {
      result_ = DirCommentContents(builder_, 0);
    }
    else if (root_ == DIR_ELEM_CONTENT) {
      result_ = DirElemContent(builder_, 0);
    }
    else if (root_ == DIR_PI_CONSTRUCTOR) {
      result_ = DirPIConstructor(builder_, 0);
    }
    else if (root_ == DIR_PI_CONTENTS) {
      result_ = DirPIContents(builder_, 0);
    }
    else if (root_ == DIRECT_CONSTRUCTOR) {
      result_ = DirectConstructor(builder_, 0);
    }
    else if (root_ == DOCUMENT_TEST) {
      result_ = DocumentTest(builder_, 0);
    }
    else if (root_ == ELEMENT_DECLARATION) {
      result_ = ElementDeclaration(builder_, 0);
    }
    else if (root_ == ELEMENT_NAME) {
      result_ = ElementName(builder_, 0);
    }
    else if (root_ == ELEMENT_NAME_OR_WILDCARD) {
      result_ = ElementNameOrWildcard(builder_, 0);
    }
    else if (root_ == ELEMENT_TEST) {
      result_ = ElementTest(builder_, 0);
    }
    else if (root_ == EMPTY_ORDER_DECL) {
      result_ = EmptyOrderDecl(builder_, 0);
    }
    else if (root_ == ENCLOSED_EXPR) {
      result_ = EnclosedExpr(builder_, 0);
    }
    else if (root_ == EQUALITY_COMP) {
      result_ = EqualityComp(builder_, 0);
    }
    else if (root_ == ESCAPE_APOS) {
      result_ = EscapeApos(builder_, 0);
    }
    else if (root_ == ESCAPE_QUOT) {
      result_ = EscapeQuot(builder_, 0);
    }
    else if (root_ == EXPR) {
      result_ = Expr(builder_, 0);
    }
    else if (root_ == EXPR_SINGLE) {
      result_ = ExprSingle(builder_, 0);
    }
    else if (root_ == EXTENSION_EXPR) {
      result_ = ExtensionExpr(builder_, 0);
    }
    else if (root_ == EXTERNAL_VAR_PART) {
      result_ = ExternalVarPart(builder_, 0);
    }
    else if (root_ == FLWOR_EXPR) {
      result_ = FLWORExpr(builder_, 0);
    }
    else if (root_ == FOR_BINDING) {
      result_ = ForBinding(builder_, 0);
    }
    else if (root_ == FOR_CLAUSE) {
      result_ = ForClause(builder_, 0);
    }
    else if (root_ == FORWARD_STEP) {
      result_ = ForwardStep(builder_, 0);
    }
    else if (root_ == FUNCTION_ARITY) {
      result_ = FunctionArity(builder_, 0);
    }
    else if (root_ == FUNCTION_BODY) {
      result_ = FunctionBody(builder_, 0);
    }
    else if (root_ == FUNCTION_CALL) {
      result_ = FunctionCall(builder_, 0);
    }
    else if (root_ == FUNCTION_DECL) {
      result_ = FunctionDecl(builder_, 0);
    }
    else if (root_ == FUNCTION_ITEM_EXPR) {
      result_ = FunctionItemExpr(builder_, 0);
    }
    else if (root_ == FUNCTION_LOCAL_NAME) {
      result_ = FunctionLocalName(builder_, 0);
    }
    else if (root_ == FUNCTION_NAME) {
      result_ = FunctionName(builder_, 0);
    }
    else if (root_ == FUNCTION_TEST) {
      result_ = FunctionTest(builder_, 0);
    }
    else if (root_ == GENERAL_ITEM_TYPE) {
      result_ = GeneralItemType(builder_, 0);
    }
    else if (root_ == GROUP_BY_CLAUSE) {
      result_ = GroupByClause(builder_, 0);
    }
    else if (root_ == GROUPING_SPEC) {
      result_ = GroupingSpec(builder_, 0);
    }
    else if (root_ == GROUPING_SPEC_LIST) {
      result_ = GroupingSpecList(builder_, 0);
    }
    else if (root_ == GROUPING_VARIABLE) {
      result_ = GroupingVariable(builder_, 0);
    }
    else if (root_ == IF_EXPR) {
      result_ = IfExpr(builder_, 0);
    }
    else if (root_ == INHERIT_MODE) {
      result_ = InheritMode(builder_, 0);
    }
    else if (root_ == INLINE_FUNCTION_EXPR) {
      result_ = InlineFunctionExpr(builder_, 0);
    }
    else if (root_ == INSERT_EXPR) {
      result_ = InsertExpr(builder_, 0);
    }
    else if (root_ == INSERT_EXPR_TARGET_CHOICE) {
      result_ = InsertExprTargetChoice(builder_, 0);
    }
    else if (root_ == INSTANCE_OF_OPERATOR) {
      result_ = InstanceOfOperator(builder_, 0);
    }
    else if (root_ == INSTANCEOF_EXPR) {
      result_ = InstanceofExpr(builder_, 0);
    }
    else if (root_ == INTERSECT_EXCEPT_EXPR) {
      result_ = IntersectExceptExpr(builder_, 0);
    }
    else if (root_ == INTERSECT_EXCEPT_OPERATOR) {
      result_ = IntersectExceptOperator(builder_, 0);
    }
    else if (root_ == ITEM_TYPE) {
      result_ = ItemType(builder_, 0);
    }
    else if (root_ == KIND_TEST) {
      result_ = KindTest(builder_, 0);
    }
    else if (root_ == LET_BINDING) {
      result_ = LetBinding(builder_, 0);
    }
    else if (root_ == LET_CLAUSE) {
      result_ = LetClause(builder_, 0);
    }
    else if (root_ == LITERAL) {
      result_ = Literal(builder_, 0);
    }
    else if (root_ == LOCAL_PART) {
      result_ = LocalPart(builder_, 0);
    }
    else if (root_ == MAP_ENTRY) {
      result_ = MapEntry(builder_, 0);
    }
    else if (root_ == MAP_ENTRY_LIST) {
      result_ = MapEntryList(builder_, 0);
    }
    else if (root_ == MAP_TEST) {
      result_ = MapTest(builder_, 0);
    }
    else if (root_ == MARKLOGIC_ANNOTATION) {
      result_ = MarklogicAnnotation(builder_, 0);
    }
    else if (root_ == MARKLOGIC_CATCH_ERROR_LIST) {
      result_ = MarklogicCatchErrorList(builder_, 0);
    }
    else if (root_ == MARKLOGIC_NAMESPACE_AXIS) {
      result_ = MarklogicNamespaceAxis(builder_, 0);
    }
    else if (root_ == MODULE_DECL) {
      result_ = ModuleDecl(builder_, 0);
    }
    else if (root_ == MODULE_IMPORT) {
      result_ = ModuleImport(builder_, 0);
    }
    else if (root_ == MODULE_IMPORT_NAMESPACE) {
      result_ = ModuleImportNamespace(builder_, 0);
    }
    else if (root_ == MODULE_IMPORT_PATH) {
      result_ = ModuleImportPath(builder_, 0);
    }
    else if (root_ == MULTI_VARIABLE_BINDING) {
      result_ = MultiVariableBinding(builder_, 0);
    }
    else if (root_ == MULTIPLICATIVE_EXPR) {
      result_ = MultiplicativeExpr(builder_, 0);
    }
    else if (root_ == MULTIPLICATIVE_OPERATOR) {
      result_ = MultiplicativeOperator(builder_, 0);
    }
    else if (root_ == NAME_TEST) {
      result_ = NameTest(builder_, 0);
    }
    else if (root_ == NAMED_FUNCTION_REF) {
      result_ = NamedFunctionRef(builder_, 0);
    }
    else if (root_ == NAMESPACE_DECL) {
      result_ = NamespaceDecl(builder_, 0);
    }
    else if (root_ == NAMESPACE_NODE_TEST) {
      result_ = NamespaceNodeTest(builder_, 0);
    }
    else if (root_ == NAMESPACE_PREFIX) {
      result_ = NamespacePrefix(builder_, 0);
    }
    else if (root_ == NEW_NAME_EXPR) {
      result_ = NewNameExpr(builder_, 0);
    }
    else if (root_ == NEXT_ITEM) {
      result_ = NextItem(builder_, 0);
    }
    else if (root_ == NODE_COMP) {
      result_ = NodeComp(builder_, 0);
    }
    else if (root_ == NODE_TEST) {
      result_ = NodeTest(builder_, 0);
    }
    else if (root_ == NUMERIC_LITERAL) {
      result_ = NumericLiteral(builder_, 0);
    }
    else if (root_ == OCCURRENCE_INDICATOR) {
      result_ = OccurrenceIndicator(builder_, 0);
    }
    else if (root_ == OPTION_DECL) {
      result_ = OptionDecl(builder_, 0);
    }
    else if (root_ == OR_EXPR) {
      result_ = OrExpr(builder_, 0);
    }
    else if (root_ == OR_OPERATOR) {
      result_ = OrOperator(builder_, 0);
    }
    else if (root_ == ORDER_BY_CLAUSE) {
      result_ = OrderByClause(builder_, 0);
    }
    else if (root_ == ORDER_SPEC) {
      result_ = OrderSpec(builder_, 0);
    }
    else if (root_ == ORDER_SPEC_LIST) {
      result_ = OrderSpecList(builder_, 0);
    }
    else if (root_ == ORDERED_EXPR) {
      result_ = OrderedExpr(builder_, 0);
    }
    else if (root_ == ORDERING_MODE_DECL) {
      result_ = OrderingModeDecl(builder_, 0);
    }
    else if (root_ == PI_TEST) {
      result_ = PITest(builder_, 0);
    }
    else if (root_ == PARAM) {
      result_ = Param(builder_, 0);
    }
    else if (root_ == PARAM_LIST) {
      result_ = ParamList(builder_, 0);
    }
    else if (root_ == PARENTHESIZED_EXPR) {
      result_ = ParenthesizedExpr(builder_, 0);
    }
    else if (root_ == PARENTHESIZED_ITEM_TYPE) {
      result_ = ParenthesizedItemType(builder_, 0);
    }
    else if (root_ == PATH_EXPR) {
      result_ = PathExpr(builder_, 0);
    }
    else if (root_ == POSITIONAL_VAR) {
      result_ = PositionalVar(builder_, 0);
    }
    else if (root_ == POSTFIX_EXPR) {
      result_ = PostfixExpr(builder_, 0);
    }
    else if (root_ == PRAGMA) {
      result_ = Pragma(builder_, 0);
    }
    else if (root_ == PRAGMA_CONTENTS) {
      result_ = PragmaContents(builder_, 0);
    }
    else if (root_ == PREDICATE) {
      result_ = Predicate(builder_, 0);
    }
    else if (root_ == PREDICATE_LIST) {
      result_ = PredicateList(builder_, 0);
    }
    else if (root_ == PREFIX) {
      result_ = Prefix(builder_, 0);
    }
    else if (root_ == PREFIX_EXPR) {
      result_ = PrefixExpr(builder_, 0);
    }
    else if (root_ == PRESERVE_MODE) {
      result_ = PreserveMode(builder_, 0);
    }
    else if (root_ == PREVIOUS_ITEM) {
      result_ = PreviousItem(builder_, 0);
    }
    else if (root_ == PRIMARY_EXPR) {
      result_ = PrimaryExpr(builder_, 0);
    }
    else if (root_ == QUANTIFIED_EXPR) {
      result_ = QuantifiedExpr(builder_, 0);
    }
    else if (root_ == QUERY_BODY) {
      result_ = QueryBody(builder_, 0);
    }
    else if (root_ == QUOT_ATTR_CONTENT_CHAR) {
      result_ = QuotAttrContentChar(builder_, 0);
    }
    else if (root_ == QUOT_ATTR_VALUE_CONTENT) {
      result_ = QuotAttrValueContent(builder_, 0);
    }
    else if (root_ == RANGE_EXPR) {
      result_ = RangeExpr(builder_, 0);
    }
    else if (root_ == RELATIONAL_COMP) {
      result_ = RelationalComp(builder_, 0);
    }
    else if (root_ == RELATIVE_PATH_OPERATOR) {
      result_ = RelativePathOperator(builder_, 0);
    }
    else if (root_ == RENAME_EXPR) {
      result_ = RenameExpr(builder_, 0);
    }
    else if (root_ == REPLACE_EXPR) {
      result_ = ReplaceExpr(builder_, 0);
    }
    else if (root_ == RETURN_CLAUSE) {
      result_ = ReturnClause(builder_, 0);
    }
    else if (root_ == REVALIDATION_DECL) {
      result_ = RevalidationDecl(builder_, 0);
    }
    else if (root_ == REVERSE_STEP) {
      result_ = ReverseStep(builder_, 0);
    }
    else if (root_ == SCHEMA_ATTRIBUTE_TEST) {
      result_ = SchemaAttributeTest(builder_, 0);
    }
    else if (root_ == SCHEMA_ELEMENT_TEST) {
      result_ = SchemaElementTest(builder_, 0);
    }
    else if (root_ == SCHEMA_IMPORT) {
      result_ = SchemaImport(builder_, 0);
    }
    else if (root_ == SEPARATOR) {
      result_ = Separator(builder_, 0);
    }
    else if (root_ == SEQUENCE_TYPE) {
      result_ = SequenceType(builder_, 0);
    }
    else if (root_ == SEQUENCE_TYPE_UNION) {
      result_ = SequenceTypeUnion(builder_, 0);
    }
    else if (root_ == SIMPLE_MAP_EXPR) {
      result_ = SimpleMapExpr(builder_, 0);
    }
    else if (root_ == SIMPLE_MAP_OPERATOR) {
      result_ = SimpleMapOperator(builder_, 0);
    }
    else if (root_ == SIMPLE_TYPE_NAME) {
      result_ = SimpleTypeName(builder_, 0);
    }
    else if (root_ == SINGLE_TYPE) {
      result_ = SingleType(builder_, 0);
    }
    else if (root_ == SOURCE_EXPR) {
      result_ = SourceExpr(builder_, 0);
    }
    else if (root_ == STEP_EXPR) {
      result_ = StepExpr(builder_, 0);
    }
    else if (root_ == STRING_CONCAT_EXPR) {
      result_ = StringConcatExpr(builder_, 0);
    }
    else if (root_ == SWITCH_CASE_CLAUSE) {
      result_ = SwitchCaseClause(builder_, 0);
    }
    else if (root_ == SWITCH_CASE_OPERAND) {
      result_ = SwitchCaseOperand(builder_, 0);
    }
    else if (root_ == SWITCH_DEFAULT_RETURN_CLAUSE) {
      result_ = SwitchDefaultReturnClause(builder_, 0);
    }
    else if (root_ == SWITCH_EXPR) {
      result_ = SwitchExpr(builder_, 0);
    }
    else if (root_ == SWITCH_RETURN_CLAUSE) {
      result_ = SwitchReturnClause(builder_, 0);
    }
    else if (root_ == TARGET_EXPR) {
      result_ = TargetExpr(builder_, 0);
    }
    else if (root_ == TEXT_TEST) {
      result_ = TextTest(builder_, 0);
    }
    else if (root_ == TO_OPERATOR) {
      result_ = ToOperator(builder_, 0);
    }
    else if (root_ == TRANSFORM_EXPR) {
      result_ = TransformExpr(builder_, 0);
    }
    else if (root_ == TREAT_EXPR) {
      result_ = TreatExpr(builder_, 0);
    }
    else if (root_ == TREAT_OPERATOR) {
      result_ = TreatOperator(builder_, 0);
    }
    else if (root_ == TRY_CATCH_EXPR) {
      result_ = TryCatchExpr(builder_, 0);
    }
    else if (root_ == TRY_CLAUSE) {
      result_ = TryClause(builder_, 0);
    }
    else if (root_ == TYPE_DECLARATION) {
      result_ = TypeDeclaration(builder_, 0);
    }
    else if (root_ == TYPE_NAME) {
      result_ = TypeName(builder_, 0);
    }
    else if (root_ == TYPED_FUNCTION_TEST) {
      result_ = TypedFunctionTest(builder_, 0);
    }
    else if (root_ == TYPESWITCH_DEFAULT_RETURN_CLAUSE) {
      result_ = TypeswitchDefaultReturnClause(builder_, 0);
    }
    else if (root_ == TYPESWITCH_EXPR) {
      result_ = TypeswitchExpr(builder_, 0);
    }
    else if (root_ == URI_EXPR) {
      result_ = URIExpr(builder_, 0);
    }
    else if (root_ == URI_LITERAL) {
      result_ = URILiteral(builder_, 0);
    }
    else if (root_ == UNARY_EXPR) {
      result_ = UnaryExpr(builder_, 0);
    }
    else if (root_ == UNION_EXPR) {
      result_ = UnionExpr(builder_, 0);
    }
    else if (root_ == UNION_OPERATOR) {
      result_ = UnionOperator(builder_, 0);
    }
    else if (root_ == UNORDERED_EXPR) {
      result_ = UnorderedExpr(builder_, 0);
    }
    else if (root_ == VALIDATE_EXPR) {
      result_ = ValidateExpr(builder_, 0);
    }
    else if (root_ == VALUE_COMP) {
      result_ = ValueComp(builder_, 0);
    }
    else if (root_ == VALUE_EXPR) {
      result_ = ValueExpr(builder_, 0);
    }
    else if (root_ == VAR_DECL) {
      result_ = VarDecl(builder_, 0);
    }
    else if (root_ == VAR_DEFAULT_VALUE) {
      result_ = VarDefaultValue(builder_, 0);
    }
    else if (root_ == VAR_LOCAL_NAME) {
      result_ = VarLocalName(builder_, 0);
    }
    else if (root_ == VAR_NAME) {
      result_ = VarName(builder_, 0);
    }
    else if (root_ == VAR_REF) {
      result_ = VarRef(builder_, 0);
    }
    else if (root_ == VAR_VALUE) {
      result_ = VarValue(builder_, 0);
    }
    else if (root_ == VERSION) {
      result_ = Version(builder_, 0);
    }
    else if (root_ == VERSION_DECL) {
      result_ = VersionDecl(builder_, 0);
    }
    else if (root_ == WHERE_CLAUSE) {
      result_ = WhereClause(builder_, 0);
    }
    else if (root_ == WILDCARD) {
      result_ = Wildcard(builder_, 0);
    }
    else if (root_ == WINDOW_CLAUSE) {
      result_ = WindowClause(builder_, 0);
    }
    else if (root_ == XML_EMPTY_TAG) {
      result_ = XmlEmptyTag(builder_, 0);
    }
    else if (root_ == XML_FULL_TAG) {
      result_ = XmlFullTag(builder_, 0);
    }
    else if (root_ == XML_TAG_LOCAL_NAME) {
      result_ = XmlTagLocalName(builder_, 0);
    }
    else if (root_ == XML_TAG_NAME) {
      result_ = XmlTagName(builder_, 0);
    }
    else if (root_ == XML_TAG_NAMESPACE) {
      result_ = XmlTagNamespace(builder_, 0);
    }
    else {
      result_ = parse_root_(root_, builder_, 0);
    }
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return xqueryFile(builder_, level_ + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ADDITIVE_EXPR, AND_EXPR, CASTABLE_EXPR, CAST_EXPR,
      COMPARISON_EXPR, CONTENT_EXPR, CONTEXT_ITEM_EXPR, DELETE_EXPR,
      ENCLOSED_EXPR, EXPR_SINGLE, EXTENSION_EXPR, FLWOR_EXPR,
      FUNCTION_ITEM_EXPR, IF_EXPR, INLINE_FUNCTION_EXPR, INSERT_EXPR,
      INSTANCEOF_EXPR, INTERSECT_EXCEPT_EXPR, MULTIPLICATIVE_EXPR, NEW_NAME_EXPR,
      ORDERED_EXPR, OR_EXPR, PARENTHESIZED_EXPR, PATH_EXPR,
      POSTFIX_EXPR, PREFIX_EXPR, PRIMARY_EXPR, QUANTIFIED_EXPR,
      RANGE_EXPR, RENAME_EXPR, REPLACE_EXPR, SIMPLE_MAP_EXPR,
      SOURCE_EXPR, STEP_EXPR, STRING_CONCAT_EXPR, SWITCH_EXPR,
      TARGET_EXPR, TRANSFORM_EXPR, TREAT_EXPR, TRY_CATCH_EXPR,
      TYPESWITCH_EXPR, UNARY_EXPR, UNION_EXPR, UNORDERED_EXPR,
      URI_EXPR, VALIDATE_EXPR, VALUE_EXPR),
  };

  /* ********************************************************** */
  // "@"? NodeTest
  public static boolean AbbrevForwardStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbbrevForwardStep")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<abbrev forward step>");
    result_ = AbbrevForwardStep_0(builder_, level_ + 1);
    result_ = result_ && NodeTest(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ABBREV_FORWARD_STEP, result_, false, null);
    return result_;
  }

  // "@"?
  private static boolean AbbrevForwardStep_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbbrevForwardStep_0")) return false;
    consumeToken(builder_, AT_SIGN);
    return true;
  }

  /* ********************************************************** */
  // ".."
  public static boolean AbbrevReverseStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbbrevReverseStep")) return false;
    if (!nextTokenIs(builder_, DOT_DOT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT_DOT);
    exit_section_(builder_, marker_, ABBREV_REVERSE_STEP, result_);
    return result_;
  }

  /* ********************************************************** */
  // MultiplicativeExpr AdditiveOptionalExpr*
  public static boolean AdditiveExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AdditiveExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<additive expr>");
    result_ = MultiplicativeExpr(builder_, level_ + 1);
    result_ = result_ && AdditiveExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ADDITIVE_EXPR, result_, false, null);
    return result_;
  }

  // AdditiveOptionalExpr*
  private static boolean AdditiveExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AdditiveExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!AdditiveOptionalExpr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "AdditiveExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // "+" | "-"
  public static boolean AdditiveOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AdditiveOperator")) return false;
    if (!nextTokenIs(builder_, "<additive operator>", OP_PLUS, OP_MINUS)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<additive operator>");
    result_ = consumeToken(builder_, OP_PLUS);
    if (!result_) result_ = consumeToken(builder_, OP_MINUS);
    exit_section_(builder_, level_, marker_, ADDITIVE_OPERATOR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // AdditiveOperator MultiplicativeExpr
  static boolean AdditiveOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AdditiveOptionalExpr")) return false;
    if (!nextTokenIs(builder_, "", OP_PLUS, OP_MINUS)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = AdditiveOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && MultiplicativeExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "allowing" "empty"
  public static boolean AllowingEmpty(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AllowingEmpty")) return false;
    if (!nextTokenIs(builder_, K_ALLOWING)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ALLOWING);
    result_ = result_ && consumeToken(builder_, K_EMPTY);
    exit_section_(builder_, marker_, ALLOWING_EMPTY, result_);
    return result_;
  }

  /* ********************************************************** */
  // ComparisonExpr AndMultipliedExpr*
  public static boolean AndExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<and expr>");
    result_ = ComparisonExpr(builder_, level_ + 1);
    result_ = result_ && AndExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, AND_EXPR, result_, false, null);
    return result_;
  }

  // AndMultipliedExpr*
  private static boolean AndExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!AndMultipliedExpr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "AndExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // AndOperator ComparisonExpr
  static boolean AndMultipliedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndMultipliedExpr")) return false;
    if (!nextTokenIs(builder_, K_AND)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = AndOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && ComparisonExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "and"
  public static boolean AndOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndOperator")) return false;
    if (!nextTokenIs(builder_, K_AND)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_AND);
    exit_section_(builder_, marker_, AND_OPERATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // VarDecl | FunctionDecl
  static boolean AnnotatedDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnnotatedDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = VarDecl(builder_, level_ + 1);
    if (!result_) result_ = FunctionDecl(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "%" AnnotationName ("(" Literal ("," Literal)* ")")?
  public static boolean Annotation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Annotation")) return false;
    if (!nextTokenIs(builder_, PERCENT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PERCENT);
    result_ = result_ && AnnotationName(builder_, level_ + 1);
    result_ = result_ && Annotation_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, ANNOTATION, result_);
    return result_;
  }

  // ("(" Literal ("," Literal)* ")")?
  private static boolean Annotation_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Annotation_2")) return false;
    Annotation_2_0(builder_, level_ + 1);
    return true;
  }

  // "(" Literal ("," Literal)* ")"
  private static boolean Annotation_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Annotation_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAR);
    result_ = result_ && Literal(builder_, level_ + 1);
    result_ = result_ && Annotation_2_0_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("," Literal)*
  private static boolean Annotation_2_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Annotation_2_0_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!Annotation_2_0_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Annotation_2_0_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," Literal
  private static boolean Annotation_2_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Annotation_2_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && Literal(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean AnnotationName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnnotationName")) return false;
    if (!nextTokenIs(builder_, "<annotation name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<annotation name>");
    result_ = EQName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ANNOTATION_NAME, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "function" "(" "*" ")"
  public static boolean AnyFunctionTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnyFunctionTest")) return false;
    if (!nextTokenIs(builder_, K_FUNCTION)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_FUNCTION);
    result_ = result_ && consumeToken(builder_, L_PAR);
    result_ = result_ && consumeToken(builder_, STAR_SIGN);
    pinned_ = result_; // pin = 3
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, level_, marker_, ANY_FUNCTION_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "node" "(" ")"
  public static boolean AnyKindTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnyKindTest")) return false;
    if (!nextTokenIs(builder_, K_NODE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_NODE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, level_, marker_, ANY_KIND_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Char
  public static boolean AposAttrContentChar(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AposAttrContentChar")) return false;
    if (!nextTokenIs(builder_, CHAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CHAR);
    exit_section_(builder_, marker_, APOS_ATTR_CONTENT_CHAR, result_);
    return result_;
  }

  /* ********************************************************** */
  // AposAttrContentChar
  //  | CommonContent
  public static boolean AposAttrValueContent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AposAttrValueContent")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<apos attr value content>");
    result_ = AposAttrContentChar(builder_, level_ + 1);
    if (!result_) result_ = CommonContent(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, APOS_ATTR_VALUE_CONTENT, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle | ArgumentPlaceholder
  public static boolean Argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Argument")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<argument>");
    result_ = ExprSingle(builder_, level_ + 1);
    if (!result_) result_ = ArgumentPlaceholder(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ARGUMENT, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "(" (Argument OptionalArgumentAfterComma*)? ")"
  public static boolean ArgumentList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentList")) return false;
    if (!nextTokenIs(builder_, L_PAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ArgumentList_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, ARGUMENT_LIST, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (Argument OptionalArgumentAfterComma*)?
  private static boolean ArgumentList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentList_1")) return false;
    ArgumentList_1_0(builder_, level_ + 1);
    return true;
  }

  // Argument OptionalArgumentAfterComma*
  private static boolean ArgumentList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = Argument(builder_, level_ + 1);
    result_ = result_ && ArgumentList_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // OptionalArgumentAfterComma*
  private static boolean ArgumentList_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentList_1_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!OptionalArgumentAfterComma(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ArgumentList_1_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // "?"
  public static boolean ArgumentPlaceholder(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentPlaceholder")) return false;
    if (!nextTokenIs(builder_, QUESTIONMARK)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, QUESTIONMARK);
    exit_section_(builder_, marker_, ARGUMENT_PLACEHOLDER, result_);
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean AtomicOrUnionType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AtomicOrUnionType")) return false;
    if (!nextTokenIs(builder_, "<atomic or union type>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<atomic or union type>");
    result_ = EQName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ATOMIC_OR_UNION_TYPE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // AttrNCName
  public static boolean AttrLocalName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttrLocalName")) return false;
    if (!nextTokenIs(builder_, ATTRNCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ATTRNCNAME);
    exit_section_(builder_, marker_, ATTR_LOCAL_NAME, result_);
    return result_;
  }

  /* ********************************************************** */
  // AttrNCName
  public static boolean AttrNamespace(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttrNamespace")) return false;
    if (!nextTokenIs(builder_, ATTRNCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ATTRNCNAME);
    exit_section_(builder_, marker_, ATTR_NAMESPACE, result_);
    return result_;
  }

  /* ********************************************************** */
  // AttributeName | "*"
  public static boolean AttribNameOrWildcard(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttribNameOrWildcard")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<attrib name or wildcard>");
    result_ = AttributeName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STAR_SIGN);
    exit_section_(builder_, level_, marker_, ATTRIB_NAME_OR_WILDCARD, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // AttributeName
  public static boolean AttributeDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeDeclaration")) return false;
    if (!nextTokenIs(builder_, "<attribute declaration>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<attribute declaration>");
    result_ = AttributeName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ATTRIBUTE_DECLARATION, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean AttributeName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeName")) return false;
    if (!nextTokenIs(builder_, "<attribute name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<attribute name>");
    result_ = EQName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ATTRIBUTE_NAME, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "attribute" "(" (AttribNameOrWildcard ("," TypeName)?)? ")"
  public static boolean AttributeTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeTest")) return false;
    if (!nextTokenIs(builder_, K_ATTRIBUTE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_ATTRIBUTE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, AttributeTest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, ATTRIBUTE_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (AttribNameOrWildcard ("," TypeName)?)?
  private static boolean AttributeTest_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeTest_2")) return false;
    AttributeTest_2_0(builder_, level_ + 1);
    return true;
  }

  // AttribNameOrWildcard ("," TypeName)?
  private static boolean AttributeTest_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeTest_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = AttribNameOrWildcard(builder_, level_ + 1);
    result_ = result_ && AttributeTest_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("," TypeName)?
  private static boolean AttributeTest_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeTest_2_0_1")) return false;
    AttributeTest_2_0_1_0(builder_, level_ + 1);
    return true;
  }

  // "," TypeName
  private static boolean AttributeTest_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeTest_2_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && TypeName(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (ReverseStep | ForwardStep | MarklogicNamespaceAxis) PredicateList
  public static boolean AxisStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AxisStep")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<axis step>");
    result_ = AxisStep_0(builder_, level_ + 1);
    result_ = result_ && PredicateList(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, AXIS_STEP, result_, false, null);
    return result_;
  }

  // ReverseStep | ForwardStep | MarklogicNamespaceAxis
  private static boolean AxisStep_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AxisStep_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ReverseStep(builder_, level_ + 1);
    if (!result_) result_ = ForwardStep(builder_, level_ + 1);
    if (!result_) result_ = MarklogicNamespaceAxis(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "base-uri" URILiteral Separator
  public static boolean BaseURIDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BaseURIDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_BASE_URI);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, URILiteral(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, BASE_URI_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "declare" "boundary-space" ("preserve" | "strip") Separator
  public static boolean BoundarySpaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BoundarySpaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_BOUNDARY_SPACE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, BoundarySpaceDecl_2(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, BOUNDARY_SPACE_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  // "preserve" | "strip"
  private static boolean BoundarySpaceDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BoundarySpaceDecl_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_PRESERVE);
    if (!result_) result_ = consumeToken(builder_, K_STRIP);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "<![CDATA[" CDataSectionContents "]]>"
  public static boolean CDataSection(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CDataSection")) return false;
    if (!nextTokenIs(builder_, CDATA_BEGIN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CDATA_BEGIN);
    result_ = result_ && CDataSectionContents(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CDATA_END);
    exit_section_(builder_, marker_, C_DATA_SECTION, result_);
    return result_;
  }

  /* ********************************************************** */
  // CDataSectionContentChar*
  public static boolean CDataSectionContents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CDataSectionContents")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<c data section contents>");
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, CDATASECTIONCONTENTCHAR)) break;
      if (!empty_element_parsed_guard_(builder_, "CDataSectionContents", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, level_, marker_, C_DATA_SECTION_CONTENTS, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // "case" ("$" VarName "as")? SequenceTypeUnion SwitchReturnClause
  public static boolean CaseClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CaseClause")) return false;
    if (!nextTokenIs(builder_, K_CASE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_CASE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, CaseClause_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, SequenceTypeUnion(builder_, level_ + 1)) && result_;
    result_ = pinned_ && SwitchReturnClause(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, CASE_CLAUSE, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ("$" VarName "as")?
  private static boolean CaseClause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CaseClause_1")) return false;
    CaseClause_1_0(builder_, level_ + 1);
    return true;
  }

  // "$" VarName "as"
  private static boolean CaseClause_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CaseClause_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_AS);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // UnaryExpr CastOptionalExpr?
  public static boolean CastExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<cast expr>");
    result_ = UnaryExpr(builder_, level_ + 1);
    result_ = result_ && CastExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CAST_EXPR, result_, false, null);
    return result_;
  }

  // CastOptionalExpr?
  private static boolean CastExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastExpr_1")) return false;
    CastOptionalExpr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "cast" "as"
  public static boolean CastOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastOperator")) return false;
    if (!nextTokenIs(builder_, K_CAST)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_CAST);
    result_ = result_ && consumeToken(builder_, K_AS);
    exit_section_(builder_, marker_, CAST_OPERATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // CastOperator SingleType
  static boolean CastOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastOptionalExpr")) return false;
    if (!nextTokenIs(builder_, K_CAST)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = CastOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && SingleType(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // CastExpr CastableOptionalExpr?
  public static boolean CastableExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastableExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<castable expr>");
    result_ = CastExpr(builder_, level_ + 1);
    result_ = result_ && CastableExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CASTABLE_EXPR, result_, false, null);
    return result_;
  }

  // CastableOptionalExpr?
  private static boolean CastableExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastableExpr_1")) return false;
    CastableOptionalExpr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "castable" "as"
  public static boolean CastableOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastableOperator")) return false;
    if (!nextTokenIs(builder_, K_CASTABLE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_CASTABLE);
    result_ = result_ && consumeToken(builder_, K_AS);
    exit_section_(builder_, marker_, CASTABLE_OPERATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // CastableOperator SingleType
  static boolean CastableOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastableOptionalExpr")) return false;
    if (!nextTokenIs(builder_, K_CASTABLE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = CastableOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && SingleType(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "catch" (CatchErrorList | MarklogicCatchErrorList) "{" Expr "}"
  public static boolean CatchClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CatchClause")) return false;
    if (!nextTokenIs(builder_, K_CATCH)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_CATCH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, CatchClause_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, L_C_BRACE)) && result_;
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, R_C_BRACE) && result_;
    exit_section_(builder_, level_, marker_, CATCH_CLAUSE, result_, pinned_, null);
    return result_ || pinned_;
  }

  // CatchErrorList | MarklogicCatchErrorList
  private static boolean CatchClause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CatchClause_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = CatchErrorList(builder_, level_ + 1);
    if (!result_) result_ = MarklogicCatchErrorList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NameTest ("|" NameTest)*
  public static boolean CatchErrorList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CatchErrorList")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<catch error list>");
    result_ = NameTest(builder_, level_ + 1);
    result_ = result_ && CatchErrorList_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CATCH_ERROR_LIST, result_, false, null);
    return result_;
  }

  // ("|" NameTest)*
  private static boolean CatchErrorList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CatchErrorList_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!CatchErrorList_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "CatchErrorList_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "|" NameTest
  private static boolean CatchErrorList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CatchErrorList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PIPE);
    result_ = result_ && NameTest(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "comment" "(" ")"
  public static boolean CommentTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommentTest")) return false;
    if (!nextTokenIs(builder_, K_COMMENT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_COMMENT);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, level_, marker_, COMMENT_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // PredefinedEntityRef | CharRef | "{{" | "}}" | EnclosedExpr
  public static boolean CommonContent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommonContent")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<common content>");
    result_ = consumeToken(builder_, PREDEFINEDENTITYREF);
    if (!result_) result_ = consumeToken(builder_, CHARREF);
    if (!result_) result_ = consumeToken(builder_, DBL_L_C_BRACE);
    if (!result_) result_ = consumeToken(builder_, DBL_R_C_BRACE);
    if (!result_) result_ = EnclosedExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, COMMON_CONTENT, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "attribute" (EQName | ("{" Expr "}")) "{" Expr? "}"
  public static boolean CompAttrConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompAttrConstructor")) return false;
    if (!nextTokenIs(builder_, K_ATTRIBUTE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ATTRIBUTE);
    result_ = result_ && CompAttrConstructor_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && CompAttrConstructor_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, COMP_ATTR_CONSTRUCTOR, result_);
    return result_;
  }

  // EQName | ("{" Expr "}")
  private static boolean CompAttrConstructor_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompAttrConstructor_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = EQName(builder_, level_ + 1);
    if (!result_) result_ = CompAttrConstructor_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "{" Expr "}"
  private static boolean CompAttrConstructor_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompAttrConstructor_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Expr?
  private static boolean CompAttrConstructor_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompAttrConstructor_3")) return false;
    Expr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "comment" "{" Expr "}"
  public static boolean CompCommentConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompCommentConstructor")) return false;
    if (!nextTokenIs(builder_, K_COMMENT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_COMMENT);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, COMP_COMMENT_CONSTRUCTOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // "document" "{" Expr "}"
  public static boolean CompDocConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompDocConstructor")) return false;
    if (!nextTokenIs(builder_, K_DOCUMENT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DOCUMENT);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, COMP_DOC_CONSTRUCTOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // "element" (EQName | ("{" Expr "}")) "{" ContentExpr? "}"
  public static boolean CompElemConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompElemConstructor")) return false;
    if (!nextTokenIs(builder_, K_ELEMENT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ELEMENT);
    result_ = result_ && CompElemConstructor_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && CompElemConstructor_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, COMP_ELEM_CONSTRUCTOR, result_);
    return result_;
  }

  // EQName | ("{" Expr "}")
  private static boolean CompElemConstructor_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompElemConstructor_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = EQName(builder_, level_ + 1);
    if (!result_) result_ = CompElemConstructor_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "{" Expr "}"
  private static boolean CompElemConstructor_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompElemConstructor_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ContentExpr?
  private static boolean CompElemConstructor_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompElemConstructor_3")) return false;
    ContentExpr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "map" "{" MapEntryList? "}"
  public static boolean CompMapConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompMapConstructor")) return false;
    if (!nextTokenIs(builder_, K_MAP)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_MAP);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && CompMapConstructor_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, COMP_MAP_CONSTRUCTOR, result_);
    return result_;
  }

  // MapEntryList?
  private static boolean CompMapConstructor_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompMapConstructor_2")) return false;
    MapEntryList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "namespace" (Prefix | ("{" PrefixExpr "}")) "{" URIExpr "}"
  public static boolean CompNamespaceConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompNamespaceConstructor")) return false;
    if (!nextTokenIs(builder_, K_NAMESPACE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_NAMESPACE);
    result_ = result_ && CompNamespaceConstructor_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && URIExpr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, COMP_NAMESPACE_CONSTRUCTOR, result_);
    return result_;
  }

  // Prefix | ("{" PrefixExpr "}")
  private static boolean CompNamespaceConstructor_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompNamespaceConstructor_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = Prefix(builder_, level_ + 1);
    if (!result_) result_ = CompNamespaceConstructor_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "{" PrefixExpr "}"
  private static boolean CompNamespaceConstructor_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompNamespaceConstructor_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_C_BRACE);
    result_ = result_ && PrefixExpr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "processing-instruction" (NCName | ("{" Expr "}")) "{" Expr? "}"
  public static boolean CompPIConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompPIConstructor")) return false;
    if (!nextTokenIs(builder_, K_PI)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_PI);
    result_ = result_ && CompPIConstructor_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && CompPIConstructor_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, COMP_PI_CONSTRUCTOR, result_);
    return result_;
  }

  // NCName | ("{" Expr "}")
  private static boolean CompPIConstructor_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompPIConstructor_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NCNAME);
    if (!result_) result_ = CompPIConstructor_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "{" Expr "}"
  private static boolean CompPIConstructor_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompPIConstructor_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Expr?
  private static boolean CompPIConstructor_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompPIConstructor_3")) return false;
    Expr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "text" "{" Expr "}"
  public static boolean CompTextConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompTextConstructor")) return false;
    if (!nextTokenIs(builder_, K_TEXT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_TEXT);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    exit_section_(builder_, marker_, COMP_TEXT_CONSTRUCTOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // ValueComp | GeneralComp | NodeComp
  static boolean Comparison(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Comparison")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ValueComp(builder_, level_ + 1);
    if (!result_) result_ = GeneralComp(builder_, level_ + 1);
    if (!result_) result_ = NodeComp(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // StringConcatExpr ComparisonOptionalExpr ?
  public static boolean ComparisonExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComparisonExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<comparison expr>");
    result_ = StringConcatExpr(builder_, level_ + 1);
    result_ = result_ && ComparisonExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, COMPARISON_EXPR, result_, false, null);
    return result_;
  }

  // ComparisonOptionalExpr ?
  private static boolean ComparisonExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComparisonExpr_1")) return false;
    ComparisonOptionalExpr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // Comparison StringConcatExpr
  static boolean ComparisonOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComparisonOptionalExpr")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = Comparison(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && StringConcatExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "updating"
  public static boolean CompatibilityAnnotation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompatibilityAnnotation")) return false;
    if (!nextTokenIs(builder_, K_UPDATING)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_UPDATING);
    exit_section_(builder_, marker_, COMPATIBILITY_ANNOTATION, result_);
    return result_;
  }

  /* ********************************************************** */
  // CompDocConstructor
  //  | CompElemConstructor
  //  | CompMapConstructor
  //  | CompAttrConstructor
  //  | CompNamespaceConstructor
  //  | CompTextConstructor
  //  | CompCommentConstructor
  //  | CompPIConstructor
  public static boolean ComputedConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComputedConstructor")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<computed constructor>");
    result_ = CompDocConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompElemConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompMapConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompAttrConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompNamespaceConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompTextConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompCommentConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompPIConstructor(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, COMPUTED_CONSTRUCTOR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "||"
  public static boolean ConcatOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ConcatOperator")) return false;
    if (!nextTokenIs(builder_, PIPE_PIPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PIPE_PIPE);
    exit_section_(builder_, marker_, CONCAT_OPERATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "construction" ("strip" | "preserve") Separator
  public static boolean ConstructionDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ConstructionDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_CONSTRUCTION);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, ConstructionDecl_2(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, CONSTRUCTION_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  // "strip" | "preserve"
  private static boolean ConstructionDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ConstructionDecl_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_STRIP);
    if (!result_) result_ = consumeToken(builder_, K_PRESERVE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // DirectConstructor
  //  | ComputedConstructor
  public static boolean Constructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Constructor")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<constructor>");
    result_ = DirectConstructor(builder_, level_ + 1);
    if (!result_) result_ = ComputedConstructor(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CONSTRUCTOR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Expr
  public static boolean ContentExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContentExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<content expr>");
    result_ = Expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CONTENT_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "context" "item" ("as" ItemType)? (VarValueAssignment | ("external" (":=" VarDefaultValue)?)) Separator
  public static boolean ContextItemDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_CONTEXT);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_ITEM));
    result_ = pinned_ && report_error_(builder_, ContextItemDecl_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, ContextItemDecl_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, CONTEXT_ITEM_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ("as" ItemType)?
  private static boolean ContextItemDecl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl_3")) return false;
    ContextItemDecl_3_0(builder_, level_ + 1);
    return true;
  }

  // "as" ItemType
  private static boolean ContextItemDecl_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_AS);
    result_ = result_ && ItemType(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // VarValueAssignment | ("external" (":=" VarDefaultValue)?)
  private static boolean ContextItemDecl_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = VarValueAssignment(builder_, level_ + 1);
    if (!result_) result_ = ContextItemDecl_4_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "external" (":=" VarDefaultValue)?
  private static boolean ContextItemDecl_4_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl_4_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_EXTERNAL);
    result_ = result_ && ContextItemDecl_4_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (":=" VarDefaultValue)?
  private static boolean ContextItemDecl_4_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl_4_1_1")) return false;
    ContextItemDecl_4_1_1_0(builder_, level_ + 1);
    return true;
  }

  // ":=" VarDefaultValue
  private static boolean ContextItemDecl_4_1_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl_4_1_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && VarDefaultValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "."
  public static boolean ContextItemExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemExpr")) return false;
    if (!nextTokenIs(builder_, DOT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT);
    exit_section_(builder_, marker_, CONTEXT_ITEM_EXPR, result_);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "copy-namespaces" PreserveMode "," InheritMode Separator
  public static boolean CopyNamespacesDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CopyNamespacesDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_COPY_NAMESPACES);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, PreserveMode(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, COMMA)) && result_;
    result_ = pinned_ && report_error_(builder_, InheritMode(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, COPY_NAMESPACES_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "count" "$" VarName
  public static boolean CountClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CountClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<count clause>");
    result_ = consumeToken(builder_, K_COUNT);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, DOLLAR_SIGN));
    result_ = pinned_ && VarName(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, COUNT_CLAUSE, result_, pinned_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // VarName
  public static boolean CurrentItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CurrentItem")) return false;
    if (!nextTokenIs(builder_, "<current item>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<current item>");
    result_ = VarName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CURRENT_ITEM, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "decimal-separator" | "grouping-separator" | "infinity" | "minus-sign" | "NaN" | "percent" | "per-mille" | "zero-digit" | "digit" | "pattern-separator"
  static boolean DFPropertyName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DFPropertyName")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DECIMAL_SEPARATOR);
    if (!result_) result_ = consumeToken(builder_, K_GROUPING_SEPARATOR);
    if (!result_) result_ = consumeToken(builder_, K_INFINITY);
    if (!result_) result_ = consumeToken(builder_, K_MINUS_SIGN);
    if (!result_) result_ = consumeToken(builder_, K_NAN);
    if (!result_) result_ = consumeToken(builder_, K_PERCENT);
    if (!result_) result_ = consumeToken(builder_, K_PER_MILLE);
    if (!result_) result_ = consumeToken(builder_, K_ZERO_DIGIT);
    if (!result_) result_ = consumeToken(builder_, K_DIGIT);
    if (!result_) result_ = consumeToken(builder_, K_PATTERN_SEPARATOR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "declare" (("decimal-format" EQName) | ("default" "decimal-format")) (DFPropertyName "=" StringLiteral)* Separator
  public static boolean DecimalFormatDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && DecimalFormatDecl_1(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, DecimalFormatDecl_2(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, DECIMAL_FORMAT_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ("decimal-format" EQName) | ("default" "decimal-format")
  private static boolean DecimalFormatDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = DecimalFormatDecl_1_0(builder_, level_ + 1);
    if (!result_) result_ = DecimalFormatDecl_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "decimal-format" EQName
  private static boolean DecimalFormatDecl_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DECIMAL_FORMAT);
    result_ = result_ && EQName(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "default" "decimal-format"
  private static boolean DecimalFormatDecl_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_DECIMAL_FORMAT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (DFPropertyName "=" StringLiteral)*
  private static boolean DecimalFormatDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!DecimalFormatDecl_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "DecimalFormatDecl_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // DFPropertyName "=" StringLiteral
  private static boolean DecimalFormatDecl_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = DFPropertyName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUAL);
    result_ = result_ && consumeToken(builder_, STRINGLITERAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "default" "collation" URILiteral Separator
  public static boolean DefaultCollationDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultCollationDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_COLLATION);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, URILiteral(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, DEFAULT_COLLATION_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "declare" "default" "element" "namespace" URILiteral
  public static boolean DefaultElementNamespaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultElementNamespaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_ELEMENT);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_NAMESPACE));
    result_ = pinned_ && URILiteral(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, DEFAULT_ELEMENT_NAMESPACE_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "declare" "default" "function" "namespace" URILiteral
  public static boolean DefaultFunctionNamespaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultFunctionNamespaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_FUNCTION);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_NAMESPACE));
    result_ = pinned_ && URILiteral(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, DEFAULT_FUNCTION_NAMESPACE_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // (DefaultFunctionNamespaceDecl | DefaultElementNamespaceDecl) Separator
  static boolean DefaultNamespaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultNamespaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = DefaultNamespaceDecl_0(builder_, level_ + 1);
    result_ = result_ && Separator(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // DefaultFunctionNamespaceDecl | DefaultElementNamespaceDecl
  private static boolean DefaultNamespaceDecl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultNamespaceDecl_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = DefaultFunctionNamespaceDecl(builder_, level_ + 1);
    if (!result_) result_ = DefaultElementNamespaceDecl(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "delete" ("node" | "nodes") TargetExpr
  public static boolean DeleteExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DeleteExpr")) return false;
    if (!nextTokenIs(builder_, K_DELETE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DELETE);
    result_ = result_ && DeleteExpr_1(builder_, level_ + 1);
    result_ = result_ && TargetExpr(builder_, level_ + 1);
    exit_section_(builder_, marker_, DELETE_EXPR, result_);
    return result_;
  }

  // "node" | "nodes"
  private static boolean DeleteExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DeleteExpr_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_NODE);
    if (!result_) result_ = consumeToken(builder_, K_NODES);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (DirAttributeName AttrEqual DirAttributeValue)*
  public static boolean DirAttributeList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeList")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<dir attribute list>");
    int pos_ = current_position_(builder_);
    while (true) {
      if (!DirAttributeList_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "DirAttributeList", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, level_, marker_, DIR_ATTRIBUTE_LIST, true, false, null);
    return true;
  }

  // DirAttributeName AttrEqual DirAttributeValue
  private static boolean DirAttributeList_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeList_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = DirAttributeName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ATTREQUAL);
    result_ = result_ && DirAttributeValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // AttrNamespace AttrColon AttrLocalName | AttrLocalName
  public static boolean DirAttributeName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeName")) return false;
    if (!nextTokenIs(builder_, ATTRNCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = DirAttributeName_0(builder_, level_ + 1);
    if (!result_) result_ = AttrLocalName(builder_, level_ + 1);
    exit_section_(builder_, marker_, DIR_ATTRIBUTE_NAME, result_);
    return result_;
  }

  // AttrNamespace AttrColon AttrLocalName
  private static boolean DirAttributeName_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeName_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = AttrNamespace(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ATTRCOLON);
    result_ = result_ && AttrLocalName(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ("\"" (EscapeQuot | QuotAttrValueContent)* "\"")
  //  | ("'" (EscapeApos | AposAttrValueContent)* "'")
  public static boolean DirAttributeValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<dir attribute value>");
    result_ = DirAttributeValue_0(builder_, level_ + 1);
    if (!result_) result_ = DirAttributeValue_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, DIR_ATTRIBUTE_VALUE, result_, false, null);
    return result_;
  }

  // "\"" (EscapeQuot | QuotAttrValueContent)* "\""
  private static boolean DirAttributeValue_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "\"");
    result_ = result_ && DirAttributeValue_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, "\"");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (EscapeQuot | QuotAttrValueContent)*
  private static boolean DirAttributeValue_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!DirAttributeValue_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "DirAttributeValue_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // EscapeQuot | QuotAttrValueContent
  private static boolean DirAttributeValue_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = EscapeQuot(builder_, level_ + 1);
    if (!result_) result_ = QuotAttrValueContent(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "'" (EscapeApos | AposAttrValueContent)* "'"
  private static boolean DirAttributeValue_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, APOSTROPHE);
    result_ = result_ && DirAttributeValue_1_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, APOSTROPHE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (EscapeApos | AposAttrValueContent)*
  private static boolean DirAttributeValue_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_1_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!DirAttributeValue_1_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "DirAttributeValue_1_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // EscapeApos | AposAttrValueContent
  private static boolean DirAttributeValue_1_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_1_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = EscapeApos(builder_, level_ + 1);
    if (!result_) result_ = AposAttrValueContent(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "<!--" DirCommentContents "-->"
  public static boolean DirCommentConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirCommentConstructor")) return false;
    if (!nextTokenIs(builder_, DIR_COMMENT_BEGIN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DIR_COMMENT_BEGIN);
    result_ = result_ && DirCommentContents(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DIR_COMMENT_END);
    exit_section_(builder_, marker_, DIR_COMMENT_CONSTRUCTOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // DirCommentChar*
  public static boolean DirCommentContents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirCommentContents")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<dir comment contents>");
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, DIRCOMMENTCHAR)) break;
      if (!empty_element_parsed_guard_(builder_, "DirCommentContents", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, level_, marker_, DIR_COMMENT_CONTENTS, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // XmlEmptyTag | XmlFullTag
  static boolean DirElemConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirElemConstructor")) return false;
    if (!nextTokenIs(builder_, XMLSTARTTAGSTART)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = XmlEmptyTag(builder_, level_ + 1);
    if (!result_) result_ = XmlFullTag(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // DirectConstructor
  //  | CDataSection
  //  | CommonContent
  //  | ElementContentChar
  public static boolean DirElemContent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirElemContent")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<dir elem content>");
    result_ = DirectConstructor(builder_, level_ + 1);
    if (!result_) result_ = CDataSection(builder_, level_ + 1);
    if (!result_) result_ = CommonContent(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ELEMENTCONTENTCHAR);
    exit_section_(builder_, level_, marker_, DIR_ELEM_CONTENT, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "<?" PITarget (S DirPIContents)? "?>"
  public static boolean DirPIConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirPIConstructor")) return false;
    if (!nextTokenIs(builder_, PI_BEGIN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PI_BEGIN);
    result_ = result_ && consumeToken(builder_, PITARGET);
    result_ = result_ && DirPIConstructor_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PI_END);
    exit_section_(builder_, marker_, DIR_PI_CONSTRUCTOR, result_);
    return result_;
  }

  // (S DirPIContents)?
  private static boolean DirPIConstructor_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirPIConstructor_2")) return false;
    DirPIConstructor_2_0(builder_, level_ + 1);
    return true;
  }

  // S DirPIContents
  private static boolean DirPIConstructor_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirPIConstructor_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, S);
    result_ = result_ && DirPIContents(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // DirPIContentChar*
  public static boolean DirPIContents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirPIContents")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<dir pi contents>");
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, DIRPICONTENTCHAR)) break;
      if (!empty_element_parsed_guard_(builder_, "DirPIContents", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, level_, marker_, DIR_PI_CONTENTS, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // DirElemConstructor
  //  | DirCommentConstructor
  //  | DirPIConstructor
  public static boolean DirectConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirectConstructor")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<direct constructor>");
    result_ = DirElemConstructor(builder_, level_ + 1);
    if (!result_) result_ = DirCommentConstructor(builder_, level_ + 1);
    if (!result_) result_ = DirPIConstructor(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, DIRECT_CONSTRUCTOR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "document-node" "(" (ElementTest | SchemaElementTest)? ")"
  public static boolean DocumentTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DocumentTest")) return false;
    if (!nextTokenIs(builder_, K_DOCUMENT_NODE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DOCUMENT_NODE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, DocumentTest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, DOCUMENT_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (ElementTest | SchemaElementTest)?
  private static boolean DocumentTest_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DocumentTest_2")) return false;
    DocumentTest_2_0(builder_, level_ + 1);
    return true;
  }

  // ElementTest | SchemaElementTest
  private static boolean DocumentTest_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DocumentTest_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ElementTest(builder_, level_ + 1);
    if (!result_) result_ = SchemaElementTest(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // QName | URIQualifiedName
  static boolean EQName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EQName")) return false;
    if (!nextTokenIs(builder_, "", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = QName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, URIQUALIFIEDNAME);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ElementName
  public static boolean ElementDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementDeclaration")) return false;
    if (!nextTokenIs(builder_, "<element declaration>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<element declaration>");
    result_ = ElementName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ELEMENT_DECLARATION, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean ElementName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementName")) return false;
    if (!nextTokenIs(builder_, "<element name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<element name>");
    result_ = EQName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ELEMENT_NAME, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ElementName | "*"
  public static boolean ElementNameOrWildcard(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementNameOrWildcard")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<element name or wildcard>");
    result_ = ElementName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STAR_SIGN);
    exit_section_(builder_, level_, marker_, ELEMENT_NAME_OR_WILDCARD, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "element" "(" (ElementNameOrWildcard ("," TypeName "?"?)?)? ")"
  public static boolean ElementTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementTest")) return false;
    if (!nextTokenIs(builder_, K_ELEMENT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_ELEMENT);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, ElementTest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, ELEMENT_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (ElementNameOrWildcard ("," TypeName "?"?)?)?
  private static boolean ElementTest_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementTest_2")) return false;
    ElementTest_2_0(builder_, level_ + 1);
    return true;
  }

  // ElementNameOrWildcard ("," TypeName "?"?)?
  private static boolean ElementTest_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementTest_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ElementNameOrWildcard(builder_, level_ + 1);
    result_ = result_ && ElementTest_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("," TypeName "?"?)?
  private static boolean ElementTest_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementTest_2_0_1")) return false;
    ElementTest_2_0_1_0(builder_, level_ + 1);
    return true;
  }

  // "," TypeName "?"?
  private static boolean ElementTest_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementTest_2_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && TypeName(builder_, level_ + 1);
    result_ = result_ && ElementTest_2_0_1_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "?"?
  private static boolean ElementTest_2_0_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementTest_2_0_1_0_2")) return false;
    consumeToken(builder_, QUESTIONMARK);
    return true;
  }

  /* ********************************************************** */
  // "declare" "default" "order" "empty" ("greatest" | "least") Separator
  public static boolean EmptyOrderDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmptyOrderDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_ORDER);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_EMPTY));
    result_ = pinned_ && report_error_(builder_, EmptyOrderDecl_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, EMPTY_ORDER_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  // "greatest" | "least"
  private static boolean EmptyOrderDecl_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmptyOrderDecl_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_GREATEST);
    if (!result_) result_ = consumeToken(builder_, K_LEAST);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "{" Expr "}"
  public static boolean EnclosedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnclosedExpr")) return false;
    if (!nextTokenIs(builder_, L_C_BRACE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, L_C_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Expr(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_C_BRACE) && result_;
    exit_section_(builder_, level_, marker_, ENCLOSED_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "=" | "!="
  public static boolean EqualityComp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EqualityComp")) return false;
    if (!nextTokenIs(builder_, "<equality comp>", NOT_EQUAL, EQUAL)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<equality comp>");
    result_ = consumeToken(builder_, EQUAL);
    if (!result_) result_ = consumeToken(builder_, NOT_EQUAL);
    exit_section_(builder_, level_, marker_, EQUALITY_COMP, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "''"
  public static boolean EscapeApos(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EscapeApos")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<escape apos>");
    result_ = consumeToken(builder_, "''");
    exit_section_(builder_, level_, marker_, ESCAPE_APOS, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "\"\""
  public static boolean EscapeQuot(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EscapeQuot")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<escape quot>");
    result_ = consumeToken(builder_, "\"\"");
    exit_section_(builder_, level_, marker_, ESCAPE_QUOT, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle ExprSingleAfterComma*
  public static boolean Expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Expr")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<expr>");
    result_ = ExprSingle(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && Expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ExprSingleAfterComma*
  private static boolean Expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Expr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!ExprSingleAfterComma(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Expr_1", pos_)) break;
      pos_ = current_position_(builder_);
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
  public static boolean ExprSingle(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprSingle")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<expr single>");
    result_ = FLWORExpr(builder_, level_ + 1);
    if (!result_) result_ = QuantifiedExpr(builder_, level_ + 1);
    if (!result_) result_ = SwitchExpr(builder_, level_ + 1);
    if (!result_) result_ = TypeswitchExpr(builder_, level_ + 1);
    if (!result_) result_ = IfExpr(builder_, level_ + 1);
    if (!result_) result_ = TryCatchExpr(builder_, level_ + 1);
    if (!result_) result_ = InsertExpr(builder_, level_ + 1);
    if (!result_) result_ = DeleteExpr(builder_, level_ + 1);
    if (!result_) result_ = RenameExpr(builder_, level_ + 1);
    if (!result_) result_ = ReplaceExpr(builder_, level_ + 1);
    if (!result_) result_ = TransformExpr(builder_, level_ + 1);
    if (!result_) result_ = OrExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, EXPR_SINGLE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "," ExprSingle
  static boolean ExprSingleAfterComma(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprSingleAfterComma")) return false;
    if (!nextTokenIs(builder_, COMMA)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, COMMA);
    pinned_ = result_; // pin = 1
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Pragma+ "{" Expr? "}"
  public static boolean ExtensionExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExtensionExpr")) return false;
    if (!nextTokenIs(builder_, PRAGMA_BEGIN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = ExtensionExpr_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, L_C_BRACE));
    result_ = pinned_ && report_error_(builder_, ExtensionExpr_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, R_C_BRACE) && result_;
    exit_section_(builder_, level_, marker_, EXTENSION_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Pragma+
  private static boolean ExtensionExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExtensionExpr_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = Pragma(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!Pragma(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ExtensionExpr_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Expr?
  private static boolean ExtensionExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExtensionExpr_2")) return false;
    Expr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "external" (":=" VarDefaultValue)?
  public static boolean ExternalVarPart(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExternalVarPart")) return false;
    if (!nextTokenIs(builder_, K_EXTERNAL)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_EXTERNAL);
    pinned_ = result_; // pin = 1
    result_ = result_ && ExternalVarPart_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, EXTERNAL_VAR_PART, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (":=" VarDefaultValue)?
  private static boolean ExternalVarPart_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExternalVarPart_1")) return false;
    ExternalVarPart_1_0(builder_, level_ + 1);
    return true;
  }

  // ":=" VarDefaultValue
  private static boolean ExternalVarPart_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExternalVarPart_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && VarDefaultValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // InitialClause IntermediateClause* ReturnClause
  public static boolean FLWORExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FLWORExpr")) return false;
    if (!nextTokenIs(builder_, "<flwor expr>", K_FOR, K_LET)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<flwor expr>");
    result_ = InitialClause(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, FLWORExpr_1(builder_, level_ + 1));
    result_ = pinned_ && ReturnClause(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, FLWOR_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // IntermediateClause*
  private static boolean FLWORExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FLWORExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!IntermediateClause(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "FLWORExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // !('count' | 'for' | 'group' | 'let' | 'order' | 'return' | 'stable' | 'where' | '}' | XmlEndTagStart TagName | XmlStartTagStart TagName | ',')
  static boolean FLWORExprRecover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FLWORExprRecover")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !FLWORExprRecover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  // 'count' | 'for' | 'group' | 'let' | 'order' | 'return' | 'stable' | 'where' | '}' | XmlEndTagStart TagName | XmlStartTagStart TagName | ','
  private static boolean FLWORExprRecover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FLWORExprRecover_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_COUNT);
    if (!result_) result_ = consumeToken(builder_, K_FOR);
    if (!result_) result_ = consumeToken(builder_, K_GROUP);
    if (!result_) result_ = consumeToken(builder_, K_LET);
    if (!result_) result_ = consumeToken(builder_, K_ORDER);
    if (!result_) result_ = consumeToken(builder_, K_RETURN);
    if (!result_) result_ = consumeToken(builder_, K_STABLE);
    if (!result_) result_ = consumeToken(builder_, K_WHERE);
    if (!result_) result_ = consumeToken(builder_, R_C_BRACE);
    if (!result_) result_ = parseTokens(builder_, 0, XMLENDTAGSTART, TAGNAME);
    if (!result_) result_ = parseTokens(builder_, 0, XMLSTARTTAGSTART, TAGNAME);
    if (!result_) result_ = consumeToken(builder_, COMMA);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // DefaultNamespaceDecl | Setter | NamespaceDecl | Import
  static boolean FirstDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FirstDecl")) return false;
    if (!nextTokenIs(builder_, "", K_DECLARE, K_IMPORT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = DefaultNamespaceDecl(builder_, level_ + 1);
    if (!result_) result_ = Setter(builder_, level_ + 1);
    if (!result_) result_ = NamespaceDecl(builder_, level_ + 1);
    if (!result_) result_ = Import(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "$" ForBindingDetails
  public static boolean ForBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForBinding")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    pinned_ = result_; // pin = 1
    result_ = result_ && ForBindingDetails(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, FOR_BINDING, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // VarName TypeDeclaration? AllowingEmpty? PositionalVar? "in" ExprSingle
  static boolean ForBindingDetails(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForBindingDetails")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = VarName(builder_, level_ + 1);
    result_ = result_ && ForBindingDetails_1(builder_, level_ + 1);
    result_ = result_ && ForBindingDetails_2(builder_, level_ + 1);
    result_ = result_ && ForBindingDetails_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_IN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, FLWORExprRecover_parser_);
    return result_;
  }

  // TypeDeclaration?
  private static boolean ForBindingDetails_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForBindingDetails_1")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  // AllowingEmpty?
  private static boolean ForBindingDetails_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForBindingDetails_2")) return false;
    AllowingEmpty(builder_, level_ + 1);
    return true;
  }

  // PositionalVar?
  private static boolean ForBindingDetails_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForBindingDetails_3")) return false;
    PositionalVar(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "for" ForBinding ("," ForBinding)*
  public static boolean ForClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForClause")) return false;
    if (!nextTokenIs(builder_, K_FOR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_FOR);
    result_ = result_ && ForBinding(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && ForClause_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, FOR_CLAUSE, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ("," ForBinding)*
  private static boolean ForClause_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForClause_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!ForClause_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ForClause_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," ForBinding
  private static boolean ForClause_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForClause_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && ForBinding(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ("child" "::")
  //  | ("descendant" "::")
  //  | ("attribute" "::")
  //  | ("self" "::")
  //  | ("descendant-or-self" "::")
  //  | ("following-sibling" "::")
  //  | ("following" "::")
  static boolean ForwardAxis(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ForwardAxis_0(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_1(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_2(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_3(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_4(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_5(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "child" "::"
  private static boolean ForwardAxis_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_CHILD);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "descendant" "::"
  private static boolean ForwardAxis_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DESCENDANT);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "attribute" "::"
  private static boolean ForwardAxis_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ATTRIBUTE);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "self" "::"
  private static boolean ForwardAxis_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_3")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_SELF);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "descendant-or-self" "::"
  private static boolean ForwardAxis_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DESCENDANT_OR_SELF);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "following-sibling" "::"
  private static boolean ForwardAxis_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_5")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_FOLLOWING_SIBLING);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "following" "::"
  private static boolean ForwardAxis_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_6")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_FOLLOWING);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // FullForwardStep | AbbrevForwardStep
  public static boolean ForwardStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardStep")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<forward step>");
    result_ = FullForwardStep(builder_, level_ + 1);
    if (!result_) result_ = AbbrevForwardStep(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, FORWARD_STEP, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ForwardAxis NodeTest
  static boolean FullForwardStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FullForwardStep")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = ForwardAxis(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && NodeTest(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ReverseAxis NodeTest
  static boolean FullReverseStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FullReverseStep")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = ReverseAxis(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && NodeTest(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // IntegerLiteral
  public static boolean FunctionArity(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionArity")) return false;
    if (!nextTokenIs(builder_, INTEGERLITERAL)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, INTEGERLITERAL);
    exit_section_(builder_, marker_, FUNCTION_ARITY, result_);
    return result_;
  }

  /* ********************************************************** */
  // EnclosedExpr
  public static boolean FunctionBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionBody")) return false;
    if (!nextTokenIs(builder_, L_C_BRACE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = EnclosedExpr(builder_, level_ + 1);
    exit_section_(builder_, marker_, FUNCTION_BODY, result_);
    return result_;
  }

  /* ********************************************************** */
  // FunctionName ArgumentList
  public static boolean FunctionCall(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionCall")) return false;
    if (!nextTokenIs(builder_, "<function call>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<function call>");
    result_ = FunctionName(builder_, level_ + 1);
    result_ = result_ && ArgumentList(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, FUNCTION_CALL, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" (MarklogicAnnotation | CompatibilityAnnotation | Annotation)* "function" FunctionName ParamList ("as" SequenceType)? (FunctionBody | "external") Separator
  public static boolean FunctionDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && FunctionDecl_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_FUNCTION);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, FunctionName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, ParamList(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, FunctionDecl_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, FunctionDecl_6(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, FUNCTION_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (MarklogicAnnotation | CompatibilityAnnotation | Annotation)*
  private static boolean FunctionDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDecl_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!FunctionDecl_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "FunctionDecl_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // MarklogicAnnotation | CompatibilityAnnotation | Annotation
  private static boolean FunctionDecl_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDecl_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = MarklogicAnnotation(builder_, level_ + 1);
    if (!result_) result_ = CompatibilityAnnotation(builder_, level_ + 1);
    if (!result_) result_ = Annotation(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("as" SequenceType)?
  private static boolean FunctionDecl_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDecl_5")) return false;
    FunctionDecl_5_0(builder_, level_ + 1);
    return true;
  }

  // "as" SequenceType
  private static boolean FunctionDecl_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDecl_5_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_AS);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // FunctionBody | "external"
  private static boolean FunctionDecl_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDecl_6")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = FunctionBody(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, K_EXTERNAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NamedFunctionRef | InlineFunctionExpr
  public static boolean FunctionItemExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionItemExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<function item expr>");
    result_ = NamedFunctionRef(builder_, level_ + 1);
    if (!result_) result_ = InlineFunctionExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, FUNCTION_ITEM_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean FunctionLocalName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionLocalName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NCNAME);
    exit_section_(builder_, marker_, FUNCTION_LOCAL_NAME, result_);
    return result_;
  }

  /* ********************************************************** */
  // Prefix ':' FunctionLocalName | FunctionLocalName | URIQualifiedName
  public static boolean FunctionName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionName")) return false;
    if (!nextTokenIs(builder_, "<function name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<function name>");
    result_ = FunctionName_0(builder_, level_ + 1);
    if (!result_) result_ = FunctionLocalName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, URIQUALIFIEDNAME);
    exit_section_(builder_, level_, marker_, FUNCTION_NAME, result_, false, null);
    return result_;
  }

  // Prefix ':' FunctionLocalName
  private static boolean FunctionName_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionName_0")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = Prefix(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    pinned_ = result_; // pin = 2
    result_ = result_ && FunctionLocalName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Annotation* (AnyFunctionTest | TypedFunctionTest)
  public static boolean FunctionTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionTest")) return false;
    if (!nextTokenIs(builder_, "<function test>", PERCENT, K_FUNCTION)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<function test>");
    result_ = FunctionTest_0(builder_, level_ + 1);
    result_ = result_ && FunctionTest_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, FUNCTION_TEST, result_, false, null);
    return result_;
  }

  // Annotation*
  private static boolean FunctionTest_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionTest_0")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!Annotation(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "FunctionTest_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // AnyFunctionTest | TypedFunctionTest
  private static boolean FunctionTest_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionTest_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = AnyFunctionTest(builder_, level_ + 1);
    if (!result_) result_ = TypedFunctionTest(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // EqualityComp  | RelationalComp
  static boolean GeneralComp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GeneralComp")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = EqualityComp(builder_, level_ + 1);
    if (!result_) result_ = RelationalComp(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "item" "(" ")"
  public static boolean GeneralItemType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GeneralItemType")) return false;
    if (!nextTokenIs(builder_, K_ITEM)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_ITEM);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, level_, marker_, GENERAL_ITEM_TYPE, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "group" "by" GroupingSpecList
  public static boolean GroupByClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupByClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<group by clause>");
    result_ = consumeToken(builder_, K_GROUP);
    result_ = result_ && consumeToken(builder_, K_BY);
    pinned_ = result_; // pin = 2
    result_ = result_ && GroupingSpecList(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, GROUP_BY_CLAUSE, result_, pinned_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // GroupingVariable (TypeDeclaration? ":=" ExprSingle)? ("collation" URILiteral)?
  public static boolean GroupingSpec(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpec")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = GroupingVariable(builder_, level_ + 1);
    result_ = result_ && GroupingSpec_1(builder_, level_ + 1);
    result_ = result_ && GroupingSpec_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, GROUPING_SPEC, result_);
    return result_;
  }

  // (TypeDeclaration? ":=" ExprSingle)?
  private static boolean GroupingSpec_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpec_1")) return false;
    GroupingSpec_1_0(builder_, level_ + 1);
    return true;
  }

  // TypeDeclaration? ":=" ExprSingle
  private static boolean GroupingSpec_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpec_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = GroupingSpec_1_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // TypeDeclaration?
  private static boolean GroupingSpec_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpec_1_0_0")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  // ("collation" URILiteral)?
  private static boolean GroupingSpec_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpec_2")) return false;
    GroupingSpec_2_0(builder_, level_ + 1);
    return true;
  }

  // "collation" URILiteral
  private static boolean GroupingSpec_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpec_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_COLLATION);
    result_ = result_ && URILiteral(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // GroupingSpec ("," GroupingSpec)*
  public static boolean GroupingSpecList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpecList")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = GroupingSpec(builder_, level_ + 1);
    result_ = result_ && GroupingSpecList_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, GROUPING_SPEC_LIST, result_);
    return result_;
  }

  // ("," GroupingSpec)*
  private static boolean GroupingSpecList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpecList_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!GroupingSpecList_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "GroupingSpecList_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," GroupingSpec
  private static boolean GroupingSpecList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpecList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && GroupingSpec(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "$" VarName
  public static boolean GroupingVariable(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingVariable")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    exit_section_(builder_, marker_, GROUPING_VARIABLE, result_);
    return result_;
  }

  /* ********************************************************** */
  // "if" "(" Expr ")" "then" ExprSingle "else" ExprSingle
  public static boolean IfExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfExpr")) return false;
    if (!nextTokenIs(builder_, K_IF)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_IF);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, L_PAR));
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, R_PAR)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, K_THEN)) && result_;
    result_ = pinned_ && report_error_(builder_, ExprSingle(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, K_ELSE)) && result_;
    result_ = pinned_ && ExprSingle(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, IF_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // SchemaImport | ModuleImport
  static boolean Import(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Import")) return false;
    if (!nextTokenIs(builder_, K_IMPORT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = SchemaImport(builder_, level_ + 1);
    if (!result_) result_ = ModuleImport(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "inherit" | "no-inherit"
  public static boolean InheritMode(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InheritMode")) return false;
    if (!nextTokenIs(builder_, "<inherit mode>", K_INHERIT, K_NO_INHERIT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<inherit mode>");
    result_ = consumeToken(builder_, K_INHERIT);
    if (!result_) result_ = consumeToken(builder_, K_NO_INHERIT);
    exit_section_(builder_, level_, marker_, INHERIT_MODE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ForClause | WindowClause | LetClause
  static boolean InitialClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InitialClause")) return false;
    if (!nextTokenIs(builder_, "", K_FOR, K_LET)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ForClause(builder_, level_ + 1);
    if (!result_) result_ = WindowClause(builder_, level_ + 1);
    if (!result_) result_ = LetClause(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Annotation* "function" ParamList ("as" SequenceType)? FunctionBody
  public static boolean InlineFunctionExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InlineFunctionExpr")) return false;
    if (!nextTokenIs(builder_, "<inline function expr>", PERCENT, K_FUNCTION)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<inline function expr>");
    result_ = InlineFunctionExpr_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_FUNCTION);
    result_ = result_ && ParamList(builder_, level_ + 1);
    result_ = result_ && InlineFunctionExpr_3(builder_, level_ + 1);
    result_ = result_ && FunctionBody(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, INLINE_FUNCTION_EXPR, result_, false, null);
    return result_;
  }

  // Annotation*
  private static boolean InlineFunctionExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InlineFunctionExpr_0")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!Annotation(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "InlineFunctionExpr_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ("as" SequenceType)?
  private static boolean InlineFunctionExpr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InlineFunctionExpr_3")) return false;
    InlineFunctionExpr_3_0(builder_, level_ + 1);
    return true;
  }

  // "as" SequenceType
  private static boolean InlineFunctionExpr_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InlineFunctionExpr_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_AS);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "insert" ("node" | "nodes") SourceExpr InsertExprTargetChoice TargetExpr
  public static boolean InsertExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InsertExpr")) return false;
    if (!nextTokenIs(builder_, K_INSERT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_INSERT);
    result_ = result_ && InsertExpr_1(builder_, level_ + 1);
    result_ = result_ && SourceExpr(builder_, level_ + 1);
    result_ = result_ && InsertExprTargetChoice(builder_, level_ + 1);
    result_ = result_ && TargetExpr(builder_, level_ + 1);
    exit_section_(builder_, marker_, INSERT_EXPR, result_);
    return result_;
  }

  // "node" | "nodes"
  private static boolean InsertExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InsertExpr_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_NODE);
    if (!result_) result_ = consumeToken(builder_, K_NODES);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (("as" ("first" | "last"))? "into")
  //  | "after"
  //  | "before"
  public static boolean InsertExprTargetChoice(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InsertExprTargetChoice")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<insert expr target choice>");
    result_ = InsertExprTargetChoice_0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, K_AFTER);
    if (!result_) result_ = consumeToken(builder_, K_BEFORE);
    exit_section_(builder_, level_, marker_, INSERT_EXPR_TARGET_CHOICE, result_, false, null);
    return result_;
  }

  // ("as" ("first" | "last"))? "into"
  private static boolean InsertExprTargetChoice_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InsertExprTargetChoice_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = InsertExprTargetChoice_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_INTO);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("as" ("first" | "last"))?
  private static boolean InsertExprTargetChoice_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InsertExprTargetChoice_0_0")) return false;
    InsertExprTargetChoice_0_0_0(builder_, level_ + 1);
    return true;
  }

  // "as" ("first" | "last")
  private static boolean InsertExprTargetChoice_0_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InsertExprTargetChoice_0_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_AS);
    result_ = result_ && InsertExprTargetChoice_0_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "first" | "last"
  private static boolean InsertExprTargetChoice_0_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InsertExprTargetChoice_0_0_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_FIRST);
    if (!result_) result_ = consumeToken(builder_, K_LAST);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "instance" "of"
  public static boolean InstanceOfOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InstanceOfOperator")) return false;
    if (!nextTokenIs(builder_, K_INSTANCE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_INSTANCE);
    result_ = result_ && consumeToken(builder_, K_OF);
    exit_section_(builder_, marker_, INSTANCE_OF_OPERATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // InstanceOfOperator SequenceType
  static boolean InstanceOfOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InstanceOfOptionalExpr")) return false;
    if (!nextTokenIs(builder_, K_INSTANCE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = InstanceOfOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && SequenceType(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // TreatExpr InstanceOfOptionalExpr?
  public static boolean InstanceofExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InstanceofExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<instanceof expr>");
    result_ = TreatExpr(builder_, level_ + 1);
    result_ = result_ && InstanceofExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, INSTANCEOF_EXPR, result_, false, null);
    return result_;
  }

  // InstanceOfOptionalExpr?
  private static boolean InstanceofExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InstanceofExpr_1")) return false;
    InstanceOfOptionalExpr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // InitialClause | WhereClause | GroupByClause | OrderByClause | CountClause
  static boolean IntermediateClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntermediateClause")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = InitialClause(builder_, level_ + 1);
    if (!result_) result_ = WhereClause(builder_, level_ + 1);
    if (!result_) result_ = GroupByClause(builder_, level_ + 1);
    if (!result_) result_ = OrderByClause(builder_, level_ + 1);
    if (!result_) result_ = CountClause(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // InstanceofExpr IntersectExceptOptionalExpr*
  public static boolean IntersectExceptExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntersectExceptExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<intersect except expr>");
    result_ = InstanceofExpr(builder_, level_ + 1);
    result_ = result_ && IntersectExceptExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, INTERSECT_EXCEPT_EXPR, result_, false, null);
    return result_;
  }

  // IntersectExceptOptionalExpr*
  private static boolean IntersectExceptExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntersectExceptExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!IntersectExceptOptionalExpr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "IntersectExceptExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // "intersect" | "except"
  public static boolean IntersectExceptOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntersectExceptOperator")) return false;
    if (!nextTokenIs(builder_, "<intersect except operator>", K_EXCEPT, K_INTERSECT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<intersect except operator>");
    result_ = consumeToken(builder_, K_INTERSECT);
    if (!result_) result_ = consumeToken(builder_, K_EXCEPT);
    exit_section_(builder_, level_, marker_, INTERSECT_EXCEPT_OPERATOR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IntersectExceptOperator InstanceofExpr
  static boolean IntersectExceptOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntersectExceptOptionalExpr")) return false;
    if (!nextTokenIs(builder_, "", K_EXCEPT, K_INTERSECT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = IntersectExceptOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && InstanceofExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // KindTest | GeneralItemType | FunctionTest | AtomicOrUnionType | ParenthesizedItemType
  public static boolean ItemType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemType")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<item type>");
    result_ = KindTest(builder_, level_ + 1);
    if (!result_) result_ = GeneralItemType(builder_, level_ + 1);
    if (!result_) result_ = FunctionTest(builder_, level_ + 1);
    if (!result_) result_ = AtomicOrUnionType(builder_, level_ + 1);
    if (!result_) result_ = ParenthesizedItemType(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ITEM_TYPE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // DocumentTest
  //  | ElementTest
  //  | MapTest
  //  | AttributeTest
  //  | SchemaElementTest
  //  | SchemaAttributeTest
  //  | PITest
  //  | CommentTest
  //  | TextTest
  //  | NamespaceNodeTest
  //  | AnyKindTest
  public static boolean KindTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "KindTest")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<kind test>");
    result_ = DocumentTest(builder_, level_ + 1);
    if (!result_) result_ = ElementTest(builder_, level_ + 1);
    if (!result_) result_ = MapTest(builder_, level_ + 1);
    if (!result_) result_ = AttributeTest(builder_, level_ + 1);
    if (!result_) result_ = SchemaElementTest(builder_, level_ + 1);
    if (!result_) result_ = SchemaAttributeTest(builder_, level_ + 1);
    if (!result_) result_ = PITest(builder_, level_ + 1);
    if (!result_) result_ = CommentTest(builder_, level_ + 1);
    if (!result_) result_ = TextTest(builder_, level_ + 1);
    if (!result_) result_ = NamespaceNodeTest(builder_, level_ + 1);
    if (!result_) result_ = AnyKindTest(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, KIND_TEST, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "$" VarName TypeDeclaration? ":=" ExprSingle
  public static boolean LetBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetBinding")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<let binding>");
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && LetBinding_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, LET_BINDING, result_, false, FLWORExprRecover_parser_);
    return result_;
  }

  // TypeDeclaration?
  private static boolean LetBinding_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetBinding_2")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "let" LetBinding ("," LetBinding)*
  public static boolean LetClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetClause")) return false;
    if (!nextTokenIs(builder_, K_LET)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_LET);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, LetBinding(builder_, level_ + 1));
    result_ = pinned_ && LetClause_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, LET_CLAUSE, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ("," LetBinding)*
  private static boolean LetClause_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetClause_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!LetClause_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "LetClause_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," LetBinding
  private static boolean LetClause_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetClause_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && LetBinding(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ModuleDecl Prolog
  static boolean LibraryModule(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LibraryModule")) return false;
    if (!nextTokenIs(builder_, K_MODULE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = ModuleDecl(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && Prolog(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // NumericLiteral | StringLiteral
  public static boolean Literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Literal")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<literal>");
    result_ = NumericLiteral(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRINGLITERAL);
    exit_section_(builder_, level_, marker_, LITERAL, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean LocalPart(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LocalPart")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NCNAME);
    exit_section_(builder_, marker_, LOCAL_PART, result_);
    return result_;
  }

  /* ********************************************************** */
  // Prolog QueryBody
  static boolean MainModule(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MainModule")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = Prolog(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && QueryBody(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ExprSingle ":=" ExprSingle
  public static boolean MapEntry(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapEntry")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<map entry>");
    result_ = ExprSingle(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, MAP_ENTRY, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // MapEntry ("," MapEntry)* ","?
  public static boolean MapEntryList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapEntryList")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<map entry list>");
    result_ = MapEntry(builder_, level_ + 1);
    result_ = result_ && MapEntryList_1(builder_, level_ + 1);
    result_ = result_ && MapEntryList_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, MAP_ENTRY_LIST, result_, false, null);
    return result_;
  }

  // ("," MapEntry)*
  private static boolean MapEntryList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapEntryList_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!MapEntryList_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "MapEntryList_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," MapEntry
  private static boolean MapEntryList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapEntryList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && MapEntry(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ","?
  private static boolean MapEntryList_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapEntryList_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // "map" "(" ("*"| (AtomicOrUnionType "," SequenceType)) ")"
  public static boolean MapTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapTest")) return false;
    if (!nextTokenIs(builder_, K_MAP)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_MAP);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, MapTest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, MAP_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  // "*"| (AtomicOrUnionType "," SequenceType)
  private static boolean MapTest_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapTest_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STAR_SIGN);
    if (!result_) result_ = MapTest_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // AtomicOrUnionType "," SequenceType
  private static boolean MapTest_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapTest_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = AtomicOrUnionType(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "private"
  public static boolean MarklogicAnnotation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MarklogicAnnotation")) return false;
    if (!nextTokenIs(builder_, K_PRIVATE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_PRIVATE);
    exit_section_(builder_, marker_, MARKLOGIC_ANNOTATION, result_);
    return result_;
  }

  /* ********************************************************** */
  // "(" "$" VarName ")"
  public static boolean MarklogicCatchErrorList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MarklogicCatchErrorList")) return false;
    if (!nextTokenIs(builder_, L_PAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAR);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, marker_, MARKLOGIC_CATCH_ERROR_LIST, result_);
    return result_;
  }

  /* ********************************************************** */
  // "namespace" "::" NodeTest
  public static boolean MarklogicNamespaceAxis(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MarklogicNamespaceAxis")) return false;
    if (!nextTokenIs(builder_, K_NAMESPACE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_NAMESPACE);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    pinned_ = result_; // pin = 2
    result_ = result_ && NodeTest(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, MARKLOGIC_NAMESPACE_AXIS, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // VersionDecl? (LibraryModule | MainModule)
  static boolean Module(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = Module_0(builder_, level_ + 1);
    result_ = result_ && Module_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // VersionDecl?
  private static boolean Module_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module_0")) return false;
    VersionDecl(builder_, level_ + 1);
    return true;
  }

  // LibraryModule | MainModule
  private static boolean Module_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = LibraryModule(builder_, level_ + 1);
    if (!result_) result_ = MainModule(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "module" "namespace" NamespacePrefix "=" URILiteral Separator
  public static boolean ModuleDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleDecl")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<module decl>");
    result_ = consumeToken(builder_, K_MODULE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_NAMESPACE));
    result_ = pinned_ && report_error_(builder_, NamespacePrefix(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, EQUAL)) && result_;
    result_ = pinned_ && report_error_(builder_, URILiteral(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, MODULE_DECL, result_, pinned_, ModuleDeclRecover_parser_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !('declare' | 'import')
  static boolean ModuleDeclRecover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleDeclRecover")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !ModuleDeclRecover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  // 'declare' | 'import'
  private static boolean ModuleDeclRecover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleDeclRecover_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DECLARE);
    if (!result_) result_ = consumeToken(builder_, K_IMPORT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "import" "module" ("namespace" NamespacePrefix "=")? ModuleImportNamespace ("at" ModuleImportPath ("," ModuleImportPath)*)? Separator
  public static boolean ModuleImport(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport")) return false;
    if (!nextTokenIs(builder_, K_IMPORT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_IMPORT);
    result_ = result_ && consumeToken(builder_, K_MODULE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, ModuleImport_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, ModuleImportNamespace(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, ModuleImport_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, MODULE_IMPORT, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ("namespace" NamespacePrefix "=")?
  private static boolean ModuleImport_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_2")) return false;
    ModuleImport_2_0(builder_, level_ + 1);
    return true;
  }

  // "namespace" NamespacePrefix "="
  private static boolean ModuleImport_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_NAMESPACE);
    result_ = result_ && NamespacePrefix(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("at" ModuleImportPath ("," ModuleImportPath)*)?
  private static boolean ModuleImport_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_4")) return false;
    ModuleImport_4_0(builder_, level_ + 1);
    return true;
  }

  // "at" ModuleImportPath ("," ModuleImportPath)*
  private static boolean ModuleImport_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_AT);
    result_ = result_ && ModuleImportPath(builder_, level_ + 1);
    result_ = result_ && ModuleImport_4_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("," ModuleImportPath)*
  private static boolean ModuleImport_4_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_4_0_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!ModuleImport_4_0_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ModuleImport_4_0_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," ModuleImportPath
  private static boolean ModuleImport_4_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_4_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && ModuleImportPath(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ModuleImportPath
  public static boolean ModuleImportNamespace(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImportNamespace")) return false;
    if (!nextTokenIs(builder_, STRINGLITERAL)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ModuleImportPath(builder_, level_ + 1);
    exit_section_(builder_, marker_, MODULE_IMPORT_NAMESPACE, result_);
    return result_;
  }

  /* ********************************************************** */
  // URILiteral
  public static boolean ModuleImportPath(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImportPath")) return false;
    if (!nextTokenIs(builder_, STRINGLITERAL)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = URILiteral(builder_, level_ + 1);
    exit_section_(builder_, marker_, MODULE_IMPORT_PATH, result_);
    return result_;
  }

  /* ********************************************************** */
  // "$" VarName TypeDeclaration? "in" ExprSingle
  public static boolean MultiVariableBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiVariableBinding")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && MultiVariableBinding_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_IN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, marker_, MULTI_VARIABLE_BINDING, result_);
    return result_;
  }

  // TypeDeclaration?
  private static boolean MultiVariableBinding_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiVariableBinding_2")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // UnionExpr MultiplicativeOptionalExpr*
  public static boolean MultiplicativeExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiplicativeExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<multiplicative expr>");
    result_ = UnionExpr(builder_, level_ + 1);
    result_ = result_ && MultiplicativeExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, MULTIPLICATIVE_EXPR, result_, false, null);
    return result_;
  }

  // MultiplicativeOptionalExpr*
  private static boolean MultiplicativeExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiplicativeExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!MultiplicativeOptionalExpr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "MultiplicativeExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // "*" | "div" | "idiv" | "mod"
  public static boolean MultiplicativeOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiplicativeOperator")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<multiplicative operator>");
    result_ = consumeToken(builder_, STAR_SIGN);
    if (!result_) result_ = consumeToken(builder_, K_DIV);
    if (!result_) result_ = consumeToken(builder_, K_IDIV);
    if (!result_) result_ = consumeToken(builder_, K_MOD);
    exit_section_(builder_, level_, marker_, MULTIPLICATIVE_OPERATOR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // MultiplicativeOperator UnionExpr
  static boolean MultiplicativeOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiplicativeOptionalExpr")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = MultiplicativeOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && UnionExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Wildcard | EQName
  public static boolean NameTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NameTest")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<name test>");
    result_ = Wildcard(builder_, level_ + 1);
    if (!result_) result_ = EQName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, NAME_TEST, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // FunctionName "#" FunctionArity
  public static boolean NamedFunctionRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFunctionRef")) return false;
    if (!nextTokenIs(builder_, "<named function ref>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<named function ref>");
    result_ = FunctionName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, HASH);
    result_ = result_ && FunctionArity(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, NAMED_FUNCTION_REF, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "namespace" NamespacePrefix "=" URILiteral Separator
  public static boolean NamespaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamespaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_NAMESPACE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, NamespacePrefix(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, EQUAL)) && result_;
    result_ = pinned_ && report_error_(builder_, URILiteral(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, NAMESPACE_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "namespace-node" "(" ")"
  public static boolean NamespaceNodeTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamespaceNodeTest")) return false;
    if (!nextTokenIs(builder_, K_NAMESPACE_NODE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_NAMESPACE_NODE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, level_, marker_, NAMESPACE_NODE_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean NamespacePrefix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamespacePrefix")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NCNAME);
    exit_section_(builder_, marker_, NAMESPACE_PREFIX, result_);
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean NewNameExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NewNameExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<new name expr>");
    result_ = ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, NEW_NAME_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // VarName
  public static boolean NextItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NextItem")) return false;
    if (!nextTokenIs(builder_, "<next item>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<next item>");
    result_ = VarName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, NEXT_ITEM, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "is" | "<<" | ">>"
  public static boolean NodeComp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NodeComp")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<node comp>");
    result_ = consumeToken(builder_, K_IS);
    if (!result_) result_ = consumeToken(builder_, NODECOMP_LT);
    if (!result_) result_ = consumeToken(builder_, NODECOMP_GT);
    exit_section_(builder_, level_, marker_, NODE_COMP, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // KindTest | NameTest
  public static boolean NodeTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NodeTest")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<node test>");
    result_ = KindTest(builder_, level_ + 1);
    if (!result_) result_ = NameTest(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, NODE_TEST, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IntegerLiteral | DecimalLiteral | DoubleLiteral
  public static boolean NumericLiteral(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NumericLiteral")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<numeric literal>");
    result_ = consumeToken(builder_, INTEGERLITERAL);
    if (!result_) result_ = consumeToken(builder_, DECIMALLITERAL);
    if (!result_) result_ = consumeToken(builder_, DOUBLELITERAL);
    exit_section_(builder_, level_, marker_, NUMERIC_LITERAL, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "?" | "*" | "+"
  public static boolean OccurrenceIndicator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OccurrenceIndicator")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<occurrence indicator>");
    result_ = consumeToken(builder_, QUESTIONMARK);
    if (!result_) result_ = consumeToken(builder_, STAR_SIGN);
    if (!result_) result_ = consumeToken(builder_, OP_PLUS);
    exit_section_(builder_, level_, marker_, OCCURRENCE_INDICATOR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "option" EQName StringLiteral Separator
  public static boolean OptionDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OptionDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_OPTION);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, EQName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, STRINGLITERAL)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, OPTION_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "," Argument
  static boolean OptionalArgumentAfterComma(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OptionalArgumentAfterComma")) return false;
    if (!nextTokenIs(builder_, COMMA)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, COMMA);
    pinned_ = result_; // pin = 1
    result_ = result_ && Argument(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // AndExpr OrMultipliedExpr*
  public static boolean OrExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<or expr>");
    result_ = AndExpr(builder_, level_ + 1);
    result_ = result_ && OrExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, OR_EXPR, result_, false, null);
    return result_;
  }

  // OrMultipliedExpr*
  private static boolean OrExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!OrMultipliedExpr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "OrExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // OrOperator AndExpr
  static boolean OrMultipliedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrMultipliedExpr")) return false;
    if (!nextTokenIs(builder_, K_OR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = OrOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && AndExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "or"
  public static boolean OrOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrOperator")) return false;
    if (!nextTokenIs(builder_, K_OR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_OR);
    exit_section_(builder_, marker_, OR_OPERATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // (("order" "by") | ("stable" "order" "by")) OrderSpecList
  public static boolean OrderByClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderByClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<order by clause>");
    result_ = OrderByClause_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && OrderSpecList(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ORDER_BY_CLAUSE, result_, pinned_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  // ("order" "by") | ("stable" "order" "by")
  private static boolean OrderByClause_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderByClause_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = OrderByClause_0_0(builder_, level_ + 1);
    if (!result_) result_ = OrderByClause_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "order" "by"
  private static boolean OrderByClause_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderByClause_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ORDER);
    result_ = result_ && consumeToken(builder_, K_BY);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "stable" "order" "by"
  private static boolean OrderByClause_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderByClause_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_STABLE);
    result_ = result_ && consumeToken(builder_, K_ORDER);
    result_ = result_ && consumeToken(builder_, K_BY);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ("ascending" | "descending")? ("empty" ("greatest" | "least"))? ("collation" URILiteral)?
  static boolean OrderModifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = OrderModifier_0(builder_, level_ + 1);
    result_ = result_ && OrderModifier_1(builder_, level_ + 1);
    result_ = result_ && OrderModifier_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("ascending" | "descending")?
  private static boolean OrderModifier_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier_0")) return false;
    OrderModifier_0_0(builder_, level_ + 1);
    return true;
  }

  // "ascending" | "descending"
  private static boolean OrderModifier_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ASCENDING);
    if (!result_) result_ = consumeToken(builder_, K_DESCENDING);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("empty" ("greatest" | "least"))?
  private static boolean OrderModifier_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier_1")) return false;
    OrderModifier_1_0(builder_, level_ + 1);
    return true;
  }

  // "empty" ("greatest" | "least")
  private static boolean OrderModifier_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_EMPTY);
    result_ = result_ && OrderModifier_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "greatest" | "least"
  private static boolean OrderModifier_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_GREATEST);
    if (!result_) result_ = consumeToken(builder_, K_LEAST);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("collation" URILiteral)?
  private static boolean OrderModifier_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier_2")) return false;
    OrderModifier_2_0(builder_, level_ + 1);
    return true;
  }

  // "collation" URILiteral
  private static boolean OrderModifier_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_COLLATION);
    result_ = result_ && URILiteral(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle OrderModifier
  public static boolean OrderSpec(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderSpec")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<order spec>");
    result_ = ExprSingle(builder_, level_ + 1);
    result_ = result_ && OrderModifier(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ORDER_SPEC, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // OrderSpec ("," OrderSpec)*
  public static boolean OrderSpecList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderSpecList")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<order spec list>");
    result_ = OrderSpec(builder_, level_ + 1);
    result_ = result_ && OrderSpecList_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ORDER_SPEC_LIST, result_, false, null);
    return result_;
  }

  // ("," OrderSpec)*
  private static boolean OrderSpecList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderSpecList_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!OrderSpecList_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "OrderSpecList_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," OrderSpec
  private static boolean OrderSpecList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderSpecList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && OrderSpec(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "ordered" "{" Expr "}"
  public static boolean OrderedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderedExpr")) return false;
    if (!nextTokenIs(builder_, K_ORDERED)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_ORDERED);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, Expr(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_C_BRACE) && result_;
    exit_section_(builder_, level_, marker_, ORDERED_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "declare" "ordering" ("ordered" | "unordered") Separator
  public static boolean OrderingModeDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderingModeDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_ORDERING);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, OrderingModeDecl_2(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, ORDERING_MODE_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  // "ordered" | "unordered"
  private static boolean OrderingModeDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderingModeDecl_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ORDERED);
    if (!result_) result_ = consumeToken(builder_, K_UNORDERED);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "processing-instruction" "(" (NCName | StringLiteral)? ")"
  public static boolean PITest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PITest")) return false;
    if (!nextTokenIs(builder_, K_PI)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_PI);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, PITest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, PI_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (NCName | StringLiteral)?
  private static boolean PITest_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PITest_2")) return false;
    PITest_2_0(builder_, level_ + 1);
    return true;
  }

  // NCName | StringLiteral
  private static boolean PITest_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PITest_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NCNAME);
    if (!result_) result_ = consumeToken(builder_, STRINGLITERAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "$" VarName TypeDeclaration?
  public static boolean Param(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Param")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && Param_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, PARAM, result_);
    return result_;
  }

  // TypeDeclaration?
  private static boolean Param_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Param_2")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "(" (Param ("," Param)*)? ")"
  public static boolean ParamList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParamList")) return false;
    if (!nextTokenIs(builder_, L_PAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ParamList_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, PARAM_LIST, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (Param ("," Param)*)?
  private static boolean ParamList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParamList_1")) return false;
    ParamList_1_0(builder_, level_ + 1);
    return true;
  }

  // Param ("," Param)*
  private static boolean ParamList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParamList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = Param(builder_, level_ + 1);
    result_ = result_ && ParamList_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("," Param)*
  private static boolean ParamList_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParamList_1_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!ParamList_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ParamList_1_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," Param
  private static boolean ParamList_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParamList_1_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && Param(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "(" Expr? ")"
  public static boolean ParenthesizedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenthesizedExpr")) return false;
    if (!nextTokenIs(builder_, L_PAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ParenthesizedExpr_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, PARENTHESIZED_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Expr?
  private static boolean ParenthesizedExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenthesizedExpr_1")) return false;
    Expr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "(" ItemType ")"
  public static boolean ParenthesizedItemType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenthesizedItemType")) return false;
    if (!nextTokenIs(builder_, L_PAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAR);
    result_ = result_ && ItemType(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, marker_, PARENTHESIZED_ITEM_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // RootPathExpr | RootPathOrChildExpr | RelativePathExpr
  public static boolean PathExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<path expr>");
    result_ = RootPathExpr(builder_, level_ + 1);
    if (!result_) result_ = RootPathOrChildExpr(builder_, level_ + 1);
    if (!result_) result_ = RelativePathExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, PATH_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "at" "$" VarName
  public static boolean PositionalVar(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PositionalVar")) return false;
    if (!nextTokenIs(builder_, K_AT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_AT);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    exit_section_(builder_, marker_, POSITIONAL_VAR, result_);
    return result_;
  }

  /* ********************************************************** */
  // PrimaryExpr (Predicate | ArgumentList)*
  public static boolean PostfixExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PostfixExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<postfix expr>");
    result_ = PrimaryExpr(builder_, level_ + 1);
    result_ = result_ && PostfixExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, POSTFIX_EXPR, result_, false, null);
    return result_;
  }

  // (Predicate | ArgumentList)*
  private static boolean PostfixExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PostfixExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!PostfixExpr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "PostfixExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // Predicate | ArgumentList
  private static boolean PostfixExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PostfixExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = Predicate(builder_, level_ + 1);
    if (!result_) result_ = ArgumentList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "(#" S? EQName (S PragmaContents)? "#)"
  public static boolean Pragma(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pragma")) return false;
    if (!nextTokenIs(builder_, PRAGMA_BEGIN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, PRAGMA_BEGIN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Pragma_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, EQName(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, Pragma_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, PRAGMA_END) && result_;
    exit_section_(builder_, level_, marker_, PRAGMA, result_, pinned_, null);
    return result_ || pinned_;
  }

  // S?
  private static boolean Pragma_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pragma_1")) return false;
    consumeToken(builder_, S);
    return true;
  }

  // (S PragmaContents)?
  private static boolean Pragma_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pragma_3")) return false;
    Pragma_3_0(builder_, level_ + 1);
    return true;
  }

  // S PragmaContents
  private static boolean Pragma_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pragma_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, S);
    result_ = result_ && PragmaContents(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // PragmaContentChar*
  public static boolean PragmaContents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PragmaContents")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<pragma contents>");
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, PRAGMACONTENTCHAR)) break;
      if (!empty_element_parsed_guard_(builder_, "PragmaContents", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, level_, marker_, PRAGMA_CONTENTS, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // "[" Expr "]"
  public static boolean Predicate(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Predicate")) return false;
    if (!nextTokenIs(builder_, L_BRACKET)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, L_BRACKET);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Expr(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACKET) && result_;
    exit_section_(builder_, level_, marker_, PREDICATE, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Predicate*
  public static boolean PredicateList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PredicateList")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<predicate list>");
    int pos_ = current_position_(builder_);
    while (true) {
      if (!Predicate(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "PredicateList", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, level_, marker_, PREDICATE_LIST, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // NCName
  public static boolean Prefix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Prefix")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NCNAME);
    exit_section_(builder_, marker_, PREFIX, result_);
    return result_;
  }

  /* ********************************************************** */
  // Expr
  public static boolean PrefixExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PrefixExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<prefix expr>");
    result_ = Expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, PREFIX_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Prefix ':' LocalPart
  static boolean PrefixedName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PrefixedName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = Prefix(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && LocalPart(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "preserve" | "no-preserve"
  public static boolean PreserveMode(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PreserveMode")) return false;
    if (!nextTokenIs(builder_, "<preserve mode>", K_NO_PRESERVE, K_PRESERVE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<preserve mode>");
    result_ = consumeToken(builder_, K_PRESERVE);
    if (!result_) result_ = consumeToken(builder_, K_NO_PRESERVE);
    exit_section_(builder_, level_, marker_, PRESERVE_MODE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // VarName
  public static boolean PreviousItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PreviousItem")) return false;
    if (!nextTokenIs(builder_, "<previous item>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<previous item>");
    result_ = VarName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, PREVIOUS_ITEM, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Literal
  //  | VarRef
  //  | ParenthesizedExpr
  //  | ContextItemExpr
  //  | FunctionCall
  //  | OrderedExpr
  //  | UnorderedExpr
  //  | Constructor
  //  | FunctionItemExpr
  public static boolean PrimaryExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PrimaryExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<primary expr>");
    result_ = Literal(builder_, level_ + 1);
    if (!result_) result_ = VarRef(builder_, level_ + 1);
    if (!result_) result_ = ParenthesizedExpr(builder_, level_ + 1);
    if (!result_) result_ = ContextItemExpr(builder_, level_ + 1);
    if (!result_) result_ = FunctionCall(builder_, level_ + 1);
    if (!result_) result_ = OrderedExpr(builder_, level_ + 1);
    if (!result_) result_ = UnorderedExpr(builder_, level_ + 1);
    if (!result_) result_ = Constructor(builder_, level_ + 1);
    if (!result_) result_ = FunctionItemExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, PRIMARY_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // FirstDecl* SecondDecl*
  static boolean Prolog(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Prolog")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = Prolog_0(builder_, level_ + 1);
    result_ = result_ && Prolog_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // FirstDecl*
  private static boolean Prolog_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Prolog_0")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!FirstDecl(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Prolog_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // SecondDecl*
  private static boolean Prolog_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Prolog_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!SecondDecl(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Prolog_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // PrefixedName
  //  | UnprefixedName
  static boolean QName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = PrefixedName(builder_, level_ + 1);
    if (!result_) result_ = UnprefixedName(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ("some" | "every") MultiVariableBinding ("," MultiVariableBinding)* "satisfies" ExprSingle
  public static boolean QuantifiedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr")) return false;
    if (!nextTokenIs(builder_, "<quantified expr>", K_EVERY, K_SOME)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<quantified expr>");
    result_ = QuantifiedExpr_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, MultiVariableBinding(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, QuantifiedExpr_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, K_SATISFIES)) && result_;
    result_ = pinned_ && ExprSingle(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, QUANTIFIED_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // "some" | "every"
  private static boolean QuantifiedExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_SOME);
    if (!result_) result_ = consumeToken(builder_, K_EVERY);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("," MultiVariableBinding)*
  private static boolean QuantifiedExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!QuantifiedExpr_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "QuantifiedExpr_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," MultiVariableBinding
  private static boolean QuantifiedExpr_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && MultiVariableBinding(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Expr
  public static boolean QueryBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QueryBody")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<query body>");
    result_ = Expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, QUERY_BODY, result_, false, QueryBodyRecover_parser_);
    return result_;
  }

  /* ********************************************************** */
  // !<<eof>>
  static boolean QueryBodyRecover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QueryBodyRecover")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !eof(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Char
  public static boolean QuotAttrContentChar(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuotAttrContentChar")) return false;
    if (!nextTokenIs(builder_, CHAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CHAR);
    exit_section_(builder_, marker_, QUOT_ATTR_CONTENT_CHAR, result_);
    return result_;
  }

  /* ********************************************************** */
  // QuotAttrContentChar
  //  | CommonContent
  public static boolean QuotAttrValueContent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuotAttrValueContent")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<quot attr value content>");
    result_ = QuotAttrContentChar(builder_, level_ + 1);
    if (!result_) result_ = CommonContent(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, QUOT_ATTR_VALUE_CONTENT, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // AdditiveExpr RangeOptionalExpr?
  public static boolean RangeExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RangeExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<range expr>");
    result_ = AdditiveExpr(builder_, level_ + 1);
    result_ = result_ && RangeExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, RANGE_EXPR, result_, false, null);
    return result_;
  }

  // RangeOptionalExpr?
  private static boolean RangeExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RangeExpr_1")) return false;
    RangeOptionalExpr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ToOperator AdditiveExpr
  static boolean RangeOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RangeOptionalExpr")) return false;
    if (!nextTokenIs(builder_, K_TO)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = ToOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && AdditiveExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "<" | "<=" | ">" | ">="
  public static boolean RelationalComp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelationalComp")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<relational comp>");
    result_ = consumeToken(builder_, LT_CHAR);
    if (!result_) result_ = consumeToken(builder_, LE_CHARS);
    if (!result_) result_ = consumeToken(builder_, GT_CHAR);
    if (!result_) result_ = consumeToken(builder_, GE_CHARS);
    exit_section_(builder_, level_, marker_, RELATIONAL_COMP, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // StepExpr RelativePathOptionalExpr*
  static boolean RelativePathExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelativePathExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = StepExpr(builder_, level_ + 1);
    result_ = result_ && RelativePathExpr_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // RelativePathOptionalExpr*
  private static boolean RelativePathExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelativePathExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!RelativePathOptionalExpr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "RelativePathExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // "/" | "//"
  public static boolean RelativePathOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelativePathOperator")) return false;
    if (!nextTokenIs(builder_, "<relative path operator>", SLASH, SLASH_SLASH)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<relative path operator>");
    result_ = consumeToken(builder_, SLASH);
    if (!result_) result_ = consumeToken(builder_, SLASH_SLASH);
    exit_section_(builder_, level_, marker_, RELATIVE_PATH_OPERATOR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (RelativePathOperator) StepExpr
  static boolean RelativePathOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelativePathOptionalExpr")) return false;
    if (!nextTokenIs(builder_, "", SLASH, SLASH_SLASH)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = RelativePathOptionalExpr_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && StepExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (RelativePathOperator)
  private static boolean RelativePathOptionalExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelativePathOptionalExpr_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = RelativePathOperator(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "rename" "node" TargetExpr "as" NewNameExpr
  public static boolean RenameExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RenameExpr")) return false;
    if (!nextTokenIs(builder_, K_RENAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_RENAME);
    result_ = result_ && consumeToken(builder_, K_NODE);
    result_ = result_ && TargetExpr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_AS);
    result_ = result_ && NewNameExpr(builder_, level_ + 1);
    exit_section_(builder_, marker_, RENAME_EXPR, result_);
    return result_;
  }

  /* ********************************************************** */
  // "replace" ("value" "of")? "node" TargetExpr "with" ExprSingle
  public static boolean ReplaceExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReplaceExpr")) return false;
    if (!nextTokenIs(builder_, K_REPLACE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_REPLACE);
    result_ = result_ && ReplaceExpr_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_NODE);
    result_ = result_ && TargetExpr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_WITH);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, marker_, REPLACE_EXPR, result_);
    return result_;
  }

  // ("value" "of")?
  private static boolean ReplaceExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReplaceExpr_1")) return false;
    ReplaceExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // "value" "of"
  private static boolean ReplaceExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReplaceExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_VALUE);
    result_ = result_ && consumeToken(builder_, K_OF);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "return" ExprSingle
  public static boolean ReturnClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReturnClause")) return false;
    if (!nextTokenIs(builder_, K_RETURN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_RETURN);
    pinned_ = result_; // pin = 1
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, RETURN_CLAUSE, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "declare" "revalidation" ("strict" | "lax" | "skip")
  public static boolean RevalidationDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RevalidationDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_REVALIDATION);
    result_ = result_ && RevalidationDecl_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, REVALIDATION_DECL, result_);
    return result_;
  }

  // "strict" | "lax" | "skip"
  private static boolean RevalidationDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RevalidationDecl_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_STRICT);
    if (!result_) result_ = consumeToken(builder_, K_LAX);
    if (!result_) result_ = consumeToken(builder_, K_SKIP);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ("parent" "::")
  //  | ("ancestor" "::")
  //  | ("preceding-sibling" "::")
  //  | ("preceding" "::")
  //  | ("ancestor-or-self" "::")
  static boolean ReverseAxis(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ReverseAxis_0(builder_, level_ + 1);
    if (!result_) result_ = ReverseAxis_1(builder_, level_ + 1);
    if (!result_) result_ = ReverseAxis_2(builder_, level_ + 1);
    if (!result_) result_ = ReverseAxis_3(builder_, level_ + 1);
    if (!result_) result_ = ReverseAxis_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "parent" "::"
  private static boolean ReverseAxis_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_PARENT);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "ancestor" "::"
  private static boolean ReverseAxis_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ANCESTOR);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "preceding-sibling" "::"
  private static boolean ReverseAxis_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_PRECEDING_SIBLING);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "preceding" "::"
  private static boolean ReverseAxis_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_3")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_PRECEDING);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "ancestor-or-self" "::"
  private static boolean ReverseAxis_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ANCESTOR_OR_SELF);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // FullReverseStep | AbbrevReverseStep
  public static boolean ReverseStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseStep")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<reverse step>");
    result_ = FullReverseStep(builder_, level_ + 1);
    if (!result_) result_ = AbbrevReverseStep(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, REVERSE_STEP, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "/" RelativePathExpr?
  static boolean RootPathExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RootPathExpr")) return false;
    if (!nextTokenIs(builder_, SLASH)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SLASH);
    result_ = result_ && RootPathExpr_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // RelativePathExpr?
  private static boolean RootPathExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RootPathExpr_1")) return false;
    RelativePathExpr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "//" RelativePathExpr
  static boolean RootPathOrChildExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RootPathOrChildExpr")) return false;
    if (!nextTokenIs(builder_, SLASH_SLASH)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, SLASH_SLASH);
    pinned_ = result_; // pin = 1
    result_ = result_ && RelativePathExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "schema-attribute" "(" AttributeDeclaration ")"
  public static boolean SchemaAttributeTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaAttributeTest")) return false;
    if (!nextTokenIs(builder_, K_SCHEMA_ATTRIBUTE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_SCHEMA_ATTRIBUTE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, AttributeDeclaration(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, SCHEMA_ATTRIBUTE_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "schema-element" "(" ElementDeclaration ")"
  public static boolean SchemaElementTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaElementTest")) return false;
    if (!nextTokenIs(builder_, K_SCHEMA_ELEMENT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_SCHEMA_ELEMENT);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, ElementDeclaration(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    exit_section_(builder_, level_, marker_, SCHEMA_ELEMENT_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "import" "schema" (("namespace" NamespacePrefix "=") | ("default" "element" "namespace"))? URILiteral ("at" URILiteral ("," URILiteral)*)? Separator
  public static boolean SchemaImport(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport")) return false;
    if (!nextTokenIs(builder_, K_IMPORT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_IMPORT);
    result_ = result_ && consumeToken(builder_, K_SCHEMA);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, SchemaImport_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, URILiteral(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, SchemaImport_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, SCHEMA_IMPORT, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (("namespace" NamespacePrefix "=") | ("default" "element" "namespace"))?
  private static boolean SchemaImport_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_2")) return false;
    SchemaImport_2_0(builder_, level_ + 1);
    return true;
  }

  // ("namespace" NamespacePrefix "=") | ("default" "element" "namespace")
  private static boolean SchemaImport_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = SchemaImport_2_0_0(builder_, level_ + 1);
    if (!result_) result_ = SchemaImport_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "namespace" NamespacePrefix "="
  private static boolean SchemaImport_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_2_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_NAMESPACE);
    result_ = result_ && NamespacePrefix(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "default" "element" "namespace"
  private static boolean SchemaImport_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_2_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_ELEMENT);
    result_ = result_ && consumeToken(builder_, K_NAMESPACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("at" URILiteral ("," URILiteral)*)?
  private static boolean SchemaImport_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_4")) return false;
    SchemaImport_4_0(builder_, level_ + 1);
    return true;
  }

  // "at" URILiteral ("," URILiteral)*
  private static boolean SchemaImport_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_AT);
    result_ = result_ && URILiteral(builder_, level_ + 1);
    result_ = result_ && SchemaImport_4_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("," URILiteral)*
  private static boolean SchemaImport_4_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_4_0_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!SchemaImport_4_0_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "SchemaImport_4_0_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," URILiteral
  private static boolean SchemaImport_4_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_4_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && URILiteral(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ContextItemDecl | OptionDecl | AnnotatedDecl
  static boolean SecondDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SecondDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ContextItemDecl(builder_, level_ + 1);
    if (!result_) result_ = OptionDecl(builder_, level_ + 1);
    if (!result_) result_ = AnnotatedDecl(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ";"
  public static boolean Separator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Separator")) return false;
    if (!nextTokenIs(builder_, SEMICOLON)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, SEPARATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // ("empty-sequence" "(" ")")
  //  | (ItemType OccurrenceIndicator?)
  public static boolean SequenceType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceType")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<sequence type>");
    result_ = SequenceType_0(builder_, level_ + 1);
    if (!result_) result_ = SequenceType_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SEQUENCE_TYPE, result_, false, null);
    return result_;
  }

  // "empty-sequence" "(" ")"
  private static boolean SequenceType_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceType_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_EMPTY_SEQUENCE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ItemType OccurrenceIndicator?
  private static boolean SequenceType_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceType_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ItemType(builder_, level_ + 1);
    result_ = result_ && SequenceType_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // OccurrenceIndicator?
  private static boolean SequenceType_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceType_1_1")) return false;
    OccurrenceIndicator(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // SequenceType ("|" SequenceType)*
  public static boolean SequenceTypeUnion(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceTypeUnion")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<sequence type union>");
    result_ = SequenceType(builder_, level_ + 1);
    result_ = result_ && SequenceTypeUnion_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SEQUENCE_TYPE_UNION, result_, false, null);
    return result_;
  }

  // ("|" SequenceType)*
  private static boolean SequenceTypeUnion_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceTypeUnion_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!SequenceTypeUnion_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "SequenceTypeUnion_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "|" SequenceType
  private static boolean SequenceTypeUnion_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceTypeUnion_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PIPE);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BoundarySpaceDecl | DefaultCollationDecl | BaseURIDecl | ConstructionDecl | OrderingModeDecl | EmptyOrderDecl | RevalidationDecl | CopyNamespacesDecl | DecimalFormatDecl
  static boolean Setter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Setter")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = BoundarySpaceDecl(builder_, level_ + 1);
    if (!result_) result_ = DefaultCollationDecl(builder_, level_ + 1);
    if (!result_) result_ = BaseURIDecl(builder_, level_ + 1);
    if (!result_) result_ = ConstructionDecl(builder_, level_ + 1);
    if (!result_) result_ = OrderingModeDecl(builder_, level_ + 1);
    if (!result_) result_ = EmptyOrderDecl(builder_, level_ + 1);
    if (!result_) result_ = RevalidationDecl(builder_, level_ + 1);
    if (!result_) result_ = CopyNamespacesDecl(builder_, level_ + 1);
    if (!result_) result_ = DecimalFormatDecl(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // PathExpr SimpleMapOptionalExpr*
  public static boolean SimpleMapExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SimpleMapExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<simple map expr>");
    result_ = PathExpr(builder_, level_ + 1);
    result_ = result_ && SimpleMapExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SIMPLE_MAP_EXPR, result_, false, null);
    return result_;
  }

  // SimpleMapOptionalExpr*
  private static boolean SimpleMapExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SimpleMapExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!SimpleMapOptionalExpr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "SimpleMapExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // "!"
  public static boolean SimpleMapOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SimpleMapOperator")) return false;
    if (!nextTokenIs(builder_, EXCLAMATION_MARK)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EXCLAMATION_MARK);
    exit_section_(builder_, marker_, SIMPLE_MAP_OPERATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // SimpleMapOperator PathExpr
  static boolean SimpleMapOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SimpleMapOptionalExpr")) return false;
    if (!nextTokenIs(builder_, EXCLAMATION_MARK)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = SimpleMapOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && PathExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // TypeName
  public static boolean SimpleTypeName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SimpleTypeName")) return false;
    if (!nextTokenIs(builder_, "<simple type name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<simple type name>");
    result_ = TypeName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SIMPLE_TYPE_NAME, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // SimpleTypeName "?"?
  public static boolean SingleType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SingleType")) return false;
    if (!nextTokenIs(builder_, "<single type>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<single type>");
    result_ = SimpleTypeName(builder_, level_ + 1);
    result_ = result_ && SingleType_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SINGLE_TYPE, result_, false, null);
    return result_;
  }

  // "?"?
  private static boolean SingleType_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SingleType_1")) return false;
    consumeToken(builder_, QUESTIONMARK);
    return true;
  }

  /* ********************************************************** */
  // "sliding" SlidingWindowDetails
  static boolean SlidingWindowClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SlidingWindowClause")) return false;
    if (!nextTokenIs(builder_, K_SLIDING)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_SLIDING);
    pinned_ = result_; // pin = 1
    result_ = result_ && SlidingWindowDetails(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "window" "$" VarName TypeDeclaration? "in" ExprSingle WindowStartCondition WindowEndCondition
  static boolean SlidingWindowDetails(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SlidingWindowDetails")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_WINDOW);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && SlidingWindowDetails_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_IN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    result_ = result_ && WindowStartCondition(builder_, level_ + 1);
    result_ = result_ && WindowEndCondition(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, FLWORExprRecover_parser_);
    return result_;
  }

  // TypeDeclaration?
  private static boolean SlidingWindowDetails_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SlidingWindowDetails_3")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean SourceExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SourceExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<source expr>");
    result_ = ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SOURCE_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PostfixExpr | AxisStep
  public static boolean StepExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StepExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<step expr>");
    result_ = PostfixExpr(builder_, level_ + 1);
    if (!result_) result_ = AxisStep(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, STEP_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // RangeExpr StringConcatMultipliedExpr*
  public static boolean StringConcatExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringConcatExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<string concat expr>");
    result_ = RangeExpr(builder_, level_ + 1);
    result_ = result_ && StringConcatExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, STRING_CONCAT_EXPR, result_, false, null);
    return result_;
  }

  // StringConcatMultipliedExpr*
  private static boolean StringConcatExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringConcatExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!StringConcatMultipliedExpr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "StringConcatExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // ConcatOperator RangeExpr
  static boolean StringConcatMultipliedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringConcatMultipliedExpr")) return false;
    if (!nextTokenIs(builder_, PIPE_PIPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = ConcatOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && RangeExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ("case" SwitchCaseOperand)+ SwitchReturnClause
  public static boolean SwitchCaseClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchCaseClause")) return false;
    if (!nextTokenIs(builder_, K_CASE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = SwitchCaseClause_0(builder_, level_ + 1);
    result_ = result_ && SwitchReturnClause(builder_, level_ + 1);
    exit_section_(builder_, marker_, SWITCH_CASE_CLAUSE, result_);
    return result_;
  }

  // ("case" SwitchCaseOperand)+
  private static boolean SwitchCaseClause_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchCaseClause_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = SwitchCaseClause_0_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!SwitchCaseClause_0_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "SwitchCaseClause_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "case" SwitchCaseOperand
  private static boolean SwitchCaseClause_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchCaseClause_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_CASE);
    result_ = result_ && SwitchCaseOperand(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean SwitchCaseOperand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchCaseOperand")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<switch case operand>");
    result_ = ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SWITCH_CASE_OPERAND, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "default" SwitchReturnClause
  public static boolean SwitchDefaultReturnClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchDefaultReturnClause")) return false;
    if (!nextTokenIs(builder_, K_DEFAULT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DEFAULT);
    pinned_ = result_; // pin = 1
    result_ = result_ && SwitchReturnClause(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SWITCH_DEFAULT_RETURN_CLAUSE, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "switch" "(" Expr ")" SwitchCaseClause+ SwitchDefaultReturnClause
  public static boolean SwitchExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchExpr")) return false;
    if (!nextTokenIs(builder_, K_SWITCH)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_SWITCH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, L_PAR));
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, R_PAR)) && result_;
    result_ = pinned_ && report_error_(builder_, SwitchExpr_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && SwitchDefaultReturnClause(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, SWITCH_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SwitchCaseClause+
  private static boolean SwitchExpr_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchExpr_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = SwitchCaseClause(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!SwitchCaseClause(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "SwitchExpr_4", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "return" ExprSingle
  public static boolean SwitchReturnClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchReturnClause")) return false;
    if (!nextTokenIs(builder_, K_RETURN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_RETURN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, marker_, SWITCH_RETURN_CLAUSE, result_);
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean TargetExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TargetExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<target expr>");
    result_ = ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, TARGET_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "text" "(" ")"
  public static boolean TextTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TextTest")) return false;
    if (!nextTokenIs(builder_, K_TEXT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_TEXT);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    exit_section_(builder_, level_, marker_, TEXT_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "to"
  public static boolean ToOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ToOperator")) return false;
    if (!nextTokenIs(builder_, K_TO)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_TO);
    exit_section_(builder_, marker_, TO_OPERATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // "copy" "$" VarName ":=" ExprSingle ("," "$" VarName ":=" ExprSingle)* "modify" ExprSingle "return" ExprSingle
  public static boolean TransformExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TransformExpr")) return false;
    if (!nextTokenIs(builder_, K_COPY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_COPY);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    result_ = result_ && TransformExpr_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_MODIFY);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_RETURN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, marker_, TRANSFORM_EXPR, result_);
    return result_;
  }

  // ("," "$" VarName ":=" ExprSingle)*
  private static boolean TransformExpr_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TransformExpr_5")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!TransformExpr_5_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TransformExpr_5", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," "$" VarName ":=" ExprSingle
  private static boolean TransformExpr_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TransformExpr_5_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // CastableExpr TreatOptionalExpr?
  public static boolean TreatExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TreatExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<treat expr>");
    result_ = CastableExpr(builder_, level_ + 1);
    result_ = result_ && TreatExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, TREAT_EXPR, result_, false, null);
    return result_;
  }

  // TreatOptionalExpr?
  private static boolean TreatExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TreatExpr_1")) return false;
    TreatOptionalExpr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "treat" "as"
  public static boolean TreatOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TreatOperator")) return false;
    if (!nextTokenIs(builder_, K_TREAT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_TREAT);
    result_ = result_ && consumeToken(builder_, K_AS);
    exit_section_(builder_, marker_, TREAT_OPERATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // TreatOperator SequenceType
  static boolean TreatOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TreatOptionalExpr")) return false;
    if (!nextTokenIs(builder_, K_TREAT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = TreatOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && SequenceType(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // TryClause CatchClause+
  public static boolean TryCatchExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCatchExpr")) return false;
    if (!nextTokenIs(builder_, K_TRY)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = TryClause(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && TryCatchExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, TRY_CATCH_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // CatchClause+
  private static boolean TryCatchExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCatchExpr_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = CatchClause(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!CatchClause(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TryCatchExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "try" "{" TryTargetExpr "}"
  public static boolean TryClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryClause")) return false;
    if (!nextTokenIs(builder_, K_TRY)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_TRY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, L_C_BRACE));
    result_ = pinned_ && report_error_(builder_, TryTargetExpr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, R_C_BRACE) && result_;
    exit_section_(builder_, level_, marker_, TRY_CLAUSE, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Expr
  static boolean TryTargetExpr(PsiBuilder builder_, int level_) {
    return Expr(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // "tumbling" TumblingWindowDetails
  static boolean TumblingWindowClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TumblingWindowClause")) return false;
    if (!nextTokenIs(builder_, K_TUMBLING)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_TUMBLING);
    pinned_ = result_; // pin = 1
    result_ = result_ && TumblingWindowDetails(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "window" "$" VarName TypeDeclaration? "in" ExprSingle WindowStartCondition WindowEndCondition?
  static boolean TumblingWindowDetails(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TumblingWindowDetails")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_WINDOW);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && TumblingWindowDetails_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_IN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    result_ = result_ && WindowStartCondition(builder_, level_ + 1);
    result_ = result_ && TumblingWindowDetails_7(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, FLWORExprRecover_parser_);
    return result_;
  }

  // TypeDeclaration?
  private static boolean TumblingWindowDetails_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TumblingWindowDetails_3")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  // WindowEndCondition?
  private static boolean TumblingWindowDetails_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TumblingWindowDetails_7")) return false;
    WindowEndCondition(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "as" SequenceType
  public static boolean TypeDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeDeclaration")) return false;
    if (!nextTokenIs(builder_, K_AS)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_AS);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    exit_section_(builder_, marker_, TYPE_DECLARATION, result_);
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean TypeName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeName")) return false;
    if (!nextTokenIs(builder_, "<type name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<type name>");
    result_ = EQName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, TYPE_NAME, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "function" "(" (SequenceType ("," SequenceType)*)? ")" "as" SequenceType
  public static boolean TypedFunctionTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypedFunctionTest")) return false;
    if (!nextTokenIs(builder_, K_FUNCTION)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_FUNCTION);
    result_ = result_ && consumeToken(builder_, L_PAR);
    result_ = result_ && TypedFunctionTest_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAR);
    pinned_ = result_; // pin = 4
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_AS));
    result_ = pinned_ && SequenceType(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, TYPED_FUNCTION_TEST, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (SequenceType ("," SequenceType)*)?
  private static boolean TypedFunctionTest_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypedFunctionTest_2")) return false;
    TypedFunctionTest_2_0(builder_, level_ + 1);
    return true;
  }

  // SequenceType ("," SequenceType)*
  private static boolean TypedFunctionTest_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypedFunctionTest_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = SequenceType(builder_, level_ + 1);
    result_ = result_ && TypedFunctionTest_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("," SequenceType)*
  private static boolean TypedFunctionTest_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypedFunctionTest_2_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!TypedFunctionTest_2_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TypedFunctionTest_2_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "," SequenceType
  private static boolean TypedFunctionTest_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypedFunctionTest_2_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "default" ("$" VarName)? SwitchReturnClause
  public static boolean TypeswitchDefaultReturnClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeswitchDefaultReturnClause")) return false;
    if (!nextTokenIs(builder_, K_DEFAULT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DEFAULT);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, TypeswitchDefaultReturnClause_1(builder_, level_ + 1));
    result_ = pinned_ && SwitchReturnClause(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, TYPESWITCH_DEFAULT_RETURN_CLAUSE, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ("$" VarName)?
  private static boolean TypeswitchDefaultReturnClause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeswitchDefaultReturnClause_1")) return false;
    TypeswitchDefaultReturnClause_1_0(builder_, level_ + 1);
    return true;
  }

  // "$" VarName
  private static boolean TypeswitchDefaultReturnClause_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeswitchDefaultReturnClause_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "typeswitch" "(" Expr ")" CaseClause+ TypeswitchDefaultReturnClause
  public static boolean TypeswitchExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeswitchExpr")) return false;
    if (!nextTokenIs(builder_, K_TYPESWITCH)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_TYPESWITCH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, L_PAR));
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, R_PAR)) && result_;
    result_ = pinned_ && report_error_(builder_, TypeswitchExpr_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && TypeswitchDefaultReturnClause(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, TYPESWITCH_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // CaseClause+
  private static boolean TypeswitchExpr_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeswitchExpr_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = CaseClause(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!CaseClause(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TypeswitchExpr_4", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Expr
  public static boolean URIExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "URIExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<uri expr>");
    result_ = Expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, URI_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // StringLiteral
  public static boolean URILiteral(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "URILiteral")) return false;
    if (!nextTokenIs(builder_, STRINGLITERAL)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRINGLITERAL);
    exit_section_(builder_, marker_, URI_LITERAL, result_);
    return result_;
  }

  /* ********************************************************** */
  // ("-" | "+")* ValueExpr
  public static boolean UnaryExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnaryExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<unary expr>");
    result_ = UnaryExpr_0(builder_, level_ + 1);
    result_ = result_ && ValueExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, UNARY_EXPR, result_, false, null);
    return result_;
  }

  // ("-" | "+")*
  private static boolean UnaryExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnaryExpr_0")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!UnaryExpr_0_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "UnaryExpr_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // "-" | "+"
  private static boolean UnaryExpr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnaryExpr_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OP_MINUS);
    if (!result_) result_ = consumeToken(builder_, OP_PLUS);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IntersectExceptExpr UnionOptionalExpr*
  public static boolean UnionExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnionExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<union expr>");
    result_ = IntersectExceptExpr(builder_, level_ + 1);
    result_ = result_ && UnionExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, UNION_EXPR, result_, false, null);
    return result_;
  }

  // UnionOptionalExpr*
  private static boolean UnionExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnionExpr_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!UnionOptionalExpr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "UnionExpr_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // "union" | "|"
  public static boolean UnionOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnionOperator")) return false;
    if (!nextTokenIs(builder_, "<union operator>", K_UNION, PIPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<union operator>");
    result_ = consumeToken(builder_, K_UNION);
    if (!result_) result_ = consumeToken(builder_, PIPE);
    exit_section_(builder_, level_, marker_, UNION_OPERATOR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // UnionOperator IntersectExceptExpr
  static boolean UnionOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnionOptionalExpr")) return false;
    if (!nextTokenIs(builder_, "", K_UNION, PIPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = UnionOperator(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && IntersectExceptExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "unordered" "{" Expr "}"
  public static boolean UnorderedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnorderedExpr")) return false;
    if (!nextTokenIs(builder_, K_UNORDERED)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_UNORDERED);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, Expr(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_C_BRACE) && result_;
    exit_section_(builder_, level_, marker_, UNORDERED_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LocalPart
  static boolean UnprefixedName(PsiBuilder builder_, int level_) {
    return LocalPart(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // "validate" (ValidationMode | ("type" TypeName))? "{" Expr "}"
  public static boolean ValidateExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValidateExpr")) return false;
    if (!nextTokenIs(builder_, K_VALIDATE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_VALIDATE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ValidateExpr_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, L_C_BRACE)) && result_;
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, R_C_BRACE) && result_;
    exit_section_(builder_, level_, marker_, VALIDATE_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (ValidationMode | ("type" TypeName))?
  private static boolean ValidateExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValidateExpr_1")) return false;
    ValidateExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // ValidationMode | ("type" TypeName)
  private static boolean ValidateExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValidateExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = ValidationMode(builder_, level_ + 1);
    if (!result_) result_ = ValidateExpr_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "type" TypeName
  private static boolean ValidateExpr_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValidateExpr_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_TYPE);
    result_ = result_ && TypeName(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "lax" | "strict"
  static boolean ValidationMode(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValidationMode")) return false;
    if (!nextTokenIs(builder_, "", K_LAX, K_STRICT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_LAX);
    if (!result_) result_ = consumeToken(builder_, K_STRICT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "eq" | "ne" | "lt" | "le" | "gt" | "ge"
  public static boolean ValueComp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueComp")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<value comp>");
    result_ = consumeToken(builder_, EQ);
    if (!result_) result_ = consumeToken(builder_, NE);
    if (!result_) result_ = consumeToken(builder_, LT);
    if (!result_) result_ = consumeToken(builder_, LE);
    if (!result_) result_ = consumeToken(builder_, GT);
    if (!result_) result_ = consumeToken(builder_, GE);
    exit_section_(builder_, level_, marker_, VALUE_COMP, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ValidateExpr | ExtensionExpr | SimpleMapExpr
  public static boolean ValueExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueExpr")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, "<value expr>");
    result_ = ValidateExpr(builder_, level_ + 1);
    if (!result_) result_ = ExtensionExpr(builder_, level_ + 1);
    if (!result_) result_ = SimpleMapExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, VALUE_EXPR, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" (MarklogicAnnotation | CompatibilityAnnotation | Annotation)* "variable" "$" VarName TypeDeclaration? (VarDetails) Separator
  public static boolean VarDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && VarDecl_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_VARIABLE);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, consumeToken(builder_, DOLLAR_SIGN));
    result_ = pinned_ && report_error_(builder_, VarName(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, VarDecl_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, VarDecl_6(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, VAR_DECL, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (MarklogicAnnotation | CompatibilityAnnotation | Annotation)*
  private static boolean VarDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!VarDecl_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "VarDecl_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // MarklogicAnnotation | CompatibilityAnnotation | Annotation
  private static boolean VarDecl_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = MarklogicAnnotation(builder_, level_ + 1);
    if (!result_) result_ = CompatibilityAnnotation(builder_, level_ + 1);
    if (!result_) result_ = Annotation(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // TypeDeclaration?
  private static boolean VarDecl_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl_5")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  // (VarDetails)
  private static boolean VarDecl_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl_6")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = VarDetails(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean VarDefaultValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDefaultValue")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<var default value>");
    result_ = ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, VAR_DEFAULT_VALUE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // VarValueAssignment | ExternalVarPart
  static boolean VarDetails(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDetails")) return false;
    if (!nextTokenIs(builder_, "", OP_ASSIGN, K_EXTERNAL)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = VarValueAssignment(builder_, level_ + 1);
    if (!result_) result_ = ExternalVarPart(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean VarLocalName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarLocalName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NCNAME);
    exit_section_(builder_, marker_, VAR_LOCAL_NAME, result_);
    return result_;
  }

  /* ********************************************************** */
  // Prefix ':' VarLocalName | VarLocalName | URIQualifiedName
  public static boolean VarName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarName")) return false;
    if (!nextTokenIs(builder_, "<var name>", NCNAME, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<var name>");
    result_ = VarName_0(builder_, level_ + 1);
    if (!result_) result_ = VarLocalName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, URIQUALIFIEDNAME);
    exit_section_(builder_, level_, marker_, VAR_NAME, result_, false, null);
    return result_;
  }

  // Prefix ':' VarLocalName
  private static boolean VarName_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarName_0")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = Prefix(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    pinned_ = result_; // pin = 2
    result_ = result_ && VarLocalName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "$" VarName
  public static boolean VarRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarRef")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    pinned_ = result_; // pin = 1
    result_ = result_ && VarName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, VAR_REF, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean VarValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarValue")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<var value>");
    result_ = ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, VAR_VALUE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ":=" VarValue
  static boolean VarValueAssignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarValueAssignment")) return false;
    if (!nextTokenIs(builder_, OP_ASSIGN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, OP_ASSIGN);
    pinned_ = result_; // pin = 1
    result_ = result_ && VarValue(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // StringLiteral
  public static boolean Version(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Version")) return false;
    if (!nextTokenIs(builder_, STRINGLITERAL)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRINGLITERAL);
    exit_section_(builder_, marker_, VERSION, result_);
    return result_;
  }

  /* ********************************************************** */
  // "xquery" ((VersionDeclEncoding) | (VersionDeclVersion)) Separator
  public static boolean VersionDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDecl")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<version decl>");
    result_ = consumeToken(builder_, K_XQUERY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, VersionDecl_1(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, VERSION_DECL, result_, pinned_, VersionDeclRecover_parser_);
    return result_ || pinned_;
  }

  // (VersionDeclEncoding) | (VersionDeclVersion)
  private static boolean VersionDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDecl_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = VersionDecl_1_0(builder_, level_ + 1);
    if (!result_) result_ = VersionDecl_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (VersionDeclEncoding)
  private static boolean VersionDecl_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDecl_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = VersionDeclEncoding(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (VersionDeclVersion)
  private static boolean VersionDecl_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDecl_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = VersionDeclVersion(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "encoding" StringLiteral
  static boolean VersionDeclEncoding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclEncoding")) return false;
    if (!nextTokenIs(builder_, K_ENCODING)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_ENCODING);
    pinned_ = result_; // pin = 1
    result_ = result_ && consumeToken(builder_, STRINGLITERAL);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !('$' | '%' | '(#' | '(' | '*' | '+' | '-' | '.' | '..' | '/' | '//' | '<!--' | XmlStartTagStart | '<?' | '@' | 'ancestor' | 'ancestor-or-self' | 'attribute' | 'child' | 'comment' | 'declare' | 'descendant' | 'descendant-or-self' | 'document' | 'document-node' | 'element' | 'every' | 'following' | 'following-sibling' | 'for' | 'function' | 'if' | 'import' | 'let' | 'module' | 'namespace' | 'namespace-node' | 'node' | 'ordered' | 'parent' | 'preceding' | 'preceding-sibling' | 'processing-instruction' | 'schema-attribute' | 'schema-element' | 'self' | 'some' | 'switch' | 'text' | 'try' | 'typeswitch' | 'unordered' | 'validate' | <<eof>> | BracedURILiteral | DecimalLiteral | DoubleLiteral | IntegerLiteral | NCName | StringLiteral | URIQualifiedName | '"' | "'" | Char)
  static boolean VersionDeclRecover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclRecover")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NOT_, null);
    result_ = !VersionDeclRecover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, false, null);
    return result_;
  }

  // '$' | '%' | '(#' | '(' | '*' | '+' | '-' | '.' | '..' | '/' | '//' | '<!--' | XmlStartTagStart | '<?' | '@' | 'ancestor' | 'ancestor-or-self' | 'attribute' | 'child' | 'comment' | 'declare' | 'descendant' | 'descendant-or-self' | 'document' | 'document-node' | 'element' | 'every' | 'following' | 'following-sibling' | 'for' | 'function' | 'if' | 'import' | 'let' | 'module' | 'namespace' | 'namespace-node' | 'node' | 'ordered' | 'parent' | 'preceding' | 'preceding-sibling' | 'processing-instruction' | 'schema-attribute' | 'schema-element' | 'self' | 'some' | 'switch' | 'text' | 'try' | 'typeswitch' | 'unordered' | 'validate' | <<eof>> | BracedURILiteral | DecimalLiteral | DoubleLiteral | IntegerLiteral | NCName | StringLiteral | URIQualifiedName | '"' | "'" | Char
  private static boolean VersionDeclRecover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclRecover_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    if (!result_) result_ = consumeToken(builder_, PERCENT);
    if (!result_) result_ = consumeToken(builder_, PRAGMA_BEGIN);
    if (!result_) result_ = consumeToken(builder_, L_PAR);
    if (!result_) result_ = consumeToken(builder_, STAR_SIGN);
    if (!result_) result_ = consumeToken(builder_, OP_PLUS);
    if (!result_) result_ = consumeToken(builder_, OP_MINUS);
    if (!result_) result_ = consumeToken(builder_, DOT);
    if (!result_) result_ = consumeToken(builder_, DOT_DOT);
    if (!result_) result_ = consumeToken(builder_, SLASH);
    if (!result_) result_ = consumeToken(builder_, SLASH_SLASH);
    if (!result_) result_ = consumeToken(builder_, DIR_COMMENT_BEGIN);
    if (!result_) result_ = consumeToken(builder_, XMLSTARTTAGSTART);
    if (!result_) result_ = consumeToken(builder_, PI_BEGIN);
    if (!result_) result_ = consumeToken(builder_, AT_SIGN);
    if (!result_) result_ = consumeToken(builder_, K_ANCESTOR);
    if (!result_) result_ = consumeToken(builder_, K_ANCESTOR_OR_SELF);
    if (!result_) result_ = consumeToken(builder_, K_ATTRIBUTE);
    if (!result_) result_ = consumeToken(builder_, K_CHILD);
    if (!result_) result_ = consumeToken(builder_, K_COMMENT);
    if (!result_) result_ = consumeToken(builder_, K_DECLARE);
    if (!result_) result_ = consumeToken(builder_, K_DESCENDANT);
    if (!result_) result_ = consumeToken(builder_, K_DESCENDANT_OR_SELF);
    if (!result_) result_ = consumeToken(builder_, K_DOCUMENT);
    if (!result_) result_ = consumeToken(builder_, K_DOCUMENT_NODE);
    if (!result_) result_ = consumeToken(builder_, K_ELEMENT);
    if (!result_) result_ = consumeToken(builder_, K_EVERY);
    if (!result_) result_ = consumeToken(builder_, K_FOLLOWING);
    if (!result_) result_ = consumeToken(builder_, K_FOLLOWING_SIBLING);
    if (!result_) result_ = consumeToken(builder_, K_FOR);
    if (!result_) result_ = consumeToken(builder_, K_FUNCTION);
    if (!result_) result_ = consumeToken(builder_, K_IF);
    if (!result_) result_ = consumeToken(builder_, K_IMPORT);
    if (!result_) result_ = consumeToken(builder_, K_LET);
    if (!result_) result_ = consumeToken(builder_, K_MODULE);
    if (!result_) result_ = consumeToken(builder_, K_NAMESPACE);
    if (!result_) result_ = consumeToken(builder_, K_NAMESPACE_NODE);
    if (!result_) result_ = consumeToken(builder_, K_NODE);
    if (!result_) result_ = consumeToken(builder_, K_ORDERED);
    if (!result_) result_ = consumeToken(builder_, K_PARENT);
    if (!result_) result_ = consumeToken(builder_, K_PRECEDING);
    if (!result_) result_ = consumeToken(builder_, K_PRECEDING_SIBLING);
    if (!result_) result_ = consumeToken(builder_, K_PI);
    if (!result_) result_ = consumeToken(builder_, K_SCHEMA_ATTRIBUTE);
    if (!result_) result_ = consumeToken(builder_, K_SCHEMA_ELEMENT);
    if (!result_) result_ = consumeToken(builder_, K_SELF);
    if (!result_) result_ = consumeToken(builder_, K_SOME);
    if (!result_) result_ = consumeToken(builder_, K_SWITCH);
    if (!result_) result_ = consumeToken(builder_, K_TEXT);
    if (!result_) result_ = consumeToken(builder_, K_TRY);
    if (!result_) result_ = consumeToken(builder_, K_TYPESWITCH);
    if (!result_) result_ = consumeToken(builder_, K_UNORDERED);
    if (!result_) result_ = consumeToken(builder_, K_VALIDATE);
    if (!result_) result_ = eof(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, BRACEDURILITERAL);
    if (!result_) result_ = consumeToken(builder_, DECIMALLITERAL);
    if (!result_) result_ = consumeToken(builder_, DOUBLELITERAL);
    if (!result_) result_ = consumeToken(builder_, INTEGERLITERAL);
    if (!result_) result_ = consumeToken(builder_, NCNAME);
    if (!result_) result_ = consumeToken(builder_, STRINGLITERAL);
    if (!result_) result_ = consumeToken(builder_, URIQUALIFIEDNAME);
    if (!result_) result_ = consumeToken(builder_, QUOT);
    if (!result_) result_ = consumeToken(builder_, APOSTROPHE);
    if (!result_) result_ = consumeToken(builder_, CHAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "version" Version ("encoding" StringLiteral)?
  static boolean VersionDeclVersion(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclVersion")) return false;
    if (!nextTokenIs(builder_, K_VERSION)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, K_VERSION);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Version(builder_, level_ + 1));
    result_ = pinned_ && VersionDeclVersion_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ("encoding" StringLiteral)?
  private static boolean VersionDeclVersion_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclVersion_2")) return false;
    VersionDeclVersion_2_0(builder_, level_ + 1);
    return true;
  }

  // "encoding" StringLiteral
  private static boolean VersionDeclVersion_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclVersion_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_ENCODING);
    result_ = result_ && consumeToken(builder_, STRINGLITERAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "where" ExprSingle
  public static boolean WhereClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WhereClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<where clause>");
    result_ = consumeToken(builder_, K_WHERE);
    pinned_ = result_; // pin = 1
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, WHERE_CLAUSE, result_, pinned_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // (Prefix ":" "*")
  //  | ("*" ":" NCName)
  //  | "*"
  //  | (BracedURILiteral "*")
  public static boolean Wildcard(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Wildcard")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<wildcard>");
    result_ = Wildcard_0(builder_, level_ + 1);
    if (!result_) result_ = Wildcard_1(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STAR_SIGN);
    if (!result_) result_ = Wildcard_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, WILDCARD, result_, false, null);
    return result_;
  }

  // Prefix ":" "*"
  private static boolean Wildcard_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Wildcard_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = Prefix(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && consumeToken(builder_, STAR_SIGN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "*" ":" NCName
  private static boolean Wildcard_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Wildcard_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STAR_SIGN);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && consumeToken(builder_, NCNAME);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // BracedURILiteral "*"
  private static boolean Wildcard_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Wildcard_3")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BRACEDURILITERAL);
    result_ = result_ && consumeToken(builder_, STAR_SIGN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "for" (TumblingWindowClause | SlidingWindowClause)
  public static boolean WindowClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowClause")) return false;
    if (!nextTokenIs(builder_, K_FOR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_FOR);
    result_ = result_ && WindowClause_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, WINDOW_CLAUSE, result_);
    return result_;
  }

  // TumblingWindowClause | SlidingWindowClause
  private static boolean WindowClause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowClause_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = TumblingWindowClause(builder_, level_ + 1);
    if (!result_) result_ = SlidingWindowClause(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "only"? "end" WindowVars "when" ExprSingle
  static boolean WindowEndCondition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowEndCondition")) return false;
    if (!nextTokenIs(builder_, "", K_END, K_ONLY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = WindowEndCondition_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_END);
    result_ = result_ && WindowVars(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_WHEN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // "only"?
  private static boolean WindowEndCondition_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowEndCondition_0")) return false;
    consumeToken(builder_, K_ONLY);
    return true;
  }

  /* ********************************************************** */
  // "start" WindowVars "when" ExprSingle
  static boolean WindowStartCondition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowStartCondition")) return false;
    if (!nextTokenIs(builder_, K_START)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_START);
    result_ = result_ && WindowVars(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_WHEN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ("$" CurrentItem)? PositionalVar? ("previous" "$" PreviousItem)? ("next" "$" NextItem)?
  static boolean WindowVars(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowVars")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = WindowVars_0(builder_, level_ + 1);
    result_ = result_ && WindowVars_1(builder_, level_ + 1);
    result_ = result_ && WindowVars_2(builder_, level_ + 1);
    result_ = result_ && WindowVars_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("$" CurrentItem)?
  private static boolean WindowVars_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowVars_0")) return false;
    WindowVars_0_0(builder_, level_ + 1);
    return true;
  }

  // "$" CurrentItem
  private static boolean WindowVars_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowVars_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && CurrentItem(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // PositionalVar?
  private static boolean WindowVars_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowVars_1")) return false;
    PositionalVar(builder_, level_ + 1);
    return true;
  }

  // ("previous" "$" PreviousItem)?
  private static boolean WindowVars_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowVars_2")) return false;
    WindowVars_2_0(builder_, level_ + 1);
    return true;
  }

  // "previous" "$" PreviousItem
  private static boolean WindowVars_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowVars_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_PREVIOUS);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && PreviousItem(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ("next" "$" NextItem)?
  private static boolean WindowVars_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowVars_3")) return false;
    WindowVars_3_0(builder_, level_ + 1);
    return true;
  }

  // "next" "$" NextItem
  private static boolean WindowVars_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowVars_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, K_NEXT);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && NextItem(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // XmlEndTagStart XmlTagName XmlTagEnd
  static boolean XmlClosingTagPart(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlClosingTagPart")) return false;
    if (!nextTokenIs(builder_, XMLENDTAGSTART)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, XMLENDTAGSTART);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, XmlTagName(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, XMLTAGEND) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // XmlStartTagStart XmlTagName DirAttributeList? XmlEmptyElementEnd
  public static boolean XmlEmptyTag(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlEmptyTag")) return false;
    if (!nextTokenIs(builder_, XMLSTARTTAGSTART)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, XMLSTARTTAGSTART);
    result_ = result_ && XmlTagName(builder_, level_ + 1);
    result_ = result_ && XmlEmptyTag_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, XMLEMPTYELEMENTEND);
    exit_section_(builder_, marker_, XML_EMPTY_TAG, result_);
    return result_;
  }

  // DirAttributeList?
  private static boolean XmlEmptyTag_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlEmptyTag_2")) return false;
    DirAttributeList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // XmlOpeningTagPart DirElemContent* XmlClosingTagPart
  public static boolean XmlFullTag(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlFullTag")) return false;
    if (!nextTokenIs(builder_, XMLSTARTTAGSTART)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = XmlOpeningTagPart(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, XmlFullTag_1(builder_, level_ + 1));
    result_ = pinned_ && XmlClosingTagPart(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, XML_FULL_TAG, result_, pinned_, null);
    return result_ || pinned_;
  }

  // DirElemContent*
  private static boolean XmlFullTag_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlFullTag_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!DirElemContent(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "XmlFullTag_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // XmlStartTagStart XmlTagName DirAttributeList? XmlTagEnd
  static boolean XmlOpeningTagPart(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlOpeningTagPart")) return false;
    if (!nextTokenIs(builder_, XMLSTARTTAGSTART)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, XMLSTARTTAGSTART);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, XmlTagName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, XmlOpeningTagPart_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, XMLTAGEND) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // DirAttributeList?
  private static boolean XmlOpeningTagPart_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlOpeningTagPart_2")) return false;
    DirAttributeList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // XmlTagNCName
  public static boolean XmlTagLocalName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlTagLocalName")) return false;
    if (!nextTokenIs(builder_, XMLTAGNCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, XMLTAGNCNAME);
    exit_section_(builder_, marker_, XML_TAG_LOCAL_NAME, result_);
    return result_;
  }

  /* ********************************************************** */
  // XmlTagNamespace XmlColon XmlTagLocalName | XmlTagLocalName
  public static boolean XmlTagName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlTagName")) return false;
    if (!nextTokenIs(builder_, XMLTAGNCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = XmlTagName_0(builder_, level_ + 1);
    if (!result_) result_ = XmlTagLocalName(builder_, level_ + 1);
    exit_section_(builder_, marker_, XML_TAG_NAME, result_);
    return result_;
  }

  // XmlTagNamespace XmlColon XmlTagLocalName
  private static boolean XmlTagName_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlTagName_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = XmlTagNamespace(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, XMLCOLON);
    result_ = result_ && XmlTagLocalName(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // XmlTagNCName
  public static boolean XmlTagNamespace(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "XmlTagNamespace")) return false;
    if (!nextTokenIs(builder_, XMLTAGNCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, XMLTAGNCNAME);
    exit_section_(builder_, marker_, XML_TAG_NAMESPACE, result_);
    return result_;
  }

  /* ********************************************************** */
  // Module
  static boolean xqueryFile(PsiBuilder builder_, int level_) {
    return Module(builder_, level_ + 1);
  }

  final static Parser FLWORExprRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return FLWORExprRecover(builder_, level_ + 1);
    }
  };
  final static Parser ModuleDeclRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return ModuleDeclRecover(builder_, level_ + 1);
    }
  };
  final static Parser QueryBodyRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return QueryBodyRecover(builder_, level_ + 1);
    }
  };
  final static Parser VersionDeclRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return VersionDeclRecover(builder_, level_ + 1);
    }
  };
}
