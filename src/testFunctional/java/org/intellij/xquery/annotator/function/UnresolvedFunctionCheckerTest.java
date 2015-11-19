/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.annotator.function;

import org.intellij.xquery.BaseFunctionalTestCase;
import org.jetbrains.annotations.NotNull;

public class UnresolvedFunctionCheckerTest extends BaseFunctionalTestCase {

    @NotNull
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/annotator/function/";
    }

    public void testUnresolved() {
        doTest();
    }

    public void testMarklogicExtendedShouldBeResolved() {
        doTest();
    }

    private void doTest() {
        myFixture.configureByFiles(getDefaultFileName());
        myFixture.checkHighlighting(false, false, true);
    }
}
