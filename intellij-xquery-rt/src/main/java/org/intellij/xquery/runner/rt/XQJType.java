/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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

import org.intellij.xquery.runner.rt.binding.AtomicValueBinder;
import org.intellij.xquery.runner.rt.binding.TypeBinder;
import org.intellij.xquery.runner.rt.binding.DocumentBinder;
import org.intellij.xquery.runner.rt.binding.TextBinder;

import javax.xml.xquery.XQItemType;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ligasgr
 * Date: 07/10/13
 * Time: 14:47
 */
public enum XQJType {
    //    ATTRIBUTE("attribute()", XQItemType.XQITEMKIND_ATTRIBUTE, AttributeBinder.class),
//    COMMENT("comment()", XQItemType.XQITEMKIND_COMMENT),
    DOCUMENT("document-node()", XQItemType.XQITEMKIND_DOCUMENT, DocumentBinder.class),
    //    DOCUMENT_ELEMENT("document-element()", XQItemType.XQITEMKIND_DOCUMENT_ELEMENT),
//    DOCUMENT_SCHEMA_ELEMENT("document-schema-element()", XQItemType.XQITEMKIND_DOCUMENT_SCHEMA_ELEMENT),
//    ELEMENT("element()", XQItemType.XQITEMKIND_ELEMENT),
//    ITEM("item()", XQItemType.XQITEMKIND_ITEM),
//    NODE("node()", XQItemType.XQITEMKIND_NODE),
//    PI("processing-instruction()", XQItemType.XQITEMKIND_PI),
    TEXT("text()", XQItemType.XQITEMKIND_TEXT, TextBinder.class),
    //    SCHEMA_ELEMENT("schema-element()", XQItemType.XQITEMKIND_SCHEMA_ELEMENT),
//    SCHEMA_ATTRIBUTE("schema-attribute()", XQItemType.XQITEMKIND_SCHEMA_ATTRIBUTE),
    XS_UNTYPED("xs:untyped", XQItemType.XQBASETYPE_UNTYPED),
    XS_ANY_TYPE("xs:anyType", XQItemType.XQBASETYPE_ANYTYPE),
    XS_ANY_SIMPLE_TYPE("xs:anySimpleType", XQItemType.XQBASETYPE_ANYSIMPLETYPE),
    XS_ANY_ATOMIC_TYPE("xs:anyAtomicType", XQItemType.XQBASETYPE_ANYATOMICTYPE),
    XS_UNTYPED_ATOMIC("xs:untypedAtomic", XQItemType.XQBASETYPE_UNTYPEDATOMIC),
    XS_DAY_TIME_DURATION("xs:dayTimeDuration", XQItemType.XQBASETYPE_DAYTIMEDURATION),
    XS_YEAR_MONTH_DURATION("xs:yearMonthDuration", XQItemType.XQBASETYPE_YEARMONTHDURATION),
    XS_ANY_URI("xs:anyURI", XQItemType.XQBASETYPE_ANYURI),
    XS_BASE_64_BINARY("xs:base64Binary", XQItemType.XQBASETYPE_BASE64BINARY),
    XS_BOOLEAN("xs:boolean", XQItemType.XQBASETYPE_BOOLEAN),
    XS_DATE("xs:date", XQItemType.XQBASETYPE_DATE),
    XS_INT("xs:int", XQItemType.XQBASETYPE_INT),
    XS_INTEGER("xs:integer", XQItemType.XQBASETYPE_INTEGER),
    XS_SHORT("xs:short", XQItemType.XQBASETYPE_SHORT),
    XS_LONG("xs:long", XQItemType.XQBASETYPE_LONG),
    XS_DATETIME("xs:dateTime", XQItemType.XQBASETYPE_DATETIME),
    XS_DECIMAL("xs:decimal", XQItemType.XQBASETYPE_DECIMAL),
    XS_DOUBLE("xs:double", XQItemType.XQBASETYPE_DOUBLE),
    XS_DURATION("xs:duration", XQItemType.XQBASETYPE_DURATION),
    XS_FLOAT("xs:float", XQItemType.XQBASETYPE_FLOAT),
    XS_G_DAY("xs:gDay", XQItemType.XQBASETYPE_GDAY),
    XS_G_MONTH("xs:gMonth", XQItemType.XQBASETYPE_GMONTH),
    XS_G_MONTH_DAY("xs:gMonthDay", XQItemType.XQBASETYPE_GMONTHDAY),
    XS_G_YEAR("xs:gYear", XQItemType.XQBASETYPE_GYEAR),
    XS_G_YEAR_MONTH("xs:gYearMonth", XQItemType.XQBASETYPE_GYEARMONTH),
    XS_HEX_BINARY("xs:hexBinary", XQItemType.XQBASETYPE_HEXBINARY),
    //    XS_NOTATION("xs:NOTATION", XQItemType.XQBASETYPE_NOTATION),
    XS_Q_NAME("xs:QName", XQItemType.XQBASETYPE_QNAME),
    XS_STRING("xs:string", XQItemType.XQBASETYPE_STRING),
    XS_TIME("xs:time", XQItemType.XQBASETYPE_TIME),
    XS_BYTE("xs:byte", XQItemType.XQBASETYPE_BYTE),
    XS_NON_POSITIVE_INTEGER("xs:nonPositiveInteger", XQItemType.XQBASETYPE_NONPOSITIVE_INTEGER),
    XS_NON_NEGATIVE_INTEGER("xs:nonNegativeInteger", XQItemType.XQBASETYPE_NONNEGATIVE_INTEGER),
    XS_NEGATIVE_INTEGER("xs:negativeInteger", XQItemType.XQBASETYPE_NEGATIVE_INTEGER),
    XS_POSITIVE_INTEGER("xs:positiveInteger", XQItemType.XQBASETYPE_POSITIVE_INTEGER),
    XS_UNSIGNED_LONG("xs:unsignedLong", XQItemType.XQBASETYPE_UNSIGNED_LONG),
    XS_UNSIGNED_INT("xs:unsignedInt", XQItemType.XQBASETYPE_UNSIGNED_INT),
    XS_UNSIGNED_SHORT("xs:unsignedShort", XQItemType.XQBASETYPE_UNSIGNED_SHORT),
    XS_UNSIGNED_BYTE("xs:unsignedByte", XQItemType.XQBASETYPE_UNSIGNED_BYTE),
    XS_NORMALIZED_STRING("xs:normalizedString", XQItemType.XQBASETYPE_NORMALIZED_STRING),
    XS_TOKEN("xs:token", XQItemType.XQBASETYPE_TOKEN),
    XS_LANGUAGE("xs:language", XQItemType.XQBASETYPE_LANGUAGE)
//    XS_NAME("xs:Name", XQItemType.XQBASETYPE_NAME),
//    XS_NC_NAME("xs:NCName", XQItemType.XQBASETYPE_NCNAME),
//    XS_NM_TOKEN("xs:NMToken", XQItemType.XQBASETYPE_NMTOKEN),
//    XS_ID("xs:ID", XQItemType.XQBASETYPE_ID),
//    XS_ID_REF("xs:IDREF", XQItemType.XQBASETYPE_IDREF),
//    XS_ENTITY("xs:ENTITY", XQItemType.XQBASETYPE_ENTITY),
//    XS_ID_REFS("xs:IDREFS", XQItemType.XQBASETYPE_IDREFS),
//    XS_ENTITIES("xs:ENTITIES", XQItemType.XQBASETYPE_ENTITIES),
//    XS_NM_TOKENS("xs:NMTOKENS", XQItemType.XQBASETYPE_NMTOKENS);
    ;

