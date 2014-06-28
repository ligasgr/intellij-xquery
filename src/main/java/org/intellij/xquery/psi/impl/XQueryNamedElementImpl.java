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

package org.intellij.xquery.psi.impl;

import com.intellij.lang.ASTNode;
import org.intellij.xquery.psi.XQueryNamedElement;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 08/06/13
 * Time: 21:31
 */
public abstract class XQueryNamedElementImpl extends XQueryPsiElementImpl implements XQueryNamedElement {
    public XQueryNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
