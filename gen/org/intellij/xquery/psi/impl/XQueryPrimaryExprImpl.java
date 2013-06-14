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

public class XQueryPrimaryExprImpl extends XQueryExprSingleImpl implements XQueryPrimaryExpr {

  public XQueryPrimaryExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryConstructor getConstructor() {
    return findChildByClass(XQueryConstructor.class);
  }

  @Override
  @Nullable
  public XQueryFunctionCall getFunctionCall() {
    return findChildByClass(XQueryFunctionCall.class);
  }

  @Override
  @Nullable
  public XQueryLiteral getLiteral() {
    return findChildByClass(XQueryLiteral.class);
  }

  @Override
  @Nullable
  public XQueryVarRef getVarRef() {
    return findChildByClass(XQueryVarRef.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitPrimaryExpr(this);
    else super.accept(visitor);
  }

}
