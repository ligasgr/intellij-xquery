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

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.intellij.xquery.gui.BaseGuiTest;
import org.intellij.xquery.gui.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.DataSourceMainConfigurationPanel;
import org.junit.Test;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.datasources.DataSourceMainConfigurationPanel.NAME_FIELD;
import static org.intellij.xquery.runner.ui.datasources.DataSourceMainConfigurationPanel.SET_AS_DEFAULT_BUTTON_NAME;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 01/11/13
 * Time: 19:49
 */
public class DataSourceMainConfigurationPanelTest extends BaseGuiTest {

    private static final String NAME_VALUE = "myName";
    private static final String NEW_NAME_VALUE = "newValue";
    private DataSourceMainConfigurationPanel panel;
    private DocumentListener nameChangedListener;
    private XQueryDataSourceConfiguration configuration;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        configuration = new XQueryDataSourceConfiguration(NAME_VALUE, XQueryDataSourceType.SAXON);
        configuration.DEFAULT = false;
        nameChangedListener = mock(DocumentListener.class);
        panel = new DataSourceMainConfigurationPanel(configuration, nameChangedListener);
        return new PanelTestingFrame(panel.getPanel());
    }

    @Test
    public void shouldShowMainPanel() {
        assertThat(panel.getPanel().isVisible(), is(true));
    }

    @Test
    public void shouldDisplayCorrectName() {
        window.textBox(NAME_FIELD).requireText(NAME_VALUE);
    }

    @Test
    public void shouldReturnCorrectName() {
        panel.getCurrentConfigurationState().NAME = NAME_VALUE;
    }

    @Test
    public void shouldEnabledSetAsDefaultButton() {
        window.button(SET_AS_DEFAULT_BUTTON_NAME).requireEnabled();
    }

    @Test
    public void shouldDisableSetAsDefaultButton() {
        window.cleanUp();
        PanelTestingFrame frame = GuiActionRunner.execute(new GuiQuery<PanelTestingFrame>() {
            protected PanelTestingFrame executeInEDT() {
                configuration.DEFAULT = true;
                panel = new DataSourceMainConfigurationPanel(configuration, nameChangedListener);
                return new PanelTestingFrame(panel.getPanel());
            }
        });
        FrameFixture anotherWindow = new FrameFixture(frame);

        anotherWindow.show();

        anotherWindow.button(SET_AS_DEFAULT_BUTTON_NAME).requireDisabled();
        anotherWindow.cleanUp();
    }

    @Test
    public void shouldDisableSetAsDefaultButtonAfterMarkingDataSourceAsDefault() {
        window.button(SET_AS_DEFAULT_BUTTON_NAME).click();

        window.button(SET_AS_DEFAULT_BUTTON_NAME).requireDisabled();
    }

    @Test
    public void shouldMarkDataSourceAsDefault() {
        window.button(SET_AS_DEFAULT_BUTTON_NAME).click();

        assertThat(panel.getCurrentConfigurationState().DEFAULT, is(true));
    }

    @Test
    public void shouldInvokeTypingInNameField() {
        window.textBox(NAME_FIELD).enterText(NEW_NAME_VALUE);

        verify(nameChangedListener, times(NEW_NAME_VALUE.length())).insertUpdate(any(DocumentEvent.class));
    }

    @Test
    public void shouldChangeNameAfterTypingInNameField() {
        window.textBox(NAME_FIELD).enterText(NEW_NAME_VALUE);

        assertThat(panel.getCurrentConfigurationState().NAME, is(NAME_VALUE + NEW_NAME_VALUE));
    }
}
