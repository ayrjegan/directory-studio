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
import org.apache.directory.studio.schemaeditor.view.views.SearchView;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;


/**
 * This action is used to run the current search again.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class RunCurrentSearchAgainAction extends Action implements IWorkbenchWindowActionDelegate
{
    /** The associated view */
    private SearchView view;


    /**
     * Creates a new instance of ShowSearchFieldAction.
     */
    public RunCurrentSearchAgainAction( SearchView view )
    {
        super( Messages.getString( "RunCurrentSearchAgainAction.RerunSearchAction" ) ); //$NON-NLS-1$
        setToolTipText( getText() );
        setImageDescriptor( Activator.getDefault().getImageDescriptor( PluginConstants.IMG_RUN_CURRENT_SEARCH_AGAIN ) );
        setEnabled( true );
        this.view = view;
    }


    /**
     * {@inheritDoc}
     */
    public void run()
    {
        view.runCurrentSearchAgain();
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
