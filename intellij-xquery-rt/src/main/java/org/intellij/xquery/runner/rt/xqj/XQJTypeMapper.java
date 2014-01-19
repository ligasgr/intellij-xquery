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

package org.intellij.xquery.runner.rt.xqj;

import org.intellij.xquery.runner.rt.TypeMapper;
import org.intellij.xquery.runner.rt.XQueryItemType;

import javax.xml.xquery.XQItemType;
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
 * Date: 12/01/14
 * Time: 10:10
 */
public class XQJTypeMapper implements TypeMapper<Integer> {
    private static Map<XQueryItemType, Integer> typeMap = new HashMap<XQueryItemType, Integer>();

    static {
        typeMap.put(DOCUMENT, XQItemType.XQITEMKIND_DOCUMENT);
        typeMap.put(TEXT, XQItemType.XQITEMKIND_TEXT);
        typeMap.put(XS_UNTYPED, XQItemType.XQBASETYPE_UNTYPED);
        typeMap.put(XS_UNTYPED_ATOMIC, XQItemType.XQBASETYPE_UNTYPEDATOMIC);
        typeMap.put(XS_DAY_TIME_DURATION, XQItemType.XQBASETYPE_DAYTIMEDURATION);
        typeMap.put(XS_YEAR_MONTH_DURATION, XQItemType.XQBASETYPE_YEARMONTHDURATION);
        typeMap.put(XS_ANY_URI, XQItemType.XQBASETYPE_ANYURI);
        typeMap.put(XS_BASE_64_BINARY, XQItemType.XQBASETYPE_BASE64BINARY);
        typeMap.put(XS_BOOLEAN, XQItemType.XQBASETYPE_BOOLEAN);
        typeMap.put(XS_DATE, XQItemType.XQBASETYPE_DATE);
        typeMap.put(XS_INT, XQItemType.XQBASETYPE_INT);
        typeMap.put(XS_INTEGER, XQItemType.XQBASETYPE_INTEGER);
        typeMap.put(XS_SHORT, XQItemType.XQBASETYPE_SHORT);
        typeMap.put(XS_LONG, XQItemType.XQBASETYPE_LONG);
        typeMap.put(XS_DATE_TIME, XQItemType.XQBASETYPE_DATETIME);
        typeMap.put(XS_DECIMAL, XQItemType.XQBASETYPE_DECIMAL);
        typeMap.put(XS_DOUBLE, XQItemType.XQBASETYPE_DOUBLE);
        typeMap.put(XS_DURATION, XQItemType.XQBASETYPE_DURATION);
        typeMap.put(XS_FLOAT, XQItemType.XQBASETYPE_FLOAT);
        typeMap.put(XS_G_DAY, XQItemType.XQBASETYPE_GDAY);
        typeMap.put(XS_G_MONTH, XQItemType.XQBASETYPE_GMONTH);
        typeMap.put(XS_G_MONTH_DAY, XQItemType.XQBASETYPE_GMONTHDAY);
        typeMap.put(XS_G_YEAR, XQItemType.XQBASETYPE_GYEAR);
        typeMap.put(XS_G_YEAR_MONTH, XQItemType.XQBASETYPE_GYEARMONTH);
        typeMap.put(XS_HEX_BINARY, XQItemType.XQBASETYPE_HEXBINARY);
        typeMap.put(XS_STRING, XQItemType.XQBASETYPE_STRING);
        typeMap.put(XS_TIME, XQItemType.XQBASETYPE_TIME);
        typeMap.put(XS_BYTE, XQItemType.XQBASETYPE_BYTE);
        typeMap.put(XS_NON_POSITIVE_INTEGER, XQItemType.XQBASETYPE_NONPOSITIVE_INTEGER);
        typeMap.put(XS_NON_NEGATIVE_INTEGER, XQItemType.XQBASETYPE_NONNEGATIVE_INTEGER);
        typeMap.put(XS_NEGATIVE_INTEGER, XQItemType.XQBASETYPE_NEGATIVE_INTEGER);
        typeMap.put(XS_POSITIVE_INTEGER, XQItemType.XQBASETYPE_POSITIVE_INTEGER);
        typeMap.put(XS_UNSIGNED_LONG, XQItemType.XQBASETYPE_UNSIGNED_LONG);
        typeMap.put(XS_UNSIGNED_INT, XQItemType.XQBASETYPE_UNSIGNED_INT);
        typeMap.put(XS_UNSIGNED_SHORT, XQItemType.XQBASETYPE_UNSIGNED_SHORT);
        typeMap.put(XS_UNSIGNED_BYTE, XQItemType.XQBASETYPE_UNSIGNED_BYTE);
        typeMap.put(XS_NORMALIZED_STRING, XQItemType.XQBASETYPE_NORMALIZED_STRING);
        typeMap.put(XS_TOKEN, XQItemType.XQBASETYPE_TOKEN);
        typeMap.put(XS_LANGUAGE, XQItemType.XQBASETYPE_LANGUAGE);
    }

    @Override
    public Integer getType(XQueryItemType itemType) {
        Integer mappedType = typeMap.get(itemType);
        validate(itemType, mappedType);
        return mappedType;
    }

    private void validate(XQueryItemType itemType, Integer mappedType) {
        if (mappedType == null)
            throw new RuntimeException(itemType.getTextRepresentation() + " is not supported");
    }
}
