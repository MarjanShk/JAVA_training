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
public class DeleteContactTests extends TestBase {
    @Test
    public void testDeleteContact() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name1", "mddlename1", "secondname1", "0933456789", "example1@gmail.com", "name88"), true);
        }
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().submitContactDeleting();
        app.getNavigationHelper().goToMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }

}
