package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Admin on 29.02.2016.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getSecondname());
        type(By.name("home"), contactData.getHomePhoneNumber());
        type(By.name("email"), contactData.getEmail());
    }

    public void initContactCreation() {
        click(By.xpath("//li[@class='all']/a[@href='edit.php']"));
    }

    public void selectContact() {
        click(By.xpath("//input[@name='selected[]']"));
    }

    public void submitContactDeleting() {
        click(By.xpath("//input[@onclick='DeleteSel()']"));
        wd.switchTo().alert().accept();
    }

    public void editFirstContact() {
        click(By.xpath("//a[contains(@href, 'edit.php?id=')]"));
    }

    public void submitContactModification() {
        click(By.xpath("//form[@action='edit.php']/input[@name='update']"));
    }
}
