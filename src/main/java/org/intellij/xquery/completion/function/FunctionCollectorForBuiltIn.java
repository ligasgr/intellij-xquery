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

package org.intellij.xquery.completion.function;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.project.Project;
import org.intellij.xquery.psi.XQueryFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.intellij.xquery.completion.XQueryCompletionContributor.BIF_PRIORITY;
import static org.intellij.xquery.completion.XQueryCompletionContributor.prioritized;
import static org.intellij.xquery.completion.function.BuiltInFunctionSignatureToLookupElementConverter.convert;

public class FunctionCollectorForBuiltIn {
    public static List<LookupElement> getLookupItems(XQueryFile file) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        Map<String, String> functionPrefixToNamespaceMap = file.getFunctionPrefixToNamespaceMap();
        Set<String> availableNamespaces = new HashSet<String>(functionPrefixToNamespaceMap.values());
        for (String namespace : availableNamespaces) {
            if (file.isPredeclaredNamespace(namespace)) {
                lookupItems.addAll(getLookupItemsForNamespace(getFunctionsSignatures(file, namespace),
                        getMatchingPrefixes(namespace, functionPrefixToNamespaceMap)));
            }
        }
        return lookupItems;
    }

    private static Collection<BuiltInFunctionSignature> getFunctionsSignatures(XQueryFile file, String namespace) {
        return file.getBuiltInFunctionTable().getFunctionsSignatures(namespace);
    }

    private static List<String> getMatchingPrefixes(String namespace, Map<String,
            String> functionPrefixToNamespaceMap) {
        List<String> matchingPrefixes = new ArrayList<String>();
        for (String prefix : functionPrefixToNamespaceMap.keySet()) {
            if (functionPrefixToNamespaceMap.get(prefix).equals(namespace)) {
                matchingPrefixes.add(prefix);
            }
        }
        return matchingPrefixes;
    }

    private static List<LookupElement> getLookupItemsForNamespace(Collection<BuiltInFunctionSignature> functions,
                                                                  List<String> matchingPrefixes) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        for (BuiltInFunctionSignature function : functions) {
            for (String prefix : matchingPrefixes) {
                if (prefix != null) {
                    lookupItems.add(prioritized(convert(function, prefix + ":" + function.getName()), BIF_PRIORITY));
                } else {
                    lookupItems.add(prioritized(convert(function, function.getName()), BIF_PRIORITY));
                }
            }
        }
        return lookupItems;
    }
}
