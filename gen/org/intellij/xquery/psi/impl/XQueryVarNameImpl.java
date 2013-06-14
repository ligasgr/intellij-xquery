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

public class XQueryVarNameImpl extends XQueryNamedElementImpl implements XQueryVarName {

  public XQueryVarNameImpl(ASTNode node) {
    super(node);
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
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitVarName(this);
    else super.accept(visitor);
  }

  public String getName() {
    return XQueryPsiImplUtil.getName(this);
  }

  public PsiElement setName(String newName) {
    return XQueryPsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return XQueryPsiImplUtil.getNameIdentifier(this);
  }

}
