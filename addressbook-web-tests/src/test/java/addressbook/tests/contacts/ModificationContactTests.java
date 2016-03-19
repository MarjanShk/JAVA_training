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
        if(! app.contact().isThereAContact()){
            app.contact().create(new ContactData().
                    withName("name1").withMiddlename("middlename1").withSecondname("secondname1").
                    withHomePhoneNumber("0933456789").withEmail("example1@gmail.com").withGroup("name88"), true);
        }
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().
                withName("mod_name1").withMiddlename("mod_mddlename1").withSecondname("mod_secondname1").
                withHomePhoneNumber("0930000000").withEmail("mod_example1@gmail.com");
        int index = before.size()-1;
        app.contact().modify(contact, index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();

        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }
}
