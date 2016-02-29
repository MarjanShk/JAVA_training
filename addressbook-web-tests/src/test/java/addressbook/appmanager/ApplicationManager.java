package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 26.02.2016.
 */
public class ApplicationManager {
    FirefoxDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        sessionHelper = new SessionHelper(wd);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        wd.get("http://localhost:8081/addressbook/");
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
    
}
