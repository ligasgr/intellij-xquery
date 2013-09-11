package org.intellij.xquery.formatter;

import org.intellij.xquery.XQueryBaseTestCase;

/**
 * User: ligasgr
 * Date: 11/09/13
 * Time: 21:02
 */
public class AutoIndentTest extends XQueryBaseTestCase {

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

    private void doTest() throws Exception {
        final String testName = getTestName(false);
        myFixture.configureByFile(testName + ".xq");
        myFixture.type("\n");
        myFixture.checkResultByFile(String.format("%s_after.xq", testName));
    }

}
