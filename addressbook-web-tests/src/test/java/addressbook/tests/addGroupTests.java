package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class addGroupTests extends TestBase {

    @Test
    public void testAddGroup() {
        app.goToGroupPage();
        app.initNewGroupCreation(new GroupData("name4", "header4", "footer4"));
        app.returnToGroupPage();
        app.logout();
    }


}
