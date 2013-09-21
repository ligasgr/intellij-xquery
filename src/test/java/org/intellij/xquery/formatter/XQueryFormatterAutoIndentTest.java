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

package org.intellij.xquery.formatter;

import org.intellij.xquery.XQueryBaseTestCase;

/**
 * User: ligasgr
 * Date: 11/09/13
 * Time: 21:02
 */
public class XQueryFormatterAutoIndentTest extends XQueryBaseTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/formatter/auto-indent";
    }

    public void testAfterVersion() throws Exception {
        doTest();
    }

    public void testInsideFunctionBody() throws Exception {
        doTest();
    }

    public void testInsideForLetBinding() throws Exception {
        doTest();
    }

    public void testInsideReturnExpression() throws Exception {
        doTest();
    }

    public void testInsideExpression() throws Exception {
        doTest();
    }

    public void testIssue42() throws Exception {
        doTest();
    }

    public void testInsideOfParenthesizedExpression() throws Exception {
        doTest();
    }

    private void doTest() throws Exception {
        final String testName = getTestName(false);
        myFixture.configureByFile(testName + ".xq");
        myFixture.type("\n");
        myFixture.checkResultByFile(String.format("%s_after.xq", testName));
    }

}
