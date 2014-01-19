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

import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.ui.ComboboxSpeedSearch;
import com.intellij.ui.SortedComboBoxModel;
import com.intellij.ui.components.JBCheckBox;
import org.intellij.xquery.runner.rt.XQueryItemType;
import org.intellij.xquery.runner.state.run.XQueryRunVariable;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Comparator;

/**
 * User: ligasgr
 * Date: 08/10/13
 * Time: 14:17
 */
public class VariableDialog {

    public static final String DIALOG_PANEL = "dialogPanel";
    public static final String NAME = "nameField";
    public static final String NAMESPACE = "namespaceField";
    public static final String VALUE = "valueField";
    public static final String NAME_CANNOT_BE_EMPTY = "Name can't be empty";
    public static final String TYPE_CANNOT_BE_EMPTY = "Type can't be empty";
    private JPanel panel;
    private LabeledComponent<JBCheckBox> active;
    private LabeledComponent<JTextField> name;
    private LabeledComponent<JTextField> namespace;
    private LabeledComponent<JComboBox> type;
    private LabeledComponent<JTextArea> value;
    private SortedComboBoxModel<Object> typesModel = new SortedComboBoxModel<Object>(new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
            return ((String) o1).compareToIgnoreCase((String) o2);
        }
    });

    public VariableDialog() {
        panel.setName(DIALOG_PANEL);
        name.getComponent().setName(NAME);
        namespace.getComponent().setName(NAMESPACE);
        value.getComponent().setRows(7);
        value.getComponent().setColumns(50);
        value.getComponent().setName(VALUE);
        type.getComponent().setModel(typesModel);
        populateTypesList();
        new ComboboxSpeedSearch(type.getComponent());
    }

    public JPanel getPanel() {
        return panel;
    }

    private void populateTypesList() {
        for (String type : XQueryItemType.getAll()) {
            typesModel.add(type);
        }
    }

    public ValidationInfo validate() {
        if (name.getComponent().getText() == null || name.getComponent().getText().length() <= 0)
            return new ValidationInfo(NAME_CANNOT_BE_EMPTY, name.getComponent());

        if (type.getComponent().getSelectedItem() == null)
            return new ValidationInfo(TYPE_CANNOT_BE_EMPTY, name.getComponent());

        return null;
    }

    public void init(XQueryRunVariable variable) {
        this.active.getComponent().setSelected(variable.isActive());
        this.name.getComponent().setText(variable.getName());
        this.namespace.getComponent().setText(variable.getNamespace());
        this.type.getComponent().setSelectedItem(variable.getType());
        this.value.getComponent().setText(variable.getValue());
    }

    public void applyValuesTo(XQueryRunVariable variable) {
        variable.setActive(active.getComponent().isSelected());
        variable.setName(name.getComponent().getText());
        variable.setNamespace(namespace.getComponent().getText());
        variable.setType((String) type.getComponent().getSelectedItem());
        variable.setValue(value.getComponent().getText());
    }
}
