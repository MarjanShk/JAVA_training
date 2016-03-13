package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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
            try {
                /*проверяет существует ли группа с названием указанным при создании контакта.Если такой не существует
                select остается не заполненным.
                */
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } catch (NoSuchElementException ex) {
                return;
            }

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.xpath("//li[@class='all']/a[@href='edit.php']"));
    }

    public void selectContact(int index) {
        wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();
    }

    public void submitContactDeleting() {
        click(By.xpath("//input[@onclick='DeleteSel()']"));
        wd.switchTo().alert().accept();

    }

    public void editContact(int index) {
        wd.findElements(By.xpath("//a[contains(@href, 'edit.php?id=')]")).get(index).click();
        //click(By.xpath("//a[contains(@href, 'edit.php?id=')]"));
    }

    public void submitContactModification() {
        click(By.xpath("//form[@action='edit.php']/input[@name='update']"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//table[@id='maintable']//input[@name='selected[]']"));
    }

    public void createContact(ContactData contactData, boolean creation) {
        initContactCreation();
        fillContactForm(contactData, creation);
        submitContactCreation();

    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement el : elements) {
            String secondName = el.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String name = el.findElement(By.cssSelector(" td:nth-of-type(3)")).getText();
            int id = Integer.parseInt(el.findElement(By.cssSelector(" td.center>input")).getAttribute("id"));
            contacts.add(new ContactData(id, name, null, secondName, null, null, null));
        }
        return contacts;
    }
}
