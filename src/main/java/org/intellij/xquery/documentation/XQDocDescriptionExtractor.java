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

package org.intellij.xquery.documentation;

import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;

import static org.intellij.xquery.documentation.XQDocTransformer.transformXQDoc;
import static org.intellij.xquery.psi.XQueryBasicTypes.DOC_COMMENT_CONTENT;

/**
 * User: ligasgr
 * Date: 29/12/13
 * Time: 09:41
 */
public class XQDocDescriptionExtractor {
    public static String getXQDocDescription(PsiElement source) {
        final PsiComment comment = getPreviousDocumentationComment(source);
        if (comment != null) {
            String documentationComment = comment.getText();
            return transformXQDoc(documentationComment);
        }
        return null;
    }

    private static PsiComment getPreviousDocumentationComment(PsiElement sibling) {
        if (sibling == null) return null;
        for (PsiElement child = sibling.getPrevSibling();
             child != null && (child instanceof PsiWhiteSpace || child instanceof PsiComment);
             child = child.getPrevSibling()) {
            if (PsiComment.class.isInstance(child) && child.getNode().getElementType() == DOC_COMMENT_CONTENT) {
                return (PsiComment) child;
            }
        }
        return null;
    }
}
