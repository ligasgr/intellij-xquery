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

public class XQueryAposAttrValueContentImpl extends XQueryElementImpl implements XQueryAposAttrValueContent {

  public XQueryAposAttrValueContentImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryAposAttrContentChar getAposAttrContentChar() {
    return findChildByClass(XQueryAposAttrContentChar.class);
  }

  @Override
  @Nullable
  public XQueryCommonContent getCommonContent() {
    return findChildByClass(XQueryCommonContent.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitAposAttrValueContent(this);
    else super.accept(visitor);
  }

}
