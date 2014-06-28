/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.annotator.variable;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;

public class UnresolvedVariableChecker {

    public void check(XQueryVarRef varRef, AnnotationHolder holder) {
        XQueryVarName varName = varRef.getVarName();
        if (varName != null && (varRef.getReference() == null || varRef.getReference().resolve() == null)) {
            holder.createErrorAnnotation(varName, "Cannot resolve variable '" + varName.getText() + "'")
                    .setHighlightType(ProblemHighlightType.ERROR);
        }
    }
}
