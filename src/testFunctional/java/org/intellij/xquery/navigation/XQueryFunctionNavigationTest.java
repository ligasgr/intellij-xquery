package org.intellij.xquery.navigation;

import com.intellij.codeInsight.navigation.actions.MethodDownAction;
import com.intellij.codeInsight.navigation.actions.MethodUpAction;
import org.intellij.xquery.BaseFunctionalTestCase;

public class XQueryFunctionNavigationTest extends BaseFunctionalTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/navigation";
    }

    public void testMoveToPreviousMethod() {
        myFixture.configureByFile("FunctionNavigation.xq");
        MethodUpAction methodUpAction = new MethodUpAction();

        methodUpAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResultByFile("FunctionNavigationAfterMethodUpAction.xq");
    }

    public void testMoveToNextMethod() {
        myFixture.configureByFile("FunctionNavigation.xq");
        MethodDownAction methodDownAction = new MethodDownAction();

        methodDownAction.actionPerformedImpl(getProject(), myFixture.getEditor());

        myFixture.checkResultByFile("FunctionNavigationAfterMethodDownAction.xq");
    }

}
