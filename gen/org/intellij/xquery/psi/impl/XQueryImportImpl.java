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

public class XQueryImportImpl extends XQueryElementImpl implements XQueryImport {

  public XQueryImportImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryModuleImport getModuleImport() {
    return findChildByClass(XQueryModuleImport.class);
  }

  @Override
  @Nullable
  public XQuerySchemaImport getSchemaImport() {
    return findChildByClass(XQuerySchemaImport.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitImport(this);
    else super.accept(visitor);
  }

}
