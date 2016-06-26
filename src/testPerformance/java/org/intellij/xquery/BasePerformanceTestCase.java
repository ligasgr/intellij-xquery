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

package org.intellij.xquery;

import com.intellij.testFramework.PlatformTestCase;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

import static org.intellij.xquery.XQueryFileType.DEFAULT_EXTENSION_WITH_DOT;

/**
 * User: ligasgr
 * Date: 24/11/13
 * Time: 13:33
 */
public abstract class BasePerformanceTestCase extends BaseFunctionalTestCase {
    private static final String MODULE_DECLARATION = "module namespace prefix_%s_%s='module_%s_%s';\n";
    private static final String IMPORT_DECLARATION = "import module namespace module_%s_%s = 'module_%s_%s.xq';\n";
    private static final String VARIABLE_DECLARATION = "declare variable $prefix_%s_%s:var_%s:= ();\n";
    private static final String FUNCTION_DECLARATION = "declare function prefix_%s_%s:function_%s(){()};\n";
    private static final String MODULE_FILENAME = "module_%s_%s.xq";
    private static final String MAIN_FILE = "target";

    protected String testName;

    protected String getDefaultFileName() {
        return getTestName() + DEFAULT_EXTENSION_WITH_DOT;
    }

    public void setUp() throws Exception {
        super.setUp();
        testName = getTestName();
    }


    protected void setupTestFiles(int numberOfProjectFiles, int numberOfImports, int numberOfFunctionsPerModule,
                                int numberOfVariablesPerModule, String testSpecificContent, String testName) {
        String mainFileName = String.format(MODULE_FILENAME, testName, MAIN_FILE);
        myFixture.addFileToProject(mainFileName,
                getMainFileContent(numberOfImports,
                        testSpecificContent, testName));
        myFixture.configureFromTempProjectFile(mainFileName);

        for (int i = 0; i < numberOfProjectFiles; i++) {
            StringBuilder projectFileContent = new StringBuilder(String.format(MODULE_DECLARATION, testName, i,
                    testName, i));
            for (int j = 0; j < numberOfVariablesPerModule; j++) {
                projectFileContent.append(String.format(VARIABLE_DECLARATION, testName, i, j));
            }
            for (int j = 0; j < numberOfFunctionsPerModule; j++) {
                projectFileContent.append(String.format(FUNCTION_DECLARATION, testName, i, j));
            }
            String importedFileName = String.format(MODULE_FILENAME, testName, i);
            myFixture.addFileToProject(importedFileName, projectFileContent.toString());
        }
    }

    private String getMainFileContent(int numberOfImports, String testSpecificContent, String testName) {
        StringBuilder mainFileContent = new StringBuilder(String.format(MODULE_DECLARATION, testName, MAIN_FILE,
                testName, MAIN_FILE));

        for (int i = 0; i < numberOfImports; i++) {
            mainFileContent.append(String.format(IMPORT_DECLARATION, testName, i, testName, i));
        }

        mainFileContent.append(testSpecificContent);
        return mainFileContent.toString();
    }
}
