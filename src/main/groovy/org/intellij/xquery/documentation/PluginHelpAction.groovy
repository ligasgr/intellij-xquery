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

package org.intellij.xquery.documentation

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.intellij.xquery.runner.ui.run.main.RunConfigHelpDialog

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 4/12/17
 * Time: 12:38 AM
 */
class PluginHelpAction extends AnAction
{
	PluginHelpAction()
	{
		super (AllIcons.Toolwindows.ToolWindowDebugger)
	}

	@Override
	void actionPerformed (AnActionEvent anActionEvent)
	{
		try {
			new RunConfigHelpDialog (anActionEvent.project).show()
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
}
