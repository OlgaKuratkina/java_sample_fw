package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        int index = 0;
        app.getNavipHelper().gotoHomePage();
        if (! app.getContactpHelper().isThereContact()) {
            app.getContactpHelper().createContact(new ContactData("Miguel", "Alberto",
                    "Navarro", "Mig","Mr", null,
                    null, "mig@navarro.io"));
        }
        app.getNavipHelper().gotoHomePage();
        List<ContactData> before = app.getContactpHelper().getContactList();
        app.getContactpHelper().initContactModifyByIndex(index);
        ContactData edited_contact = new ContactData(before.get(index).getId(), "Mateo", "Santana",
                "Barcelona Spain", "mateo@lalala.la");
        app.getContactpHelper().fillContactForm(edited_contact);
        app.getContactpHelper().submitContactModification();
        app.getNavipHelper().gotoHomePage();
        List<ContactData> after = app.getContactpHelper().getContactList();
        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(edited_contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
