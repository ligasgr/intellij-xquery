// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryVarName extends XQueryNamedElement {

  @Nullable
  XQueryLocalPart getLocalPart();

  @Nullable
  XQueryPrefix getPrefix();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
