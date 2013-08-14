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
