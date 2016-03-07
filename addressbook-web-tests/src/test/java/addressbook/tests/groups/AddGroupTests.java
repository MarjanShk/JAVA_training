package addressbook.tests.groups;

import addressbook.model.GroupData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class AddGroupTests extends TestBase {

    @Test
    public void testAddGroup() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("name888", null, null));
    }
}