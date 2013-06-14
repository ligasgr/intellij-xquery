// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryDirAttributeValue extends XQueryElement {

  @NotNull
  List<XQueryAposAttrValueContent> getAposAttrValueContentList();

  @NotNull
  List<XQueryEscapeApos> getEscapeAposList();

  @NotNull
  List<XQueryEscapeQuot> getEscapeQuotList();

  @NotNull
  List<XQueryQuotAttrValueContent> getQuotAttrValueContentList();

}
