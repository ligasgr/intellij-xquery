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

package org.intellij.xquery.runner.rt;

import java.util.ArrayList;
import java.util.List;

public class XQueryRunConfigBuilder {
    private String typeName;
    private String mainFileName;
    private String contextItemType;
    private String contextItemValue;
    private List<XQueryRunnerVariable> variables = new ArrayList<XQueryRunnerVariable>();
    private String host;
    private String port;
    private String username;
    private String password;
    private boolean connectionDataIsAvailable;
    private String databaseName;
    private boolean debug;
    private String debugPort;

    private XQueryRunConfigBuilder() {
    }

    public static XQueryRunConfigBuilder runConfig() {
        return new XQueryRunConfigBuilder();
    }

    public XQueryRunConfigBuilder withTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public XQueryRunConfigBuilder withMainFileName(String mainFileName) {
        this.mainFileName = mainFileName;
        return this;
    }

    public XQueryRunConfigBuilder withContextItemType(String contextItemType) {
        this.contextItemType = contextItemType;
        return this;
    }

    public XQueryRunConfigBuilder withContextItemValue(String contextItemValue) {
        this.contextItemValue = contextItemValue;
        return this;
    }

    public XQueryRunConfigBuilder withVariable(String name, String value, String type) {
        XQueryRunnerVariable variable = new XQueryRunnerVariable();
        variable.ACTIVE = true;
        variable.NAME = name;
        variable.VALUE = value;
        variable.TYPE = type;
        variables.add(variable);
        return this;
    }

    public XQueryRunConfigBuilder withConnectionData(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        connectionDataIsAvailable = true;
        return this;
    }

    public XQueryRunConfigBuilder withDatabase(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public XQueryRunConfigBuilder withDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public XQueryRunConfigBuilder withDebugPort(String debugPort) {
        this.debugPort = debugPort;
        return this;
    }

    public String build() {
        return "<run debug=\"" + debug + "\" debugPort=\"" + debugPort + "\" >\n" +
                "<xQueryConfiguration " +
                "mainFileName=\"" + mainFileName + "\" " +
                contextItemType() +
                ">" +
                contextItemValue() +
                "</xQueryConfiguration>\n" +
                variables() +
                "<data-source-configuration " +
                "type=\"" + typeName + "\" " +
                connectionData() +
                databaseName() +
                "/>\n" +
                "</run>\n";
    }

    private String databaseName() {
        if (databaseName != null) {
            return " databaseName=\"" + databaseName + "\"";
        }
        return "";
    }

    private String connectionData() {
        if (connectionDataIsAvailable) {
            return "host=\"" + host + "\"" +
                    " port=\"" + port + "\"" +
                    " username=\"" + username + "\"" +
                    " password=\"" + password + "\"";
        }
        return "";
    }

    private String variables() {
        if (variables.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer("<variables><list>");
        for (XQueryRunnerVariable v : variables) {
            sb.append("<variable name=\"" + v.NAME + "\" active=\"" + v.ACTIVE + "\" type=\"" + v.TYPE + "\">"
                    + v.VALUE +
                    "</variable>");
        }
        sb.append("</list></variables>");
        return sb.toString();
    }

    private String contextItemValue() {
        return (contextItemValue != null
                ? "<contextItemText>" + contextItemValue + "</contextItemText>"
                : "");
    }

    private String contextItemType() {
        return (contextItemType != null
                ? "contextItemEnabled=\"true\" contextItemFromEditorEnabled=\"true\" contextItemType=\"" +
                contextItemType + "\""
                : "contextItemEnabled=\"false\"");
    }
}
