package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().HomePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("Miguel", "Alberto",
                    "Navarro", "Mig","Mr", null,
                    null, "mig@navarro.io"));
        }
    }

    @Test
    public void testContactModification() {
        int index = 0;
        app.goTo().HomePage();
        List<ContactData> before = app.contact().list();
        ContactData edited_contact = new ContactData(before.get(index).getId(), "Mateo", "Santana",
                "Barcelona Spain", "mateo@lalala.la");
        app.contact().modify(index, edited_contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(edited_contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
