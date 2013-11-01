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

package org.intellij.xquery.functional;

import com.intellij.testFramework.PlatformTestCase;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

import static org.intellij.xquery.XQueryFileType.DEFAULT_EXTENSION_WITH_DOT;

/**
 * User: ligasgr
 * Date: 30/08/13
 * Time: 21:50
 */
public abstract class BaseFunctionalTestCase extends LightPlatformCodeInsightFixtureTestCase {
    public BaseFunctionalTestCase() {
        PlatformTestCase.initPlatformPrefix("not_existing_class", "PlatformLangXml");
    }

    protected boolean isWriteActionRequired() {
        return false;
    }

    protected String getDefaultFileName() {
        return getTestName(false) + DEFAULT_EXTENSION_WITH_DOT;
    }
}
