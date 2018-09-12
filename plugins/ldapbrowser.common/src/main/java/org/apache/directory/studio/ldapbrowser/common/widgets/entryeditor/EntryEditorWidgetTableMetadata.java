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


/**
 * The EntryEditorWidgetTableMetadata class contains some constants used
 * by the entry editor widget.
 * Final reference -> class shouldn't be extended
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public final class EntryEditorWidgetTableMetadata
{

    /**
     *  Ensures no construction of this class, also ensures there is no need for final keyword above
     *  (Implicit super constructor is not visible for default constructor),
     *  but is still self documenting.
     */
    private EntryEditorWidgetTableMetadata()
    {
    }

    /** The Constant KEY_COLUMN_INDEX. */
    public static final int KEY_COLUMN_INDEX = 0;

    /** The Constant VALUE_COLUMN_INDEX. */
    public static final int VALUE_COLUMN_INDEX = 1;

    /** The Constant KEY_COLUMN_NAME. */
    public static final String KEY_COLUMN_NAME = Messages
        .getString( "EntryEditorWidgetTableMetadata.AttributeDescription" ); //$NON-NLS-1$

    /** The Constant VALUE_COLUMN_NAME. */
    public static final String VALUE_COLUMN_NAME = Messages.getString( "EntryEditorWidgetTableMetadata.Value" ); //$NON-NLS-1$

    /** The Constant COLUM_NAMES. */
    public static final String[] COLUM_NAMES =
        { KEY_COLUMN_NAME, VALUE_COLUMN_NAME };

}
