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

import com.intellij.openapi.ui.DialogWrapper;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.intellij.xquery.runner.ui.datasources.DataSourcesSettingsForm;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * User: ligasgr
 * Date: 06/10/13
 * Time: 21:59
 */
public class DataSourcesDialog extends DialogWrapper {

    private DataSourcesSettingsForm settingsForm;
    private UpdateDataSourceSelectionRunnable updateDataSourceSelectionRunnable;

    public DataSourcesDialog(Component parent, DataSourceSelector dataSourceConfigurationSelector,
                             DataSourcesSettingsForm settingsForm) {
        super(parent, false);
        this.settingsForm = settingsForm;
        updateDataSourceSelectionRunnable.setSettingsForm(this.settingsForm);
        updateDataSourceSelectionRunnable.setDataSourceSelector(dataSourceConfigurationSelector);
        setTitle("XQuery Data Sources");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return settingsForm.getFormComponent();
    }

    @Override
    protected void createDefaultActions() {
        super.createDefaultActions();
        updateDataSourceSelectionRunnable = new UpdateDataSourceSelectionRunnable();
        myOKAction = new MyOkAction(updateDataSourceSelectionRunnable);
    }

    protected XQueryDataSourcesSettings getDataSourceSettings() {
        return XQueryDataSourcesSettings.getInstance();
    }

    private class MyOkAction extends OkAction {

        private UpdateDataSourceSelectionRunnable updateDataSourceSelectionRunnable;

        private MyOkAction(UpdateDataSourceSelectionRunnable updateDataSourceSelectionRunnable) {
            this.updateDataSourceSelectionRunnable = updateDataSourceSelectionRunnable;
        }
        @Override
        protected void doAction(ActionEvent e) {
            super.doAction(e);
            getDataSourceSettings()
                    .setDataSourceConfigurations(settingsForm.getCurrentConfigurations());
            updateDataSourceSelectionRunnable.run();
        }

    }

    public static class UpdateDataSourceSelectionRunnable implements Runnable {
        private DataSourceSelector dataSourceSelector;
        private DataSourcesSettingsForm settingsForm;

        @Override
        public void run() {
            dataSourceSelector.setDataSources(settingsForm.getCurrentConfigurations());
            XQueryDataSourceConfiguration selectedDataSource = settingsForm.getSelectedDataSource();
            if (selectedDataSource != null) {
                dataSourceSelector.setSelectedDataSource(selectedDataSource);
            }
        }

        public void setDataSourceSelector(DataSourceSelector dataSourceSelector) {
            this.dataSourceSelector = dataSourceSelector;
        }

        public void setSettingsForm(DataSourcesSettingsForm settingsForm) {
            this.settingsForm = settingsForm;
        }
    }
}
