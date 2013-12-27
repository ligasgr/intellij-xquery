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

package org.intellij.xquery.documentation;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryVarName;
import org.jetbrains.annotations.NotNull;

import static org.intellij.xquery.documentation.XQueryDocumentationProvider.FILE_LINK_TEMPLATE;
import static org.intellij.xquery.documentation.XQueryDocumentationProvider.HTML_BR;
import static org.intellij.xquery.documentation.XQueryDocumentationProvider.NAMESPACE_LABEL;

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
    private XQueryDocumentationProvider documentationProvider = new XQueryDocumentationProvider();

    public void testUserVariableReference() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION,
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        VARIABLE_DECLARATION + ";\n" +
                        "declare variable $ns:another := $ns:v<caret>ar");
    }

    public void testUserExternalVariableReference() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare variable $ns:var as xs:string external := 'var'",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare variable $ns:var as xs:string external := 'var';\n" +
                        "declare variable $ns:another := $ns:v<caret>ar");
    }

    public void testUserVariableReferenceInMainModule() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION,
                "declare namespace ns = '" + NAMESPACE + "';\n" +
                        VARIABLE_DECLARATION + ";\n" +
                        "declare variable $ns:another := $ns:v<caret>ar");
    }

    public void testUserVariableDeclaration() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION,
                "declare namespace ns = '" + NAMESPACE + "';\n" +
                        "declare variable $ns:v<caret>ar as xs:string := 'var';");
    }

    public void testUserVariableDeclarationWithComment() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION + HTML_BR + HTML_BR +
                "my description",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)" +
                        "declare variable $ns:v<caret>ar as xs:string := 'var';");
    }

    public void testLetBindingReference() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "let $var as xs:string := 'var'",
                "let $var as xs:string := 'var' return $v<caret>ar");
    }

    public void testForBindingReference() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "for $var as xs:string in 1 to 10",
                "for $var as xs:string in 1 to 10 return $v<caret>ar");
    }

    public void testSecondForBindingReference() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "for $var2 in 5 to 10",
                "for $var as xs:string in 1 to 10, $var2 in 5 to 10 return $v<caret>ar2");
    }

    public void testGroupingSpec() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "group by $var2 := 'a' collation 'mine'",
                "for $var as xs:string in 1 to 10 group by $v<caret>ar2 := 'a' collation 'mine' return $var");
    }

    public void testParameter() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "$param as xs:int",
                "declare namespace ns = '" + NAMESPACE + "';\n" +
                        "declare function ns:f($pa<caret>ram as xs:int) {()};");
    }

    public void testParameterReference() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "$param as xs:int",
                "declare namespace ns = '" + NAMESPACE + "';\n" +
                        "declare function ns:f($param as xs:int) {$par<caret>am};");
    }

    public void testWindow() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "for tumbling window $w in (2, 4, 6, 8, 10, 12, 14)" +
                "start at $s when fn:true() " +
                "only end at $e when $e - $s eq 2",
                "for tumbling window $w in (2, 4, 6, 8, 10, 12, 14)" +
                        "start at $s when fn:true() " +
                        "only end at $<caret>e when $e - $s eq 2 " +
                        "return <window>{ $w }</window>");
    }

    public void testTypeswitchCaseClause() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "case $a as element() return $a",
                "typeswitch ($node) case $a as element() return $<caret>a default return $node");
    }

    public void testTypeswitchDefaultReturnClause() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "default $b return $b",
                "typeswitch ($node) case $a as element() return $a default $b return $<caret>b");
    }

    public void testQuantifiedExpressionSome() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "some $searchString in ('a', 'b')",
                "some $searchString in ('a', 'b') satisfies contains('ag', $s<caret>earchString)");
    }

    public void testQuantifiedExpressionEvery() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "every $searchString in ('a', 'b')",
                "every $searchString in ('a', 'b') satisfies contains('ag', $s<caret>earchString)");
    }

    public void testQuantifiedExpressionSomeForSecondBinding() throws Exception {
        doTestGenerateVariableDoc(FILE_LINK + HTML_BR +
                "some $a in ('a')",
                "some $searchString in ('a', 'b'), $a in ('a') satisfies $<caret>a = ('a')");
    }

    public void testUserVariableReferenceToAnotherFile() throws Exception {
        doTestGenerateVariableDoc(String.format(FILE_LINK_TEMPLATE, "x.xq", "x.xq") + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION,
                "import module namespace x = '" + NAMESPACE + "' at 'x.xq'" +
                        "$x:v<caret>ar",
                new FileData("x.xq",
                        "module namespace ns = '" + NAMESPACE + "';\n" +
                                VARIABLE_DECLARATION + ";"));
    }

    public void testLookupItemForVariableWithDocComment() throws Exception {
        doTestGenerateLookupItemDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                VARIABLE_DECLARATION + HTML_BR + HTML_BR +
                "my description",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)\n" +
                        VARIABLE_DECLARATION + ";\n" +
                        "declare function ns:x() {$ns:<caret>};",
                XQueryVarName.class);
    }

    public void testUserFunctionReference() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare %private function ns:f($a) as item()",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare %private function ns:f($a) as item() {()};\n" +
                        "declare variable $ns:another := ns:<caret>f(1);");
    }

    public void testUserFunctionDeclaration() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function ns:f()",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithThreeAnnotationsAndNoParams() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare %private %my %ns:another function ns:fun",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare %private %my %ns:another function ns:<caret>fun");
    }

    public void testUserFunctionDeclarationWithDefaultFunctionNamespace() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + "def" + HTML_BR +
                "declare function fun()",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "declare default function namespace 'def';\n" +
                        "declare function f<caret>un() {()};");
    }

    public void testUserFunctionDeclarationWithIncorrectNamespace() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                "declare function x:fun()",
                "declare function x:f<caret>un() {()};");
    }

    public void testUserFunctionDeclarationWithDocComment() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function ns:f()" + HTML_BR + HTML_BR +
                "my description",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithMultiLineDocComment() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function ns:f()" + HTML_BR + HTML_BR +
                "my description in multiple lines",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description\n" +
                        " : in\n" +
                        " : multiple\n" +
                        " :lines\n" +
                        " :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentForPreviousFunction() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function ns:f()",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)" +
                        "declare function ns:a() {()};\n" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testUserFunctionDeclarationWithNonDocComment() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function ns:f()",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(: my description :)" +
                        "declare function ns:<caret>f() {()};");
    }

    public void testLookupItemForUserFunctionDeclarationWithDocComment() throws Exception {
        doTestGenerateLookupItemDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function ns:f()" + HTML_BR + HTML_BR +
                "my description",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description :)" +
                        "declare function ns:f() {(<caret>)};",
                XQueryFunctionName.class);
    }

    public void testUserFunctionDeclarationWithDocCommentWithParameter() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function ns:f($a)" + HTML_BR + HTML_BR +
                "my description" + HTML_BR + HTML_BR +
                "<b>Parameters:</b>" + HTML_BR +
                "a - my parameter",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description\n" +
                        " : @param a my parameter\n" +
                        " :)" +
                        "declare function ns:<caret>f($a) {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithParameterWithDollar() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function ns:f($a)" + HTML_BR + HTML_BR +
                "my description" + HTML_BR + HTML_BR +
                "<b>Parameters:</b>" + HTML_BR +
                "a - my parameter",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description\n" +
                        " : @param $a my parameter\n" +
                        " :)" +
                        "declare function ns:<caret>f($a) {()};");
    }

    public void testUserFunctionDeclarationWithDocCommentWithParameters() throws Exception {
        doTestGenerateFunctionDoc(FILE_LINK + HTML_BR +
                NAMESPACE_LABEL + NAMESPACE + HTML_BR +
                "declare function ns:f($a, $b, $c)" + HTML_BR + HTML_BR +
                "my description" + HTML_BR + HTML_BR +
                "<b>Parameters:</b>" + HTML_BR +
                "a - my parameter" + HTML_BR +
                "b - my parameter 1" + HTML_BR +
                "c - my parameter 2",
                "module namespace ns = '" + NAMESPACE + "';\n" +
                        "(:~ my description\n" +
                        " : @param a my parameter\n" +
                        " : @param b my parameter 1\n" +
                        " : @param c my parameter 2\n" +
                        " :)" +
                        "declare function ns:<caret>f($a, $b, $c) {()};");
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
                                             final Class<? extends PsiElement> documentationSourceClass) throws
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
        PsiElement sourceOfDocumentation = documentationProvider.getDocumentationElementForLookupItem(getPsiManager()
                , interestingElement.getObject(), null);
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
