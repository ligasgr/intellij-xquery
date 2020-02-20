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
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.ui.components.JBRadioButton;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.rt.debugger.marklogic.MarkLogicRunMode;
import org.intellij.xquery.runner.state.run.XQueryRunConfiguration;
import org.jetbrains.annotations.Nullable;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.*;
import static org.intellij.xquery.runner.rt.debugger.marklogic.MarkLogicRunMode.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 5/5/17
 * Time: 2:54 PM
 */
public class MarkLogicRunModeConfigPanel implements PanelWithAnchor
{
	private final ButtonGroup buttonGroup = new ButtonGroup ();
	private final ActionListener radioButtonListener = new RadioButtonListener();

	private MarkLogicRunMode runMode = ADHOC;
	private JComponent anchor;
	private Project project;

	private JPanel mainPanel;
	private JBRadioButton adHocEval;
	private JBRadioButton invokeModule;
	private JBRadioButton grabAppserver;
	private JBRadioButton grabRunning;
	private TextFieldWithBrowseButton appserverRoot;
	private JLabel label;
	private JLabel appserverRootLabel;
	private JLabel debugAppserverLabel;
	private JPanel appserverRootPanel;
	private JPanel debugAppserverPanel;
	private LabeledComponent<JTextField> mlDebugAppserver;
	private JButton helpButton;
	private LabeledComponent<JTextField> mlCaptureTimeoutSecs;

	public MarkLogicRunModeConfigPanel()
	{
		adHocEval.setEnabled (true);
		adHocEval.addActionListener (radioButtonListener);

		invokeModule.setEnabled (true);
		invokeModule.addActionListener (radioButtonListener);

		grabAppserver.setEnabled (true);
		grabAppserver.addActionListener (radioButtonListener);

		// ToDo
		grabRunning.setEnabled (false);
		grabRunning.addActionListener (radioButtonListener);

		appserverRootPanel.setVisible (false);
		debugAppserverPanel.setVisible (false);

		buttonGroup.add (adHocEval);
		buttonGroup.add (invokeModule);
		buttonGroup.add (grabAppserver);
		buttonGroup.add (grabRunning);

		helpButton.addActionListener (getHelpButtonListener());
		helpButton.putClientProperty("JButton.buttonType", "help");
		helpButton.setText("");

		mainPanel.setVisible (false);
	}

	public void init (XQueryRunConfiguration cfg)
	{
		applyMode (cfg.getMlDebuggerRunMode());
		appserverRoot.setText (cfg.getMlDebuggerAppserverRoot());
		mlDebugAppserver.getComponent().setText (cfg.getMlDebugAppserver());
		mlCaptureTimeoutSecs.getComponent().setText (cfg.getMlCaptureTimeoutSecs());

		XQueryDataSourceType dsType = cfg.getDataSourceType();

		mainPanel.setVisible ((dsType != null) && dsType.isMarklogicDebugSupported ());

		project = cfg.getProject();

		appserverRoot.addBrowseFolderListener("Choose Appserver Root Directory",
			"Local directory that corresponds to MarkLogic's appserver root", project, createSingleFolderDescriptor());

		appserverRoot.getTextField().setName ("appserverRoot");
	}

	public void applyChanges(XQueryRunConfiguration cfg)
	{
		cfg.setMlDebuggerRunMode (runMode);
		cfg.setMlDebuggerAppserverRoot (appserverRoot.getText());
		cfg.setMlDebugAppserver (mlDebugAppserver.getComponent().getText());
		cfg.setMlCaptureTimeoutSecs (mlCaptureTimeoutSecs.getComponent().getText());
	}

	public JPanel getMainPanel()
	{
		return mainPanel;
	}

	// ---------------------------------------------------

	@Override
	public JComponent getAnchor()
	{
		return anchor;
	}

	@Override
	public void setAnchor (@Nullable JComponent anchor)
	{
		this.anchor = anchor;
	}

	// ---------------------------------------------------

	private ActionListener getHelpButtonListener()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed (ActionEvent e)
			{
				new RunConfigHelpDialog (getMainPanel()).show();
			}
		};
	}

	// ---------------------------------------------------

	private void applyMode (MarkLogicRunMode mode)
	{
		runMode = mode;

		if (mode == ADHOC) {
			buttonGroup.setSelected (adHocEval.getModel(), true);
			appserverRootPanel.setVisible (false);
			debugAppserverPanel.setVisible (false);
		}

		if (mode == INVOKE) {
			buttonGroup.setSelected (invokeModule.getModel(), true);
			appserverRootPanel.setVisible (true);
			debugAppserverPanel.setVisible (false);
		}

		if (mode == CAPTURE_APPSERVER) {
			buttonGroup.setSelected (grabAppserver.getModel(), true);
			appserverRootPanel.setVisible (true);
			debugAppserverPanel.setVisible (true);
		}

		if (mode == DEBUG_RUNNING) {
			buttonGroup.setSelected (grabRunning.getModel(), true);
			appserverRootPanel.setVisible (true);
			debugAppserverPanel.setVisible (true);
		}
	}

	private class RadioButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent e)
		{
			Object button = e.getSource();
			MarkLogicRunMode mode;

			if (button == adHocEval) {
				mode = ADHOC;
			} else if (button == invokeModule) {
				mode = INVOKE;
			} else if (button == grabAppserver) {
				mode = CAPTURE_APPSERVER;
			} else if (button == grabRunning) {
				mode = DEBUG_RUNNING;
			} else {
				mode = ADHOC;
				System.err.println ("RadioButtonListener: IMPOSSIBLE BUTTON SOURCE: " + e.getSource());
			}

			applyMode (mode);
		}
	}
}
