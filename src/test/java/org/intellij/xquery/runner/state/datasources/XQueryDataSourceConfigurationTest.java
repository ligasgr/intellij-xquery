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

package org.intellij.xquery.runner.state.datasources;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.MARKLOGIC;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.SAXON;
import static org.intellij.xquery.util.XmlUtils.*;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 06/10/13
 * Time: 19:32
 */
public class XQueryDataSourceConfigurationTest {

    private static final String CONFIG_PATH = "/my/path/to/config/file.xml";
    private static final String LIBRARY_PATH = "/path/to/user/library.jar";
    private static final String XML_TEMPLATE = "<data-source-configuration %s='%s'/>";
    private static final String NAME_FIELD = "name";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String PORT = "1234";
    private static final String NAME = "name";
    private static final String HOST = "localhost";
    private static final String DATABASE = "testdb";
    private static final String TYPE_FIELD = "type";
    private static final String HOST_FIELD = "host";
    private static final String CONFIG_ENABLED_FIELD = "configEnabled";
    private static final String CONFIG_FILE_FIELD = "configFile";
    private static final String PORT_FIELD = "port";
    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    private static final String USER_DEFINED_LIBRARY_ENABLED = "userDefinedLibraryEnabled";
    private static final String USER_DEFINED_LIBRARY_PATH = "userDefinedLibraryPath";
    private static final String USER_DEFINED_LIBRARY_PATHS = "userDefinedLibraryPaths";
    private static final String DATABASE_NAME_FIELD = "databaseName";
    private static final String CONFIG_ATTRIBUTE_XPATH = "/data-source-configuration/@";
    private static final String CONFIG_ELEMENT_XPATH = "/data-source-configuration/";
    private static final String DEFAULT_FIELD = "default";
    private static final String VALUE = "1";
    private static final String DIFFERENT_VALUE = "2";

    private Class<XQueryDataSourceConfiguration> dataSourceClass = XQueryDataSourceConfiguration.class;
    private XQueryDataSourceConfiguration dataSourceConfiguration;
    private XQueryDataSourceConfiguration dataSourceConfiguration1;

    @Before
    public void setUp() throws Exception {
        dataSourceConfiguration = new XQueryDataSourceConfiguration();
        dataSourceConfiguration1 = new XQueryDataSourceConfiguration();
    }

