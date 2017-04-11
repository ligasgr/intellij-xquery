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

package org.intellij.xquery.runner.rt.vendor.basex;

import org.basex.core.Context;
import org.basex.io.serial.Serializer;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Hex;
import org.basex.query.value.item.Item;
import org.intellij.xquery.runner.rt.FileUtil;
import org.intellij.xquery.runner.rt.RunnerApp;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerVariable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;

import static org.intellij.xquery.runner.rt.FileUtil.readFile;

public class BaseXLocalRunnerApp implements RunnerApp {
    private final XQueryRunConfig config;
    private final PrintStream output;

    public BaseXLocalRunnerApp(XQueryRunConfig config, PrintStream output) {
        this.config = config;
        this.output = output;
    }

    @Override
    public void runApp() throws Exception {
        Context context = new Context();
        try (QueryProcessor proc = new QueryProcessor(FileUtil.readFile(config.getMainFile()), context)) {
            for (XQueryRunnerVariable variable : config.getVariables()) {
                if (variable.ACTIVE) {
                    proc.bind(variable.NAME, variable.VALUE, variable.TYPE);
                }
            }
            if (config.isContextItemEnabled()) {
                String contextItemValue = config.isContextItemFromEditorEnabled() ? config.getContextItemText() : readFile(config.getContextItemFile());
                proc.context(contextItemValue, config.getContextItemType());
            }
            Iter iter = proc.iter();
            try(Serializer ser = proc.getSerializer(output)) {
                for(Item item; (item = iter.next()) != null;) {
                    if (item instanceof Hex) {
                        output.println(item.toString());
                    } else {
                        ser.serialize(item);
                    }
                }
            }
        }
        context.close();
    }
}
