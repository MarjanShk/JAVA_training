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
    @Test() //enabled = false
    public void testDeleteContact() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().
                    withName("name1").withMiddlename("mddlename1").withSecondname("secondname1").
                    withHomePhoneNumber("0933456789").withEmail("example1@gmail.com").withGroup("name88"), true);
        }
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().mainPage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), index);

        before.remove(index);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }

}
