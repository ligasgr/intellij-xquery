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

import com.intellij.openapi.ui.ValidationInfo;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryItemType;
import org.intellij.xquery.runner.state.run.XQueryRunVariable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 11/11/13
 * Time: 14:17
 */
public class VariableDialogGuiTest extends BaseGuiTest {
    private VariableDialog dialog;
    private static final String VALUE = "my value";
    private static final String TYPE = XQueryItemType.XS_STRING.getTextRepresentation();
    private static final String NAME = "my name";
    private static final String NAMESPACE = "my namespace";
    private XQueryRunVariable variable;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        variable = new XQueryRunVariable();
        dialog = new VariableDialog();
        return new PanelTestingFrame(dialog.getPanel());
    }

    @Test
    public void shouldShowPanel() {
        window.panel(VariableDialog.DIALOG_PANEL).requireVisible();
    }

    @Test
    public void shouldPopulateTypeComboBox() {
        List<String> sortedTypes = getSortedTypes();

        List<String> types = asList(window.comboBox().contents());

        assertThat(types, is(equalTo(sortedTypes)));
    }

    @Test
    public void shouldPopulateActiveCheckbox() {
        variable.setActive(true);
        dialog.init(variable);

        window.checkBox().requireSelected();

        variable.setActive(false);
        dialog.init(variable);

        window.checkBox().requireNotSelected();
    }

    @Test
    public void shouldPopulateName() {
        variable.setName(NAME);
        dialog.init(variable);

        window.textBox(VariableDialog.NAME).requireText(NAME);
    }

    @Test
    public void shouldPopulateNamespace() {
        variable.setNamespace(NAMESPACE);
        dialog.init(variable);

        window.textBox(VariableDialog.NAMESPACE).requireText(NAMESPACE);
    }

    @Test
    public void shouldPopulateType() {
        variable.setType(TYPE);
        dialog.init(variable);

        window.comboBox().requireSelection(TYPE);
    }

    @Test
    public void shouldPopulateValue() {
        variable.setValue(VALUE);
        dialog.init(variable);

        window.textBox(VariableDialog.VALUE).requireText(VALUE);
    }

    @Test
    public void shouldChangeValueOfPassAfterChecked() {
        dialog.init(variable);

        window.checkBox().check();
        dialog.applyValuesTo(variable);

        assertThat(variable.isActive(), is(true));
    }

    @Test
    public void shouldChangeValueOfPassAfterUnChecked() {
        variable.setActive(true);
        dialog.init(variable);

        window.checkBox().uncheck();
        dialog.applyValuesTo(variable);

        assertThat(variable.isActive(), is(false));
    }

    @Test
    public void shouldChangeValueOfNameAfterTextEntered() {
        dialog.init(variable);

        window.textBox(VariableDialog.NAME).enterText(NAME);
        dialog.applyValuesTo(variable);

        assertThat(variable.getName(), is(NAME));
    }

    @Test
    public void shouldChangeValueOfNamespaceAfterTextEntered() {
        dialog.init(variable);

        window.textBox(VariableDialog.NAMESPACE).enterText(NAMESPACE);
        dialog.applyValuesTo(variable);

        assertThat(variable.getNamespace(), is(NAMESPACE));
    }

    @Test
    public void shouldChangeValueOfTypeAfterSelectionMade() {
        dialog.init(variable);

        window.comboBox().selectItem(TYPE);
        dialog.applyValuesTo(variable);

        assertThat(variable.getType(), is(TYPE));
    }

    @Test
    public void shouldChangeValueOfValueFieldAfterTextEntered() {
        dialog.init(variable);

        window.textBox(VariableDialog.VALUE).enterText(VALUE);
        dialog.applyValuesTo(variable);

        assertThat(variable.getValue(), is(VALUE));
    }

    @Test
    public void shouldReturnValidationInfoWhenNameIsNull() {
        dialog.init(variable);

        ValidationInfo result = dialog.validate();

        assertThat(result, is(not(nullValue())));
        assertThat(result.component, is(not(nullValue())));
        assertThat(result.message, is(VariableDialog.NAME_CANNOT_BE_EMPTY));
    }

    @Test
    public void shouldReturnValidationInfoWhenNameIsEmptyText() {
        variable.setName("");
        dialog.init(variable);

        ValidationInfo result = dialog.validate();

        assertThat(result, is(not(nullValue())));
        assertThat(result.component, is(not(nullValue())));
        assertThat(result.message, is(VariableDialog.NAME_CANNOT_BE_EMPTY));
    }

    @Test
    public void shouldReturnValidationInfoWhenTypeNotSelected() {
        variable.setName(NAME);
        dialog.init(variable);

        ValidationInfo result = dialog.validate();

        assertThat(result, is(not(nullValue())));
        assertThat(result.component, is(not(nullValue())));
        assertThat(result.message, is(VariableDialog.TYPE_CANNOT_BE_EMPTY));
    }

    @Test
    public void shouldReturnValidationInfoWhenAllOk() {
        variable.setName(NAME);
        variable.setType(TYPE);
        dialog.init(variable);

        ValidationInfo result = dialog.validate();

        assertThat(result, is(nullValue()));
    }

    private List<String> getSortedTypes() {
        List<String> sortedTypes = new ArrayList<String>(XQueryItemType.getAll());
        sort(sortedTypes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        return sortedTypes;
    }
}
