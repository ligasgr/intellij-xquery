// This is a generated file. Not intended for manual editing.
package org.intellij.xquery.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface XQueryIntermediateClause extends XQueryElement {

  @Nullable
  XQueryCountClause getCountClause();

  @Nullable
  XQueryGroupByClause getGroupByClause();

  @Nullable
  XQueryInitialClause getInitialClause();

  @Nullable
  XQueryOrderByClause getOrderByClause();

  @Nullable
  XQueryWhereClause getWhereClause();

}
