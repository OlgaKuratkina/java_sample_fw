package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();
        Set<ContactData> before = app.contact().all();
        ContactData newContact = new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                .withEmail("mig@navarro.io");
        app.contact().create(newContact);
        app.goTo().HomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before.size(), after.size()-1);

        newContact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(newContact);

        Assert.assertEquals(before, after);

    }
}
