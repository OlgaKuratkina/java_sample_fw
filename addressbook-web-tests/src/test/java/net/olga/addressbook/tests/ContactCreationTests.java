package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavipHelper().gotoHomePage();
        app.getContactpHelper().initContactCreation();
        app.getContactpHelper().fillContactForm(new ContactData("Miguel", "Alberto", "Navarro", "Mig",
                "Mr", "Madrid Spain", "3409867784532", "mig@navarro.io"));
        app.getContactpHelper().submitContactCreation();
        app.getNavipHelper().gotoHomePage();
    }
}
