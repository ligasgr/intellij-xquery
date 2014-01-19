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

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.ui.ComboboxSpeedSearch;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.GuiUtils;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.ui.SortedComboBoxModel;
import com.intellij.ui.components.JBCheckBox;
import net.miginfocom.swing.MigLayout;
import org.intellij.xquery.runner.rt.XQueryItemType;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.jetbrains.annotations.Nullable;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

    public static final String CONTEXT_ITEM_PANEL = "contextItemPanel";
    public static final String CONTEXT_ITEM_OPTIONS_PANEL = "contextItemOptionsPanel";
    public static final String CONTEXT_ITEM_TYPE = "contextItemType";
    public static final String EDITOR_RADIO_BUTTON = "editorRadioButton";
    public static final String FILE_RADIO_BUTTON = "fileRadioButton";
    public static final String EDITOR_CONTENT = "editorContent";
    public static final String FILE_PATH = "filePath";
    public static final String SHOW_EDITOR_BUTTON = "showEditorButton";
    private final JBCheckBox contextItemEnabled;
    protected final TextFieldWithBrowseButton contextItemPathField;
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
        setName(CONTEXT_ITEM_PANEL);
        contextItemEnabled = new JBCheckBox("Pass context item");
        contextItemEnabled.setMnemonic(KeyEvent.VK_C);
        contextItemEnabled.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contextItemEnabledChanged();
            }
        });
        contextItemOptionsPanel = new JPanel(new MigLayout("ins 0, gap 5, fill, flowx"));
        contextItemOptionsPanel.setName(CONTEXT_ITEM_OPTIONS_PANEL);
        contextItemEditorField = new EditorTextField("", project, StdFileTypes.PLAIN_TEXT);
        contextItemEditorContent = new MyEditorTextFieldWithBrowseButton(project, contextItemEditorField);
        contextItemEditorContent.getChildComponent().setName(EDITOR_CONTENT);
        contextItemEditorContent.setButtonIcon(AllIcons.Actions.ShowViewer);
        contextItemPathField = new TextFieldWithBrowseButton();
        contextItemPathField.getTextField().setName(FILE_PATH);
        addPathFieldButtonListener();
        contextItemTypeField = new LabeledComponent<JComboBox>();
        contextItemTypeField.setText("&Type");
        contextItemTypeField.setLabelLocation("West");
        contextItemTypeField.setComponent(new JComboBox());
        editorRadioButton = new JRadioButton("Custom content");
        editorRadioButton.setName(EDITOR_RADIO_BUTTON);
        editorRadioButton.setMnemonic(KeyEvent.VK_E);
        editorRadioButton.setSelected(true);
        editorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contextItemSourceChanged();
            }
        });
        fileRadioButton = new JRadioButton("Content from file");
        fileRadioButton.setName(FILE_RADIO_BUTTON);
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
        contextItemTypeField.getComponent().setName(CONTEXT_ITEM_TYPE);
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

    protected void addPathFieldButtonListener() {
        contextItemPathField.addBrowseFolderListener("Choose file", null, null,
                FileChooserDescriptorFactory.createSingleFileOrFolderDescriptor());
    }

    @Override
    public JComponent getAnchor() {
        return anchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        this.anchor = anchor;
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
            GuiUtils.enableChildren(contextItemPathField, ! editorAsSource);
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

    public void applyChanges(XQueryRunConfiguration configuration) {
        configuration.setContextItemEnabled(isContextItemEnabled());
        configuration.setContextItemFile(getContextItemPath());
        configuration.setContextItemText(getContextItemEditorContent());
        configuration.setContextItemFromEditorEnabled(isContextItemFromEditorEnabled());
        configuration.setContextItemType(getContextItemType());
    }

    public void init(XQueryRunConfiguration configuration) {
        contextItemEditorContent.getChildComponent().setText(configuration.getContextItemText());
        contextItemPathField.setText(configuration.getContextItemFile());
        setContextItemEnabled(configuration.isContextItemEnabled());
        setContextItemFromEditorEnabled(configuration.isContextItemFromEditorEnabled());
        contextItemTypeField.getComponent().setSelectedItem(configuration.getContextItemType());
    }

    private class MyEditorTextFieldWithBrowseButton extends ComponentWithBrowseButton<EditorTextField> {
        public MyEditorTextFieldWithBrowseButton(final Project project, final EditorTextField editorTextField) {
            super(editorTextField, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editorTextField.setText(getValueFromMultilineInputDialog(project, editorTextField));
                }
            });
            getButton().setName(SHOW_EDITOR_BUTTON);

        }
    }

    private String getValueFromMultilineInputDialog(Project project, EditorTextField editorTextField) {
        return Messages.showMultilineInputDialog(project, "Edit custom content", "Edit",
                editorTextField.getText(), null, null);
    }

    private void populateTypesList() {
        for (String type : XQueryItemType.getAll()) {
            typesModel.add(type);
        }
    }
}
