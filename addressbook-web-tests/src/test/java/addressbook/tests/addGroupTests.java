package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class AddGroupTests extends TestBase {

    @Test
    public void testAddGroup() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initNewGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("name5", "header5", "footer5"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        app.getSessionHelper().logout();
    }


}
