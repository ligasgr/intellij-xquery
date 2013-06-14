package org.intellij.xquery;

import com.intellij.lang.Language;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 17:34
 */
public class XQueryLanguage  extends Language {
    public static final XQueryLanguage INSTANCE = new XQueryLanguage();

    public XQueryLanguage() {
        super("XQuery"); //, "application/xquery", "application/xquery+xml");
    }
}
