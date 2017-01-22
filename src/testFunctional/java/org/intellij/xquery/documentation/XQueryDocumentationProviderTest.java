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

package org.intellij.xquery.documentation;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.XQueryFlavour;
import org.intellij.xquery.completion.function.BuiltInFunctionSignature;
import org.intellij.xquery.psi.XQueryFunctionCall;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.settings.XQuerySettings;
import org.jetbrains.annotations.NotNull;

import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.documentation.CommentAndSignatureBasedDocumentation.FILE_LINK_TEMPLATE;
import static org.intellij.xquery.documentation.CommentAndSignatureBasedDocumentation.NAMESPACE_LABEL;
import static org.intellij.xquery.documentation.DocumentationStylist.DD_END;
import static org.intellij.xquery.documentation.DocumentationStylist.DD_START;
import static org.intellij.xquery.documentation.DocumentationStylist.DL_END;
import static org.intellij.xquery.documentation.DocumentationStylist.DL_START;
import static org.intellij.xquery.documentation.DocumentationStylist.FUNCTION_END;
import static org.intellij.xquery.documentation.DocumentationStylist.FUNCTION_START;
import static org.intellij.xquery.documentation.DocumentationStylist.HTML_BR;
import static org.intellij.xquery.documentation.DocumentationStylist.LABEL_END;
import static org.intellij.xquery.documentation.DocumentationStylist.LABEL_START;
import static org.intellij.xquery.documentation.DocumentationStylist.WRAPPER_END;
import static org.intellij.xquery.documentation.DocumentationStylist.WRAPPER_START;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 16/12/13
 * Time: 14:59
 */
public class XQueryDocumentationProviderTest extends BaseFunctionalTestCase {

    private static final String SOURCE_FILE_NAME = "a.xq";
    private static final String FILE_LINK = String.format(FILE_LINK_TEMPLATE, SOURCE_FILE_NAME, SOURCE_FILE_NAME);
    private static final String VARIABLE_DECLARATION = "declare variable $ns:var as xs:string := 'var'";
    private static final String NAMESPACE = "ns";
    private static final String FUN_SIGNATURE = "declare function " + FUNCTION_START + "ns:f" + FUNCTION_END + "()";
    private static final String SIGNATURE_BASED_HEADER = FILE_LINK + HTML_BR +
            NAMESPACE_LABEL + NAMESPACE + HTML_BR +
            FUN_SIGNATURE;
    private XQueryDocumentationProvider documentationProvider = new XQueryDocumentationProvider();

