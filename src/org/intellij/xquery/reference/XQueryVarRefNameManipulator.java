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
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.intellij.xquery.psi.XQueryElementFactory;
import org.intellij.xquery.psi.XQueryVarRefName;
import org.intellij.xquery.psi.impl.XQueryVarRefNameImpl;

/**
 * User: ligasgr
 * Date: 12/06/13
 * Time: 00:26
 */
public class XQueryVarRefNameManipulator extends AbstractElementManipulator<XQueryVarRefNameImpl> {
    @Override
    public XQueryVarRefNameImpl handleContentChange(XQueryVarRefNameImpl element, TextRange range, String newContent) throws IncorrectOperationException {
        final String oldText = element.getText();
        return (XQueryVarRefNameImpl) element.replace(getUpdatedRef(element, newContent));
    }

    private PsiElement getUpdatedRef(XQueryVarRefNameImpl element, String newName) {
        XQueryVarRefName varName = XQueryElementFactory.createVariableReference(element.getProject(), newName);
        return varName;
    }
}
