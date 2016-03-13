package addressbook.tests.groups;

import addressbook.model.GroupData;
import addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteGroups extends TestBase {

    @Test
    public void testDeleteGroups() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("name888", "header888", "footer888"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroups(before.size() - 1);
        app.getGroupHelper().removeSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
