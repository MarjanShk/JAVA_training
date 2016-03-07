package addressbook.tests.groups;

import addressbook.model.GroupData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class DeleteGroups extends TestBase {

    @Test
    public void testDeleteGroups() {
        app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("name888", "header888", "footer888"));
        }
        app.getGroupHelper().selectGroups();
        app.getGroupHelper().removeSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
