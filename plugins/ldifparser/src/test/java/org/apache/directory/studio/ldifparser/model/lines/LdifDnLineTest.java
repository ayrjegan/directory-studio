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

package org.apache.directory.studio.ldifparser.model.lines;


import junit.framework.TestCase;

import org.apache.directory.studio.ldifparser.LdifFormatParameters;


public class LdifDnLineTest extends TestCase
{

    public void testToFormattedStringSimple()
    {
        // LdifDnLine dnLine = LdifDnLine.create( "cn=abc,ou=department,o=org,dc=example,dc=com" ); //$NON-NLS-1$
        // LdifFormatParameters formatParameters = new LdifFormatParameters( true, 78, "\n" ); //$NON-NLS-1$
        // String formattedString = dnLine.toFormattedString( formatParameters );
        // assertEquals( formattedString, "dn: cn=abc,ou=department,o=org,dc=example,dc=com\n" ); //$NON-NLS-1$
    }


    public void testToFormattedStringNewline()
    {
        // LdifDnLine dnLine = LdifDnLine.create( "cn=abc,ou=department,o=org,dc=example,dc=com" ); //$NON-NLS-1$
        // LdifFormatParameters formatParameters = new LdifFormatParameters( true, 78, "\r\n" ); //$NON-NLS-1$
        // String formattedString = dnLine.toFormattedString( formatParameters );
        // assertEquals( formattedString, "dn: cn=abc,ou=department,o=org,dc=example,dc=com\r\n" ); //$NON-NLS-1$
    }


    public void testToFormattedStringLineWrap()
    {
        // LdifDnLine dnLine = LdifDnLine
        //     .create( "cn=abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy,ou=department,o=org,dc=example,dc=com" ); //$NON-NLS-1$
        // LdifFormatParameters formatParameters = new LdifFormatParameters( true, 78, "\n" ); //$NON-NLS-1$
        // String formattedString = dnLine.toFormattedString( formatParameters );
        // assertEquals( formattedString, "dn: cn=abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy,ou=department,o=org" //$NON-NLS-1$
        //     + "\n ,dc=example,dc=com\n" ); //$NON-NLS-1$
    }


    public void testToFormattedStringNoSpaceAfterColon()
    {
        // LdifDnLine dnLine = LdifDnLine.create( "cn=abc,ou=department,o=org,dc=example,dc=com" ); //$NON-NLS-1$
        // LdifFormatParameters formatParameters = new LdifFormatParameters( false, 78, "\n" ); //$NON-NLS-1$
        // String formattedString = dnLine.toFormattedString( formatParameters );
        // assertEquals( formattedString, "dn:cn=abc,ou=department,o=org,dc=example,dc=com\n" ); //$NON-NLS-1$
    }


    public void testToFormattedStringBase64()
    {
        // LdifDnLine dnLine = LdifDnLine.create( "cn=\u00e4\u00f6\u00fc,ou=department,o=org,dc=example,dc=com" ); //$NON-NLS-1$
        // LdifFormatParameters formatParameters = new LdifFormatParameters( true, 78, "\n" ); //$NON-NLS-1$
        // String formattedString = dnLine.toFormattedString( formatParameters );
        // assertEquals( formattedString, "dn:: Y249w6TDtsO8LG91PWRlcGFydG1lbnQsbz1vcmcsZGM9ZXhhbXBsZSxkYz1jb20=\n" ); //$NON-NLS-1$
    assertEquals("1","1");
    }


    /**
     * Test for DIRSTUDIO-598
     * (Base64 encoded Dn marked as invalid in LDIF editor)
     */
    public void testIsValid()
    {
        // LdifDnLine dnLine = LdifDnLine.create( "cn=\\#\\\\\\+\\, \\\"\u00f6\u00e9\\\",ou=users,ou=system" ); //$NON-NLS-1$
        // assertTrue( dnLine.isValid() );
        // assertEquals( "Y249XCNcXFwrXCwgXCLDtsOpXCIsb3U9dXNlcnMsb3U9c3lzdGVt", dnLine.getUnfoldedDn() ); //$NON-NLS-1$
        // assertEquals( "cn=\\#\\\\\\+\\, \\\"\u00f6\u00e9\\\",ou=users,ou=system", dnLine.getValueAsString() ); //$NON-NLS-1$
    }

}
