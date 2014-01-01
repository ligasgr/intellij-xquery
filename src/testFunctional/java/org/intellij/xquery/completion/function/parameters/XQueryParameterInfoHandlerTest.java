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

package org.intellij.xquery.completion.function.parameters;

import com.intellij.lang.parameterInfo.CreateParameterInfoContext;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.psi.XQueryArgumentList;

/**
 * User: ligasgr
 * Date: 03/12/13
 * Time: 20:54
 */
public class XQueryParameterInfoHandlerTest extends BaseFunctionalTestCase {

    private static final String TWO_PARAMETERS_FUNCTION = "declare function fun($a, $b){()};";
    private static final String ONE_AND_TWO_PARAMETERS_FUNCTION = "declare function fun($a){()};" + TWO_PARAMETERS_FUNCTION;

    public void testShouldNotPointToAnyParameter() {
        doTest(TWO_PARAMETERS_FUNCTION + "fu<caret>n()", -1, 0);
    }

    public void testShouldPointToFirstParameter() {
        doTest(TWO_PARAMETERS_FUNCTION + "fun(<caret>)", 0, 1);
    }

    public void testShouldPointToFirstParameterAndShowTwoItems() {
        doTest(ONE_AND_TWO_PARAMETERS_FUNCTION + "fun(<caret>)", 0, 2);
    }

    public void testShouldPointToSecondParameter() {
        doTest(TWO_PARAMETERS_FUNCTION + "fun('a', <caret>)", 1, 1);
    }

    public void testShouldPointToSecondParameterAndShowTwoItems() {
        doTest(ONE_AND_TWO_PARAMETERS_FUNCTION + "fun('a', <caret>)", 1, 2);
    }

    public void testShouldPointToThirdParameter() {
        doTest(TWO_PARAMETERS_FUNCTION + "fun('a', 'b', <caret>)", 2, 1);
    }

    public void testShouldPointToFirstParameterForBuiltInFunctionAndShowOneItem() {
        doTest("fn:true(<caret>)", 0, 1);
    }

    public void testShouldPointToFirstParameterForBuiltInFunctionAndShowTwoItems() {
        doTest("fn:starts-with(<caret>)", 0, 2);
    }

    private void doTest(String text, int highlightedParameterIndex, int shownItems) {
        myFixture.configureByText("a.xq", text);
        final XQueryParameterInfoHandler parameterInfoHandler = new XQueryParameterInfoHandler();
        final CreateParameterInfoContext createContext = new FakeCreateParameterInfoContext(myFixture.getEditor(), myFixture.getFile());
        final XQueryArgumentList list = parameterInfoHandler.findElementForParameterInfo(createContext);

        if (highlightedParameterIndex >= 0) {
            assertNotNull(list);
            parameterInfoHandler.showParameterInfo(list, createContext);
            Object[] itemsToShow = createContext.getItemsToShow();
            assertNotNull(itemsToShow);
            assertEquals(shownItems, itemsToShow.length);
        }
        FakeUpdateParameterInfoContext updateContext = new FakeUpdateParameterInfoContext(myFixture.getEditor(), myFixture.getFile());
        final XQueryArgumentList element = parameterInfoHandler.findElementForUpdatingParameterInfo(updateContext);
        if (element == null) {
            assertEquals(-1, highlightedParameterIndex);
        } else {
            assertNotNull(element);
            parameterInfoHandler.updateParameterInfo(element, updateContext);
            assertEquals(highlightedParameterIndex, updateContext.getCurrentParameter());
        }
    }
}
