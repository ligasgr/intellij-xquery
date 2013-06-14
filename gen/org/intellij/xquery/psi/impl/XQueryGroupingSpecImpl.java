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

public class XQueryGroupingSpecImpl extends XQueryElementImpl implements XQueryGroupingSpec {

  public XQueryGroupingSpecImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryExprSingle getExprSingle() {
    return findChildByClass(XQueryExprSingle.class);
  }

  @Override
  @NotNull
  public XQueryGroupingVariable getGroupingVariable() {
    return findNotNullChildByClass(XQueryGroupingVariable.class);
  }

  @Override
  @Nullable
  public XQueryTypeDeclaration getTypeDeclaration() {
    return findChildByClass(XQueryTypeDeclaration.class);
  }

  @Override
  @Nullable
  public XQueryURILiteral getURILiteral() {
    return findChildByClass(XQueryURILiteral.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitGroupingSpec(this);
    else super.accept(visitor);
  }

}
