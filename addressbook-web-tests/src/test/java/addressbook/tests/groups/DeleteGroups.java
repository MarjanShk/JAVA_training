package addressbook.tests.groups;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import addressbook.tests.TestBase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteGroups extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("name888").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testDeleteGroups() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after =app.db().groups() ;
        assertThat(after, equalTo(before.without(deletedGroup)));
    }

}
