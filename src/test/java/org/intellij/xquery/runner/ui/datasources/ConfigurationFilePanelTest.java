package org.intellij.xquery.runner.ui.datasources;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.ui.PanelTestingFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: ligasgr
 * Date: 24/10/13
 * Time: 13:42
 */
public class ConfigurationFilePanelTest {
    private FrameFixture window;
    private ConfigurationFilePanel configurationFilePanel;

    @Before
    public void setUp() throws Exception {
        PanelTestingFrame frame = GuiActionRunner.execute(new GuiQuery<PanelTestingFrame>() {
            protected PanelTestingFrame executeInEDT() {
                configurationFilePanel = new ConfigurationFilePanel();
                return new PanelTestingFrame(configurationFilePanel.getMainPanel());
            }
        });
        window = new FrameFixture(frame);
        window.show();
    }

    @Test
    public void shouldShowPanelWhenNeeded() throws Exception {
        configurationFilePanel.init(XQueryDataSourceType.SAXON, false, null);

        assertThat(true, is(configurationFilePanel.getMainPanel().isVisible()));
    }

    @Test
    public void shouldHidePanelWhenNeeded() throws Exception {
        configurationFilePanel.init(XQueryDataSourceType.MARKLOGIC, false, null);

        assertThat(false, is(configurationFilePanel.getMainPanel().isVisible()));
    }

    @After
    public void tearDown() throws Exception {
        window.cleanUp();
    }
}
