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

package org.intellij.xquery.structure;

import com.intellij.ide.util.treeView.smartTree.Filter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class VisibilityFilterTest {
    Filter filter = new VisibilityFilter();

    @Test
    public void nonPublicElemensShouldBeDisplaydWhenButtonIsPressed()  {
        assertThat(filter.isReverted(), is(true));
    }

    @Test
    public void privateStructureViewElemensIsNotVisible() {
        XQueryStructureViewElement element = mock(XQueryStructureViewElement.class);
        given(element.isPublic()).willReturn(false);

        final boolean visible = filter.isVisible(element);
        assertThat(visible, is(false));
    }

    @Test
    public void publicStructureViewElemensIsVisible() {
        XQueryStructureViewElement element = mock(XQueryStructureViewElement.class);
        given(element.isPublic()).willReturn(true);

        final boolean visible = filter.isVisible(element);
        assertThat(visible, is(true));
    }


}
