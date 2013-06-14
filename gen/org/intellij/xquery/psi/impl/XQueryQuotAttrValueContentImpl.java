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

public class XQueryQuotAttrValueContentImpl extends XQueryElementImpl implements XQueryQuotAttrValueContent {

  public XQueryQuotAttrValueContentImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryCommonContent getCommonContent() {
    return findChildByClass(XQueryCommonContent.class);
  }

  @Override
  @Nullable
  public XQueryQuotAttrContentChar getQuotAttrContentChar() {
    return findChildByClass(XQueryQuotAttrContentChar.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitQuotAttrValueContent(this);
    else super.accept(visitor);
  }

}
