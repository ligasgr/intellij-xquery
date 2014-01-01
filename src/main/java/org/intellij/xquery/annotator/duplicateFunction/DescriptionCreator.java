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

package org.intellij.xquery.annotator.duplicateFunction;

import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryParamList;

import static java.lang.String.format;

public class DescriptionCreator {

    public static final String DUPLICATE_FUNCTION_TOOLTIP = "'%s' is already defined in '%s'";

    public String createDescription(XQueryFunctionName functionName, XQueryFile file) {
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
        XQueryParamList paramList = functionDeclaration.getParamList();
        String functionArityString = "";
        if (paramList != null && paramList.getParamList().size() > 0) {
            functionArityString = "#" + paramList.getParamList().size();
        }
        return format(DUPLICATE_FUNCTION_TOOLTIP, functionName.getName() + functionArityString, file.getName());
    }
}
