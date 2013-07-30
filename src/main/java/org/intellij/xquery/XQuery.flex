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

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.intellij.xquery.psi.XQueryBasicTypes;
import org.intellij.xquery.psi.XQueryTypes;
import com.intellij.psi.TokenType;
import java.util.Stack;

%%

%{

  public _XQueryLexer() {
    this((java.io.Reader)null);
  }


  private Stack<Integer> stack = new Stack<Integer>();

  private void pushState(int state) {
    stack.push(yystate());
    yybegin(state);
  }

  private void popState() {
    if (stack.empty()) {
        yybegin(YYINITIAL);

    } else {
        int state = stack.pop();
        yybegin(state);
    }
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
StringLiteral=("\"" ({PredefinedEntityRef} | {CharRef} | {EscapeQuot} | [^\"&])* "\"") | ("'" ({PredefinedEntityRef} | {CharRef} | {EscapeApos} | [^'&])* "'") 	/* ws: explicit */
URIQualifiedName={BracedURILiteral} {NCName}                                                /* ws: explicit */
BracedURILiteral="Q" "{" ({PredefinedEntityRef} | {CharRef} | [^&{}]    )* "}"                  /* ws: explicit */
PredefinedEntityRef="&" ("lt" | "gt" | "amp" | "quot" | "apos") ";"                         /* ws: explicit */
EscapeQuot="\"\""
EscapeApos="''"
Digits=[0-9]+
Comment="(:" ({CommentContents} | {Comment})* ":)"                                          /* ws: explicit */ /* gn: comments */
CommentContents=({Char}+ - ({Char}* ("(:" | ":)") {Char}*))
DirCommentContents=(({Char} - '-') | ('-' ({Char} - '-')))*                                 /* ws: explicit */
PITarget={Name} - (("X" | "x") ("M" | "m") ("L" | "l"))                                     /* xgc: xml-version */
CharRef="&#" [0-9]+ ";" | "&#x" [0-9a-fA-F]+ ";"                                            /* xgc: xml-version */
NCName={NameStartCharWithoutFirst} ({NameCharWithoutFirst})*                                                     /* xgc: xml-version */
NameStartChar=":" | {NameStartCharWithoutFirst}
NameStartCharWithoutFirst= [A-Z] | "_" | [a-z] | [\u00C0-\u00D6] | [\u00D8-\u00F6] | [\u00F8-\u02FF] | [\u0370-\u037D] | [\u037F-\u1FFF] | [\u200C-\u200D] | [\u2070-\u218F] | [\u2C00-\u2FEF] | [\u3001-\uD7FF] | [\uF900-\uFDCF] | [\uFDF0-\uFFFD] | [\uD800\uDC00-\uDB7F\uDFFF]
NameChar={NameStartChar} | "-" | "." | [0-9] | \u00B7 | [\u0300-\u036F] | [\u203F-\u2040]
NameCharWithoutFirst={NameStartCharWithoutFirst} | "-" | "." | [0-9] | \u00B7 | [\u0300-\u036F] | [\u203F-\u2040]
Name={NameStartChar} ({NameChar})*
S=(\u20 | \u9 | \uD | \uA)+                                                                 /* xgc: xml-version */
Char=\u9| \uA | \uD | [\u20-\uD7FF] | [\uE000-\uFFFD] | [\u10000-\u10FFFF]                  /* xgc: xml-version */

%state EXPR_COMMENT
%state START_TAG
%state END_TAG
%state ELEMENT_CONTENT
%state QUOT_STRING
%state APOS_STRING
%state QUOT_STRING_SIMPLE
%state APOS_STRING_SIMPLE
%state URIQUALIFIED
%state QNAME
%state DIR_COMMENT
// helper states for better support of live syntax highlighting
%state XQUERY_RECOGNITION
%state DECLARATION_RECOGNITION
%state IMPORT_RECOGNITION
%state MODULE_RECOGNITION
%%


<YYINITIAL> {
{S}                                       {return TokenType.WHITE_SPACE;}
{DecimalLiteral}                          {return XQueryTypes.DECIMALLITERAL;}
{DoubleLiteral}                           {return XQueryTypes.DOUBLELITERAL;}
{IntegerLiteral}                          {return XQueryTypes.INTEGERLITERAL;}
"\""                                      {pushState(QUOT_STRING_SIMPLE);yypushback(yylength());return TokenType.WHITE_SPACE;}
"'"                                       {pushState(APOS_STRING_SIMPLE);yypushback(yylength());return TokenType.WHITE_SPACE;}
"Q{"                                      {pushState(URIQUALIFIED); yypushback(2);}
"(:"                                      {pushState(EXPR_COMMENT);return XQueryBasicTypes.EXPR_COMMENT_START;}
"<<"                                      {return XQueryTypes.NODECOMP_LT;}
">>"                                      {return XQueryTypes.NODECOMP_GT;}
"<" / {S}? "$"                            {return XQueryTypes.LT_CHAR;}
"<" / {S}? {IntegerLiteral}               {return XQueryTypes.LT_CHAR;}
"<" / {S}? {DecimalLiteral}               {return XQueryTypes.LT_CHAR;}
"<" / {S}? {DoubleLiteral}                {return XQueryTypes.LT_CHAR;}
"<="                                      {return XQueryTypes.LE_CHARS;}
">="                                      {return XQueryTypes.GE_CHARS;}
"<"                                       {pushState(START_TAG); return XQueryTypes.LT_CHAR;}
">"                                       {return XQueryTypes.GT_CHAR;}
"@"                                       {pushState(QNAME);return XQueryTypes.AT_SIGN;}
"//"                                      {pushState(QNAME);return XQueryTypes.SLASH_SLASH;}
"/"                                       {pushState(QNAME);return XQueryTypes.SLASH;}
"+"                                       {return XQueryTypes.OP_PLUS;}
"-"                                       {return XQueryTypes.OP_MINUS;}
":="                                      {return XQueryTypes.OP_ASSIGN;}
"::"                                      {return XQueryTypes.COLON_COLON;}
":"                                       {return XQueryTypes.COLON;}
"?"                                       {return XQueryTypes.QUESTIONMARK;}
"$"                                       {pushState(QNAME);return XQueryTypes.DOLLAR_SIGN;}
".."                                      {return XQueryTypes.DOT_DOT;}
"."                                       {return XQueryTypes.DOT;}
"*"                                       {return XQueryTypes.STAR_SIGN;}
"(#"                                      {return XQueryTypes.PRAGMA_BEGIN;}
"#)"                                      {return XQueryTypes.PRAGMA_END;}
"("                                       {return XQueryTypes.L_PAR;}
")"                                       {return XQueryTypes.R_PAR;}
"["                                       {return XQueryTypes.L_BRACKET;}
"]"                                       {return XQueryTypes.R_BRACKET;}
"{"                                       {pushState(YYINITIAL); return XQueryTypes.L_C_BRACE;}
"}"                                       {popState(); return XQueryTypes.R_C_BRACE;}
","                                       {return XQueryTypes.COMA;}
"=="                                      {return XQueryTypes.EQUAL_EQUAL;}
"!="                                      {return XQueryTypes.NOT_EQUAL;}
"="                                       {return XQueryTypes.EQUAL;}
";"                                       {return XQueryTypes.SEMICOLON;}
"#"                                       {return XQueryTypes.HASH;}
"||"                                      {return XQueryTypes.PIPE_PIPE;}
"|"                                       {return XQueryTypes.PIPE;}
"eq"                                      {return XQueryTypes.EQ;}
"ne"                                      {return XQueryTypes.NE;}
"lt"                                      {return XQueryTypes.LT;}
"le"                                      {return XQueryTypes.LE;}
"gt"                                      {return XQueryTypes.GT;}
"ge"                                      {return XQueryTypes.GE;}
"declare"                                 {yypushback(yylength()); pushState(DECLARATION_RECOGNITION); return TokenType.WHITE_SPACE;}
"namespace" / {S} ({NCName}|"\""|"'"|"{") {return XQueryTypes.K_NAMESPACE;}
"empty" / {S} ("greates"|"least"|"at"|"in") {return XQueryTypes.K_EMPTY;}
"allowing" / {S} "empty"                  {return XQueryTypes.K_ALLOWING;}
"greatest" / {S}? (";"|",")               {return XQueryTypes.K_GREATEST;}
"greatest" / {S} ("for"|"let"|"order"|"stable"|"group"|"count"|"return") {return XQueryTypes.K_GREATEST;}
"least"/ {S}? (";"|",")                   {return XQueryTypes.K_LEAST;}
"least" / {S} ("for"|"let"|"order"|"stable"|"group"|"count"|"return") {return XQueryTypes.K_LEAST;}
"ascending" / {S} ("empty"|"collation"|"for"|"let"|"order"|"stable"|"group"|"count"|"return")   {return XQueryTypes.K_ASCENDING;}
"descending" / {S} ("empty"|"collation"|"for"|"let"|"order"|"stable"|"group"|"count"|"return")  {return XQueryTypes.K_DESCENDING;}
"inherit" / {S}? ";"                      {return XQueryTypes.K_INHERIT;}
"no-inherit" / {S}? ";"                   {return XQueryTypes.K_NO_INHERIT;}
"import"                                  {yypushback(yylength()); pushState(IMPORT_RECOGNITION); return TokenType.WHITE_SPACE;}
"module"                                  {yypushback(yylength()); pushState(MODULE_RECOGNITION); return TokenType.WHITE_SPACE;}
"xquery"                                  {yypushback(yylength()); pushState(XQUERY_RECOGNITION); return TokenType.WHITE_SPACE;}
"at" / {S} ("\""|"'"|"$")                 {return XQueryTypes.K_AT;}
"return"                                  {return XQueryTypes.K_RETURN;}
"for" / {S} ("$"|"tumbling"|"sliding")    {return XQueryTypes.K_FOR;}
"let" / {S} "$"                           {return XQueryTypes.K_LET;}
"some" / {S} "$"                          {return XQueryTypes.K_SOME;}
"every" / {S} "$"                         {return XQueryTypes.K_EVERY;}
"in"                                      {return XQueryTypes.K_IN;}
"then"                                    {return XQueryTypes.K_THEN;}
"else"                                    {return XQueryTypes.K_ELSE;}
"case"                                    {return XQueryTypes.K_CASE;}
"and"                                     {return XQueryTypes.K_AND;}
"or"                                      {return XQueryTypes.K_OR;}
"as"                                      {return XQueryTypes.K_AS;}
"to"                                      {return XQueryTypes.K_TO;}
"where"                                   {return XQueryTypes.K_WHERE;}
"group" / {S} "by"                        {return XQueryTypes.K_GROUP;}
"by"                                      {return XQueryTypes.K_BY;}
"instance" / {S} "of"                     {return XQueryTypes.K_INSTANCE;}
"of"                                      {return XQueryTypes.K_OF;}
"satisfies"                               {return XQueryTypes.K_SATISFIES;}
"child" / {S}? "::"                       {return XQueryTypes.K_CHILD;}
"descendant" / {S}? "::"                  {return XQueryTypes.K_DESCENDANT;}
"self" / {S}? "::"                        {return XQueryTypes.K_SELF;}
"descendant-or-self" / {S}? "::"          {return XQueryTypes.K_DESCENDANT_OR_SELF;}
"following-sibling" / {S}? "::"           {return XQueryTypes.K_FOLLOWING_SIBLING;}
"following" / {S}? "::"                   {return XQueryTypes.K_FOLLOWING;}
"parent" / {S}? "::"                      {return XQueryTypes.K_PARENT;}
"ancestor" / {S}? "::"                    {return XQueryTypes.K_ANCESTOR;}
"preceding-sibling" / {S}? "::"           {return XQueryTypes.K_PRECEDING_SIBLING;}
"preceding" / {S}? "::"                   {return XQueryTypes.K_PRECEDING;}
"ancestor-or-self" / {S}? "::"            {return XQueryTypes.K_ANCESTOR_OR_SELF;}
"tumbling" / {S} "window"                 {return XQueryTypes.K_TUMBLING;}
"sliding" / {S} "window"                  {return XQueryTypes.K_SLIDING;}
"window" / {S} "$"                        {return XQueryTypes.K_WINDOW;}
"start" / {S} ("$"|"when"|"at"|"previous"|"next") {return XQueryTypes.K_START;}
"when"                                    {return XQueryTypes.K_WHEN;}
"only" / {S} "end"                        {return XQueryTypes.K_ONLY;}
"end" / {S} ("$"|"when"|"at"|"previous"|"next") {return XQueryTypes.K_END;}
"when"                                    {return XQueryTypes.K_WHEN;}
"previous" / {S} "$"                      {return XQueryTypes.K_PREVIOUS;}
"next" / {S} "$"                          {return XQueryTypes.K_NEXT;}
"count" / {S} "$"                         {return XQueryTypes.K_COUNT;}
"try" / {S} "{"                           {return XQueryTypes.K_TRY;}
"catch" / {S} ("Q{"|"*"|{NCName})         {return XQueryTypes.K_CATCH;}
"div"                                     {return XQueryTypes.K_DIV;}
"idiv"                                    {return XQueryTypes.K_IDIV;}
"mod"                                     {return XQueryTypes.K_MOD;}
"union"                                   {return XQueryTypes.K_UNION;}
"intersect"                               {return XQueryTypes.K_INTERSECT;}
"except"                                  {return XQueryTypes.K_EXCEPT;}
"treat" / {S} "as"                        {return XQueryTypes.K_TREAT;}
"castable" / {S} "as"                     {return XQueryTypes.K_CASTABLE;}
"cast" / {S} "as"                         {return XQueryTypes.K_CAST;}
"is"                                      {return XQueryTypes.K_IS;}
"type" / {S} ({NCName}|"Q{")              {return XQueryTypes.K_TYPE;}
"lax" / {S}? "{"                          {return XQueryTypes.K_LAX;}
"strict" / {S}? "{"                       {return XQueryTypes.K_STRICT;}
"external" / {S}? (":="|";")              {return XQueryTypes.K_EXTERNAL;}
"validate" / {S} ("lax"|"strict"|"type")  {return XQueryTypes.K_VALIDATE;}
"order" / {S} "by"                        {return XQueryTypes.K_ORDER;}
"map" / {S}? ("("|"{")                    {return XQueryTypes.K_MAP;}
"attribute" / ({S}?"("|{S}?"{"|{S}{NCName})     {return XQueryTypes.K_ATTRIBUTE;}
"comment" / {S}? ("("|"{")                {return XQueryTypes.K_COMMENT;}
"document-node" / {S}? ("(")              {return XQueryTypes.K_DOCUMENT_NODE;}
"element" / ({S}?"("|{S}?"{"| {S}{NCName})       {return XQueryTypes.K_ELEMENT;}
"empty-sequence" / {S}? ("(")             {return XQueryTypes.K_EMPTY_SEQUENCE;}
"function" / {S}? ("(")                   {return XQueryTypes.K_FUNCTION;}
"if" / {S}? ("(")                         {return XQueryTypes.K_IF;}
"item" / {S}? ("(")                       {return XQueryTypes.K_ITEM;}
"namespace-node" / {S}? ("(")             {return XQueryTypes.K_NAMESPACE_NODE;}
"node" / {S}? ("(")                       {return XQueryTypes.K_NODE;}
"processing-instruction" / {S}? ("("|"{"|{NCName}) {return XQueryTypes.K_PI;}
"schema-attribute" / {S}? ("(")           {return XQueryTypes.K_SCHEMA_ATTRIBUTE;}
"schema-element" / {S}? ("(")             {return XQueryTypes.K_SCHEMA_ELEMENT;}
"switch" / {S}? ("(")                     {return XQueryTypes.K_SWITCH;}
"text" / {S}? ("("|"{")                   {return XQueryTypes.K_TEXT;}
"typeswitch" / {S}? ("(")                 {return XQueryTypes.K_TYPESWITCH;}
"default" / {S} ("$"|"return")            {return XQueryTypes.K_DEFAULT;}
"document" / {S}? ("{")                   {return XQueryTypes.K_DOCUMENT;}
"stable" / {S} "order"                    {return XQueryTypes.K_STABLE;}
{NCName}                                  {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
}

<EXPR_COMMENT> {
":)"                                      {popState(); return XQueryBasicTypes.EXPR_COMMENT_END;}
"(:"                                      {pushState(EXPR_COMMENT); return XQueryBasicTypes.EXPR_COMMENT_START;}
{Char}                                    {return XQueryBasicTypes.EXPR_COMMENT_CONTENT;}
}

<START_TAG> {
{S}                                       {return XQueryTypes.S;}
{NCName}                                  {return XQueryTypes.NCNAME;}
":"                                       {return XQueryTypes.COLON;}
"="                                       {return XQueryTypes.EQUAL;}
"\""                                      {pushState(QUOT_STRING); return XQueryTypes.QUOT;}
"'"                                       {pushState(APOS_STRING); return XQueryTypes.APOSTROPHE;}
">"                                       {popState();pushState(ELEMENT_CONTENT); return XQueryTypes.GT_CHAR;}
"/>"                                      {popState(); return XQueryTypes.CLOSE_TAG;}
}

<ELEMENT_CONTENT> {
{S}                                       {return TokenType.WHITE_SPACE;}
"<!--"                                    {pushState(DIR_COMMENT); return XQueryTypes.DIR_COMMENT_BEGIN;}
"{{" | "}}" | [^{}<]+                     {return XQueryTypes.ELEMENTCONTENTCHAR;}
"{"                                       {pushState(YYINITIAL); return XQueryTypes.L_C_BRACE; }
"</"                                      {popState(); pushState(END_TAG); return XQueryTypes.END_TAG;}
"<"                                       {pushState(START_TAG); return XQueryTypes.LT_CHAR; }
}

<DIR_COMMENT> {
"--"                                      {return TokenType.BAD_CHARACTER;}
"-->"                                     {popState(); return XQueryTypes.DIR_COMMENT_END;}
{Char}                                    {return XQueryTypes.DIRCOMMENTCHAR;}
}

<END_TAG> {
{S}                                       {return XQueryTypes.S;}
{NCName}                                  {return XQueryTypes.NCNAME;}
":"                                       {return XQueryTypes.COLON;}
">"                                       {popState(); return XQueryTypes.GT_CHAR;}
}

<QUOT_STRING> {
{PredefinedEntityRef}                     {return XQueryTypes.PREDEFINEDENTITYREF;}
{CharRef}                                 {return XQueryTypes.CHARREF;}
"{{"                                      {return XQueryTypes.DBL_L_C_BRACE;}
"}}"                                      {return XQueryTypes.DBL_R_C_BRACE;}
"{"                                       {pushState(YYINITIAL); return XQueryTypes.L_C_BRACE; }
"\""                                      {popState(); return XQueryTypes.QUOT;}
{Char}                                    {return XQueryTypes.CHAR;}
}

<APOS_STRING> {
{PredefinedEntityRef}                     {return XQueryTypes.PREDEFINEDENTITYREF;}
{CharRef}                                 {return XQueryTypes.CHARREF;}
"{{"                                      {return XQueryTypes.DBL_L_C_BRACE;}
"}}"                                      {return XQueryTypes.DBL_R_C_BRACE;}
"{"                                       {pushState(YYINITIAL); return XQueryTypes.L_C_BRACE; }
"'"                                       {popState(); return XQueryTypes.APOSTROPHE;}
{Char}                                    {return XQueryTypes.CHAR;}
}

<URIQUALIFIED> {
{S}                                       {return TokenType.WHITE_SPACE;}
{URIQualifiedName}                        {popState(); return XQueryTypes.URIQUALIFIEDNAME;}
}

<QNAME> {
{NCName} ":" {NameStartCharWithoutFirst}  {yypushback(2); return XQueryTypes.NCNAME;}
{NCName}                                  {popState(); return XQueryTypes.NCNAME;}
":"                                       {return XQueryTypes.COLON;}
{S}                                       {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
.                                         {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<XQUERY_RECOGNITION> {
{S}                                       {return TokenType.WHITE_SPACE;}
"\""                                      {pushState(QUOT_STRING_SIMPLE);yypushback(yylength());return TokenType.WHITE_SPACE;}
"'"                                       {pushState(APOS_STRING_SIMPLE);yypushback(yylength());return TokenType.WHITE_SPACE;}
"xquery" / {S} ("encoding"|"version")     {return XQueryTypes.K_XQUERY;}
"version" / {S} ("\""|"'")                {return XQueryTypes.K_VERSION;}
"encoding" / {S} ("\""|"'")               {return XQueryTypes.K_ENCODING;}
{NCName}                                  {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                         {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<DECLARATION_RECOGNITION> {
{S}                                       {return TokenType.WHITE_SPACE;}
"declare" / {S} ("boundary-space"|"default"|"base-uri"|"construction"|"ordering"|"copy-namespaces"|"decimal-format"|"namespace"|"context"|"option"|"function"|"variable"|"%") {return XQueryTypes.K_DECLARE;}
"default" / {S} ("collation"|"order"|"decimal-format"|"element"|"function") {return XQueryTypes.K_DEFAULT;}
"base-uri" / {S} ("\""|"'")               {return XQueryTypes.K_BASE_URI;}
"option" / {S} {NCName}                   {return XQueryTypes.K_OPTION;}
"variable" / {S} "$"                      {return XQueryTypes.K_VARIABLE;}
"function" / {S} "namespace" {S} {StringLiteral} {return XQueryTypes.K_FUNCTION;}
"function"                                {pushState(QNAME); return XQueryTypes.K_FUNCTION;}
"boundary-space" / {S} ("preserve"|"strip") {return XQueryTypes.K_BOUNDARY_SPACE;}
"construction" / {S} ("preserve"|"strip") {return XQueryTypes.K_CONSTRUCTION;}
"ordering" / {S} ("ordered"|"unordered")  {return XQueryTypes.K_ORDERING;}
"ordered" / {S}? (";"|"{")                {return XQueryTypes.K_ORDERED;}
"unordered" / {S}? (";"|"{")              {return XQueryTypes.K_UNORDERED;}
"copy-namespaces" / {S} ("preserve"|"no-preserve") {return XQueryTypes.K_COPY_NAMESPACES;}
"preserve" / {S}? (";"|",")               {return XQueryTypes.K_PRESERVE;}
"no-preserve" / {S}? ","                  {return XQueryTypes.K_NO_PRESERVE;}
"decimal-format" / {S} ({NCName} | "decimal-separator" | "grouping-separator" | "infinity" | "minus-sign" | "NaN" | "percent" | "per-mille" | "zero-digit" | "digit" | "pattern-separator")                          {return XQueryTypes.K_DECIMAL_FORMAT;}
"decimal-separator" / {S}? "="            {return XQueryTypes.K_DECIMAL_SEPARATOR;}
"grouping-separator" / {S}? "="           {return XQueryTypes.K_GROUPING_SEPARATOR;}
"infinity" / {S}? "="                     {return XQueryTypes.K_INFINITY;}
"minus-sign" / {S}? "="                   {return XQueryTypes.K_MINUS_SIGN;}
"NaN" / {S}? "="                          {return XQueryTypes.K_NAN;}
"percent" / {S}? "="                      {return XQueryTypes.K_PERCENT;}
"per-mille" / {S}? "="                    {return XQueryTypes.K_PER_MILLE;}
"zero-digit" / {S}? "="                   {return XQueryTypes.K_ZERO_DIGIT;}
"digit" / {S}? "="                        {return XQueryTypes.K_DIGIT;}
"pattern-separator" / {S}? "="            {return XQueryTypes.K_PATTERN_SEPARATOR;}
"namespace" / {S} ({NCName}|"\""|"'"|"{")       {return XQueryTypes.K_NAMESPACE;}
"context" / {S} "item"                    {return XQueryTypes.K_CONTEXT;}
"empty" / {S} ("greatest"|"least") {return XQueryTypes.K_EMPTY;}
"strip" / {S}? ";"                        {return XQueryTypes.K_STRIP;}
"collation" / {S} ("\""|"'")              {return XQueryTypes.K_COLLATION;}
"%"                                       {return XQueryTypes.PERCENT;}
"element" / ({S}?"("|{S}?"{"| {S}{NCName})       {return XQueryTypes.K_ELEMENT;}
{NCName}                                  {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                         {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<IMPORT_RECOGNITION> {
{S}                                       {return TokenType.WHITE_SPACE;}
"import" / {S} ("schema"|"module")        {return XQueryTypes.K_IMPORT;}
"schema" / {S} ("namespace"|"default"|"\"") {return XQueryTypes.K_SCHEMA;}
"default" / {S} "element"                 {return XQueryTypes.K_DEFAULT;}
"module" / {S} ("namespace"|"\"")         {return XQueryTypes.K_MODULE;}
"element" / {S} "namespace"               {return XQueryTypes.K_ELEMENT;}
"namespace" / {S} ({NCName}|"\""|"'"|"{") {return XQueryTypes.K_NAMESPACE;}
{NCName}                                  {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                         {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<MODULE_RECOGNITION> {
{S}                                       {return TokenType.WHITE_SPACE;}
"module" / {S} ("namespace"|"\"")         {return XQueryTypes.K_MODULE;}
"namespace" / {S} {NCName}                {return XQueryTypes.K_NAMESPACE;}
{NCName}                                  {pushState(QNAME);yypushback(yylength());return TokenType.WHITE_SPACE;}
.                                         {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}


<QUOT_STRING_SIMPLE> {
"\""                                      {return XQueryTypes.QUOT;}
{StringLiteral}                           {popState(); return XQueryTypes.STRINGLITERAL;}
{Char}                                    {return XQueryTypes.CHAR;}
.                                         {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

<APOS_STRING_SIMPLE> {
"\""                                      {return XQueryTypes.APOSTROPHE;}
{StringLiteral}                           {popState(); return XQueryTypes.STRINGLITERAL;}
{Char}                                    {return XQueryTypes.CHAR;}
.                                         {yypushback(yylength()); popState(); return TokenType.WHITE_SPACE;}
}

.                                         {return TokenType.BAD_CHARACTER;}