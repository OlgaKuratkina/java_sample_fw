package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                    .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                    .withEmail("mig@navarro.io"));
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData edited_contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("Sergio").withMiddleName("Moreno")
                .withLastName("Santana").withNick("Serg").withTitle("Dr")
                .withEmail("serg@santana.io");
        app.contact().modify(edited_contact);
        Contacts after = app.contact().all();
        assertThat(before.size(), equalTo(after.size()));

        assertThat(before.withOut(modifiedContact).withAdded(edited_contact), equalTo(after));
    }

}
