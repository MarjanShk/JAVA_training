package tests;

import appmanager.ApplicationManager;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import model.Issue;
import model.Project;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Admin on 26.02.2016.
 */
public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));///BrowserType.FIREFOX

    public  boolean isIssueOpen(int issue_id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnectPort("connectPort");
        IssueData bug = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issue_id));
        return !Objects.equals(bug.getStatus().getName(), "resolved") ? true : false;
    }

    public  void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @BeforeSuite()
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_incXX.php");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php", "config_incXX.php");
        app.stop();
    }


}