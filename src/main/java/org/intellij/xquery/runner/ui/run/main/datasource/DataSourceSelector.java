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

package org.intellij.xquery.runner.ui.run.main.datasource;

import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ComboboxSpeedSearch;
import com.intellij.ui.ListCellRendererWrapper;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;

import javax.swing.*;
import java.util.List;

import static org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings.getInstance;

/**
 * User: ligasgr
 * Date: 06/10/13
 * Time: 21:52
 */
public class DataSourceSelector {
    public static final String NO_DATA_SOURCE = "<no data source>";
    private final JComboBox dataSourceList;
    private ComboBoxCollectionListModel dataSourcesModel = new ComboBoxCollectionListModel();

    public DataSourceSelector(JComboBox dataSourceList) {
        this.dataSourceList = dataSourceList;
        new ComboboxSpeedSearch(this.dataSourceList) {
            protected String getElementText(Object element) {
                if (element instanceof XQueryDataSourceConfiguration) {
                    return ((XQueryDataSourceConfiguration) element).NAME;
                } else if (element == null) {
                    return NO_DATA_SOURCE;
                }
                return super.getElementText(element);
            }
        };
        this.dataSourceList.setModel(dataSourcesModel);
        this.dataSourceList.setRenderer(new ListCellRendererWrapper() {
            @Override
            public void customize(final JList list, final Object value, final int index, final boolean selected,
                                  final boolean hasFocus) {
                if (value instanceof XQueryDataSourceConfiguration) {
                    final XQueryDataSourceConfiguration dataSourceConfiguration = (XQueryDataSourceConfiguration) value;
                    setText(dataSourceConfiguration.NAME);
                } else if (value == null) {
                    setText(NO_DATA_SOURCE);
                }
            }
        });
    }

    public void applyTo(final XQueryRunConfiguration configuration) {
        Object selectedItem = this.dataSourceList.getSelectedItem();
        String name = null;
        if (selectedItem != null) {
            name = ((XQueryDataSourceConfiguration) selectedItem).NAME;
        }
        configuration.setDataSourceName(name);
    }

    public void reset(final XQueryRunConfiguration configuration) {
        boolean configNotFound = true;
        XQueryDataSourcesSettings dataSourcesSettings = getDataSourcesSettings();
        List<XQueryDataSourceConfiguration> dataSources = dataSourcesSettings.getDataSourceConfigurations();
        setDataSources(dataSources);
        for (XQueryDataSourceConfiguration cfg : dataSources) {
            if (cfg.NAME.equals(configuration.getDataSourceName())) {
                setSelectedDataSource(cfg);
                configNotFound = false;
                break;
            }
        }
        if (configNotFound) {
            setSelectedDataSource(dataSourcesSettings.getDefaultDataSourceConfiguration());
            applyTo(configuration);
        }
    }

    protected XQueryDataSourcesSettings getDataSourcesSettings() {
        return getInstance();
    }

    public void setDataSources(List<XQueryDataSourceConfiguration> dataSources) {
        dataSourcesModel.removeAll();
        dataSourcesModel.add(dataSources);
    }

    public void setSelectedDataSource(XQueryDataSourceConfiguration selectedDataSource) {
        dataSourceList.setSelectedItem(selectedDataSource);
    }

    private class ComboBoxCollectionListModel extends CollectionListModel<XQueryDataSourceConfiguration>
            implements ComboBoxModel<XQueryDataSourceConfiguration> {
        Object selectedItem;

        @Override
        public void setSelectedItem(Object anItem) {
            selectedItem = anItem;
        }

        @Override
        public Object getSelectedItem() {
            return selectedItem;
        }
    }
}
