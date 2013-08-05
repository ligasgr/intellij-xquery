package org.intellij.xquery.runner.rt.datasource;

import org.intellij.xquery.runner.rt.XQueryRunConfig;

import javax.xml.xquery.XQDataSource;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 08:03
 */
public interface DataSourceFactory {
    XQDataSource getDataSource(XQueryRunConfig config) throws Exception;
}
