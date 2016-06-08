/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainModuleTypeValidator implements ModuleTypeValidator {
    public static final Pattern LIBRARY_MODULE_NAMESPACE_PATTERN = Pattern.compile(".*^(?!import)\\s*module\\s+namespace.*", Pattern.DOTALL|Pattern.MULTILINE);

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
        Matcher matcher = LIBRARY_MODULE_NAMESPACE_PATTERN.matcher(contents);
        return !matcher.matches();
    }
}
