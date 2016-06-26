/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.folding;

import org.intellij.xquery.BaseFunctionalTestCase;

import java.io.IOException;

public class XQueryFoldingBuilderTest extends BaseFunctionalTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/folding/";
    }

    public void testFoldingFunctions() throws Exception {
        doTest();
    }

    public void testFoldingNamespaces() throws Exception {
        doTest();
    }

    public void testFoldingNamespacesOneLineDoesntFold() throws Exception {
        doTest();
    }

    public void testFoldingImports() throws Exception {
        doTest();
    }

    public void testFoldingImportsOneLineDoesntFold() throws Exception {
        doTest();
    }

    private void doTest() throws IOException {
        final String testName = getTestName();
        myFixture.testFolding(getTestDataPath() + testName + ".xq");
    }
}
