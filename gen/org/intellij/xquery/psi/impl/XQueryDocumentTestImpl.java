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

public class XQueryDocumentTestImpl extends XQueryElementImpl implements XQueryDocumentTest {

  public XQueryDocumentTestImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryElementTest getElementTest() {
    return findChildByClass(XQueryElementTest.class);
  }

  @Override
  @Nullable
  public XQuerySchemaElementTest getSchemaElementTest() {
    return findChildByClass(XQuerySchemaElementTest.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitDocumentTest(this);
    else super.accept(visitor);
  }

}
