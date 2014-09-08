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

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import org.intellij.xquery.icons.XQueryIcons;

/**
 * User: ligasgr
 * Date: 08/12/13
 * Time: 00:27
 */
public class BuiltInFunctionSignatureToLookupElementConverter {
    public static LookupElement convert(BuiltInFunctionSignature functionSignature, String key) {
        return LookupElementBuilder.create(functionSignature, key)
                .withIcon(XQueryIcons.FUNCTION_ICON)
                .withTailText(getTailText(functionSignature), true)
                .withTypeText(getTypeText(functionSignature))
                .withInsertHandler(new XQueryFunctionInsertHandler())
                .withLookupString(functionSignature.getName())
                ;
    }

    private static String getTypeText(BuiltInFunctionSignature functionSignature) {
        return functionSignature.getReturnType();
    }

    private static String getTailText(BuiltInFunctionSignature functionSignature) {
        return "(" + functionSignature.getArguments() + ")";
    }
}
