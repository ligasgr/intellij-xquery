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
import org.mockito.ArgumentCaptor;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 21/11/13
 * Time: 21:46
 */
public class VariablesAccessorTest {

    private XQueryRunConfiguration originalConfiguration;
    private XQueryRunConfiguration readConfiguration;
    private Element element;
    private VariablesAccessor accessor;

    @Before
    public void setUp() throws Exception {
        element = new Element("any");
        originalConfiguration = mock(XQueryRunConfiguration.class);
        readConfiguration = mock(XQueryRunConfiguration.class);
        accessor = new VariablesAccessor();
    }

    @Test
    public void shouldWriteAndReadTheSameVariables() {
        XQueryRunVariables variables = new XQueryRunVariables(asList(new XQueryRunVariable("my", "ns", "xs:int",
                "val", false)));
        given(originalConfiguration.getVariables()).willReturn(variables);

        accessor.writeVariables(originalConfiguration, element);
        accessor.readVariables(element, readConfiguration);

        ArgumentCaptor<XQueryRunVariables> variablesCaptor = ArgumentCaptor.forClass(XQueryRunVariables.class);
        verify(readConfiguration).setVariables(variablesCaptor.capture());
        assertThat(variablesCaptor.getValue(), is(variables));
    }
}
