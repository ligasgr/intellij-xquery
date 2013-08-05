package org.intellij.xquery.runner.rt.variable;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;

/**
 * User: ligasgr
 * Date: 14/10/13
 * Time: 13:39
 */
public class DocumentBinderFactory implements BinderFactory {
    @Override
    public void bindValueForType(XQPreparedExpression expression, XQConnection connection, String name, String value,
                                 String type) throws Exception {
        expression.bindDocument(new QName(name), value, null, getType(connection));
    }

    public XQItemType getType(XQConnection connection) throws Exception {
        return connection.createDocumentType();
    }
}
