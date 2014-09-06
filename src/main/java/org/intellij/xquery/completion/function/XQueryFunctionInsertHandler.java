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

package org.intellij.xquery.completion.function;

import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import org.intellij.xquery.completion.InsertHandlerUtils;

/**
 * User: ligasgr
 * Date: 08/12/13
 * Time: 19:57
 */
public class XQueryFunctionInsertHandler extends ParenthesesInsertHandler<LookupElement> {

    @Override
    public void handleInsert(InsertionContext context, LookupElement item) {
        super.handleInsert(context, item);
        InsertHandlerUtils.removePreviousNamespaceAndColonIfPresent(context);
        AutoPopupController.getInstance(context.getProject()).autoPopupParameterInfo(context.getEditor(),
                item.getPsiElement());
    }

    @Override
    protected boolean placeCaretInsideParentheses(InsertionContext context, LookupElement item) {
        return true;
    }
}
