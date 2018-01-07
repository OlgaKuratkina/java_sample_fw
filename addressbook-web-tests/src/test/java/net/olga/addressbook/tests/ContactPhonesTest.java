package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhonesTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                    .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                    .withEmail("mig@navarro.io").withMobilePhone("3859629(8)"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);
        System.out.println(contactFromEditForm);

//        Contacts after = app.contact().all();

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesTest::cleaned)
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
