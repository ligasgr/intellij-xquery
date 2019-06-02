/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiManager;
import com.intellij.testFramework.EdtTestUtil;
import com.intellij.testFramework.fixtures.CodeInsightTestFixture;
import com.intellij.testFramework.fixtures.IdeaProjectTestFixture;
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory;
import com.intellij.testFramework.fixtures.TestFixtureBuilder;
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl;
import com.intellij.util.PlatformUtils;
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;

import static org.intellij.xquery.XQueryFileType.DEFAULT_EXTENSION_WITH_DOT;

public abstract class BaseFunctionalTestCase extends TestCase {

    public BaseFunctionalTestCase() {
        System.setProperty(PlatformUtils.PLATFORM_PREFIX_KEY, PlatformUtils.IDEA_CE_PREFIX);
    }

    protected CodeInsightTestFixture myFixture;

    protected boolean isWriteActionRequired() {
        return false;
    }

    protected String getDefaultFileName() {
        return getTestName() + DEFAULT_EXTENSION_WITH_DOT;
    }

    protected String getTestName() {
        String fullTestName = getName();
        if (fullTestName.startsWith("test")) {
            return fullTestName.substring("test".length());
        }
        return fullTestName;
    }

    protected Project getProject() {
        return myFixture.getProject();
    }

    protected PsiManager getPsiManager() {
        return PsiManager.getInstance(getProject());
    }

    @Override
    protected void runTest() throws Throwable {
        if (isWriteActionRequired()) {
            new WriteCommandAction(myFixture.getProject()) {
                @Override
                protected void run(@NotNull Result result) throws Throwable {
                    doRunTest();
                }
            }.execute();
        } else {
            doRunTest();
        }
    }

    private void doRunTest() throws Throwable {
        EdtTestUtil.runInEdtAndWait(() -> {
            try {
                BaseFunctionalTestCase.super.runTest();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }

    @Override
    protected void setUp() throws Exception {
        IdeaTestFixtureFactory factory = IdeaTestFixtureFactory.getFixtureFactory();
        TestFixtureBuilder<IdeaProjectTestFixture> fixtureBuilder = factory.createLightFixtureBuilder(null);
        final IdeaProjectTestFixture fixture = fixtureBuilder.getFixture();
        myFixture = IdeaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(fixture, new LightTempDirTestFixtureImpl(true));

        myFixture.setTestDataPath(getTestDataPath());
        myFixture.setUp();
    }

    protected String getTestDataPath() {
        return PathManager.getHomePath();
    }

    @Override
    protected void tearDown() throws Exception {
        myFixture.tearDown();
    }
}
