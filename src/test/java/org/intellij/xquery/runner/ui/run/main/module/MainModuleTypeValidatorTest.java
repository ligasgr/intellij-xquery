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

package org.intellij.xquery.runner.ui.run.main.module;

import com.intellij.openapi.vfs.VirtualFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class MainModuleTypeValidatorTest {

    private VirtualFile file;
    private ModuleTypeValidator validator;

    @Before
    public void setUp() throws Exception {
        file = mock(VirtualFile.class);
        validator = new MainModuleTypeValidator();
    }

    @Test
    public void shouldReturnFalseWhenContentContainsModuleDeclaration() throws IOException {
        String content = "module namespace xxx = 'xxx';\n x";
        given(file.contentsToByteArray()).willReturn(content.getBytes());

        boolean result = validator.isValidModuleType(file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnFalseWhenContentContainsModuleDeclarationInMultilineFile() throws IOException {
        String content = "xquery version '1.0';\nmodule namespace x = 'a';\nx";
        given(file.contentsToByteArray()).willReturn(content.getBytes());

        boolean result = validator.isValidModuleType(file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnFalseWhenContentContainsModuleDeclarationAndImportOfModule() throws IOException {
        String content = "xquery version '1.0';\nmodule namespace x = 'a';\nimport module namespace z = 'z';";
        given(file.contentsToByteArray()).willReturn(content.getBytes());

        boolean result = validator.isValidModuleType(file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnFalseWhenExceptionThrownFromFile() throws IOException {
        given(file.contentsToByteArray()).willThrow(new IOException());

        boolean result = validator.isValidModuleType(file);

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnTrueWhenContentDoesNotContainModuleDeclaration() throws IOException {
        String content = "xxx";
        given(file.contentsToByteArray()).willReturn(content.getBytes());

        boolean result = validator.isValidModuleType(file);

        assertThat(result, is(true));
    }

    @Test
    public void shouldReturnTrueWhenFileContainsModuleImport() throws IOException {
        String content = "xquery version '1.0';\nimport module namespace x = 'a';\nx";
        given(file.contentsToByteArray()).willReturn(content.getBytes());

        boolean result = validator.isValidModuleType(file);

        assertThat(result, is(true));
    }
}
