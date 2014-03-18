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

import java.util.Properties;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 14:08
 */
public class OutputMethodFactory {
    public static final String METHOD_PROPERTY_NAME = "method";
    public static final String OUTPUT_TYPE_XML = "xml";
    private final XQueryRunConfig config;

    public OutputMethodFactory(XQueryRunConfig config) {
        this.config = config;
    }

    public Properties getOutputMethodProperties() {
        Properties props = new Properties();
        props.setProperty(METHOD_PROPERTY_NAME, OUTPUT_TYPE_XML);
        return props;
    }
}
