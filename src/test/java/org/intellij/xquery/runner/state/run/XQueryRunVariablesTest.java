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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.intellij.xquery.util.XmlUtils.*;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 28/09/13
 * Time: 18:06
 */
public class XQueryRunVariablesTest {

    private List<XQueryRunVariable> variableList;
    private XQueryRunVariable variable;
    private XQueryRunVariables variables;

    @Before
    public void setUp() throws Exception {
        variableList = new ArrayList<XQueryRunVariable>();
        variable = new XQueryRunVariable();
        variableList.add(variable);
        variables = new XQueryRunVariables(variableList);
    }

    @Test
    public void shouldReturnListOfVariablesOfTheSameSize() throws Exception {
        XQueryRunVariables variables = new XQueryRunVariables();

        assertThat(variables.getVariables().size(), is(equalTo(0)));
    }

    @Test
    public void shouldReturnEqualListOfVariables() throws Exception {
        assertThat(variables.getVariables(), is(equalTo(variableList)));
    }

    @Test
    public void shouldBeEqualWhenListsAreEqual() throws Exception {
        XQueryRunVariables variables2 = new XQueryRunVariables(variableList);

        assertThat(variables, is(equalTo(variables2)));
    }

    @Test
    public void shouldWriteOnlyVariablesTag() throws Exception {
        XQueryRunVariables variables = new XQueryRunVariables();

        String xml = serializeToXml(variables);

        assertThat(the(xml), hasXPath("/variables/list/variable/@active", equalTo("")));
    }

    @Test
    public void shouldWriteChildVariable() throws Exception {
        String xml = serializeToXml(variables);

        assertThat(the(xml), hasXPath("/variables/list/variable/@active", equalTo("false")));
    }

    @Test
    public void shouldReadVariable() throws Exception {
        String xml = "<variables><list><variable></variable></list></variables>";

        XQueryRunVariables readVariables = deserializeFromXml(rootElement(xml), XQueryRunVariables.class);

        assertThat(readVariables.getVariables().size(), is(equalTo(1)));
        assertThat(readVariables.getVariables().get(0), is(equalTo(variable)));
    }
}
