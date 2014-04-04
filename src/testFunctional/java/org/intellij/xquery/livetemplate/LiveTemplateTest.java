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

package org.intellij.xquery.livetemplate;

import com.intellij.codeInsight.lookup.Lookup;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.codeInsight.lookup.impl.LookupImpl;
import com.intellij.codeInsight.template.impl.actions.ListTemplatesAction;
import com.intellij.openapi.editor.Editor;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.junit.Test;

public class LiveTemplateTest extends BaseFunctionalTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/livetemplate";
    }

    @Test
    public void testDeclareVariableTemplate() throws Exception {
        doTest();
    }

    private void doTest() throws Exception {
        final String testName = getTestName(false);
        myFixture.configureByFile(testName + ".xq");
        expandTemplate(myFixture.getEditor());
        myFixture.checkResultByFile(String.format("%s_after.xq", testName));
    }

    public void expandTemplate(final Editor editor) {
        new ListTemplatesAction().actionPerformedImpl(editor.getProject(), editor);
        ((LookupImpl) LookupManager.getActiveLookup(editor)).finishLookup(Lookup.NORMAL_SELECT_CHAR);
    }

}
