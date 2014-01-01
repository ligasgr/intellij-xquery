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

package org.intellij.xquery.runner.ui.run.main.module;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.PanelWithAnchor;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * User: ligasgr
 * Date: 20/11/13
 * Time: 15:45
 */
public class ModuleSelectionPanel extends JPanel implements PanelWithAnchor {
    public static final String MODULE_SELECTION_PANEL = "moduleSelectionPanel";
    private LabeledComponent<ModuleSelector> mainFile;
    private JComponent anchor;

    public ModuleSelectionPanel(Project project) {
        super(new BorderLayout());
        setName(MODULE_SELECTION_PANEL);
        mainFile = new LabeledComponent<ModuleSelector>();
        mainFile.setText("Main &file");
        mainFile.setLabelLocation("West");
        mainFile.setComponent(getModuleSelector(project));
        add(mainFile);
    }

    private ModuleSelector getMainFileField() {
        return mainFile.getComponent();
    }

    public void init(XQueryRunConfiguration configuration) {
        getMainFileField().setText(configuration.getMainFileName());
    }

    public void applyChanges(XQueryRunConfiguration configuration) {
        configuration.setMainFileName(getMainFileField().getText());
    }

    public void setAnchor(JComponent anchor) {
        this.anchor = anchor;
    }

    public JComponent getAnchor() {
        return anchor;
    }

    protected ModuleSelector getModuleSelector(Project project) {
        return new ModuleSelector(project);
    }
}
