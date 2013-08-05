package org.intellij.xquery.runner.rt.datasource;

import net.xqj.sedna.SednaXQDataSource;
import org.intellij.xquery.runner.rt.XQueryRunConfig;

import javax.xml.xquery.XQDataSource;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 08:08
 */
public class SednaDataSourceFactory implements DataSourceFactory {
    @Override
    public XQDataSource getDataSource(XQueryRunConfig config) throws Exception {
        return new SednaXQDataSource();
    }
}
