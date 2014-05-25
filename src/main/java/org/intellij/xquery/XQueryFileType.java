/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
 * (see the CONTRIBUTORS file).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intellij.xquery;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.intellij.xquery.icons.XQueryIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 17:29
 */
public class XQueryFileType extends LanguageFileType {
    public static final XQueryFileType INSTANCE = new XQueryFileType();
    public static final String DEFAULT_EXTENSION = "xq";
    public static final String DEFAULT_EXTENSION_WITH_DOT = "." + DEFAULT_EXTENSION;
    public static final String ALL_EXTENSIONS = "xq;xqi;xql;xqm;xqy;xqws;xquery";
    static final String EXTENSION_SEPARATOR = ";";
    public static final List<String> ALL_EXTENSIONS_LIST = asList(ALL_EXTENSIONS.split(EXTENSION_SEPARATOR));

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
