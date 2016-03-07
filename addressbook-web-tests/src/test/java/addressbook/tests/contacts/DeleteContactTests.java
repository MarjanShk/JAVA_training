package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Admin on 29.02.2016.
 */
public class DeleteContactTests extends TestBase {
    @Test
    public void testDeleteFirstContact() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name1", "mddlename1", "secondname1", "0933456789", "example1@gmail.com", "name88"), true);
        }
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeleting();
    }

}
