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

import org.intellij.xquery.runner.rt.XQueryRunConfig;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;

public class ConnectionFactory {
    private final XQueryRunConfig config;

    public ConnectionFactory(XQueryRunConfig config) {
        this.config = config;
    }

    public XQConnection getConnection(XQDataSource dataSource) throws Exception {
        XQConnection connection;
        if (config.getDataSourceType().connectionPropertiesAreSupported()
                && config.getUsername() != null && config.getUsername().length() > 0) {
            connection = dataSource.getConnection(config.getUsername(), config.getPassword());
        } else {
            connection = dataSource.getConnection();
        }
        return connection;
    }
}