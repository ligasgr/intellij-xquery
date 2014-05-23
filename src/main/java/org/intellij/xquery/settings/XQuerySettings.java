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

package org.intellij.xquery.settings;


import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.Nullable;


@State(name = "XQuerySettings", storages = {
        @Storage(id = "xqueryInProjectFile", file = StoragePathMacros.PROJECT_FILE),
        @Storage(id = "xqueryInDirectory", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/xquery.xml",
                scheme = StorageScheme.DIRECTORY_BASED)
})
public class XQuerySettings implements PersistentStateComponent<XQuerySettings> {

    private String flavour = "XQuery 3.0 Standard";

    public static XQuerySettings getInstance(Project project) {
        return ServiceManager.getService(project, XQuerySettings.class);
    }

    @Nullable
    @Override
    public XQuerySettings getState() {
        return this;
    }

    @Override
    public void loadState(XQuerySettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Tag("flavour")
    public String getFlavour() {
        return flavour;
    }
}
