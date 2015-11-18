/*
 * Copyright 2013-2015 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.actions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CreateXQueryFileActionTest {

    private CreateXQueryFileAction action = new CreateXQueryFileAction();

    @Test
    public void allowsForNamesWithAllValidCharacters() throws Exception {
        String name = "ABCDEFGHIJKLMNOPQRSTUVWXYZ-abcdefghijklmnopqrstuvwxyz_0123456789";

        boolean isValidName = action.isValidFileName(name);

        assertThat(isValidName, is(true));
    }

    @Test
    public void doesNotAllowNamesThatStartWithNumber() throws Exception {
        String name = "0123456789";

        boolean isValidName = action.isValidFileName(name);

        assertThat(isValidName, is(false));
    }

    @Test
    public void doesAllowNamesThatStartWithUnderscore() throws Exception {
        String name = "_0123456789";

        boolean isValidName = action.isValidFileName(name);

        assertThat(isValidName, is(true));
    }

    @Test
    public void doesNotAllowNamesThatStartWithHyphen() throws Exception {
        String name = "-a";

        boolean isValidName = action.isValidFileName(name);

        assertThat(isValidName, is(false));
    }
}