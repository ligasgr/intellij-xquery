// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryPostfixExpr extends XQueryExprSingle {

  @NotNull
  List<XQueryArgumentList> getArgumentListList();

  @NotNull
  List<XQueryPredicate> getPredicateList();

  @NotNull
  XQueryPrimaryExpr getPrimaryExpr();

}
