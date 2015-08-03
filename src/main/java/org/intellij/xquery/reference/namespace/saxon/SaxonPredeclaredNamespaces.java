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

package org.intellij.xquery.reference.namespace.saxon;

import com.intellij.openapi.util.Pair;
import org.intellij.xquery.reference.namespace.XQuery30PredeclaredNamespaces;

public class SaxonPredeclaredNamespaces extends XQuery30PredeclaredNamespaces {

    {
        prefixToNamespaceMap.put("expath-file", Pair.create("http://expath.org/ns/file", false));
        prefixToNamespaceMap.put("expath-zip", Pair.create("http://expath.org/ns/zip", false));
        prefixToNamespaceMap.put("exslt-common", Pair.create("http://exslt.org/common", false));
        prefixToNamespaceMap.put("exslt-math", Pair.create("http://exslt.org/math", false));
        prefixToNamespaceMap.put("exslt-random", Pair.create("http://exslt.org/random", false));
        prefixToNamespaceMap.put("saxon", Pair.create("http://saxon.sf.net/", true));
    }
}
