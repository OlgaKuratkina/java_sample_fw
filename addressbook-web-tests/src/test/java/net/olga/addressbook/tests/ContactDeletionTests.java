package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                            .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                            .withEmail("mig@navarro.io"));
        }
    }

    @Test
    public void testContactDeletion() {
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().HomePage();
        assertThat(before.size(), equalTo(app.contact().count()+1));
        Contacts after = app.db().contacts();

        assertThat(before.withOut(deletedContact), equalTo(after));
    }
}
