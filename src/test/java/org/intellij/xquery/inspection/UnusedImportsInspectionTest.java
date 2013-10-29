package org.intellij.xquery.inspection;

import org.intellij.xquery.XQueryBaseTestCase;

public class UnusedImportsInspectionTest extends XQueryBaseTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/inspection";
    }

    @Override
    protected boolean isWriteActionRequired() {
        return false;
    }

    public void testUnusedImports() {
        myFixture.enableInspections(UnusedImportsInspection.class);

        myFixture.testHighlighting(true, false, false, "UnusedImports.xq");
    }
}
