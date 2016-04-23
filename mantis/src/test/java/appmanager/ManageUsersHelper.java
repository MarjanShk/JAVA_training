package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

/**
 * Created by Admin on 23.04.2016.
 */
public class ManageUsersHelper extends HelperBase {
    public String userEmail;
    public String userName;

    public ManageUsersHelper(ApplicationManager app) {
        super(app);
    }

    public void changePassword() throws IOException {
        click(By.xpath("//a[contains(@href, 'manage_user_page.php')]"));
        wd.findElement(By.xpath("//a[contains(text(), 'user' )]"));
        WebElement user = wd.findElement(By.xpath("//a[contains(text(), 'user' )]"));
        userName = user.getText();
        userEmail = wd.findElement(By.xpath("//a[contains(text(), 'user' )]/parent::td/following-sibling::td[2]")).getText();/////////////!!!!!
        user.click();
        click(By.xpath("//input[@value='Reset Password']"));
    }
}
