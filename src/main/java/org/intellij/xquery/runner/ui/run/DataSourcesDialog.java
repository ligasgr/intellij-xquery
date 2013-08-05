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

package org.intellij.xquery.runner.ui.run;

import com.intellij.openapi.ui.DialogWrapper;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.intellij.xquery.runner.ui.datasources.DataSourcesSettingsForm;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: ligasgr
 * Date: 06/10/13
 * Time: 21:59
 */
public class DataSourcesDialog extends DialogWrapper {

    private DataSourcesSettingsForm settingsForm;
    private UpdateDataSourceSelectionRunnable updateDataSourceSelectionRunnable;

    protected DataSourcesDialog(JComponent parent, DataSourceSelector dataSourceConfigurationSelector) {
        super(parent, false);
        settingsForm = new DataSourcesSettingsForm(XQueryDataSourcesSettings.getInstance()
                .getDataSourceConfigurations());
        updateDataSourceSelectionRunnable.setSettingsForm(settingsForm);
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
        updateDataSourceSelectionRunnable = new UpdateDataSourceSelectionRunnable();
        myOKAction = new MyOkAction(updateDataSourceSelectionRunnable);
        myCancelAction = new MyCancelAction();
    }

    private class MyOkAction extends OkAction {
        private UpdateDataSourceSelectionRunnable updateDataSourceSelectionRunnable;

        private MyOkAction(UpdateDataSourceSelectionRunnable updateDataSourceSelectionRunnable) {
            this.updateDataSourceSelectionRunnable = updateDataSourceSelectionRunnable;
        }

        @Override
        protected void doAction(ActionEvent e) {
            XQueryDataSourcesSettings.getInstance()
                    .setDataSourceConfigurations(settingsForm.getCurrentConfigurations());
            updateDataSourceSelectionRunnable.run();
            super.doAction(e);
        }
    }

    private class MyCancelAction extends DialogWrapperAction {

        protected MyCancelAction() {
            super("Cancel");
        }

        @Override
        protected void doAction(ActionEvent e) {
            doCancelAction();
        }
    }

    public static class UpdateDataSourceSelectionRunnable implements Runnable {
        private DataSourceSelector dataSourceSelector;
        private DataSourcesSettingsForm settingsForm;

        @Override
        public void run() {
            dataSourceSelector.setDataSources(settingsForm.getCurrentConfigurations());
            if (settingsForm.getSelectedDataSource() != null) {
                dataSourceSelector.setSelectedDataSource(settingsForm.getSelectedDataSource());
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
