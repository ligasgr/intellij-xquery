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

package org.intellij.xquery.reference.namespace.basex;

import com.intellij.openapi.util.Pair;
import org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces;

public class BaseXPredeclaredNamespaces extends XQuery30PredeclaredNamespaces {
    {
        prefixToNamespaceMap.put("admin", Pair.create("http://basex.org/modules/admin", true));
        prefixToNamespaceMap.put("archive", Pair.create("http://basex.org/modules/archive", true));
        prefixToNamespaceMap.put("array", Pair.create("http://www.w3.org/2005/xpath-functions/array", true));
        prefixToNamespaceMap.put("bin", Pair.create("http://expath.org/ns/binary", true));
        prefixToNamespaceMap.put("client", Pair.create("http://basex.org/modules/client", true));
        prefixToNamespaceMap.put("convert", Pair.create("http://basex.org/modules/convert", true));
        prefixToNamespaceMap.put("crypto", Pair.create("http://expath.org/ns/crypto", true));
        prefixToNamespaceMap.put("csv", Pair.create("http://basex.org/modules/csv", true));
        prefixToNamespaceMap.put("db", Pair.create("http://basex.org/modules/db", true));
        prefixToNamespaceMap.put("fetch", Pair.create("http://basex.org/modules/fetch", true));
        prefixToNamespaceMap.put("file", Pair.create("http://expath.org/ns/file", true));
        prefixToNamespaceMap.put("ft", Pair.create("http://basex.org/modules/ft", true));
        prefixToNamespaceMap.put("hash", Pair.create("http://basex.org/modules/hash", true));
        prefixToNamespaceMap.put("hof", Pair.create("http://basex.org/modules/hof", true));
        prefixToNamespaceMap.put("html", Pair.create("http://basex.org/modules/html", true));
        prefixToNamespaceMap.put("http", Pair.create("http://expath.org/ns/http-client", true));
        prefixToNamespaceMap.put("index", Pair.create("http://basex.org/modules/index", true));
        prefixToNamespaceMap.put("inspect", Pair.create("http://basex.org/modules/inspect", true));
        prefixToNamespaceMap.put("json", Pair.create("http://basex.org/modules/json", true));
        prefixToNamespaceMap.put("out", Pair.create("http://basex.org/modules/out", true));
        prefixToNamespaceMap.put("proc", Pair.create("http://basex.org/modules/proc", true));
        prefixToNamespaceMap.put("prof", Pair.create("http://basex.org/modules/prof", true));
        prefixToNamespaceMap.put("random", Pair.create("http://basex.org/modules/random", true));
        prefixToNamespaceMap.put("repo", Pair.create("http://basex.org/modules/repo", true));
        prefixToNamespaceMap.put("sql", Pair.create("http://basex.org/modules/sql", true));
        prefixToNamespaceMap.put("stream", Pair.create("http://basex.org/modules/stream", true));
        prefixToNamespaceMap.put("unit", Pair.create("http://basex.org/modules/unit", true));
        prefixToNamespaceMap.put("user", Pair.create("http://basex.org/modules/user", true));
        prefixToNamespaceMap.put("validate", Pair.create("http://basex.org/modules/validate", true));
        prefixToNamespaceMap.put("web", Pair.create("http://basex.org/modules/web", true));
        prefixToNamespaceMap.put("xquery", Pair.create("http://basex.org/modules/xquery", true));
        prefixToNamespaceMap.put("xslt", Pair.create("http://basex.org/modules/xslt", true));
        prefixToNamespaceMap.put("zip", Pair.create("http://expath.org/ns/zip", true));
        prefixToNamespaceMap.put("geo", Pair.create("http://expath.org/ns/geo", true));
        prefixToNamespaceMap.put("request", Pair.create("http://exquery.org/ns/request", true));
        prefixToNamespaceMap.put("rest", Pair.create("http://exquery.org/ns/restxq", true));
        prefixToNamespaceMap.put("session", Pair.create("http://basex.org/modules/session", true));
        prefixToNamespaceMap.put("sessions", Pair.create("http://basex.org/modules/sessions", true));
    }
}
