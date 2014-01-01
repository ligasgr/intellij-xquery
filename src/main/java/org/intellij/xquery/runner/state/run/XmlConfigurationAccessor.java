/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

import org.jdom.CDATA;
import org.jdom.Element;

/**
 * User: ligasgr
 * Date: 18/11/13
 * Time: 13:58
 */
public class XmlConfigurationAccessor {

    public static final String CONFIGURATION_TAG = "xQueryConfiguration";

    public void readConfiguration(Element element, XQueryRunConfiguration runConfiguration) {
        Element configuration = element.getChild(CONFIGURATION_TAG);
        if (configuration == null) return;

        runConfiguration.setMainFileName(configuration.getAttributeValue("mainFileName"));
        runConfiguration.setVMParameters(configuration.getAttributeValue("vmParameters"));
        runConfiguration.setProgramParameters(configuration.getAttributeValue("programParameters"));
        runConfiguration.setRawWorkingDirectory(configuration.getAttributeValue("workingDirectory"));
        String alternativeJrePathEnabledString = configuration.getAttributeValue("alternativeJrePathEnabled");
        if (alternativeJrePathEnabledString != null)
            runConfiguration.setAlternativeJrePathEnabled(new Boolean(alternativeJrePathEnabledString));
        runConfiguration.setAlternativeJrePath(configuration.getAttributeValue("alternativeJrePath"));
        String contextItemEnabledString = configuration.getAttributeValue("contextItemEnabled");
        if (contextItemEnabledString != null)
            runConfiguration.setContextItemEnabled(new Boolean(contextItemEnabledString));
        String contextItemFromEditorEnabledString = configuration.getAttributeValue("contextItemFromEditorEnabled");
        if (contextItemFromEditorEnabledString != null)
            runConfiguration.setContextItemFromEditorEnabled(new Boolean(contextItemFromEditorEnabledString));
        Element contextItemTextElement = configuration.getChild("contextItemText");
        if (contextItemTextElement != null) {
            runConfiguration.setContextItemText(contextItemTextElement.getText());
        }
        runConfiguration.setContextItemFile(configuration.getAttributeValue("contextItemFile"));
        runConfiguration.setContextItemType(configuration.getAttributeValue("contextItemType"));
        runConfiguration.setDataSourceName(configuration.getAttributeValue("dataSourceName"));
    }

    public void writeConfiguration(XQueryRunConfiguration runConfiguration, Element element) {
            Element configuration = new Element(CONFIGURATION_TAG);
            element.addContent(configuration);
            if (runConfiguration.getMainFileName() != null)
                configuration.setAttribute("mainFileName", runConfiguration.getMainFileName());
            if (runConfiguration.getVMParameters() != null)
                configuration.setAttribute("vmParameters", runConfiguration.getVMParameters());
            if (runConfiguration.getProgramParameters() != null)
                configuration.setAttribute("programParameters", runConfiguration.getProgramParameters());
            if (runConfiguration.getRawWorkingDirectory() != null)
                configuration.setAttribute("workingDirectory", runConfiguration.getRawWorkingDirectory());
            configuration.setAttribute("alternativeJrePathEnabled", Boolean.toString(runConfiguration.isAlternativeJrePathEnabled()));
            if (runConfiguration.getAlternativeJrePath() != null)
                configuration.setAttribute("alternativeJrePath", runConfiguration.getAlternativeJrePath());
            configuration.setAttribute("contextItemEnabled", Boolean.toString(runConfiguration.isContextItemEnabled()));
            configuration.setAttribute("contextItemFromEditorEnabled", Boolean.toString(runConfiguration.isContextItemFromEditorEnabled()));
            if (runConfiguration.getContextItemText() != null) {
                Element contextItemTextElement = new Element("contextItemText");
                contextItemTextElement.addContent(new CDATA(runConfiguration.getContextItemText()));
                configuration.addContent(contextItemTextElement);
            }
            if (runConfiguration.getContextItemFile() != null) {
                configuration.setAttribute("contextItemFile", runConfiguration.getContextItemFile());
            }
            if (runConfiguration.getContextItemType() != null) {
                configuration.setAttribute("contextItemType", runConfiguration.getContextItemType());
            }
            if (runConfiguration.getDataSourceName() != null) {
                configuration.setAttribute("dataSourceName", runConfiguration.getDataSourceName());
            }
        }
}
