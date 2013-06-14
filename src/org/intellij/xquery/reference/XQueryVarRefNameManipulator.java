package org.intellij.xquery.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.psi.XQueryElementFactory;
import org.intellij.xquery.psi.XQueryVarRefName;
import org.intellij.xquery.psi.impl.XQueryVarRefNameImpl;

/**
 * User: ligasgr
 * Date: 12/06/13
 * Time: 00:26
 */
public class XQueryVarRefNameManipulator extends AbstractElementManipulator<XQueryVarRefNameImpl> {
    @Override
    public XQueryVarRefNameImpl handleContentChange(XQueryVarRefNameImpl element, TextRange range, String newContent) throws IncorrectOperationException {
        final String oldText = element.getText();
        return (XQueryVarRefNameImpl) element.replace(getUpdatedRef(element, newContent));
    }

    private PsiElement getUpdatedRef(XQueryVarRefNameImpl element, String newName) {
        XQueryVarRefName varName = XQueryElementFactory.createVariableReference(element.getProject(), newName);
        return varName;
    }
}
