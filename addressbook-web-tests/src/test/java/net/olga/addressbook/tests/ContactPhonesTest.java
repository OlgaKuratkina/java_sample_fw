package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhonesTest extends TestBase {

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
    public void testContactCreation() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        ContactData contact = before.iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);

//        Contacts after = app.contact().all();

//        assertThat(before, equalTo(after));

    }
}
