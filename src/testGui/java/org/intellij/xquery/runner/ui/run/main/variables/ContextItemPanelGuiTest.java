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

package org.intellij.xquery.runner.ui.run.main.variables;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TestInputDialog;
import com.intellij.openapi.util.io.FileUtil;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryItemType;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.junit.Test;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.run.main.variables.ContextItemPanel.CONTEXT_ITEM_OPTIONS_PANEL;
import static org.intellij.xquery.runner.ui.run.main.variables.ContextItemPanel.CONTEXT_ITEM_PANEL;
import static org.intellij.xquery.runner.ui.run.main.variables.ContextItemPanel.CONTEXT_ITEM_TYPE;
import static org.intellij.xquery.runner.ui.run.main.variables.ContextItemPanel.EDITOR_CONTENT;
import static org.intellij.xquery.runner.ui.run.main.variables.ContextItemPanel.EDITOR_RADIO_BUTTON;
import static org.intellij.xquery.runner.ui.run.main.variables.ContextItemPanel.FILE_PATH;
import static org.intellij.xquery.runner.ui.run.main.variables.ContextItemPanel.FILE_RADIO_BUTTON;
import static org.intellij.xquery.runner.ui.run.main.variables.ContextItemPanel.SHOW_EDITOR_BUTTON;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * User: ligasgr
 * Date: 09/11/13
 * Time: 15:29
 */
