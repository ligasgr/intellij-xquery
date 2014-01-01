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
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.Text;
import org.jetbrains.annotations.Nullable;

import static org.intellij.xquery.util.StringUtils.EMPTY;

/**
 * User: ligasgr
 * Date: 22/09/13
 * Time: 00:10
 */
@Tag("variable")
public class XQueryRunVariable implements PersistentStateComponent<XQueryRunVariable> {
    private String name = EMPTY;
    private String namespace = EMPTY;
    private String type = EMPTY;
    private String value = EMPTY;
    private boolean active = false;

    public XQueryRunVariable() {
    }

    public XQueryRunVariable(String name, String namespace, String type, String value, boolean active) {
        this.name = name;
        this.namespace = namespace;
        this.type = type;
        this.value = value;
        this.active = active;
    }

    @Attribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Attribute("namespace")
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Attribute("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Text
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Attribute("active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Nullable
    @Override
    public XQueryRunVariable getState() {
        return this;
    }

    @Override
    public void loadState(XQueryRunVariable state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XQueryRunVariable variable = (XQueryRunVariable) o;

        if (isActive() != variable.isActive()) return false;
        if (name != null ? !name.equals(variable.name) : variable.name != null) return false;
        if (namespace != null ? !namespace.equals(variable.namespace) : variable.namespace != null) return false;
        if (type != null ? !type.equals(variable.type) : variable.type != null) return false;
        if (value != null ? !value.equals(variable.value) : variable.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (namespace != null ? namespace.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
