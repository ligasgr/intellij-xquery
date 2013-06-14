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

public class XQuerySetterImpl extends XQueryElementImpl implements XQuerySetter {

  public XQuerySetterImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryBaseURIDecl getBaseURIDecl() {
    return findChildByClass(XQueryBaseURIDecl.class);
  }

  @Override
  @Nullable
  public XQueryBoundarySpaceDecl getBoundarySpaceDecl() {
    return findChildByClass(XQueryBoundarySpaceDecl.class);
  }

  @Override
  @Nullable
  public XQueryConstructionDecl getConstructionDecl() {
    return findChildByClass(XQueryConstructionDecl.class);
  }

  @Override
  @Nullable
  public XQueryCopyNamespacesDecl getCopyNamespacesDecl() {
    return findChildByClass(XQueryCopyNamespacesDecl.class);
  }

  @Override
  @Nullable
  public XQueryDecimalFormatDecl getDecimalFormatDecl() {
    return findChildByClass(XQueryDecimalFormatDecl.class);
  }

  @Override
  @Nullable
  public XQueryDefaultCollationDecl getDefaultCollationDecl() {
    return findChildByClass(XQueryDefaultCollationDecl.class);
  }

  @Override
  @Nullable
  public XQueryEmptyOrderDecl getEmptyOrderDecl() {
    return findChildByClass(XQueryEmptyOrderDecl.class);
  }

  @Override
  @Nullable
  public XQueryOrderingModeDecl getOrderingModeDecl() {
    return findChildByClass(XQueryOrderingModeDecl.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitSetter(this);
    else super.accept(visitor);
  }

}