    private final String description;
    private final int xqjType;
    private final Class<? extends TypeBinder> typeFactoryClass;

    XQJType(String description, int xqjType) {
        this.description = description;
        this.xqjType = xqjType;
        this.typeFactoryClass = AtomicValueBinder.class;
    }

    XQJType(String description, int xqjType, Class<? extends TypeBinder> typeFactoryClass) {
        this.description = description;
        this.xqjType = xqjType;
        this.typeFactoryClass = typeFactoryClass;
    }

    public String getDescription() {
        return description;
    }

    public int getXqjType() {
        return xqjType;
    }

    public static List<String> getAll() {
        List<String> all = new ArrayList<String>();
        for (XQJType type : XQJType.values()) {
            String singleType = type.getDescription();
            all.add(singleType);
//            all.add(singleType + "?");
//            all.add(singleType + "*");
//            all.add(singleType + "+");
        }
        return all;
    }

    public static Class<? extends TypeBinder> getTypeFactoryClass(String description) {
        for (XQJType type : XQJType.values()) {
            if (type.getDescription().equalsIgnoreCase(description)) {
                return type.typeFactoryClass;
            }
        }
        return AtomicValueBinder.class;
    }

    public static int getXQJTypeForDescription(String description) {
        for (XQJType type : XQJType.values()) {
            if (type.getDescription().equalsIgnoreCase(description)) {
                return type.getXqjType();
            }
        }
        return XQItemType.XQITEMKIND_ATOMIC;
    }
}
