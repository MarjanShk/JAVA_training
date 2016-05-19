package tests;

import model.Issue;
import model.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by Admin on 24.04.2016.
 */
public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void createIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withDescription("Test description").withSummary("Test summary").
                withProject(projects.iterator().next());
        Issue createdIssue = app.soap().addIssue(issue);

        assertEquals(issue.getSummary(), createdIssue.getSummary());
        assertEquals(issue.getProject(), createdIssue.getProject());

    }
}
