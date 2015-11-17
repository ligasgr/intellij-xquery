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

package org.intellij.xquery.annotator.xml;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;
import org.intellij.xquery.psi.XQueryXmlTagNamespace;

public class UnresolvedXmlNamespaceChecker {

    public void check(XQueryXmlTagNamespace xmlTagNamespace, AnnotationHolder holder) {
        if (xmlTagNamespace.getReference() == null || xmlTagNamespace.getReference().resolve() == null) {
            holder.createErrorAnnotation(xmlTagNamespace, "Cannot resolve namespace '" + xmlTagNamespace.getText() + "'")
                    .setHighlightType(ProblemHighlightType.ERROR);
        }
    }
}
