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

package org.intellij.xquery.inspection.imports;

import org.intellij.xquery.XQueryBaseTestCase;
import org.intellij.xquery.XQueryFileType;

public class UnusedImportsInspectionTest extends XQueryBaseTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/org/intellij/xquery/inspection/imports";
    }

    public void testImportWithDefaultNamespaceForFunctionCall() {
        executeTest();
    }

    public void testImportWithDefaultNamespaceForNamedFunctionRef() {
        executeTest();
    }

    public void testImportWithDefaultNamespaceForVariableRef() {
        executeTest();
    }

    public void testImportWithDefaultNamespaceUnused() {
        executeTest();
    }

    public void testImportWithoutPrefixWithDeclaredNamespace() {
        executeTest();
    }

    public void testImportWithoutPrefixWithoutDeclaredNamespace() {
        executeTest();
    }

    public void testImportWithPrefix() {
        executeTest();
    }

    private void executeTest() {
        myFixture.enableInspections(UnusedImportsInspection.class);

        myFixture.testHighlighting(true, false, false, getDefaultFileName());
    }
}
