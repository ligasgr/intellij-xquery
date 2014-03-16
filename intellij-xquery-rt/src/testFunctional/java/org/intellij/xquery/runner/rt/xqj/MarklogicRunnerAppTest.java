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

import java.io.File;

/**
 * User: ligasgr
 * Date: 27/01/14
 * Time: 14:28
 */
@Ignore("works only when Marklogic instance is up")
public class MarklogicRunnerAppTest extends RunnerAppTest {

    @Override
    protected String getDataSourceType() {
        return XQueryDataSourceType.MARKLOGIC.toString();
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
                "host=\"localhost\" port=\"8003\" username=\"admin\" password=\"admin\"" +
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
                "host=\"localhost\" port=\"8003\" username=\"admin\" password=\"admin\"" +
                "/>\n" +
                "</run>";
    }
}
