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

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.datasources.details.NameAndDefaultButtonPanel.NAME_FIELD;
import static org.intellij.xquery.runner.ui.datasources.details.NameAndDefaultButtonPanel.SET_AS_DEFAULT_BUTTON_NAME;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 19/11/13
 * Time: 13:13
 */
public class NameAndDefaultButtonPanelGuiTest extends BaseGuiTest {

    private static final String NAME_VALUE = "myName";
    private static final String NEW_NAME_VALUE = "newValue";
    private XQueryDataSourceConfiguration cfg;
    private NameAndDefaultButtonPanel panel;
    private DataSourceConfigurationAggregatingPanel aggregatingPanel;
    private ConfigurationChangeListener listener;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        cfg = new XQueryDataSourceConfiguration(NAME_VALUE, XQueryDataSourceType.SAXON);
        cfg.DEFAULT = false;
        panel = new NameAndDefaultButtonPanel();
        return new PanelTestingFrame(panel.getPanel());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        aggregatingPanel = mock(DataSourceConfigurationAggregatingPanel.class);
        given(aggregatingPanel.getCurrentConfigurationState()).willReturn(cfg);
        listener = mock(ConfigurationChangeListener.class);
    }

    @Test
    public void shouldShowMainPanel() {
        panel.init(cfg, aggregatingPanel, listener);

        assertThat(panel.getPanel().isVisible(), is(true));
    }

    @Test
    public void shouldDisplayCorrectName() {
        panel.init(cfg, aggregatingPanel, listener);

        window.textBox(NAME_FIELD).requireText(NAME_VALUE);
    }

    @Test
    public void shouldReturnCorrectName() {
        panel.init(cfg, aggregatingPanel, listener);

        panel.updateConfigurationWithChanges(cfg);

        assertThat(cfg.NAME, is(NAME_VALUE));
    }

    @Test
    public void shouldEnabledSetAsDefaultButton() {
        panel.init(cfg, aggregatingPanel, listener);

        window.button(SET_AS_DEFAULT_BUTTON_NAME).requireEnabled();
    }

    @Test
    public void shouldDisableSetAsDefaultButton() {
        window.cleanUp();
        PanelTestingFrame frame = GuiActionRunner.execute(new GuiQuery<PanelTestingFrame>() {
            protected PanelTestingFrame executeInEDT() {
                cfg.DEFAULT = true;
                panel = new NameAndDefaultButtonPanel();
                return new PanelTestingFrame(panel.getPanel());
            }
        });
        FrameFixture anotherWindow = new FrameFixture(frame);

        anotherWindow.show();
        panel.init(cfg, aggregatingPanel, listener);

        anotherWindow.button(SET_AS_DEFAULT_BUTTON_NAME).requireDisabled();
        anotherWindow.cleanUp();
    }

    @Test
    public void shouldDisableSetAsDefaultButtonAfterMarkingDataSourceAsDefault() {
        panel.init(cfg, aggregatingPanel, listener);

        window.button(SET_AS_DEFAULT_BUTTON_NAME).click();

        window.button(SET_AS_DEFAULT_BUTTON_NAME).requireDisabled();
    }

    @Test
    public void shouldMarkDataSourceAsDefault() {
        panel.init(cfg, aggregatingPanel, listener);

        window.button(SET_AS_DEFAULT_BUTTON_NAME).click();

        panel.updateConfigurationWithChanges(cfg);
        assertThat(cfg.DEFAULT, is(true));
    }

    @Test
    public void shouldInvokeTypingInNameField() {
        panel.init(cfg, aggregatingPanel, listener);

        window.textBox(NAME_FIELD).enterText(NEW_NAME_VALUE);

        verify(listener, times(NEW_NAME_VALUE.length())).changeApplied(isA(XQueryDataSourceConfiguration
                .class));
    }

    @Test
    public void shouldChangeNameAfterTypingInNameField() {
        cfg.NAME = "";
        panel.init(cfg, aggregatingPanel, listener);

        window.textBox(NAME_FIELD).enterText(NEW_NAME_VALUE);

        panel.updateConfigurationWithChanges(cfg);
        assertThat(cfg.NAME, is(NEW_NAME_VALUE));
    }
}
