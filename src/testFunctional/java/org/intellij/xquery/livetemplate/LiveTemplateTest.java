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

package org.intellij.xquery.livetemplate;

import com.intellij.codeInsight.lookup.Lookup;
import com.intellij.codeInsight.template.impl.TemplateImpl;
import com.intellij.codeInsight.template.impl.TemplateSettings;
import com.intellij.codeInsight.template.impl.actions.ListTemplatesAction;
import com.intellij.openapi.editor.Editor;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.junit.Test;

import java.io.IOException;

import static com.intellij.codeInsight.template.impl.TemplateManagerImpl.isApplicable;

public class LiveTemplateTest extends BaseFunctionalTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/livetemplate";
    }

    @Test
    public void testPrologContext() throws Exception {
        final TemplateImpl template = TemplateSettings.getInstance().getTemplate("var", "XQuery");
        assertTrue(isApplicableFor("<caret>xxx", template));
        assertTrue(isApplicableFor("import module namespace x = 'x'; <caret>xxx", template));
        assertTrue(isApplicableFor("module namespace x = 'x'; <caret>xxx", template));
        assertTrue(isApplicableFor("declare variable $x := 'x';\n" +
                "<caret>xxx", template));
        assertTrue(isApplicableFor("declare function x(){'x'};\n" +
                "<caret>xxx", template));
        assertFalse(isApplicableFor("xquery version '1.0';\n<caret>xxx\nmodule namespace x = 'x';", template));
        assertFalse(isApplicableFor("declare variable $x := <caret>xxx;", template));
        assertFalse(isApplicableFor("declare function x(){<caret>xxx};", template));
        assertFalse(isApplicableFor("let $x := <caret>xxx return $x", template));
        assertFalse(isApplicableFor("let $x := 'x' return $x\n" +
                "<caret>xxx", template));
        assertFalse(isApplicableFor("let $x := 'x' return $x(: comment :)" +
                "<caret>xxx", template));
        assertFalse(isApplicableFor("let $x := 'x' return $x\n" +
                "var <caret>xxx", template));
    }

    @Test
    public void testDeclareVariableTemplate() throws Exception {
        doTestTemplateExpansion("name", "'value'");
    }

    @Test
    public void testExtendedDeclareVariableTemplate() throws Exception {
        doTestTemplateExpansion("%private", "name", "xs:string", "'value'");
    }

    @Test
    public void testDeclareFunctionTemplate() throws Exception {
        doTestTemplateExpansion("name", "'value'");
    }

    @Test
    public void testExtendedDeclareFunctionTemplate() throws Exception {
        doTestTemplateExpansion("%private", "name", "$param", "xs:string", "'value'");
    }

    @Test
    public void testDeclareNamespaceTemplate() throws Exception {
        doTestTemplateExpansion("name", "value", "");
    }

    @Test
    public void testImportTemplate() throws Exception {
        doTestTemplateExpansion("name", "value", "");
    }

    @Test
    public void testModuleTemplate() throws Exception {
        doTestTemplateExpansion("name", "value", "");
    }

    @Test
    public void testDeclareOptionTemplate() throws Exception {
        doTestTemplateExpansion("name", "value", "");
    }

    @Test
    public void testGenericContext() throws Exception {
        final TemplateImpl template = TemplateSettings.getInstance().getTemplate("cr", "XQuery");
        assertTrue(isApplicableFor("<caret>cr", template));
    }

    @Test
    public void testCarriageReturnTemplate() throws Exception {
        doTestTemplateExpansion();
    }

    @Test
    public void testNewLineTemplate() throws Exception {
        doTestTemplateExpansion();
    }

    @Test
    public void testTabulationTemplate() throws Exception {
        doTestTemplateExpansion();
    }

    @Test
    public void testExpressionContext() throws Exception {
        final TemplateImpl template = TemplateSettings.getInstance().getTemplate("let", "XQuery");
        assertTrue(isApplicableFor("<caret>xxx", template));
        assertTrue(isApplicableFor("import module namespace x = 'x'; <caret>xxx", template));
        assertFalse(isApplicableFor("module namespace x = 'x'; <caret>xxx", template));
        assertTrue(isApplicableFor("declare variable $x := 'x';\n" +
                "<caret>xxx", template));
        assertTrue(isApplicableFor("declare function x(){'x'};\n" +
                "<caret>xxx", template));
        assertFalse(isApplicableFor("xquery version '1.0';\n<caret>xxx\nmodule namespace x = 'x';", template));
        assertTrue(isApplicableFor("declare variable $x := <caret>xxx;", template));
        assertTrue(isApplicableFor("declare function x(){<caret>xxx};", template));
        assertTrue(isApplicableFor("let $x := <caret>xxx return $x", template));
        assertFalse(isApplicableFor("let $x := 'x' return $x\n" +
                "<caret>xxx", template));
        assertFalse(isApplicableFor("let $x := 'x' return $x(: comment :)" +
                "<caret>xxx", template));
        assertFalse(isApplicableFor("let $x := 'x' return $x\n" +
                "var <caret>xxx", template));
        assertTrue(isApplicableFor("let $x := 'x' return $x,<caret>xxx", template));
    }

    @Test
    public void testLetTemplate() throws Exception {
        doTestTemplateExpansion("name", "'value'", "");
    }

    @Test
    public void testForTemplate() throws Exception {
        doTestTemplateExpansion("name", "(1 to 10)", "");
    }

    @Test
    public void testOrderByTemplate() throws Exception {
        doTestTemplateExpansion("$x");
    }

    @Test
    public void testGroupByTemplate() throws Exception {
        doTestTemplateExpansion("$x");
    }

    @Test
    public void testSomeTemplate() throws Exception {
        doTestTemplateExpansion("name", "(1 to 10)", "");
    }

    @Test
    public void testAllTemplate() throws Exception {
        doTestTemplateExpansion("name", "(1 to 10)", "");
    }

    @Test
    public void testSwitchTemplate() throws Exception {
        doTestTemplateExpansion("'value'", "'value'", "'matched'", "'unknown'");
    }

    @Test
    public void testTypeswitchTemplate() throws Exception {
        doTestTemplateExpansion("'value'", "xs:string", "'xs:string'", "'unknown'");
    }

    @Test
    public void testTryCatchTemplate() throws Exception {
        doTestTemplateExpansion("'value'", "");
    }

    @Test
    public void testTraceTemplate() throws Exception {
        doTestTemplateExpansion("$x");
    }

    private void doTestTemplateExpansion(String... values) throws Exception {
        final String testName = getTestName(false);
        myFixture.configureByFile(testName + ".xq");
        expandTemplate(myFixture.getEditor(), values);
        myFixture.checkResultByFile(String.format("%s_after.xq", testName));
    }

    public void expandTemplate(final Editor editor, String... values) {
        new ListTemplatesAction().actionPerformedImpl(editor.getProject(), editor);
        myFixture.finishLookup(Lookup.NORMAL_SELECT_CHAR);
        substituteValues(values);
    }

    private void substituteValues(String[] values) {
        int counter = 0;
        for (String value : values) {
            myFixture.type(value);
            boolean notLast = ++counter != values.length;
            if (notLast) {
                myFixture.type(Lookup.REPLACE_SELECT_CHAR);
            }
        }
    }

    private boolean isApplicableFor(String text, TemplateImpl inst) throws IOException {
        myFixture.configureByText("a.xq", text);
        return isApplicable(myFixture.getFile(), myFixture.getEditor().getCaretModel().getOffset(), inst);
    }
}
