package mantis.tests;

import mantis.models.Issue;
import mantis.models.Project;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {
    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project: projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Project project = projects.iterator().next();
        Issue issue = new Issue().withSummary("test issue"
        ).withDescription("Some strange happened").withProject(project);
        Issue created = app.soap().addIssue(issue);
        System.out.println(created.getId());
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
