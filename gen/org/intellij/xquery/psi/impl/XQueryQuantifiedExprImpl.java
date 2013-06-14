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

public class XQueryQuantifiedExprImpl extends XQueryExprSingleImpl implements XQueryQuantifiedExpr {

  public XQueryQuantifiedExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQueryExprSingle> getExprSingleList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryExprSingle.class);
  }

  @Override
  @NotNull
  public List<XQueryTypeDeclaration> getTypeDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryTypeDeclaration.class);
  }

  @Override
  @NotNull
  public List<XQueryVarName> getVarNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryVarName.class);
  }

  @Override
  @Nullable
  public XQueryVarRefName getVarRefName() {
    return findChildByClass(XQueryVarRefName.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitQuantifiedExpr(this);
    else super.accept(visitor);
  }

}
