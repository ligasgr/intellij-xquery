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

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.TestActionEvent;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.CommonActionsPanel;
import com.intellij.util.Consumer;
import org.fest.swing.edt.GuiTask;
import org.intellij.xquery.psi.XQueryElementFactory;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.gui.BaseGuiTest;
import org.intellij.xquery.gui.PanelTestingFrame;
import org.intellij.xquery.runner.ui.datasources.UserDefinedLibraryPanel;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.intellij.ui.CommonActionsPanel.Buttons.ADD;
import static com.intellij.ui.CommonActionsPanel.Buttons.REMOVE;
import static java.util.Arrays.asList;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.datasources.UserDefinedLibraryPanel.PATH_LIST_NAME;
import static org.intellij.xquery.runner.ui.datasources.UserDefinedLibraryPanel.USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 26/10/13
 * Time: 21:36
 */
public class UserDefinedLibraryPanelTest extends BaseGuiTest {

    private static final String PATH_JAR = "/my/path/to.jar";
    private UserDefinedLibraryPanel panel;
    boolean fileChooserUsedToChooseFiles;

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

    @Test
    public void shouldShowMainPanel() {
        panel.init(false, Collections.<String>emptyList());

        assertThat(panel.getMainPanel().isVisible(), is(true));
    }

    @Test
    public void shouldPopulateEnabledFieldAndDisablePathsPanel() {
        panel.init(false, Collections.<String>emptyList());

        window.checkBox(USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME).requireNotSelected();
        window.list(PATH_LIST_NAME).requireDisabled();
        assertThat(window.list(PATH_LIST_NAME).contents().length, is(0));
    }

    @Test
    public void shouldPopulateEnabledFieldAndEnablePathsPanel() {
        panel.init(true, Collections.<String>emptyList());

        window.checkBox(USER_DEFINED_LIBRARY_ENABLED_FIELD_NAME).requireSelected();
        window.list(PATH_LIST_NAME).requireEnabled();
    }

    @Test
    public void shouldReturnIfUserDefineLibraryIsEnabled() {
        panel.init(true, Collections.<String>emptyList());

        boolean result = panel.isUserDefinedLibraryEnabled();

        assertThat(result, is(true));
    }

    @Test
    public void shouldPopulatePathList() {
        panel.init(true, asList(PATH_JAR, PATH_JAR));

        String[] contents = window.list(PATH_LIST_NAME).contents();
        assertThat(contents.length, is(2));
        assertThat(contents[0], is(PATH_JAR));
        assertThat(contents[1], is(PATH_JAR));
    }

    @Test
    public void shouldReturnAllPaths() {
        panel.init(true, asList(PATH_JAR, PATH_JAR));

        List<String> result = panel.getUserDefinedLibraryPaths();

        assertThat(result.size(), is(2));
        assertThat(result.get(0), is(PATH_JAR));
        assertThat(result.get(1), is(PATH_JAR));
    }

    @Test
    public void shouldPopulatePathListWithChosenFile() {
        panel.init(true, Collections.<String>emptyList());
        XQueryFile file = XQueryElementFactory.createPhysicalFile(getProject(), "()");

        panel.onFileChosen(file.getVirtualFile());

        String[] contents = window.list(PATH_LIST_NAME).contents();
        assertThat(contents.length, is(1));
        assertThat(contents[0], is(file.getVirtualFile().getPresentableUrl()));
    }

    @Test
    public void shouldShowAddPathDialogAfterActioningAddButton() {
        panel.init(true, Collections.<String>emptyList());
        final AnActionButton action = getAnActionButton(ADD);
        final AnActionEvent event = new TestActionEvent(action);

        execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                action.actionPerformed(event);
            }
        });

        assertThat(fileChooserUsedToChooseFiles, is(true));
    }

    @Test
    public void shouldRemoveSelectedPositionAfterActioningRemoveButton() {
        panel.init(true, asList(PATH_JAR));
        final AnActionButton action = getAnActionButton(REMOVE);
        final AnActionEvent event = new TestActionEvent(action);
        panel.getPathList().setSelectedIndex(0);

        execute(new GuiTask() {
            @Override
            protected void executeInEDT() throws Throwable {
                action.actionPerformed(event);
            }
        });

        assertThat(window.list(PATH_LIST_NAME).contents().length, is(0));
    }

    private AnActionButton getAnActionButton(CommonActionsPanel.Buttons button) {
        return panel.getToolbarDecorator()
                .getActionsPanel()
                .getAnActionButton(button);
    }
}
