package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        int index = 0;
        app.getNavipHelper().gotoHomePage();
        if (! app.getContactpHelper().isThereContact()) {
            app.getContactpHelper().createContact(new ContactData("Miguel", "Alberto",
                    "Navarro", "Mig","Mr", "Madrid Spain",
                    "3409867784532", "mig@navarro.io"));
        }
        app.getNavipHelper().gotoHomePage();
        List<ContactData> before = app.getContactpHelper().getContactList();
        app.getContactpHelper().selectContact(index);
        app.getContactpHelper().deleteSelectedContacts();
        app.getContactpHelper().acceptAlert();
        app.getNavipHelper().gotoHomePage();
        List<ContactData> after = app.getContactpHelper().getContactList();
        Assert.assertEquals(before.size(), after.size() + 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
