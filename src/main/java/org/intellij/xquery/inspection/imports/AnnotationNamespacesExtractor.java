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

import org.intellij.xquery.psi.XQueryAnnotation;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryPrefix;

import java.util.HashSet;
import java.util.Set;

public class AnnotationNamespacesExtractor {

    public Set<String> getNamespacesUsedByAnnotations(XQueryFile xQueryFile) {
        Set<String> usedNamespaces = new HashSet<String>();
        for (XQueryAnnotation annotation : xQueryFile.getAnnotations()) {
            XQueryPrefix namespacePrefix = annotation.getAnnotationName().getPrefix();
            if (namespacePrefix != null) {
                usedNamespaces.add(xQueryFile.mapFunctionPrefixToNamespace(namespacePrefix.getText()));
            }
        }

        return usedNamespaces;
    }
}