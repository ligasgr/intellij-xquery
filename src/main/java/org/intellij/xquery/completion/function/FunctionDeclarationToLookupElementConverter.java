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
import org.intellij.xquery.psi.XQueryFunctionDecl;

/**
 * User: ligasgr
 * Date: 07/12/13
 * Time: 19:14
 */
public class FunctionDeclarationToLookupElementConverter {
    public static LookupElement convert(XQueryFunctionDecl functionDeclaration, String key) {
        return LookupElementBuilder.create(functionDeclaration.getFunctionName(), key)
                .withIcon(XQueryIcons.FUNCTION_ICON)
                .withTailText(getTailText(functionDeclaration), true)
                .withTypeText(getTypeText(functionDeclaration))
                .withInsertHandler(new XQueryFunctionInsertHandler())
                .withLookupString(functionDeclaration.getFunctionName().getLocalNameText())
                ;
    }

    private static String getTypeText(XQueryFunctionDecl functionDeclaration) {
        return functionDeclaration.getSequenceType() != null ? functionDeclaration.getSequenceType()
                .getText() : "item()*";
    }

    private static String getTailText(XQueryFunctionDecl functionDeclaration) {
        return functionDeclaration.getParamList() != null ? functionDeclaration.getParamList().getText() :
                    "";
    }
}
