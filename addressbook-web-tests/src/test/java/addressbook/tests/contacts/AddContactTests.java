package addressbook.tests.contacts;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.tests.TestBase;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> contactsFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/generatedContacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
        }.getType());
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "contactsFromJSON")
    public void testAddContact(ContactData contact) {
        Contacts before = app.db().contacts();
        //File photo = new File("src/test/resources/photo.png");
        app.contact().create(contact, true);
        app.goTo().mainPage();
        Contacts after = app.db().contacts();
        before.sort((c1, c2) -> c1.getId() - c2.getId());
        after.sort((c1, c2) -> c1.getId() - c2.getId());
        assertThat(after, equalTo(before.withAdded(contact)));
        verifyContactListInUI();
    }
}
