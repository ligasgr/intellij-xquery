/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.documentation;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.intellij.xquery.runner.ui.run.main.HelpForm;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 5/11/17
 * Time: 10:40 PM
 */
class RunDocDialog extends DialogWrapper
{
	private static final String TITLE = "MarkLogic Documentation"
	private final String html

	RunDocDialog (Project project, String html)
	{
		super (project, false)

		this.html = html

		initDialog()
	}

	private void initDialog()
	{
		setOKActionEnabled (true)
		setOKButtonText ("Dismiss")
		setTitle (TITLE)

		init()

		getButton (getCancelAction()).setVisible (false)
	}

	@Nullable
	@Override
	protected JComponent createCenterPanel()
	{
		HelpForm form = new HelpForm()

		form.setText (html)
		form.setCss (resourceFileText (getClass().getPackage().getName(), "doc-dialog.css"))

		form.setLabel (TITLE)

		if (form.getMainPanel() == null) {
			new JPanel()
		} else {
			form.getMainPanel()
		}

	}

	private static String SLASH = File.separator

	static String resourceFileText (String packageName, String filename)
	{
		RunDocDialog.classLoader.getResource ("${packageName.replace ('.', SLASH)}${SLASH}${filename}").text
	}

}
