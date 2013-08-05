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

package org.intellij.xquery.runner.state.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * User: ligasgr
 * Date: 04/08/13
 * Time: 14:48
 */
public class XQueryRunConfigurationFactory extends ConfigurationFactory {

    private final String name;

    public XQueryRunConfigurationFactory(String name, @NotNull ConfigurationType type) {
        super(type);
        this.name = name;
    }

    @Override
    public RunConfiguration createTemplateConfiguration(Project project) {
        return new XQueryRunConfiguration(name, new XQueryModuleBasedConfiguration(project), this);
    }
}
