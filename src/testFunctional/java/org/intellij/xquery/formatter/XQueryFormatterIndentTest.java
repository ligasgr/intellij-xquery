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

package org.intellij.xquery.formatter;

/**
 * User: ligasgr
 * Date: 12/09/13
 * Time: 16:58
 */
public class XQueryFormatterIndentTest extends XQueryFormattingModelBuilderTest {

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/formatter/indent";
    }

    public void testIndentFunctionBody() {
        executeTest();
    }

    public void testIndentDirectXml() {
        executeTest();
    }

    public void testIndentIfExpression() {
        executeTest();
    }

    public void testIndentVariableValue() {
        executeTest();
    }

    public void testIndentFunctionArgumentsAndParams() {
        executeTest();
    }

    public void testIndentFlwor() {
        executeTest();
    }

    public void testIndentBinaryExpressions() {
        executeTest();
    }

    public void testIndentExpr() {
        executeTest();
    }

    public void testIndentParenthesizedExpression() {
        executeTest();
    }

    public void testIssue42() {
        executeTest();
    }

    public void testIndentTypeswitch() {
        executeTest();
    }

    public void testIndentSwitch() {
        executeTest();
    }

    public void testIndentContentExpression() {
        executeTest();
    }

    public void testIndentTryCatch() {
        executeTest();
    }

    public void testIndentStepExpr() {
        executeTest();
    }

    public void testIndentInTheMiddleOfStepExpr() {
        executeTest();
    }

    public void testIndentCommentInsideOfFunction() {
        executeTest();
    }

    @Override
    protected boolean isWriteActionRequired() {
        return true;
    }
}
