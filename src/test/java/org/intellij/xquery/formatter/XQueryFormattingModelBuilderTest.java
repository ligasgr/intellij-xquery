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

package org.intellij.xquery.formatter;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import org.intellij.xquery.XQueryBaseTestCase;

/**
 * User: ligasgr
 * Date: 22/08/13
 * Time: 23:41
 */
public abstract class XQueryFormattingModelBuilderTest extends XQueryBaseTestCase {

    void executeTest() {
        myFixture.configureByFiles(getTestName(false) + ".xq");
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
                CodeStyleManager.getInstance(getProject()).reformat(myFixture.getFile());
            }
        });
        myFixture.checkResultByFile(getTestName(false) + "_after.xq");
    }

    CodeStyleSettings getSettings() {
        return CodeStyleSettingsManager.getSettings(getProject());
    }
}

