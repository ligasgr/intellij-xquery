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

package org.intellij.xquery.runner.rt;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.FileTestUtil.createFileWithContents;
import static org.intellij.xquery.runner.rt.XQueryItemType.DOCUMENT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_ANY_URI;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BOOLEAN;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE_TIME;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DAY_TIME_DURATION;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DECIMAL;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DOUBLE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_FLOAT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_DAY;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_MONTH;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_MONTH_DAY;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_YEAR;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_YEAR_MONTH;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_HEX_BINARY;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_INT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_LONG;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_SHORT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TIME;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_YEAR_MONTH_DURATION;
import static org.intellij.xquery.runner.rt.XQueryRunConfigBuilder.runConfig;
import static org.junit.Assert.assertThat;

@RunWith(Theories.class)
public abstract class RunnerAppTest {
    protected static final String RETURN_CONTEXT_ITEM_XQUERY = ".";
    protected static final String VALUE = "val";
    protected static final String NUMERIC_VALUE = "123";
    protected static final String NEGATIVE_NUMERIC_VALUE = "-123";
    protected static final String FLOATING_POINT_VALUE = "123.456";
    protected static final String RETURN_VARIABLE_XQUERY = "declare variable $v external; $v";
    private StringOutputStream outputStream;
    private PrintStream printStream;

    @DataPoints
    public static DataPair[] getFullyCompatibleData() {
        return new DataPair[]{
                DataPair.pair(XS_INTEGER, NUMERIC_VALUE),
                DataPair.pair(XS_INT, NUMERIC_VALUE),
                DataPair.pair(XS_SHORT, NUMERIC_VALUE),
                DataPair.pair(XS_LONG, NUMERIC_VALUE),
                DataPair.pair(XS_DECIMAL, FLOATING_POINT_VALUE),
                DataPair.pair(XS_DOUBLE, FLOATING_POINT_VALUE),
                DataPair.pair(XS_FLOAT, FLOATING_POINT_VALUE),
                DataPair.pair(XS_BOOLEAN, "true"),
                DataPair.pair(XS_STRING, VALUE),
                DataPair.pair(XS_HEX_BINARY, "FFFF"),
                DataPair.pair(XS_DAY_TIME_DURATION, "P4DT12H30M5S"),
                DataPair.pair(XS_YEAR_MONTH_DURATION, "P3Y6M"),
                DataPair.pair(XS_DATE, "2013-12-31"),
                DataPair.pair(XS_DATE_TIME, "2013-12-31T23:59:59"),
                DataPair.pair(XS_TIME, "23:59:59"),
                DataPair.pair(XS_G_DAY, "---01"),
                DataPair.pair(XS_G_MONTH, "--01"),
                DataPair.pair(XS_G_MONTH_DAY, "--01-01"),
                DataPair.pair(XS_G_YEAR, "0001"),
                DataPair.pair(XS_G_YEAR_MONTH, "0001-01"),
                DataPair.pair(XS_ANY_URI, VALUE),
        };
    }

