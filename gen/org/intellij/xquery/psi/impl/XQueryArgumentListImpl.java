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

public class XQueryArgumentListImpl extends XQueryElementImpl implements XQueryArgumentList {

  public XQueryArgumentListImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQueryArgument> getArgumentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryArgument.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitArgumentList(this);
    else super.accept(visitor);
  }

}
