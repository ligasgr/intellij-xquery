package org.intellij.xquery.psi;

import com.intellij.psi.tree.IElementType;
import org.intellij.xquery.XQueryLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 18:40
 */
public class XQueryElementType extends IElementType {
    public XQueryElementType(@NotNull @NonNls String debugName) {
        super(debugName, XQueryLanguage.INSTANCE);
    }


}
