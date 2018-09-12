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
package org.apache.directory.studio.test.integration.ui.bots;


public class SearchDialogBot extends DialogBot
{
    SearchPageWrapperBot searchPageWrapperBot;


    public SearchDialogBot()
    {
        super( "Search" );
        this.searchPageWrapperBot = new SearchPageWrapperBot( bot );
    }


    public boolean isVisible()
    {
        return super.isVisible() && searchPageWrapperBot.isVisible();
    }


    public void setSearchName( String string )
    {
        searchPageWrapperBot.setSearchName( string );
    }


    public void setFilter( String string )
    {
        searchPageWrapperBot.setFilter( string );
    }


    public void setReturningAttributes( String string )
    {
        searchPageWrapperBot.setReturningAttributes( string );
    }


    public void clickSearchButton()
    {
        super.clickButton( "Search" );
    }

}
