package org.intellij.xquery.reference.variable;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.BaseScopeProcessor;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRef;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;

/**
* User: ligasgr
* Date: 14/08/13
* Time: 14:41
*/
class VariableReferenceScopeProcessor extends BaseScopeProcessor {
    private List<XQueryVarName> results = new ArrayList<XQueryVarName>();
    private XQueryVarRef myElement;
    private String checkedNamespace;

    VariableReferenceScopeProcessor(String checkedNamespace, XQueryVarRef myElement) {
        this.checkedNamespace = checkedNamespace;
        this.myElement = myElement;
    }

    public List<XQueryVarName> getResults() {
        return results;
    }

    @Override
    public boolean execute(@NotNull PsiElement element, ResolveState state) {
        boolean elementIsGoodCandidate = !element.equals(myElement) && element instanceof XQueryVarName &&
                !(element.getParent() instanceof XQueryVarRef);
        if (elementIsGoodCandidate) {
            XQueryQName<XQueryVarName> source = aXQueryQName(myElement.getVarName())
                    .withPrefix(checkedNamespace)
                    .build();
            XQueryQName<XQueryVarName> checkedQName = aXQueryQName((XQueryVarName) element)
                    .build();
            if (source.equals(checkedQName)) {
                results.add(checkedQName.getNamedObject());
                return false;
            }
        }
        return true;
    }
}
