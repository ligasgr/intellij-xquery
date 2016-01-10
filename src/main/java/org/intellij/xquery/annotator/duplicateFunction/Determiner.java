/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import org.intellij.xquery.model.XQueryQName;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.intellij.xquery.psi.XQueryModuleDecl;
import org.intellij.xquery.psi.XQueryModuleImport;
import org.intellij.xquery.psi.XQueryPrefix;
import org.intellij.xquery.psi.XQueryURILiteral;

import java.util.Collection;

import static com.intellij.util.containers.ContainerUtil.filter;
import static org.intellij.xquery.model.XQueryQNameBuilder.aXQueryQName;
import static org.intellij.xquery.util.StringUtils.removeQuotOrAposIfNeeded;

public class Determiner {

    public static final Condition<XQueryModuleImport> NO_CONDITION = new Condition<XQueryModuleImport>() {
        @Override
        public boolean value(XQueryModuleImport xQueryModuleImport) {
            return true;
        }
    };

    public boolean hasDuplicateDeclarationInTheSameFile(XQueryFunctionName functionName, XQueryFile file) {
        boolean result = false;
        if (functionName.getParent() instanceof XQueryFunctionDecl) {
            final XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
            Collection<XQueryFunctionDecl> filteredDeclarations = filter(file.getFunctionDeclarations(), new Condition<XQueryFunctionDecl>() {
                @Override
                public boolean value(XQueryFunctionDecl otherFunctionDeclaration) {
                    return functionDeclaration != otherFunctionDeclaration;
                }
            });
            result = hasDuplicate(functionName, filteredDeclarations);
        }
        return result;
    }

    public XQueryFile getImportedFileWithDuplicatedDeclaration(XQueryFunctionName functionName, XQueryFile file) {
        XQueryFile result = null;
        if (functionName.getParent() instanceof XQueryFunctionDecl) {
            Collection<XQueryFile> importedFiles = file.getImportedFilesThatExist(NO_CONDITION);
            result = getImportedFileOrNull(functionName, file, importedFiles);
        }
        return result;
    }

    private XQueryFile getImportedFileOrNull(XQueryFunctionName functionName, XQueryFile file, Collection<XQueryFile> importedFiles) {
        for (XQueryFile importedFile : importedFiles) {
            XQueryPrefix functionNamespaceObject = functionName.getPrefix();
            XQueryModuleDecl moduleDeclaration = importedFile.getModuleDeclaration();
            XQueryURILiteral uriLiteral = moduleDeclaration != null ? moduleDeclaration.getURILiteral() : null;
            if (hasDuplicatedNamespaceValue(file, functionNamespaceObject, uriLiteral)) {
                return getImportedFileOrNull(functionName, importedFile);
            }
        }
        return null;
    }

    private boolean hasDuplicatedNamespaceValue(XQueryFile file, XQueryPrefix functionNamespaceObject, XQueryURILiteral uriLiteral) {
        if (functionNamespaceObject != null && uriLiteral != null) {
            String functionNamespace = functionNamespaceObject.getText();
            String namespaceValue = file.mapFunctionPrefixToNamespace(functionNamespace);
            String importedFileNamespaceValue = removeQuotOrAposIfNeeded(uriLiteral.getText());
            if (importedFileNamespaceValue.equals(namespaceValue)) {
                return true;
            }
        }
        return false;
    }

    private XQueryFile getImportedFileOrNull(XQueryFunctionName functionName, XQueryFile importedFile) {
        Collection<XQueryFunctionDecl> importedFunctionDeclarations = importedFile.getFunctionDeclarations();
        for (XQueryFunctionDecl importedFunctionDeclaration : importedFunctionDeclarations) {
            if (nameIsTheSame(functionName, importedFunctionDeclaration) && arityIsTheSame(functionName, importedFunctionDeclaration)) {
                return importedFile;
            }
        }
        return null;
    }

    private boolean hasDuplicate(XQueryFunctionName functionName, Collection<XQueryFunctionDecl> functionDeclarations) {
        for (XQueryFunctionDecl otherFunctionDeclaration : functionDeclarations) {
            if (nameIsTheSame(functionName, otherFunctionDeclaration) && arityIsTheSame(functionName, otherFunctionDeclaration)) {
                return true;
            }
        }
        return false;
    }

    private boolean nameIsTheSame(XQueryFunctionName functionName, XQueryFunctionDecl otherFunctionDeclaration) {
        XQueryFunctionName otherFunctionName = otherFunctionDeclaration.getFunctionName();
        if (otherFunctionName != null) {
            XQueryQName<XQueryFunctionName> source = aXQueryQName(functionName).build();
            XQueryQName<XQueryFunctionName> checkedQName = aXQueryQName(otherFunctionName).build();
            return source.equals(checkedQName);
        } else {
            return false;
        }
    }

    private boolean arityIsTheSame(XQueryFunctionName functionName, XQueryFunctionDecl otherFunctionDeclaration) {
        PsiElement parent = functionName.getParent();
        return otherFunctionDeclaration.getArity() == ((XQueryFunctionDecl) parent).getArity();
    }
}
