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

package org.intellij.xquery.runner.rt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: ligasgr
 * Date: 07/10/13
 * Time: 14:47
 */
public enum XQueryItemType {
    //    ATTRIBUTE("attribute()"),
//    COMMENT("comment()"),
    DOCUMENT("document-node()"),
    //    DOCUMENT_ELEMENT("document-element()"),
//    DOCUMENT_SCHEMA_ELEMENT("document-schema-element()"),
//    ELEMENT("element()"),
//    ITEM("item()"),
//    NODE("node()"),
//    PI("processing-instruction()"),
    TEXT("text()"),
    //    SCHEMA_ELEMENT("schema-element()"),
//    SCHEMA_ATTRIBUTE("schema-attribute()"),
    XS_UNTYPED("xs:untyped"),
    XS_UNTYPED_ATOMIC("xs:untypedAtomic"),
    XS_DAY_TIME_DURATION("xs:dayTimeDuration"),
    XS_YEAR_MONTH_DURATION("xs:yearMonthDuration"),
    XS_ANY_URI("xs:anyURI"),
    XS_BASE_64_BINARY("xs:base64Binary"),
    XS_BOOLEAN("xs:boolean"),
    XS_DATE("xs:date"),
    XS_INT("xs:int"),
    XS_INTEGER("xs:integer"),
    XS_SHORT("xs:short"),
    XS_LONG("xs:long"),
    XS_DATE_TIME("xs:dateTime"),
    XS_DATE_TIME_STAMP("xs:dateTimeStamp"),
    XS_DECIMAL("xs:decimal"),
    XS_DOUBLE("xs:double"),
    XS_DURATION("xs:duration"),
    XS_FLOAT("xs:float"),
    XS_G_DAY("xs:gDay"),
    XS_G_MONTH("xs:gMonth"),
    XS_G_MONTH_DAY("xs:gMonthDay"),
    XS_G_YEAR("xs:gYear"),
    XS_G_YEAR_MONTH("xs:gYearMonth"),
    XS_HEX_BINARY("xs:hexBinary"),
    XS_STRING("xs:string"),
    XS_TIME("xs:time"),
    XS_BYTE("xs:byte"),
    XS_NON_POSITIVE_INTEGER("xs:nonPositiveInteger"),
    XS_NON_NEGATIVE_INTEGER("xs:nonNegativeInteger"),
    XS_NEGATIVE_INTEGER("xs:negativeInteger"),
    XS_POSITIVE_INTEGER("xs:positiveInteger"),
    XS_UNSIGNED_LONG("xs:unsignedLong"),
    XS_UNSIGNED_INT("xs:unsignedInt"),
    XS_UNSIGNED_SHORT("xs:unsignedShort"),
    XS_UNSIGNED_BYTE("xs:unsignedByte"),
    XS_NORMALIZED_STRING("xs:normalizedString"),
    XS_TOKEN("xs:token"),
    XS_LANGUAGE("xs:language")
//    XS_NAME("xs:Name"),
//    XS_NC_NAME("xs:NCName"),
//    XS_NM_TOKEN("xs:NMToken"),
//    XS_ID("xs:ID"),
//    XS_ID_REF("xs:IDREF"),
//    XS_ENTITY("xs:ENTITY"),
//    XS_ID_REFS("xs:IDREFS"),
//    XS_ENTITIES("xs:ENTITIES"),
//    XS_NM_TOKENS("xs:NMTOKENS", XQItemType.XQBASETYPE_NMTOKENS);
    ;
    private static Map<String, XQueryItemType> textRepresentationToTypeMapping = new HashMap<String, XQueryItemType>();

    static {
        for (XQueryItemType itemType : XQueryItemType.values()) {
            textRepresentationToTypeMapping.put(itemType.textRepresentation, itemType);
        }
    }

    private final String textRepresentation;

    XQueryItemType(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    public static List<String> getAll() {
        List<String> all = new ArrayList<String>();
        for (XQueryItemType type : XQueryItemType.values()) {
            String singleType = type.getTextRepresentation();
            all.add(singleType);
//            all.add(singleType + "?");
//            all.add(singleType + "*");
//            all.add(singleType + "+");
        }
        return all;
    }

    public static XQueryItemType valueFor(String textRepresentation) {
        return textRepresentationToTypeMapping.get(textRepresentation);
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }

}
