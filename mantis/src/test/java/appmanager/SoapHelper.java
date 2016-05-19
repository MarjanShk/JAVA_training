package appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import model.Issue;
import model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Admin on 24.04.2016.
 */
public class SoapHelper {
    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {

        this.app = app;
    }

    public MantisConnectPortType getMantisConnectPort(String port) throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().
                getMantisConnectPort(new URL(app.getProperty(port)));
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnectPort("connectPort");
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projects).stream().map((p) -> new Project().
                withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }


    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnectPort("connectPort");
        IssueData issueData = new IssueData();
        String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
        IssueData createdIssue = mc.mc_issue_get("administrator", "root", issueId);
        return new Issue().withId(createdIssue.getId().intValue()).withSummary(createdIssue.getSummary()).
                withDescription(createdIssue.getDescription()).
                withProject(new Project().withId(createdIssue.getProject().getId().intValue()).
                        withName(createdIssue.getProject().getName()));
    }

    public boolean isIssueOpen(int issue_id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnectPort("connectPort");
        Set<Project> projects = getProjects();
        BigInteger id = mc.mc_issue_get_biggest_id("administrator", "root", BigInteger.valueOf(projects.iterator().next().getId()));
        IssueData bug = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issue_id));
        return !Objects.equals(bug.getStatus().getName(), "resolved") ? false : true;
    }
}
