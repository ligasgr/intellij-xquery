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

import org.intellij.xquery.runner.rt.XQueryContentFactory;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.xqj.binding.ContextItemBinder;
import org.intellij.xquery.runner.rt.xqj.binding.VariablesBinder;
import org.junit.Before;
import org.junit.Test;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import java.io.InputStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 18:30
 */
public class ExpressionFactoryTest {
    private ExpressionFactory factory;
    private XQueryRunConfig config;
    private VariablesBinder variablesBinder;
    private ContextItemBinder contextItemBinder;
    private XQueryContentFactory contentFactory;
    private XQConnection connection;
    private XQPreparedExpression expression;

    @Before
    public void setUp() throws XQException {
        config = mock(XQueryRunConfig.class);
        variablesBinder = mock(VariablesBinder.class);
        contextItemBinder = mock(ContextItemBinder.class);
        contentFactory = mock(XQueryContentFactory.class);
        connection = mock(XQConnection.class);
        expression = mock(XQPreparedExpression.class);
        given(connection.prepareExpression(any(InputStream.class))).willReturn(expression);
        factory = new ExpressionFactory(config, contentFactory, variablesBinder, contextItemBinder);
    }

    @Test
    public void shouldPrepareExpressionUsingConnection() throws Exception {
        InputStream inputStream = mock(InputStream.class);
        given(contentFactory.getXQueryContentAsStream()).willReturn(inputStream);

        factory.getExpression(connection);

        verify(contentFactory).getXQueryContentAsStream();
        verify(connection).prepareExpression(inputStream);
    }

    @Test
    public void shouldBindContextItem() throws Exception {
        factory.getExpression(connection);

        verify(contextItemBinder).bindContextItem(connection, expression);
    }

    @Test
    public void shouldBindVariables() throws Exception {
        factory.getExpression(connection);

        verify(variablesBinder).bindVariables(connection, expression);
    }
}
