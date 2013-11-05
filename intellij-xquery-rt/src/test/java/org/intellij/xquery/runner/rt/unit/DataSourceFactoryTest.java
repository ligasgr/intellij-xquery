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

package org.intellij.xquery.runner.rt.unit;

import org.intellij.xquery.runner.rt.DataSourceFactory;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.datasource.XQDataSourceFactory;
import org.junit.Before;
import org.junit.Test;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.intellij.xquery.runner.rt.DataSourceFactory.DATABASE_NAME;
import static org.intellij.xquery.runner.rt.DataSourceFactory.PORT;
import static org.intellij.xquery.runner.rt.DataSourceFactory.SERVER_NAME;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 15:41
 */
public class DataSourceFactoryTest {

    private static final String HOST = "host";
    private static final String PORT_VALUE = "123";
    private static final String DB_NAME = "database";
    private DataSourceFactory factory;
    private XQueryRunConfig config;
    private XQDataSource dataSource;

    @Before
    public void setUp() {
        config = mock(XQueryRunConfig.class);
        dataSource = mock(XQDataSource.class);
        factory = new DataSourceFactory(config) {
            @Override
            public XQDataSource getXQDataSource(XQueryDataSourceType dataSourceType,
                                                XQueryRunConfig config) throws Exception {
                return dataSource;
            }
        };
    }

    @Test
    public void shouldGetDataSourceWithoutSettingProperties() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SAXON);

        factory.getDataSource();

        verifyNoMoreInteractions(dataSource);
    }

    @Test
    public void shouldSetServerNameOnDataSourceWhenConnectionPropertiesSupported() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SEDNA);
        given(config.getHost()).willReturn(HOST);

        factory.getDataSource();

        verifyHostSet();
    }

    @Test
    public void shouldSetPortOnDataSourceWhenConnectionPropertiesSupportedAndPortNotEmpty() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SEDNA);
        given(config.getHost()).willReturn(HOST);
        given(config.getPort()).willReturn(PORT_VALUE);

        factory.getDataSource();

        verifyHostSet();
        verify(dataSource).setProperty(PORT, PORT_VALUE);
    }

    @Test
    public void shouldNotSetPortOnDataSourceWhenConnectionPropertiesSupportedAndPortEmpty() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SEDNA);
        given(config.getHost()).willReturn(HOST);
        given(config.getPort()).willReturn("");

        factory.getDataSource();

        verifyHostSet();
        verifyNoMoreInteractions(dataSource);
    }

    @Test
    public void shouldNotSetPortOnDataSourceWhenConnectionPropertiesSupportedAndPortNotSet() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SEDNA);
        given(config.getHost()).willReturn(HOST);
        given(config.getPort()).willReturn(null);

        factory.getDataSource();

        verifyHostSet();
        verifyNoMoreInteractions(dataSource);
    }

    @Test
    public void shouldSetDbNameOnDataSourceWhenConnectionPropertiesSupportedAndDbNameNotEmpty() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SEDNA);
        given(config.getHost()).willReturn(HOST);
        given(config.getDatabaseName()).willReturn(DB_NAME);

        factory.getDataSource();

        verifyHostSet();
        verify(dataSource).setProperty(DATABASE_NAME, DB_NAME);
    }

    @Test
    public void shouldNotSetDbNameOnDataSourceWhenConnectionPropertiesSupportedAndDbNameEmpty() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SEDNA);
        given(config.getHost()).willReturn(HOST);
        given(config.getDatabaseName()).willReturn("");

        factory.getDataSource();

        verifyHostSet();
        verifyNoMoreInteractions(dataSource);
    }

    @Test
    public void shouldNotSetDbNameOnDataSourceWhenConnectionPropertiesSupportedAndDbNameNotSet() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SEDNA);
        given(config.getHost()).willReturn(HOST);
        given(config.getDatabaseName()).willReturn(null);

        factory.getDataSource();

        verifyHostSet();
        verifyNoMoreInteractions(dataSource);
    }

    @Test
    public void shouldGetDataSourceBasedOnDataSourceType() throws Exception {
        factory = new DataSourceFactory(config);
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SAXON);

        XQDataSource result = factory.getDataSource();

        assertThat(result, is(not(nullValue())));
    }

    private void verifyHostSet() throws XQException {
        verify(dataSource).setProperty(SERVER_NAME, HOST);
    }
}
