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

package org.intellij.xquery.completion.namespace;

import com.intellij.codeInsight.completion.CompletionType;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.reference.MatchingStringCondition;

import java.util.List;

import static com.intellij.util.containers.ContainerUtil.findAll;

/**
 * User: ligasgr
 * Date: 27/11/13
 * Time: 20:47
 */
public class NamespaceCollectorTest extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/completion/namespace";
    }

    public void testFunctionNamespaceCompletion() {
        myFixture.configureByFiles("FunctionNamespaceNameCompletion.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("xxx"));
        assertEquals(0, referenceBasedEntries.size());
    }

    public void testVariableNamespaceCompletion() {
        myFixture.configureByFiles("VariableNamespaceNameCompletion.xq");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        List<String> referenceBasedEntries = findAll(strings, new MatchingStringCondition("xxx"));
        assertEquals(0, referenceBasedEntries.size());
    }
}
