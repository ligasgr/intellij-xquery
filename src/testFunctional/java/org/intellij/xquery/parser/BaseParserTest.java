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

package org.intellij.xquery.parser;

import com.intellij.testFramework.ParsingTestCase;

/**
 * User: ligasgr
 * Date: 08/04/14
 * Time: 21:01
 */
public abstract class BaseParserTest extends ParsingTestCase {

    public BaseParserTest(String subtype) {
        super(subtype, "xq", new XQueryParserDefinition());
    }

    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/parser";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }
}
