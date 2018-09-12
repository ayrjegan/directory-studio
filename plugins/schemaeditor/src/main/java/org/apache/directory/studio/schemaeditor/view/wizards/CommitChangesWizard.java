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
import org.apache.directory.studio.schemaeditor.model.DependenciesComputer;
import org.apache.directory.studio.schemaeditor.model.DependenciesComputer.DependencyComputerException;
import org.apache.directory.studio.schemaeditor.model.Project;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;


/**
 * This class represents the wizard to commit changes to the Apache Directory Server.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class CommitChangesWizard extends Wizard implements IExportWizard
{
    /** The flag to know if the Schema contains errors */
    private boolean schemaContainsErrors = false;

    /** The project */
    private Project project;

    /** The DependenciesComputer */
    private DependenciesComputer dependenciesComputer;

    // The pages of the wizard
    private CommitChangesInformationWizardPage commitChangesInformation;
    private CommitChangesDifferencesWizardPage commitChangesDifferences;


    /**
     * {@inheritDoc}
     */
    public void addPages()
    {
        // Creating pages
        commitChangesInformation = new CommitChangesInformationWizardPage();
        commitChangesDifferences = new CommitChangesDifferencesWizardPage();

        // Adding pages
        addPage( commitChangesInformation );
        addPage( commitChangesDifferences );
    }


    /**
     * {@inheritDoc}
     */
    public boolean performFinish()
    {
        //        final List<Schema> orderedSchemas = dependenciesComputer.getDependencyOrderedSchemasList();
        //
        //        try
        //        {
        //            getContainer().run( false, false, new IRunnableWithProgress()
        //            {
        //                public void run( IProgressMonitor monitor )
        //                {
        //                    //TODO implement
        //                }
        //            } );
        //        }
        //        catch ( InvocationTargetException e )
        //        {
        //            // Nothing to do (it will never occur)
        //        }
        //        catch ( InterruptedException e )
        //        {
        //            // Nothing to do.
        //        }

        return true;
    }


    /**
     * {@inheritDoc}
     */
    public boolean canFinish()
    {
        if ( schemaContainsErrors )
        {
            return false;
        }
        else
        {
            return ( getContainer().getCurrentPage() instanceof CommitChangesDifferencesWizardPage );
        }
    }


    /**
     * {@inheritDoc}
     */
    public void init( IWorkbench workbench, IStructuredSelection selection )
    {
        setNeedsProgressMonitor( true );

        project = Activator.getDefault().getProjectsHandler().getOpenProject();

        try
        {
            dependenciesComputer = new DependenciesComputer( project.getSchemaHandler().getSchemas() );
        }
        catch ( DependencyComputerException e )
        {
            schemaContainsErrors = true;
        }
    }


    /**
     * Gets the SchemaContainsErrors flag.
     *
     * @return
     *      the SchemaContainsErrors flag
     */
    public boolean isSchemaContainsErrors()
    {
        return schemaContainsErrors;
    }


    /**
     * Gets the DependenciesComputer.
     *
     * @return
     *      the DependenciesComputer
     */
    public DependenciesComputer getDependenciesComputer()
    {
        return dependenciesComputer;
    }
}
