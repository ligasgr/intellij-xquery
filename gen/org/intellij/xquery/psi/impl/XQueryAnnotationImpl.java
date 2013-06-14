// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.xquery.psi.XQueryTypes.*;
import org.intellij.xquery.psi.*;

public class XQueryAnnotationImpl extends XQueryElementImpl implements XQueryAnnotation {

  public XQueryAnnotationImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public XQueryAnnotationName getAnnotationName() {
    return findNotNullChildByClass(XQueryAnnotationName.class);
  }

  @Override
  @NotNull
  public List<XQueryLiteral> getLiteralList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryLiteral.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitAnnotation(this);
    else super.accept(visitor);
  }

}
