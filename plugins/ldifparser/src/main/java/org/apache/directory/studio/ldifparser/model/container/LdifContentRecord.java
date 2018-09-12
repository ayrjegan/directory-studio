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

package org.apache.directory.studio.ldifparser.model.container;


import java.util.ArrayList;
import java.util.List;

import org.apache.directory.studio.ldifparser.model.LdifPart;
import org.apache.directory.studio.ldifparser.model.lines.LdifAttrValLine;
import org.apache.directory.studio.ldifparser.model.lines.LdifDnLine;


/**
 * A LDIF container for content records
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class LdifContentRecord extends LdifRecord
{
    public LdifContentRecord( LdifDnLine dn )
    {
        super( dn );
    }


    public void addAttrVal( LdifAttrValLine attrVal )
    {
        if ( attrVal == null )
        {
            throw new IllegalArgumentException( "null argument" ); //$NON-NLS-1$
        }

        ldifParts.add( attrVal );
    }


    public LdifAttrValLine[] getAttrVals()
    {
        List<LdifAttrValLine> ldifAttrValLines = new ArrayList<LdifAttrValLine>();

        for ( LdifPart ldifPart : ldifParts )
        {
            if ( ldifPart instanceof LdifAttrValLine )
            {
                ldifAttrValLines.add( ( LdifAttrValLine ) ldifPart );
            }
        }

        return ldifAttrValLines.toArray( new LdifAttrValLine[ldifAttrValLines.size()] );
    }


    private int getSize()
    {
        int size = 0;

        for ( LdifPart ldifPart : ldifParts )
        {
            if ( ldifPart instanceof LdifAttrValLine )
            {
                size++;
            }
        }

        return size;
    }


    public static LdifContentRecord create( String dn )
    {
        return new LdifContentRecord( LdifDnLine.create( dn ) );
    }


    public boolean isValid()
    {
        if ( !super.isAbstractValid() )
        {
            return false;
        }

        return getSize() > 0;

    }


    public String getInvalidString()
    {
        if ( getSize() > 0 )
        {
            return super.getInvalidString();
        }
        else
        {
            return "Record must contain attribute value lines";
        }
    }
}
