/*
 * Copyright 2013 Grzegorz Ligas
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

package org.intellij.xquery.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 22:16
 */
public class XQueryFunctionReference extends PsiReferenceBase<XQueryFunctionCall> implements PsiPolyVariantReference {

    private final String checkedNamespace;

    public XQueryFunctionReference(@NotNull XQueryFunctionCall element, TextRange textRange) {
        super(element, textRange);
        checkedNamespace = (myElement.getFunctionName() != null && myElement.getFunctionName().getFunctionNamespace()
                != null) ? myElement.getFunctionName().getFunctionNamespace().getText() : null;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return new XQueryFunctionReferenceResolver(checkedNamespace, myElement).multiResolve(incompleteCode);
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
        return new XQueryFunctionReferenceVariantsCollector(myElement).getVariants();
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        myElement.getFunctionName().getFunctionLocalName().replace(getUpdatedRef(newElementName).getFunctionLocalName
                ());
        return myElement;
    }

    private XQueryFunctionName getUpdatedRef(String newName) {
        XQueryFunctionName functionName = XQueryElementFactory.createFunctionReference(myElement.getProject(),
                "dummy", newName);
        return functionName;
    }
}
