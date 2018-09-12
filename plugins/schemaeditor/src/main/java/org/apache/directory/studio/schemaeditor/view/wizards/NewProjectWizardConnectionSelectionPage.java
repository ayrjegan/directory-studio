/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.studio.schemaeditor.view.wizards;


import org.apache.directory.studio.connection.core.Connection;
import org.apache.directory.studio.connection.core.ConnectionCorePlugin;
import org.apache.directory.studio.connection.ui.widgets.ConnectionActionGroup;
import org.apache.directory.studio.connection.ui.widgets.ConnectionConfiguration;
import org.apache.directory.studio.connection.ui.widgets.ConnectionUniversalListener;
import org.apache.directory.studio.connection.ui.widgets.ConnectionWidget;
import org.apache.directory.studio.schemaeditor.Activator;
import org.apache.directory.studio.schemaeditor.PluginConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * This class represents the Information Page of the NewProjectWizard.
 * <p>
 * It is used to let the user create a new Project
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class NewProjectWizardConnectionSelectionPage extends AbstractWizardPage
{
    private ConnectionConfiguration configuration;
    private ConnectionUniversalListener universalListener;

    // UI Fields
    private ConnectionWidget connectionWidget;
    private ConnectionActionGroup actionGroup;


    /**
     * Creates a new instance of NewProjectWizardConnectionSelectionPage.
     */
    protected NewProjectWizardConnectionSelectionPage()
    {
        super( "NewProjectWizardConnectionSelectionPage" ); //$NON-NLS-1$
        setTitle( Messages.getString( "NewProjectWizardConnectionSelectionPage.CreateSchemaProject" ) ); //$NON-NLS-1$
        setDescription( Messages.getString( "NewProjectWizardConnectionSelectionPage.PleaseSelectConnection" ) ); //$NON-NLS-1$
        setImageDescriptor( Activator.getDefault().getImageDescriptor( PluginConstants.IMG_PROJECT_NEW_WIZARD ) );
        setPageComplete( false );
    }


    /**
     * {@inheritDoc}
     */
    public void createControl( Composite parent )
    {
        Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new GridLayout() );

        // Choose A Connection Label
        Label label = new Label( composite, SWT.NONE );
        label.setText( Messages.getString( "NewProjectWizardConnectionSelectionPage.ChooseConnection" ) ); //$NON-NLS-1$
        label.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false ) );

        // Creating configuration
        configuration = new ConnectionConfiguration();

        // Creating Connection Widget
        connectionWidget = new ConnectionWidget( configuration, null );
        connectionWidget.createWidget( composite );
        connectionWidget.setInput( ConnectionCorePlugin.getDefault().getConnectionFolderManager() );

        connectionWidget.getViewer().addSelectionChangedListener( new ISelectionChangedListener()
        {
            public void selectionChanged( SelectionChangedEvent event )
            {
                validatePage();
            }
        } );

        // creating the listener
        universalListener = new ConnectionUniversalListener( connectionWidget.getViewer() );

        // create actions and context menu (and register global actions)
        actionGroup = new ConnectionActionGroup( connectionWidget, configuration );
        actionGroup.fillToolBar( connectionWidget.getToolBarManager() );
        actionGroup.fillMenu( connectionWidget.getMenuManager() );
        actionGroup.fillContextMenu( connectionWidget.getContextMenuManager() );
        actionGroup.activateGlobalActionHandlers();

        initFields();

        setControl( composite );
    }


    /**
     * Initializes the UI Fields.
     */
    private void initFields()
    {
        displayErrorMessage( null );
        setPageComplete( false );
    }


    /**
     * Validates the page.
     */
    private void validatePage()
    {
        ISelection selection = connectionWidget.getViewer().getSelection();
        if ( selection.isEmpty() )
        {
            displayErrorMessage( Messages
                .getString( "NewProjectWizardConnectionSelectionPage.ErrorNoConnectionSelected" ) ); //$NON-NLS-1$
            return;
        }

        displayErrorMessage( null );
    }


    /**
     * Gets the selected connection.
     *
     * @return
     *      the selection connection
     */
    public Connection getSelectedConnection()
    {
        return ( Connection ) ( ( StructuredSelection ) connectionWidget.getViewer().getSelection() ).getFirstElement();
    }


    /**
     * {@inheritDoc}
     */
    public void dispose()
    {
        if ( configuration != null )
        {
            actionGroup.dispose();
            actionGroup = null;
            configuration.dispose();
            configuration = null;
            universalListener.dispose();
            universalListener = null;
            connectionWidget.dispose();
            connectionWidget = null;
        }

        super.dispose();
    }
}
