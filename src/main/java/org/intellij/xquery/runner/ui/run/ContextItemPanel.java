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

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.ui.*;
import com.intellij.ui.components.JBCheckBox;
import net.miginfocom.swing.MigLayout;
import org.intellij.xquery.runner.rt.XQJType;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Comparator;

/**
 * User: ligasgr
 * Date: 24/09/13
 * Time: 15:23
 */
public class ContextItemPanel extends JPanel implements PanelWithAnchor {

    private final JBCheckBox contextItemEnabled;
    private final TextFieldWithBrowseButton contextItemPathField;
    private final EditorTextField contextItemEditorField;
    private final ComponentWithBrowseButton<EditorTextField> contextItemEditorContent;
    private final JRadioButton editorRadioButton;
    private final JRadioButton fileRadioButton;
    private final ButtonGroup buttonGroup;
    private final LabeledComponent<JComboBox> contextItemTypeField;
    private final JPanel contextItemOptionsPanel;
    private JComponent anchor;
    private SortedComboBoxModel<Object> typesModel = new SortedComboBoxModel<Object>(new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
            return ((String) o1).compareToIgnoreCase((String) o2);
        }
    });

    public ContextItemPanel(Project project) {
        contextItemEnabled = new JBCheckBox("Pass context item");
        contextItemEnabled.setMnemonic(KeyEvent.VK_C);
        contextItemEnabled.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contextItemEnabledChanged();
            }
        });
        contextItemOptionsPanel = new JPanel(new MigLayout("ins 0, gap 5, fill, flowx"));
        contextItemEditorField = new EditorTextField("", project, StdFileTypes.PLAIN_TEXT);
        contextItemEditorContent = new MyEditorTextFieldWithBrowseButton(project, contextItemEditorField);
        contextItemEditorContent.setButtonIcon(AllIcons.Actions.ShowViewer);
        contextItemPathField = new TextFieldWithBrowseButton();
        contextItemPathField.addBrowseFolderListener("Choose file", null, null,
                FileChooserDescriptorFactory.createSingleFileOrFolderDescriptor());
        contextItemTypeField = new LabeledComponent<JComboBox>();
        contextItemTypeField.setText("&Type");
        contextItemTypeField.setLabelLocation("West");
        contextItemTypeField.setComponent(new JComboBox());
        editorRadioButton = new JRadioButton("Custom content");
        editorRadioButton.setMnemonic(KeyEvent.VK_E);
        editorRadioButton.setSelected(true);
        editorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contextItemSourceChanged();
            }
        });
        fileRadioButton = new JRadioButton("Content from file");
        fileRadioButton.setMnemonic(KeyEvent.VK_L);
        fileRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contextItemSourceChanged();
            }
        });
        buttonGroup = new ButtonGroup();
        buttonGroup.add(editorRadioButton);
        buttonGroup.add(fileRadioButton);
        setLayout(new MigLayout("ins 0, gap 5, fill, flowx"));
        add(contextItemEnabled, "shrinkx, top");
        add(contextItemOptionsPanel, "growx, pushx");
        contextItemTypeField.getComponent().setModel(typesModel);
        contextItemOptionsPanel.add(contextItemTypeField, "growx, pushx, wrap, span 2");
        contextItemOptionsPanel.add(editorRadioButton);
        contextItemOptionsPanel.add(contextItemEditorContent, "growx, pushx, wrap");
        contextItemOptionsPanel.add(fileRadioButton);
        contextItemOptionsPanel.add(contextItemPathField, "growx, pushx");
        contextItemEnabledChanged();
        contextItemSourceChanged();
        populateTypesList();
        new ComboboxSpeedSearch(contextItemTypeField.getComponent());
    }

    @Override
    public JComponent getAnchor() {
        return anchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        this.anchor = anchor;
    }

    public void init(boolean isEnabled, String content, String filePath, boolean contextItemFromEditorEnabled, String
            type) {
        contextItemEditorContent.getChildComponent().setText(content);
        contextItemPathField.setText(filePath);
        setContextItemEnabled(isEnabled);
        setContextItemFromEditorEnabled(contextItemFromEditorEnabled);
        contextItemTypeField.getComponent().setSelectedItem(type);
    }

    private void setContextItemFromEditorEnabled(boolean contextItemFromEditorEnabled) {
        if (contextItemFromEditorEnabled) {
            editorRadioButton.setSelected(true);
        } else {
            fileRadioButton.setSelected(true);
        }
        contextItemSourceChanged();
    }

    private void setContextItemEnabled(boolean enabled) {
        contextItemEnabled.setSelected(enabled);
        contextItemEnabledChanged();
    }

    private void contextItemEnabledChanged() {
        final boolean pathEnabled = isContextItemEnabled();
        GuiUtils.enableChildren(contextItemOptionsPanel, pathEnabled);
        contextItemSourceChanged();
    }

    private void contextItemSourceChanged() {
        final boolean editorAsSource = isContextItemFromEditorEnabled();
        if (isContextItemEnabled()) {
            GuiUtils.enableChildren(contextItemEditorContent, editorAsSource);
            GuiUtils.enableChildren(contextItemPathField, !editorAsSource);
        }
        contextItemEditorContent.invalidate();
        contextItemPathField.invalidate();
    }

    public boolean isContextItemFromEditorEnabled() {
        return editorRadioButton.isSelected();
    }

    public boolean isContextItemEnabled() {
        return contextItemEnabled.isSelected();
    }

    public String getContextItemPath() {
        return FileUtil.toSystemIndependentName(contextItemPathField.getChildComponent().getText().trim());
    }

    public String getContextItemEditorContent() {
        return contextItemEditorContent.getChildComponent().getText();
    }

    public String getContextItemType() {
        return (String) contextItemTypeField.getComponent().getSelectedItem();
    }

    private class MyEditorTextFieldWithBrowseButton extends ComponentWithBrowseButton<EditorTextField> {
        public MyEditorTextFieldWithBrowseButton(final Project project, final EditorTextField editorTextField) {
            super(editorTextField, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editorTextField.setText(
                            Messages.showMultilineInputDialog(project, "Edit custom content", "Edit",
                                    editorTextField.getText(), null, null));
                }
            });
        }
    }
    private void populateTypesList() {
        for (String type : XQJType.getAll()) {
            typesModel.add(type);
        }
    }
}
