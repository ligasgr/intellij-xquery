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

package org.intellij.xquery.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleImportPath;
import org.intellij.xquery.psi.XQueryUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ligasgr
 * Date: 02/07/13
 * Time: 14:17
 */
public class XQueryModuleReference extends PsiReferenceBase<XQueryModuleImportPath> implements PsiPolyVariantReference {
    private String filename;

    public XQueryModuleReference(XQueryModuleImportPath element, String filename, TextRange textRange) {
        super(element, textRange);
        this.filename = filename;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<XQueryFile> files = XQueryUtil.findXQueryFileByName(myElement.getProject(), filename);
        List<ResolveResult> results = new ArrayList<ResolveResult>(files.size());
        for (PsiFile file : files) {
            results.add(new PsiElementResolveResult(file));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }
}
