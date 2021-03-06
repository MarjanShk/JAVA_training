package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Admin on 26.02.2016.
 */
@Listeners(MyTestlistener.class)
public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));///BrowserType.FIREFOX

    @BeforeSuite
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app", app);
    }

    @AfterSuite(alwaysRun = true) //TODO: check
    public void tearDown() {
        app.stop();
    }


    @BeforeMethod
    public void startLogging(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void stopLogging(Method m) {
        logger.info("Stop test " + m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups groupsUI = app.group().all();
            Groups groupsDB = app.db().groups();
            assertThat(groupsUI, equalTo(groupsDB.stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts contactsUI = app.contact().all();
            Contacts contactsDB = app.db().contacts();
            contactsUI.sort((c1, c2) -> c1.getId() - c2.getId());
            contactsDB.sort((c1, c2) -> c1.getId() - c2.getId());
            assertThat(contactsUI, equalTo(contactsDB));
        }
    }
}