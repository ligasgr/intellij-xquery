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

public class XQueryForClauseImpl extends XQueryElementImpl implements XQueryForClause {

  public XQueryForClauseImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQueryForBinding> getForBindingList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryForBinding.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitForClause(this);
    else super.accept(visitor);
  }

}
