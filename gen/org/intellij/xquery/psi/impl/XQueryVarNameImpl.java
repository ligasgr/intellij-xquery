/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
 * (see the CONTRIBUTORS file).
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
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.search.SearchScope;

public class XQueryVarNameImpl extends XQueryNamedElementImpl implements XQueryVarName {

  public XQueryVarNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull XQueryVisitor visitor) {
    visitor.visitVarName(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) accept((XQueryVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public XQueryPrefix getPrefix() {
    return findChildByClass(XQueryPrefix.class);
  }

  @Override
  @Nullable
  public XQueryVarLocalName getVarLocalName() {
    return findChildByClass(XQueryVarLocalName.class);
  }

  public String getName() {
    return XQueryPsiImplUtil.getName(this);
  }

  public PsiElement setName(String newName) {
    return XQueryPsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return XQueryPsiImplUtil.getNameIdentifier(this);
  }

  public int getTextOffset() {
    return XQueryPsiImplUtil.getTextOffset(this);
  }

  public SearchScope getUseScope() {
    return XQueryPsiImplUtil.getUseScope(this);
  }

  public ItemPresentation getPresentation() {
    return XQueryPsiImplUtil.getPresentation(this);
  }

  public void delete() {
    XQueryPsiImplUtil.delete(this);
  }

}