    @Test
    public void shouldPersistName() throws Exception {
        dataSourceConfiguration.NAME = NAME;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + NAME_FIELD, equalTo(NAME)));
    }

    @Test
    public void shouldPersistType() throws Exception {
        dataSourceConfiguration.TYPE = SAXON;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + TYPE_FIELD, equalTo(SAXON.toString())));
    }

    @Test
    public void shouldPersistConfigurationFileActivity() throws Exception {
        dataSourceConfiguration.CONFIG_ENABLED = true;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + CONFIG_ENABLED_FIELD, equalTo(TRUE.toString())));
    }

    @Test
    public void shouldPersistConfigurationFilePath() throws Exception {
        dataSourceConfiguration.CONFIG_FILE = CONFIG_PATH;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + CONFIG_FILE_FIELD, equalTo(CONFIG_PATH)));
    }

    @Test
    public void shouldPersistHost() throws Exception {
        dataSourceConfiguration.HOST = HOST;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + HOST_FIELD, equalTo(HOST)));
    }

    @Test
    public void shouldPersistPort() throws Exception {
        dataSourceConfiguration.PORT = PORT;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + PORT_FIELD, equalTo(PORT)));
    }

    @Test
    public void shouldPersistUsername() throws Exception {
        dataSourceConfiguration.USERNAME = USER;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + USERNAME_FIELD, equalTo(USER)));
    }

    @Test
    public void shouldPersistPassword() throws Exception {
        dataSourceConfiguration.PASSWORD = PASSWORD;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + PASSWORD_FIELD, equalTo(PASSWORD)));
    }

    @Test
    public void shouldPersistUserDefinedLibraryActivity() throws Exception {
        dataSourceConfiguration.USER_DEFINED_LIBRARY_ENABLED = true;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + USER_DEFINED_LIBRARY_ENABLED, equalTo(TRUE.toString())));
    }

    @Test
    public void shouldPersistUserDefinedLibraryPaths() throws Exception {
        dataSourceConfiguration.USER_DEFINED_LIBRARY_PATHS.add(LIBRARY_PATH);
        dataSourceConfiguration.USER_DEFINED_LIBRARY_PATHS.add(LIBRARY_PATH);

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ELEMENT_XPATH + USER_DEFINED_LIBRARY_PATHS + "/" +
                USER_DEFINED_LIBRARY_PATH + "[1]/@value", equalTo(LIBRARY_PATH)));
        assertThat(the(xml), hasXPath(CONFIG_ELEMENT_XPATH + USER_DEFINED_LIBRARY_PATHS + "/" +
                USER_DEFINED_LIBRARY_PATH + "[2]/@value", equalTo(LIBRARY_PATH)));
    }

    @Test
    public void shouldPersistDatabaseName() throws Exception {
        dataSourceConfiguration.DATABASE_NAME = DATABASE;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + DATABASE_NAME_FIELD, equalTo(DATABASE)));
    }

    @Test
    public void shouldPersistIfIsDefault() throws Exception {
        dataSourceConfiguration.DEFAULT = true;

        String xml = serializeToXml(dataSourceConfiguration);

        assertThat(the(xml), hasXPath(CONFIG_ATTRIBUTE_XPATH + DEFAULT_FIELD, equalTo(TRUE.toString())));
    }

    @Test
    public void shouldReadName() throws Exception {
        String xml = format(XML_TEMPLATE, NAME_FIELD, NAME);

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.NAME, is(equalTo(NAME)));
    }

    @Test
    public void shouldReadType() throws Exception {
        String xml = format(XML_TEMPLATE, TYPE_FIELD, MARKLOGIC.toString());

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.TYPE, is(equalTo(MARKLOGIC)));
    }

    @Test
    public void shouldReadConfigurationFileActivity() throws Exception {
        String xml = format(XML_TEMPLATE, CONFIG_ENABLED_FIELD, TRUE.toString());

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.CONFIG_ENABLED, is(equalTo(TRUE)));
    }

    @Test
    public void shouldReadConfigurationFilePath() throws Exception {
        String xml = format(XML_TEMPLATE, CONFIG_FILE_FIELD, CONFIG_PATH);

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.CONFIG_FILE, is(equalTo(CONFIG_PATH)));
    }

    @Test
    public void shouldReadHost() throws Exception {
        String xml = format(XML_TEMPLATE, HOST_FIELD, HOST);

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.HOST, is(equalTo(HOST)));
    }

    @Test
    public void shouldReadPort() throws Exception {
        String xml = format(XML_TEMPLATE, PORT_FIELD, PORT);

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.PORT, is(equalTo(PORT)));
    }

    @Test
    public void shouldReadUsername() throws Exception {
        String xml = format(XML_TEMPLATE, USERNAME_FIELD, USER);

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.USERNAME, is(equalTo(USER)));
    }

    @Test
    public void shouldReadPassword() throws Exception {
        String xml = format(XML_TEMPLATE, PASSWORD_FIELD, PASSWORD);

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.PASSWORD, is(equalTo(PASSWORD)));
    }

    @Test
    public void shouldReadUserDefinedLibraryActivity() throws Exception {
        String xml = format(XML_TEMPLATE, USER_DEFINED_LIBRARY_ENABLED, TRUE.toString());

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.USER_DEFINED_LIBRARY_ENABLED, is(equalTo(TRUE)));
    }

    @Test
    public void shouldReadUserDefinedLibraryPaths() throws Exception {
        String xml = "<data-source-configuration>\n" +
                "  <" + USER_DEFINED_LIBRARY_PATHS + ">\n" +
                "    <" + USER_DEFINED_LIBRARY_PATH + " value=\"" + LIBRARY_PATH + "\" />\n" +
                "    <" + USER_DEFINED_LIBRARY_PATH + " value=\"" + LIBRARY_PATH + "\" />\n" +
                "  </" + USER_DEFINED_LIBRARY_PATHS + ">\n" +
                "</data-source-configuration>\n";

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.USER_DEFINED_LIBRARY_PATHS.get(0), is(equalTo(LIBRARY_PATH)));
        assertThat(dataSourceConfiguration.USER_DEFINED_LIBRARY_PATHS.get(1), is(equalTo(LIBRARY_PATH)));
    }

    @Test
    public void shouldReadDatabaseName() throws Exception {
        String xml = format(XML_TEMPLATE, DATABASE_NAME_FIELD, DATABASE);

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.DATABASE_NAME, is(equalTo(DATABASE)));
    }

    @Test
    public void shouldReadIfIsDefault() throws Exception {
        String xml = format(XML_TEMPLATE, DEFAULT_FIELD, TRUE.toString());

        dataSourceConfiguration = deserializeFromXml(rootElement(xml), dataSourceClass);

        assertThat(dataSourceConfiguration.DEFAULT, is(equalTo(TRUE)));
    }

    @Test
    public void shouldBeEqualWhenItIsTheSameObject() throws Exception {
        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration)));
    }

    @Test
    public void shouldNotBeEqualWhenObjectNull() throws Exception {
        assertThat(dataSourceConfiguration, is(not(equalTo(null))));
    }

    @Test
    public void shouldNotBeEqualWhenObjectOfDifferentClass() throws Exception {
        assertThat(dataSourceConfiguration, is(not(equalTo(new Object()))));
    }

    @Test
    public void shouldNotBeEqualWhenNameIsDifferent() throws Exception {
        dataSourceConfiguration.NAME = VALUE;
        dataSourceConfiguration1.NAME = DIFFERENT_VALUE;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenNameIsTheSame() throws Exception {
        dataSourceConfiguration.NAME = VALUE;
        dataSourceConfiguration1.NAME = VALUE;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenTypeIsDifferent() throws Exception {
        dataSourceConfiguration.TYPE = SAXON;
        dataSourceConfiguration1.TYPE = MARKLOGIC;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenTypeIsTheSame() throws Exception {
        dataSourceConfiguration.TYPE = SAXON;
        dataSourceConfiguration1.TYPE = SAXON;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenConfigFileActivityIsDifferent() throws Exception {
        dataSourceConfiguration.CONFIG_ENABLED = true;
        dataSourceConfiguration1.CONFIG_ENABLED = false;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenConfigFileActivityIsTheSame() throws Exception {
        dataSourceConfiguration.CONFIG_ENABLED = true;
        dataSourceConfiguration1.CONFIG_ENABLED = true;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenConfigFileIsDifferent() throws Exception {
        dataSourceConfiguration.CONFIG_FILE = VALUE;
        dataSourceConfiguration1.CONFIG_FILE = DIFFERENT_VALUE;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenConfigFileIsTheSame() throws Exception {
        dataSourceConfiguration.CONFIG_FILE = VALUE;
        dataSourceConfiguration1.CONFIG_FILE = VALUE;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenHostIsDifferent() throws Exception {
        dataSourceConfiguration.HOST = VALUE;
        dataSourceConfiguration1.HOST = DIFFERENT_VALUE;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenHostIsTheSame() throws Exception {
        dataSourceConfiguration.HOST = VALUE;
        dataSourceConfiguration1.HOST = VALUE;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenPortIsDifferent() throws Exception {
        dataSourceConfiguration.PORT = VALUE;
        dataSourceConfiguration1.PORT = DIFFERENT_VALUE;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenPortIsTheSame() throws Exception {
        dataSourceConfiguration.PORT = VALUE;
        dataSourceConfiguration1.PORT = VALUE;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenUsernameIsDifferent() throws Exception {
        dataSourceConfiguration.USERNAME = VALUE;
        dataSourceConfiguration1.USERNAME = DIFFERENT_VALUE;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenUsernameIsTheSame() throws Exception {
        dataSourceConfiguration.USERNAME = VALUE;
        dataSourceConfiguration1.USERNAME = VALUE;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenPasswordIsDifferent() throws Exception {
        dataSourceConfiguration.PASSWORD = VALUE;
        dataSourceConfiguration1.PASSWORD = DIFFERENT_VALUE;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenPasswordIsTheSame() throws Exception {
        dataSourceConfiguration.PASSWORD = VALUE;
        dataSourceConfiguration1.PASSWORD = VALUE;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenUserDefinedLibraryActivityIsDifferent() throws Exception {
        dataSourceConfiguration.USER_DEFINED_LIBRARY_ENABLED = true;
        dataSourceConfiguration1.USER_DEFINED_LIBRARY_ENABLED = false;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenUserDefinedLibraryActivityIsTheSame() throws Exception {
        dataSourceConfiguration.USER_DEFINED_LIBRARY_ENABLED = true;
        dataSourceConfiguration1.USER_DEFINED_LIBRARY_ENABLED = true;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenUserDefinedLibraryPathsAreDifferent() throws Exception {
        dataSourceConfiguration.USER_DEFINED_LIBRARY_PATHS = asList(VALUE);
        dataSourceConfiguration1.USER_DEFINED_LIBRARY_PATHS = asList(DIFFERENT_VALUE);

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenUserDefinedLibraryPathsAreTheSame() throws Exception {
        dataSourceConfiguration.USER_DEFINED_LIBRARY_PATHS = asList(VALUE);
        dataSourceConfiguration1.USER_DEFINED_LIBRARY_PATHS = asList(VALUE);

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldNotBeEqualWhenDefaultIsDifferent() throws Exception {
        dataSourceConfiguration.DEFAULT = true;
        dataSourceConfiguration1.DEFAULT = false;

        assertThat(dataSourceConfiguration, is(not(equalTo(dataSourceConfiguration1))));
    }

    @Test
    public void shouldBeEqualWhenDefaultIsTheSame() throws Exception {
        dataSourceConfiguration.DEFAULT = true;
        dataSourceConfiguration1.DEFAULT = true;

        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration1)));
    }

    @Test
    public void shouldCreateCloneWhichIsEqual() throws Exception {
        assertThat(dataSourceConfiguration, is(equalTo(dataSourceConfiguration.clone())));
    }
}
