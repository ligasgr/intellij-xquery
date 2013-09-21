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
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.ResolveState;

public interface XQueryProlog extends XQueryElement {

  @NotNull
  List<XQueryContextItemDecl> getContextItemDeclList();

  @NotNull
  List<XQueryDefaultNamespaceDecl> getDefaultNamespaceDeclList();

  @NotNull
  List<XQueryFunctionDecl> getFunctionDeclList();

  @NotNull
  List<XQueryImport> getImportList();

  @NotNull
  List<XQueryNamespaceDecl> getNamespaceDeclList();

  @NotNull
  List<XQueryOptionDecl> getOptionDeclList();

  @NotNull
  List<XQuerySeparator> getSeparatorList();

  @NotNull
  List<XQuerySetter> getSetterList();

  @NotNull
  List<XQueryVarDecl> getVarDeclList();

  boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}
