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

package org.intellij.xquery.icons;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.RowIcon;
import com.intellij.util.PlatformIcons;

import javax.swing.Icon;

/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 18:14
 */
public class XQueryIcons {
    public static final Icon FILE = IconLoader.getIcon("/icons/xq.png");
    public static final Icon FUNCTION_ICON = AllIcons.Nodes.Method;
    public static final Icon VARIABLE_ICON = AllIcons.Nodes.Variable;

    public static final Icon FUNCTION_PRIVATE_ICON = createRowIcon(AllIcons.Nodes.Method, PlatformIcons.PRIVATE_ICON);
    public static final Icon FUNCTION_PUBLIC_ICON = createRowIcon(AllIcons.Nodes.Method, PlatformIcons.PUBLIC_ICON);
    public static final Icon VARIABLE_PRIVATE_ICON = createRowIcon(AllIcons.Nodes.Variable, PlatformIcons.PRIVATE_ICON);
    public static final Icon VARIABLE_PUBLIC_ICON = createRowIcon(AllIcons.Nodes.Variable, PlatformIcons.PUBLIC_ICON);
    public static final Icon QUERY_BODY = AllIcons.Nodes.HomeFolder;

    private static Icon createRowIcon(Icon icon1, Icon icon2) {
        RowIcon rowIcon = new RowIcon(2);
        rowIcon.setIcon(icon1, 0);
        rowIcon.setIcon(icon2, 1);
        return rowIcon;
    }
}