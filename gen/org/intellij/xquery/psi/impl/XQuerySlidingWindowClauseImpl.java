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

public class XQuerySlidingWindowClauseImpl extends XQueryElementImpl implements XQuerySlidingWindowClause {

  public XQuerySlidingWindowClauseImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryExprSingle getExprSingle() {
    return findChildByClass(XQueryExprSingle.class);
  }

  @Override
  @Nullable
  public XQueryTypeDeclaration getTypeDeclaration() {
    return findChildByClass(XQueryTypeDeclaration.class);
  }

  @Override
  @Nullable
  public XQueryVarName getVarName() {
    return findChildByClass(XQueryVarName.class);
  }

  @Override
  @Nullable
  public XQueryWindowEndCondition getWindowEndCondition() {
    return findChildByClass(XQueryWindowEndCondition.class);
  }

  @Override
  @Nullable
  public XQueryWindowStartCondition getWindowStartCondition() {
    return findChildByClass(XQueryWindowStartCondition.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitSlidingWindowClause(this);
    else super.accept(visitor);
  }

}
