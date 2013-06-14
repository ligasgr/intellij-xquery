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

public class XQueryWindowEndConditionImpl extends XQueryElementImpl implements XQueryWindowEndCondition {

  public XQueryWindowEndConditionImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public XQueryExprSingle getExprSingle() {
    return findNotNullChildByClass(XQueryExprSingle.class);
  }

  @Override
  @NotNull
  public XQueryWindowVars getWindowVars() {
    return findNotNullChildByClass(XQueryWindowVars.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitWindowEndCondition(this);
    else super.accept(visitor);
  }

}
