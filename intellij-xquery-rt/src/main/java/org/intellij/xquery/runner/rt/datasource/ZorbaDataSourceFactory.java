package org.intellij.xquery.runner.rt.datasource;

import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.zorbaxquery.api.xqj.ZorbaXQDataSource;

import javax.xml.xquery.XQDataSource;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 08:08
 */
public class ZorbaDataSourceFactory implements DataSourceFactory {
    @Override
    public XQDataSource getDataSource(XQueryRunConfig config) throws Exception {
        return new ZorbaXQDataSource();
    }
}