    @Before
    public void setUp() throws Exception {
        outputStream = new StringOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Theory
    public void shouldBindContextItem(DataPair dataPair) throws Exception {
        assertBindsContextItem(dataPair.first.getTextRepresentation(), dataPair.second);
    }

    @Theory
    public void shouldBindVariable(DataPair dataPair) throws Exception {
        assertBindsVariable(dataPair.first.getTextRepresentation(), dataPair.second);
    }

    @Test
    public void shouldBindContextItemForDocumentNode() throws Exception {
        String contextItemValue = "<outer><tag>val</tag></outer>";
        String contextItemValueInCData = "<![CDATA[" + contextItemValue + "]]>";
        String contextItemType = DOCUMENT.getTextRepresentation();
        assertBindsContextItem(contextItemType, contextItemValueInCData, contextItemValue);
    }

    @Test
    public void shouldBindVariableForDocumentNode() throws Exception {
        String variableItemValue = "<outer><tag>val</tag></outer>";
        String variableItemValueInCData = "<![CDATA[" + variableItemValue + "]]>";
        String variableItemType = DOCUMENT.getTextRepresentation();
        assertBindsVariable(variableItemType, variableItemValueInCData, variableItemValue);
    }

    @Test
    public void shouldProperlyReturnXmlFromContents() throws Exception {
        String contents = "<outer><tag>val</tag></outer>";
        File xqueryMainFile = createFileWithContents(contents);
        String config = prepareConfigurationForMainFile(xqueryMainFile);
        XQueryRunConfig runConfig = new XQueryRunConfig(config);

        runAppFor(runConfig);

        assertThat(normalize(outputStream.getString()), is(contents));
    }

    protected void assertBindsContextItem(String type, String value) throws Exception {
        assertBindsContextItem(type, value, value);
    }

    protected void assertBindsVariable(String type, String value) throws Exception {
        assertBindsVariable(type, value, value);
    }

    protected void assertBindsContextItem(String contextItemType, String contextItemValue,
                                          String expectedValue) throws Exception {
        XQueryRunConfig config = prepareConfigForContextItem(contextItemType, contextItemValue);

        runAppFor(config);

        assertThat(normalize(outputStream.getString()), is(expectedValue));
    }

    protected void assertBindsVariable(String type, String value,
                                       String expectedValue) throws Exception {
        XQueryRunConfig config = prepareConfigForVariable(type, value);

        runAppFor(config);

        assertThat(normalize(outputStream.getString()), is(expectedValue));
    }

    private String normalize(String initialValue) {
        if (initialValue == null) return null;
        if (initialValue.length() < 2) return initialValue;
        String value = initialValue.replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>","").replaceAll("\\s+", "");
        String unescaped = value.startsWith("\"") && value.endsWith("\"") ? value.substring(1, value.length() - 1) : value;
        if ("true()".equals(unescaped)) {
            return "true";
        } else {
            return unescaped;
        }
    }

    protected XQueryRunConfig prepareConfigForVariable(String type, String value) throws Exception {
        File xqueryMainFile = createFileWithContents(RETURN_VARIABLE_XQUERY);
        return new XQueryRunConfig(prepareConfigurationWithVariableForMainFile(xqueryMainFile, value, type));
    }

    protected XQueryRunConfig prepareConfigForContextItem(String type, String value) throws Exception {
        File xqueryMainFile = createFileWithContents(RETURN_CONTEXT_ITEM_XQUERY);
        return new XQueryRunConfig(prepareConfigurationWithContextItemForMainFile(xqueryMainFile, value, type));
    }

    private void runAppFor(XQueryRunConfig config) throws Exception {
        RunnerApp app = XQueryRunnerAppFactory.getInstance(config, printStream);
        app.runApp();
    }

    protected String prepareConfigurationForMainFile(File xqueryMainFile) {
        return runConfig()
                .withTypeName(getDataSourceType())
                .withMainFileName(xqueryMainFile.getAbsolutePath())
                .build();
    }

    protected String prepareConfigurationWithContextItemForMainFile(File xqueryMainFile, String contextItemValue,
                                                                    String contextItemType) {
        return runConfig()
                .withTypeName(getDataSourceType())
                .withMainFileName(xqueryMainFile.getAbsolutePath())
                .withContextItemType(contextItemType)
                .withContextItemValue(contextItemValue)
                .build();
    }

    protected String prepareConfigurationWithVariableForMainFile(File xqueryMainFile, String value, String type) {
        return runConfig()
                .withTypeName(getDataSourceType())
                .withMainFileName(xqueryMainFile.getAbsolutePath())
                .withVariable("v", value, type)
                .build();
    }

    protected abstract String getDataSourceType();

    protected static class DataPair {
        XQueryItemType first;
        String second;

        private DataPair(XQueryItemType first, String second) {
            this.first = first;
            this.second = second;
        }

        public static DataPair pair(XQueryItemType first, String second) {
            return new DataPair(first, second);
        }
    }
}
