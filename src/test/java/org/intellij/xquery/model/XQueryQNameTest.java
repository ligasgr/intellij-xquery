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

package org.intellij.xquery.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * User: ligasgr
 * Date: 09/08/13
 * Time: 13:08
 */
public class XQueryQNameTest {

    private final XQueryQName qName = new XQueryQName<Object>("prefix", "localName", "namespace", new Object());

    @Test
    public void shouldNotEqualWhenObjectIsNull() throws Exception {
        assertFalse(qName.equals(null));
    }

    @Test
    public void shouldNotEqualWhenObjectIsOfDifferentClass() throws Exception {
        assertFalse(qName.equals(new Object()));
    }

    @Test
    public void shouldEqualWhenPrefixesAndLocalNamesEqual() throws Exception {
        XQueryQName qName1 = new XQueryQName<Object>("prefix", "localName", "namespace1", new Object());
        XQueryQName qName2 = new XQueryQName<Object>("prefix", "localName", "namespace2", new Object());

        assertEquals(qName1, qName2);
    }

    @Test
    public void shouldNotEqualWhenPrefixesEqualAndLocalNamesDoNot() throws Exception {
        XQueryQName qName1 = new XQueryQName<Object>("prefix", "localName1", "namespace1", new Object());
        XQueryQName qName2 = new XQueryQName<Object>("prefix", "localName2", "namespace2", new Object());

        assertFalse(qName1.equals(qName2));
    }

    @Test
    public void shouldNotEqualWhenPrefixesDoNotEqualAndLocalNamesEqual() throws Exception {
        XQueryQName qName1 = new XQueryQName<Object>("prefix1", "localName", "namespace1", new Object());
        XQueryQName qName2 = new XQueryQName<Object>("prefix2", "localName", "namespace2", new Object());

        assertFalse(qName1.equals(qName2));
    }

    @Test
    public void shouldNotEqualWhenPrefixesDoNotEqualAndLocalNamesDoNotEqual() throws Exception {
        XQueryQName qName1 = new XQueryQName<Object>("prefix1", "localName", "namespace1", new Object());
        XQueryQName qName2 = new XQueryQName<Object>("prefix2", "localName", "namespace2", new Object());

        assertFalse(qName1.equals(qName2));
    }

    @Test
    public void shouldEqualWhenPrefixEmptyAndLocalNamesEqual() throws Exception {
        XQueryQName qName1 = new XQueryQName<Object>(null, "localName", "namespace1", new Object());
        XQueryQName qName2 = new XQueryQName<Object>(null, "localName", "namespace2", new Object());

        assertEquals(qName1, qName2);
    }

    @Test
    public void shouldNotEqualWhenPrefixEmptyAndLocalNamesDoNotEqual() throws Exception {
        XQueryQName qName1 = new XQueryQName<Object>(null, "localName1", "namespace1", new Object());
        XQueryQName qName2 = new XQueryQName<Object>(null, "localName2", "namespace2", new Object());

        assertFalse(qName1.equals(qName2));
    }

    @Test
    public void shouldEqualWhenNamespaceAndLocalNamesEqual() throws Exception {
        XQueryQName qName1 = new XQueryQName<Object>("prefix1", "localName", "namespace", new Object());
        XQueryQName qName2 = new XQueryQName<Object>("prefix1", "localName", "namespace", new Object());

        assertEquals(qName1, qName2);
    }

    @Test
    public void shouldNotEqualWhenNamespaceAndLocalNamesDoNotEqual() throws Exception {
        XQueryQName qName1 = new XQueryQName<Object>("prefix1", "localName1", "namespace1", new Object());
        XQueryQName qName2 = new XQueryQName<Object>("prefix2", "localName2", "namespace2", new Object());

        assertFalse(qName1.equals(qName2));
    }

    @Test
    public void shouldNotEqualWhenPrefixEmptyAndLocalNamesEmpty() throws Exception {
        XQueryQName qName1 = new XQueryQName<Object>(null, null, "namespace1", new Object());
        XQueryQName qName2 = new XQueryQName<Object>(null, null, "namespace2", new Object());

        assertFalse(qName1.equals(qName2));
    }

    @Test
    public void shouldReturnTheSameObject() {
        Object o = new Object();
        final XQueryQName qName = new XQueryQName<Object>("prefix", "localName", "namespace", o);

        assertEquals(o, qName.getNamedObject());
    }

    @Test
    public void shouldReturnTheSameNamespace() {
        assertEquals("namespace", qName.getNamespace());
    }

    @Test
    public void shouldReturnTextRepresentationForFullyQualifiedName() {
        assertEquals("prefix:localName", qName.getTextRepresentation());
    }

    @Test
    public void shouldReturnTextRepresentationForNameWithoutPrefix() {
        XQueryQName qName = new XQueryQName<Object>(null, "localName", null, new Object());
        assertEquals("localName", qName.getTextRepresentation());
    }
}
