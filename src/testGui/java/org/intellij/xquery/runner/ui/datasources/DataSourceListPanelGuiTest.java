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

package org.intellij.xquery.runner.ui.datasources;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.testFramework.TestActionEvent;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.CommonActionsPanel;
import com.intellij.ui.ExpandedItemListCellRendererWrapper;
import org.fest.swing.driver.BasicJListCellReader;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.JListFixture;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.junit.Test;

import javax.swing.JList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.intellij.ui.CommonActionsPanel.Buttons.ADD;
import static com.intellij.ui.CommonActionsPanel.Buttons.REMOVE;
import static java.util.Arrays.asList;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.datasources.DataSourceListPanel.DATA_SOURCE_LIST_PANEL;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 03/11/13
 * Time: 19:23
 */
public class DataSourceListPanelGuiTest extends BaseGuiTest {
    private TestingDataSourceListPanel panel;
    private DataSourceDetailsPanel dataSourceDetailsPanel;
    private XQueryDataSourceConfiguration defaultDataSource =
            new XQueryDataSourceConfiguration("default", XQueryDataSourceType.SAXON);
    private XQueryDataSourceConfiguration notDefaultDataSource =
            new XQueryDataSourceConfiguration("not_default", XQueryDataSourceType.MARKLOGIC);
    private boolean showPopupInvoked;
    private XQueryDataSourceTypeBasedActionExecutor addDataSourceActionExecutor;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        defaultDataSource.DEFAULT = true;
        dataSourceDetailsPanel = mock(DataSourceDetailsPanel.class);
        panel = new TestingDataSourceListPanel(dataSourceDetailsPanel);
        return new PanelTestingFrame(panel);
    }

    @Test
    public void shouldShowPanelWhenNoDataSources() {
        preparePanelWithEmptyList();

        window.panel(DATA_SOURCE_LIST_PANEL).requireVisible();
    }

    @Test
    public void shouldUseCorrectCellRenderer() {
        preparePanelWithEmptyList();

        assertThat(((ExpandedItemListCellRendererWrapper) window.list().component().getCellRenderer()).getWrappee()
                instanceof DataSourceConfigurationCellRenderer, is(true));
    }

    @Test
    public void shouldShowPanelWhenOneDataSourceAvailable() {
        panel.populateWithConfigurations(asList(defaultDataSource));

        window.panel(DATA_SOURCE_LIST_PANEL).requireVisible();
    }

    @Test
    public void shouldHaveNoSelectedItem() {
        preparePanelWithEmptyList();

        window.list().requireNoSelection();
    }

    @Test
    public void shouldHaveSelectedItemWhenOneDataSourceAvailable() {
        panel.populateWithConfigurations(asList(defaultDataSource));

        window.list().requireSelection(0);
    }

    @Test
    public void shouldHaveFirstItemSelectedItemWhenTwoDataSourcesAvailable() {
        panel.populateWithConfigurations(asList(notDefaultDataSource, defaultDataSource));

        window.list().requireSelection(0);
    }

    @Test
    public void shouldReturnSelectedDataSource() {
        panel.populateWithConfigurations(asList(notDefaultDataSource, defaultDataSource));

        GuiActionRunner.execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                assertThat(panel.getSelectedDataSource(), is(notDefaultDataSource));
            }
        });
    }

    @Test
    public void shouldDisplayDataSourceNameAsListEntry() {
        panel.populateWithConfigurations(asList(defaultDataSource));

        JListFixture result = window.list();
        result.cellReader(new DataSourceListCellReader());
        assertThat(result.item(0).value(), is(defaultDataSource.NAME));
    }

    @Test
    public void shouldReturnAllConfigurations() {
        panel.populateWithConfigurations(asList(notDefaultDataSource, defaultDataSource));

        Set<XQueryDataSourceConfiguration> result = new HashSet<XQueryDataSourceConfiguration>(panel
                .getCurrentConfigurations());

        assertThat(result.size(), is(2));
        assertThat(result.contains(notDefaultDataSource), is(true));
        assertThat(result.contains(defaultDataSource), is(true));
    }

    @Test
    public void shouldCleanupDetailsPanelAndDisplayNewConfigAfterSelectionChanged() {
        panel.populateWithConfigurations(asList(notDefaultDataSource, defaultDataSource));

        window.list().selectItem(1);

        verify(dataSourceDetailsPanel, times(2)).stopDisplayingDetails();
        verify(dataSourceDetailsPanel, times(1)).displayDetails(eq(defaultDataSource),
                isA(ConfigurationChangeListener.class));
    }

    @Test
    public void shouldShowAddPathDialogAfterActioningAddButton() {
        preparePanelWithEmptyList();
        clickAdd();

        assertThat(showPopupInvoked, is(true));
    }

    @Test
    public void shouldAddNewDataSourceAfterExecutionInvoked() {
        panel.populateWithConfigurations(asList(notDefaultDataSource));
        clickAdd();

        GuiActionRunner.execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                addDataSourceActionExecutor.execute(XQueryDataSourceType.SAXON);
            }
        });

        JListFixture list = window.list().cellReader(new DataSourceListCellReader());
        list.requireSelection(1);
        assertThat(list.contents()[1], is(XQueryDataSourceType.SAXON.getPresentableName()));
    }

    @Test
    public void shouldRemoveEntryAfterRemoveButtonClicked() {
        panel.populateWithConfigurations(asList(defaultDataSource));
        final AnActionButton action = getAnActionButton(REMOVE);
        final AnActionEvent event = new TestActionEvent(action);

        execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                action.actionPerformed(event);
            }
        });

        window.list().requireNoSelection();
        assertThat(window.list().contents().length, is(0));
    }

    @Test
    public void shouldOnlySwapObjectsInModelIfUpdatedWithNotDefault() {
        panel.populateWithConfigurations(asList(defaultDataSource));
        window.list().selectItem(0);

        execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                panel.updateCurrentlySelectedItemWithData(notDefaultDataSource);

                assertThat(panel.getSelectedDataSource(), is(notDefaultDataSource));
                assertThat(panel.getSelectedDataSource().DEFAULT, is(false));
            }
        });
    }

    @Test
    public void shouldSwapObjectsInModelAndMarkOthersNotDefaultIfUpdatedWithDefault() {
        panel.populateWithConfigurations(asList(defaultDataSource, defaultDataSource));
        window.list().selectItem(0);

        execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                panel.updateCurrentlySelectedItemWithData(defaultDataSource);
            }
        });

        execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                assertThat(panel.getSelectedDataSource(), is(defaultDataSource));
                assertThat(panel.getSelectedDataSource().DEFAULT, is(true));
                assertThat(panel.getCurrentConfigurations().get(1).DEFAULT, is(false));
            }
        });
    }

    private void preparePanelWithEmptyList() {
        panel.populateWithConfigurations(Collections.<XQueryDataSourceConfiguration>emptyList());
    }

    private void clickAdd() {
        final AnActionButton action = getAnActionButton(ADD);
        final AnActionEvent event = new TestActionEvent(action);

        execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                action.actionPerformed(event);
            }
        });
    }

    private AnActionButton getAnActionButton(CommonActionsPanel.Buttons button) {
        return panel.getToolbarDecorator()
                .getActionsPanel()
                .getAnActionButton(button);
    }

    private class DataSourceListCellReader extends BasicJListCellReader {
        @Override
        public String valueAt(JList list, int index) {
            return ((XQueryDataSourceConfiguration) list.getModel().getElementAt(index)).NAME;
        }
    }

    private class TestingDataSourceListPanel extends DataSourceListPanel {
        public TestingDataSourceListPanel(DataSourceDetailsPanel dataSourceDetailsPanel) {
            super(dataSourceDetailsPanel);
        }

        @Override
        protected void showAddDataSourcePopupWithActionExecutor(XQueryDataSourceTypeBasedActionExecutor
                                                                        addDataSourceActionExecutor) {
            showPopupInvoked = true;
            DataSourceListPanelGuiTest.this.addDataSourceActionExecutor = addDataSourceActionExecutor;
        }

        @Override
        public void updateCurrentlySelectedItemWithData(XQueryDataSourceConfiguration
                                                                currentConfigurationState) {
            super.updateCurrentlySelectedItemWithData(currentConfigurationState);
        }
    }
}
