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
import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;
import com.intellij.ui.AnActionButtonUpdater;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.ui.TableUtil;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.table.TableView;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.state.run.XQueryRunVariable;
import org.intellij.xquery.runner.state.run.XQueryRunVariables;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * User: ligasgr
 * Date: 19/11/13
 * Time: 21:49
 */
public class VariablesPanel extends JPanel implements PanelWithAnchor {

    public static final String VARIABLES_PANEL = "variablesPanel";
    private final ColumnInfo<XQueryRunVariable, String> NAME = new NameColumnInfo();
    private final ColumnInfo<XQueryRunVariable, String> NAMESPACE = new NamespaceColumnInfo();
    private final ColumnInfo<XQueryRunVariable, String> TYPE = new TypeColumnInfo();
    private final ColumnInfo<XQueryRunVariable, String> VALUE = new ValueColumnInfo();
    private final ColumnInfo<XQueryRunVariable, Boolean> IS_ACTIVE = new IsActiveColumnInfo();
    private JComponent anchor;
    private final TableView<XQueryRunVariable> variablesTable;
    private final ListTableModel<XQueryRunVariable> variablesModel;
    private ToolbarDecorator toolbarDecorator;

    public VariablesPanel() {
        super(new BorderLayout());
        setName(VARIABLES_PANEL);
        variablesModel = new ListTableModel<XQueryRunVariable>(IS_ACTIVE, NAME, NAMESPACE, TYPE, VALUE);
        variablesTable = prepareVariablesTable();
        toolbarDecorator = prepareVariablesTableToolbarDecorator(variablesTable);
        add(toolbarDecorator.createPanel());
        setPreferredSize(new Dimension(- 1, 120));
    }

    private ToolbarDecorator prepareVariablesTableToolbarDecorator(final TableView<XQueryRunVariable> variablesTable) {
        return ToolbarDecorator.createDecorator(variablesTable)
                .setAddAction(getAddAction(variablesTable))
                .setRemoveAction(getRemoveAction(variablesTable))
                .setEditAction(getUpdateAction(variablesTable))
                .setRemoveActionUpdater(new AnActionButtonUpdater() {
                    @Override
                    public boolean isEnabled(AnActionEvent e) {
                        return variablesTable.getSelectedRowCount() >= 1;
                    }
                }).setEditActionUpdater(new AnActionButtonUpdater() {
                    @Override
                    public boolean isEnabled(AnActionEvent e) {
                        return variablesTable.getSelectedRowCount() >= 1 &&
                                variablesTable.getSelectedObject() != null;
                    }
                }).disableUpDownActions().initPosition();
    }

    private AnActionButtonRunnable getUpdateAction(final TableView<XQueryRunVariable> variablesTable) {
        return new AnActionButtonRunnable() {
            @Override
            public void run(AnActionButton button) {
                final int selectedRow = variablesTable.getSelectedRow();
                final XQueryRunVariable selectedVariable = variablesTable.getSelectedObject();
                showEditorDialog(selectedVariable);
                variablesModel.fireTableDataChanged();
                variablesTable.setRowSelectionInterval(selectedRow, selectedRow);
            }
        };
    }

    private AnActionButtonRunnable getRemoveAction(final TableView<XQueryRunVariable> variablesTable) {
        return new AnActionButtonRunnable() {
            @Override
            public void run(AnActionButton button) {
                TableUtil.stopEditing(variablesTable);
                final int[] selected = variablesTable.getSelectedRows();
                if (selected == null || selected.length == 0) return;
                for (int i = selected.length - 1; i >= 0; i--) {
                    variablesModel.removeRow(selected[i]);
                }
                for (int i = selected.length - 1; i >= 0; i--) {
                    int idx = selected[i];
                    variablesModel.fireTableRowsDeleted(idx, idx);
                }
                int selection = selected[0];
                if (selection >= variablesModel.getRowCount()) {
                    selection = variablesModel.getRowCount() - 1;
                }
                if (selection >= 0) {
                    variablesTable.setRowSelectionInterval(selection, selection);
                }
                variablesTable.requestFocus();
            }
        };
    }

    private AnActionButtonRunnable getAddAction(final TableView<XQueryRunVariable> variablesTable) {
        return new AnActionButtonRunnable() {
            @Override
            public void run(AnActionButton button) {
                XQueryRunVariable newVariable = new XQueryRunVariable();
                if (showEditorDialog(newVariable)) {
                    ArrayList<XQueryRunVariable> newList = new ArrayList<XQueryRunVariable>(variablesModel
                            .getItems());
                    newList.add(newVariable);
                    variablesModel.setItems(newList);
                    int index = variablesModel.getRowCount() - 1;
                    variablesModel.fireTableRowsInserted(index, index);
                    variablesTable.setRowSelectionInterval(index, index);
                }
            }
        };
    }

    private TableView<XQueryRunVariable> prepareVariablesTable() {
        TableView<XQueryRunVariable> variablesTable = new TableView<XQueryRunVariable>(variablesModel);
        variablesTable.getEmptyText().setText("No variables defined");
        variablesTable.setColumnSelectionAllowed(false);
        variablesTable.setShowGrid(false);
        variablesTable.setDragEnabled(false);
        variablesTable.setShowHorizontalLines(false);
        variablesTable.setShowVerticalLines(false);
        variablesTable.setIntercellSpacing(new Dimension(0, 0));
        return variablesTable;
    }

    protected boolean showEditorDialog(@NotNull XQueryRunVariable variable) {
        VariableDialogWrapper wrapper = new VariableDialogWrapper(new VariableDialog());
        wrapper.init(variable);
        wrapper.show();
        if (wrapper.isOK()) {
            wrapper.applyValuesTo(variable);
            return true;
        }
        return false;
    }

    @Override
    public JComponent getAnchor() {
        return anchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        this.anchor = anchor;
    }

    public void applyChanges(XQueryRunConfiguration configuration) {
        configuration.setVariables(new XQueryRunVariables(variablesModel.getItems()));
    }

    public void init(XQueryRunConfiguration configuration) {
        XQueryRunVariables variables = configuration.getVariables();
        ArrayList<XQueryRunVariable> newList = new ArrayList<XQueryRunVariable>(variables.getVariables());
        variablesModel.setItems(newList);
    }

    public ToolbarDecorator getToolbarDecorator() {
        return toolbarDecorator;
    }
}

