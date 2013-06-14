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

public class XQueryModuleDeclImpl extends XQueryElementImpl implements XQueryModuleDecl {

  public XQueryModuleDeclImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQuerySeparator getSeparator() {
    return findChildByClass(XQuerySeparator.class);
  }

  @Override
  @Nullable
  public XQueryURILiteral getURILiteral() {
    return findChildByClass(XQueryURILiteral.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitModuleDecl(this);
    else super.accept(visitor);
  }

}
