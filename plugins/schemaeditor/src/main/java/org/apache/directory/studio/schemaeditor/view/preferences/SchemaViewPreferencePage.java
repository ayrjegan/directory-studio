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
package org.apache.directory.studio.schemaeditor.view.preferences;


import org.apache.directory.studio.schemaeditor.Activator;
import org.apache.directory.studio.schemaeditor.PluginConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * This class implements the Preference page for the Schema View
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class SchemaViewPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
    /** The preference page ID */
    public static final String ID = PluginConstants.PREF_PAGE_SCHEMA_VIEW_ID;

    // UI fields
    private Combo labelCombo;
    private Button limitButton;
    private Text lengthText;
    private Button secondaryLabelButtonDisplay;
    private Combo secondaryLabelCombo;
    private Button secondaryLabelLimitButton;
    private Text secondaryLabelLengthText;
    private Button schemaLabelButtonDisplay;


    /**
     * Creates a new instance of SchemaViewPreferencePage.
     */
    public SchemaViewPreferencePage()
    {
        super();
        setPreferenceStore( Activator.getDefault().getPreferenceStore() );
        setDescription( Messages.getString( "SchemaViewPreferencePage.GeneralSettings" ) ); //$NON-NLS-1$
    }


    /**
     * {@inheritDoc}
     */
    protected Control createContents( Composite parent )
    {
        Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new GridLayout() );
        composite.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );

        // Label Group
        Group labelGroup = new Group( composite, SWT.NONE );
        labelGroup.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false ) );
        labelGroup.setText( Messages.getString( "SchemaViewPreferencePage.Label" ) ); //$NON-NLS-1$
        labelGroup.setLayout( new GridLayout() );
        Composite labelGroupComposite = new Composite( labelGroup, SWT.NONE );
        GridLayout gl = new GridLayout( 1, false );
        gl.marginHeight = gl.marginWidth = 0;
        labelGroupComposite.setLayout( gl );
        labelGroupComposite.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false ) );

        // Label row composite
        Composite labelComposite = new Composite( labelGroupComposite, SWT.NONE );
        gl = new GridLayout( 3, false );
        gl.marginHeight = gl.marginWidth = 0;
        labelComposite.setLayout( gl );
        GridData gd = new GridData( SWT.FILL, SWT.NONE, true, false );
        gd.horizontalSpan = 1;
        labelComposite.setLayoutData( gd );

        // Use Label
        Label useLabel = new Label( labelComposite, SWT.NONE );
        useLabel.setText( Messages.getString( "SchemaViewPreferencePage.Use" ) ); //$NON-NLS-1$

        // Label Combo
        labelCombo = new Combo( labelComposite, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER );
        labelCombo.setLayoutData( new GridData() );
        labelCombo
            .setItems( new String[]
                {
                    Messages.getString( "SchemaViewPreferencePage.FirstName" ), Messages.getString( "SchemaViewPreferencePage.AllAliases" ), Messages.getString( "SchemaViewPreferencePage.OID" ) } ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        labelCombo.setEnabled( true );

        // As label Label
        Label asLabel = new Label( labelComposite, SWT.NONE );
        asLabel.setText( Messages.getString( "SchemaViewPreferencePage.AsLabel" ) ); //$NON-NLS-1$

        // Abbreviate row composite
        Composite abbreviateComposite = new Composite( labelGroupComposite, SWT.NONE );
        gl = new GridLayout( 3, false );
        gl.marginHeight = gl.marginWidth = 0;
        abbreviateComposite.setLayout( gl );
        gd = new GridData( SWT.FILL, SWT.NONE, true, false );
        gd.horizontalSpan = 1;
        abbreviateComposite.setLayoutData( gd );

        // Limit label lenght to Label
        limitButton = new Button( abbreviateComposite, SWT.CHECK );
        limitButton.setText( Messages.getString( "SchemaViewPreferencePage.LimitLabel" ) ); //$NON-NLS-1$
        gd = new GridData();
        gd.horizontalSpan = 1;
        limitButton.setLayoutData( gd );

        // Lenght Text
        lengthText = new Text( abbreviateComposite, SWT.NONE | SWT.BORDER );
        GridData gridData = new GridData();
        gridData.horizontalSpan = 1;
        gridData.widthHint = 9 * 3;
        lengthText.setLayoutData( gridData );
        lengthText.setTextLimit( 3 );
        lengthText.addVerifyListener( new VerifyListener()
        {
            public void verifyText( VerifyEvent e )
            {
                if ( !e.text.matches( "[0-9]*" ) ) //$NON-NLS-1$
                {
                    e.doit = false;
                }
                if ( "".equals( lengthText.getText() ) && e.text.matches( "[0]" ) ) //$NON-NLS-1$ //$NON-NLS-2$
                {
                    e.doit = false;
                }
            }
        } );

        // Characters Label
        Label charactersLabel = new Label( abbreviateComposite, SWT.NONE );
        charactersLabel.setText( Messages.getString( "SchemaViewPreferencePage.Characters" ) ); //$NON-NLS-1$

        // Secondary Label Group
        Group secondaryLabelGroup = new Group( composite, SWT.NONE );
        secondaryLabelGroup.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false ) );
        secondaryLabelGroup.setText( Messages.getString( "SchemaViewPreferencePage.SecondaryLabel" ) ); //$NON-NLS-1$
        secondaryLabelGroup.setLayout( new GridLayout() );
        Composite secondaryLabelGroupComposite = new Composite( secondaryLabelGroup, SWT.NONE );
        gl = new GridLayout( 1, false );
        gl.marginHeight = gl.marginWidth = 0;
        secondaryLabelGroupComposite.setLayout( gl );
        secondaryLabelGroupComposite.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false ) );

        secondaryLabelButtonDisplay = new Button( secondaryLabelGroupComposite, SWT.CHECK );
        secondaryLabelButtonDisplay.setText( Messages.getString( "SchemaViewPreferencePage.DisplaySecondaryLabel" ) ); //$NON-NLS-1$

        // Label row composite
        Composite secondaryLabelComposite = new Composite( secondaryLabelGroupComposite, SWT.NONE );
        gl = new GridLayout( 3, false );
        gl.marginHeight = gl.marginWidth = 0;
        secondaryLabelComposite.setLayout( gl );
        gd = new GridData( SWT.FILL, SWT.NONE, true, false );
        gd.horizontalSpan = 1;
        secondaryLabelComposite.setLayoutData( gd );

        // Use Label
        Label useLabel2 = new Label( secondaryLabelComposite, SWT.NONE );
        useLabel2.setText( Messages.getString( "SchemaViewPreferencePage.Use" ) ); //$NON-NLS-1$

        // Label Combo
        secondaryLabelCombo = new Combo( secondaryLabelComposite, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER );
        secondaryLabelCombo.setLayoutData( new GridData() );
        secondaryLabelCombo
            .setItems( new String[]
                {
                    Messages.getString( "SchemaViewPreferencePage.FirstName" ), Messages.getString( "SchemaViewPreferencePage.AllAliases" ), Messages.getString( "SchemaViewPreferencePage.OID" ) } ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        secondaryLabelCombo.setEnabled( true );

        // As label Label
        Label asLabel2 = new Label( secondaryLabelComposite, SWT.NONE );
        asLabel2.setText( Messages.getString( "SchemaViewPreferencePage.AsSecondaryLabel" ) ); //$NON-NLS-1$

        // Abbreviate row composite
        Composite abbreviateComposite2 = new Composite( secondaryLabelGroup, SWT.NONE );
        gl = new GridLayout( 3, false );
        gl.marginHeight = gl.marginWidth = 0;
        abbreviateComposite2.setLayout( gl );
        gd = new GridData( SWT.FILL, SWT.NONE, true, false );
        gd.horizontalSpan = 1;
        abbreviateComposite2.setLayoutData( gd );

        // Limit label lenght to Label
        secondaryLabelLimitButton = new Button( abbreviateComposite2, SWT.CHECK );
        secondaryLabelLimitButton.setText( Messages.getString( "SchemaViewPreferencePage.LimitSecondaryLabel" ) ); //$NON-NLS-1$
        gd = new GridData();
        gd.horizontalSpan = 1;
        secondaryLabelLimitButton.setLayoutData( gd );

        // Lenght Text
        secondaryLabelLengthText = new Text( abbreviateComposite2, SWT.NONE | SWT.BORDER );
        gridData = new GridData();
        gridData.horizontalSpan = 1;
        gridData.widthHint = 9 * 3;
        secondaryLabelLengthText.setLayoutData( gridData );
        secondaryLabelLengthText.setTextLimit( 3 );
        secondaryLabelLengthText.addVerifyListener( new VerifyListener()
        {
            public void verifyText( VerifyEvent e )
            {
                if ( !e.text.matches( "[0-9]*" ) ) //$NON-NLS-1$
                {
                    e.doit = false;
                }
                if ( "".equals( secondaryLabelLengthText.getText() ) && e.text.matches( "[0]" ) ) //$NON-NLS-1$ //$NON-NLS-2$
                {
                    e.doit = false;
                }
            }
        } );

        // Schema Label Group
        Group schemaLabelGroup = new Group( composite, SWT.NONE );
        schemaLabelGroup.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false ) );
        schemaLabelGroup.setText( Messages.getString( "SchemaViewPreferencePage.SchemaLabel" ) ); //$NON-NLS-1$
        schemaLabelGroup.setLayout( new GridLayout() );
        Composite schemaLabelGroupComposite = new Composite( schemaLabelGroup, SWT.NONE );
        gl = new GridLayout( 1, false );
        gl.marginHeight = gl.marginWidth = 0;
        schemaLabelGroupComposite.setLayout( gl );
        schemaLabelGroupComposite.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false ) );

        schemaLabelButtonDisplay = new Button( schemaLabelGroupComposite, SWT.CHECK );
        schemaLabelButtonDisplay.setText( Messages.getString( "SchemaViewPreferencePage.DisplaySchemaLabel" ) ); //$NON-NLS-1$

        // Characters Label
        Label secondaryLabelcharactersLabel = new Label( abbreviateComposite2, SWT.NONE );
        secondaryLabelcharactersLabel.setText( Messages.getString( "SchemaViewPreferencePage.Characters" ) ); //$NON-NLS-1$

        initFieldsFromPreferences();

        initListeners();

        applyDialogFont( parent );

        return parent;
    }


    /**
     * Initializes the fields from the preferences store.
     */
    private void initFieldsFromPreferences()
    {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();

        labelCombo.select( store.getInt( PluginConstants.PREFS_SCHEMA_VIEW_LABEL ) );
        limitButton.setSelection( store.getBoolean( PluginConstants.PREFS_SCHEMA_VIEW_ABBREVIATE ) );
        lengthText.setEnabled( limitButton.getSelection() );
        lengthText.setText( store.getString( PluginConstants.PREFS_SCHEMA_VIEW_ABBREVIATE_MAX_LENGTH ) );

        secondaryLabelButtonDisplay.setSelection( store
            .getBoolean( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_DISPLAY ) );
        secondaryLabelCombo.select( store.getInt( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL ) );
        secondaryLabelLimitButton.setSelection( store
            .getBoolean( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_ABBREVIATE ) );
        secondaryLabelLengthText.setText( store
            .getString( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_ABBREVIATE_MAX_LENGTH ) );
        if ( store.getBoolean( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_DISPLAY ) )
        {
            secondaryLabelCombo.setEnabled( true );
            secondaryLabelLimitButton.setEnabled( true );
            secondaryLabelLengthText.setEnabled( secondaryLabelLimitButton.getSelection() );
        }
        else
        {
            secondaryLabelCombo.setEnabled( false );
            secondaryLabelLimitButton.setEnabled( false );
            secondaryLabelLengthText.setEnabled( false );
        }

        schemaLabelButtonDisplay.setSelection( store
            .getBoolean( PluginConstants.PREFS_SCHEMA_VIEW_SCHEMA_LABEL_DISPLAY ) );
    }


    /**
     * Initializes the listeners.
     */
    private void initListeners()
    {
        limitButton.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( SelectionEvent e )
            {
                lengthText.setEnabled( limitButton.getSelection() );
            }
        } );

        secondaryLabelButtonDisplay.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( SelectionEvent e )
            {
                if ( secondaryLabelButtonDisplay.getSelection() )
                {
                    secondaryLabelCombo.setEnabled( true );
                    secondaryLabelLimitButton.setEnabled( true );
                    secondaryLabelLengthText.setEnabled( secondaryLabelLimitButton.getSelection() );
                }
                else
                {
                    secondaryLabelCombo.setEnabled( false );
                    secondaryLabelLimitButton.setEnabled( false );
                    secondaryLabelLengthText.setEnabled( false );
                }
            }
        } );

        secondaryLabelLimitButton.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( SelectionEvent e )
            {
                secondaryLabelLengthText.setEnabled( secondaryLabelLimitButton.getSelection() );
            }
        } );
    }


    /**
     * {@inheritDoc}
     */
    protected void performDefaults()
    {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();

        labelCombo.select( store.getDefaultInt( PluginConstants.PREFS_SCHEMA_VIEW_LABEL ) );
        limitButton.setSelection( store.getDefaultBoolean( PluginConstants.PREFS_SCHEMA_VIEW_ABBREVIATE ) );
        lengthText.setEnabled( limitButton.getSelection() );
        lengthText.setText( store.getDefaultString( PluginConstants.PREFS_SCHEMA_VIEW_ABBREVIATE_MAX_LENGTH ) );

        secondaryLabelButtonDisplay.setSelection( store
            .getDefaultBoolean( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_DISPLAY ) );
        secondaryLabelCombo.select( store.getDefaultInt( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL ) );
        secondaryLabelLimitButton.setSelection( store
            .getDefaultBoolean( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_ABBREVIATE ) );
        secondaryLabelLengthText.setText( store
            .getDefaultString( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_ABBREVIATE_MAX_LENGTH ) );

        if ( secondaryLabelButtonDisplay.getSelection() )
        {
            secondaryLabelCombo.setEnabled( true );
            secondaryLabelLimitButton.setEnabled( true );
            secondaryLabelLengthText.setEnabled( secondaryLabelLimitButton.getSelection() );
        }
        else
        {
            secondaryLabelCombo.setEnabled( false );
            secondaryLabelLimitButton.setEnabled( false );
            secondaryLabelLengthText.setEnabled( false );
        }

        schemaLabelButtonDisplay.setSelection( store
            .getDefaultBoolean( PluginConstants.PREFS_SCHEMA_VIEW_SCHEMA_LABEL_DISPLAY ) );

        super.performDefaults();
    }


    /**
     * {@inheritDoc}
     */
    public boolean performOk()
    {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();

        if ( labelCombo.getItem( labelCombo.getSelectionIndex() ).equals(
            Messages.getString( "SchemaViewPreferencePage.FirstName" ) ) ) //$NON-NLS-1$
        {
            store
                .setValue( PluginConstants.PREFS_SCHEMA_VIEW_LABEL, PluginConstants.PREFS_SCHEMA_VIEW_LABEL_FIRST_NAME );
        }
        else if ( labelCombo.getItem( labelCombo.getSelectionIndex() ).equals(
            Messages.getString( "SchemaViewPreferencePage.AllAliases" ) ) ) //$NON-NLS-1$
        {
            store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_LABEL,
                PluginConstants.PREFS_SCHEMA_VIEW_LABEL_ALL_ALIASES );
        }
        else if ( labelCombo.getItem( labelCombo.getSelectionIndex() ).equals(
            Messages.getString( "SchemaViewPreferencePage.OID" ) ) ) //$NON-NLS-1$
        {
            store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_LABEL, PluginConstants.PREFS_SCHEMA_VIEW_LABEL_OID );
        }
        store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_ABBREVIATE, limitButton.getSelection() );
        store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_ABBREVIATE_MAX_LENGTH, lengthText.getText() );

        store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_DISPLAY, secondaryLabelButtonDisplay
            .getSelection() );
        if ( secondaryLabelCombo.getItem( secondaryLabelCombo.getSelectionIndex() ).equals(
            Messages.getString( "SchemaViewPreferencePage.FirstName" ) ) ) //$NON-NLS-1$
        {
            store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL,
                PluginConstants.PREFS_SCHEMA_VIEW_LABEL_FIRST_NAME );
        }
        else if ( secondaryLabelCombo.getItem( secondaryLabelCombo.getSelectionIndex() ).equals(
            Messages.getString( "SchemaViewPreferencePage.AllAliases" ) ) ) //$NON-NLS-1$
        {
            store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL,
                PluginConstants.PREFS_SCHEMA_VIEW_LABEL_ALL_ALIASES );
        }
        else if ( secondaryLabelCombo.getItem( secondaryLabelCombo.getSelectionIndex() ).equals(
            Messages.getString( "SchemaViewPreferencePage.OID" ) ) ) //$NON-NLS-1$
        {
            store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL,
                PluginConstants.PREFS_SCHEMA_VIEW_LABEL_OID );
        }
        store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_ABBREVIATE, secondaryLabelLimitButton
            .getSelection() );
        store.setValue( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_ABBREVIATE_MAX_LENGTH,
            secondaryLabelLengthText.getText() );

        store
            .setValue( PluginConstants.PREFS_SCHEMA_VIEW_SCHEMA_LABEL_DISPLAY, schemaLabelButtonDisplay.getSelection() );

        return true;
    }


    /**
     * {@inheritDoc}
     */
    public void init( IWorkbench workbench )
    {
        // Nothing to do
    }
}
