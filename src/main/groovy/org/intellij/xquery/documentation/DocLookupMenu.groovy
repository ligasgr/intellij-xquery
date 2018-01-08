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

import com.intellij.codeInsight.completion.actions.CodeCompletionGroup
import com.intellij.ide.actions.SmartPopupActionGroup
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.DefaultCompactActionGroup
import com.intellij.openapi.actionSystem.ex.ComboBoxAction
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.ui.popup.JBPopupListener
import com.intellij.openapi.ui.popup.LightweightWindowEvent
import com.intellij.openapi.ui.popup.ListPopup
import com.intellij.openapi.ui.popup.ListPopupStep
import com.intellij.openapi.ui.popup.PopupChooserBuilder
import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep
import com.intellij.openapi.util.TextRange
import com.intellij.ui.ListSpeedSearch
import com.intellij.ui.speedSearch.ListWithFilter
import com.intellij.util.ArrayUtil
import com.intellij.util.Function
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

import javax.swing.Icon
import javax.swing.JComponent
import javax.swing.JList
import javax.swing.ListModel
import java.awt.Dimension
import java.beans.PropertyChangeEvent

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 4/12/17
 * Time: 12:38 AM
 */
class DocLookupMenu extends ComboBoxAction
{
	@Override
	protected DefaultActionGroup createPopupActionGroup (JComponent jComponent)
	{
		DefaultActionGroup dag = new DefaultActionGroup()
	}

	@Override
	void actionPerformed (AnActionEvent anActionEvent)
	{
		println "PluginHelpAction: ${anActionEvent.presentation.description}"

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
				return super.matchingFragments (text)    // FIXME: auto-generated
			}
		}

//		JBPopupFactory.getInstance().createListPopupBuilder (list)
//			.setTitle ("Start typing function name, RTN to select, ESC to close")
//			.setResizable (true)
//			.setMinSize (new Dimension (500, 600))
//			.setMovable (true)
//			.setItemChoosenCallback (new Runnable() {
//				 void run() {
//					if (list.getSelectedIndices().length > 0) {
//						println list.getSelectedValue().fullName
//					} else {
//						println 'nothing selected'
//					}
//			}
//		}).createPopup().showInFocusCenter()


		new PopupChooserBuilder (list).setTitle ('Start typing function name')
			.setMovable (true)
			.setItemChoosenCallback (new Runnable() {
				void run()
				{
					if (list.getSelectedIndices ().length > 0) {
						println list.getSelectedValue ().fullName
					} else {
						println 'nothing selected'
					}
				}
			})
			.setAdText ('Press CMD+up/down arrow to jump to next match')
			.createPopup().showInBestPositionFor (anActionEvent.dataContext)



//		ComboBox<String> combo = new ComboBox<> (['Line 1', 'Line 2', 'Line 3'])
//
//		combo.popupVisible = true
//		combo.visible = true


//		ListPopupStep step = new MyPopupStep<String> ('Enter Function Name', ['Line 1', 'Line 2', 'Line 3'])
//		ListPopup popup = JBPopupFactory.getInstance().createListPopup (step)
//
//
//		popup.showInBestPositionFor (anActionEvent.dataContext)



//		PopupChooserBuilder builder = JBPopupFactory.getInstance().createListPopupBuilder (list)
//
//		builder.title = 'Enter Function Name'
//		builder.addListener (new JBPopupListener() {
//			@Override
//			void beforeShown (LightweightWindowEvent lightweightWindowEvent)
//			{
//				println "beforeShown"
//			}
//
//			@Override
//			void onClosed (LightweightWindowEvent lightweightWindowEvent)
//			{
//				println "onClosed"
//
//				lightweightWindowEvent.
//		})
//
//
//
//		builder.createPopup().showCenteredInCurrentWindow (anActionEvent.project)




//		ListSpeedSearch listUi = new ListSpeedSearch (list)
//
//		listUi.addChangeListener { evt ->
//			println "Property name ${evt.propertyName}"
//		}
//		listUi.component.enable()
//		listUi.component.show()
	}

	private static class MyPopupStep<T> extends BaseListPopupStep<T>
	{
		@SafeVarargs
		MyPopupStep(@Nullable String title, @NotNull List<T> values) {
			super (title, values)
		}

		@Override
		PopupStep onChosen (T selectedValue, boolean finalChoice)
		{
			println "ONCHOSEN: ${selectedValue}"

			null
		}
	}

	private static class MyActionGroup extends ActionGroup
	{
		private final AnAction [] children

		MyActionGroup (String text, String description, Icon icon, AnAction[] children)
		{
			super (text, description, icon)
			this.children = children
		}

		@Override
		AnAction[] getChildren (@Nullable AnActionEvent anActionEvent)
		{
			return children
		}
	}
}
