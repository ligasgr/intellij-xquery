package org.intellij.xquery.formatter;

/**
 * User: ligasgr
 * Date: 12/09/13
 * Time: 16:58
 */
public class XQueryFormatterIndentTest extends XQueryFormattingModelBuilderTest {

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/formatter/indent";
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


}
