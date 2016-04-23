package tests;

import model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 23.04.2016.
 */
public class ChangePasswordTest extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        String pass = "new_password";
        app.getSessionHelper().login("administrator", "root");
        app.manageUsers().changePassword();
        String userEmail = app.manageUsers().userEmail;
        String userName = app.manageUsers().userName;
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, userEmail);
        app.registration().finish(confirmationLink, pass);
        Assert.assertTrue(app.newSession().login(userName, pass));

        System.out.println(userName);
        System.out.println(pass);
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
