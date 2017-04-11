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

package org.intellij.xquery.runner.rt.vendor.exist;

import org.intellij.xquery.runner.rt.FileUtil;
import org.intellij.xquery.runner.rt.RunnerApp;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerVariable;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.xml.xpath.XPathConstants.NODE;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

public class ExistRunnerApp implements RunnerApp {
    public static final String SERIALIZED_NAMESPACE = "http://exist-db.org/xquery/types/serialized";
    public static final String XMLNS = "xmlns";
    private final XQueryRunConfig config;
    private final PrintStream output;

    public ExistRunnerApp(XQueryRunConfig config, PrintStream output) {
        this.config = config;
        this.output = output;
    }

    @Override
    public void runApp() throws Exception {
        String requestXml = getRequest();
        String uri = getUri();
        String response = executeRequest(uri, requestXml);
        String result = getEvaluationResult(response);
        output.print(result);

    }

    private String getUri() {
        return String.format("http://%s:%s/exist/rest/db/", config.getHost(), config.getPort());
    }

    private String getRequest() throws IOException, TransformerException, ParserConfigurationException {
        DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = dFact.newDocumentBuilder();
        Document doc = build.newDocument();

        Element root = doc.createElementNS("http://exist.sourceforge.net/NS/exist", "query");
        root.setAttribute("start", "1");
        root.setAttribute("max", "100");

        Element text = getText(doc);
        Element variables = getVariables(doc);
        Element properties = getProperties(doc);

        root.appendChild(text);
        root.appendChild(variables);
        root.appendChild(properties);
        doc.appendChild(root);

        TransformerFactory tFact = TransformerFactory.newInstance();
        Transformer trans = tFact.newTransformer();

        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        DOMSource source = new DOMSource(doc);
        trans.transform(source, result);
        String requestXml = writer.toString();
        return requestXml;
    }

    private Element getText(Document doc) throws IOException {
        String query = FileUtil.readFile(config.getMainFile());
        Element text = doc.createElement("text");
        text.appendChild(doc.createTextNode(query));
        return text;
    }

    private Element getProperties(Document doc) {
        Element properties = doc.createElement("properties");
        Element indent = doc.createElement("property");
        indent.setAttribute("name", "indent");
        indent.setAttribute("value", "yes");
        properties.appendChild(indent);
        return properties;
    }

    private Element getVariables(Document doc) {
        Element variables = doc.createElement("variables");
        for (XQueryRunnerVariable variable : config.getVariables()) {
            if (variable.ACTIVE) {
                Element variableElement = doc.createElement("variable");
                Element localName = doc.createElement("localname");
                localName.appendChild(doc.createTextNode(variable.NAME));
                Element name = doc.createElement("qname");
                name.appendChild(localName);

                Element sequence = doc.createElementNS(SERIALIZED_NAMESPACE, "sequence");
                Element value = doc.createElementNS(SERIALIZED_NAMESPACE, "value");
                value.appendChild(doc.createTextNode(variable.VALUE));
                value.setAttribute("type", variable.TYPE);

                sequence.appendChild(value);

                variableElement.appendChild(name);
                variableElement.appendChild(sequence);

                variables.appendChild(variableElement);
            }
        }
        return variables;
    }

    private String getEvaluationResult(String response) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(response)));
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("/result/value/text()");
        String textValue = expr.evaluate(doc);
        if (!"".equals(textValue)) return textValue;
        expr = xpath.compile("/result/text()");
        String resultingXmlAsText = expr.evaluate(doc);
        if (!"".equals(resultingXmlAsText.trim())) return resultingXmlAsText;
        expr = xpath.compile("/result/child::*");
        Element node = (Element) expr.evaluate(doc, NODE);
        if (node != null) {
            NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                String nodeName = attribute.getNodeName();
                String nodeValue = attribute.getNodeValue();
                if (XMLNS.equals(nodeName) && SERIALIZED_NAMESPACE.equals(nodeValue)) {
                    node.removeAttribute(XMLNS);
                }
            }
            return prettyPrint(node);
        } else {
            return response;
        }
    }

    private String prettyPrint(Element node) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        DOMImplementationRegistry domImplementationRegistry = DOMImplementationRegistry.newInstance();
        DOMImplementationLS domImplementationLS = (DOMImplementationLS) domImplementationRegistry.getDOMImplementation("LS");
        LSOutput lsOutput = domImplementationLS.createLSOutput();
        LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
        lsOutput.setEncoding("UTF-8");
        Writer stringWriter = new StringWriter();
        lsOutput.setCharacterStream(stringWriter);
        lsSerializer.write(node, lsOutput);
        String result = stringWriter.toString();
        return result;
    }

    private String executeRequest(String uri, String requestXml) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/xml");
        String usernameAndPassword = config.getUsername() + ":" + config.getPassword();
        String encoded = encodeBase64String(usernameAndPassword.getBytes(UTF_8));
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setDoOutput(true);
        try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {
            out.println(requestXml);
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            StringBuilder responseXml = new StringBuilder();
            while ((line = in.readLine()) != null) {
                responseXml.append(line);
                responseXml.append('\n');
            }
            return responseXml.toString();
        }
    }
}
