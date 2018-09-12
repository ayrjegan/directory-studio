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
package org.apache.directory.studio.schemaeditor.view.wizards;


import org.apache.directory.studio.schemaeditor.Activator;
import org.apache.directory.studio.schemaeditor.PluginConstants;
import org.apache.directory.studio.schemaeditor.model.Project;
import org.apache.directory.studio.schemaeditor.model.difference.DifferenceEngine;
import org.apache.directory.studio.schemaeditor.view.widget.DifferencesWidget;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;


/**
 * This class represents the WizardPage of the ExportProjectsWizard.
 * <p>
 * It is used to let the user enter the informations about the
 * schemas projects he wants to export and where to export.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class CommitChangesDifferencesWizardPage extends WizardPage
{
    // UI Fields
    private DifferencesWidget differencesWidget;


    /**
     * Creates a new instance of ExportSchemasAsXmlWizardPage.
     */
    protected CommitChangesDifferencesWizardPage()
    {
        super( "CommitChangesDifferencesWizardPage" ); //$NON-NLS-1$
        setTitle( Messages.getString( "CommitChangesDifferencesWizardPage.CommitChanges" ) ); //$NON-NLS-1$
        setDescription( Messages.getString( "CommitChangesDifferencesWizardPage.DisplayModifications" ) ); //$NON-NLS-1$
        setImageDescriptor( Activator.getDefault().getImageDescriptor( PluginConstants.IMG_COMMIT_CHANGES_WIZARD ) );
    }


    /**
     * {@inheritDoc}
     */
    public void createControl( Composite parent )
    {
        Composite composite = new Composite( parent, SWT.NULL );
        GridLayout layout = new GridLayout();
        composite.setLayout( layout );

        differencesWidget = new DifferencesWidget();
        differencesWidget.createWidget( composite );

        initFields();

        setControl( composite );
    }


    /**
     * Initializes the UI Fields.
     */
    private void initFields()
    {
        Project project = Activator.getDefault().getProjectsHandler().getOpenProject();

        differencesWidget.setInput( DifferenceEngine.getDifferences( project.getInitialSchema(), project
            .getSchemaHandler().getSchemas() ) );
    }


    /**
     * {@inheritDoc}
     */
    public void dispose()
    {
        differencesWidget.dispose();

        super.dispose();
    }
}
