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


import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.widgets.Composite;


/**
 * Implementation of a SourceViewer for displaying Schema files source code.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class SchemaSourceViewer extends SourceViewer
{
    /**
     * Creates a new instance of SchemaSourceViewer.
     *
     * @param parent
     *      the parent of the viewer's control
     * @param verticalRuler
     *      the vertical ruler used by this source viewer
     * @param overviewRuler
     *      the overview ruler
     * @param showAnnotationsOverview
     *      true if the overview ruler should be visible, false otherwise
     * @param styles
     *      the SWT style bits
     */
    public SchemaSourceViewer( Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
        boolean showAnnotationsOverview, int styles )
    {
        super( parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles );
        this.configure( new SchemaSourceViewerConfiguration() );
    }


    /**
     * Creates a new instance of SchemaSourceViewer.
     *
     * @param parent
     *      the parent of the viewer's control
     * @param ruler
     *      the vertical ruler used by this source viewer
     * @param styles
     *      the SWT style bits
     */
    public SchemaSourceViewer( Composite parent, IVerticalRuler ruler, int styles )
    {
        super( parent, ruler, styles );
        this.configure( new SchemaSourceViewerConfiguration() );
    }
}
