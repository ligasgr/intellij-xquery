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

package org.intellij.xquery.inspection.imports;

import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleImport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnusedImportsFinder {
    private final VariableNamespacesExtractor variableNamespacesExtractor;
    private final FunctionNamespacesExtractor functionNamespacesExtractor;

    public UnusedImportsFinder(FunctionNamespacesExtractor functionNamespacesExtractor, VariableNamespacesExtractor
            variableNamespacesExtractor) {
        this.functionNamespacesExtractor = functionNamespacesExtractor;
        this.variableNamespacesExtractor = variableNamespacesExtractor;
    }

    public Collection<XQueryModuleImport> getUnusedImports(XQueryFile xQueryFile) {
        List<XQueryModuleImport> unusedImports = new ArrayList<XQueryModuleImport>();
        Set<String> usedNamespaces = getUsedNamespaces(xQueryFile);
        for (XQueryModuleImport moduleImport : xQueryFile.getModuleImports()) {
            if (moduleImportIsUnused(usedNamespaces, moduleImport)) {
                unusedImports.add(moduleImport);
            }
        }

        return unusedImports;
    }

    private boolean moduleImportIsUnused(Set<String> usedNamespaces, XQueryModuleImport moduleImport) {
        return ! moduleImportIsUsed(moduleImport, usedNamespaces);
    }

    boolean moduleImportIsUsed(XQueryModuleImport moduleImport, Set<String> usedNamespaces) {
        String importedNamespace = moduleImport.getNamespace();
        return usedNamespaces.contains(importedNamespace);
    }

    Set<String> getUsedNamespaces(XQueryFile xQueryFile) {
        Set<String> namespaces = new HashSet<String>();
        namespaces.addAll(functionNamespacesExtractor.getNamespacesUsedByFunctions(xQueryFile));
        namespaces.addAll(variableNamespacesExtractor.getNamespacesUsedByVariables(xQueryFile));
        return namespaces;
    }
}