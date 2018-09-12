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

package org.apache.directory.studio.ldapbrowser.ui.editors.searchresult;


import org.apache.directory.studio.common.ui.widgets.BaseWidgetUtils;
import org.apache.directory.studio.ldapbrowser.common.BrowserCommonActivator;
import org.apache.directory.studio.ldapbrowser.common.BrowserCommonConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;


/**
 * The SearchResultEditorQuickFilterWidget implements an instant search 
 * for the search result edtior. It contains one fields for all displayed values.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class SearchResultEditorQuickFilterWidget
{

    /** The filter. */
    private SearchResultEditorFilter filter;

    /** The parent. */
    private Composite parent;

    /** The composite. */
    private Composite composite;

    /** The inner composite. */
    private Composite innerComposite;

    /** The quick filter value text. */
    private Text quickFilterValueText;

    /** The clear quick filter button. */
    private Button clearQuickFilterButton;


    /**
     * Creates a new instance of SearchResultEditorQuickFilterWidget.
     * 
     * @param filter the filter
     */
    public SearchResultEditorQuickFilterWidget( SearchResultEditorFilter filter )
    {
        this.filter = filter;
    }


    /**
     * Creates the outer composite.
     * 
     * @param parent the parent
     */
    public void createComposite( Composite parent )
    {
        this.parent = parent;

        composite = BaseWidgetUtils.createColumnContainer( parent, 1, 1 );
        GridLayout gl = new GridLayout();
        gl.marginHeight = 2;
        gl.marginWidth = 2;
        composite.setLayout( gl );
        // Setting the default width and height of the composite to 0
        GridData compositeGridData = new GridData( SWT.NONE, SWT.NONE, false, false );
        compositeGridData.heightHint = 0;
        compositeGridData.widthHint = 0;
        composite.setLayoutData( compositeGridData );

        innerComposite = null;
    }


    /**
     * Creates the inner composite with its input fields.
     */
    private void create()
    {
        // Reseting the layout of the composite to be displayed correctly
        GridData compositeGridData = new GridData( SWT.FILL, SWT.NONE, true, false );
        composite.setLayoutData( compositeGridData );

        innerComposite = BaseWidgetUtils.createColumnContainer( this.composite, 2, 1 );

        quickFilterValueText = new Text( innerComposite, SWT.BORDER );
        quickFilterValueText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        quickFilterValueText.addModifyListener( new ModifyListener()
        {
            public void modifyText( ModifyEvent e )
            {
                filter.setQuickFilterValue( quickFilterValueText.getText() );
                clearQuickFilterButton.setEnabled( !"".equals( quickFilterValueText.getText() ) ); //$NON-NLS-1$
            }
        } );

        clearQuickFilterButton = new Button( innerComposite, SWT.PUSH );
        clearQuickFilterButton.setToolTipText( Messages
            .getString( "SearchResultEditorQuickFilterWidget.ClearQuickFilterToolTip" ) ); //$NON-NLS-1$
        clearQuickFilterButton.setImage( BrowserCommonActivator.getDefault()
            .getImage( BrowserCommonConstants.IMG_CLEAR ) );
        clearQuickFilterButton.setEnabled( false );
        clearQuickFilterButton.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( SelectionEvent e )
            {
                if ( !"".equals( quickFilterValueText.getText() ) ) //$NON-NLS-1$
                    quickFilterValueText.setText( "" ); //$NON-NLS-1$
            }
        } );

        setEnabled( composite.isEnabled() );

        composite.layout( true, true );
        parent.layout( true, true );
    }


    /**
     * Destroys the inner widget.
     */
    private void destroy()
    {
        // Reseting the layout of the composite with a width and height set to 0
        GridData compositeGridData = new GridData( SWT.NONE, SWT.NONE, false, false );
        compositeGridData.heightHint = 0;
        compositeGridData.widthHint = 0;
        composite.setLayoutData( compositeGridData );

        if ( !"".equals( quickFilterValueText.getText() ) ) //$NON-NLS-1$
        {
            quickFilterValueText.setText( "" ); //$NON-NLS-1$
        }
        innerComposite.dispose();
        innerComposite = null;

        composite.layout( true, true );
        parent.layout( true, true );
    }


    /**
     * Disposes this widget.
     */
    public void dispose()
    {
        if ( innerComposite != null && !innerComposite.isDisposed() )
        {
            quickFilterValueText.dispose();
            quickFilterValueText = null;
            clearQuickFilterButton = null;
            innerComposite = null;
        }
        if ( filter != null )
        {
            composite.dispose();
            composite = null;
            parent = null;
            filter = null;
        }
    }


    /**
     * Enables or disables this quick filter widget.
     * 
     * @param enabled true to enable this quick filter widget, false to disable it
     */
    public void setEnabled( boolean enabled )
    {
        if ( composite != null && !composite.isDisposed() )
        {
            composite.setEnabled( enabled );
        }
        if ( innerComposite != null && !innerComposite.isDisposed() )
        {
            innerComposite.setEnabled( enabled );
            quickFilterValueText.setEnabled( enabled );
            clearQuickFilterButton.setEnabled( enabled );
        }
    }


    /**
     * Activates or deactivates this quick filter widget.
     *
     * @param visible true to create this quick filter widget, false to destroy it
     */
    public void setActive( boolean visible )
    {
        if ( visible && innerComposite == null && composite != null )
        {
            create();
            this.quickFilterValueText.setFocus();
        }
        else if ( !visible && innerComposite != null && composite != null )
        {
            destroy();
        }
    }

}
