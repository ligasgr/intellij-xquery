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

package org.intellij.xquery.runner.ui.datasources;

import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.util.ui.UIUtil;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;

import javax.swing.*;

/**
 * User: ligasgr
 * Date: 06/10/13
 * Time: 17:25
 */
public class DataSourceConfigurationCellRenderer extends ColoredListCellRenderer {
    @Override
    protected void customizeCellRenderer(JList list, Object value, int index, boolean selected, boolean hasFocus) {
        setBackground(UIUtil.getListBackground(selected));
        XQueryDataSourceConfiguration configuration = (XQueryDataSourceConfiguration) value;
        append(configuration.NAME);
    }
}
