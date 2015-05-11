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

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.junit.Test;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 09/11/13
 * Time: 02:03
 */
public class DataSourceDetailsPanelGuiTest extends BaseGuiTest {
    private DataSourceDetailsPanel panel;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = spy(new DataSourceDetailsPanel());
        return new PanelTestingFrame(panel);
    }

    @Test
    public void shouldRemoveAllChildrenAfterStopDisplayingDetailsInvoked() {
        panel.add(new JButton());

        panel.stopDisplayingDetails();

        assertThat(panel.getComponents().length, is(0));
    }

    @Test
    public void shouldAddNewPanelAfterDisplayDetailsInvoked() {
        final XQueryDataSourceConfiguration cfg = new XQueryDataSourceConfiguration("name", XQueryDataSourceType.SAXON);
        final ConfigurationChangeListener listener = mock(ConfigurationChangeListener.class);

        GuiActionRunner.execute(new GuiTask() {
            protected void executeInEDT() {
                panel.displayDetails(cfg, listener);

            }
        });
        assertThat(panel.getComponents().length, is(1));
        verify(panel).add(isA(JPanel.class), eq(BorderLayout.NORTH));
    }
}
