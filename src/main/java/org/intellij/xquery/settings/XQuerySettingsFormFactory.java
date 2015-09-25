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

package org.intellij.xquery.settings;

import java.util.List;

import static org.intellij.xquery.XQueryFileType.ALL_EXTENSIONS_LIST;
import static org.intellij.xquery.XQueryFileType.DEFAULT_EXTENSION;

public class XQuerySettingsFormFactory {

    public XQuerySettingsForm getSettingsForm() {
        String defExt = DEFAULT_EXTENSION;
        List<String> allExt = ALL_EXTENSIONS_LIST;
        SettingsPanel fileExtensionsPanel = new DefaultFileExtensionsPanel(defExt, defExt, allExt);
        SettingsPanel otherOptionsPanel = new OtherOptionsPanel();
        return new XQuerySettingsForm(fileExtensionsPanel, otherOptionsPanel);
    }
}
