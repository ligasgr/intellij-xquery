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

package org.intellij.xquery.runner.rt.vendor.saxon;

import org.intellij.xquery.runner.rt.RunnerAppTest;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.junit.Test;

import static org.intellij.xquery.runner.rt.XQueryItemType.TEXT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_ANY_URI;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE_TIME_STAMP;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_LANGUAGE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NORMALIZED_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TOKEN;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED_ATOMIC;

/**
 * User: ligasgr
 * Date: 12/01/14
 * Time: 19:14
 */
public class SaxonRunnerAppTest extends RunnerAppTest {

    @Override
    protected String getDataSourceType() {
        return XQueryDataSourceType.SAXON.toString();
    }

    @Test
    public void shouldBindVariableForXsByte() throws Exception {
        String variableValue = "32";
        String variableType = XS_BYTE.getTextRepresentation();
        assertBindsVariable(variableType, variableValue, "32");
    }

    @Test
    public void shouldBindContextItemForXsNormalizedString() throws Exception {
        assertBindsContextItem(XS_NORMALIZED_STRING.getTextRepresentation(), VALUE);
    }

    @Test
    public void shouldBindVariableForXsNormalizedString() throws Exception {
        assertBindsVariable(XS_NORMALIZED_STRING.getTextRepresentation(), VALUE);
    }

    @Test
    public void shouldBindVariableForXsToken() throws Exception {
        assertBindsVariable(XS_TOKEN.getTextRepresentation(), VALUE);
    }

    @Test
    public void shouldBindVariableForXsLanguage() throws Exception {
        assertBindsVariable(XS_LANGUAGE.getTextRepresentation(), VALUE);
    }

    @Test
    public void shouldBindVariableForXsDateTimeStamp() throws Exception {
        assertBindsVariable(XS_DATE_TIME_STAMP.getTextRepresentation(), "2013-12-31T23:59:59Z");
    }

    @Test
    public void shouldBindVariableForXsUntyped() throws Exception {
        assertBindsVariable(XS_UNTYPED.getTextRepresentation(), VALUE);
    }
    @Test
    public void shouldBindVariableForXsUntypedAtomic() throws Exception {
        assertBindsVariable(XS_UNTYPED_ATOMIC.getTextRepresentation(), VALUE);
    }

    @Test
    public void shouldBindVariableForXsAnyUri() throws Exception {
        assertBindsVariable(XS_ANY_URI.getTextRepresentation(), VALUE);
    }

    @Test
    public void shouldBindVariableForXsText() throws Exception {
        assertBindsVariable(TEXT.getTextRepresentation(), VALUE);
    }


}
