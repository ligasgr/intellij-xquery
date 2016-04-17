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
import com.intellij.ui.ListCellRendererWrapper;
import com.intellij.ui.SortedComboBoxModel;
import net.miginfocom.swing.MigLayout;
import org.intellij.xquery.XQueryFlavour;

import javax.swing.*;
import java.util.Comparator;

public class OtherOptionsPanel extends SettingsPanel {

    private final LabeledComponent<JComboBox<XQueryFlavour>> xqueryFlavour;
    private final SortedComboBoxModel<XQueryFlavour> xqueryFlavourModel = comboBoxModel();

    public OtherOptionsPanel() {
        setLayout(new MigLayout("ins 0, gap 5, fill, flowy"));
        xqueryFlavour = UIUtils.<XQueryFlavour>comboBox("&XQuery flavour", "xqueryFlavour", xqueryFlavourModel);
        xqueryFlavour.getComponent().setRenderer(new XQueryFlavourRenderer());
        add(xqueryFlavour);
        setBorder(BorderFactory.createTitledBorder("Other options"));
        populateFlavourList();
    }

    private void populateFlavourList() {
        for (XQueryFlavour flavour : XQueryFlavour.values()) {
            xqueryFlavourModel.add(flavour);
        }
    }

    @Override
    public XQuerySettings updateSettings(XQuerySettings settings) {
        settings.setFlavour((XQueryFlavour) xqueryFlavour.getComponent().getSelectedItem());
        return settings;
    }

    @Override
    public void updatePanel(XQuerySettings settings) {
        xqueryFlavour.getComponent().setSelectedItem(settings.getFlavour());
    }

    private class XQueryFlavourRenderer extends ListCellRendererWrapper<XQueryFlavour> {

        @Override
        public void customize(JList list, XQueryFlavour value, int index, boolean selected, boolean hasFocus) {
            if (value != null) {
                setText(value.getPresentableName());
            }
        }
    }

    private SortedComboBoxModel<XQueryFlavour> comboBoxModel() {
        return new SortedComboBoxModel<>(new Comparator<XQueryFlavour>() {
            @Override
            public int compare(XQueryFlavour o1, XQueryFlavour o2) {
                return o1.getPresentableName().compareToIgnoreCase(o2.getPresentableName());
            }
        });
    }
}
