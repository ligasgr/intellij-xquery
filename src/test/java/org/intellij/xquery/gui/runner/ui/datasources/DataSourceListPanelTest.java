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

package org.intellij.xquery.gui.runner.ui.datasources;

import com.intellij.ui.ExpandedItemListCellRendererWrapper;
import org.fest.swing.driver.BasicJListCellReader;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.JListFixture;
import org.intellij.xquery.gui.BaseGuiTest;
import org.intellij.xquery.gui.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.DataSourceConfigurationCellRenderer;
import org.intellij.xquery.runner.ui.datasources.DataSourceListPanel;
import org.junit.Test;

import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.datasources.DataSourceListPanel.DATA_SOURCE_LIST_PANEL;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 03/11/13
 * Time: 19:23
 */
public class DataSourceListPanelTest extends BaseGuiTest {
    private DataSourceListPanel panel;
    private JPanel dataSourceConfigurationPanel;
    private JPanel mainPanel;
    private XQueryDataSourceConfiguration defaultDataSource =
            new XQueryDataSourceConfiguration("default", XQueryDataSourceType.SAXON);
    private XQueryDataSourceConfiguration notDefaultDataSource =
            new XQueryDataSourceConfiguration("not_default", XQueryDataSourceType.MARKLOGIC);

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        mainPanel = mock(JPanel.class);
        dataSourceConfigurationPanel = mock(JPanel.class);
        panel = new DataSourceListPanel(dataSourceConfigurationPanel, mainPanel);
        return new PanelTestingFrame(panel);
    }

    @Test
    public void shouldShowPanelWhenNoDataSources() {
        panel.populateWithConfigurations(Collections.<XQueryDataSourceConfiguration>emptyList());

        window.panel(DATA_SOURCE_LIST_PANEL).requireVisible();
    }

    @Test
    public void shouldUseCorrectCellRenderer() {
        panel.populateWithConfigurations(Collections.<XQueryDataSourceConfiguration>emptyList());

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
        panel.populateWithConfigurations(Collections.<XQueryDataSourceConfiguration>emptyList());

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
    public void shouldCleanupDetailsPanel() {
        panel.populateWithConfigurations(asList(notDefaultDataSource, defaultDataSource));

        window.list().selectItem(1);

        verify(dataSourceConfigurationPanel, times(3)).removeAll();
        verify(dataSourceConfigurationPanel, times(3)).add(any(JPanel.class), eq(BorderLayout.NORTH));
    }

    private class DataSourceListCellReader extends BasicJListCellReader {
        @Override
        public String valueAt(JList list, int index) {
            return ((XQueryDataSourceConfiguration) list.getModel().getElementAt(index)).NAME;
        }
    }
}
