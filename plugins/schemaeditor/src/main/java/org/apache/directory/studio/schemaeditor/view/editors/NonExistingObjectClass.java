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
package org.apache.directory.studio.schemaeditor.view.editors;


/**
 * This class implements the Non Existing Object Class.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class NonExistingObjectClass
{
    public static final String NONE = "(None)"; //$NON-NLS-1$

    /** The name */
    private String name;


    /**
     * Creates a new instance of NonExistingObjectClass.
     *
     * @param name
     *      the name the NonExistingObjectClass
     */
    public NonExistingObjectClass( String name )
    {
        this.name = name;
    }


    /**
     * Gets the name of the NonExistingObjectClass.
     *
     * @return
     *      the name of the NonExistingObjectClass
     */
    public String getName()
    {
        return name;
    }


    /**
     * Gets the displayable name of the NonExistingObjectClass.
     *
     * @return
     *      the displayable name of the NonExistingObjectClass
     */
    public String getDisplayName()
    {
        if ( name.equals( NONE ) )
        {
            return NONE;
        }
        else
        {
            return name + "   " + "(This object class doesnt exist)";
        }
    }


    /**
     * {@inheritDoc}
     */
    public boolean equals( Object obj )
    {
        if ( obj instanceof NonExistingObjectClass )
        {
            return name.equalsIgnoreCase( ( ( NonExistingObjectClass ) obj ).getName() );
        }

        return false;
    }
}
