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
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BOOLEAN;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE_TIME;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DAY_TIME_DURATION;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DECIMAL;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DOUBLE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DURATION;
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
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_SHORT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TIME;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_INT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_LONG;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_SHORT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_YEAR_MONTH_DURATION;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 09/01/14
 * Time: 17:31
 */
@RunWith(Theories.class)
public abstract class RunnerAppTest {
    private static final String RETURN_CONTEXT_ITEM_XQUERY = ".";
    protected static final String VALUE = "val";
    private static final String NUMERIC_VALUE = "123";
    private static final String NEGATIVE_NUMERIC_VALUE = "-123";
    private static final String FLOATING_POINT_VALUE = "123.456";
    private static final String RETURN_VARIABLE_XQUERY = "declare variable $v external; $v";
    private StringOutputStream outputStream;
    private PrintStream printStream;

    @DataPoints
    public static DataPair[] getData() {
        return new DataPair[]{
                DataPair.pair(XS_INTEGER, NUMERIC_VALUE),
                DataPair.pair(XS_NON_NEGATIVE_INTEGER, NUMERIC_VALUE),
                DataPair.pair(XS_POSITIVE_INTEGER, NUMERIC_VALUE),
                DataPair.pair(XS_NON_POSITIVE_INTEGER, NEGATIVE_NUMERIC_VALUE),
                DataPair.pair(XS_NEGATIVE_INTEGER, NEGATIVE_NUMERIC_VALUE),
                DataPair.pair(XS_INT, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_INT, NUMERIC_VALUE),
                DataPair.pair(XS_SHORT, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_SHORT, NUMERIC_VALUE),
                DataPair.pair(XS_LONG, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_LONG, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_BYTE, "10"),
                DataPair.pair(XS_DECIMAL, FLOATING_POINT_VALUE),
                DataPair.pair(XS_DOUBLE, FLOATING_POINT_VALUE),
                DataPair.pair(XS_FLOAT, FLOATING_POINT_VALUE),
                DataPair.pair(XS_BOOLEAN, "true"),
                DataPair.pair(XS_STRING, VALUE),
                DataPair.pair(XS_HEX_BINARY, "FFFF"),
                DataPair.pair(XS_DURATION, "P3Y6M4DT12H30M5S"),
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
        String contextItemValue = "<tag>val</tag>";
        String contextItemValueInCData = "<![CDATA[" + contextItemValue + "]]>";
        String contextItemType = DOCUMENT.getTextRepresentation();
        assertBindsContextItem(contextItemType, contextItemValueInCData, contextItemValue);
    }

    @Test
    public void shouldBindVariableForDocumentNode() throws Exception {
        String contextItemValue = "<tag>val</tag>";
        String contextItemValueInCData = "<![CDATA[" + contextItemValue + "]]>";
        String contextItemType = DOCUMENT.getTextRepresentation();
        assertBindsVariable(contextItemType, contextItemValueInCData, contextItemValue);
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

        assertThat(outputStream.getString(), is(expectedValue));
    }

    protected void assertBindsVariable(String type, String value,
                                       String expectedValue) throws Exception {
        XQueryRunConfig config = prepareConfigForVariable(type, value);

        runAppFor(config);

        assertThat(outputStream.getString(), is(expectedValue));
    }

    private XQueryRunConfig prepareConfigForVariable(String type, String value) throws Exception {
        File xqueryMainFile = createFileWithContents(RETURN_VARIABLE_XQUERY);
        return new XQueryRunConfig(prepareConfigurationWithVariableForMainFile(xqueryMainFile, value, type));
    }

    private XQueryRunConfig prepareConfigForContextItem(String type, String value) throws Exception {
        File xqueryMainFile = createFileWithContents(RETURN_CONTEXT_ITEM_XQUERY);
        return new XQueryRunConfig(prepareConfigurationWithContextItemForMainFile(xqueryMainFile, value, type));
    }

    private void runAppFor(XQueryRunConfig config) throws Exception {
        RunnerApp app = XQueryRunnerAppFactory.getInstance(config, printStream);
        app.run();
    }

    protected String prepareConfigurationWithContextItemForMainFile(File xqueryMainFile, String contextItemValue,
                                                                    String contextItemType) {
        return "<run>\n" +
                "<xQueryConfiguration " +
                "mainFileName=\"" + xqueryMainFile.getAbsolutePath() + "\" " +
                "contextItemEnabled=\"true\" contextItemFromEditorEnabled=\"true\" contextItemType=\"" +
                contextItemType + "\">" +
                "<contextItemText>" + contextItemValue + "</contextItemText>" +
                "</xQueryConfiguration>\n" +
                "<data-source-configuration " +
                "type=\"" + getDataSourceType() + "\" " +
                "/>\n" +
                "</run>";
    }

    protected String prepareConfigurationWithVariableForMainFile(File xqueryMainFile, String value, String type) {
        return "<run>\n" +
                "<xQueryConfiguration " +
                "mainFileName=\"" + xqueryMainFile.getAbsolutePath() + "\" " +
                "contextItemEnabled=\"false\"/>\n" +
                "<variables>" +
                "<list>" +
                "<variable name=\"v\" active=\"true\" type=\"" + type + "\">" + value + "</variable>" +
                "</list>" +
                "</variables>" +
                "<data-source-configuration " +
                "type=\"" + getDataSourceType() + "\" " +
                "/>\n" +
                "</run>";
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
