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

/**
 * User: ligasgr
 * Date: 09/08/13
 * Time: 13:08
 */
public class XQueryQName<T> {
    private final String prefix;
    private final String localName;
    private final String namespace;
    private final T namedObject;

    XQueryQName(String prefix, String localName, String namespace, T namedObject) {
        this.prefix = prefix;
        this.localName = localName;
        this.namespace = namespace;
        this.namedObject = namedObject;
    }

    public T getNamedObject() {
        return namedObject;
    }

    public String getNamespace() {
        return namespace;
    }

    @Override
    public int hashCode() {
        int result = prefix != null ? prefix.hashCode() : 0;
        result = 31 * result + (localName != null ? localName.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof XQueryQName)) return false;
        XQueryQName that = (XQueryQName) obj;

        boolean prefixesAndLocalNamesMatch = prefix != null && prefix.equals
                (that.prefix) && localName.equals(that.localName);
        boolean prefixesAreEmptyAndLocalNamesMatch = prefix == null && that.prefix == null && localName != null &&
                localName.equals(that.localName);
        boolean namespacesAndLocalNamesMatch = namespace != null && namespace.equals(that.namespace) && localName
                .equals(that.localName);

        return prefixesAndLocalNamesMatch || prefixesAreEmptyAndLocalNamesMatch || namespacesAndLocalNamesMatch;
    }

    public String getTextRepresentation() {
        if (prefix != null) {
            return prefix + ":" + localName;
        } else {
            return localName;
        }
    }
}
