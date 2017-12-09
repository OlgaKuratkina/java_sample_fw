package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavipHelper().gotoHomePage();
        if (! app.getContactpHelper().isThereContact()) {
            app.getContactpHelper().createContact(new ContactData("Miguel", "Alberto",
                    "Navarro", "Mig","Mr", null,
                    null, "mig@navarro.io"));
        }
        app.getNavipHelper().gotoHomePage();
        app.getContactpHelper().initContactModifyByIndex(1);
        ContactData contactData = new ContactData("Mateo", "Velazkez", "Santana", "Mat",
                "Mr", "Barcelona Spain", "398566907", "mateo@lalala.la");
        app.getContactpHelper().fillContactForm(contactData);
        app.getContactpHelper().submitContactModification();
        app.getNavipHelper().gotoHomePage();
    }
}
