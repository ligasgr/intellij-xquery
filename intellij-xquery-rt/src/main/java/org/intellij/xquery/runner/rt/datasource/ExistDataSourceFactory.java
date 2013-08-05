package org.intellij.xquery.runner.rt.datasource;

import net.xqj.exist.ExistXQDataSource;
import org.intellij.xquery.runner.rt.XQueryRunConfig;

import javax.xml.xquery.XQDataSource;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 08:06
 */
public class ExistDataSourceFactory implements DataSourceFactory {
    @Override
    public XQDataSource getDataSource(XQueryRunConfig config) throws Exception {
        return new ExistXQDataSource();
    }
}
