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

package org.intellij.performance;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.PlatformTestUtil;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.ThrowableRunnable;
import org.intellij.xquery.XQueryBaseTestCase;
import org.intellij.xquery.psi.XQueryFunctionCall;

import java.util.Collection;

import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 19/08/13
 * Time: 19:28
 */
public class FunctionReferencesPerformanceTest extends XQueryBaseTestCase {

    private static final String MODULE_DECLARATION = "module namespace prefix_%s='module_%s';\n";
    private static final String IMPORT_DECLARATION = "import module namespace module_%s = 'module_%s.xq';\n";
    private static final String VARIABLE_DECLARATION = "declare variable $prefix_%s:var_%s:= ();\n";
    private static final String FUNCTION_DECLARATION = "declare function prefix_%s:function_%s(){()};\n";
    private static final String MODULE_FILENAME = "module_%s.xq";
    private static final String MAIN_FILE = "target";

    public void testCompletion() throws Exception {
        setupTestFiles(1000, 100, 10, "declare function prefix_target:example() {<caret>};");

        PlatformTestUtil.startPerformanceTest(getTestName(false), 11000, new ThrowableRunnable() {
            @Override
            public void run() throws Exception {
                for (int i = 0; i < 100; i++) {
                    myFixture.complete(CompletionType.BASIC, 1);
                    myFixture.getLookupElementStrings();
                }
            }
        }).cpuBound().attempts(1).assertTiming();
    }

    public void testReferenceResolution() throws Exception {
        setupTestFiles(1000, 100, 10, "declare function prefix_target:example() {module_0:fun<caret>ction_9()};");
        PlatformTestUtil.startPerformanceTest(getTestName(false), 1000, new ThrowableRunnable() {
            @Override
            public void run() throws Exception {
                for (int i = 0; i < 100; i++) {
                    getTargetOfReferenceAtCaret(myFixture, XQueryFunctionCall.class);
                }
            }
        }).cpuBound().attempts(1).assertTiming();
    }

    public void testFindUsage() throws Exception {
        setupTestFiles(1000, 100, 10, "declare function prefix_target:example() {module_0:fun<caret>ction_9()};");
        final PsiElement source = myFixture.getElementAtCaret();
        PlatformTestUtil.startPerformanceTest(getTestName(false), 50000, new ThrowableRunnable() {
            @Override
            public void run() throws Exception {
                for (int i = 0; i < 100; i++) {
                    myFixture.findUsages(source);
                }
            }
        }).cpuBound().attempts(1).assertTiming();
    }

    private void setupTestFiles(int numberOfProjectFiles, int numberOfImports, int numberOfFunctionsPerModule, String
            testSpecificContent) {
        myFixture.addFileToProject(String.format(MODULE_FILENAME, MAIN_FILE), getMainFileContent(numberOfImports, testSpecificContent));
        myFixture.configureFromTempProjectFile(String.format(MODULE_FILENAME, MAIN_FILE));

        for (int i = 0; i < numberOfProjectFiles; i++) {
            StringBuilder projectFileContent = new StringBuilder(String.format(MODULE_DECLARATION, i, i));
            for (int j = 0; j < 20; j++) {
                projectFileContent.append(String.format(VARIABLE_DECLARATION, i, j));
            }
            for (int j = 0; j < numberOfFunctionsPerModule; j++) {
                projectFileContent.append(String.format(FUNCTION_DECLARATION, i, j));
            }
            myFixture.addFileToProject(String.format(MODULE_FILENAME, i), projectFileContent.toString());
        }
    }

    private String getMainFileContent(int numberOfImports, String testSpecificContent) {
        StringBuilder mainFileContent = new StringBuilder(String.format(MODULE_DECLARATION, MAIN_FILE, MAIN_FILE));

        for (int i = 0; i < numberOfImports; i++) {
            mainFileContent.append(String.format(IMPORT_DECLARATION, i, i));
        }

        mainFileContent.append(testSpecificContent);
        return mainFileContent.toString();
    }
}
