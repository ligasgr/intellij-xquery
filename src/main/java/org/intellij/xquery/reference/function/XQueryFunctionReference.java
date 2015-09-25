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

package org.intellij.xquery.reference.function;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveResult;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionInvocation;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static org.intellij.xquery.psi.XQueryElementFactory.createFunctionReference;

public class XQueryFunctionReference extends PsiReferenceBase<XQueryFunctionInvocation> implements
        PsiPolyVariantReference {

    private XQueryFunctionReferenceResolver functionReferenceResolver;

    public XQueryFunctionReference(@NotNull XQueryFunctionInvocation element, TextRange textRange) {
        super(element, textRange);
        functionReferenceResolver = new XQueryFunctionReferenceResolver(myElement);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<XQueryFunctionName> matchingFunctionNames = functionReferenceResolver.getResolutionResults();
        return convertToResolveResults(matchingFunctionNames);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        List<XQueryFunctionName> matchingFunctionNames = functionReferenceResolver.getResolutionResults();
        List<XQueryFunctionName> filtered = filterByArity(myElement.getArity(), matchingFunctionNames);
        if (filtered.size() == 1) {
            return filtered.get(0);
        } else if (isBuiltInFunction(myElement)) {
            return getElement().getFunctionName();
        } else {
            return null;
        }
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        if (myElement.getFunctionName().getFunctionLocalName() != null)
            myElement.getFunctionName().getFunctionLocalName()
                    .replace(getUpdatedRef(newElementName).getFunctionLocalName());
        return myElement;
    }

    @NotNull
    private XQueryFunctionName getUpdatedRef(String newName) {
        return createFunctionReference(myElement.getProject(), "dummy", newName);
    }

    private ResolveResult[] convertToResolveResults(List<XQueryFunctionName> resolveResults) {
        ResolveResult[] convertedResults = new ResolveResult[resolveResults.size()];
        for (int i = 0; i < resolveResults.size(); i++) {
            convertedResults[i] = new PsiElementResolveResult(resolveResults.get(i));
        }
        return convertedResults;
    }

    private List<XQueryFunctionName> filterByArity(int arity, List<XQueryFunctionName> matchingFunctionNames) {
        List<XQueryFunctionName> matchingArityFunctionNames = new ArrayList<XQueryFunctionName>();
        for (XQueryFunctionName functionName : matchingFunctionNames) {
            XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
            if (functionDeclaration.getArity() == arity) {
                matchingArityFunctionNames.add(functionName);
            }
        }
        return matchingArityFunctionNames;
    }

    private boolean isBuiltInFunction(XQueryFunctionInvocation functionInvocation) {
        XQueryFunctionName functionName = functionInvocation.getFunctionName();
        XQueryFile file = (XQueryFile) functionName.getContainingFile();
        return file.isBuiltInFunction(functionName);
    }
}
