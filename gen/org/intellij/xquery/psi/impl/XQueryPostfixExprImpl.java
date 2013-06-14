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

public class XQueryPostfixExprImpl extends XQueryExprSingleImpl implements XQueryPostfixExpr {

  public XQueryPostfixExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQueryArgumentList> getArgumentListList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryArgumentList.class);
  }

  @Override
  @NotNull
  public List<XQueryPredicate> getPredicateList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryPredicate.class);
  }

  @Override
  @NotNull
  public XQueryPrimaryExpr getPrimaryExpr() {
    return findNotNullChildByClass(XQueryPrimaryExpr.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitPostfixExpr(this);
    else super.accept(visitor);
  }

}
