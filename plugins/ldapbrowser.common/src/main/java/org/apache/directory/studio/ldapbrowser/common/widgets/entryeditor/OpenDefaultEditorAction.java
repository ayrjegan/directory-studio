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

package org.apache.directory.studio.ldapbrowser.common.widgets.entryeditor;


import org.apache.directory.studio.ldapbrowser.common.BrowserCommonConstants;
import org.apache.directory.studio.ldapbrowser.common.actions.BrowserAction;
import org.apache.directory.studio.ldapbrowser.common.actions.proxy.EntryEditorActionProxy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;


/**
 * The OpenBestEditorAction is used to edit a value with the default value editor.
 * This is either the best value editor or in case of an Rdn attribute the rename
 * action is invoked.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class OpenDefaultEditorAction extends BrowserAction
{

    /** The best value editor proxy. */
    private EntryEditorActionProxy bestValueEditorProxy;


    /**
     * Creates a new instance of OpenDefaultEditorAction.
     * 
     * @param viewer the viewer
     * @param bestValueEditorProxy the best value editor proxy
     * @param enableRenameAction true to enable rename action
     */
    public OpenDefaultEditorAction( TreeViewer viewer, EntryEditorActionProxy bestValueEditorProxy )
    {
        this.bestValueEditorProxy = bestValueEditorProxy;
    }


    /**
     * @see org.apache.directory.studio.ldapbrowser.common.actions.BrowserAction#dispose()
     */
    public void dispose()
    {
        bestValueEditorProxy = null;
        super.dispose();
    }


    /**
     * @see org.apache.directory.studio.ldapbrowser.common.actions.BrowserAction#getCommandId()
     */
    public String getCommandId()
    {
        return BrowserCommonConstants.ACTION_ID_EDIT_VALUE;
    }


    /**
     * @see org.apache.directory.studio.ldapbrowser.common.actions.BrowserAction#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor()
    {
        if ( bestValueEditorProxy != null )
        {
            return bestValueEditorProxy.getImageDescriptor();
        }
        else
        {
            return null;
        }
    }


    /**
     * @see org.apache.directory.studio.ldapbrowser.common.actions.BrowserAction#getText()
     */
    public String getText()
    {
        return Messages.getString( "OpenDefaultEditorAction.EditValue" ); //$NON-NLS-1$
    }


    /**
     * @see org.apache.directory.studio.ldapbrowser.common.actions.BrowserAction#isEnabled()
     */
    public boolean isEnabled()
    {
        if ( bestValueEditorProxy != null )
        {
            return bestValueEditorProxy.isEnabled();
        }
        else
        {
            return false;
        }
    }


    /**
     * @see org.apache.directory.studio.ldapbrowser.common.actions.BrowserAction#run()
     */
    public void run()
    {
        if ( bestValueEditorProxy != null && bestValueEditorProxy.isEnabled() )
        {
            bestValueEditorProxy.run();
        }
    }

}
