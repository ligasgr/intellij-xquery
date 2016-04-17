/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.settings;

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.SortedComboBoxModel;

import javax.swing.*;
import java.util.Comparator;

public class UIUtils {
    static SortedComboBoxModel<String> comboBoxModel() {
        return new SortedComboBoxModel<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
    }

    static <T> LabeledComponent<JComboBox<T>> comboBox(String text, String name, ComboBoxModel<T> model) {
        LabeledComponent<JComboBox<T>> comboBox = new LabeledComponent<>();
        comboBox.setText(text);
        comboBox.setLabelLocation("West");
        comboBox.setComponent(new JComboBox<>());
        comboBox.getComponent().setName(name);
        comboBox.getComponent().setModel(model);
        return comboBox;
    }

    static void restartDaemons() {
        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        for (Project project : projects) {
            DaemonCodeAnalyzer.getInstance(project).restart();
        }
    }
}
