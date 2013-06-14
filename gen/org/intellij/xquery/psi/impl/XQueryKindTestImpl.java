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

public class XQueryKindTestImpl extends XQueryElementImpl implements XQueryKindTest {

  public XQueryKindTestImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public XQueryAnyKindTest getAnyKindTest() {
    return findChildByClass(XQueryAnyKindTest.class);
  }

  @Override
  @Nullable
  public XQueryAttributeTest getAttributeTest() {
    return findChildByClass(XQueryAttributeTest.class);
  }

  @Override
  @Nullable
  public XQueryCommentTest getCommentTest() {
    return findChildByClass(XQueryCommentTest.class);
  }

  @Override
  @Nullable
  public XQueryDocumentTest getDocumentTest() {
    return findChildByClass(XQueryDocumentTest.class);
  }

  @Override
  @Nullable
  public XQueryElementTest getElementTest() {
    return findChildByClass(XQueryElementTest.class);
  }

  @Override
  @Nullable
  public XQueryMapTest getMapTest() {
    return findChildByClass(XQueryMapTest.class);
  }

  @Override
  @Nullable
  public XQueryNamespaceNodeTest getNamespaceNodeTest() {
    return findChildByClass(XQueryNamespaceNodeTest.class);
  }

  @Override
  @Nullable
  public XQueryPITest getPITest() {
    return findChildByClass(XQueryPITest.class);
  }

  @Override
  @Nullable
  public XQuerySchemaAttributeTest getSchemaAttributeTest() {
    return findChildByClass(XQuerySchemaAttributeTest.class);
  }

  @Override
  @Nullable
  public XQuerySchemaElementTest getSchemaElementTest() {
    return findChildByClass(XQuerySchemaElementTest.class);
  }

  @Override
  @Nullable
  public XQueryTextTest getTextTest() {
    return findChildByClass(XQueryTextTest.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitKindTest(this);
    else super.accept(visitor);
  }

}
