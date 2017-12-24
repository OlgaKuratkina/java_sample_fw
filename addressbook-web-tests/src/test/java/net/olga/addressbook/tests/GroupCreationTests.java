package net.olga.addressbook.tests;

import net.olga.addressbook.models.GroupData;
import net.olga.addressbook.models.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationDefault() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        assertThat(before.size(), equalTo(app.group().count()-1));
        Groups after = app.group().all();

//        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

        assertThat(before.withAdded(group
                .withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())), equalTo(after));
    }

}
