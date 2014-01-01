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

package org.intellij.xquery.runner.ui.run.main.variables;

import org.intellij.xquery.runner.state.run.XQueryRunVariable;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.run.main.variables.IsActiveColumnInfo.HEADER;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 09/11/13
 * Time: 21:40
 */
public class IsActiveColumnInfoTest {

    private IsActiveColumnInfo columnInfo;
    private XQueryRunVariable variable;

    @Before
    public void setUp() throws Exception {
        columnInfo = new IsActiveColumnInfo();
        variable = new XQueryRunVariable();
    }

    @Test
    public void shouldReturnHeader() {
        assertThat(columnInfo.getName(), is(HEADER));
    }

    @Test
    public void shouldReturnActivityOfVariable() {
        boolean active = true;
        variable.setActive(active);

        boolean result = columnInfo.valueOf(variable);

        assertThat(result, is(active));
    }

    @Test
    public void shouldReturnBooleanAsColumnClass() {
        assertThat(columnInfo.getColumnClass().equals(Boolean.class), is(true));
    }

    @Test
    public void shouldBeAbleToEditCell() {
        assertThat(columnInfo.isCellEditable(variable), is(true));
    }

    @Test
    public void shouldSetActivityInVariable() {
        boolean active = true;

        columnInfo.setValue(variable, active);

        assertThat(variable.isActive(), is(active));
    }
}
