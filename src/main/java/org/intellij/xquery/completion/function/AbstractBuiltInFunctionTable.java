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

package org.intellij.xquery.completion.function;

import com.intellij.util.containers.MultiMap;
import org.intellij.xquery.reference.namespace.XQueryPredeclaredNamespace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class AbstractBuiltInFunctionTable implements BuiltInFunctionTable {
    protected final MultiMap<String, BuiltInFunctionSignature> fnMap = new MultiMap<String,
            BuiltInFunctionSignature>() {
        @Override
        protected Collection<BuiltInFunctionSignature> createCollection() {
            return new TreeSet<BuiltInFunctionSignature>();
        }
    };

    protected String ns(String prefix) {
        return XQueryPredeclaredNamespace.getNamespaceForPrefix(prefix);
    }

    protected BuiltInFunctionSignature bif(String prefix, String name, int arity, String arguments,
                                                  String returnType) {
        return new BuiltInFunctionSignature(ns(prefix), name, arity, arguments, returnType);
    }

    public Collection<BuiltInFunctionSignature> getFunctionsSignatures(String namespace) {
        return fnMap.get(namespace);
    }

    public Collection<BuiltInFunctionSignature> getFunctionsSignatures(String namespace, String name) {
        final List<BuiltInFunctionSignature> signatures = new ArrayList<BuiltInFunctionSignature>();
        for (BuiltInFunctionSignature signature : fnMap.get(namespace)) {
            if (name.equals(signature.getName())) {
                signatures.add(signature);
            }
        }
        return signatures;
    }

    public boolean isBuiltInFunction(String namespace, String name) {
        return getFunctionsSignatures(namespace, name).size() > 0;
    }
}
