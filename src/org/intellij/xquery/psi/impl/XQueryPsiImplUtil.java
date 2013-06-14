package org.intellij.xquery.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.psi.XQueryElementFactory;
import org.intellij.xquery.psi.XQueryTypes;
import org.intellij.xquery.psi.XQueryVarName;
import org.intellij.xquery.psi.XQueryVarRefName;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 18:59
 */
public class XQueryPsiImplUtil {

    public static String getName(XQueryVarName element) {
        return element.getText();
    }

    public static PsiElement setName(XQueryVarName element, String newName) {
        XQueryVarName newNameElement = XQueryElementFactory.createVariableDeclaration(element.getProject(), newName);
        return element.replace(newNameElement);
    }

    public static PsiElement getNameIdentifier(XQueryVarName element) {
        return element;
    }
}