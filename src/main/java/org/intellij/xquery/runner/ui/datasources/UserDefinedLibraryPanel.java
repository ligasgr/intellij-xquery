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

package org.intellij.xquery.runner.ui.datasources;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;
import com.intellij.ui.ListUtil;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBList;
import com.intellij.util.Consumer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ligasgr
 * Date: 06/10/13
 * Time: 19:05
 */
public class UserDefinedLibraryPanel {
    private final FileChooserDescriptor jarsOnlyFileChooserDescriptor = new FileChooserDescriptor(false, false, true,
            true, false, true);
    private JPanel mainPanel;
    private JBCheckBox userDefinedLibraryEnabled;
    private JPanel pathsPanel;
    private DefaultListModel pathListModel;
    private JBList pathList;

    public UserDefinedLibraryPanel() {
        pathListModel = new DefaultListModel();
        pathList = preparePathList(pathListModel);
        ToolbarDecorator toolbarDecorator = prepareToolbarDecorator(pathList);
        pathsPanel.add(toolbarDecorator.createPanel());
        userDefinedLibraryEnabled.addActionListener(getUserDefinedLibraryEnabledListener());
    }

    private ToolbarDecorator prepareToolbarDecorator(final JBList pathList) {
        return ToolbarDecorator.createDecorator(pathList)
                .setAddAction(new AnActionButtonRunnable() {
                    @Override
                    public void run(AnActionButton button) {
                        showAddPathPopup();
                    }
                }).setRemoveAction(new AnActionButtonRunnable() {
                    @Override
                    public void run(AnActionButton button) {
                        ListUtil.removeSelectedItems(pathList);
                        pathList.repaint();
                    }
                });
    }

    private void showAddPathPopup() {
        FileChooser.chooseFiles(jarsOnlyFileChooserDescriptor, null, null, new Consumer<List<VirtualFile>>() {
            @Override
            public void consume(List<VirtualFile> files) {
                for (VirtualFile file : files) {
                    onFileChosen(file);
                }
            }
        });
    }

    private void onFileChosen(VirtualFile chosenFile) {
        pathListModel.addElement(chosenFile.getPresentableUrl());
    }

    public void init(boolean userDefinedLibraryEnabled, List<String> userDefinedLibraryPaths) {
        this.userDefinedLibraryEnabled.setSelected(userDefinedLibraryEnabled);
        populatePathList(userDefinedLibraryPaths);
        userDefinedLibraryEnabledChanged();
    }

    private void populatePathList(List<String> userDefinedLibraryPaths) {
        pathListModel.removeAllElements();
        for (String userDefinedLibraryPath : userDefinedLibraryPaths) {
            pathListModel.addElement(userDefinedLibraryPath);
        }
    }

    public boolean isUserDefinedLibraryEnabled() {
        return userDefinedLibraryEnabled.isSelected();
    }

    private void userDefinedLibraryEnabledChanged() {
        pathList.setEnabled(userDefinedLibraryEnabled.isSelected());
    }

    public List<String> getUserDefinedLibraryPaths() {
        List<String> currentPaths = new ArrayList<String>();
        for (int i = 0; i < pathListModel.getSize(); i++) {
            currentPaths.add(((String) pathListModel.getElementAt(i)));
        }
        return currentPaths;
    }

    private JBList preparePathList(DefaultListModel pathListModel) {
        final JBList pathList = new JBList(pathListModel);
        pathList.getEmptyText().setText("No classpath entries defined");
        pathList.setDragEnabled(false);
        pathList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return pathList;
    }

    private ActionListener getUserDefinedLibraryEnabledListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userDefinedLibraryEnabledChanged();
            }
        };
    }
}
