/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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
import org.intellij.xquery.XQueryFlavour;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OtherOptionsPanelTest extends BaseGuiTest {

    private OtherOptionsPanel panel;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new OtherOptionsPanel();
        return new PanelTestingFrame(panel);
    }

    @Test
    public void shouldPopulateComboBoxWithAllFlavours() {
        List<String> sortedFlavoursList = allDescriptions(XQueryFlavour.values());
        Collections.sort(sortedFlavoursList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        assertThat(asList(window.comboBox().contents()), is(equalTo(sortedFlavoursList)));
    }

    @Test
    public void shouldUpdateSettingsWithSelectedValue() {
        XQuerySettings settings = new XQuerySettings();

        window.comboBox().selectItem(XQueryFlavour.MARKLOGIC.getPresentableName());
        panel.updateSettings(settings);

        assertThat(settings.getFlavour(), is(XQueryFlavour.MARKLOGIC));
    }

    @Test
    public void shouldUpdatePanelWithDefaultSelectionWhenNoFlavourSetInSettings() {
        XQuerySettings settings = new XQuerySettings();

        panel.updatePanel(settings);

        window.comboBox().requireSelection(settings.getFlavour().getPresentableName());
    }

    @Test
    public void shouldUpdatePanelWithFlavourSetInSettings() {
        XQuerySettings settings = new XQuerySettings();
        settings.setFlavour(XQueryFlavour.MARKLOGIC);

        panel.updatePanel(settings);

        window.comboBox().requireSelection(XQueryFlavour.MARKLOGIC.getPresentableName());
    }

    private List<String> allDescriptions(XQueryFlavour[] values) {
        List<String> result = new ArrayList<String>();
        for (XQueryFlavour value : values) {
            result.add(value.getPresentableName());
        }
        return result;
    }
}
