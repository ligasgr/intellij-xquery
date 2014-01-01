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

import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * User: ligasgr
 * Date: 06/10/13
 * Time: 17:07
 */
public class DataSourceTypesListPopup extends BaseListPopupStep<XQueryDataSourceType> {
    public static final String TITLE = "Add new data source";
    private XQueryDataSourceTypeBasedActionExecutor actionExecutor;

    public DataSourceTypesListPopup(XQueryDataSourceTypeBasedActionExecutor actionExecutor) {
        super(TITLE, XQueryDataSourceType.values());
        this.actionExecutor = actionExecutor;
    }

    @NotNull
    public String getTextFor(final XQueryDataSourceType type) {
        return type.getPresentableName();
    }

    @Override
    public boolean isSpeedSearchEnabled() {
        return true;
    }

    @Override
    public boolean canBeHidden(XQueryDataSourceType value) {
        return true;
    }

    public Icon getIconFor(final XQueryDataSourceType type) {
        return type.getIcon();
    }

    public PopupStep onChosen(final XQueryDataSourceType type, final boolean finalChoice) {
        actionExecutor.execute(type);
        return FINAL_CHOICE;
    }
}
