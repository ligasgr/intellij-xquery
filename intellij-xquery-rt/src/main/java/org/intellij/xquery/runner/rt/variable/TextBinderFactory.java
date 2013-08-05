package org.intellij.xquery.runner.rt.variable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;

/**
 * User: ligasgr
 * Date: 14/10/13
 * Time: 14:18
 */
public class TextBinderFactory implements BinderFactory {
    @Override
    public void bindValueForType(XQPreparedExpression expression, XQConnection connection, String name, String value,
                                 String type) throws Exception {
        expression.bindNode(new QName(name), createTextNode(value), getType(connection));
    }

    private Node createTextNode(String value) throws ParserConfigurationException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element parentNode = document.createElement("parent");
        document.appendChild(parentNode);
        Text textNode = document.createTextNode(value);
        parentNode.appendChild(textNode);
        return textNode;
    }

    public XQItemType getType(XQConnection connection) throws Exception {
        return connection.createTextType();
    }
}
