package org.intellij.xquery;


import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 17:29
 */
public class XQueryFileType extends LanguageFileType {
    public static final XQueryFileType INSTANCE = new XQueryFileType();
    public static final String DEFAULT_EXTENSION = "xq";

    private XQueryFileType() {
        super(XQueryLanguage.INSTANCE);
    }

    @Override
    @NotNull
    public String getName() {
        return "XQuery file";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "XQuery files";
    }

    @Override
    @NotNull
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override
    @Nullable
    public Icon getIcon() {
        return XQueryIcons.FILE;
    }
}
