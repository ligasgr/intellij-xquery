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

package org.intellij.xquery.runner.rt;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.FileTestUtil.createFileWithContents;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 15:08
 */
public class FileUtilTest {

    private static final String EXAMPLE_CONTENTS = "my contents" + System.getProperty("line.separator") + "with lines.";

    @Test
    public void shouldReturnFullContentsOfFile() throws IOException {
        File fileToRead = createFileWithContents(EXAMPLE_CONTENTS + System.getProperty("line.separator"));

        String result = FileUtil.readFile(fileToRead.getAbsolutePath());

        assertThat(result, is(EXAMPLE_CONTENTS + System.getProperty("line.separator")));
    }

    @Test
    public void shouldReturnFullContentsOfFileWithNewLineAppendedWhenWasWithoutLastNewLine()
            throws IOException {
        File fileToRead = createFileWithContents(EXAMPLE_CONTENTS);

        String result = FileUtil.readFile(fileToRead.getAbsolutePath());

        assertThat(result, is(EXAMPLE_CONTENTS + System.getProperty("line.separator")));
    }
}
