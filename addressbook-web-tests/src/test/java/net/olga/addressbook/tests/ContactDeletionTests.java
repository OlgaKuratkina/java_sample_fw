package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavipHelper().gotoHomePage();
        if (! app.getContactpHelper().isThereContact()) {
            app.getContactpHelper().createContact(new ContactData("Miguel", "Alberto",
                    "Navarro", "Mig","Mr", "Madrid Spain",
                    "3409867784532", "mig@navarro.io"));
        }
        app.getNavipHelper().gotoHomePage();
        app.getContactpHelper().selectContact();
        app.getContactpHelper().deleteSelectedContacts();
        app.getContactpHelper().acceptAlert();
        app.getNavipHelper().gotoHomePage();
    }
}
