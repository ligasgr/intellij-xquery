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

public class XQueryComputedConstructorImpl extends XQueryPsiElementImpl implements XQueryComputedConstructor {

  public XQueryComputedConstructorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull XQueryVisitor visitor) {
    visitor.visitComputedConstructor(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof XQueryVisitor) accept((XQueryVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public XQueryCompAttrConstructor getCompAttrConstructor() {
    return findChildByClass(XQueryCompAttrConstructor.class);
  }

  @Override
  @Nullable
  public XQueryCompCommentConstructor getCompCommentConstructor() {
    return findChildByClass(XQueryCompCommentConstructor.class);
  }

  @Override
  @Nullable
  public XQueryCompDocConstructor getCompDocConstructor() {
    return findChildByClass(XQueryCompDocConstructor.class);
  }

  @Override
  @Nullable
  public XQueryCompElemConstructor getCompElemConstructor() {
    return findChildByClass(XQueryCompElemConstructor.class);
  }

  @Override
  @Nullable
  public XQueryCompNamespaceConstructor getCompNamespaceConstructor() {
    return findChildByClass(XQueryCompNamespaceConstructor.class);
  }

  @Override
  @Nullable
  public XQueryCompPIConstructor getCompPIConstructor() {
    return findChildByClass(XQueryCompPIConstructor.class);
  }

  @Override
  @Nullable
  public XQueryCompTextConstructor getCompTextConstructor() {
    return findChildByClass(XQueryCompTextConstructor.class);
  }

  @Override
  @Nullable
  public XQueryMarklogicCompArrayNodeConstructor getMarklogicCompArrayNodeConstructor() {
    return findChildByClass(XQueryMarklogicCompArrayNodeConstructor.class);
  }

  @Override
  @Nullable
  public XQueryMarklogicCompBinaryConstructor getMarklogicCompBinaryConstructor() {
    return findChildByClass(XQueryMarklogicCompBinaryConstructor.class);
  }

  @Override
  @Nullable
  public XQueryMarklogicCompBooleanNodeConstructor getMarklogicCompBooleanNodeConstructor() {
    return findChildByClass(XQueryMarklogicCompBooleanNodeConstructor.class);
  }

  @Override
  @Nullable
  public XQueryMarklogicCompNullNodeConstructor getMarklogicCompNullNodeConstructor() {
    return findChildByClass(XQueryMarklogicCompNullNodeConstructor.class);
  }

  @Override
  @Nullable
  public XQueryMarklogicCompNumberNodeConstructor getMarklogicCompNumberNodeConstructor() {
    return findChildByClass(XQueryMarklogicCompNumberNodeConstructor.class);
  }

  @Override
  @Nullable
  public XQueryMarklogicCompObjectNodeConstructor getMarklogicCompObjectNodeConstructor() {
    return findChildByClass(XQueryMarklogicCompObjectNodeConstructor.class);
  }

}
