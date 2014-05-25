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

package org.intellij.xquery.settings;

import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.XQueryFileType.ALL_EXTENSIONS_LIST;
import static org.intellij.xquery.XQueryFileType.DEFAULT_EXTENSION;
import static org.intellij.xquery.settings.DefaultFileExtensionsPanel.LIBRARY_MODULE_FILE_EXTENSION;
import static org.intellij.xquery.settings.DefaultFileExtensionsPanel.MAIN_MODULE_FILE_EXTENSION;
import static org.junit.Assert.assertThat;

public class DefaultFileExtensionsPanelTest extends BaseGuiTest {

    public static final String EXAMPLE_EXTENSION_1 = ALL_EXTENSIONS_LIST.get(3);
    public static final String EXAMPLE_EXTENSION_2 = ALL_EXTENSIONS_LIST.get(4);
    private DefaultFileExtensionsPanel panel;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new DefaultFileExtensionsPanel(DEFAULT_EXTENSION, DEFAULT_EXTENSION, ALL_EXTENSIONS_LIST);
        return new PanelTestingFrame(panel);
    }

    @Test
    public void shouldHaveVisibleComboBoxes() {
        window.comboBox(MAIN_MODULE_FILE_EXTENSION).requireVisible();
        window.comboBox(LIBRARY_MODULE_FILE_EXTENSION).requireVisible();
    }

    @Test
    public void shouldHaveBothBoxesPopulatedWithAllExtensionsSorted() {
        List<String> sortedExtensionsList = new ArrayList<String>(ALL_EXTENSIONS_LIST);
        Collections.sort(sortedExtensionsList);

        assertThat(asList(window.comboBox(MAIN_MODULE_FILE_EXTENSION).contents()), is(equalTo(sortedExtensionsList)));
        assertThat(asList(window.comboBox(LIBRARY_MODULE_FILE_EXTENSION).contents()), is(equalTo(sortedExtensionsList)));
    }

    @Test
    public void shouldHaveDefaultExtensionSelectedByDefault() {
        window.comboBox(MAIN_MODULE_FILE_EXTENSION).requireSelection(DEFAULT_EXTENSION);
        window.comboBox(LIBRARY_MODULE_FILE_EXTENSION).requireSelection(DEFAULT_EXTENSION);
    }

    @Test
    public void shouldUpdateExtensionsAfterChangingSelection() {
        XQuerySettings settings = new XQuerySettings();
        window.comboBox(MAIN_MODULE_FILE_EXTENSION).selectItem(EXAMPLE_EXTENSION_1);
        window.comboBox(LIBRARY_MODULE_FILE_EXTENSION).selectItem(EXAMPLE_EXTENSION_2);

        panel.updateSettings(settings);

        assertThat(settings.getDefaultMainModuleExtension(), is(EXAMPLE_EXTENSION_1));
        assertThat(settings.getDefaultLibraryModuleExtension(), is(EXAMPLE_EXTENSION_2));
    }

    @Test
    public void shouldSetDefaultExtensionsOnPanelIfNoneSelectedInSettings() {
        XQuerySettings settings = new XQuerySettings();

        panel.updatePanel(settings);

        window.comboBox(MAIN_MODULE_FILE_EXTENSION).requireSelection(DEFAULT_EXTENSION);
        window.comboBox(LIBRARY_MODULE_FILE_EXTENSION).requireSelection(DEFAULT_EXTENSION);
    }

    @Test
    public void shouldSetExtensionsOnPanelBasedOnSettings() {
        XQuerySettings settings = new XQuerySettings();
        settings.setDefaultMainModuleExtension(EXAMPLE_EXTENSION_1);
        settings.setDefaultLibraryModuleExtension(EXAMPLE_EXTENSION_2);

        panel.updatePanel(settings);

        window.comboBox(MAIN_MODULE_FILE_EXTENSION).requireSelection(EXAMPLE_EXTENSION_1);
        window.comboBox(LIBRARY_MODULE_FILE_EXTENSION).requireSelection(EXAMPLE_EXTENSION_2);
    }
}
