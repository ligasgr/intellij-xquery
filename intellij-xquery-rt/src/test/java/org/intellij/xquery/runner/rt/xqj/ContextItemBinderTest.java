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

import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.xqj.binding.ContextItemBinder;
import org.intellij.xquery.runner.rt.xqj.binding.TypeBinder;
import org.intellij.xquery.runner.rt.xqj.binding.TypeBinderFactory;
import org.junit.Before;
import org.junit.Test;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQPreparedExpression;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 21:39
 */
public class ContextItemBinderTest {

    private static final String FILE_CONTENTS = "example contents";
    private static final String CONTEXT_ITEM_TYPE = "xs:string";
    private static final String CONTEXT_ITEM_TEXT = "text";
    private ContextItemBinder binder;
    private XQueryRunConfig config;
    private TypeBinderFactory binderFactory;
    private XQConnection connection;
    private XQPreparedExpression expression;
    private boolean fileRead;
    private TypeBinder typeBinder;

    @Before
    public void setUp() throws Exception {
        config = mock(XQueryRunConfig.class);
        binderFactory = mock(TypeBinderFactory.class);
        connection = mock(XQConnection.class);
        expression = mock(XQPreparedExpression.class);
        binder = new ContextItemBinder(binderFactory, config) {
            @Override
            protected String readFile(String fileName) throws IOException {
                fileRead = true;
                return FILE_CONTENTS;
            }
        };
        typeBinder = mock(TypeBinder.class);
        given(binderFactory.getBinder(CONTEXT_ITEM_TYPE)).willReturn(typeBinder);
    }

    @Test
    public void shouldDoNothingIfContextItemIsDisabled() throws Exception {
        given(config.isContextItemEnabled()).willReturn(false);

        binder.bindContextItem(connection, expression);

        verify(config).isContextItemEnabled();
        verifyNoMoreInteractions(config, binderFactory, connection, expression);
    }

    @Test
    public void shouldGetContentsFromEditorIfContentsFromEditorEnabled() throws Exception {
        given(config.isContextItemEnabled()).willReturn(true);
        given(config.isContextItemFromEditorEnabled()).willReturn(true);
        given(config.getContextItemText()).willReturn(CONTEXT_ITEM_TEXT);
        given(config.getContextItemType()).willReturn(CONTEXT_ITEM_TYPE);

        binder.bindContextItem(connection, expression);

        assertThat(fileRead, is(false));
        verify(config).isContextItemEnabled();
        verify(config).isContextItemFromEditorEnabled();
        verify(config).getContextItemText();
        verify(binderFactory).getBinder(CONTEXT_ITEM_TYPE);
        verify(typeBinder).bind(expression, connection, XQConstants.CONTEXT_ITEM, CONTEXT_ITEM_TEXT, CONTEXT_ITEM_TYPE);
    }

    @Test
    public void shouldGetContentsFromFileIfContentsFromEditorDisabled() throws Exception {
        given(config.isContextItemEnabled()).willReturn(true);
        given(config.isContextItemFromEditorEnabled()).willReturn(false);
        given(config.getContextItemType()).willReturn(CONTEXT_ITEM_TYPE);

        binder.bindContextItem(connection, expression);

        assertThat(fileRead, is(true));
        verify(config).isContextItemEnabled();
        verify(config).isContextItemFromEditorEnabled();
        verify(config, times(0)).getContextItemText();
        verify(binderFactory).getBinder(CONTEXT_ITEM_TYPE);
        verify(typeBinder).bind(expression, connection, XQConstants.CONTEXT_ITEM, FILE_CONTENTS, CONTEXT_ITEM_TYPE);
    }
}
