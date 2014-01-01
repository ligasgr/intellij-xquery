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

package org.intellij.xquery.completion;

import com.intellij.patterns.PatternCondition;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

/**
* User: ligasgr
* Date: 28/11/13
* Time: 14:28
*/
class ReferencePattern extends PsiElementPattern<PsiElement, ReferencePattern> {

    ReferencePattern() {
        super(PsiElement.class);
    }

    public ReferencePattern withReferenceOfAnyOfTypes(
            final Class<? extends PsiReference>... referenceClasses) {
        return with(new PatternCondition<PsiElement>("withReference") {
            @Override
            public boolean accepts(@NotNull final PsiElement t, final ProcessingContext context) {
                PsiReference ref = t.findReferenceAt(0);
                return ref != null && isOfAnyOfRequiredTypes(ref);
            }

            private boolean isOfAnyOfRequiredTypes(PsiReference ref) {
                for (Class<? extends PsiReference> referenceClass : referenceClasses) {
                    if (ref.getClass().isAssignableFrom(referenceClass))
                        return true;
                }
                return false;
            }
        });
    }

    public ReferencePattern withoutReference() {
        return with(new PatternCondition<PsiElement>("withoutReference") {
            @Override
            public boolean accepts(@NotNull final PsiElement t, final ProcessingContext context) {
                PsiReference ref = t.findReferenceAt(0);
                return ref == null;
            }
        });
    }
}
