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

package org.intellij.xquery.runner.state.run;

import org.jdom.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.Writer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 08/10/13
 * Time: 22:35
 */
public class XQueryRunConfigurationSerializerTest {

    private XQueryRunConfiguration configuration;
    private XQueryRunConfigurationSerializer serializer;
    private Writer writer;
    private ElementWriter elementWriter;

    @Before
    public void setUp() throws Exception {
        configuration = mock(XQueryRunConfiguration.class);
        writer = mock(Writer.class);
        elementWriter = mock(ElementWriter.class);
        serializer = new XQueryRunConfigurationSerializer(configuration) {
            @Override
            protected ElementWriter getElementWriter() {
                return elementWriter;
            }
        };
    }

    @Test
    public void shouldWriteVariables() throws Exception {
        serializer.serialize(writer);

        verify(configuration).writeVariables(any(Element.class));
    }

    @Test
    public void shouldWriteConfiguration() throws Exception {
        serializer.serialize(writer);

        verify(configuration).writeConfiguration(any(Element.class));
    }

    @Test
    public void shouldWriteDataSourceConfiguration() throws Exception {
        serializer.serialize(writer);

        verify(configuration).writeDataSourceConfiguration(any(Element.class));
    }

    @Test
    public void shouldWriteUsingElementWriter() throws Exception {
        ArgumentCaptor<Writer> writerArgumentCaptor = ArgumentCaptor.forClass(Writer.class);

        serializer.serialize(writer);

        verify(elementWriter).writeElement(any(Element.class), writerArgumentCaptor.capture(), anyString());
        assertThat(writerArgumentCaptor.getValue(), is(writer));
    }
}
