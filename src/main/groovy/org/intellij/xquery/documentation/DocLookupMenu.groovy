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

package org.intellij.xquery.documentation

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.ex.ComboBoxAction
import com.intellij.openapi.ui.popup.PopupChooserBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.ui.ListSpeedSearch
import org.jetbrains.annotations.NotNull

import javax.swing.JComponent
import javax.swing.JList

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 4/12/17
 * Time: 12:38 AM
 */
class DocLookupMenu extends ComboBoxAction
{
	static final String cKey = ('Mac OS X'.equals (System.getProperty ('os.name'))) ? '\u2318' : 'Ctl'

	@Override
	protected DefaultActionGroup createPopupActionGroup (JComponent jComponent)
	{
		new DefaultActionGroup()
	}

	@Override
	void actionPerformed (AnActionEvent anActionEvent)
	{
		JList<MarkLogicFunctionDefs.Function> list = new JList<>()

		list.setListData (MarkLogicFunctionDefs.instance().getFunctions().toArray())

		//noinspection GroovyResultOfObjectAllocationIgnored
		new ListSpeedSearch<MarkLogicFunctionDefs.Function> (list) {
			@Override
			protected boolean isMatchingElement (Object function, String pattern)
			{
				return ((MarkLogicFunctionDefs.Function)function).fullName.contains (pattern)
			}

			@Override
			Iterable<TextRange> matchingFragments (@NotNull String text)
			{
				return super.matchingFragments (text)
			}
		}

		new PopupChooserBuilder (list).setTitle ('Start typing the name of a MarkLogic function')
			.setMovable (true)
			.setItemChoosenCallback (new Runnable() {
				void run()
				{
					if (list.getSelectedIndices().length > 0) {
						String name = list.getSelectedValue().fullName
						MarkLogicFunctionDefs.Function function = MarkLogicFunctionDefs.instance().getFunction (name)

						new RunDocDialog (anActionEvent.project, function.docAsHtml()).show()
					}
				}
			})
			.setAdText ("Press ${cKey}+up/down arrow to jump to next match")
			.createPopup().showInBestPositionFor (anActionEvent.dataContext)
	}
}
