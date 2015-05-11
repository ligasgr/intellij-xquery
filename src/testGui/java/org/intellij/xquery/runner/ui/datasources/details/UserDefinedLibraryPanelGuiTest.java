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

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.TestActionEvent;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.CommonActionsPanel;
import com.intellij.util.Consumer;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.psi.XQueryElementFactory;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.intellij.ui.CommonActionsPanel.Buttons.ADD;
import static com.intellij.ui.CommonActionsPanel.Buttons.REMOVE;
import static java.util.Arrays.asList;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.datasources.details.UserDefinedLibraryPanel.PATH_LIST_NAME;
import static org.intellij.xquery.runner.ui.datasources.details.UserDefinedLibraryPanel.USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 26/10/13
 * Time: 21:36
 */
public class UserDefinedLibraryPanelGuiTest extends BaseGuiTest {

    private static final String PATH_JAR = "/my/path/to.jar";
    private static final boolean ENABLED = true;
    private static final boolean DISABLED = false;
    private UserDefinedLibraryPanel panel;
    boolean fileChooserUsedToChooseFiles;
    private DataSourceConfigurationAggregatingPanel aggregatingPanel;
    private ConfigurationChangeListener listener;
    private XQueryDataSourceConfiguration cfg;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        panel = new UserDefinedLibraryPanel() {
            @Override
            protected void chooseFilesWithFileChooser(FileChooserDescriptor descriptor, Consumer<List<VirtualFile>>
                    consumer) {
                fileChooserUsedToChooseFiles = true;
            }
        };
        return new PanelTestingFrame(panel.getMainPanel());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        cfg = new XQueryDataSourceConfiguration();
        aggregatingPanel = mock(DataSourceConfigurationAggregatingPanel.class);
        given(aggregatingPanel.getCurrentConfigurationState()).willReturn(cfg);
        listener = mock(ConfigurationChangeListener.class);
    }

    @Test
    public void shouldShowMainPanel() {
        setUpPanelWithUserLibrary(ENABLED);

        assertThat(panel.getMainPanel().isVisible(), is(true));
    }

    @Test
    public void shouldPopulateEnabledFieldAndDisablePathsPanel() {
        setUpPanelWithUserLibrary(DISABLED);

        window.checkBox(USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME).requireNotSelected();
        window.list(PATH_LIST_NAME).requireDisabled();
        assertThat(window.list(PATH_LIST_NAME).contents().length, is(0));
    }

    @Test
    public void shouldPopulateEnabledFieldAndEnablePathsPanel() {
        setUpPanelWithUserLibrary(ENABLED);

        window.checkBox(USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME).requireSelected();
        window.list(PATH_LIST_NAME).requireEnabled();
    }

    @Test
    public void shouldChangeEnabledFieldToUnchecked() {
        setUpPanelWithUserLibrary(ENABLED);

        window.checkBox(USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME).uncheck();

        assertThat(panel.isUserDefinedLibraryEnabled(), is(false));
        window.list(PATH_LIST_NAME).requireDisabled();
    }

    @Test
    public void shouldChangeEnabledFieldToChecked() {
        setUpPanelWithUserLibrary(DISABLED);

        window.checkBox(USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME).check();

        assertThat(panel.isUserDefinedLibraryEnabled(), is(true));
        window.list(PATH_LIST_NAME).requireEnabled();
    }

    @Test
    public void shouldReturnIfUserDefineLibraryIsEnabled() {
        setUpPanelWithUserLibrary(ENABLED);

        boolean result = panel.isUserDefinedLibraryEnabled();

        assertThat(result, is(true));
    }

    @Test
    public void shouldPopulatePathList() {
        cfg.USER_DEFINED_LIBRARY_PATHS = asList(PATH_JAR, PATH_JAR);
        setUpPanelWithUserLibrary(ENABLED);

        String[] contents = window.list(PATH_LIST_NAME).contents();
        assertThat(contents.length, is(2));
        assertThat(contents[0], is(PATH_JAR));
        assertThat(contents[1], is(PATH_JAR));
    }

    @Test
    public void shouldReturnAllPaths() {
        cfg.USER_DEFINED_LIBRARY_PATHS = asList(PATH_JAR, PATH_JAR);
        setUpPanelWithUserLibrary(ENABLED);

        List<String> result = panel.getUserDefinedLibraryPaths();

        assertThat(result.size(), is(2));
        assertThat(result.get(0), is(PATH_JAR));
        assertThat(result.get(1), is(PATH_JAR));
    }

    @Test
    public void shouldPopulatePathListWithChosenFile() {
        setUpPanelWithUserLibrary(ENABLED);
        XQueryFile file = createFile();
        panel.onFileChosen(file.getVirtualFile());

        String[] contents = window.list(PATH_LIST_NAME).contents();
        assertThat(contents.length, is(1));
        assertThat(contents[0], is(file.getVirtualFile().getPresentableUrl()));
    }

    @Test
    public void shouldShowAddPathDialogAfterActioningAddButton() {
        setUpPanelWithUserLibrary(ENABLED);
        final AnActionButton action = getAnActionButton(ADD);
        final AnActionEvent event = new TestActionEvent(action);

        simulateAction(action, event);

        assertThat(fileChooserUsedToChooseFiles, is(true));
    }

    @Test
    public void shouldRemoveSelectedPositionAfterActioningRemoveButton() {
        cfg.USER_DEFINED_LIBRARY_PATHS = asList(PATH_JAR);
        setUpPanelWithUserLibrary(ENABLED);
        final AnActionButton action = getAnActionButton(REMOVE);
        final AnActionEvent event = new TestActionEvent(action);
        panel.getPathList().setSelectedIndex(0);

        simulateAction(action, event);

        assertThat(window.list(PATH_LIST_NAME).contents().length, is(0));
    }

    @Test
    public void shouldInvokeChangeListenerAfterChangeOfEnabledField() {
        setUpPanelWithUserLibrary(ENABLED);
        panel.setUpChangeListeners(aggregatingPanel, listener);

        window.checkBox(USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME).uncheck();

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    @Test
    public void shouldInvokeChangeListenerAfterChangeOfPathListContents() {
        cfg.USER_DEFINED_LIBRARY_PATHS = asList(PATH_JAR);
        setUpPanelWithUserLibrary(ENABLED);
        panel.setUpChangeListeners(aggregatingPanel, listener);
        final AnActionButton action = getAnActionButton(REMOVE);
        final AnActionEvent event = new TestActionEvent(action);
        panel.getPathList().setSelectedIndex(0);

        simulateAction(action, event);

        verifyChangeListenerInvokedForCurrentConfigurationState();
    }

    private AnActionButton getAnActionButton(CommonActionsPanel.Buttons button) {
        return panel.getToolbarDecorator()
                .getActionsPanel()
                .getAnActionButton(button);
    }

    private void verifyChangeListenerInvokedForCurrentConfigurationState() {
        verify(aggregatingPanel, atLeast(1)).getCurrentConfigurationState();
        verify(listener, atLeast(1)).changeApplied(cfg);
    }

    private void setUpPanelWithUserLibrary(boolean enabled) {
        cfg.USER_DEFINED_LIBRARY_ENABLED = enabled;
        panel.init(cfg, aggregatingPanel, listener);
    }

    private void simulateAction(final AnActionButton action, final AnActionEvent event) {
        execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                action.actionPerformed(event);
            }
        });
    }

    private XQueryFile createFile() {
        return GuiActionRunner.execute(new GuiQuery<XQueryFile>() {
            protected XQueryFile executeInEDT() {
                return XQueryElementFactory.createPhysicalFile(getProject(), "()");
            }
        });
    }
}
