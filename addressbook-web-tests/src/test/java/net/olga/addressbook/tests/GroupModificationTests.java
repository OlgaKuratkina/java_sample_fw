package net.olga.addressbook.tests;

import net.olga.addressbook.models.GroupData;
import net.olga.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupModification() {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData groupData = new GroupData()
                .withId(modifiedGroup.getId()).withName("Changed name").withHeader("changed header")
                .withFooter("changed footer");
        app.group().modify(groupData);
        assertThat(before.size(), equalTo(app.group().count()));
        Groups after = app.db().groups();

        assertThat(before.withOut(modifiedGroup).withAdded(groupData), equalTo(after));

        verifyGroupListinUI();
    }
}
