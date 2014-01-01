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

import org.junit.Before;
import org.junit.Test;

import static com.intellij.openapi.util.text.StringUtil.escapeXml;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static org.hamcrest.Matchers.*;
import static org.intellij.xquery.util.XmlUtils.*;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 27/09/13
 * Time: 13:49
 */
public class XQueryRunVariableTest {

    private static final String XML_TEMPLATE = "<variable %s='%s'/>";
    private static final String XML_TEMPLATE_WITH_TEXT = "<variable>%s</variable>";
    private static final String MY_EXAMPLE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<my><example>text</example></my>";
    private static final String NAME = "name";
    private static final String NAMESPACE = "namespace";
    private static final String TYPE = "type";
    private static final String VALUE = "1";
    private static final String DIFFERENT_VALUE = "2";
    private Class<XQueryRunVariable> variableClass = XQueryRunVariable.class;
    private XQueryRunVariable variable;
    private XQueryRunVariable variable2;

    @Before
    public void setUp() throws Exception {
        variable = new XQueryRunVariable();
        variable2 = new XQueryRunVariable();
    }

    @Test
    public void shouldPersistVariableName() throws Exception {
        variable.setName(NAME);

        String xml = serializeToXml(variable);

        assertThat(the(xml), hasXPath("/variable/@name", equalTo(NAME)));
    }

    @Test
    public void shouldPersistVariableNamespace() throws Exception {
        variable.setNamespace(NAMESPACE);

        String xml = serializeToXml(variable);

        assertThat(the(xml), hasXPath("/variable/@namespace", equalTo(NAMESPACE)));
    }

    @Test
    public void shouldPersistVariableType() throws Exception {
        variable.setType(TYPE);

        String xml = serializeToXml(variable);

        assertThat(the(xml), hasXPath("/variable/@type", equalTo(TYPE)));
    }

    @Test
    public void shouldPersistVariableValue() throws Exception {
        variable.setValue(MY_EXAMPLE_XML);

        String xml = serializeToXml(variable);

        assertThat(the(xml), hasXPath("/variable/text()", equalTo(MY_EXAMPLE_XML)));
    }

    @Test
    public void shouldPersistVariableActivity() throws Exception {
        variable.setActive(true);

        String xml = serializeToXml(variable);

        assertThat(the(xml), hasXPath("/variable/@active", equalTo(TRUE.toString())));
    }

    @Test
    public void shouldReadVariableName() throws Exception {
        String xml = format(XML_TEMPLATE, NAME, NAME);

        variable = deserializeFromXml(rootElement(xml), variableClass);

        assertThat(variable.getName(), is(equalTo(NAME)));
    }

    @Test
    public void shouldReadVariableNamespace() throws Exception {
        String xml = format(XML_TEMPLATE, NAMESPACE, NAMESPACE);

        variable = deserializeFromXml(rootElement(xml), variableClass);

        assertThat(variable.getNamespace(), is(equalTo(NAMESPACE)));
    }

    @Test
    public void shouldReadVariableType() throws Exception {
        String xml = format(XML_TEMPLATE, TYPE, TYPE);

        variable = deserializeFromXml(rootElement(xml), variableClass);

        assertThat(variable.getType(), is(equalTo(TYPE)));
    }

    @Test
    public void shouldReadVariableValue() throws Exception {
        String xml = format(XML_TEMPLATE_WITH_TEXT, escapeXml(MY_EXAMPLE_XML));

        variable = deserializeFromXml(rootElement(xml), variableClass);

        assertThat(variable.getValue(), is(equalTo(MY_EXAMPLE_XML)));
    }

    @Test
    public void shouldReadVariableActivity() throws Exception {
        String xml = format(XML_TEMPLATE, "active", FALSE.toString());

        variable = deserializeFromXml(rootElement(xml), variableClass);

        assertThat(variable.isActive(), is(equalTo(false)));
    }

    @Test
    public void shouldBeEqualWhenItIsTheSameObject() throws Exception {
        assertThat(variable, is(equalTo(variable)));
    }

    @Test
    public void shouldNotBeEqualWhenObjectNull() throws Exception {
        assertThat(variable, is(not(equalTo(null))));
    }

    @Test
    public void shouldNotBeEqualWhenObjectOfDifferentClass() throws Exception {
        assertThat(variable, is(not(equalTo(new Object()))));
    }

    @Test
    public void shouldNotBeEqualWhenActivityIsDifferent() throws Exception {
        variable.setActive(true);
        variable2.setActive(false);

        assertThat(variable, is(not(equalTo(variable2))));
    }

    @Test
    public void shouldBeEqualWhenActivityIsTheSame() throws Exception {
        variable.setActive(true);
        variable2.setActive(true);

        assertThat(variable, is(equalTo(variable2)));
    }

    @Test
    public void shouldNotBeEqualWhenNameIsDifferent() throws Exception {
        variable.setName(VALUE);
        variable2.setName(DIFFERENT_VALUE);

        assertThat(variable, is(not(equalTo(variable2))));
    }

    @Test
    public void shouldBeEqualWhenNameIsTheSame() throws Exception {
        variable.setName(VALUE);
        variable2.setName(VALUE);

        assertThat(variable, is(equalTo(variable2)));
    }

    @Test
    public void shouldNotBeEqualWhenNamespaceIsDifferent() throws Exception {
        variable.setNamespace(VALUE);
        variable2.setNamespace(DIFFERENT_VALUE);

        assertThat(variable, is(not(equalTo(variable2))));
    }

    @Test
    public void shouldBeEqualWhenNamespaceIsTheSame() throws Exception {
        variable.setNamespace(VALUE);
        variable2.setNamespace(VALUE);

        assertThat(variable, is(equalTo(variable2)));
    }

    @Test
    public void shouldNotBeEqualWhenTypeIsDifferent() throws Exception {
        variable.setType(VALUE);
        variable2.setType(DIFFERENT_VALUE);

        assertThat(variable, is(not(equalTo(variable2))));
    }

    @Test
    public void shouldBeEqualWhenTypeIsTheSame() throws Exception {
        variable.setType(VALUE);
        variable2.setType(VALUE);

        assertThat(variable, is(equalTo(variable2)));
    }

    @Test
    public void shouldNotBeEqualWhenValueIsDifferent() throws Exception {
        variable.setValue(VALUE);
        variable2.setValue(DIFFERENT_VALUE);

        assertThat(variable, is(not(equalTo(variable2))));
    }

    @Test
    public void shouldBeEqualWhenValueIsTheSame() throws Exception {
        variable.setValue(VALUE);
        variable2.setValue(VALUE);

        assertThat(variable, is(equalTo(variable2)));
    }
}
