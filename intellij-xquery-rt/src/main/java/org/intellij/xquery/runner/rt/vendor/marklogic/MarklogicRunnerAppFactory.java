/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.rt.vendor.marklogic;

import org.intellij.xquery.runner.rt.RunnerApp;
import org.intellij.xquery.runner.rt.RunnerAppFactory;
import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.debugger.marklogic.MarkLogicDebuggerApp;

import java.io.PrintStream;

public class MarklogicRunnerAppFactory implements RunnerAppFactory
{
    @Override
    public RunnerApp getInstance (XQueryRunConfig config, PrintStream output) throws Exception
    {
        if (config.isDebugEnabled()) {
            return new MarkLogicDebuggerApp (config, output);
        } else {
            return new MarklogicRunnerApp (config, output);
        }
    }
}
