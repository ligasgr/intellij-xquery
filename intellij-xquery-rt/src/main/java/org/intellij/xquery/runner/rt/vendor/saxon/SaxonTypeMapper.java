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

package org.intellij.xquery.runner.rt.vendor.saxon;

import net.sf.saxon.s9api.ItemType;
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
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE_TIME_STAMP;
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
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NORMALIZED_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_SHORT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TIME;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TOKEN;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_INT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_LONG;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_SHORT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED_ATOMIC;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_YEAR_MONTH_DURATION;

/**
 * User: ligasgr
 * Date: 10/01/14
 * Time: 22:22
 */
public class SaxonTypeMapper implements TypeMapper<ItemType> {

    private static Map<XQueryItemType, ItemType> typeMap = new HashMap<XQueryItemType, ItemType>();

    static {
        typeMap.put(DOCUMENT, ItemType.ANY_NODE);
        typeMap.put(TEXT, ItemType.ANY_NODE);
        typeMap.put(XS_UNTYPED, ItemType.UNTYPED_ATOMIC);
        typeMap.put(XS_UNTYPED_ATOMIC, ItemType.UNTYPED_ATOMIC);
        typeMap.put(XS_DAY_TIME_DURATION, ItemType.DAY_TIME_DURATION);
        typeMap.put(XS_YEAR_MONTH_DURATION, ItemType.YEAR_MONTH_DURATION);
        typeMap.put(XS_ANY_URI, ItemType.ANY_URI);
        typeMap.put(XS_BASE_64_BINARY, ItemType.BASE64_BINARY);
        typeMap.put(XS_BOOLEAN, ItemType.BOOLEAN);
        typeMap.put(XS_DATE, ItemType.DATE);
        typeMap.put(XS_INT, ItemType.INT);
        typeMap.put(XS_INTEGER, ItemType.INT);
        typeMap.put(XS_SHORT, ItemType.SHORT);
        typeMap.put(XS_LONG, ItemType.LONG);
        typeMap.put(XS_DATE_TIME, ItemType.DATE_TIME);
        typeMap.put(XS_DATE_TIME_STAMP, ItemType.DATE_TIME_STAMP);
        typeMap.put(XS_DECIMAL, ItemType.DOUBLE);
        typeMap.put(XS_DOUBLE, ItemType.DOUBLE);
        typeMap.put(XS_DURATION, ItemType.DURATION);
        typeMap.put(XS_FLOAT, ItemType.FLOAT);
        typeMap.put(XS_G_DAY, ItemType.G_DAY);
        typeMap.put(XS_G_MONTH, ItemType.G_MONTH);
        typeMap.put(XS_G_MONTH_DAY, ItemType.G_MONTH_DAY);
        typeMap.put(XS_G_YEAR, ItemType.G_YEAR);
        typeMap.put(XS_G_YEAR_MONTH, ItemType.G_YEAR_MONTH);
        typeMap.put(XS_HEX_BINARY, ItemType.HEX_BINARY);
        typeMap.put(XS_STRING, ItemType.STRING);
        typeMap.put(XS_TIME, ItemType.TIME);
        typeMap.put(XS_BYTE, ItemType.BYTE);
        typeMap.put(XS_NON_POSITIVE_INTEGER, ItemType.NON_POSITIVE_INTEGER);
        typeMap.put(XS_NON_NEGATIVE_INTEGER, ItemType.NON_NEGATIVE_INTEGER);
        typeMap.put(XS_NEGATIVE_INTEGER, ItemType.NEGATIVE_INTEGER);
        typeMap.put(XS_POSITIVE_INTEGER, ItemType.POSITIVE_INTEGER);
        typeMap.put(XS_UNSIGNED_LONG, ItemType.UNSIGNED_LONG);
        typeMap.put(XS_UNSIGNED_INT, ItemType.UNSIGNED_INT);
        typeMap.put(XS_UNSIGNED_SHORT, ItemType.UNSIGNED_SHORT);
        typeMap.put(XS_UNSIGNED_BYTE, ItemType.UNSIGNED_BYTE);
        typeMap.put(XS_NORMALIZED_STRING, ItemType.NORMALIZED_STRING);
        typeMap.put(XS_TOKEN, ItemType.TOKEN);
        typeMap.put(XS_LANGUAGE, ItemType.LANGUAGE);
    }

    @Override
    public ItemType getType(XQueryItemType itemType) {
        ItemType mappedType = typeMap.get(itemType);
        validate(itemType, mappedType);
        return mappedType;
    }

    private void validate(XQueryItemType itemType, ItemType mappedType) {
        if (mappedType == null)
            throw new RuntimeException(itemType.getTextRepresentation() + " is not supported");
    }
}
