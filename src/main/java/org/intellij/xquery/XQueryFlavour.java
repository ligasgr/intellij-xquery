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

package org.intellij.xquery;

import org.intellij.xquery.completion.function.BuiltInFunctionTable;
import org.intellij.xquery.completion.function.XQuery30BuiltInFunctionTable;

public enum XQueryFlavour {
    STANDARD_30("XQuery 3.0 Standard", new XQuery30BuiltInFunctionTable()),
    BASEX("BaseX", new XQuery30BuiltInFunctionTable()),
    EXIST("eXist", new XQuery30BuiltInFunctionTable()),
    MARKLOGIC("MarkLogic", new XQuery30BuiltInFunctionTable()),
    SAXON("Saxon", new XQuery30BuiltInFunctionTable()),
    SEDNA("Sedna", new XQuery30BuiltInFunctionTable()),
    ZORBA("Zorba", new XQuery30BuiltInFunctionTable());

    private final String presentableName;
    private final BuiltInFunctionTable bifTable;

    XQueryFlavour(String presentableName, BuiltInFunctionTable bifTable) {
        this.presentableName = presentableName;
        this.bifTable = bifTable;
    }

    public String getPresentableName() {
        return presentableName;
    }

    public BuiltInFunctionTable getBifTable() {
        return bifTable;
    }
}
