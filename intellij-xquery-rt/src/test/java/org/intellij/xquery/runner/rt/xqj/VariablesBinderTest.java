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

import org.intellij.xquery.runner.rt.NameExtractor;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerVariable;
import org.intellij.xquery.runner.rt.xqj.binding.TypeBinder;
import org.intellij.xquery.runner.rt.xqj.binding.TypeBinderFactory;
import org.intellij.xquery.runner.rt.xqj.binding.VariablesBinder;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQPreparedExpression;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 22:35
 */
public class VariablesBinderTest {

    private static final String TYPE = "type";
    private static final String NAME = "name";
    private static final String NAMESPACE = "namespace";
    private static final String VALUE = "value";
    private VariablesBinder binder;
    private TypeBinderFactory binderFactory;
    private XQueryRunConfig config;
    private NameExtractor extractor;
    private XQConnection connection;
    private XQPreparedExpression expression;
    private TypeBinder typeBinder;
    private XQueryRunnerVariable variable;

    @Before
    public void setUp() throws Exception {
        binderFactory = mock(TypeBinderFactory.class);
        config = mock(XQueryRunConfig.class);
        extractor = mock(NameExtractor.class);
        connection = mock(XQConnection.class);
        expression = mock(XQPreparedExpression.class);
        typeBinder = mock(TypeBinder.class);
        binder = new VariablesBinder(binderFactory, config, extractor);
        variable = prepareVariable();
    }

    @Test
    public void shouldDoNothingIfNoVariables() throws Exception {
        given(config.getVariables()).willReturn(Collections.<XQueryRunnerVariable>emptyList());

        binder.bindVariables(connection, expression);

        verify(config).getVariables();
        verifyNoMoreInteractions(binderFactory, config, extractor, connection, expression);
    }

    @Test
    public void shouldGetBinderBasedOnVariableType() throws Exception {
        given(config.getVariables()).willReturn(asList(variable));
        given(binderFactory.getBinder(TYPE)).willReturn(typeBinder);

        binder.bindVariables(connection, expression);

        verify(binderFactory).getBinder(TYPE);
    }

    @Test
    public void shouldExtractQNameFromVariableNameAndNamespace() throws Exception {
        given(config.getVariables()).willReturn(asList(variable));
        given(binderFactory.getBinder(TYPE)).willReturn(typeBinder);

        binder.bindVariables(connection, expression);

        verify(extractor).getName(NAME, NAMESPACE);
    }

    @Test
    public void shouldBindWithTypeBinder() throws Exception {
        QName qName = new QName(variable.NAMESPACE, variable.NAME);
        given(config.getVariables()).willReturn(asList(variable));
        given(binderFactory.getBinder(TYPE)).willReturn(typeBinder);
        given(extractor.getName(NAME, NAMESPACE)).willReturn(qName);

        binder.bindVariables(connection, expression);

        verify(typeBinder).bind(expression, connection, qName, VALUE, TYPE);
    }

    @Test
    public void shouldDoNothingIfVariableNotActive() throws Exception {
        QName qName = new QName(variable.NAMESPACE, variable.NAME);
        given(config.getVariables()).willReturn(asList(variable));
        given(binderFactory.getBinder(TYPE)).willReturn(typeBinder);
        given(extractor.getName(NAME, NAMESPACE)).willReturn(qName);
        variable.ACTIVE = false;

        binder.bindVariables(connection, expression);

        verify(config).getVariables();
        verifyNoMoreInteractions(binderFactory, config, extractor, connection, expression);
    }

    private XQueryRunnerVariable prepareVariable() {
        XQueryRunnerVariable variable = new XQueryRunnerVariable();
        variable.NAME = NAME;
        variable.NAMESPACE = NAMESPACE;
        variable.TYPE = TYPE;
        variable.VALUE = VALUE;
        variable.ACTIVE = true;
        return variable;
    }
}
