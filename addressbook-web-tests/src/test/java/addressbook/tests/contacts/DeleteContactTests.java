package addressbook.tests.contacts;

import addressbook.tests.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Admin on 29.02.2016.
 */
public class DeleteContactTests extends TestBase {
    @Test
    public void testDeleteFirstContact(){
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeleting();
    }

}
