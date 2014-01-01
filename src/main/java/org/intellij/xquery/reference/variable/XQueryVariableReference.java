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

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveResult;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.psi.XQueryElementFactory;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 22:16
 */
public class XQueryVariableReference extends PsiReferenceBase<XQueryVarRef> implements PsiPolyVariantReference {

    private XQueryVariableReferenceResolver variableReferenceResolver;

    public XQueryVariableReference(@NotNull XQueryVarRef element, TextRange textRange) {
        super(element, textRange);
        variableReferenceResolver = new XQueryVariableReferenceResolver(myElement);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return variableReferenceResolver.getResolutionResults();
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        myElement.getVarName().getVarLocalName().replace(getUpdatedRef(newElementName).getVarLocalName());
        return myElement;
    }

    private XQueryVarName getUpdatedRef(String newName) {
        XQueryVarName varName = XQueryElementFactory.createVariableReference(myElement.getProject(), newName);
        return varName;
    }
}
