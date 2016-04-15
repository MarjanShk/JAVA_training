package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Admin on 29.02.2016.
 */
public class ModificationContactTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().
                    withName("name1").withMiddlename("middlename1").withSecondname("secondname1").
                    withHomePhone("0933456789").withEmail("example1@gmail.com").withGroup("name88"), true);
        }
    }

    @Test
    public void testModificationContact() {
        app.goTo().mainPage();
        Contacts before = app.db().contacts();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId()).withName("mod_name1")
                .withMiddlename("mod_middlename1").withSecondname("mod_secondname1")
                .withHomePhone("0930000000").withEmail("mod_example1@gmail.com");
        app.contact().modify(contact);
        app.goTo().mainPage();
        Contacts after = app.db().contacts();
        before.remove(modifyContact);
        before.add(contact);
        before.sort((c1,c2)->c1.getId()-c2.getId());
        after.sort((c1,c2)->c1.getId()-c2.getId());
        assertThat(after, equalTo(before));
    }
}
