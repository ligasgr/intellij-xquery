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

public class XQueryDirElemConstructorImpl extends XQueryElementImpl implements XQueryDirElemConstructor {

  public XQueryDirElemConstructorImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryDirAttributeList getDirAttributeList() {
    return findChildByClass(XQueryDirAttributeList.class);
  }

  @Override
  @NotNull
  public List<XQueryDirElemContent> getDirElemContentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryDirElemContent.class);
  }

  @Override
  @NotNull
  public List<XQueryTagName> getTagNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryTagName.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitDirElemConstructor(this);
    else super.accept(visitor);
  }

}
