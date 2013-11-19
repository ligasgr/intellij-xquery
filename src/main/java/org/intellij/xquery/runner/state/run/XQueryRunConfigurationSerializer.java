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

package org.intellij.xquery.runner.state.run;

import com.intellij.openapi.util.JDOMUtil;
import org.jdom.Element;

import java.io.IOException;
import java.io.Writer;

/**
 * User: ligasgr
 * Date: 08/10/13
 * Time: 22:41
 */
public class XQueryRunConfigurationSerializer {
    private final XQueryRunConfiguration configuration;
    private XmlConfigurationAccessor xmlConfigurationAccessor;
    private VariablesAccessor variablesAccessor;
    private DataSourceAccessor dataSourceAccessor;

    public XQueryRunConfigurationSerializer(XQueryRunConfiguration configuration, XmlConfigurationAccessor
            xmlConfigurationAccessor, VariablesAccessor variablesAccessor, DataSourceAccessor dataSourceAccessor) {
        this.configuration = configuration;
        this.xmlConfigurationAccessor = xmlConfigurationAccessor;
        this.variablesAccessor = variablesAccessor;
        this.dataSourceAccessor = dataSourceAccessor;
    }

    public void serialize(Writer writer) throws Exception {
        Element xmlRootElement = new Element("run");
        variablesAccessor.writeVariables(configuration, xmlRootElement);
        xmlConfigurationAccessor.writeConfiguration(configuration, xmlRootElement);
        dataSourceAccessor.writeDataSourceConfiguration(configuration, xmlRootElement);
        getElementWriter().writeElement(xmlRootElement, writer, System.getProperty("line.separator"));
    }

    protected ElementWriter getElementWriter() {
        return new ElementWriter() {
            @Override
            public void writeElement(Element element, Writer writer, String lineSeparator) throws IOException {
                JDOMUtil.writeElement(element, writer, lineSeparator);
            }
        };
    }
}
