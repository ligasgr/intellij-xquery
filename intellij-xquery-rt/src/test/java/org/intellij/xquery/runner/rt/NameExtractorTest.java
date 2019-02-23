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

import org.junit.Test;

import javax.xml.namespace.QName;

import static javax.xml.XMLConstants.DEFAULT_NS_PREFIX;
import static javax.xml.XMLConstants.DEFAULT_NS_PREFIX;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 22:16
 */
public class NameExtractorTest {
    private static final String LOCAL_PART = "local";
    private static final String PREFIX = "prefix";
    private static final String SEPARATOR = ":";
    private static final String NAMESPACE = "http://namespace";
    private NameExtractor extractor = new NameExtractor();

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionForInvalidNameWithNotEnoughDetails() {
        extractor.getName(SEPARATOR, "");
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionForInvalidNameWithTooManyColons() {
        extractor.getName("a:b:c", "");
    }

    @Test
    public void shouldReturnQNameWithLocalPartOnly() {
        QName result = extractor.getName(LOCAL_PART, null);

        assertThat(result.getLocalPart(), is(LOCAL_PART));
        assertThat(result.getNamespaceURI(), is(DEFAULT_NS_PREFIX));
        assertThat(result.getPrefix(), is(DEFAULT_NS_PREFIX));
    }

    @Test
    public void shouldReturnQNameWithLocalPartAndPrefix() {
        QName result = extractor.getName(PREFIX + SEPARATOR + LOCAL_PART, null);

        assertThat(result.getLocalPart(), is(LOCAL_PART));
        assertThat(result.getNamespaceURI(), is(DEFAULT_NS_PREFIX));
        assertThat(result.getPrefix(), is(PREFIX));
    }

    @Test
    public void shouldReturnQNameWithLocalPartAndEmptyNamespace() {
        QName result = extractor.getName(LOCAL_PART, DEFAULT_NS_PREFIX);

        assertThat(result.getLocalPart(), is(LOCAL_PART));
        assertThat(result.getNamespaceURI(), is(DEFAULT_NS_PREFIX));
        assertThat(result.getPrefix(), is(DEFAULT_NS_PREFIX));
    }

    @Test
    public void shouldReturnQNameWithLocalPartAndPrefixAndEmptyNamespace() {
        QName result = extractor.getName(PREFIX + SEPARATOR + LOCAL_PART, DEFAULT_NS_PREFIX);

        assertThat(result.getLocalPart(), is(LOCAL_PART));
        assertThat(result.getNamespaceURI(), is(DEFAULT_NS_PREFIX));
        assertThat(result.getPrefix(), is(PREFIX));
    }

    @Test
    public void shouldReturnQNameWithLocalPartAndNamespace() {
        QName result = extractor.getName(LOCAL_PART, NAMESPACE);

        assertThat(result.getLocalPart(), is(LOCAL_PART));
        assertThat(result.getNamespaceURI(), is(NAMESPACE));
        assertThat(result.getPrefix(), is(DEFAULT_NS_PREFIX));
    }

    @Test
    public void shouldReturnQNameWithLocalPartAndPrefixAndNamespace() {
        QName result = extractor.getName(PREFIX + SEPARATOR + LOCAL_PART, NAMESPACE);

        assertThat(result.getLocalPart(), is(LOCAL_PART));
        assertThat(result.getNamespaceURI(), is(NAMESPACE));
        assertThat(result.getPrefix(), is(PREFIX));
    }
}
