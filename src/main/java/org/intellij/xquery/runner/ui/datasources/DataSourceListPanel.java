/*
 * Copyright 2013 Grzegorz Ligas <ligasgr@gmail.com> and other contributors (see the CONTRIBUTORS file).
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

package org.intellij.xquery.runner.ui.datasources;

import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.ListScrollingUtil;
import com.intellij.ui.ListUtil;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.UIUtil;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import static org.intellij.xquery.runner.UniqueNameGenerator.generateUniqueNameUsingBaseName;

/**
 * User: ligasgr
 * Date: 03/11/13
 * Time: 01:39
 */
public class DataSourceListPanel extends JPanel {
    public static final String DATA_SOURCE_LIST_PANEL = "dataSourceListPanel";
    private ToolbarDecorator dataSourcesToolbarDecorator;
    private DefaultListModel dataSourcesListModel;
    private JBList dataSourceList;
    private JPanel dataSourceConfigurationPanel;
    private JPanel formComponent;
    private DataSourceMainConfigurationPanel dataSourceMainConfigurationPanel;
    private int previouslySelectedIndex = -1;

    public DataSourceListPanel(JPanel dataSourceConfigurationPanel, JPanel formComponent) {
        super(new BorderLayout());
        setName(DATA_SOURCE_LIST_PANEL);
        setMinimumSize(new Dimension(150, 400));
        this.dataSourceConfigurationPanel = dataSourceConfigurationPanel;
        this.formComponent = formComponent;
        dataSourcesListModel = new DefaultListModel();
        dataSourceList = prepareDataSourcesList(dataSourcesListModel);
        dataSourcesToolbarDecorator = prepareDataSourcesTableToolbarDecorator(dataSourceList);
        add(dataSourcesToolbarDecorator.createPanel(), BorderLayout.CENTER);
    }

    private JBList prepareDataSourcesList(DefaultListModel dataSourcesListModel) {
        final JBList dataSourcesList = new JBList(dataSourcesListModel);
        dataSourcesList.getEmptyText().setText("No data sources defined");
        dataSourcesList.setDragEnabled(false);
        dataSourcesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dataSourcesList.setCellRenderer(new DataSourceConfigurationCellRenderer());
        dataSourcesList.addListSelectionListener(getSelectionChangedListener(dataSourcesList));
        return dataSourcesList;
    }

