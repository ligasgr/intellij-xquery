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

public class XQueryInlineFunctionExprImpl extends XQueryExprSingleImpl implements XQueryInlineFunctionExpr {

  public XQueryInlineFunctionExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQueryAnnotation> getAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryAnnotation.class);
  }

  @Override
  @NotNull
  public XQueryFunctionBody getFunctionBody() {
    return findNotNullChildByClass(XQueryFunctionBody.class);
  }

  @Override
  @Nullable
  public XQueryParamList getParamList() {
    return findChildByClass(XQueryParamList.class);
  }

  @Override
  @Nullable
  public XQuerySequenceType getSequenceType() {
    return findChildByClass(XQuerySequenceType.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitInlineFunctionExpr(this);
    else super.accept(visitor);
  }

}
