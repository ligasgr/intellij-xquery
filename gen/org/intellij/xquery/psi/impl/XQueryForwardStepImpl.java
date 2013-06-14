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

public class XQueryForwardStepImpl extends XQueryElementImpl implements XQueryForwardStep {

  public XQueryForwardStepImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryAbbrevForwardStep getAbbrevForwardStep() {
    return findChildByClass(XQueryAbbrevForwardStep.class);
  }

  @Override
  @Nullable
  public XQueryForwardAxis getForwardAxis() {
    return findChildByClass(XQueryForwardAxis.class);
  }

  @Override
  @Nullable
  public XQueryNodeTest getNodeTest() {
    return findChildByClass(XQueryNodeTest.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitForwardStep(this);
    else super.accept(visitor);
  }

}
