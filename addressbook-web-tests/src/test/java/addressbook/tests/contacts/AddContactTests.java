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
                withName("name_C").withMiddlename("middle_C").withSecondname("second_C").
                withHomePhone("0933456789").withMobilePhone("0931122333").withWorkPhone("0954455666").
                withEmail("example1@gmail.com").withEmail2("example2@gmail.com").withEmail3("example3@gmail.com").
                withGroup("name88").withAddress("City street");
        app.contact().create(contact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withAdded(contact)));

    }
}
