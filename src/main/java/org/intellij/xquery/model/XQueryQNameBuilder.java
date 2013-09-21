/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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

package org.intellij.xquery.model;

import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryVarName;

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
        XQueryFile containingFile = (XQueryFile) functionName.getContainingFile();
        XQueryQNameBuilder<XQueryFunctionName> instance = new XQueryQNameBuilder<XQueryFunctionName>();
        if (functionName.getFunctionNamespace() != null) {
            instance.prefix = functionName.getFunctionNamespace().getText();
        }
        if (functionName.getFunctionLocalName() != null) {
            instance.localName = functionName.getFunctionLocalName().getText();
        }
        instance.namespace = containingFile.mapPrefixToNamespace(instance.prefix);
        instance.namedObject = functionName;
        return instance;
    }

    public static XQueryQNameBuilder<XQueryVarName> aXQueryQName(XQueryVarName varName) {
        XQueryQNameBuilder<XQueryVarName> instance = new XQueryQNameBuilder<XQueryVarName>();
        if (varName.getVarNamespace() != null) {
            instance.prefix = varName.getVarNamespace().getText();
        }
        if (varName.getVarLocalName() != null) {
            instance.localName = varName.getVarLocalName().getText();
        }
        instance.namedObject = varName;
        return instance;
    }

    public XQueryQNameBuilder<T> withPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public XQueryQName<T> build() {
        return new XQueryQName<T>(prefix, localName, namespace, namedObject);
    }
}
