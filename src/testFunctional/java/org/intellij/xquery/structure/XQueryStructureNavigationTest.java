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

package org.intellij.xquery.structure;

import com.intellij.codeInsight.actions.BaseCodeInsightAction;
import com.intellij.codeInsight.navigation.actions.MethodDownAction;
import com.intellij.codeInsight.navigation.actions.MethodUpAction;
import org.intellij.xquery.BaseFunctionalTestCase;

public class XQueryStructureNavigationTest extends BaseFunctionalTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/structure";
    }

    public void testMainModuleMoveUp() {
        MethodUpAction methodUpAction = new MethodUpAction();
        doTest("MainModuleNavigationBottom.xq", "MainModuleNavigationTop.xq", 4, methodUpAction);
    }

    public void testMainModuleMoveDown() {
        MethodDownAction methodDownAction = new MethodDownAction();
        doTest("MainModuleNavigationTop.xq", "MainModuleNavigationBottom.xq", 4, methodDownAction);
    }

    public void testLibraryModuleMoveUp() {
        MethodUpAction methodUpAction = new MethodUpAction();
        doTest("LibraryModuleNavigationBottom.xq", "LibraryModuleNavigationTop.xq", 4, methodUpAction);
    }

    public void testLibraryModuleMoveDown() {
        MethodDownAction methodDownAction = new MethodDownAction();
        doTest("LibraryModuleNavigationTop.xq", "LibraryModuleNavigationBottom.xq", 4, methodDownAction);
    }

    private void doTest(String beforeFile, String afterFile, int repeatActionTimes, BaseCodeInsightAction action) {
        myFixture.configureByFile(beforeFile);

        while (repeatActionTimes-- > 0) {
            action.actionPerformedImpl(getProject(), myFixture.getEditor());
        }

        myFixture.checkResultByFile(afterFile);
    }

}
