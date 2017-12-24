package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        ContactData newContact = new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                .withEmail("mig@navarro.io");
        app.contact().create(newContact);
        app.goTo().HomePage();
        assertThat(before.size(), equalTo(app.contact().count()-1));
        Contacts after = app.contact().all();

        assertThat(before.withAdded(newContact
                .withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt())), equalTo(after));

    }
}
