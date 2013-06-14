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

public class XQueryArgumentImpl extends XQueryElementImpl implements XQueryArgument {

  public XQueryArgumentImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryArgumentPlaceholder getArgumentPlaceholder() {
    return findChildByClass(XQueryArgumentPlaceholder.class);
  }

  @Override
  @Nullable
  public XQueryExprSingle getExprSingle() {
    return findChildByClass(XQueryExprSingle.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitArgument(this);
    else super.accept(visitor);
  }

}
