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

package org.intellij.xquery.runner.rt;

import org.intellij.xquery.runner.rt.datasource.XqjDataSourceFactory;
import org.intellij.xquery.runner.rt.variable.XqjBinderFactory;

import javax.xml.namespace.QName;
import javax.xml.xquery.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * User: ligasgr
 * Date: 15/08/13
 * Time: 13:56
 */
public class XQueryRunnerApp {

    public static void main(String[] args) throws Exception {
        XQueryRunConfig config = new XQueryRunConfig(readFile(args[0]));
        XQueryDataSourceType dataSourceType = config.getDataSourceType();
        XQDataSource xqs = getDataSource(config, dataSourceType);
        XQConnection connection = null;
        try {
            connection = getConnection(xqs, config);
            XQPreparedExpression preparedExpression = getExpression(config, connection);
            XQResultSequence rs = preparedExpression.executeQuery();
            rs.writeSequence(System.out, getOutputMethodProperties());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static Properties getOutputMethodProperties() {
        Properties props = new Properties();
        props.setProperty("method", "xml");
        return props;
    }

    private static XQPreparedExpression getExpression(XQueryRunConfig config,
                                                      XQConnection connection) throws Exception {
        XQPreparedExpression preparedExpression = connection.prepareExpression(new FileInputStream(config.getMainFile
                ()));
        prepareContextItem(config, connection, preparedExpression);
        prepareVariables(config, connection, preparedExpression);
        return preparedExpression;
    }

    private static void prepareVariables(XQueryRunConfig config, XQConnection connection, XQPreparedExpression
            preparedExpression) throws Exception {
        for (XQueryRunnerVariable variable : config.getVariables()) {
            XqjBinderFactory.bind(preparedExpression, connection, getName(variable.NAME, variable.NAMESPACE),
                    variable.VALUE,
                    variable.TYPE);
        }
    }

    private static QName getName(String name, String namespace) {
        String[] parts = name.split(":");
        if (parts.length < 1 || parts.length > 2) {
            throw new RuntimeException("Variable name '" + name + "' is invalid");
        }
        String namespacePrefix = null;
        String localPart = parts[parts.length - 1];
        if (parts.length == 2) {
            namespacePrefix = parts[0];
        }
        if (namespace != null && namespace.length() > 0) {
            if (namespacePrefix != null) {
                return new QName(namespace, localPart, namespacePrefix);
            } else {
                return new QName(namespace, localPart);
            }
        }
        if (namespacePrefix != null) {
            return new QName(localPart, namespacePrefix);
        } else {
            return new QName(localPart);
        }
    }

    private static void prepareContextItem(XQueryRunConfig config, XQConnection connection,
                                           XQPreparedExpression preparedExpression) throws
            Exception {
        if (config.isContextItemEnabled()) {
            String contextItemValue = config.isContextItemFromEditorEnabled() ? config.getContextItemText() :
                    readFile(config.getContextItemFile());
            XqjBinderFactory.bind(preparedExpression, connection, XQConstants.CONTEXT_ITEM, contextItemValue,
                    config.getContextItemType());
        }
    }

    private static XQConnection getConnection(XQDataSource dataSource, XQueryRunConfig config) throws Exception {
        XQConnection connection;
        if (config.getDataSourceType().connectionPropertiesAreSupported() && config.getUsername() != null && config
                .getUsername().length() > 0) {
            connection = dataSource.getConnection(config.getUsername(), config.getPassword());
        } else {
            connection = dataSource.getConnection();
        }
        return connection;
    }

    private static XQDataSource getDataSource(XQueryRunConfig config, XQueryDataSourceType dataSourceType) throws
            Exception {
        XQDataSource dataSource = XqjDataSourceFactory.getDataSource(config);
        if (dataSourceType.connectionPropertiesAreSupported()) {
            dataSource.setProperty("serverName", config.getHost());
            if (config.getPort() != null && config.getPort().length() > 0)
                dataSource.setProperty("port", config.getPort());
            if (config.getDatabaseName() != null && config.getDatabaseName().length() > 0)
                dataSource.setProperty("databaseName", config.getDatabaseName());
        }

        return dataSource;
    }

    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        return stringBuilder.toString();
    }
}