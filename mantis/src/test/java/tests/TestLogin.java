package tests;

import appmanager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Admin on 21.04.2016.
 */
public class TestLogin extends TestBase {
    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login("administrator", "root"));
        Assert.assertTrue(session.isLoggedAs("administrator"));
    }

}
