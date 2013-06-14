// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryTypeswitchExpr extends XQueryExprSingle {

  @NotNull
  List<XQueryCaseClause> getCaseClauseList();

  @Nullable
  XQueryExpr getExpr();

  @Nullable
  XQueryExprSingle getExprSingle();

  @Nullable
  XQueryVarRefName getVarRefName();

}
