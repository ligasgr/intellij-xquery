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

package org.intellij.xquery.reference.namespace.sedna;

import com.intellij.openapi.util.Pair;
import org.intellij.xquery.reference.namespace.PredeclaredNamespaces;

public class SednaPredeclaredNamespaces extends PredeclaredNamespaces {
    {
        prefixToNamespaceMap.put("xml", Pair.create("http://www.w3.org/XML/1998/namespace", true));
        prefixToNamespaceMap.put("xs", Pair.create("http://www.w3.org/2001/XMLSchema", true));
        prefixToNamespaceMap.put("xsi", Pair.create("http://www.w3.org/2001/XMLSchema-instance", true));
        prefixToNamespaceMap.put("fn", Pair.create("http://www.w3.org/2005/xpath-functions", true));
        prefixToNamespaceMap.put("math", Pair.create("http://www.w3.org/2005/xpath-functions/math", true));
        prefixToNamespaceMap.put("err", Pair.create("http://www.w3.org/2005/xqt-errors", true));
        prefixToNamespaceMap.put("local", Pair.create("http://www.w3.org/2005/xquery-local-functions", true));
        prefixToNamespaceMap.put("xmlns", Pair.create("", true));
        prefixToNamespaceMap.put("se", Pair.create("http://www.modis.ispras.ru/sedna", true));
        prefixToNamespaceMap.put("sql", Pair.create("http://modis.ispras.ru/Sedna/SQL", false));
    }
}
