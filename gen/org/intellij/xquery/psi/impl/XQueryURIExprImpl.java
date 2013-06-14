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

public class XQueryURIExprImpl extends XQueryExprSingleImpl implements XQueryURIExpr {

  public XQueryURIExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public XQueryExpr getExpr() {
    return findNotNullChildByClass(XQueryExpr.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitURIExpr(this);
    else super.accept(visitor);
  }

}
