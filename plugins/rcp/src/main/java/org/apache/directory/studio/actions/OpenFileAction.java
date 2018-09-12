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

package org.apache.directory.studio.actions;


import java.text.MessageFormat;

import org.apache.directory.studio.Messages;
import org.apache.directory.studio.PluginConstants;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;


/**
 * The Action is used to open files from file system. 
 * It creates IPathEditorInput inputs.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class OpenFileAction extends Action implements IWorkbenchWindowActionDelegate
{
    private IWorkbenchWindow window;
    private String filterPath;


    /**
     * Creates a new instance of OpenFileAction.
     */
    public OpenFileAction()
    {
        setId( PluginConstants.ACTION_OPEN_FILE_ID ); //$NON-NLS-1$
        setText( Messages.getString( "OpenFileAction.Open_File" ) ); //$NON-NLS-1$
        setToolTipText( Messages.getString( "OpenFileAction.Open_file_from_filesystem" ) ); //$NON-NLS-1$
        setEnabled( true );
    }


    /**
     * Creates a new instance of OpenFileAction.
     *
     * @param window the workbench window
     */
    public OpenFileAction( IWorkbenchWindow window )
    {
        this();
        init( window );
    }


    /**
     * {@inheritDoc}
     */
    public void dispose()
    {
        window = null;
        filterPath = null;
    }


    /**
     * {@inheritDoc}
     */
    public void init( IWorkbenchWindow window )
    {
        this.window = window;
        filterPath = System.getProperty( "user.home" ); //$NON-NLS-1$
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
    public void selectionChanged( IAction action, ISelection selection )
    {
    }


    /**
     * {@inheritDoc}
     */
    public void run()
    {
        FileDialog dialog = new FileDialog( window.getShell(), SWT.OPEN | SWT.MULTI );
        dialog.setText( Messages.getString( "OpenFileAction.Open_File" ) ); //$NON-NLS-1$
        dialog.setFilterPath( filterPath );
        dialog.open();
        String[] names = dialog.getFileNames();

        if ( names != null )
        {
            filterPath = dialog.getFilterPath();

            int numberOfFilesNotFound = 0;
            StringBuffer notFound = new StringBuffer();
            IWorkbenchPage page = window.getActivePage();
            for ( String name : names )
            {
                IFileStore fileStore = EFS.getLocalFileSystem().getStore( new Path( filterPath ) ).getChild( name );
                IFileInfo fetchInfo = fileStore.fetchInfo();
                if ( !fetchInfo.isDirectory() && fetchInfo.exists() )
                {
                    try
                    {
                        IDE.openEditorOnFileStore( page, fileStore );
                    }
                    catch ( PartInitException e )
                    {
                        MessageDialog.openError( window.getShell(), Messages.getString( "OpenFileAction.Error" ), e //$NON-NLS-1$
                            .getMessage() );
                    }
                }
                else
                {
                    if ( ++numberOfFilesNotFound > 1 )
                    {
                        notFound.append( '\n' );
                    }
                    notFound.append( fileStore.getName() );
                }
            }

            if ( numberOfFilesNotFound > 0 )
            {
                String msg = MessageFormat.format( numberOfFilesNotFound == 1 ? Messages
                    .getString( "OpenFileAction.File_not_found" ) : Messages //$NON-NLS-1$
                    .getString( "OpenFileAction.Files_not_found" ), new Object[] //$NON-NLS-1$
                    { notFound.toString() } );
                MessageDialog.openError( window.getShell(), Messages.getString( "OpenFileAction.Error" ), msg ); //$NON-NLS-1$
            }
        }
    }
}