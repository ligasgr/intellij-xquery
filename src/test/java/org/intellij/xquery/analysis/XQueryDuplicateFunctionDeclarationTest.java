package org.intellij.xquery.analysis;

import com.intellij.codeInsight.daemon.LightDaemonAnalyzerTestCase;
import org.jetbrains.annotations.NotNull;

public class XQueryDuplicateFunctionDeclarationTest extends LightDaemonAnalyzerTestCase {

    @NotNull
    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/analysis/duplicateFunction/";
    }

    public void testTwoFunctions() {
        doTest(getTestName(false) + ".xq", false, false);
    }

    public void testThreeFunctionsWithOverridden() {
        doTest(getTestName(false) + ".xq", false, false);
    }

    public void testDoubleDuplicate() {
        doTest(getTestName(false) + ".xq", false, false);
    }
}
