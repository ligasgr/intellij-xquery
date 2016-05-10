/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

import com.intellij.lang.DefaultWordCompletionFilter;
import com.intellij.psi.tree.IElementType;
import org.intellij.xquery.psi.XQueryTypes;

/**
 * User: ligasgr
 * Date: 07/07/13
 * Time: 17:28
 */
public class XQueryWordCompletionFilter extends DefaultWordCompletionFilter {
    public boolean isWordCompletionEnabledIn(final IElementType element) {
        return super.isWordCompletionEnabledIn(element);
    }
}