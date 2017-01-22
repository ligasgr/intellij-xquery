/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.model;

public enum XQueryLanguageVersion {
    V3_1("3.1"),
    V3_0("3.0"),
    V1_0("1.0"),
    V1_0_ML("1.0-ml"),
    V0_9_ML("0.9-ml");

    public final String versionString;

    XQueryLanguageVersion(String versionString) {
        this.versionString = versionString;
    }

    public static XQueryLanguageVersion valueFor(String versionString) {
        for (XQueryLanguageVersion XQueryLanguageVersion : values()) {
            if (XQueryLanguageVersion.versionString.equals(versionString))
                return XQueryLanguageVersion;
        }
        return null;
    }
}
