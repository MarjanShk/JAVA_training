package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

    @Test
    public void testAddContact() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("name1", "mddlename1", "secondname1", "0933456789", "example1@gmail.com"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();
        app.getSessionHelper().logout();
    }

}
