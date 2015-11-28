/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.actions;

import com.intellij.ide.fileTemplates.DefaultCreateFromTemplateHandler;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.ex.FileTypeManagerEx;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.XQueryFileType;
import org.intellij.xquery.settings.XQuerySettings;

import java.util.Map;

import static org.intellij.xquery.actions.XQueryFileTemplates.LIBRARY_MODULE;
import static org.intellij.xquery.actions.XQueryFileTemplates.MAIN_MODULE;

public class XQueryCreateFromTemplateHandler extends DefaultCreateFromTemplateHandler {
    public static final String NAME_WITHOUT_EXTENSION = "NAME_WITHOUT_EXTENSION";
    private Project project;

    @Override
    public PsiElement createFromTemplate(Project project, PsiDirectory directory, String fileName, FileTemplate template, String templateText, Map<String, Object> props) throws IncorrectOperationException {
        this.project = project;
        return super.createFromTemplate(project, directory, fileName, template, templateText, props);
    }

    @Override
    public boolean handlesTemplate(FileTemplate template) {
        FileType fileType = FileTypeManagerEx.getInstanceEx().getFileTypeByExtension(template.getExtension());
        return fileType.equals(XQueryFileType.INSTANCE);
    }

    @Override
    public void prepareProperties(Map<String, Object> props) {
        String name = (String) props.get(FileTemplate.ATTRIBUTE_NAME);
        if (name != null) {
            String nameWithoutExtension = name.indexOf(".") > 0 ? name.substring(0, name.lastIndexOf(".")) : name;
            props.put(NAME_WITHOUT_EXTENSION, nameWithoutExtension);
        }
    }

    @Override
    protected String checkAppendExtension(String fileName, FileTemplate template) {
        if (hasExtension(fileName)) {
            return fileName;
        }
        XQuerySettings settings = XQuerySettings.getInstance(project);
        String extension = getExtension(template, settings);
        return fileName + "." + extension;
    }

    private boolean hasExtension(String fileName) {
        return fileName.indexOf('.') > 0;
    }

    private String getExtension(FileTemplate template, XQuerySettings settings) {
        String extension = template.getExtension();
        if (MAIN_MODULE.equals(template.getName()) && settings.getDefaultMainModuleExtension() != null) {
            extension = settings.getDefaultMainModuleExtension();
        } else if (LIBRARY_MODULE.equals(template.getName()) && settings.getDefaultLibraryModuleExtension() != null) {
            extension = settings.getDefaultLibraryModuleExtension();
        }
        return extension;
    }
}
