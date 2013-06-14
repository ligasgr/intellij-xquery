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

public class XQueryPrologImpl extends XQueryElementImpl implements XQueryProlog {

  public XQueryPrologImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<XQueryContextItemDecl> getContextItemDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryContextItemDecl.class);
  }

  @Override
  @NotNull
  public List<XQueryDefaultNamespaceDecl> getDefaultNamespaceDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryDefaultNamespaceDecl.class);
  }

  @Override
  @NotNull
  public List<XQueryFunctionDecl> getFunctionDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryFunctionDecl.class);
  }

  @Override
  @NotNull
  public List<XQueryImport> getImportList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryImport.class);
  }

  @Override
  @NotNull
  public List<XQueryNamespaceDecl> getNamespaceDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryNamespaceDecl.class);
  }

  @Override
  @NotNull
  public List<XQueryOptionDecl> getOptionDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryOptionDecl.class);
  }

  @Override
  @NotNull
  public List<XQuerySeparator> getSeparatorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQuerySeparator.class);
  }

  @Override
  @NotNull
  public List<XQuerySetter> getSetterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQuerySetter.class);
  }

  @Override
  @NotNull
  public List<XQueryVarDecl> getVarDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, XQueryVarDecl.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) ((XQueryVisitor)visitor).visitProlog(this);
    else super.accept(visitor);
  }

}
