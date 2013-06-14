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

public class XQuerySequenceTypeImpl extends XQueryElementImpl implements XQuerySequenceType {

  public XQuerySequenceTypeImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryItemType getItemType() {
    return findChildByClass(XQueryItemType.class);
  }

  @Override
  @Nullable
  public XQueryOccurrenceIndicator getOccurrenceIndicator() {
    return findChildByClass(XQueryOccurrenceIndicator.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitSequenceType(this);
    else super.accept(visitor);
  }

}
