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

package org.intellij.xquery.runner.ui.datasources.details;

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
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
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
    public static final String USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME = "userDefinedLibraryEnabled";
    public static final String PATH_LIST_NAME = "pathList";
    private final FileChooserDescriptor jarsOnlyFileChooserDescriptor = new FileChooserDescriptor(false, false, true,
            true, false, true);
    private JPanel mainPanel;
    private JBCheckBox userDefinedLibraryEnabled;
    private JPanel pathsPanel;
    private DefaultListModel pathListModel;
    private JBList pathList;
    private final ToolbarDecorator toolbarDecorator;

    public UserDefinedLibraryPanel() {
        pathListModel = new DefaultListModel();
        pathList = preparePathList(pathListModel);
        toolbarDecorator = prepareToolbarDecorator(pathList);
        pathsPanel.add(toolbarDecorator.createPanel());
        userDefinedLibraryEnabled.addActionListener(getUserDefinedLibraryEnabledListener());
    }

    public void init(XQueryDataSourceConfiguration cfg, DataSourceConfigurationAggregatingPanel
            aggregatingPanel, ConfigurationChangeListener listener) {
        this.userDefinedLibraryEnabled.setSelected(cfg.USER_DEFINED_LIBRARY_ENABLED);
        populatePathList(cfg.USER_DEFINED_LIBRARY_PATHS);
        userDefinedLibraryEnabledChanged();
        setUpChangeListeners(aggregatingPanel, listener);
    }

    public List<String> getUserDefinedLibraryPaths() {
        List<String> currentPaths = new ArrayList<String>();
        for (int i = 0; i < pathListModel.getSize(); i++) {
            currentPaths.add(((String) pathListModel.getElementAt(i)));
        }
        return currentPaths;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public ToolbarDecorator getToolbarDecorator() {
        return toolbarDecorator;
    }

    public boolean isUserDefinedLibraryEnabled() {
        return userDefinedLibraryEnabled.isSelected();
    }

    private ToolbarDecorator prepareToolbarDecorator(final JBList pathList) {
        return ToolbarDecorator.createDecorator(pathList)
                .setAddAction(new AnActionButtonRunnable() {
                    @Override
                    public void run(AnActionButton button) {
                        showAddPathPopup();
                    }
                })
                .setRemoveAction(new AnActionButtonRunnable() {
                    @Override
                    public void run(AnActionButton button) {
                        ListUtil.removeSelectedItems(pathList);
                        pathList.repaint();
                    }
                });
    }

    private void showAddPathPopup() {
        chooseFilesWithFileChooser(jarsOnlyFileChooserDescriptor, new Consumer<List<VirtualFile>>() {
            @Override
            public void consume(List<VirtualFile> files) {
                for (VirtualFile file : files) {
                    onFileChosen(file);
                }
            }
        });
    }

    protected void chooseFilesWithFileChooser(FileChooserDescriptor descriptor, Consumer<List<VirtualFile>> consumer) {
        FileChooser.chooseFiles(descriptor, null, null, consumer);
    }

    public void onFileChosen(VirtualFile chosenFile) {
        pathListModel.addElement(chosenFile.getPresentableUrl());
    }

    public JBList getPathList() {
        return pathList;
    }

    private void populatePathList(List<String> userDefinedLibraryPaths) {
        pathListModel.removeAllElements();
        for (String userDefinedLibraryPath : userDefinedLibraryPaths) {
            pathListModel.addElement(userDefinedLibraryPath);
        }
    }

    private void userDefinedLibraryEnabledChanged() {
        pathList.setEnabled(userDefinedLibraryEnabled.isSelected());
    }

    private JBList preparePathList(DefaultListModel pathListModel) {
        final JBList pathList = new JBList(pathListModel);
        pathList.getEmptyText().setText("No classpath entries defined");
        pathList.setDragEnabled(false);
        pathList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pathList.setName(PATH_LIST_NAME);
        return pathList;
    }

    private ActionListener getUserDefinedLibraryEnabledListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userDefinedLibraryEnabledChanged();
            }
        };
    }

    public void updateConfigurationWithChanges(XQueryDataSourceConfiguration currentConfiguration) {
        currentConfiguration.USER_DEFINED_LIBRARY_ENABLED = isUserDefinedLibraryEnabled();
        currentConfiguration.USER_DEFINED_LIBRARY_PATHS = getUserDefinedLibraryPaths();
    }

    public void setUpChangeListeners(final DataSourceConfigurationAggregatingPanel aggregatingPanel,
                                     final ConfigurationChangeListener listener) {
        userDefinedLibraryEnabled.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.changeApplied(aggregatingPanel
                        .getCurrentConfigurationState());
            }
        });
        pathListModel.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                listener.changeApplied(aggregatingPanel.getCurrentConfigurationState());
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                listener.changeApplied(aggregatingPanel.getCurrentConfigurationState());
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                // do nothing (can't happen - we only support adding and removing)
            }
        });
    }
}
