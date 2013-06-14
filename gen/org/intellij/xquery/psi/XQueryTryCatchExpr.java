// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryTryCatchExpr extends XQueryExprSingle {

  @NotNull
  List<XQueryCatchClause> getCatchClauseList();

  @NotNull
  XQueryTryClause getTryClause();

}
