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

package org.intellij.xquery.runner.rt;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 09/10/13
 * Time: 00:06
 */
public class XQueryRunConfigTest {

    private static final String MAIN_FILE_NAME = "/path/to/main/file.xq";
    private static final Boolean CONTEXT_ITEM_ENABLED = true;
    private static final Boolean CONTEXT_ITEM_FROM_EDITOR_ENABLED = false;
    private static final String CONTEXT_ITEM_FILE_NAME = "/path/to/context/item/file.xml";
    private static final String INNER_XML = "<tag><inner>text</inner></tag>";
    private static final String VARIABLE_VALUE = "<![CDATA[" + INNER_XML + "]]>";
    private static final Boolean VARIABLE_ACTIVE = true;
    private static final String VARIABLE_NAME = "name";
    private static final String VARIABLE_NAMESPACE = "namespace";
    private static final String VARIABLE_TYPE = "xs:string";
    private static final XQueryDataSourceType DATA_SOURCE_TYPE = XQueryDataSourceType.SAXON;
    private static final String DATA_SOURCE_TYPE_NAME = DATA_SOURCE_TYPE.toString();
    private static final String HOST = "localhost";
    private static final String PORT = "8081";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pwd";
    private static final String CONFIG_FILE = "/path/to/config/file.xml";
    private static final Boolean CONFIG_FILE_ENABLED = true;
    private static final String DATABASE_NAME = "dbName";
    private static final String CONTEXT_ITEM_TYPE = "xs:string";

    private final String xml = "<run>\n" +
            "<variables>\n" +
            "<list>\n" +
            "<variable active=\"" + VARIABLE_ACTIVE +
            "\" name=\"" + VARIABLE_NAME +
            "\" namespace=\"" + VARIABLE_NAMESPACE +
            "\" type=\"" + VARIABLE_TYPE +
            "\">" + VARIABLE_VALUE + "</variable>\n" +
            "</list>\n" +
            "</variables>\n" +
            "<xQueryConfiguration " +
            "mainFileName=\"" + MAIN_FILE_NAME + "\" " +
            "contextItemEnabled=\"" + CONTEXT_ITEM_ENABLED.toString() + "\" " +
            "contextItemFromEditorEnabled=\"" + CONTEXT_ITEM_FROM_EDITOR_ENABLED.toString() + "\"\n" +
            "contextItemFile=\"" + CONTEXT_ITEM_FILE_NAME + "\" " +
            "contextItemType=\"" + CONTEXT_ITEM_TYPE + "\">\n" +
            "<contextItemText>" + VARIABLE_VALUE + "</contextItemText> " +
            "</xQueryConfiguration>\n" +
            "<data-source-configuration " +
            "name=\"example\" " +
            "type=\"" + DATA_SOURCE_TYPE_NAME + "\" " +
            "configEnabled=\"" + CONFIG_FILE_ENABLED + "\" " +
            "configFile=\"" + CONFIG_FILE + "\" " +
            "host=\"" + HOST + "\" " +
            "port=\"" + PORT + "\" " +
            "username=\"" + USERNAME + "\" " +
            "password=\"" + PASSWORD + "\" " +
            "userDefinedLibraryEnabled=\"true\" " +
            "userDefinedLibraryPath=\"/path/to/xqj.jar\" " +
            "databaseName=\"" + DATABASE_NAME + "\" " +
            "/>\n" +
            "</run>\n";

    private XQueryRunConfig config;

    @Before
    public void setUp() throws Exception {
        config = new XQueryRunConfig(xml);
    }

    @Test
    public void shouldReturnMainFileValue() {
        String result = config.getMainFile();

        assertThat(result, is(MAIN_FILE_NAME));
    }

    @Test
    public void shouldReturnContextItemEnabledValue() {
        boolean result = config.isContextItemEnabled();

        assertThat(result, is(CONTEXT_ITEM_ENABLED));
    }

    @Test
    public void shouldReturnContextItemFromEditorEnabledValue() {
        boolean result = config.isContextItemFromEditorEnabled();

        assertThat(result, is(CONTEXT_ITEM_FROM_EDITOR_ENABLED));
    }

    @Test
    public void shouldReturnContextItemFileValue() {
        String result = config.getContextItemFile();

        assertThat(result, is(CONTEXT_ITEM_FILE_NAME));
    }

    @Test
    public void shouldReturnContextItemTextValue() {
        String result = config.getContextItemText();

        assertThat(result, is(INNER_XML));
    }

    @Test
    public void shouldReturnZeroVariables() throws Exception {
        config = new XQueryRunConfig("<run/>");
        List<XQueryRunnerVariable> result = config.getVariables();

        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldReturnVariables() {
        List<XQueryRunnerVariable> result = config.getVariables();

        assertThat(result.get(0).ACTIVE, is(VARIABLE_ACTIVE));
        assertThat(result.get(0).NAME, is(VARIABLE_NAME));
        assertThat(result.get(0).NAMESPACE, is(VARIABLE_NAMESPACE));
        assertThat(result.get(0).TYPE, is(VARIABLE_TYPE));
        assertThat(result.get(0).VALUE, is(INNER_XML));
    }

    @Test
    public void shouldReturnDataSourceType() {
        XQueryDataSourceType result = config.getDataSourceType();

        assertThat(result, is(DATA_SOURCE_TYPE));
    }

    @Test
    public void shouldReturnHostValue() {
        String result = config.getHost();

        assertThat(result, is(HOST));
    }

    @Test
    public void shouldReturnPortValue() {
        String result = config.getPort();

        assertThat(result, is(PORT));
    }

    @Test
    public void shouldReturnUsernameValue() {
        String result = config.getUsername();

        assertThat(result, is(USERNAME));
    }

    @Test
    public void shouldReturnPasswordValue() {
        String result = config.getPassword();

        assertThat(result, is(PASSWORD));
    }

    @Test
    public void shouldReturnConfigEnabledValue() {
        boolean result = config.isConfigFileEnabled();

        assertThat(result, is(CONFIG_FILE_ENABLED));
    }

    @Test
    public void shouldReturnConfigFileValue() {
        String result = config.getConfigFile();

        assertThat(result, is(CONFIG_FILE));
    }

    @Test
    public void shouldReturnDatabaseNameValue() {
        String result = config.getDatabaseName();

        assertThat(result, is(DATABASE_NAME));
    }

    @Test
    public void shouldReturnContextItemTypeValue() {
        String result = config.getContextItemType();

        assertThat(result, is(CONTEXT_ITEM_TYPE));
    }
}
