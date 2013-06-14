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

public class XQueryModuleImpl extends XQueryElementImpl implements XQueryModule {

  public XQueryModuleImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryLibraryModule getLibraryModule() {
    return findChildByClass(XQueryLibraryModule.class);
  }

  @Override
  @Nullable
  public XQueryMainModule getMainModule() {
    return findChildByClass(XQueryMainModule.class);
  }

  @Override
  @Nullable
  public XQueryVersionDecl getVersionDecl() {
    return findChildByClass(XQueryVersionDecl.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitModule(this);
    else super.accept(visitor);
  }

}
