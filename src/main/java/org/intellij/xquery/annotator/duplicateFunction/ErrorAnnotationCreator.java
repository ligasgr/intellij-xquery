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

package org.intellij.xquery.annotator.duplicateFunction;

import com.intellij.lang.annotation.AnnotationHolder;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionName;
import org.jetbrains.annotations.NotNull;

public class ErrorAnnotationCreator {

    private AnnotationHolderModifier annotationHolderModifier = new AnnotationHolderModifier();
    private Determiner determiner = new Determiner();

    public void createDuplicateFunctionDeclarationError(@NotNull AnnotationHolder holder, XQueryFunctionName functionName, XQueryFile file) {
        boolean hasDuplicate = determiner.hasDuplicateDeclarationInTheSameFile(functionName, file);
        if (hasDuplicate) {
            annotationHolderModifier.modifyHolder(holder, functionName, file);
        }

        XQueryFile importedFileWithDuplicatedDeclaration = determiner.getImportedFileWithDuplicatedDeclaration(functionName, file);
        if (importedFileWithDuplicatedDeclaration != null) {
            annotationHolderModifier.modifyHolder(holder, functionName, importedFileWithDuplicatedDeclaration);
        }
    }

}
