package org.intellij.xquery.runner.rt.variable;

import org.intellij.xquery.runner.rt.XQJType;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 16:35
 */
public class AtomicValueBinderFactory implements BinderFactory {
    @Override
    public void bindValueForType(XQPreparedExpression expression, XQConnection connection, String name, String value,
                                 String type) throws Exception {
        expression.bindAtomicValue(new QName(name), value, getType(connection, type));
    }

    private XQItemType getType(XQConnection connection, String type) throws Exception {
        return connection.createAtomicType(XQJType.getXQJTypeForDescription(type));
    }
}
