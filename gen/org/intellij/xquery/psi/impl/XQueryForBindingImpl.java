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

public class XQueryForBindingImpl extends XQueryElementImpl implements XQueryForBinding {

  public XQueryForBindingImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryAllowingEmpty getAllowingEmpty() {
    return findChildByClass(XQueryAllowingEmpty.class);
  }

  @Override
  @NotNull
  public XQueryExprSingle getExprSingle() {
    return findNotNullChildByClass(XQueryExprSingle.class);
  }

  @Override
  @Nullable
  public XQueryPositionalVar getPositionalVar() {
    return findChildByClass(XQueryPositionalVar.class);
  }

  @Override
  @Nullable
  public XQueryTypeDeclaration getTypeDeclaration() {
    return findChildByClass(XQueryTypeDeclaration.class);
  }

  @Override
  @NotNull
  public XQueryVarName getVarName() {
    return findNotNullChildByClass(XQueryVarName.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitForBinding(this);
    else super.accept(visitor);
  }

}
