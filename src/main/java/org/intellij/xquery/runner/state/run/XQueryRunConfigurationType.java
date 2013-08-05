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
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.xquery.icons.XQueryIcons;

/**
 * User: ligasgr
 * Date: 04/08/13
 * Time: 14:40
 */
public class XQueryRunConfigurationType extends ConfigurationTypeBase {

    private static final String XQUERY_MAIN_MODULE = "XQuery Main Module";

    public static XQueryRunConfigurationType getInstance() {
        return ContainerUtil.findInstance(Extensions.getExtensions(CONFIGURATION_TYPE_EP),
                XQueryRunConfigurationType.class);
    }

    public XQueryRunConfigurationType() {
        super("XQueryRunConfiguration", XQUERY_MAIN_MODULE, XQUERY_MAIN_MODULE, XQueryIcons.FILE);
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{new XQueryRunConfigurationFactory(XQUERY_MAIN_MODULE, getInstance())};
    }
}
