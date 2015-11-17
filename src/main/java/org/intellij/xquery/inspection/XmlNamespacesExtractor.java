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

package org.intellij.xquery.inspection;

import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryXmlTagName;

import java.util.HashSet;
import java.util.Set;

public class XmlNamespacesExtractor {

    public Set<String> getNamespacesUsedByXml(XQueryFile xQueryFile) {
        Set<String> usedNamespaces = new HashSet<String>();
        for (XQueryXmlTagName tagName : xQueryFile.getXmlTagNames()) {
            if (tagName.getXmlTagNamespace() != null) {
                usedNamespaces.add(xQueryFile.mapFunctionPrefixToNamespace(tagName.getXmlTagNamespace().getText()));
            }
        }

        return usedNamespaces;
    }
}