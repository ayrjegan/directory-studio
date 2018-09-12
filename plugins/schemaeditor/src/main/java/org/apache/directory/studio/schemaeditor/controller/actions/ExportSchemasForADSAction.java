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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.directory.studio.schemaeditor.Activator;
import org.apache.directory.studio.schemaeditor.PluginConstants;
import org.apache.directory.studio.schemaeditor.model.Schema;
import org.apache.directory.studio.schemaeditor.view.wizards.ExportSchemasForADSWizard;
import org.apache.directory.studio.schemaeditor.view.wrappers.SchemaWrapper;
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
 * This action launches the ExportSchemasAsXmlWizard.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class ExportSchemasForADSAction extends Action implements IWorkbenchWindowActionDelegate
{
    /** The associated viewer */
    private TreeViewer viewer;


    /**
     * Creates a new instance of ExportSchemasAsXmlAction.
     */
    public ExportSchemasForADSAction( TreeViewer viewer )
    {
        super( Messages.getString( "ExportSchemasForADSAction.SchemaForADSAction" ) ); //$NON-NLS-1$
        setToolTipText( getText() );
        setImageDescriptor( Activator.getDefault().getImageDescriptor( PluginConstants.IMG_SCHEMAS_EXPORT_FOR_ADS ) );
        setEnabled( true );
        this.viewer = viewer;
    }


    /**
     * {@inheritDoc}
     */
    public void run()
    {
        List<Schema> selectedSchemas = new ArrayList<Schema>();
        // Getting the selection
        StructuredSelection selection = ( StructuredSelection ) viewer.getSelection();
        if ( ( !selection.isEmpty() ) && ( selection.size() > 0 ) )
        {
            for ( Iterator<?> i = selection.iterator(); i.hasNext(); )
            {
                Object o = i.next();
                if ( o instanceof SchemaWrapper )
                {
                    selectedSchemas.add( ( ( SchemaWrapper ) o ).getSchema() );
                }
            }
        }

        // Instantiates and initializes the wizard
        ExportSchemasForADSWizard wizard = new ExportSchemasForADSWizard();
        wizard.setSelectedSchemas( selectedSchemas.toArray( new Schema[0] ) );
        wizard.init( PlatformUI.getWorkbench(), StructuredSelection.EMPTY );
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
