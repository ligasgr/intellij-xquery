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

package org.intellij.xquery;

import com.intellij.psi.PsiElement;
import com.intellij.usageView.UsageInfo;

import java.util.Collection;

/**
 * User: ligasgr
 * Date: 24/11/13
 * Time: 14:01
 */
public class BaseWarmUpPerformanceTest extends BasePerformanceTestCase {

    public void testWarmsSystemUp() {
        String template = "declare function prefix_%s_target:example() {module_%s_0:fun<caret>ction_9()};";
        String testSpecificContent = String.format(template, testName, testName);
        setupTestFiles(1000, 100, 10, 10, testSpecificContent, testName);
        final PsiElement source = myFixture.getElementAtCaret();
        for (int i = 0; i < 100; i++) {
            Collection<UsageInfo> usages = myFixture.findUsages(source);
            assertTrue(usages != null && usages.size() > 0);
        }
    }
}
