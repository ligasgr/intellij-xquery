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

package org.intellij.xquery.runner.rt.xqj.binding;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;

/**
 * User: ligasgr
 * Date: 14/10/13
 * Time: 20:23
 */
public class AttributeBinder implements TypeBinder {
    @Override
    public void bind(XQPreparedExpression expression, XQConnection connection, QName name, String value,
                                 String type) throws Exception {
        expression.bindNode(name, createAttributeNode(value), getType(connection));
    }

    private Node createAttributeNode(String value) throws ParserConfigurationException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element parentNode = document.createElement("parent");
        document.appendChild(parentNode);
        Attr attribute = document.createAttribute("attr");
        attribute.setValue(value);
        parentNode.setAttributeNode(attribute);
        return attribute;
    }

    public XQItemType getType(XQConnection connection) throws Exception {
        return connection.createAttributeType(null, XQItemType.XQBASETYPE_ANYSIMPLETYPE);
    }
}
