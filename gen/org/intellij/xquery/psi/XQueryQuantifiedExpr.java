// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryQuantifiedExpr extends XQueryExprSingle {

  @NotNull
  List<XQueryExprSingle> getExprSingleList();

  @NotNull
  List<XQueryTypeDeclaration> getTypeDeclarationList();

  @NotNull
  List<XQueryVarName> getVarNameList();

  @Nullable
  XQueryVarRefName getVarRefName();

}
