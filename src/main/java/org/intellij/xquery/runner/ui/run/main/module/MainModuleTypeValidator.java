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

package org.intellij.xquery.runner.ui.run.main.module;

import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

/**
 * User: ligasgr
 * Date: 12/11/13
 * Time: 15:05
 */
public class MainModuleTypeValidator implements ModuleTypeValidator {
    private static final String MODULE_NAMESPACE_DETECTION_STRING = "module namespace ";

    @Override
    public boolean isValidModuleType(VirtualFile file) {
        try {
            return isMainModuleBasedOnContent(file);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isMainModuleBasedOnContent(VirtualFile file) throws IOException {
        String contents = new String(file.contentsToByteArray());
        return contents.indexOf(MODULE_NAMESPACE_DETECTION_STRING) < 0;
    }
}
