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

package org.intellij.xquery;

public enum XQueryFlavour {
    STANDARD_30("XQuery 3.0 Standard"),
    BASEX("BaseX"),
    EXIST("eXist"),
    MARKLOGIC("MarkLogic"),
    SAXON("Saxon"),
    SEDNA("Sedna"),
    ZORBA("Zorba");

    private final String presentableName;

    XQueryFlavour(String presentableName) {
        this.presentableName = presentableName;
    }

    public String getPresentableName() {
        return presentableName;
    }
}
