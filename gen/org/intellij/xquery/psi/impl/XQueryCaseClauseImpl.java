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

public class XQueryCaseClauseImpl extends XQueryElementImpl implements XQueryCaseClause {

  public XQueryCaseClauseImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryExprSingle getExprSingle() {
    return findChildByClass(XQueryExprSingle.class);
  }

  @Override
  @Nullable
  public XQuerySequenceTypeUnion getSequenceTypeUnion() {
    return findChildByClass(XQuerySequenceTypeUnion.class);
  }

  @Override
  @Nullable
  public XQueryVarRefName getVarRefName() {
    return findChildByClass(XQueryVarRefName.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitCaseClause(this);
    else super.accept(visitor);
  }

}
