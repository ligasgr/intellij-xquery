// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryFLWORExpr extends XQueryExprSingle {

  @NotNull
  XQueryInitialClause getInitialClause();

  @NotNull
  List<XQueryIntermediateClause> getIntermediateClauseList();

  @NotNull
  XQueryReturnClause getReturnClause();

}
