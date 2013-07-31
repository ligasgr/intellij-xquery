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

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.scope.BaseScopeProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.XQueryIcons;
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
        checkedNamespace = (myElement.getFunctionName() != null && myElement.getFunctionName().getFunctionNamespace() != null) ? myElement.getFunctionName().getFunctionNamespace().getText() : null;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        if (myElement.getFunctionName() != null) {
            Map<String, ResolveResult> functionDeclarationResults = getFunctionDeclarationReferences(file, new HashMap<String, ResolveResult>(), checkedNamespace);

            Map<String, ResolveResult> externalFunctionDeclarationResults = getExternalFunctionDeclarationReferences(file, functionDeclarationResults);

            return externalFunctionDeclarationResults.values().toArray(new ResolveResult[externalFunctionDeclarationResults.size()]);
        }
        return new ResolveResult[0];
    }

    private Map<String, ResolveResult> getExternalFunctionDeclarationReferences(XQueryFile file, Map<String, ResolveResult> results) {
        final String referenceNamespace = myElement.getFunctionName().getFunctionNamespace() != null ? myElement.getFunctionName().getFunctionNamespace().getText() : null;

        if (referenceNamespace != null) {
            addReferencesFromImportedModules(file, results, referenceNamespace);
        }
        return results;
    }

    private void addReferencesFromImportedModules(XQueryFile file, Map<String, ResolveResult> results, String referenceNamespace) {
        for (XQueryModuleImport moduleImport : file.getModuleImports()) {
            if (referenceNamespace.equals(moduleImport.getNamespaceName().getText())) {
                addReferencesFromAllFilesInImport(moduleImport, results);
            }
        }
    }

    private void addReferencesFromAllFilesInImport(XQueryModuleImport moduleImport, Map<String, ResolveResult> results) {
        List<XQueryModuleImportPath> importPaths = moduleImport.getModuleImportPathList();
        for (XQueryModuleImportPath path : importPaths) {
            if (path.getReference() != null) {
                XQueryFile xQueryFile = (XQueryFile) path.getReference().resolve();
                if (xQueryFile != null) {
                    getFunctionDeclarationReferences(xQueryFile, results, xQueryFile.getModuleNamespaceName().getText());
                }
            }
        }
    }

    private Map<String, ResolveResult> getFunctionDeclarationReferences(XQueryFile file, Map<String, ResolveResult> results, String checkedNamespace) {

        for (XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                addReferenceIfNotAlreadyAdded(results, functionDecl, checkedNamespace);
            }
        }
        return results;
    }

    private boolean functionNameExists(XQueryFunctionDecl functionDecl) {
        return functionDecl.getFunctionName() != null && functionDecl.getFunctionName().getTextLength() > 0;
    }

    private void addReferenceIfNotAlreadyAdded(Map<String, ResolveResult> results, XQueryFunctionDecl functionDecl, String checkedNamespace) {
        String key = functionDecl.getFunctionName().getText();
        if (!results.containsKey(key)) {
            addElementToResultsIfMatching(results, functionDecl.getFunctionName(), functionDecl.getFunctionName(), key, checkedNamespace);
        }
    }

    private void addElementToResultsIfMatching(Map<String, ResolveResult> results, PsiElement referenceTarget, XQueryFunctionName compareFunctionName, String key, String checkedNamespace) {
        final String functionDeclNamespace = compareFunctionName.getFunctionNamespace() != null ? compareFunctionName.getFunctionNamespace().getText() : null;
        final String referenceNamespace = checkedNamespace;
        final String functionDeclLocalName = compareFunctionName.getFunctionLocalName().getText();
        final String referenceLocalName = myElement.getFunctionName().getFunctionLocalName().getText();
        boolean namespacesAndLocalNamesMatch = referenceNamespace != null && referenceNamespace.equals(functionDeclNamespace) && referenceLocalName.equals(functionDeclLocalName);
        boolean namespacesAreEmptyAndLocalNamesMatch = referenceNamespace == null && functionDeclNamespace == null && functionDeclLocalName.equals(referenceLocalName);

        if (namespacesAndLocalNamesMatch) {
            results.put(key, new PsiElementResolveResult(referenceTarget));
        } else if (namespacesAreEmptyAndLocalNamesMatch) {
            results.put(key, new PsiElementResolveResult(referenceTarget));
        }
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
        XQueryFile file = (XQueryFile) myElement.getContainingFile();
        Map<String, LookupElement> variants = new HashMap<String, LookupElement>();

        VariableVariantsScopeProcessor processor = new VariableVariantsScopeProcessor(variants);
        PsiTreeUtil.treeWalkUp(processor, myElement, null, ResolveState.initial());

        return getVariantsFromFunctionDeclarations(file, variants);
    }

    private Object[] getVariantsFromFunctionDeclarations(XQueryFile file, Map<String, LookupElement> variants) {
        for (final XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionNameExists(functionDecl)) {
                addVariantIfNotAlreadyAdded(variants, functionDecl);
            }
        }
        return variants.values().toArray();
    }

    private void addVariantIfNotAlreadyAdded(Map<String, LookupElement> variants, XQueryFunctionDecl functionDecl) {
        String key = functionDecl.getFunctionName().getText();
        if (!variants.containsKey(key)) {
            variants.put(key, createLookupElement(functionDecl.getFunctionName(), key));
        }
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        myElement.getFunctionName().getFunctionLocalName().replace(getUpdatedRef(newElementName).getFunctionLocalName());
        return myElement;
    }

    private XQueryFunctionName getUpdatedRef(String newName) {
        XQueryFunctionName functionName = XQueryElementFactory.createFunctionReference(myElement.getProject(), "dummy", newName);
        return functionName;
    }

    private boolean isNotFunctionCall(PsiElement psiElement) {
        return !(psiElement.getParent() instanceof XQueryFunctionCall || psiElement.getParent() instanceof XQueryNamedFunctionRef);
    }

    private LookupElement createLookupElement(PsiElement psiElement, String key) {
        return LookupElementBuilder.create(psiElement, key)
                .withIcon(XQueryIcons.FILE)
                .withTypeText(psiElement.getContainingFile().getName())
                .withInsertHandler(new InsertHandler() {
                    @Override
                    public void handleInsert(final InsertionContext context, LookupElement item) {
                        final Document document = context.getEditor().getDocument();
                        document.insertString(context.getSelectionEndOffset(), "()");
                    }
                });
    }

    private class VariableVariantsScopeProcessor extends BaseScopeProcessor {
        private final Map<String, LookupElement> myResult;

        public VariableVariantsScopeProcessor(Map<String, LookupElement> result) {
            myResult = result;
        }

        @Override
        public boolean execute(@NotNull PsiElement psiElement, ResolveState resolveState) {
            boolean elementIsAGoodCandidate = !psiElement.equals(myElement)
                    && psiElement instanceof XQueryFunctionName
                    && isNotFunctionCall(psiElement);

            if (elementIsAGoodCandidate) {
                addElementIfNotAlreadyAdded(psiElement);
            }
            return true;
        }

        private void addElementIfNotAlreadyAdded(PsiElement psiElement) {
            String key = psiElement.getText();
            if (!myResult.containsKey(key)) {
                myResult.put(key, createLookupElement(psiElement, key));
            }
        }
    }

}
