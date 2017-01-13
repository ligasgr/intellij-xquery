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

package org.intellij.xquery.runner.rt.vendor.saxon;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.QueryModule;
import net.sf.saxon.s9api.ItemType;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryEvaluator;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.trans.XPathException;
import org.intellij.xquery.runner.rt.FileUtil;
import org.intellij.xquery.runner.rt.RunnerApp;
import org.intellij.xquery.runner.rt.XQueryItemType;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerVariable;
import org.w3c.dom.Document;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class SaxonRunnerApp implements RunnerApp {

    protected final XQueryRunConfig config;
    private final PrintStream output;
    private Processor processor;
    private SaxonTypeMapper typeMapper = new SaxonTypeMapper();

    public SaxonRunnerApp(XQueryRunConfig config, PrintStream output) throws XPathException {
        this.config = config;
        this.output = output;
        setUpProcessor(config);
    }

    private void setUpProcessor(XQueryRunConfig config) throws XPathException {
        if (config.isConfigFileEnabled()) {
            Configuration configuration = getConfiguration(config);
            processor = new Processor(configuration);
        } else {
            processor = new Processor(false);
        }
    }

    private Configuration getConfiguration(XQueryRunConfig config) throws XPathException {
        File configFile = new File(config.getConfigFile());
        return Configuration.readConfiguration(new StreamSource(configFile));
    }

    @Override
    public void runApp() throws Exception {
        doAdditionalConfiguration(processor);
        Serializer out = prepareSerializer();
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XQueryExecutable executable = compiler.compile(new File(config.getMainFile()));
        setMainModule(executable.getUnderlyingCompiledQuery().getMainModule());
        XQueryEvaluator evaluator = executable.load();
        bindContextItem(evaluator);
        bindVariables(evaluator);
        evaluator.run(out);
    }

    private Serializer prepareSerializer() {
        Serializer out = processor.newSerializer(output);
        out.setOutputProperty(Serializer.Property.METHOD, "xml");
        out.setOutputProperty(Serializer.Property.OMIT_XML_DECLARATION, "yes");
        return out;
    }

    private void bindContextItem(XQueryEvaluator evaluator) throws Exception {
        if (config.isContextItemEnabled()) {
            String contextItemValue = config.isContextItemFromEditorEnabled() ? config.getContextItemText() :
                    readFile(config.getContextItemFile());
            evaluator.setContextItem(getXdmValue(config.getContextItemType(), contextItemValue));
        }
    }

    private void bindVariables(XQueryEvaluator evaluator) throws Exception {
        for (XQueryRunnerVariable variable : config.getVariables()) {
            if (variable.ACTIVE) {
                evaluator.setExternalVariable(getName(variable.NAME, variable.NAMESPACE), getXdmValue(variable.TYPE,
                        variable.VALUE));
            }
        }
    }

    private QName getName(String name, String namespace) {
        if (namespace != null) {
            return new QName(namespace, name);
        } else {
            return new QName(name);
        }
    }

    private XdmItem getXdmValue(String type, String value) throws Exception {
        XQueryItemType itemType = XQueryItemType.valueFor(type);
        if (itemType == XQueryItemType.TEXT) {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Text textNode = document.createTextNode(value);
            return processor.newDocumentBuilder().wrap(textNode);
        } else if (itemType == XQueryItemType.DOCUMENT) {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new
                    ByteArrayInputStream(value.getBytes()));
            return processor.newDocumentBuilder().wrap(document);
        } else {
            ItemType mappedType = typeMapper.getType(itemType);
            return new XdmAtomicValue(value, mappedType);
        }
    }

    protected String readFile(String fileName) throws IOException {
        return FileUtil.readFile(fileName);
    }

    protected void doAdditionalConfiguration(Processor debugger) { }

    protected void setMainModule(QueryModule mainModule) { }

}
