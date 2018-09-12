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
package org.apache.directory.studio.schemaeditor.view.widget;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.directory.studio.schemaeditor.Activator;
import org.apache.directory.studio.schemaeditor.PluginConstants;
import org.apache.directory.studio.schemaeditor.model.difference.SchemaDifference;
import org.apache.directory.studio.schemaeditor.view.widget.Folder.FolderType;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * This class implements the ContentProvider for the Difference Widget.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class DifferencesWidgetSchemaContentProvider implements IStructuredContentProvider, ITreeContentProvider
{
    /** The preferences store */
    private IPreferenceStore store;

    /** The FirstName Sorter */
    private FirstNameSorter firstNameSorter;

    /** The OID Sorter */
    private OidSorter oidSorter;

    /** The Schema Sorter */
    private SchemaDifferenceSorter schemaDifferenceSorter;


    /**
     * Creates a new instance of DifferencesWidgetSchemaContentProvider.
     */
    public DifferencesWidgetSchemaContentProvider()
    {
        store = Activator.getDefault().getPreferenceStore();

        firstNameSorter = new FirstNameSorter();
        oidSorter = new OidSorter();
        schemaDifferenceSorter = new SchemaDifferenceSorter();
    }


    /**
     * {@inheritDoc}
     */
    public Object[] getElements( Object inputElement )
    {
        return getChildren( inputElement );
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
    public void inputChanged( Viewer viewer, Object oldInput, Object newInput )
    {
        // Nothing to do
    }


    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Object[] getChildren( Object parentElement )
    {
        List<Object> children = new ArrayList<Object>();

        int group = store.getInt( PluginConstants.PREFS_SCHEMA_VIEW_GROUPING );
        int sortBy = store.getInt( PluginConstants.PREFS_SCHEMA_VIEW_SORTING_BY );
        int sortOrder = store.getInt( PluginConstants.PREFS_SCHEMA_VIEW_SORTING_ORDER );

        if ( parentElement instanceof List )
        {
            List<SchemaDifference> schemaDifferences = ( List<SchemaDifference> ) parentElement;

            children.addAll( schemaDifferences );

            Collections.sort( children, schemaDifferenceSorter );
        }
        else if ( parentElement instanceof SchemaDifference )
        {
            SchemaDifference difference = ( SchemaDifference ) parentElement;

            if ( group == PluginConstants.PREFS_SCHEMA_VIEW_GROUPING_FOLDERS )
            {
                Folder atFolder = new Folder( FolderType.ATTRIBUTE_TYPE );
                atFolder.addAllChildren( difference.getAttributeTypesDifferences() );
                children.add( atFolder );

                Folder ocFolder = new Folder( FolderType.OBJECT_CLASS );
                ocFolder.addAllChildren( difference.getObjectClassesDifferences() );
                children.add( ocFolder );
            }
            else if ( group == PluginConstants.PREFS_SCHEMA_VIEW_GROUPING_MIXED )
            {
                children.addAll( difference.getAttributeTypesDifferences() );
                children.addAll( difference.getObjectClassesDifferences() );

                // Sort by
                if ( sortBy == PluginConstants.PREFS_SCHEMA_VIEW_SORTING_BY_FIRSTNAME )
                {
                    Collections.sort( children, firstNameSorter );
                }
                else if ( sortBy == PluginConstants.PREFS_SCHEMA_VIEW_SORTING_BY_OID )
                {
                    Collections.sort( children, oidSorter );
                }

                // Sort Order
                if ( sortOrder == PluginConstants.PREFS_SCHEMA_VIEW_SORTING_ORDER_DESCENDING )
                {
                    Collections.reverse( children );
                }
            }
        }
        else if ( parentElement instanceof Folder )
        {
            children.addAll( ( ( Folder ) parentElement ).getChildren() );

            // Sort by
            if ( sortBy == PluginConstants.PREFS_SCHEMA_VIEW_SORTING_BY_FIRSTNAME )
            {
                Collections.sort( children, firstNameSorter );
            }
            else if ( sortBy == PluginConstants.PREFS_SCHEMA_VIEW_SORTING_BY_OID )
            {
                Collections.sort( children, oidSorter );
            }

            // Sort Order
            if ( sortOrder == PluginConstants.PREFS_SCHEMA_VIEW_SORTING_ORDER_DESCENDING )
            {
                Collections.reverse( children );
            }
        }

        return children.toArray();
    }


    /**
     * {@inheritDoc}
     */
    public Object getParent( Object element )
    {
        // Default
        return null;
    }


    /**
     * {@inheritDoc}
     */
    public boolean hasChildren( Object element )
    {
        if ( element instanceof SchemaDifference )
        {
            return true;
        }
        else if ( element instanceof Folder )
        {
            return true;
        }

        // Default
        return false;
    }
}
