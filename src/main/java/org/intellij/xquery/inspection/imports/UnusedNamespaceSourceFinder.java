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

package org.intellij.xquery.inspection.imports;

import org.intellij.xquery.inspection.FunctionNamespacesExtractor;
import org.intellij.xquery.inspection.PrefixExtractor;
import org.intellij.xquery.inspection.QNameConstructorNamespacesExtractor;
import org.intellij.xquery.inspection.VariableNamespacesExtractor;
import org.intellij.xquery.inspection.XmlNamespacesExtractor;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryNamespaceSource;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class UnusedNamespaceSourceFinder<T extends XQueryNamespaceSource> {
    private final FunctionNamespacesExtractor functionNamespacesExtractor = new FunctionNamespacesExtractor();
    private final VariableNamespacesExtractor variableNamespacesExtractor = new VariableNamespacesExtractor();
    private final PrefixExtractor prefixExtractor = new PrefixExtractor();
    private final QNameConstructorNamespacesExtractor qnameConstructorNamespacesExtractor = new QNameConstructorNamespacesExtractor();
    private final XmlNamespacesExtractor xmlNamespacesExtractor = new XmlNamespacesExtractor();

    public Collection<T> getUnusedNamespaceSources(XQueryFile xQueryFile) {
        List<T> unusedNamespaceSources = new ArrayList<T>();
        Set<String> usedNamespaces = getUsedNamespaces(xQueryFile);
        for (T moduleImport : getAllNamespaceSources(xQueryFile)) {
            if (namespaceSourceIsUnused(usedNamespaces, moduleImport)) {
                unusedNamespaceSources.add(moduleImport);
            }
        }

        return unusedNamespaceSources;
    }

    @NotNull
    protected abstract Collection<T> getAllNamespaceSources(XQueryFile xQueryFile);

    private boolean namespaceSourceIsUnused(Set<String> usedNamespaces, T namespaceSource) {
        return ! namespaceSourceIsUsed(namespaceSource, usedNamespaces);
    }

    boolean namespaceSourceIsUsed(T namespaceSource, Set<String> usedNamespaces) {
        String importedNamespace = namespaceSource.getNamespace();
        return usedNamespaces.contains(importedNamespace);
    }

    private Set<String> getUsedNamespaces(XQueryFile xQueryFile) {
        Set<String> namespaces = new HashSet<String>();
        namespaces.addAll(functionNamespacesExtractor.getNamespacesUsedByFunctions(xQueryFile));
        namespaces.addAll(variableNamespacesExtractor.getNamespacesUsedByVariables(xQueryFile));
        namespaces.addAll(prefixExtractor.getNamespacesUsedByPrefixes(xQueryFile));
        namespaces.addAll(qnameConstructorNamespacesExtractor.getNamespacesUsedByQNameConstructors(xQueryFile));
        namespaces.addAll(xmlNamespacesExtractor.getNamespacesUsedByXml(xQueryFile));
        return namespaces;
    }
}