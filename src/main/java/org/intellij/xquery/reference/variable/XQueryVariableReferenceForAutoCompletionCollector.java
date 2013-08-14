package org.intellij.xquery.reference.variable;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.psi.ResolveState;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.*;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;

/**
 * User: ligasgr
 * Date: 14/08/13
 * Time: 22:28
 */
public class XQueryVariableReferenceForAutoCompletionCollector {

    private XQueryVarRef sourceOfReference;
    private List<XQueryQName> proposedReferences;

    public XQueryVariableReferenceForAutoCompletionCollector(XQueryVarRef sourceOfReference) {
        this.sourceOfReference = sourceOfReference;
    }

    public Object[] getReferencesForAutoCompletion() {
        XQueryFile file = (XQueryFile) sourceOfReference.getContainingFile();
        proposedReferences = new LinkedList<XQueryQName>();
        addProposedReferencesFromLocalScopes();
        addProposedReferencesFromFile(file);
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
                addProposedReferenceIfNotAlreadyAdded(varDecl);
            }
        }
    }

    private boolean variableNameExists(XQueryVarDecl variableDeclaration) {
        return variableDeclaration.getVarName() != null && variableDeclaration.getVarName().getTextLength() > 0;
    }

    private void addProposedReferenceIfNotAlreadyAdded(XQueryVarDecl varDecl) {
        XQueryQName<XQueryVarName> qName = aXQueryQName(varDecl.getVarName()).build();
        if (!proposedReferences.contains(qName)) {
            proposedReferences.add(qName);
        }
    }

    private LookupElement[] convertToLookupElements(List<XQueryQName> proposedReferences) {
        LookupElement[] lookupElements = new LookupElement[proposedReferences.size()];
        for (int i = 0; i < proposedReferences.size(); i++) {
            lookupElements[i] = convertToLookupElement(proposedReferences.get(i));
        }
        return lookupElements;
    }

    private LookupElement convertToLookupElement(XQueryQName qName) {
        XQueryVarName variableName = (XQueryVarName) qName.getNamedObject();
        return createLookupElement(variableName, qName.getTextRepresentation());
    }

    private LookupElement createLookupElement(XQueryVarName psiElement, String key) {
        Icon icon = AllIcons.Nodes.Variable;
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
