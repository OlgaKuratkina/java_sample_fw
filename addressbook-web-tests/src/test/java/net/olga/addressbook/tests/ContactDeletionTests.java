package net.olga.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavipHelper().gotoHomePage();
        app.getContactpHelper().selectContact();
        app.getContactpHelper().deleteSelectedContacts();
        app.getContactpHelper().acceptAlert();
        app.getNavipHelper().gotoHomePage();
    }
}
