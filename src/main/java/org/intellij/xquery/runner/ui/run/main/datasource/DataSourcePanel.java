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

package org.intellij.xquery.runner.ui.run.main.datasource;

import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.PanelWithAnchor;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.intellij.xquery.runner.ui.datasources.DataSourceDetailsPanel;
import org.intellij.xquery.runner.ui.datasources.DataSourceListPanel;
import org.intellij.xquery.runner.ui.datasources.DataSourcesSettingsForm;
import org.jetbrains.annotations.Nullable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * User: ligasgr
 * Date: 19/11/13
 * Time: 22:57
 */
public class DataSourcePanel extends JPanel implements PanelWithAnchor {

    public static final String DATA_SOURCE_PANEL = "dataSourcePanel";
    public static final String CONFIGURE_DATA_SOURCES_BUTTON = "configureDataSourcesButton";
    private JButton configureDataSourcesButton;
    private LabeledComponent<JComboBox> dataSourceSelectorComponent;
    private DataSourceSelector dataSourceSelector;
    private JComponent anchor;

    public DataSourcePanel() {
        super(new BorderLayout());
        setName(DATA_SOURCE_PANEL);
        dataSourceSelectorComponent = new LabeledComponent<JComboBox>();
        dataSourceSelectorComponent.setLabelLocation("West");
        dataSourceSelectorComponent.setText("&Data source");
        dataSourceSelectorComponent.setComponent(new JComboBox());
        dataSourceSelector = getDataSourceSelector();
        add(dataSourceSelectorComponent, BorderLayout.CENTER);
        configureDataSourcesButton = new JButton("Configure");
        configureDataSourcesButton.setName(CONFIGURE_DATA_SOURCES_BUTTON);
        configureDataSourcesButton.setMnemonic(KeyEvent.VK_O);
        configureDataSourcesButton.addActionListener(getShowDialogActionListener());
        add(configureDataSourcesButton, BorderLayout.EAST);
    }

    public void init(XQueryRunConfiguration configuration) {
        dataSourceSelector.reset(configuration);
    }

    public void applyChanges(XQueryRunConfiguration configuration) {
        dataSourceSelector.applyTo(configuration);
    }

    @Override
    public JComponent getAnchor() {
        return anchor;
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        this.anchor = anchor;
    }

    protected DataSourceSelector getDataSourceSelector() {
        return new DataSourceSelector(dataSourceSelectorComponent.getComponent());
    }

    protected ActionListener getShowDialogActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataSourceDetailsPanel dataSourceDetailsPanel = new DataSourceDetailsPanel();
                DataSourceListPanel dataSourceListPanel = new DataSourceListPanel(dataSourceDetailsPanel);
                DataSourcesSettingsForm settingsForm = new DataSourcesSettingsForm(XQueryDataSourcesSettings
                        .getInstance()
                        .getDataSourceConfigurations(), dataSourceListPanel, dataSourceDetailsPanel);
                new DataSourcesDialog(DataSourcePanel.this, dataSourceSelector, settingsForm).show();
            }
        };
    }
}
