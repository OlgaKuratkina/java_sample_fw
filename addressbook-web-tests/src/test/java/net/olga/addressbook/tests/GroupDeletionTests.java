package net.olga.addressbook.tests;

import net.olga.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        int index = 0;
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        List<GroupData> before = app.group().list();
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size(), after.size() + 1);
        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
