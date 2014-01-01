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

package org.intellij.xquery.runner;

import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 12/11/13
 * Time: 14:33
 */
public class UniqueNameGeneratorTest {

    private static final String BASE = "base";

    @Test
    public void shouldBeAbleToCreateObject() {
        UniqueNameGenerator generator = new UniqueNameGenerator();

        assertThat(generator, is(notNullValue()));
    }

    @Test
    public void shouldReturnBaseNameWhenTakenNamesListIsEmpty() {
        String result = UniqueNameGenerator.generateUniqueNameUsingBaseName(BASE, Collections.<String>emptyList());

        assertThat(result, is(BASE));
    }

    @Test
    public void shouldReturnBaseNameWhenTakenNamesListDoesNotContainIt() {
        String result = UniqueNameGenerator.generateUniqueNameUsingBaseName(BASE, asList("sth"));

        assertThat(result, is(BASE));
    }

    @Test
    public void shouldReturnBaseNameWithFirstNumberSuffixWhenBaseNameTaken() {
        String result = UniqueNameGenerator.generateUniqueNameUsingBaseName(BASE, asList(BASE));

        assertThat(result, is(BASE + "1"));
    }

    @Test
    public void shouldReturnBaseNameWithNextNumberSuffixWhenBaseNameAndBaseNameWithFirstTaken() {
        String result = UniqueNameGenerator.generateUniqueNameUsingBaseName(BASE, asList(BASE, BASE + "1"));

        assertThat(result, is(BASE + "2"));
    }
}
