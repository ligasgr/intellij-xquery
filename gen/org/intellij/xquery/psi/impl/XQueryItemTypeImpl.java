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

public class XQueryItemTypeImpl extends XQueryElementImpl implements XQueryItemType {

  public XQueryItemTypeImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryAtomicOrUnionType getAtomicOrUnionType() {
    return findChildByClass(XQueryAtomicOrUnionType.class);
  }

  @Override
  @Nullable
  public XQueryFunctionTest getFunctionTest() {
    return findChildByClass(XQueryFunctionTest.class);
  }

  @Override
  @Nullable
  public XQueryKindTest getKindTest() {
    return findChildByClass(XQueryKindTest.class);
  }

  @Override
  @Nullable
  public XQueryParenthesizedItemType getParenthesizedItemType() {
    return findChildByClass(XQueryParenthesizedItemType.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitItemType(this);
    else super.accept(visitor);
  }

}
