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

public class XQueryNodeTestImpl extends XQueryElementImpl implements XQueryNodeTest {

  public XQueryNodeTestImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryKindTest getKindTest() {
    return findChildByClass(XQueryKindTest.class);
  }

  @Override
  @Nullable
  public XQueryNameTest getNameTest() {
    return findChildByClass(XQueryNameTest.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitNodeTest(this);
    else super.accept(visitor);
  }

}
