package net.olga.addressbook.tests;

import net.olga.addressbook.models.GroupData;
import net.olga.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupDeletion() {
        app.goTo().groupPage();
        Groups before = app.db().groups();;
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(before.size(), equalTo(app.group().count()+1));
        Groups after = app.db().groups();

        assertThat(before.withOut(deletedGroup), equalTo(after));
    }

}
