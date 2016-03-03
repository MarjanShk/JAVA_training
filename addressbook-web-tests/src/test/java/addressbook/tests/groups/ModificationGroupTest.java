package addressbook.tests.groups;

import addressbook.model.GroupData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Admin on 28.02.2016.
 */
public class ModificationGroupTest extends TestBase {

    @Test
    public void testModificationGroup(){
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroups();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("name8", "modheader", "modfooter"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
