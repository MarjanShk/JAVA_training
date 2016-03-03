package addressbook.tests.groups;

import addressbook.model.GroupData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class AddGroupTests extends TestBase {

    @Test
    public void testAddGroup() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initNewGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("name88", null, null));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }
}
