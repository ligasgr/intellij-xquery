/*
 * Copyright 2013 Grzegorz Ligas
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

package org.intellij.xquery.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionInitializationContext;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 31/07/13
 * Time: 14:35
 */
public class XQueryCompletionContributor extends CompletionContributor {

    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        final CharSequence text = context.getEditor().getDocument().getCharsSequence();
        if (context.getStartOffset() > 1 && !text.subSequence(context.getStartOffset() - 1, context.getStartOffset()).equals("$")) {
            context.setDummyIdentifier(CompletionInitializationContext.DUMMY_IDENTIFIER_TRIMMED + "()");
        } else {
            context.setDummyIdentifier(CompletionInitializationContext.DUMMY_IDENTIFIER);
        }
    }
}
