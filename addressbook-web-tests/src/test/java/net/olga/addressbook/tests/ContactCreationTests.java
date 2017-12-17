package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavipHelper().gotoHomePage();
        List<ContactData> before = app.getContactpHelper().getContactList();
        ContactData new_contact = new ContactData("Miguel", "Alberto", "Navarro", "Mig",
                "Mr", "Madrid Spain", "3409867784532", "mig@navarro.io");
        app.getContactpHelper().createContact(new_contact);
        app.getNavipHelper().gotoHomePage();
        List<ContactData> after = app.getContactpHelper().getContactList();
        before.add(new_contact);
        Assert.assertEquals(before.size(), after.size());

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
