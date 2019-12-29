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

package org.intellij.xquery.completion.function.bif;

import org.intellij.xquery.completion.function.AbstractBuiltInFunctionTable;
import org.intellij.xquery.reference.namespace.PredeclaredNamespaces;
import org.intellij.xquery.reference.namespace.marklogic.MarkLogicPredeclaredNamespaces;

public class MarkLogicBuiltInFunctionTable extends AbstractBuiltInFunctionTable
{
    private PredeclaredNamespaces predeclaredNamespaces = new MarkLogicPredeclaredNamespaces();

    {
        loadBifFile("/bif/xs.bif");
        loadBifFile("/bif/marklogic.bif");
        loadBifFile("/bif/throwbacks.bif");
    }

    @Override
    public PredeclaredNamespaces predeclaredNamespaces()
    {
        return predeclaredNamespaces;
    }
}
