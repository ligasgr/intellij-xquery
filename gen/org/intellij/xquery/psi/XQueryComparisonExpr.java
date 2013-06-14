// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryComparisonExpr extends XQueryExprSingle {

  @NotNull
  List<XQueryExprSingle> getExprSingleList();

  @Nullable
  XQueryGeneralComp getGeneralComp();

  @Nullable
  XQueryNodeComp getNodeComp();

  @Nullable
  XQueryValueComp getValueComp();

}
