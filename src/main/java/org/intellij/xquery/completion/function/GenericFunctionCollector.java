/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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

import static org.intellij.xquery.completion.function.FunctionDeclarationToLookupElementConverter.convert;

/**
 * User: ligasgr
 * Date: 07/12/13
 * Time: 22:52
 */
public class GenericFunctionCollector {

    public static List<LookupElement> getLookupItemsForDefaultNamespace(XQueryFunctionDecl functionDecl,
                                                                        XQueryFile targetFile, String localName) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        lookupItems.add(convert(functionDecl, localName));
        for (XQueryNamespaceDecl namespaceDeclaration : targetFile.getNamespaceDeclarationsMatchingDefaultNamespace()) {
            String namespaceDeclarationPrefix = namespaceDeclaration.getNamespacePrefix().getText();
            lookupItems.add(convert(functionDecl, namespaceDeclarationPrefix + ":" + localName));
        }
        return lookupItems;
    }

    public static List<LookupElement> getLookupItemForNonDefaultNamespace(XQueryFunctionDecl functionDecl,
                                                                          String prefix, String localName) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        if (prefix != null) {
            lookupItems.add(convert(functionDecl, prefix + ":" + localName));
        } else {
            lookupItems.add(convert(functionDecl, localName));
        }
        return lookupItems;
    }

    public static List<LookupElement> getLookupItemsForDefaultNamespaceWhenImported(XQueryFunctionDecl functionDecl,
                                                                                    XQueryFile targetFile,
                                                                                    String localName,
                                                                                    String targetPrefix) {
        List<LookupElement> lookupItems = getLookupItemsForDefaultNamespace(functionDecl, targetFile, localName);
        lookupItems.add(convert(functionDecl, targetPrefix + ":" + localName));
        return lookupItems;
    }
}
