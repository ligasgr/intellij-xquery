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

package org.intellij.xquery.runner.state.run;

import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;

/**
 * User: ligasgr
 * Date: 19/11/13
 * Time: 12:39
 */
public class VariablesAccessor {
    public void writeVariables(XQueryRunConfiguration configuration, Element element) {
        XQueryRunVariables variables = configuration.getVariables();
        if (variables != null) {
            Element variablesElement = XmlSerializer.serialize(variables.getState());
            element.addContent(variablesElement);
        }
    }

    public void readVariables(Element element, XQueryRunConfiguration configuration) {
       XQueryRunVariables variables = new XQueryRunVariables();
        Element variablesElement = element.getChild("variables");
        if (variablesElement != null) {
            XQueryRunVariables localVariables = new XQueryRunVariables();
            XmlSerializer.deserializeInto(localVariables, variablesElement);
            variables.loadState(localVariables);
        }
        configuration.setVariables(variables);
    }
}
