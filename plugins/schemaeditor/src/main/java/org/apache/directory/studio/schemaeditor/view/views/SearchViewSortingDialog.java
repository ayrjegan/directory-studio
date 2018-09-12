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

package org.apache.directory.studio.schemaeditor.view.views;


import org.apache.directory.studio.schemaeditor.Activator;
import org.apache.directory.studio.schemaeditor.PluginConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/**
 * This class implements the SearchView Sorting Dialog.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class SearchViewSortingDialog extends Dialog
{
    // UI Fields
    private Button attributeTypesFirst;
    private Button objectClassesFirst;
    private Button mixedButton;
    private Combo sortingCombo;
    private Button ascendingButton;
    private Button descendingButton;


    /**
     * Creates a new instance of SearchViewSortingDialog.
     *
     * @param parentShell
     *      the parent shell
     */
    public SearchViewSortingDialog( Shell parentShell )
    {
        super( parentShell );
    }


    /**
     * {@inheritDoc}
     */
    protected void configureShell( Shell newShell )
    {
        super.configureShell( newShell );
        newShell.setText( Messages.getString( "SearchViewSortingDialog.ViewSorting" ) ); //$NON-NLS-1$
    }


    /**
     * {@inheritDoc}
     */
    protected Control createDialogArea( Composite parent )
    {
        Composite composite = ( Composite ) super.createDialogArea( parent );
        GridData gd = new GridData( SWT.FILL, SWT.FILL, true, true );
        composite.setLayoutData( gd );

        // Grouping Group
        Group groupingGroup = new Group( composite, SWT.NONE );
        groupingGroup.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
        groupingGroup.setText( Messages.getString( "SearchViewSortingDialog.Grouping" ) ); //$NON-NLS-1$
        groupingGroup.setLayout( new GridLayout() );

        // Attribute Types first Button
        attributeTypesFirst = new Button( groupingGroup, SWT.RADIO );
        attributeTypesFirst.setText( Messages.getString( "SearchViewSortingDialog.TypesFirst" ) ); //$NON-NLS-1$
        attributeTypesFirst.setEnabled( true );

        // Object Classes first Button
        objectClassesFirst = new Button( groupingGroup, SWT.RADIO );
        objectClassesFirst.setText( Messages.getString( "SearchViewSortingDialog.ClassesFirst" ) ); //$NON-NLS-1$
        objectClassesFirst.setEnabled( true );

        // Mixed Button
        mixedButton = new Button( groupingGroup, SWT.RADIO );
        mixedButton.setText( Messages.getString( "SearchViewSortingDialog.Mixed" ) ); //$NON-NLS-1$
        mixedButton.setEnabled( true );

        // Sorting Group
        Group sortingGroup = new Group( composite, SWT.NONE );
        sortingGroup.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
        sortingGroup.setText( Messages.getString( "SearchViewSortingDialog.Sorting" ) ); //$NON-NLS-1$
        sortingGroup.setLayout( new GridLayout() );
        Composite sortingGroupComposite = new Composite( sortingGroup, SWT.NONE );
        GridLayout gl = new GridLayout( 4, false );
        gl.marginHeight = gl.marginWidth = 0;
        sortingGroupComposite.setLayout( gl );
        sortingGroupComposite.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false ) );

        // Sort by Label
        Label sortByLabel = new Label( sortingGroupComposite, SWT.NONE );
        sortByLabel.setText( Messages.getString( "SearchViewSortingDialog.SortBy" ) ); //$NON-NLS-1$

        // Sorting Combo
        sortingCombo = new Combo( sortingGroupComposite, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER );
        sortingCombo.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false ) );
        sortingCombo
            .setItems( new String[]
                {
                    Messages.getString( "SearchViewSortingDialog.FirstName" ), Messages.getString( "SearchViewSortingDialog.OID" ) } ); //$NON-NLS-1$ //$NON-NLS-2$
        sortingCombo.setEnabled( true );

        // Ascending Button
        ascendingButton = new Button( sortingGroupComposite, SWT.RADIO );
        ascendingButton.setText( Messages.getString( "SearchViewSortingDialog.Ascending" ) ); //$NON-NLS-1$
        ascendingButton.setEnabled( true );

        // Descending Button
        descendingButton = new Button( sortingGroupComposite, SWT.RADIO );
        descendingButton.setText( Messages.getString( "SearchViewSortingDialog.Descending" ) ); //$NON-NLS-1$
        descendingButton.setEnabled( true );

        initFieldsFromPreferences();

        applyDialogFont( composite );
        return composite;
    }


    /**
     * Initializes the fields for the stored preferences.
     */
    private void initFieldsFromPreferences()
    {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();

        int grouping = store.getInt( PluginConstants.PREFS_SEARCH_VIEW_GROUPING );
        if ( grouping == PluginConstants.PREFS_SEARCH_VIEW_GROUPING_ATTRIBUTE_TYPES_FIRST )
        {
            attributeTypesFirst.setSelection( true );
        }
        else if ( grouping == PluginConstants.PREFS_SEARCH_VIEW_GROUPING_OBJECT_CLASSES_FIRST )
        {
            objectClassesFirst.setSelection( true );
        }
        else if ( grouping == PluginConstants.PREFS_SEARCH_VIEW_GROUPING_MIXED )
        {
            mixedButton.setSelection( true );
        }

        int sortingBy = store.getInt( PluginConstants.PREFS_SEARCH_VIEW_SORTING_BY );
        if ( sortingBy == PluginConstants.PREFS_SEARCH_VIEW_SORTING_BY_FIRSTNAME )
        {
            sortingCombo.select( 0 );
        }
        else if ( sortingBy == PluginConstants.PREFS_SEARCH_VIEW_SORTING_BY_OID )
        {
            sortingCombo.select( 1 );
        }

        int sortingOrder = store.getInt( PluginConstants.PREFS_SEARCH_VIEW_SORTING_ORDER );
        if ( sortingOrder == PluginConstants.PREFS_SEARCH_VIEW_SORTING_ORDER_ASCENDING )
        {
            ascendingButton.setSelection( true );
        }
        else if ( sortingOrder == PluginConstants.PREFS_SEARCH_VIEW_SORTING_ORDER_DESCENDING )
        {
            descendingButton.setSelection( true );
        }

    }


    /**
     * {@inheritDoc}
     */
    protected void buttonPressed( int buttonId )
    {
        if ( buttonId == IDialogConstants.OK_ID )
        {
            IPreferenceStore store = Activator.getDefault().getPreferenceStore();
            if ( ( attributeTypesFirst.getSelection() ) && ( !objectClassesFirst.getSelection() )
                && ( !mixedButton.getSelection() ) )
            {
                store.setValue( PluginConstants.PREFS_SEARCH_VIEW_GROUPING,
                    PluginConstants.PREFS_SEARCH_VIEW_GROUPING_ATTRIBUTE_TYPES_FIRST );
            }
            else if ( ( !attributeTypesFirst.getSelection() ) && ( objectClassesFirst.getSelection() )
                && ( !mixedButton.getSelection() ) )
            {
                store.setValue( PluginConstants.PREFS_SEARCH_VIEW_GROUPING,
                    PluginConstants.PREFS_SEARCH_VIEW_GROUPING_OBJECT_CLASSES_FIRST );
            }
            else if ( ( !attributeTypesFirst.getSelection() ) && ( !objectClassesFirst.getSelection() )
                && ( mixedButton.getSelection() ) )
            {
                store.setValue( PluginConstants.PREFS_SEARCH_VIEW_GROUPING,
                    PluginConstants.PREFS_SEARCH_VIEW_GROUPING_MIXED );
            }

            if ( sortingCombo.getItem( sortingCombo.getSelectionIndex() ).equals(
                Messages.getString( "SearchViewSortingDialog.FirstName" ) ) ) //$NON-NLS-1$
            {
                store.setValue( PluginConstants.PREFS_SEARCH_VIEW_SORTING_BY,
                    PluginConstants.PREFS_SEARCH_VIEW_SORTING_BY_FIRSTNAME );
            }
            else if ( sortingCombo.getItem( sortingCombo.getSelectionIndex() ).equals(
                Messages.getString( "SearchViewSortingDialog.OID" ) ) ) //$NON-NLS-1$
            {
                store.setValue( PluginConstants.PREFS_SEARCH_VIEW_SORTING_BY,
                    PluginConstants.PREFS_SEARCH_VIEW_SORTING_BY_OID );
            }

            if ( ascendingButton.getSelection() && !descendingButton.getSelection() )
            {
                store.setValue( PluginConstants.PREFS_SEARCH_VIEW_SORTING_ORDER,
                    PluginConstants.PREFS_SEARCH_VIEW_SORTING_ORDER_ASCENDING );
            }
            else if ( !ascendingButton.getSelection() && descendingButton.getSelection() )
            {
                store.setValue( PluginConstants.PREFS_SEARCH_VIEW_SORTING_ORDER,
                    PluginConstants.PREFS_SEARCH_VIEW_SORTING_ORDER_DESCENDING );
            }
        }

        super.buttonPressed( buttonId );
    }
}
