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

package org.intellij.xquery.util;

import com.intellij.openapi.util.JDOMUtil;
import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static com.intellij.util.xmlb.XmlSerializer.serialize;

/**
 * User: ligasgr
 * Date: 28/09/13
 * Time: 23:58
 */
public class XmlUtils {
    public static Node the(String xml) throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        return new DelegatingNode(doc);
    }

    public static String xml(Element mainElement) throws IOException, ParserConfigurationException, SAXException {
        Document document = new Document(mainElement);
        XMLOutputter out = new XMLOutputter();
        StringWriter writer = new StringWriter();
        out.output(document, writer);
        writer.flush();
        writer.close();
        return writer.toString();
    }

    public static Element rootElement(String xml) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(new StringReader(xml));
        return document.getRootElement();
    }

    public static String serializeToXml(Object bean) {
        Element element = serialize(bean);

        return JDOMUtil.writeElement(element).trim();
    }

    public static <T> T deserializeFromXml(Element element, Class<T> deserializedElementClass) {
        return XmlSerializer.deserialize(element, deserializedElementClass);
    }
}
