/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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


import com.intellij.configurationStore.DirectoryBasedStorage;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Tag;
import org.intellij.xquery.XQueryFlavour;
import org.jetbrains.annotations.Nullable;


@State(name = "XQuerySettings", storages = {
        @Storage(value = "xquery.xml")
})
public class XQuerySettings implements PersistentStateComponent<XQuerySettings>, ModificationTracker {

    private String defaultMainModuleExtension;
    private String defaultLibraryModuleExtension;
    private XQueryFlavour flavour;
    private long modificationCount = 0;

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
        if (!this.equals(state)) {
            modificationCount++;
        }
        XmlSerializerUtil.copyBean(state, this);
    }

    @Tag("defaultMainModuleExtension")
    public String getDefaultMainModuleExtension() {
        return defaultMainModuleExtension;
    }

    public void setDefaultMainModuleExtension(String defaultMainModuleExtension) {
        this.defaultMainModuleExtension = defaultMainModuleExtension;
    }

    @Tag("defaultLibraryModuleExtension")
    public String getDefaultLibraryModuleExtension() {
        return defaultLibraryModuleExtension;
    }

    public void setDefaultLibraryModuleExtension(String defaultLibraryModuleExtension) {
        this.defaultLibraryModuleExtension = defaultLibraryModuleExtension;
    }

    @Tag("flavour")
    public XQueryFlavour getFlavour() {
        return flavour != null ? flavour : XQueryFlavour.STANDARD_30;
    }

    public void setFlavour(XQueryFlavour flavour) {
        this.flavour = flavour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XQuerySettings settings = (XQuerySettings) o;

        if (defaultLibraryModuleExtension != null ? !defaultLibraryModuleExtension.equals(settings.defaultLibraryModuleExtension) : settings.defaultLibraryModuleExtension != null)
            return false;
        if (defaultMainModuleExtension != null ? !defaultMainModuleExtension.equals(settings.defaultMainModuleExtension) : settings.defaultMainModuleExtension != null)
            return false;
        if (flavour != settings.flavour) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = defaultMainModuleExtension != null ? defaultMainModuleExtension.hashCode() : 0;
        result = 31 * result + (defaultLibraryModuleExtension != null ? defaultLibraryModuleExtension.hashCode() : 0);
        result = 31 * result + (flavour != null ? flavour.hashCode() : 0);
        return result;
    }

    @Override
    public long getModificationCount() {
        return modificationCount;
    }

    public boolean isMarklogicFlavour() {
        return XQueryFlavour.MARKLOGIC == getFlavour();
    }

    public boolean isFlavourWithVersion31() {
        return XQueryFlavour.BASEX == getFlavour() || XQueryFlavour.EXIST == getFlavour() || XQueryFlavour.SAXON == getFlavour() || XQueryFlavour.STANDARD_31 == getFlavour();
    }
}
