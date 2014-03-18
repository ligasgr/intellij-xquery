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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.FileTestUtil.createFileWithContents;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 23:08
 */
public class XQueryContentFactoryTest {

    @Test
    public void shouldReturnFileInputStreamForGivenFile() throws IOException {
        String contents = "contents";
        File file = createFileWithContents(contents);
        XQueryRunConfig config = mock(XQueryRunConfig.class);
        given(config.getMainFile()).willReturn(file.getAbsolutePath());
        XQueryContentFactory factory = new XQueryContentFactory(config);

        InputStream result = factory.getXQueryContentAsStream();

        assertThat(getString(result), is(contents));
    }

    private String getString(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        return out.toString();
    }
}
