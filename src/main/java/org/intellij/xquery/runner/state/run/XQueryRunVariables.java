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

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * User: ligasgr
 * Date: 22/09/13
 * Time: 00:38
 */
@Tag("variables")
public class XQueryRunVariables implements PersistentStateComponent<XQueryRunVariables> {

    private List<XQueryRunVariable> variables = new ArrayList<XQueryRunVariable>();

    public XQueryRunVariables() {
    }

    public XQueryRunVariables(Collection<XQueryRunVariable> variables) {
        this.variables.addAll(variables);
    }

    @Tag("list")
    @AbstractCollection(surroundWithTag = false)
    public List<XQueryRunVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<XQueryRunVariable> variables) {
        this.variables = variables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XQueryRunVariables that = (XQueryRunVariables) o;

        if (variables != null ? !variables.equals(that.variables) : that.variables != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return variables != null ? variables.hashCode() : 0;
    }

    @Nullable
    @Override
    public XQueryRunVariables getState() {
        return this;
    }

    @Override
    public void loadState(XQueryRunVariables state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
