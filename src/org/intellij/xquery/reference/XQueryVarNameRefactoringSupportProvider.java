package org.intellij.xquery.reference;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.psi.XQueryVarDecl;
import org.intellij.xquery.psi.XQueryVarName;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 22:08
 */
public class XQueryVarNameRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
        return element instanceof XQueryVarName;
    }
}
