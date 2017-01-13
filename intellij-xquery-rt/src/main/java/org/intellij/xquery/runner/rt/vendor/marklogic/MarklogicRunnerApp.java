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

package org.intellij.xquery.runner.rt.vendor.marklogic;

import com.marklogic.xcc.ContentSource;
import com.marklogic.xcc.ContentSourceFactory;
import com.marklogic.xcc.Request;
import com.marklogic.xcc.ResultSequence;
import com.marklogic.xcc.Session;
import com.marklogic.xcc.ValueFactory;
import com.marklogic.xcc.types.ValueType;
import com.marklogic.xcc.types.XName;
import com.marklogic.xcc.types.XdmValue;
import org.intellij.xquery.runner.rt.RunnerApp;
import org.intellij.xquery.runner.rt.XQueryItemType;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerVariable;

import java.io.PrintStream;
import java.net.URI;

import static org.intellij.xquery.runner.rt.FileUtil.readFile;

public class MarklogicRunnerApp implements RunnerApp {
    public static final String XCC_CONNECTION_URI_TEMPLATE = "xcc://%s:%s@%s:%s/%s";
    private final XQueryRunConfig config;
    private final PrintStream output;
    private final MarklogicTypeMapper typeMapper = new MarklogicTypeMapper();

    public MarklogicRunnerApp(XQueryRunConfig config, PrintStream output) {
        this.config = config;
        this.output = output;
    }

    @Override
    public void runApp() throws Exception {
        URI uri = new URI(String.format(XCC_CONNECTION_URI_TEMPLATE, config.getUsername(), config.getPassword(), config.getHost(), config.getPort(), config.getDatabaseName()));
        ContentSource contentSource = ContentSourceFactory.newContentSource(uri);
        try (Session session = contentSource.newSession()) {
            Request request = session.newAdhocQuery(readFile(config.getMainFile()));
            for (XQueryRunnerVariable variable : config.getVariables()) {
                if (variable.ACTIVE) {
                    request.setNewVariable(getName(variable.NAME, variable.NAMESPACE), getXdmValue(variable.TYPE,
                            variable.VALUE));
                }
            }
            ResultSequence rs = session.submitRequest(request);
            output.println(rs.asString());
        }
    }

    private XdmValue getXdmValue(String type, String value) {
        XQueryItemType itemType = XQueryItemType.valueFor(type);
        ValueType xdmType = typeMapper.getType(itemType);
        return ValueFactory.newValue(xdmType, value);
    }

    private XName getName(String name, String namespace) {
        return new XName(namespace, name);
    }
}
