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

package org.intellij.xquery.runner.rt.debugger;

public class Variable {
    public String name;
    public String type;
    public String value;
    public String scope;

    public Variable (String name, String type, String value)
    {
        this (name, type, value, null);
    }

    public Variable(String name, String type, String value, String scope)
    {
        this.name = name;
        this.value = value;

        if (scope == null) {
             this.type = type;
        } else {
            this.type = type + ", " + scope;
        }
    }

    @Override
    public String toString ()
    {
        return "Variable: name=" + name + ", type=" + type + ", value=" + value;
    }
}
