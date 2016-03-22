package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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

    public void select(int index) {
        wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();
    }

    public void selectById(int id) {
        wd.findElement(By.xpath("//input[@name='selected[]'][@id='" + id + "']")).click();

    }

    public void submitContactDeleting() {
        click(By.xpath("//input[@onclick='DeleteSel()']"));
        wd.switchTo().alert().accept();

    }

    public void edit(int id) {
        wd.findElement(By.xpath("//a[@href=\"edit.php?id=" + id + "\"]")).click();
    }

    public void submitContactModification() {
        click(By.xpath("//form[@action='edit.php']/input[@name='update']"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//table[@id='maintable']//input[@name='selected[]']"));
    }

    public void create(ContactData contactData, boolean creation) {
        initContactCreation();
        fillContactForm(contactData, creation);
        submitContactCreation();
        contactsCash = null;
    }

    public void delete(int index) {
        select(index);
        submitContactDeleting();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        submitContactDeleting();
        contactsCash = null;
    }

    public void modify(ContactData contact) {
        edit(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactsCash = null;
    }

    private Contacts contactsCash = null;

    public Contacts all() {
        if (contactsCash != null) {
            return new Contacts(contactsCash);
        }
        contactsCash = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement el : elements) {
            String secondName = el.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String name = el.findElement(By.cssSelector(" td:nth-of-type(3)")).getText();
            int id = Integer.parseInt(el.findElement(By.cssSelector(" td.center>input")).getAttribute("id"));
            contactsCash.add(new ContactData().withId(id).withName(name).withSecondname(secondName));
        }
        return new Contacts(contactsCash);
    }
}
