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
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address"), contactData.getAddress());
        type(By.name("address2"), contactData.getAddress2());
        attach(By.name("photo"), contactData.getPhoto());

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
            String allPhones = el.findElement(By.cssSelector(" td:nth-of-type(6)")).getText();
            String allEmails = el.findElement(By.cssSelector(" td:nth-of-type(5)")).getText();
            String address = el.findElement(By.cssSelector(" td:nth-of-type(4)")).getText();
            int id = Integer.parseInt(el.findElement(By.cssSelector(" td.center>input")).getAttribute("id"));
            contactsCash.add(new ContactData().withId(id).withName(name).
                    withSecondname(secondName).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactsCash);
    }

    public int count() {
        return wd.findElements(By.xpath("//input[@name='selected[]']")).size();
    }

    public String getAllPhonesFromEditForm() {
        return wd.findElement(By.name("home")).getAttribute("value") +
                wd.findElement(By.name("mobile")).getAttribute("value") +
                wd.findElement(By.name("work")).getAttribute("value");
    }

    public String getAllEmailseFromEditForm() {
        return wd.findElement(By.name("email")).getAttribute("value") +
                wd.findElement(By.name("email2")).getAttribute("value") +
                wd.findElement(By.name("email3")).getAttribute("value");
    }

    public String getAddressFromEditForm() {
        return wd.findElement(By.name("address")).getText();
    }

    public void details(int id) {
        wd.findElement(By.xpath("//a[@href='view.php?id=" + id + "']")).click();
    }

    public String dataFromDetailsPage() {
        return wd.findElement(By.id("content")).getText();
    }

    public String dataFromEditForm() {
        return wd.findElement(By.name("firstname")).getAttribute("value") +
                wd.findElement(By.name("middlename")).getAttribute("value") +
                wd.findElement(By.name("lastname")).getAttribute("value") +
                wd.findElement(By.name("address")).getAttribute("value") +
                getAllPhonesFromEditForm() +
                getAllEmailseFromEditForm();
    }
}
