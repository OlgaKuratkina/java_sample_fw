package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressesTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                    .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                    .withEmail("mig@navarro.io").withEmail2("sfjkg@kfh.oi")
                    .withAddress("Barcelona Spain\n Calle Mayor"));
        }
    }

    @Test
    public void testContactEmails() {
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);
        System.out.println(contactFromEditForm);


        assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactFromEditForm.getAddress())));

    }


    public static String cleaned(String address) {
        return address.replaceAll("\\s", "").replaceAll("\\\\", "");
    }
}
