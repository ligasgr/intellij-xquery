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

package org.intellij.xquery.performance;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.PlatformTestUtil;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.ThrowableRunnable;
import org.intellij.xquery.psi.XQueryVarRef;

import java.util.Collection;
import java.util.List;

import static org.intellij.xquery.functional.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 19/08/13
 * Time: 19:28
 */
public class VariableReferencesPerformanceTest extends BasePerformanceTestCase {

    public void testVariableCompletion() throws Exception {
        String template = "declare function prefix_%s_target:example() {$<caret>}";
        String testSpecificContent = String.format(template, testName);
        setupTestFiles(1000, 100, 10, 10, testSpecificContent, testName);

        PlatformTestUtil.startPerformanceTest(testName, 40000, new ThrowableRunnable() {
            @Override
            public void run() throws Exception {
                for (int i = 0; i < 100; i++) {
                    myFixture.complete(CompletionType.BASIC, 1);
                    List<String> lookupElementStrings = myFixture.getLookupElementStrings();
                    assertTrue(lookupElementStrings != null && lookupElementStrings.size() > 0);
                }
            }
        }).cpuBound().attempts(1).assertTiming();
    }

    public void testVariableReferenceResolution() throws Exception {
        String template = "declare function prefix_%s_target:example() {$module_%s_0:v<caret>ar_9()};";
        String testSpecificContent = String.format(template, testName, testName);
        setupTestFiles(1000, 100, 10, 10, testSpecificContent, testName);

        PlatformTestUtil.startPerformanceTest(testName, 1000, new ThrowableRunnable() {
            @Override
            public void run() throws Exception {
                for (int i = 0; i < 100; i++) {
                    assertTrue(getTargetOfReferenceAtCaret(myFixture, XQueryVarRef.class) != null);
                }
            }
        }).cpuBound().attempts(1).assertTiming();
    }

    public void testVariableFindUsage() throws Exception {
        String template = "declare function prefix_%s_target:example() {$module_%s_0:v<caret>ar_9()};";
        String testSpecificContent = String.format(template, testName, testName);
        setupTestFiles(1000, 100, 10, 10, testSpecificContent, testName);
        final PsiElement source = myFixture.getElementAtCaret();


        PlatformTestUtil.startPerformanceTest(testName, 300000, new ThrowableRunnable() {
            @Override
            public void run() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Collection<UsageInfo> usages = myFixture.findUsages(source);
                    assertTrue(usages != null && usages.size() > 0);
                }
            }
        }).cpuBound().attempts(1).assertTiming();
    }
}
