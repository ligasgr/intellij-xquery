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

import com.intellij.openapi.fileChooser.FileTypeDescriptor;
import com.intellij.openapi.vfs.VirtualFile;

import static org.intellij.xquery.XQueryFileType.ALL_EXTENSIONS_LIST;

/**
 * User: ligasgr
 * Date: 12/11/13
 * Time: 14:14
 */
public class ModuleFileDescriptor extends FileTypeDescriptor {

    private ModuleTypeValidator moduleTypeValidator;

    public ModuleFileDescriptor(ModuleTypeValidator moduleTypeValidator) {
        super("XQuery module", ALL_EXTENSIONS_LIST.toArray(new String[ALL_EXTENSIONS_LIST.size()]));
        this.moduleTypeValidator = moduleTypeValidator;
    }

    @Override
    public boolean isFileSelectable(VirtualFile file) {
        return super.isFileSelectable(file) && moduleTypeValidator.isValidModuleType(file);
    }
}
