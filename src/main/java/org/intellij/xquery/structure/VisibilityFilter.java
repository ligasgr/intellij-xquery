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

import com.intellij.ide.util.treeView.smartTree.ActionPresentation;
import com.intellij.ide.util.treeView.smartTree.ActionPresentationData;
import com.intellij.ide.util.treeView.smartTree.Filter;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NotNull;

public class VisibilityFilter implements Filter {

    @NotNull
    @Override
    public ActionPresentation getPresentation() {
        return new ActionPresentationData("Show non-public", null, PlatformIcons.PRIVATE_ICON);
    }

    @NotNull
    @Override
    public String getName() {
        return "SHOW_NON_PUBLIC_FILTER";
    }

    @Override
    public boolean isVisible(TreeElement element) {
        if (element instanceof XQueryStructureViewElement) {
            return ((XQueryStructureViewElement) element).isPublic();
        } else {
            return true;
        }
    }

    @Override
    public boolean isReverted() {
        return true;
    }

}
