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

package org.intellij.xquery.runner.state.datasources;

import com.intellij.configurationStore.DirectoryBasedStorage;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ligasgr
 * Date: 02/10/13
 * Time: 14:09
 */
@State(name = "XQueryDataSourcesSettings", storages = {@Storage("xquery.xml")})
public class XQueryDataSourcesSettings implements PersistentStateComponent<XQueryDataSourcesSettings>,
        ExportableApplicationComponent {

    public static final String PRESENTABLE_NAME = "XQuery data sources configuration";
    public static final String COMPONENT_NAME = "XQueryDataSourcesSettings";
    public static final String NO_DATA_SOURCE_FOUND_FOR_NAME_MESSAGE = "No data source found for name: ";
    private List<XQueryDataSourceConfiguration> dataSourceConfigurations = new
            ArrayList<XQueryDataSourceConfiguration>();

    public static XQueryDataSourcesSettings getInstance() {
        return ApplicationManager.getApplication().getComponent(XQueryDataSourcesSettings.class);
    }

    @Tag("list")
    @AbstractCollection(surroundWithTag = false)
    public List<XQueryDataSourceConfiguration> getDataSourceConfigurations() {
        return dataSourceConfigurations;
    }

    public void setDataSourceConfigurations(List<XQueryDataSourceConfiguration> dataSourceConfigurations) {
        this.dataSourceConfigurations = dataSourceConfigurations;
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public File[] getExportFiles() {
        return new File[]{PathManager.getOptionsFile("xquery.xml")};
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return PRESENTABLE_NAME;
    }

    @NotNull
    @Override
    public String getComponentName() {
        return COMPONENT_NAME;
    }

    @Nullable
    @Override
    public XQueryDataSourcesSettings getState() {
        return this;
    }

    @Override
    public void loadState(XQueryDataSourcesSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public XQueryDataSourceConfiguration getDataSourceConfigurationForName(String name) {
        for (XQueryDataSourceConfiguration dataSourceConfiguration : dataSourceConfigurations) {
            if (dataSourceConfiguration.NAME != null && dataSourceConfiguration.NAME.equals(name)) {
                return dataSourceConfiguration;
            }
        }
        return null;
    }

    public XQueryDataSourceConfiguration getDefaultDataSourceConfiguration() {
        for (XQueryDataSourceConfiguration dataSourceConfiguration : dataSourceConfigurations) {
            if (dataSourceConfiguration.DEFAULT) {
                return dataSourceConfiguration;
            }
        }
        return null;
    }
}
