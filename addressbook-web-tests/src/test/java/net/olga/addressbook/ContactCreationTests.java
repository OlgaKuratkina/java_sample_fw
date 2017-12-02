package net.olga.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        gotoHomePage();
        initContactCreation();
        fillContactForm(new ContactData("Miguel", "Alberto", "Navarro", "Mig",
                "Mr", "Madrid Spain", "3409867784532", "mig@navarro.io"));
        submitContactCreation();
        gotoHomePage();
    }
}
