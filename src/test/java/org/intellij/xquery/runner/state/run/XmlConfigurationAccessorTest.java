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

import org.jdom.Element;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 21/11/13
 * Time: 19:05
 */
public class XmlConfigurationAccessorTest {

    private static final String STRING_VALUE = "value";
    private static final boolean BOOLEAN_VALUE = true;
    private XQueryRunConfiguration originalConfiguration;
    private XQueryRunConfiguration readConfiguration;
    private Element element;
    private XmlConfigurationAccessor accessor;

    @Before
    public void setUp() throws Exception {
        element = new Element("any");
        originalConfiguration = mock(XQueryRunConfiguration.class);
        readConfiguration = mock(XQueryRunConfiguration.class);
        accessor = new XmlConfigurationAccessor();
    }

    @Test
    public void shouldWriteAndReadMainFileName() {
        given(originalConfiguration.getMainFileName()).willReturn(STRING_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setMainFileName(STRING_VALUE);
    }

    @Test
    public void shouldWriteAndReadVmParameters() {
        given(originalConfiguration.getVMParameters()).willReturn(STRING_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setVMParameters(STRING_VALUE);
    }

    @Test
    public void shouldWriteAndReadProgramParameters() {
        given(originalConfiguration.getProgramParameters()).willReturn(STRING_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setProgramParameters(STRING_VALUE);
    }

    @Test
    public void shouldWriteAndReadRawWorkingDirectory() {
        given(originalConfiguration.getRawWorkingDirectory()).willReturn(STRING_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setRawWorkingDirectory(STRING_VALUE);
    }

    @Test
    public void shouldWriteAndReadAlternativeJrePathEnabled() {
        given(originalConfiguration.isAlternativeJrePathEnabled()).willReturn(BOOLEAN_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setAlternativeJrePathEnabled(BOOLEAN_VALUE);
    }

    @Test
    public void shouldWriteAndReadAlternativeJrePath() {
        given(originalConfiguration.getAlternativeJrePath()).willReturn(STRING_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setAlternativeJrePath(STRING_VALUE);
    }

    @Test
    public void shouldWriteAndReadContextItemEnabled() {
        given(originalConfiguration.isContextItemEnabled()).willReturn(BOOLEAN_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setContextItemEnabled(BOOLEAN_VALUE);
    }

    @Test
    public void shouldWriteAndReadContextItemFromEditorEnabled() {
        given(originalConfiguration.isContextItemFromEditorEnabled()).willReturn(BOOLEAN_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setContextItemFromEditorEnabled(BOOLEAN_VALUE);
    }

    @Test
    public void shouldWriteAndReadContextItemText() {
        given(originalConfiguration.getContextItemText()).willReturn(STRING_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setContextItemText(STRING_VALUE);
    }

    @Test
    public void shouldWriteAndReadContextItemFile() {
        given(originalConfiguration.getContextItemFile()).willReturn(STRING_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setContextItemFile(STRING_VALUE);
    }

    @Test
    public void shouldWriteAndReadContextItemType() {
        given(originalConfiguration.getContextItemType()).willReturn(STRING_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setContextItemType(STRING_VALUE);
    }

    @Test
    public void shouldWriteAndReadDataSourceName() {
        given(originalConfiguration.getDataSourceName()).willReturn(STRING_VALUE);

        writeAndReadConfiguration();

        verify(readConfiguration).setDataSourceName(STRING_VALUE);
    }

    private void writeAndReadConfiguration() {
        accessor.writeConfiguration(originalConfiguration, element);
        accessor.readConfiguration(element, readConfiguration);
    }
}
