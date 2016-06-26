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

package org.intellij.xquery.reference.namespace;

import com.intellij.refactoring.actions.SafeDeleteAction;
import org.intellij.xquery.BaseFunctionalTestCase;

public class XQueryPrefixReferenceTest extends BaseFunctionalTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/reference/namespace";
    }

    public void testModuleNamespaceSafeDelete() {
        doTest();
    }

    public void testImportedNamespaceSafeDelete() {
        doTest();
    }

    public void testDeclaredNamespaceSafeDelete() {
        doTest();
    }

    public void testInlineNamespaceSafeDelete() {
        doTest();
    }

    private void doTest() {
        String testName = getTestName();
        String fileBefore = testName + ".xq";
        String fileAfter = testName + "After" + ".xq";
        assertAsExpectedAfterSafeDelete(fileBefore, fileAfter);
    }

    private void assertAsExpectedAfterSafeDelete(String fileBefore, String fileAfter) {
        myFixture.configureByFiles(fileBefore);
        myFixture.testAction(new SafeDeleteAction());
        myFixture.checkResultByFile(fileBefore, fileAfter, false);
    }
}
