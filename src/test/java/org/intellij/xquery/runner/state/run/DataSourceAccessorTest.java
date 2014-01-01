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

package org.intellij.xquery.runner.state.run;

import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.jdom.Element;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * User: ligasgr
 * Date: 21/11/13
 * Time: 21:59
 */
public class DataSourceAccessorTest {

    private XQueryRunConfiguration configuration;
    private Element element;
    private TestDataSourceAccessor accessor;
    private XQueryDataSourcesSettings dataSourceSettings;
    private XQueryDataSourceConfiguration ds;

    @Before
    public void setUp() throws Exception {
        element = new Element("any");
        configuration = mock(XQueryRunConfiguration.class);
        dataSourceSettings = mock(XQueryDataSourcesSettings.class);
        accessor = new TestDataSourceAccessor();
        ds = new XQueryDataSourceConfiguration();
        ds.NAME = "name";
        ds.TYPE = XQueryDataSourceType.SAXON;
        ds.CONFIG_ENABLED = true;
        ds.CONFIG_FILE = "file";
        ds.HOST = "host";
        ds.PORT = "port";
        ds.USERNAME = "user";
        ds.PASSWORD = "pass";
        ds.USER_DEFINED_LIBRARY_ENABLED = true;
        ds.DATABASE_NAME = "dbName";
        ds.DEFAULT = true;
        ds.USER_DEFINED_LIBRARY_PATHS.add("entry");
    }

    @Test
    public void shouldPersistDataSource() {
        String dataSourceName = "config";
        given(configuration.getDataSourceName()).willReturn(dataSourceName);
        given(dataSourceSettings.getDataSourceConfigurationForName(dataSourceName)).willReturn(ds);

        accessor.writeDataSourceConfiguration(configuration, element);

        Element dataSourceElement = element.getChild("data-source-configuration");
        assertThat(dataSourceElement.getAttributeValue("name"), is(ds.NAME));
        assertThat(dataSourceElement.getAttributeValue("type"), is(ds.TYPE.toString()));
        assertThat(dataSourceElement.getAttributeValue("configEnabled"), is(Boolean.toString(ds.CONFIG_ENABLED)));
        assertThat(dataSourceElement.getAttributeValue("configFile"), is(ds.CONFIG_FILE));
        assertThat(dataSourceElement.getAttributeValue("host"), is(ds.HOST));
        assertThat(dataSourceElement.getAttributeValue("port"), is(ds.PORT));
        assertThat(dataSourceElement.getAttributeValue("username"), is(ds.USERNAME));
        assertThat(dataSourceElement.getAttributeValue("password"), is(ds.PASSWORD));
        assertThat(dataSourceElement.getAttributeValue("userDefinedLibraryEnabled"), is(Boolean.toString(ds.USER_DEFINED_LIBRARY_ENABLED)));
        assertThat(dataSourceElement.getAttributeValue("databaseName"), is(ds.DATABASE_NAME));
        assertThat(dataSourceElement.getAttributeValue("default"), is(Boolean.toString(ds.DEFAULT)));
        Element pathsElement = dataSourceElement.getChild("userDefinedLibraryPaths");
        assertThat(pathsElement, is(notNullValue()));
        List paths = pathsElement.getChildren();
        assertThat(paths.size(), is(1));
        assertThat(((Element)paths.get(0)).getAttributeValue("value"), is(ds.USER_DEFINED_LIBRARY_PATHS.get(0)));
    }

    private class TestDataSourceAccessor extends DataSourceAccessor{
        @Override
        protected XQueryDataSourcesSettings getDataSourcesSettings() {
            return dataSourceSettings;
        }
    }
}
