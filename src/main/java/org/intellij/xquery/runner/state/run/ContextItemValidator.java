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

package org.intellij.xquery.runner.state.run;

import com.intellij.execution.configurations.RuntimeConfigurationError;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * User: ligasgr
 * Date: 18/11/13
 * Time: 13:29
 */
public class ContextItemValidator {

    public static final String CONTEXT_ITEM_TYPE_MISSING_MESSAGE = "Context item must have a type defined";
    public static final String CONTEXT_ITEM_FILE_MISSING_MESSAGE = "Context item file must be defined";

    public void validate(boolean contextItemEnabled, String contextItemType,
                         boolean contextItemFromEditorEnabled,
                         String contextItemText, String contextItemFile) throws RuntimeConfigurationError {
        if (contextItemEnabled) {
            if (isEmpty(contextItemType))
                throw new RuntimeConfigurationError(CONTEXT_ITEM_TYPE_MISSING_MESSAGE);
            if (!contextItemFromEditorEnabled && isEmpty(contextItemFile)) {
                throw new RuntimeConfigurationError(CONTEXT_ITEM_FILE_MISSING_MESSAGE);
            }
        }

    }
}
