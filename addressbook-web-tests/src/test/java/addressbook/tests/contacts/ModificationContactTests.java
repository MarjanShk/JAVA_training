package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Admin on 29.02.2016.
 */
public class ModificationContactTests extends TestBase {

    @Test
    public void testModificationContact(){
        app.getContactHelper().editFirstContact();
        app.getContactHelper().fillContactForm(new ContactData("mod_name1", "mod_mddlename1", "mod_secondname1", "000000000", "mod_example1@gmail.com"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }

}
