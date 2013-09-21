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

package org.intellij.xquery.reference.namespace;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

/**
 * User: ligasgr
 * Date: 17/08/13
 * Time: 19:09
 */
public enum XQueryPredeclaredNamespace {
    XML("xml", "http://www.w3.org/XML/1998/namespace"),
    XS("xs", "http://www.w3.org/2001/XMLSchema"),
    XSI("xsi", "http://www.w3.org/2001/XMLSchema-instance"),
    FN("fn", "http://www.w3.org/2005/xpath-functions"),
    LOCAL("local", "http://www.w3.org/2005/xquery-local-functions");

    private static final Map<String, String> mappingFromPrefix = new HashMap<String, String>();
    private String prefix;
    private String namespace;

    static {
        for (XQueryPredeclaredNamespace value : values()) {
            mappingFromPrefix.put(value.getPrefix(), value.getNamespace());
        }
    }

    private XQueryPredeclaredNamespace(String prefix, String namespace) {
        this.prefix = prefix;
        this.namespace = namespace;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNamespace() {
        return namespace;
    }

    public static Map<String, String> getMappingFromPrefix() {
        return unmodifiableMap(mappingFromPrefix);
    }
}
