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

// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.parser;

import org.jetbrains.annotations.*;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static org.intellij.xquery.psi.XQueryTypes.*;
import static org.intellij.xquery.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class XQueryParser implements PsiParser {

  public static Logger LOG_ = Logger.getInstance("org.intellij.xquery.parser.XQueryParser");

  @NotNull
  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    int level_ = 0;
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this);
    if (root_ == ABBREV_FORWARD_STEP) {
      result_ = AbbrevForwardStep(builder_, level_ + 1);
    }
    else if (root_ == ABBREV_REVERSE_STEP) {
      result_ = AbbrevReverseStep(builder_, level_ + 1);
    }
    else if (root_ == ADDITIVE_EXPR) {
      result_ = AdditiveExpr(builder_, level_ + 1);
    }
    else if (root_ == ALLOWING_EMPTY) {
      result_ = AllowingEmpty(builder_, level_ + 1);
    }
    else if (root_ == AND_EXPR) {
      result_ = AndExpr(builder_, level_ + 1);
    }
    else if (root_ == ANNOTATION) {
      result_ = Annotation(builder_, level_ + 1);
    }
    else if (root_ == ANNOTATION_NAME) {
      result_ = AnnotationName(builder_, level_ + 1);
    }
    else if (root_ == ANY_FUNCTION_TEST) {
      result_ = AnyFunctionTest(builder_, level_ + 1);
    }
    else if (root_ == ANY_KIND_TEST) {
      result_ = AnyKindTest(builder_, level_ + 1);
    }
    else if (root_ == APOS_ATTR_CONTENT_CHAR) {
      result_ = AposAttrContentChar(builder_, level_ + 1);
    }
    else if (root_ == APOS_ATTR_VALUE_CONTENT) {
      result_ = AposAttrValueContent(builder_, level_ + 1);
    }
    else if (root_ == ARGUMENT) {
      result_ = Argument(builder_, level_ + 1);
    }
    else if (root_ == ARGUMENT_LIST) {
      result_ = ArgumentList(builder_, level_ + 1);
    }
    else if (root_ == ARGUMENT_PLACEHOLDER) {
      result_ = ArgumentPlaceholder(builder_, level_ + 1);
    }
    else if (root_ == ATOMIC_OR_UNION_TYPE) {
      result_ = AtomicOrUnionType(builder_, level_ + 1);
    }
    else if (root_ == ATTRIB_NAME_OR_WILDCARD) {
      result_ = AttribNameOrWildcard(builder_, level_ + 1);
    }
    else if (root_ == ATTRIBUTE_DECLARATION) {
      result_ = AttributeDeclaration(builder_, level_ + 1);
    }
    else if (root_ == ATTRIBUTE_NAME) {
      result_ = AttributeName(builder_, level_ + 1);
    }
    else if (root_ == ATTRIBUTE_TEST) {
      result_ = AttributeTest(builder_, level_ + 1);
    }
    else if (root_ == AXIS_STEP) {
      result_ = AxisStep(builder_, level_ + 1);
    }
    else if (root_ == BASE_URI_DECL) {
      result_ = BaseURIDecl(builder_, level_ + 1);
    }
    else if (root_ == BOUNDARY_SPACE_DECL) {
      result_ = BoundarySpaceDecl(builder_, level_ + 1);
    }
    else if (root_ == C_DATA_SECTION) {
      result_ = CDataSection(builder_, level_ + 1);
    }
    else if (root_ == C_DATA_SECTION_CONTENTS) {
      result_ = CDataSectionContents(builder_, level_ + 1);
    }
    else if (root_ == CASE_CLAUSE) {
      result_ = CaseClause(builder_, level_ + 1);
    }
    else if (root_ == CAST_EXPR) {
      result_ = CastExpr(builder_, level_ + 1);
    }
    else if (root_ == CASTABLE_EXPR) {
      result_ = CastableExpr(builder_, level_ + 1);
    }
    else if (root_ == CATCH_CLAUSE) {
      result_ = CatchClause(builder_, level_ + 1);
    }
    else if (root_ == CATCH_ERROR_LIST) {
      result_ = CatchErrorList(builder_, level_ + 1);
    }
    else if (root_ == COMMENT_TEST) {
      result_ = CommentTest(builder_, level_ + 1);
    }
    else if (root_ == COMMON_CONTENT) {
      result_ = CommonContent(builder_, level_ + 1);
    }
    else if (root_ == COMP_ATTR_CONSTRUCTOR) {
      result_ = CompAttrConstructor(builder_, level_ + 1);
    }
    else if (root_ == COMP_COMMENT_CONSTRUCTOR) {
      result_ = CompCommentConstructor(builder_, level_ + 1);
    }
    else if (root_ == COMP_DOC_CONSTRUCTOR) {
      result_ = CompDocConstructor(builder_, level_ + 1);
    }
    else if (root_ == COMP_ELEM_CONSTRUCTOR) {
      result_ = CompElemConstructor(builder_, level_ + 1);
    }
    else if (root_ == COMP_MAP_CONSTRUCTOR) {
      result_ = CompMapConstructor(builder_, level_ + 1);
    }
    else if (root_ == COMP_NAMESPACE_CONSTRUCTOR) {
      result_ = CompNamespaceConstructor(builder_, level_ + 1);
    }
    else if (root_ == COMP_PI_CONSTRUCTOR) {
      result_ = CompPIConstructor(builder_, level_ + 1);
    }
    else if (root_ == COMP_TEXT_CONSTRUCTOR) {
      result_ = CompTextConstructor(builder_, level_ + 1);
    }
    else if (root_ == COMPARISON_EXPR) {
      result_ = ComparisonExpr(builder_, level_ + 1);
    }
    else if (root_ == COMPUTED_CONSTRUCTOR) {
      result_ = ComputedConstructor(builder_, level_ + 1);
    }
    else if (root_ == CONSTRUCTION_DECL) {
      result_ = ConstructionDecl(builder_, level_ + 1);
    }
    else if (root_ == CONSTRUCTOR) {
      result_ = Constructor(builder_, level_ + 1);
    }
    else if (root_ == CONTENT_EXPR) {
      result_ = ContentExpr(builder_, level_ + 1);
    }
    else if (root_ == CONTEXT_ITEM_DECL) {
      result_ = ContextItemDecl(builder_, level_ + 1);
    }
    else if (root_ == CONTEXT_ITEM_EXPR) {
      result_ = ContextItemExpr(builder_, level_ + 1);
    }
    else if (root_ == COPY_NAMESPACES_DECL) {
      result_ = CopyNamespacesDecl(builder_, level_ + 1);
    }
    else if (root_ == COUNT_CLAUSE) {
      result_ = CountClause(builder_, level_ + 1);
    }
    else if (root_ == CURRENT_ITEM) {
      result_ = CurrentItem(builder_, level_ + 1);
    }
    else if (root_ == DF_PROPERTY_NAME) {
      result_ = DFPropertyName(builder_, level_ + 1);
    }
    else if (root_ == DECIMAL_FORMAT_DECL) {
      result_ = DecimalFormatDecl(builder_, level_ + 1);
    }
    else if (root_ == DEFAULT_COLLATION_DECL) {
      result_ = DefaultCollationDecl(builder_, level_ + 1);
    }
    else if (root_ == DEFAULT_ELEMENT_NAMESPACE_DECL) {
      result_ = DefaultElementNamespaceDecl(builder_, level_ + 1);
    }
    else if (root_ == DEFAULT_FUNCTION_NAMESPACE_DECL) {
      result_ = DefaultFunctionNamespaceDecl(builder_, level_ + 1);
    }
    else if (root_ == DEFAULT_NAMESPACE_DECL) {
      result_ = DefaultNamespaceDecl(builder_, level_ + 1);
    }
    else if (root_ == DIR_ATTRIBUTE_LIST) {
      result_ = DirAttributeList(builder_, level_ + 1);
    }
    else if (root_ == DIR_ATTRIBUTE_NAME) {
      result_ = DirAttributeName(builder_, level_ + 1);
    }
    else if (root_ == DIR_ATTRIBUTE_VALUE) {
      result_ = DirAttributeValue(builder_, level_ + 1);
    }
    else if (root_ == DIR_COMMENT_CONSTRUCTOR) {
      result_ = DirCommentConstructor(builder_, level_ + 1);
    }
    else if (root_ == DIR_COMMENT_CONTENTS) {
      result_ = DirCommentContents(builder_, level_ + 1);
    }
    else if (root_ == DIR_ELEM_CONSTRUCTOR) {
      result_ = DirElemConstructor(builder_, level_ + 1);
    }
    else if (root_ == DIR_ELEM_CONTENT) {
      result_ = DirElemContent(builder_, level_ + 1);
    }
    else if (root_ == DIR_PI_CONSTRUCTOR) {
      result_ = DirPIConstructor(builder_, level_ + 1);
    }
    else if (root_ == DIR_PI_CONTENTS) {
      result_ = DirPIContents(builder_, level_ + 1);
    }
    else if (root_ == DIRECT_CONSTRUCTOR) {
      result_ = DirectConstructor(builder_, level_ + 1);
    }
    else if (root_ == DOCUMENT_TEST) {
      result_ = DocumentTest(builder_, level_ + 1);
    }
    else if (root_ == ELEMENT_DECLARATION) {
      result_ = ElementDeclaration(builder_, level_ + 1);
    }
    else if (root_ == ELEMENT_NAME) {
      result_ = ElementName(builder_, level_ + 1);
    }
    else if (root_ == ELEMENT_NAME_OR_WILDCARD) {
      result_ = ElementNameOrWildcard(builder_, level_ + 1);
    }
    else if (root_ == ELEMENT_TEST) {
      result_ = ElementTest(builder_, level_ + 1);
    }
    else if (root_ == EMPTY_ORDER_DECL) {
      result_ = EmptyOrderDecl(builder_, level_ + 1);
    }
    else if (root_ == ENCLOSED_EXPR) {
      result_ = EnclosedExpr(builder_, level_ + 1);
    }
    else if (root_ == ESCAPE_APOS) {
      result_ = EscapeApos(builder_, level_ + 1);
    }
    else if (root_ == ESCAPE_QUOT) {
      result_ = EscapeQuot(builder_, level_ + 1);
    }
    else if (root_ == EXPR) {
      result_ = Expr(builder_, level_ + 1);
    }
    else if (root_ == EXPR_SINGLE) {
      result_ = ExprSingle(builder_, level_ + 1);
    }
    else if (root_ == EXTENSION_EXPR) {
      result_ = ExtensionExpr(builder_, level_ + 1);
    }
    else if (root_ == EXTERNAL_VAR_PART) {
      result_ = ExternalVarPart(builder_, level_ + 1);
    }
    else if (root_ == FLWOR_EXPR) {
      result_ = FLWORExpr(builder_, level_ + 1);
    }
    else if (root_ == FOR_BINDING) {
      result_ = ForBinding(builder_, level_ + 1);
    }
    else if (root_ == FOR_CLAUSE) {
      result_ = ForClause(builder_, level_ + 1);
    }
    else if (root_ == FORWARD_STEP) {
      result_ = ForwardStep(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_ARITY) {
      result_ = FunctionArity(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_BODY) {
      result_ = FunctionBody(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_CALL) {
      result_ = FunctionCall(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_DECL) {
      result_ = FunctionDecl(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_ITEM_EXPR) {
      result_ = FunctionItemExpr(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_LOCAL_NAME) {
      result_ = FunctionLocalName(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_NAME) {
      result_ = FunctionName(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_NAMESPACE) {
      result_ = FunctionNamespace(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_TEST) {
      result_ = FunctionTest(builder_, level_ + 1);
    }
    else if (root_ == GENERAL_ITEM_TYPE) {
      result_ = GeneralItemType(builder_, level_ + 1);
    }
    else if (root_ == GROUP_BY_CLAUSE) {
      result_ = GroupByClause(builder_, level_ + 1);
    }
    else if (root_ == GROUPING_SPEC) {
      result_ = GroupingSpec(builder_, level_ + 1);
    }
    else if (root_ == GROUPING_SPEC_LIST) {
      result_ = GroupingSpecList(builder_, level_ + 1);
    }
    else if (root_ == GROUPING_VARIABLE) {
      result_ = GroupingVariable(builder_, level_ + 1);
    }
    else if (root_ == IF_EXPR) {
      result_ = IfExpr(builder_, level_ + 1);
    }
    else if (root_ == IMPORT) {
      result_ = Import(builder_, level_ + 1);
    }
    else if (root_ == INHERIT_MODE) {
      result_ = InheritMode(builder_, level_ + 1);
    }
    else if (root_ == INITIAL_CLAUSE) {
      result_ = InitialClause(builder_, level_ + 1);
    }
    else if (root_ == INLINE_FUNCTION_EXPR) {
      result_ = InlineFunctionExpr(builder_, level_ + 1);
    }
    else if (root_ == INSTANCEOF_EXPR) {
      result_ = InstanceofExpr(builder_, level_ + 1);
    }
    else if (root_ == INTERMEDIATE_CLAUSE) {
      result_ = IntermediateClause(builder_, level_ + 1);
    }
    else if (root_ == INTERSECT_EXCEPT_EXPR) {
      result_ = IntersectExceptExpr(builder_, level_ + 1);
    }
    else if (root_ == ITEM_TYPE) {
      result_ = ItemType(builder_, level_ + 1);
    }
    else if (root_ == KIND_TEST) {
      result_ = KindTest(builder_, level_ + 1);
    }
    else if (root_ == LET_BINDING) {
      result_ = LetBinding(builder_, level_ + 1);
    }
    else if (root_ == LET_CLAUSE) {
      result_ = LetClause(builder_, level_ + 1);
    }
    else if (root_ == LIBRARY_MODULE) {
      result_ = LibraryModule(builder_, level_ + 1);
    }
    else if (root_ == LITERAL) {
      result_ = Literal(builder_, level_ + 1);
    }
    else if (root_ == LOCAL_PART) {
      result_ = LocalPart(builder_, level_ + 1);
    }
    else if (root_ == MAIN_MODULE) {
      result_ = MainModule(builder_, level_ + 1);
    }
    else if (root_ == MAP_ENTRY) {
      result_ = MapEntry(builder_, level_ + 1);
    }
    else if (root_ == MAP_ENTRY_LIST) {
      result_ = MapEntryList(builder_, level_ + 1);
    }
    else if (root_ == MAP_TEST) {
      result_ = MapTest(builder_, level_ + 1);
    }
    else if (root_ == MODULE) {
      result_ = Module(builder_, level_ + 1);
    }
    else if (root_ == MODULE_DECL) {
      result_ = ModuleDecl(builder_, level_ + 1);
    }
    else if (root_ == MODULE_IMPORT) {
      result_ = ModuleImport(builder_, level_ + 1);
    }
    else if (root_ == MODULE_IMPORT_NAMESPACE) {
      result_ = ModuleImportNamespace(builder_, level_ + 1);
    }
    else if (root_ == MODULE_IMPORT_PATH) {
      result_ = ModuleImportPath(builder_, level_ + 1);
    }
    else if (root_ == MULTIPLICATIVE_EXPR) {
      result_ = MultiplicativeExpr(builder_, level_ + 1);
    }
    else if (root_ == NAME_TEST) {
      result_ = NameTest(builder_, level_ + 1);
    }
    else if (root_ == NAMED_FUNCTION_REF) {
      result_ = NamedFunctionRef(builder_, level_ + 1);
    }
    else if (root_ == NAMESPACE_DECL) {
      result_ = NamespaceDecl(builder_, level_ + 1);
    }
    else if (root_ == NAMESPACE_NAME) {
      result_ = NamespaceName(builder_, level_ + 1);
    }
    else if (root_ == NAMESPACE_NODE_TEST) {
      result_ = NamespaceNodeTest(builder_, level_ + 1);
    }
    else if (root_ == NEXT_ITEM) {
      result_ = NextItem(builder_, level_ + 1);
    }
    else if (root_ == NODE_TEST) {
      result_ = NodeTest(builder_, level_ + 1);
    }
    else if (root_ == NUMERIC_LITERAL) {
      result_ = NumericLiteral(builder_, level_ + 1);
    }
    else if (root_ == OCCURRENCE_INDICATOR) {
      result_ = OccurrenceIndicator(builder_, level_ + 1);
    }
    else if (root_ == OPTION_DECL) {
      result_ = OptionDecl(builder_, level_ + 1);
    }
    else if (root_ == OR_EXPR) {
      result_ = OrExpr(builder_, level_ + 1);
    }
    else if (root_ == ORDER_BY_CLAUSE) {
      result_ = OrderByClause(builder_, level_ + 1);
    }
    else if (root_ == ORDER_MODIFIER) {
      result_ = OrderModifier(builder_, level_ + 1);
    }
    else if (root_ == ORDER_SPEC) {
      result_ = OrderSpec(builder_, level_ + 1);
    }
    else if (root_ == ORDER_SPEC_LIST) {
      result_ = OrderSpecList(builder_, level_ + 1);
    }
    else if (root_ == ORDERED_EXPR) {
      result_ = OrderedExpr(builder_, level_ + 1);
    }
    else if (root_ == ORDERING_MODE_DECL) {
      result_ = OrderingModeDecl(builder_, level_ + 1);
    }
    else if (root_ == PI_TEST) {
      result_ = PITest(builder_, level_ + 1);
    }
    else if (root_ == PARAM) {
      result_ = Param(builder_, level_ + 1);
    }
    else if (root_ == PARAM_LIST) {
      result_ = ParamList(builder_, level_ + 1);
    }
    else if (root_ == PARENTHESIZED_EXPR) {
      result_ = ParenthesizedExpr(builder_, level_ + 1);
    }
    else if (root_ == PARENTHESIZED_ITEM_TYPE) {
      result_ = ParenthesizedItemType(builder_, level_ + 1);
    }
    else if (root_ == PATH_EXPR) {
      result_ = PathExpr(builder_, level_ + 1);
    }
    else if (root_ == POSITIONAL_VAR) {
      result_ = PositionalVar(builder_, level_ + 1);
    }
    else if (root_ == POSTFIX_EXPR) {
      result_ = PostfixExpr(builder_, level_ + 1);
    }
    else if (root_ == PRAGMA) {
      result_ = Pragma(builder_, level_ + 1);
    }
    else if (root_ == PRAGMA_CONTENTS) {
      result_ = PragmaContents(builder_, level_ + 1);
    }
    else if (root_ == PREDICATE) {
      result_ = Predicate(builder_, level_ + 1);
    }
    else if (root_ == PREDICATE_LIST) {
      result_ = PredicateList(builder_, level_ + 1);
    }
    else if (root_ == PREFIX) {
      result_ = Prefix(builder_, level_ + 1);
    }
    else if (root_ == PREFIX_EXPR) {
      result_ = PrefixExpr(builder_, level_ + 1);
    }
    else if (root_ == PRESERVE_MODE) {
      result_ = PreserveMode(builder_, level_ + 1);
    }
    else if (root_ == PREVIOUS_ITEM) {
      result_ = PreviousItem(builder_, level_ + 1);
    }
    else if (root_ == PRIMARY_EXPR) {
      result_ = PrimaryExpr(builder_, level_ + 1);
    }
    else if (root_ == PROLOG) {
      result_ = Prolog(builder_, level_ + 1);
    }
    else if (root_ == QUANTIFIED_EXPR) {
      result_ = QuantifiedExpr(builder_, level_ + 1);
    }
    else if (root_ == QUERY_BODY) {
      result_ = QueryBody(builder_, level_ + 1);
    }
    else if (root_ == QUOT_ATTR_CONTENT_CHAR) {
      result_ = QuotAttrContentChar(builder_, level_ + 1);
    }
    else if (root_ == QUOT_ATTR_VALUE_CONTENT) {
      result_ = QuotAttrValueContent(builder_, level_ + 1);
    }
    else if (root_ == RANGE_EXPR) {
      result_ = RangeExpr(builder_, level_ + 1);
    }
    else if (root_ == RELATIVE_PATH_EXPR) {
      result_ = RelativePathExpr(builder_, level_ + 1);
    }
    else if (root_ == RETURN_CLAUSE) {
      result_ = ReturnClause(builder_, level_ + 1);
    }
    else if (root_ == REVERSE_STEP) {
      result_ = ReverseStep(builder_, level_ + 1);
    }
    else if (root_ == SCHEMA_ATTRIBUTE_TEST) {
      result_ = SchemaAttributeTest(builder_, level_ + 1);
    }
    else if (root_ == SCHEMA_ELEMENT_TEST) {
      result_ = SchemaElementTest(builder_, level_ + 1);
    }
    else if (root_ == SCHEMA_IMPORT) {
      result_ = SchemaImport(builder_, level_ + 1);
    }
    else if (root_ == SEPARATOR) {
      result_ = Separator(builder_, level_ + 1);
    }
    else if (root_ == SEQUENCE_TYPE) {
      result_ = SequenceType(builder_, level_ + 1);
    }
    else if (root_ == SEQUENCE_TYPE_UNION) {
      result_ = SequenceTypeUnion(builder_, level_ + 1);
    }
    else if (root_ == SETTER) {
      result_ = Setter(builder_, level_ + 1);
    }
    else if (root_ == SIMPLE_MAP_EXPR) {
      result_ = SimpleMapExpr(builder_, level_ + 1);
    }
    else if (root_ == SIMPLE_TYPE_NAME) {
      result_ = SimpleTypeName(builder_, level_ + 1);
    }
    else if (root_ == SINGLE_TYPE) {
      result_ = SingleType(builder_, level_ + 1);
    }
    else if (root_ == SLIDING_WINDOW_CLAUSE) {
      result_ = SlidingWindowClause(builder_, level_ + 1);
    }
    else if (root_ == STEP_EXPR) {
      result_ = StepExpr(builder_, level_ + 1);
    }
    else if (root_ == STRING_CONCAT_EXPR) {
      result_ = StringConcatExpr(builder_, level_ + 1);
    }
    else if (root_ == SWITCH_CASE_CLAUSE) {
      result_ = SwitchCaseClause(builder_, level_ + 1);
    }
    else if (root_ == SWITCH_CASE_OPERAND) {
      result_ = SwitchCaseOperand(builder_, level_ + 1);
    }
    else if (root_ == SWITCH_DEFAULT_RETURN_CLAUSE) {
      result_ = SwitchDefaultReturnClause(builder_, level_ + 1);
    }
    else if (root_ == SWITCH_EXPR) {
      result_ = SwitchExpr(builder_, level_ + 1);
    }
    else if (root_ == SWITCH_RETURN_CLAUSE) {
      result_ = SwitchReturnClause(builder_, level_ + 1);
    }
    else if (root_ == TAG_NAME) {
      result_ = TagName(builder_, level_ + 1);
    }
    else if (root_ == TEXT_TEST) {
      result_ = TextTest(builder_, level_ + 1);
    }
    else if (root_ == TREAT_EXPR) {
      result_ = TreatExpr(builder_, level_ + 1);
    }
    else if (root_ == TRY_CATCH_EXPR) {
      result_ = TryCatchExpr(builder_, level_ + 1);
    }
    else if (root_ == TRY_CLAUSE) {
      result_ = TryClause(builder_, level_ + 1);
    }
    else if (root_ == TRY_TARGET_EXPR) {
      result_ = TryTargetExpr(builder_, level_ + 1);
    }
    else if (root_ == TUMBLING_WINDOW_CLAUSE) {
      result_ = TumblingWindowClause(builder_, level_ + 1);
    }
    else if (root_ == TYPE_DECLARATION) {
      result_ = TypeDeclaration(builder_, level_ + 1);
    }
    else if (root_ == TYPE_NAME) {
      result_ = TypeName(builder_, level_ + 1);
    }
    else if (root_ == TYPED_FUNCTION_TEST) {
      result_ = TypedFunctionTest(builder_, level_ + 1);
    }
    else if (root_ == TYPESWITCH_DEFAULT_RETURN_CLAUSE) {
      result_ = TypeswitchDefaultReturnClause(builder_, level_ + 1);
    }
    else if (root_ == TYPESWITCH_EXPR) {
      result_ = TypeswitchExpr(builder_, level_ + 1);
    }
    else if (root_ == URI_EXPR) {
      result_ = URIExpr(builder_, level_ + 1);
    }
    else if (root_ == URI_LITERAL) {
      result_ = URILiteral(builder_, level_ + 1);
    }
    else if (root_ == UNARY_EXPR) {
      result_ = UnaryExpr(builder_, level_ + 1);
    }
    else if (root_ == UNION_EXPR) {
      result_ = UnionExpr(builder_, level_ + 1);
    }
    else if (root_ == UNORDERED_EXPR) {
      result_ = UnorderedExpr(builder_, level_ + 1);
    }
    else if (root_ == VALIDATE_EXPR) {
      result_ = ValidateExpr(builder_, level_ + 1);
    }
    else if (root_ == VALIDATION_MODE) {
      result_ = ValidationMode(builder_, level_ + 1);
    }
    else if (root_ == VALUE_EXPR) {
      result_ = ValueExpr(builder_, level_ + 1);
    }
    else if (root_ == VAR_DECL) {
      result_ = VarDecl(builder_, level_ + 1);
    }
    else if (root_ == VAR_DEFAULT_VALUE) {
      result_ = VarDefaultValue(builder_, level_ + 1);
    }
    else if (root_ == VAR_LOCAL_NAME) {
      result_ = VarLocalName(builder_, level_ + 1);
    }
    else if (root_ == VAR_NAME) {
      result_ = VarName(builder_, level_ + 1);
    }
    else if (root_ == VAR_NAMESPACE) {
      result_ = VarNamespace(builder_, level_ + 1);
    }
    else if (root_ == VAR_REF) {
      result_ = VarRef(builder_, level_ + 1);
    }
    else if (root_ == VAR_VALUE) {
      result_ = VarValue(builder_, level_ + 1);
    }
    else if (root_ == VERSION_DECL) {
      result_ = VersionDecl(builder_, level_ + 1);
    }
    else if (root_ == VERSION_DECL_ENCODING) {
      result_ = VersionDeclEncoding(builder_, level_ + 1);
    }
    else if (root_ == VERSION_DECL_VERSION) {
      result_ = VersionDeclVersion(builder_, level_ + 1);
    }
    else if (root_ == WHERE_CLAUSE) {
      result_ = WhereClause(builder_, level_ + 1);
    }
    else if (root_ == WILDCARD) {
      result_ = Wildcard(builder_, level_ + 1);
    }
    else if (root_ == WINDOW_CLAUSE) {
      result_ = WindowClause(builder_, level_ + 1);
    }
    else if (root_ == WINDOW_END_CONDITION) {
      result_ = WindowEndCondition(builder_, level_ + 1);
    }
    else if (root_ == WINDOW_START_CONDITION) {
      result_ = WindowStartCondition(builder_, level_ + 1);
    }
    else if (root_ == WINDOW_VARS) {
      result_ = WindowVars(builder_, level_ + 1);
    }
    else {
      Marker marker_ = builder_.mark();
      enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, null);
      result_ = parse_root_(root_, builder_, level_);
      exitErrorRecordingSection(builder_, level_, result_, true, _SECTION_RECOVER_, TOKEN_ADVANCER);
      marker_.done(root_);
    }
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return xqueryFile(builder_, level_ + 1);
  }

  private static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    TokenSet.create(ADDITIVE_EXPR, AND_EXPR, CASTABLE_EXPR, CAST_EXPR,
      COMPARISON_EXPR, CONTENT_EXPR, CONTEXT_ITEM_EXPR, ENCLOSED_EXPR,
      EXPR_SINGLE, EXTENSION_EXPR, FLWOR_EXPR, FUNCTION_ITEM_EXPR,
      IF_EXPR, INLINE_FUNCTION_EXPR, INSTANCEOF_EXPR, INTERSECT_EXCEPT_EXPR,
      MULTIPLICATIVE_EXPR, ORDERED_EXPR, OR_EXPR, PARENTHESIZED_EXPR,
      PATH_EXPR, POSTFIX_EXPR, PREFIX_EXPR, PRIMARY_EXPR,
      QUANTIFIED_EXPR, RANGE_EXPR, RELATIVE_PATH_EXPR, SIMPLE_MAP_EXPR,
      STEP_EXPR, STRING_CONCAT_EXPR, SWITCH_EXPR, TREAT_EXPR,
      TRY_CATCH_EXPR, TRY_TARGET_EXPR, TYPESWITCH_EXPR, UNARY_EXPR,
      UNION_EXPR, UNORDERED_EXPR, URI_EXPR, VALIDATE_EXPR,
      VALUE_EXPR),
  };

  public static boolean type_extends_(IElementType child_, IElementType parent_) {
    for (TokenSet set : EXTENDS_SETS_) {
      if (set.contains(child_) && set.contains(parent_)) return true;
    }
    return false;
  }

  /* ********************************************************** */
  // "@"? NodeTest
  public static boolean AbbrevForwardStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbbrevForwardStep")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<abbrev forward step>");
    result_ = AbbrevForwardStep_0(builder_, level_ + 1);
    result_ = result_ && NodeTest(builder_, level_ + 1);
    if (result_) {
      marker_.done(ABBREV_FORWARD_STEP);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOT_DOT);
    if (result_) {
      marker_.done(ABBREV_REVERSE_STEP);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // MultiplicativeExpr ( ("+" | "-") MultiplicativeExpr )*
  public static boolean AdditiveExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AdditiveExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<additive expr>");
    result_ = MultiplicativeExpr(builder_, level_ + 1);
    result_ = result_ && AdditiveExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), ADDITIVE_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(ADDITIVE_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ( ("+" | "-") MultiplicativeExpr )*
  private static boolean AdditiveExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AdditiveExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!AdditiveExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "AdditiveExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // ("+" | "-") MultiplicativeExpr
  private static boolean AdditiveExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AdditiveExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = AdditiveExpr_1_0_0(builder_, level_ + 1);
    result_ = result_ && MultiplicativeExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "+" | "-"
  private static boolean AdditiveExpr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AdditiveExpr_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, OP_PLUS);
    if (!result_) result_ = consumeToken(builder_, OP_MINUS);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "allowing" "empty"
  public static boolean AllowingEmpty(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AllowingEmpty")) return false;
    if (!nextTokenIs(builder_, K_ALLOWING)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ALLOWING);
    result_ = result_ && consumeToken(builder_, K_EMPTY);
    if (result_) {
      marker_.done(ALLOWING_EMPTY);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // ComparisonExpr (AndMultipliedExpr)*
  public static boolean AndExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<and expr>");
    result_ = ComparisonExpr(builder_, level_ + 1);
    result_ = result_ && AndExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), AND_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(AND_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // (AndMultipliedExpr)*
  private static boolean AndExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!AndExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "AndExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // (AndMultipliedExpr)
  private static boolean AndExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = AndMultipliedExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "and" ComparisonExpr
  static boolean AndMultipliedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndMultipliedExpr")) return false;
    if (!nextTokenIs(builder_, K_AND)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_AND);
    pinned_ = result_; // pin = 1
    result_ = result_ && ComparisonExpr(builder_, level_ + 1);
    if (!result_ && !pinned_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // (VarDecl | FunctionDecl) Separator
  static boolean AnnotatedDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnnotatedDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = AnnotatedDecl_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && Separator(builder_, level_ + 1);
    if (!result_ && !pinned_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // VarDecl | FunctionDecl
  private static boolean AnnotatedDecl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnnotatedDecl_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = VarDecl(builder_, level_ + 1);
    if (!result_) result_ = FunctionDecl(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "%" AnnotationName ("(" Literal ("," Literal)* ")")?
  public static boolean Annotation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Annotation")) return false;
    if (!nextTokenIs(builder_, PERCENT)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PERCENT);
    result_ = result_ && AnnotationName(builder_, level_ + 1);
    result_ = result_ && Annotation_2(builder_, level_ + 1);
    if (result_) {
      marker_.done(ANNOTATION);
    }
    else {
      marker_.rollbackTo();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, L_PAR);
    result_ = result_ && Literal(builder_, level_ + 1);
    result_ = result_ && Annotation_2_0_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ("," Literal)*
  private static boolean Annotation_2_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Annotation_2_0_2")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!Annotation_2_0_2_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "Annotation_2_0_2");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," Literal
  private static boolean Annotation_2_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Annotation_2_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && Literal(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean AnnotationName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnnotationName")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<annotation name>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<annotation name>");
    result_ = EQName(builder_, level_ + 1);
    if (result_) {
      marker_.done(ANNOTATION_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "function" "(" "*" ")"
  public static boolean AnyFunctionTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnyFunctionTest")) return false;
    if (!nextTokenIs(builder_, K_FUNCTION)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_FUNCTION);
    result_ = result_ && consumeToken(builder_, L_PAR);
    result_ = result_ && consumeToken(builder_, STAR_SIGN);
    pinned_ = result_; // pin = 3
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (result_ || pinned_) {
      marker_.done(ANY_FUNCTION_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "node" "(" ")"
  public static boolean AnyKindTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnyKindTest")) return false;
    if (!nextTokenIs(builder_, K_NODE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_NODE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (result_ || pinned_) {
      marker_.done(ANY_KIND_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Char
  public static boolean AposAttrContentChar(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AposAttrContentChar")) return false;
    if (!nextTokenIs(builder_, CHAR)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, CHAR);
    if (result_) {
      marker_.done(APOS_ATTR_CONTENT_CHAR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // AposAttrContentChar
  //  | CommonContent
  public static boolean AposAttrValueContent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AposAttrValueContent")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<apos attr value content>");
    result_ = AposAttrContentChar(builder_, level_ + 1);
    if (!result_) result_ = CommonContent(builder_, level_ + 1);
    if (result_) {
      marker_.done(APOS_ATTR_VALUE_CONTENT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle | ArgumentPlaceholder
  public static boolean Argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Argument")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<argument>");
    result_ = ExprSingle(builder_, level_ + 1);
    if (!result_) result_ = ArgumentPlaceholder(builder_, level_ + 1);
    if (result_) {
      marker_.done(ARGUMENT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "(" (Argument ("," Argument)*)? ")"
  public static boolean ArgumentList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentList")) return false;
    if (!nextTokenIs(builder_, L_PAR)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, L_PAR);
    result_ = result_ && ArgumentList_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (result_) {
      marker_.done(ARGUMENT_LIST);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // (Argument ("," Argument)*)?
  private static boolean ArgumentList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentList_1")) return false;
    ArgumentList_1_0(builder_, level_ + 1);
    return true;
  }

  // Argument ("," Argument)*
  private static boolean ArgumentList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = Argument(builder_, level_ + 1);
    result_ = result_ && ArgumentList_1_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ("," Argument)*
  private static boolean ArgumentList_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentList_1_0_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!ArgumentList_1_0_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "ArgumentList_1_0_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," Argument
  private static boolean ArgumentList_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentList_1_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && Argument(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "?"
  public static boolean ArgumentPlaceholder(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArgumentPlaceholder")) return false;
    if (!nextTokenIs(builder_, QUESTIONMARK)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, QUESTIONMARK);
    if (result_) {
      marker_.done(ARGUMENT_PLACEHOLDER);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean AtomicOrUnionType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AtomicOrUnionType")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<atomic or union type>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<atomic or union type>");
    result_ = EQName(builder_, level_ + 1);
    if (result_) {
      marker_.done(ATOMIC_OR_UNION_TYPE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // AttributeName | "*"
  public static boolean AttribNameOrWildcard(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttribNameOrWildcard")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<attrib name or wildcard>");
    result_ = AttributeName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STAR_SIGN);
    if (result_) {
      marker_.done(ATTRIB_NAME_OR_WILDCARD);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // AttributeName
  public static boolean AttributeDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeDeclaration")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<attribute declaration>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<attribute declaration>");
    result_ = AttributeName(builder_, level_ + 1);
    if (result_) {
      marker_.done(ATTRIBUTE_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean AttributeName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeName")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<attribute name>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<attribute name>");
    result_ = EQName(builder_, level_ + 1);
    if (result_) {
      marker_.done(ATTRIBUTE_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "attribute" "(" (AttribNameOrWildcard ("," TypeName)?)? ")"
  public static boolean AttributeTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttributeTest")) return false;
    if (!nextTokenIs(builder_, K_ATTRIBUTE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_ATTRIBUTE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, AttributeTest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    if (result_ || pinned_) {
      marker_.done(ATTRIBUTE_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = AttribNameOrWildcard(builder_, level_ + 1);
    result_ = result_ && AttributeTest_2_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && TypeName(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // (ReverseStep | ForwardStep) PredicateList
  public static boolean AxisStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AxisStep")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<axis step>");
    result_ = AxisStep_0(builder_, level_ + 1);
    result_ = result_ && PredicateList(builder_, level_ + 1);
    if (result_) {
      marker_.done(AXIS_STEP);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ReverseStep | ForwardStep
  private static boolean AxisStep_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AxisStep_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ReverseStep(builder_, level_ + 1);
    if (!result_) result_ = ForwardStep(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "declare" "base-uri" URILiteral Separator
  public static boolean BaseURIDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BaseURIDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_BASE_URI);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, URILiteral(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(BASE_URI_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "declare" "boundary-space" ("preserve" | "strip") Separator
  public static boolean BoundarySpaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BoundarySpaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_BOUNDARY_SPACE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, BoundarySpaceDecl_2(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(BOUNDARY_SPACE_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // "preserve" | "strip"
  private static boolean BoundarySpaceDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BoundarySpaceDecl_2")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_PRESERVE);
    if (!result_) result_ = consumeToken(builder_, K_STRIP);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "<![CDATA[" CDataSectionContents "]]>"
  public static boolean CDataSection(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CDataSection")) return false;
    if (!nextTokenIs(builder_, CDATA_BEGIN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, CDATA_BEGIN);
    result_ = result_ && CDataSectionContents(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CDATA_END);
    if (result_) {
      marker_.done(C_DATA_SECTION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // CDataSectionContentChar*
  public static boolean CDataSectionContents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CDataSectionContents")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<c data section contents>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!consumeToken(builder_, CDATASECTIONCONTENTCHAR)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "CDataSectionContents");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(C_DATA_SECTION_CONTENTS);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  /* ********************************************************** */
  // "case" ("$" VarName "as")? SequenceTypeUnion SwitchReturnClause
  public static boolean CaseClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CaseClause")) return false;
    if (!nextTokenIs(builder_, K_CASE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_CASE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, CaseClause_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, SequenceTypeUnion(builder_, level_ + 1)) && result_;
    result_ = pinned_ && SwitchReturnClause(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(CASE_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_AS);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // UnaryExpr ( "cast" "as" SingleType )?
  public static boolean CastExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<cast expr>");
    result_ = UnaryExpr(builder_, level_ + 1);
    result_ = result_ && CastExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), CAST_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(CAST_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ( "cast" "as" SingleType )?
  private static boolean CastExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastExpr_1")) return false;
    CastExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // "cast" "as" SingleType
  private static boolean CastExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_CAST);
    result_ = result_ && consumeToken(builder_, K_AS);
    result_ = result_ && SingleType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // CastExpr ( "castable" "as" SingleType )?
  public static boolean CastableExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastableExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<castable expr>");
    result_ = CastExpr(builder_, level_ + 1);
    result_ = result_ && CastableExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), CASTABLE_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(CASTABLE_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ( "castable" "as" SingleType )?
  private static boolean CastableExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastableExpr_1")) return false;
    CastableExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // "castable" "as" SingleType
  private static boolean CastableExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastableExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_CASTABLE);
    result_ = result_ && consumeToken(builder_, K_AS);
    result_ = result_ && SingleType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "catch" CatchErrorList "{" Expr "}"
  public static boolean CatchClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CatchClause")) return false;
    if (!nextTokenIs(builder_, K_CATCH)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_CATCH);
    result_ = result_ && CatchErrorList(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(CATCH_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // NameTest ("|" NameTest)*
  public static boolean CatchErrorList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CatchErrorList")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<catch error list>");
    result_ = NameTest(builder_, level_ + 1);
    result_ = result_ && CatchErrorList_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(CATCH_ERROR_LIST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ("|" NameTest)*
  private static boolean CatchErrorList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CatchErrorList_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!CatchErrorList_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "CatchErrorList_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "|" NameTest
  private static boolean CatchErrorList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CatchErrorList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PIPE);
    result_ = result_ && NameTest(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "comment" "(" ")"
  public static boolean CommentTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommentTest")) return false;
    if (!nextTokenIs(builder_, K_COMMENT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_COMMENT);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (result_ || pinned_) {
      marker_.done(COMMENT_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // PredefinedEntityRef | CharRef | "{{" | "}}" | EnclosedExpr
  public static boolean CommonContent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommonContent")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<common content>");
    result_ = consumeToken(builder_, PREDEFINEDENTITYREF);
    if (!result_) result_ = consumeToken(builder_, CHARREF);
    if (!result_) result_ = consumeToken(builder_, DBL_L_C_BRACE);
    if (!result_) result_ = consumeToken(builder_, DBL_R_C_BRACE);
    if (!result_) result_ = EnclosedExpr(builder_, level_ + 1);
    if (result_) {
      marker_.done(COMMON_CONTENT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "attribute" (EQName | ("{" Expr "}")) "{" Expr? "}"
  public static boolean CompAttrConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompAttrConstructor")) return false;
    if (!nextTokenIs(builder_, K_ATTRIBUTE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ATTRIBUTE);
    result_ = result_ && CompAttrConstructor_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && CompAttrConstructor_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(COMP_ATTR_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // EQName | ("{" Expr "}")
  private static boolean CompAttrConstructor_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompAttrConstructor_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = EQName(builder_, level_ + 1);
    if (!result_) result_ = CompAttrConstructor_1_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "{" Expr "}"
  private static boolean CompAttrConstructor_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompAttrConstructor_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_COMMENT);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(COMP_COMMENT_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "document" "{" Expr "}"
  public static boolean CompDocConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompDocConstructor")) return false;
    if (!nextTokenIs(builder_, K_DOCUMENT)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_DOCUMENT);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(COMP_DOC_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "element" (EQName | ("{" Expr "}")) "{" ContentExpr? "}"
  public static boolean CompElemConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompElemConstructor")) return false;
    if (!nextTokenIs(builder_, K_ELEMENT)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ELEMENT);
    result_ = result_ && CompElemConstructor_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && CompElemConstructor_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(COMP_ELEM_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // EQName | ("{" Expr "}")
  private static boolean CompElemConstructor_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompElemConstructor_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = EQName(builder_, level_ + 1);
    if (!result_) result_ = CompElemConstructor_1_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "{" Expr "}"
  private static boolean CompElemConstructor_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompElemConstructor_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_MAP);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && CompMapConstructor_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(COMP_MAP_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_NAMESPACE);
    result_ = result_ && CompNamespaceConstructor_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && URIExpr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(COMP_NAMESPACE_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // Prefix | ("{" PrefixExpr "}")
  private static boolean CompNamespaceConstructor_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompNamespaceConstructor_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = Prefix(builder_, level_ + 1);
    if (!result_) result_ = CompNamespaceConstructor_1_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "{" PrefixExpr "}"
  private static boolean CompNamespaceConstructor_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompNamespaceConstructor_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, L_C_BRACE);
    result_ = result_ && PrefixExpr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "processing-instruction" (NCName | ("{" Expr "}")) "{" Expr? "}"
  public static boolean CompPIConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompPIConstructor")) return false;
    if (!nextTokenIs(builder_, K_PI)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_PI);
    result_ = result_ && CompPIConstructor_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && CompPIConstructor_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(COMP_PI_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // NCName | ("{" Expr "}")
  private static boolean CompPIConstructor_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompPIConstructor_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    if (!result_) result_ = CompPIConstructor_1_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "{" Expr "}"
  private static boolean CompPIConstructor_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompPIConstructor_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_TEXT);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(COMP_TEXT_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // StringConcatExpr ( (ValueComp
  //  | GeneralComp
  //  | NodeComp) StringConcatExpr )?
  public static boolean ComparisonExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComparisonExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<comparison expr>");
    result_ = StringConcatExpr(builder_, level_ + 1);
    result_ = result_ && ComparisonExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), COMPARISON_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(COMPARISON_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ( (ValueComp
  //  | GeneralComp
  //  | NodeComp) StringConcatExpr )?
  private static boolean ComparisonExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComparisonExpr_1")) return false;
    ComparisonExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // (ValueComp
  //  | GeneralComp
  //  | NodeComp) StringConcatExpr
  private static boolean ComparisonExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComparisonExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ComparisonExpr_1_0_0(builder_, level_ + 1);
    result_ = result_ && StringConcatExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ValueComp
  //  | GeneralComp
  //  | NodeComp
  private static boolean ComparisonExpr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ComparisonExpr_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ValueComp(builder_, level_ + 1);
    if (!result_) result_ = GeneralComp(builder_, level_ + 1);
    if (!result_) result_ = NodeComp(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<computed constructor>");
    result_ = CompDocConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompElemConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompMapConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompAttrConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompNamespaceConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompTextConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompCommentConstructor(builder_, level_ + 1);
    if (!result_) result_ = CompPIConstructor(builder_, level_ + 1);
    if (result_) {
      marker_.done(COMPUTED_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "construction" ("strip" | "preserve") Separator
  public static boolean ConstructionDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ConstructionDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_CONSTRUCTION);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, ConstructionDecl_2(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(CONSTRUCTION_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // "strip" | "preserve"
  private static boolean ConstructionDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ConstructionDecl_2")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_STRIP);
    if (!result_) result_ = consumeToken(builder_, K_PRESERVE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // DirectConstructor
  //  | ComputedConstructor
  public static boolean Constructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Constructor")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<constructor>");
    result_ = DirectConstructor(builder_, level_ + 1);
    if (!result_) result_ = ComputedConstructor(builder_, level_ + 1);
    if (result_) {
      marker_.done(CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // Expr
  public static boolean ContentExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContentExpr")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<content expr>");
    result_ = Expr(builder_, level_ + 1);
    if (result_) {
      marker_.done(CONTENT_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "context" "item" ("as" ItemType)? ((":=" VarValue) | ("external" (":=" VarDefaultValue)?)) Separator
  public static boolean ContextItemDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_CONTEXT);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_ITEM));
    result_ = pinned_ && report_error_(builder_, ContextItemDecl_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, ContextItemDecl_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(CONTEXT_ITEM_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_AS);
    result_ = result_ && ItemType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (":=" VarValue) | ("external" (":=" VarDefaultValue)?)
  private static boolean ContextItemDecl_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl_4")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ContextItemDecl_4_0(builder_, level_ + 1);
    if (!result_) result_ = ContextItemDecl_4_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ":=" VarValue
  private static boolean ContextItemDecl_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && VarValue(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "external" (":=" VarDefaultValue)?
  private static boolean ContextItemDecl_4_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemDecl_4_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_EXTERNAL);
    result_ = result_ && ContextItemDecl_4_1_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && VarDefaultValue(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "."
  public static boolean ContextItemExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContextItemExpr")) return false;
    if (!nextTokenIs(builder_, DOT)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOT);
    if (result_) {
      marker_.done(CONTEXT_ITEM_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "declare" "copy-namespaces" PreserveMode "," InheritMode Separator
  public static boolean CopyNamespacesDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CopyNamespacesDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_COPY_NAMESPACES);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, PreserveMode(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, COMMA)) && result_;
    result_ = pinned_ && report_error_(builder_, InheritMode(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(COPY_NAMESPACES_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "count" "$" VarName
  public static boolean CountClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CountClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<count clause>");
    result_ = consumeToken(builder_, K_COUNT);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, DOLLAR_SIGN));
    result_ = pinned_ && VarName(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(COUNT_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean CurrentItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CurrentItem")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<current item>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<current item>");
    result_ = EQName(builder_, level_ + 1);
    if (result_) {
      marker_.done(CURRENT_ITEM);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "decimal-separator" | "grouping-separator" | "infinity" | "minus-sign" | "NaN" | "percent" | "per-mille" | "zero-digit" | "digit" | "pattern-separator"
  public static boolean DFPropertyName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DFPropertyName")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<df property name>");
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
    if (result_) {
      marker_.done(DF_PROPERTY_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" (("decimal-format" EQName) | ("default" "decimal-format")) (DFPropertyName "=" StringLiteral)* Separator
  public static boolean DecimalFormatDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && DecimalFormatDecl_1(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, DecimalFormatDecl_2(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(DECIMAL_FORMAT_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // ("decimal-format" EQName) | ("default" "decimal-format")
  private static boolean DecimalFormatDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = DecimalFormatDecl_1_0(builder_, level_ + 1);
    if (!result_) result_ = DecimalFormatDecl_1_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "decimal-format" EQName
  private static boolean DecimalFormatDecl_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_DECIMAL_FORMAT);
    result_ = result_ && EQName(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "default" "decimal-format"
  private static boolean DecimalFormatDecl_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_DECIMAL_FORMAT);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (DFPropertyName "=" StringLiteral)*
  private static boolean DecimalFormatDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_2")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!DecimalFormatDecl_2_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "DecimalFormatDecl_2");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // DFPropertyName "=" StringLiteral
  private static boolean DecimalFormatDecl_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecimalFormatDecl_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = DFPropertyName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUAL);
    result_ = result_ && consumeToken(builder_, STRINGLITERAL);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "declare" "default" "collation" URILiteral Separator
  public static boolean DefaultCollationDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultCollationDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_COLLATION);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, URILiteral(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(DEFAULT_COLLATION_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "declare" "default" "element" "namespace" URILiteral
  public static boolean DefaultElementNamespaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultElementNamespaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_ELEMENT);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_NAMESPACE));
    result_ = pinned_ && URILiteral(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(DEFAULT_ELEMENT_NAMESPACE_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "declare" "default" "function" "namespace" URILiteral
  public static boolean DefaultFunctionNamespaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultFunctionNamespaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_FUNCTION);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_NAMESPACE));
    result_ = pinned_ && URILiteral(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(DEFAULT_FUNCTION_NAMESPACE_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // (DefaultFunctionNamespaceDecl | DefaultElementNamespaceDecl) Separator
  public static boolean DefaultNamespaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultNamespaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = DefaultNamespaceDecl_0(builder_, level_ + 1);
    result_ = result_ && Separator(builder_, level_ + 1);
    if (result_) {
      marker_.done(DEFAULT_NAMESPACE_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // DefaultFunctionNamespaceDecl | DefaultElementNamespaceDecl
  private static boolean DefaultNamespaceDecl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DefaultNamespaceDecl_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = DefaultFunctionNamespaceDecl(builder_, level_ + 1);
    if (!result_) result_ = DefaultElementNamespaceDecl(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // (DirAttributeName "=" DirAttributeValue)*
  public static boolean DirAttributeList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeList")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<dir attribute list>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!DirAttributeList_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "DirAttributeList");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(DIR_ATTRIBUTE_LIST);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  // DirAttributeName "=" DirAttributeValue
  private static boolean DirAttributeList_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeList_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = DirAttributeName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUAL);
    result_ = result_ && DirAttributeValue(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // QName
  public static boolean DirAttributeName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = QName(builder_, level_ + 1);
    if (result_) {
      marker_.done(DIR_ATTRIBUTE_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // ("\"" (EscapeQuot | QuotAttrValueContent)* "\"")
  //  | ("'" (EscapeApos | AposAttrValueContent)* "'")
  public static boolean DirAttributeValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue")) return false;
    if (!nextTokenIs(builder_, APOSTROPHE) && !nextTokenIs(builder_, QUOT)
        && replaceVariants(builder_, 2, "<dir attribute value>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<dir attribute value>");
    result_ = DirAttributeValue_0(builder_, level_ + 1);
    if (!result_) result_ = DirAttributeValue_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(DIR_ATTRIBUTE_VALUE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // "\"" (EscapeQuot | QuotAttrValueContent)* "\""
  private static boolean DirAttributeValue_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, QUOT);
    result_ = result_ && DirAttributeValue_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, QUOT);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (EscapeQuot | QuotAttrValueContent)*
  private static boolean DirAttributeValue_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_0_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!DirAttributeValue_0_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "DirAttributeValue_0_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // EscapeQuot | QuotAttrValueContent
  private static boolean DirAttributeValue_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = EscapeQuot(builder_, level_ + 1);
    if (!result_) result_ = QuotAttrValueContent(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "'" (EscapeApos | AposAttrValueContent)* "'"
  private static boolean DirAttributeValue_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, APOSTROPHE);
    result_ = result_ && DirAttributeValue_1_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, APOSTROPHE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (EscapeApos | AposAttrValueContent)*
  private static boolean DirAttributeValue_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_1_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!DirAttributeValue_1_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "DirAttributeValue_1_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // EscapeApos | AposAttrValueContent
  private static boolean DirAttributeValue_1_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirAttributeValue_1_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = EscapeApos(builder_, level_ + 1);
    if (!result_) result_ = AposAttrValueContent(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "<!--" DirCommentContents "-->"
  public static boolean DirCommentConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirCommentConstructor")) return false;
    if (!nextTokenIs(builder_, DIR_COMMENT_BEGIN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DIR_COMMENT_BEGIN);
    result_ = result_ && DirCommentContents(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DIR_COMMENT_END);
    if (result_) {
      marker_.done(DIR_COMMENT_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // DirCommentChar*
  public static boolean DirCommentContents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirCommentContents")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<dir comment contents>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!consumeToken(builder_, DIRCOMMENTCHAR)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "DirCommentContents");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(DIR_COMMENT_CONTENTS);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  /* ********************************************************** */
  // "<" TagName DirAttributeList? ("/>" | (">" DirElemContent* "</" TagName ">"))
  public static boolean DirElemConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirElemConstructor")) return false;
    if (!nextTokenIs(builder_, LT_CHAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, LT_CHAR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, TagName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, DirElemConstructor_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && DirElemConstructor_3(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(DIR_ELEM_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // DirAttributeList?
  private static boolean DirElemConstructor_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirElemConstructor_2")) return false;
    DirAttributeList(builder_, level_ + 1);
    return true;
  }

  // "/>" | (">" DirElemContent* "</" TagName ">")
  private static boolean DirElemConstructor_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirElemConstructor_3")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, CLOSE_TAG);
    if (!result_) result_ = DirElemConstructor_3_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ">" DirElemContent* "</" TagName ">"
  private static boolean DirElemConstructor_3_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirElemConstructor_3_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, GT_CHAR);
    result_ = result_ && DirElemConstructor_3_1_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END_TAG);
    result_ = result_ && TagName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, GT_CHAR);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // DirElemContent*
  private static boolean DirElemConstructor_3_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirElemConstructor_3_1_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!DirElemContent(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "DirElemConstructor_3_1_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // DirectConstructor
  //  | CDataSection
  //  | CommonContent
  //  | ElementContentChar
  public static boolean DirElemContent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirElemContent")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<dir elem content>");
    result_ = DirectConstructor(builder_, level_ + 1);
    if (!result_) result_ = CDataSection(builder_, level_ + 1);
    if (!result_) result_ = CommonContent(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ELEMENTCONTENTCHAR);
    if (result_) {
      marker_.done(DIR_ELEM_CONTENT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "<?" PITarget (S DirPIContents)? "?>"
  public static boolean DirPIConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirPIConstructor")) return false;
    if (!nextTokenIs(builder_, PI_BEGIN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PI_BEGIN);
    result_ = result_ && consumeToken(builder_, PITARGET);
    result_ = result_ && DirPIConstructor_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PI_END);
    if (result_) {
      marker_.done(DIR_PI_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, S);
    result_ = result_ && DirPIContents(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // DirPIContentChar*
  public static boolean DirPIContents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirPIContents")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<dir pi contents>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!consumeToken(builder_, DIRPICONTENTCHAR)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "DirPIContents");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(DIR_PI_CONTENTS);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  /* ********************************************************** */
  // DirElemConstructor
  //  | DirCommentConstructor
  //  | DirPIConstructor
  public static boolean DirectConstructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DirectConstructor")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<direct constructor>");
    result_ = DirElemConstructor(builder_, level_ + 1);
    if (!result_) result_ = DirCommentConstructor(builder_, level_ + 1);
    if (!result_) result_ = DirPIConstructor(builder_, level_ + 1);
    if (result_) {
      marker_.done(DIRECT_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "document-node" "(" (ElementTest | SchemaElementTest)? ")"
  public static boolean DocumentTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DocumentTest")) return false;
    if (!nextTokenIs(builder_, K_DOCUMENT_NODE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DOCUMENT_NODE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, DocumentTest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    if (result_ || pinned_) {
      marker_.done(DOCUMENT_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = ElementTest(builder_, level_ + 1);
    if (!result_) result_ = SchemaElementTest(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // QName | URIQualifiedName
  static boolean EQName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EQName")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = QName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, URIQUALIFIEDNAME);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ElementName
  public static boolean ElementDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementDeclaration")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<element declaration>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<element declaration>");
    result_ = ElementName(builder_, level_ + 1);
    if (result_) {
      marker_.done(ELEMENT_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean ElementName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementName")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<element name>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<element name>");
    result_ = EQName(builder_, level_ + 1);
    if (result_) {
      marker_.done(ELEMENT_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // ElementName | "*"
  public static boolean ElementNameOrWildcard(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementNameOrWildcard")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<element name or wildcard>");
    result_ = ElementName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STAR_SIGN);
    if (result_) {
      marker_.done(ELEMENT_NAME_OR_WILDCARD);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "element" "(" (ElementNameOrWildcard ("," TypeName "?"?)?)? ")"
  public static boolean ElementTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElementTest")) return false;
    if (!nextTokenIs(builder_, K_ELEMENT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_ELEMENT);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, ElementTest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    if (result_ || pinned_) {
      marker_.done(ELEMENT_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = ElementNameOrWildcard(builder_, level_ + 1);
    result_ = result_ && ElementTest_2_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && TypeName(builder_, level_ + 1);
    result_ = result_ && ElementTest_2_0_1_0_2(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_ORDER);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_EMPTY));
    result_ = pinned_ && report_error_(builder_, EmptyOrderDecl_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(EMPTY_ORDER_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // "greatest" | "least"
  private static boolean EmptyOrderDecl_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmptyOrderDecl_4")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_GREATEST);
    if (!result_) result_ = consumeToken(builder_, K_LEAST);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "{" Expr "}"
  public static boolean EnclosedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnclosedExpr")) return false;
    if (!nextTokenIs(builder_, L_C_BRACE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, L_C_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Expr(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_C_BRACE) && result_;
    if (result_ || pinned_) {
      marker_.done(ENCLOSED_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "''"
  public static boolean EscapeApos(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EscapeApos")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<escape apos>");
    result_ = consumeToken(builder_, "''");
    if (result_) {
      marker_.done(ESCAPE_APOS);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "\"\""
  public static boolean EscapeQuot(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EscapeQuot")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<escape quot>");
    result_ = consumeToken(builder_, "\"\"");
    if (result_) {
      marker_.done(ESCAPE_QUOT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle ExprSingleAfterComma*
  public static boolean Expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Expr")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<expr>");
    result_ = ExprSingle(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && Expr_1(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // ExprSingleAfterComma*
  private static boolean Expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Expr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!ExprSingleAfterComma(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "Expr_1");
        break;
      }
      offset_ = next_offset_;
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
  //  | OrExpr
  public static boolean ExprSingle(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprSingle")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<expr single>");
    result_ = FLWORExpr(builder_, level_ + 1);
    if (!result_) result_ = QuantifiedExpr(builder_, level_ + 1);
    if (!result_) result_ = SwitchExpr(builder_, level_ + 1);
    if (!result_) result_ = TypeswitchExpr(builder_, level_ + 1);
    if (!result_) result_ = IfExpr(builder_, level_ + 1);
    if (!result_) result_ = TryCatchExpr(builder_, level_ + 1);
    if (!result_) result_ = OrExpr(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), EXPR_SINGLE)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(EXPR_SINGLE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "," ExprSingle
  static boolean ExprSingleAfterComma(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprSingleAfterComma")) return false;
    if (!nextTokenIs(builder_, COMMA)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, COMMA);
    pinned_ = result_; // pin = 1
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (!result_ && !pinned_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Pragma+ "{" Expr? "}"
  public static boolean ExtensionExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExtensionExpr")) return false;
    if (!nextTokenIs(builder_, PRAGMA_BEGIN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ExtensionExpr_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && ExtensionExpr_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(EXTENSION_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // Pragma+
  private static boolean ExtensionExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExtensionExpr_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = Pragma(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!Pragma(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "ExtensionExpr_0");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_EXTERNAL);
    result_ = result_ && ExternalVarPart_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(EXTERNAL_VAR_PART);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && VarDefaultValue(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // InitialClause IntermediateClause* ReturnClause
  public static boolean FLWORExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FLWORExpr")) return false;
    if (!nextTokenIs(builder_, K_FOR) && !nextTokenIs(builder_, K_LET)
        && replaceVariants(builder_, 2, "<flwor expr>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<flwor expr>");
    result_ = InitialClause(builder_, level_ + 1);
    result_ = result_ && FLWORExpr_1(builder_, level_ + 1);
    result_ = result_ && ReturnClause(builder_, level_ + 1);
    if (result_) {
      marker_.done(FLWOR_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // IntermediateClause*
  private static boolean FLWORExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FLWORExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!IntermediateClause(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "FLWORExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // !('count' | 'for' | 'group' | 'let' | 'order' | 'return' | 'stable' | 'where')
  static boolean FLWORExprRecover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FLWORExprRecover")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_NOT_, null);
    result_ = !FLWORExprRecover_0(builder_, level_ + 1);
    marker_.rollbackTo();
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_NOT_, null);
    return result_;
  }

  // 'count' | 'for' | 'group' | 'let' | 'order' | 'return' | 'stable' | 'where'
  private static boolean FLWORExprRecover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FLWORExprRecover_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_COUNT);
    if (!result_) result_ = consumeToken(builder_, K_FOR);
    if (!result_) result_ = consumeToken(builder_, K_GROUP);
    if (!result_) result_ = consumeToken(builder_, K_LET);
    if (!result_) result_ = consumeToken(builder_, K_ORDER);
    if (!result_) result_ = consumeToken(builder_, K_RETURN);
    if (!result_) result_ = consumeToken(builder_, K_STABLE);
    if (!result_) result_ = consumeToken(builder_, K_WHERE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // DefaultNamespaceDecl | Setter | NamespaceDecl | Import
  static boolean FirstDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FirstDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE) && !nextTokenIs(builder_, K_IMPORT)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = DefaultNamespaceDecl(builder_, level_ + 1);
    if (!result_) result_ = Setter(builder_, level_ + 1);
    if (!result_) result_ = NamespaceDecl(builder_, level_ + 1);
    if (!result_) result_ = Import(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "$" VarName TypeDeclaration? AllowingEmpty? PositionalVar? "in" ExprSingle
  public static boolean ForBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForBinding")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && ForBinding_2(builder_, level_ + 1);
    result_ = result_ && ForBinding_3(builder_, level_ + 1);
    result_ = result_ && ForBinding_4(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_IN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (result_) {
      marker_.done(FOR_BINDING);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // TypeDeclaration?
  private static boolean ForBinding_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForBinding_2")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  // AllowingEmpty?
  private static boolean ForBinding_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForBinding_3")) return false;
    AllowingEmpty(builder_, level_ + 1);
    return true;
  }

  // PositionalVar?
  private static boolean ForBinding_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForBinding_4")) return false;
    PositionalVar(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "for" ForBinding ("," ForBinding)*
  public static boolean ForClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<for clause>");
    result_ = consumeToken(builder_, K_FOR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ForBinding(builder_, level_ + 1));
    result_ = pinned_ && ForClause_2(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(FOR_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  // ("," ForBinding)*
  private static boolean ForClause_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForClause_2")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!ForClause_2_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "ForClause_2");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," ForBinding
  private static boolean ForClause_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForClause_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && ForBinding(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = ForwardAxis_0(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_1(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_2(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_3(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_4(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_5(builder_, level_ + 1);
    if (!result_) result_ = ForwardAxis_6(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "child" "::"
  private static boolean ForwardAxis_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_CHILD);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "descendant" "::"
  private static boolean ForwardAxis_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_DESCENDANT);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "attribute" "::"
  private static boolean ForwardAxis_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_2")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ATTRIBUTE);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "self" "::"
  private static boolean ForwardAxis_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_3")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_SELF);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "descendant-or-self" "::"
  private static boolean ForwardAxis_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_4")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_DESCENDANT_OR_SELF);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "following-sibling" "::"
  private static boolean ForwardAxis_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_5")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_FOLLOWING_SIBLING);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "following" "::"
  private static boolean ForwardAxis_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardAxis_6")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_FOLLOWING);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // (ForwardAxis NodeTest) | AbbrevForwardStep
  public static boolean ForwardStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardStep")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<forward step>");
    result_ = ForwardStep_0(builder_, level_ + 1);
    if (!result_) result_ = AbbrevForwardStep(builder_, level_ + 1);
    if (result_) {
      marker_.done(FORWARD_STEP);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ForwardAxis NodeTest
  private static boolean ForwardStep_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForwardStep_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ForwardAxis(builder_, level_ + 1);
    result_ = result_ && NodeTest(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // IntegerLiteral
  public static boolean FunctionArity(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionArity")) return false;
    if (!nextTokenIs(builder_, INTEGERLITERAL)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, INTEGERLITERAL);
    if (result_) {
      marker_.done(FUNCTION_ARITY);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // EnclosedExpr
  public static boolean FunctionBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionBody")) return false;
    if (!nextTokenIs(builder_, L_C_BRACE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = EnclosedExpr(builder_, level_ + 1);
    if (result_) {
      marker_.done(FUNCTION_BODY);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // FunctionName ArgumentList
  public static boolean FunctionCall(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionCall")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<function call>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<function call>");
    result_ = FunctionName(builder_, level_ + 1);
    result_ = result_ && ArgumentList(builder_, level_ + 1);
    if (result_) {
      marker_.done(FUNCTION_CALL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" Annotation* "function" FunctionName ParamList ("as" SequenceType)? (FunctionBody | "external")
  public static boolean FunctionDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDecl")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<function decl>");
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && FunctionDecl_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_FUNCTION);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, FunctionName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, ParamList(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, FunctionDecl_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && FunctionDecl_6(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(FUNCTION_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, FunctionDeclRec_parser_);
    return result_ || pinned_;
  }

  // Annotation*
  private static boolean FunctionDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDecl_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!Annotation(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "FunctionDecl_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_AS);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // FunctionBody | "external"
  private static boolean FunctionDecl_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDecl_6")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = FunctionBody(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, K_EXTERNAL);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // !(';')
  static boolean FunctionDeclRec(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDeclRec")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_NOT_, null);
    result_ = !FunctionDeclRec_0(builder_, level_ + 1);
    marker_.rollbackTo();
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_NOT_, null);
    return result_;
  }

  // (';')
  private static boolean FunctionDeclRec_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionDeclRec_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SEMICOLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // NamedFunctionRef | InlineFunctionExpr
  public static boolean FunctionItemExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionItemExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<function item expr>");
    result_ = NamedFunctionRef(builder_, level_ + 1);
    if (!result_) result_ = InlineFunctionExpr(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), FUNCTION_ITEM_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(FUNCTION_ITEM_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean FunctionLocalName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionLocalName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    if (result_) {
      marker_.done(FUNCTION_LOCAL_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // FunctionNamespace ':' FunctionLocalName | FunctionLocalName | URIQualifiedName
  public static boolean FunctionName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionName")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<function name>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<function name>");
    result_ = FunctionName_0(builder_, level_ + 1);
    if (!result_) result_ = FunctionLocalName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, URIQUALIFIEDNAME);
    if (result_) {
      marker_.done(FUNCTION_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // FunctionNamespace ':' FunctionLocalName
  private static boolean FunctionName_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionName_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = FunctionNamespace(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && FunctionLocalName(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean FunctionNamespace(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionNamespace")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    if (result_) {
      marker_.done(FUNCTION_NAMESPACE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // Annotation* (AnyFunctionTest
  //  | TypedFunctionTest)
  public static boolean FunctionTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionTest")) return false;
    if (!nextTokenIs(builder_, PERCENT) && !nextTokenIs(builder_, K_FUNCTION)
        && replaceVariants(builder_, 2, "<function test>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<function test>");
    result_ = FunctionTest_0(builder_, level_ + 1);
    result_ = result_ && FunctionTest_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(FUNCTION_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // Annotation*
  private static boolean FunctionTest_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionTest_0")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!Annotation(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "FunctionTest_0");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // AnyFunctionTest
  //  | TypedFunctionTest
  private static boolean FunctionTest_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionTest_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = AnyFunctionTest(builder_, level_ + 1);
    if (!result_) result_ = TypedFunctionTest(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "=" | "!=" | "<" | "<=" | ">" | ">="
  static boolean GeneralComp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GeneralComp")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, EQUAL);
    if (!result_) result_ = consumeToken(builder_, NOT_EQUAL);
    if (!result_) result_ = consumeToken(builder_, LT_CHAR);
    if (!result_) result_ = consumeToken(builder_, LE_CHARS);
    if (!result_) result_ = consumeToken(builder_, GT_CHAR);
    if (!result_) result_ = consumeToken(builder_, GE_CHARS);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "item" "(" ")"
  public static boolean GeneralItemType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GeneralItemType")) return false;
    if (!nextTokenIs(builder_, K_ITEM)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_ITEM);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (result_ || pinned_) {
      marker_.done(GENERAL_ITEM_TYPE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "group" "by" GroupingSpecList
  public static boolean GroupByClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupByClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<group by clause>");
    result_ = consumeToken(builder_, K_GROUP);
    result_ = result_ && consumeToken(builder_, K_BY);
    pinned_ = result_; // pin = 2
    result_ = result_ && GroupingSpecList(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(GROUP_BY_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // GroupingVariable (TypeDeclaration? ":=" ExprSingle)? ("collation" URILiteral)?
  public static boolean GroupingSpec(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpec")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = GroupingVariable(builder_, level_ + 1);
    result_ = result_ && GroupingSpec_1(builder_, level_ + 1);
    result_ = result_ && GroupingSpec_2(builder_, level_ + 1);
    if (result_) {
      marker_.done(GROUPING_SPEC);
    }
    else {
      marker_.rollbackTo();
    }
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
    Marker marker_ = builder_.mark();
    result_ = GroupingSpec_1_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_COLLATION);
    result_ = result_ && URILiteral(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // GroupingSpec ("," GroupingSpec)*
  public static boolean GroupingSpecList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpecList")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = GroupingSpec(builder_, level_ + 1);
    result_ = result_ && GroupingSpecList_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(GROUPING_SPEC_LIST);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // ("," GroupingSpec)*
  private static boolean GroupingSpecList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpecList_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!GroupingSpecList_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "GroupingSpecList_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," GroupingSpec
  private static boolean GroupingSpecList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingSpecList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && GroupingSpec(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "$" VarName
  public static boolean GroupingVariable(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GroupingVariable")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    if (result_) {
      marker_.done(GROUPING_VARIABLE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "if" "(" Expr ")" "then" ExprSingle "else" ExprSingle
  public static boolean IfExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfExpr")) return false;
    if (!nextTokenIs(builder_, K_IF)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_IF);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, L_PAR));
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, R_PAR)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, K_THEN)) && result_;
    result_ = pinned_ && report_error_(builder_, ExprSingle(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, K_ELSE)) && result_;
    result_ = pinned_ && ExprSingle(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(IF_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // SchemaImport | ModuleImport
  public static boolean Import(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Import")) return false;
    if (!nextTokenIs(builder_, K_IMPORT)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = SchemaImport(builder_, level_ + 1);
    if (!result_) result_ = ModuleImport(builder_, level_ + 1);
    if (result_) {
      marker_.done(IMPORT);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "inherit" | "no-inherit"
  public static boolean InheritMode(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InheritMode")) return false;
    if (!nextTokenIs(builder_, K_INHERIT) && !nextTokenIs(builder_, K_NO_INHERIT)
        && replaceVariants(builder_, 2, "<inherit mode>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<inherit mode>");
    result_ = consumeToken(builder_, K_INHERIT);
    if (!result_) result_ = consumeToken(builder_, K_NO_INHERIT);
    if (result_) {
      marker_.done(INHERIT_MODE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // ForClause | LetClause | WindowClause
  public static boolean InitialClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InitialClause")) return false;
    if (!nextTokenIs(builder_, K_FOR) && !nextTokenIs(builder_, K_LET)
        && replaceVariants(builder_, 2, "<initial clause>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<initial clause>");
    result_ = ForClause(builder_, level_ + 1);
    if (!result_) result_ = LetClause(builder_, level_ + 1);
    if (!result_) result_ = WindowClause(builder_, level_ + 1);
    if (result_) {
      marker_.done(INITIAL_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // Annotation* "function" ParamList ("as" SequenceType)? FunctionBody
  public static boolean InlineFunctionExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InlineFunctionExpr")) return false;
    if (!nextTokenIs(builder_, PERCENT) && !nextTokenIs(builder_, K_FUNCTION)
        && replaceVariants(builder_, 2, "<inline function expr>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<inline function expr>");
    result_ = InlineFunctionExpr_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_FUNCTION);
    result_ = result_ && ParamList(builder_, level_ + 1);
    result_ = result_ && InlineFunctionExpr_3(builder_, level_ + 1);
    result_ = result_ && FunctionBody(builder_, level_ + 1);
    if (result_) {
      marker_.done(INLINE_FUNCTION_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // Annotation*
  private static boolean InlineFunctionExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InlineFunctionExpr_0")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!Annotation(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "InlineFunctionExpr_0");
        break;
      }
      offset_ = next_offset_;
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_AS);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // TreatExpr ( "instance" "of" SequenceType )?
  public static boolean InstanceofExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InstanceofExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<instanceof expr>");
    result_ = TreatExpr(builder_, level_ + 1);
    result_ = result_ && InstanceofExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), INSTANCEOF_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(INSTANCEOF_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ( "instance" "of" SequenceType )?
  private static boolean InstanceofExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InstanceofExpr_1")) return false;
    InstanceofExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // "instance" "of" SequenceType
  private static boolean InstanceofExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InstanceofExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_INSTANCE);
    result_ = result_ && consumeToken(builder_, K_OF);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // InitialClause | WhereClause | GroupByClause | OrderByClause | CountClause
  public static boolean IntermediateClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntermediateClause")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<intermediate clause>");
    result_ = InitialClause(builder_, level_ + 1);
    if (!result_) result_ = WhereClause(builder_, level_ + 1);
    if (!result_) result_ = GroupByClause(builder_, level_ + 1);
    if (!result_) result_ = OrderByClause(builder_, level_ + 1);
    if (!result_) result_ = CountClause(builder_, level_ + 1);
    if (result_) {
      marker_.done(INTERMEDIATE_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // InstanceofExpr ( ("intersect" | "except") InstanceofExpr )*
  public static boolean IntersectExceptExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntersectExceptExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<intersect except expr>");
    result_ = InstanceofExpr(builder_, level_ + 1);
    result_ = result_ && IntersectExceptExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), INTERSECT_EXCEPT_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(INTERSECT_EXCEPT_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ( ("intersect" | "except") InstanceofExpr )*
  private static boolean IntersectExceptExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntersectExceptExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!IntersectExceptExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "IntersectExceptExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // ("intersect" | "except") InstanceofExpr
  private static boolean IntersectExceptExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntersectExceptExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = IntersectExceptExpr_1_0_0(builder_, level_ + 1);
    result_ = result_ && InstanceofExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "intersect" | "except"
  private static boolean IntersectExceptExpr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IntersectExceptExpr_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_INTERSECT);
    if (!result_) result_ = consumeToken(builder_, K_EXCEPT);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // KindTest | GeneralItemType | FunctionTest | AtomicOrUnionType | ParenthesizedItemType
  public static boolean ItemType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemType")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<item type>");
    result_ = KindTest(builder_, level_ + 1);
    if (!result_) result_ = GeneralItemType(builder_, level_ + 1);
    if (!result_) result_ = FunctionTest(builder_, level_ + 1);
    if (!result_) result_ = AtomicOrUnionType(builder_, level_ + 1);
    if (!result_) result_ = ParenthesizedItemType(builder_, level_ + 1);
    if (result_) {
      marker_.done(ITEM_TYPE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<kind test>");
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
    if (result_) {
      marker_.done(KIND_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "$" VarName TypeDeclaration? ":=" ExprSingle
  public static boolean LetBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetBinding")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && LetBinding_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (result_) {
      marker_.done(LET_BINDING);
    }
    else {
      marker_.rollbackTo();
    }
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
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<let clause>");
    result_ = consumeToken(builder_, K_LET);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, LetBinding(builder_, level_ + 1));
    result_ = pinned_ && LetClause_2(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(LET_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  // ("," LetBinding)*
  private static boolean LetClause_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetClause_2")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!LetClause_2_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "LetClause_2");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," LetBinding
  private static boolean LetClause_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetClause_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && LetBinding(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ModuleDecl Prolog
  public static boolean LibraryModule(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LibraryModule")) return false;
    if (!nextTokenIs(builder_, K_MODULE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = ModuleDecl(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && Prolog(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(LIBRARY_MODULE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // NumericLiteral | StringLiteral
  public static boolean Literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Literal")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<literal>");
    result_ = NumericLiteral(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRINGLITERAL);
    if (result_) {
      marker_.done(LITERAL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean LocalPart(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LocalPart")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    if (result_) {
      marker_.done(LOCAL_PART);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // Prolog QueryBody
  public static boolean MainModule(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MainModule")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<main module>");
    result_ = Prolog(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && QueryBody(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(MAIN_MODULE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ExprSingle ":=" ExprSingle
  public static boolean MapEntry(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapEntry")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<map entry>");
    result_ = ExprSingle(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (result_) {
      marker_.done(MAP_ENTRY);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // MapEntry ("," MapEntry)*
  public static boolean MapEntryList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapEntryList")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<map entry list>");
    result_ = MapEntry(builder_, level_ + 1);
    result_ = result_ && MapEntryList_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(MAP_ENTRY_LIST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ("," MapEntry)*
  private static boolean MapEntryList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapEntryList_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!MapEntryList_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "MapEntryList_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," MapEntry
  private static boolean MapEntryList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapEntryList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && MapEntry(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "map" "(" ("*"| (AtomicOrUnionType "," SequenceType)) ")"
  public static boolean MapTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapTest")) return false;
    if (!nextTokenIs(builder_, K_MAP)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_MAP);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, MapTest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    if (result_ || pinned_) {
      marker_.done(MAP_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // "*"| (AtomicOrUnionType "," SequenceType)
  private static boolean MapTest_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapTest_2")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, STAR_SIGN);
    if (!result_) result_ = MapTest_2_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // AtomicOrUnionType "," SequenceType
  private static boolean MapTest_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapTest_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = AtomicOrUnionType(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // VersionDecl? (LibraryModule | MainModule)
  public static boolean Module(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<module>");
    result_ = Module_0(builder_, level_ + 1);
    result_ = result_ && Module_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(MODULE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = LibraryModule(builder_, level_ + 1);
    if (!result_) result_ = MainModule(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "module" "namespace" NamespaceName "=" URILiteral Separator
  public static boolean ModuleDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleDecl")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<module decl>");
    result_ = consumeToken(builder_, K_MODULE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_NAMESPACE));
    result_ = pinned_ && report_error_(builder_, NamespaceName(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, EQUAL)) && result_;
    result_ = pinned_ && report_error_(builder_, URILiteral(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(MODULE_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, ModuleDeclRecover_parser_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !('declare' | 'import')
  static boolean ModuleDeclRecover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleDeclRecover")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_NOT_, null);
    result_ = !ModuleDeclRecover_0(builder_, level_ + 1);
    marker_.rollbackTo();
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_NOT_, null);
    return result_;
  }

  // 'declare' | 'import'
  private static boolean ModuleDeclRecover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleDeclRecover_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_DECLARE);
    if (!result_) result_ = consumeToken(builder_, K_IMPORT);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "import" "module" ("namespace" NamespaceName "=")? ModuleImportNamespace ("at" ModuleImportPath ("," ModuleImportPath)*)? Separator
  public static boolean ModuleImport(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport")) return false;
    if (!nextTokenIs(builder_, K_IMPORT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_IMPORT);
    result_ = result_ && consumeToken(builder_, K_MODULE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, ModuleImport_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, ModuleImportNamespace(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, ModuleImport_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(MODULE_IMPORT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // ("namespace" NamespaceName "=")?
  private static boolean ModuleImport_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_2")) return false;
    ModuleImport_2_0(builder_, level_ + 1);
    return true;
  }

  // "namespace" NamespaceName "="
  private static boolean ModuleImport_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_NAMESPACE);
    result_ = result_ && NamespaceName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUAL);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_AT);
    result_ = result_ && ModuleImportPath(builder_, level_ + 1);
    result_ = result_ && ModuleImport_4_0_2(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ("," ModuleImportPath)*
  private static boolean ModuleImport_4_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_4_0_2")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!ModuleImport_4_0_2_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "ModuleImport_4_0_2");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," ModuleImportPath
  private static boolean ModuleImport_4_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImport_4_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && ModuleImportPath(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ModuleImportPath
  public static boolean ModuleImportNamespace(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImportNamespace")) return false;
    if (!nextTokenIs(builder_, STRINGLITERAL)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ModuleImportPath(builder_, level_ + 1);
    if (result_) {
      marker_.done(MODULE_IMPORT_NAMESPACE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // URILiteral
  public static boolean ModuleImportPath(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleImportPath")) return false;
    if (!nextTokenIs(builder_, STRINGLITERAL)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = URILiteral(builder_, level_ + 1);
    if (result_) {
      marker_.done(MODULE_IMPORT_PATH);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // UnionExpr ( ("*" | "div" | "idiv" | "mod") UnionExpr )*
  public static boolean MultiplicativeExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiplicativeExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<multiplicative expr>");
    result_ = UnionExpr(builder_, level_ + 1);
    result_ = result_ && MultiplicativeExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), MULTIPLICATIVE_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(MULTIPLICATIVE_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ( ("*" | "div" | "idiv" | "mod") UnionExpr )*
  private static boolean MultiplicativeExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiplicativeExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!MultiplicativeExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "MultiplicativeExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // ("*" | "div" | "idiv" | "mod") UnionExpr
  private static boolean MultiplicativeExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiplicativeExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = MultiplicativeExpr_1_0_0(builder_, level_ + 1);
    result_ = result_ && UnionExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "*" | "div" | "idiv" | "mod"
  private static boolean MultiplicativeExpr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MultiplicativeExpr_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, STAR_SIGN);
    if (!result_) result_ = consumeToken(builder_, K_DIV);
    if (!result_) result_ = consumeToken(builder_, K_IDIV);
    if (!result_) result_ = consumeToken(builder_, K_MOD);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // Wildcard | EQName
  public static boolean NameTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NameTest")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<name test>");
    result_ = Wildcard(builder_, level_ + 1);
    if (!result_) result_ = EQName(builder_, level_ + 1);
    if (result_) {
      marker_.done(NAME_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // FunctionName "#" FunctionArity
  public static boolean NamedFunctionRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFunctionRef")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<named function ref>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<named function ref>");
    result_ = FunctionName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, HASH);
    result_ = result_ && FunctionArity(builder_, level_ + 1);
    if (result_) {
      marker_.done(NAMED_FUNCTION_REF);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "namespace" NamespaceName "=" URILiteral Separator
  public static boolean NamespaceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamespaceDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_NAMESPACE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, NamespaceName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, EQUAL)) && result_;
    result_ = pinned_ && report_error_(builder_, URILiteral(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(NAMESPACE_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean NamespaceName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamespaceName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    if (result_) {
      marker_.done(NAMESPACE_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "namespace-node" "(" ")"
  public static boolean NamespaceNodeTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamespaceNodeTest")) return false;
    if (!nextTokenIs(builder_, K_NAMESPACE_NODE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_NAMESPACE_NODE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (result_ || pinned_) {
      marker_.done(NAMESPACE_NODE_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean NextItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NextItem")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<next item>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<next item>");
    result_ = EQName(builder_, level_ + 1);
    if (result_) {
      marker_.done(NEXT_ITEM);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "is" | "<<" | ">>"
  static boolean NodeComp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NodeComp")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_IS);
    if (!result_) result_ = consumeToken(builder_, NODECOMP_LT);
    if (!result_) result_ = consumeToken(builder_, NODECOMP_GT);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // KindTest | NameTest
  public static boolean NodeTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NodeTest")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<node test>");
    result_ = KindTest(builder_, level_ + 1);
    if (!result_) result_ = NameTest(builder_, level_ + 1);
    if (result_) {
      marker_.done(NODE_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // IntegerLiteral | DecimalLiteral | DoubleLiteral
  public static boolean NumericLiteral(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NumericLiteral")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<numeric literal>");
    result_ = consumeToken(builder_, INTEGERLITERAL);
    if (!result_) result_ = consumeToken(builder_, DECIMALLITERAL);
    if (!result_) result_ = consumeToken(builder_, DOUBLELITERAL);
    if (result_) {
      marker_.done(NUMERIC_LITERAL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "?" | "*" | "+"
  public static boolean OccurrenceIndicator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OccurrenceIndicator")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<occurrence indicator>");
    result_ = consumeToken(builder_, QUESTIONMARK);
    if (!result_) result_ = consumeToken(builder_, STAR_SIGN);
    if (!result_) result_ = consumeToken(builder_, OP_PLUS);
    if (result_) {
      marker_.done(OCCURRENCE_INDICATOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" "option" EQName StringLiteral Separator
  public static boolean OptionDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OptionDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_OPTION);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, EQName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, STRINGLITERAL)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(OPTION_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // AndExpr (OrMultipliedExpr)*
  public static boolean OrExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<or expr>");
    result_ = AndExpr(builder_, level_ + 1);
    result_ = result_ && OrExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), OR_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(OR_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // (OrMultipliedExpr)*
  private static boolean OrExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!OrExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "OrExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // (OrMultipliedExpr)
  private static boolean OrExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = OrMultipliedExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "or" AndExpr
  static boolean OrMultipliedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrMultipliedExpr")) return false;
    if (!nextTokenIs(builder_, K_OR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_OR);
    pinned_ = result_; // pin = 1
    result_ = result_ && AndExpr(builder_, level_ + 1);
    if (!result_ && !pinned_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // (("order" "by") | ("stable" "order" "by")) OrderSpecList
  public static boolean OrderByClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderByClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<order by clause>");
    result_ = OrderByClause_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && OrderSpecList(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(ORDER_BY_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  // ("order" "by") | ("stable" "order" "by")
  private static boolean OrderByClause_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderByClause_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = OrderByClause_0_0(builder_, level_ + 1);
    if (!result_) result_ = OrderByClause_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "order" "by"
  private static boolean OrderByClause_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderByClause_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ORDER);
    result_ = result_ && consumeToken(builder_, K_BY);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "stable" "order" "by"
  private static boolean OrderByClause_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderByClause_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_STABLE);
    result_ = result_ && consumeToken(builder_, K_ORDER);
    result_ = result_ && consumeToken(builder_, K_BY);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ("ascending" | "descending")? ("empty" ("greatest" | "least"))? ("collation" URILiteral)?
  public static boolean OrderModifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<order modifier>");
    result_ = OrderModifier_0(builder_, level_ + 1);
    result_ = result_ && OrderModifier_1(builder_, level_ + 1);
    result_ = result_ && OrderModifier_2(builder_, level_ + 1);
    if (result_) {
      marker_.done(ORDER_MODIFIER);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ASCENDING);
    if (!result_) result_ = consumeToken(builder_, K_DESCENDING);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_EMPTY);
    result_ = result_ && OrderModifier_1_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "greatest" | "least"
  private static boolean OrderModifier_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderModifier_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_GREATEST);
    if (!result_) result_ = consumeToken(builder_, K_LEAST);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_COLLATION);
    result_ = result_ && URILiteral(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle OrderModifier
  public static boolean OrderSpec(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderSpec")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<order spec>");
    result_ = ExprSingle(builder_, level_ + 1);
    result_ = result_ && OrderModifier(builder_, level_ + 1);
    if (result_) {
      marker_.done(ORDER_SPEC);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // OrderSpec ("," OrderSpec)*
  public static boolean OrderSpecList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderSpecList")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<order spec list>");
    result_ = OrderSpec(builder_, level_ + 1);
    result_ = result_ && OrderSpecList_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(ORDER_SPEC_LIST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ("," OrderSpec)*
  private static boolean OrderSpecList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderSpecList_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!OrderSpecList_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "OrderSpecList_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," OrderSpec
  private static boolean OrderSpecList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderSpecList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && OrderSpec(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "ordered" "{" Expr "}"
  public static boolean OrderedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderedExpr")) return false;
    if (!nextTokenIs(builder_, K_ORDERED)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ORDERED);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(ORDERED_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "declare" "ordering" ("ordered" | "unordered") Separator
  public static boolean OrderingModeDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderingModeDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && consumeToken(builder_, K_ORDERING);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, OrderingModeDecl_2(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(ORDERING_MODE_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // "ordered" | "unordered"
  private static boolean OrderingModeDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrderingModeDecl_2")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ORDERED);
    if (!result_) result_ = consumeToken(builder_, K_UNORDERED);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "processing-instruction" "(" (NCName | StringLiteral)? ")"
  public static boolean PITest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PITest")) return false;
    if (!nextTokenIs(builder_, K_PI)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_PI);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, PITest_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    if (result_ || pinned_) {
      marker_.done(PI_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    if (!result_) result_ = consumeToken(builder_, STRINGLITERAL);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "$" VarName TypeDeclaration?
  public static boolean Param(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Param")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && Param_2(builder_, level_ + 1);
    if (result_) {
      marker_.done(PARAM);
    }
    else {
      marker_.rollbackTo();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, L_PAR);
    result_ = result_ && ParamList_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (result_) {
      marker_.done(PARAM_LIST);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
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
    Marker marker_ = builder_.mark();
    result_ = Param(builder_, level_ + 1);
    result_ = result_ && ParamList_1_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ("," Param)*
  private static boolean ParamList_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParamList_1_0_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!ParamList_1_0_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "ParamList_1_0_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," Param
  private static boolean ParamList_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParamList_1_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && Param(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "(" Expr? ")"
  public static boolean ParenthesizedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenthesizedExpr")) return false;
    if (!nextTokenIs(builder_, L_PAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ParenthesizedExpr_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    if (result_ || pinned_) {
      marker_.done(PARENTHESIZED_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, L_PAR);
    result_ = result_ && ItemType(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (result_) {
      marker_.done(PARENTHESIZED_ITEM_TYPE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // ("/" RelativePathExpr?)
  //  | ("//" RelativePathExpr)
  //  | RelativePathExpr
  public static boolean PathExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<path expr>");
    result_ = PathExpr_0(builder_, level_ + 1);
    if (!result_) result_ = PathExpr_1(builder_, level_ + 1);
    if (!result_) result_ = RelativePathExpr(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), PATH_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(PATH_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // "/" RelativePathExpr?
  private static boolean PathExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathExpr_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SLASH);
    result_ = result_ && PathExpr_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // RelativePathExpr?
  private static boolean PathExpr_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathExpr_0_1")) return false;
    RelativePathExpr(builder_, level_ + 1);
    return true;
  }

  // "//" RelativePathExpr
  private static boolean PathExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathExpr_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SLASH_SLASH);
    result_ = result_ && RelativePathExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "at" "$" VarName
  public static boolean PositionalVar(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PositionalVar")) return false;
    if (!nextTokenIs(builder_, K_AT)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_AT);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    if (result_) {
      marker_.done(POSITIONAL_VAR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // PrimaryExpr (Predicate | ArgumentList)*
  public static boolean PostfixExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PostfixExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<postfix expr>");
    result_ = PrimaryExpr(builder_, level_ + 1);
    result_ = result_ && PostfixExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), POSTFIX_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(POSTFIX_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // (Predicate | ArgumentList)*
  private static boolean PostfixExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PostfixExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!PostfixExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "PostfixExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // Predicate | ArgumentList
  private static boolean PostfixExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PostfixExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = Predicate(builder_, level_ + 1);
    if (!result_) result_ = ArgumentList(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "(#" S? EQName (S PragmaContents)? "#)"
  public static boolean Pragma(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pragma")) return false;
    if (!nextTokenIs(builder_, PRAGMA_BEGIN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PRAGMA_BEGIN);
    result_ = result_ && Pragma_1(builder_, level_ + 1);
    result_ = result_ && EQName(builder_, level_ + 1);
    result_ = result_ && Pragma_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PRAGMA_END);
    if (result_) {
      marker_.done(PRAGMA);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, S);
    result_ = result_ && PragmaContents(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // PragmaContentChar*
  public static boolean PragmaContents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PragmaContents")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<pragma contents>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!consumeToken(builder_, PRAGMACONTENTCHAR)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "PragmaContents");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(PRAGMA_CONTENTS);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  /* ********************************************************** */
  // "[" Expr "]"
  public static boolean Predicate(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Predicate")) return false;
    if (!nextTokenIs(builder_, L_BRACKET)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, L_BRACKET);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACKET);
    if (result_) {
      marker_.done(PREDICATE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // Predicate*
  public static boolean PredicateList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PredicateList")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<predicate list>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!Predicate(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "PredicateList");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(PREDICATE_LIST);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  /* ********************************************************** */
  // NCName
  public static boolean Prefix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Prefix")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    if (result_) {
      marker_.done(PREFIX);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // Expr
  public static boolean PrefixExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PrefixExpr")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<prefix expr>");
    result_ = Expr(builder_, level_ + 1);
    if (result_) {
      marker_.done(PREFIX_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // Prefix ':' LocalPart
  static boolean PrefixedName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PrefixedName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = Prefix(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && LocalPart(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "preserve" | "no-preserve"
  public static boolean PreserveMode(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PreserveMode")) return false;
    if (!nextTokenIs(builder_, K_NO_PRESERVE) && !nextTokenIs(builder_, K_PRESERVE)
        && replaceVariants(builder_, 2, "<preserve mode>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<preserve mode>");
    result_ = consumeToken(builder_, K_PRESERVE);
    if (!result_) result_ = consumeToken(builder_, K_NO_PRESERVE);
    if (result_) {
      marker_.done(PRESERVE_MODE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean PreviousItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PreviousItem")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<previous item>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<previous item>");
    result_ = EQName(builder_, level_ + 1);
    if (result_) {
      marker_.done(PREVIOUS_ITEM);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
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
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<primary expr>");
    result_ = Literal(builder_, level_ + 1);
    if (!result_) result_ = VarRef(builder_, level_ + 1);
    if (!result_) result_ = ParenthesizedExpr(builder_, level_ + 1);
    if (!result_) result_ = ContextItemExpr(builder_, level_ + 1);
    if (!result_) result_ = FunctionCall(builder_, level_ + 1);
    if (!result_) result_ = OrderedExpr(builder_, level_ + 1);
    if (!result_) result_ = UnorderedExpr(builder_, level_ + 1);
    if (!result_) result_ = Constructor(builder_, level_ + 1);
    if (!result_) result_ = FunctionItemExpr(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), PRIMARY_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(PRIMARY_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // FirstDecl* SecondDecl*
  public static boolean Prolog(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Prolog")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<prolog>");
    result_ = Prolog_0(builder_, level_ + 1);
    result_ = result_ && Prolog_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(PROLOG);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // FirstDecl*
  private static boolean Prolog_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Prolog_0")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!FirstDecl(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "Prolog_0");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // SecondDecl*
  private static boolean Prolog_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Prolog_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!SecondDecl(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "Prolog_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // PrefixedName
  // 			| UnprefixedName
  static boolean QName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = PrefixedName(builder_, level_ + 1);
    if (!result_) result_ = UnprefixedName(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ("some" | "every") "$" VarName TypeDeclaration? "in" ExprSingle ("," "$" VarName TypeDeclaration? "in" ExprSingle)* "satisfies" ExprSingle
  public static boolean QuantifiedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr")) return false;
    if (!nextTokenIs(builder_, K_EVERY) && !nextTokenIs(builder_, K_SOME)
        && replaceVariants(builder_, 2, "<quantified expr>")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<quantified expr>");
    result_ = QuantifiedExpr_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, DOLLAR_SIGN));
    result_ = pinned_ && report_error_(builder_, VarName(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, QuantifiedExpr_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, K_IN)) && result_;
    result_ = pinned_ && report_error_(builder_, ExprSingle(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, QuantifiedExpr_6(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, K_SATISFIES)) && result_;
    result_ = pinned_ && ExprSingle(builder_, level_ + 1) && result_;
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), QUANTIFIED_EXPR)) {
      marker_.drop();
    }
    else if (result_ || pinned_) {
      marker_.done(QUANTIFIED_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // "some" | "every"
  private static boolean QuantifiedExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_SOME);
    if (!result_) result_ = consumeToken(builder_, K_EVERY);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // TypeDeclaration?
  private static boolean QuantifiedExpr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr_3")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  // ("," "$" VarName TypeDeclaration? "in" ExprSingle)*
  private static boolean QuantifiedExpr_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr_6")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!QuantifiedExpr_6_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "QuantifiedExpr_6");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," "$" VarName TypeDeclaration? "in" ExprSingle
  private static boolean QuantifiedExpr_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr_6_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    result_ = result_ && QuantifiedExpr_6_0_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_IN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // TypeDeclaration?
  private static boolean QuantifiedExpr_6_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantifiedExpr_6_0_3")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // Expr
  public static boolean QueryBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QueryBody")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<query body>");
    result_ = Expr(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    if (result_ || pinned_) {
      marker_.done(QUERY_BODY);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Char
  public static boolean QuotAttrContentChar(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuotAttrContentChar")) return false;
    if (!nextTokenIs(builder_, CHAR)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, CHAR);
    if (result_) {
      marker_.done(QUOT_ATTR_CONTENT_CHAR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // QuotAttrContentChar
  //  | CommonContent
  public static boolean QuotAttrValueContent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuotAttrValueContent")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<quot attr value content>");
    result_ = QuotAttrContentChar(builder_, level_ + 1);
    if (!result_) result_ = CommonContent(builder_, level_ + 1);
    if (result_) {
      marker_.done(QUOT_ATTR_VALUE_CONTENT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // AdditiveExpr (RangeOptionalExpr)?
  public static boolean RangeExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RangeExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<range expr>");
    result_ = AdditiveExpr(builder_, level_ + 1);
    result_ = result_ && RangeExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), RANGE_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(RANGE_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // (RangeOptionalExpr)?
  private static boolean RangeExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RangeExpr_1")) return false;
    RangeExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // (RangeOptionalExpr)
  private static boolean RangeExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RangeExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = RangeOptionalExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "to" AdditiveExpr
  static boolean RangeOptionalExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RangeOptionalExpr")) return false;
    if (!nextTokenIs(builder_, K_TO)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_TO);
    pinned_ = result_; // pin = 1
    result_ = result_ && AdditiveExpr(builder_, level_ + 1);
    if (!result_ && !pinned_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // StepExpr (("/" | "//") StepExpr)*
  public static boolean RelativePathExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelativePathExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<relative path expr>");
    result_ = StepExpr(builder_, level_ + 1);
    result_ = result_ && RelativePathExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), RELATIVE_PATH_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(RELATIVE_PATH_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // (("/" | "//") StepExpr)*
  private static boolean RelativePathExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelativePathExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!RelativePathExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "RelativePathExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // ("/" | "//") StepExpr
  private static boolean RelativePathExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelativePathExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = RelativePathExpr_1_0_0(builder_, level_ + 1);
    result_ = result_ && StepExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "/" | "//"
  private static boolean RelativePathExpr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RelativePathExpr_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SLASH);
    if (!result_) result_ = consumeToken(builder_, SLASH_SLASH);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "return" ExprSingle
  public static boolean ReturnClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReturnClause")) return false;
    if (!nextTokenIs(builder_, K_RETURN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_RETURN);
    pinned_ = result_; // pin = 1
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(RETURN_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
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
    Marker marker_ = builder_.mark();
    result_ = ReverseAxis_0(builder_, level_ + 1);
    if (!result_) result_ = ReverseAxis_1(builder_, level_ + 1);
    if (!result_) result_ = ReverseAxis_2(builder_, level_ + 1);
    if (!result_) result_ = ReverseAxis_3(builder_, level_ + 1);
    if (!result_) result_ = ReverseAxis_4(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "parent" "::"
  private static boolean ReverseAxis_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_PARENT);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "ancestor" "::"
  private static boolean ReverseAxis_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ANCESTOR);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "preceding-sibling" "::"
  private static boolean ReverseAxis_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_2")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_PRECEDING_SIBLING);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "preceding" "::"
  private static boolean ReverseAxis_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_3")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_PRECEDING);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "ancestor-or-self" "::"
  private static boolean ReverseAxis_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseAxis_4")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ANCESTOR_OR_SELF);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // (ReverseAxis NodeTest) | AbbrevReverseStep
  public static boolean ReverseStep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseStep")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<reverse step>");
    result_ = ReverseStep_0(builder_, level_ + 1);
    if (!result_) result_ = AbbrevReverseStep(builder_, level_ + 1);
    if (result_) {
      marker_.done(REVERSE_STEP);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ReverseAxis NodeTest
  private static boolean ReverseStep_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReverseStep_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ReverseAxis(builder_, level_ + 1);
    result_ = result_ && NodeTest(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "schema-attribute" "(" AttributeDeclaration ")"
  public static boolean SchemaAttributeTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaAttributeTest")) return false;
    if (!nextTokenIs(builder_, K_SCHEMA_ATTRIBUTE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_SCHEMA_ATTRIBUTE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, AttributeDeclaration(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    if (result_ || pinned_) {
      marker_.done(SCHEMA_ATTRIBUTE_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "schema-element" "(" ElementDeclaration ")"
  public static boolean SchemaElementTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaElementTest")) return false;
    if (!nextTokenIs(builder_, K_SCHEMA_ELEMENT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_SCHEMA_ELEMENT);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, ElementDeclaration(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAR) && result_;
    if (result_ || pinned_) {
      marker_.done(SCHEMA_ELEMENT_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "import" "schema" (("namespace" NamespaceName "=") | ("default" "element" "namespace"))? URILiteral ("at" URILiteral ("," URILiteral)*)? Separator
  public static boolean SchemaImport(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport")) return false;
    if (!nextTokenIs(builder_, K_IMPORT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_IMPORT);
    result_ = result_ && consumeToken(builder_, K_SCHEMA);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, SchemaImport_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, URILiteral(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, SchemaImport_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(SCHEMA_IMPORT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // (("namespace" NamespaceName "=") | ("default" "element" "namespace"))?
  private static boolean SchemaImport_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_2")) return false;
    SchemaImport_2_0(builder_, level_ + 1);
    return true;
  }

  // ("namespace" NamespaceName "=") | ("default" "element" "namespace")
  private static boolean SchemaImport_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = SchemaImport_2_0_0(builder_, level_ + 1);
    if (!result_) result_ = SchemaImport_2_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "namespace" NamespaceName "="
  private static boolean SchemaImport_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_2_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_NAMESPACE);
    result_ = result_ && NamespaceName(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUAL);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "default" "element" "namespace"
  private static boolean SchemaImport_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_2_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_DEFAULT);
    result_ = result_ && consumeToken(builder_, K_ELEMENT);
    result_ = result_ && consumeToken(builder_, K_NAMESPACE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_AT);
    result_ = result_ && URILiteral(builder_, level_ + 1);
    result_ = result_ && SchemaImport_4_0_2(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ("," URILiteral)*
  private static boolean SchemaImport_4_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_4_0_2")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!SchemaImport_4_0_2_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "SchemaImport_4_0_2");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," URILiteral
  private static boolean SchemaImport_4_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaImport_4_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && URILiteral(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ContextItemDecl | OptionDecl | AnnotatedDecl
  static boolean SecondDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SecondDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ContextItemDecl(builder_, level_ + 1);
    if (!result_) result_ = OptionDecl(builder_, level_ + 1);
    if (!result_) result_ = AnnotatedDecl(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ";"
  public static boolean Separator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Separator")) return false;
    if (!nextTokenIs(builder_, SEMICOLON)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SEMICOLON);
    if (result_) {
      marker_.done(SEPARATOR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // ("empty-sequence" "(" ")")
  //  | (ItemType OccurrenceIndicator?)
  public static boolean SequenceType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceType")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<sequence type>");
    result_ = SequenceType_0(builder_, level_ + 1);
    if (!result_) result_ = SequenceType_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(SEQUENCE_TYPE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // "empty-sequence" "(" ")"
  private static boolean SequenceType_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceType_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_EMPTY_SEQUENCE);
    result_ = result_ && consumeToken(builder_, L_PAR);
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ItemType OccurrenceIndicator?
  private static boolean SequenceType_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceType_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ItemType(builder_, level_ + 1);
    result_ = result_ && SequenceType_1_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<sequence type union>");
    result_ = SequenceType(builder_, level_ + 1);
    result_ = result_ && SequenceTypeUnion_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(SEQUENCE_TYPE_UNION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ("|" SequenceType)*
  private static boolean SequenceTypeUnion_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceTypeUnion_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!SequenceTypeUnion_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "SequenceTypeUnion_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "|" SequenceType
  private static boolean SequenceTypeUnion_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SequenceTypeUnion_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PIPE);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // BoundarySpaceDecl | DefaultCollationDecl | BaseURIDecl | ConstructionDecl | OrderingModeDecl | EmptyOrderDecl | CopyNamespacesDecl | DecimalFormatDecl
  public static boolean Setter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Setter")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = BoundarySpaceDecl(builder_, level_ + 1);
    if (!result_) result_ = DefaultCollationDecl(builder_, level_ + 1);
    if (!result_) result_ = BaseURIDecl(builder_, level_ + 1);
    if (!result_) result_ = ConstructionDecl(builder_, level_ + 1);
    if (!result_) result_ = OrderingModeDecl(builder_, level_ + 1);
    if (!result_) result_ = EmptyOrderDecl(builder_, level_ + 1);
    if (!result_) result_ = CopyNamespacesDecl(builder_, level_ + 1);
    if (!result_) result_ = DecimalFormatDecl(builder_, level_ + 1);
    if (result_) {
      marker_.done(SETTER);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // PathExpr ("!" PathExpr)*
  public static boolean SimpleMapExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SimpleMapExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<simple map expr>");
    result_ = PathExpr(builder_, level_ + 1);
    result_ = result_ && SimpleMapExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), SIMPLE_MAP_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(SIMPLE_MAP_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ("!" PathExpr)*
  private static boolean SimpleMapExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SimpleMapExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!SimpleMapExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "SimpleMapExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "!" PathExpr
  private static boolean SimpleMapExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SimpleMapExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, EXCLAMATION_MARK);
    result_ = result_ && PathExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // TypeName
  public static boolean SimpleTypeName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SimpleTypeName")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<simple type name>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<simple type name>");
    result_ = TypeName(builder_, level_ + 1);
    if (result_) {
      marker_.done(SIMPLE_TYPE_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // SimpleTypeName "?"?
  public static boolean SingleType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SingleType")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<single type>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<single type>");
    result_ = SimpleTypeName(builder_, level_ + 1);
    result_ = result_ && SingleType_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(SINGLE_TYPE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // "?"?
  private static boolean SingleType_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SingleType_1")) return false;
    consumeToken(builder_, QUESTIONMARK);
    return true;
  }

  /* ********************************************************** */
  // "sliding" "window" "$" VarName TypeDeclaration? "in" ExprSingle WindowStartCondition WindowEndCondition
  public static boolean SlidingWindowClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SlidingWindowClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<sliding window clause>");
    result_ = consumeToken(builder_, K_SLIDING);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_WINDOW));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, DOLLAR_SIGN)) && result_;
    result_ = pinned_ && report_error_(builder_, VarName(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, SlidingWindowClause_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, K_IN)) && result_;
    result_ = pinned_ && report_error_(builder_, ExprSingle(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, WindowStartCondition(builder_, level_ + 1)) && result_;
    result_ = pinned_ && WindowEndCondition(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(SLIDING_WINDOW_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  // TypeDeclaration?
  private static boolean SlidingWindowClause_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SlidingWindowClause_4")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // PostfixExpr | AxisStep
  public static boolean StepExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StepExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<step expr>");
    result_ = PostfixExpr(builder_, level_ + 1);
    if (!result_) result_ = AxisStep(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), STEP_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(STEP_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // RangeExpr (StringConcatMultipliedExpr)*
  public static boolean StringConcatExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringConcatExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<string concat expr>");
    result_ = RangeExpr(builder_, level_ + 1);
    result_ = result_ && StringConcatExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), STRING_CONCAT_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(STRING_CONCAT_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // (StringConcatMultipliedExpr)*
  private static boolean StringConcatExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringConcatExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!StringConcatExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "StringConcatExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // (StringConcatMultipliedExpr)
  private static boolean StringConcatExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringConcatExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = StringConcatMultipliedExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "||" RangeExpr
  static boolean StringConcatMultipliedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringConcatMultipliedExpr")) return false;
    if (!nextTokenIs(builder_, PIPE_PIPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, PIPE_PIPE);
    pinned_ = result_; // pin = 1
    result_ = result_ && RangeExpr(builder_, level_ + 1);
    if (!result_ && !pinned_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ("case" SwitchCaseOperand)+ SwitchReturnClause
  public static boolean SwitchCaseClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchCaseClause")) return false;
    if (!nextTokenIs(builder_, K_CASE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = SwitchCaseClause_0(builder_, level_ + 1);
    result_ = result_ && SwitchReturnClause(builder_, level_ + 1);
    if (result_) {
      marker_.done(SWITCH_CASE_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // ("case" SwitchCaseOperand)+
  private static boolean SwitchCaseClause_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchCaseClause_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = SwitchCaseClause_0_0(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!SwitchCaseClause_0_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "SwitchCaseClause_0");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "case" SwitchCaseOperand
  private static boolean SwitchCaseClause_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchCaseClause_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_CASE);
    result_ = result_ && SwitchCaseOperand(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean SwitchCaseOperand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchCaseOperand")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<switch case operand>");
    result_ = ExprSingle(builder_, level_ + 1);
    if (result_) {
      marker_.done(SWITCH_CASE_OPERAND);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "default" SwitchReturnClause
  public static boolean SwitchDefaultReturnClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchDefaultReturnClause")) return false;
    if (!nextTokenIs(builder_, K_DEFAULT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DEFAULT);
    pinned_ = result_; // pin = 1
    result_ = result_ && SwitchReturnClause(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(SWITCH_DEFAULT_RETURN_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "switch" "(" Expr ")" SwitchCaseClause+ SwitchDefaultReturnClause
  public static boolean SwitchExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchExpr")) return false;
    if (!nextTokenIs(builder_, K_SWITCH)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_SWITCH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, L_PAR));
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, R_PAR)) && result_;
    result_ = pinned_ && report_error_(builder_, SwitchExpr_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && SwitchDefaultReturnClause(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(SWITCH_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // SwitchCaseClause+
  private static boolean SwitchExpr_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchExpr_4")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = SwitchCaseClause(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!SwitchCaseClause(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "SwitchExpr_4");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "return" ExprSingle
  public static boolean SwitchReturnClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SwitchReturnClause")) return false;
    if (!nextTokenIs(builder_, K_RETURN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_RETURN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (result_) {
      marker_.done(SWITCH_RETURN_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // QName
  public static boolean TagName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TagName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = QName(builder_, level_ + 1);
    if (result_) {
      marker_.done(TAG_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "text" "(" ")"
  public static boolean TextTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TextTest")) return false;
    if (!nextTokenIs(builder_, K_TEXT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_TEXT);
    result_ = result_ && consumeToken(builder_, L_PAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAR);
    if (result_ || pinned_) {
      marker_.done(TEXT_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // CastableExpr ( "treat" "as" SequenceType )?
  public static boolean TreatExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TreatExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<treat expr>");
    result_ = CastableExpr(builder_, level_ + 1);
    result_ = result_ && TreatExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), TREAT_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(TREAT_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ( "treat" "as" SequenceType )?
  private static boolean TreatExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TreatExpr_1")) return false;
    TreatExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // "treat" "as" SequenceType
  private static boolean TreatExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TreatExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_TREAT);
    result_ = result_ && consumeToken(builder_, K_AS);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // TryClause CatchClause+
  public static boolean TryCatchExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCatchExpr")) return false;
    if (!nextTokenIs(builder_, K_TRY)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = TryClause(builder_, level_ + 1);
    result_ = result_ && TryCatchExpr_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(TRY_CATCH_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // CatchClause+
  private static boolean TryCatchExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCatchExpr_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = CatchClause(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!CatchClause(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "TryCatchExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "try" "{" TryTargetExpr "}"
  public static boolean TryClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryClause")) return false;
    if (!nextTokenIs(builder_, K_TRY)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_TRY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, L_C_BRACE));
    result_ = pinned_ && report_error_(builder_, TryTargetExpr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, R_C_BRACE) && result_;
    if (result_ || pinned_) {
      marker_.done(TRY_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Expr
  public static boolean TryTargetExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryTargetExpr")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<try target expr>");
    result_ = Expr(builder_, level_ + 1);
    if (result_) {
      marker_.done(TRY_TARGET_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "tumbling" "window" "$" VarName TypeDeclaration? "in" ExprSingle WindowStartCondition WindowEndCondition?
  public static boolean TumblingWindowClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TumblingWindowClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<tumbling window clause>");
    result_ = consumeToken(builder_, K_TUMBLING);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_WINDOW));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, DOLLAR_SIGN)) && result_;
    result_ = pinned_ && report_error_(builder_, VarName(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, TumblingWindowClause_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, K_IN)) && result_;
    result_ = pinned_ && report_error_(builder_, ExprSingle(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, WindowStartCondition(builder_, level_ + 1)) && result_;
    result_ = pinned_ && TumblingWindowClause_8(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(TUMBLING_WINDOW_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  // TypeDeclaration?
  private static boolean TumblingWindowClause_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TumblingWindowClause_4")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  // WindowEndCondition?
  private static boolean TumblingWindowClause_8(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TumblingWindowClause_8")) return false;
    WindowEndCondition(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "as" SequenceType
  public static boolean TypeDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeDeclaration")) return false;
    if (!nextTokenIs(builder_, K_AS)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_AS);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    if (result_) {
      marker_.done(TYPE_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // EQName
  public static boolean TypeName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeName")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<type name>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<type name>");
    result_ = EQName(builder_, level_ + 1);
    if (result_) {
      marker_.done(TYPE_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "function" "(" (SequenceType ("," SequenceType)*)? ")" "as" SequenceType
  public static boolean TypedFunctionTest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypedFunctionTest")) return false;
    if (!nextTokenIs(builder_, K_FUNCTION)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_FUNCTION);
    result_ = result_ && consumeToken(builder_, L_PAR);
    result_ = result_ && TypedFunctionTest_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAR);
    pinned_ = result_; // pin = 4
    result_ = result_ && report_error_(builder_, consumeToken(builder_, K_AS));
    result_ = pinned_ && SequenceType(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(TYPED_FUNCTION_TEST);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = SequenceType(builder_, level_ + 1);
    result_ = result_ && TypedFunctionTest_2_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ("," SequenceType)*
  private static boolean TypedFunctionTest_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypedFunctionTest_2_0_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!TypedFunctionTest_2_0_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "TypedFunctionTest_2_0_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "," SequenceType
  private static boolean TypedFunctionTest_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypedFunctionTest_2_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && SequenceType(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "default" ("$" VarName)? SwitchReturnClause
  public static boolean TypeswitchDefaultReturnClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeswitchDefaultReturnClause")) return false;
    if (!nextTokenIs(builder_, K_DEFAULT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DEFAULT);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, TypeswitchDefaultReturnClause_1(builder_, level_ + 1));
    result_ = pinned_ && SwitchReturnClause(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(TYPESWITCH_DEFAULT_RETURN_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "typeswitch" "(" Expr ")" CaseClause+ TypeswitchDefaultReturnClause
  public static boolean TypeswitchExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeswitchExpr")) return false;
    if (!nextTokenIs(builder_, K_TYPESWITCH)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_TYPESWITCH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, L_PAR));
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, R_PAR)) && result_;
    result_ = pinned_ && report_error_(builder_, TypeswitchExpr_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && TypeswitchDefaultReturnClause(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(TYPESWITCH_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // CaseClause+
  private static boolean TypeswitchExpr_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeswitchExpr_4")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = CaseClause(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!CaseClause(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "TypeswitchExpr_4");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // Expr
  public static boolean URIExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "URIExpr")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<uri expr>");
    result_ = Expr(builder_, level_ + 1);
    if (result_) {
      marker_.done(URI_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // StringLiteral
  public static boolean URILiteral(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "URILiteral")) return false;
    if (!nextTokenIs(builder_, STRINGLITERAL)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, STRINGLITERAL);
    if (result_) {
      marker_.done(URI_LITERAL);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // ("-" | "+")* ValueExpr
  public static boolean UnaryExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnaryExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<unary expr>");
    result_ = UnaryExpr_0(builder_, level_ + 1);
    result_ = result_ && ValueExpr(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), UNARY_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(UNARY_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ("-" | "+")*
  private static boolean UnaryExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnaryExpr_0")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!UnaryExpr_0_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "UnaryExpr_0");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // "-" | "+"
  private static boolean UnaryExpr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnaryExpr_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, OP_MINUS);
    if (!result_) result_ = consumeToken(builder_, OP_PLUS);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // IntersectExceptExpr ( ("union" | "|") IntersectExceptExpr )*
  public static boolean UnionExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnionExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<union expr>");
    result_ = IntersectExceptExpr(builder_, level_ + 1);
    result_ = result_ && UnionExpr_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), UNION_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(UNION_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // ( ("union" | "|") IntersectExceptExpr )*
  private static boolean UnionExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnionExpr_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!UnionExpr_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "UnionExpr_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // ("union" | "|") IntersectExceptExpr
  private static boolean UnionExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnionExpr_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = UnionExpr_1_0_0(builder_, level_ + 1);
    result_ = result_ && IntersectExceptExpr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "union" | "|"
  private static boolean UnionExpr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnionExpr_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_UNION);
    if (!result_) result_ = consumeToken(builder_, PIPE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "unordered" "{" Expr "}"
  public static boolean UnorderedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnorderedExpr")) return false;
    if (!nextTokenIs(builder_, K_UNORDERED)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_UNORDERED);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(UNORDERED_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_VALIDATE);
    result_ = result_ && ValidateExpr_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_C_BRACE);
    result_ = result_ && Expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_C_BRACE);
    if (result_) {
      marker_.done(VALIDATE_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
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
    Marker marker_ = builder_.mark();
    result_ = ValidationMode(builder_, level_ + 1);
    if (!result_) result_ = ValidateExpr_1_0_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "type" TypeName
  private static boolean ValidateExpr_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValidateExpr_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_TYPE);
    result_ = result_ && TypeName(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "lax" | "strict"
  public static boolean ValidationMode(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValidationMode")) return false;
    if (!nextTokenIs(builder_, K_LAX) && !nextTokenIs(builder_, K_STRICT)
        && replaceVariants(builder_, 2, "<validation mode>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<validation mode>");
    result_ = consumeToken(builder_, K_LAX);
    if (!result_) result_ = consumeToken(builder_, K_STRICT);
    if (result_) {
      marker_.done(VALIDATION_MODE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "eq" | "ne" | "lt" | "le" | "gt" | "ge"
  static boolean ValueComp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueComp")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, EQ);
    if (!result_) result_ = consumeToken(builder_, NE);
    if (!result_) result_ = consumeToken(builder_, LT);
    if (!result_) result_ = consumeToken(builder_, LE);
    if (!result_) result_ = consumeToken(builder_, GT);
    if (!result_) result_ = consumeToken(builder_, GE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ValidateExpr | ExtensionExpr | SimpleMapExpr
  public static boolean ValueExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueExpr")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<value expr>");
    result_ = ValidateExpr(builder_, level_ + 1);
    if (!result_) result_ = ExtensionExpr(builder_, level_ + 1);
    if (!result_) result_ = SimpleMapExpr(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), VALUE_EXPR)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(VALUE_EXPR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "declare" Annotation* "variable" "$" VarName TypeDeclaration? ((":=" VarValue) | ExternalVarPart)
  public static boolean VarDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl")) return false;
    if (!nextTokenIs(builder_, K_DECLARE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_DECLARE);
    result_ = result_ && VarDecl_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_VARIABLE);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, consumeToken(builder_, DOLLAR_SIGN));
    result_ = pinned_ && report_error_(builder_, VarName(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, VarDecl_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && VarDecl_6(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(VAR_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // Annotation*
  private static boolean VarDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!Annotation(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "VarDecl_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // TypeDeclaration?
  private static boolean VarDecl_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl_5")) return false;
    TypeDeclaration(builder_, level_ + 1);
    return true;
  }

  // (":=" VarValue) | ExternalVarPart
  private static boolean VarDecl_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl_6")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = VarDecl_6_0(builder_, level_ + 1);
    if (!result_) result_ = ExternalVarPart(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ":=" VarValue
  private static boolean VarDecl_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDecl_6_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && VarValue(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean VarDefaultValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarDefaultValue")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<var default value>");
    result_ = ExprSingle(builder_, level_ + 1);
    if (result_) {
      marker_.done(VAR_DEFAULT_VALUE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean VarLocalName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarLocalName")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    if (result_) {
      marker_.done(VAR_LOCAL_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // VarNamespace ':' VarLocalName | VarLocalName | URIQualifiedName
  public static boolean VarName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarName")) return false;
    if (!nextTokenIs(builder_, NCNAME) && !nextTokenIs(builder_, URIQUALIFIEDNAME)
        && replaceVariants(builder_, 2, "<var name>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<var name>");
    result_ = VarName_0(builder_, level_ + 1);
    if (!result_) result_ = VarLocalName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, URIQUALIFIEDNAME);
    if (result_) {
      marker_.done(VAR_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // VarNamespace ':' VarLocalName
  private static boolean VarName_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarName_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = VarNamespace(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && VarLocalName(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // NCName
  public static boolean VarNamespace(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarNamespace")) return false;
    if (!nextTokenIs(builder_, NCNAME)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    if (result_) {
      marker_.done(VAR_NAMESPACE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "$" VarName
  public static boolean VarRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarRef")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VarName(builder_, level_ + 1);
    if (result_) {
      marker_.done(VAR_REF);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // ExprSingle
  public static boolean VarValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VarValue")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<var value>");
    result_ = ExprSingle(builder_, level_ + 1);
    if (result_) {
      marker_.done(VAR_VALUE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "xquery" ((VersionDeclEncoding) | (VersionDeclVersion)) Separator
  public static boolean VersionDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDecl")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<version decl>");
    result_ = consumeToken(builder_, K_XQUERY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, VersionDecl_1(builder_, level_ + 1));
    result_ = pinned_ && Separator(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(VERSION_DECL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, VersionDeclRecover_parser_);
    return result_ || pinned_;
  }

  // (VersionDeclEncoding) | (VersionDeclVersion)
  private static boolean VersionDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDecl_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = VersionDecl_1_0(builder_, level_ + 1);
    if (!result_) result_ = VersionDecl_1_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (VersionDeclEncoding)
  private static boolean VersionDecl_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDecl_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = VersionDeclEncoding(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (VersionDeclVersion)
  private static boolean VersionDecl_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDecl_1_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = VersionDeclVersion(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "encoding" StringLiteral
  public static boolean VersionDeclEncoding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclEncoding")) return false;
    if (!nextTokenIs(builder_, K_ENCODING)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_ENCODING);
    pinned_ = result_; // pin = 1
    result_ = result_ && consumeToken(builder_, STRINGLITERAL);
    if (result_ || pinned_) {
      marker_.done(VERSION_DECL_ENCODING);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !('$' | '%' | '(#' | '(' | '*' | '+' | '-' | '.' | '..' | '/' | '//' | '<!--' | '<' | '<?' | '@' | 'ancestor' | 'ancestor-or-self' | 'attribute' | 'child' | 'comment' | 'declare' | 'descendant' | 'descendant-or-self' | 'document' | 'document-node' | 'element' | 'every' | 'following' | 'following-sibling' | 'for' | 'function' | 'if' | 'import' | 'let' | 'module' | 'namespace' | 'namespace-node' | 'node' | 'ordered' | 'parent' | 'preceding' | 'preceding-sibling' | 'processing-instruction' | 'schema-attribute' | 'schema-element' | 'self' | 'some' | 'switch' | 'text' | 'try' | 'typeswitch' | 'unordered' | 'validate' | -eof- | BracedURILiteral | DecimalLiteral | DoubleLiteral | IntegerLiteral | NCName | QName | StringLiteral | URIQualifiedName)
  static boolean VersionDeclRecover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclRecover")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_NOT_, null);
    result_ = !VersionDeclRecover_0(builder_, level_ + 1);
    marker_.rollbackTo();
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_NOT_, null);
    return result_;
  }

  // '$' | '%' | '(#' | '(' | '*' | '+' | '-' | '.' | '..' | '/' | '//' | '<!--' | '<' | '<?' | '@' | 'ancestor' | 'ancestor-or-self' | 'attribute' | 'child' | 'comment' | 'declare' | 'descendant' | 'descendant-or-self' | 'document' | 'document-node' | 'element' | 'every' | 'following' | 'following-sibling' | 'for' | 'function' | 'if' | 'import' | 'let' | 'module' | 'namespace' | 'namespace-node' | 'node' | 'ordered' | 'parent' | 'preceding' | 'preceding-sibling' | 'processing-instruction' | 'schema-attribute' | 'schema-element' | 'self' | 'some' | 'switch' | 'text' | 'try' | 'typeswitch' | 'unordered' | 'validate' | -eof- | BracedURILiteral | DecimalLiteral | DoubleLiteral | IntegerLiteral | NCName | QName | StringLiteral | URIQualifiedName
  private static boolean VersionDeclRecover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclRecover_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
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
    if (!result_) result_ = consumeToken(builder_, LT_CHAR);
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
    if (!result_) result_ = VersionDeclRecover_0_53(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, BRACEDURILITERAL);
    if (!result_) result_ = consumeToken(builder_, DECIMALLITERAL);
    if (!result_) result_ = consumeToken(builder_, DOUBLELITERAL);
    if (!result_) result_ = consumeToken(builder_, INTEGERLITERAL);
    if (!result_) result_ = consumeToken(builder_, NCNAME);
    if (!result_) result_ = QName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRINGLITERAL);
    if (!result_) result_ = consumeToken(builder_, URIQUALIFIEDNAME);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  private static boolean VersionDeclRecover_0_53(PsiBuilder builder_, int level_) {
    return true;
  }

  /* ********************************************************** */
  // "version" StringLiteral ("encoding" StringLiteral)?
  public static boolean VersionDeclVersion(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VersionDeclVersion")) return false;
    if (!nextTokenIs(builder_, K_VERSION)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, K_VERSION);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, STRINGLITERAL));
    result_ = pinned_ && VersionDeclVersion_2(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(VERSION_DECL_VERSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_ENCODING);
    result_ = result_ && consumeToken(builder_, STRINGLITERAL);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "where" ExprSingle
  public static boolean WhereClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WhereClause")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<where clause>");
    result_ = consumeToken(builder_, K_WHERE);
    pinned_ = result_; // pin = 1
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(WHERE_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, FLWORExprRecover_parser_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // (NCName ":" "*")
  //  | ("*" ":" NCName)
  //  | "*"
  //  | (BracedURILiteral "*")
  public static boolean Wildcard(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Wildcard")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<wildcard>");
    result_ = Wildcard_0(builder_, level_ + 1);
    if (!result_) result_ = Wildcard_1(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STAR_SIGN);
    if (!result_) result_ = Wildcard_3(builder_, level_ + 1);
    if (result_) {
      marker_.done(WILDCARD);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // NCName ":" "*"
  private static boolean Wildcard_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Wildcard_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NCNAME);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && consumeToken(builder_, STAR_SIGN);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "*" ":" NCName
  private static boolean Wildcard_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Wildcard_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, STAR_SIGN);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && consumeToken(builder_, NCNAME);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // BracedURILiteral "*"
  private static boolean Wildcard_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Wildcard_3")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, BRACEDURILITERAL);
    result_ = result_ && consumeToken(builder_, STAR_SIGN);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "for" (TumblingWindowClause | SlidingWindowClause)
  public static boolean WindowClause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowClause")) return false;
    if (!nextTokenIs(builder_, K_FOR)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_FOR);
    result_ = result_ && WindowClause_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(WINDOW_CLAUSE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // TumblingWindowClause | SlidingWindowClause
  private static boolean WindowClause_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowClause_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = TumblingWindowClause(builder_, level_ + 1);
    if (!result_) result_ = SlidingWindowClause(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "only"? "end" WindowVars "when" ExprSingle
  public static boolean WindowEndCondition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowEndCondition")) return false;
    if (!nextTokenIs(builder_, K_END) && !nextTokenIs(builder_, K_ONLY)
        && replaceVariants(builder_, 2, "<window end condition>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<window end condition>");
    result_ = WindowEndCondition_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_END);
    result_ = result_ && WindowVars(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_WHEN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (result_) {
      marker_.done(WINDOW_END_CONDITION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
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
  public static boolean WindowStartCondition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowStartCondition")) return false;
    if (!nextTokenIs(builder_, K_START)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_START);
    result_ = result_ && WindowVars(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, K_WHEN);
    result_ = result_ && ExprSingle(builder_, level_ + 1);
    if (result_) {
      marker_.done(WINDOW_START_CONDITION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // ("$" CurrentItem)? PositionalVar? ("previous" "$" PreviousItem)? ("next" "$" NextItem)?
  public static boolean WindowVars(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WindowVars")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<window vars>");
    result_ = WindowVars_0(builder_, level_ + 1);
    result_ = result_ && WindowVars_1(builder_, level_ + 1);
    result_ = result_ && WindowVars_2(builder_, level_ + 1);
    result_ = result_ && WindowVars_3(builder_, level_ + 1);
    if (result_) {
      marker_.done(WINDOW_VARS);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && CurrentItem(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_PREVIOUS);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && PreviousItem(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, K_NEXT);
    result_ = result_ && consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && NextItem(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
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
  final static Parser FunctionDeclRec_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return FunctionDeclRec(builder_, level_ + 1);
    }
  };
  final static Parser ModuleDeclRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return ModuleDeclRecover(builder_, level_ + 1);
    }
  };
  final static Parser VersionDeclRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return VersionDeclRecover(builder_, level_ + 1);
    }
  };
}
