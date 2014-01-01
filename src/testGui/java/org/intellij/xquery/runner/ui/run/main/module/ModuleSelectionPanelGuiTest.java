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

package org.intellij.xquery.runner.ui.run.main.module;

import com.intellij.openapi.project.Project;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.junit.Test;

import javax.swing.JComponent;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.run.main.module.ModuleSelectionPanel.MODULE_SELECTION_PANEL;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 21/11/13
 * Time: 14:12
 */
public class ModuleSelectionPanelGuiTest extends BaseGuiTest {
    private static final String MAIN_FILE_NAME = "name.xq";
    private TestModuleSelectionPanel panel;
    private TestModuleSelector moduleSelector;
    private XQueryRunConfiguration configuration;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        configuration = mock(XQueryRunConfiguration.class);
        moduleSelector = new TestModuleSelector(getProject());
        panel = new TestModuleSelectionPanel(getProject());
        return new PanelTestingFrame(panel);
    }

    @Test
    public void shouldShowPanel() {
        window.panel(MODULE_SELECTION_PANEL).requireVisible();
    }

    @Test
    public void shouldSetTextInModuleSelector() {
        given(configuration.getMainFileName()).willReturn(MAIN_FILE_NAME);

        panel.init(configuration);

        assertThat(moduleSelector.setTextInvoked, is(true));
        assertThat(moduleSelector.setTextValue, is(MAIN_FILE_NAME));
    }

    @Test
    public void shouldGetTextFromModuleSelector() {
        moduleSelector.getTextValue = MAIN_FILE_NAME;

        panel.applyChanges(configuration);

        assertThat(moduleSelector.getTextInvoked, is(true));
        verify(configuration).setMainFileName(MAIN_FILE_NAME);
    }

    @Test
    public void shouldReturnAnchorThatWasSet() {
        JComponent anchor = mock(JComponent.class);
        panel.setAnchor(anchor);

        assertThat(panel.getAnchor(), is(anchor));
    }

    private class TestModuleSelectionPanel extends ModuleSelectionPanel {
        public TestModuleSelectionPanel(Project project) {
            super(project);
        }

        @Override
        protected ModuleSelector getModuleSelector(Project project) {
            return moduleSelector;
        }
    }

    private class TestModuleSelector extends ModuleSelector {
        public boolean setTextInvoked;
        public boolean getTextInvoked;
        public String setTextValue;
        public String getTextValue;

        public TestModuleSelector(Project project) {
            super(project);
        }

        @Override
        protected void addBrowseFolderListener(Project project) {

        }

        @Override
        public void setText(String text) {
            setTextInvoked = true;
            setTextValue = text;
        }

        @Override
        public String getText() {
            getTextInvoked = true;
            return getTextValue;
        }
    }
}
