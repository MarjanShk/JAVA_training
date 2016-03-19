package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class AddContactTests extends TestBase {

    @Test()
    public void testAddContact() {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().
                withName("name1").withMiddlename("mddlename1").withSecondname("secondname1").
                withHomePhoneNumber("0933456789").withEmail("example1@gmail.com").withGroup("name88");
        app.contact().create(contact, true);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
