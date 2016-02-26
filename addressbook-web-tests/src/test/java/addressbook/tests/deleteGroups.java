package addressbook.tests;

import org.testng.annotations.Test;

public class deleteGroups extends TestBase {

    @Test
    public void testDeleteGroups() {
        app.goToGroupPage();
        app.selectGroups();
        app.removeSelectedGroups();
        app.returnToGroupPage();
        app.logout();
    }

}
