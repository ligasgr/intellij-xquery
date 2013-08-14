package org.intellij.xquery.reference.variable;

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