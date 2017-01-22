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
package org.intellij.xquery.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.intellij.xquery.psi.XQueryBasicTypes;
import org.intellij.xquery.psi.XQueryTypes;
import com.intellij.psi.TokenType;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

%%

%{

  public _XQueryLexer() {
    this((java.io.Reader)null);
  }

  private Map<Integer, String> states = new HashMap<Integer, String>() {
    {
      put(YYINITIAL, "YYINITIAL");
      put(ITEM_TYPE, "ITEM_TYPE");
      put(ATTR_QNAME, "ATTR_QNAME");
      put(QUOT_STRING_SIMPLE, "QUOT_STRING_SIMPLE");
      put(PRAGMA, "PRAGMA");
      put(PI_CONTENT, "PI_CONTENT");
      put(TAG_QNAME, "TAG_QNAME");
      put(END_TAG, "END_TAG");
      put(WS_BEFORE_QNAME, "WS_BEFORE_QNAME");
      put(XQUERY_RECOGNITION, "XQUERY_RECOGNITION");
      put(QNAME, "QNAME");
      put(EXPRESSION_IN_QUOT_STRING, "EXPRESSION_IN_QUOT_STRING");
      put(EXPR_COMMENT, "EXPR_COMMENT");
      put(MISPLACED_EXPR_COMMENT, "MISPLACED_EXPR_COMMENT");
      put(QUOT_STRING, "QUOT_STRING");
      put(ELEMENT_CONTENT, "ELEMENT_CONTENT");
      put(DECLARATION_RECOGNITION, "DECLARATION_RECOGNITION");
      put(DEFAULT_RECOGNITION, "DEFAULT_RECOGNITION");
      put(VALIDATE_RECOGNITION, "VALIDATE_RECOGNITION");
      put(BY_RECOGNITION, "BY_RECOGNITION");
      put(DOC_COMMENT, "DOC_COMMENT");
      put(PRAGMA_CONTENT, "PRAGMA_CONTENT");
      put(MODULE_RECOGNITION, "MODULE_RECOGNITION");
      put(AS_RECOGNITION, "AS_RECOGNITION");
      put(CDATA, "CDATA");
      put(START_TAG, "START_TAG");
      put(PI, "PI");
      put(PRAGMA_BEFORE_CONTENT, "PRAGMA_BEFORE_CONTENT");
      put(IMPORT_RECOGNITION, "IMPORT_RECOGNITION");
      put(PI_BEFORE_CONTENT, "PI_BEFORE_CONTENT");
      put(DIR_COMMENT, "DIR_COMMENT");
      put(APOS_STRING_SIMPLE, "APOS_STRING_SIMPLE");
      put(URIQUALIFIED, "URIQUALIFIED");
      put(EXPRESSION_IN_APOS_STRING, "EXPRESSION_IN_APOS_STRING");
      put(EXPRESSION, "EXPRESSION");
      put(EXPRESSION_IN_STRING_CONSTR, "EXPRESSION_IN_STRING_CONSTR");
      put(APOS_STRING, "APOS_STRING");
      put(ATTR_LIST, "ATTR_LIST");
      put(STRING_CONSTR, "STRING_CONSTR");
    }
  };

  private String state(int state) {
    return states.get(state);
  }

  private void log(String msg) {
//    System.out.println(msg);
  }

  private Stack<Integer> stack = new Stack<Integer>();

  private void pushState(int state) {
    int current = yystate();
    log("pushState is changing from " + state(current) + "(" + current + ") to " + state(state) + "(" + state +")");
    stack.push(current);
    yybegin(state);
    log("new state = " + state(yystate()) + "(" + yystate() + ")");
  }

  private void popState() {
    if (stack.empty()) {
      log("popState is defaulting to EXPRESSION");
      yybegin(EXPRESSION);
    } else {
      int state = stack.pop();
      log("popState is changing from " + state(yystate()) + "(" + yystate() + ") to " + state(state) + "(" + state + ")");
      yybegin(state);
    }
    log("new state = " + state(yystate()) + "(" + yystate() + ")");
  }

  private void popStateOrDefaultTo(int defaultState) {
    if (stack.empty()) {
      log("popStateOrDefault is defaulting to " + defaultState);
      yybegin(defaultState);
    } else {
      int state = stack.pop();
      log("popStateOrDefault is changing to " + state);
      yybegin(state);
    }
    log("new state = " + state(yystate()) + "(" + yystate() + ")");
  }
%}


%class _XQueryLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}


IntegerLiteral={Digits}
DecimalLiteral=("." {Digits}) | ({Digits} "." [0-9]*)               	                    /* ws: explicit */
DoubleLiteral=(("." {Digits}) | ({Digits} ("." [0-9]*)?)) [eE] [+-]? {Digits}             	/* ws: explicit */
URIQualifiedName={BracedURILiteral} {NCName}                                                /* ws: explicit */
BracedURILiteral="Q" "{" ({PredefinedEntityRef} | {CharRef} | [^&{}]    )* "}"                  /* ws: explicit */
PredefinedEntityRef="&" ("lt" | "gt" | "amp" | "quot" | "apos" | "bdquo" | "brvbar" | "bull" | "circ" | "copy" | "emsp" | "ensp" | "hellip" | "iexcl" | "iquest" | "laquo" | "ldquo" | "lsaquo" | "lsquo" | "mdash" | "nbsp" |  "dash" | "oline" | "prime" | "Prime" | "raquo" | "rdquo" | "rsaquo" | "rsquo" | "sbquo" | "thinsp" | "tilde" | "uml" | "acute" | "cedil" | "cent" | "curren" | "deg" | "divide" | "macr" | "micro" | "middot" | "not" | "ordf" | "ordm" | "para" | "plusmn" | "pound" | "sect" | "times" | "yen") ";"                         /* ws: explicit */
EscapeQuot="\"\""
EscapeApos="''"
Digits=[0-9]+
CharRef="&#" [0-9]+ ";" | "&#x" [0-9a-fA-F]+ ";"                                            /* xgc: xml-version */
NCName={NameStartCharWithoutFirst} ({NameCharWithoutFirst})*                                                     /* xgc: xml-version */
NameStartChar=":" | {NameStartCharWithoutFirst}
NameStartCharWithoutFirst= [A-Z] | "_" | [a-z] | [\u00C0-\u00D6] | [\u00D8-\u00F6] | [\u00F8-\u02FF] | [\u0370-\u037D] | [\u037F-\u1FFF] | [\u200C-\u200D] | [\u2070-\u218F] | [\u2C00-\u2FEF] | [\u3001-\uD7FF] | [\uF900-\uFDCF] | [\uFDF0-\uFFFD] | [\uD800\uDC00-\uDB7F\uDFFF]
NameChar={NameStartChar} | "-" | "." | [0-9] | \u00B7 | [\u0300-\u036F] | [\u203F-\u2040]
NameCharWithoutFirst={NameStartCharWithoutFirst} | "-" | "." | [0-9] | \u00B7 | [\u0300-\u036F] | [\u203F-\u2040]
Name={NameStartChar} ({NameChar})*
S=(\u20 | \u9 | \uD | \uA)+                                                                 /* xgc: xml-version */
Char=\u9| \uA | \uD | [\u20-\uD7FF] | [\uE000-\uFFFD] | [\u10000-\u10FFFF]                  /* xgc: xml-version */
SC=({S} | "(:" {Char}* ~":)")+

