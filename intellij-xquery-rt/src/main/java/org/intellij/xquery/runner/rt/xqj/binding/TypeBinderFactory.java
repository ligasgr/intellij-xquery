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

package org.intellij.xquery.runner.rt.xqj.binding;

import org.intellij.xquery.runner.rt.XQueryItemType;

/**
 * User: ligasgr
 * Date: 11/10/13
 * Time: 16:34
 */
public class TypeBinderFactory {
    public TypeBinder getBinder(String type) throws Exception {
        Class<? extends TypeBinder> factoryClass = getTypeFactoryClass(type);
        return factoryClass.newInstance();
    }

    private Class<? extends TypeBinder> getTypeFactoryClass(String type) {
        if (XQueryItemType.DOCUMENT.getTextRepresentation().equals(type)) {
            return DocumentBinder.class;
        }
        if (XQueryItemType.TEXT.getTextRepresentation().equals(type)) {
            return TextBinder.class;
        }
        return AtomicValueBinder.class;
    }
}
