// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryInlineFunctionExpr extends XQueryExprSingle {

  @NotNull
  List<XQueryAnnotation> getAnnotationList();

  @NotNull
  XQueryFunctionBody getFunctionBody();

  @Nullable
  XQueryParamList getParamList();

  @Nullable
  XQuerySequenceType getSequenceType();

}
