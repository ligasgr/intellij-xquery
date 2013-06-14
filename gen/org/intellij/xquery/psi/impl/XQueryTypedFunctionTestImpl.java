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

public class XQueryTypedFunctionTestImpl extends XQueryElementImpl implements XQueryTypedFunctionTest {

  public XQueryTypedFunctionTestImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQuerySequenceType> getSequenceTypeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQuerySequenceType.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitTypedFunctionTest(this);
    else super.accept(visitor);
  }

}
