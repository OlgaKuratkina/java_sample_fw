package net.olga.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import net.olga.mantis.models.Issue;
import net.olga.mantis.models.Project;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTestsSkippinLogic extends TestBase{
    @BeforeMethod
    public void skip() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(2);
    }

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project: projects) {
            System.out.println(project.getName());
        }
        app.soap().getIssue(3);

    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Project project = projects.iterator().next();
        Issue issue = new Issue().withSummary("test issue"
        ).withDescription("Some strange happened").withProject(project);
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
