// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryVarDecl extends XQueryElement {

  @NotNull
  List<XQueryAnnotation> getAnnotationList();

  @Nullable
  XQueryTypeDeclaration getTypeDeclaration();

  @Nullable
  XQueryVarDefaultValue getVarDefaultValue();

  @Nullable
  XQueryVarName getVarName();

  @Nullable
  XQueryVarValue getVarValue();

}
