/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;
import org.intellij.xquery.runner.rt.FileUtil;
import org.intellij.xquery.runner.rt.RunnerApp;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerVariable;
import org.w3c.dom.Document;
import org.w3c.dom.Text;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.PrintStream;
import java.io.StringWriter;

import static org.intellij.xquery.runner.rt.FileUtil.readFile;

public class BaseXLocalRunnerApp implements RunnerApp {
    private final XQueryRunConfig config;
    private final PrintStream output;

    public BaseXLocalRunnerApp(XQueryRunConfig config, PrintStream output) {
        this.config = config;
        this.output = output;
    }

    @Override
    public void run() throws Exception {
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
            for(Item item; (item = iter.next()) != null;) {
                Object value = item.toJava();
                if (value instanceof Document) {
                    DOMSource domSource = new DOMSource((Document) value);
                    StringWriter writer = new StringWriter();
                    StreamResult result = new StreamResult(writer);
                    TransformerFactory tf = TransformerFactory.newInstance();
                    Transformer transformer = tf.newTransformer();
                    transformer.transform(domSource, result);
                    output.println(writer.toString());
                } else if (value instanceof Text) {
                    output.println(((Text) value).getWholeText());
                } else {
                    output.println(item);
                }
            }
        }
        context.close();
    }
}
