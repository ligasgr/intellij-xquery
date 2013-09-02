/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com>
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

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import org.intellij.xquery.XQueryBaseTestCase;

/**
 * User: ligasgr
 * Date: 22/08/13
 * Time: 23:41
 */
public class XQueryFormattingModelBuilderTest extends XQueryBaseTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/formatter";
    }

    public void testSpaceAroundAssignmentOperators() {
        getSettings().SPACE_AROUND_ASSIGNMENT_OPERATORS = false;
        executeTest();
    }

    public void testSpaceAroundEqualityOperators() {
        getSettings().SPACE_AROUND_EQUALITY_OPERATORS = false;
        executeTest();
    }

    public void testSpaceAroundRelationalOperators() {
        getSettings().SPACE_AROUND_RELATIONAL_OPERATORS = false;
        executeTest();
    }

    public void testSpaceAroundAdditiveOperators() {
        getSettings().SPACE_AROUND_ADDITIVE_OPERATORS = false;
        executeTest();
    }

    public void testSpaceAroundMultiplicativeOperators() {
        getSettings().SPACE_AROUND_MULTIPLICATIVE_OPERATORS= true;
        executeTest();
    }

    private CodeStyleSettings getSettings() {
        return CodeStyleSettingsManager.getSettings(getProject());
    }

    public void testSpaceBeforeAfterComma() {
        getSettings().SPACE_BEFORE_COMMA = true;
        getSettings().SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
        executeTest();
    }

    public void testSpaceBeforeAndAfterArgumentAndParamList() {
        executeTest();
    }

    public void testSpaceAroundKeyword() {
        executeTest();
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

    private void executeTest() {
        myFixture.configureByFiles(getTestName(false) + ".xq");
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
                CodeStyleManager.getInstance(getProject()).reformat(myFixture.getFile());
            }
        });
        myFixture.checkResultByFile(getTestName(false) + "_after.xq");
    }
}
