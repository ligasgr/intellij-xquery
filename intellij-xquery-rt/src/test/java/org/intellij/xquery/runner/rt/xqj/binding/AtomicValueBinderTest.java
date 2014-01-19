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

package org.intellij.xquery.runner.rt.xqj.binding;

import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 05/11/13
 * Time: 15:18
 */
public class AtomicValueBinderTest {

    private static final String VALUE = "value";
    private static final String TYPE = "xs:string";
    private XQPreparedExpression expression;
    private XQConnection connection;
    private AtomicValueBinder binder;
    private final QName qName = new QName("local");
    private XQItemType xqItemType;

    @Before
    public void setUp() throws XQException {
        expression = mock(XQPreparedExpression.class);
        connection = mock(XQConnection.class);
        binder = new AtomicValueBinder();
        xqItemType = mock(XQItemType.class);
        given(connection.createAtomicType(anyInt())).willReturn(xqItemType);
    }

    @Test
    public void shouldCreateAtomicTypeUsingConnection() throws Exception {
        binder.bind(expression, connection, qName, VALUE, TYPE);

        verify(connection).createAtomicType(anyInt());
    }

    @Test
    public void shouldBindAtomicValue() throws Exception {
        binder.bind(expression, connection, qName, VALUE, TYPE);

        verify(expression).bindAtomicValue(qName, VALUE, xqItemType);
    }
}
