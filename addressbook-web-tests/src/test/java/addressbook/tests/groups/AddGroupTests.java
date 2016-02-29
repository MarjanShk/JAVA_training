package addressbook.tests.groups;

import addressbook.model.GroupData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class AddGroupTests extends TestBase {

    @Test
    public void testAddGroup() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initNewGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("name8", "header8", "footer8"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        app.getSessionHelper().logout();
    }
}
