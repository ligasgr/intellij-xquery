// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryFunctionDecl extends XQueryElement {

  @NotNull
  List<XQueryAnnotation> getAnnotationList();

  @Nullable
  XQueryFunctionBody getFunctionBody();

  @Nullable
  XQueryFunctionName getFunctionName();

  @Nullable
  XQueryParamList getParamList();

  @Nullable
  XQuerySequenceType getSequenceType();

}
