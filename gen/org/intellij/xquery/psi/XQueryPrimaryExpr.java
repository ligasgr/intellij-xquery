// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryPrimaryExpr extends XQueryExprSingle {

  @Nullable
  XQueryConstructor getConstructor();

  @Nullable
  XQueryFunctionCall getFunctionCall();

  @Nullable
  XQueryLiteral getLiteral();

  @Nullable
  XQueryVarRef getVarRef();

}
