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

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 5/11/17
 * Time: 11:46 PM
 */
public class HelpForm
{
	private JEditorPane body;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private StyleSheet styleSheet;

	public HelpForm()
	{
		mainPanel.setName ("helpPanel");
		body.setName ("body");

		HTMLEditorKit kit = new HTMLEditorKit();
		Document doc = kit.createDefaultDocument();
		styleSheet = kit.getStyleSheet();

		body.setEditorKit (kit);
		body.setDocument (doc);
	}

	public JPanel getMainPanel()
	{
		return mainPanel;
	}

	public void setText (String text)
	{
		body.setText (text);
		body.setCaretPosition (0);
	}

	public void setCss (String css)
	{
		styleSheet.addRule (css);
	}

	public void setLabel (String text)
	{
		((TitledBorder)(scrollPane.getBorder())).setTitle (text);
	}
}
