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

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.testFramework.TestActionEvent;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.CommonActionsPanel;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import org.fest.swing.edt.GuiTask;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.state.run.XQueryRunVariable;
import org.intellij.xquery.runner.state.run.XQueryRunVariables;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.swing.JComponent;

import static com.intellij.ui.CommonActionsPanel.Buttons.ADD;
import static com.intellij.ui.CommonActionsPanel.Buttons.EDIT;
import static com.intellij.ui.CommonActionsPanel.Buttons.REMOVE;
import static java.util.Arrays.asList;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.run.main.variables.VariablesPanel.VARIABLES_PANEL;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 20/11/13
 * Time: 20:54
 */
public class VariablesPanelGuiTest extends BaseGuiTest {
    private static final String NAME = "name";
    private static final String NAMESPACE = "namespace";
    private static final String TYPE = "xs:string";
    private static final String VALUE = "value";
    private static final boolean ACTIVE = true;
    private static final String ANOTHER_NAME = "another";
    private TestVariablesPanel panel;
    private XQueryRunConfiguration configuration;
    private XQueryRunVariables variables;
    private XQueryRunVariable variable;
    private boolean returnOkFromDialogAndUpdateVariable;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new TestVariablesPanel();
        configuration = mock(XQueryRunConfiguration.class);
        variable = new XQueryRunVariable(NAME, NAMESPACE, TYPE, VALUE, ACTIVE);
        variables = new XQueryRunVariables(asList(variable));
        given(configuration.getVariables()).willReturn(variables);
        return new PanelTestingFrame(panel);
    }

    @Test
    public void shouldShowPanel() {
        window.panel(VARIABLES_PANEL).requireVisible();
    }

    @Test
    public void shouldHaveTheRightColumnNames() {
        ListTableModel<XQueryRunVariable> model = (ListTableModel<XQueryRunVariable>) window.table().component()
                .getModel();

        ColumnInfo[] columnInfoArray = model.getColumnInfos();

        assertThat(columnInfoArray[0].getName(), is(IsActiveColumnInfo.HEADER));
        assertThat(columnInfoArray[1].getName(), is(NameColumnInfo.HEADER));
        assertThat(columnInfoArray[2].getName(), is(NamespaceColumnInfo.HEADER));
        assertThat(columnInfoArray[3].getName(), is(TypeColumnInfo.HEADER));
        assertThat(columnInfoArray[4].getName(), is(ValueColumnInfo.HEADER));
    }

    @Test
    public void shouldNotAllowEditing() {
        window.table().requireNoSelection();
    }

    @Test
    public void shouldDisplayAllVariableData() {
        panel.init(configuration);

        String[] variableRow = window.table().contents()[0];

        assertThat(variableRow[0], is(String.valueOf(ACTIVE)));
        assertThat(variableRow[1], is(NAME));
        assertThat(variableRow[2], is(NAMESPACE));
        assertThat(variableRow[3], is(TYPE));
        assertThat(variableRow[4], is(VALUE));
    }

    @Test
    public void shouldApplyChangesToConfiguration() {
        XQueryRunConfiguration newConfiguration = mock(XQueryRunConfiguration.class);
        ArgumentCaptor<XQueryRunVariables> variablesCaptor = ArgumentCaptor.forClass(XQueryRunVariables.class);

        panel.init(configuration);

        panel.applyChanges(newConfiguration);

        verify(newConfiguration).setVariables(variablesCaptor.capture());
        assertThat(variablesCaptor.getValue().getVariables().size(), is(1));
        assertThat(variablesCaptor.getValue().getVariables().contains(variable), is(true));
    }

    @Test
    public void shouldNotAddNewVariableToListAfterShowingDialogWithoutAccepting() {
        returnOkFromDialogAndUpdateVariable = false;

        clickAdd();

        window.table().requireRowCount(0);
    }

    @Test
    public void shouldAddNewVariableToListAfterShowingDialogAfterValueAcceptedAndSelectIt() {
        returnOkFromDialogAndUpdateVariable = true;

        clickAdd();

        window.table().requireRowCount(1);
        window.table().requireSelectedRows(0);
        String[] variableRow = window.table().contents()[0];
        assertThat(variableRow[0], is(String.valueOf(ACTIVE)));
        assertThat(variableRow[1], is(ANOTHER_NAME));
        assertThat(variableRow[2], is(NAMESPACE));
        assertThat(variableRow[3], is(TYPE));
        assertThat(variableRow[4], is(VALUE));
    }

    @Test
    public void shouldRemoveVariableAfterRemoveButtonClicked() {
        panel.init(configuration);
        window.table().component().getSelectionModel().setSelectionInterval(0, 0);
        window.table().requireSelectedRows(0);

        clickRemove();

        window.table().requireNoSelection();
        window.table().requireRowCount(0);
    }

    @Test
    public void shouldNotUpdateVariableIfDialogWasCancelled() {
        returnOkFromDialogAndUpdateVariable = false;
        panel.init(configuration);
        window.table().component().getSelectionModel().setSelectionInterval(0, 0);
        window.table().requireSelectedRows(0);

        clickUpdate();

        String[] variableRow = window.table().contents()[0];
        assertThat(variableRow[0], is(String.valueOf(ACTIVE)));
        assertThat(variableRow[1], is(NAME));
        assertThat(variableRow[2], is(NAMESPACE));
        assertThat(variableRow[3], is(TYPE));
        assertThat(variableRow[4], is(VALUE));
    }

    @Test
    public void shouldUpdateVariableIfDialogWasAccepted() {
        returnOkFromDialogAndUpdateVariable = true;
        panel.init(configuration);
        window.table().component().getSelectionModel().setSelectionInterval(0, 0);
        window.table().requireSelectedRows(0);

        clickUpdate();

        String[] variableRow = window.table().contents()[0];
        assertThat(variableRow[0], is(String.valueOf(ACTIVE)));
        assertThat(variableRow[1], is(ANOTHER_NAME));
        assertThat(variableRow[2], is(NAMESPACE));
        assertThat(variableRow[3], is(TYPE));
        assertThat(variableRow[4], is(VALUE));
    }

    @Test
    public void shouldReturnAnchorThatWasSet() {
        JComponent anchor = mock(JComponent.class);
        panel.setAnchor(anchor);

        assertThat(panel.getAnchor(), is(anchor));
    }

    private void clickAdd() {
        clickButton(ADD);
    }

    private void clickRemove() {
        clickButton(REMOVE);
    }

    private void clickUpdate() {
        clickButton(EDIT);
    }

    private void clickButton(CommonActionsPanel.Buttons button) {
        final AnActionButton action = getAnActionButton(button);
        final AnActionEvent event = new TestActionEvent(action);

        execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                action.actionPerformed(event);
            }
        });
    }

    private AnActionButton getAnActionButton(CommonActionsPanel.Buttons button) {
        return panel.getToolbarDecorator()
                .getActionsPanel()
                .getAnActionButton(button);
    }

    private class TestVariablesPanel extends VariablesPanel {
        @Override
        protected boolean showEditorDialog(@NotNull XQueryRunVariable variable) {
            if (returnOkFromDialogAndUpdateVariable) {
                variable.setActive(ACTIVE);
                variable.setName(ANOTHER_NAME);
                variable.setNamespace(NAMESPACE);
                variable.setType(TYPE);
                variable.setValue(VALUE);

                return true;
            } else {
                return false;
            }
        }
    }
}
