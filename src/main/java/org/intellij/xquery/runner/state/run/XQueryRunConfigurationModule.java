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

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;

/**
 * User: ligasgr
 * Date: 18/09/13
 * Time: 16:22
 */
public class XQueryRunConfigurationModule extends RunConfigurationModule {
    public XQueryRunConfigurationModule(Project project) {
        super(project);
    }

    public GlobalSearchScope getSearchScope() {
        final Module module = getModule();
        if (module != null) {
            return GlobalSearchScope.moduleWithDependenciesScope(module);
        }
        return GlobalSearchScope.projectScope(getProject());
    }
}
