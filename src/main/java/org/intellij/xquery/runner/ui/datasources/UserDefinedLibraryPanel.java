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

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBCheckBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: ligasgr
 * Date: 06/10/13
 * Time: 19:05
 */
public class UserDefinedLibraryPanel {
    private JPanel mainPanel;
    private JBCheckBox userDefinedLibraryEnabled;
    private TextFieldWithBrowseButton userDefinedLibraryPath;

    public UserDefinedLibraryPanel() {
        userDefinedLibraryPath.addBrowseFolderListener("Choose file", null, null, getJarsOnlyFolderDescriptor());
        userDefinedLibraryEnabled.addActionListener(getUserDefinedLibraryEnabledListener());
    }

    public void init(boolean userDefinedLibraryEnabled, String userDefinedLibraryPath) {
        this.userDefinedLibraryEnabled.setSelected(userDefinedLibraryEnabled);
        this.userDefinedLibraryPath.setText(userDefinedLibraryPath);
        userDefinedLibraryEnabledChanged();
    }

    public boolean isUserDefinedLibraryEnabled() {
        return userDefinedLibraryEnabled.isSelected();
    }

    public String getUserDefinedLibraryPath() {
        return userDefinedLibraryPath.getText();
    }

    private void userDefinedLibraryEnabledChanged() {
        userDefinedLibraryPath.setEnabled(userDefinedLibraryEnabled.isSelected());
    }

    private FileChooserDescriptor getJarsOnlyFolderDescriptor() {
        return new FileChooserDescriptor(false, false, true, true, false, false);
    }

    private ActionListener getUserDefinedLibraryEnabledListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userDefinedLibraryEnabledChanged();
            }
        };
    }
}
