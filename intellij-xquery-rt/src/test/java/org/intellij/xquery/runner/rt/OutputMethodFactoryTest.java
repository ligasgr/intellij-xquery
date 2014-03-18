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

import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.rt.OutputMethodFactory.METHOD_PROPERTY_NAME;
import static org.intellij.xquery.runner.rt.OutputMethodFactory.OUTPUT_TYPE_XML;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 15:02
 */
public class OutputMethodFactoryTest {

    @Test
    public void shouldAlwaysReturnOutputMethodXml() {
        OutputMethodFactory outputMethodFactory = new OutputMethodFactory(mock(XQueryRunConfig.class));

        Properties result  = outputMethodFactory.getOutputMethodProperties();

        assertThat(result.keySet().size(), is(1));
        assertThat(result.getProperty(METHOD_PROPERTY_NAME), is(OUTPUT_TYPE_XML));
    }


}
