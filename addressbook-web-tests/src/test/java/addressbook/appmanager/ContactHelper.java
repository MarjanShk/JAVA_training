package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Created by Admin on 29.02.2016.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getSecondname());
        type(By.name("home"), contactData.getHomePhoneNumber());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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

    public boolean isThereAContact(){
        return isElementPresent(By.xpath("//table[@id='maintable']//input[@name='selected[]']"));
    }
    public void createContact(ContactData contactData, boolean creation){
        initContactCreation();
        fillContactForm(contactData, creation);
        submitContactCreation();

    }
}
