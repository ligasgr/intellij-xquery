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

package org.intellij.xquery.runner.ui.datasources;

import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import org.fest.swing.driver.BasicJListCellReader;
import org.fest.swing.fixture.JListFixture;
import org.intellij.xquery.BaseGuiTest;
import org.intellij.xquery.PanelTestingFrame;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.junit.Test;

import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.intellij.xquery.runner.ui.datasources.DataSourceTypesListPopup.TITLE;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: ligasgr
 * Date: 02/11/13
 * Time: 12:51
 */
public class DataSourceTypesListPopupGuiTest extends BaseGuiTest {

    private JPanel mainPanel;
    private DataSourceTypesListPopup popup;
    private XQueryDataSourceTypeBasedActionExecutor executor;

    @Override
    protected PanelTestingFrame getPanelTestingFrame() {
        executor = mock(XQueryDataSourceTypeBasedActionExecutor.class);
        popup = new DataSourceTypesListPopup(executor);
        final ListPopup listPopup = JBPopupFactory.getInstance().createListPopup(popup);

        mainPanel = (JPanel) listPopup.getContent();
        listPopup.show((Component) null);
        return new PanelTestingFrame(mainPanel);
    }

    @Test
    public void shouldShowCorrectLabel() throws InterruptedException {
        window.label().requireText(TITLE);
    }

    @Test
    public void shouldShowAllOptions() {
        JListFixture list = window.list();
        list.cellReader(new DataSourceTypeCellReader());

        List<String> options = asList(list.contents());

        assertThat(options, is(equalTo(getPresentableDataSourceTypeNames())));
    }

    @Test
    public void shouldCallExecutorAfterSelection() {
        XQueryDataSourceType type = XQueryDataSourceType.SAXON;

        popup.onChosen(type, true);

        verify(executor).execute(type);
    }

    private List<String> getPresentableDataSourceTypeNames() {
        List<String> typesPresentableNames = new ArrayList<String>();
        for (XQueryDataSourceType xQueryDataSourceType : XQueryDataSourceType.values()) {
            typesPresentableNames.add(xQueryDataSourceType.getPresentableName());
        }
        return typesPresentableNames;
    }

    private class DataSourceTypeCellReader extends BasicJListCellReader {
        @Override
        public String valueAt(JList list, int index) {
            return popup.getTextFor((XQueryDataSourceType) list.getModel().getElementAt(index));
        }
    }
}
