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

public class XQueryMapTestImpl extends XQueryElementImpl implements XQueryMapTest {

  public XQueryMapTestImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryAtomicOrUnionType getAtomicOrUnionType() {
    return findChildByClass(XQueryAtomicOrUnionType.class);
  }

  @Override
  @Nullable
  public XQuerySequenceType getSequenceType() {
    return findChildByClass(XQuerySequenceType.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitMapTest(this);
    else super.accept(visitor);
  }

}
