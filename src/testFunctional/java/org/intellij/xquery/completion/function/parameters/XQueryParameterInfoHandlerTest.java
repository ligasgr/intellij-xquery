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

    public void testShouldHighlightFirstParameter() {
        doTest(TWO_PARAMETERS_FUNCTION + "fun(<caret>)", 0);
    }

    public void testShouldHighlightSecondParameter() {
        doTest(TWO_PARAMETERS_FUNCTION + "fun('a', <caret>)", 1);
    }

    public void testShouldHighlightNothing() {
        doTest(TWO_PARAMETERS_FUNCTION + "fun('a', 'b', <caret>)", 2);
    }

    private void doTest(String text, int highlightedParameterIndex) {
        myFixture.configureByText("a.xq", text);
        final XQueryParameterInfoHandler parameterInfoHandler = new XQueryParameterInfoHandler();
        final CreateParameterInfoContext createContext = new FakeCreateParameterInfoContext(myFixture.getEditor(), myFixture.getFile());
        final XQueryArgumentList list = parameterInfoHandler.findElementForParameterInfo(createContext);

        if (highlightedParameterIndex >= 0) {
            assertNotNull(list);
            parameterInfoHandler.showParameterInfo(list, createContext);
            Object[] itemsToShow = createContext.getItemsToShow();
            assertNotNull(itemsToShow);
            assertTrue(itemsToShow.length > 0);
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
