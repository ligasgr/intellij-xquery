package org.intellij.xquery.runner.rt.variable;

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
public class AttributeBinderFactory implements BinderFactory {
    @Override
    public void bindValueForType(XQPreparedExpression expression, XQConnection connection, String name, String value,
                                 String type) throws Exception {
        expression.bindNode(new QName(name), createAttributeNode(value), getType(connection));
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
