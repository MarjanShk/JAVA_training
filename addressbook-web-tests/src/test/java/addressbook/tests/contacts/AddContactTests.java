package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.tests.TestBase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTests extends TestBase {

    @Test()
    public void testAddContact() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().
                withName("name1").withMiddlename("middlename1").withSecondname("secondname1").
                withHomePhoneNumber("0933456789").withEmail("example1@gmail.com").withGroup("name88");
        app.contact().create(contact, true);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact)));

    }
}
