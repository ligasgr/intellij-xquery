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

package org.intellij.xquery.runner.rt;

import javax.xml.namespace.QName;

import static javax.xml.XMLConstants.DEFAULT_NS_PREFIX;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 22:08
 */
public class NameExtractor {
    public QName getName(String name, String namespace) {
        String[] parts = name.split(":");
        if (parts.length < 1 || parts.length > 2) {
            throw new RuntimeException("Variable name '" + name + "' is invalid");
        }
        String namespacePrefix = null;
        String localPart = parts[parts.length - 1];
        if (parts.length == 2) {
            namespacePrefix = parts[0];
        }
        if (namespace != null && namespace.length() > 0) {
            if (namespacePrefix != null) {
                return new QName(namespace, localPart, namespacePrefix);
            } else {
                return new QName(namespace, localPart);
            }
        }
        if (namespacePrefix != null) {
            return new QName(DEFAULT_NS_PREFIX, localPart, namespacePrefix);
        } else {
            return new QName(localPart);
        }
    }
}
