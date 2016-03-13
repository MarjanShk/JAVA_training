package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class AddContactTests extends TestBase {

    @Test
    public void testAddContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("name222", "middlename2", "secondname2", "0933456789", "example1@gmail.com", "name888");
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
