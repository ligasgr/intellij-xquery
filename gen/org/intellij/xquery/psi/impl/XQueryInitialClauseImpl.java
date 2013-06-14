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

public class XQueryInitialClauseImpl extends XQueryElementImpl implements XQueryInitialClause {

  public XQueryInitialClauseImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryForClause getForClause() {
    return findChildByClass(XQueryForClause.class);
  }

  @Override
  @Nullable
  public XQueryLetClause getLetClause() {
    return findChildByClass(XQueryLetClause.class);
  }

  @Override
  @Nullable
  public XQueryWindowClause getWindowClause() {
    return findChildByClass(XQueryWindowClause.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitInitialClause(this);
    else super.accept(visitor);
  }

}
