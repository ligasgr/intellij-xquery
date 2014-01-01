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

package org.intellij.xquery.reference.variable;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.BaseScopeProcessor;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;
import org.jetbrains.annotations.NotNull;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;

/**
 * User: ligasgr
 * Date: 14/08/13
 * Time: 14:41
 */
public class VariableReferenceScopeProcessor extends BaseScopeProcessor {
    private XQueryVarName result;
    private XQueryVarRef myElement;

    public VariableReferenceScopeProcessor(XQueryVarRef myElement) {
        this.myElement = myElement;
    }

    public XQueryVarName getResult() {
        return result;
    }

    @Override
    public boolean execute(@NotNull PsiElement element, ResolveState state) {
        boolean elementIsGoodCandidate = !element.equals(myElement) && element instanceof XQueryVarName &&
                !(element.getParent() instanceof XQueryVarRef);
        if (elementIsGoodCandidate) {
            XQueryQName<XQueryVarName> source = aXQueryQName(myElement.getVarName()).build();
            XQueryQName<XQueryVarName> checkedQName = aXQueryQName((XQueryVarName) element).build();
            if (source.equals(checkedQName)) {
                result = checkedQName.getNamedObject();
                return false;
            }
        }
        return true;
    }
}
