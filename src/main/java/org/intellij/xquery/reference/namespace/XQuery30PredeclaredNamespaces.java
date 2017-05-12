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

import com.intellij.openapi.util.Pair;

public class XQuery30PredeclaredNamespaces extends PredeclaredNamespaces {
    public static final Namespace FN = ns("fn", "http://www.w3.org/2005/xpath-functions");
    public static final Namespace MATH = ns("math", "http://www.w3.org/2005/xpath-functions/math");
    public static final Namespace XMLNS = ns("xmlns", "");
    public static final Namespace XS = ns("xs", "http://www.w3.org/2001/XMLSchema");

    {
        prefixToNamespaceMap.put("xml", Pair.create("http://www.w3.org/XML/1998/namespace", true));
        prefixToNamespaceMap.put(XS.getPrefix(), Pair.create(XS.getNamespace(), true));
        prefixToNamespaceMap.put("xsi", Pair.create("http://www.w3.org/2001/XMLSchema-instance", true));
        prefixToNamespaceMap.put(FN.getPrefix(), Pair.create(FN.getNamespace(), true));
        prefixToNamespaceMap.put(MATH.getPrefix(), Pair.create(MATH.getNamespace(), true));
        prefixToNamespaceMap.put("map", Pair.create("http://www.w3.org/2005/xpath-functions/map", true));
        prefixToNamespaceMap.put("err", Pair.create("http://www.w3.org/2005/xqt-errors", true));
        prefixToNamespaceMap.put("local", Pair.create("http://www.w3.org/2005/xquery-local-functions", true));
        prefixToNamespaceMap.put(XMLNS.getPrefix(), Pair.create(XMLNS.getNamespace(), true));
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
