package org.intellij.xquery.runner.rt.variable;

import org.intellij.xquery.runner.rt.XQJType;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQPreparedExpression;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 16:34
 */
public class XqjBinderFactory {

    public static void bindVariable(XQPreparedExpression expression, XQConnection connection, String name, String value,
                                    String type) throws Exception {
        Class<? extends BinderFactory> factoryClass = XQJType.getTypeFactoryClass(type);
        factoryClass.newInstance().bindValueForType(expression, connection, name, value, type);
    }
}
