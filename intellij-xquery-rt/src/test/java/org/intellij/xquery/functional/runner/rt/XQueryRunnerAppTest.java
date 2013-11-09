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

package org.intellij.xquery.functional.runner.rt;

import org.intellij.xquery.runner.rt.XQueryRunConfig;
import org.intellij.xquery.runner.rt.XQueryRunnerApp;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.unit.runner.rt.FileTestUtil.createFileWithContents;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 23:24
 */
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
        return "<run>\n" +
                "<xQueryConfiguration " +
                "mainFileName=\"" + xqueryMainFile.getAbsolutePath() + "\" " +
                "contextItemEnabled=\"false\">" +
                "</xQueryConfiguration>\n" +
                "<data-source-configuration " +
                "type=\"SAXON\" " +
                "/>\n" +
                "</run>\n";
    }

    private class StringOutputStream extends OutputStream {
        StringBuilder buffer = new StringBuilder();

        @Override
        public void write(int b) throws IOException {
            buffer.append((char) b);
        }

        public String getString() {
            return buffer.toString();
        }
    }
}
