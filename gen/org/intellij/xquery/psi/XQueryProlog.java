// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

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

}
