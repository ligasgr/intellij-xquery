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

public class XQueryWindowClauseImpl extends XQueryElementImpl implements XQueryWindowClause {

  public XQueryWindowClauseImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQuerySlidingWindowClause getSlidingWindowClause() {
    return findChildByClass(XQuerySlidingWindowClause.class);
  }

  @Override
  @Nullable
  public XQueryTumblingWindowClause getTumblingWindowClause() {
    return findChildByClass(XQueryTumblingWindowClause.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitWindowClause(this);
    else super.accept(visitor);
  }

}
