package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Admin on 23.03.2016.
 */
public class PhoneNumbersEmailAddressesTest extends TestBase {

    @Test
    public void testPhoneNumbersEmailAddresses() {
        ContactData contact = app.contact().all().iterator().next();
        app.contact().edit(contact.getId());
        String allPhonesFromEditForm = clean(app.contact().getAllPhonesFromEditForm());
        String allEmailsFromEditForm = clean(app.contact().getAllEmailseFromEditForm());
        String addressFromEditForm = clean(app.contact().getAddressFromEditForm());
        app.goTo().mainPage();
        String allPhonesFromMainPage = clean(contact.getAllPhones());
        String allEmailsFromMainPage = clean(contact.getAllEmails());
        String addressFromMainPage = clean(contact.getAddress());

        assertThat(allPhonesFromEditForm, equalTo(allPhonesFromMainPage));
        assertThat(allEmailsFromEditForm, equalTo(allEmailsFromMainPage));
        assertThat(addressFromEditForm, equalTo(addressFromMainPage));

    }

    public static String clean(String s){
        return s.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
