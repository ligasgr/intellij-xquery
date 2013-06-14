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

public class XQueryWindowVarsImpl extends XQueryElementImpl implements XQueryWindowVars {

  public XQueryWindowVarsImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryCurrentItem getCurrentItem() {
    return findChildByClass(XQueryCurrentItem.class);
  }

  @Override
  @Nullable
  public XQueryNextItem getNextItem() {
    return findChildByClass(XQueryNextItem.class);
  }

  @Override
  @Nullable
  public XQueryPositionalVar getPositionalVar() {
    return findChildByClass(XQueryPositionalVar.class);
  }

  @Override
  @Nullable
  public XQueryPreviousItem getPreviousItem() {
    return findChildByClass(XQueryPreviousItem.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitWindowVars(this);
    else super.accept(visitor);
  }

}