    private ListSelectionListener getSelectionChangedListener(final JBList dataSourceList) {
        return new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                dataSourceConfigurationPanel.removeAll();
                XQueryDataSourceConfiguration selection = getSelectedDataSource();
                if (selection != null) {
                    saveConfigurationChangesForPreviouslySelected();
                    updateDataSourceConfigurationPanel(selection);
                    dataSourceList.repaint();
                }
                dataSourceConfigurationPanel.revalidate();
                dataSourceConfigurationPanel.repaint();
            }
        };
    }

    public XQueryDataSourceConfiguration getSelectedDataSource() {
        return (XQueryDataSourceConfiguration) dataSourceList.getSelectedValue();
    }

    private ToolbarDecorator prepareDataSourcesTableToolbarDecorator(final JBList dataSourceList) {
        return ToolbarDecorator.createDecorator(dataSourceList)
                .setAddAction(new AnActionButtonRunnable() {
                    @Override
                    public void run(AnActionButton button) {
                        showAddDataSourcePopupWithActionExecutor(getAddDataSourceActionExecutor());
                    }
                }).setRemoveAction(new AnActionButtonRunnable() {
                    @Override
                    public void run(AnActionButton button) {
                        cleanupCurrentConfigurationPanel();
                        ListUtil.removeSelectedItems(dataSourceList);
                        dataSourceList.repaint();
                    }
                }).disableUpDownActions().setToolbarPosition(ActionToolbarPosition.TOP);
    }

    private void cleanupCurrentConfigurationPanel() {
        dataSourceMainConfigurationPanel = null;
    }

    private void saveConfigurationChangesForPreviouslySelected() {
        if (dataSourceMainConfigurationPanel != null
                && previouslySelectedIndex > -1
                && previouslySelectedIndex < dataSourcesListModel.getSize()) {
            updateModelWithChangesFromConfigurationPanel(previouslySelectedIndex);
        }
    }

    private void updateModelWithChangesFromConfigurationPanel(int index) {
        if (dataSourceMainConfigurationPanel == null) return;
        XQueryDataSourceConfiguration currentConfigurationState
                = dataSourceMainConfigurationPanel.getCurrentConfigurationState();
        dataSourcesListModel.setElementAt(currentConfigurationState, index);
        updateDefaultFlagIfNeeded(currentConfigurationState);
    }


    private void updateDefaultFlagIfNeeded(XQueryDataSourceConfiguration currentConfigurationState) {
        if (currentConfigurationState.DEFAULT) {
            updateAllAsNotDefault();
            markCurrentAsDefaultAfterAllSetAsNotDefault(currentConfigurationState);
        }
    }

    private void markCurrentAsDefaultAfterAllSetAsNotDefault(XQueryDataSourceConfiguration currentConfigurationState) {
        currentConfigurationState.DEFAULT = true;
    }

    private void updateAllAsNotDefault() {
        for (int i = 0; i < dataSourcesListModel.size(); i++) {
            XQueryDataSourceConfiguration cfg = (XQueryDataSourceConfiguration) dataSourcesListModel.elementAt(i);
            cfg.DEFAULT = false;
        }
    }

    private void updateDataSourceConfigurationPanel(final XQueryDataSourceConfiguration dataSourceConfiguration) {
        final int index = dataSourceList.getSelectedIndex();
        dataSourceMainConfigurationPanel = new DataSourceMainConfigurationPanel(dataSourceConfiguration,
                getNameChangedListener(index));
        dataSourceConfigurationPanel.add(dataSourceMainConfigurationPanel.getPanel(), BorderLayout.NORTH);
        setupEnclosingDialogBounds();
        previouslySelectedIndex = index;
    }

    private void setupEnclosingDialogBounds() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIUtil.setupEnclosingDialogBounds(formComponent);
            }
        });
    }

    private DocumentAdapter getNameChangedListener(final int index) {
        return new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent e) {
                updateModelWithChangesFromConfigurationPanel(index);
            }
        };
    }

    private void showAddDataSourcePopupWithActionExecutor(ActionExecutor addDataSourceActionExecutor) {
        DataSourceTypesListPopup dataSourceTypesListPopup = new DataSourceTypesListPopup(addDataSourceActionExecutor);
        final ListPopup popup = JBPopupFactory.getInstance().createListPopup(dataSourceTypesListPopup);
        popup.showUnderneathOf(dataSourcesToolbarDecorator.getActionsPanel());
    }

    private ActionExecutor getAddDataSourceActionExecutor() {
        return new ActionExecutor() {
            @Override
            public void execute(XQueryDataSourceType type) {
                addDataSourceConfigurationBasedOnType(type);
            }
        };
    }

    private void addDataSourceConfigurationBasedOnType(XQueryDataSourceType type) {
        XQueryDataSourceConfiguration newItem = createNewDataSourceConfiguration(type);
        addNewDataSourceConfigurationToModelAndSelectIt(newItem);
    }

    private void addNewDataSourceConfigurationToModelAndSelectIt(XQueryDataSourceConfiguration newItem) {
        dataSourcesListModel.addElement(newItem);
        dataSourceList.clearSelection();
        ListScrollingUtil.selectItem(dataSourceList, newItem);
    }

    private void clearDataSourcesListModel() {
        dataSourcesListModel.removeAllElements();
    }

    private void populateModelWithClonesOfConfigurations(List<XQueryDataSourceConfiguration>
                                                                 dataSourcesConfigurations) {
        for (XQueryDataSourceConfiguration configuration : dataSourcesConfigurations) {
            addConfigurationCloneToModel(configuration);
        }
    }

    private void addConfigurationCloneToModel(XQueryDataSourceConfiguration configuration) {
        try {
            dataSourcesListModel.addElement(configuration.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setFirstPositionAsSelectedIfExists() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (dataSourcesListModel.size() > 0) {
                    dataSourceList.setSelectedIndex(0);
                }
            }
        });
    }

    private XQueryDataSourceConfiguration createNewDataSourceConfiguration(XQueryDataSourceType type) {
        String name = generateUniqueNameUsingBaseName(type.getPresentableName(), getNames(getCurrentConfigurations()));
        return new XQueryDataSourceConfiguration(name, type);
    }

    public List<XQueryDataSourceConfiguration> getCurrentConfigurations() {
        updateModelWithChangesFromConfigurationPanel(previouslySelectedIndex);
        return getConfigurationsFromModel();
    }

    private List<XQueryDataSourceConfiguration> getConfigurationsFromModel() {
        List<XQueryDataSourceConfiguration> currentConfigurations = new ArrayList<XQueryDataSourceConfiguration>();
        for (int i = 0; i < dataSourcesListModel.getSize(); i++) {
            currentConfigurations.add(((XQueryDataSourceConfiguration) dataSourcesListModel.getElementAt(i)));
        }
        return currentConfigurations;
    }


    private List<String> getNames(List<XQueryDataSourceConfiguration> currentConfigurations) {
        List<String> names = new ArrayList<String>();
        for (XQueryDataSourceConfiguration cfg : currentConfigurations) {
            names.add(cfg.NAME);
        }
        return names;
    }

    public void populateWithConfigurations(List<XQueryDataSourceConfiguration> dataSourceConfigurations) {
        cleanupCurrentConfigurationPanel();
        clearDataSourcesListModel();
        populateModelWithClonesOfConfigurations(dataSourceConfigurations);
        setFirstPositionAsSelectedIfExists();
    }
}
