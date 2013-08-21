/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com>
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

package org.intellij.xquery.performance;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

import static com.intellij.testFramework.PlatformTestCase.initPlatformPrefix;

/**
 * User: ligasgr
 * Date: 19/08/13
 * Time: 19:28
 */
public class FunctionCompletionPerformanceTest extends LightPlatformCodeInsightFixtureTestCase {

    private static final String MODULE_DECLARATION = "module namespace prefix_%s='module_%s';\n";
    private static final String IMPORT_DECLARATION = "import module namespace module_%s = 'module_%s.xq';\n";
    private static final String VARIABLE_DECLARATION = "declare variable $prefix_%s:var_%s:= ();\n";
    private static final String FUNCTION_DECLARATION = "declare function prefix_%s:function_%s(){()};\n";
    private static final String MODULE_FILENAME = "module_%s.xq";
    private static final String MAIN_FILE = "target";

    public FunctionCompletionPerformanceTest() {
        initPlatformPrefix("com.intellij.idea.IdeaUltimateApplication", "PlatformLangXml");
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        long start = System.currentTimeMillis();
        setupTestFiles(1000, 100, 10);
        System.out.println("setup time: " + (System.currentTimeMillis() - start));
    }

    public void testCompletion() throws Exception {
        for (int i = 0; i < 5; i++) {
            long start = System.currentTimeMillis();
            myFixture.complete(CompletionType.BASIC, 1);
            myFixture.getLookupElementStrings();
            System.out.println("completion no " + (i + 1) + ": " + (System.currentTimeMillis() - start));
        }
    }

    private void setupTestFiles(int numberOfProjectFiles, int numberOfImports, int numberOfFunctionsPerModule) {
        myFixture.addFileToProject(String.format(MODULE_FILENAME, MAIN_FILE), getMainFileContent(numberOfImports));
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

    private String getMainFileContent(int numberOfImports) {
        StringBuilder mainFileContent = new StringBuilder(String.format(MODULE_DECLARATION, MAIN_FILE, MAIN_FILE));

        for (int i = 0; i < numberOfImports; i++) {
            mainFileContent.append(String.format(IMPORT_DECLARATION, i, i));
        }

        mainFileContent.append("declare function prefix_target:example() {<caret>};");
        return mainFileContent.toString();
    }
}
