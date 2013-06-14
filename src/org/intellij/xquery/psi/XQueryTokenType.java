package org.intellij.xquery.psi;

import com.intellij.psi.tree.IElementType;
import org.intellij.xquery.XQueryLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 18:37
 */
public class XQueryTokenType extends IElementType {

    public XQueryTokenType(@NotNull @NonNls String debugName) {
        super(debugName, XQueryLanguage.INSTANCE);
    }
}
