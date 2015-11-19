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

package org.intellij.xquery.reference.namespace.marklogic;

import com.intellij.openapi.util.Pair;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MarkLogicPredeclaredNamespaces extends XQuery30PredeclaredNamespaces {
    private static final Set<String> AVAILABLE_BY_DEFAULT_IN_MARK_LOGIC_EXTENDED_SYNTAX = ContainerUtil.set(
            "cts",
            "dbg",
            "exsl",
            "map",
            "sec",
            "sem",
            "spell",
            "xdmp",
            "prof",
            "rdf",
            "sc"
    );

    {
        prefixToNamespaceMap.put("cts", Pair.create("http://marklogic.com/cts", false));
        prefixToNamespaceMap.put("exsl", Pair.create("http://exslt.org/common", false));
        prefixToNamespaceMap.put("spell", Pair.create("http://marklogic.com/xdmp/spell", false));
        prefixToNamespaceMap.put("xdmp", Pair.create("http://marklogic.com/xdmp", false));
        prefixToNamespaceMap.put("admin", Pair.create("http://marklogic.com/xdmp/admin", false));
        prefixToNamespaceMap.put("alert", Pair.create("http://marklogic.com/xdmp/alert", false));
        prefixToNamespaceMap.put("cdict", Pair.create("http://marklogic.com/xdmp/custom-dictionary", false));
        prefixToNamespaceMap.put("cpf", Pair.create("http://marklogic.com/cpf", false));
        prefixToNamespaceMap.put("css", Pair.create("http://marklogic.com/cpf/css", false));
        prefixToNamespaceMap.put("cvt", Pair.create("http://marklogic.com/cpf/convert", false));
        prefixToNamespaceMap.put("dbg", Pair.create("http://marklogic.com/xdmp/debug", false));
        prefixToNamespaceMap.put("dbk", Pair.create("http://marklogic.com/cpf/docbook", false));
        prefixToNamespaceMap.put("dls", Pair.create("http://marklogic.com/xdmp/dls", false));
        prefixToNamespaceMap.put("dom", Pair.create("http://marklogic.com/cpf/domains", false));
        prefixToNamespaceMap.put("ec2", Pair.create("http://marklogic.com/ec2", false));
        prefixToNamespaceMap.put("excel", Pair.create("http://marklogic.com/cpf/excel", false));
        prefixToNamespaceMap.put("flexrep", Pair.create("http://marklogic.com/xdmp/flexible-replication", false));
        prefixToNamespaceMap.put("geo", Pair.create("http://marklogic.com/geospatial", false));
        prefixToNamespaceMap.put("geojson", Pair.create("http://marklogic.com/geospatial/geojson", false));
        prefixToNamespaceMap.put("georss", Pair.create("http://www.georss.org/georss", false));
        prefixToNamespaceMap.put("gml", Pair.create("http://www.opengis.net/gml", false));
        prefixToNamespaceMap.put("hadoop", Pair.create("http://marklogic.com/xdmp/hadoop", false));
        prefixToNamespaceMap.put("infodev", Pair.create("http://marklogic.com/appservices/infostudio/dev", false));
        prefixToNamespaceMap.put("info", Pair.create("http://marklogic.com/appservices/infostudio", false));
        prefixToNamespaceMap.put("json", Pair.create("http://marklogic.com/xdmp/json", false));
        prefixToNamespaceMap.put("kml", Pair.create("http://earth.google.com/kml/2.0", false));
        prefixToNamespaceMap.put("lnk", Pair.create("http://marklogic.com/cpf/links", false));
        prefixToNamespaceMap.put("map", Pair.create("http://marklogic.com/xdmp/map", false));
        prefixToNamespaceMap.put("mcgm", Pair.create("http://marklogic.com/geospatial/mcgm", false));
        prefixToNamespaceMap.put("msword", Pair.create("http://marklogic.com/cpf/msword", false));
        prefixToNamespaceMap.put("ooxml", Pair.create("http://marklogic.com/openxml", false));
        prefixToNamespaceMap.put("pdf", Pair.create("http://marklogic.com/cpf/pdf", false));
        prefixToNamespaceMap.put("p", Pair.create("http://marklogic.com/cpf/pipelines", false));
        prefixToNamespaceMap.put("pkg", Pair.create("http://marklogic.com/manage/package", false));
        prefixToNamespaceMap.put("pki", Pair.create("http://marklogic.com/xdmp/pki", false));
        prefixToNamespaceMap.put("plugin", Pair.create("http://marklogic.com/extension/plugin", false));
        prefixToNamespaceMap.put("ppt", Pair.create("http://marklogic.com/cpf/powerpoint", false));
        prefixToNamespaceMap.put("prof", Pair.create("http://marklogic.com/xdmp/profile", false));
        prefixToNamespaceMap.put("rdf", Pair.create("http://www.w3.org/1999/02/22-rdf-syntax-ns#", false));
        prefixToNamespaceMap.put("rest", Pair.create("http://marklogic.com/appservices/rest", false));
        prefixToNamespaceMap.put("sc", Pair.create("http://marklogic.com/xdmp/schema-components", false));
        prefixToNamespaceMap.put("search", Pair.create("http://marklogic.com/appservices/search", false));
        prefixToNamespaceMap.put("sec", Pair.create("http://marklogic.com/xdmp/security", false));
        prefixToNamespaceMap.put("sem", Pair.create("http://marklogic.com/semantics", false));
        prefixToNamespaceMap.put("spell", Pair.create("http://marklogic.com/xdmp/spell", false));
        prefixToNamespaceMap.put("temporal", Pair.create("http://marklogic.com/xdmp/temporal", false));
        prefixToNamespaceMap.put("thsr", Pair.create("http://marklogic.com/xdmp/thesaurus", false));
        prefixToNamespaceMap.put("tieredstorage", Pair.create("http://marklogic.com/xdmp/thesaurus", false));
        prefixToNamespaceMap.put("trgr", Pair.create("http://marklogic.com/xdmp/triggers", false));
        prefixToNamespaceMap.put("view", Pair.create("http://marklogic.com/xdmp/view", false));
        prefixToNamespaceMap.put("xhtml", Pair.create("http://marklogic.com/cpf/xhtml", false));
        prefixToNamespaceMap.put("xinc", Pair.create("http://marklogic.com/xinclude", false));
        prefixToNamespaceMap.put("xp", Pair.create("http://marklogic.com/xinclude/xpointer", false));
    }


    public Map<String, String> getPrefixToNamespaceMap(XQueryFile xQueryFile) {
        Map<String, String> defaultMap = super.getPrefixToNamespaceMap(xQueryFile);
        if (xQueryFile.versionIsNotMarklogicSpecific()) {
            return defaultMap;
        }
        return getAvailableByDefaultInMarkLogicExtendedSyntax(defaultMap);
    }

    protected Map<String, String> getAvailableByDefaultInMarkLogicExtendedSyntax(Map<String, String> defaultMap) {
        Map<String, String> result = new HashMap<String, String>(defaultMap);
        for (Map.Entry<String, Pair<String, Boolean>> entry : prefixToNamespaceMap.entrySet()) {
            if (AVAILABLE_BY_DEFAULT_IN_MARK_LOGIC_EXTENDED_SYNTAX.contains(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue().first);
            }
        }
        return result;
    }
}
