/*
 * Copyright 2017 OverStory Ltd <copyright@overstory.co.uk> and other contributors
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

package org.intellij.xquery.runner.ui.run.main;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.intellij.xquery.runner.rt.debugger.marklogic.MarkLogicDebugConnector;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 5/11/17
 * Time: 10:40 PM
 */
public class RunConfigHelpDialog extends DialogWrapper
{
	private static final String TITLE = "MarkLogic Run Config Help";

	public RunConfigHelpDialog (Component parent)
	{
		super (parent, false);

		initDialog();
	}

	public RunConfigHelpDialog (Project project)
	{
		super (project);

		initDialog();
	}

	private void initDialog()
	{
		setOKActionEnabled (true);
		setOKButtonText ("Dismiss");
		setTitle (TITLE);

		init();

		getButton (getCancelAction()).setVisible (false);
	}

	@Nullable
	@Override
	protected JComponent createCenterPanel()
	{
		HelpForm form = new HelpForm();

		form.setText (MarkLogicDebugConnector.resourceFileText (getClass().getPackage().getName(), "run-config-help.html"));
		form.setCss (MarkLogicDebugConnector.resourceFileText (getClass().getPackage().getName(), "run-config-help.css"));

		form.setLabel (TITLE);

		return form.getMainPanel();
	}
}
