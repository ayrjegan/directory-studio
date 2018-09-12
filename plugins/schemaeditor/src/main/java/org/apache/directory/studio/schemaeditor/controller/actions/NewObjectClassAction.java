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
package org.apache.directory.studio.schemaeditor.controller.actions;


import org.apache.directory.studio.schemaeditor.Activator;
import org.apache.directory.studio.schemaeditor.PluginConstants;
import org.apache.directory.studio.schemaeditor.model.Schema;
import org.apache.directory.studio.schemaeditor.view.wizards.NewObjectClassWizard;
import org.apache.directory.studio.schemaeditor.view.wrappers.AttributeTypeWrapper;
import org.apache.directory.studio.schemaeditor.view.wrappers.Folder;
import org.apache.directory.studio.schemaeditor.view.wrappers.ObjectClassWrapper;
import org.apache.directory.studio.schemaeditor.view.wrappers.SchemaWrapper;
import org.apache.directory.studio.schemaeditor.view.wrappers.TreeNode;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;


/**
 * This action launches the NewObjectClassWizard.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class NewObjectClassAction extends Action implements IWorkbenchWindowActionDelegate
{
    /** The associated viewer */
    private TreeViewer viewer;


    /**
     * Creates a new instance of NewObjectClassAction.
     */
    public NewObjectClassAction( TreeViewer viewer )
    {
        super( Messages.getString( "NewObjectClassAction.NewObjectClassAction" ) ); //$NON-NLS-1$
        setToolTipText( Messages.getString( "NewObjectClassAction.NewObjectClassToolTip" ) ); //$NON-NLS-1$
        setId( PluginConstants.CMD_NEW_OBJECT_CLASS );
        setActionDefinitionId( PluginConstants.CMD_NEW_OBJECT_CLASS );
        setImageDescriptor( Activator.getDefault().getImageDescriptor( PluginConstants.IMG_OBJECT_CLASS_NEW ) );
        setEnabled( false );
        this.viewer = viewer;
    }


    /**
     * {@inheritDoc}
     */
    public void run()
    {
        // Getting the selection
        Schema selectedSchema = null;

        int presentation = Activator.getDefault().getPreferenceStore().getInt(
            PluginConstants.PREFS_SCHEMA_VIEW_SCHEMA_PRESENTATION );
        if ( presentation == PluginConstants.PREFS_SCHEMA_VIEW_SCHEMA_PRESENTATION_FLAT )
        {
            StructuredSelection selection = ( StructuredSelection ) viewer.getSelection();
            if ( ( !selection.isEmpty() ) && ( selection.size() == 1 ) )
            {
                Object firstElement = selection.getFirstElement();
                if ( firstElement instanceof SchemaWrapper )
                {
                    selectedSchema = ( ( SchemaWrapper ) firstElement ).getSchema();
                }
                else if ( firstElement instanceof Folder )
                {
                    selectedSchema = ( ( SchemaWrapper ) ( ( Folder ) firstElement ).getParent() ).getSchema();
                }
                else if ( firstElement instanceof AttributeTypeWrapper )
                {
                    TreeNode parent = ( ( AttributeTypeWrapper ) firstElement ).getParent();

                    if ( parent instanceof Folder )
                    {
                        selectedSchema = ( ( SchemaWrapper ) ( ( Folder ) parent ).getParent() ).getSchema();

                    }
                    else if ( parent instanceof SchemaWrapper )
                    {
                        selectedSchema = ( ( SchemaWrapper ) parent ).getSchema();
                    }
                }
                else if ( firstElement instanceof ObjectClassWrapper )
                {
                    TreeNode parent = ( ( ObjectClassWrapper ) firstElement ).getParent();

                    if ( parent instanceof Folder )
                    {
                        selectedSchema = ( ( SchemaWrapper ) ( ( Folder ) parent ).getParent() ).getSchema();
                    }
                    else if ( parent instanceof SchemaWrapper )
                    {
                        selectedSchema = ( ( SchemaWrapper ) parent ).getSchema();
                    }
                }
            }
        }

        // Instantiates and initializes the wizard
        NewObjectClassWizard wizard = new NewObjectClassWizard();
        wizard.init( PlatformUI.getWorkbench(), StructuredSelection.EMPTY );
        wizard.setSelectedSchema( selectedSchema );
        // Instantiates the wizard container with the wizard and opens it
        WizardDialog dialog = new WizardDialog( PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard );
        dialog.create();
        dialog.open();
    }


    /**
     * {@inheritDoc}
     */
    public void run( IAction action )
    {
        run();
    }


    /**
     * {@inheritDoc}
     */
    public void dispose()
    {
        // Nothing to do
    }


    /**
     * {@inheritDoc}
     */
    public void init( IWorkbenchWindow window )
    {
        // Nothing to do
    }


    /**
     * {@inheritDoc}
     */
    public void selectionChanged( IAction action, ISelection selection )
    {
        // Nothing to do
    }
}
