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

import net.java.openjdk.cacio.ctc.junit.CacioTestRunner;
import org.intellij.xquery.CheatingIdeaApplicationManager;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.intellij.xquery.runner.ui.datasources.DataSourcesSettingsForm;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * User: ligasgr
 * Date: 10/11/13
 * Time: 00:50
 */
@RunWith(CacioTestRunner.class)
@Ignore("Temporarily switching off until I have better idea how to rewrite dialog not to be too bound to idea")
public class DataSourcesDialogGuiTest {

    private JPanel parent;
    private DataSourceSelector selector;

    private DataSourcesSettingsForm settingsForm;
    private TestDataSourcesDialog dialog;
    private XQueryDataSourcesSettings dataSourceSettings;
    private Object showMonitor;

    @Before
    public void setUp() throws Exception {
        CheatingIdeaApplicationManager.removeApplication();
        selector = mock(DataSourceSelector.class);
        settingsForm = mock(DataSourcesSettingsForm.class);
        dataSourceSettings = mock(XQueryDataSourcesSettings.class);
        parent = mock(JPanel.class);
        given(parent.isShowing()).willReturn(true);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dialog = new TestDataSourcesDialog(parent, selector, settingsForm);
            }
        });
        showMonitor = new Object();
    }

    @After
    public void tearDown() throws Exception {
        CheatingIdeaApplicationManager.restoreApplication();
    }

    @Test
    public void shouldDelegateCreationOfCenterPanelToSettingsForm() throws InterruptedException {
        showDialog();

        clickCancelButton();

        performVerifications(new Runnable() {
            @Override
            public void run() {
                verify(settingsForm).getFormComponent();
            }
        });
    }

    @Test
    public void shouldUpdateDataSourceConfigurationsWithCurrentStateFromForm() throws InterruptedException {
        showDialog();

        clickOkButton();

        performVerifications(new Runnable() {
            @Override
            public void run() {
                verify(settingsForm, atLeastOnce()).getCurrentConfigurations();
                verify(dataSourceSettings).setDataSourceConfigurations(anyListOf(XQueryDataSourceConfiguration.class));
            }
        });
    }

    @Test
    public void shouldUpdateDataSourceSelectorWithCurrentConfigurations() throws InterruptedException {
        showDialog();

        clickOkButton();

        performVerifications(new Runnable() {
            @Override
            public void run() {
                verify(settingsForm, atLeast(2)).getCurrentConfigurations();
                verify(selector).setDataSources(anyListOf(XQueryDataSourceConfiguration.class));
            }
        });
    }

    @Test
    public void shouldUpdateCurrentlySelectedDataSourceWithSelectionFromDialog() throws InterruptedException {
        final XQueryDataSourceConfiguration cfg = new XQueryDataSourceConfiguration();
        given(settingsForm.getSelectedDataSource()).willReturn(cfg);
        showDialog();

        clickOkButton();

        performVerifications(new Runnable() {
            @Override
            public void run() {
                verify(settingsForm).getSelectedDataSource();
                verify(selector).setSelectedDataSource(cfg);
            }
        });
    }

    @Test
    public void shouldNotUpdateCurrentlySelectedDataSourceWhenNoSelectionInDialog() throws InterruptedException {
        given(settingsForm.getSelectedDataSource()).willReturn(null);
        showDialog();

        clickOkButton();

        performVerifications(new Runnable() {
            @Override
            public void run() {
                verify(selector).setDataSources(anyListOf(XQueryDataSourceConfiguration.class));
                verify(settingsForm).getSelectedDataSource();
                verifyNoMoreInteractions(selector);
            }
        });
    }

    @Test
    public void shouldDoNothingIfWasClosedWithClose() throws InterruptedException {
        showDialog();

        clickCancelButton();

        performVerifications(new Runnable() {
            @Override
            public void run() {
                verifyZeroInteractions(selector, dataSourceSettings);
            }
        });
    }

    private void clickOkButton() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Action okAction = dialog.getOKAction();
                ActionEvent event = new ActionEvent(DataSourcesDialogGuiTest.this, 0, "command");
                okAction.actionPerformed(event);
            }
        });
    }

    private void clickCancelButton() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Action cancelAction = dialog.getCancelAction();
                ActionEvent event = new ActionEvent(DataSourcesDialogGuiTest.this, 0, "command");
                cancelAction.actionPerformed(event);
            }
        });
    }

    private void performVerifications(Runnable verifications) throws InterruptedException {
        synchronized (showMonitor) {
            showMonitor.wait();
            verifications.run();
        }
    }

    private void showDialog() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                synchronized (showMonitor) {
                    dialog.show();
                    showMonitor.notify();
                }
            }
        });
    }

    private class TestDataSourcesDialog extends DataSourcesDialog {
        public TestDataSourcesDialog(JComponent parent, DataSourceSelector selector,
                                     DataSourcesSettingsForm settingsForm) {
            super(parent, selector, settingsForm);
        }

        @NotNull
        @Override
        public Action getOKAction() {
            return super.getOKAction();
        }

        @NotNull
        @Override
        public Action getCancelAction() {
            return super.getCancelAction();
        }

        @Override
        protected XQueryDataSourcesSettings getDataSourceSettings() {
            return dataSourceSettings;
        }
    }
}
