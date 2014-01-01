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

import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.junit.Test;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.intellij.xquery.runner.ui.run.main.datasource.DataSourcePanel.CONFIGURE_DATA_SOURCES_BUTTON;
import static org.intellij.xquery.runner.ui.run.main.datasource.DataSourcePanel.DATA_SOURCE_PANEL;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 19/11/13
 * Time: 23:07
 */
public class DataSourcePanelGuiTest extends BaseGuiTest {
    private TestDataSourcePanel panel;
    private ActionListener actionListener;
    private DataSourceSelector dataSourceSelector;
    private XQueryRunConfiguration configuration;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        actionListener = mock(ActionListener.class);
        dataSourceSelector = mock(DataSourceSelector.class);
        configuration = mock(XQueryRunConfiguration.class);
        panel = new TestDataSourcePanel();
        return new PanelTestingFrame(panel);
    }

    @Test
    public void shouldShowPanel() {
        window.panel(DATA_SOURCE_PANEL).requireVisible();
    }

    @Test
    public void shouldDisplayEnabledComboBoxForDataSourceSelection() {
        window.comboBox().requireVisible().requireEnabled();
    }

    @Test
    public void shouldDisplayEnabledConfigurationButton() {
        window.button(CONFIGURE_DATA_SOURCES_BUTTON).requireVisible().requireEnabled();
    }

    @Test
    public void shouldInvokeActionListenerAfterClickingConfigurationButton() {
        window.button(CONFIGURE_DATA_SOURCES_BUTTON).click();

        verify(actionListener).actionPerformed(isA(ActionEvent.class));
    }

    @Test
    public void shouldDelegateInitToDataSourceSelector() {
        panel.init(configuration);

        verify(dataSourceSelector).reset(configuration);
    }

    @Test
    public void shouldDelegateApplyChangesToDataSourceSelector() {
        panel.applyChanges(configuration);

        verify(dataSourceSelector).applyTo(configuration);
    }

    @Test
    public void shouldReturnTheSameAnchorThatWasSet() {
        JComponent anchor = mock(JComponent.class);

        panel.setAnchor(anchor);

        assertThat(panel.getAnchor(), is(sameInstance(anchor)));
    }

    private class TestDataSourcePanel extends DataSourcePanel {
        @Override
        protected DataSourceSelector getDataSourceSelector() {
            return dataSourceSelector;
        }

        @Override
        protected ActionListener getShowDialogActionListener() {
            return actionListener;
        }
    }
}
