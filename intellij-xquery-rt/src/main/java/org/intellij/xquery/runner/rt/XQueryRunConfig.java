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

package org.intellij.xquery.runner.rt;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ligasgr
 * Date: 09/10/13
 * Time: 00:07
 */
public class XQueryRunConfig {
    private final Document document;
    private final XPathExpression mainFileExpression;
    private final XPathExpression contextItemEnabledExpression;
    private final XPathExpression contextItemFromEditorEnabledExpression;
    private final XPathExpression contextItemFileExpression;
    private final XPathExpression contextItemTextExpression;
    private final XPathExpression numberOfVariablesExpression;
    private final XPathExpression dataSourceTypeExpression;
    private final XPathExpression hostExpression;
    private final XPathExpression portExpression;
    private final XPathExpression usernameExpression;
    private final XPathExpression passwordExpression;
    private final XPathExpression configFileEnabledExpression;
    private final XPathExpression configFileExpression;
    private final XPathExpression databaseNameExpression;
    private final XPathExpression contextItemTypeExpression;

    public XQueryRunConfig(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
        XPath xPath = XPathFactory.newInstance().newXPath();
        mainFileExpression = xPath.compile(xqCfgAttrXPath("mainFileName"));
        contextItemEnabledExpression = xPath.compile(xqCfgAttrXPath("contextItemEnabled"));
        contextItemFromEditorEnabledExpression = xPath.compile(xqCfgAttrXPath("contextItemFromEditorEnabled"));
        contextItemFileExpression = xPath.compile(xqCfgAttrXPath("contextItemFile"));
        contextItemTextExpression = xPath.compile("string(/run/xQueryConfiguration/contextItemText)");
        numberOfVariablesExpression = xPath.compile("count(/run/variables/list/variable)");
        dataSourceTypeExpression = xPath.compile(xqRunnerAttrXPath("type"));
        hostExpression = xPath.compile(xqRunnerAttrXPath("host"));
        portExpression = xPath.compile(xqRunnerAttrXPath("port"));
        usernameExpression = xPath.compile(xqRunnerAttrXPath("username"));
        passwordExpression = xPath.compile(xqRunnerAttrXPath("password"));
        configFileEnabledExpression = xPath.compile(xqRunnerAttrXPath("configEnabled"));
        configFileExpression = xPath.compile(xqRunnerAttrXPath("configFile"));
        databaseNameExpression = xPath.compile(xqRunnerAttrXPath("databaseName"));
        contextItemTypeExpression = xPath.compile(xqCfgAttrXPath("contextItemType"));
    }

    public String getMainFile() {
        return getExpressionValue(mainFileExpression);
    }

    public boolean isContextItemEnabled() {
        return Boolean.parseBoolean(getExpressionValue(contextItemEnabledExpression));
    }

    public boolean isContextItemFromEditorEnabled() {
        return Boolean.parseBoolean(getExpressionValue(contextItemFromEditorEnabledExpression));
    }

    public String getContextItemFile() {
        return getExpressionValue(contextItemFileExpression);
    }

    private String getExpressionValue(XPathExpression expression) {
        try {
            return expression.evaluate(document);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public String getContextItemText() {
        return getExpressionValue(contextItemTextExpression);
    }

    public List<XQueryRunnerVariable> getVariables() {
        String count = getExpressionValue(numberOfVariablesExpression);
        int numberOfVariables = Integer.valueOf(count);
        List<XQueryRunnerVariable> result = new ArrayList<XQueryRunnerVariable>(numberOfVariables);
        for (int i = 1; i <= numberOfVariables; i++) {
            String baseXPath = "/run/variables/list/variable[" + i + "]/";
            XQueryRunnerVariable variable = new XQueryRunnerVariable();
            variable.ACTIVE = Boolean.parseBoolean(getExpressionValue(getExpression(baseXPath + "@active")));
            variable.NAME = getExpressionValue(getExpression(baseXPath + "@name"));
            variable.NAMESPACE = getExpressionValue(getExpression(baseXPath + "@namespace"));
            variable.TYPE = getExpressionValue(getExpression(baseXPath + "@type"));
            variable.VALUE = getExpressionValue(getExpression("string(" + baseXPath + "text())"));
            result.add(variable);
        }
        return result;
    }

    public XQueryDataSourceType getDataSourceType() {
        return XQueryDataSourceType.getForName(getExpressionValue(dataSourceTypeExpression));
    }

    public String getHost() {
        return getExpressionValue(hostExpression);
    }

    public String getPort() {
        return getExpressionValue(portExpression);
    }

    public String getUsername() {
        return getExpressionValue(usernameExpression);
    }

    public String getPassword() {
        return getExpressionValue(passwordExpression);
    }

    public boolean isConfigFileEnabled() {
        return Boolean.parseBoolean(getExpressionValue(configFileEnabledExpression));
    }

    public String getConfigFile() {
        return getExpressionValue(configFileExpression);
    }

    public String getDatabaseName() {
        return getExpressionValue(databaseNameExpression);
    }

    public String getContextItemType() {
        return getExpressionValue(contextItemTypeExpression);
    }

    private XPathExpression getExpression(String xpath) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            return xPath.compile(xpath);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private String xqRunnerAttrXPath(String attributeName) {
        return "/run/data-source-configuration/@" + attributeName;
    }

    private String xqCfgAttrXPath(String attributeName) {
        return "/run/xQueryConfiguration/@" + attributeName;
    }
}
