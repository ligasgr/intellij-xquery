/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.rt.vendor.basex;

import org.basex.api.client.ClientQuery;
import org.basex.api.client.ClientSession;
import org.intellij.xquery.runner.rt.FileUtil;
import org.intellij.xquery.runner.rt.RunnerApp;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerVariable;

import java.io.PrintStream;

import static org.intellij.xquery.runner.rt.FileUtil.readFile;

public class BaseXRunnerApp implements RunnerApp {
    private final XQueryRunConfig config;
    private final PrintStream output;

    public BaseXRunnerApp(XQueryRunConfig config, PrintStream output) {
        this.config = config;
        this.output = output;
    }

    @Override
    public void runApp() throws Exception {
        try (ClientSession session = new ClientSession(config.getHost(), Integer.valueOf(config.getPort()), config.getUsername(), config.getPassword())) {
            try (ClientQuery query = session.query(FileUtil.readFile(config.getMainFile()))) {

                for (XQueryRunnerVariable variable : config.getVariables()) {
                    if (variable.ACTIVE) {
                        query.bind(variable.NAME, variable.VALUE, variable.TYPE);
                    }
                }
                if (config.isContextItemEnabled()) {
                    String contextItemValue = config.isContextItemFromEditorEnabled() ? config.getContextItemText() : readFile(config.getContextItemFile());
                    query.context(contextItemValue, config.getContextItemType());
                }
                output.print(query.execute());
            }
        }
    }
}
