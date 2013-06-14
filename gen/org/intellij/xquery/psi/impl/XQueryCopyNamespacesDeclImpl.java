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

public class XQueryCopyNamespacesDeclImpl extends XQueryElementImpl implements XQueryCopyNamespacesDecl {

  public XQueryCopyNamespacesDeclImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryInheritMode getInheritMode() {
    return findChildByClass(XQueryInheritMode.class);
  }

  @Override
  @Nullable
  public XQueryPreserveMode getPreserveMode() {
    return findChildByClass(XQueryPreserveMode.class);
  }

  @Override
  @Nullable
  public XQuerySeparator getSeparator() {
    return findChildByClass(XQuerySeparator.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitCopyNamespacesDecl(this);
    else super.accept(visitor);
  }

}