    public void testUserVariableReference() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        VARIABLE_DECLARATION + ";\n" +
                        "declare variable $ns:another := $ns:v<caret>ar");
    }

    public void testUserExternalVariableReference() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare variable $ns:var as xs:string external := 'var'" +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare variable $ns:var as xs:string external := 'var';\n" +
                        "declare variable $ns:another := $ns:v<caret>ar");
    }

    public void testUserVariableReferenceInMainModule() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION +
                WRAPPER_END,
                "declare namespace ns = '" + NAMESPACE + "';\n" +
                        VARIABLE_DECLARATION + ";\n" +
                        "declare variable $ns:another := $ns:v<caret>ar");
    }

    public void testUserVariableDeclaration() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION +
                WRAPPER_END,
                "declare namespace ns = '" + NAMESPACE + "';\n" +
                        "declare variable $ns:v<caret>ar as xs:string := 'var';");
    }

    public void testUserVariableDeclarationWithComment() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION +
                DL_START +
                LABEL_START + "Summary" + LABEL_END + DD_START + "my description" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)" +
                        "declare variable $ns:v<caret>ar as xs:string := 'var';");
    }

    public void testLetBindingReference() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "let $var as xs:string := 'var'" +
                WRAPPER_END,
                "let $var as xs:string := 'var' return $v<caret>ar");
    }

    public void testForBindingReference() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "for $var as xs:string in 1 to 10" +
                WRAPPER_END,
                "for $var as xs:string in 1 to 10 return $v<caret>ar");
    }

    public void testSecondForBindingReference() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "for $var2 in 5 to 10" +
                WRAPPER_END,
                "for $var as xs:string in 1 to 10, $var2 in 5 to 10 return $v<caret>ar2");
    }

    public void testGroupingSpec() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "group by $var2 := 'a' collation 'mine'" +
                WRAPPER_END,
                "for $var as xs:string in 1 to 10 group by $v<caret>ar2 := 'a' collation 'mine' return $var");
    }

    public void testParameter() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "$param as xs:int" +
                WRAPPER_END,
                "declare namespace ns = '" + NAMESPACE + "';\n" +
                        "declare function ns:f($pa<caret>ram as xs:int) {()};");
    }

    public void testParameterReference() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "$param as xs:int" +
                WRAPPER_END,
                "declare namespace ns = '" + NAMESPACE + "';\n" +
                        "declare function ns:f($param as xs:int) {$par<caret>am};");
    }

    public void testWindow() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "for tumbling window $w in (2, 4, 6, 8, 10, 12, 14)" +
                "start at $s when fn:true() " +
                "only end at $e when $e - $s eq 2" +
                WRAPPER_END,
                "for tumbling window $w in (2, 4, 6, 8, 10, 12, 14)" +
                        "start at $s when fn:true() " +
                        "only end at $<caret>e when $e - $s eq 2 " +
                        "return <window>{ $w }</window>");
    }

    public void testTypeswitchCaseClause() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "case $a as element() return $a" +
                WRAPPER_END,
                "typeswitch ($node) case $a as element() return $<caret>a default return $node");
    }

    public void testTypeswitchDefaultReturnClause() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "default $b return $b" +
                WRAPPER_END,
                "typeswitch ($node) case $a as element() return $a default $b return $<caret>b");
    }

    public void testQuantifiedExpressionSome() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "some $searchString in ('a', 'b')" +
                WRAPPER_END,
                "some $searchString in ('a', 'b') satisfies contains('ag', $s<caret>earchString)");
    }

    public void testQuantifiedExpressionEvery() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "every $searchString in ('a', 'b')" +
                WRAPPER_END,
                "every $searchString in ('a', 'b') satisfies contains('ag', $s<caret>earchString)");
    }

    public void testQuantifiedExpressionSomeForSecondBinding() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                "some $a in ('a')" +
                WRAPPER_END,
                "some $searchString in ('a', 'b'), $a in ('a') satisfies $<caret>a = ('a')");
    }

    public void testUserVariableReferenceToAnotherFile() throws Exception {
        doTestGenerateVariableDoc(WRAPPER_START +
                String.format(FILE_LINK_TEMPLATE, "x.xq", "x.xq") + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION +
                WRAPPER_END,
                "import module namespace x = '" + NAMESPACE + "' at 'x.xq'" +
                        "$x:v<caret>ar",
                new FileData("x.xq",
                        "module namespace ns = '" + NAMESPACE + "';\n" +
                                VARIABLE_DECLARATION + ";"));
    }

    public void testLookupItemForVariableWithDocComment() throws Exception {
        doTestGenerateLookupItemDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION +
                DL_START +
                LABEL_START + "Summary" + LABEL_END + DD_START + "my description" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)\n" +
                        VARIABLE_DECLARATION + ";\n" +
                        "declare function ns:x() {$ns:<caret>};",
                XQueryVarName.class);
    }

    public void testUserFunctionReference() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare %private function " + FUNCTION_START + "ns:f" + FUNCTION_END + "($a) as item()" +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare %private function ns:f($a) as item() {()};\n" +
                        "declare variable $ns:another := ns:<caret>f(1);");
    }

    public void testUserFunctionDeclaration() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithThreeAnnotationsAndNoParams() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare %private %my %ns:another function " + FUNCTION_START + "ns:f" + FUNCTION_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare %private %my %ns:another function ns:<caret>f");
    }

    public void testUserFunctionDeclarationWithDefaultFunctionNamespace() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + "def" + HTML_BR +
                "declare function " + FUNCTION_START + "fun" + FUNCTION_END + "()" +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare default function namespace 'def';\n" +
                        "declare function f<caret>un() {()};");
    }

    public void testUserFunctionDeclarationWithIncorrectNamespace() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                FUN_SIGNATURE +
                WRAPPER_END,
                "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocComment() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER +
                DL_START +
                LABEL_START + "Summary" + LABEL_END + DD_START + "my description" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithMultiLineDocComment() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER +
                DL_START +
                LABEL_START + "Summary" + LABEL_END + DD_START + "my description in multiple lines" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description\n" +
                        " : in\n" +
                        " : multiple\n" +
                        " :lines\n" +
                        " :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentForPreviousFunction() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)" +
                        "declare function ns:a() {()};\n" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithNonDocComment() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(: my description :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testLookupItemForUserFunctionDeclarationWithDocComment() throws Exception {
        doTestGenerateLookupItemDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER +
                DL_START +
                LABEL_START + "Summary" + LABEL_END + DD_START + "my description" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)" +
                        "declare function ns:f() {(<caret>)};",
                XQueryFunctionName.class);
    }

    public void testUserFunctionDeclarationWithDocCommentWithParameter() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function " + FUNCTION_START + "ns:f" + FUNCTION_END + "($a)" + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Parameters" + LABEL_END + DD_START + "a - my parameter" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ \n" +
                        " : @param a my parameter\n" +
                        " :)" +
                        "declare function ns:<caret>f($a) {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithParameterWithDollar() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function " + FUNCTION_START + "ns:f" + FUNCTION_END + "($a)" + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Parameters" + LABEL_END + DD_START + "a - my parameter" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ \n" +
                        " : @param $a my parameter\n" +
                        " :)" +
                        "declare function ns:<caret>f($a) {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithParameters() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function " + FUNCTION_START + "ns:f" + FUNCTION_END + "($a, $b, $c)" + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Parameters" + LABEL_END +
                DD_START +
                "a - my parameter" + HTML_BR +
                "b - my parameter 1" + HTML_BR +
                "c - my parameter 2" +
                DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ @param a my parameter\n" +
                        " : @param b my parameter 1\n" +
                        " : @param c my parameter 2\n" +
                        " :)" +
                        "declare function ns:<caret>f($a, $b, $c) {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithParametersWithoutDescriptions() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function " + FUNCTION_START + "ns:f" + FUNCTION_END + "($a, $b, $c)" + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Parameters" + LABEL_END +
                DD_START +
                "a - " + HTML_BR +
                "b - " + HTML_BR +
                "c - " + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ @param a\n" +
                        " : @param b\n" +
                        " : @param c\n" +
                        " :)" +
                        "declare function ns:<caret>f($a, $b, $c) {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithAuthor() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Author" + LABEL_END + DD_START + "author with spaces" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ @author author with spaces :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithVersion() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Version" + LABEL_END + DD_START + "1.0" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ \n" +
                        " : @version 1.0\n" +
                        " :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithReturn() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Returns" + LABEL_END + DD_START + "value described" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ \n" +
                        " : @return value described\n" +
                        " :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithError() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Throws errors" + LABEL_END + DD_START + "err:SomeError - if condition is met" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ \n" +
                        " : @error err:SomeError if condition is met\n" +
                        " :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithSince() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Since" + LABEL_END + DD_START + "2.3" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ \n" +
                        " : @since 2.3\n" +
                        " :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithSee() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "See" + LABEL_END + DD_START + "anotherModule.xq" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ \n" +
                        " : @see anotherModule.xq\n" +
                        " :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithDeprecated() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Deprecated" + LABEL_END + DD_START + "because of a reason" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ \n" +
                        " : @deprecated because of a reason\n" +
                        " :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentForAllTags() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                SIGNATURE_BASED_HEADER + HTML_BR + HTML_BR +
                DL_START +
                LABEL_START + "Summary" + LABEL_END + DD_START + "my comment" + DD_END +
                LABEL_START + "Author" + LABEL_END + DD_START + "author with spaces" + DD_END +
                LABEL_START + "Version" + LABEL_END + DD_START + "2.0" + DD_END +
                LABEL_START + "Since" + LABEL_END + DD_START + "1.0" + DD_END +
                LABEL_START + "Deprecated" + LABEL_END + DD_START + "because of a reason" + DD_END +
                LABEL_START + "Parameters" + LABEL_END + DD_START + "a - my parameter" + DD_END +
                LABEL_START + "Returns" + LABEL_END + DD_START + "value described" + DD_END +
                LABEL_START + "Throws errors" + LABEL_END + DD_START + "err:SomeError - if condition is met" + DD_END +
                LABEL_START + "See" + LABEL_END + DD_START + "anotherModule.xq" + DD_END +
                DL_END +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my comment\n" +
                        " : @author author with spaces\n" +
                        " : @version 2.0\n" +
                        " : @since 1.0\n" +
                        " : @deprecated because of a reason\n" +
                        " : @param $a my parameter\n" +
                        " : @return value described\n" +
                        " : @error err:SomeError if condition is met\n" +
                        " : @see anotherModule.xq\n" +
                        " :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testBuiltInFunctionReference() throws Exception {
        doTestGenerateFunctionDoc(WRAPPER_START +
                "<dl><dt class=\"label\">Summary</dt> <dd> <p>Returns the <code>xs:boolean</code> value " +
                "<code>true</code>.</p> </dd> <dt class=\"label\">Signature</dt> <dd> <div class=\"exampleInner\"> " +
                "<div class=\"proto\"><code class= \"function\">fn:true</code>()<code class= \"as\">&#160;as&#160;" +
                "</code><code class= \"return-type\">xs:boolean</code></div> </div> </dd> <dt " +
                "class=\"label\">Properties</dt> <dd> <p>This function is <a title=\"deterministic\" " +
                "class=\"termref\" href= \"#dt-deterministic\"><span class= \"arrow\">·</span>deterministic<span " +
                "class=\"arrow\">·</span></a>, <a title=\"context-independent\" class=\"termref\" href= " +
                "\"#dt-context-independent\"><span class= \"arrow\">·</span>context-independent<span class= " +
                "\"arrow\">·</span></a>, and <a title=\"focus-dependent\" class= \"termref\" " +
                "href=\"#dt-focus-independent\"><span class= \"arrow\">·</span>focus-independent<span class= " +
                "\"arrow\">·</span></a>.</p> </dd> <dt class=\"label\">Rules</dt> <dd> <p>The result is equivalent to" +
                " <code>xs:boolean(\"1\")</code>.</p> </dd> <dt class=\"label\">Examples</dt> <dd> <p>The expression " +
                "<code>fn:true()</code> returns <code>xs:boolean(1)</code>.</p> </dd> </dl>" +
                WRAPPER_END,
                "fn:<caret>true()");
    }

    public void testLookupItemForBuiltInFunction() throws Exception {
        doTestGenerateLookupItemDoc(WRAPPER_START +
                "<dl><dt class=\"label\">Summary</dt> <dd> <p>Returns the <code>xs:boolean</code> value " +
                "<code>true</code>.</p> </dd> <dt class=\"label\">Signature</dt> <dd> <div class=\"exampleInner\"> " +
                "<div class=\"proto\"><code class= \"function\">fn:true</code>()<code class= \"as\">&#160;as&#160;" +
                "</code><code class= \"return-type\">xs:boolean</code></div> </div> </dd> <dt " +
                "class=\"label\">Properties</dt> <dd> <p>This function is <a title=\"deterministic\" " +
                "class=\"termref\" href= \"#dt-deterministic\"><span class= \"arrow\">·</span>deterministic<span " +
                "class=\"arrow\">·</span></a>, <a title=\"context-independent\" class=\"termref\" href= " +
                "\"#dt-context-independent\"><span class= \"arrow\">·</span>context-independent<span class= " +
                "\"arrow\">·</span></a>, and <a title=\"focus-dependent\" class= \"termref\" " +
                "href=\"#dt-focus-independent\"><span class= \"arrow\">·</span>focus-independent<span class= " +
                "\"arrow\">·</span></a>.</p> </dd> <dt class=\"label\">Rules</dt> <dd> <p>The result is equivalent to" +
                " <code>xs:boolean(\"1\")</code>.</p> </dd> <dt class=\"label\">Examples</dt> <dd> <p>The expression " +
                "<code>fn:true()</code> returns <code>xs:boolean(1)</code>.</p> </dd> </dl>" +
                WRAPPER_END,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare variable $v := fn:tru<caret>;",
                BuiltInFunctionSignature.class);
    }

    public void testBuiltInFunctionReferenceForXQuery31() throws Exception {
        XQuerySettings settings = XQuerySettings.getInstance(myFixture.getProject());
        XQueryFlavour previous = settings.getFlavour();
        settings.setFlavour(XQueryFlavour.STANDARD_31);
        try {
            doTestGenerateFunctionDoc(WRAPPER_START +
                            "<dl> <dt class=\"label\">Summary</dt> <dd> <p>Returns the <code>xs:boolean</code> value " +
                            "<code>true</code>. </p> </dd> <dt class=\"label\">Signature</dt> <dd> <div class=\"proto\">" +
                            "<code class=\"function\">fn:true</code>()<code class=\"as\">&nbsp;as&nbsp;</code>" +
                            "<code class=\"return-type\">xs:boolean</code></div> </dd> <dt class=\"label\">Properties</dt>" +
                            " <dd> <p>This function is <a title=\"deterministic\" class=\"termref\" " +
                            "href=\"https://www.w3.org/TR/xpath-functions-31/#dt-deterministic\"><span class=\"arrow\">·" +
                            "</span>deterministic<span class=\"arrow\">·</span></a>, <a title=\"context-independent\"" +
                            " class=\"termref\" href=\"https://www.w3.org/TR/xpath-functions-31/#dt-context-independent\">" +
                            "<span class=\"arrow\">·</span>context-independent<span class=\"arrow\">·</span></a>, and " +
                            "<a title=\"focus-dependent\" class=\"termref\" " +
                            "href=\"https://www.w3.org/TR/xpath-functions-31/#dt-focus-independent\"><span class=\"arrow\">·" +
                            "</span>focus-independent<span class=\"arrow\">·</span></a>. </p> </dd> " +
                            "<dt class=\"label\">Rules</dt> <dd> <p>The result is equivalent to <code>xs:boolean(\"1\")</code>. " +
                            "</p> </dd> <dt class=\"label\">Examples</dt> <dd> <div class=\"example\"> " +
                            "<p>The expression <code>fn:true()</code> returns <code>xs:boolean(1)</code>. </p> </div> </dd> </dl>" +
                            WRAPPER_END,
                    "fn:<caret>true()");
        } finally {
            settings.setFlavour(previous);
        }
    }

    public void testLookupItemForBuiltInFunctionForXQuery31() throws Exception {
        XQuerySettings settings = XQuerySettings.getInstance(myFixture.getProject());
        XQueryFlavour previous = settings.getFlavour();
        settings.setFlavour(XQueryFlavour.STANDARD_31);
        try {
            doTestGenerateLookupItemDoc(WRAPPER_START +
                            "<dl> <dt class=\"label\">Summary</dt> <dd> <p>Returns the <code>xs:boolean</code> value " +
                            "<code>true</code>. </p> </dd> <dt class=\"label\">Signature</dt> <dd> <div class=\"proto\">" +
                            "<code class=\"function\">fn:true</code>()<code class=\"as\">&nbsp;as&nbsp;</code>" +
                            "<code class=\"return-type\">xs:boolean</code></div> </dd> <dt class=\"label\">Properties</dt>" +
                            " <dd> <p>This function is <a title=\"deterministic\" class=\"termref\" " +
                            "href=\"https://www.w3.org/TR/xpath-functions-31/#dt-deterministic\"><span class=\"arrow\">·" +
                            "</span>deterministic<span class=\"arrow\">·</span></a>, <a title=\"context-independent\"" +
                            " class=\"termref\" href=\"https://www.w3.org/TR/xpath-functions-31/#dt-context-independent\">" +
                            "<span class=\"arrow\">·</span>context-independent<span class=\"arrow\">·</span></a>, and " +
                            "<a title=\"focus-dependent\" class=\"termref\" " +
                            "href=\"https://www.w3.org/TR/xpath-functions-31/#dt-focus-independent\"><span class=\"arrow\">·" +
                            "</span>focus-independent<span class=\"arrow\">·</span></a>. </p> </dd> " +
                            "<dt class=\"label\">Rules</dt> <dd> <p>The result is equivalent to <code>xs:boolean(\"1\")</code>. " +
                            "</p> </dd> <dt class=\"label\">Examples</dt> <dd> <div class=\"example\"> " +
                            "<p>The expression <code>fn:true()</code> returns <code>xs:boolean(1)</code>. </p> </div> </dd> </dl>" +
                            WRAPPER_END,
                    "module namespace ns = '" + NAMESPACE + "';\n" +
                            "declare variable $v := fn:tru<caret>;",
                    BuiltInFunctionSignature.class);
        } finally {
            settings.setFlavour(previous);
        }
    }

    private void doTestGenerateVariableDoc(@NotNull String expected, @NotNull String text,
                                           FileData... otherFiles) throws Exception {
        doTestGenerateDoc(expected, text, XQueryVarName.class, otherFiles);
    }

    private void doTestGenerateFunctionDoc(@NotNull String expected, @NotNull String text,
                                           FileData... otherFiles) throws Exception {
        doTestGenerateDoc(expected, text, XQueryFunctionName.class, otherFiles);
    }

    private void doTestGenerateLookupItemDoc(@NotNull String expected, @NotNull String text,
                                             final Class documentationSourceClass) throws
            Exception {
        myFixture.configureByText(SOURCE_FILE_NAME, text);
        LookupElement[] lookupItems = myFixture.completeBasic();
        LookupElement interestingElement = ContainerUtil.find(lookupItems, new Condition<LookupElement>() {
            @Override
            public boolean value(LookupElement lookupElement) {
                return documentationSourceClass.isAssignableFrom(lookupElement.getObject().getClass());
            }
        });
        assertNotNull(interestingElement);
        PsiElement elementAtCaret = myFixture.getFile().findElementAt(myFixture.getCaretOffset());
        PsiElement sourceOfDocumentation = documentationProvider.getDocumentationElementForLookupItem(getPsiManager()
                , interestingElement.getObject(), elementAtCaret);
        assertEquals(expected, documentationProvider.generateDoc(sourceOfDocumentation, null));
    }

    private void doTestGenerateDoc(@NotNull String expected, @NotNull String text,
                                   Class<? extends PsiElement> documentationSourceClass,
                                   FileData... otherFiles) throws Exception {
        for (FileData otherFile : otherFiles) {
            myFixture.configureByText(otherFile.name, otherFile.contents);
        }
        myFixture.configureByText(SOURCE_FILE_NAME, text);
        final int caretPosition = myFixture.getEditor().getCaretModel().getOffset();
        PsiElement elementAtCaret = myFixture.getFile().findElementAt(caretPosition);
        PsiElement element = PsiTreeUtil.getParentOfType(elementAtCaret, documentationSourceClass);
        assertEquals(expected, documentationProvider.generateDoc(element, null));
    }

    private class FileData {
        public String name;
        public String contents;

        public FileData(String name, String contents) {
            this.name = name;
            this.contents = contents;
        }
    }
}