public class ContextItemPanelGuiTest extends BaseGuiTest {
    private static final String CONTEXT_ITEM_PATH_BUTTON = "contextItemPathButton";
    private TestContextItemPanel panel;
    private static final String TYPE = "xs:string";

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new TestContextItemPanel(getProject());
        return new PanelTestingFrame(panel);
    }

    @Test
    public void shouldShowPanel() {
        window.panel(CONTEXT_ITEM_PANEL).requireVisible();
    }

    @Test
    public void shouldPopulateContextItemTypeSelectBoxWithSortedCorrectTypes() {
        List<String> sortedTypes = getSortedTypes();

        List<String> result = asList(window.comboBox(CONTEXT_ITEM_TYPE).contents());

        assertThat(result, is(equalTo(sortedTypes)));
    }

    @Test
    public void shouldDisableContextItemOptionsPanelChildren() {
        initPanel(false, null, null, false, null);

        window.panel(CONTEXT_ITEM_OPTIONS_PANEL).requireDisabled();
        window.comboBox(CONTEXT_ITEM_TYPE).requireDisabled();
        window.radioButton(EDITOR_RADIO_BUTTON).requireDisabled();
        window.panel(EDITOR_CONTENT).requireDisabled();
        window.radioButton(FILE_RADIO_BUTTON).requireDisabled();
        window.textBox(FILE_PATH).requireDisabled();
    }

    @Test
    public void shouldEnabledContextItemOptionsPanelChildrenWithoutFilePath() {
        initPanel(true, null, null, true, null);

        window.panel(CONTEXT_ITEM_OPTIONS_PANEL).requireEnabled();
        window.comboBox(CONTEXT_ITEM_TYPE).requireEnabled();
        window.radioButton(EDITOR_RADIO_BUTTON).requireEnabled();
        window.panel(EDITOR_CONTENT).requireEnabled();
        window.radioButton(FILE_RADIO_BUTTON).requireEnabled();
        window.textBox(FILE_PATH).requireDisabled();
    }

    @Test
    public void shouldEnabledContextItemOptionsPanelChildrenWithoutEditorContent() {
        initPanel(true, null, null, false, null);

        window.panel(CONTEXT_ITEM_OPTIONS_PANEL).requireEnabled();
        window.comboBox(CONTEXT_ITEM_TYPE).requireEnabled();
        window.radioButton(EDITOR_RADIO_BUTTON).requireEnabled();
        window.panel(EDITOR_CONTENT).requireDisabled();
        window.radioButton(FILE_RADIO_BUTTON).requireEnabled();
        window.textBox(FILE_PATH).requireEnabled();
    }

    @Test
    public void shouldPopulateContextItemEnabled() {
        initPanel(true, null, null, false, null);

        assertThat(panel.isContextItemEnabled(), is(true));

        initPanel(false, null, null, false, null);

        assertThat(panel.isContextItemEnabled(), is(false));
    }

    @Test
    public void shouldPopulateContextItemEditorContent() {
        String content = "text";

        initPanel(true, content, null, false, null);

        assertThat(panel.getContextItemEditorContent(), is(content));
    }

    @Test
    public void shouldPopulateContextItemPath() {
        String path = "C:\\path";

        initPanel(true, null, path, false, null);

        assertThat(panel.getContextItemPath(), is(FileUtil.toSystemIndependentName(path)));
    }

    @Test
    public void shouldPopulateContextItemFromEditorEnabled() {
        initPanel(true, null, null, true, null);

        assertThat(panel.isContextItemFromEditorEnabled(), is(true));

        initPanel(true, null, null, false, null);

        assertThat(panel.isContextItemFromEditorEnabled(), is(false));
    }

    @Test
    public void shouldPopulateContextItemType() {
        initPanel(true, null, null, false, TYPE);

        assertThat(panel.getContextItemType(), is(TYPE));
    }

    @Test
    public void shouldSelectPopulatedType() {
        initPanel(true, null, null, false, TYPE);

        window.comboBox(CONTEXT_ITEM_TYPE).requireSelection(TYPE);
    }

    @Test
    public void shouldSelectNothingIfPopulatedTypeIsNotAvailable() {
        String type = "anything";

        initPanel(true, null, null, false, type);

        window.comboBox(CONTEXT_ITEM_TYPE).requireNoSelection();
    }

    @Test
    public void shouldShowDialogAfterEditorContextButtonClickedAndSetValue() {
        final String myValue = "my Content";
        final boolean[] dialogShown = {false};
        initPanel(true, null, null, true, null);
        TestInputDialog oldDialog = Messages.setTestInputDialog(new TestInputDialog() {
            @Override
            public String show(String message) {
                dialogShown[0] = true;
                return myValue;
            }
        });

        window.button(SHOW_EDITOR_BUTTON).click();

        assertThat(dialogShown[0], is(true));
        assertThat(panel.getContextItemEditorContent(), is(myValue));
        Messages.setTestInputDialog(oldDialog);
    }

    @Test
    public void shouldInvokeActionRegisteredForPathFieldButton() {
        initPanel(true, null, null, false, null);

        window.button(CONTEXT_ITEM_PATH_BUTTON).click();

        assertThat(panel.pathButtonClicked, is(true));
    }

    @Test
    public void shouldReturnAnchorThatWasSet() {
        JComponent anchor = mock(JComponent.class);
        panel.setAnchor(anchor);

        assertThat(panel.getAnchor(), is(anchor));
    }

    private List<String> getSortedTypes() {
        List<String> sortedTypes = new ArrayList<String>(XQueryItemType.getAll());
        sort(sortedTypes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        return sortedTypes;
    }

    private class TestContextItemPanel extends ContextItemPanel {

        public boolean pathButtonClicked;

        public TestContextItemPanel(Project project) {
            super(project);
        }
        @Override
        protected void addPathFieldButtonListener() {
            contextItemPathField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pathButtonClicked = true;
                }
            });
            contextItemPathField.getButton().setName(CONTEXT_ITEM_PATH_BUTTON);
        }
    }

    private void initPanel(final boolean isEnabled, final String content, final String filePath,
                           final boolean contextItemFromEditorEnabled, final String type) {
        GuiActionRunner.execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                XQueryRunConfiguration configuration = mock(XQueryRunConfiguration.class);
                given(configuration.isContextItemEnabled()).willReturn(isEnabled);
                given(configuration.getContextItemText()).willReturn(content);
                given(configuration.getContextItemFile()).willReturn(filePath);
                given(configuration.isContextItemFromEditorEnabled()).willReturn(contextItemFromEditorEnabled);
                given(configuration.getContextItemType()).willReturn(type);
                panel.init(configuration);
            }
        });
    }
}
