/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.settings;

import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.SortedComboBoxModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.List;

public class DefaultFileExtensionsPanel extends SettingsPanel {

    public static final String MAIN_MODULE_FILE_EXTENSION = "mainModuleFileExtension";
    public static final String LIBRARY_MODULE_FILE_EXTENSION = "libraryModuleFileExtension";
    private final LabeledComponent<JComboBox<String>> mainModuleFileExtension;
    private final LabeledComponent<JComboBox<String>> libraryModuleFileExtension;
    private final SortedComboBoxModel<String> mainModuleFileExtensionModel = UIUtils.<String>comboBoxModel();
    private final SortedComboBoxModel<String> libraryModuleFileExtensionModel = UIUtils.<String>comboBoxModel();
    private final String defaultMainModuleExtension;
    private final String defaultLibraryModuleExtension;

    public DefaultFileExtensionsPanel(String defaultMainModuleExtension, String defaultLibraryModuleExtension, List<String> allExtensions) {
        this.defaultMainModuleExtension = defaultMainModuleExtension;
        this.defaultLibraryModuleExtension = defaultLibraryModuleExtension;
        setLayout(new MigLayout("ins 0, gap 5, fill, flowy"));
        mainModuleFileExtension = UIUtils.<String>comboBox("&Main module", MAIN_MODULE_FILE_EXTENSION, mainModuleFileExtensionModel);
        libraryModuleFileExtension = UIUtils.<String>comboBox("&Library module", LIBRARY_MODULE_FILE_EXTENSION, libraryModuleFileExtensionModel);
        add(mainModuleFileExtension);
        add(libraryModuleFileExtension);

        setBorder(BorderFactory.createTitledBorder("Default new file extensions"));
        populateExtensionsList(mainModuleFileExtensionModel, defaultMainModuleExtension, allExtensions);
        populateExtensionsList(libraryModuleFileExtensionModel, defaultLibraryModuleExtension, allExtensions);
    }

    @Override
    public XQuerySettings updateSettings(XQuerySettings settings) {
        settings.setDefaultMainModuleExtension((String) mainModuleFileExtension.getComponent().getSelectedItem());
        settings.setDefaultLibraryModuleExtension((String) libraryModuleFileExtension.getComponent().getSelectedItem());
        return settings;
    }

    @Override
    public void updatePanel(XQuerySettings settings) {
        String mainFileExtension = settings.getDefaultMainModuleExtension() != null ? settings.getDefaultMainModuleExtension() : defaultMainModuleExtension;
        String libraryFileExtension = settings.getDefaultLibraryModuleExtension() != null ? settings.getDefaultLibraryModuleExtension() : defaultLibraryModuleExtension;
        mainModuleFileExtension.getComponent().setSelectedItem(mainFileExtension);
        libraryModuleFileExtension.getComponent().setSelectedItem(libraryFileExtension);
    }

    private void populateExtensionsList(SortedComboBoxModel<String> model, Object defaultItem, List<String> allItems) {
        for (String type : allItems) {
            model.add(type);
        }
        model.setSelectedItem(defaultItem);
    }
}
