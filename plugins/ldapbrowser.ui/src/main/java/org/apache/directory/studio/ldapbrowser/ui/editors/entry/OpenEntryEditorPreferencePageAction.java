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

package org.apache.directory.studio.ldapbrowser.ui.editors.entry;


import org.apache.directory.studio.ldapbrowser.ui.BrowserUIConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;


/**
 * This action opens the preference page of the entry editor.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class OpenEntryEditorPreferencePageAction extends Action
{

    /**
     * Creates a new instance of OpenEntryEditorPreferencePageAction.
     */
    public OpenEntryEditorPreferencePageAction()
    {
        setText( Messages.getString( "OpenEntryEditorPreferencePageAction.Preferences" ) ); //$NON-NLS-1$
        setToolTipText( Messages.getString( "OpenEntryEditorPreferencePageAction.PreferencesToolTip" ) ); //$NON-NLS-1$
        setEnabled( true );
    }


    /**
     * {@inheritDoc}
     */
    public void run()
    {
        Shell shell = Display.getCurrent().getActiveShell();
        String eePageId = BrowserUIConstants.PREFERENCEPAGEID_ENTRYEDITOR;
        String attPageId = BrowserUIConstants.PREFERENCEPAGEID_ATTRIBUTES;
        PreferencesUtil.createPreferenceDialogOn( shell, eePageId, new String[]
            { eePageId, attPageId }, null ).open();
    }

}
