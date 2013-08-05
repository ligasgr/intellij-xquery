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

import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBCheckBox;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor;

/**
 * User: ligasgr
 * Date: 03/10/13
 * Time: 15:11
 */
public class ConfigurationFilePanel {

    private JPanel mainPanel;
    private JBCheckBox configurationEnabled;
    private TextFieldWithBrowseButton configFile;

    public ConfigurationFilePanel() {
        configFile.addBrowseFolderListener("Choose file", null, null, createSingleFileNoJarsDescriptor());
        configurationEnabled.addActionListener(getConfigEnabledListener());
    }

    public void init(XQueryDataSourceType type, boolean configurationEnabled, String configurationFile) {
        mainPanel.setVisible(type.configFileIsSupported());
        this.configurationEnabled.setSelected(configurationEnabled);
        configFile.setText(configurationFile);
        configurationEnabledChanged();
    }

    public boolean isConfigurationEnabled() {
        return configurationEnabled.isSelected();
    }

    public String getConfigFile() {
        return configFile.getText();
    }

    private void configurationEnabledChanged() {
        configFile.setEnabled(configurationEnabled.isSelected());
    }

    private ActionListener getConfigEnabledListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                configurationEnabledChanged();
            }
        };
    }
}
