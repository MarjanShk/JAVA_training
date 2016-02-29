package addressbook.appmanager;

import addressbook.tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Admin on 26.02.2016.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("Групи"));
    }
}
