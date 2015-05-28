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
import org.intellij.xquery.psi.XQueryNamespaceDecl;

import java.util.ArrayList;
import java.util.List;

import static org.intellij.xquery.completion.XQueryCompletionContributor.prioritized;
import static org.intellij.xquery.completion.function.FunctionDeclarationToLookupElementConverter.convert;

/**
 * User: ligasgr
 * Date: 07/12/13
 * Time: 22:52
 */
public class GenericFunctionCollector {

    public static List<LookupElement> getLookupItemsForDefaultNamespace(XQueryFunctionDecl functionDecl,
                                                                        XQueryFile targetFile, String localName,
                                                                        int priority) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        lookupItems.add(prioritized(convert(functionDecl, localName), priority));
        for (XQueryNamespaceDecl namespaceDeclaration : targetFile.getNamespaceDeclarationsMatchingDefaultNamespace()) {
            String namespaceDeclarationPrefix = namespaceDeclaration.getNamespacePrefix().getText();
            lookupItems.add(prioritized(convert(functionDecl, namespaceDeclarationPrefix + ":" + localName), priority));
        }
        return lookupItems;
    }

    public static List<LookupElement> getLookupItemForNonDefaultNamespace(XQueryFunctionDecl functionDecl,
                                                                          String prefix, String localName, int priority) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        if (prefix != null) {
            lookupItems.add(prioritized(convert(functionDecl, prefix + ":" + localName), priority));
        } else {
            lookupItems.add(prioritized(convert(functionDecl, localName), priority));
        }
        return lookupItems;
    }

    public static List<LookupElement> getLookupItemsForDefaultNamespaceWhenImported(XQueryFunctionDecl functionDecl,
                                                                                    XQueryFile targetFile,
                                                                                    String localName,
                                                                                    String targetPrefix, int priority) {
        List<LookupElement> lookupItems = getLookupItemsForDefaultNamespace(functionDecl, targetFile, localName, priority);
        lookupItems.add(prioritized(convert(functionDecl, targetPrefix + ":" + localName), priority));
        return lookupItems;
    }
}
