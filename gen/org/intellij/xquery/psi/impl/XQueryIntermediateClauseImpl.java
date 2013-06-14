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

public class XQueryIntermediateClauseImpl extends XQueryElementImpl implements XQueryIntermediateClause {

  public XQueryIntermediateClauseImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryCountClause getCountClause() {
    return findChildByClass(XQueryCountClause.class);
  }

  @Override
  @Nullable
  public XQueryGroupByClause getGroupByClause() {
    return findChildByClass(XQueryGroupByClause.class);
  }

  @Override
  @Nullable
  public XQueryInitialClause getInitialClause() {
    return findChildByClass(XQueryInitialClause.class);
  }

  @Override
  @Nullable
  public XQueryOrderByClause getOrderByClause() {
    return findChildByClass(XQueryOrderByClause.class);
  }

  @Override
  @Nullable
  public XQueryWhereClause getWhereClause() {
    return findChildByClass(XQueryWhereClause.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitIntermediateClause(this);
    else super.accept(visitor);
  }

}
