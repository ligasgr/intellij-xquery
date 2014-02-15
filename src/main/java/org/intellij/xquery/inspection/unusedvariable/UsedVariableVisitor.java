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

package org.intellij.xquery.inspection.unusedvariable;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.intellij.xquery.psi.XQueryVarRef;

import java.util.ArrayList;
import java.util.List;

class UsedVariableVisitor extends PsiElementVisitor {
    private List<XQueryVarRef> variableReferences = new ArrayList<XQueryVarRef>();

    public List<XQueryVarRef> getVariableReferences() {
        return variableReferences;
    }

    @Override
    public void visitElement(PsiElement element) {
        if (element instanceof XQueryVarRef) {
            visitXQueryVarRef((XQueryVarRef) element);
        }
        super.visitElement(element);
        element.acceptChildren(this);
    }

    private void visitXQueryVarRef(XQueryVarRef varRef) {
        variableReferences.add(varRef);
    }
}
