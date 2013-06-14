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

public class XQueryEnclosedExprImpl extends XQueryExprSingleImpl implements XQueryEnclosedExpr {

  public XQueryEnclosedExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryExpr getExpr() {
    return findChildByClass(XQueryExpr.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitEnclosedExpr(this);
    else super.accept(visitor);
  }

}
