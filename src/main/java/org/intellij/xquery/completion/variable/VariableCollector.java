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

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.icons.XQueryIcons;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.intellij.xquery.psi.XQueryParam;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.impl.XQueryPsiImplUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.intellij.xquery.completion.XQueryCompletionContributor.EXTERNAL_VARIABLES_PRIORITY;
import static org.intellij.xquery.completion.XQueryCompletionContributor.VARIABLES_PRIORITY;
import static org.intellij.xquery.completion.XQueryCompletionContributor.prioritized;
import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;
import static org.intellij.xquery.psi.XQueryUtil.getReferencesToExistingFilesInImport;

/**
 * User: ligasgr
 * Date: 14/08/13
 * Time: 22:28
 */
public class VariableCollector {

    private PsiElement sourceOfReference;
    private Set<XQueryQName<XQueryVarName>> collectedNames;
    private LinkedList<LookupElement> proposedReferences;

    public VariableCollector(PsiElement sourceOfReference) {
        this.sourceOfReference = sourceOfReference;
    }

    public List<LookupElement> getProposedLookUpItems() {
        XQueryFile file = (XQueryFile) sourceOfReference.getContainingFile();
        collectedNames = new HashSet<XQueryQName<XQueryVarName>>();
        proposedReferences = new LinkedList<LookupElement>();
        addProposedReferencesFromLocalScopes();
        addProposedReferencesFromFile(file);
        addProposedReferencesFromModuleImports(file);
        return proposedReferences;
    }

    private void addProposedReferencesFromLocalScopes() {
        VariableVariantsScopeProcessor processor = new VariableVariantsScopeProcessor();
        PsiTreeUtil.treeWalkUp(processor, sourceOfReference, null, ResolveState.initial());
        List<XQueryQName<XQueryVarName>> references = processor.getProposedReferences();
        proposedReferences.addAll(convertToLookupElements(references, VARIABLES_PRIORITY));
        collectedNames.addAll(references);
    }

    private void addProposedReferencesFromFile(XQueryFile file) {
        for (final XQueryVarDecl varDecl : file.getVariableDeclarations()) {
            if (variableNameExists(varDecl)) {
                XQueryQName<XQueryVarName> qName = aXQueryQName(varDecl.getVarName()).build();
                addProposedReferenceIfNotAlreadyAdded(qName, VARIABLES_PRIORITY);
            }
        }
    }

    private boolean variableNameExists(XQueryVarDecl variableDeclaration) {
        return variableDeclaration.getVarName() != null && variableDeclaration.getVarName().getTextLength() > 0;
    }

    private void addProposedReferenceIfNotAlreadyAdded(XQueryQName<XQueryVarName> qName, int priority) {
        if (! collectedNames.contains(qName)) {
            collectedNames.add(qName);
            proposedReferences.add(convertToLookupElement(qName, priority));
        }
    }

    private void addProposedReferencesFromModuleImports(XQueryFile file) {
        for (XQueryModuleImport moduleImport : file.getModuleImports()) {
            if (moduleImport.getNamespacePrefix() != null) {
                String targetPrefix = moduleImport.getNamespacePrefix().getName();
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
            if (variableNameExists(variableDeclaration) && XQueryPsiImplUtil.isPublic(variableDeclaration)) {
                XQueryQName<XQueryVarName> qName = aXQueryQName(variableDeclaration.getVarName()).withPrefix
                        (targetPrefix)
                        .build();
                addProposedReferenceIfNotAlreadyAdded(qName, EXTERNAL_VARIABLES_PRIORITY);
            }
        }
    }

    private List<LookupElement> convertToLookupElements(List<XQueryQName<XQueryVarName>> proposedReferences, int priority) {
        List<LookupElement> lookupElements = new ArrayList<LookupElement>(proposedReferences.size());
        for (int i = 0; i < proposedReferences.size(); i++) {
            lookupElements.add(convertToLookupElement(proposedReferences.get(i), priority));
        }
        return lookupElements;
    }

    private LookupElement convertToLookupElement(XQueryQName<XQueryVarName> qName, int priority) {
        XQueryVarName variableName = qName.getNamedObject();
        return prioritized(createLookupElement(variableName, qName.getTextRepresentation()), priority);
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
        return LookupElementBuilder.create(psiElement, key)
                .withIcon(icon)
                .withTypeText(typeText)
                .withInsertHandler(new VariableInsertHandler())
                .withLookupString(psiElement.getVarLocalName().getText())
                ;
    }
}
