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
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;

import java.util.ArrayList;
import java.util.List;

import static org.intellij.xquery.completion.XQueryCompletionContributor.FUNCTIONS_PRIORITY;
import static org.intellij.xquery.completion.function.GenericFunctionCollector
        .getLookupItemForNonDefaultNamespace;
import static org.intellij.xquery.completion.function.GenericFunctionCollector.getLookupItemsForDefaultNamespace;

/**
 * User: ligasgr
 * Date: 07/12/13
 * Time: 19:17
 */
public class FunctionCollectorForCurrentFile {
    public static List<LookupElement> getLookupItems(XQueryFile file) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        for (XQueryFunctionDecl functionDecl : file.getFunctionDeclarations()) {
            if (functionDecl.hasValidFunctionName()) {
                lookupItems.addAll(getLookupItemsForDeclaration(functionDecl));
            }
        }
        return lookupItems;
    }

    private static List<LookupElement> getLookupItemsForDeclaration(XQueryFunctionDecl functionDecl) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        XQueryFile file = (XQueryFile) functionDecl.getContainingFile();
        XQueryFunctionName functionName = functionDecl.getFunctionName();
        String prefix = functionName.getPrefixText();
        String localName = functionName.getLocalNameText();
        boolean isInDefaultNamespace = file.isPrefixForDefaultFunctionNamespace(prefix);

        if (isInDefaultNamespace) {
            lookupItems.addAll(getLookupItemsForDefaultNamespace(functionDecl, file, localName, FUNCTIONS_PRIORITY));
        } else {
            lookupItems.addAll(getLookupItemForNonDefaultNamespace(functionDecl, prefix, localName, FUNCTIONS_PRIORITY));
        }
        return lookupItems;
    }
}
