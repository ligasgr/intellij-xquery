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
import org.junit.experimental.theories.DataPoints;

import java.io.File;

import static org.intellij.xquery.runner.rt.XQueryItemType.TEXT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_ANY_URI;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE_TIME_STAMP;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DURATION;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_LANGUAGE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NORMALIZED_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TOKEN;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_INT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_LONG;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_SHORT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED_ATOMIC;

/**
 * User: ligasgr
 * Date: 16/03/14
 * Time: 16:08
 */
@Ignore("works only when Marklogic instance is up")
public class SednaRunnerAppTest extends RunnerAppTest {


    @DataPoints
    public static DataPair[] getSaxonCompatibleData() {
        return new DataPair[]{
                DataPair.pair(XS_DURATION, "P3Y6M4DT12H30M5S"),
                DataPair.pair(XS_BYTE, "32"),
                DataPair.pair(XS_NORMALIZED_STRING, VALUE),
                DataPair.pair(XS_TOKEN, VALUE),
                DataPair.pair(XS_LANGUAGE, VALUE),
                DataPair.pair(XS_UNTYPED_ATOMIC, VALUE),
                DataPair.pair(XS_ANY_URI, VALUE),
        };
    }

    @Override
    protected String getDataSourceType() {
        return XQueryDataSourceType.SEDNA.toString();
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
                "type=\"SEDNA\" configEnabled=\"false\" configFile=\"\" host=\"localhost\" port=\"\" username=\"\" " +
                "password=\"\" userDefinedLibraryEnabled=\"false\" databaseName=\"auction\"" +
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
                "type=\"SEDNA\" configEnabled=\"false\" configFile=\"\" host=\"localhost\" port=\"\" username=\"\" " +
                "password=\"\" userDefinedLibraryEnabled=\"false\" databaseName=\"auction\"" +
                "/>\n" +
                "</run>";
    }
}
