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

package org.intellij.xquery.completion.function;

/**
 * User: ligasgr
 * Date: 08/12/13
 * Time: 00:05
 */
public class BuiltInFunctionSignature implements Comparable<BuiltInFunctionSignature> {

    private final String namespace;
    private final String name;
    private final int arity;
    private final String arguments;
    private final String returnType;

    public BuiltInFunctionSignature(String namespace, String name, int arity, String arguments, String returnType) {
        this.namespace = namespace;
        this.name = name;
        this.arity = arity;
        this.arguments = arguments;
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getArguments() {
        return arguments != null ? arguments : "";
    }

    public String getName() {
        return name;
    }

    public String getNamespace() {
        return namespace;
    }

    @Override
    public int compareTo(BuiltInFunctionSignature that) {
        int result = namespace.compareTo(that.namespace);
        if (result == 0) {
            result = name.compareTo(that.name);
            if (result == 0) {
                result = Integer.signum(arity - that.arity);
            }
        }
        return result;
    }
}
