package org.intellij.xquery.model;

import org.intellij.xquery.psi.XQueryFunctionName;

/**
 * User: ligasgr
 * Date: 12/08/13
 * Time: 22:01
 */
public class XQueryQNameBuilder<T> {
    private String prefix;
    private String localName;
    private String namespace;
    private T namedObject;

    private XQueryQNameBuilder() {
    }

    public static XQueryQNameBuilder<XQueryFunctionName> aXQueryQName(XQueryFunctionName functionName) {
        XQueryQNameBuilder<XQueryFunctionName> instance = new XQueryQNameBuilder<XQueryFunctionName>();
        if (functionName.getFunctionNamespace() != null) {
            instance.prefix = functionName.getFunctionNamespace().getText();
        }
        if (functionName.getFunctionLocalName() != null) {
            instance.localName = functionName.getFunctionLocalName().getText();
        }
        instance.namedObject = functionName;
        return instance;
    }

    public XQueryQNameBuilder withPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public XQueryQNameBuilder withLocalName(String localName) {
        this.localName = localName;
        return this;
    }

    public XQueryQNameBuilder withNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public XQueryQNameBuilder withNamedObject(T namedObject) {
        this.namedObject = namedObject;
        return this;
    }

    public XQueryQName<T> build() {
        return new XQueryQName<T>(prefix, localName, namespace, namedObject);
    }
}
