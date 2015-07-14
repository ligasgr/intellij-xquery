/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
 * (see the CONTRIBUTORS file).
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

public class XQueryStandardPredeclaredNamespaces extends PredeclaredNamespaces {
    public static final Namespace FN = ns("fn", "http://www.w3.org/2005/xpath-functions");
    public static final Namespace MATH = ns("math", "http://www.w3.org/2005/xpath-functions/math");
    public static final Namespace XMLNS = ns("xmlns", "");

    {
        prefixToNamespaceMap.put("xml", "http://www.w3.org/XML/1998/namespace");
        prefixToNamespaceMap.put("xs", "http://www.w3.org/2001/XMLSchema");
        prefixToNamespaceMap.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        prefixToNamespaceMap.put(FN.getPrefix(), FN.getNamespace());
        prefixToNamespaceMap.put(MATH.getPrefix(), MATH.getNamespace());
        prefixToNamespaceMap.put("map", "http://www.w3.org/2005/xpath-functions/map");
        prefixToNamespaceMap.put("err", "http://www.w3.org/2005/xqt-errors");
        prefixToNamespaceMap.put("local", "http://www.w3.org/2005/xquery-local-functions");
        prefixToNamespaceMap.put(XMLNS.getPrefix(), XMLNS.getNamespace());
    }

    public static Namespace ns(String prefix, String namespace) {
        return new Namespace(prefix, namespace);
    }

    public static class Namespace {
        private final String prefix;
        private final String namespace;

        public Namespace(String prefix, String namespace) {
            this.prefix = prefix;
            this.namespace = namespace;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getNamespace() {
            return namespace;
        }
    }
}
