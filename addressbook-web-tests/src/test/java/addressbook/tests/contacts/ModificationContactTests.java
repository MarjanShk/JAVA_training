package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Admin on 29.02.2016.
 */
public class ModificationContactTests extends TestBase {

    @Test
    public void testModificationContact() {
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("name1", "middlename1", "secondname1", "0933456789", "example1@gmail.com", "name88"), true);
        }
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().editFirstContact();
        app.getContactHelper().fillContactForm(new ContactData("mod_name1", "mod_middlename1", "mod_secondname1", "000000000", "mod_example1@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }

}
