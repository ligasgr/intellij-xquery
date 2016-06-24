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

package org.intellij.xquery.runner.rt.vendor.exist;

import org.intellij.xquery.runner.rt.RunnerAppTest;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;

import java.io.File;

import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DURATION;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_LANGUAGE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NORMALIZED_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TOKEN;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED_ATOMIC;
import static org.intellij.xquery.runner.rt.XQueryRunConfigBuilder.runConfig;

@Ignore("works only when Exist instance is up")
public class ExistNativeRunnerAppTest extends RunnerAppTest {

    @DataPoints
    public static DataPair[] getExistCompatibleData() {
        return new DataPair[]{
                DataPair.pair(XS_DURATION, "P3Y6M4DT12H30M5S"),
                DataPair.pair(XS_BYTE, "32"),
                DataPair.pair(XS_NORMALIZED_STRING, VALUE),
                DataPair.pair(XS_TOKEN, VALUE),
                DataPair.pair(XS_LANGUAGE, VALUE),
                DataPair.pair(XS_UNTYPED_ATOMIC, VALUE),
        };
    }

    @Theory
    @Ignore("Native eXist client doesn't support context item binding")
    public void shouldBindContextItem(DataPair dataPair) throws Exception {
        super.shouldBindContextItem(dataPair);
    }

    @Test
    @Ignore("Native eXist client doesn't support context item binding")
    public void shouldBindContextItemForDocumentNode() throws Exception {
        super.shouldBindContextItemForDocumentNode();
    }

    @Override
    protected String getDataSourceType() {
        return XQueryDataSourceType.EXIST_NATIVE.toString();
    }

    protected String prepareConfigurationForMainFile(File xqueryMainFile) {
        return runConfig()
                .withTypeName(getDataSourceType())
                .withMainFileName(xqueryMainFile.getAbsolutePath())
                .withConnectionData("localhost", "8080", "admin", "admin")
                .build();
    }

    protected String prepareConfigurationWithVariableForMainFile(File xqueryMainFile, String value, String type) {
        return runConfig()
                .withTypeName(getDataSourceType())
                .withMainFileName(xqueryMainFile.getAbsolutePath())
                .withVariable("v", value, type)
                .withConnectionData("localhost", "8080", "admin", "admin")
                .build();
    }
}
