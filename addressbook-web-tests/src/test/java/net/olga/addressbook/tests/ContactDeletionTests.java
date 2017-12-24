package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().HomePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                            .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                            .withEmail("mig@navarro.io"));
        }
    }

    @Test
    public void testContactDeletion() {
        int index = 0;
        app.goTo().HomePage();
        List<ContactData> before = app.contact().list();
        app.contact().delete(index);
        app.goTo().HomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size(), after.size() + 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
