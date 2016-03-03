package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Admin on 26.02.2016.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.xpath("//a[@href='group.php']"));
    }

    public void goToHomePage() {
        click(By.linkText("home page"));
    }
}
