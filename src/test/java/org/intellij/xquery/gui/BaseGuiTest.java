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

package org.intellij.xquery.gui;

import net.java.openjdk.cacio.ctc.junit.CacioFESTRunner;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.intellij.xquery.functional.BaseFunctionalTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * User: ligasgr
 * Date: 26/10/13
 * Time: 17:36
 */
@RunWith(CacioFESTRunner.class)
public abstract class BaseGuiTest extends BaseFunctionalTestCase {
    protected FrameFixture window;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        PanelTestingFrame frame = GuiActionRunner.execute(new GuiQuery<PanelTestingFrame>() {
            protected PanelTestingFrame executeInEDT() {
                return getPanelTestingFrame();
            }
        });
        window = new FrameFixture(frame);
        window.show();
    }

    protected abstract PanelTestingFrame getPanelTestingFrame();

    @Override
    @After
    public void tearDown() throws Exception {
        window.cleanUp();
        super.tearDown();
    }
}