%state START_TAG
%state END_TAG
%state ELEMENT_CONTENT
%state QUOT_STRING
%state APOS_STRING
%state QUOT_STRING_SIMPLE
%state APOS_STRING_SIMPLE
%state URIQUALIFIED
%state QNAME
%state EXPR_COMMENT
%state MISPLACED_EXPR_COMMENT
%state DIR_COMMENT
%state PRAGMA
%state PRAGMA_BEFORE_CONTENT
%state PRAGMA_CONTENT
%state PI
%state PI_BEFORE_CONTENT
%state PI_CONTENT
%state CDATA
%state TAG_QNAME
%state ATTR_LIST
%state ATTR_QNAME
%state DOC_COMMENT
%state EXPRESSION_IN_QUOT_STRING
%state EXPRESSION_IN_APOS_STRING
%state EXPRESSION
%state STRING_CONSTR
%state EXPRESSION_IN_STRING_CONSTR
// helper states for better support of live syntax highlighting
%state XQUERY_RECOGNITION
%state DECLARATION_RECOGNITION
%state DEFAULT_RECOGNITION
%state IMPORT_RECOGNITION
%state MODULE_RECOGNITION
%state AS_RECOGNITION
%state VALIDATE_RECOGNITION
%state BY_RECOGNITION
%state WS_BEFORE_QNAME
%state ITEM_TYPE
%state KIND_TEST
%state UPDATE_FACILITY
%%

<YYINITIAL> {
{S}                                        {return TokenType.WHITE_SPACE;}
. {{yypushback(yylength()); pushState(EXPRESSION); return TokenType.WHITE_SPACE;}}
}

