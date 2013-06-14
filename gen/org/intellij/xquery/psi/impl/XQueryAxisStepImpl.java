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

public class XQueryAxisStepImpl extends XQueryElementImpl implements XQueryAxisStep {

  public XQueryAxisStepImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryForwardStep getForwardStep() {
    return findChildByClass(XQueryForwardStep.class);
  }

  @Override
  @NotNull
  public XQueryPredicateList getPredicateList() {
    return findNotNullChildByClass(XQueryPredicateList.class);
  }

  @Override
  @Nullable
  public XQueryReverseStep getReverseStep() {
    return findChildByClass(XQueryReverseStep.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitAxisStep(this);
    else super.accept(visitor);
  }

}
