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

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.ComboboxSpeedSearch;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.SortedComboBoxModel;
import com.intellij.ui.components.JBCheckBox;
import org.intellij.xquery.runner.rt.XQJType;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

/**
 * User: ligasgr
 * Date: 08/10/13
 * Time: 14:17
 */
public class VariableDialog extends DialogWrapper {

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

    private boolean isValidConfiguration() {
        boolean nameFieldPopulated = name.getComponent().getText() != null
                && name.getComponent().getText().length() > 0;
        boolean typeFieldPopulated = type.getComponent().getSelectedItem() != null;
        return nameFieldPopulated && typeFieldPopulated;
    }

    public VariableDialog() {
        super(true);
        setTitle("Edit variable");
        init();
    }

    public void init(boolean active, String name, String namespace, String type, String value) {
        this.active.getComponent().setSelected(active);
        this.name.getComponent().setText(name);
        this.namespace.getComponent().setText(namespace);
        this.type.getComponent().setSelectedItem(type);
        this.value.getComponent().setText(value);
        setOKActionEnabled(isValidConfiguration());
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        name.getComponent().getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent e) {
                setOKActionEnabled(isValidConfiguration());
            }
        });
        value.getComponent().setRows(7);
        value.getComponent().setColumns(50);
        type.getComponent().setModel(typesModel);
        type.getComponent().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOKActionEnabled(isValidConfiguration());
            }
        });
        populateTypesList();
        new ComboboxSpeedSearch(type.getComponent());
        return panel;
    }

    private void populateTypesList() {
        for (String type : XQJType.getAll()) {
            typesModel.add(type);
        }
    }

    public boolean isActive() {
        return active.getComponent().isSelected();
    }

    public String getName() {
        return name.getComponent().getText();
    }

    public String getNamespace() {
        return namespace.getComponent().getText();
    }

    public String getType() {
        return (String) type.getComponent().getSelectedItem();
    }

    public String getValue() {
        return value.getComponent().getText();
    }
}
