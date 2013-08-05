package org.intellij.xquery.runner.rt.datasource;

import org.intellij.xquery.runner.rt.XQueryRunConfig;

import javax.xml.xquery.XQDataSource;

/**
 * User: ligasgr
 * Date: 10/10/13
 * Time: 16:19
 */
public class XqjDataSourceFactory {
    public static XQDataSource getDataSource(XQueryRunConfig config) throws Exception {
        Class<? extends DataSourceFactory> factoryClass = config.getDataSourceType().getDataSourceFactoryClass();
        DataSourceFactory factory = factoryClass.newInstance();
        return factory.getDataSource(config);
    }
}
