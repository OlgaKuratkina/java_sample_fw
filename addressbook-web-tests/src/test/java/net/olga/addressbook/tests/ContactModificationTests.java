package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData edited_contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("Sergio").withMiddleName("Moreno")
                .withLastName("Santana").withNick("Serg").withTitle("Dr")
                .withEmail("serg@santana.io");
        app.contact().modify(edited_contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before.size(), after.size());

        before.remove(modifiedContact);
        before.add(edited_contact);
        Assert.assertEquals(before, after);
    }

}
