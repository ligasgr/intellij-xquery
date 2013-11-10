package org.intellij.xquery.runner.ui.datasources.details;

import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.DocumentAdapter;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;
import org.intellij.xquery.runner.ui.datasources.ConfigurationChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleLocalFileDescriptor;

/**
 * A view for including an embedded XQuery processor. The Jar file has to be externally given, so that
 * a version defined by the user can be used.
 *
 * @author Dirk Kirsten, BaseX GmbH
 */
public class EmbeddedJarFilePanel {
  public static final String JAR_FILE = "jarFile";
  private JPanel mainPanel;
  private TextFieldWithBrowseButton jarLocation;

  public EmbeddedJarFilePanel() {
    jarLocation.addBrowseFolderListener("Choose file", null, null, createSingleLocalFileDescriptor(),
            TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT, false);
    jarLocation.getTextField().setName(JAR_FILE);
  }

  public String getJarFile() {
    return jarLocation.getText();
  }

  public void init(XQueryDataSourceConfiguration cfg, DataSourceConfigurationAggregatingPanel
          dataSourceConfigurationAggregatingPanel, ConfigurationChangeListener changeListener) {
    mainPanel.setVisible(cfg.TYPE.embeddedJarIsSupported());
    jarLocation.setText(cfg.JAR_LOCATION);
    setUpChangeListeners(dataSourceConfigurationAggregatingPanel, changeListener);
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }

  public void updateConfigurationWithChanges(XQueryDataSourceConfiguration currentConfiguration) {
    currentConfiguration.JAR_LOCATION = getJarFile();
  }

  private void setUpChangeListeners(final DataSourceConfigurationAggregatingPanel aggregatingPanel,
                                    final ConfigurationChangeListener listener) {
    jarLocation.getTextField().getDocument().addDocumentListener(new DocumentAdapter() {
      @Override
      protected void textChanged(DocumentEvent e) {
        listener.changeApplied(aggregatingPanel.getCurrentConfigurationState());
      }
    });
  }
}