<EXPRESSION_IN_QUOT_STRING,EXPRESSION_IN_APOS_STRING,EXPRESSION,EXPRESSION_IN_STRING_CONSTR> {
{S}                                        {return TokenType.WHITE_SPACE;}
{DecimalLiteral}                           {return XQueryTypes.DECIMALLITERAL;}
{DoubleLiteral}                            {return XQueryTypes.DOUBLELITERAL;}
{IntegerLiteral}                           {return XQueryTypes.INTEGERLITERAL;}
"\""                                       {pushState(QUOT_STRING_SIMPLE);return XQueryTypes.OPENINGQUOT;}
"'"                                        {pushState(APOS_STRING_SIMPLE);return XQueryTypes.OPENINGAPOS;}
"Q{"                                       {pushState(URIQUALIFIED); yypushback(2);}
"(:~"                                      {pushState(DOC_COMMENT);return XQueryBasicTypes.DOC_COMMENT_START;}
"(:"                                       {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"<<"                                       {return XQueryTypes.NODECOMP_LT;}
">>"                                       {return XQueryTypes.NODECOMP_GT;}
"<!--"                                     {pushState(DIR_COMMENT); return XQueryTypes.DIR_COMMENT_BEGIN;}
"<![CDATA["                                {pushState(CDATA); return XQueryTypes.CDATA_BEGIN;}
"<" / {SC}? "$"                            {return XQueryTypes.LT_CHAR;}
"<" / {SC}? {IntegerLiteral}               {return XQueryTypes.LT_CHAR;}
"<" / {SC}? {DecimalLiteral}               {return XQueryTypes.LT_CHAR;}
"<" / {SC}? {DoubleLiteral}                {return XQueryTypes.LT_CHAR;}
"<" / {SC}? {NCName} {S}? "(:"             {pushState(START_TAG); return XQueryTypes.XMLSTARTTAGSTART;}
"<" / {NCName} {S} "("                     {pushState(START_TAG); return XQueryTypes.XMLSTARTTAGSTART;}
"<" / {SC}? {NCName} {S}? "("              {return XQueryTypes.LT_CHAR;}
"<" / {SC}? {NCName} {SC}? ":" {SC}? {NCName} {S}? "(:" {pushState(START_TAG); return XQueryTypes.XMLSTARTTAGSTART;}
"<" / {NCName} ":" {NCName} {S} "("        {pushState(START_TAG); return XQueryTypes.XMLSTARTTAGSTART;}
"<" / {SC}? {NCName} {SC}? ":" {SC}? {NCName} {S}? "(" {return XQueryTypes.LT_CHAR;}
"<" / {SC}? "("                            {return XQueryTypes.LT_CHAR;}
"<"                                        {pushState(START_TAG); return XQueryTypes.XMLSTARTTAGSTART;}
"<="                                       {return XQueryTypes.LE_CHARS;}
">="                                       {return XQueryTypes.GE_CHARS;}
"</"                                       {pushState(END_TAG); return XQueryTypes.XMLENDTAGSTART;}
">" / {SC}? "$"                            {return XQueryTypes.GT_CHAR;}
">" / {SC}? {IntegerLiteral}               {return XQueryTypes.GT_CHAR;}
">" / {SC}? {DecimalLiteral}               {return XQueryTypes.GT_CHAR;}
">" / {SC}? {DoubleLiteral}                {return XQueryTypes.GT_CHAR;}
">" / {SC}? {NCName} {S}? "(:"             {return XQueryTypes.XMLTAGEND;}
">" / {SC}? {NCName} {S}? "("             {return XQueryTypes.GT_CHAR;}
">" / {SC}? {NCName} {SC}? ":" {SC}? {NCName} {S}? "(:" {return XQueryTypes.XMLTAGEND;}
">" / {SC}? {NCName} {SC}? ":" {SC}? {NCName} {SC}? "(" {return XQueryTypes.GT_CHAR;}
">"                                        {return XQueryTypes.XMLTAGEND;}
"@"                                        {pushState(QNAME);return XQueryTypes.AT_SIGN;}
"//" / {SC}? ("item"|"node"|"document-node"|"text"|"element"|"attribute"|"schema-element"|"schema-attribute"|"processing-instruction"|"comment"|"namespace-node"|"%"|"function"|"binary"|"object-node"|"number-node"|"boolean-node"|"null-node"|"array-node") {SC}? "("  {return XQueryTypes.SLASH_SLASH;}
"//" / {SC}? ("child"|"descendant"|"attribute"|"self"|"descendant-or-self"|"following-sibling"|"following"|"parent"|"ancestor"|"preceding-sibling"|"preceding"|"ancestor-or-self"|"namespace") {SC}? "::" {return XQueryTypes.SLASH_SLASH;}
"//"                                       {pushState(QNAME);return XQueryTypes.SLASH_SLASH;}
"/" / {SC}? ("item"|"node"|"document-node"|"text"|"element"|"attribute"|"schema-element"|"schema-attribute"|"processing-instruction"|"comment"|"namespace-node"|"%"|"function"|"binary"|"object-node"|"number-node"|"boolean-node"|"null-node"|"array-node") {SC}? "("  {return XQueryTypes.SLASH;}
"/" / {SC}? ("child"|"descendant"|"attribute"|"self"|"descendant-or-self"|"following-sibling"|"following"|"parent"|"ancestor"|"preceding-sibling"|"preceding"|"ancestor-or-self"|"namespace") {SC}? "::" {return XQueryTypes.SLASH;}
"/"                                        {pushState(QNAME);return XQueryTypes.SLASH;}
"+"                                        {return XQueryTypes.OP_PLUS;}
"-"                                        {return XQueryTypes.OP_MINUS;}
":="                                       {return XQueryTypes.OP_ASSIGN;}
"::"                                       {pushState(KIND_TEST);return XQueryTypes.COLON_COLON;}
":"                                        {return XQueryTypes.COLON;}
"?"                                        {return XQueryTypes.QUESTIONMARK;}
"$"                                        {pushState(QNAME);return XQueryTypes.DOLLAR_SIGN;}
".."                                       {return XQueryTypes.DOT_DOT;}
"."                                        {return XQueryTypes.DOT;}
"*"                                        {return XQueryTypes.STAR_SIGN;}
"(#"                                       {pushState(PRAGMA);return XQueryTypes.PRAGMA_BEGIN;}
"#)"                                       {return XQueryTypes.PRAGMA_END;}
"<?"                                       {pushState(PI);return XQueryTypes.PI_BEGIN;}
"?>"                                       {return XQueryTypes.PI_END;}
"("                                        {return XQueryTypes.L_PAR;}
")"                                        {return XQueryTypes.R_PAR;}
"["                                        {return XQueryTypes.L_BRACKET;}
"]"                                        {return XQueryTypes.R_BRACKET;}
"{"                                        {pushState(EXPRESSION); return XQueryTypes.L_C_BRACE;}
"}"                                        {popState(); return XQueryTypes.R_C_BRACE;}
"``["                                      {pushState(STRING_CONSTR); return XQueryTypes.STRING_CONSTR_START;}
"]``"                                      {popState(); return XQueryTypes.STRING_CONSTR_END;}
"}`"                                       {popState(); return XQueryTypes.STRING_CONSTR_EXPR_END;}
"`"                                        {return XQueryTypes.BACKTICK;}
","                                        {return XQueryTypes.COMMA;}
"!="                                       {return XQueryTypes.NOT_EQUAL;}
"="                                        {return XQueryTypes.EQUAL;}
";"                                        {return XQueryTypes.SEMICOLON;}
"#"                                        {return XQueryTypes.HASH;}
"||"                                       {return XQueryTypes.PIPE_PIPE;}
"|"                                        {return XQueryTypes.PIPE;}
"!"                                        {return XQueryTypes.EXCLAMATION_MARK;}
"=>"                                       {return XQueryTypes.OP_ARROW;}
"eq"                                       {return XQueryTypes.EQ;}
"ne"                                       {return XQueryTypes.NE;}
"lt"                                       {return XQueryTypes.LT;}
"le"                                       {return XQueryTypes.LE;}
"gt"                                       {return XQueryTypes.GT;}
"ge"                                       {return XQueryTypes.GE;}
"declare"                                  {yypushback(yylength()); pushState(DECLARATION_RECOGNITION); return TokenType.WHITE_SPACE;}
"namespace" / {SC} ({NCName}|"\""|"'"|"{") {return XQueryTypes.K_NAMESPACE;}
"empty" / {SC} ("greatest"|"least"|"at"|"in") {return XQueryTypes.K_EMPTY;}
"allowing" / {SC} "empty"                  {return XQueryTypes.K_ALLOWING;}
"greatest" / {SC}? (";"|",")               {return XQueryTypes.K_GREATEST;}
"greatest" / {SC} ("for"|"let"|"order"|"stable"|"group"|"count"|"return"|"collation") {return XQueryTypes.K_GREATEST;}
"least" / {SC}? (";"|",")                  {return XQueryTypes.K_LEAST;}
"least" / {SC} ("for"|"let"|"order"|"stable"|"group"|"count"|"return"|"collation") {return XQueryTypes.K_LEAST;}
"ascending" / {SC}? ","                    {return XQueryTypes.K_ASCENDING;}
"ascending" / {SC} ("empty"|"collation"|"for"|"let"|"order"|"stable"|"group"|"count"|"return"|"where"|",")   {return XQueryTypes.K_ASCENDING;}
"descending" / {SC}? ","                   {return XQueryTypes.K_DESCENDING;}
"descending" / {SC} ("empty"|"collation"|"for"|"let"|"order"|"stable"|"group"|"count"|"return"|"where"|",")  {return XQueryTypes.K_DESCENDING;}
"collation" / {SC} ("\""|"'")              {return XQueryTypes.K_COLLATION;}
"import"                                   {yypushback(yylength()); pushState(IMPORT_RECOGNITION); return TokenType.WHITE_SPACE;}
"module"                                   {yypushback(yylength()); pushState(MODULE_RECOGNITION); return TokenType.WHITE_SPACE;}
"xquery"                                   {yypushback(yylength()); pushState(XQUERY_RECOGNITION); return TokenType.WHITE_SPACE;}
"at" / {SC} ("\""|"'"|"$")                 {return XQueryTypes.K_AT;}
"return"                                   {return XQueryTypes.K_RETURN;}
"for" / {SC} ("$"|"tumbling"|"sliding")    {return XQueryTypes.K_FOR;}
"let" / {SC} "$"                           {return XQueryTypes.K_LET;}
"some" / {SC} "$"                          {return XQueryTypes.K_SOME;}
"every" / {SC} "$"                         {return XQueryTypes.K_EVERY;}
"in"                                       {return XQueryTypes.K_IN;}
"then"                                     {return XQueryTypes.K_THEN;}
"else"                                     {return XQueryTypes.K_ELSE;}
"case"                                     {return XQueryTypes.K_CASE;}
"and"                                      {return XQueryTypes.K_AND;}
"or"                                       {return XQueryTypes.K_OR;}
"as"/ ({SC}? "(" {SC}?|{SC}) (("item"|"node"|"document-node"|"text"|"element"|"map"|"array"|"attribute"|"schema-element"|"schema-attribute"|"processing-instruction"|"comment"|"namespace-node"|"%"|"function"|"binary"|"object-node"|"number-node"|"boolean-node"|"null-node"|"array-node") {SC}? "(")                        {pushState(ITEM_TYPE); return XQueryTypes.K_AS;}
"as"                                       {return XQueryTypes.K_AS;}
"to"                                       {return XQueryTypes.K_TO;}
"where"                                    {return XQueryTypes.K_WHERE;}
"group"                                    {{yypushback(yylength()); pushState(BY_RECOGNITION); return TokenType.WHITE_SPACE;}}
"instance" / {SC} "of"                     {return XQueryTypes.K_INSTANCE;}
"of"                                       {return XQueryTypes.K_OF;}
"satisfies"                                {return XQueryTypes.K_SATISFIES;}
"attribute" / {SC}? "::"                   {return XQueryTypes.K_ATTRIBUTE;}
"child" / {SC}? "::"                       {return XQueryTypes.K_CHILD;}
"descendant" / {SC}? "::"                  {return XQueryTypes.K_DESCENDANT;}
"self" / {SC}? "::"                        {return XQueryTypes.K_SELF;}
"descendant-or-self" / {SC}? "::"          {return XQueryTypes.K_DESCENDANT_OR_SELF;}
"following-sibling" / {SC}? "::"           {return XQueryTypes.K_FOLLOWING_SIBLING;}
"following" / {SC}? "::"                   {return XQueryTypes.K_FOLLOWING;}
"parent" / {SC}? "::"                      {return XQueryTypes.K_PARENT;}
"ancestor" / {SC}? "::"                    {return XQueryTypes.K_ANCESTOR;}
"preceding-sibling" / {SC}? "::"           {return XQueryTypes.K_PRECEDING_SIBLING;}
"preceding" / {SC}? "::"                   {return XQueryTypes.K_PRECEDING;}
"ancestor-or-self" / {SC}? "::"            {return XQueryTypes.K_ANCESTOR_OR_SELF;}
"namespace" / {SC}? "::"                   {return XQueryTypes.K_NAMESPACE;}
"tumbling" / {SC} "window"                 {return XQueryTypes.K_TUMBLING;}
"sliding" / {SC} "window"                  {return XQueryTypes.K_SLIDING;}
"window" / {SC} "$"                        {return XQueryTypes.K_WINDOW;}
"start" / {SC} ("$"|"when"|"at"|"previous"|"next") {return XQueryTypes.K_START;}
"when"                                     {return XQueryTypes.K_WHEN;}
"only" / {SC} "end"                        {return XQueryTypes.K_ONLY;}
"end" / {SC} ("$"|"when"|"at"|"previous"|"next") {return XQueryTypes.K_END;}
"when"                                     {return XQueryTypes.K_WHEN;}
"previous" / {SC} "$"                      {return XQueryTypes.K_PREVIOUS;}
"next" / {SC} "$"                          {return XQueryTypes.K_NEXT;}
"count" / {SC} "$"                         {return XQueryTypes.K_COUNT;}
"try" / {SC}? "{"                           {return XQueryTypes.K_TRY;}
"catch" / ({SC} "Q{"|{SC} "*"|{SC} {NCName}| {SC}? "(" {SC}? "$") {return XQueryTypes.K_CATCH;}
"div"                                      {return XQueryTypes.K_DIV;}
"idiv"                                     {return XQueryTypes.K_IDIV;}
"mod"                                      {return XQueryTypes.K_MOD;}
"union"                                    {return XQueryTypes.K_UNION;}
"intersect"                                {return XQueryTypes.K_INTERSECT;}
"except"                                   {return XQueryTypes.K_EXCEPT;}
"treat" / {SC} "as"                        {pushState(AS_RECOGNITION); return XQueryTypes.K_TREAT;}
"castable" / {SC} "as"                     {pushState(AS_RECOGNITION); return XQueryTypes.K_CASTABLE;}
"cast" / {SC} "as"                         {pushState(AS_RECOGNITION); return XQueryTypes.K_CAST;}
"is"                                       {return XQueryTypes.K_IS;}
"external" / {SC}? (":="|";")              {return XQueryTypes.K_EXTERNAL;}
"validate"                                 {yypushback(yylength()); pushState(VALIDATE_RECOGNITION); return TokenType.WHITE_SPACE;}
"order"                                    {yypushback(yylength()); pushState(BY_RECOGNITION); return TokenType.WHITE_SPACE;}
"map" / {SC}? "("                          {pushState(ITEM_TYPE); return XQueryTypes.K_MAP;}
"array" / {SC}? "("                        {pushState(ITEM_TYPE); return XQueryTypes.K_ARRAY;}
"attribute" / {SC}?"("                     {pushState(ITEM_TYPE); return XQueryTypes.K_ATTRIBUTE;}
"document-node" / {SC}? "("                {pushState(ITEM_TYPE); return XQueryTypes.K_DOCUMENT_NODE;}
"element" / {SC}?"("                       {pushState(ITEM_TYPE); return XQueryTypes.K_ELEMENT;}
"function" / {SC}? "("                     {pushState(ITEM_TYPE); return XQueryTypes.K_FUNCTION;}
"processing-instruction" / {SC}? "("       {pushState(ITEM_TYPE); return XQueryTypes.K_PI;}
"schema-attribute" / {SC}? "("             {pushState(ITEM_TYPE); return XQueryTypes.K_SCHEMA_ATTRIBUTE;}
"schema-element" / {SC}? "("               {pushState(ITEM_TYPE); return XQueryTypes.K_SCHEMA_ELEMENT;}
"empty-sequence" / {SC}? "("               {return XQueryTypes.K_EMPTY_SEQUENCE;}
"item" / {SC}? "("                         {return XQueryTypes.K_ITEM;}
"node" / {SC}? "("                         {return XQueryTypes.K_NODE;}
"namespace-node" / {SC}? "("               {return XQueryTypes.K_NAMESPACE_NODE;}
"map" / {SC}? "{"                          {return XQueryTypes.K_MAP;}
"array" / {SC}? "{"                        {return XQueryTypes.K_ARRAY;}
"attribute" / ({SC}?"{"|{SC}{NCName})      {return XQueryTypes.K_ATTRIBUTE;}
"comment" / {SC}? ("("|"{")                {return XQueryTypes.K_COMMENT;}
"element" / ({SC}?"{"| {SC}{NCName})       {return XQueryTypes.K_ELEMENT;}
"processing-instruction" / {SC}? ("{"|{NCName}) {return XQueryTypes.K_PI;}
"text" / {SC}? ("("|"{")                   {return XQueryTypes.K_TEXT;}
"binary" / {SC}? ("("|"{")                 {return XQueryTypes.K_BINARY;}
"object-node" / {SC}? ("("|"{")            {return XQueryTypes.K_OBJECT_NODE;}
"number-node" / {SC}? ("("|"{")            {return XQueryTypes.K_NUMBER_NODE;}
"boolean-node" / {SC}? ("("|"{")           {return XQueryTypes.K_BOOLEAN_NODE;}
"null-node" / {SC}? ("("|"{")              {return XQueryTypes.K_NULL_NODE;}
"array-node" / {SC}? ("("|"{")             {return XQueryTypes.K_ARRAY_NODE;}
"switch" / {SC}? ("(")                     {return XQueryTypes.K_SWITCH;}
"if" / {SC}? ("(")                         {return XQueryTypes.K_IF;}
"typeswitch" / {SC}? ("(")                 {return XQueryTypes.K_TYPESWITCH;}
"default" / {SC} ("$"|"return")            {return XQueryTypes.K_DEFAULT;}
"document" / {SC}? ("{")                   {return XQueryTypes.K_DOCUMENT;}
"stable"                                   {yypushback(yylength()); pushState(BY_RECOGNITION); return TokenType.WHITE_SPACE;}
"ordered" / {SC}? "{"                      {return XQueryTypes.K_ORDERED;}
"unordered" / {SC}? "{"                    {return XQueryTypes.K_UNORDERED;}
"insert" / {SC} ("node"|"nodes")           {pushState(UPDATE_FACILITY);return XQueryTypes.K_INSERT;}
"delete" / {SC} ("node"|"nodes")           {pushState(UPDATE_FACILITY);return XQueryTypes.K_DELETE;}
"after"                                    {return XQueryTypes.K_AFTER;}
"before"                                   {return XQueryTypes.K_BEFORE;}
"first" / {SC} "into"                      {return XQueryTypes.K_FIRST;}
"last" / {SC} "into"                       {return XQueryTypes.K_LAST;}
"into"                                     {return XQueryTypes.K_INTO;}
"replace" / {SC} ("value"|"node")          {pushState(UPDATE_FACILITY);return XQueryTypes.K_REPLACE;}
"with"                                     {return XQueryTypes.K_WITH;}
"rename" / {SC} "node"                     {pushState(UPDATE_FACILITY);return XQueryTypes.K_RENAME;}
"copy" / {SC} "$"                          {return XQueryTypes.K_COPY;}
"modify"                                   {return XQueryTypes.K_MODIFY;}
{NCName}                                   {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
}

<EXPR_COMMENT> {
":)"                                       {popState(); return XQueryBasicTypes.EXPR_COMMENT_END;}
"(:"                                       {pushState(EXPR_COMMENT); return XQueryBasicTypes.EXPR_COMMENT_START;}
{Char}                                     {return XQueryBasicTypes.EXPR_COMMENT_CONTENT;}
}

<MISPLACED_EXPR_COMMENT> {
":)"                                       {popState(); return XQueryTypes.EXPR_COMMENT_END;}
"(:"                                       {pushState(MISPLACED_EXPR_COMMENT); return XQueryTypes.EXPR_COMMENT_START;}
{Char}                                     {return XQueryTypes.EXPRCOMMENTCONTENT;}
}

<DOC_COMMENT> {
":)"                                       {popState(); return XQueryBasicTypes.DOC_COMMENT_END;}
{Char}                                     {return XQueryBasicTypes.DOC_COMMENT_CONTENT;}
}

<START_TAG> {
"(:"                                       {pushState(MISPLACED_EXPR_COMMENT); return XQueryTypes.EXPR_COMMENT_START;}
{S}                                        {return TokenType.WHITE_SPACE;}
{NCName}                                   {pushState(TAG_QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
">"                                        {popState();pushState(ELEMENT_CONTENT); return XQueryTypes.XMLTAGEND;}
"/>"                                       {popState(); return XQueryTypes.XMLEMPTYELEMENTEND;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<TAG_QNAME> {
{NCName} ":" {NameStartCharWithoutFirst}   {yypushback(2); return XQueryTypes.XMLTAGNCNAME;}
{NCName}                                   {pushState(ATTR_LIST); return XQueryTypes.XMLTAGNCNAME;}
":"                                        {return XQueryTypes.XMLCOLON;}
{S}                                        {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<ATTR_LIST> {
"(:"                                       {pushState(MISPLACED_EXPR_COMMENT); return XQueryTypes.EXPR_COMMENT_START;}
{S}                                        {return TokenType.WHITE_SPACE;}
{NCName}                                   {pushState(ATTR_QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
"="                                        {return XQueryTypes.ATTREQUAL;}
"\""                                       {pushState(QUOT_STRING); return XQueryTypes.OPENINGQUOT;}
"'"                                        {pushState(APOS_STRING); return XQueryTypes.OPENINGAPOS;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<ATTR_QNAME> {
{NCName} ":" {NameStartCharWithoutFirst}   {yypushback(2); return XQueryTypes.ATTRNCNAME;}
{NCName}                                   {popState(); return XQueryTypes.ATTRNCNAME;}
":"                                        {return XQueryTypes.ATTRCOLON;}
{S}                                        {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<ELEMENT_CONTENT> {
{S}                                        {return TokenType.WHITE_SPACE;}
"<!--"                                     {pushState(DIR_COMMENT); return XQueryTypes.DIR_COMMENT_BEGIN;}
"<![CDATA["                                {pushState(CDATA); return XQueryTypes.CDATA_BEGIN;}
{PredefinedEntityRef}                      {return XQueryTypes.PREDEFINEDENTITYREF;}
{CharRef}                                  {return XQueryTypes.CHARREF;}
"{{" | "}}" | [^{}<]                       {return XQueryTypes.ELEMENTCONTENTCHAR;}
"{"                                        {pushState(EXPRESSION); return XQueryTypes.L_C_BRACE; }
"</"                                       {popState(); pushState(END_TAG); return XQueryTypes.XMLENDTAGSTART;}
"<"                                        {pushState(START_TAG); return XQueryTypes.XMLSTARTTAGSTART; }
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<DIR_COMMENT> {
"--"                                       {return TokenType.BAD_CHARACTER;}
"-->"                                      {popState(); return XQueryTypes.DIR_COMMENT_END;}
{Char}                                     {return XQueryTypes.DIRCOMMENTCHAR;}
}

<END_TAG> {
"(:"                                       {pushState(MISPLACED_EXPR_COMMENT); return XQueryTypes.EXPR_COMMENT_START;}
{S}                                        {return TokenType.WHITE_SPACE;}
{NCName}                                   {return XQueryTypes.XMLTAGNCNAME;}
":"                                        {return XQueryTypes.XMLCOLON;}
">"                                        {popState(); return XQueryTypes.XMLTAGEND;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<STRING_CONSTR> {
"`{"                                       {pushState(EXPRESSION_IN_STRING_CONSTR); return XQueryTypes.STRING_CONSTR_EXPR_START;}
"}`"                                       {return XQueryTypes.STRING_CONSTR_EXPR_END;}
"]``"                                      {popState(); return XQueryTypes.STRING_CONSTR_END;}
{Char}                                     {return XQueryTypes.CHAR;}
}

<QUOT_STRING> {
{PredefinedEntityRef}                      {return XQueryTypes.PREDEFINEDENTITYREF;}
{CharRef}                                  {return XQueryTypes.CHARREF;}
"{{"                                       {return XQueryTypes.DBL_L_C_BRACE;}
"}}"                                       {return XQueryTypes.DBL_R_C_BRACE;}
"{"                                        {pushState(EXPRESSION_IN_QUOT_STRING); return XQueryTypes.L_C_BRACE; }
"\""                                       {popState(); return XQueryTypes.CLOSINGQUOT;}
{Char}                                     {return XQueryTypes.CHAR;}
}

<APOS_STRING> {
{PredefinedEntityRef}                      {return XQueryTypes.PREDEFINEDENTITYREF;}
{CharRef}                                  {return XQueryTypes.CHARREF;}
"{{"                                       {return XQueryTypes.DBL_L_C_BRACE;}
"}}"                                       {return XQueryTypes.DBL_R_C_BRACE;}
"{"                                        {pushState(EXPRESSION_IN_APOS_STRING); return XQueryTypes.L_C_BRACE; }
"'"                                        {popState(); return XQueryTypes.CLOSINGAPOS;}
{Char}                                     {return XQueryTypes.CHAR;}
}

<EXPRESSION_IN_QUOT_STRING> {
.                                          {yypushback(yylength()); popStateOrDefaultTo(QUOT_STRING); return TokenType.WHITE_SPACE;}
}
<EXPRESSION_IN_APOS_STRING> {
.                                          {yypushback(yylength()); popStateOrDefaultTo(APOS_STRING); return TokenType.WHITE_SPACE;}
}
<EXPRESSION_IN_STRING_CONSTR> {
.                                          {yypushback(yylength()); popStateOrDefaultTo(STRING_CONSTR); return TokenType.WHITE_SPACE;}
}
<EXPRESSION> {
.                                          {return TokenType.BAD_CHARACTER;}
}

<URIQUALIFIED> {
{S}                                        {return TokenType.WHITE_SPACE;}
{URIQualifiedName}                         {popState(); return XQueryTypes.URIQUALIFIEDNAME;}
}

<WS_BEFORE_QNAME> {
{S}                                        {popState(); pushState(QNAME); return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); pushState(QNAME); return TokenType.WHITE_SPACE;}
}

<QNAME> {
"*"                                        {return XQueryTypes.STAR_SIGN;}
{NCName} ":" {NameStartCharWithoutFirst}   {yypushback(2); return XQueryTypes.NCNAME;}
{NCName}                                   {popState(); return XQueryTypes.NCNAME;}
":"                                        {return XQueryTypes.COLON;}
{S}                                        {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<XQUERY_RECOGNITION> {
{S}                                        {return TokenType.WHITE_SPACE;}
"\""                                       {pushState(QUOT_STRING_SIMPLE);return XQueryTypes.OPENINGQUOT;}
"'"                                        {pushState(APOS_STRING_SIMPLE);return XQueryTypes.OPENINGAPOS;}
"xquery" / {S} ("encoding"|"version")      {return XQueryTypes.K_XQUERY;}
"version"                                  {return XQueryTypes.K_VERSION;}
"encoding"                                 {return XQueryTypes.K_ENCODING;}
{NCName}                                   {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<DECLARATION_RECOGNITION> {
{S}                                        {return TokenType.WHITE_SPACE;}
"(:~"                                      {pushState(DOC_COMMENT);return XQueryBasicTypes.DOC_COMMENT_START;}
"(:"                                       {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"{"                                        {pushState(EXPRESSION);return XQueryTypes.L_C_BRACE;}
"="                                        {return XQueryTypes.EQUAL;}
"\""                                       {pushState(QUOT_STRING_SIMPLE);return XQueryTypes.OPENINGQUOT;}
"'"                                        {pushState(APOS_STRING_SIMPLE);return XQueryTypes.OPENINGAPOS;}
"declare" / {SC} ("boundary-space"|"default"|"base-uri"|"construction"|"ordering"|"copy-namespaces"|"decimal-format"|"namespace"|"context"|"option"|"function"|"variable"|"%"|"updating"|"revalidation"|"private") {return XQueryTypes.K_DECLARE;}
"default"                                  {pushState(DEFAULT_RECOGNITION);return XQueryTypes.K_DEFAULT;}
"base-uri"                                 {return XQueryTypes.K_BASE_URI;}
"option"                                   {return XQueryTypes.K_OPTION;}
"variable"                                 {return XQueryTypes.K_VARIABLE;}
"function"                                 {pushState(WS_BEFORE_QNAME); return XQueryTypes.K_FUNCTION;}
"boundary-space"                           {return XQueryTypes.K_BOUNDARY_SPACE;}
"construction"                             {return XQueryTypes.K_CONSTRUCTION;}
"ordering"                                 {return XQueryTypes.K_ORDERING;}
"ordered"                                  {return XQueryTypes.K_ORDERED;}
"unordered"                                {return XQueryTypes.K_UNORDERED;}
"copy-namespaces"                          {return XQueryTypes.K_COPY_NAMESPACES;}
"preserve"                                 {return XQueryTypes.K_PRESERVE;}
"no-preserve"                              {return XQueryTypes.K_NO_PRESERVE;}
"decimal-format"                           {return XQueryTypes.K_DECIMAL_FORMAT;}
"decimal-separator"                        {return XQueryTypes.K_DECIMAL_SEPARATOR;}
"grouping-separator"                       {return XQueryTypes.K_GROUPING_SEPARATOR;}
"infinity"                                 {return XQueryTypes.K_INFINITY;}
"minus-sign"                               {return XQueryTypes.K_MINUS_SIGN;}
"NaN"                                      {return XQueryTypes.K_NAN;}
"percent"                                  {return XQueryTypes.K_PERCENT;}
"per-mille"                                {return XQueryTypes.K_PER_MILLE;}
"zero-digit"                               {return XQueryTypes.K_ZERO_DIGIT;}
"digit"                                    {return XQueryTypes.K_DIGIT;}
"pattern-separator"                        {return XQueryTypes.K_PATTERN_SEPARATOR;}
"exponent-separator"                       {return XQueryTypes.K_EXPONENT_SEPARATOR;}
"namespace"                                {return XQueryTypes.K_NAMESPACE;}
"context"                                  {return XQueryTypes.K_CONTEXT;}
"item"                                     {return XQueryTypes.K_ITEM;}
"as"/ ({SC}? "(" {SC}?|{SC}) (("item"|"node"|"document-node"|"text"|"element"|"map"|"array"|"attribute"|"schema-element"|"schema-attribute"|"processing-instruction"|"comment"|"namespace-node"|"%"|"function"|"binary"|"object-node"|"number-node"|"boolean-node"|"null-node"|"array-node") {SC}? "(" | {NCName})                        {pushState(ITEM_TYPE); return XQueryTypes.K_AS;}
"empty-sequence"                           {return XQueryTypes.K_EMPTY_SEQUENCE;}
"item"                                     {return XQueryTypes.K_ITEM;}
"external"                                 {return XQueryTypes.K_EXTERNAL;}
"empty"                                    {return XQueryTypes.K_EMPTY;}
"strict"                                   {return XQueryTypes.K_STRICT;}
"strip"                                    {return XQueryTypes.K_STRIP;}
"lax"                                      {return XQueryTypes.K_LAX;}
"skip"                                     {return XQueryTypes.K_SKIP;}
"collation"                                {return XQueryTypes.K_COLLATION;}
"%"                                        {pushState(QNAME); return XQueryTypes.PERCENT;}
"("                                        {return XQueryTypes.L_PAR;}
")"                                        {return XQueryTypes.R_PAR;}
","                                        {return XQueryTypes.COMMA;}
"order"                                    {return XQueryTypes.K_ORDER;}
"greatest"                                 {return XQueryTypes.K_GREATEST;}
"least"                                    {return XQueryTypes.K_LEAST;}
"inherit"                                  {return XQueryTypes.K_INHERIT;}
"no-inherit"                               {return XQueryTypes.K_NO_INHERIT;}
"updating"                                 {return XQueryTypes.K_UPDATING;}
"revalidation"                             {return XQueryTypes.K_REVALIDATION;}
"private"                                  {return XQueryTypes.K_PRIVATE;}
{NCName}                                   {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<IMPORT_RECOGNITION> {
{S}                                        {return TokenType.WHITE_SPACE;}
"\""                                       {pushState(QUOT_STRING_SIMPLE);return XQueryTypes.OPENINGQUOT;}
"'"                                        {pushState(APOS_STRING_SIMPLE);return XQueryTypes.OPENINGAPOS;}
"(:~"                                      {pushState(DOC_COMMENT);return XQueryBasicTypes.DOC_COMMENT_START;}
"(:"                                       {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"import" / {SC} ("schema"|"module")        {return XQueryTypes.K_IMPORT;}
"schema"                                   {return XQueryTypes.K_SCHEMA;}
"default"                                  {return XQueryTypes.K_DEFAULT;}
"module"                                   {return XQueryTypes.K_MODULE;}
"element"                                  {return XQueryTypes.K_ELEMENT;}
"namespace"                                {return XQueryTypes.K_NAMESPACE;}
"at"                                       {return XQueryTypes.K_AT;}
","                                        {return XQueryTypes.COMMA;}
"="                                        {return XQueryTypes.EQUAL;}
{NCName}                                   {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<MODULE_RECOGNITION> {
{S}                                        {return TokenType.WHITE_SPACE;}
"(:~"                                      {pushState(DOC_COMMENT);return XQueryBasicTypes.DOC_COMMENT_START;}
"(:"                                       {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"module" / {SC}  "namespace"               {return XQueryTypes.K_MODULE;}
"namespace"                                {return XQueryTypes.K_NAMESPACE;}
{NCName}                                   {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<DEFAULT_RECOGNITION> {
{S}                                        {return TokenType.WHITE_SPACE;}
"(:~"                                      {pushState(DOC_COMMENT);return XQueryBasicTypes.DOC_COMMENT_START;}
"(:"                                       {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"function"                                 {return XQueryTypes.K_FUNCTION;}
"element"                                  {return XQueryTypes.K_ELEMENT;}
"collation"                                {return XQueryTypes.K_COLLATION;}
"order"                                    {return XQueryTypes.K_ORDER;}
"decimal-format"                           {return XQueryTypes.K_DECIMAL_FORMAT;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}


<QUOT_STRING_SIMPLE> {
{PredefinedEntityRef}                      {return XQueryTypes.PREDEFINEDENTITYREF;}
{CharRef}                                  {return XQueryTypes.CHARREF;}
{EscapeQuot}                               {return XQueryTypes.STRINGCHAR;}
[^\"&]                                     {return XQueryTypes.STRINGCHAR;}
"&"                                        {return XQueryTypes.AMPERSAND;}
"\""                                       {popState(); return XQueryTypes.CLOSINGQUOT;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<APOS_STRING_SIMPLE> {
{PredefinedEntityRef}                      {return XQueryTypes.PREDEFINEDENTITYREF;}
{CharRef}                                  {return XQueryTypes.CHARREF;}
{EscapeApos}                               {return XQueryTypes.STRINGCHAR;}
[^\'&]                                     {return XQueryTypes.STRINGCHAR;}
"&"                                        {return XQueryTypes.AMPERSAND;}
"'"                                        {popState(); return XQueryTypes.CLOSINGAPOS;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<PRAGMA> {
{S}                                        {return XQueryTypes.S;}
{NCName}                                   {popState();pushState(PRAGMA_BEFORE_CONTENT);return XQueryTypes.NCNAME;}
"#)"                                       {popState();return XQueryTypes.PRAGMA_END;}
}

<PRAGMA_BEFORE_CONTENT> {
{S}                                        {popState();pushState(PRAGMA_CONTENT);return XQueryTypes.S;}
"#)"                                       {popState();return XQueryTypes.PRAGMA_END;}
}

<PRAGMA_CONTENT> {
{Char}                                     {return XQueryTypes.PRAGMACONTENTCHAR;}
"#)"                                       {popState();return XQueryTypes.PRAGMA_END;}
}

<CDATA> {
{Char}                                     {return XQueryTypes.CDATASECTIONCONTENTCHAR;}
"]]>"                                      {popState();return XQueryTypes.CDATA_END;}
}

<PI> {
{S}                                        {return XQueryTypes.S;}
{Name}                                     {popState();pushState(PI_BEFORE_CONTENT);return XQueryTypes.PITARGET;}
"?>"                                       {popState();return XQueryTypes.PRAGMA_END;}
}

<PI_BEFORE_CONTENT> {
{S}                                        {popState();pushState(PI_CONTENT);return XQueryTypes.S;}
"?>"                                       {popState();return XQueryTypes.PI_END;}
}

<PI_CONTENT> {
{Char}                                     {return XQueryTypes.DIRPICONTENTCHAR;}
"?>"                                       {popState();return XQueryTypes.PI_END;}
}

<AS_RECOGNITION> {
{S}                                        {return TokenType.WHITE_SPACE;}
"(:"                                       {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"as"                                       {popState(); pushState(ITEM_TYPE); return XQueryTypes.K_AS;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<VALIDATE_RECOGNITION> {
{S}                                        {return TokenType.WHITE_SPACE;}
"(:"                                       {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"validate" / {SC} ("lax"|"strict"|"type"|"as")  {return XQueryTypes.K_VALIDATE;}
"validate" / {SC}? "{"                     {return XQueryTypes.K_VALIDATE;}
"lax"                                      {return XQueryTypes.K_LAX;}
"strict"                                   {return XQueryTypes.K_STRICT;}
"type"                                     {return XQueryTypes.K_TYPE;}
"as"                                       {return XQueryTypes.K_AS;}
{NCName}                                   {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<BY_RECOGNITION> {
{S}                                        {return TokenType.WHITE_SPACE;}
"(:"                                       {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"stable" / {SC} "order"                    {return XQueryTypes.K_STABLE;}
"order" / {SC} "by"                        {return XQueryTypes.K_ORDER;}
"group" / {SC} "by"                        {return XQueryTypes.K_GROUP;}
"by"                                       {return XQueryTypes.K_BY;}
{NCName}                                   {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<ITEM_TYPE> {
"("                                        {return XQueryTypes.L_PAR;}
")"                                        {popState(); return XQueryTypes.R_PAR;}
","                                        {return XQueryTypes.COMMA;}
"*"                                        {return XQueryTypes.STAR_SIGN;}
"+"                                        {return XQueryTypes.OP_PLUS;}
"?"                                        {return XQueryTypes.QUESTIONMARK;}
"%"                                        {return XQueryTypes.PERCENT;}
"function" / {SC}? "("                     {return XQueryTypes.K_FUNCTION;}
"empty-sequence" / {SC}? "("               {return XQueryTypes.K_EMPTY_SEQUENCE;}
"item" / {SC}? "("                         {return XQueryTypes.K_ITEM;}
}

<KIND_TEST,ITEM_TYPE> {
{S}                                        {return TokenType.WHITE_SPACE;}
"(:"                                       {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"attribute" / {SC}? "("                    {return XQueryTypes.K_ATTRIBUTE;}
"binary" / {SC}? "("                       {return XQueryTypes.K_BINARY;}
"comment" / {SC}? "("                      {return XQueryTypes.K_COMMENT;}
"document-node" / {SC}? "("                {return XQueryTypes.K_DOCUMENT_NODE;}
"element" / {SC}? "("                      {return XQueryTypes.K_ELEMENT;}
"map" / {SC}? "("                          {return XQueryTypes.K_MAP;}
"array" / {SC}? "("                        {return XQueryTypes.K_ARRAY;}
"namespace-node" / {SC}? "("               {return XQueryTypes.K_NAMESPACE_NODE;}
"node" / {SC}? "("                         {return XQueryTypes.K_NODE;}
"processing-instruction" / {SC}? "("       {return XQueryTypes.K_PI;}
"schema-attribute" / {SC}? "("             {return XQueryTypes.K_SCHEMA_ATTRIBUTE;}
"schema-element" / {SC}? "("               {return XQueryTypes.K_SCHEMA_ELEMENT;}
"text" / {SC}? "("                         {return XQueryTypes.K_TEXT;}
"object-node" / {SC}? "("                  {return XQueryTypes.K_OBJECT_NODE;}
"number-node" / {SC}? "("                  {return XQueryTypes.K_NUMBER_NODE;}
"boolean-node" / {SC}? "("                 {return XQueryTypes.K_BOOLEAN_NODE;}
"null-node" / {SC}? "("                    {return XQueryTypes.K_NULL_NODE;}
"array-node" / {SC}? "("                   {return XQueryTypes.K_ARRAY_NODE;}
{NCName}                                   {popState(); pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<UPDATE_FACILITY> {
{S}                                        {return TokenType.WHITE_SPACE;}
"node"                                     {return XQueryTypes.K_NODE;}
"nodes"                                    {return XQueryTypes.K_NODES;}
"value" / {SC} "of"                        {return XQueryTypes.K_VALUE;}
"of"                                       {return XQueryTypes.K_OF;}
.                                          {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

.                                          {return TokenType.BAD_CHARACTER;}