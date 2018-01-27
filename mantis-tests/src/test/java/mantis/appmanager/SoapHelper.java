package mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import mantis.models.Issue;
import mantis.models.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }
    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"));
        return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue())
                .withName(p.getName())).collect(Collectors.toSet());
    }

    public IssueData getIssue(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String user = app.getProperty("web.adminLogin");
        String pass = app.getProperty("web.adminPassword");
        IssueData issue = mc.mc_issue_get(user, pass, BigInteger.valueOf(id));
        return issue;
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new
                URL(app.getProperty("web.soapUrl")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        String user = app.getProperty("web.adminLogin");
        String pass = app.getProperty("web.adminPassword");
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(user, pass, BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),
                issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(user, pass, issueData);
        IssueData newIssueData = mc.mc_issue_get(user, pass, issueId);
        return new Issue().withId(newIssueData.getId().intValue())
                .withDescription(newIssueData.getDescription())
                .withSummary(newIssueData.getSummary())
                .withProject(new Project().withId(newIssueData.getProject().getId().intValue())
                        .withName(newIssueData.getProject().getName()));
    }
}
