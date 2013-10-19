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

package org.intellij.xquery.runner.ui.run;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.*;
import com.intellij.ui.table.TableView;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import com.intellij.util.ui.UIUtil;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.state.run.XQueryRunVariable;
import org.intellij.xquery.runner.state.run.XQueryRunVariables;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * User: ligasgr
 * Date: 21/09/13
 * Time: 22:54
 */
public class RunConfigurationMainTab extends SettingsEditor<XQueryRunConfiguration> implements PanelWithAnchor {

    private JPanel variablesPanel;
    private final TableView<XQueryRunVariable> variablesTable;
    private final ListTableModel<XQueryRunVariable> variablesModel;
    private LabeledComponent<XQueryModuleSelector> mainFile;
    private ContextItemPanel contextItemPanel;
    private final Project project;
    private JComponent anchor;
    private JPanel editor;
    private JButton configureDataSourcesButton;
    private LabeledComponent<JComboBox> dataSourceSelectorComponent;
    private DataSourceSelector dataSourceSelector;
    private final ColumnInfo<XQueryRunVariable, String> NAME = new NameColumnInfo();
    private final ColumnInfo<XQueryRunVariable, String> NAMESPACE = new NamespaceColumnInfo();
    private final ColumnInfo<XQueryRunVariable, String> TYPE = new TypeColumnInfo();
    private final ColumnInfo<XQueryRunVariable, String> VALUE = new ValueColumnInfo();
    private final ColumnInfo<XQueryRunVariable, Boolean> IS_ACTIVE = new IsActiveColumnInfo();

    public RunConfigurationMainTab(final Project project) {
        this.project = project;
        variablesModel = new ListTableModel<XQueryRunVariable>(IS_ACTIVE, NAME, NAMESPACE, TYPE, VALUE);
        variablesTable = prepareVariablesTable();
        ToolbarDecorator variablesTableToolbarDecorator = prepareVariablesTableToolbarDecorator(variablesTable);
        variablesPanel.add(variablesTableToolbarDecorator.createPanel(), BorderLayout.CENTER);
        variablesPanel.setPreferredSize(new Dimension(-1, 120));
        dataSourceSelector = new DataSourceSelector(dataSourceSelectorComponent.getComponent());
        configureDataSourcesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataSourcesDialog(editor, dataSourceSelector).show();
            }
        });

        anchor = UIUtil.mergeComponentsWithAnchor(mainFile, contextItemPanel);
    }

    private ToolbarDecorator prepareVariablesTableToolbarDecorator(final TableView<XQueryRunVariable> variablesTable) {
        return ToolbarDecorator.createDecorator(variablesTable)
                .setAddAction(new AnActionButtonRunnable() {
                    @Override
                    public void run(AnActionButton button) {
                        ArrayList<XQueryRunVariable> newList = new ArrayList<XQueryRunVariable>
                                (variablesModel.getItems());
                        XQueryRunVariable newVariable = new XQueryRunVariable();
                        if (showEditorDialog(newVariable)) {
                            newList.add(newVariable);
                            variablesModel.setItems(newList);
                            int index = variablesModel.getRowCount() - 1;
                            variablesModel.fireTableRowsInserted(index, index);
                            variablesTable.setRowSelectionInterval(index, index);
                        }
                    }
                }).setRemoveAction(new AnActionButtonRunnable() {
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
                }).setEditAction(new AnActionButtonRunnable() {
                    @Override
                    public void run(AnActionButton button) {
                        final int selectedRow = variablesTable.getSelectedRow();
                        final XQueryRunVariable selectedVariable = variablesTable.getSelectedObject();
                        showEditorDialog(selectedVariable);
                        variablesModel.fireTableDataChanged();
                        variablesTable.setRowSelectionInterval(selectedRow, selectedRow);
                    }
                }).setRemoveActionUpdater(new AnActionButtonUpdater() {
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
                }).disableUpDownActions();
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

    @Override
    public JComponent getAnchor() {
        return anchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        mainFile.setAnchor(anchor);
        contextItemPanel.setAnchor(anchor);
    }

    public XQueryModuleSelector getMainFileField() {
        return mainFile.getComponent();
    }

    @Override
    protected void resetEditorFrom(XQueryRunConfiguration configuration) {
        getMainFileField().setText(configuration.getMainFileName());
        ArrayList<XQueryRunVariable> newList = new ArrayList<XQueryRunVariable>(configuration.getVariables()
                .getVariables());
        variablesModel.setItems(newList);
        contextItemPanel.init(configuration.isContextItemEnabled(), configuration.getContextItemText(),
                configuration.getContextItemFile(), configuration.isContextItemFromEditorEnabled(),
                configuration.getContextItemType());
        dataSourceSelector.reset(configuration);
    }

    @Override
    protected void applyEditorTo(XQueryRunConfiguration configuration) throws ConfigurationException {
        configuration.setMainFileName(getMainFileField().getText());
        configuration.setVariables(new XQueryRunVariables(variablesModel.getItems()));
        configuration.setContextItemEnabled(contextItemPanel.isContextItemEnabled());
        configuration.setContextItemFile(contextItemPanel.getContextItemPath());
        configuration.setContextItemText(contextItemPanel.getContextItemEditorContent());
        configuration.setContextItemFromEditorEnabled(contextItemPanel.isContextItemFromEditorEnabled());
        configuration.setContextItemType(contextItemPanel.getContextItemType());
        dataSourceSelector.applyTo(configuration);
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return editor;
    }

    @Override
    protected void disposeEditor() {
    }

    private void createUIComponents() {
        mainFile = new LabeledComponent<XQueryModuleSelector>();
        mainFile.setComponent(new XQueryModuleSelector(project));
        contextItemPanel = new ContextItemPanel(project);
    }

    private static boolean showEditorDialog(@NotNull XQueryRunVariable variable) {
        VariableDialog dialog = new VariableDialog();
        dialog.init(variable.isActive(), variable.getName(), variable.getNamespace(), variable.getType(),
                variable.getValue());
        dialog.show();
        if (dialog.isOK()) {
            variable.setActive(dialog.isActive());
            variable.setName(dialog.getName());
            variable.setNamespace(dialog.getNamespace());
            variable.setType(dialog.getType());
            variable.setValue(dialog.getValue());
            return true;
        }
        return false;
    }
}
