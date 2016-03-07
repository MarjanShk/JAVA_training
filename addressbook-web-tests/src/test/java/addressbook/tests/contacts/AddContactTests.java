package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

    @Test
    public void testAddContact() {
        if(! app.getGroupHelper().isThereAGroup()){
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createGroup(new GroupData("name888", null, null));
        }
        app.getContactHelper().createContact(new ContactData("name1", "middlename1", "secondname1", "0933456789", "example1@gmail.com", "name888"), true);
        app.getNavigationHelper().goToHomePage();
    }

}
