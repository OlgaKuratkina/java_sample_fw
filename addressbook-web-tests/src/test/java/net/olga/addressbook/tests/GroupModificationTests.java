package net.olga.addressbook.tests;

import net.olga.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupModification() {
        int index = 0;
        List<GroupData> before = app.group().list();
        GroupData groupData = new GroupData()
                .withId(before.get(index).getId()).withName("Changed name").withHeader("changed header")
                .withFooter("changed footer");
        app.group().modify(index, groupData);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(groupData);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
