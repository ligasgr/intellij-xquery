package org.intellij.xquery.runner.rt.datasource;

import net.sf.saxon.Configuration;
import net.sf.saxon.xqj.SaxonXQDataSource;
import org.intellij.xquery.runner.rt.XQueryRunConfig;

import javax.xml.transform.stream.StreamSource;
import javax.xml.xquery.XQDataSource;
import java.io.File;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 08:01
 */
public class SaxonDataSourceFactory implements DataSourceFactory {
    @Override
    public XQDataSource getDataSource(XQueryRunConfig config) throws Exception {
        if (config.isConfigFileEnabled()) {
            File configFile = new File(config.getConfigFile());
            Configuration configuration = Configuration.readConfiguration(new StreamSource(configFile));
            return new SaxonXQDataSource(configuration);
        }
        return new SaxonXQDataSource();
    }
}
