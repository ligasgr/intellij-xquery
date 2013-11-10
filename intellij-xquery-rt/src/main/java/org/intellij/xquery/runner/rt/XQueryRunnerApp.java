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

package org.intellij.xquery.runner.rt;

import java.io.PrintStream;

/**
 * User: ligasgr
 * Date: 15/08/13
 * Time: 13:56
 */
public class XQueryRunnerApp {
  public static void main(String[] args) throws Exception {
    runConfigForOutputStream(new XQueryRunConfig(FileUtil.readFile(args[0])), System.out);
  }

  public static void runConfigForOutputStream(XQueryRunConfig config, PrintStream output) throws Exception {
    XQueryGenericRunner app;

    if (config.getDataSourceType() == XQueryDataSourceType.BASEX_EMBEDDED) {
      app = new XQueryBaseXRunner(config);
    } else {
      app = new XQueryXQJRunner(config);
    }

    app.run(output);
  }
}