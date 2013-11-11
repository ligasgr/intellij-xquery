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

package org.intellij.xquery.gui.runner.ui.run.main.variables;

import org.intellij.xquery.gui.BaseGuiTest;
import org.intellij.xquery.gui.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQJType;
import org.intellij.xquery.runner.ui.run.main.variables.VariableDialog;
import org.intellij.xquery.runner.ui.run.main.variables.VariableDialogWrapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 11/11/13
 * Time: 14:17
 */
public class VariableDialogTest extends BaseGuiTest {
    private VariableDialogWrapper wrapper;
    private VariableDialog dialog;
    private static final String VALUE = "my value";
    private static final String TYPE = XQJType.XS_STRING.getDescription();
    private static final String NAME = "my name";
    private static final String NAMESPACE = "my namespace";

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        wrapper = mock(VariableDialogWrapper.class);
        dialog = new VariableDialog(wrapper);
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
        dialog.init(true, null, null, null, null);

        window.checkBox().requireSelected();

        dialog.init(false, null, null, null, null);

        window.checkBox().requireNotSelected();
    }

    @Test
    public void shouldPopulateName() {
        dialog.init(false, NAME, null, null, null);

        window.textBox(VariableDialog.NAME).requireText(NAME);
    }

    @Test
    public void shouldPopulateNamespace() {
        dialog.init(false, null, NAMESPACE, null, null);

        window.textBox(VariableDialog.NAMESPACE).requireText(NAMESPACE);
    }

    @Test
    public void shouldPopulateType() {
        dialog.init(false, null, null, TYPE, null);

        window.comboBox().requireSelection(TYPE);
    }

    @Test
    public void shouldPopulateValue() {
        dialog.init(false, null, null, null, VALUE);

        window.textBox(VariableDialog.VALUE).requireText(VALUE);
    }

    @Test
    public void shouldChangeValueOfPassAfterChecked() {
        dialog.init(false, null, null, null, null);

        window.checkBox().check();

        assertThat(dialog.isActive(), is(true));
    }

    @Test
    public void shouldChangeValueOfPassAfterUnChecked() {
        dialog.init(true, null, null, null, null);

        window.checkBox().uncheck();

        assertThat(dialog.isActive(), is(false));
    }

    @Test
    public void shouldChangeValueOfNameAfterTextEntered() {
        dialog.init(false, null, null, null, null);

        window.textBox(VariableDialog.NAME).enterText(NAME);

        assertThat(dialog.getName(), is(NAME));
    }

    @Test
    public void shouldChangeValueOfNamespaceAfterTextEntered() {
        dialog.init(false, null, null, null, null);

        window.textBox(VariableDialog.NAMESPACE).enterText(NAMESPACE);

        assertThat(dialog.getNamespace(), is(NAMESPACE));
    }

    @Test
    public void shouldChangeValueOfTypeAfterSelectionMade() {
        dialog.init(false, null, null, null, null);

        window.comboBox().selectItem(TYPE);

        assertThat(dialog.getType(), is(TYPE));
    }

    @Test
    public void shouldChangeValueOfValueFieldAfterTextEntered() {
        dialog.init(false, null, null, null, null);

        window.textBox(VariableDialog.VALUE).enterText(VALUE);

        assertThat(dialog.getValue(), is(VALUE));
    }

    @Test
    public void shouldChangeOkButtonStateAfterTypingIntoNameField() {
        dialog.init(false, null, null, null, null);

        window.textBox(VariableDialog.NAME).enterText("a");

        verify(wrapper, atLeast(1)).setOKActionEnabled(anyBoolean());
    }

    @Test
    public void shouldChangeOkButtonStateAfterSelectingType() {
        dialog.init(false, null, null, null, null);

        window.comboBox().selectItem(TYPE);

        verify(wrapper, atLeast(1)).setOKActionEnabled(anyBoolean());
    }

    @Test
    public void shouldDisableOkButtonWhenOnlyNameFilled() {
        dialog.init(false, null, null, null, null);

        window.textBox(VariableDialog.NAME).enterText("a");

        verify(wrapper, atLeast(1)).setOKActionEnabled(false);
    }

    @Test
    public void shouldDisableOkButtonWhenOnlyTypeFilled() {
        dialog.init(false, null, null, null, null);

        window.comboBox().selectItem(TYPE);

        verify(wrapper, atLeast(1)).setOKActionEnabled(false);
    }

    @Test
    public void shouldDisableOkButtonWhenNameWasFilledAndNowIsNot() {
        dialog.init(false, NAME, null, TYPE, null);

        window.textBox(VariableDialog.NAME).deleteText();

        verify(wrapper, atLeast(1)).setOKActionEnabled(false);
    }

    @Test
    public void shouldDisableOkButtonWhenTypeWasFilledAndNowIsNot() {
        dialog.init(false, NAME, null, TYPE, null);

        window.comboBox().clearSelection();

        verify(wrapper, atLeast(1)).setOKActionEnabled(false);
    }

    @Test
    public void shouldEnableOkButtonAfterSelectingType() {
        dialog.init(false, NAME, null, null, null);

        window.comboBox().selectItem(TYPE);

        verify(wrapper, atLeast(1)).setOKActionEnabled(true);
    }

    @Test
    public void shouldEnableOkButtonAfterTypingName() {
        dialog.init(false, null, null, TYPE, null);

        window.textBox(VariableDialog.NAME).enterText("a");

        verify(wrapper, atLeast(1)).setOKActionEnabled(true);
    }

    private List<String> getSortedTypes() {
        List<String> sortedTypes = new ArrayList<String>(XQJType.getAll());
        sort(sortedTypes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        return sortedTypes;
    }
}
