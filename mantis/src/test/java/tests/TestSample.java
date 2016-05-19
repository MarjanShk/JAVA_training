package tests;

import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by Admin on 24.04.2016.
 */
public class TestSample extends TestBase {
    @Test
    public void getStatus() throws RemoteException, ServiceException, MalformedURLException {
        System.out.println(app.soap().isIssueOpen(9));
    }
}
