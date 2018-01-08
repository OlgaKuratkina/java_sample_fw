package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import net.olga.addressbook.models.GroupData;
import net.olga.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensureContactAndGroup() {
//        More efficient to add contact to group in precondition,
//         then go in cycle through all the data trying to find out is there a contact with a group
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                    .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                    .withEmail("mig@navarro.io"));
        }
        GroupData group = app.db().groups().iterator().next();
        ContactData contact = app.db().contacts().iterator().next();
        app.contact().addToGroup(contact, group);
        }
    @Test
    public void testContactRemoveFromGroup() {
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();
        while (contact.getGroups().size() == 0) {
            contact = before.iterator().next();
        }
        Groups groups = contact.getGroups();
        GroupData group = groups.iterator().next();
        app.goTo().HomePage();
        app.contact().removeFromGroup(contact, group);
        app.goTo().HomePage();

        Contacts after = app.db().contacts();
        assertThat(before.size(), equalTo(after.size()));

        assertThat(before.withOut(contact).withAdded(contact.minusGroup(group)), equalTo(after));

    }
}
