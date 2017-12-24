package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

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
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().HomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before.size(), after.size() + 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
