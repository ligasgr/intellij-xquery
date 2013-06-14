// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryForBinding extends XQueryElement {

  @Nullable
  XQueryAllowingEmpty getAllowingEmpty();

  @NotNull
  XQueryExprSingle getExprSingle();

  @Nullable
  XQueryPositionalVar getPositionalVar();

  @Nullable
  XQueryTypeDeclaration getTypeDeclaration();

  @NotNull
  XQueryVarName getVarName();

}
