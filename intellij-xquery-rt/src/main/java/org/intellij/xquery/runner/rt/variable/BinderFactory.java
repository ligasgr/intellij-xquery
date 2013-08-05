package org.intellij.xquery.runner.rt.variable;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQPreparedExpression;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 16:35
 */
public interface BinderFactory {
    void bindValueForType(XQPreparedExpression expression, XQConnection connection, String name, String value,
                          String type) throws Exception;
}
