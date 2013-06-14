package org.intellij.xquery.parser;

import com.intellij.testFramework.ParsingTestCase;
import org.intellij.xquery.XQueryParserDefinition;

/**
 * User: ligasgr
 * Date: 14/06/13
 * Time: 21:01
 */
public class ParsingTests extends ParsingTestCase {

    public ParsingTests() {
        super("parser", "xq", new XQueryParserDefinition());
    }

    public void testTour() {
        doTest(true);
    }

    public void testDefaultNamespaces() {
        doTest(true);
    }

    public void testStringConcatention() {
        doTest(true);
    }

    public void testSaxonMapSyntax() {
        doTest(true);
    }

    public void testMarklogicMapSyntax() {
        doTest(true);
    }

    public void testPrivate() {
        doTest(true);
    }

    public void testVersionInstanceOf() {
        doTest(true);
    }

    public void testFunctionReferenceDirComment() {
        doTest(true);
    }

    public void testFlwor() {
        doTest(true);
    }

    public void testPureXml() {
        doTest(true);
    }

    public void testSatisfies() {
        doTest(true);
    }

    @Override
    protected String getTestDataPath() {
        return "testData/org/intellij/xquery";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }
}
