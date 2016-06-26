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

package org.intellij.xquery.formatter;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import junit.framework.Assert;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.XQueryFileType;
import org.intellij.xquery.XQueryLanguage;
import org.intellij.xquery.formatter.settings.XQueryCodeStyleSettings;

/**
 * User: ligasgr
 * Date: 22/08/13
 * Time: 23:41
 */
public abstract class XQueryFormattingModelBuilderTest extends BaseFunctionalTestCase {

    private CodeStyleSettings myTemporarySettings;

    void executeTest() {
        myFixture.configureByFiles(getTestName() + ".xq");
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
                CodeStyleManager.getInstance(getProject()).reformat(myFixture.getFile());
            }
        });
        myFixture.checkResultByFile(getTestName() + "_after.xq");
    }


    void executeTest(String before, String after) {
        myFixture.configureByText("a.xq", before);
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
                CodeStyleManager.getInstance(getProject()).reformat(myFixture.getFile());
            }
        });
        myFixture.checkResult(after);
    }

    CommonCodeStyleSettings getSettings() {
        return myTemporarySettings.getCommonSettings(XQueryLanguage.INSTANCE);
    }

    XQueryCodeStyleSettings getXQuerySettings() {
        return myTemporarySettings.getCustomSettings(XQueryCodeStyleSettings.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setTestStyleSettings();
    }

    @Override
    public void tearDown() throws Exception {
        restoreStyleSettings();
        super.tearDown();
    }

    private void setTestStyleSettings() {
        CodeStyleSettingsManager settingsManager = CodeStyleSettingsManager.getInstance(getProject());
        CodeStyleSettings currSettings = settingsManager.getCurrentSettings();
        Assert.assertNotNull(currSettings);
        myTemporarySettings = currSettings.clone();
        CodeStyleSettings.IndentOptions indentOptions = myTemporarySettings.getIndentOptions(XQueryFileType.INSTANCE);
        Assert.assertNotNull(indentOptions);
        settingsManager.setTemporarySettings(myTemporarySettings);
    }

    private void restoreStyleSettings() {
        CodeStyleSettingsManager.getInstance(getProject()).dropTemporarySettings();
    }
}

