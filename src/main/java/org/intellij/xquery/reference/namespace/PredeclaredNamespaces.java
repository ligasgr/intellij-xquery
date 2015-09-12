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
import org.intellij.xquery.psi.XQueryFile;

import java.util.HashMap;
import java.util.Map;

public abstract class PredeclaredNamespaces {

    protected final Map<String, Pair<String, Boolean>> prefixToNamespaceMap = new HashMap<String, Pair<String, Boolean>>();

    public boolean isPredeclaredNamespace(String namespace) {
        for (Map.Entry<String, Pair<String, Boolean>> entry : prefixToNamespaceMap.entrySet()) {
            if (entry.getValue().first.equals(namespace)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPredeclaredNamespacePrefix(String namespacePrefix) {
        return prefixToNamespaceMap.containsKey(namespacePrefix);
    }

    public String getNamespaceForPrefix(String prefix) {
        return prefixToNamespaceMap.get(prefix).first;
    }

    public Map<String, String> getPrefixToNamespaceMap(XQueryFile unused) {
        Map<String, String> result = new HashMap<String, String>(prefixToNamespaceMap.size());
        for (Map.Entry<String, Pair<String, Boolean>> entry : prefixToNamespaceMap.entrySet()) {
            if (entry.getValue().second) {
                result.put(entry.getKey(), entry.getValue().first);
            }
        }
        return result;
    }
}
