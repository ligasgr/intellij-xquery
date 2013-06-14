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

public class XQueryReverseStepImpl extends XQueryElementImpl implements XQueryReverseStep {

  public XQueryReverseStepImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryAbbrevReverseStep getAbbrevReverseStep() {
    return findChildByClass(XQueryAbbrevReverseStep.class);
  }

  @Override
  @Nullable
  public XQueryNodeTest getNodeTest() {
    return findChildByClass(XQueryNodeTest.class);
  }

  @Override
  @Nullable
  public XQueryReverseAxis getReverseAxis() {
    return findChildByClass(XQueryReverseAxis.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitReverseStep(this);
    else super.accept(visitor);
  }

}
