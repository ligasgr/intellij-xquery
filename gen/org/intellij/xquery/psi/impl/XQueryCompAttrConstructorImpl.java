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

public class XQueryCompAttrConstructorImpl extends XQueryElementImpl implements XQueryCompAttrConstructor {

  public XQueryCompAttrConstructorImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQueryExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryExpr.class);
  }

  @Override
  @Nullable
  public XQueryLocalPart getLocalPart() {
    return findChildByClass(XQueryLocalPart.class);
  }

  @Override
  @Nullable
  public XQueryPrefix getPrefix() {
    return findChildByClass(XQueryPrefix.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitCompAttrConstructor(this);
    else super.accept(visitor);
  }

}
