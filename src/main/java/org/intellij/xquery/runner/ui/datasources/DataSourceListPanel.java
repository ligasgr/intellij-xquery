/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.ui.datasources;

import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;
import com.intellij.ui.ListScrollingUtil;
import com.intellij.ui.ListUtil;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.intellij.xquery.runner.state.datasources.XQueryDataSourceConfiguration;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
    private DefaultListModel dataSourcesListModel;
    private JBList dataSourceList;
    private DataSourceDetailsPanel dataSourceDetailsPanel;
    private ToolbarDecorator toolbarDecorator;

    public DataSourceListPanel(DataSourceDetailsPanel dataSourceDetailsPanel) {
        super(new BorderLayout());
        this.dataSourceDetailsPanel = dataSourceDetailsPanel;
        setName(DATA_SOURCE_LIST_PANEL);
        setMinimumSize(new Dimension(150, 400));
        dataSourcesListModel = new DefaultListModel();
        dataSourceList = prepareDataSourcesList(dataSourcesListModel);
        toolbarDecorator = prepareDataSourcesTableToolbarDecorator(dataSourceList);
        add(toolbarDecorator.createPanel(), BorderLayout.CENTER);
    }

    public XQueryDataSourceConfiguration getSelectedDataSource() {
        return (XQueryDataSourceConfiguration) dataSourceList.getSelectedValue();
    }

    public List<XQueryDataSourceConfiguration> getCurrentConfigurations() {
        List<XQueryDataSourceConfiguration> currentConfigurations = new ArrayList<XQueryDataSourceConfiguration>();
        for (int i = 0; i < dataSourcesListModel.getSize(); i++) {
            currentConfigurations.add(((XQueryDataSourceConfiguration) dataSourcesListModel.getElementAt(i)));
        }
        return currentConfigurations;
    }

    public void populateWithConfigurations(List<XQueryDataSourceConfiguration> dataSourceConfigurations) {
        clearDataSourcesListModel();
        populateModelWithClonesOfConfigurations(dataSourceConfigurations);
        setFirstPositionAsSelectedIfExists();
    }

    private JBList prepareDataSourcesList(DefaultListModel dataSourcesListModel) {
        final JBList dataSourcesList = new JBList(dataSourcesListModel);
        dataSourcesList.getEmptyText().setText("No data sources defined");
        dataSourcesList.setDragEnabled(false);
        dataSourcesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dataSourcesList.setCellRenderer(new DataSourceConfigurationCellRenderer());
        dataSourcesList.addListSelectionListener(getSelectionChangedListener());
        return dataSourcesList;
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
                        dataSourceDetailsPanel.stopDisplayingDetails();
                        ListUtil.removeSelectedItems(dataSourceList);
                        dataSourceList.repaint();
                    }
                }).disableUpDownActions().setToolbarPosition(ActionToolbarPosition.TOP);
    }

    protected void updateCurrentlySelectedItemWithData(XQueryDataSourceConfiguration currentConfigurationState) {
        int index = dataSourceList.getSelectedIndex();
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

    protected void showAddDataSourcePopupWithActionExecutor(XQueryDataSourceTypeBasedActionExecutor
                                                                    addDataSourceActionExecutor) {
        DataSourceTypesListPopup dataSourceTypesListPopup = new DataSourceTypesListPopup(addDataSourceActionExecutor);
        final ListPopup popup = JBPopupFactory.getInstance().createListPopup(dataSourceTypesListPopup);
        popup.showUnderneathOf(toolbarDecorator.getActionsPanel());
    }

    private XQueryDataSourceTypeBasedActionExecutor getAddDataSourceActionExecutor() {
        return new XQueryDataSourceTypeBasedActionExecutor() {
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

    private List<String> getNames(List<XQueryDataSourceConfiguration> currentConfigurations) {
        List<String> names = new ArrayList<String>();
        for (XQueryDataSourceConfiguration cfg : currentConfigurations) {
            names.add(cfg.NAME);
        }
        return names;
    }


    private ListSelectionListener getSelectionChangedListener() {
        return new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;
                dataSourceDetailsPanel.stopDisplayingDetails();
                XQueryDataSourceConfiguration selection = getSelectedDataSource();
                if (selection != null) {
                    dataSourceDetailsPanel.displayDetails(selection, getConfigurationChangeListener());
                    ((JBList) e.getSource()).repaint();
                }
                dataSourceDetailsPanel.revalidate();
                dataSourceDetailsPanel.repaint();
            }
        };
    }

    private ConfigurationChangeListener getConfigurationChangeListener() {
        return new ConfigurationChangeListener() {
            @Override
            public void changeApplied(XQueryDataSourceConfiguration currentState) {
                updateCurrentlySelectedItemWithData(currentState);
            }
        };
    }

    public ToolbarDecorator getToolbarDecorator() {
        return toolbarDecorator;
    }
}
