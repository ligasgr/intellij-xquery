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

package org.intellij.xquery.runner.rt;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.FileTestUtil.createFileWithContents;
import static org.intellij.xquery.runner.rt.XQueryDataSourceType.SAXON;
import static org.intellij.xquery.runner.rt.XQueryRunConfigBuilder.runConfig;
import static org.junit.Assert.assertThat;

public class XQueryRunnerAppTest {
    private static final String HELLO_WORLD = "Hello world!";
    private static final String XQUERY_LITERAL = "'" + HELLO_WORLD + "'";
    private StringOutputStream outputStream;
    private PrintStream printStream;

    @Before
    public void setUp() throws Exception {
        outputStream = new StringOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    public void shouldProduceHelloWorld() throws Exception {
        File xqueryMainFile = createFileWithContents(XQUERY_LITERAL);
        XQueryRunConfig config = new XQueryRunConfig(prepareConfigurationForMainFile(xqueryMainFile));

        XQueryRunnerApp.runConfigForOutputStream(config, printStream);

        assertThat(outputStream.getString(), is(HELLO_WORLD));
    }

    private String prepareConfigurationForMainFile(File xqueryMainFile) {
        return runConfig()
                .withTypeName(SAXON.toString())
                .withMainFileName(xqueryMainFile.getAbsolutePath())
                .build();
    }
}
