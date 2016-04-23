package appmanager;

import org.openqa.selenium.By;

/**
 * Created by Admin on 23.04.2016.
 */
public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }
    public void login(String username, String password){
        wd.get("http://localhost:8082/mantisbt-1.2.19/login_page.php");
        type(By.name("username"),username);
        type(By.name("password"), password);
        click(By.xpath("//input[@value='Login']"));
    }
}
