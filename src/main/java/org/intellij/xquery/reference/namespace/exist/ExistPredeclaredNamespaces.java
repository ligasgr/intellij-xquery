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

package org.intellij.xquery.reference.namespace.exist;

import com.intellij.openapi.util.Pair;
import org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces;

public class ExistPredeclaredNamespaces extends XQuery30PredeclaredNamespaces {

    {
        prefixToNamespaceMap.put("compression", Pair.create("http://exist-db.org/xquery/compression", true));
        prefixToNamespaceMap.put("console", Pair.create("http://exist-db.org/xquery/console", false));
        prefixToNamespaceMap.put("contentextraction", Pair.create("http://exist-db.org/xquery/contentextraction", true));
        prefixToNamespaceMap.put("emath", Pair.create("http://exist-db.org/xquery/math", false));
        prefixToNamespaceMap.put("example", Pair.create("http://exist-db.org/xquery/examples", true));
        prefixToNamespaceMap.put("file", Pair.create("http://exist-db.org/xquery/file", true));
        prefixToNamespaceMap.put("ft", Pair.create("http://exist-db.org/xquery/lucene", true));
        prefixToNamespaceMap.put("httpclient", Pair.create("http://exist-db.org/xquery/httpclient", true));
        prefixToNamespaceMap.put("inspect", Pair.create("http://exist-db.org/xquery/inspect", true));
        prefixToNamespaceMap.put("kwic", Pair.create("http://exist-db.org/xquery/kwic", false));
        prefixToNamespaceMap.put("mail", Pair.create("http://exist-db.org/xquery/mail", true));
        prefixToNamespaceMap.put("ner", Pair.create("http://exist-db.org/xquery/stanford-ner", false));
        prefixToNamespaceMap.put("ngram", Pair.create("http://exist-db.org/xquery/ngram", false));
        prefixToNamespaceMap.put("range", Pair.create("http://exist-db.org/xquery/range", false));
        prefixToNamespaceMap.put("repo", Pair.create("http://exist-db.org/xquery/repo", true));
        prefixToNamespaceMap.put("request", Pair.create("http://exist-db.org/xquery/request", true));
        prefixToNamespaceMap.put("response", Pair.create("http://exist-db.org/xquery/response", true));
        prefixToNamespaceMap.put("scheduler", Pair.create("http://exist-db.org/xquery/scheduler", true));
        prefixToNamespaceMap.put("seq", Pair.create("http://exist-db.org/xquery/sequences", false));
        prefixToNamespaceMap.put("session", Pair.create("http://exist-db.org/xquery/session", true));
        prefixToNamespaceMap.put("sm", Pair.create("http://exist-db.org/xquery/securitymanager", true));
        prefixToNamespaceMap.put("sort", Pair.create("http://exist-db.org/xquery/sort", true));
        prefixToNamespaceMap.put("system", Pair.create("http://exist-db.org/xquery/system", true));
        prefixToNamespaceMap.put("t", Pair.create("http://exist-db.org/xquery/testing", false));
        prefixToNamespaceMap.put("text", Pair.create("http://exist-db.org/xquery/text", true));
        prefixToNamespaceMap.put("transform", Pair.create("http://exist-db.org/xquery/transform", true));
        prefixToNamespaceMap.put("util", Pair.create("http://exist-db.org/xquery/util", true));
        prefixToNamespaceMap.put("v", Pair.create("http://exist-db.org/versioning", false));
        prefixToNamespaceMap.put("validation", Pair.create("http://exist-db.org/xquery/validation", true));
        prefixToNamespaceMap.put("xmldb", Pair.create("http://exist-db.org/xquery/xmldb", true));
        prefixToNamespaceMap.put("xqdm", Pair.create("http://exist-db.org/xquery/xqdoc", true));
        prefixToNamespaceMap.put("xsl", Pair.create("http://www.w3.org/1999/XSL/Transform", true));
        prefixToNamespaceMap.put("xslfo", Pair.create("http://exist-db.org/xquery/xslfo", true));
    }
}
