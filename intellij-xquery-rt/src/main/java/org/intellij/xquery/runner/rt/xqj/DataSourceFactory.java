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

package org.intellij.xquery.runner.rt.xqj;

import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.xqj.datasource.XQDataSourceFactory;

import javax.xml.xquery.XQDataSource;

public class DataSourceFactory {
    public static final String SERVER_NAME = "serverName";
    public static final String PORT = "port";
    public static final String DATABASE_NAME = "databaseName";
    private final XQueryRunConfig config;

    public DataSourceFactory(XQueryRunConfig config) {
        this.config = config;
    }

    public XQDataSource getDataSource() throws Exception {
        XQueryDataSourceType dataSourceType = config.getDataSourceType();
        XQDataSource dataSource = getXQDataSource(dataSourceType, config);
        if (dataSourceType.connectionPropertiesAreSupported()) {
            if (config.getHost() != null && config.getHost().length() > 0) {
                dataSource.setProperty(SERVER_NAME, config.getHost());
            }
            if (config.getPort() != null && config.getPort().length() > 0) {
                dataSource.setProperty(PORT, config.getPort());
            }
            if (config.getDatabaseName() != null && config.getDatabaseName().length() > 0) {
                dataSource.setProperty(DATABASE_NAME, config.getDatabaseName());
            }
        }
        return dataSource;
    }

    protected XQDataSource getXQDataSource(XQueryDataSourceType dataSourceType,
                                           XQueryRunConfig config) throws Exception {
        return getXQDataSourceFactory(dataSourceType).getXQDataSource(config);
    }

    private XQDataSourceFactory getXQDataSourceFactory(XQueryDataSourceType dataSourceType) throws Exception {
        Class<? extends XQDataSourceFactory> factoryClass = dataSourceType.getXQDataSourceFactoryClass();
        return factoryClass.newInstance();
    }
}