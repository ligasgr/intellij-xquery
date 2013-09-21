/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.ResolveState;

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

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return XQueryPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
