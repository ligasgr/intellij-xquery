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

package org.intellij.xquery.runner.rt.xqj;

import org.intellij.xquery.runner.rt.RunnerAppTest;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.Theory;

import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BYTE;

/**
 * User: ligasgr
 * Date: 12/01/14
 * Time: 19:16
 */
public class ZorbaRunnerAppTest extends RunnerAppTest {

    @Override
    protected String getDataSourceType() {
        return XQueryDataSourceType.ZORBA.toString();
    }

    @Test
    public void shouldBindVariableForXsByte() throws Exception {
        char byteValue = (char)32;
        String variableValue = "" + byteValue;
        String variableType = XS_BYTE.getTextRepresentation();
        assertBindsVariable(variableType, variableValue, "32");
    }

    @Theory
    @Ignore("Zorba XQJ client doesn't support context item binding")
    public void shouldBindContextItem(DataPair dataPair) throws Exception {
        super.shouldBindContextItem(dataPair);
    }

    @Test
    @Ignore("Zorba XQJ client doesn't support context item binding")
    public void shouldBindContextItemForDocumentNode() throws Exception {
        super.shouldBindContextItemForDocumentNode();
    }
}
