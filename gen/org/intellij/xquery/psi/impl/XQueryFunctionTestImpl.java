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

public class XQueryFunctionTestImpl extends XQueryElementImpl implements XQueryFunctionTest {

  public XQueryFunctionTestImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQueryAnnotation> getAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryAnnotation.class);
  }

  @Override
  @Nullable
  public XQueryAnyFunctionTest getAnyFunctionTest() {
    return findChildByClass(XQueryAnyFunctionTest.class);
  }

  @Override
  @Nullable
  public XQueryTypedFunctionTest getTypedFunctionTest() {
    return findChildByClass(XQueryTypedFunctionTest.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitFunctionTest(this);
    else super.accept(visitor);
  }

}
