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
import org.intellij.xquery.psi.XQueryModuleImport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.intellij.xquery.completion.XQueryCompletionContributor.EXTERNAL_FUNCTIONS_PRIORITY;
import static org.intellij.xquery.completion.function.GenericFunctionCollector
        .getLookupItemForNonDefaultNamespace;
import static org.intellij.xquery.completion.function.GenericFunctionCollector
        .getLookupItemsForDefaultNamespaceWhenImported;
import static org.intellij.xquery.psi.XQueryUtil.getReferencesToExistingFilesInImport;

/**
 * User: ligasgr
 * Date: 07/12/13
 * Time: 19:20
 */
public class FunctionCollectorForImportedFiles {
    public static List<LookupElement> getLookupItems(XQueryFile file) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        for (XQueryModuleImport moduleImport : file.getModuleImports()) {
            lookupItems.addAll(getLookupItemsFromImport(file, getTargetPrefix(moduleImport), moduleImport));
        }
        return lookupItems;
    }

    private static String getTargetPrefix(XQueryModuleImport moduleImport) {
        String targetPrefix = null;
        if (moduleImport.getNamespacePrefix() != null) {
            targetPrefix = moduleImport.getNamespacePrefix().getName();
        }
        return targetPrefix;
    }

    private static List<LookupElement> getLookupItemsFromImport(XQueryFile targetFile, String targetPrefix,
                                                                XQueryModuleImport moduleImport) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        for (XQueryFile importedFile : getReferencesToExistingFilesInImport(moduleImport)) {
            lookupItems.addAll(getLookupItemsFromImportedFile(targetPrefix, importedFile, targetFile));
        }
        return lookupItems;
    }

    private static List<LookupElement> getLookupItemsFromImportedFile(String targetPrefix, XQueryFile importedFile,
                                                                      XQueryFile targetFile) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        for (final XQueryFunctionDecl functionDecl : importedFile.getFunctionDeclarations()) {
            if (functionDecl.hasValidFunctionName() && functionDecl.isPublic()) {
                lookupItems.addAll(getLookupItemsForDeclaration(functionDecl, targetPrefix, targetFile));
            }
        }
        return lookupItems;
    }

    private static Collection<? extends LookupElement> getLookupItemsForDeclaration(XQueryFunctionDecl functionDecl,
                                                                                    String targetPrefix,
                                                                                    XQueryFile targetFile) {
        List<LookupElement> lookupItems = new ArrayList<LookupElement>();
        XQueryFunctionName functionName = functionDecl.getFunctionName();
        String localName = functionName.getLocalNameText();
        boolean isInDefaultNamespace = targetFile.isPrefixForDefaultFunctionNamespace(targetPrefix);

        if (isInDefaultNamespace) {
            lookupItems.addAll(getLookupItemsForDefaultNamespaceWhenImported(functionDecl, targetFile, localName, targetPrefix, EXTERNAL_FUNCTIONS_PRIORITY));
        } else {
            lookupItems.addAll(getLookupItemForNonDefaultNamespace(functionDecl, targetPrefix, localName, EXTERNAL_FUNCTIONS_PRIORITY));
        }
        return lookupItems;
    }
}
