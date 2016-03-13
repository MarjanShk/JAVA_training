package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size()-1);
        ContactData contact = new ContactData("mod_name1", "mod_middlename1", "mod_secondname1", "000000000", "mod_example1@gmail.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(before.size(), after.size());

        before.remove(before.size()-1);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
