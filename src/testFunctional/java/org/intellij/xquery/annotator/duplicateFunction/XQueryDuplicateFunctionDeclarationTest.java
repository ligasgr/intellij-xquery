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

package org.intellij.xquery.annotator.duplicateFunction;

import org.intellij.xquery.BaseFunctionalTestCase;
import org.jetbrains.annotations.NotNull;

public class XQueryDuplicateFunctionDeclarationTest extends BaseFunctionalTestCase {

    @NotNull
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/annotator/duplicateFunction/";
    }

    public void testTwoFunctions() {
        executeTest();
    }

    public void testThreeFunctionsWithOverridden() {
        executeTest();
    }

    public void testDoubleDuplicate() {
        executeTest();
    }

    public void testIncompleteFunction() {
        executeTest();
    }

    public void testDuplicateFromImport() throws Exception {
        executeTest(getDefaultFileName(), "ImportedModule.xq");
    }

    public void testFunctionWithDifferentNamespace() {
        executeTest();
    }

    private void executeTest() {
        executeTest(getDefaultFileName());
    }

    private void executeTest(String... files) {
        myFixture.configureByFiles(files);
        myFixture.checkHighlighting(false, false, true);
    }
}
