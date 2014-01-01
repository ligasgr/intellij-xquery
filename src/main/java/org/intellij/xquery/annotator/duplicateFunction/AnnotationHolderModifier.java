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

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.util.TextRange;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryFunctionDecl;
import org.intellij.xquery.psi.XQueryFunctionName;

public class AnnotationHolderModifier {
    private TextRangeCalculator textRangeCalculator = new TextRangeCalculator();
    private DescriptionCreator descriptionProducer = new DescriptionCreator();

    public void modifyHolder(AnnotationHolder holder, XQueryFunctionName functionName, XQueryFile file) {
        XQueryFunctionDecl functionDeclaration = (XQueryFunctionDecl) functionName.getParent();
        TextRange textRange = textRangeCalculator.calculateTextRange(functionDeclaration);
        String description = descriptionProducer.createDescription(functionName, file);
        holder.createErrorAnnotation(textRange, description);
    }
}