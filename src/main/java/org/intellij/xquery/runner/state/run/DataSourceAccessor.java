/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.state.run;

import com.intellij.util.xmlb.XmlSerializer;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourcesSettings;
import org.jdom.Element;

/**
 * User: ligasgr
 * Date: 19/11/13
 * Time: 12:48
 */
public class DataSourceAccessor {

    public void writeDataSourceConfiguration(XQueryRunConfiguration configuration, Element xmlRootElement) {
        String dataSourceName = configuration.getDataSourceName();
        if (dataSourceName != null) {
            XQueryDataSourceConfiguration dataSourceConfiguration = getDataSourcesSettings()
                    .getDataSourceConfigurationForName(dataSourceName);
            if (dataSourceConfiguration != null) {
                Element dataSourceElement = XmlSerializer.serialize(dataSourceConfiguration);
                xmlRootElement.addContent(dataSourceElement);
            }
        }
    }

    protected XQueryDataSourcesSettings getDataSourcesSettings() {
        return XQueryDataSourcesSettings.getInstance();
    }
}
