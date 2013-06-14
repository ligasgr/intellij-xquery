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

public class XQueryFLWORExprImpl extends XQueryExprSingleImpl implements XQueryFLWORExpr {

  public XQueryFLWORExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public XQueryInitialClause getInitialClause() {
    return findNotNullChildByClass(XQueryInitialClause.class);
  }

  @Override
  @NotNull
  public List<XQueryIntermediateClause> getIntermediateClauseList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryIntermediateClause.class);
  }

  @Override
  @NotNull
  public XQueryReturnClause getReturnClause() {
    return findNotNullChildByClass(XQueryReturnClause.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitFLWORExpr(this);
    else super.accept(visitor);
  }

}
