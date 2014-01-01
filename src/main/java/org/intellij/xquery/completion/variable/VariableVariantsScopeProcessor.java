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

package org.intellij.xquery.completion.variable;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.BaseScopeProcessor;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;

public class VariableVariantsScopeProcessor extends BaseScopeProcessor {

    private List<XQueryQName<XQueryVarName>> proposedReferences = new LinkedList<XQueryQName<XQueryVarName>>();

    public List<XQueryQName<XQueryVarName>> getProposedReferences() {
        return proposedReferences;
    }

    @Override
    public boolean execute(@NotNull PsiElement psiElement, ResolveState state) {
        boolean elementIsAGoodCandidate = psiElement instanceof XQueryVarName
                && !(psiElement.getParent() instanceof XQueryVarRef);

        if (elementIsAGoodCandidate) {
            XQueryQName<XQueryVarName> qName = aXQueryQName((XQueryVarName) psiElement).build();
            if (!proposedReferences.contains(qName)) {
                proposedReferences.add(qName);
            }
        }
        return true;
    }
}