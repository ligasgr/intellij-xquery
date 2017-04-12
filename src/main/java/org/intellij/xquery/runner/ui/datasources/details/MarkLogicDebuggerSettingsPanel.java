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

package org.intellij.xquery.runner.ui.datasources.details;

import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.components.JBCheckBox;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 4/12/17
 * Time: 1:27 PM
 */
public class MarkLogicDebuggerSettingsPanel
{
	public static final String MLDEBUGGER_ENABLED_FIELD_NAME = "mlDebuggerEnabled";
	public static final String MLDEBUGGER_PORT_FIELD_NAME = "mlDebuggerPort";
	public static final String MLDEBUGGER_USER_FIELD_NAME = "mlDebuggerUser";
	public static final String MLDEBUGGER_PASSWORD_FIELD_NAME = "mlDebuggerPassword";

	private JPanel mainPanel;
	private JBCheckBox mlDebuggerEnabled;
	private LabeledComponent<JTextField> mlDebuggerPort;
	private LabeledComponent<JTextField> mlDebuggerUser;
	private LabeledComponent<JPasswordField> mlDebuggerPassword;

	public MarkLogicDebuggerSettingsPanel()
	{
		mlDebuggerPort.getComponent().setName (MLDEBUGGER_PORT_FIELD_NAME);
		mlDebuggerUser.getComponent().setName (MLDEBUGGER_USER_FIELD_NAME);
		mlDebuggerPassword.getComponent().setName (MLDEBUGGER_PASSWORD_FIELD_NAME);
		mlDebuggerEnabled.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsEnableChanged();
			}
		});
	}

	public void init (XQueryDataSourceConfiguration cfg, DataSourceConfigurationAggregatingPanel aggregatingPanel, ConfigurationChangeListener listener)
	{
		mainPanel.setVisible (cfg.TYPE.isDebugSupported());
		mlDebuggerEnabled.setSelected (cfg.MLDEBUGGER_CONFIG_ENABLED);
		mlDebuggerPort.getComponent().setText (cfg.MLDEBUGGER_CONFIG_PORT);
		mlDebuggerUser.getComponent().setText (cfg.MLDEBUGGER_CONFIG_USER);
		mlDebuggerPassword.getComponent().setText (cfg.MLDEBUGGER_CONFIG_PASSWORD);

		settingsEnableChanged();
		setUpChangeListeners (aggregatingPanel, listener);
	}

	public void updateConfigurationWithChanges (XQueryDataSourceConfiguration currentConfiguration)
	{
		currentConfiguration.MLDEBUGGER_CONFIG_ENABLED = isMlDebuggerEnabled();
		currentConfiguration.MLDEBUGGER_CONFIG_PORT = getPort();
		currentConfiguration.MLDEBUGGER_CONFIG_USER = getUser();
		currentConfiguration.MLDEBUGGER_CONFIG_PASSWORD = getPassword();
	}

	public JPanel getMainPanel()
	{
		return mainPanel;
	}

	public String getPort()
	{
		return mlDebuggerPort.getComponent().getText();
	}

	public String getUser()
	{
		return mlDebuggerUser.getComponent().getText();
	}

	public String getPassword()
	{
		return mlDebuggerPassword.getComponent().getText();
	}

	public boolean isMlDebuggerEnabled() {
		return mlDebuggerEnabled.isSelected();
	}

	private void settingsEnableChanged()
	{
		boolean selected = mlDebuggerEnabled.isSelected();

		mlDebuggerPort.setEnabled (selected);
		mlDebuggerUser.setEnabled (selected);
		mlDebuggerPassword.setEnabled (selected);
	}

	private void setUpChangeListeners (final DataSourceConfigurationAggregatingPanel aggregatingPanel, final ConfigurationChangeListener listener)
	{
		mlDebuggerEnabled.addActionListener (
			new ActionListener() {
				@Override
				public void actionPerformed (ActionEvent e)
				{
					settingsEnableChanged ();
					listener.changeApplied (aggregatingPanel.getCurrentConfigurationState());
				}
			});

		DocumentListener textFieldListener = new DocumentAdapter() {
			@Override
			protected void textChanged(DocumentEvent e)
			{
				listener.changeApplied (aggregatingPanel.getCurrentConfigurationState());
			}
		};

		mlDebuggerPort.getComponent().getDocument().addDocumentListener (textFieldListener);
		mlDebuggerUser.getComponent().getDocument().addDocumentListener (textFieldListener);
		mlDebuggerPassword.getComponent().getDocument().addDocumentListener (textFieldListener);
	}
}
