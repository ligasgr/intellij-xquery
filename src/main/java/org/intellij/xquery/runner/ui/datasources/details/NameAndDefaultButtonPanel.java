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

import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.DocumentAdapter;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: ligasgr
 * Date: 19/11/13
 * Time: 13:02
 */
public class NameAndDefaultButtonPanel {

    public static final String NAME_FIELD = "nameField";
    public static final String SET_AS_DEFAULT_BUTTON_NAME = "setAsDefaultButton";
    private JPanel mainPanel;
    private LabeledComponent<JTextField> name;
    private JButton setAsDefaultButton;
    private boolean isDefault;

    public void init(XQueryDataSourceConfiguration cfg, DataSourceConfigurationAggregatingPanel
            aggregatingPanel, ConfigurationChangeListener changeListener) {

        name.getComponent().setText(cfg.NAME);
        name.getComponent().setName(NAME_FIELD);
        setAsDefaultButton.setName(SET_AS_DEFAULT_BUTTON_NAME);
        isDefault = cfg.DEFAULT;
        setAsDefaultButton.setEnabled(! isDefault);
        setUpChangeListeners(aggregatingPanel, changeListener);
    }


    private void setUpChangeListeners(final DataSourceConfigurationAggregatingPanel aggregatingPanel,
                                      final ConfigurationChangeListener configurationChangeListener) {
        name.getComponent().getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent e) {
                configurationChangeListener.changeApplied(aggregatingPanel.getCurrentConfigurationState());
            }
        });

        setAsDefaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDefault = true;
                setAsDefaultButton.setEnabled(false);
                configurationChangeListener.changeApplied(aggregatingPanel.getCurrentConfigurationState());
            }
        });
    }

    public void updateConfigurationWithChanges(XQueryDataSourceConfiguration currentConfiguration) {
        currentConfiguration.NAME = name.getComponent().getText();
        currentConfiguration.DEFAULT = isDefault;
    }

    public Component getPanel() {
        return mainPanel;
    }
}
