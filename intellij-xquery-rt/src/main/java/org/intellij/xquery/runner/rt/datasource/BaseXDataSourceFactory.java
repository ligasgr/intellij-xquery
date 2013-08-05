package org.intellij.xquery.runner.rt.datasource;

import net.xqj.basex.BaseXXQDataSource;
import org.intellij.xquery.runner.rt.XQueryRunConfig;

import javax.xml.xquery.XQDataSource;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 08:07
 */
public class BaseXDataSourceFactory implements DataSourceFactory {
    @Override
    public XQDataSource getDataSource(XQueryRunConfig config) throws Exception {
        return new BaseXXQDataSource();
    }
}
