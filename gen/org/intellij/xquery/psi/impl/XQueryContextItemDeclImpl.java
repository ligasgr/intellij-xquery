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

public class XQueryContextItemDeclImpl extends XQueryElementImpl implements XQueryContextItemDecl {

  public XQueryContextItemDeclImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryItemType getItemType() {
    return findChildByClass(XQueryItemType.class);
  }

  @Override
  @Nullable
  public XQuerySeparator getSeparator() {
    return findChildByClass(XQuerySeparator.class);
  }

  @Override
  @Nullable
  public XQueryVarDefaultValue getVarDefaultValue() {
    return findChildByClass(XQueryVarDefaultValue.class);
  }

  @Override
  @Nullable
  public XQueryVarValue getVarValue() {
    return findChildByClass(XQueryVarValue.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitContextItemDecl(this);
    else super.accept(visitor);
  }

}
