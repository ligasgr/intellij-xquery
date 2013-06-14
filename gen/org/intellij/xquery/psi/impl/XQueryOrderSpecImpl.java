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

public class XQueryOrderSpecImpl extends XQueryElementImpl implements XQueryOrderSpec {

  public XQueryOrderSpecImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public XQueryExprSingle getExprSingle() {
    return findNotNullChildByClass(XQueryExprSingle.class);
  }

  @Override
  @NotNull
  public XQueryOrderModifier getOrderModifier() {
    return findNotNullChildByClass(XQueryOrderModifier.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitOrderSpec(this);
    else super.accept(visitor);
  }

}
