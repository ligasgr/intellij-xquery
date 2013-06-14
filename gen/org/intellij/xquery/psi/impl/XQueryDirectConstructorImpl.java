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

public class XQueryDirectConstructorImpl extends XQueryElementImpl implements XQueryDirectConstructor {

  public XQueryDirectConstructorImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryDirCommentConstructor getDirCommentConstructor() {
    return findChildByClass(XQueryDirCommentConstructor.class);
  }

  @Override
  @Nullable
  public XQueryDirElemConstructor getDirElemConstructor() {
    return findChildByClass(XQueryDirElemConstructor.class);
  }

  @Override
  @Nullable
  public XQueryDirPIConstructor getDirPIConstructor() {
    return findChildByClass(XQueryDirPIConstructor.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitDirectConstructor(this);
    else super.accept(visitor);
  }

}
