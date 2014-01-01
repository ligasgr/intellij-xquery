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

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.TextAccessor;

import java.io.File;

/**
 * User: ligasgr
 * Date: 19/09/13
 * Time: 16:27
 */
public class ModuleSelector extends ComponentWithBrowseButton<EditorTextField> implements TextAccessor {

    public ModuleSelector(Project project) {
        super(createEditorTextField(project), null);
        addBrowseFolderListener(project);
    }

    protected void addBrowseFolderListener(Project project) {
        addBrowseFolderListener("Choose module", null, project, getDescriptor(), getTextComponentAccessor());
    }

    @Override
    public void setText(String text) {
        if (text == null) text = "";
        getChildComponent().setText(text);
    }

    @Override
    public String getText() {
        return getChildComponent().getText();
    }

    private static EditorTextField createEditorTextField(Project project) {
        return new EditorTextField("", project, StdFileTypes.PLAIN_TEXT);
    }

    private static FileChooserDescriptor getDescriptor() {
        return new ModuleFileDescriptor(new MainModuleTypeValidator());
    }

    private static TextComponentAccessor<EditorTextField> getTextComponentAccessor() {
        return new TextComponentAccessor<EditorTextField>() {
            @Override
            public String getText(EditorTextField component) {
                String text = component.getText();
                final VirtualFile file = VirtualFileManager.getInstance()
                                .findFileByUrl(VfsUtil.pathToUrl(text.replace(File.separatorChar, '/')));
                if (file != null && !file.isDirectory()) {
                    final VirtualFile parent = file.getParent();
                    assert parent != null;
                    return parent.getPresentableUrl();
                }
                return text;
            }

            @Override
            public void setText(EditorTextField component, String text) {
                component.setText(text);
            }
        };
    }
}
