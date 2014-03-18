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
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;

import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DURATION;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_INT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_LONG;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_SHORT;

/**
 * User: ligasgr
 * Date: 12/01/14
 * Time: 19:16
 */
@Ignore("works only when zorba_api is present on LD_LIBRARY_PATH")
public class ZorbaRunnerAppTest extends RunnerAppTest {

    @DataPoints
    public static DataPair[] getZorbaCompatibleData() {
        return new DataPair[]{
                DataPair.pair(XS_NON_NEGATIVE_INTEGER, NUMERIC_VALUE),
                DataPair.pair(XS_POSITIVE_INTEGER, NUMERIC_VALUE),
                DataPair.pair(XS_NON_POSITIVE_INTEGER, NEGATIVE_NUMERIC_VALUE),
                DataPair.pair(XS_NEGATIVE_INTEGER, NEGATIVE_NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_INT, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_SHORT, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_LONG, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_BYTE, "10"),
                DataPair.pair(XS_DURATION, "P3Y6M4DT12H30M5S"),
        };
    }

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
