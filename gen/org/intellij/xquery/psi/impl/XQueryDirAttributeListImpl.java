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

public class XQueryDirAttributeListImpl extends XQueryElementImpl implements XQueryDirAttributeList {

  public XQueryDirAttributeListImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQueryDirAttributeName> getDirAttributeNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryDirAttributeName.class);
  }

  @Override
  @NotNull
  public List<XQueryDirAttributeValue> getDirAttributeValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryDirAttributeValue.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitDirAttributeList(this);
    else super.accept(visitor);
  }

}
