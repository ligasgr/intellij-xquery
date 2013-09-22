/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.icons.XQueryIcons;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.*;
import org.intellij.xquery.psi.impl.XQueryPsiImplUtil;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;
import static org.intellij.xquery.psi.XQueryUtil.getReferencesToExistingFilesInImport;

/**
 * User: ligasgr
 * Date: 14/08/13
 * Time: 22:28
 */
public class XQueryVariableReferenceForAutoCompletionCollector {

    private XQueryVarRef sourceOfReference;
    private List<XQueryQName<XQueryVarName>> proposedReferences;

    public XQueryVariableReferenceForAutoCompletionCollector(XQueryVarRef sourceOfReference) {
        this.sourceOfReference = sourceOfReference;
    }

    public Object[] getReferencesForAutoCompletion() {
        XQueryFile file = (XQueryFile) sourceOfReference.getContainingFile();
        proposedReferences = new LinkedList<XQueryQName<XQueryVarName>>();
        addProposedReferencesFromLocalScopes();
        addProposedReferencesFromFile(file);
        addProposedReferencesFromModuleImports(file);
        return convertToLookupElements(proposedReferences);
    }

    private void addProposedReferencesFromLocalScopes() {
        VariableVariantsScopeProcessor processor = new VariableVariantsScopeProcessor();
        PsiTreeUtil.treeWalkUp(processor, sourceOfReference, null, ResolveState.initial());
        proposedReferences.addAll(processor.getProposedReferences());
    }

    private void addProposedReferencesFromFile(XQueryFile file) {
        for (final XQueryVarDecl varDecl : file.getVariableDeclarations()) {
            if (variableNameExists(varDecl)) {
                XQueryQName<XQueryVarName> qName = aXQueryQName(varDecl.getVarName()).build();
                addProposedReferenceIfNotAlreadyAdded(qName);
            }
        }
    }

    private boolean variableNameExists(XQueryVarDecl variableDeclaration) {
        return variableDeclaration.getVarName() != null && variableDeclaration.getVarName().getTextLength() > 0;
    }

    private void addProposedReferenceIfNotAlreadyAdded(XQueryQName<XQueryVarName> qName) {
        if (!proposedReferences.contains(qName)) {
            proposedReferences.add(qName);
        }
    }

    private void addProposedReferencesFromModuleImports(XQueryFile file) {
        for (XQueryModuleImport moduleImport : file.getModuleImports()) {
            if (moduleImport.getNamespaceName() != null) {
                String targetPrefix = moduleImport.getNamespaceName().getName();
                addProposedReferencesFromImport(targetPrefix, moduleImport);
            }
        }
    }

    private void addProposedReferencesFromImport(String targetPrefix, XQueryModuleImport moduleImport) {
        for (XQueryFile file : getReferencesToExistingFilesInImport(moduleImport)) {
            addProposedReferencesFromImportedFile(targetPrefix, file);
        }
    }

    private void addProposedReferencesFromImportedFile(String targetPrefix, XQueryFile file) {
        for (final XQueryVarDecl variableDeclaration : file.getVariableDeclarations()) {
            if (variableNameExists(variableDeclaration) && XQueryPsiImplUtil.variableIsPublic(variableDeclaration)) {
                XQueryQName<XQueryVarName> qName = aXQueryQName(variableDeclaration.getVarName()).withPrefix(targetPrefix)
                        .build();
                addProposedReferenceIfNotAlreadyAdded(qName);
            }
        }
    }

    private LookupElement[] convertToLookupElements(List<XQueryQName<XQueryVarName>> proposedReferences) {
        LookupElement[] lookupElements = new LookupElement[proposedReferences.size()];
        for (int i = 0; i < proposedReferences.size(); i++) {
            lookupElements[i] = convertToLookupElement(proposedReferences.get(i));
        }
        return lookupElements;
    }

    private LookupElement convertToLookupElement(XQueryQName<XQueryVarName> qName) {
        XQueryVarName variableName = qName.getNamedObject();
        return createLookupElement(variableName, qName.getTextRepresentation());
    }

    private LookupElement createLookupElement(XQueryVarName psiElement, String key) {
        Icon icon = XQueryIcons.VARIABLE_ICON;
        String typeText = "item()*";
        if (psiElement.getParent() instanceof XQueryParam) {
            icon = AllIcons.Nodes.Parameter;
            XQueryParam param = (XQueryParam) psiElement.getParent();
            if (param.getTypeDeclaration() != null) {
                typeText = param.getTypeDeclaration().getSequenceType().getText();
            }
        }
        if (psiElement.getParent() instanceof XQueryVarDecl) {
            icon = AllIcons.Nodes.Field;
            XQueryVarDecl varDecl = (XQueryVarDecl) psiElement.getParent();
            if (varDecl.getTypeDeclaration() != null) {
                typeText = varDecl.getTypeDeclaration().getSequenceType().getText();
            }
        }
        return LookupElementBuilder.create(key)
                .withIcon(icon)
                .withTypeText(typeText);
    }
}
