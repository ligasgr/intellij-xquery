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

import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.xqj.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;

import javax.xml.xquery.XQDataSource;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 15:20
 */
public class ConnectionFactoryTest {

    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private ConnectionFactory factory;
    private XQueryRunConfig config;
    private XQDataSource dataSource;

    @Before
    public void setUp() {
        dataSource = mock(XQDataSource.class);
        config = mock(XQueryRunConfig.class);
        factory = new ConnectionFactory(config);
    }

    @Test
    public void shouldInvokeGetConnectionWithoutParametersWhenConnectionPropertiesNotSupported() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.SAXON);

        factory.getConnection(dataSource);

        verify(dataSource).getConnection();
    }

    @Test
    public void shouldInvokeGetConnectionWithParametersWhenConnectionPropertiesAreSupportedAndSet() throws Exception {
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.MARKLOGIC);
        given(config.getUsername()).willReturn(USER);
        given(config.getPassword()).willReturn(PASSWORD);

        factory.getConnection(dataSource);

        verify(dataSource).getConnection(USER, PASSWORD);
    }

    @Test
    public void shouldInvokeGetConnectionWithoutParametersWhenConnectionPropertiesAreSupportedAndUsernameNotSet()
            throws Exception {
        given(config.getUsername()).willReturn(null);
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.MARKLOGIC);

        factory.getConnection(dataSource);

        verify(dataSource).getConnection();
    }

    @Test
    public void shouldInvokeGetConnectionWithoutParametersWhenConnectionPropertiesAreSupportedAndUsernameSetEmpty()
            throws Exception {
        given(config.getUsername()).willReturn("");
        given(config.getDataSourceType()).willReturn(XQueryDataSourceType.MARKLOGIC);

        factory.getConnection(dataSource);

        verify(dataSource).getConnection();
    }
}
