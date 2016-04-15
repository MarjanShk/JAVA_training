package addressbook.tests.groups;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Admin on 28.02.2016.
 */
public class ModificationGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("name888").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testModificationGroup() {
        Groups before = app.db().groups();
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifyGroup.getId()).withName("mod_name").withHeader("mod_header").withFooter("mod_footer");
        app.group().modify(group);
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifyGroup).withAdded(group)));
    }

}
