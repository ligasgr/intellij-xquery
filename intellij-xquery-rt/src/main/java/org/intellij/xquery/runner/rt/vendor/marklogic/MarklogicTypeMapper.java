/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.rt.vendor.marklogic;

import com.marklogic.xcc.types.ValueType;
import org.intellij.xquery.runner.rt.TypeMapper;
import org.intellij.xquery.runner.rt.XQueryItemType;

import java.util.HashMap;
import java.util.Map;

import static org.intellij.xquery.runner.rt.XQueryItemType.DOCUMENT;
import static org.intellij.xquery.runner.rt.XQueryItemType.TEXT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_ANY_URI;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BASE_64_BINARY;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BOOLEAN;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE_TIME;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DAY_TIME_DURATION;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DECIMAL;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DOUBLE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DURATION;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_FLOAT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_DAY;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_MONTH;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_MONTH_DAY;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_YEAR;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_G_YEAR_MONTH;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_HEX_BINARY;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_INT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_LANGUAGE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_LONG;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NORMALIZED_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_SHORT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TIME;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TOKEN;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED_ATOMIC;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_YEAR_MONTH_DURATION;

public class MarklogicTypeMapper implements TypeMapper<ValueType> {

    private static Map<XQueryItemType, ValueType> typeMap = new HashMap<XQueryItemType, ValueType>();

    static {
        typeMap.put(DOCUMENT, ValueType.DOCUMENT);
        typeMap.put(TEXT, ValueType.XS_STRING);
        typeMap.put(XS_UNTYPED, ValueType.XS_UNTYPED_ATOMIC);
        typeMap.put(XS_UNTYPED_ATOMIC, ValueType.XS_UNTYPED_ATOMIC);
        typeMap.put(XS_DAY_TIME_DURATION, ValueType.XS_DAY_TIME_DURATION);
        typeMap.put(XS_YEAR_MONTH_DURATION, ValueType.XS_YEAR_MONTH_DURATION);
        typeMap.put(XS_ANY_URI, ValueType.XS_ANY_URI);
        typeMap.put(XS_BASE_64_BINARY, ValueType.XS_BASE64_BINARY);
        typeMap.put(XS_BOOLEAN, ValueType.XS_BOOLEAN);
        typeMap.put(XS_DATE, ValueType.XS_DATE);
        typeMap.put(XS_INT, ValueType.XS_INTEGER);
        typeMap.put(XS_INTEGER, ValueType.XS_INTEGER);
        typeMap.put(XS_SHORT, ValueType.XS_INTEGER);
        typeMap.put(XS_LONG, ValueType.XS_INTEGER);
        typeMap.put(XS_DATE_TIME, ValueType.XS_DATE_TIME);
        typeMap.put(XS_DECIMAL, ValueType.XS_DOUBLE);
        typeMap.put(XS_DOUBLE, ValueType.XS_DOUBLE);
        typeMap.put(XS_DURATION, ValueType.XS_DURATION);
        typeMap.put(XS_FLOAT, ValueType.XS_FLOAT);
        typeMap.put(XS_G_DAY, ValueType.XS_GDAY);
        typeMap.put(XS_G_MONTH, ValueType.XS_GMONTH);
        typeMap.put(XS_G_MONTH_DAY, ValueType.XS_GMONTH_DAY);
        typeMap.put(XS_G_YEAR, ValueType.XS_GYEAR);
        typeMap.put(XS_G_YEAR_MONTH, ValueType.XS_GYEAR_MONTH);
        typeMap.put(XS_HEX_BINARY, ValueType.XS_HEX_BINARY);
        typeMap.put(XS_STRING, ValueType.XS_STRING);
        typeMap.put(XS_BYTE, ValueType.XS_HEX_BINARY);
        typeMap.put(XS_TIME, ValueType.XS_TIME);
        typeMap.put(XS_NORMALIZED_STRING, ValueType.XS_STRING);
        typeMap.put(XS_TOKEN, ValueType.XS_STRING);
        typeMap.put(XS_LANGUAGE, ValueType.XS_STRING);
    }

    @Override
    public ValueType getType(XQueryItemType itemType) {
        ValueType mappedType = typeMap.get(itemType);
        validate(itemType, mappedType);
        return mappedType;
    }

    private void validate(XQueryItemType itemType, ValueType mappedType) {
        if (mappedType == null)
            throw new RuntimeException(itemType.getTextRepresentation() + " is not supported");
    }
}
