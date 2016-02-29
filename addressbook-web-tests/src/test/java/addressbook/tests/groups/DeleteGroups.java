package addressbook.tests.groups;

import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class DeleteGroups extends TestBase {

    @Test
    public void testDeleteGroups() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroups();
        app.getGroupHelper().removeSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
