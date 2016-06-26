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

package org.intellij.xquery.completion;

import com.intellij.codeInsight.completion.CompletionType;
import org.intellij.xquery.BaseFunctionalTestCase;

import java.util.List;

public abstract class BaseCollectorTest extends BaseFunctionalTestCase {

    protected List<String> getCompletionProposals() {
        return getCompletionProposals(getTestName() + ".xq");
    }

    protected List<String> getCompletionProposals(String additionalPath) {
        return getCompletionProposals(getTestName() + ".xq", additionalPath);
    }

    private List<String> getCompletionProposals(String... additionalPaths) {
        myFixture.configureByFiles(additionalPaths);
        myFixture.complete(CompletionType.BASIC, 1);
        return myFixture.getLookupElementStrings();
    }

    protected void testInsertHandler(char completionChar) {
        testInsertHandler(null, completionChar);
    }

    protected void testInsertHandler(String typedText, char completionChar) {
        myFixture.configureByFile(getTestName() + ".xq");

        myFixture.completeBasic();
        if (typedText != null) {
            myFixture.type(typedText);
        }
        myFixture.type(completionChar);

        myFixture.checkResultByFile(getTestName() + "_after.xq");
    }
}
