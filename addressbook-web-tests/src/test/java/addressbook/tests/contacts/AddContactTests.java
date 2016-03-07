package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

    @Test
    public void testAddContact() {
        app.getContactHelper().createContact(new ContactData("name2", "middlename2", "secondname2", "0933456789", "example1@gmail.com", "name888"), true);
        app.getNavigationHelper().goToHomePage();
    }

}
