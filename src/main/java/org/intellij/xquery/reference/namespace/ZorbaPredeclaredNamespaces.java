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

public class ZorbaPredeclaredNamespaces extends PredeclaredNamespaces {

    {
        prefixToNamespaceMap.put("an", Pair.create("http://zorba.io/annotations", false));
        prefixToNamespaceMap.put("anim", Pair.create("http://zorba.io/modules/image/animation", false));
        prefixToNamespaceMap.put("base64", Pair.create("http://zorba.io/modules/base64", false));
        prefixToNamespaceMap.put("basic", Pair.create("http://zorba.io/modules/image/basic", false));
        prefixToNamespaceMap.put("cb", Pair.create("http://www.zorba-xquery.com/modules/couchbase", false));
        prefixToNamespaceMap.put("cddl", Pair.create("http://zorba.io/modules/store/static/collections/ddl", false));
        prefixToNamespaceMap.put("cdml", Pair.create("http://zorba.io/modules/store/static/collections/dml", false));
        prefixToNamespaceMap.put("collections-ddl", Pair.create("http://zorba.io/modules/store/dynamic/collections/ddl", false));
        prefixToNamespaceMap.put("collections-dml", Pair.create("http://zorba.io/modules/store/dynamic/collections/dml", false));
        prefixToNamespaceMap.put("con", Pair.create("http://zorba.io/modules/data-cleaning/consolidation", false));
        prefixToNamespaceMap.put("conversion", Pair.create("http://zorba.io/modules/data-cleaning/conversion", false));
        prefixToNamespaceMap.put("csv", Pair.create("http://zorba.io/modules/json-csv", false));
        prefixToNamespaceMap.put("datetime", Pair.create("http://zorba.io/modules/datetime", false));
        prefixToNamespaceMap.put("ddl", Pair.create("http://zorba.io/modules/store/dynamic/collections/ddl", false));
        prefixToNamespaceMap.put("dml", Pair.create("http://zorba.io/modules/store/dynamic/collections/dml", false));
        prefixToNamespaceMap.put("doc", Pair.create("http://zorba.io/modules/store/documents", false));
        prefixToNamespaceMap.put("err", Pair.create("http://www.w3.org/2005/xqt-errors", false));
        prefixToNamespaceMap.put("ex", Pair.create("http://zorba.io/modules/info-extraction", false));
        prefixToNamespaceMap.put("excel-lookup", Pair.create("http://zorba.io/modules/excel/lookup", false));
        prefixToNamespaceMap.put("excel-datetime", Pair.create("http://zorba.io/modules/excel/datetime", false));
        prefixToNamespaceMap.put("excel-engineering", Pair.create("http://zorba.io/modules/excel/engineering", false));
        prefixToNamespaceMap.put("excel-err", Pair.create("http://zorba.io/modules/excel/errors", false));
        prefixToNamespaceMap.put("excel-information", Pair.create("http://zorba.io/modules/excel/information", false));
        prefixToNamespaceMap.put("excel-logical", Pair.create("http://zorba.io/modules/excel/logical", false));
        prefixToNamespaceMap.put("excel-math", Pair.create("http://zorba.io/modules/excel/math", false));
        prefixToNamespaceMap.put("excel-math-sumproduct", Pair.create("http://zorba.io/modules/excel/math-sumproduct", false));
        prefixToNamespaceMap.put("excel-statistical", Pair.create("http://zorba.io/modules/excel/statistical", false));
        prefixToNamespaceMap.put("excel-statistical-zorba", Pair.create("http://zorba.io/modules/excel/statistical-zorba", false));
        prefixToNamespaceMap.put("excel-text", Pair.create("http://zorba.io/modules/excel/text", false));
        prefixToNamespaceMap.put("expath-err", Pair.create("http://expath.org/ns/error", false));
        prefixToNamespaceMap.put("expath-http", Pair.create("http://expath.org/ns/http-client", false));
        prefixToNamespaceMap.put("exref", Pair.create("http://www.ecb.int/vocabulary/2002-08-01/eurofxref", false));
        prefixToNamespaceMap.put("f", Pair.create("http://zorba.io/features", false));
        prefixToNamespaceMap.put("fetch", Pair.create("http://zorba.io/modules/fetch", false));
        prefixToNamespaceMap.put("file", Pair.create("http://expath.org/ns/file", false));
        prefixToNamespaceMap.put("fn", Pair.create("http://www.w3.org/2005/xpath-functions", true));
        prefixToNamespaceMap.put("ft", Pair.create("http://zorba.io/modules/full-text", false));
        prefixToNamespaceMap.put("functx", Pair.create("http://www.functx.com", false));
        prefixToNamespaceMap.put("geo", Pair.create("http://expath.org/ns/geo", false));
        prefixToNamespaceMap.put("geo-err", Pair.create("http://expath.org/ns/error", false));
        prefixToNamespaceMap.put("geoproj", Pair.create("http://zorba.io/modules/geoproj", false));
        prefixToNamespaceMap.put("gml", Pair.create("http://www.opengis.net/gml", false));
        prefixToNamespaceMap.put("hash", Pair.create("http://zorba.io/modules/hash", false));
        prefixToNamespaceMap.put("hmac", Pair.create("http://zorba.io/modules/hmac", false));
        prefixToNamespaceMap.put("html", Pair.create("http://www.zorba-xquery.com/modules/converters/html", false));
        prefixToNamespaceMap.put("http", Pair.create("http://zorba.io/modules/http-client", false));
        prefixToNamespaceMap.put("http-old", Pair.create("http://www.zorba-xquery.com/modules/http-client", false));
        prefixToNamespaceMap.put("http-wrapper", Pair.create("http://zorba.io/modules/http-client-wrapper", false));
        prefixToNamespaceMap.put("icddl", Pair.create("http://zorba.io/modules/store/static/integrity-constraints/ddl", false));
        prefixToNamespaceMap.put("icdml", Pair.create("http://zorba.io/modules/store/static/integrity-constraints/dml", false));
        prefixToNamespaceMap.put("iddl", Pair.create("http://zorba.io/modules/store/static/indexes/ddl", false));
        prefixToNamespaceMap.put("idml", Pair.create("http://zorba.io/modules/store/static/indexes/dml", false));
        prefixToNamespaceMap.put("ierr", Pair.create("http://zorba.io/modules/image/error", false));
        prefixToNamespaceMap.put("imap", Pair.create("http://www.zorba-xquery.com/modules/email/imap", false));
        prefixToNamespaceMap.put("item", Pair.create("http://zorba.io/modules/item", false));
        prefixToNamespaceMap.put("jdbc", Pair.create("http://www.zorba-xquery.com/modules/jdbc", false));
        prefixToNamespaceMap.put("jerr", Pair.create("http://jsoniq.org/errors", false));
        prefixToNamespaceMap.put("jn", Pair.create("http://jsoniq.org/functions", false));
        prefixToNamespaceMap.put("js", Pair.create("http://jsoniq.org/types", false));
        prefixToNamespaceMap.put("jsv", Pair.create("http://jsound.io/modules/validate", false));
        prefixToNamespaceMap.put("jx", Pair.create("http://zorba.io/modules/json-xml", false));
        prefixToNamespaceMap.put("libjn", Pair.create("http://jsoniq.org/function-library", false));
        prefixToNamespaceMap.put("man", Pair.create("http://zorba.io/modules/image/manipulation", false));
        prefixToNamespaceMap.put("jsound-map", Pair.create("http://jsound.io/modules/validate/map", false));
        prefixToNamespaceMap.put("map", Pair.create("http://zorba.io/modules/unordered-maps", false));
        prefixToNamespaceMap.put("math", Pair.create("http://www.w3.org/2005/xpath-functions/math", false));
        prefixToNamespaceMap.put("node", Pair.create("http://zorba.io/modules/node", false));
        prefixToNamespaceMap.put("normalization", Pair.create("http://zorba.io/modules/data-cleaning/normalization", false));
        prefixToNamespaceMap.put("nosql", Pair.create("http://zorba.io/modules/oracle-nosqldb", false));
        prefixToNamespaceMap.put("np", Pair.create("http://zorba.io/modules/node-position", false));
        prefixToNamespaceMap.put("oauth", Pair.create("http://www.zorba-xquery.com/modules/oauth/client", false));
        prefixToNamespaceMap.put("oerr", Pair.create("http://www.zorba-xquery.com/modules/oauth/error", false));
        prefixToNamespaceMap.put("op", Pair.create("http://zorba.io/options/features", false));
        prefixToNamespaceMap.put("p", Pair.create("http://zorba.io/modules/process", false));
        prefixToNamespaceMap.put("paint", Pair.create("http://zorba.io/modules/image/paint", false));
        prefixToNamespaceMap.put("qddl", Pair.create("http://zorba.io/modules/store/dynamic/collections/ddl", false));
        prefixToNamespaceMap.put("qdml", Pair.create("http://zorba.io/modules/store/dynamic/collections/dml", false));
        prefixToNamespaceMap.put("queue", Pair.create("http://zorba.io/modules/queue", false));
        prefixToNamespaceMap.put("r", Pair.create("http://zorba.io/modules/random", false));
        prefixToNamespaceMap.put("ra", Pair.create("http://zorba.io/modules/random", false));
        prefixToNamespaceMap.put("read-pdf", Pair.create("http://www.zorba-xquery.com/modules/read-pdf", false));
        prefixToNamespaceMap.put("reflection", Pair.create("http://zorba.io/modules/reflection", false));
        prefixToNamespaceMap.put("s", Pair.create("http://zorba.io/modules/sqlite", false));
        prefixToNamespaceMap.put("schema", Pair.create("http://zorba.io/modules/schema", false));
        prefixToNamespaceMap.put("schema-tools", Pair.create("http://www.zorba-xquery.com/modules/schema-tools", false));
        prefixToNamespaceMap.put("sctx", Pair.create("http://zorba.io/modules/sctx", false));
        prefixToNamespaceMap.put("set", Pair.create("http://zorba.io/modules/data-cleaning/set-similarity", false));
        prefixToNamespaceMap.put("simc", Pair.create("http://zorba.io/modules/data-cleaning/character-based-string-similarity", false));
        prefixToNamespaceMap.put("simh", Pair.create("http://zorba.io/modules/data-cleaning/hybrid-string-similarity", false));
        prefixToNamespaceMap.put("simp", Pair.create("http://zorba.io/modules/data-cleaning/phonetic-string-similarity", false));
        prefixToNamespaceMap.put("simt", Pair.create("http://zorba.io/modules/data-cleaning/token-based-string-similarity", false));
        prefixToNamespaceMap.put("smtp", Pair.create("http://www.zorba-xquery.com/modules/email/smtp", false));
        prefixToNamespaceMap.put("stack", Pair.create("http://zorba.io/modules/stack", false));
        prefixToNamespaceMap.put("string", Pair.create("http://zorba.io/modules/string", false));
        prefixToNamespaceMap.put("svg", Pair.create("http://www.w3.org/2000/svg", false));
        prefixToNamespaceMap.put("system", Pair.create("http://zorba.io/modules/system", false));
        prefixToNamespaceMap.put("tidy", Pair.create("http://www.zorba-xquery.com/modules/converters/html", false));
        prefixToNamespaceMap.put("uri", Pair.create("http://zorba.io/modules/uri", false));
        prefixToNamespaceMap.put("util-jvm", Pair.create("http://www.zorba-xquery.com/modules/util-jvm", false));
        prefixToNamespaceMap.put("ver", Pair.create("http://zorba.io/options/versioning", false));
        prefixToNamespaceMap.put("w3c-ddl", Pair.create("http://zorba.io/modules/store/dynamic/collections/w3c/ddl", false));
        prefixToNamespaceMap.put("w3c-dml", Pair.create("http://zorba.io/modules/store/dynamic/collections/w3c/dml", false));
        prefixToNamespaceMap.put("x", Pair.create("http://zorba.io/modules/xml", false));
        prefixToNamespaceMap.put("xqd", Pair.create("http://zorba.io/modules/xqdoc", false));
        prefixToNamespaceMap.put("xs", Pair.create("http://www.w3.org/2001/XMLSchema", true));
        prefixToNamespaceMap.put("xsl-fo", Pair.create("http://zorba.io/modules/xsl-fo", false));
        prefixToNamespaceMap.put("xslt", Pair.create("http://www.zorba-xquery.com/modules/languages/xslt", false));
        prefixToNamespaceMap.put("yahoo", Pair.create("urn:yahoo:cap", false));
        prefixToNamespaceMap.put("zerr", Pair.create("http://zorba.io/errors", false));
        prefixToNamespaceMap.put("zorba-math", Pair.create("http://zorba.io/modules/math", false));
        prefixToNamespaceMap.put("zq", Pair.create("http://zorba.io/modules/zorba-query", false));
    }
}
