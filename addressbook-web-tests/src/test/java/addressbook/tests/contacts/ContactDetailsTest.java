package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Admin on 23.03.2016.
 */
public class ContactDetailsTest extends TestBase {

    @Test
    public void testContactDetails() {
        ContactData contact = app.contact().all().iterator().next();
        app.contact().edit(contact.getId());
        String dataFromEditForm = clean(app.contact().dataFromEditForm());
        app.goTo().mainPage();
        app.contact().details(contact.getId());
        String dataFromDetailsPage = clean(app.contact().dataFromDetailsPage());

        assertThat(dataFromEditForm, equalTo(dataFromDetailsPage));
    }

    public static String clean(String s) {
        return s.replaceAll("H: |M: |W: |\\s+|[-()]+", "");
    }
}
