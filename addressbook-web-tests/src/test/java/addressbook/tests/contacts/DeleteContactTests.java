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
public class DeleteContactTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().
                    withName("name1").withMiddlename("middlename1").withSecondname("secondname1").
                    withHomePhone("0933456789").withEmail("example1@gmail.com").withGroup("name88"), true);
        }
    }

    @Test() //enabled = false
    public void testDeleteContact() {
        app.goTo().mainPage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().mainPage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        before.sort((c1,c2)->c1.getId()-c2.getId());
        after.sort((c1,c2)->c1.getId()-c2.getId());
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
